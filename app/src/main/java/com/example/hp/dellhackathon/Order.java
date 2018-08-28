package com.example.hp.dellhackathon;

/**
 * Created by hp on 28-Aug-18.
 */

public class Order {
    String name;
    String price;
    int time;
    int expected;
    public Order(){

    }
    public Order(String n,String p,int t,int ex){
        name=n;
        price=p;
        time=t;
        expected=ex;
    }

    public int getExpected() {
        return expected;
    }

    public int getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public void setExpected(int expected) {


    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
