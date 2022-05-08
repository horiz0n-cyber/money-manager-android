package com.example.project07.tracking;

public class IncomeExpenseDetail {

    private String money;
    private String date;

    public IncomeExpenseDetail() {

    }

    public IncomeExpenseDetail(String money, String date) {
        this.money = money;
        this.date = date;
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
