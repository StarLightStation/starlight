package model;

public class Test03 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Test03.java : OK" + "\n");
		
		OrderVO oVO = new OrderVO();
		OrderSetDAO osDAO = new OrderSetDAO();
		
		oVO.setmID("HWANII");
		
		osDAO.selectAll(oVO);

	}

}
