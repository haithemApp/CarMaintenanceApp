package com.example.haithem_carmaintenancesystem;

import com.google.firebase.database.Exclude;

public class UserServices {
    public String serName;
    public String serPrice;
    public String serDetail;
    public String serTime;

    public String mKey;

    public UserServices() {
    }

    public UserServices(String serName, String serPrice, String serDetail, String serTime) {
        this.serName = serName;
        this.serPrice = serPrice;
        this.serDetail = serDetail;
        this.serTime = serTime;
    }

    public String getSerName() {
        return serName;
    }

    public void setSerName(String serName) {
        this.serName = serName;
    }

    public String getSerPrice() {
        return serPrice;
    }

    public void setSerPrice(String serPrice) {
        this.serPrice = serPrice;
    }

    public String getSerDetail() {
        return serDetail;
    }

    public void setSerDetail(String serDetail) {
        this.serDetail = serDetail;
    }

    public String getSerTime() {
        return serTime;
    }

    public void setSerTime(String serTime) {
        this.serTime = serTime;
    }


    @Exclude
    public String getKey() {
        return mKey;
    }
    @Exclude
    public void setKey(String Key) {
        mKey = Key;
    }
}
