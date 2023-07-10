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

@WebServlet("/update_profile")
public class UpdateProfileServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			String name = req.getParameter("name");
			String email = req.getParameter("email");
			String phoneno = req.getParameter("phoneno");
			String password = req.getParameter("password");
			
			User user = new User();
			user.setId(id);
			user.setName(name);
			user.setEmail(email);
			user.setPhoneno(phoneno);
			
			HttpSession session = req.getSession();
			UserDaoImpl dao = new UserDaoImpl(DBConnect.getConn());
			
			boolean f = dao.checkPassword(id,password);
			if (f) {
				boolean f2 = dao.updateProfile(user);
				if (f2) {
					session.setAttribute("succMsg","Profile Update Successfully..");
					resp.sendRedirect("edit_profile.jsp");
				} else {
					session.setAttribute("failedMsg","Something Wrong On Server");
					resp.sendRedirect("edit_profile.jsp");
				}
			} else {
				session.setAttribute("failedMsg","Your Password is Incorrect");
				resp.sendRedirect("edit_profile.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
