package com.pknu.ebtalk.mappers.chatting;

import com.pknu.ebtalk.dto.chatting.ChatRoomDto;
import com.pknu.ebtalk.dto.chatting.ChatRoomUserDto;
import com.pknu.ebtalk.dto.chatting.MessageDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface IChattingMapper {

    List<ChatRoomUserDto> selectChatName(ChatRoomUserDto param);

    // 새 채팅방 생성 메서드
    void insertChatRoom(ChatRoomDto chatRoom);

    // 채팅방에 사용자 추가
    void insertChatRoomUser(ChatRoomUserDto chatRoomUser);
}
