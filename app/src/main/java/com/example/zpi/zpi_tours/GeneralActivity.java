package com.example.zpi.zpi_tours;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.database.sqlite.SQLiteOpenHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class GeneralActivity extends Activity  {

    private String jsonResult;
    private String url = "http://zpitours.za.pl/wycieczki.php";//192.168.0.11//10.0.2.2
    public ListView listView;
    final String LOG_TAG = "myLogs";
    Button temp1, temp2, temp3, temp4;
    Wycieczka wycieczka_adapter = new Wycieczka();
    Wycieczka wycieczka ;
    ArrayList<Map<String, Object>> data;
    int id_w;
    double cena_w;
    String nazwa;
    ArrayList<Wycieczka> Wycieczki =  new ArrayList<Wycieczka>(7);

    JSONArray jsonMainNode ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);
        listView = (ListView) findViewById(R.id.listView1);

        accessWebService();

        temp1 = (Button)findViewById(R.id.temp1);
        temp1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Context context =getApplicationContext();
                Intent intent = new Intent(context, ADMIN_InsertUzytkownikActivity.class );
                startActivity(intent);
            }
        });
        temp2 = (Button)findViewById(R.id.temp2);
        temp2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Context context =getApplicationContext();
                Intent intent = new Intent(context, ADMIN_UpdateUzytkownikActivity.class );
                startActivity(intent);
            }
        });
        temp3 = (Button)findViewById(R.id.temp3);
        temp3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Context context =getApplicationContext();
                Intent intent = new Intent(context, ADMIN_InsertWycieczkaActivity.class );
                startActivity(intent);
            }
        });
        temp4 = (Button)findViewById(R.id.temp4);
        temp4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Context context =getApplicationContext();
                Intent intent = new Intent(context, ADMIN_UpdateWycieczkaActivity.class );
                startActivity(intent);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                    getToast(position);
            }

        });

    }
    public  void getToast (int position ){

         //int id = wycieczka.getId(position);
         //String test =""+id;
         //Toast.makeText(this,test , Toast.LENGTH_LONG).show();
        list();

    }


    // Async Task to access the web
    private class JsonReadTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(params[0]);
            try {
                HttpResponse response = httpclient.execute(httppost);
                jsonResult = inputStreamToString(
                        response.getEntity().getContent()).toString();
            }

            catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        private StringBuilder inputStreamToString(InputStream is) {
            String rLine = "";
            StringBuilder answer = new StringBuilder();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));

            try {
                while ((rLine = rd.readLine()) != null) {
                    answer.append(rLine);
                }
            }

            catch (IOException e) {
                // e.printStackTrace();
                Toast.makeText(getApplicationContext(),
                        "Error..." + e.toString(), Toast.LENGTH_LONG).show();
            }
            return answer;
        }

        @Override
        protected void onPostExecute(String result) {
            ListDrwaer();
        }
    }// end async task

    public void accessWebService() {
        JsonReadTask task = new JsonReadTask();
        // passes values for the urls string array
        task.execute(new String[] { url });
    }

    // build hash set for list view
    public void ListDrwaer() {

        try {
            JSONObject jsonResponse = new JSONObject(jsonResult);
            jsonMainNode = jsonResponse.optJSONArray("wycieczki");
            data = new ArrayList<Map<String, Object>>();
            Map<String, Object> m;


            for (int i = 0; i < jsonMainNode.length(); i++) {
                Log.d(LOG_TAG, "--- Insert in myAndroidSQL: ---");
                JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
               jsonMainNode = jsonResponse.optJSONArray("wycieczki");


                String id = jsonChildNode.optString("id_wycieczki");
                nazwa = jsonChildNode.optString("nazwa");
                String dlugosc_trasy = jsonChildNode.optString("dlugosc_trasy");
                String cena  = jsonChildNode.optString("cena");

                id_w = Integer.parseInt(id);
                cena_w = Double.parseDouble(cena);

                m = new HashMap<String, Object>();
                m.put ("nazwa",nazwa);
                m.put ("cena", cena);
                m.put("id_w",id);

                data.add(m);
                wycieczka_adapter = new Wycieczka(id_w,nazwa,cena_w,this);
                //System.out.println("Test"+i);

                 dodajWy(i,id_w,nazwa,cena_w);

            }
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), "Error" + e.toString(),
                    Toast.LENGTH_SHORT).show();
        }
            listView.setAdapter(wycieczka_adapter.SimpleAdapter(data));

    }

    public void dodajWy(int k ,int id_w,String nazwa,double cena_w ){

           //wycieczka = new Wycieczka();
           //wycieczka.nowa_wyczieczka(k,new Wycieczka(id_w,nazwa,cena_w,this));
            Wycieczki.add( k,new Wycieczka(id_w,nazwa,cena_w,this));

    }
    public void list(){
        for (int k = 0 ; k< Wycieczki.size(); k++ ) {
            System.out.println(Wycieczki.get(k).nazwa);
        }
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_general, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
