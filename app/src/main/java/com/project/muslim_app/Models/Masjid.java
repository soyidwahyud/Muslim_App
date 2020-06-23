package com.project.muslim_app.Models;

public class Masjid {
    private String code;
    private String gambar;
    private String nama;
    private String alamat;
    private String link;


    public Masjid(){

    }
    public Masjid(String code, String nama, String alamat, String link, String gambar) {
        this.code = code;
        this.nama = nama;
        this.alamat = alamat;
        this.link = link;
        this.gambar = gambar;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
}
