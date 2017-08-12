package com.example.barbara.testapp;


import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.activeandroid.ActiveAndroid;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;


import java.util.ArrayList;
import java.util.List;


public class DB_Activity extends AppCompatActivity {
    @Override
    public void onBackPressed() {
        Intent startMain =  new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_db_);


        Intent intent = getIntent();
        String message = intent.getStringExtra(Login.MESSAGE);

        final TextView textView3 = (TextView) findViewById(R.id.textView3);
        textView3.setText("user: " + message);



        final EditText name = (EditText) findViewById(R.id.name);
        final EditText surname = (EditText) findViewById(R.id.surname);
        final EditText age = (EditText) findViewById(R.id.age);
        final Spinner mf = (Spinner) findViewById(R.id.mf);
        final TextView warning = (TextView) findViewById(R.id.warning);
        Button add = (Button) findViewById(R.id.add);
        final Button delete = (Button) findViewById(R.id.delete);
        final ListView itemList = (ListView) findViewById(R.id.itemList);
        final Button edit = (Button) findViewById(R.id.edit);

        ActiveAndroid.initialize(this);
        showDB(itemList);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dbname = name.getText().toString();
                String dbsurname = surname.getText().toString();
                String dbage = age.getText().toString();
                String dbmf = mf.getSelectedItem().toString();
                int nameLength = dbname.length();
                int nameSurname = dbsurname.length();
                int nameAge = dbage.length();


                if ((nameLength == 0 | nameSurname == 0 | nameAge == 0))
                    warning.setText("You can't leave empty field");
                else {

                    Item item = new Item(dbname, dbsurname, dbage, dbmf);
                    item.save();
                    warning.setText("Add to database");
                    showDB(itemList);
                }
            }
        });

        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                String item = (String) itemList.getItemAtPosition(i);
                Item person = new Item();
                String ID = item.substring(0, item.indexOf(' '));

                final long IDnumber = Long.parseLong(ID);
                person = getPerson(IDnumber);

                int x;

               if (person.getMF().equals("Male"))
                   x = 0;
                else
                    x=1;



               name.setText(person.getName());
                surname.setText(person.getSurname());
                age.setText(person.getAge());
                mf.setSelection(x);

                final boolean[] selected = {true};
                warning.setText("Selected: " + person.getId() + " " + person.getName() + " " + person.getSurname() + " " + person.getAge() + " " + person.getMF());

                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(selected[0]) {
                        warning.setText("Deleted from database one record");
                        new Delete().from(Item.class).where("id = ?", IDnumber).execute();

                        showDB(itemList);

                    }
                        selected[0] = false;}

                });

                edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

if(selected[0]) {
    String dbname = name.getText().toString();
    String dbsurname = surname.getText().toString();
    String dbage = age.getText().toString();
    String dbmf = mf.getSelectedItem().toString();

    Item person1 = new Item();
    person1 = getPerson(IDnumber);
    person1.name = dbname;
    person1.surname = dbsurname;
    person1.age = dbage;
    person1.mf = dbmf;
    person1.save();
    selected[0] = false;
    warning.setText("Changes saved");
    showDB(itemList);
}

                    }

                });

            }


        });
    }

    private void showDB(ListView itemList) {
        ArrayList<String> arrayList;
        arrayList = new ArrayList<>();
        Item item1 = new Item();
        List<Item> a = getAll();
        for (int i = 0; i < a.size(); i++) {
            item1 = a.get(i);
            arrayList.add(item1.getId() + "      " + item1.getName() + "      " + item1.getSurname() + "      " + item1.getAge() + "      " + item1.getMF());
        }
        final ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);
        itemList.setAdapter(adapter);
    }




   public static List<Item> getAll() {
       return new Select().from(Item.class).execute();
    }

    public static Item getPerson(long i) {
        return new Select()
                .from(Item.class)
                .where("ID = ?", i)
                .executeSingle();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater =  getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return true;
    }
    public void logout(MenuItem item){
        Intent intent = new Intent(DB_Activity.this, Login.class);

        startActivity(intent);
    }




    public void medapp(MenuItem item) {
        Uri uri = Uri.parse("http://www.medapp.pl");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);

    }
    }
