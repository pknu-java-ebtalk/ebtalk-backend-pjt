package com.pknu.ebtalk.service.board;

import com.pknu.ebtalk.dto.board.BoardDto;
import com.pknu.ebtalk.mappers.board.INotificationMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class NotificationService implements INotificationService{
    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);
    private final INotificationMapper notificationMappers;

//게시글 등록
    @Override
    public BoardDto insertNotificationInfo(BoardDto boardDto) {
        int result = notificationMappers.insertNotificationInfo(boardDto);      //DB에 게시글 정보 저장 
//        값이 db에 insert가 되야 1이상의 값이 반환됨

        if (result > 0) {
            int no = boardDto.getNo();  // dto에 있는 no 불러옴
//            int 타입의 no라는 변수에 Dto에 있는 no를 저장해준다
            boardDto = notificationMappers.selectNotificationInfoByNo(no);
//            mapper.XMl 파일에서 select 한 정보 값들을 boardDto 에 담아준다
        }
        return boardDto;
    }

    //  게시글 수정
    @Override
    public BoardDto selectNotificationInfoByNo(int no) {
        log.info("[NotificationService] selectNotificationInfoByNo");

        return notificationMappers.selectNotificationInfoByNo(no);
    }

    @Override
    public BoardDto updateBoardInfo(BoardDto boardDto) {
        log.info("[NotificationService] selectNotificationInfoByNo");

        int result = notificationMappers.updateNotificationInfo(boardDto);

        if (result > 0) {
            log.info("성공");
            boardDto = notificationMappers.selectNotificationInfoByNo(boardDto.getNo());
        } else {
            log.info("실패");
        }
        return boardDto;
    }


//    게시글 삭제
    @Override 
    public void deleteBoardConfirm(int no){
        log.info("[NotificationService] deleteBoardConfirm");
        
        int result = notificationMappers.deleteNotificationInfoByNo(no);
        
        if (result > 0) {
            log.info("삭제 성공");
        } else{
            log.info("삭제 실패");
        }
    }

//    전체 게시글
    @Override
    public List<BoardDto> selectNotificationAllLits() {
        log.info("[NotificationService] selectNotificationAllLits");

        List<BoardDto> boardList = notificationMappers.selectBoardAllList();
        return boardList;
    }

//    게시글 보기
    @Override
    public BoardDto findBoardByNo(int no) {
        log.info("[NotificationService] findBoardByNo");

        BoardDto boardDto = notificationMappers.selectNotificationInfoByNo(no);
        notificationMappers.updateHits(no);

        return boardDto;
    }
}
