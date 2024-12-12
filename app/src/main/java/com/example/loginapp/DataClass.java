package com.example.loginapp;

public class DataClass {
    private String dataname;
    private String dataprice;
    private String datades;
    private String dataimage;
    private String key;
    public String getKey(){
    return key;
    }
    public void setKey(String key){
    this.key=key;
    }
    public String getDataname(){
        return dataname;
    }
    public void setDataname(String dataname) {
        this.dataname = dataname;
    }

    public String getDataprice(){
        return dataprice;
    }
    public void setDataprice(String dataprice) {
        this.dataprice = dataprice;
    }
    public String getDatades(){
        return datades;
    }
    public void setDatades(String datades) {
        this.datades = datades;
    }
    public String getDataimage(){
        return dataimage;
    }
    public void setDataimage(String dataimage) {
        this.dataimage = dataimage;
    }
    public DataClass(){
    //default constructor
    }
    public DataClass(String dataname,String dataprice,String datades,String dataimage){
        this.dataname = dataname;
        this.dataprice= dataprice;
        this.datades=datades;
        this.dataimage=dataimage;
    }

}