package dao.impl;

import beans.Admin;
import beans.User;
import dao.AdminDao;
import dbutils.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDaoimpl implements AdminDao {
    /*
    @param id
    @return 返回对应id账号的User对象，如果不存在返回null
     */
    @Override
    public Admin findAdminbyID(String id) {
        //TODO Auto-generated method stub
        if (id == null){
            return null;
        }
        Connection conn = DBHelper.getConnection();
        String sql = "select * from Admin WHERE id='" + id +"';";
        PreparedStatement stat = null;
        ResultSet rs = null;
        Admin a = new Admin();
        try{
            stat = conn.prepareStatement(sql);
            rs = stat.executeQuery(); //executeQuery 该方法用来执行查询语句
            while (rs.next()){
                a.setId(rs.getString("id"));
                a.setName(rs.getString("name"));
                a.setPassword(rs.getString("password"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBHelper.closeAll(conn,stat,rs);
        }
        if (a.getId().equals(""))
            return null;
        else
            return a;
    }
}
