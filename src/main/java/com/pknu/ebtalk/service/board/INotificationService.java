package com.pknu.ebtalk.service.board;

import com.pknu.ebtalk.dto.board.BoardDto;

import java.util.List;

public interface INotificationService {
     BoardDto insertNotificationInfo(BoardDto boardDto);

    //  게시글 수정
    BoardDto selectNotificationInfoByNo(int no);

    BoardDto updateBoardInfo(BoardDto boardDto);

    //    게시글 리스트
    List<BoardDto> selectboardAllList();

    void deleteBoardConfirm(int no);
}
