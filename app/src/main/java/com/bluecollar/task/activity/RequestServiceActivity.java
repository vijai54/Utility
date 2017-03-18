package com.bluecollar.task.activity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bluecollar.common.logger.Log;
import com.bluecollar.task.json.JSONPayload;
import com.bluecollar.task.model.Service;
import com.bluecollar.task.sqllite.DatabaseHandler;
import com.bluecollar.task.widget.MultiSelectionSpinner;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class RequestServiceActivity extends AppCompatActivity {


    public static final String TAG = "RequestServiceActivity";

    private UserLoginTask mAuthTask = null;

    private AutoCompleteTextView mNameView;
    private EditText servicesummaryView;
    private EditText mobile;
    private EditText address;

    MultiSelectionSpinner spinner = null;
    Button requestButton = null;


    public static List<Service> service = null;

    public static List<String> serviceStr = null;

    JSONPayload payloadPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_request_service);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        serviceStr = new ArrayList<String>();
        /****************************************
         *  service access
         */
        DatabaseHandler db = new DatabaseHandler(this);

        /**
         * CRUD Operations
         * */
        // Inserting Contacts
        // Log.d("Insert: ", "Inserting ..");
        db.addService(new Service(1, "Electricial ", "Our electricians are highly skilled and can help you with electric installation,\n" +
                "        removal, repair, and more. We ensure that they are professional, and follow all safety measures"));
        db.addService(new Service(2,"Plumbing", " All your plumbing-related problems will be taken care of.\n" +
                "        Our professionals are experts in fitting, installations, and drainage related issues"));
        db.addService(new Service(3,"AC Mechanical", "– Our services include – AC installation, repair, maintenance, fixing major and minor AC problems and AMCs"));
        db.addService(new Service(4,"Driver On Demand", "Select your pick-up point on and our professional driver will\n" +
                "        reach you in no time."));
        db.addService(new Service(4,"Carpentary", "Our carpenters are trusted, and can deal with all your carpentry issues – repair, renovation, assembling of furniture, and making new furniture"));

        // Reading all contacts
        //   Log.d("Reading: ", "Reading all Services..");
        service = db.getAllServices();

        for (Service cn : service) {
            //String log = "Id: "+cn.getService_id()+" ,Name: " + cn.getServiceType() + " ,Phone: " + cn.getServcieDescription();
            // Writing Contacts to log
            //     Log.d("Name: ", log);
            serviceStr.add(cn.getServiceType());
        }



        spinner = (MultiSelectionSpinner) findViewById(R.id.request_spinner_services);
        spinner.setItems(serviceStr);
        mNameView = (AutoCompleteTextView) findViewById(R.id.request_name);


        servicesummaryView = (EditText) findViewById(R.id.service_summary);

        mobile  = (EditText) findViewById(R.id.request_mobile);
        address = (EditText) findViewById(R.id.request_address);

        servicesummaryView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        requestButton  = (Button) findViewById(R.id.request_button);
        requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });


    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.

    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    public void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mNameView.setError(null);
        servicesummaryView.setError(null);
        mobile.setError(null);
        address.setError(null);

        // Store values at the time of the login attempt.
        String nameStr = mNameView.getText().toString();
        String summaryStr = servicesummaryView.getText().toString();
        String mobileStr = mobile.getText().toString();
        String addressStr = address.getText().toString();
        String servicesSelectedStr = spinner.getSelectedItemsAsString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(summaryStr) && !isSummaryValid(summaryStr)) {
            servicesummaryView.setError(getString(R.string.error_invalid_summary));
            focusView = servicesummaryView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(nameStr) ) {
            mNameView.setError(getString(R.string.error_field_required));

            // servicesummaryView.setError(getString(R.string.error_field_required));
            // mobile.setError(getString(R.string.error_field_required));
            focusView = mNameView;
            cancel = true;
        } else if (!ismobileValid( mobileStr)) {
            mobile.setError(getString(R.string.error_invalid_mobile));
            focusView = mobile;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.

            mAuthTask = new UserLoginTask(nameStr,mobileStr, addressStr ,summaryStr,servicesSelectedStr);
            mAuthTask.execute((Void) null);
        }
    }

    private boolean ismobileValid(String mobileNo) {

        //String destination="  + + ++  919090909090";
        mobileNo.trim();

        mobileNo = mobileNo.replaceAll("\\s","");
        while(mobileNo.startsWith("+"))
        {
            mobileNo = new String(mobileNo.toString().substring(1));
        }
        if (mobileNo.matches("^\\s*\\d+\\s*$")) {

            //System.out.println("match");
            if (mobileNo.length() == 10 || mobileNo.length() == 12) {
                //  System.out.println("lenght correct ");
                if ((mobileNo != null)) {
                    if (mobileNo.matches("9{1}1{1}[7-8-9]{1}[0-9]{9}")) {
                        // System.out.println(" correct number");
                        return true;

                    } else if (mobileNo.matches("[7-8-9]{1}[0-9]{9}")) {

                        // System.out.println("correct num");
                        return true;
                    } else {
                        // System.out.println("not correct ");
                        return false;
                    }
                } else {
                    //System.out.println("null ");
                }


            }else
            {
                // System.out.println("lenght not correct !");
                return false;
            }
        }
        else
        {
            //System.out.println("Char not allowed !");
            return false;
        }
        return false;
    }

    private boolean isSummaryValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 10;
    }

    /**
     * Represents an asynchronous registration task
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mName;
        private final String mMobile;

        private final String mAddress;

        private final String mSummary;
        private final String mServiceSelected;




        UserLoginTask(String name, String mobile,String address ,String summary,String servicesSelected) {
            mName = name;
            mMobile = mobile;
            mAddress = address;
            mSummary = summary;
            mServiceSelected = servicesSelected;

          /*  Log.d("mName: ", name);
            Log.d("mMobile: ", mobile);
            Log.d("mGovtId: ", govidId);
            Log.d("mAddress: ", address);
            Log.d("mSummary: ", summary);
            Log.d("mServiceSelected: ", servicesSelected);*/



        }

        @Override
        protected Boolean doInBackground(Void... params) {


            String payload = payloadPage.getServiceRequestPayload(mName, mSummary, mMobile, mAddress, mServiceSelected);
            try {
                // Simulate network access.
             //   Log.i("Loginpayload", payload);
                //  response = HttpHandler.sendPostRequest(AppConstant.URL , payload);
                //   loginResponse = EntityUtils.toString(response.getEntity());
                //   Log.i("AppConstants.URL+loginUrl", AppConstants.URL + loginUrl);
                //    Log.i("response", loginResponse);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }



            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
           // showProgress(false);

            if (success) {
                finish();
            } else {
                servicesummaryView.setError(getString(R.string.error_incorrect_password));
                servicesummaryView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
        //    showProgress(false);
        }
    }


}
