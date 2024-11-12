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
import java.util.HashMap;
import java.util.List;
import model.OrderDetail;
import model.SiteUser;

/**
 *
 * @author tuong
 */
public class OrderDetailServlet extends HttpServlet {

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
            out.println("<title>Servlet OrderDetailServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderDetailServlet at " + request.getContextPath() + "</h1>");
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
        List<OrderDetail> listOd = act.getAllOrderDetail();
        request.setAttribute("listOd", listOd);
        List<SiteUser> list = act.getAllUser();
        HashMap<Integer, Float> total = new HashMap<>();
        for (int i = 0; i < listOd.size(); i++) {
            float price = act.getTotalOrderPrice(listOd.get(i).getId());
            total.put(listOd.get(i).getId(), price);
        }
        request.setAttribute("listCus", list);
        request.setAttribute("total", total);
        request.getRequestDispatcher("OrderDetail.jsp").forward(request, response);
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
        String status = request.getParameter("status");
        String search = request.getParameter("search");
        DAO act = new DAO();
        List<OrderDetail> listOd = new ArrayList<>();
        if (status != null && !status.equalsIgnoreCase("all")) {
            listOd = act.getListByStatus(status);
        } else {
            listOd = act.getAllOrderDetail();
        }

        request.setAttribute("status", status);

        List<SiteUser> list = act.getAllUser();
        HashMap<Integer, Float> total = new HashMap<>();
        for (int i = 0; i < listOd.size(); i++) {
            float price = act.getTotalOrderPrice(listOd.get(i).getId());
            total.put(listOd.get(i).getId(), price);
        }

        request.setAttribute("listOd", listOd);
        request.setAttribute("listCus", list);
        request.setAttribute("total", total);
        request.getRequestDispatcher("OrderDetail.jsp").forward(request, response);
    }

    public List<OrderDetail> getOdbyStatus(List<OrderDetail> listOd, String status) {
        List<OrderDetail> filteredList = new ArrayList<>();
        for (int i = 0; i < listOd.size(); i++) {
            if (listOd.get(i).getStatus().equalsIgnoreCase(status)) {
                filteredList.add(listOd.get(i));
            }
        }
        return filteredList;
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
