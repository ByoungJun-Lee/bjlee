/**
 * 
 */
package bj.jdbc.firstproject.member.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import bj.jdbc.firstproject.member.dao.MemberIfmDAOImpl;
import bj.jdbc.firstproject.member.util.DbUtil;
import bj.jdbc.firstproject.member.util.ExceptionMetadata;
import bj.jdbc.firstproject.member.vo.MemberIfmVO;


/**
 * @author a
 *
 */
public final class MemberIfmDAOImpl implements MemberIfmDAO {

private static MemberIfmDAOImpl instance = null;
	
	private MemberIfmDAOImpl() {}
	
	public static MemberIfmDAOImpl getInstance() {
		
		if(instance == null) instance = new MemberIfmDAOImpl();
		
		return instance;
		
	} //getInstance
	
	
	/**
	 * @see bj.jdbc.firstproject.member.dao.MemberDAO#insertMember(bj.jdbc.firstproject.member.vo.MemberIfmVO)
	 */
	@Override
	public void insertMember(MemberIfmVO member) { 
		ExceptionMetadata emd =
				new ExceptionMetadata(new Exception().getStackTrace()[0]);

		Connection con = DbUtil.connect();				// 1. DB 연결 객체 활성화 <- 연결 메서드
		PreparedStatement pstmt = null;					// SQL 처리 객체
		String sql = "INSERT INTO member_ifm_tbl VALUES "+	// SQL 구문
					 "(?,?,?,?,?,?,?,?,?,TO_DATE(?, 'yyyy-mm-dd'), TO_DATE(?, 'yyyy-mm-dd hh24:mi:ss'))";
		
		try {
			
			// 트랜잭션 수행-1 : 수동 commit 전환
            con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(sql);			// SQL 처리
			
			// SQL 구문 인자 처리
			pstmt.setString(1,  member.getId());
			pstmt.setString(2,  member.getPassword());
			pstmt.setString(3,  member.getName());
			pstmt.setString(4,  member.getGender());
			pstmt.setString(5,  member.getEmail());
			pstmt.setString(6,  member.getMobile());
			pstmt.setString(7,  member.getPhone());
			pstmt.setString(8,  member.getZip());
			pstmt.setString(9,  member.getAddress());
			pstmt.setString(10, member.getBirthday());
			pstmt.setString(11, member.getJoindate());
			
			// SQL 구문 실행
			if(pstmt.executeUpdate() == 1) { 
				System.out.println("회원정보 저장에 성공하였습니다.");
			}else {
				System.out.println("회원정보 저장에 실패하였습니다.");
			}
			
			// 트랜잭션 수행-2 : 작업 승인
            con.commit();
			
		}catch(SQLException e) {
			emd.printErr(e, con, true);
		}catch(Exception e) {
			emd.printErr(e, con, true);
		}finally {
			// 연결 해체(자원 반납)
			DbUtil.close(con, pstmt, null);
			
		}//try

	} // insertMember

	
	/**
	 * @see bj.jdbc.firstproject.member.dao.MemberDAO#updateMember(bj.jdbc.firstproject.member.vo.MemberIfmVO)
	 */
	@Override
	public void updateMember(MemberIfmVO member) {
		
		System.out.println(member);
				
		ExceptionMetadata emd =
				new ExceptionMetadata(new Exception().getStackTrace()[0]);
        Connection con = DbUtil.connect();			// DB 연결 객체 활성화
        PreparedStatement pstmt = null;				// SQL 처리 객체
        
        StringBuilder sb = new StringBuilder();		// SQL 구문
        sb.append("UPDATE member_ifm_tbl SET ");
        sb.append("pw=?, ");
        sb.append("name=?, ");
        sb.append("gender=?, ");
        sb.append("email=?, ");
        sb.append("mobile=?, ");
        sb.append("phone=?, ");
        sb.append("zip=?, ");
        sb.append("address=?, ");
        sb.append("birthday=?, ");
        sb.append("joindate=? ");
        sb.append("WHERE id=?");
        
        try {
        	con.setAutoCommit(false);
        	
            // SQL 구문 처리/예외처리
            pstmt = con.prepareStatement(sb.toString());
               
            // SQL 구문 인자 처리(대입)
            
    		pstmt.setString(1,  member.getPassword());
    		pstmt.setString(2,  member.getName());
    		pstmt.setString(3,  member.getGender());
    		pstmt.setString(4,  member.getEmail());
    		pstmt.setString(5,  member.getMobile());
    		pstmt.setString(6,  member.getPhone());
    		pstmt.setString(7,  member.getZip());
    		pstmt.setString(8,  member.getAddress());
    		pstmt.setString(9, member.getBirthday());
    		pstmt.setString(10, member.getJoindate());
    		pstmt.setString(11,  member.getId());
               
            // SQL 구문 실행/메시징
            if (pstmt.executeUpdate() == 1) {
                System.out.println("회원 정보 갱신에 성공하였습니다.");
            } else {
                System.out.println("회원 정보 갱신에 실패하였습니다.");
            }
            
            con.commit();
               
        } catch (SQLException e) {
        	emd.printErr(e, con, true);
        } catch (Exception e) {
        	emd.printErr(e, con, true);
        } finally {
            // 7. DB 연결 해제(자원 반납)
            DbUtil.close(con, pstmt, null);
        } // try

	} // void updateMember

	
	/**
	 * @see bj.jdbc.firstproject.member.dao.MemberDAO#getAllMembers()
	 */
	@Override
	public List<MemberIfmVO> getAllMembers() {
		
		// 주의사항) 개별(한 명의) 회원정보 여러번 인쇄!
		Connection con = DbUtil.connect();
		List<MemberIfmVO> memberlist = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ExceptionMetadata emd =
				new ExceptionMetadata(new Exception().getStackTrace()[0]);
		
		String sql = "SELECT * FROM member_ifm_tbl";
		
		try {
			
			con.setAutoCommit(false);
			
	         pstmt = con.prepareStatement(sql);	// SQL 구문 처리
	         rs = pstmt.executeQuery();			// SQL 구문 실행 -> 결과셋 // never null
	         
	         while(rs.next()) {
	        	 
	        	 MemberIfmVO member = new MemberIfmVO();

	        	 member.setId(rs.getString("id"));
		 		 member.setPassword(rs.getString("pw"));
		 		 member.setName(rs.getString("name"));
		 		 member.setGender(rs.getString("gender"));
		 		 member.setEmail(rs.getString("email"));
		 		 member.setMobile(rs.getString("mobile"));
		 		 member.setPhone(rs.getString("phone"));
		 		 member.setZip(rs.getString("zip"));
		 		 member.setAddress(rs.getString("address"));
		 		 member.setBirthday(rs.getString("birthday"));
		 		 member.setJoindate(rs.getString("joindate"));
		 		 
	        	 memberlist.add(member);
	        	 
	         } // while
	         
	         con.commit();
	         
	      }catch(SQLException e) {
	    	  emd.printErr(e, con, true);
	      }catch(Exception e) {
	          emd.printErr(e, con, true);
	      }finally {
	         DbUtil.close(con, pstmt, rs);
	      }
		
		for(int i=0; i<memberlist.size(); i++) {
			System.out.println(memberlist.get(i));
		}
		 
		return memberlist;
		
	} // List<MemberIfmVO> getAllMembers

	
	/**
	 * @see bj.jdbc.firstproject.member.dao.MemberDAO#getMember(java.lang.String)
	 */
	@Override
	public MemberIfmVO getMember(String id) {
		
		  Connection con = DbUtil.connect();	// DB연결
	      MemberIfmVO member = new MemberIfmVO();		// 결과값 (개별 횐원 정보)
	      PreparedStatement pstmt = null;		// SQL 처리 객체	Prepared를 안쓰면 와일드카드 사용 못함
	      ResultSet rs = null;					// SQL 결과셋 객체
	      ExceptionMetadata emd =
					new ExceptionMetadata(new Exception().getStackTrace()[0]);
	      
	      String sql = "SELECT * FROM member_ifm_tbl"+" WHERE ID = ?";	// ? : 와일드카드
	      //"WHERE ID ='"+id+"'";
	      try {
	    	  
	    	  con.setAutoCommit(false);
	    	  
	         pstmt = con.prepareStatement(sql);	// SQL 구문 처리
	         pstmt.setString(1, id);			// 인자 처리
	         rs = pstmt.executeQuery();			// SQL 구문 실행 -> 결과셋
	         
//	         if(re.next()) {
	         while(rs.next()) { 
	        	 
	        	member.setId(rs.getString("id"));
	 			member.setPassword(rs.getString("pw"));
	 			member.setName(rs.getString("name"));
	 			member.setGender(rs.getString("gender"));
	 			member.setEmail(rs.getString("email"));
	 			member.setMobile(rs.getString("mobile"));
	 			member.setPhone(rs.getString("phone"));
	 			member.setZip(rs.getString("zip"));
	 			member.setAddress(rs.getString("address"));
	 			member.setBirthday(rs.getString("birthday"));
	 			member.setJoindate(rs.getString("joindate"));
	        	 
	         }// while
	         
	         con.commit();
	         
	      }catch(SQLException e) {
	         emd.printErr(e, con, true);
	      }catch(Exception e) {
	         emd.printErr(e, con, true);
	      }finally {
	         DbUtil.close(con, pstmt, rs);
	      }
	      
	      return member;

	} // MemberIfmVO getMember

	
	/**
	 * @see bj.jdbc.firstproject.member.dao.MemberDAO#deleteMember(java.lang.String)
	 */
	@Override
	public void deleteMember(String id) {
		
		Connection con = DbUtil.connect();						// DB 연결
		PreparedStatement pstmt = null;							// SQL 처리 객체
		String sql = "Delete member_ifm_tbl " + "Where id = ?";		// SQL 구문(실행할)
		ExceptionMetadata emd =
				new ExceptionMetadata(new Exception().getStackTrace()[0]);
		
		try {
			
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			if(pstmt.executeUpdate() == 1) {
				System.out.println("회원정보 삭제에 성공하였습니다.");
			}else {
				System.out.println("회원정보 삭제에 실패하였습니다.");
			}
			
			con.commit();
			
		}catch(SQLException e) {
			emd.printErr(e, con, true);
	    }catch(Exception e) {
	    	emd.printErr(e, con, true);
	    }finally {
	         DbUtil.close(con, pstmt, null);
	    } // finally

	} // void deleteMember
	
	
	/**
	 * @see bj.jdbc.firstproject.member.dao.MemberDAO#isMember()
	 */
	@Override
	public boolean isMember(String id) { 
		
		ExceptionMetadata emd = 
				new ExceptionMetadata(new Exception().getStackTrace()[0]);
		Connection con = DbUtil.connect();
		boolean flag = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT (*) FROM member_ifm_tbl " + "WHERE id =?";
		
		try {
			
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				flag = ( rs.getInt(1) ) == 1 ? true : false;
			}
			
			con.commit();
			
		}catch(SQLException e) {
			emd.printErr(e, con, true);
		}catch(Exception e) {
			emd.printErr(e, con, true);
		}
		
