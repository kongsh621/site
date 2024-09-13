package com.example.project01.controller;

import com.example.project01.domain.*;
import com.example.project01.service.BoardService;
import com.example.project01.service.RedirectMessage;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Controller
@AllArgsConstructor
@Slf4j
public class BoardController {
    @Autowired
    private BoardService boardService;

    @Autowired
    private RedirectMessage redirectMessage;


    @GetMapping({"/list"})
    public String list(Model model, Criteria criteria, @SessionAttribute(name = "loginMember", required = false) MemberVO loginMember,
                       @ModelAttribute BoardVO boardVO) {
        log.info("list");
        model.addAttribute("loginSession", loginMember);

        long total = boardService.getTotal();

        log.info("total = {}", total);

        List<BoardVO> result = boardService.getListWithPaging(criteria);
        model.addAttribute("list", result);
        model.addAttribute("pageMaker", new PageMaker(criteria, total));

        if (loginMember != null && loginMember.getId() == 0){ // 관리자
            model.addAttribute("admin", loginMember);
            System.out.println("관리자입니다.");
        }

        return "siteForMember";
    }

    @PostMapping("/write")
    public String write(BoardVO board, RedirectAttributes redirectAttributes, Model model, MultipartFile file,
                        @SessionAttribute(name = "loginMember", required = false) MemberVO loginMember,
                        HttpServletRequest request) throws IOException {


        log.info("write = {}", board);
        model.addAttribute("loginSession", loginMember);
        // 파일은 board가 아니라 MultipartFile 로 읽어와서 처리 후 데이터베이스에 추가한다

        log.info("-----------파일 받고 바로" +file.getOriginalFilename());
        if (file != null && !file.isEmpty()) { // 첨부파일이 있다면
            // 첨부파일 저장 경로
            String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

            // 랜덤으로 파일 이름 설정
            UUID uuid = UUID.randomUUID();
            String fileOrgin = file.getOriginalFilename();
            String fileName = uuid + "_" + fileOrgin;

            // 파일 껍데기
            File saveFile = new File(projectPath, fileName);
            file.transferTo(saveFile);

            // DB에 값 넣기
            board.setFilename(fileName);
            board.setFilepath("/files/" + fileName);

            boardService.register(board, file);
            model.addAttribute("file", file);

        } else { // 첨부파일 없을 때
            boardService.register(board, file);
        }

        redirectAttributes.addAttribute("id", board.getId());
        redirectAttributes.addFlashAttribute("result",
            new ResultDTO(true, "write"));


        return "redirect:/read";
    }

    @GetMapping("/write")
    public String write(@SessionAttribute(name = "loginMember", required = false) MemberVO loginMember, Model model,
                        RedirectAttributes redirectAttributes) {
        model.addAttribute("loginSession", loginMember);

        // 로그인 해야 글 작성 가능
        if (loginMember == null) {
            redirectAttributes.addAttribute("message", "글 작성은 회원만 가능합니다.");
            return "redirect:/list";
        }
        return "siteForMemberWrite";
    }

    @GetMapping("/read")
    public String read(@RequestParam Long id, Criteria criteria, Model model, HttpServletRequest request,
                       @SessionAttribute(name = "loginMember", required = false) MemberVO loginMember,
                       HttpServletResponse response, RedirectAttributes ra) {
        log.info("read = {}", id);
        model.addAttribute("loginSession", loginMember);

        try {
            // get 메서드를 이용해 id로 게시글 정보를 불러온다
            BoardVO boardResult = boardService.get(id);

            // 파일이 존재하면 이미지로 출력
            // 다운 버튼 표시
            if (boardResult.getFilename() != null && !boardResult.getFilename().isEmpty()){
                log.info("파일이 존재" + boardResult.getFilename());
                model.addAttribute("imgSession", boardResult.getFilepath());
                model.addAttribute("attachedFile", boardResult);
            }

            // 만약 회원이라면, 작성자일 때 writer 세션에 저장해서 다른 양식 출력
            if (loginMember != null && loginMember.getNickname().equals(boardResult.getWriter())) {
                // 일단 null인지부터 확인해줘야 오류 안 난다
                model.addAttribute("writer", loginMember);
            }

            // 댓글 출력
            HashMap<String, Object> map = new HashMap<>();
            map.put("criteria", criteria);
            map.put("postid", id);

            // 댓글 불러와서 list로 보냄
            List<BoardCommentVO> list = boardService.getCommentWithPaging(map);
            model.addAttribute("list", list);

            long total = boardService.getTotal();
            model.addAttribute("pageMaker", new PageMaker(criteria, total));

            long comTotal = boardService.getCommentTotal(id);
            model.addAttribute("commentPageMaker", new PageMaker(criteria, comTotal));

            // 조회수 (회원/비회원 동일하게 1번씩만 조회수 집계, 브라우저 종료시 초기화 되는 단점이 있다.)
            Cookie viewCount = addHits(request, id, response);
            if (viewCount != null)
                response.addCookie(viewCount);

            model.addAttribute("board", boardResult);
            return "siteForMemberRead";
        } catch (Exception e){
            return redirectMessage.sendRedirectExceptId(ra, "시스템 오류가 발생했습니다.", "/list");
        }
    }

