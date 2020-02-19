package com.example.finallabassignment.model;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "person")
public class Person {
    @PrimaryKey(autoGenerate = true)
    int id;
    public String name;
    public String lastname;
    public String number;
    public String email;
    public String city;

    @Ignore
    public Person(String name, String lastname, String number, String email, String city) {
        this.name = name;
        this.lastname = lastname;
        this.number = number;
        this.email = email;
        this.city = city;
    }

    public Person(int id, String name, String lastname, String number, String email, String city) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.number = number;
        this.email = email;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getlastname() {
        return lastname;
    }

    public void setlastname(String lastname) {
        this.lastname = lastname;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
