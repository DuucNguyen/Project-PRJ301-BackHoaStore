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
import models.OrdersDTO;
import models.accountsDTO;

/**
 *
 * @author admin
 */
@WebServlet(name = "OrderServlet", urlPatterns = {"/OrderServlet"})
public class OrderServlet extends HttpServlet {

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
        dal.productsDAO dao = new productsDAO();
        accountsDTO account = (accountsDTO) session.getAttribute("account");
        String username = account.getUsername();
        String[] checkedProduct = request.getParameterValues("isChecked");
//        date
        LocalDate currentDate = LocalDate.now();
        Date orderDate = Date.valueOf(currentDate);

        ArrayList<OrdersDTO> listOrder = dao.getAllOrders();
        int OrderId = listOrder.size() + 1;
        String msg = "*Lỗi (Vui Lòng Thử Lại Sau)";
        try {
            boolean rs1 = dao.placeOrder(account.getUsername(), (java.sql.Date) orderDate);//add 1 order per time
            boolean rs2 = false;
            boolean rs3 = false;
            for (int i = 0; i < checkedProduct.length; i++) {
                String[] pid = checkedProduct[i].split("\\s+");
                int id = 0;
                int quantity = 0;
                for (int j = 0; j < pid.length; j++) {
                    if (j % 2 == 0) {
                        id = Integer.parseInt(pid[j]);
                    } else {
                        quantity = Integer.parseInt(pid[j]);
                    }
                }
                double totalPrice = dao.getPriceByID(id) * quantity; //calculate total of 1 bill
                rs2 = dao.addOrderDetails(OrderId, id, quantity, totalPrice); //add details
                rs3 = dao.removeCart(username, id); //remove items from cart
            }

            if (rs1 && rs2) {
                msg = "*Đặt Hàng Thành Công Vui Lòng Chờ Xác Nhận";
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("DispatchController?action=Giỏ+Hàng").forward(request, response);
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
