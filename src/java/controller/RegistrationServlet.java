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
import models.RegistrationError;

/**
 *
 * @author admin
 */
@WebServlet(name = "RegistrationServlet", urlPatterns = {"/RegistrationServlet"})
public class RegistrationServlet extends HttpServlet {

    private final String REGISTRATION_PAGE = "registration.jsp";
    private final String LOGIN_PAGE = "login.jsp";

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
        String password = request.getParameter("password");
        String confirmPass = request.getParameter("confirmPass");
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String url = REGISTRATION_PAGE;
        RegistrationError error = new RegistrationError();
        boolean isError = false;
        try {
            if (username.trim().length() < 5 || username.trim().length() > 20) {
                isError = true;
                error.setUsernameLengthErr("*Tên Đăng Nhập Yêu Cầu 5-20 Kí Tự");
            }
            if (password.trim().length() < 6 || password.trim().length() > 30) {
                isError = true;
                error.setPasswordLengthErr("*Mật Khẩu Yêu Cầu 6-30 Ký Tự");
            } else if (!password.trim().equals(confirmPass.trim())) {
                isError = true;
                error.setConfirmNotMatchErr("*Xác Nhận Mật Khẩu Không Chính Xác");
            }
            if (fullName.trim().length() < 6 || fullName.trim().length() > 30) {
                isError = true;
                error.setFullNameLengthErr("*Tên Đầy Đủ Yêu Cầu 6-30 Kí Tự");
            }
            if (!email.matches("^[\\w\\d]{5,28}\\@gmail\\.[\\w]{3}$")) {
                isError = true;
                error.setEmailInvalid("*Định Dạng Email Không Chính Xác");
            }
            //check usernameExisted 
            accountsDAO dao = new accountsDAO();
            boolean isExisted = dao.checkAccount(username);
            if (isExisted) {
                isError = true;
                error.setUsernameExistedErr("*Tài Khoản " + "'" + username + "'" + " Đã Tồn Tại ");
            }
            if (isError) {
                request.setAttribute("InsertErr", error);
            } else {
                dao.registration(username, password, fullName, email);
                String msg = "*Đăng Ký Tài Khoản Thành Công \n"
                        + "Click Để Đăng Nhập";
                request.setAttribute("msg", msg);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
