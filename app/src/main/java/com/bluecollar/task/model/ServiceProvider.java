package com.bluecollar.task.model;


import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by vijay.a on 1/18/2016.
 */

public class ServiceProvider implements Parcelable {

        public int id;
        public String name;
        public int mobileNo;
        public String govtid;
        public String photoid;
        public String address;
        public String rating;
        public String description;
        public String descriptionSummary;
        public String category;
        public int serviceid;


        public ServiceProvider(int id, String name, int mobileNo, String govtid, String photoid, String address, String rating, String description, String descriptionSummary, String category,int serviceid) {
            this.id = id;
            this.name = name;
            this.mobileNo = mobileNo;
            this.govtid = govtid;
            this.photoid = photoid;
            this.address = address;
            this.rating = rating;
            this.description = description;
            this.descriptionSummary = descriptionSummary;
            this.category = category;
            this.serviceid = serviceid;
        }

    public ServiceProvider()
    {

    }

    public ServiceProvider(Parcel p)
    {
        id = p.readInt() ;
        name = p.readString();
        mobileNo = p.readInt();
        govtid = p.readString();;
        photoid = p.readString();;
        address = p.readString();;
        rating = p.readString();;
        description = p.readString();;
        descriptionSummary = p.readString();
        this.category = p.readString();;
        this.serviceid = p.readInt();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getServiceid() {
        return serviceid;
    }

    public void setServiceid(int serviceid) {
        this.serviceid = serviceid;
    }
        public String getDescriptionSummary() {
            return descriptionSummary;
        }

        public void setDescriptionSummary(String descriptionSummary) {
            this.descriptionSummary = descriptionSummary;
        }

        public String getGovtid() {
            return govtid;
        }

        public void setGovtid(String govtid) {
            this.govtid = govtid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setMobileNo(int mobileNo) {
            this.mobileNo = mobileNo;
        }

        public void setPhotoid(String photoid) {
            this.photoid = photoid;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public int getMobileNo() {
            return mobileNo;
        }

        public String getPhotoid() {
            return photoid;
        }

        public String getAddress() {
            return address;
        }

        public String getRating() {
            return rating;
        }

        public String getDescription() {
            return description;
        }

        public String getCategory() {
            return category;
        }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(mobileNo);
        dest.writeString(govtid);
        dest.writeString(photoid);
        dest.writeString(address);
        dest.writeString(rating);
        dest.writeString(description);
        dest.writeString(descriptionSummary);
        dest.writeString(category);
        dest.writeInt(serviceid);
    }

    // We need to add a Creator
    public static final Parcelable.Creator<ServiceProvider> CREATOR = new Parcelable.Creator<ServiceProvider>()
    {
        @Override
        public ServiceProvider createFromParcel(Parcel parcel)
        {
            return new ServiceProvider(parcel);
        }
        @Override
        public ServiceProvider[] newArray(int size)
        {
            return new ServiceProvider[size];
        } };
    }