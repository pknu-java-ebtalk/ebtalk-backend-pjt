package com.pknu.ebtalk.service.board;

import com.pknu.ebtalk.vo.PaginationVo;
import com.pknu.ebtalk.dto.board.BoardDto;

import java.util.List;

public interface INotificationService {
    BoardDto insertNotificationInfo(BoardDto boardDto);

    // 게시글 수정
    BoardDto selectNotificationInfoByNo(int no);

    BoardDto updateBoardInfo(BoardDto boardDto);

    // 게시글 리스트
    void deleteBoardConfirm(int no);

    List<BoardDto> selectNotificationAllLits();

    BoardDto findBoardByNo(int no);

    int countNotifications();

    List<BoardDto> selectNotificationListPaged(PaginationVo paginationVo);
}
