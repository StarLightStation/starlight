package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberDAO {
	
	static final String sql_insert="INSERT INTO MEMBER (MID,MPW,NAME) VALUES(?,?,?)";
	static final String sql_selectAll="SELECT MID,MPW,NAME,TOTAL,MGRADE,GRADEUP FROM MEMBER";

	static final String sql_selectOne="SELECT MID,MPW,NAME,TOTAL,MGRADE,GRADEUP FROM MEMBER WHERE MID=? AND MPW=?";
	static final String sql_selectOne_ID = "SELECT MID,MPW,NAME,TOTAL,MGRADE,GRADEUP FROM MEMBER WHERE MID=?";

	static final String sql_update_MPW="UPDATE MEMBER SET MPW=? WHERE MID=?";
	static final String sql_update_TOTAL="UPDATE MEMBER SET TOTAL=TOTAL+? WHERE MID=?";
	static final String sql_update_NAME="UPDATE MEMBER SET NAME=? WHERE MID=?";
	static final String sql_update_GRADEUP = "UPDATE MEMBER SET MGRADE =?,GRADEUP =? WHERE MID =?";
	//	update 에서 set을 두번하는 경우는 , 를 사용해서 이어준다.
	
	//	static final String sql_update_GRADEUP = "UPDATE MEMBER SET GRADEUP =? WHERE MID =?";
	
	static final String sql_delete="DELETE FROM MEMBER WHERE MID =?";

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public boolean insert(MemberVO mVO) { 
		
		// 회원가입
		//		C에서 아이디 비밀번호 받아오기
		//		add하기
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_insert);
			pstmt.setString(1, mVO.getMid()); // 이것은 [1]로 시작함!!!!! // 배열은 [0]번째 부터인데 
			pstmt.setString(2, mVO.getMpw()); // String 인지 int인지 타입 맞춰주기 
			pstmt.setString(3, mVO.getName()); // String 인지 int인지 타입 맞춰주기 
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			return false;
		}
		JDBCUtil.disconnect(pstmt, conn);
		return true;

	}

	public ArrayList<MemberVO> selectAll(MemberVO mVO) {

		ArrayList<MemberVO> mdatas=new ArrayList<MemberVO>();

		conn=JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(sql_selectAll);
			rs=pstmt.executeQuery();

			while(rs.next()) {
				mdatas.add(new MemberVO(rs.getString("MID"), rs.getString("MPW"), rs.getString("NAME"), rs.getInt("TOTAL"),rs.getString("MGRADE"),rs.getInt("GRADEUP"))); // 출력은 fdate, name , rate 만
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
		JDBCUtil.disconnect(rs, pstmt, conn);

		return mdatas;
		
		// 회원 목록 출력(관리자모드)
		//회원목록 반환
	}

	public MemberVO selectOne(MemberVO mVO) {
		
		// 로그인
		// DB와 회원 정보 비교해서 맞으면 return mVO
		// tmp가 필요하네 ~ id pw 다 비교할때, id 중복 검사할때
		//		static final String sql_selectOne="SELECT MID,MPW,NAME,TOTAL,MGRADE,COUPON FROM MEMBER WHERE MID=? AND MPW=?";
		// 		static final String sql_selectOne_ID = "SELECT MID,MPW,NAME,TOTAL,MGRADE,COUPON FROM MEMBER WHERE MID=?;		
		MemberVO mdata=null;

		conn=JDBCUtil.connect();
		try {
			if(mVO.getTmpCondition().equals("회원정보")) { // 로그인 - 회원 정보 비교할때
				pstmt=conn.prepareStatement(sql_selectOne);
				pstmt.setString(1, mVO.getMid());
				pstmt.setString(2, mVO.getMpw());
			}	
			else if(mVO.getTmpCondition().equals("중복검사")) {	
				// 회원가입 - 아이디가 DB에 저장이 되어 있는지 중복이 안 되어 있어야함 ! 
				// 추천인등록 - 아이디가 DB에 저장이 되어 있어야 함! 
				pstmt=conn.prepareStatement(sql_selectOne_ID);
				pstmt.setString(1, mVO.getMid());
			}
			//			컨트롤러에서 if(selectOne()==null){
			//			mDAO.insert();
			//			else {
			//				continue;
			//			}

			rs=pstmt.executeQuery();

			if(rs.next()) {	// 값이 있으면 중복된거
				mdata=new MemberVO(rs.getString("MID"),rs.getString("MPW"),rs.getString("NAME"),rs.getInt("TOTAL"), rs.getString("MGRADE"), rs.getInt("GRADEUP"));
			}
			else {	// 값이 없다면 중복 안 된거
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		JDBCUtil.disconnect(pstmt, conn);

		return mdata;
	}

	public boolean update(MemberVO mVO) {
		// 비밀번호 변경
		//		C에서 "비밀번호변경"으로 넘어오면
		//		비밀번호 변경
		// 이름 변경
		//		C에서 "이름변경"으로 넘어오면
		//		이름 변경
		// 토탈 변경
		// 쿠폰 개수 변경
		//		추천인을 받는다면 
		//		쿠폰 ++
		//		쿠폰을 사용한다면
		//		쿠폰 -- 
		// 		등급 조정


		//		static final String sql_update_MPW="UPDATE MEMBER SET MPW=? WHERE MID=?";
		//		static final String sql_update_NAME="UPDATE MEMBER SET NAME=? WHERE MID=?";
		//		static final String sql_update_TOTAL="UPDATE MEMBER SET TOTAL=TOTAL+? WHERE MID=?";
		//		static final String sql_update_USECOUPON="UPDATE MEMBER SET COUPON=COUPON-1 WHERE MID=?";
		//		static final String sql_update_ADDCOUPON="UPDATE MEMBER SET COUPON=COUPON+1 WHERE MID=?";

		conn=JDBCUtil.connect();
		try {
			if(mVO.getTmpCondition().equals("비번변경")) {
				pstmt=conn.prepareStatement(sql_update_MPW);	
				pstmt.setString(1, mVO.getTmpString());
				pstmt.setString(2, mVO.getMid());
			}
			else if(mVO.getTmpCondition().equals("이름변경")) {
				pstmt=conn.prepareStatement(sql_update_NAME);	
				pstmt.setString(1, mVO.getTmpString());
				pstmt.setString(2, mVO.getMid());
			}
			else if(mVO.getTmpCondition().equals("토탈변경")) {
				pstmt=conn.prepareStatement(sql_update_TOTAL);
				pstmt.setInt(1, mVO.getTmpPrice()); //타입 맞춰주기 //total에 TmpPrice만큼 누적해주기 
				pstmt.setString(2, mVO.getMid());	
			}
			else if(mVO.getTmpCondition().equals("등급변경")) {	// 브론즈 등급이었다면
				if(mVO.getMgrade().equals("BRONZE")) {
					pstmt=conn.prepareStatement(sql_update_GRADEUP);
					pstmt.setString(1, "SILVER");
					pstmt.setInt(2, 1);
					pstmt.setString(3, mVO.getMid());	
				}
				else if(mVO.getMgrade().equals("SILVER")){
					pstmt=conn.prepareStatement(sql_update_GRADEUP);
					pstmt.setString(1, "GOLD");
					pstmt.setInt(2, 2);
					pstmt.setString(3, mVO.getMid());	
				}
				else if(mVO.getMgrade().equals("GOLD")){
					pstmt=conn.prepareStatement(sql_update_GRADEUP);
					pstmt.setString(1, "DIAMOND");
					pstmt.setInt(2, 3);
					pstmt.setString(3, mVO.getMid());	
				}
			}

			int result=pstmt.executeUpdate();
			if(result<=0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			return false;
		}
		JDBCUtil.disconnect(pstmt, conn);
		return true;

	}

	public boolean delete(MemberVO mVO) {
		// 회원 탈퇴
		// 회원 정보 확인 후
		// 회원 정보 없애기
		//		static final String sql_delete="DELETE FROM MEMBER WHERE MID=?";
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_delete);
			pstmt.setString(1, mVO.getMid());
			int result=pstmt.executeUpdate();
			if(result<=0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			return false;
		}
		JDBCUtil.disconnect(pstmt, conn);
		return true;
	}

}
