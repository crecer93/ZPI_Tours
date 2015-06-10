package com.example.zpi.zpi_tours;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ListaUczestnikow extends Activity {

    private String jsonResult;
    private String url = "http://zpitours.za.pl/wycieczka-moderator-uczestnicy.php";
    JSONArray jsonMainNode;
    final String LOG_TAG = "myLogs";
    ArrayList<Map<String, Object>> data;
    public ListView listUczestnicy;
    SimpleAdapter simpleAdapter ;
    int czyModer;
    public static final String MyPREFERENCES = "MyPrefs" ;

    public static final String mod = "Moderator";
    ImageButton remove;

    String id_wycieczki;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences preferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        czyModer = preferences.getInt(mod,0);
        remove  = (ImageButton)findViewById(R.id.imageButton);

        if (czyModer==0){
           remove.setVisibility(View.INVISIBLE);
        }
        id_wycieczki = getIntent().getStringExtra("id_wycieczki");
        if(id_wycieczki == null) {
            Toast.makeText(getApplicationContext(),
                    "Wywołano tę aktywność bez podania klucza wycieczki w intencji.", Toast.LENGTH_LONG).show();
        } else {
            setContentView(R.layout.activity_lista_uczestnikow);
            listUczestnicy = (ListView)findViewById(R.id.listUczestnicy);
            accessWebService();
        }

    }

    private class JsonReadTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(params[0]);
            try {
                if(params[0].equals(url)) {
                    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                    nameValuePairs.add(new BasicNameValuePair("id_wycieczki",id_wycieczki));
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                }
                HttpResponse response = httpclient.execute(httppost);
                jsonResult = inputStreamToString(
                        response.getEntity().getContent()).toString();
                Log.v("Ping", "jsonResult" + jsonResult);
            }
           /* try {
                HttpResponse response = httpclient.execute(httppost);
                jsonResult = inputStreamToString(
                        response.getEntity().getContent()).toString();
            }*/

            catch (ClientProtocolException e) {
                Log.v("Ping","ClientProtocolExecption");
                e.printStackTrace();
            } catch (IOException e) {
                Log.v("Ping","IOException");
                e.printStackTrace();
            } catch (Exception e) {
                Log.v("Ping","Other exception");
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
            ListUczestnicy();
        }
    }// end async task

    public void accessWebService() {
        JsonReadTask task = new JsonReadTask();
        // passes values for the urls string array
        task.execute(new String[] { url });
    }

    public void ListUczestnicy() {
        String email_u;
        String miasto_u;

        try {
            JSONObject jsonResponse = new JSONObject(jsonResult);
            jsonMainNode = jsonResponse.optJSONArray("uczestnicy");
            data = new ArrayList<Map<String, Object>>();
            Map<String, Object> m;


            for (int i = 0; i < jsonMainNode.length(); i++) {
                Log.d(LOG_TAG, "--- Insert in myAndroidSQL: ---");
                JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);

                email_u =  jsonChildNode.optString("email");
                miasto_u=  jsonChildNode.optString("nazwa_miasta");




                m = new HashMap<String, Object>();
                m.put ("email",email_u);
                m.put ("miasto", miasto_u);

                data.add(m);

            }
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), "Error" + e.toString(),
                    Toast.LENGTH_SHORT).show();
        }
        String[] from = { "email" ,"miasto" };
        int[] to = { R.id.tekst1, R.id.tekst2};

        simpleAdapter = new SimpleAdapter(this, data, R.layout.item_uczestnik,from, to);
        listUczestnicy.setAdapter(simpleAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista_uczestnikow, menu);
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
