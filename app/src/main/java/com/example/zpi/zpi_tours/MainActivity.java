package com.example.zpi.zpi_tours;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {

    Button   buttonLogin;
    EditText login ;
    EditText password ;
    Context context;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        buttonLogin = (Button)findViewById(R.id.buttonLogin);
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
