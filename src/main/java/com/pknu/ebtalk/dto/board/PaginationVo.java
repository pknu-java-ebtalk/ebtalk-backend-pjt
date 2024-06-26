package com.pknu.ebtalk.dto.board;

import lombok.Data;

@Data
public class PaginationVo {

    int rowCount = 10;     // 한 페이지 당 보여줄 게시물 개수
    int pageCount = 5;    // 한 블럭에 몇 개의 페이지 개수
    int totalCount;       // 총 게시물 개수
    int page;             // 현재 페이지

    int startPage = 1;    // 한 블럭의 시작 페이지
    int endPage;          // 한 블럭의 끝 페이지

    int totalPageCount;   // 총 페이지 개수

    boolean isPrev = false; // 이전 페이지로 이동하는 버튼 유무
    boolean isNext = false; // 다음 페이지로 이동하는 버튼 유무

    int offset;

    public PaginationVo(final int totalCount, final int page) {
        this.totalCount = totalCount;
        this.page = page;

        // 총 페이지 개수 구하기
        setTotalPageCount(totalCount, this.rowCount);

        // 총 게시물 개수가 0일 때 페이지 번호를 1로 설정
        if (totalCount == 0) {
            this.totalPageCount = 1;
            this.startPage = 1;
            this.endPage = 1;
            this.isPrev = false;
            this.isNext = false;
            this.offset = 0;
        } else {
            // 한 블럭의 첫 페이지 구하기
            setStartPage(this.startPage, page, this.pageCount);

            // 한 블럭의 끝 페이지 구하기
            setEndpage(this.startPage, this.pageCount, this.totalPageCount);

            // 이전 블록 버튼 유무 판별하기
            isPrev(page, this.pageCount);

            // 다음 블록 버튼 유무 판별하기
            isNext(this.endPage, this.totalPageCount);

            // offset 구하기
            setOffset(page, this.rowCount);
        }
    }

    private void setTotalPageCount(final int totalCount, final int rowCount) {
        this.totalPageCount = (int) Math.ceil(totalCount * 1.0 / rowCount);
    }

    private void setStartPage(final int startPage, final int page, final int pageCount) {
        this.startPage = startPage + (((page - startPage) / pageCount) * pageCount);
    }

    private void setEndpage(final int startPage, final int pageCount, final int totalPageCount) {
        this.endPage = ((startPage - 1) + pageCount) < totalPageCount ? (startPage - 1) + pageCount : totalPageCount;
    }

    private void isPrev(final int page, final int pageCount) {
        this.isPrev = 1 < ((page * 1.0) / pageCount);
    }

    private void isNext(final int endPage, final int totalPageCount) {
        this.isNext = endPage < totalPageCount;
    }

    private void setOffset(final int page, final int rowCount) {
        this.offset = (page - 1) * rowCount;
    }
}
