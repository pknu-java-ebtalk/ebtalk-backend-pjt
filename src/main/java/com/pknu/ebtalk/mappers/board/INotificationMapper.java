package com.pknu.ebtalk.mappers.board;

import com.pknu.ebtalk.dto.board.BoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface INotificationMapper {
//    게시글 등록
    int insertNotificationInfo(BoardDto notificationDTO);
    BoardDto selectNotificationInfoByNo(int no);

//    게시글 수정
    int updateNotificationInfo(BoardDto notificationDTO);

//    게시글 삭제
    int deleteNotificationInfoByNo(int no);

//    게시글 리스트
    List<BoardDto> selectBoardCountInfo();
}
