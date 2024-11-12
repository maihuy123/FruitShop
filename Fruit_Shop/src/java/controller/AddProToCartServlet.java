/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import DAL.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Cart;
import model.CartItem;
import model.Category;
import model.Discount;
import model.Product;
import model.SiteUser;

/**
 *
 * @author tuong
 */
@WebServlet(name="AddProToCartServlet", urlPatterns={"/addProCart"})
public class AddProToCartServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet AddProToCartServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddProToCartServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
        // ADD product to
        SiteUser user = (SiteUser) session.getAttribute("user");
        int userId = user.getId();
        Cart cart = dao.getCartByUserId(userId);
        if(cart == null){
            dao.createCartForUser(userId);
            cart = dao.getCartByUserId(userId);
        }
        
        request.setAttribute("cart", cart);
        List<CartItem> listItem = dao.getAllCartItemByCartId(cart.getId());
        //Add Product
        String addPrId_raw = request.getParameter("addPrId");
        int addPrId = Integer.parseInt(addPrId_raw);
        boolean exist = false;
        for(int i = 0; i < listItem.size(); i++){
            CartItem cur = listItem.get(i);
            if(cur.getPrId()== addPrId){
                dao.updateQuanCartItemById(cur.getId(), cur.getQuantity()+1);
                exist = true;
            }
        }
        if(exist==false){
            dao.insertItemToCart(cart.getId(), addPrId);
            
        }
        listItem = dao.getAllCartItemByCartId(cart.getId());
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
        request.getRequestDispatcher("ShoppingCart.jsp").forward(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
