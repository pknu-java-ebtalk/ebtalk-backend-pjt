package com.pknu.ebtalk.controller.board;

import com.pknu.ebtalk.dto.board.BoardDto;
import com.pknu.ebtalk.dto.member.UserMemberDto;
import com.pknu.ebtalk.service.board.NotificationService;
import com.pknu.ebtalk.dto.board.PaginationVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/notification") // 공지사항 게시판 form
public class  NotificationController {
    private final NotificationService notificationService;

    // 게시글 리스트
    @GetMapping(value = {"/notification_board_list"})
    public String notificationBoard(Model model, @RequestParam(value = "page", defaultValue = "1") int page, HttpSession session) {
        log.info("[NotificationController] notificationBoard() - Page: " + page);

        if (session.getAttribute("loginUser") == null) {
            return "redirect:/member/sign_in";
        }

        PaginationVo paginationVo = new PaginationVo(notificationService.countNotifications(), page);

        List<BoardDto> boardList = notificationService.selectNotificationListPaged(paginationVo);

        // 모델에 게시글 리스트와 페이징 정보 추가
        model.addAttribute("boardDtos", boardList);
        model.addAttribute("pageVo", paginationVo);

        String notificationBoard = "/html/board/board";
        return notificationBoard;
    }

    // 게시판 글쓰기 기능
    @GetMapping(value = {"/notification_write_form"}) // 스프링에서는 주소 값을 가지고 메서드를 찾아간다.
    public String notificationWrite() {
        String notificationWritePage = "/html/board/board_write";
        return notificationWritePage; // 프론트로 주소를 보내준다.
    }

    // write html에서 등록 버튼을 눌리게 된다면 실행
    @PostMapping(value = {"/notification_write_confirm"})
    public String notificationWriteConfirm(Model model, BoardDto boardDto, HttpSession session) {
        if (session.getAttribute("loginUser") == null) {
            return "redirect:/member/sign_in";
        }

        String name = ((UserMemberDto) session.getAttribute("loginUser")).getName();

        boardDto.setUser_id(name);
        boardDto = notificationService.insertNotificationInfo(boardDto);
        if (boardDto.getNo() != 0) {
            model.addAttribute("boardDto", boardDto);
            return "/html/board/board_view";
        }
        return "/html/board/board_write";
    }

    // 게시글 보기
    @GetMapping("/notification_view_form")
    public String notificationViewForm(@RequestParam("no") int no, Model model, HttpSession session) {
        log.info("[NotificationController] notificationViewForm()");

        if (session.getAttribute("loginUser") == null) {
            return "redirect:/member/sign_in";
        }

        model.addAttribute("boardDto", notificationService.findBoardByNo(no));

        return "/html/board/board_view";
    }

    // 게시글 수정
    @GetMapping(value = {"/notification_edit_form"})
    public String notificationEdit(@RequestParam int no, Model model) {
        log.info("[NotificationController] notificationEditForm()");

        BoardDto boardDto = notificationService.selectNotificationInfoByNo(no);
        model.addAttribute("boardDto", boardDto);

        String notificationEditPage = "/html/board/board_edit";
        return notificationEditPage;
    }

    @PutMapping(value = {"/notification_edit_confirm"})
    public String notificationEditConfirm(Model model, BoardDto boardDto, HttpSession session) {
        log.info("[NotificationController] notificationEditConfirm()");
        log.info(boardDto.getNo());

        if (session.getAttribute("loginUser") == null) {
            return "redirect:/member/sign_in";
        }

        boardDto = notificationService.updateBoardInfo(boardDto);

        model.addAttribute("boardDto", boardDto);
        return "/html/board/board_view";
    }

    // 게시글 삭제
    @GetMapping(value = {"/notification_delete_confirm"})
    public String notificationDeleteConfirm(@RequestParam int no, HttpSession session) {
        log.info("[NotificationController] notificationDeleteConfirm()");

        if (session.getAttribute("loginUser") == null) {
            return "redirect:/member/sign_in";
        }

        notificationService.deleteBoardConfirm(no);
        return "redirect:/notification/notification_board_list";
    }

    // 검색 기능
    @GetMapping("/search")
    public String searchNotifications(@RequestParam("searchKey") String searchKey,
                                      @RequestParam("keyword") String keyword,
                                      @RequestParam(value = "page", defaultValue = "1") int page,
                                      Model model, HttpSession session) {
        log.info("[NotificationController] searchNotifications() - SearchKey: " + searchKey + ", Keyword: " + keyword);

        if (session.getAttribute("loginUser") == null) {
            return "redirect:/member/sign_in";
        }

        int totalCount = notificationService.countSearchNotifications(searchKey, keyword);
        PaginationVo paginationVo = new PaginationVo(totalCount, page);

        List<BoardDto> searchResults = notificationService.searchNotifications(searchKey, keyword, paginationVo);

        model.addAttribute("boardDtos", searchResults);
        model.addAttribute("pageVo", paginationVo);

        return "/html/board/board";
    }

    // 특정 카테고리의 게시글 리스트
    @GetMapping(value = {"/category"})
    public String notificationBoardByCategory(Model model, @RequestParam("category_no") int categoryNo, @RequestParam(value = "page", defaultValue = "1") int page, HttpSession session) {
        log.info("[NotificationController] notificationBoardByCategory() - Category: " + categoryNo + ", Page: " + page);

        if (session.getAttribute("loginUser") == null) {
            return "redirect:/member/sign_in";
        }

        PaginationVo paginationVo;
        List<BoardDto> boardList;

        if (categoryNo == 1) {
            // 전체 공지의 경우 모든 게시글을 조회
            paginationVo = new PaginationVo(notificationService.countNotifications(), page);
            boardList = notificationService.selectNotificationListPaged(paginationVo);
        } else {
            // 특정 카테고리의 게시글을 조회
            paginationVo = new PaginationVo(notificationService.countNotificationsByCategory(categoryNo), page);
            boardList = notificationService.selectNotificationListByCategoryPaged(categoryNo, paginationVo);
        }

        // 모델에 게시글 리스트와 페이징 정보 추가
        model.addAttribute("boardDtos", boardList);
        model.addAttribute("pageVo", paginationVo);

        String notificationBoard = "/html/board/board";
        return notificationBoard;
    }
}
