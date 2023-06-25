package service;

import beans.User;
import dao.impl.UserDaoImpl;
import dbutils.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.SimpleTimeZone;

import static com.sun.xml.internal.ws.policy.sourcemodel.wspolicy.XmlToken.Name;

public class UserService {
    /*
     * 注册功能
     */
    public User singln(String name, String password0) {
        // 创建账号对象
        User user = new User();
        // 在数据库中插入新空用户，自动生成ID序列号
        UserDaoImpl udi = new UserDaoImpl();
        udi.creatNewUser();   //1.创建新用户
        int serialnum = udi.getSerial();//2.获取序列号
        String id = String.format("%06d", serialnum);//结果将用零来填充6位
        System.out.println("serialnum: " + serialnum);
        System.out.println("id: " + id);
        user.setId(id);    // 3.设置新用户ID
        udi.addUser(id,serialnum); // 4.在数据库中添加账号的ID
        user.setName(name);
        user.setPassword(password0);
        udi.updateUser(user);// 6.更新用户到数据库
        return user;
    }

    /*
    登录功能
     */
    public User login(String id, String password) {
        String success = "success";
        String failed = "failed";
        UserDaoImpl udi = new UserDaoImpl();
        User user_login = udi.findUserbyID(id);
        if (id != null && user_login.getPassword().equals(password)){
            return user_login;
        }
        else{
            return  null;
        }
    }

    /*
    查询余额功能
     */
    public double search(User user_Login) {
        LogService.addLogs(user_Login, "用户查询余额");
        return user_Login.getBalance();
    }

    /*
    取款功能
     */
    public boolean draw(User user_Login, double monet_draw){
        if (user_Login.getBalance() >= monet_draw) {
            user_Login.setBalance(user_Login.getBalance() - monet_draw);
            UserDaoImpl udi = new UserDaoImpl();
            udi.updateUser(user_Login);
            LogService.addLogs(user_Login, "用户取款");
            return true;
        } else {
            return false;
        }
    }

    /*
    存款功能
     */
    public void deposit(User user_Login,double monet_draw) {
        user_Login.setBalance(user_Login.getBalance() + monet_draw);
        UserDaoImpl udi = new UserDaoImpl();
        udi.updateUser(user_Login);
        LogService.addLogs(user_Login, "用户存款");
    }

    /*
    登出功能
     */
    public void Logout(User user_Login) {
        Date time = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        String dateTimeNow = sdf.format(time);
        System.out.println(user_Login.getId() + "于" + dateTimeNow + "登出系统");
        LogService.addLogs(user_Login, "用户登出");
        user_Login = null;

    }

//    /*
//   转账功能
//    */
//    public boolean transfer(User user_Login1,String id_transfer,String name_transfer,double money_transfer1) {
////          System.out.println("您当前的账号为：" + user_Login1.getId());
//        //查询账号是否存在
//        UserDaoImpl userId = new UserDaoImpl();
//        User user_transfer = userId.findUserbyID(id_transfer);
//        if (user_transfer != null && user_transfer.getName().equals(name_transfer)) {
//            if (user_Login1.getBalance() >= money_transfer1) {
//                user_transfer.setBalance(user_transfer.getBalance() + money_transfer1);
//                userId.updateUser(user_transfer);
//                user_Login1.setBalance(user_Login1.getBalance() - money_transfer1);
//                userId.updateUser(user_Login1);
//                LogService.addLogs(user_Login1, "用户转账");
//                return true;
//            } else
//                return false;
//
//        } else {
//            return false;
//        }
//    }


    /*
    帮助功能
     */
    public void help(User user_Login) {
        LogService.addLogs(user_Login,"用户请求帮助");
    }

}
