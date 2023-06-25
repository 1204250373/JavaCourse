package service;

import beans.User;
import beans.powerBank;
import dao.impl.UserDaoImpl;
import dao.impl.powerBankDaoimpl;

public class powerBankService {
    /*
    充电宝按钮与数据库id匹配
     */
    public powerBank match(String id) {
        String success = "success";
        String failed = "failed";
        powerBankDaoimpl pbi = new powerBankDaoimpl();
        powerBank powerBank_match = pbi.findPowerBankbyID(id);
        if (id != null){
            return powerBank_match;
        }
        else{
            return  null;
        }
    }

    /*
    借出
     */
    public void lend(powerBank powerBank_match) {
        powerBank_match.setavailable(0);
        powerBankDaoimpl pbi = new powerBankDaoimpl();
        pbi.updatePowerBank(powerBank_match);
    }

    /*
    归还
     */
    public void restore(powerBank powerBank_match) {
        powerBank_match.setavailable(1);
        powerBankDaoimpl pbi = new powerBankDaoimpl();
        pbi.updatePowerBank(powerBank_match);
    }
}
