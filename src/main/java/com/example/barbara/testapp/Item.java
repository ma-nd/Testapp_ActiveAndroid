package com.example.barbara.testapp;

import com.activeandroid.Model;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;




@Table(name = "Items")
public class Item extends Model {

    @Column(name = "Name")
    public String name;

    public String getName() {
        return name;
    }

    @Column(name = "Surname")
    public String surname;

    public String getSurname() {
        return surname;
    }


    @Column(name = "Age")
    public String age;

    public String getAge() {
        return age;
    }

    @Column(name = "MF")
    public String mf;

    public String getMF() {
        return mf;
    }

    public Item(){
        super();
    }


    public Item(String name, String surname,String age,String mf) {
        super();
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.mf = mf;
    }



}

