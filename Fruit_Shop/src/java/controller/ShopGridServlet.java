package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import DAL.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.Discount;
import model.Product;

/**
 *
 * @author tuong
 */
@WebServlet(urlPatterns = {"/shopgrid"})
public class ShopGridServlet extends HttpServlet {

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
            out.println("<title>Servlet ShopGridServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShopGridServlet at " + request.getContextPath() + "</h1>");
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

        int page = 1;
        int recordsPerPage = 9;
        int categoryId = -1; // Default value for all categories

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        if (request.getParameter("caId") != null) {
            categoryId = Integer.parseInt(request.getParameter("caId"));
        }
        List<Product> listSale = dao.get5SaleProduct();
        request.setAttribute("li5sale", listSale);
        List<Product> listPr = dao.getProductsByPageAndCategory((page - 1) * recordsPerPage, recordsPerPage, categoryId);
        List<Discount> listDis = dao.getAllDiscAvail();
        request.setAttribute("liDi", listDis);
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        List<Product> newest = dao.get5NewestProduct();
        request.setAttribute("li5new", newest);
        if (from != null && to != null && !from.isEmpty() && !to.isEmpty()) {
            listPr = getFilteredProducts(listPr, from, to);
        }

        int noOfRecords = dao.getProductCountByCategory(categoryId);
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        List<Category> listCa = dao.getAllCategory();
        request.setAttribute("listPr", listPr);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("liCa", listCa);
        request.setAttribute("caId", categoryId);

        request.getRequestDispatcher("Shop_grid.jsp").forward(request, response);
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
        int categoryId = -1;

        if (request.getParameter("caId") != null) {
            categoryId = Integer.parseInt(request.getParameter("caId"));
        }
        List<Product> listPr = new ArrayList<>();
        if (categoryId != -1) {
            listPr = dao.getAllProByCaId(categoryId);
        } else {
            listPr = dao.getAllProduct();

        }
        String search = request.getParameter("search");

        if (search != null && !search.isEmpty()) {
            if (categoryId != -1) {
                listPr = dao.getAllProByCaIdAndName(categoryId, search);
            } else {
                listPr = dao.getAllProByNa(search);
            }
        }
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        if (from != null && to != null && !from.isEmpty() && !to.isEmpty()) {
            listPr = getFilteredProducts(listPr, from, to);
        }
         List<Discount> listDis = dao.getAllDiscAvail();
        request.setAttribute("liDi", listDis);
        List<Product> listSale = dao.get5SaleProduct();
        request.setAttribute("li5sale", listSale);
         List<Product> newest = dao.get5NewestProduct();
        request.setAttribute("li5new", newest);
        List<Category> listCa = dao.getAllCategory();
        request.setAttribute("listPr", listPr);
        request.setAttribute("liCa", listCa);
        request.setAttribute("caId", categoryId);
        request.getRequestDispatcher("Shop_grid.jsp").forward(request, response);
    }

    public List<Product> getFilteredProducts(List<Product> list, String minRaw, String maxRaw) {
        List<Product> filteredList = new ArrayList<>();
        try {
            float min = Float.parseFloat(minRaw);
            float max = Float.parseFloat(maxRaw);
            for (Product product : list) {
                if (product.getPrice() >= min && product.getPrice() <= max) {
                    filteredList.add(product);
                }
            }
        } catch (NumberFormatException e) {
            // Handle parsing errors if necessary
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
