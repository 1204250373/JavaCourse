package dao;

import beans.User;
import beans.powerBank;

public interface powerBankDao {
    public powerBank findPowerBankbyID(String id);
    public void updatePowerBank(powerBank powerBank);
}
