package model;

public class Client {

	public static void main(String[] args) {
		
		ProductDAO pDAO = new ProductDAO();
		ProductVO pVO = new ProductVO();
		
		//	pVO.setSk("SELECT");
		
			pVO.setpName(null);
			pVO.setpCategory(null);
			pVO.setpAlcohol(20.0);
			pVO.setpSweet(null);
			pVO.setpSour(null);
			pVO.setpSparkle(null);	
		
		//	문자열 자체로 NULL 들어갔는지 확인 하기 위해 테스트.
		// setpSour("NULL");
		// pVO.setpSparkle("NULL");
		
		System.out.println("log : Client : " + pVO + "\n");
		
		pDAO.selectAll(pVO);

	}

}	//	Client
