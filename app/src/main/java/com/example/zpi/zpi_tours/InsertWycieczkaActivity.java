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


public class InsertWycieczkaActivity extends Activity {
    Button buttonUtworz;
    EditText textBoxNazwa;
    EditText textBoxLiczbaMiejsc;
    EditText textBoxOpis;
    EditText textBoxDlugosc;
    EditText textBoxLokalizacja;
    EditText textBoxDataPoczatku;
    EditText textBoxDataKonca;
    EditText textBoxCena;
    Spinner spinnerTrudnosc;
    Spinner spinnerModerator;

    String valueTrudnosc = "";
    String valueModerator = "";

    private String jsonResult = "";
    private String url = "http://10.0.2.2/SerwerXampp/ZPI_Tours/insert-wycieczka.php";//10.0.2.2//192.168.0.11
    private String urlModeratorzy = "http://10.0.2.2/SerwerXampp/ZPI_Tours/moderatorzy.php";//10.0.2.2//192.168.0.11

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_wycieczka);

        buttonUtworz = (Button)findViewById(R.id.buttonUtworz);
        textBoxNazwa = (EditText)findViewById(R.id.textBoxNazwa);
        textBoxLiczbaMiejsc = (EditText)findViewById(R.id.textBoxLiczbaMiejsc);
        textBoxOpis = (EditText)findViewById(R.id.textBoxOpis);
        textBoxDlugosc = (EditText)findViewById(R.id.textBoxDlugosc);
        textBoxLokalizacja = (EditText)findViewById(R.id.textBoxLokalizacja);
        textBoxDataPoczatku = (EditText)findViewById(R.id.textBoxDataPoczatku);
        textBoxDataKonca = (EditText)findViewById(R.id.textBoxDataKonca);
        textBoxCena = (EditText)findViewById(R.id.textBoxCena);
        spinnerTrudnosc = (Spinner)findViewById(R.id.spinnerTrudnosc);
        spinnerModerator = (Spinner)findViewById(R.id.spinnerModerator);

        buttonUtworz.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        accessWebService();
                    }
                });

        //zapełnienie Spinnera dla trudności
        ArrayAdapter<CharSequence> adapterTrudnosc = ArrayAdapter.createFromResource(this,
                R.array.trudnosc_array, android.R.layout.simple_spinner_item);
        adapterTrudnosc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTrudnosc.setAdapter(adapterTrudnosc);

        spinnerTrudnosc.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view,
                                               int pos, long id) {
                        valueTrudnosc = Integer.toString((pos+1));
                    }

                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
        );

        //zapełnienie Spinnera dla moderatorów
        accessModeratorzy();

        spinnerModerator.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view,
                                               int pos, long id) {
                        if(pos != 0) {
                            valueModerator = (String) parent.getItemAtPosition(pos);
                        } else {
                            valueModerator = "";
                        }
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                        valueModerator = "";
                    }
                }
        );

        //wyczyszczenie jsonResult
        jsonResult = "";
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
                    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(7);
                    nameValuePairs.add(new BasicNameValuePair("nazwa", textBoxNazwa.getText().toString()));
                    nameValuePairs.add(new BasicNameValuePair("liczba_miejsc", textBoxLiczbaMiejsc.getText().toString()));
                    nameValuePairs.add(new BasicNameValuePair("opis", textBoxOpis.getText().toString()));
                    nameValuePairs.add(new BasicNameValuePair("dlugosc_trasy", textBoxDlugosc.getText().toString()));
                    nameValuePairs.add(new BasicNameValuePair("poziom_trudnosci", valueTrudnosc));
                    nameValuePairs.add(new BasicNameValuePair("lokalizacja", textBoxLokalizacja.getText().toString()));
                    nameValuePairs.add(new BasicNameValuePair("data_poczatku", textBoxDataPoczatku.getText().toString()));
                    nameValuePairs.add(new BasicNameValuePair("data_konca", textBoxDataKonca.getText().toString()));
                    nameValuePairs.add(new BasicNameValuePair("cena", textBoxCena.getText().toString()));
                    nameValuePairs.add(new BasicNameValuePair("id_moderatora", valueModerator));
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                }

                HttpResponse response = httpclient.execute(httppost);
                jsonResult = inputStreamToString(
                        response.getEntity().getContent()).toString();
                Log.v("Ping","jsonResult"+jsonResult);
            }

            catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
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
            else if(lastUrl.equals(urlModeratorzy))
                accessPointModeratorzy();
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

            boolean sukces = (sukcesString.equals("true") ? true : false);

            if(sukces) {
                String nazwa = textBoxNazwa.getText().toString()
                        + " do " + textBoxLokalizacja.getText().toString();
                Toast.makeText(getApplicationContext(),
                        "Wycieczka " + nazwa + " została pomyślnie dodana.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "Dodanie nie powiodło się.", Toast.LENGTH_LONG).show();
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
    public void accessModeratorzy() {
        JsonReadTask task = new JsonReadTask();
        // passes values for the urls string array
        task.execute(new String[] { urlModeratorzy });
    }

    public void accessPointModeratorzy() {
        List<String> listaModeratorow = new ArrayList<String>(100);
        listaModeratorow.add("-wybierz-");

        try {
            JSONObject jsonResponse = new JSONObject(jsonResult);
            JSONArray jsonMainNode = jsonResponse.optJSONArray("moderator");

            for (int i = 0; i < jsonMainNode.length(); i++) {
                JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                String miasto = jsonChildNode.optString("id_uzytkownika");

                listaModeratorow.add(miasto);
            }
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), "Spinner miast: Error " + e.toString(),
                    Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Spinner miast: Error " + e.toString(),
                    Toast.LENGTH_LONG).show();
        }

        ArrayAdapter<String> adapterModerator = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listaModeratorow);
        adapterModerator.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerModerator.setAdapter(adapterModerator);
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
