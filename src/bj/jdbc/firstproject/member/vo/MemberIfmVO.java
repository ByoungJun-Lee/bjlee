/**
 * 
 */
package bj.jdbc.firstproject.member.vo;


/**
 * 회원 정보 테이블 값 객체(VO)
 * @author a
 *
 */
public class MemberIfmVO {
	
	private String id;			// 아이디
	private String password;	// 패스워드
	private String name;		// 이름
	private String gender;		// 성별
	private String email;		// 메일주소
	private String mobile;		// 연락처1(휴대폰)	Default 010
	private String phone;		// 연락처2		null
	private String zip;			// 도로명 우편번호	null
	private String address;		// 도로명 주소		null	
	private String birthday;	// 생년월일		date
	private String joindate;	// 가입일			date
	
	
	/**
	 * 기본 생성자
	 */
	public MemberIfmVO() {
		super();
	}

	
	/**
	 * 필수 항목 생성자
	 * 
	 * @param id
	 * @param password
	 * @param name
	 * @param gender
	 * @param email
	 * @param mobile
	 * @param phone
	 * @param birthday
	 * @param joindate
	 */
	public MemberIfmVO(String id,
					   String password, 
					   String name, 
					   String gender, 
					   String email, 
					   String mobile, 
					   String phone,
					   String birthday, 
					   String joindate) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.email = email;
		this.mobile = mobile;
		this.phone = phone;
		this.birthday = birthday;
		this.joindate = joindate;
	}

	
	/**
	 * 모든 항목 생성자
	 * 
	 * @param id
	 * @param password
	 * @param name
	 * @param gender
	 * @param email
	 * @param mobile
	 * @param phone
	 * @param zip
	 * @param address
	 * @param birthday
	 * @param joindate
	 */
	public MemberIfmVO(String id,
					   String password, 
					   String name, 
					   String gender, 
					   String email, 
					   String mobile, 
					   String phone,
					   String zip, 
					   String address, 
					   String birthday, 
					   String joindate) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.email = email;
		this.mobile = mobile;
		this.phone = phone;
		this.zip = zip;
		this.address = address;
		this.birthday = birthday;
		this.joindate = joindate;
	}


	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}


	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}


	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}


	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}


	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}


	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}


	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}


	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}


	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}


	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}


	/**
	 * @return the birthday
	 */
	public String getBirthday() {
		return birthday;
	}


	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}


	/**
	 * @return the joindate
	 */
	public String getJoindate() {
		return joindate;
	}


	/**
	 * @param joindate the joindate to set
	 */
	public void setJoindate(String joindate) {
		this.joindate = joindate;
	}


	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MemberIfmVO [id=").append(id).append(", password=").append(password).append(", name=")
				.append(name).append(", gender=").append(gender).append(", email=").append(email).append(", mobile=")
				.append(mobile).append(", phone=").append(phone).append(", zip=").append(zip).append(", address=")
				.append(address).append(", birthday=").append(birthday).append(", joindate=").append(joindate)
				.append("]");
		return builder.toString();
	}
		
	
	
} // MemberIfmVO
