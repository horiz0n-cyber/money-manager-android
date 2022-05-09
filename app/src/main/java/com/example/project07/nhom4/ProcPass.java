package com.example.project07.nhom4;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ProcPass {
    public static String HashPass(String passwd){
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("md5");
            byte[] result = messageDigest.digest(passwd.getBytes());
            StringBuffer sb = new StringBuffer();
            for(byte b: result){
                int number = b & 0xff;
                String hex = Integer.toHexString(number);
                if(hex.length() == 1){
                    sb.append("0" + hex);
                }else {
                    sb.append(hex);
                }
            }
            return sb.toString();

        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            return "";
        }
    }
}
