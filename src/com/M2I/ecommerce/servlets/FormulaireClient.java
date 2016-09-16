package com.M2I.ecommerce.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.M2I.ecommerce.beans.Client;
import com.M2I.ecommerce.exceptions.InvalidArgumentException;
import com.M2I.ecommerce.exceptions.MetierException;
import com.M2I.ecommerce.services.ClientService;

@SuppressWarnings("serial")
public class FormulaireClient extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/FormulaireClient.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Client client = ClientService.get().creerClient(
					request.getParameter("nom"),
					request.getParameter("prenom"),
					request.getParameter("email"),
					request.getParameter("motDePasse"),
					request.getParameter("codePostal"),
					request.getParameter("pays"),
					request.getParameter("ville"),
					request.getParameter("nrue"),
					request.getParameter("rue"),
					request.getParameter("appartement"),
					request.getParameter("telephone") );

			request.setAttribute("clientBean", client);
		} catch (InvalidArgumentException | MetierException e) {
			e.printStackTrace();
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/Client.jsp").forward(request, response);

	}
}
