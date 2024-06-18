package com.pknu.ebtalk.mappers.board;

import com.pknu.ebtalk.vo.PaginationVo;
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

    // 게시글 리스트
    List<BoardDto> selectBoardAllList();

    void updateHits(int no);

    int getCount();

    List<BoardDto> getListPage(PaginationVo paginationVo);

    List<BoardDto> searchNotifications(String searchKey, String keyword, com.pknu.ebtalk.vo.PaginationVo paginationVo);

    int countSearchNotifications(String searchKey, String keyword);
    int getCountByCategory(int categoryNo);

    List<BoardDto> getListPageByCategory(@Param("categoryNo") int categoryNo, @Param("paginationVo") PaginationVo paginationVo);
}