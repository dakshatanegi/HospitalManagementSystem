package com.doctor.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.dao.AppointmentDao;
import com.db.DBConnect;

@WebServlet("/updateStatus")
public class UpdateStatus extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			int Id = Integer.parseInt(req.getParameter("Id"));
			int DocId = Integer.parseInt(req.getParameter("DocId"));
			String comment = req.getParameter("comment");

			AppointmentDao dao = new AppointmentDao(DBConnect.getConn());

			HttpSession session = req.getSession();

			if (dao.updateCommentStatus(Id, DocId, comment)) {
				session.setAttribute("SuccessMsg", "Comment Updated");
				resp.sendRedirect("doctor/patient.jsp");
			} else {
				session.setAttribute("ErrorMsg", "Something wrong on server");
				resp.sendRedirect("doctor/patient.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}