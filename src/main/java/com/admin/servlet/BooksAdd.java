package com.admin.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.DB.DBConnect;
import com.dao.BookDaoImpl;
import com.entity.BookDetails;

@WebServlet("/add_books")
@MultipartConfig
public class BooksAdd extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			String bookName = req.getParameter("bname");
			String author = req.getParameter("author");
			String price = req.getParameter("price");
			String categories = req.getParameter("categories");
			String status = req.getParameter("status");
			Part part = req.getPart("bimg");
			String fileName = part.getSubmittedFileName();

			BookDetails details = new BookDetails(bookName, author, price, categories, status, fileName, "admin");
			BookDaoImpl dao = new BookDaoImpl(DBConnect.getConn());

			boolean b = dao.addBooks(details);

			HttpSession session = req.getSession();

			if (b) {
				String path = getServletContext().getRealPath("") + "book";
				File f = new File(path);
				part.write(path + File.separator + fileName);

				session.setAttribute("succMsg", "Book Add Successfully");
				resp.sendRedirect("admin/add_books.jsp");
			} else {
				session.setAttribute("failedMsg", "Something Wrong on Servers");
				resp.sendRedirect("admin/add_books.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