		return flag;
		
	} // isMember(String id)

	
	/**
	 * @see bj.jdbc.firstproject.member.dao.MemberDAO#isMember(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean isMember(String id, String password) {
		
		ExceptionMetadata emd =
				new ExceptionMetadata(new Exception().getStackTrace()[0]);
		Connection con = DbUtil.connect();
		boolean flag = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT (*) FROM member_ifm_tbl "
				   + "WHERE id =? AND password = ?";
		
		try {
			
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				flag = ( rs.getInt(1) ) == 1 ? true : false;
			}
			
			con.commit();
			
		}catch(SQLException e) {
			emd.printErr(e, con, true);
		}catch(Exception e) {
			emd.printErr(e, con, true);
		}
		
		return flag;
		
	} // isMember(String id, String password)

	
	/**
	 * @see bj.jdbc.firstproject.member.dao.MemberDAO#getRowCount()
	 */
	@Override
	public int getRowCount() {
		
		ExceptionMetadata emd = 
				new ExceptionMetadata(new Exception().getStackTrace()[0]);
		Connection con = DbUtil.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int rowCount = 0;
		
		String sql = "SELECT count(*) FROM member_ifm_tbl";
		
		try {
			
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next())
				rowCount = rs.getInt(1);
			
			con.commit();
			
		}catch(SQLException e) {
			emd.printErr(e, con, true);
		}catch(Exception e) {
			emd.printErr(e, con, true);
		}
		
		return rowCount;
		
	} // int getRowCount

	/**
	 * @see bj.jdbc.firstproject.member.dao.MemberDAO#getMemberArray()
	 */
	@Override
	public MemberIfmVO[] getMemberArray() {
				
		ExceptionMetadata emd =
				new ExceptionMetadata(new Exception().getStackTrace()[0]);
		Connection con = DbUtil.connect();									// DB연결
	    MemberIfmVO[] members = new MemberIfmVO[this.getRowCount()];		// 결과값 (개별 횐원 정보)
	    PreparedStatement pstmt = null;										// SQL 처리 객체	
	    ResultSet rs = null;												// SQL 결과셋 객체
	    int count=0;
	    
	    String sql = "SELECT * FROM member_ifm_tbl";	// ? : 와일드카드
	    
	    try {
	    	
	    	con.setAutoCommit(false);
	    	
	       pstmt = con.prepareStatement(sql);		// SQL 구문 처리
	       rs = pstmt.executeQuery();				// SQL 구문 실행 -> 결과셋
	       
	       while(rs.next()) {
	    	   
	    	   MemberIfmVO member = new MemberIfmVO();
	    	   
	    	   member.setId(rs.getString("id"));
	    	   member.setPassword(rs.getString("pw"));
	    	   member.setName(rs.getString("name"));
	    	   member.setGender(rs.getString("gender"));
	    	   member.setEmail(rs.getString("email"));
	    	   member.setMobile(rs.getString("mobile"));
	    	   member.setPhone(rs.getString("phone"));
	    	   member.setZip(rs.getString("zip"));
	    	   member.setAddress(rs.getString("address"));
	    	   member.setBirthday(rs.getString("birthday"));
	    	   member.setJoindate(rs.getString("joindate"));
	      	   
	      	   members[count] = member;
	      	   count++;
	      	   System.out.println(members[count-1]);
	       }// while
	         
	       con.commit();
	       
	    }catch(SQLException e) {
	    	emd.printErr(e, con, true);
	    }catch(Exception e) {
	    	emd.printErr(e, con, true);
	    }finally {
	       DbUtil.close(con, pstmt, rs);
	    }

	    return members;
				
	} // MemberIfmVO[] getMemberArray

	
	/**
	 * 
	 * @see bj.jdbc.firstproject.member.dao.MemberDAO#getMemberInfo()
	 */
	@Override
	public void getMemberInfo() throws Exception {

		Connection con = DbUtil.connect();
		DatabaseMetaData dbmd = con.getMetaData();
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM member_ifm_tbl";
		   
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		   
		rsmd = rs.getMetaData();
		   
		System.out.println("DB 종류 : "			 +dbmd.getDatabaseProductName());
		System.out.println("DB 버전1 : "			 +dbmd.getDatabaseMajorVersion()+"."
									   		 	 +dbmd.getDatabaseMinorVersion());
		
		System.out.println("DB 버전(full) : "		 +dbmd.getDatabaseProductVersion());
		
		System.out.println("DB JDBC 드라이버 버전 : " +dbmd.getDriverMajorVersion()+"."
		                                         +dbmd.getDriverMinorVersion());
		   
		System.out.println("컬럼의 수 : "			+ rsmd.getColumnCount());
		System.out.println("컬럼 라벨명 : "			+ rsmd.getColumnLabel(1));
		System.out.println("컬럼 라벨명 : "			+ rsmd.getColumnLabel(2));
		System.out.println("컬럼명 : "				+ rsmd.getColumnName(1));
		System.out.println("컬럼명 : "				+ rsmd.getColumnName(2));
		System.out.println("컬럼 타입 : "			+ rsmd.getColumnTypeName(1));
		System.out.println("컬럼 타입 : "			+ rsmd.getColumnTypeName(5));
		System.out.println("컬럼 타입 대응 클래스명 : "	+ rsmd.getColumnClassName(1));
		   
		ResultSet pks = dbmd.getPrimaryKeys(null, null, "MEMBER_TBL");
		   
		while (pks.next()) {
		       
			System.out.println("현재 테이블 기본키명 : "	+ pks.getString("COLUMN_NAME"));	// index = 4
		    System.out.println("현재 계정명 : " 			+ pks.getString("TABLE_SCHEM"));	// index = 2
		    System.out.println("현재 테이블명 : " 		+ pks.getString("TABLE_NAME")); 	// index = 3
		    System.out.println("현재 키 시퀀스 : " 		+ pks.getString("KEY_SEQ")); 		// index = 5
		    System.out.println("현재 기본키 제약조건명 : "	+ pks.getString("PK_NAME"));		// index = 6
		       
		} // while

		DbUtil.close(con, pstmt, rs);
		
	} // getMemberInfo

	
	/**
	 * @see bj.jdbc.firstproject.member.dao.MemberDAO#getMember(java.lang.StringBuilder)
	 */
	@Override
	public List<MemberIfmVO> getMember(StringBuilder sql) {
		
		List<MemberIfmVO> members = new ArrayList<>();
		ExceptionMetadata emd =
				new ExceptionMetadata(new Exception().getStackTrace()[0]);
		Connection con = DbUtil.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberIfmVO member = null;
		
		try {
			
			con.setAutoCommit(false);

			pstmt = con.prepareStatement(sql.toString()); // 
			rs = pstmt.executeQuery();
			
			while(rs.next()) {

				member = new MemberIfmVO();
				
				member.setId(rs.getString("id"));
	 			member.setPassword(rs.getString("pw"));
	 			member.setName(rs.getString("name"));
	 			member.setGender(rs.getString("gender"));
	 			member.setEmail(rs.getString("email"));
	 			member.setMobile(rs.getString("mobile"));
	 			member.setPhone(rs.getString("phone"));
	 			member.setZip(rs.getString("zip"));
	 			member.setAddress(rs.getString("address"));
	 			member.setBirthday(rs.getString("birthday"));
	 			member.setJoindate(rs.getString("joindate"));
				
				members.add(member);
				
			} // while
			
			con.commit();
			
		}catch(SQLException e) {
			emd.printErr(e, con, true);
		}catch(Exception e) {
			emd.printErr(e, con, true);
		}finally {
			DbUtil.close(con, pstmt, rs);
		}
		
		return members;
		
	} // List<MemberIfmVO> getMember
	
	
	
	
} // MemberIfmDAOImpl
