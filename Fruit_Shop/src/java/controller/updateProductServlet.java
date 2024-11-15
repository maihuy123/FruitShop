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
import java.util.List;
import model.Category;
import model.Product;

/**
 *
 * @author tuong
 */
public class updateProductServlet extends HttpServlet {

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
        String pid = request.getParameter("pid");
        DAO dao = new DAO();
        Product pro = dao.getProById(pid);
        List<Category> listCa = dao.getAllCategory();
        int caId = pro.getCateId();
        request.setAttribute("cur", caId);
        request.setAttribute("detail", pro);
        request.setAttribute("listCa", listCa);
        request.getRequestDispatcher("updateProduct.jsp").forward(request, response);

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
        processRequest(request, response);
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
        String id_raw = request.getParameter("id");
        String name = request.getParameter("name");
        String caId_raw = request.getParameter("category");
        String quantity_raw = request.getParameter("quantity");
        String price_raw = request.getParameter("price");
        String description = request.getParameter("description");
        String img = request.getParameter("img");
        DAO dao = new DAO();
        try {
            int id = Integer.parseInt(id_raw);
            int quantity = Integer.parseInt(quantity_raw);
            float price = Float.parseFloat(price_raw);
            int caId = Integer.parseInt(caId_raw);
            dao.updateProduct(name, description, id, caId, quantity, price);
            
            dao.updateProImgById(id, img);
        } catch (NumberFormatException e) {
        }
         String pid = request.getParameter("pid");
        
        Product pro = dao.getProById(pid);
        List<Category> listCa = dao.getAllCategory();
        int caId = pro.getCateId();
        request.setAttribute("cur", caId);
        request.setAttribute("detail", pro);
        request.setAttribute("listCa", listCa);
        request.getRequestDispatcher("updateProduct.jsp").forward(request, response);
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
