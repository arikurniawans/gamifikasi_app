package com.diskominfo.ari.gamifikasi_app.Kelas;

public class ListPostingModel {
    String foto;
    String kategori;
    String alamat;
    String idLapor;
    String pelapor;
    String waktuLapor;
    String statusLapor;

    public ListPostingModel(){

    }

    public ListPostingModel(String foto, String kategori, String alamat, String idLapor, String pelapor, String waktuLapor, String statusLapor) {
        this.foto = foto;
        this.kategori = kategori;
        this.alamat = alamat;
        this.idLapor = idLapor;
        this.pelapor = pelapor;
        this.waktuLapor = waktuLapor;
        this.statusLapor = statusLapor;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getIdLapor() {
        return idLapor;
    }

    public void setIdLapor(String idLapor) {
        this.idLapor = idLapor;
    }

    public String getPelapor() {
        return pelapor;
    }

    public void setPelapor(String pelapor) {
        this.pelapor = pelapor;
    }

    public String getWaktuLapor() {
        return waktuLapor;
    }

    public void setWaktuLapor(String waktuLapor) {
        this.waktuLapor = waktuLapor;
    }

    public String getStatusLapor() {
        return statusLapor;
    }

    public void setStatusLapor(String statusLapor) {
        this.statusLapor = statusLapor;
    }
}
