package dao;

import beans.User;

public interface UserDao {
    public User findUserbyID(String id);
    public void creatNewUser(); //插入空账号，用于注册
    public void addUser(String id,int serialNum);
    public void updateUser(User user);
    public int getSerial(); //获取空账号的ID序列，用于注册，定位倥账号
}
