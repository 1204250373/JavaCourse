package service;

import beans.Admin;
import dao.impl.AdminDaoimpl;


public class AdminService {
    /*
    登录功能
     */
    public Admin login(String id, String password) {
        String success = "success";
        String failed = "failed";
        AdminDaoimpl udi = new AdminDaoimpl();
        Admin admin_login = udi.findAdminbyID(id);
        if (id != null && admin_login.getPassword().equals(password)){
            return admin_login;
        }
        else{
            return  null;
        }
    }
}
