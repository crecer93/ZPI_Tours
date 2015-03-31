package com.example.zpi.zpi_tours;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.PrintStreamPrinter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;


public class MainActivity extends Activity {

    Button   buttonLogin;
    Button   buttonAnswer;
    EditText login ;
    EditText password ;
    Context context;
    Intent intent;
   // private  String message;




    private Socket client;
    private PrintWriter printwriter;
    private InputStreamReader inputstreamreader ;


    private String messsage;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        buttonLogin = (Button)findViewById(R.id.buttonLogin);
        buttonAnswer = (Button)findViewById(R.id.buttonAnswer);
        login   = (EditText)findViewById(R.id.textBoxLogin);
        password   = (EditText)findViewById(R.id.textBoxPass);


        buttonLogin.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        if (login.getText().toString().equals("login")& password.getText().toString().equals("qwerty")){
                            Log.v("Urzytkownik", login.getText().toString()+" jest zalogowany");
                            context =getApplicationContext();

                             intent = new Intent(context, GeneralActivity.class );
                            startActivity(intent);
                        }else {
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    "Hasło lub login jest nie poprawne!", Toast.LENGTH_SHORT);
                            toast.show();
                            Log.v("Urzytkownik", login.getText().toString()+" nie jest zalogowany");
                        }



                    }
                });

        buttonLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {


                    messsage = ":"+login.getText()+":"+password.getText(); // get the text message on the text field
                    // Reset the text field to blank
                    SendMessage sendMessageTask = new SendMessage();
                    sendMessageTask.execute();
                }


        });

        buttonAnswer.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {



               GetAnswer getAnswer = new GetAnswer();
               getAnswer.execute();

            }


        });
    }

    private class SendMessage extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {

                client = new Socket("192.168.1.2", 4444); // connect to the server
                printwriter = new PrintWriter(client.getOutputStream(), true);
                printwriter.write(messsage); // write the message to output stream

                printwriter.flush();
                printwriter.close();
                client.close(); // closing the connection

            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

    }

    private class GetAnswer extends AsyncTask<Void, Void, Void> {
       // String message ;
        @Override
        protected Void doInBackground(Void... params) {
            try {

                client = new Socket("192.168.1.2", 4444); // connect to the server
                inputstreamreader = new  InputStreamReader ( client.getInputStream());



                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

                String fromServer;
                fromServer = in.readLine();
                 System.out.println( fromServer+"");

               // printwriter = new PrintWriter(client.getOutputStream(), true);
                //printwriter.write(messsage); // write the message to output stream

                //printwriter.flush();
               // printwriter.close();
                client.close(); // closing the connection

            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
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
