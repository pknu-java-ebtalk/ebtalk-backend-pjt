package com.pknu.ebtalk.service.board;

import com.pknu.ebtalk.dto.board.BoardDto;

public interface INotificationService {
     BoardDto insertNotificationInfo(BoardDto boardDto);

    //  게시글 수정
    BoardDto selectNotificationInfoByNo(int no);

    BoardDto updateBoardInfo(BoardDto boardDto);

    void deleteBoardConfirm(int no);
}
