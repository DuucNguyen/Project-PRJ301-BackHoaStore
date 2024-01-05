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
import java.util.ArrayList;
import models.CartDTO;
import models.CreateProductError;
import models.accountsDTO;
import models.productsDTO;

/**
 *
 * @author admin
 */
@WebServlet(name = "AddNewProductServlet", urlPatterns = {"/AddNewProductServlet"})
public class AddNewProductServlet extends HttpServlet {

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
        dal.productsDAO dao = new productsDAO();
        productsDTO product = new productsDTO();

        CreateProductError error = new CreateProductError();
        String msg = "Sản Phẩm Đã Tồn Tại";
        int id = 0;
        boolean isErr = false;
        if (account != null) {
            try {
                id = Integer.parseInt(request.getParameter("id"));
                String name = request.getParameter("name");
                double price = Double.parseDouble(request.getParameter("price"));
//                String category = request.getParameter("category");
                int categoryID = Integer.parseInt(request.getParameter("categoryID"));
                String description = request.getParameter("description");
                String imgLocation = request.getParameter("imgLocation");
                int storeQuantity = Integer.parseInt(request.getParameter("storeQuantity"));

                if (name.trim().length() < 3 || name.trim().length() > 50) {
                    isErr = true;
                    error.setNameLengthErr("Tên Sản Phẩm (3-50) Kí Tự");
                }
                if (description.trim().length() < 5 || description.trim().length() > 50) {
                    isErr = true;
                    error.setDescriptionInvalidErr("Mô Tả (5-50) Kí Tự");
                }
                if (price < 0) {
                    isErr = true;
                    error.setPriceInvalidErr("Giá Không Hợp Lệ (Lớn hơn 0)");
                }
                if (storeQuantity < 0) {
                    isErr = true;
                    error.setStoreQuantiyInvalidErr("Số Lượng Không Hợp Lệ");
                }
                if (!imgLocation.matches("^image\\/\\w{1,100}\\.\\w{3,6}$")) {
                    isErr = true;
                    error.setImgLocationinvalidErr("Định Dạng Đường Dẫn Không Hợp Lệ");
                }

                if (isErr) {
                    request.setAttribute("error", error);
                } else {
                    boolean rs = dao.addNewProduct(id, name, price, categoryID, description, imgLocation, storeQuantity);
                    if (rs) {
                        product = dao.getProductDetail(id);
                        msg = "Đã Thêm Thành Công";
                        request.setAttribute("product", product);
                    }
                }

            } catch (Exception e) {
                System.out.println("addNewProductErr:" + e);
            } finally {
                request.setAttribute("msg", msg);
                request.getRequestDispatcher("DispatchController?action=Sản+Phẩm+Mới&id=" + id).forward(request, response);
            }
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
