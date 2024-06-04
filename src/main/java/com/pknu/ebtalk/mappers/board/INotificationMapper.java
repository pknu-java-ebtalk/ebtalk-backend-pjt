package com.pknu.ebtalk.mappers.board;

import com.pknu.ebtalk.dto.board.BoardDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface INotificationMapper {
    int insertNotificationInfo(BoardDto notificationDTO);

    BoardDto selectNotificationInfoByNo(int no);

    int updateNotificationInfo(BoardDto notificationDTO);

    int deleteNotificationInfoByNo(int no);
}
