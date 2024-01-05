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
import java.util.ArrayList;
import models.productsDTO;

/**
 *
 * @author admin
 */
public class DispatchController extends HttpServlet {

    private final String HOME_PAGE = "home.jsp";
    private final String LOGIN_PAGE = "login.jsp";
    private final String FILTER_PAGE = "FilterServlet";
    private final String REGISTER_PAGE = "registration.jsp";
    private final String REGISTER_SERVLET = "RegisterServlet";
    private final String ACCOUNTLIST_PAGE = "ListAccServlet";
    private final String DELETE_PAGE = "DeleteServlet";
    private final String UPDATE_PAGE = "UpdateServlet";
//    private final String SEARCH_PAGE = "SearchServlet";
    private final String SEARCH_PAGE = "SearchResult.jsp";

    private final String LOGOUT_PAGE = "LogoutServlet";
    private final String CHANGEPASSWORD_PAGE = "changePassword.jsp";
    private final String CHANGEPASSWORD_SERVLET = "ChangePasswordServlet";
    private final String DETAIL_PAGE = "GetDetailServlet";
    private final String ADDTOCART_SERVLET = "AddToCartServlet";
    private final String ACCOUNTINFO_PAGE = "accountInfor.jsp";
    private final String CHANGEINFOR_SERVLET = "ChangeInforServlet";
    private final String CHANGEINFOR_PAGE = "changeInfor.jsp";
    private final String VIEWCART_SERVLET = "ViewCartServlet";
    private final String UPDATECART_SERVLET = "UpdateCartServlet";
    private final String ORDER_SERVLET = "OrderServlet";
    private final String VIEWORDER_SERVLER = "ViewUserOrderServlet";
    private final String VIEWORDERDETAIL_SERVLER = "ViewOrderDetailsServlet";
    private final String VIEWALLORDER_SERVLER = "ViewAllOrderServlet";
    private final String APPROVEORDER_SERVLET = "ApproveOrderServlet";
    private final String RETURNORDER_SERVLET = "ReturnOrderServlet";
    private final String REJECTORDER_SERVLET = "RejectOrderServlet";
    private final String CANCELORDER_SERVLET = "CancelOrderServlet";
    private final String PRODUCTLIST_PAGE = "listProducts.jsp";
    private final String GETPRODUCTDETAIL_SERVLET = "GetDetail_2Servlet";
    private final String CHANGEPRODUCTDETAILS_SERVLET = "ChangeProductDetailServlet";
    private final String DELETEPRODUCT_SERVLET = "DeleteProductServlet";
    private final String ADDNEWPRODUCT_PAGE = "addNewProduct.jsp";
    private final String ADDNEWPRODUCT_SERVLET = "AddNewProductServlet";
    private final String ABOUTUS_PAGE = "AboutUs.jsp";

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
        String action = request.getParameter("action");
        ArrayList<productsDTO> list = new ArrayList<>();
        dal.productsDAO dao = new productsDAO();
        String url = HOME_PAGE;
        try {
            list = dao.List();
            if (action.contains("Đăng Nhập")) {
                url = LOGIN_PAGE;
            } //            else if (action.equals("Lọc")) {
            //                url = FILTER_PAGE;
            //            } 
            else if (action.equals("Đăng Ký Ngay")) {
                url = REGISTER_PAGE;
            } else if (action.equals("Đăng Ký Tài Khoản")) {
                url = REGISTER_SERVLET;
            } else if (action.equals("Quản Lí Tài Khoản")) {
                url = ACCOUNTLIST_PAGE;
            } else if (action.equals("Quản Lí Đơn Hàng")) {
                url = VIEWALLORDER_SERVLER;
            } else if (action.equals("Quản Lí Sản Phẩm")) {
                url = PRODUCTLIST_PAGE;
            } else if (action.equals("Delete")) {
                url = DELETE_PAGE;
            } else if (action.equals("Update")) {
                url = UPDATE_PAGE;
            } else if (action.equals("Tất Cả Sản Phẩm")) {
                url = SEARCH_PAGE;
            } else if (action.equals("Đăng Xuất")) {
                url = LOGOUT_PAGE;
            } else if (action.equals("Quên Mật Khẩu")) {
                url = CHANGEPASSWORD_PAGE;
            } else if (action.equals("Thay Đổi Mật Khẩu")) {
                url = CHANGEPASSWORD_SERVLET;
            } else if (action.equals("Lưu Thay Đổi")) {
                url = CHANGEPASSWORD_SERVLET;
            } else if (action.equals("Thêm Sản Phẩm")) {
                url = ADDTOCART_SERVLET;
            } else if (action.equals("Tài Khoản")) {
                url = ACCOUNTINFO_PAGE;
            } else if (action.equals("Cập Nhật Thông Tin")) {
                url = CHANGEINFOR_PAGE;
            } else if (action.equals("Xác Nhận Thay Đổi")) {
                url = CHANGEINFOR_SERVLET;
            } else if (action.equals("Thêm Vào Giỏ Hàng")) {
                url = DETAIL_PAGE;
            } else if (action.equals("Giỏ Hàng")) {
                url = VIEWCART_SERVLET;
            } else if (action.equals("Mua Hàng")) {
                url = ORDER_SERVLET;
            } else if (action.equals("Đơn Hàng")) {
                url = VIEWORDER_SERVLER;
            } else if (action.equals("Chi Tiết Đơn Hàng")) {
                url = VIEWORDERDETAIL_SERVLER;
            } else if (action.equals("Sửa")) {
                url = UPDATECART_SERVLET;
            } else if (action.equals("Phê Duyệt Đơn Hàng")) {
                url = APPROVEORDER_SERVLET;
            } else if (action.equals("Trả Lại Hàng")) {
                url = RETURNORDER_SERVLET;
            } else if (action.equals("Từ Chối Đơn Hàng")) {
                url = REJECTORDER_SERVLET;
            } else if (action.equals("Hủy Đơn Hàng")) {
                url = CANCELORDER_SERVLET;
            } else if (action.equals("Chỉnh Sửa")) {
                url = GETPRODUCTDETAIL_SERVLET;
            } else if (action.equals("Xác Nhận")) {
                url = CHANGEPRODUCTDETAILS_SERVLET;
            } else if (action.equals("Xóa")) {
                url = DELETEPRODUCT_SERVLET;
            } else if (action.equals("Sản Phẩm Mới")) {
                url = ADDNEWPRODUCT_PAGE;
            } else if (action.equals("Thêm")) {
                url = ADDNEWPRODUCT_SERVLET;
            } else if (action.equals("Về Chúng Tôi")) {
                url = ABOUTUS_PAGE;
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            request.setAttribute("list", list);
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
