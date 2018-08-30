/**
 * 
 */
package bj.jdbc.firstproject.member.test;

import bj.jdbc.firstproject.member.dao.MemberIfmDAO;
import bj.jdbc.firstproject.member.dao.MemberIfmDAOImpl;
import bj.jdbc.firstproject.member.vo.MemberIfmVO;

/**
 * @author a
 *
 */
public class InsertMemberIfmTest {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		MemberIfmDAO dao = MemberIfmDAOImpl.getInstance();
		
		MemberIfmVO member = 
				new MemberIfmVO("java12",
								"javajava",
								"오자바",
								"M",
								"javajava@oracle.com",
								"010-4452-2222",
								"02-352-1156",
								"94256",
								"관악구 신림동 115-3",
								"1955-12-08",
								"2015-11-23");
		
		dao.insertMember(member);
		
		dao.updateMember(member);
		
		System.out.println(dao.getMember("dhfgwer159"));
		
		dao.getAllMembers();

		dao.getMemberArray();
		
		dao.getMemberInfo();
		
	} // main

}
