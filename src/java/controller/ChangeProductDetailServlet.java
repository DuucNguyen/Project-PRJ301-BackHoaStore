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
import models.CreateProductError;
import models.accountsDTO;
import models.productsDTO;

/**
 *
 * @author admin
 */
@WebServlet(name = "ChangeProductDetailServlet", urlPatterns = {"/ChangeProductDetailServlet"})
public class ChangeProductDetailServlet extends HttpServlet {

    private final String DRINK = "1";
    private final String FOOD = "2";
    private final String SEASON = "3";
    private final String HOUSEHOLD_PRODUCTs = "4";
    private final String OTHERS = "5";

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
        int id = Integer.parseInt(request.getParameter("id"));
        if (account != null) {
            try {

                String name = request.getParameter("name");
                double price = Double.parseDouble(request.getParameter("price"));
                String category = request.getParameter("category");
                int categoryID = Integer.parseInt(request.getParameter("categoryID"));
                String description = request.getParameter("description");
                String imgLocation = request.getParameter("imgLocation");
                int storeQuantity = Integer.parseInt(request.getParameter("storeQuantity"));

                dal.productsDAO dao = new productsDAO();
                productsDTO product = dao.getProductDetail(id);
                CreateProductError error = new CreateProductError();
                boolean isErr = false;

                if (name.trim().length() < 3 || name.trim().length() > 50) {
                    isErr = true;
                    error.setNameLengthErr("Tên Sản Phẩm (3-50) Kí Tự");
                }
//                if (categoryID != 1
//                        && categoryID != 2
//                        && categoryID != 3
//                        && categoryID != 4
//                        && categoryID != 5) {
//                    isErr = true;
//                    error.setCategoryInvalidErr("Thể Loại Không Hợp Lệ");
//                }
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
                if(!imgLocation.matches("^image\\/\\w{1,100}\\.\\w{3,6}$")){
                    isErr = true;
                    error.setImgLocationinvalidErr("Định Dạng Đường Dẫn Không Hợp Lệ");
                }
                if (isErr) {
                    request.setAttribute("error", error);
                } else {
                    boolean rs = dao.updateProductDetails(id, name, price, categoryID, description, imgLocation, storeQuantity);
                    if (rs) {
                        String msg = "Thay Đổi Thành Công";
                        request.setAttribute("msg", msg);
                    }
                }
            } catch (Exception e) {
                System.out.println("UPDATEPRODUCTDETAILS-" + e);
            } finally {
                request.getRequestDispatcher("DispatchController?action=Chỉnh+Sửa&productID=" + id).forward(request, response);
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
