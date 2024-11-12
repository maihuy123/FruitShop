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
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import model.Discount;
import model.OrderDetail;
import model.OrderItem;
import model.Product;
import model.SiteUser;

/**
 *
 * @author tuong
 */
public class viewOrderServler extends HttpServlet {

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
            out.println("<title>Servlet viewOrderServler</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet viewOrderServler at " + request.getContextPath() + "</h1>");
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
        String id_raw = request.getParameter("odId");
        DAO act = new DAO();
        try {
            int id = Integer.parseInt(id_raw);
            List<OrderItem> list = act.getAllItemById(id);
            request.setAttribute("od", list);
            float totalPrice = act.getTotalOrderPrice(id);
            request.setAttribute("total", totalPrice);
            OrderDetail curOd = act.getOderDeById(id);
            request.setAttribute("curOd", curOd);
            String stasus = curOd.getStatus();
            request.setAttribute("status", stasus);
            List<Product> listPr = act.getAllProduct();
            request.setAttribute("pro", listPr);
            List<Discount> listDi = act.getAllDiscount();
            request.setAttribute("dis", listDi);
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("viewOrderItem.jsp").forward(request, response);
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
        String id_raw = request.getParameter("odId");
        DAO act = new DAO();
        try {
            // Retrieve new status from the request
            String status = request.getParameter("newSta");
            int id = Integer.parseInt(id_raw);
            if (status != null && !status.isEmpty()) {
                act.setOrderStatus(id, status);
            }
            String search = request.getParameter("search");
            List<OrderItem> list;

            if (search != null && !search.isEmpty()) {

                int searchId = Integer.parseInt(search);
                list = act.getAllItemById(id).stream()
                        .filter(item -> item.getId() == searchId)
                        .collect(Collectors.toList());
            } else {

                list = act.getAllItemById(id);
            }

            request.setAttribute("od", list);

            float totalPrice = act.getTotalOrderPrice(id);
            request.setAttribute("total", totalPrice);

            OrderDetail curOd = act.getOderDeById(id);
            request.setAttribute("curOd", curOd);
            String orderStatus = curOd.getStatus();
            request.setAttribute("status", orderStatus);

            List<Product> listPr = act.getAllProduct();
            request.setAttribute("pro", listPr);

            List<Discount> listDi = act.getAllDiscount();
            request.setAttribute("dis", listDi);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Forward to the JSP page
        request.getRequestDispatcher("viewOrderItem.jsp").forward(request, response);
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
