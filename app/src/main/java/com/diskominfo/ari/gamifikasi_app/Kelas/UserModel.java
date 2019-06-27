package com.diskominfo.ari.gamifikasi_app.Kelas;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UserModel implements Serializable {
    public String username;
    public String email;
    public String nope;
    public String foto;
    public String akses;


    public UserModel(String username, String email, String nope, String foto,String akses) {
        this.username = username;
        this.email = email;
        this.nope = nope;
        this.foto = foto;
        this.akses = akses;
    }

    public UserModel(){

    }

    public String getAkses() {
        return akses;
    }

    public void setAkses(String akses) {
        this.akses = akses;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNope() {
        return nope;
    }

    public void setNope(String nope) {
        this.nope = nope;
    }
}
