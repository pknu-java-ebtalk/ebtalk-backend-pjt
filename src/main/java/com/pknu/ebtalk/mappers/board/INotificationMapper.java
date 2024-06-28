package com.pknu.ebtalk.mappers.board;

import com.pknu.ebtalk.dto.board.PaginationVo;
import com.pknu.ebtalk.dto.board.BoardDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface INotificationMapper {
    // 게시글 등록
    int insertNotificationInfo(BoardDto notificationDTO);

    BoardDto selectNotificationInfoByNo(int no);

    // 게시글 수정
    int updateNotificationInfo(BoardDto notificationDTO);

    // 게시글 삭제
    int deleteNotificationInfoByNo(int no);

    // 조회수 업데이트
    void updateHits(int no);

    // 전체 게시글 수 조회
    int getCount();

    // 페이징을 위한 게시글 리스트 조회
    List<BoardDto> getListPage(PaginationVo paginationVo);

    // 검색을 위한 게시글 리스트 조회
    List<BoardDto> searchNotifications(@Param("searchKey") String searchKey,
                                       @Param("keyword") String keyword,
                                       @Param("paginationVo") PaginationVo paginationVo);

    // 검색된 게시글 수 조회
    int countSearchNotifications(@Param("searchKey") String searchKey,
                                 @Param("keyword") String keyword);

    // 카테고리별 게시글 수 조회
    int getCountByCategory(int categoryNo);

    // 카테고리별 페이징을 위한 게시글 리스트 조회
    List<BoardDto> getListPageByCategory(@Param("categoryNo") int categoryNo,
                                         @Param("paginationVo") PaginationVo paginationVo);

    // 최대 게시글 번호 조회
    int getMaxPostNumber();

    // 게시글 번호 재정렬
    void reorderPostNumbers(@Param("no") int no);
}
