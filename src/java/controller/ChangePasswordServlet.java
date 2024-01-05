/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.accountsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.RegistrationError;

/**
 *
 * @author admin
 */
@WebServlet(name = "ChangePasswordServlet", urlPatterns = {"/ChangePasswordServlet"})
public class ChangePasswordServlet extends HttpServlet {

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
        
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String newPass = request.getParameter("newPass");
        String reNewPass = request.getParameter("reNewPass");
        RegistrationError error = new RegistrationError();
        dal.accountsDAO dao = new accountsDAO();
        boolean isError = false;
        String msg = "";
        try {
            if (!dao.checkAccount(username)) {
                isError = true;
                error.setUsernameExistedErr("Tài Khoản " + "'" + username + "'" + " Không Tồn Tại");
            }
            if (newPass.length() < 6 || newPass.length() > 30) {
                isError = true;
                error.setPasswordLengthErr("*Mật Khẩu Mới Yêu Cầu 6-30 Kí Tự");
            } else if (!newPass.equals(reNewPass)) {
                isError = true;
                error.setConfirmNotMatchErr("*Xác Nhận Mật Khẩu Mới Không Khớp");
            }
            if (!email.matches("^[\\w\\d]{5,28}\\@gmail\\.[\\w]{3}$")) {
                isError = true;
                error.setEmailInvalid("*Định Dạng Email Không Chính Xác");
            }
            if (isError) {
                request.setAttribute("changeError", error);
            } else {
                dao.changePassword(username, email, newPass);
                msg = "*Thay Đổi Thành Công Vui Lòng Đăng Nhập Lại";
            }
        } catch (Exception e) {
        } finally {
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("changePassword.jsp").forward(request, response);
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
