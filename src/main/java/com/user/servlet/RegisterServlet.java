package com.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DB.DBConnect;
import com.dao.UserDaoImpl;
import com.entity.User;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String name=req.getParameter("name");
			String email=req.getParameter("email");
			String phoneno=req.getParameter("phoneno");
			String password=req.getParameter("password");
			String check=req.getParameter("check");
			
//			System.out.println(name+" "+email+" "+phoneno+" "+password+" "+check);
			
			User user = new User();
			user.setName(name);
			user.setEmail(email);
			user.setPhoneno(phoneno);
			user.setPassword(password);
			
			HttpSession session = req.getSession();
			
			if (check!=null) {
				UserDaoImpl dao = new UserDaoImpl(DBConnect.getConn());
				boolean f2 = dao.checkUser(email);
				if (f2) {
					boolean b = dao.userRegister(user);
					if (b) {
//						System.out.println("User Register Success...");
						session.setAttribute("succMsg", "Registration Successfully..");
						resp.sendRedirect("register.jsp");
					} else {
//		                System.out.println("Something went Wrong on Server..");
						session.setAttribute("failedMsg", "Something went Wrong on Server..");
						resp.sendRedirect("register.jsp");
					}
				} else {
					    System.out.println("Something went Wrong on Server..");
						session.setAttribute("failedMsg", "User Already Exist Try Another Email id");
						resp.sendRedirect("register.jsp");
				}
			} else {
//                 System.out.println("Please check agree & Terms Condition");
				session.setAttribute("failedMsg", "Please check agree & Terms Conditions");
				resp.sendRedirect("register.jsp");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
	
	
}
