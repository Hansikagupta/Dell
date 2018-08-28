package com.example.hp.dellhackathon;

/**
 * Created by hp on 27-Aug-18.
 */

public class Item {
    private String Name;
    private String Price;
    private int Thumbnail;
    private int ManufacturingTime;
    private String Description;

    public Item(){

    }

    public Item(String name, String price, int thumbnail,int manufacturingTime,String description){
        Name=name;
        Price=price;
        Thumbnail=thumbnail;
        ManufacturingTime=manufacturingTime;
        Description=description;
    }

    public String getName(){
        return Name;
    }
    public String getPrice(){
        return Price;
    }
    public int getThumbnail(){
        return Thumbnail;
    }
    public int getManufacturingTime() {
        return ManufacturingTime;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setManufacturingTime(int manufacturingTime) {
        ManufacturingTime = manufacturingTime;
    }

    public void setName(String name){
        Name=name;
    }
    public void setPrice(String type){
        Price=type;
    }
    public void setThumbnail(int thumbnail){
        Thumbnail=thumbnail;
    }
}
