package com.hp.house.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hp.house.dao.ShouruDao;
import com.hp.house.dao.impl.ShouruDaoImpl;
import com.hp.house.entity.PageInfo;
import com.hp.house.entity.Shouru;



/**
 * Servlet implementation class DeptServlet
 */
@WebServlet("/ShouruController")
public class ShouruController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ShouruDao shouruDao = new ShouruDaoImpl();

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String type = request.getParameter("type");
		if (type != null) {
			switch (type) {
			case "list":
				list(request, response);
				break;
			case "listAll":
				listAll(request, response);
				break;
			case "add":
				add(request, response);
				break;
			case "toUpdate":
				toUpdate(request, response);
				break;
			case "update":
				update(request, response);
				break;
			case "delete":
				delete(request, response);
				break;
			default:
				break;
			}
		} else {
			response.sendRedirect("wq.jsp");
		}
	}

	protected void listAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Shouru> list = shouruDao.ListAll();
		request.getSession().setAttribute("list", list);
		response.setContentType("text/html;charset=UTF-8");
		response.sendRedirect("shour/eShouru.jsp");
	}
	protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String current = request.getParameter("current") ;	
		int c= 1;
		if(current!=null) 
		c=Integer.parseInt(request.getParameter("current"));
		PageInfo<Shouru> pageInfo = shouruDao.findByPage(c,3);
		String jsonStr = new ObjectMapper().writeValueAsString(pageInfo);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}

	

	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void toUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
	}

	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
	}

}
