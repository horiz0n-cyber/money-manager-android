package com.example.project07.reminder;

public class Reminder {

    private String title;
    private String money;
    private String date;

    public Reminder() {
    }

    public Reminder(String title, String money, String date) {
        this.title = title;
        this.money = money;
        this.date = date;
    }

    //Getter

    public String getTitle() {
        return title;
    }

    public String getMoney() {
        return money;
    }

    public String getDate() {
        return date;
    }

    //Setter

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
