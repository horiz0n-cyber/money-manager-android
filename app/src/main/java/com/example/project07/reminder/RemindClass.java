package com.example.project07.reminder;

public class RemindClass {
    private int noti_id;
    private String money;
    private String date;
    private String note;
    private int AccId;

    public RemindClass(int noti_id, String money, String date, String note) {
        this.noti_id = noti_id;
        this.money = money;
        this.date = date;
        this.note = note;
    }

    public RemindClass(int noti_id, String money, String date, String note, int accId) {
        this.noti_id = noti_id;
        this.money = money;
        this.date = date;
        this.note = note;
        AccId = accId;
    }

    public RemindClass() {
    }

    public int getNoti_id() {
        return noti_id;
    }

    public void setNoti_id(int noti_id) {
        this.noti_id = noti_id;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getAccId() {
        return AccId;
    }

    public void setAccId(int accId) {
        AccId = accId;
    }

    @Override
    public String toString() {
        return "RemindClass{" +
                "noti_id=" + noti_id +
                ", money='" + money + '\'' +
                ", date='" + date + '\'' +
                ", note='" + note + '\'' +
                ", AccId=" + AccId +
                '}';
    }
}
