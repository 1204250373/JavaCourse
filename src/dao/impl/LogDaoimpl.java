package dao.impl;

import beans.Logs;
import dao.LogDao;
import dbutils.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LogDaoimpl implements LogDao {
    public List<Logs> queryLog(String userId) {
        if (userId == null) {
            return null;
        }
        Connection conn = DBHelper.getConnection();
        String sql = "select * from Logs WHERE userId='" +userId +"';";
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Logs> logsList = new ArrayList<>();
//        Logs log = new Logs();
        try {
            stat = conn.prepareStatement(sql);
            rs = stat.executeQuery(); //executeQuery 该方法用来执行查询语句
            while (rs.next()) {
                Logs log = new Logs();
                log.setUserId(rs.getString("userId"));
                log.setTimes(rs.getString("times"));
                log.setLogContext(rs.getString("LogContext"));
                logsList.add(log);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBHelper.closeAll(conn, (com.mysql.jdbc.PreparedStatement) stat, rs);
        }
        if (logsList.isEmpty()) {
            return null;
        } else {
            return logsList;
        }
    }
    public void addLog(Logs log) {
        Connection conn = DBHelper.getConnection();
        String sql = "INSERT Logs (userId,times,logContext) VALUE(?,?,?);";
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1, log.getUserId());
            stat.setString(2, log.getTimes());
            stat.setString(3, log.getLogContext());
            stat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBHelper.closeAll(conn, (com.mysql.jdbc.PreparedStatement) stat, rs);
        }
    }
}
