/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAL.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.SiteUser;

/**
 *
 * @author tuong
 */
public class UserInforServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserInforServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserInforServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DAO dao = new DAO();
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            request.getRequestDispatcher("login").forward(request, response);
            return; // Ensure further code is not executed after forwarding
        }
        String userId_raw = request.getParameter("userId");
        if (userId_raw != null) {
            int userId = Integer.parseInt(userId_raw);
            SiteUser user = dao.getUserById(userId);
            request.setAttribute("user", user);
        } else {
            SiteUser user = (SiteUser) session.getAttribute("user");
            request.setAttribute("user", user);
        }
        request.getRequestDispatcher("UserProfile.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao = new DAO();
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("edit")) {
            String username = request.getParameter("username");
            String fname = request.getParameter("fname");
            String lname = request.getParameter("lname");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            String gmail = request.getParameter("gmail");
            String userId_raw = request.getParameter("userId");
            int userId = Integer.parseInt(userId_raw);
            dao.updateUserProById(username, fname, lname, address, phone, gmail, userId);
            SiteUser user = dao.getUserById(userId);
            request.setAttribute("user", user);
        } else if (action.equalsIgnoreCase("pass")) {
            String userId_raw = request.getParameter("userId");
            int userId = Integer.parseInt(userId_raw);
            SiteUser user = dao.getUserById(userId);
            String curPass = request.getParameter("curPass");
            String newPass = request.getParameter("newPass");
            String confirPass = request.getParameter("confirPass");
            if (!user.getPassword().equals(curPass)) {
                request.setAttribute("msgCur", "Wrong Password!");
            } else if (!newPass.equals(confirPass)) {
                request.setAttribute("msgNew", "New pass not equal!");
            } else {
                dao.setNewPassByUserId(userId, newPass);
            }
            request.setAttribute("user", user);
        }
        request.getRequestDispatcher("UserProfile.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
