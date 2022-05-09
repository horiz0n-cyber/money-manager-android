package com.example.project07.income;

public class IncomeClass {

    private int in_id;
    private String money;
    private int cate_id;
    private String note;
    private String date;
    private int AccId;

    public IncomeClass(String money, int cate_id, String note, String date, int accId) {
        this.money = money;
        this.cate_id = cate_id;
        this.note = note;
        this.date = date;
        AccId = accId;
    }

    public IncomeClass( String money, int cate_id, String note, String date) {
        this.money = money;
        this.cate_id = cate_id;
        this.note = note;
        this.date = date;
    }

    public IncomeClass(int in_id, String money, String date) {
        this.money = money;
        this.in_id = in_id;
        this.date = date;
    }

    public int getIn_id() {
        return in_id;
    }

    public void setIn_id(int in_id) {
        this.in_id = in_id;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public int getCate_id() {
        return cate_id;
    }

    public void setCate_id(int cate_id) {
        this.cate_id = cate_id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAccId() {
        return AccId;
    }

    public void setAccId(int accId) {
        AccId = accId;
    }
}
