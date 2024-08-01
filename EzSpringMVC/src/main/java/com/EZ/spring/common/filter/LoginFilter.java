package com.EZ.spring.common.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(filterName="LoginFilter" , urlPatterns= {"/board/*","/member/mypage.kh"})
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }
    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
    	// TODO Auto-generated method stub
    	//서버가 동작 하면 필터코드가 변경되었을떄 필터 생성
    System.out.println("----로그인 필터 생성----");
    }
    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    	// place your code here
    	// pass the request along the filter chain
// 클라이언트 요청-> 필터(필터 코드 실행) <--> DispatcherServiet  
    	// 1)httpServlet Request, HttpServletResponse 으로 다운 캐스팅
    	HttpServletRequest req = (HttpServletRequest)request;
    	HttpServletResponse resp=(HttpServletResponse)response;
    	// 2) 세션 얻어오기
    	HttpSession session = req.getSession();
    	// 3) 로그인 여부 확인
    	//if문 필요 , 로그인이 되었으면 세션에 있는것은? 널이 아니어야한다. 널이면 로그인 페이지로 Redirect
    	String memberId=(String)session.getAttribute("memberId");
    	if(memberId !=null) {
    		//로그인 한 경우 다음 필터 또는 DispatcherServlet 으로 전달
    		chain.doFilter(request, response);    		
    	}else {
    		resp.setContentType("text/html;charset-utf-8");
    		PrintWriter writer=resp.getWriter();
    		//PrintWriter html 출력할 수 있는 출력 스트림
    		writer.println("<script>alert('로그인 정보가 존재하지 않슴다');location.href='/';</script>");
    		resp.sendRedirect("/");
    	}
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	
		//필터 코드가 변경 되면 이전 필터를 파괴하는 메소드
		System.out.println("--이전 로그인 필터 파괴--");
	}



}
