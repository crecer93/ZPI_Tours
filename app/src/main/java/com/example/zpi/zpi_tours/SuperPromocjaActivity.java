package com.example.zpi.zpi_tours;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
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
import java.util.List;


public class SuperPromocjaActivity extends Activity {
    Button buttonUtworz;
    EditText textBoxId;
    EditText textBoxEmail;
    EditText textBoxImie;
    EditText textBoxNazwisko;
    Spinner spinnerPlec;
    Spinner spinnerMiasto;
    CheckBox checkBoxModerator;

    String valuePlec = "";
    String valueMiasto = "";

    String id_uzytkownika;

    private String jsonResult = "";
    private String url = "http://zpitours.za.pl/super-promocja.php";//10.0.2.2//192.168.0.11
    private String urlMiasta = "http://zpitours.za.pl/miasta.php";//10.0.2.2//192.168.0.11
    private String urlUzytkownik = "http://zpitours.za.pl/uzytkownik.php";//10.0.2.2//192.168.0.11

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        id_uzytkownika = getIntent().getStringExtra("id_uzytkownika");

        if(id_uzytkownika == null) {
            Toast.makeText(getApplicationContext(),
                    "Wywołano tę aktywność bez podania klucza użytkownika w intencji.", Toast.LENGTH_LONG).show();
        } else {
            setContentView(R.layout.activity_super_promocja);

            buttonUtworz = (Button) findViewById(R.id.buttonUtworz);
            textBoxId = (EditText) findViewById(R.id.textBoxId);
            textBoxId.setKeyListener(null);
            textBoxId.setText(id_uzytkownika);
            textBoxEmail = (EditText) findViewById(R.id.textBoxEmail);
            textBoxEmail.setKeyListener(null);
            textBoxImie = (EditText) findViewById(R.id.textBoxImie);
            textBoxImie.setKeyListener(null);
            textBoxNazwisko = (EditText) findViewById(R.id.textBoxNazwisko);
            textBoxNazwisko.setKeyListener(null);
            spinnerPlec = (Spinner) findViewById(R.id.spinnerPlec);
            spinnerMiasto = (Spinner) findViewById(R.id.spinnerMiasto);

            checkBoxModerator = (CheckBox) findViewById(R.id.checkBoxModerator);

            buttonUtworz.setOnClickListener(
                    new View.OnClickListener() {
                        public void onClick(View view) {
                            accessWebService();
                        }
                    });

            //zapełnienie Spinnera dla płci
            ArrayAdapter<CharSequence> adapterPlec = ArrayAdapter.createFromResource(this,
                    R.array.plec_array, android.R.layout.simple_spinner_item);
            adapterPlec.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerPlec.setAdapter(adapterPlec);

            spinnerPlec.setOnItemSelectedListener(
                    new AdapterView.OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> parent, View view,
                                                   int pos, long id) {
                            valuePlec = Integer.toString((pos));
                        }

                        public void onNothingSelected(AdapterView<?> parent) {
                            valuePlec = "";
                        }
                    }
            );

            //zapełnienie Spinnera dla miast
            accessMiasta();

            spinnerMiasto.setOnItemSelectedListener(
                    new AdapterView.OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> parent, View view,
                                                   int pos, long id) {
                            if (pos != 0)
                                valueMiasto = Integer.toString((pos));
                            else
                                valueMiasto = "";
                        }

                        public void onNothingSelected(AdapterView<?> parent) {
                            valueMiasto = "";
                        }
                    }
            );

            //zapełnienie pól dla uzytkowników
            accessUzytkownik();


            //wyczyszczenie jsonResult
            jsonResult = "";
        }
    }

    private class JsonReadTask extends AsyncTask<String, Void, String> {
        protected String lastUrl;
        @Override
        protected String doInBackground(String... params) {
            lastUrl = params[0];
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(params[0]);
            try {
                if(params[0].equals(url)) {
                    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                    nameValuePairs.add(new BasicNameValuePair("id_uzytkownika", id_uzytkownika));
                    nameValuePairs.add(new BasicNameValuePair("moderator", (checkBoxModerator.isChecked() ? "1" : "0")));
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                } else if(params[0].equals(urlUzytkownik)) {
                    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                    nameValuePairs.add(new BasicNameValuePair("id_uzytkownika", id_uzytkownika));
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                }

                HttpResponse response = httpclient.execute(httppost);
                jsonResult = inputStreamToString(
                        response.getEntity().getContent()).toString();
                Log.v("Ping", "jsonResult" + jsonResult);
            }

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
            if(lastUrl.equals(url))
                accessPointInsert();
            else if(lastUrl.equals(urlMiasta))
                accessPointMiasta();
            else if(lastUrl.equals(urlUzytkownik))
                accessPointUzytkownicy();
        }
    }// end async task

    public void accessWebService() {
        JsonReadTask task = new JsonReadTask();
        // passes values for the urls string array
        task.execute(new String[] { url });
    }

    public void accessPointInsert() {
        try {
            JSONObject jsonResponse = new JSONObject(jsonResult);
            String sukcesString = jsonResponse.optString("sukces");

            boolean sukces = (sukcesString.equals("true"));

            if(sukces) {
                String nazwa = textBoxImie.getText().toString()
                        + " " + textBoxNazwisko.getText().toString();
                if(checkBoxModerator.isChecked()) {
                    Toast.makeText(getApplicationContext(),
                            "Moderator " + nazwa + " został ustanowiony!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Użytkownik " + nazwa + " przestał być moderatorem.", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(getApplicationContext(),
                        "Zmiana stanus nie powiodło się.", Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), "Error " + e.toString(),
                    Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error " + e.toString(),
                    Toast.LENGTH_SHORT).show();
        }
    }

    //accessMiasta()->JsonReadTask.doBackground()->JSonReadTask.postExecute()->accessPointMiasta()
    public void accessMiasta() {
        JsonReadTask task = new JsonReadTask();
        // passes values for the urls string array
        task.execute(new String[]{urlMiasta});
    }

    public void accessPointMiasta() {
        List<String> listaMiast = new ArrayList<String>(1000);
        listaMiast.add("-brak-");

        try {
            Log.v("Ping","0");
            JSONObject jsonResponse = new JSONObject(jsonResult);
            Log.v("Ping","1");
            JSONArray jsonMainNode = jsonResponse.optJSONArray("miasto");
            Log.v("Ping","2");

            for (int i = 0; i < jsonMainNode.length(); i++) {
                JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                String miasto = jsonChildNode.optString("nazwa_miasta");

                listaMiast.add(miasto);
            }
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), "Spinner miast: Error " + e.toString(),
                    Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Spinner miast: Error " + e.toString(),
                    Toast.LENGTH_LONG).show();
        }

        ArrayAdapter<String> adapterMiasto = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listaMiast);
        adapterMiasto.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMiasto.setAdapter(adapterMiasto);
    }

    public void accessUzytkownik() {
        JsonReadTask task = new JsonReadTask();
        // passes values for the urls string array
        task.execute(new String[] {urlUzytkownik});
    }

    public void accessPointUzytkownicy() {
        try {
            JSONObject jsonResponse = new JSONObject(jsonResult);
            JSONArray jsonMainNode = jsonResponse.optJSONArray("uzytkownik");

            JSONObject jsonChildNode = jsonMainNode.getJSONObject(0);

            String email = jsonChildNode.optString("email");
            String imie = jsonChildNode.optString("imie");
            String nazwisko = jsonChildNode.optString("nazwisko");
            String plec = jsonChildNode.optString("plec");
            String id_miasta = jsonChildNode.optString("id_miasta");
            String moderator = jsonChildNode.optString("moderator");

            int plec_pos = 0;

            if(plec.equals("mężczyzna"))
                plec_pos = 1;
            else if(plec.equals("kobieta"))
                plec_pos = 2;

            int miasto_pos;

            if(id_miasta.equals("null"))
                miasto_pos = 0;
            else
                miasto_pos = Integer.parseInt(id_miasta);

            textBoxEmail.setText(email);
            textBoxImie.setText(imie);
            textBoxNazwisko.setText(nazwisko);
            spinnerPlec.setSelection(plec_pos, true);
            spinnerMiasto.setSelection(miasto_pos, true);

            if(moderator.equals("1")) {
                checkBoxModerator.setChecked(true);
            }
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), "Spinner miast: Error " + e.toString(),
                    Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Spinner miast: Error " + e.toString(),
                    Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_insert_uzytkownik, menu);
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
