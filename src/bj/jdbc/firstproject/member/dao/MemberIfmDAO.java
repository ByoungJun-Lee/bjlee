/**
 * 
 */
package bj.jdbc.firstproject.member.dao;

import java.util.List;

import bj.jdbc.firstproject.member.vo.MemberIfmVO;

/**
 * @author a
 *
 */
public interface MemberIfmDAO {
	
	
	/**
	 * 회원정보 삽입(작성)
	 * 
	 * @param member		회원정보 객체
	 * @throws Exception	예외처리
	 */
	void insertMember(MemberIfmVO member) throws Exception;
	
	
	/**
	 * 회원정보 수정(갱신)
	 * 
	 * @param member		회원정보 객체
	 * @throws Exception	예외처리
	 */
	void updateMember(MemberIfmVO member) throws Exception;
	
	
	/**
	 * 전체 회원 정보(들) 조회(검색)
	 * 
	 * @return				전체 회원(들) 정보
	 * @throws Exception	예외처리
	 */
	List<MemberIfmVO> getAllMembers() throws Exception;
	
	
	/**
	 * 개별 회원 정보 조회(검색)
	 * 
	 * @param id			회원 아이디
	 * @return				개별 회원 정보
	 * @throws Exception	예외처리
	 */
	MemberIfmVO getMember(String id) throws Exception;
	
	
	/**
	 * 개별 회원 정보 삭제
	 * 
	 * @param id			회원 아이디
	 * @throws Exception	예외처리
	 */
	void deleteMember(String id) throws Exception;
	
	
	/**
	 * 회원 존재 유무 점검
	 * ex) 아이디 중복 점검
	 * 
	 * @param id			회원 아이디
	 * @return				회원 존재 유무(여부)
	 * @throws Exception	예외처리
	 */
	boolean isMember(String id) throws Exception;
	
	
	/**
	 * 회원 존재 유무 점검
	 * ex) 로그인 인증(authentication)
	 * 
	 * @param id			회원 아이디
	 * @param password		회원 비밀번호
	 * @return				회원 존재 유무(여부)
	 * @throws Exception	예외처리
	 */
	boolean isMember(String id, String password) throws Exception;
	
	
	/**
	 * 테이블의 전체 행수 구하기
	 * 
	 * @return				테이블의 전체 행수
	 * @throws Exception	예외처리
	 */
	int getRowCount() throws Exception;
	
	
	/**
	 * 전체 회원(들) 정보 조회(검색)
	 * 
	 * @return				전체 회원(들) 정보
	 * @throws Exception	예외처리
	 */
	MemberIfmVO[] getMemberArray() throws Exception;
	
	
	/**
	 * 회원 테이블 메타데이터 조회
	 * 
	 * @throws Exception	예외처리
	 */
	void getMemberInfo() throws Exception;
	
	
	/**
	 * SQL구문을 통해서 회원 정보 조회(검색)
	 * @param sql			SQL 구문
	 * @return				회원 정보(들)
	 * @throws Exception	예외처리
	 */
	List<MemberIfmVO> getMember(StringBuilder sql) throws Exception;
	
	
	
	
	
} // MemberIfmDAO
