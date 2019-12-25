package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void btnSignup_clicked(View view){
        String username=((EditText)findViewById(R.id.txtUsername))
                .getText()
                .toString();
        String password=((EditText)findViewById(R.id.txtPassword))
                .getText()
                .toString();
        new SignupTask().execute(username,password);
    }
    public class SignupTask extends AsyncTask<String , Object,Object>{
        @Override
        protected Object doInBackground(String... strings) {
            try {
                new OkHttpClient()
                        .newCall(
                                new Request.Builder()
                                        .url("http://192.168.43.201:8080/ServiceDemo23/signup?username="+strings[0]+"&password="+strings[1])
                                        .build()
                        )
                        .execute();
            }catch (Exception e){
                return null;
            }
        }

        @Override
        protected void onPostExecute(Object o) {
            new SignupDialog().show(getSupportFragmentManager(),"");
        }
    }
}
