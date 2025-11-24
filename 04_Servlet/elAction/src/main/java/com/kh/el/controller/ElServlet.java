package com.kh.el.controller;

import java.io.IOException;

import com.kh.el.model.vo.Person;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class ElServlet
 */
@WebServlet("/basic.do")
public class ElServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ElServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 데이터를 담을 수 있는 JSP/Servlet의 내장객체(scope)
		 * 
		 * 1) ServletContext (application scope)
		 * 	- 애플케이션 당 1개 존재하는 객체, 전역에 공유, 서버 재시작 전까지 유지.
		 *  - 멀티스레드 환경에서 공유되므로 동시성을 주의
		 *  
		 * 2) HttpSession (session scope)
		 * 	- 브라우저(사용자) 당 1개 존재하는 객체, 로그인 상태/ 사용자별 데이터를 저장하는 것에 적합
		 *  - 이영역에 데이터를 담으면 jsp/servlet단에서 사용이 가능.
		 *  
		 * 3) HttpServletRequest(request scope)
		 * 	- 요청마다 1개 존재하는 객체.
		 *  - 이 영역에 데이터를 담으면 request객체를 포워딩받는 응답jsp까지 사용이 가능
		 *  
		 * 4) PageContext (page scope)
		 *  - jsp페이지마다 존재하는 객체, 가장 좁은 범위 객체
		 *  
		 * 위 객체의 공통 API : 
		 *  setAttribute("키", 값) -> 데이터를 담을 때
		 *  getAttribute("키") -> 데이터를 꺼낼 때
		 *  removeAttribute("키") -> 데이터를 삭제할 때
		 */
		
		//1) application scope
		ServletContext application = request.getServletContext();
		application.setAttribute("scope", "appicaion");
		
		//2) session scope
		HttpSession session = request.getSession();
		session.setAttribute("scope", "session");
		session.setAttribute("academy", "KH");
		session.setAttribute("lecture", new Person("최지원", 22, "여자"));
		 
		//3) request scope
		request.setAttribute("scope", "request");
		request.setAttribute("student", new Person("김지원", 20, "남자"));
		request.setAttribute("classRoom", "Q강의장");

		request.getRequestDispatcher("views/01_el_basic.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
