package com.example.latpas;

public class Model {
    String nama;
    String gambar;

    public Model(String nama, String gambar) {
        this.nama = nama;
        this.gambar = gambar;
    }

    public String getNama() {
        return nama;
    }

    public String getGambar() {
        return gambar;
    }
}
