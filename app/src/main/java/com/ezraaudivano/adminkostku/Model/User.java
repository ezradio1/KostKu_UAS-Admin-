package com.ezraaudivano.adminkostku.Model;

import java.io.Serializable;

public class User implements Serializable {
    String email, fullname;
    int jumlah;

    public User(String email, String fullname, int jumlah) {
        this.email = email;
        this.fullname = fullname;
        this.jumlah = jumlah;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
}
