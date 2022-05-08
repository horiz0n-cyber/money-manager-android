package com.example.project07.tracking;

public class IncomeExpense {

    private String Category;
    private String Money;
    private int Photo;

    public IncomeExpense(){

    }

    public IncomeExpense(String category, String money, int photo){
        Category = category;
        Money = money;
        Photo = photo;
    }

    //Getter

    public String getCategory() {
        return Category;
    }

    public String getMoney() {
        return Money;
    }

    public int getPhoto() {
        return Photo;
    }

    //Setter

    public void setCategory(String category) {
        Category = category;
    }

    public void setMoney(String money) {
        Money = money;
    }

    public void setPhoto(int photo) {
        Photo = photo;
    }
}
