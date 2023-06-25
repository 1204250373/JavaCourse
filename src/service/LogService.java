package service;

import beans.Logs;
import beans.User;
import dao.impl.LogDaoimpl;
import utils.DateUtil;

import java.util.Iterator;
import java.util.List;

public class LogService {
    /*
    插入日志
     */
    public static void addLogs(User user, String logContext) {
        LogDaoimpl logDaoimpl = new LogDaoimpl();
        Logs log = new Logs();
        log.setUserId(user.getId());
        log.setTimes(DateUtil.getDateTimeNow());
        log.setLogContext(logContext);
        logDaoimpl.addLog(log);
    }

    /*
    查询日志
     */
    public static  List<Logs> queryLogs(User user) {
        LogDaoimpl logDaoimpl =new LogDaoimpl();
        List<Logs> logList = logDaoimpl.queryLog(user.getId());
        return logList;
//        System.out.println("该账号的日志记录如下：");
//        System.out.println("账号"+"\t"+"时间"+"\t"+"操作");

    }
}
