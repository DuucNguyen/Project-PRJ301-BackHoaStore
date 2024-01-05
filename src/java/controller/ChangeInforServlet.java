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
import models.accountsDTO;

/**
 *
 * @author admin
 */
@WebServlet(name = "ChangeInforServlet", urlPatterns = {"/ChangeInforServlet"})
public class ChangeInforServlet extends HttpServlet {

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
        String password = account.getPassword();
        String username = request.getParameter("username");
        String in_password = request.getParameter("password");
        String new_email = request.getParameter("new_email");
        String new_fullName = request.getParameter("new_fullName");
        dal.accountsDAO dao = new accountsDAO();
        models.RegistrationError error = new RegistrationError();
        boolean isErr = false;
        String msg = "";
        try {
            if (!new_email.matches("^[\\w\\d]{5,28}\\@gmail\\.[\\w]{3}$")) {
                isErr = true;
                error.setEmailInvalid("*Định Dạng Email Không Chính Xác");
            }
            if (new_fullName.trim().length() < 6 || new_fullName.trim().length() > 30) {
                isErr = true;
                error.setFullNameLengthErr("*Tên Đầy Đủ Yêu Cầu 6-30 Kí Tự");
            }
            if (in_password.trim().length() < 6 || in_password.trim().length() > 30) {
                isErr = true;
                error.setPasswordLengthErr("*Mật Khẩu Yêu Cầu 6-30 Ký Tự");
            } else if (!in_password.equals(password)) {
                isErr = true;
                error.setConfirmNotMatchErr("*Mật Khẩu Không Chính Xác");
            }
            if (isErr) {
                request.setAttribute("error", error);
            } else {
                boolean rs = dao.changeAccountInfor(username, password, new_email, new_fullName);
                msg = "*Thông Tin Mới Đã Được Cập Nhật Thành Công !";
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("changeInfor.jsp").forward(request, response);
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
