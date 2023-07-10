package com.user.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DB.DBConnect;
import com.dao.BookOrderImpl;
import com.dao.CartDaoImpl;
import com.entity.BookOrder;
import com.entity.Cart;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

			HttpSession session = req.getSession();

			int id = Integer.parseInt(req.getParameter("id"));

			String name = req.getParameter("username");
			String email = req.getParameter("email");
			String phoneno = req.getParameter("phoneno");
			String address = req.getParameter("address");
			String landmark = req.getParameter("landmark");
			String city = req.getParameter("city");
			String state = req.getParameter("state");
			String pincode = req.getParameter("pincode");
			String paymentType = req.getParameter("payment");

			String fulladd = address + "," + landmark + " ," + city + "," + state + "," + pincode;
//			System.out.println(name + " " + email + " " + phoneno + " " + fulladd + " " + paymentType);

			CartDaoImpl dao = new CartDaoImpl(DBConnect.getConn());

			List<Cart> list = dao.getBookByUser(id);
			if (list.isEmpty()) {
				   session.setAttribute("failedMsg", "Add Item");
                   resp.sendRedirect("checkout.jsp");
			} else {
				BookOrderImpl dao2 = new BookOrderImpl(DBConnect.getConn());
				BookOrder o = null;

				ArrayList<BookOrder> orderList = new ArrayList<BookOrder>();
				Random r = new Random();
				for (Cart c : list) {
					// System.out.println(c.getBookName()+" "+c.getAuthor()+" "+c.getPrice());
					o = new BookOrder();
					o.setOrderId("BOOK-ORD-00" + r.nextInt(1000));
					o.setUserName(name);
					o.setEmail(email);
					o.setPhoneno(phoneno);
					o.setFulladd(fulladd);
					o.setBookName(c.getBookName());
					o.setAuthor(c.getAuthor());
					o.setPrice(c.getPrice() + "");
					o.setPaymentType(paymentType);
					orderList.add(o);

				}

				if ("noselect".equals(paymentType)) {
					session.setAttribute("failedMsg", "Choose Payment Method");
					resp.sendRedirect("checkout.jsp");
				} else {
					boolean f = dao2.saveOrder(orderList);
					if (f) {
						resp.sendRedirect("order_success.jsp");
//						System.out.println("order success");
					} else {
						session.setAttribute("failedMsg", "Your Order Failed");
						resp.sendRedirect("order_success.jsp");
//						System.out.println("order failed");
					}
				}
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}
}
