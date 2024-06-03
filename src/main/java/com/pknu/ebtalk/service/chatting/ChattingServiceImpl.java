package com.pknu.ebtalk.service.chatting;
import com.pknu.ebtalk.dto.chatting.*;
import com.pknu.ebtalk.dto.chatting.websocket.Util;
import com.pknu.ebtalk.mappers.chatting.IChattingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ChattingServiceImpl implements ChattingService{
    @Autowired
    private IChattingMapper chattingMapper;

    @Override
    public List<ChatRoomUserDTO> selectRoomList(int NO) {
        return chattingMapper.selectRoomList(NO);
    }

    @Override
    public int checkChattingNo(Map<String, Integer> map) {
        return chattingMapper.checkChattingNo(map);
    }

    @Override
    public int createChattingRoom(Map<String, Integer> map) {
        return chattingMapper.createChattingRoom(map);
    }

    @Override
    public int insertMessage(MessageDTO msg) {
        msg.setMessageContent(Util.XSSHandling(msg.getMessageContent()));
        return chattingMapper.insertMessage(msg);
    }


    @Override
    public int updateReadFlag(Map<String, Object> paramMap) {
        return chattingMapper.updateReadFlag(paramMap);
    }

    @Override
    public List<MessageDTO> selectMessageList(Map<String, Object> paramMap) {
        System.out.println(paramMap);

        List<MessageDTO> messageDTOList = chattingMapper.selectMessageList(Integer.parseInt(String.valueOf(paramMap.get("NO"))));

        if(!messageDTOList.isEmpty()){
            int result = chattingMapper.updateReadFlag(paramMap);
        }
        return messageDTOList;
    }

    @Override
    public List<ChatRoomUserDTO> selectTarget(Map<String, Object> map) {
        return chattingMapper.selectTarget(map);
    }
}
