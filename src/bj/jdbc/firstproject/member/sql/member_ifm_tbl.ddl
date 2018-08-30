DROP TABLE member_ifm_tbl CASCADE CONSTRAINTS;

/**********************************/
/* Table Name: 회원 정보 테이블 */
/**********************************/
CREATE TABLE member_ifm_tbl(
		id       	VARCHAR2(20)		 NOT NULL,
		pw       	VARCHAR2(20)		 NOT NULL,
		name     	VARCHAR2(50)		 NOT NULL, 
		gender   	CHAR(3)		 		 NOT NULL,
		email    	VARCHAR2(50)		 NOT NULL		UNIQUE,
		mobile   	VARCHAR2(15)		 DEFAULT 010	NOT NULL	UNIQUE,
		phone    	VARCHAR2(15)		 NULL ,
		zip      	CHAR(5)		 		 NULL ,
		address  	VARCHAR2(100)		 NULL ,
		birthday 	DATE		 		 NOT NULL,
		joindate 	DATE		 		 NOT NULL
);

COMMENT ON TABLE member_ifm_tbl is '회원 정보 테이블';
COMMENT ON COLUMN member_ifm_tbl.id is '아이디';
COMMENT ON COLUMN member_ifm_tbl.pw is '패스워드';
COMMENT ON COLUMN member_ifm_tbl.name is '이름(성명)';
COMMENT ON COLUMN member_ifm_tbl.gender is '성별';
COMMENT ON COLUMN member_ifm_tbl.email is '메일주소';
COMMENT ON COLUMN member_ifm_tbl.mobile is '연락처1(휴대폰)';
COMMENT ON COLUMN member_ifm_tbl.phone is '연락처2';
COMMENT ON COLUMN member_ifm_tbl.zip is '도로명 우편번호';
COMMENT ON COLUMN member_ifm_tbl.address is '도로명 주소';
COMMENT ON COLUMN member_ifm_tbl.birthday is '생년월일';
COMMENT ON COLUMN member_ifm_tbl.joindate is '가입일';



ALTER TABLE member_ifm_tbl ADD CONSTRAINT IDX_member_ifm_tbl_PK PRIMARY KEY (id);

