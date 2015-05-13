package com.example.zpi.zpi_tours;

import android.content.Context;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Filip on 2015-05-13.
 */
public class WycieczkiLista {
    SimpleAdapter simpleAdapter;
    Context context;//Czy to w ogóle bêdzie u¿ywane
    ArrayList<Wycieczka> wycieczki = new ArrayList<Wycieczka>();

    public WycieczkiLista(Context context) {
        this.context = context;
    }

    public void nowaWycieczka(int i, int id, String nazwa, double cena){
        wycieczki.add(i,new Wycieczka(id,nazwa,cena));
    }

    public ListAdapter simpleAdapter() {
        String[] from = { "nazwa", "cena" };
        int[] to = { R.id.tekst1, R.id.tekst2};

        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        Map<String, Object> m;

        for(Wycieczka w: wycieczki) {
            m = new HashMap<String, Object>();

            m.put("nazwa", w.getNazwa());
            m.put("cena", w.getCena());
            m.put("id_w", w.getId());

            data.add(m);
        }

        simpleAdapter = new SimpleAdapter(context, data, R.layout.item, from, to);
        return simpleAdapter;
    }
}
