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
    SimpleAdapter simpleAdapter ;
    Context context;
    ArrayList<Wycieczka> Wycieczki = new ArrayList<Wycieczka>();


    public Wycieczka(int id, String nazwa, double cena,Context context) {

        this.id = id;
        this.nazwa = nazwa;
        this.cena = cena;
        this.context = context;
    }



    public int getId(int position) {
        return id;
    }

    public void nowa_wyczieczka(int i,int id, String nazwa, double cena,Context context){
       Wycieczki.add(i,new Wycieczka(id,nazwa,cena,context));
    }

    public ListAdapter SimpleAdapter ( ArrayList<Map<String, Object>> data){
        String[] from = { "nazwa" ,"cena" };
        int[] to = { R.id.tekst1, R.id.tekst2};

        simpleAdapter = new SimpleAdapter(context, data, R.layout.item,from, to);
        return simpleAdapter;
    }
}
