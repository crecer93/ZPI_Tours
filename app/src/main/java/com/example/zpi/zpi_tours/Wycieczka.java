package com.example.zpi.zpi_tours;

import android.content.Context;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrij on 2015-05-11.
 */
public class Wycieczka {
    int id;
    String nazwa;
    double cena;

    public Wycieczka(int id, String nazwa, double cena) {
        this.id = id;
        this.nazwa = nazwa;
        this.cena = cena;
    }

    public int getId() {
        return id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public double getCena() {
        return cena;
    }
}
