package com.example.barbara.testapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity {

    public static final String MESSAGE = "com.example.barbara.testapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login = (Button)findViewById(R.id.button);


        final EditText email = (EditText)findViewById(R.id.editText);
        final EditText password = (EditText)findViewById(R.id.editText2);
        final TextView warning = (TextView)findViewById(R.id.textView);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Pattern pattern = Pattern.compile("(.+)[@](.+)[.](.+)");  //change to compatible to protocol

                Pattern passwordPattern = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[\\W\\_])(?=.*[A-Z])(?!.*\\s).{4,}$");   //uppercase and lowercase letter, digit, special character without blank space, min. 4 characters

                String newEmail = email.getText().toString();

                Matcher matcher = pattern.matcher(email.getText().toString());
                Matcher passwordMatcher = passwordPattern.matcher(password.getText().toString());

                if (matcher.matches() == false & passwordMatcher.matches()==false)
                    warning.setText("Invalid email address format \nPassword must contains at least: one uppercase and lowercase letter, digit, special character");

                else if(matcher.matches() == false)
                    warning.setText("Invalid email address format");

                else if(passwordMatcher.matches()==false)
                    warning.setText("Password must contains at least: one uppercase and lowercase letter, digit, special character");
                else {
                    warning.setText("");
                   // Intent intent = new Intent(Login.this, Tab.class);
                   Intent intent = new Intent(Login.this, DB_Activity.class);
                    intent.putExtra(MESSAGE, newEmail);
                    startActivity(intent);
                    finish();


                }
                ;

            }

});
    }

}