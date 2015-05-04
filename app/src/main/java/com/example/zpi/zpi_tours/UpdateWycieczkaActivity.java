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


public class UpdateWycieczkaActivity extends Activity {
    Spinner spinnerIdWycieczki;
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
    List<String> listaModeratorow;

    private String jsonResult = "";
    private String jsonWycieczki = "";
    private String url = "http://10.0.2.2/SerwerXampp/ZPI_Tours/update-wycieczka.php";//10.0.2.2//192.168.0.11
    private String urlModeratorzy = "http://10.0.2.2/SerwerXampp/ZPI_Tours/moderatorzy.php";//10.0.2.2//192.168.0.11
    private String urlWycieczki = "http://10.0.2.2/SerwerXampp/ZPI_Tours/wycieczki-pelne.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_wycieczka);

        spinnerIdWycieczki = (Spinner)findViewById(R.id.spinnerIdWycieczki);
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

        //zapełnienie Spinnera dla wycieczek
        /********************************/Log.v("Ping","1");
        accessWycieczki();
        /********************************/Log.v("Ping","2");

        spinnerIdWycieczki.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view,
                                               int pos, long id) {
                        /********************************/Log.v("Ping","onItemSelected");
                        if(pos != 0) {
                            /********************************/Log.v("Ping","onItemSelected if()");
                            Integer id_wycieczki = Integer.parseInt((String) parent.getItemAtPosition(pos));
                            /********************************/Log.v("Ping","onItemSelected przed if() if()");
                            if (id_wycieczki != null)
                            /********************************/Log.v("Ping","onItemSelected if() if()");
                                fillInFieldsForId(id_wycieczki);
                        }
                    }

                    public void onNothingSelected(AdapterView<?> parent) {

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
                    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(10);
                    nameValuePairs.add(new BasicNameValuePair("id_wycieczki", spinnerIdWycieczki.getSelectedItem().toString()));
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
                Log.v("Ping", "jsonResult" + jsonResult);
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
                e.printStackTrace();
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
            else if(lastUrl.equals(urlWycieczki))
                accessPointWycieczki();
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
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error " + e.toString(),
                    Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
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
        listaModeratorow = new ArrayList<String>(100);
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
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Spinner miast: Error " + e.toString(),
                    Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Spinner miast: Error " + e.toString(),
                    Toast.LENGTH_LONG).show();
        }

        ArrayAdapter<String> adapterModerator = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listaModeratorow);
        adapterModerator.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerModerator.setAdapter(adapterModerator);
    }

    public void accessWycieczki() {
        JsonReadTask task = new JsonReadTask();
        // passes values for the urls string array
        task.execute(new String[] { urlWycieczki });
    }

    public void accessPointWycieczki() {
        jsonWycieczki = jsonResult;
        List<String> listaWycieczek = new ArrayList<String>(1000);
        listaWycieczek.add("-wybierz-");

        try {
            JSONObject jsonResponse = new JSONObject(jsonWycieczki);
            JSONArray jsonMainNode = jsonResponse.optJSONArray("wycieczka");

            for (int i = 0; i < jsonMainNode.length(); i++) {
                JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                String id = jsonChildNode.optString("id_wycieczki");

                listaWycieczek.add(id);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Spinner miast: Error " + e.toString(),
                    Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Spinner miast: Error " + e.toString(),
                    Toast.LENGTH_LONG).show();
        }

        ArrayAdapter<String> adapterWycieczka = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listaWycieczek);
        adapterWycieczka.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        /**************/Log.v("Ping","adapterWycieczka==null : "+(adapterWycieczka==null));
        /**************/Log.v("Ping","spinnerUzytkownik==null : "+(spinnerIdWycieczki==null));
        spinnerIdWycieczki.setAdapter(adapterWycieczka);
    }

    public void fillInFieldsForId(int id) {
        boolean found = false;
        try {
            JSONObject jsonResponse = new JSONObject(jsonWycieczki);
            JSONArray jsonMainNode = jsonResponse.optJSONArray("wycieczka");

            for (int i = 0; !found && i < jsonMainNode.length(); i++) {
                JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                String current_id = jsonChildNode.optString("id_wycieczki");

                if (Integer.parseInt(current_id) == id){
                    found = true;

                    String liczba_miejsc = jsonChildNode.optString("liczba_miejsc");
                    String nazwa = jsonChildNode.optString("nazwa");
                    String opis = jsonChildNode.optString("opis");
                    String dlugosc_trasy = jsonChildNode.optString("dlugosc_trasy");
                    String poziom_trudnosci = jsonChildNode.optString("poziom_trudnosci");
                    String lokalizacja = jsonChildNode.optString("lokalizacja");
                    String data_poczatku = jsonChildNode.optString("data_poczatku");
                    String data_konca = jsonChildNode.optString("data_konca");
                    String cena = jsonChildNode.optString("cena");
                    String id_moderatora = jsonChildNode.optString("id_moderatora");

                    int trudnosc_pos = 0;

                    if(poziom_trudnosci.equals("łatwy"))
                        trudnosc_pos = 1;
                    else if(poziom_trudnosci.equals("średni"))
                        trudnosc_pos = 2;
                    else if(poziom_trudnosci.equals("trudny"))
                        trudnosc_pos = 2;

                    int moderator_pos = 0;

                    for(int j = 0; moderator_pos == 0 && j < listaModeratorow.size(); j++) {
                        if(listaModeratorow.get(j).equals(id_moderatora)) {
                            moderator_pos = j;
                        }
                    }

                    textBoxLiczbaMiejsc.setText(liczba_miejsc);
                    textBoxNazwa.setText(nazwa);
                    textBoxOpis.setText(opis);
                    textBoxDlugosc.setText(dlugosc_trasy);
                    spinnerTrudnosc.setSelection(trudnosc_pos, true);
                    textBoxLokalizacja.setText(lokalizacja);
                    textBoxDataPoczatku.setText(data_poczatku);
                    textBoxDataKonca.setText(data_konca);
                    textBoxCena.setText(cena);
                    spinnerModerator.setSelection(moderator_pos, true);
                }
            }
            if(!found) {
                Toast.makeText(getApplicationContext(), "Nie można znaleźć użytkownika o id "+id+".",
                        Toast.LENGTH_LONG).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error " + e.toString(),
                    Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error " + e.toString(),
                    Toast.LENGTH_SHORT).show();
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
