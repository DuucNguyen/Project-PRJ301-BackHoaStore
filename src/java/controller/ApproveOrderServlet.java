/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.productsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import models.CartDTO;
import models.*;

/**
 *
 * @author admin
 */
@WebServlet(name = "ApproveOrderServlet", urlPatterns = {"/ApproveOrderServlet"})
public class ApproveOrderServlet extends HttpServlet {

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

        HttpSession session = request.getSession();
        accountsDTO account = (accountsDTO) session.getAttribute("account");
        if (account != null) {
            int orderID = Integer.parseInt(request.getParameter("OrderID"));
            String[] productIDs = request.getParameterValues("productIDs");
            String[] numberOfProducts = request.getParameterValues("numberOfProducts");
            String[] storeQuantity = request.getParameterValues("storeQuantity");
            String username = account.getUsername();
            dal.productsDAO dao = new productsDAO();
            String msg = "Phê Duyệt Không Thành Công \n"
                    + "Do Số Lượng Vượt Quá Số Sản Phẩm Trong Kho";
            //date
            LocalDate currentDate = LocalDate.now();
            Date approvedDate = Date.valueOf(currentDate);
            try {
                boolean updateQuantity;
                for (int i = 0; i < productIDs.length; i++) {
                    int id = Integer.parseInt(productIDs[i]);
                    int num = Integer.parseInt(numberOfProducts[i]);
                    int quantity = Integer.parseInt(storeQuantity[i]);
                    if (num <= quantity) {                     //travel and check if storeQuanity able to approve
                        updateQuantity = dao.reduceProductStoreQuantity(id, num);
                        boolean updateStatus = dao.updateOrderStatus(orderID, "Đã Phê Duyệt");
                        boolean updateApprovedDate = dao.updateOrderApprovedDate(orderID, approvedDate);
                        msg = "";
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                request.setAttribute("msg", msg);
                request.getRequestDispatcher("DispatchController?action=Chi+Tiết+Đơn+Hàng&orderID="+orderID).forward(request, response);
            }
        } else {
            request.getRequestDispatcher("login.jsp").forward(request, response);
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
        processRequest(request, response);
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
