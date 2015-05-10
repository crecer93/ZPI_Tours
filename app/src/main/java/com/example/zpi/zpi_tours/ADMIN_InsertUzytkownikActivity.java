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


public class ADMIN_InsertUzytkownikActivity extends Activity {

    Button buttonUtworz;
    EditText textBoxEmail;
    EditText textBoxHaslo;
    EditText textBoxImie;
    EditText textBoxNazwisko;
    Spinner spinnerPlec;
    Spinner spinnerMiasto;
    CheckBox checkBoxModerator;

    String valuePlec = "";
    String valueMiasto = "";

    private String jsonResult = "";
    private String url = "http://zpitours.za.pl/insert-uzytkownik.php";//10.0.2.2//192.168.0.11
    private String urlMiasta = "http://zpitours.za.pl/miasta.php";//10.0.2.2//192.168.0.11

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ADMIN_activity_insert_uzytkownik);

        buttonUtworz = (Button)findViewById(R.id.buttonUtworz);
        textBoxEmail = (EditText)findViewById(R.id.textBoxEmail);
        textBoxHaslo = (EditText)findViewById(R.id.textBoxHaslo);
        textBoxImie = (EditText)findViewById(R.id.textBoxImie);
        textBoxNazwisko = (EditText)findViewById(R.id.textBoxNazwisko);
        spinnerPlec = (Spinner)findViewById(R.id.spinnerPlec);
        spinnerMiasto = (Spinner)findViewById(R.id.spinnerMiasto);
        checkBoxModerator = (CheckBox)findViewById(R.id.checkBoxModerator);

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
                        if(pos != 0)
                            valueMiasto = Integer.toString((pos));
                        else
                            valueMiasto = "";
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                        valueMiasto = "";
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
                Log.v("Ping","doInBackground 0");
                if(params[0].equals(url)) {
                    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(7);
                    nameValuePairs.add(new BasicNameValuePair("email", textBoxEmail.getText().toString()));
                    nameValuePairs.add(new BasicNameValuePair("haslo", textBoxHaslo.getText().toString()));
                    nameValuePairs.add(new BasicNameValuePair("imie", textBoxImie.getText().toString()));
                    nameValuePairs.add(new BasicNameValuePair("nazwisko", textBoxNazwisko.getText().toString()));
                    nameValuePairs.add(new BasicNameValuePair("plec", valuePlec));
                    Log.v("Plec","wartość płci: "+valuePlec);
                    nameValuePairs.add(new BasicNameValuePair("id_miasta", valueMiasto));
                    nameValuePairs.add(new BasicNameValuePair("moderator", (checkBoxModerator.isChecked() ? "1" : "0") ));
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                }
                Log.v("Ping","doInBackground 1");

                HttpResponse response = httpclient.execute(httppost);
                Log.v("Ping","doInBackground 2");
                jsonResult = inputStreamToString(
                        response.getEntity().getContent()).toString();
                Log.v("Ping","jsonResult"+jsonResult);
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
                String nazwa = textBoxImie.getText().toString()
                        + " " + textBoxNazwisko.getText().toString();
                Toast.makeText(getApplicationContext(),
                        "Użytkownik " + nazwa + " został pomyślnie dodany.", Toast.LENGTH_LONG).show();
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
    public void accessMiasta() {
        JsonReadTask task = new JsonReadTask();
        // passes values for the urls string array
        task.execute(new String[] { urlMiasta });
    }

    public void accessPointMiasta() {
        List<String> listaMiast = new ArrayList<String>(1000);
        listaMiast.add("-wybierz-");

        try {
            Log.v("Ping","0");
            JSONObject jsonResponse = new JSONObject(jsonResult);
            Log.v("Ping","1");
            JSONArray jsonMainNode = jsonResponse.optJSONArray("miasto");
            Log.v("Ping","2");

            for (int i = 0; i < jsonMainNode.length(); i++) {
                Log.v("Ping","3");
                JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                Log.v("Ping","4");
                String miasto = jsonChildNode.optString("nazwa_miasta");
                /***************************/Log.v("Miasto",miasto+" zostało wybrane");

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
