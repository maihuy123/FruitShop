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
import java.util.List;
import model.Category;
import model.Product;

/**
 *
 * @author tuong
 */
@WebServlet(urlPatterns = {"/homepage"})
public class HomeServlet extends HttpServlet {

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
            out.println("<title>Servlet HomeServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HomeServlet at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAO dao = new DAO();

        int page = 1;
        int recordsPerPage = 10;
        int categoryId = -1; // Default value for all categories

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        if (request.getParameter("caId") != null) {
            categoryId = Integer.parseInt(request.getParameter("caId"));
        }

        List<Product> listPr = dao.getProductsByPageAndCategory((page - 1) * recordsPerPage, recordsPerPage, categoryId);
        int noOfRecords = dao.getProductCountByCategory(categoryId);
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        List<Category> listCa = dao.getAllCategory();
        List<Product> listMoSale = dao.get5MostSaleProduct();
        request.setAttribute("liMoSale", listMoSale);
       List<Product> listSale = dao.get5SaleProduct();
        request.setAttribute("li5sale", listSale);
        List<Product> newest = dao.get5NewestProduct();
        request.setAttribute("li5new", newest);
        request.setAttribute("listPr", listPr);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("liCa", listCa);
        request.setAttribute("caId", categoryId); // Pass the current category ID for highlighting or other use

        request.getRequestDispatcher("Home.jsp").forward(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    DAO dao = new DAO();

    int page = 1;
    int recordsPerPage = 10;

    if (request.getParameter("page") != null) {
        page = Integer.parseInt(request.getParameter("page"));
    }

    String searchQuery = request.getParameter("search");

    List<Product> listPr;
    int noOfRecords;

    if (searchQuery != null && !searchQuery.isEmpty()) {
        listPr = dao.getProductsByName(searchQuery, (page - 1) * recordsPerPage, recordsPerPage);
        noOfRecords = dao.getProductCountByName(searchQuery);
    } else {
        listPr = dao.getProductsByPage((page - 1) * recordsPerPage, recordsPerPage);
        noOfRecords = dao.getProductCount();
    }

    int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

    request.setAttribute("listPr", listPr);
    request.setAttribute("noOfPages", noOfPages);
    request.setAttribute("currentPage", page);
    request.setAttribute("searchQuery", searchQuery);
    
    List<Category> listCa = dao.getAllCategory();
    request.setAttribute("liCa", listCa);

    request.getRequestDispatcher("Home.jsp").forward(request, response);
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
