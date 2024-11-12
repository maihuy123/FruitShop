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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import model.Category;
import model.Product;
import model.SiteUser;

/**
 *
 * @author tuong
 */
public class CustomerServlet extends HttpServlet {

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
            out.println("<title>Servlet CustomerServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CustomerServlet at " + request.getContextPath() + "</h1>");
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
        DAO act = new DAO();
        List<SiteUser> list = act.getAllUser();
        List<SiteUser> listCus = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getRole().equalsIgnoreCase("customer")) {
                listCus.add(list.get(i));
            }
        }
        request.setAttribute("list", listCus);
        request.getRequestDispatcher("adminCustomer.jsp").forward(request, response);
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
    List<SiteUser> allUsers = dao.getAllUser();

    // Filter customers
    List<SiteUser> listCus = allUsers.stream()
                                     .filter(user -> user.getRole().equalsIgnoreCase("customer"))
                                     .collect(Collectors.toList());

    // Initialize listSer for search results
    List<SiteUser> listSer = new ArrayList<>();

    String name = request.getParameter("search");
    if (!name.isEmpty()) {
        // Filter customers by name, ignoring case
    String lowerCaseName = name.toLowerCase();
        // Filter customers by name
        listSer = listCus.stream()
                         .filter(user -> user.getName().toLowerCase().contains(lowerCaseName))
                         .collect(Collectors.toList());
    } else {
        // No search query, show all customers
        listSer.addAll(listCus);
    }

    // Set attribute based on search results
    request.setAttribute("list", listSer);
    request.getRequestDispatcher("adminCustomer.jsp").forward(request, response);
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
