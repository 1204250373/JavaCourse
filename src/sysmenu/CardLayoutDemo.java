package sysmenu;

import beans.Admin;
import beans.powerBank;
import dao.impl.UserDaoImpl;
import service.AdminService;
import service.UserService;
import beans.User;
import service.powerBankService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//主界面
public class CardLayoutDemo extends JFrame {
    Admin admin1 = new Admin();
    User user1 = new User();
    powerBank powerSource = new powerBank();
    powerBank updatePowerSource = new powerBank();
    private CardLayout cardLayout;
    private Font font;
    private JPanel mainPanel, loginPanel, adminLogPanel, registerPanel, cardPanel,tradeMenuPanel, functionPanel, powerBankPanel, AdminPowerBankPanel, powerBankShelvesPanel, powerBankTakeDownPanel,
    AdminFunctionPanel;
    private JButton log1, log2, register, adminLog;
    private JLabel l;
    private JTextField text0, passText0, passText1;
    AdminService as = new AdminService();
    UserService us = new UserService();
    powerBankService pbs = new powerBankService();

    /*
    主界面
     */


    public CardLayoutDemo() {
        super("移动电源租赁系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        font = new Font("宋体", Font.PLAIN, 20);
        setFont(font);
        setSize(500, 250);
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        //面板放置系统标题
        mainPanel = new JPanel(new BorderLayout());
        l = new JLabel("移动电源租赁系统");
        Font font1 = new Font("宋体", Font.PLAIN, 50);
        l.setFont(font1);
        mainPanel.add(l, BorderLayout.NORTH);
        add(mainPanel);

        //按钮设置
        JPanel MainPanel = new JPanel(new GridLayout(1, 3));
        adminLog = new JButton("管理人员登录");
        log1 = new JButton("用户登录");
        register = new JButton("注册");
        log2 = new JButton("退出");

        //对四个按钮事件监听
        log1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "login");
            }
        });

        register.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "regist");
            }

        });

        log2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        adminLog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel,"adminLog");
            }
        });

        //向面板添加按钮
        MainPanel.add(register);
        MainPanel.add(log1);
        MainPanel.add(adminLog);
        MainPanel.add(log2);
        mainPanel.add(MainPanel, BorderLayout.SOUTH);
        cardPanel.add(mainPanel, "main");


        /*
        用户登录面板
        */

        loginPanel = new JPanel(new GridLayout(3, 2));
        //设置字体
        Font font0 = new Font("宋体", Font.PLAIN, 20);
        loginPanel.setFont(font0);
        //设置标签按钮文本框等组件
        JLabel l1 = new JLabel("用户名");
        JTextField text = new JTextField(15);
        JButton log = new JButton("确定登录");
        JButton back = new JButton("返回");
        JLabel l2 = new JLabel("密码");
        TextField passWord = new TextField(15);
        loginPanel.add(l1);
        loginPanel.add(text);
        loginPanel.add(l2);
        loginPanel.add(passWord);
        passWord.setEchoChar('*');
        loginPanel.add(log);
        loginPanel.add(back);

        //为登录按钮添加监听事件
        log.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username1 = text.getText();
                String password1 = passWord.getText();
                user1 = us.login(username1,password1);
                if(user1 == null){
                    JOptionPane.showMessageDialog(null, "登录失败！账号或者密码错误");
                }else{
                    cardLayout.show(cardPanel, "function");
                }
            }
        });

        //为返回按钮添加组件
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "main");
            }
        });
        cardPanel.add(loginPanel, "login");

        /*
        管理人员登录页面
         */

        adminLogPanel = new JPanel(new GridLayout(3, 2));
        //设置字体
        loginPanel.setFont(font0);
        //设置标签按钮文本框等组件
        JLabel adminL1 = new JLabel("用户名");
        JTextField adminText = new JTextField(15);
        JButton adminLog = new JButton("确定登录");
        JButton adminBack = new JButton("返回");
        JLabel adminL2 = new JLabel("密码");
        TextField adminPassWord = new TextField(15);

        adminLogPanel.add(adminL1);
        adminLogPanel.add(adminText);
        adminLogPanel.add(adminL2);
        adminLogPanel.add(adminPassWord);
        passWord.setEchoChar('*');
        adminLogPanel.add(adminLog);

        //对四个按钮事件监听
        adminLog.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String adminName1 = adminText.getText();
                String password1 = adminPassWord.getText();
                admin1 = as.login(adminName1,password1);
                if(admin1 == null){
                    JOptionPane.showMessageDialog(null, "登录失败！账号或者密码错误");
                }else{
                    cardLayout.show(cardPanel, "function");
                }
            }
        });

        adminBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "main");
            }

        });

        cardPanel.add(adminLogPanel, "adminLog");

        /*
        充电宝上架
         */
