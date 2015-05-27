package com.example.zpi.zpi_tours;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class SuperUzytkownicyActivity extends Activity {
    private ListView listUzytkownicy;
    private ArrayList<Map<String, Object>> data;

    private String jsonResult = "";
    private String url = "http://zpitours.za.pl/uzytkownicy.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_uzytkownicy);

        listUzytkownicy = (ListView)findViewById(R.id.listUzytkownicy);
        accessWebService();

        listUzytkownicy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                przejscie(position);
            }

        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        accessWebService();
    }

    protected void przejscie(int position) {
        String id = (String)data.get(position).get("id");

        Context context = getApplicationContext();
        Intent intent = new Intent(context, SuperPromocjaActivity.class );
        intent.putExtra("id_uzytkownika",id);

        startActivity(intent);
    }

    private class JsonReadTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(params[0]);
            try {
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
            listUzytkownicy();
        }
    }// end async task

    public void accessWebService() {
        JsonReadTask task = new JsonReadTask();
        // passes values for the urls string array
        task.execute(new String[]{url});
    }

    public void listUzytkownicy() {
        String id;
        String email;
        String mod;
        data = new ArrayList<Map<String, Object>>();

        try {
            JSONObject jsonResponse = new JSONObject(jsonResult);
            JSONArray jsonMainNode = jsonResponse.optJSONArray("uzytkownik");
            data = new ArrayList<Map<String, Object>>();
            Map<String, Object> m;


            for (int i = 0; i < jsonMainNode.length(); i++) {
                Log.v("Wstawianie", "--- Insert in myAndroidSQL: ---");
                JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);

                id = jsonChildNode.optString("id_uzytkownika");
                email = jsonChildNode.optString("email");

                if(jsonChildNode.optString("moderator").equals("0")) {
                    mod = " ";
                } else {
                    mod = "moderator";
                }

                m = new HashMap<String, Object>();
                m.put("id",id);
                m.put("email",email);
                m.put("mod",mod);

                data.add(m);
            }
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), "Error" + e.toString(),
                    Toast.LENGTH_SHORT).show();
        }
        String[] from = { "id", "email" , "mod" };
        int[] to = { R.id.textOnList1, R.id.textOnList2, R.id.textOnList3};

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, data, R.layout.item_uzytkownik, from, to);
        listUzytkownicy.setAdapter(simpleAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_super_uzytkownicy, menu);
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
