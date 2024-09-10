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

//import static com.amazonaws.util.StringUtils.toInteger;

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
        model.addAttribute("favorites", favorites);
        System.out.println("favorites-----" + favorites);

        Criteria criteria1 = new Criteria(criteria.getPage(), 4, null);
        String type = null;
        int total = infoBoardService.getTotal(type);

        if (loginMember != null && loginMember.getId() == 0) { // 일단 null인지부터 확인해줘야 오류 안 난다
            //if (loginMember.getId() == 0) { // 관리자일 때
            model.addAttribute("admin", loginMember);
            // }
        }
        // 일반 정렬
        List<InfoBoardVO> result = infoBoardService.getListWithPaging(criteria1);
        model.addAttribute("list", result);
        model.addAttribute("pageMaker", new PageMaker(criteria1, total));


        return "siteInfo";
    }

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

        List<InfoBoardVO> lbt = infoBoardService.sortByType(map);
        int total = infoBoardService.getTotal(type);
        log.info("total = {}", total);

        model.addAttribute("button", true);
        model.addAttribute("list", lbt);
        model.addAttribute("pageMaker", new PageMaker(criteria1, total));
        return "siteInfo";
    }
    /*@ResponseBody
    @PostMapping("/sort")
    public Map<String, Object> sort(@RequestBody Map<String, Object> requestBody){
        Map<String, Object> map = new HashMap<>();
        Criteria criteria1 = new Criteria(1, 4, null);
        String type = (String) requestBody.get("type");
        map.put("type", type);
        map.put("criteria", criteria1);
        log.info("type = {}", type);
        log.info("requestBody = {}", requestBody);

        Map<String, Object> map1 = new HashMap<>();
        List<InfoBoardVO> lbt = infoBoardService.sortByType(map);
        log.info("lbt------= {}", lbt);
        int total = infoBoardService.getTotal(type);
        log.info("total = {}", total);

        map1.put("list", lbt);
        map1.put("pageMaker", new PageMaker(criteria1, total));

        return map1;
    }*/

    @RequestMapping("/search")
    public String searchInfo(@RequestParam String search, Model model, Criteria criteria,
                             @SessionAttribute(name = "loginMember", required = false) MemberVO loginMember,
                             RedirectAttributes redirectAttributes,
                             @SessionAttribute(name = "favorites", required = false)Set<JjimVO> favorites) {
        model.addAttribute("loginSession", loginMember);
        model.addAttribute("favorites", favorites);

        Criteria criteria1 = new Criteria(criteria.getPage(), 5, null);

        List<InfoBoardVO> result = null;
        // 검색어와 페이지 객체를 맵핑해서 리스트를 받아옴
        HashMap<String, Object> map = new HashMap<>();
        map.put("search", search);
        map.put("criteria", criteria1);
        System.out.println("Map!!!" + map);
        try {
            if (search.length() <= 1) { // 2글자 이상
                redirectAttributes.addAttribute("message", "검색어를 최소 2글자 이상 입력해 주세요.");
                return "redirect:/infoboard/";
            }
            result = infoBoardService.searchByTitle(map);
            long total = infoBoardService.getSearchTotal(search);

            if (result != null) {
                model.addAttribute("list", result);
                model.addAttribute("pageMaker", new PageMaker(criteria1, total));
                return "siteInfo";
            }

        } catch (Exception e) {
            redirectAttributes.addAttribute("message", "시스템에 문제가 발생했습니다");
            return "redirect:/infoboard/";
        }
        return "siteInfo";
    }

    @PostMapping("/writeinfo")
    public String writeInfo(@ModelAttribute InfoBoardVO infoBoard, @SessionAttribute(name = "loginMember", required = false) MemberVO loginMember,
                            Model model, RedirectAttributes ra){
        log.info("write = {}", infoBoard);
        model.addAttribute("loginSession", loginMember);

        boolean result = false;
        try {
            result = infoBoardService.write(infoBoard);
            if (result){
                ra.addAttribute("id", infoBoard.getId());
                ra.addAttribute("message", "성공적으로 글을 작성하였습니다.");
                return "redirect:/infoboard/readinfo";
            } else {
                ra.addAttribute("message", "글을 작성하지 못 했습니다.");
                ra.addFlashAttribute("resultDTO", new ResultDTO(false, "write", infoBoard));
                return "redirect:/infoboard/writeinfo";
            }
        }catch (Exception e) {
            ra.addAttribute("message", "시스템에 문제가 발생했습니다");
            return "redirect:/infoboard/writeinfo";
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

    @PostMapping("/rate")
    public String leaveRating(@ModelAttribute InfoBoardVO infoBoardVO, Model model, RedirectAttributes ra,
                              Criteria criteria, @SessionAttribute(name = "loginMember", required = false) MemberVO loginMember) {
        model.addAttribute("loginSession", loginMember);

        Long id = infoBoardVO.getId();
        // 평점 남길 수 있는지 확인
        if (loginMember == null) { // 로그인 했는지
            redirect.sendRedirect(ra, id, criteria, "로그인 후 이용하실 수 있습니다.",
                model, "", null);
            return "redirect:/infoboard/readinfo";
        }

        // ratingsVO에 id 2개와 rating을 넣고 insert
        RatingsVO ratingsVO = new RatingsVO();
        ratingsVO.setRating((double)infoBoardVO.getRate());
        ratingsVO.setBoard_id(id);
        ratingsVO.setMember_id(loginMember.getId());

        // 이미 작품 평점 기록이 존재하면 리턴시킨다.
        if (infoBoardService.isExistRating(ratingsVO)){
            System.out.println("평점은 한 번만 남길 수 있습니다.");
            redirect.sendRedirect(ra, id, criteria, "평점은 한 번만 남길 수 있습니다.",
                model, "infoboardVO", infoBoardVO);
            return "redirect:/infoboard/readinfo";
        }

        boolean insertResult = infoBoardService.insertRating(ratingsVO);
        if (!insertResult){
            System.out.println("InsertRating Failed.");
            redirect.sendRedirect(ra, id, criteria, "insertRating failed.",
                model, "infoboardVO", infoBoardVO);
            return "redirect:/infoboard/readinfo";
        }
        System.out.println("InsertRating success");
        System.out.println(infoBoardService.getRatingsList());

        // 작품별 평균 평점을 구한다 ( 평균(rating에), 작품당 평가인원(count에) 가져옴)
        RatingsVO getAvgByBoard = infoBoardService.getAvgByBoard(ratingsVO.getBoard_id());
        // infoboardVO에 넣어 업데이트 후 화면으로 돌려보낸다
        System.out.println("GetAvgByBoard = " + getAvgByBoard);
        infoBoardVO.setTotal_rate(getAvgByBoard.getRating());
        infoBoardVO.setTotal(getAvgByBoard.getCount());
        System.out.println("업데이트를 위해 total_rate 추가 = " + infoBoardVO);
        boolean updateTotalRateResult = infoBoardService.updateTotalRate(infoBoardVO);

        if (!updateTotalRateResult){
            System.out.println("UpdateTotalRate Failed.");
            redirect.sendRedirect(ra, id, criteria, "Update Total_Rate failed.",
                model, "infoboardVO", infoBoardVO);
            return "redirect:/infoboard/readinfo";
        }
        System.out.println("평점 반영 성공.");
        redirect.sendRedirect(ra, id, criteria, "평점이 성공적으로 반영되었습니다.",
            model, "infoboardVO", infoBoardVO);
        return "redirect:/infoboard/readinfo";
    }

    @GetMapping("/readinfo")
    public String readInfo(@SessionAttribute(name = "loginMember", required = false) MemberVO loginMember,
                           Model model, RedirectAttributes ra, @ModelAttribute InfoBoardVO infoBoardVO,
                           Criteria criteria){
        model.addAttribute("loginSession", loginMember);
        InfoBoardVO result = infoBoardService.readById(infoBoardVO.getId());
        model.addAttribute("infoboardVO", result);
        if (loginMember != null && loginMember.getId() == 0) { // 일단 null인지부터 확인해줘야 오류 안 난다
            //if (loginMember.getId() == 0) { // 관리자일 때
            model.addAttribute("admin", loginMember);
            // }
        }
        String type = null;
        long total = infoBoardService.getTotal(type);
        model.addAttribute("pageMaker", new PageMaker(criteria, total));

//        return "/information/readInfo";
        return "siteReadForm";
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

    @GetMapping("sortinfo")
    public String sortInfoByType(@SessionAttribute(name = "loginMember", required = false) MemberVO loginMember,
                                 Model model, RedirectAttributes ra, Criteria criteria, @RequestParam("type")String type,
                                 @SessionAttribute(name = "favorites", required = false)Set<JjimVO> favorites){
        model.addAttribute("loginSession", loginMember);
        model.addAttribute("favorites", favorites);

        HashMap<String, Object> map = new HashMap<>();
        map.put("type", type);
        map.put("criteria", criteria);

        List<InfoBoardVO> result = infoBoardService.sortByType(map);

        if (result != null){
            model.addAttribute("list", result);
        }
        return "information/infoList";
    }

    @GetMapping("/like")
    public String change_is_like(@ModelAttribute JjimVO jjimVO, RedirectAttributes ra, Model model,
                                 @SessionAttribute(name = "loginMember", required = false) MemberVO loginMember,
                                 HttpSession session, Criteria criteria, HttpServletRequest request){
        model.addAttribute("loginSession", loginMember);
        System.out.println("로그인 멤버 "+loginMember);
       System.out.println("JjimVO = " + jjimVO);

        try {
            // 찜 테이블에 존재하는지 확인
            boolean isExitst = infoBoardService.isExistJjim(jjimVO);
            System.out.println("Jjim isExist " + isExitst);
            if (isExitst){
                // 찜 유무 변경/ 찜으로 등록
                if (jjimVO.getIs_like().equals("Y")){
                    // 존재한다면 상태만 바꿔줌
                    boolean result = infoBoardService.changeIsLike(jjimVO);
                    System.out.println("Updated Jjim = " + result);
                    if (result){
                        ra.addAttribute("message", "관심 목록에 추가하였습니다.");
                        Set<JjimVO> list = infoBoardService.getJjimList(); // 상태 바꾼 거 가져온다
                        session.setAttribute("favorites", list); // 세션에 찜 리스트 추가
                        model.addAttribute("favorites", list);
                        ra.addAttribute("page", criteria.getPage());
                        return "redirect:/infoboard/";

                    } else {
                        ra.addAttribute("message", "관심 목록에 추가하지 못했습니다.");
                        ra.addAttribute("page", criteria.getPage());
                        return "redirect:/infoboard/";

                    }
                } else {
                    // 찜에서 삭제
                    boolean result = infoBoardService.changeIsLike(jjimVO);
                    System.out.println("Updated Jjim = " + result);
                    if (result){
                        ra.addAttribute("message", "관심 목록에서 삭제하였습니다.");
                        Set<JjimVO> list = infoBoardService.getJjimList(); // 상태 바꾼 거 가져온다
                        session.setAttribute("favorites", list); // 세션에 찜 리스트 추가
                        model.addAttribute("favorites", list);
                        ra.addAttribute("page", criteria.getPage());
                        // 현재 경로 가져오기
                        //관심 목록, 추천 페이지, 정보 게시판에서 삭제 가능해서 그전 주소로 리다이렉트 하도록 함
                        String requestUri = (String) session.getAttribute("previousPage");
                        if (requestUri != null){
                            return "redirect:" + requestUri;
                        }
                        return "redirect:/";
                    }else {
                        ra.addAttribute("message", "관심 목록에서 삭제하지 못했습니다.");
                        ra.addAttribute("page", criteria.getPage());
                        return "redirect:/infoboard/";
                    }
                }
            } else {
                // 테이블에 존재하지 않는다면 새로 추가해준다
                JjimVO result = infoBoardService.insertJjim(jjimVO);
                System.out.println("Inserted Jjim = " + result);
                if (result != null){
                    // 당연히 추가일 것이다
                    ra.addAttribute("message", "관심 목록에 추가하였습니다.");
                    session.setAttribute("favorites", result); // 세션에 찜 리스트 추가
                    model.addAttribute("favorites", result);
                    ra.addAttribute("page", criteria.getPage());

                    return "redirect:/infoboard/";

                } else {
                    ra.addAttribute("message", "관심 목록에 추가하지 못했습니다.");
                    ra.addAttribute("page", criteria.getPage());

                    return "redirect:/infoboard/";
                }
            }
        } catch (Exception e){
            ra.addAttribute("message", "시스템 오류가 발생하여 실패했습니다.");
            ra.addAttribute("page", criteria.getPage());

            return  "redirect:/index";
        }


        // 기존 위치로 돌려보냄
//        String redirectUrl = userService.getRedirectUrlAfterLogin(session);
//        return "redirect:" + redirectUrl;
        // 아직 오류나서 계속 index로 보내지니 나중에 수정 후 다시 돌려놓자
    }

    @GetMapping("/favorites")
    public String favoritesList(@SessionAttribute(name = "loginMember", required = false) MemberVO loginMember, Model model,
                         @SessionAttribute(name = "favorites", required = false)Set<JjimVO> favorites, Criteria criteria,
                                HttpSession session, HttpServletRequest request){
        model.addAttribute("loginSession", loginMember);
        model.addAttribute("favorites", favorites);

        if (loginMember != null){
            HashMap<String, Object> map = new HashMap<>();
            Criteria criteria1 = new Criteria(criteria.getPage(), 8, null);
            map.put("criteria", criteria1);
            map.put("id", loginMember.getId());
            System.out.println("Criteria " + criteria);
            Set<InfoBoardVO> result = infoBoardService.getJjimListWithPaging(map);
            System.out.println("Result " + result);
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