//        powerBankShelvesPanel = new JPanel(new GridLayout(3, 2));
//        //设置字体
//        powerBankPanel.setFont(font0);
//        //设置标签按钮文本框等组件
//        // 使用for循环添加多个按钮到卡片布局中
//        for (int i = 1; i <= 9; i++) {
//            JButton button = new JButton("上架充电宝 " + i);
//            powerBankShelvesPanel.add(button, "Card " + i);
//            button.addActionListener(new ActionListener() {
//                public void actionPerformed(ActionEvent e) {
//
//                }
//            });
//        }
//        JButton powerBankShelvesBack = new JButton("返回");
//        powerBankPanel.add(powerBankShelvesBack);
//        powerBankShelvesBack.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                cardLayout.show(mainPanel, "main");
//            }
//        });
//
//        cardPanel.add(powerBankPanel, "powerBank");

        /*
        管理员充电宝功能选择
         */
//        AdminFunctionPanel

        /*
        充电宝下架
         */



        /*
        功能选择界面
         */
        functionPanel = new JPanel(new GridLayout(3, 2));
        //设置字体
        functionPanel.setFont(font0);
        //设置标签按钮
        JButton function1 = new JButton("租赁充电宝");
        JButton function2 = new JButton("钱包");
        JButton functionBack = new JButton("返回");

        functionPanel.add(function1);
        functionPanel.add(function2);
        functionPanel.add(functionBack);

        //对两个个按钮事件监听
        function1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "lendPowerBank");
            }
        });

        function2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "SystemMenu");
            }
        });

        functionBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "main");
            }

        });

        cardPanel.add(functionPanel, "function");


        /*
        充电宝管理界面
         */
//        AdminPowerBankPanel = new JPanel(new GridLayout(3, 2));
//        //设置字体
//        AdminPowerBankPanel.setFont(font0);
//        //设置标签按钮
//        JButton shelves = new JButton("充电宝上架");
//        JButton takeDown = new JButton("充电宝下架");
//        JButton AdminPowerBankBack = new JButton("返回");
//
//        AdminPowerBankPanel.add(shelves);
//        AdminPowerBankPanel.add(takeDown);
//        AdminPowerBankPanel.add(AdminPowerBankBack);
//
//        cardPanel.add(AdminPowerBankPanel, "AdminPowerBank");

        /*
        充电宝租赁界面
         */
        powerBankService powerBankService = new powerBankService();
        UserService userService = new UserService();
        powerBankPanel = new JPanel(new GridLayout(3, 2));
        //设置字体
        powerBankPanel.setFont(font0);


        //设置标签按钮文本框等组件

        // 使用for循环添加多个按钮到卡片布局中
        for (int i = 1; i <= 9; i++) {
            boolean isBorrowed = false; // 添加一个标志位用于记录按钮是否已经借出
            JButton button = new JButton("充电宝" + i);
            powerBankPanel.add(button, "Card " + i);

            button.addActionListener(new ActionListener() {

                private boolean isBorrowed = false; // 添加一个标志位用于记录按钮是否已经借出

                public void actionPerformed(ActionEvent e) {
//                    int index = powerBankPanel.getComponentZOrder(button);
                    String buttonText = button.getText();
                    String  powerBankId = String.valueOf(buttonText.charAt(3));
                    powerSource = pbs.match(powerBankId);
                    if (isBorrowed){
                        //归还充电宝
                        String updatedButtonText = button.getText();
                        String updatedPowerBankId = String.valueOf(updatedButtonText.charAt(3));
                        button.setText("充电宝"+updatedPowerBankId);
                        isBorrowed = false;
                        updatePowerSource = pbs.match(updatedPowerBankId);
                        JOptionPane.showMessageDialog(null, "成功归还充电宝");

                        // 在这里使用updatedPowerBankId进行后续操作
                        powerBankService.restore(powerSource);
                    } else {
                        if (powerSource.getavailable() == 1) {
                            if (powerSource.getelectricity() >= 50) {
                                if (userService.search(user1) >= 99){
                                    powerBankService.lend(powerSource);
                                    JOptionPane.showMessageDialog(null, "成功借出充电宝");
                                    // 更新按钮文本
                                    button.setText(buttonText + "（已借出）");
                                    // 更新按钮状态为true
                                    isBorrowed = true;

                                } else {
                                    JOptionPane.showMessageDialog(null, "当前余额不足99元，请点击返回按钮，前往钱包功能充值！");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "该充电宝电量不足");
                            }
                    }else {
                            JOptionPane.showMessageDialog(null, "该充电宝已被借出");
                        }
                    }
                }
            });
        }

        JButton powerBankBack = new JButton("返回");
        powerBankPanel.add(powerBankBack);
        powerBankBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "function");
            }
        });


        cardPanel.add(powerBankPanel, "lendPowerBank");

         /*
         注册页面
         */

        //定义面板并使用网格布局
        registerPanel = new JPanel(new GridLayout(4, 2));
        registerPanel.setFont(font0);
        //设置标签等一系列组件
        JLabel l3 = new JLabel("用户名");
        text0 = new JTextField(15);
        JLabel l4 = new JLabel("密码");
        passText0 = new JTextField(15);
        JLabel l5 = new JLabel("确定密码");
        passText1 = new JTextField(15);
        JButton log1 = new JButton("确定注册");
        JButton back1 = new JButton("返回");

        registerPanel.add(l3);
        registerPanel.add(text0);
        registerPanel.add(l4);
        registerPanel.add(passText0);
        registerPanel.add(l5);
        registerPanel.add(passText1);
        registerPanel.add(log1);
        registerPanel.add(back1);
        add(registerPanel);

        //为注册用户名添加事件监听
        log1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "regist");
                String username0 = text0.getText();
                String password0 = passText0.getText();
                String password1 = passText1.getText();
                if (password0.equals(password1)) {
                    User newUser = us.singln(username0, password0);

                    // 注册完成后弹出窗口
                    JFrame success = new JFrame("注册");
                    JPanel p4 = new JPanel();
                    JLabel JL = new JLabel("用户：" + username0 + " ID:" + newUser.getId() + " 密码：" + password0 + "注册完成！" );
                    p4.add(JL);
                    success.add(p4);
                    success.setSize(100, 100);
                    success.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "密码不匹配");
                }

            }
        });
        //返回按钮事件
        back1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "main");
            }

        });
        cardPanel.add(registerPanel, "regist");
        cardLayout.show(cardPanel, "main");
        add(cardPanel);
        setVisible(true);

        /*
        钱包功能
        */
        UserDaoImpl userdaoimpl = new UserDaoImpl();
