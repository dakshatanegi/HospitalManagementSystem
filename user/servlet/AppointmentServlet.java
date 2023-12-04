package com.user.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import com.dao.AppointmentDao;
import com.db.DBConnect;
import com.entity.Appointment;

@WebServlet("/appAppointment")
public class AppointmentServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int userId = Integer.parseInt(req.getParameter("userId"));
		String fullName = req.getParameter("fullName");
		String gender = req.getParameter("gender");
		String age = req.getParameter("age");
		String appointDate = req.getParameter("appointDate");
		String email = req.getParameter("email");
		String phoneNo = req.getParameter("phoneNo");
		String diseases = req.getParameter("diseases");
		int doctorId = Integer.parseInt(req.getParameter("doctorId"));
		String address = req.getParameter("address");

		Appointment ap = new Appointment(userId, fullName, gender, age, appointDate, email, phoneNo, diseases, doctorId,
				address, "Pending");

		AppointmentDao dao = new AppointmentDao(DBConnect.getConn());
		HttpSession session = req.getSession();

		if (dao.addAppointment(ap)) {
			session.setAttribute("SuccessMsg", "Appointment Sucessfully");
			resp.sendRedirect("userAppointment.jsp");
		} else {
			session.setAttribute("ErrorMsg", "Something wrong on server");
			resp.sendRedirect("userAppointment.jsp");
		}

	}

}