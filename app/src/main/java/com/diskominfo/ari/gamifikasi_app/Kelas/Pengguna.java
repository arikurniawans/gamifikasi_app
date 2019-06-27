package com.diskominfo.ari.gamifikasi_app.Kelas;

public class Pengguna {
    public String email;
    public String no_hp;
    public String password;

    public Pengguna(String email, String no_hp, String password) {
        this.email = email;
        this.no_hp = no_hp;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
