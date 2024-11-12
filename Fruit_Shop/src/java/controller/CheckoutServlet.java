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
import java.util.List;
import model.Cart;
import model.CartItem;
import model.Category;
import model.Discount;
import model.OrderDetail;
import model.Product;
import model.SiteUser;

/**
 *
 * @author tuong
 */
public class CheckoutServlet extends HttpServlet {

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
            out.println("<title>Servlet CheckoutServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckoutServlet at " + request.getContextPath() + "</h1>");
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
        //remove item from cart
        String deleteId_raw = request.getParameter("deleteId");
        if (deleteId_raw != null && !deleteId_raw.isEmpty()) {
            int deleteId = Integer.parseInt(deleteId_raw);
            dao.removeCartItemById(deleteId);
        }
        //Add quantity with id
        String itemId_raw = request.getParameter("quanId");
        if (itemId_raw != null && !itemId_raw.isEmpty()) {
            String quantity_raw = request.getParameter("quantity");
            int itemId = Integer.parseInt(itemId_raw);
            int quantity = Integer.parseInt(quantity_raw);
            dao.updateQuanCartItemById(itemId, quantity);
        }
        SiteUser user = (SiteUser) session.getAttribute("user");
        request.setAttribute("user", user);
        int userId = user.getId();
        Cart cart = dao.getCartByUserId(userId);
        request.setAttribute("cart", cart);
        List<CartItem> listItem = dao.getAllCartItemByCartId(cart.getId());
        request.setAttribute("liItem", listItem);
        //Get total price
        float cartPrice = dao.getTotalCartId(cart.getId());
        request.setAttribute("totalCart", cartPrice);
        //Manage get discount 
        List<Discount> listDiscount = dao.getAllDisByCart(cart.getId());
        request.setAttribute("liDis", listDiscount);
        
        

        List<Category> listCa = dao.getAllCategory();
        request.setAttribute("liCa", listCa);
        List<Product> listPro = dao.getAllProInCart(cart.getId());
        request.setAttribute("liPro", listPro);
        request.getRequestDispatcher("Checkout.jsp").forward(request, response);
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
         PrintWriter out = response.getWriter();
        String addres = request.getParameter("address");
        String Payment = request.getParameter("payment");
        DAO dao = new DAO();
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            request.getRequestDispatcher("login").forward(request, response);
            return; // Ensure further code is not executed after forwarding
        }
        SiteUser user = (SiteUser) session.getAttribute("user");
        dao.insertOrderDetail(user.getId(), Payment, addres);
        int orderId = dao.getRecentOrderDetailId();
        Cart cart = dao.getCartByUserId(user.getId());
       
        List<CartItem> listItem = dao.getAllCartItemByCartId(cart.getId());
        
        if (orderId != 0) {
            for (int i = 0; i < listItem.size(); i++) {
                CartItem cur = listItem.get(i);
                
                dao.insertItemToOrderDetail(cur, orderId);
            }
        }
        dao.removeCartItemByCartId(cart.getId());
request.getRequestDispatcher("homepage").forward(request, response);
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