    // 조회수 1 증가
    public Cookie addHits(HttpServletRequest request, Long id, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        System.out.println("cookies = " + Arrays.toString(cookies));

        if (cookies == null) { // 쿠키 없으면 생성
            boardService.updateHits(id);
            Cookie newCookie = new Cookie("view_count", id.toString());
            newCookie.setPath("/");
            return newCookie;
        } else {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("view_count")){
                    if (!cookie.getValue().contains(id.toString())){
                        // 쿠키에 조회한 기록 없으면 조회수 +1
                        boardService.updateHits(id);
                        cookie.setValue(cookie.getValue() + "_" + id);
                        return cookie;
                    } else return null;
                }
            }
            // view_count 가 없음
            boardService.updateHits(id);
            Cookie newCookie = new Cookie("view_count", id.toString());
            newCookie.setPath("/");
            return newCookie;
        }
    }

    @RequestMapping("/writeComment")
    public String writeBoardComment(@ModelAttribute BoardCommentVO boardCommentVO, RedirectAttributes ra,
                                    @SessionAttribute(name = "loginMember", required = false) MemberVO loginMember,
                                     Model model, @RequestParam int comPage){
        model.addAttribute("loginSession", loginMember);

        try {
            boolean result = boardService.writeComment(boardCommentVO); // 새 댓글 추가
            Long postId = boardCommentVO.getPostid();
            Criteria criteria = new Criteria(comPage, 10, null);

            // 댓글 페이징
            if (result){
                return redirectMessage.sendRedirect(ra, postId, criteria, "댓글을 작성하였습니다.", "/read");
            }
            return redirectMessage.sendRedirect(ra, postId, criteria, "댓글 작성에 실패했습니다.", "/read");
        } catch (Exception e){
            return redirectMessage.sendRedirect(ra, boardCommentVO.getPostid(), null, "시스템 오류가 발생했습니다.", "/read");
        }
    }

    @PostMapping("/update")
    public String update(BoardVO board, Criteria criteria, RedirectAttributes ra,
                         @SessionAttribute(name = "loginMember", required = false) MemberVO loginMember, Model model) {
        log.info("update = {}", board);
        model.addAttribute("loginSession", loginMember);

        try {
            Long postId = board.getId();
            if (boardService.update(board)) {
                ra.addFlashAttribute("result", new ResultDTO(true, "update"));
            }
            return redirectMessage.sendRedirect(ra, postId, criteria, "업데이트 되었습니다.", "/read");
        } catch (Exception e){
            return redirectMessage.sendRedirect(ra, board.getId(), criteria, "시스템 오류가 발생했습니다.", "/read");
        }
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") Long id, Model model,
                         @SessionAttribute(name = "loginMember", required = false) MemberVO loginMember) {
        log.info("update = {}", id);
        model.addAttribute("loginSession", loginMember);
        model.addAttribute("board", boardService.get(id));
        return "siteForMemberWrite";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam("id") Long id, Criteria criteria, RedirectAttributes ra,
                         @SessionAttribute(name = "loginMember", required = false) MemberVO loginMember, Model model)  {
        log.info("delete = {}", id);
        model.addAttribute("loginSession", loginMember);

        BoardVO file = boardService.findFiles(id);

        if (boardService.delete(id))
            ra.addFlashAttribute("result",
                new ResultDTO(true, "delete"));

        // 파일이 존재하면
        if (file != null) {
            // 불러온 첨부파일에 대해 해당 파일들을 로컬디스크에서 지워준다
            File attachedFile = new File("C:\\kjy\\workspace\\project1\\src\\main\\resources\\static\\files" + File.separator + file.getFilename());

            if (attachedFile.exists()) {
                if (attachedFile.delete()) {
                    System.out.println("파일이 삭제되었습니다");
                } else {
                    System.out.println("파일이 삭제되지 않았습니다");
                }
            }
        }
        return redirectMessage.sendRedirect(ra, null, criteria, "게시물이 삭제되었습니다", "/list");
    }

    @GetMapping("/deleteComment")
    public String deleteComment(@ModelAttribute BoardCommentVO commentVO,
                                @ModelAttribute Criteria criteria, RedirectAttributes ra){
        // 보낸 값들 읽어서 리다이렉트 주소에 사용
        long id = commentVO.getId();
        long postId = commentVO.getPostid();

        if (boardService.deleteComment(id))
            return redirectMessage.sendRedirect(ra, postId, criteria, "댓글이 삭제되었습니다.", "/read");
        else
            return redirectMessage.sendRedirect(ra, postId, criteria, "댓글을 삭제하지 못했습니다.", "/read");
    }

    @RequestMapping("/search")
    public String listSearch (@RequestParam String search, Model model, Criteria criteria,
                              @SessionAttribute(name = "loginMember", required = false) MemberVO loginMember,
                              RedirectAttributes ra){
        model.addAttribute("loginSession", loginMember);

        List<BoardVO> result = null;
        // 검색어와 페이지 객체를 맵핑해서 리스트를 받아옴
        HashMap<String, Object> map = new HashMap<>();
        map.put("search", search);
        map.put("criteria", criteria);

        try {
            if (search.length() <= 2) { // 3글자 이상
                return redirectMessage.sendRedirectExceptId(ra,"검색어를 최소 3글자 이상 입력해 주세요.", "/list");
            }
            result = boardService.findByText(map);
            long total = boardService.getSearchTotal(search);

            if (result != null) {
                model.addAttribute("list", result);
                model.addAttribute("pageMaker", new PageMaker(criteria, total));
                return "/siteForMember";
            }
            model.addAttribute("emptyResult", "Y");
            return "/siteForMember";
        } catch (Exception e) {
            return redirectMessage.sendRedirectExceptId(ra, "시스템에 문제가 발생했습니다.", "/list");
        }
    }
}
