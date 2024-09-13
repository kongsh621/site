package com.example.project01.controller;

import com.example.project01.domain.*;
import com.example.project01.service.InfoBoardService;
import com.example.project01.service.RedirectMessage;
import com.example.project01.service.UserService;
import com.oracle.wls.shaded.org.apache.xpath.operations.Mod;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@Controller
@RequestMapping("/infoboard/")
public class InfoBoardController {
    @Autowired
    private InfoBoardService infoBoardService;

    @Autowired
    private RedirectMessage redirect;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(@ModelAttribute InfoBoardVO infoBoardVO, Model model, Criteria criteria,
                        @SessionAttribute(name = "loginMember", required = false) MemberVO loginMember,
                        @SessionAttribute(name = "favorites", required = false)Set<JjimVO> favorites,
                        HttpSession session, HttpServletRequest request){
        model.addAttribute("loginSession", loginMember);
        // 세션에 현재 위치 저장 -> 찜 삭제 했을 때 다시 돌아오기 위함
        session.setAttribute("previousPage", request.getRequestURI());

        // 로그아웃 해서 세션이 지워졌으면 다시 만들어준다
        if (favorites == null){
            session.setAttribute("favorites", infoBoardService.getJjimList());
        }
        model.addAttribute("favorites", favorites); // 찜 여부 표시를 위해 뷰로 보냄


        // 관리자
        if (loginMember != null && loginMember.getId() == 0) { // 일단 null인지부터 확인해줘야 오류 안 난다
            model.addAttribute("admin", loginMember);
        }

        Criteria criteria1 = new Criteria(criteria.getPage(), 4, null);
        int total = infoBoardService.getTotal(null); // 전체 개수

        // 일반 정렬
        List<InfoBoardVO> result = infoBoardService.getListWithPaging(criteria1);
        model.addAttribute("list", result);
        model.addAttribute("pageMaker", new PageMaker(criteria1, total));
        return "siteInfo";
    }

    // 버튼 정렬
    @RequestMapping("/sort")
    public String  sortByType(@SessionAttribute(name = "loginMember", required = false) MemberVO loginMember,
                              @RequestParam("type")String type, Criteria criteria, Model model,
                              @SessionAttribute(name = "favorites", required = false)Set<JjimVO> favorites){
        model.addAttribute("loginSession", loginMember);
        model.addAttribute("favorites", favorites);

        // 추천 페이지에서 종류 눌렀을 때
        Map<String, Object> map = new HashMap<>();
        Criteria criteria1 = new Criteria(criteria.getPage(), 4, null);
        map.put("type", type);
        map.put("criteria", criteria1);

        List<InfoBoardVO> sorted = infoBoardService.sortByType(map);
        int total = infoBoardService.getTotal(type);
        log.info("total = {}", total);

        model.addAttribute("button", type);
        model.addAttribute("list", sorted);
        model.addAttribute("pageMaker", new PageMaker(criteria1, total));
        return "siteInfo";
    }

    @RequestMapping("/search")
    public String searchInfo(@RequestParam String search, Model model, Criteria criteria,
                             @SessionAttribute(name = "loginMember", required = false) MemberVO loginMember,
                             RedirectAttributes redirectAttributes,
                             @SessionAttribute(name = "favorites", required = false)Set<JjimVO> favorites) {
        model.addAttribute("loginSession", loginMember);
        model.addAttribute("favorites", favorites);

        Criteria criteria1 = new Criteria(criteria.getPage(), 5, null);

        // 검색어와 페이지 객체를 맵핑해서 리스트를 받아옴
        HashMap<String, Object> map = new HashMap<>();
        map.put("search", search);
        map.put("criteria", criteria1);

        List<InfoBoardVO> result = null;
        try {
            if (search.length() <= 1) { // 2글자 이상
                return redirect.sendRedirectExceptId(redirectAttributes, "검색어를 최소 2글자 이상 입력해 주세요.", "/infoboard/");
            }
            result = infoBoardService.searchByTitle(map);
            long total = infoBoardService.getSearchTotal(search);

            if (result != null) {
                model.addAttribute("list", result);
                model.addAttribute("pageMaker", new PageMaker(criteria1, total));
                return "siteInfo";
            }
        } catch (Exception e) {
            return redirect.sendRedirectExceptId(redirectAttributes, "시스템에 문제가 발생했습니다", "/infoboard/");
        }
        return "siteInfo";
    }

