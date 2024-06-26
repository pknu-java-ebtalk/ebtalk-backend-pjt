package com.pknu.ebtalk.service.board;

import com.pknu.ebtalk.dto.board.PaginationVo;
import com.pknu.ebtalk.dto.board.BoardDto;
import com.pknu.ebtalk.mappers.board.INotificationMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService implements INotificationService {
    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);
    private final INotificationMapper notificationMappers;

    // 게시글 등록
    @Override
    public BoardDto insertNotificationInfo(BoardDto boardDto) {
        // 최대 번호 조회
        int maxNo = notificationMappers.getMaxPostNumber();
        // 새로운 게시글 번호 할당
        boardDto.setNo(maxNo + 1);

        int result = notificationMappers.insertNotificationInfo(boardDto); // DB에 게시글 정보 저장
        // 값이 DB에 insert가 되면 1 이상의 값이 반환됨

        if (result > 0) {
            int no = boardDto.getNo();  // DTO에 있는 no 불러옴
            boardDto = notificationMappers.selectNotificationInfoByNo(no);
            // Mapper.XML 파일에서 select한 정보 값들을 boardDto에 담아준다
        }
        return boardDto;
    }

    // 게시글 번호로 게시글 조회
    @Override
    public BoardDto selectNotificationInfoByNo(int no) {
        log.info("[NotificationService] selectNotificationInfoByNo");
        return notificationMappers.selectNotificationInfoByNo(no);
    }

    // 게시글 수정
    @Override
    public BoardDto updateBoardInfo(BoardDto boardDto) {
        log.info("[NotificationService] updateBoardInfo");

        int result = notificationMappers.updateNotificationInfo(boardDto);
        if (result > 0) {
            log.info("성공");
            boardDto = notificationMappers.selectNotificationInfoByNo(boardDto.getNo());
        } else {
            log.info("실패");
        }
        return boardDto;
    }

    // 게시글 삭제
    @Override
    public void deleteBoardConfirm(int no) {
        log.info("[NotificationService] deleteBoardConfirm");

        int result = notificationMappers.deleteNotificationInfoByNo(no);
        if (result > 0) {
            log.info("삭제 성공");
            // 번호 재정렬
            notificationMappers.reorderPostNumbers(no);
        } else {
            log.info("삭제 실패");
        }
    }

    // 게시글 번호로 게시글 조회 및 조회수 업데이트
    @Override
    public BoardDto findBoardByNo(int no) {
        log.info("[NotificationService] findBoardByNo");

        BoardDto boardDto = notificationMappers.selectNotificationInfoByNo(no);
        notificationMappers.updateHits(no);
        return boardDto;
    }

    // 전체 게시글 수 조회
    @Override
    public int countNotifications() {
        return this.notificationMappers.getCount();
    }

    // 페이징을 위한 게시글 리스트 조회
    @Override
    public List<BoardDto> selectNotificationListPaged(PaginationVo paginationVo) {
        return this.notificationMappers.getListPage(paginationVo);
    }

    // 검색 기능을 위한 게시글 리스트 조회
    @Override
    public List<BoardDto> searchNotifications(String searchKey, String keyword, PaginationVo paginationVo) {
        log.info("[NotificationService] searchNotifications() - SearchKey: " + searchKey + ", Keyword: " + keyword);
        return notificationMappers.searchNotifications(searchKey, keyword, paginationVo);
    }

    // 검색된 게시글 수 조회
    @Override
    public int countSearchNotifications(String searchKey, String keyword) {
        return notificationMappers.countSearchNotifications(searchKey, keyword);
    }

    // 특정 카테고리의 게시글 수 조회
    @Override
    public int countNotificationsByCategory(int categoryNo) {
        return this.notificationMappers.getCountByCategory(categoryNo);
    }

    // 특정 카테고리의 게시글 리스트 조회
    @Override
    public List<BoardDto> selectNotificationListByCategoryPaged(int categoryNo, PaginationVo paginationVo) {
        return this.notificationMappers.getListPageByCategory(categoryNo, paginationVo);
    }
}
