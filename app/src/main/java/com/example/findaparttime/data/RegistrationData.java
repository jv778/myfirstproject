package com.example.findaparttime.data;
import java.util.ArrayList;

public class RegistrationData {
    public String fname;
    public String email;
    public String password;
    public String phonenumber;
    public ArrayList<DataFile> li=new ArrayList<>();


    public RegistrationData(String fname, String email, String password, String phonenumber, ArrayList<DataFile> li) {
        this.fname = fname;
        this.email = email;
        this.password = password;
        this.phonenumber = phonenumber;
        this.li = li;
    }

    public RegistrationData() {
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public ArrayList<DataFile> getLi() {
        return li;
    }

    public void setLi(ArrayList<DataFile> li) {
        this.li = li;
    }
}
