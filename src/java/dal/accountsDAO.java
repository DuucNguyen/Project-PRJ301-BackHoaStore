/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.DBHelper;
import models.accountsDTO;

/**
 *
 * @author admin
 */
public class accountsDAO implements Serializable {

    public accountsDTO checkLogin(String username, String password) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT username, password, fullName,email, role "
                        + "FROM accounts "
                        + "WHERE username = ? "
                        + "AND password= ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);

                rs = stm.executeQuery();
                if (rs.next()) {
                    String user = rs.getString("username");
                    String pass = rs.getString("password");
                    String fullName = rs.getString("fullName");
                    String email = rs.getString("email");
                    boolean role = rs.getBoolean("role");
                    accountsDTO account = new accountsDTO(username, password, fullName, email, role);
                    return account;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            rs.close();
            stm.close();
            con.close();
        }
        return null;
    }

    public boolean checkAccount(String username) {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT username "
                        + "FROM accounts "
                        + "WHERE username = ? ";
//                        + "AND password = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
//                stm.setString(2, password);

                rs = stm.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }
        } catch (Exception e) {
        } finally {
        }
        return false;
    }

    public boolean registration(String username, String password, String fullname, String email) {
        boolean role = false; //default role = false
        Connection con = null;
        PreparedStatement stm = null;
//        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO accounts(username,password,fullname,email,role) "
                        + "VALUES(?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                stm.setString(3, fullname);
                stm.setString(4, email);
                stm.setBoolean(5, role);
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

    public ArrayList<accountsDTO> getListAccount() {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<accountsDTO> list = new ArrayList<>();
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT * "
                        + "FROM accounts "
                        + "ORDER BY role DESC";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullName = rs.getString("fullName");
                    String email = rs.getString("email");
                    boolean role = rs.getBoolean("role");
                    list.add(new accountsDTO(username, password, fullName, email, role));
                }
            }
        } catch (Exception e) {
        } finally {
        }
        return list;
    }

    public boolean deleteAccount(String username) {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "DELETE "
                        + "FROM accounts "
                        + "WHERE username = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
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

    public boolean updateAccount(String username, boolean role) {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE accounts "
                        + "SET role = ? "
                        + "WHERE username = ?";
                stm = con.prepareStatement(sql);
                stm.setBoolean(1, role);
                stm.setString(2, username);
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

    public boolean changePassword(String username, String email, String newPass) {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE accounts "
                        + "SET password = ? "
                        + "WHERE username = ? AND email = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, newPass);
                stm.setString(2, username);
                stm.setString(3, email);
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

    public boolean changeAccountInfor(String username, String password, String email, String fullname) {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE accounts "
                        + "SET email = ? , "
                        + "fullName = ? "
                        + "WHERE username = ? AND password = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, fullname);
                stm.setString(3, username);
                stm.setString(4, password);
                int row = stm.executeUpdate();
                if(row>0){
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
        }
        return false;
    }
}
