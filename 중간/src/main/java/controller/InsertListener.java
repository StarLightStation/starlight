package controller;

import java.util.ArrayList;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import model.ProductDAO;
import model.ProductVO;
import model.SampleData;

@WebListener
public class InsertListener implements ServletContextListener {

    public InsertListener() {	//	기본 생성자. (디폴트 생성자)
       
    }
    public void contextDestroyed(ServletContextEvent sce)  {	//	소멸 메서드.
        
    }
    public void contextInitialized(ServletContextEvent sce)  {	//	생성 메서드.
       
    	ArrayList<ProductVO> pdatas = controller.SampleData.sample();	//	샘플 데이터 모듈화.
    	
    	ProductDAO pDAO = new ProductDAO();
    	
    	/*
    	for(ProductVO data : pdatas) {
    		pDAO.insert(data);
    	}
    	*/
    	
    	//	pDAO.insert(data) 는 최초 1번만 사용하고, 주석 처리 하기.
    	
    	System.out.println("log : InsertListener : contextInitialized() : OK.");
    }
	
}
