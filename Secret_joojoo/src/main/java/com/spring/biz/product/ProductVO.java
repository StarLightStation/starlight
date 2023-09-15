package com.spring.biz.product;

import org.springframework.web.multipart.MultipartFile;

public class ProductVO {   //   상품 / 주류

	private int pNum;   //   상품 번호. (PK)
	private String pName;   //   상품 이름.
	private int pPrice;   //   상품 가격.
	private String pImage;   //   상품 이미지.
	private int pCnt;   //   상품 재고.
	private String pCategory;   //   상품_카테고리.
	private double pAlcohol;   //   상품_도수.
	private String pSweet;   //   상품_단맛.
	private String pSour;   //   상품_신맛.
	private String pSparkle;   //   상품_탄산.
	private String pImagedetail;   //   상품 상세 이미지.

	// --------------------------- 임시변수
	private MultipartFile fileUpload1;
	private MultipartFile fileUpload2;

	// static String FilePath = "C:\\Users\\hjms0\\Documents\\workspace-sts-3.9.11.RELEASE\\Secret_joojoo\\src\\main\\webapp\\assets\\img\\products\\";// 한진만
	static String FilePath = "E:\\down\\sworkspace\\secret_joojoo\\src\\main\\webapp\\assets\\img\\products\\";
	// 경로

	public static String getFilePath() {
		return FilePath;
	}

	public static void setFilePath(String filePath) {
		FilePath = filePath;
	}


	public MultipartFile getFileUpload1() {
		return fileUpload1;
	}
	public void setFileUpload1(MultipartFile fileUpload1) {
		this.fileUpload1 = fileUpload1;
	}
	public MultipartFile getFileUpload2() {
		return fileUpload2;
	}
	public void setFileUpload2(MultipartFile fileUpload2) {
		this.fileUpload2 = fileUpload2;
	}



	private String sk;   //   Search Keyword.

	private int pStarCnt;
	private double pStarAvg;
	private int tmpCnt;
	//   장바구니는 세션으로 관리 => JAVA <--> View
	//   같은 상품을 장바구니에 담았을 경우, cnt를 관리하기 위한 임시 변수.

	public int getpNum() {
		return pNum;
	}
	public void setpNum(int pNum) {
		this.pNum = pNum;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public int getpPrice() {
		return pPrice;
	}
	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}
	public String getpImage() {
		return pImage;
	}
	public void setpImage(String pImage) {
		this.pImage = pImage;
	}
	public int getpCnt() {
		return pCnt;
	}
	public void setpCnt(int pCnt) {
		this.pCnt = pCnt;
	}
	public String getpCategory() {
		return pCategory;
	}
	public void setpCategory(String pCategory) {
		this.pCategory = pCategory;
	}
	public double getpAlcohol() {
		return pAlcohol;
	}
	public void setpAlcohol(double pAlcohol) {
		this.pAlcohol = pAlcohol;
	}
	public String getpSweet() {
		return pSweet;
	}
	public void setpSweet(String pSweet) {
		this.pSweet = pSweet;
	}
	public String getpSour() {
		return pSour;
	}
	public void setpSour(String pSour) {
		this.pSour = pSour;
	}
	public String getpSparkle() {
		return pSparkle;
	}
	public void setpSparkle(String pSparkle) {
		this.pSparkle = pSparkle;
	}
	public String getpImagedetail() {
		return pImagedetail;
	}
	public void setpImagedetail(String pImagedetail) {
		this.pImagedetail = pImagedetail;
	}

	public String getSk() {
		return sk;
	}
	public void setSk(String sk) {
		this.sk = sk;
	}
	public int getpStarCnt() {
		return pStarCnt;
	}
	public void setpStarCnt(int pStarCnt) {
		this.pStarCnt = pStarCnt;
	}
	public double getpStarAvg() {
		return pStarAvg;
	}
	public void setpStarAvg(double pStarAvg) {
		this.pStarAvg = pStarAvg;
	}
	public int getTmpCnt() {
		return tmpCnt;
	}
	public void setTmpCnt(int tmpCnt) {
		this.tmpCnt = tmpCnt;
	}

	/*
   @Override
   public String toString() {

      return this.pName + "\n";
   }
	 */


	@Override
	public String toString() {

		return "상품 : " + this.pName + " / 가격 : " + this.pPrice + " / 별점 평균 : " + this.pStarAvg
				+ " / 상품 개수 : " + this.pCnt + " / 카테고리 : " + this.pCategory
				+ " / 알콜 도수 : " + this.pAlcohol + " / 단맛 : " + this.pSweet
				+ " / 신맛 : " + this.pSour + " / 탄산감 : " + this.pSparkle + "\n";
	}


	@Override
	public boolean equals(Object obj) {   //   Object 타입의 매개변수 obj를 ProductVO 타입으로 다운 캐스팅 == 다형성

		ProductVO pVO = (ProductVO)obj;

		if(this.pNum == pVO.pNum) {
			return true;
		}
		return false;
	}

}   //   ProductVO