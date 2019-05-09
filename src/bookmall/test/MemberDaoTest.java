package bookmall.test;

import bookmall.dao.MemberDao;
import bookmall.vo.MemberVo;

public class MemberDaoTest {
	public static void main(String[] args) {
		System.out.println("멤버 insert 성공:"+memberinsert("김석현", "010-6866-9202", "skok1025@naver.com", "1234"));
		System.out.println("멤버 insert 성공:"+memberinsert("유서정", "010-2626-8234", "uscasc@naver.com", "1234"));
		System.out.println("멤버 입력완료");
	}
	public static Boolean memberinsert(String name,String tel,String email,String passwd) {
		MemberVo vo = new MemberVo();
		vo.setName(name);
		vo.setTel(tel);
		vo.setEmail(email);
		vo.setPasswd(passwd);
		
		return new MemberDao().insert(vo);
	}

}
