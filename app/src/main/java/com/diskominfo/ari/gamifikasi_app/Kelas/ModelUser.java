package com.diskominfo.ari.gamifikasi_app.Kelas;

public class ModelUser {
    public String nip;
    public String nama;
    public String jenkel;
    public String no_hp;
    public String alamat;

    public ModelUser(){

    }

    public ModelUser(String nip, String nama, String jenkel, String no_hp, String alamat) {
        this.nip = nip;
        this.nama = nama;
        this.jenkel = jenkel;
        this.no_hp = no_hp;
        this.alamat = alamat;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenkel() {
        return jenkel;
    }

    public void setJenkel(String jenkel) {
        this.jenkel = jenkel;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