    @PostMapping("/writeinfo")
    public String writeInfo(@ModelAttribute InfoBoardVO infoBoard,
                            @SessionAttribute(name = "loginMember", required = false) MemberVO loginMember,
                            Model model, RedirectAttributes ra, Criteria criteria){
        log.info("write = {}", infoBoard);
        model.addAttribute("loginSession", loginMember);

        boolean result = false;
        try {
            result = infoBoardService.write(infoBoard);
            if (result){
                return redirect.sendRedirect(ra, infoBoard.getId(), criteria, "성공적으로 글을 작성하였습니다.", "/infoboard/readinfo");
            } else {
                ra.addFlashAttribute("resultDTO", new ResultDTO(false, "write", infoBoard));
                return redirect.sendRedirectExceptId(ra, "글을 작성하지 못 했습니다.", "/infoboard/writeinfo");
            }
        }catch (Exception e) {
            return redirect.sendRedirectExceptId(ra, "시스템에 문제가 발생했습니다", "/infoboard/writeinfo");
        }
    }

    @GetMapping("/writeinfo")
    public String writeInfoForm(@SessionAttribute(name = "loginMember", required = false) MemberVO loginMember,
                            Model model, @ModelAttribute(name = "resultDTO") ResultDTO resultDTO){
        model.addAttribute("loginSession", loginMember);
        log.info("writeInfo ResultDTO" + resultDTO);
        if (resultDTO.getResult() != null) { // 리다이렉트 된 경우 기존 입력내용 보냄
            model.addAttribute("infoboard", resultDTO);
        }
        return "siteInfoWrite";
    }

    @GetMapping("/readinfo")
    public String readInfo(@SessionAttribute(name = "loginMember", required = false) MemberVO loginMember,
                           Model model, @ModelAttribute InfoBoardVO infoBoardVO,
                           Criteria criteria){
        model.addAttribute("loginSession", loginMember);
        InfoBoardVO result = infoBoardService.readById(infoBoardVO.getId());
        model.addAttribute("infoboardVO", result);

        if (loginMember != null && loginMember.getId() == 0) { // 일단 null인지부터 확인해줘야 오류 안 난다
            model.addAttribute("admin", loginMember);
        }

        String type = null;
        long total = infoBoardService.getTotal(type);
        model.addAttribute("pageMaker", new PageMaker(criteria, total));

        return "siteReadForm";
    }

    @PostMapping("/rate")
    public String leaveRating(@ModelAttribute InfoBoardVO infoBoardVO, Model model, RedirectAttributes ra,
                              Criteria criteria, @SessionAttribute(name = "loginMember", required = false) MemberVO loginMember) {
        model.addAttribute("loginSession", loginMember);

        try {
            Long id = infoBoardVO.getId();
            // 평점 남길 수 있는지 확인
            if (loginMember == null) { // 로그인 했는지
                return redirect.sendRedirect(ra, id, criteria, "로그인 후 이용하실 수 있습니다.","redirect:/infoboard/readinfo");
            }

            // ratingsVO에 id 2개와 rating을 넣고 insert
            RatingsVO ratingsVO = new RatingsVO();
            ratingsVO.setRating((double)infoBoardVO.getRate());
            ratingsVO.setBoard_id(id);
            ratingsVO.setMember_id(loginMember.getId());

            // 이미 작품 평점 기록이 존재하면 리턴시킨다.
            if (infoBoardService.isExistRating(ratingsVO)){
                return redirect.sendRedirect(ra, id, criteria, "평점은 한 번만 남길 수 있습니다.", "redirect:/infoboard/readinfo");
            }

            boolean insertResult = infoBoardService.insertRating(ratingsVO);
            if (!insertResult){
                return redirect.sendRedirect(ra, id, criteria, "평점이 반영되지 않았습니다.", "redirect:/infoboard/readinfo");
            }

            System.out.println("InsertRating succeed");
            System.out.println(infoBoardService.getRatingsList());

            // 작품별 평균 평점을 구한다 ( 평균(rating에), 작품당 평가인원(count에) 가져옴)
            RatingsVO getAvgByBoard = infoBoardService.getAvgByBoard(ratingsVO.getBoard_id());

            // infoboardVO에 넣어 업데이트 후 화면으로 돌려보낸다
            infoBoardVO.setTotal_rate(getAvgByBoard.getRating());
            infoBoardVO.setTotal(getAvgByBoard.getCount());
            boolean updateTotalRateResult = infoBoardService.updateTotalRate(infoBoardVO);

            if (!updateTotalRateResult){
                return redirect.sendRedirect(ra, id, criteria, "반영된 평점을 읽어오지 못했습니다.", "redirect:/infoboard/readinfo");
            }
            return redirect.sendRedirect(ra, id, criteria, "평점이 성공적으로 반영되었습니다.", "redirect:/infoboard/readinfo");
        } catch (Exception e){
            return redirect.sendRedirectExceptId(ra, "시스템에 문제가 발생했습니다", "/infoboard/readinfo");
        }
    }


