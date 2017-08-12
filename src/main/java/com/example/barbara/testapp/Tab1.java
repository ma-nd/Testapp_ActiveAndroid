package com.example.barbara.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class Tab1 extends Fragment{




    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1, container, false);



        final EditText name = (EditText) view.findViewById(R.id.name);
       final EditText surname = (EditText) view.findViewById(R.id.surname);
        final EditText age = (EditText) view.findViewById(R.id.age);
        final Spinner mf = (Spinner) view.findViewById(R.id.mf);
        final TextView warning = (TextView) view.findViewById(R.id.warning);
        Button add = (Button) view.findViewById(R.id.add);

       String login = log();

        TextView textView = (TextView) view.findViewById(R.id.textView3);
        textView.setText("user:  "+login);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dbname = name.getText().toString();
                String dbsurname = surname.getText().toString();
                String dbage = age.getText().toString();
                String dbmf = mf.getSelectedItem().toString();
                int nameLength =dbname.length();
                int nameSurname =dbsurname.length();
                int nameAge =dbage.length();


                if((nameLength==0|nameSurname==0|nameAge==0))
                    warning.setText("You can't leave empty field");
                else {



                    warning.setText("Add to database");
                }
            }
            });

        return view;
    }





    public String log() {
        Intent intent = getActivity().getIntent();
        String message = intent.getStringExtra(Login.MESSAGE);


    return message;
    }



}