//        UserService userService = new UserService();
        tradeMenuPanel = new JPanel();
        JPanel Trade = new JPanel();
        JButton search = new JButton("查询余额");
        JButton draw = new JButton("取款");
        JButton deposit = new JButton("存款");
        JButton help = new JButton("帮助" );
        JButton tradeBack = new JButton("返回");
        Trade.add(search);
        Trade.add(draw);
        Trade.add(deposit);
        Trade.add(help);
        Trade.add(tradeBack);

        //在这里的一个面板再次定义了一个卡片布局来显示各个按钮功能
        JPanel card = new JPanel(new CardLayout());

        //第一个小窗（实现查询余额
        JPanel card0 = new JPanel();
        card.add(card0,"main");
        JPanel card1 = new JPanel();
        JLabel JLabel = new JLabel("您的余额为：" + userService.search(user1));
        card1.add(JLabel);
        card1.setSize(200,200);
        card.add(card1,"chaxun");
        CardLayout cardLayout1 = (CardLayout) card.getLayout();

        //第二个小窗（实现取款）
        JPanel card2 = new JPanel(new GridLayout(2,1));
        JTextField text0 = new JTextField(15);
        JButton draw1 = new JButton("确定取款");
        card2.add(text0);
        card2.add(draw1);
        card2.setSize(200,200);
        card.add(card2,"qukuan");
        cardLayout1.show(card, "main");
        tradeMenuPanel.add(Trade,BorderLayout.NORTH);
        tradeMenuPanel.add(card,BorderLayout.SOUTH);
        card.setVisible(true);
        cardPanel.add(tradeMenuPanel, "SystemMenu");

        //第三个小窗（实现存款）
        JPanel card3 = new JPanel(new GridLayout(2,1));
        JTextField text1 = new JTextField(15);
        JButton deposit1 = new JButton("确定存款");
        card3.add(text1);
        card3.add(deposit1);
        card3.setSize(200,200);
        card.add(card3,"cunkuan");

        //第五个小窗
        JPanel card6 = new JPanel();
        JTextArea helpTextArea = new JTextArea("******欢迎来到自助在线业务系统******\n" +
                "1.关于注册\n"+"2.关于登录\n"+"如有问题请致电139XXXXXXXX\n" +
                "***********************************");
        card6.add(helpTextArea);
        card.add(card6,"help");

        //添加小窗按钮监听器
        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userService.search(user1);
                JLabel.setText("您的余额为:"+user1.getBalance());
                cardLayout1.show(card, "chaxun");
            }
        });
        draw.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout1.show(card, "qukuan");
            }
        });
        draw1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean b = false;
                String monet_draw_str = text0.getText();
                double monet_draw = Double.parseDouble(monet_draw_str);
                if (monet_draw > userService.search(user1)) {
                    b = false;
                    JOptionPane.showMessageDialog(null, "取款失败！余额不足");
                } else {
                    b = true;
                }
                userService.draw(user1,monet_draw);
                if (b==true){
                    JOptionPane.showMessageDialog(null, "取款成功");
                }
            }
        });
        deposit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout1.show(card, "cunkuan");
            }
        });
        deposit1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String monet_draw_str = text1.getText();
                double monet_draw = Double.parseDouble(monet_draw_str);
                userService.deposit(user1,monet_draw);
                JOptionPane.showMessageDialog(null, "存款成功");

            }
        });

        help.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout1.show(card, "help");
            }
        });
        tradeBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "function");
            }
        });

        cardLayout.show(cardPanel, "main");
        add(cardPanel);
        setVisible(true);
    }
}
