package com.pknu.ebtalk.mappers.chatting;

import com.pknu.ebtalk.dto.chatting.ChatRoomUserDTO;
import com.pknu.ebtalk.dto.chatting.MessageDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface IChattingMapper {
    List<ChatRoomUserDTO> selectRoomList(int NO);

    int checkChattingNo(Map<String, Integer> map);

    int createChattingRoom(Map<String, Integer> map);

    int insertMessage(MessageDTO msg);

    int updateReadFlag(Map<String, Object> paramMap);

    List<MessageDTO> selectMessageList(int no);

    List<ChatRoomUserDTO> selectTarget(Map<String, Object> map);
}