    @PostMapping("/updateinfo")
    public String updateInfo(@SessionAttribute(name = "loginMember", required = false) MemberVO loginMember,
                             Model model, RedirectAttributes ra, @ModelAttribute(name = "resultDTO") ResultDTO resultDTO,
                             @ModelAttribute InfoBoardVO infoBoardVO, Criteria criteria){
        log.info("update info = {}", infoBoardVO);
        model.addAttribute("loginSession", loginMember);

        boolean result = false;
        try {
            result = infoBoardService.update(infoBoardVO);
            if (result) {
                ra.addAttribute("id", infoBoardVO.getId());
                ra.addAttribute("page", criteria.getPage());
                ra.addAttribute("message", "글을 수정하였습니다.");
                return "redirect:/infoboard/readinfo";
            }
            if (resultDTO.isSuccess()){
                ra.addAttribute("message", "글을 작성하지 못 했습니다.");
                ra.addFlashAttribute("resultDTO", new ResultDTO(false, "update", infoBoardVO));
                return "redirect:/infoboard/updateinfo";
            }
        } catch (Exception e) {
            ra.addAttribute("message", "시스템에 문제가 발생했습니다");
             return "redirect:/infoboard/updateinfo";
        }
        return "redirect:/infoboard/";
    }
    @GetMapping("/updateinfo")
    public String updateInfoForm(@SessionAttribute(name = "loginMember", required = false) MemberVO loginMember,
                                 Model model, RedirectAttributes ra, @ModelAttribute(name = "resultDTO") ResultDTO resultDTO,
                                 @ModelAttribute InfoBoardVO infoBoardVO, Criteria criteria){
        model.addAttribute("loginSession", loginMember);
        // 받은 id로 데이터 읽어와 보낸다
        InfoBoardVO inforboard = infoBoardService.readById(infoBoardVO.getId());
        if (resultDTO.getResult() != null && !resultDTO.isSuccess()) { // 리다이렉트 된 경우 리다이렉트 된 입력내용 보냄
            model.addAttribute("resultDTO", resultDTO);
        } else { // 그냥 업데이트면 기존 글 내용 보냄
            model.addAttribute("infoboard", inforboard);
        }
        System.out.println("resultDTO = " + resultDTO + "infoboard" + infoBoardVO);
        return "siteInfoWrite";
    }

    @GetMapping("/deleteinfo")
    public String deleteInfo(@SessionAttribute(name = "loginMember", required = false) MemberVO loginMember,
                             Model model, RedirectAttributes ra, @RequestParam("id") Long id,
                             Criteria criteria){
        log.info("delete info");

        model.addAttribute("loginSession", loginMember);
        System.out.println("id -------" + id);
        System.out.println("page -------" + criteria.getPage());
        boolean result = false;
        try {
            result = infoBoardService.delete(id);
            if (!result){
                ra.addAttribute("id", id);
                ra.addAttribute("page", criteria.getPage());
                ra.addAttribute("message", "게시물을 삭제하지 못했습니다.");
                return "redirect:/infoboard/read/";
            } else {
                ra.addAttribute("page", criteria.getPage());
                ra.addAttribute("message", "게시물이 삭제되었습니다.");
                ra.addFlashAttribute("result", new ResultDTO(true, "infoDelete"));
                return "redirect:/infoboard/";
            }
        } catch (Exception e){
            ra.addAttribute("page", criteria.getPage());
            ra.addAttribute("message", "시스템에 문제가 발생했습니다");
            return "redirect:/infoboard/";
        }
    }


