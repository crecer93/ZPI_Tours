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



    public Wycieczka(int id, String nazwa, double cena,Context context) {

        this.id = id;
        this.nazwa = nazwa;
        this.cena = cena;
        this.context = context;

    }
    public Wycieczka (){

    }



   /* public int getId(int position) {
       int id =  Wycieczki.get(position).id;
        return id ;
    }
    public void list (){
        for (int k = 0 ; k< Wycieczki.size(); k++ ) {
            System.out.println(Wycieczki.get(k).nazwa);
        }
    }*/
    public void setSizeArray(){
        //Wycieczki =
    }
    public void nowa_wyczieczka(int i,Wycieczka wycieczka)
    {




    }

    public ListAdapter SimpleAdapter ( ArrayList<Map<String, Object>> data){
        String[] from = { "nazwa" ,"cena" };
        int[] to = { R.id.tekst1, R.id.tekst2};

        simpleAdapter = new SimpleAdapter(context, data, R.layout.item,from, to);
        return simpleAdapter;
    }
}
