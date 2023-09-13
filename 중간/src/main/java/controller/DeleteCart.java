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

@WebServlet("*.delete")
public class DeleteCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteCart() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();// 세션 객체 생성
		ArrayList<HashMap<ProductVO, Integer>> cart = new ArrayList<HashMap<ProductVO, Integer>>();// 상품 정보들을 담아줄 장바구니
		if (session.getAttribute("cart") == null) {
			session.setAttribute("cart", cart); // YourCartClass는 실제 카트를 나타내는 클래스 이름으로 대체해야 합니다
		}
		cart = (ArrayList<HashMap<ProductVO, Integer>>) session.getAttribute("cart"); // cart 라는 배열에 세션값 담아주기
		System.out.println("pnum : "+request.getParameter("pnum"));
		int pnum = Integer.parseInt(request.getParameter("pnum"));// 상품 번호 가져오기
		for (int i = 0; i < cart.size(); i++) {
			if (cart.get(i).containsKey(pnum)) {// pnum이 있다면
				cart.remove(i);
				session.removeAttribute("cart");// 원래 있던거 삭제
				session.setAttribute("cart", cart);// 새로운 cart로 갱신
			}
		}
	}
}