    @GetMapping("/like")
    public String change_is_like(@ModelAttribute JjimVO jjimVO, RedirectAttributes ra, Model model,
                                 @SessionAttribute(name = "loginMember", required = false) MemberVO loginMember,
                                 HttpSession session, Criteria criteria, HttpServletRequest request){
        model.addAttribute("loginSession", loginMember);
        System.out.println("JjimVO = " + jjimVO);

        try {
            // 찜 테이블에 존재하는지 확인
            boolean isExitst = infoBoardService.isExistJjim(jjimVO);
            System.out.println("Is this <borad-member> record Exists in Jjim table? = " + isExitst);
            if (isExitst){
                // 찜 유무 변경/ 찜으로 등록
                if (jjimVO.getIs_like().equals("Y")){
                    // 존재한다면 상태만 바꿔줌
                    boolean result = infoBoardService.changeIsLike(jjimVO);
                    System.out.println("Updated Jjim = " + result);
                    if (result){
                        Set<JjimVO> list = infoBoardService.getJjimList(); // 상태 바꾼 거 가져온다
                        session.setAttribute("favorites", list); // 세션에 찜 리스트 추가
                        return redirect.sendRedirect(ra, null, criteria, "관심 목록에 추가하였습니다.", "/infoboard/");
                    } else {
                        return redirect.sendRedirect(ra, null, criteria, "관심 목록에 추가하지 못했습니다.", "/infoboard/");
                    }
                } else {
                    // 찜에서 삭제
                    boolean result = infoBoardService.changeIsLike(jjimVO);
                    System.out.println("Updated Jjim = " + result);

                    if (result){
                        Set<JjimVO> list = infoBoardService.getJjimList(); // 상태 바꾼 거 가져온다
                        session.setAttribute("favorites", list); // 세션에 찜 리스트 추가

                        // 현재 경로 가져오기
                        //관심 목록, 추천 페이지, 정보 게시판에서 삭제 가능해서 그전 주소로 리다이렉트 하도록 함
                        String requestUri = (String) session.getAttribute("previousPage");
                        if (requestUri != null){
                            return redirect.sendRedirect(ra, null, criteria, "관심 목록에서 삭제하였습니다.", requestUri);
                        }
                        return "redirect:/";
                    }else
                        return redirect.sendRedirect(ra, null, criteria, "관심 목록에서 삭제하지 못했습니다.", "/infoboard/");
                }
            } else {
                // 테이블에 존재하지 않는다면 새로 추가해준다
                JjimVO result = infoBoardService.insertJjim(jjimVO);
                System.out.println("Inserted Jjim = " + result);

                if (result != null){
                    session.setAttribute("favorites", result); // 세션에 찜 리스트 추가
                    return redirect.sendRedirect(ra, null, criteria, "관심 목록에 추가하였습니다.", "/infoboard/");
                } else {
                    return redirect.sendRedirect(ra, null, criteria, "관심 목록에 추가하지 못했습니다.", "/infoboard/");
                }
            }
        } catch (Exception e){
            return redirect.sendRedirectExceptId(ra, "시스템 오류가 발생하여 실패했습니다.", "/index");
        }
    }

    @GetMapping("/favorites")
    public String favoritesList(@SessionAttribute(name = "loginMember", required = false) MemberVO loginMember, Model model,
                         @SessionAttribute(name = "favorites", required = false)Set<JjimVO> favorites, Criteria criteria,
                                HttpSession session, HttpServletRequest request){
        model.addAttribute("loginSession", loginMember);
        model.addAttribute("favorites", favorites);

        if (loginMember != null){
            Criteria criteria1 = new Criteria(criteria.getPage(), 8, null);
            HashMap<String, Object> map = new HashMap<>();
            map.put("criteria", criteria1);
            map.put("id", loginMember.getId());

            Set<InfoBoardVO> result = infoBoardService.getJjimListWithPaging(map);
            int total = infoBoardService.getJjimTotal();

            model.addAttribute("list", result);
            model.addAttribute("pageMaker", new PageMaker(criteria1, total));

            // 세션에 현재 위치 저장 -> 찜 삭제 했을 때 다시 돌아오기 위함
            session.setAttribute("previousPage", request.getRequestURI());
            return "siteFavorites";
        } else {
            return "redirect:/member/login";
        }
    }
}
