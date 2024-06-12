-- 사용자 테이블
CREATE TABLE `USER` (
                        `ID`    VARCHAR(30)    primary key comment '사용자 아이디',
                        `EDU_NO`    INT comment '교육과정 번호',
                        `PW`    VARCHAR(100)   NOT NULL comment '사용자 비밀번호',
                        `PHONE` VARCHAR(100)   NOT NULL comment '사용자 전화번호',
                        `NAME`  VARCHAR(30)    NOT NULL comment '사용자 이름',
                        `PROFILE_IMG`   VARCHAR(100) comment '프로필 사진 파일 이름',
                        `PROFILE_IMG_PATH` VARCHAR(200) comment '프로필 사진 파일 경로',
                        `APPROVE_YN` CHAR(1) DEFAULT 'n' comment '사용자 승인여부',
                        `DELETE_YN` CHAR(1) DEFAULT 'n' comment '사용자 탈퇴여부',
                        `CREATED_AT`    DATETIME   NOT NULL comment '가입일',
                        `ADMIN_YN`  CHAR(1) DEFAULT 'n' comment '관리자 여부',
                        foreign key (`EDU_NO`) references edu_course(`NO`) on update set null
);

-- 교육과정 테이블
CREATE TABLE `EDU_COURSE` (
                        `NO`  INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
                        `TYPE`    VARCHAR(50)    NOT NULL,
                        `STARTED_AT`  DATETIME,
                        `FINISHED_AT` DATETIME
);

-- 스터디 모집 테이블
create table study(
                      `NO` int auto_increment primary key comment '스터디 번호',
                      `USER_ID` varchar(30) not null comment '방장 아이디',
                      `TITLE` varchar(100) not null comment '제목',
                      `STARTED_AT` date not null comment '스터디 시작일',
                      `FINISHED_AT` date not null comment '스터디 종료일',
                      `COUNT` int not null comment '인원 수',
                      `CONTENT` text not null comment '내용',
                      `VIEWS` int default 0 comment '조회수',
                      `CREATED_AT` date comment '등록일'
);

-- 스터디 조원 테이블
create table study_mate(
                        `STUDY_NO` int comment '스터디 번호',
                        `user_id` varchar(30) comment '조원 아이디',
                        `approve_yn` char(1) default 'N' comment '스터디 승인여부',
                        foreign key(study_no) references study(no)
                        on delete cascade
                        on update cascade
);

-- 회고록 테이블
create table MEMOIR(
                       `NO` int auto_increment primary key comment '회고록 번호',
                       `M_CATEGORY_NO` int not null comment '카테고리 번호',
                       `STUDY_NO` int not null comment '스터디 번호',
                       `USER_ID` varchar(30) not null comment '사용자 아이디',
                       `CONTENT` text not null comment '내용',
                       `CREATED_AT` date comment '등록일'
);

-- 회고록 카테고리 테이블
create table MEMOIR_CATEGORY(
                        `NO` int auto_increment primary key comment '카테고리 번호',
                        `TYPE` varchar(30) not null comment '종류'
);

-- 게시판 테이블
create table BOARD(
                      `NO` int auto_increment primary key comment '게시판 번호',
                      `USER_ID` varchar(30) not null comment '게시글 작성자',
                      `B_CATEGORY_NO` int not null comment '카테고리 번호',
                      `TITLE` varchar(100) not null comment '제목',
                      `CONTENT` text not null comment '내용',
                      `VIEWS` int default 0 comment '조회수',
                      `MODIFIED_AT` datetime comment '수정일',
                      `CREATED_AT` datetime comment '등록일'
);

-- 게시판 카테고리
create table CATEGORY(
                        `NO` int auto_increment primary key comment '카테고리 번호',
                        `TYPE` varchar(50) not null comment '종류'
);

-- 게시판 카테고리(세부)
create table CATEGORYOFLOWER(
                        `NO` int auto_increment primary key comment '하위 카테고리 번호',
                        `C_NO` int not null comment '카테고리 번호',
                        `TYPE` varchar(50) not null comment '종류'
);

-- 댓글 테이블
create table COMMENT(
                        `NO` int auto_increment primary key comment '댓글 번호',
                        `USER_ID` varchar(30) not null comment '사용자 아이디',
                        `BOARD_NO` int not null comment '게시물 번호',
                        `CONTENT` text not null comment '내용',
                        `CREATED_AT` datetime not null comment '등록일'
);

-- 좋아요 테이블
create table FAVORITES(
                        `USER_ID` varchar(30) not null comment '댓글 작성자',
                        `B_NO` int not null comment '게시물 번호'
);

-- 채팅방유저테이블
CREATE TABLE `ChatRoomUser` (
                        `CHAT_ROOM_NO`  INT    NOT NULL PRIMARY KEY,
                        `USER_ID`   VARCHAR(30)    NOT NULL
);

-- 메세지 테이블
CREATE TABLE `Message` (
                        `NO` INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
                        `FROM_ID`    VARCHAR(30)    NOT NULL   COMMENT 'FOREIGN KEY (USER_ID) REFERENCES USER(ID) ON DELETE CASCADE',
                        `CHAT_ROOM_NO`   INT    NOT NULL,
                        `FILE_NO`    INT    NOT NULL,
                        `CONTENT`    TEXT   NULL,
                        `MESSAGE_TYPE`   ENUM('TEXT', 'IMAGE')  NOT NULL,
                        `CREATED_AT` DATETIME   NOT NULL
);

-- 메시지 첨부 파일 테이블
CREATE TABLE `Attachment` (
                        `NO`  INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
                        `FILE_PATH`   VARCHAR(255)   NOT NULL,
                        `FILE_TYPE`   VARCHAR(50)    NOT NULL,
                        `CREATED_AT`  DATETIME   NULL DEFAULT CURRENT_TIMESTAMP
);