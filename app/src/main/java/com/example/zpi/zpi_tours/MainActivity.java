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
import android.widget.Button;
import android.widget.EditText;
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
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MainActivity extends Activity {

    Button   buttonLogin;
    Button   buttonAnswer;
    EditText loginBox ;
    EditText password ;
    Context context;
    Intent intent;


    private String jsonResult = "";
    private String url = "http://zpitours.za.pl/login-improved.php";
    //"http://10.0.2.2/login.php";//10.0.2.2//192.168.0.11
   // private String url = "http://zpitours.za.pl/login.php";//10.0.2.2//192.168.0.11


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        buttonLogin = (Button)findViewById(R.id.buttonLogin);
        buttonAnswer = (Button)findViewById(R.id.buttonAnswer);
        loginBox   = (EditText)findViewById(R.id.textBoxLogin);
        password   = (EditText)findViewById(R.id.textBoxPass);


        buttonLogin.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        accessWebService();
                    }
                });
        
        buttonAnswer.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i = new Intent(getApplicationContext(), RejestracjaUzytkownikaActivity.class);
                startActivity(i);
            }
        });
    }

    private class JsonReadTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(params[0]);
            try {
                if(params[0].equals(url)) {
                    byte[] hash;

                    try {
                        hash = MessageDigest.getInstance("MD5").digest(password.getText().toString().getBytes("UTF-8"));
                    } catch (NoSuchAlgorithmException e) {
                        throw new RuntimeException("Huh, MD5 should be supported?", e);
                    } catch (UnsupportedEncodingException e) {
                        throw new RuntimeException("Huh, UTF-8 should be supported?", e);
                    }

                    StringBuilder hex = new StringBuilder(hash.length * 2);

                    for (byte b : hash) {
                        int i = (b & 0xFF);
                        if (i < 0x10) hex.append('0');
                        hex.append(Integer.toHexString(i));
                    }

                    String haslo = hex.toString();
                    Log.v("Użytkownik","hash to " + haslo + " ");

                    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                    nameValuePairs.add(new BasicNameValuePair("email",loginBox.getText().toString()));
                    nameValuePairs.add(new BasicNameValuePair("hash",haslo));
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                }
                HttpResponse response = httpclient.execute(httppost);
                jsonResult = inputStreamToString(
                        response.getEntity().getContent()).toString();
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
            asceessPoint();
        }
    }// end async task

    public void accessWebService() {
        JsonReadTask task = new JsonReadTask();
        // passes values for the urls string array
        task.execute(new String[] { url });
    }

    // build hash set for list view
    public void asceessPoint() {
        List<Map<String, String>> employeeList = new ArrayList<Map<String, String>>();
        Boolean isAccept = false;
        try {
            JSONObject jsonResponse = new JSONObject(jsonResult);
            JSONArray jsonMainNode = jsonResponse.optJSONArray("klient");
            Log.v("Użytkownik", "jsonMainNode: " + jsonMainNode);

            if (jsonMainNode != null) {
                String stan = jsonResponse.optString("stan");
                Log.v("Użytkownik", "Odebrany stan to: "+ stan);

                if (stan.equals("user")){
                    Log.v("Użytkownik", loginBox.getText().toString()+" jest zalogowany");
                    context =getApplicationContext();
                    intent = new Intent(context, GeneralActivity.class );
                    startActivity(intent);
                    isAccept = true;
                } else if(stan.equals("admin")) {
                    Log.v("Użytkownik", loginBox.getText().toString()+" (super-admin) jest zalogowany");
                    context =getApplicationContext();
                    intent = new Intent(context, SuperUzytkownicyActivity.class );
                    startActivity(intent);
                    isAccept = true;
                }
            }
            if(isAccept==false) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Hasło lub login jest niepoprawne!", Toast.LENGTH_SHORT);
                toast.show();
                Log.v("Użytkownik", loginBox.getText().toString()+" nie jest zalogowany");
            }

        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), "Error " + e.toString(),
                    Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error " + e.toString(),
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
