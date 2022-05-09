package com.example.project07.tracking;

public class IncomeExpenseDetail {

    private int ieID;
    private String money;
    private String date;

    public IncomeExpenseDetail() {

    }

    public IncomeExpenseDetail(int ieID, String money, String date) {
        this.ieID = ieID;
        this.money = money;
        this.date = date;
    }

    public int getIeID() {
        return ieID;
    }

    public void setIeID(int ieID) {
        this.ieID = ieID;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
