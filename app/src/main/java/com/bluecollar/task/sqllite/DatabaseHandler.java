package com.bluecollar.task.sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteOpenHelper;

import com.bluecollar.task.model.Service;
import com.bluecollar.task.model.ServiceProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vijay.a on 1/18/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {


    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "utility";

    // Services table name
    private static final String TABLE_SERVICES = "services";

    // Services table name
    private static final String TABLE_SERVICES_PROVIDER = "serviceprovider";

    // Services Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_SERVICE = "service";
    private static final String KEY_SERVICE_DESC = "service_description";


    // Serviceprovider Table Columns names
    private static final String KEY_SERVICE_PROVIDER_ID = "service_provider_id";
    private static final String KEY_SERVICE_PROVIDER_NAME = "service_provider_name";
    private static final String KEY_SERVICE_PROVIDER_GOVT_ID = "service_provider_govt_id";
    private static final String KEY_SERVICE_PROVIDER_MOBILE = "service_provider_mobile";
    private static final String KEY_SERVICE_PROVIDER_PHOTO_ID = "service_provider_photo_id";
    private static final String KEY_SERVICE_PROVIDER_ADDRESS = "service_provider_address";
    private static final String KEY_SERVICE_PROVIDER_RATING = "service_provider_rating";
    private static final String KEY_SERVICE_PROVIDER_CATEGORY = "service_provider_category";
    private static final String KEY_SERVICE_PROVIDER_DESCRIPTION = "service_provider_description";
    private static final String KEY_SERVICE_PROVIDER_DESCRIPTION_SUMMARY = "service_provider_description_summary";
    private static final String KEY_SERVICE_PROVIDER_SERVICE_ID = "service_provider_service_id";

    String CREATE_SERVICES_TABLE = "CREATE TABLE " + TABLE_SERVICES + "("
            + KEY_ID + " INTEGER PRIMARY KEY," + KEY_SERVICE + " TEXT,"
            + KEY_SERVICE_DESC + " TEXT" + ")";


    String CREATE_SERVICES_PROVIDER_TABLE = "CREATE TABLE " + TABLE_SERVICES_PROVIDER + "("
            + KEY_SERVICE_PROVIDER_ID + " INTEGER PRIMARY KEY," + KEY_SERVICE_PROVIDER_NAME + " TEXT,"
            + KEY_SERVICE_PROVIDER_GOVT_ID + " TEXT,"+ KEY_SERVICE_PROVIDER_MOBILE + " INT,"
            + KEY_SERVICE_PROVIDER_PHOTO_ID + " TEXT,"+ KEY_SERVICE_PROVIDER_ADDRESS + " TEXT,"
            + KEY_SERVICE_PROVIDER_RATING + " INT,"+ KEY_SERVICE_PROVIDER_CATEGORY + " TEXT,"
            + KEY_SERVICE_PROVIDER_DESCRIPTION_SUMMARY + " TEXT,"
            + KEY_SERVICE_PROVIDER_DESCRIPTION + " TEXT" +KEY_SERVICE_PROVIDER_DESCRIPTION + " TEXT,"+ KEY_SERVICE_PROVIDER_SERVICE_ID + " INT" + ")";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_SERVICES_TABLE);
        db.execSQL(CREATE_SERVICES_PROVIDER_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICES_PROVIDER);

        // Create tables again
        onCreate(db);

    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new Service
  public void addService(Service service) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SERVICE, service.getServiceType()); // Contact Name
        values.put(KEY_SERVICE_DESC, service.getServcieDescription()); // Contact Phone

        // Inserting Row
        db.insert(TABLE_SERVICES, null, values);
        db.close(); // Closing database connection
    }

    // Getting single service
  public  Service getService(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_SERVICES, new String[] { KEY_ID,
                        KEY_SERVICE, KEY_SERVICE_DESC }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Service service = new Service(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return service
        return service;
    }

    // Getting All Services
    public List<Service> getAllServices() {
        List<Service> serviceList = new ArrayList<Service>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SERVICES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Service service = new Service();
                service.setService_id(Integer.parseInt(cursor.getString(0)));
                service.setServiceType(cursor.getString(1));
                service.setServcieDescription(cursor.getString(2));
                // Adding contact to list
                serviceList.add(service);
            } while (cursor.moveToNext());
        }

        // return service list
        return serviceList;
    }

    // Updating single service
    public int updateServices(Service service) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SERVICE, service.getServiceType());
        values.put(KEY_SERVICE_DESC, service.getServcieDescription());

        // updating row
        return db.update(TABLE_SERVICES, values, KEY_ID + " = ?",
                new String[] { String.valueOf(service.getService_id()) });
    }

    // Deleting single Service
    public void deleteService(Service service) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SERVICES, KEY_ID + " = ?",
                new String[]{String.valueOf(service.getService_id())});
        db.close();
    }


    // Getting Services Count
    public int getServicesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_SERVICES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }


    /////////CRUD OPERATIONS FOR SERVICE PROVIDER


    // Adding new Service
    public void addServiceProvider(ServiceProvider provider) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SERVICE_PROVIDER_NAME, provider.getName()); // provider Name
        values.put(KEY_SERVICE_PROVIDER_GOVT_ID, provider.getGovtid()); // provider govt id
        values.put(KEY_SERVICE_PROVIDER_MOBILE, provider.getMobileNo()); // provider phone
        values.put(KEY_SERVICE_PROVIDER_PHOTO_ID, provider.getPhotoid()); // provider photo url
        values.put(KEY_SERVICE_PROVIDER_ADDRESS, provider.getAddress()); // provider address
        values.put(KEY_SERVICE_PROVIDER_CATEGORY, provider.getCategory()); // provider category
        values.put(KEY_SERVICE_PROVIDER_DESCRIPTION, provider.getDescription()); // provider decription
        values.put(KEY_SERVICE_PROVIDER_DESCRIPTION_SUMMARY, provider.getDescriptionSummary()); // provider summary
        values.put(KEY_SERVICE_PROVIDER_SERVICE_ID, provider.getServiceid()); // provider service id
        values.put(KEY_SERVICE_PROVIDER_RATING, provider.getRating()); // provider rating



        // Inserting Row
        db.insert(TABLE_SERVICES_PROVIDER, null, values);
        db.close(); // Closing database connection
    }

    // Getting single service Provider
    public  ServiceProvider getServiceProvider(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_SERVICES_PROVIDER, new String[] { KEY_SERVICE_PROVIDER_ID,
                        KEY_SERVICE, KEY_SERVICE_DESC }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        ServiceProvider service = new ServiceProvider(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),   Integer.parseInt(cursor.getString(2)),cursor.getString(3),
                //Integer.parseInt(cursor.getString(3)),
        cursor.getString(4),
       cursor.getString(5),
        cursor.getString(6),
        cursor.getString(7),
       cursor.getString(8),
       cursor.getString(9),
       Integer.parseInt(cursor.getString(10)));
        // return Service Provider
        return service;
    }

    // Getting All Services
    public ArrayList<ServiceProvider> getAllServicesProvider() {
        ArrayList<ServiceProvider> serviceList = new ArrayList<ServiceProvider>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SERVICES_PROVIDER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ServiceProvider provider = new ServiceProvider();
                provider.setId(Integer.parseInt(cursor.getString(0)));
                provider.setName(cursor.getString(1));
                provider.setGovtid(cursor.getString(2));
                provider.setMobileNo(Integer.parseInt(cursor.getString(3)));
                provider.setPhotoid(cursor.getString(4));
                provider.setAddress(cursor.getString(5));
                provider.setRating(cursor.getString(6));
                provider.setCategory(cursor.getString(7));
                provider.setDescription(cursor.getString(8));
                provider.setDescriptionSummary(cursor.getString(9));
                provider.setServiceid(Integer.parseInt(cursor.getString(10)));

                // Adding providers to list
                serviceList.add(provider);
            } while (cursor.moveToNext());
        }

        // return contact list
        return serviceList;
    }

    // Updating single Service Provider
    public int updateServiceProvider(ServiceProvider provider) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SERVICE_PROVIDER_NAME, provider.getName());
        values.put(KEY_SERVICE_PROVIDER_GOVT_ID, provider.getGovtid());
        values.put(KEY_SERVICE_PROVIDER_MOBILE, provider.getMobileNo());
        values.put(KEY_SERVICE_PROVIDER_PHOTO_ID, provider.getPhotoid());
        values.put(KEY_SERVICE_PROVIDER_ADDRESS, provider.getAddress());
        values.put(KEY_SERVICE_PROVIDER_RATING, provider.getRating());
        values.put(KEY_SERVICE_PROVIDER_CATEGORY, provider.getCategory());
        values.put(KEY_SERVICE_PROVIDER_DESCRIPTION, provider.getDescription());
        values.put(KEY_SERVICE_PROVIDER_DESCRIPTION_SUMMARY, provider.getDescriptionSummary());
        values.put(KEY_SERVICE_PROVIDER_SERVICE_ID, provider.getServiceid());

        // updating row
        return db.update(TABLE_SERVICES_PROVIDER, values, KEY_SERVICE_PROVIDER_ID + " = ?",
                new String[] { String.valueOf(provider.getId()) });
    }

    // Deleting single Service Provider
    public void deleteServiceProvider(ServiceProvider provider) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SERVICES_PROVIDER, KEY_SERVICE_PROVIDER_ID + " = ?",
                new String[] { String.valueOf(provider)});
        db.close();
    }


    // Getting Services Provider Count
    public int getServicesProviderCount() {
        String countQuery = "SELECT  * FROM " + TABLE_SERVICES_PROVIDER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
}
