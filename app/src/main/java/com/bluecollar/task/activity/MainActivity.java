package com.bluecollar.task.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;


import com.bluecollar.common.logger.Log;
import com.bluecollar.common.logger.LogFragment;
import com.bluecollar.common.logger.LogWrapper;
import com.bluecollar.common.logger.MessageOnlyLogFilter;
import com.bluecollar.task.adapter.GridAdapter;
import com.bluecollar.task.constant.AppConstant;
import com.bluecollar.task.model.Service;
import com.bluecollar.task.model.ServiceProvider;
import com.bluecollar.task.sqllite.DatabaseHandler;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "Utility MainActivity";

    GridView grid;

    public ArrayList<ServiceProvider> serviceprovider = null;

    // Reference to the fragment showing events, so we can clear it with a button
    // as necessary.
    private LogFragment mLogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


       // requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
     /*   getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_main);


        try {
            Log.i(TAG, "Version Utility App: "
                    + this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName + " 2016/March/24");
        } catch (PackageManager.NameNotFoundException e) {

            e.printStackTrace();
        }

        DatabaseHandler db = new DatabaseHandler(this);

        /**
         * CRUD Operations
         * */
        // Inserting Contacts
     /*   Log.d("Insert: ", "Inserting ..");
        db.addService(new Service(1, "Electricial ", "Our electricians are highly skilled and can help you with electric installation,\n" +
                "        removal, repair, and more. We ensure that they are professional, and follow all safety measures"));
        db.addService(new Service(2,"Plumbing", " All your plumbing-related problems will be taken care of.\n" +
                "        Our professionals are experts in fitting, installations, and drainage related issues"));
        db.addService(new Service(3,"AC Mechanical", "– Our services include – AC installation, repair, maintenance, fixing major and minor AC problems and AMCs"));
        db.addService(new Service(4,"Driver On Demand", "Select your pick-up point on and our professional driver will\n" +
                "        reach you in no time."));
        db.addService(new Service(4,"Carpentary", "Our carpenters are trusted, and can deal with all your carpentry issues – repair, renovation, assembling of furniture, and making new furniture"));

        // Reading all contacts
        Log.d("Reading: ", "Reading all Services..");
        service = db.getAllServices();

        for (Service cn : service) {
            String log = "Id: "+cn.getService_id()+" ,Name: " + cn.getServiceType() + " ,Phone: " + cn.getServcieDescription();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }*/

        //ServiceProvider(int id, String name, int mobileNo, String govtid, String photoid, String address, String rating, String description, String descriptionSummary, String category,int serviceid)
        db.addServiceProvider(new ServiceProvider(1, "VIJAY ", 953578,"AHGPV2282A","","No:6a 4th street indra nagar byepass road ambur - 635802","***",
                "wiring,switches,fan,etc..","Our electricians are highly skilled and can help you with electric installation, removal, repair, and more. We ensure that they are professional, and follow all safety measures","Electrician",1));

        db.addServiceProvider(new ServiceProvider(1, "RAJA ", 953578,"AHGPV2282A","","No:6a 4th street indra nagar byepass road ambur - 635802","***",
                "wiring,switches,fan,etc..","Our electricians are highly skilled and can help you with electric installation, removal, repair, and more. We ensure that they are professional, and follow all safety measures","Electrician",1));
        db.addServiceProvider(new ServiceProvider(1, "RAVI", 953578,"AHGPV2282A","","No:6a 4th street indra nagar byepass road ambur - 635802","***",
                "wiring,switches,fan,etc..","Our electricians are highly skilled and can help you with electric installation, removal, repair, and more. We ensure that they are professional, and follow all safety measures","Electrician",1));
        db.addServiceProvider(new ServiceProvider(1, "GANESH", 953578,"AHGPV2282A","","No:6a 4th street indra nagar byepass road ambur - 635802","***",
                "wiring,switches,fan,etc..","Our electricians are highly skilled and can help you with electric installation, removal, repair, and more. We ensure that they are professional, and follow all safety measures","Electrician",1));
       /* db.addService(new Service(2,"Plumbing", " All your plumbing-related problems will be taken care of.\n" +
                "        Our professionals are experts in fitting, installations, and drainage related issues"));
        db.addService(new Service(3,"AC Mechanical", "– Our services include – AC installation, repair, maintenance, fixing major and minor AC problems and AMCs"));
        db.addService(new Service(4,"Driver On Demand", "Select your pick-up point on and our professional driver will\n" +
                "        reach you in no time."));
        db.addService(new Service(4,"Carpentary", "Our carpenters are trusted, and can deal with all your carpentry issues – repair, renovation, assembling of furniture, and making new furniture"));*/

        // Reading all contacts
        Log.e("Reading: ", "Reading all Services..");
        serviceprovider = db.getAllServicesProvider();

        for (ServiceProvider cn : serviceprovider) {
            String log = "Id: "+cn.getAddress()+" ,Name: " + cn.getCategory() + " ,Phone: " + cn.getName();
            // Writing Contacts to log
            Log.e("Name: ", log);
        }

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        GridAdapter adapter = new GridAdapter(MainActivity.this, AppConstant.web, AppConstant.imageId);
        grid=(GridView)findViewById(R.id.gridView);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Toast.makeText(MainActivity.this, "You Clicked at " + AppConstant.web[+position], Toast.LENGTH_SHORT).show();

                Intent intentItem  = new Intent(MainActivity.this,ServiceProviderListActivity.class);
                intentItem.putExtra("servicelist", serviceprovider);
                startActivity(intentItem);

            }
        });


        // TO DO need to add Location
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // Initialize the logging framework.
        initializeLogging();
    }


   /* private ArrayList<s> getData() {
        final ArrayList<Service> imageItems = new ArrayList<>();
        TypedArray imgs = getResources().obtainTypedArray(R.array.image_ids);
        for (int i = 0; i < imgs.length(); i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(i, -1));
            imageItems.add(new Service(bitmap, "Image#" + i));
        }
        return imageItems;
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_register) {

            Intent intentregister  = new Intent(MainActivity.this,RegistrationActivity.class);
            startActivity(intentregister);
            return true;
        }
        if (id == R.id.action_sync) {
            return true;
        }
        if (id == R.id.action_search) {
            Intent intentsearchobj  = new Intent(MainActivity.this,SearchServiceActivity.class);
            startActivity(intentsearchobj);
            return true;
        }

        if (id == R.id.action_request_service) {
            Intent intentrequestserviceobj  = new Intent(MainActivity.this,RequestServiceActivity.class);
            startActivity(intentrequestserviceobj);
            return true;
        }
        if (id == R.id.action_about) {
            Intent intentregister  = new Intent(MainActivity.this,AboutActivity.class);
            startActivity(intentregister);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /** Create a chain of targets that will receive log data */
    public void initializeLogging() {

        // Using Log, front-end to the logging chain, emulates
        // android.util.log method signatures.

        // Wraps Android's native log framework
        LogWrapper logWrapper = new LogWrapper();
        Log.setLogNode(logWrapper);

        // A filter that strips out everything except the message text.
        MessageOnlyLogFilter msgFilter = new MessageOnlyLogFilter();
        logWrapper.setNext(msgFilter);

        // On screen logging via a fragment with a TextView.
        mLogFragment =
                (LogFragment) getSupportFragmentManager().findFragmentById(R.id.log_fragment);
        msgFilter.setNext(mLogFragment.getLogView());
    }


    private class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
           /*try {
                return //loadFromNetwork(urls[0]);
            } catch (IOException e) {
                return getString(R.string.connection_error);
            }*/

            return null;
        }

        /**
         * Uses the logging framework to display the output of the fetch
         * operation in the log fragment.
         */
        @Override
        protected void onPostExecute(String result) {
            Log.i(TAG, result);
        }
    }


}
