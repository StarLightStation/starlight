package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.ProductDAO;
import model.ProductVO;

@WebServlet("/fruitkha-1.0.0/filterSearch.do")
public class FilterSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FilterSearchController() {
		super();

	}
	private void doAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<ProductVO> pdatas = new ArrayList<ProductVO>();

		ProductVO pVO = new ProductVO();
		ProductDAO pDAO = new ProductDAO();

		//	사용자가 입력한 이름 키워드 값 가져오기.
		String pName = request.getParameter("pName");

		System.out.println("log : FilterSearchController.java : doAction : pName : " + pName);

		//	사용자가 입력한 이름 키워드 값을 pVO 객체에 set.
		pVO.setpName(pName);

		//	이름 키워드 값을 가지고, DB 치고 나오기.
		pdatas = pDAO.selectAll(pVO);

		System.out.println("log : FilterSearchController.java : doAction : pdatas : " + pdatas);

		//	인코딩 깨져서 추가.
		response.setCharacterEncoding("UTF-8");

		// Gson 객체 생성
		Gson gson = new Gson();
		
		// pdatas를 JSON 형식으로 변환
		String jsonData = gson.toJson(pdatas);

		//	out 객체에 담아서 View 에게 보내기.
		PrintWriter out = response.getWriter();

		//	View 에게 pdatas 데이터 보내기.
		out.print(jsonData);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

}