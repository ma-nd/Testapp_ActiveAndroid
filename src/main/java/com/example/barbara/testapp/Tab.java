package com.example.barbara.testapp;

import android.content.Intent;
import android.support.design.widget.TabLayout;

import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;




public class Tab extends AppCompatActivity{
        private Adapter mSectionsPageAdapter;

        private ViewPager mViewPager;
    @Override
    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_tab);






            mSectionsPageAdapter = new Adapter(getSupportFragmentManager());


            mViewPager = (ViewPager) findViewById(R.id.container);
            setupViewPager(mViewPager);

            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(mViewPager);


        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       MenuInflater menuInflater =  getMenuInflater();
               menuInflater.inflate(R.menu.main, menu);
        return true;
    }
public void logout(MenuItem item){
finish();
}

    private void setupViewPager(ViewPager viewPager) {
            Adapter adapter = new Adapter(getSupportFragmentManager());
            adapter.addFragment(new Tab1(), "TAB1");
            adapter.addFragment(new Tab2(), "TAB2");
            //adapter.addFragment(new Tab3(), "TAB3");
            viewPager.setAdapter(adapter);
        }

}