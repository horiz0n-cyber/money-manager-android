package com.example.project07.nhom4;

public class AccClass {
//    private int id;
    private String name;
    private String phone;
    private String passwd;
    private String date;

    public AccClass() {
        super();
    }

    public AccClass( String name, String phone, String passwd, String date) {
        this.name = name;
        this.phone = phone;
        this.passwd = passwd;
        this.date = date;
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
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", passwd='" + passwd + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public String convertPhone (String phone){
        String strFirst = "";
        for(int i=0; i<phone.length(); i++){
            if(i<2 || i>phone.length()-3){
                strFirst += phone.charAt(i);
            } else
                strFirst += "x";
        }
        return strFirst;
    }
}
