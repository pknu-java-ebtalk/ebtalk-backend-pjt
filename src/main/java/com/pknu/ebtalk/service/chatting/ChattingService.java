package com.pknu.ebtalk.service.chatting;
import com.pknu.ebtalk.dto.chatting.*;
import com.pknu.ebtalk.mappers.chatting.IChattingMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class ChattingService implements IChattingService {
    @Autowired
    private IChattingMapper chattingMapper;

    @Override
    public List<ChatRoomUserDto> selectChatName(ChatRoomUserDto param) {
        log.info("ChattingService: selectChatName called with param: {}", param);


        // 로그 확인용
        List<ChatRoomUserDto> result = chattingMapper.selectChatName(param);
        log.info("ChattingService: selectChatName result: {}", result);
        return result;
    }



    public void createChatRoom(ChatRoomDto chatRoom){
        log.info("ChattingService: addChatRoomUser called with param: {}", chatRoom);
        chattingMapper.insertChatRoom(chatRoom);
    }

    public void addChatRoomUser(ChatRoomUserDto chatRoomUser) {
        log.info("ChattingService: addChatRoomUser called with param: {}", chatRoomUser);
        chattingMapper.insertChatRoomUser(chatRoomUser);
    }

}
