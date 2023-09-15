package com.spring.biz.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.spring.biz.common.JDBCUtil;

//	static final String SQL_SELECTONE_FINDPW
//	= "SELECT MID,MPW,MNAME,SUBSCRIPTION,ISADMIN,MPHONE,SIGNUPKIND FROM MEMBER WHERE MID = ?";
//	비밀번호 찾기는 중간 프로젝트 이후 추가할 예정.
//	static final String SQL_UPDATE_CHANGENAME = "UPDATE MEMBER SET MNAME = ? WHERE MID = ?";
//	PW, NAME SQL문 합치기로 해서 주석 처리.

public class MemberDAO {

	static final String SQL_INSERT = "INSERT INTO MEMBER (MID,MPW,MNAME,MPHONE,SIGNUPKIND) VALUES (?,?,?,?,?)";
	static final String SQL_SELECTALL = "SELECT MID,MPW,MNAME,SUBSCRIPTION,ISADMIN,MPHONE,SIGNUPKIND FROM MEMBER";
	static final String SQL_SELECTONE_SIGNIN
	= "SELECT MID,MPW,MNAME,SUBSCRIPTION,ISADMIN,MPHONE,SIGNUPKIND FROM MEMBER WHERE MID = ? AND MPW = ?";
	static final String SQL_SELECTONE_INFO
	= "SELECT MID,MPW,MNAME,SUBSCRIPTION,ISADMIN,MPHONE,SIGNUPKIND FROM MEMBER WHERE MID = ?";
	static final String SQL_SELECTONE_SIGNUP
	= "SELECT MID,MPW,MNAME,SUBSCRIPTION,ISADMIN,MPHONE,SIGNUPKIND FROM MEMBER WHERE MID = ? OR MPHONE = ?";
	static final String SQL_SELECTONE_FINDID
	= "SELECT MID,MPW,MNAME,SUBSCRIPTION,ISADMIN,MPHONE,SIGNUPKIND FROM MEMBER WHERE MPHONE = ?";
	static final String SQL_UPDATE_CHANGE = "UPDATE MEMBER SET MPW = ?, MNAME = ?, MPHONE = ? WHERE MID = ?";
	static final String SQL_UPDATE_CHANGESUBS = "UPDATE MEMBER SET SUBSCRIPTION = 1 WHERE MID = ?";
	static final String SQL_DELETE = "DELETE FROM MEMBER WHERE MID = ?";

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public boolean insert(MemberVO mVO) {	//	회원 가입.
		
		System.out.println("log : MemberDAO : insert()");

		conn = JDBCUtil.connect();

		try {
			pstmt = conn.prepareStatement(SQL_INSERT);
			pstmt.setString(1, mVO.getmID()); 
			pstmt.setString(2, mVO.getmPW()); 
			pstmt.setString(3, mVO.getmName());
			pstmt.setString(4, mVO.getmPhone());
			pstmt.setString(5, mVO.getSignUpKind());

			int result = pstmt.executeUpdate();

			if(result <= 0) {
				return false;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			try {
				Thread.sleep(1000);
			}
			catch (InterruptedException e1) {
				e1.printStackTrace();
				return false;
			}
		}
		finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return true;
	}

	public ArrayList<MemberVO> selectAll(MemberVO mVO) {	//	회원 목록 전체 출력. (관리자 페이지)

		System.out.println("log : MemberDAO : selectAll()");
		
		ArrayList<MemberVO> mdatas = new ArrayList<MemberVO>();

		conn = JDBCUtil.connect();

		try {
			pstmt = conn.prepareStatement(SQL_SELECTALL);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				MemberVO mdata = new MemberVO();	//	while문 돌릴때는, 각각의 경우에 객체를 생성 하는 개념이라, 여기다가 new 해야함.
				mdata.setmID(rs.getString("MID"));	
				mdata.setmPW(rs.getString("MPW"));	
				mdata.setmName(rs.getString("MNAME"));
				mdata.setSubscription(rs.getInt("SUBSCRIPTION"));
				mdata.setIsAdmin(rs.getInt("ISADMIN"));
				mdata.setmPhone(rs.getString("MPHONE"));
				mdata.setSignUpKind(rs.getString("SIGNUPKIND"));
				mdatas.add(mdata);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
		finally {
			JDBCUtil.disconnect(rs, pstmt, conn);
		}
		return mdatas;
	}

	public MemberVO selectOne(MemberVO mVO) {	//	1) 로그인 2) 회원 정보 3) 회원 가입 4) 아이디 찾기

		System.out.println("log : MemberDAO : selectOne()");
		
		MemberVO mdata = new MemberVO();

		conn = JDBCUtil.connect();

		try {
			if(mVO.getSk().equals("SIGNIN")) { 	// 	로그인 유효성 검사.
				pstmt = conn.prepareStatement(SQL_SELECTONE_SIGNIN);
				pstmt.setString(1, mVO.getmID());
				pstmt.setString(2, mVO.getmPW());

				rs = pstmt.executeQuery();

				if(rs.next()) {
					mdata.setmID(rs.getString("MID"));
					mdata.setmPW(rs.getString("MPW"));
					mdata.setmName(rs.getString("MNAME"));
					mdata.setSubscription(rs.getInt("SUBSCRIPTION"));
					mdata.setIsAdmin(rs.getInt("ISADMIN"));
					mdata.setmPhone(rs.getString("MPHONE"));
					mdata.setSignUpKind(rs.getString("SIGNUPKIND"));
				}
				else {
					return null;	//	유효 하지 않은 아이디와 비밀번호 이면, C에게 null값 반환.
				}
			}
			else if(mVO.getSk().equals("INFO")) {	//	회원 정보 불러 오기.
				pstmt = conn.prepareStatement(SQL_SELECTONE_INFO);
				pstmt.setString(1, mVO.getmID());

				rs = pstmt.executeQuery();

				if(rs.next()) {
					mdata.setmID(rs.getString("MID"));
					mdata.setmPW(rs.getString("MPW"));
					mdata.setmName(rs.getString("MNAME"));
					mdata.setSubscription(rs.getInt("SUBSCRIPTION"));
					mdata.setIsAdmin(rs.getInt("ISADMIN"));
					mdata.setmPhone(rs.getString("MPHONE"));
					mdata.setSignUpKind(rs.getString("SIGNUPKIND"));
				}
				else {
					return null;	//	필요한 코드인가 ?
				}
			}
			else if(mVO.getSk().equals("SIGNUP")) {	//	회원 가입 중복 검사. (WHERE MID = ? OR MPHONE = ?)
				pstmt = conn.prepareStatement(SQL_SELECTONE_SIGNUP);
				pstmt.setString(1, mVO.getmID());
				pstmt.setString(2, mVO.getmPhone());

				rs = pstmt.executeQuery();

				if(!(rs.next())) {	//	로직 수정 필요. rs.next()가 false 이면 ! 으로 부정 해서 if 문을 true로..
					return null;	//	중복 되는 아이디인 경우, C에게 null값 반환.
				}
			}
			else if(mVO.getSk().equals("FINDID")) {	//	아이디 (이메일) 찾기 유효성 검사.
				pstmt = conn.prepareStatement(SQL_SELECTONE_FINDID);
				pstmt.setString(1, mVO.getmPhone());

				rs = pstmt.executeQuery();

				if(rs.next()) {
					mdata.setmID(rs.getString("MID"));
					mdata.setmPW(rs.getString("MPW"));
					mdata.setmName(rs.getString("MNAME"));
					mdata.setSubscription(rs.getInt("SUBSCRIPTION"));
					mdata.setIsAdmin(rs.getInt("ISADMIN"));
					mdata.setmPhone(rs.getString("MPHONE"));
					mdata.setSignUpKind(rs.getString("SIGNUPKIND"));
				}
				else {
					return null;	//	유효 하지 않은 휴대폰 번호이면, C에게 null값 반환.
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			try {
				Thread.sleep(1000);
			}
			catch (InterruptedException e1) {
				e1.printStackTrace();
				return null;
			}
		}
		finally {
			JDBCUtil.disconnect(rs, pstmt, conn);
		}
		return mdata;
		//	로그인 시도시, 유효한 아이디와 비밀번호 이면, C에게 mdata 반환.
		//	로그인된 상황일 때, 유효한 아이디면, C에게 mdata 반환. (회원 정보 불러 오기.)
		//	회원 가입시, 중복 되는 아이디가 아닌 경우 C에게 mdata 반환.
		//	아이디 찾기시, 유효한 핸드폰 번호 이면, C에게 mdata 반환.
	}

	public boolean update(MemberVO mVO) {	//	회원 정보 변경. (비밀번호 변경, 닉네임 변경, 구독 정보 변경.)

		System.out.println("log : MemberDAO : update()");
		
		conn = JDBCUtil.connect();

		try {
			if(mVO.getSk().equals("CHANGE")) {
				pstmt = conn.prepareStatement(SQL_UPDATE_CHANGE);
				pstmt.setString(1, mVO.getTmpMpw());	
				pstmt.setString(2,mVO.getTmpMname());	
				pstmt.setString(3,mVO.getTmpMphone());	
				pstmt.setString(4, mVO.getmID());
			}
			else if(mVO.getSk().equals("CHANGESUBS")) {
				pstmt = conn.prepareStatement(SQL_UPDATE_CHANGESUBS);	//	구독 신청을 하면 0에서 -> 1으로 변경.
				pstmt.setString(1, mVO.getmID());						
			}
			//	(사용자가 구독을 취소 해도 바로 취소 되지 않고, 유효 기간 만료날 까지는 구독이 유지 된다.)
			//	(그래서 해줄게 위에 코드 밖에 없다.)

			/*
			else if(mVO.getSk().equals("CHANGENAME")) {
				pstmt = conn.prepareStatement(SQL_UPDATE_CHANGENAME);
				pstmt.setString(1, mVO.getTmpMname());
				pstmt.setString(2, mVO.getmID());
			}
			 */

			int result = pstmt.executeUpdate();

			if(result <= 0) {
				return false;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			try {
				Thread.sleep(1000);
			}
			catch (InterruptedException e1) {
				e1.printStackTrace();
				return false;
			}
		}
		finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return true;
	}

	public boolean delete(MemberVO mVO) {	//	회원 탈퇴.

		System.out.println("log : MemberDAO : delete()");
		
		conn = JDBCUtil.connect();

		try {
			pstmt = conn.prepareStatement(SQL_DELETE);
			pstmt.setString(1, mVO.getmID());

			int result = pstmt.executeUpdate();

			if(result <= 0) {
				return false;
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
			try {
				Thread.sleep(1000);
			}
			catch (InterruptedException e1) {
				e1.printStackTrace();
				return false;
			}
		}
		finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return true;
	}

}	//	MemberDAO

