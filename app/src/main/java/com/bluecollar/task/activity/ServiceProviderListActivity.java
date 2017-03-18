package com.bluecollar.task.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;

import com.bluecollar.task.fragment.ServiceProviderDetailFragment;
import com.bluecollar.task.fragment.ServiceProviderListFragment;
import com.bluecollar.task.model.ServiceProvider;


/**
 *
 */
public class ServiceProviderListActivity extends AppCompatActivity  implements ServiceProviderListFragment.Callbacks {

    public static final String TAG = "ServiceProviderListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.service_provider_activity);

       Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // Show the Up button in the action bar.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // get action bar
        //ActionBar actionBar = getActionBar();

        // Enabling Up / Back navigation
        //actionBar.setDisplayHomeAsUpEnabled(true);


        /*    ((ServiceProviderListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.service_list_fragment))
                    .setActivateOnItemClick(true);*/

       // ServiceProvider serviceproviderArrayList = getIntent().getParcelableExtra("servicelist");
        if (savedInstanceState == null) {

            ServiceProviderListFragment fragment = new ServiceProviderListFragment();
            //  fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.service_list_fragment, fragment)
                    .commit();
        }



    }

    /**
     * Callback method from {@link ServiceProviderListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(String id) {

            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, ServiceProviderDetailActivity.class);
            detailIntent.putExtra(ServiceProviderDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, MainActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
