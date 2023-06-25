package dao.impl;

import beans.User;
import dao.powerBankDao;
import beans.powerBank;
import dbutils.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class powerBankDaoimpl implements powerBankDao{
    @Override
    public powerBank findPowerBankbyID(String id) {
        //TODO Auto-generated method stub
        if (id == null){
            return null;
        }

        Connection conn = DBHelper.getConnection();
        String sql = "select * from p_source WHERE id = '" + id +"';";
        PreparedStatement stat = null;
        ResultSet rs = null;
        powerBank pb = new powerBank();
        try{
            stat = conn.prepareStatement(sql);
            rs = stat.executeQuery(); //executeQuery 该方法用来执行查询语句
            while (rs.next()){
                pb.setId(rs.getString("id"));
//                System.err.println(rs.getString("id"));
//                System.err.println(rs.getInt("available"));
//                System.err.println(rs.getInt("electricity"));
                pb.setelectricity(rs.getInt("electricity"));
                pb.setavailable(rs.getInt("available"));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBHelper.closeAll(conn,stat,rs);
        }
        if (pb.getId().equals(""))
            return null;
        else
            return pb;
    }

    @Override
    public void updatePowerBank(powerBank powerBank) {
        //TODO Auto-generated method stub
        Connection conn = DBHelper.getConnection();
        String sql = "UPDATE p_source SET id ='" + powerBank.getId() +
                "', electricity = '" + powerBank.getelectricity() +
                "', available =" + powerBank.getavailable() + " WHERE id ='" + powerBank.getId() + "';";
        PreparedStatement stat = null;
        ResultSet rs = null;
        try{
            stat = conn.prepareStatement(sql);
            stat.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBHelper.closeAll(conn,stat,rs);
        }
    }
}
