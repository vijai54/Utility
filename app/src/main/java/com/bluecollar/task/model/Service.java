package com.bluecollar.task.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by vijay.a on 1/18/2016.
 */
public class Service implements Parcelable
{
    int service_id;
    String serviceType;
    String servcieDescription;

    public Service(int service_id, String serviceType, String servcieDescription) {
        this.service_id = service_id;
        this.serviceType = serviceType;
        this.servcieDescription = servcieDescription;
    }

    public Service(Parcel p)
    {
        service_id = p.readInt() ;
        serviceType = p.readString();
        servcieDescription = p.readString();
    }

    public Service() {
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServcieDescription() {
        return servcieDescription;
    }

    public void setServcieDescription(String servcieDescription) {
        this.servcieDescription = servcieDescription;
    }

    @Override
    public int describeContents()
        {
            return hashCode();
        }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(service_id);
        dest.writeString(serviceType);
        dest.writeString(servcieDescription);

    }

    // We need to add a Creator
    public static final Parcelable.Creator<Service> CREATOR = new Parcelable.Creator<Service>()
    {
        @Override
        public Service createFromParcel(Parcel parcel)
        {
            return new Service(parcel);
        }
        @Override
        public Service[] newArray(int size)
        {
            return new Service[size];
        } };
}