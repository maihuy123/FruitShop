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
import model.Category;
import model.Product;

/**
 *
 * @author tuong
 */
public class ProductAdminServlet extends HttpServlet {

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
            out.println("<title>Servlet ProductServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductServlet at " + request.getContextPath() + "</h1>");
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
        List<Product> listPr = act.getAllProduct();
        List<Category> listCa = act.getAllCategory();
        request.setAttribute("listPr", listPr);
        request.setAttribute("listCa", listCa);
        request.getRequestDispatcher("adminProduct.jsp").forward(request, response);
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
    DAO act = new DAO();
    List<Product> listPrCur = act.getAllProduct();
    List<Category> listCa = act.getAllCategory();
    String caId_raw = request.getParameter("caId");
    String name = request.getParameter("search");
    
    try {
        // Initialize caId with a default value if it's not present or invalid
        int caId = caId_raw != null && !caId_raw.isEmpty() ? Integer.parseInt(caId_raw) : -1;

        if (caId == -1 && (name == null || name.isEmpty())) {
            // No category and no search query provided, return all products
            request.setAttribute("listPr", listPrCur);
        } else if (name != null && !name.isEmpty()) {
            // Search query provided, filter products by name
            List<Product> list3 = getPrByNa(name, listPrCur);
            request.setAttribute("listPr", list3);
        } else {
            // Category selected, filter products by category ID
            List<Product> list2 = getPrByCaId(caId, listPrCur);
            request.setAttribute("listPr", list2);
        }

        // Common attributes
        request.setAttribute("listCa", listCa);
        request.setAttribute("cur", caId);

        request.getRequestDispatcher("adminProduct.jsp").forward(request, response);
    } catch (ServletException | IOException | NumberFormatException e) {
        e.printStackTrace(); // Prefer using a logging framework
    }
}


    public List<Product> getPrByCaId(int caID, List<Product> list1) {
        List<Product> list2 = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            if (caID == list1.get(i).getCateId()) {
                list2.add(list1.get(i));
            }
        }
        if (list2.isEmpty()) {
            return null;
        }
        return list2;
    }

    public List<Product> getPrByNa(String name, List<Product> list1) {
        List<Product> list2 = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i).getName().equalsIgnoreCase(name)) {
                list2.add(list1.get(i));
            }
        }
        if (list2.isEmpty()) {
            return null;
        }
        return list2;
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
