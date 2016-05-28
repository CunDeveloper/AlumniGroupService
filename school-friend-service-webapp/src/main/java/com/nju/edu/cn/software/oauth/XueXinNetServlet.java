package com.nju.edu.cn.software.oauth;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.nju.edu.cn.software.mapper.AuthorMapper;

/**
 * Servlet implementation class XueXinNetServlet
 */
public class XueXinNetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private AuthorMapper authoDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XueXinNetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath()).append("hello world").
		append(authoDao.getTokenIdByAuthorId(1));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
