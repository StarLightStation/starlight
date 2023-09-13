package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ProductVO;

@WebServlet("*.update")
public class Updatecart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Updatecart() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 값 받아오기
		// 2. 키값 받아오기
		// 3. cart 세션 받아오기
		// 4. 키값이 포함되는지 확인하기
		// 5. 포함된다면 값 덮어씌우고
		// 6. 세션 갱신하기

		int pnum = Integer.parseInt(request.getParameter("pnum"));
		int count = Integer.parseInt(request.getParameter("count"));
		ProductVO pVO = new ProductVO();//상품 객체

		HttpSession session = request.getSession();//세션 객체
		ArrayList<HashMap<Integer, ProductVO>> cart
		= (ArrayList<HashMap<Integer, ProductVO>>) session.getAttribute("cart");//세션 담아주기 → 장바구니
		HashMap<Integer, ProductVO> hashMap = new HashMap<>();//해쉬맵 객체

		System.out.println("장바구니 재고 변경전 : "+ cart);

		for (int i = 0; i < cart.size(); i++) {
			if (cart.get(i).containsKey(pnum)) {//키가 포함되어 있다면
				pVO = cart.get(i).get(pnum);
				pVO.setTmpCnt(count);
				cart.get(i).remove(pnum);//해쉬맵 제거
				cart.remove(i);//배열에서 제거
				hashMap.put(pnum,pVO);//해쉬맵에 추가
				cart.add(hashMap);//장바구니에 추가

				session.removeAttribute("cart");//기존 장바구니 세션 삭제
				session.setAttribute("cart", cart);//새로운 장바구니로 세션 갱신
				System.out.println("장바구니 재고 변경 후 : "+ cart);

			}
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
