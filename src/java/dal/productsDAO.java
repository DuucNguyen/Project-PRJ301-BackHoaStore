/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.*;

/**
 *
 * @author admin
 */
public class productsDAO implements Serializable {

    public ArrayList<productsDTO> List() throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<productsDTO> list = new ArrayList<>();
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT * "
                        + "FROM Products p JOIN Category c on p.categoryID = c.CategoryID ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    String category = rs.getString("CategoryName");
                    String description = rs.getString("description");
                    String imgLocation = rs.getString("imgLocation");
                    int storeQuantity = rs.getInt("storeQuantity");
                    list.add(new productsDTO(id, name, price, category, description, imgLocation, storeQuantity));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            rs.close();
            stm.close();
            con.close();
        }
        return list;
    }

    public boolean addNewProduct(int id, String name, double price, int categoryID, String description, String imgLocation, int storeQuantity) {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO Products(id, name, price, categoryID, description, imgLocation, storeQuantity) "
                        + "VALUES(?,?,?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, id);
                stm.setNString(2, name);
                stm.setDouble(3, price);
                stm.setInt(4, categoryID);
                stm.setNString(5, description);
                stm.setNString(6, imgLocation);
                stm.setInt(7, storeQuantity);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("addnewPErr" + e);
        } finally {
        }
        return false;
    }

    public ArrayList<productsDTO> getList(String findValue, String price, String order_raw) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<productsDTO> list = new ArrayList<>();
        try {
            //open connection
            con = DBHelper.makeConnection();
            int priceA = 0, priceB = 0;
            String order = "";
            switch (price) {
                case "1":
                    priceA = 0;
                    priceB = 9999;
                    break;
                case "2":
                    priceA = 10000;
                    priceB = 20000;
                    break;
                case "3":
                    priceA = 20000;
                    priceB = 50000;
                    break;
                case "4":
                    priceA = 50000;
                    priceB = 100000;
                    break;
                case "5":
                    priceB = 100000;
                    break;
                default:
                    priceA = 0;
                    priceB = 999999999;
                    break;
            }

            switch (order_raw) {
                case "ASC":
                    order = "ORDER BY price ASC";
                    break;
                case "DESC":
                    order = "ORDER BY price DESC";
                    break;
                case "BestSeller":
                    order = "ORDER BY storeQuantity ASC";
                    break;
                default:
                    order = "";
            }

            if (con != null) {
                if (price.equals("5")) {
                    //create sql query
                    String sql = "SELECT * "
                            + "FROM Products p join Category c on p.categoryID = c.CategoryID "
                            + "WHERE name LIKE ? AND p.price > ? "
                            + "OR description LIKE ? AND p.price > ? "
                            + order;
                    //setString
                    stm = con.prepareStatement(sql);
                    stm.setString(1, "%" + findValue + "%");
                    stm.setDouble(2, priceB);
                    stm.setString(3, "%" + findValue + "%");
                    stm.setDouble(4, priceB);
                } else {
                    //create sql query
                    String sql = "SELECT * "
                            + "FROM Products p join Category c on p.categoryID = c.CategoryID "
                            + "WHERE name LIKE ? AND p.price BETWEEN ? AND ? "
                            + "OR description LIKE ? AND p.price BETWEEN ? AND ? "
                            + order;
                    //setString
                    stm = con.prepareStatement(sql);
                    stm.setString(1, "%" + findValue + "%");
                    stm.setDouble(2, priceA);
                    stm.setDouble(3, priceB);
                    stm.setString(4, "%" + findValue + "%");
                    stm.setDouble(5, priceA);
                    stm.setDouble(6, priceB);
                }
                //execute
                rs = stm.executeQuery();
                //getList
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String cate = rs.getString("CategoryName");
                    int priceGet = rs.getInt("price");
                    String description = rs.getString("description");
                    String imgLocation = rs.getString("imgLocation");
                    int storeQuantity = rs.getInt("storeQuantity");
                    list.add(new productsDTO(id, name, priceGet, cate, description, imgLocation, storeQuantity));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println(list.size());
            rs.close();
            stm.close();
            con.close();
        }
        return list;
    }

    public ArrayList<productsDTO> searchAllProducts(String findValue) {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<productsDTO> list = new ArrayList<>();
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT * "
                        + "FROM Products p JOIN Category c on p.categoryID = c.CategoryID "
                        + "WHERE p.name LIKE ? OR p.description LIKE ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + findValue + "%");
                stm.setString(2, "%" + findValue + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String category = rs.getString("CategoryName");
                    int priceGet = rs.getInt("price");
                    String description = rs.getString("description");
                    String imgLocation = rs.getString("imgLocation");
                    int storeQuantity = rs.getInt("storeQuantity");
                    list.add(new productsDTO(id, name, priceGet, category, description, imgLocation, storeQuantity));
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
        }
        return list;
    }

    public double getPriceByID(int id) {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        double price = 0;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT price "
                        + "FROM Products "
                        + "WHERE id = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, id);
                rs = stm.executeQuery();
                while (rs.next()) {
                    price = rs.getDouble("price");
                }
            }
        } catch (Exception e) {
        } finally {
        }
        return price;
    }

    public productsDTO getProductDetail(int productID) {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT * "
                        + "FROM Products p JOIN Category c on p.categoryID = c.CategoryID "
                        + "WHERE p.id = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, productID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String category = rs.getString("CategoryName");
                    String description = rs.getString("description");
                    double price = rs.getDouble("price");
                    String imgLocation = rs.getString("imgLocation");
                    int storeQuantity = rs.getInt("storeQuantity");
                    productsDTO product = new productsDTO(id, name, price, category, description, imgLocation, storeQuantity);
                    return product;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public boolean addToCart(String username, int productID, int numberOfProduct) {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO Cart(username, productID, numberOfProducts) "
                        + "VALUES(?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setInt(2, productID);
                stm.setInt(3, numberOfProduct);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
        }
        return false;
    }

    public boolean checkCartExist(String username, int productID) {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT * "
                        + "FROM Cart "
                        + "WHERE username = ? "
                        + "AND productID = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setInt(2, productID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
        }
        return false;
    }

    public CartDTO findCartItem(String username, int productID) {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        CartDTO cartItem = new CartDTO();
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT * "
                        + "FROM Cart "
                        + "WHERE username = ? "
                        + "AND productID = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setInt(2, productID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int numberOfProducts = rs.getInt("numberOfProducts");
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String category = rs.getString("CategoryName");
                    String description = rs.getString("description");
                    double price = rs.getDouble("price");
                    String imgLocation = rs.getString("imgLocation");
                    int storeQuantity = rs.getInt("storeQuantity");

                    cartItem = new CartDTO(username, numberOfProducts, id, name, price, category, description, imgLocation);
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
        }
        return cartItem;
    }

    public boolean addCartQuantity(String username, int productID, int numberOfProduct) {//if cart exist +numberOfProducts
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE Cart "
                        + "SET numberOfProducts = numberOfProducts + ? "
                        + "WHERE username = ? "
                        + "AND productID = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, numberOfProduct);
                stm.setString(2, username);
                stm.setInt(3, productID);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
        } finally {
        }
        return false;
    }

    public boolean updateCartQuantity(String username, int productID, int numberOfProducts_new) { //update NumberOfProducts
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE Cart "
                        + "SET numberOfProducts = ? "
                        + "WHERE username = ? AND productID = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, numberOfProducts_new);
                stm.setString(2, username);
                stm.setInt(3, productID);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
        } finally {
        }
        return false;
    }

    public ArrayList<CartDTO> viewCart(String user) {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<CartDTO> listCart = new ArrayList<>();
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT * "
                        + "FROM Cart c "
                        + "JOIN Products p on c.productID = p.id "
                        + "JOIN Category cate on p.categoryID = cate.CategoryID "
                        + "WHERE username = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, user);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String username = rs.getString("username");
                    int numberOfProducts = rs.getInt("numberOfProducts");
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String category = rs.getString("CategoryName");
                    String description = rs.getString("description");
                    double price = rs.getDouble("price");
                    String imgLocation = rs.getString("imgLocation");
                    int storeQuantity = rs.getInt("storeQuantity");
                    listCart.add(new CartDTO(username, numberOfProducts, id, name, price, category, description, imgLocation, storeQuantity));
                }
            }
        } catch (Exception e) {
            System.out.println(e + "DTO");
        } finally {
        }
        return listCart;
    }

    public boolean placeOrder(String username, Date orderDate) {
        String status = "Chờ Phê Duyệt";

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int id = 0;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO [Order] (username, orderDate, status) "
                        + "VALUES(?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setDate(2, orderDate);
                stm.setNString(3, status);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("placeOrderErr" + e);
        } finally {
        }
        return false;
    }

    public boolean removeCart(String username, int productID) {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "DELETE FROM Cart "
                        + "WHERE username = ? AND productID = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setInt(2, productID);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
        } finally {
        }
        return false;
    }

    public ArrayList<OrdersDTO> getAllOrders() {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<OrdersDTO> list = new ArrayList<>();
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT * "
                        + "FROM [Order] ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int oid = rs.getInt("OrderID");
                    String username = rs.getString("username");
                    Date orderDate = rs.getDate("OrderDate");
                    Date approvedDate = rs.getDate("ApprovedDate");
                    String status = rs.getString("Status");
                    list.add(new OrdersDTO(oid, username, orderDate, approvedDate, status));
                }
            }
        } catch (Exception e) {
            System.out.println("GetOrdersErr" + e);
        } finally {
        }
        return list;
    }

    public ArrayList<OrdersDTO> getOrdersByUser(String user) {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<OrdersDTO> list = new ArrayList<>();
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT * "
                        + "FROM [Order] "
                        + "WHERE username = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, user);
                rs = stm.executeQuery();

                while (rs.next()) {
                    int oid = rs.getInt("OrderID");
                    String username = rs.getString("username");
                    Date orderDate = rs.getDate("OrderDate");
                    Date approvedDate = rs.getDate("ApprovedDate");
                    String status = rs.getString("Status");
                    list.add(new OrdersDTO(oid, username, orderDate, approvedDate, status));
                }
            }
        } catch (Exception e) {
            System.out.println("GetOrdersErr" + e);
        } finally {
        }
        return list;
    }

    public OrdersDTO getOrderByID(int ID) {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        OrdersDTO order = new OrdersDTO();
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT * "
                        + "FROM [Order] "
                        + "WHERE OrderID = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, ID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int orderID = rs.getInt("OrderID");
                    String username = rs.getString("username");
                    Date orderDate = rs.getDate("OrderDate");
                    Date approvedDate = rs.getDate("ApprovedDate");
                    String status = rs.getString("Status");
                    order = new OrdersDTO(orderID, username, orderDate, approvedDate, status);
                }

            }
        } catch (Exception e) {
        } finally {
        }
        return order;
    }

    public boolean addOrderDetails(int orderID, int productID, int quantity, double totalPrice) {

        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO OrderDetails(OrderID, ProductID, Quantity, TotalPrice)"
                        + "VALUES(?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, orderID);
                stm.setInt(2, productID);
                stm.setInt(3, quantity);
                stm.setDouble(4, totalPrice);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
        } finally {
        }
        return false;
    }

    public ArrayList<OrderDetailsDTO> getOrderDetails(int Id) {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<OrderDetailsDTO> list = new ArrayList<>();
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT * "
                        + "FROM [OrderDetails] o JOIN Products p on o.ProductID = p.id "
                        + "WHERE OrderID = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, Id);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int orderID = rs.getInt("OrderID");
                    int productID = rs.getInt("ProductID");
                    String productName = rs.getString("name");
                    String description = rs.getString("description");
                    int quantity = rs.getInt("quantity");
                    int storeQuantity = rs.getInt("storeQuantity");
                    double unitPrice = rs.getDouble("price");
                    double totalPrice = rs.getDouble("TotalPrice");
                    String imgLocation = rs.getString("imgLocation");

                    list.add(new OrderDetailsDTO(orderID, productID, productName, description, quantity, storeQuantity, unitPrice, totalPrice, imgLocation));
                }
            }
        } catch (Exception e) {
            System.out.println("GetOrderDetailsErr" + e);

        } finally {
        }
        return list;
    }

    public boolean updateOrderApprovedDate(int orderID, Date approvedDate) {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE [Order] "
                        + "SET ApprovedDate = ? "
                        + "WHERE OrderID = ? ";
                stm = con.prepareStatement(sql);
                stm.setDate(1, approvedDate);
                stm.setInt(2, orderID);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Update approve date err");
        } finally {
        }
        return false;
    }

    public boolean updateOrderStatus(int productID, String status) {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE [Order] "
                        + "SET [Status] = ? "
                        + "WHERE OrderID = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, status);
                stm.setInt(2, productID);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Update status err");
        } finally {
        }
        return false;
    }

    public boolean reduceProductStoreQuantity(int productID, int numberOfProducts) {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE Products "
                        + "SET storeQuantity = storeQuantity - ? "
                        + "WHERE id = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, numberOfProducts);
                stm.setInt(2, productID);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Update - storeQuantity err");
        } finally {
        }
        return false;
    }

    public boolean inceaseProductStoreQuantity(int productID, int numberOfProducts) {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE Products "
                        + "SET storeQuantity = storeQuantity + ? "
                        + "WHERE id = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, numberOfProducts);
                stm.setInt(2, productID);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Update + storeQuantity err");
        } finally {
        }
        return false;
    }

    public boolean updateProductDetails(int id, String name, double price, int categoryID, String description, String imgLocation, int storeQuantity) {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE Products "
                        + "SET [name] = ? , "
                        + "price = ? , "
                        + "categoryID = ? , "
                        + "[description] = ? , "
                        + "imgLocation = ? , "
                        + "storeQuantity = ? "
                        + "WHERE id = ? ";
                stm = con.prepareStatement(sql);
                stm.setNString(1, name);
                stm.setDouble(2, price);
                stm.setInt(3, categoryID);
                stm.setNString(4, description);
                stm.setNString(5, imgLocation);
                stm.setInt(6, storeQuantity);
                stm.setInt(7, id);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("UpdateDetails-err" + e);
        } finally {
        }
        return false;
    }

    public boolean deteteProductById(int id) {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "DELETE FROM Products "
                        + "WHERE id = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, id);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
        } finally {
        }
        return false;
    }

}
