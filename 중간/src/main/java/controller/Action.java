package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {// 인터페이스
	ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;
}
