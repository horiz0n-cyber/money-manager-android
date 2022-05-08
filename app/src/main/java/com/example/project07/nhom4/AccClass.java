package com.example.project07.nhom4;

public class AccClass {
    private int id;
    private String name;
    private String phone;
    private String passwd;
    private String date;

    public AccClass() {
        super();
    }

    public AccClass(int id, String name, String phone, String passwd, String date) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.passwd = passwd;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "TaiKhoan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", passwd='" + passwd + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
