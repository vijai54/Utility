package com.bluecollar.task.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bluecollar.task.activity.R;
import com.bluecollar.task.adapter.ServiceAdapter;
import com.bluecollar.task.fragment.ServiceProviderDetailFragment;
import com.bluecollar.task.model.ServiceProvider;

import java.util.ArrayList;

/**
 *
 */
public class ServiceProviderListFragment extends android.support.v4.app.Fragment implements AdapterView.OnItemClickListener {

    public static final String TAG = "ServiceProviderListFragment";

    /**
     * The serialization (saved instance state) Bundle key representing the
     * activated item position. Only used on tablets.
     */
    private static final String STATE_ACTIVATED_POSITION = "activated_position";

    /**
     * The fragment's current callback object, which is notified of list item
     * clicks.
     */
    private Callbacks mCallbacks = sDummyCallbacks;

    /**
     * The current activated item position. Only used on tablets.
     */
    private int mActivatedPosition = ListView.INVALID_POSITION;



    /**
     * A callback interface that all activities containing this fragment must
     * implement. This mechanism allows activities to be notified of item
     * selections.
     */
    public interface Callbacks {
        /**
         * Callback for when an item has been selected.
         */
        public void onItemSelected(String id);
    }

    /**
     * A dummy implementation of the {@link Callbacks} interface that does
     * nothing. Used only when this fragment is not attached to an activity.
     */
    private static Callbacks sDummyCallbacks = new Callbacks() {
        @Override
        public void onItemSelected(String id) {
        }
    };

    ListView listView = null;
    ArrayList<ServiceProvider> serviceproviderArrayList = new ArrayList<ServiceProvider>();
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ServiceProviderListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: replace with a real list adapter.
      /*  setListAdapter(new ArrayAdapter<Service>(
                getActivity(),
                android.R.layout.simple_list_item_activated_1,
                android.R.id.text1,
                MainActivity.service));*/

      /*  DatabaseHandler db = new DatabaseHandler(  getActivity());

        List<ServiceProvider> provider = db.getAllServicesProvider();

        for (ServiceProvider cn : provider) {
            String log = "Id: "+cn.getAddress()+" ,Name: " + cn.getCategory() + " ,Phone: " + cn.getName();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }*/

        if(serviceproviderArrayList != null &&  serviceproviderArrayList.size() ==0)
        {
            Bundle bundle = getActivity().getIntent().getExtras();
            if(bundle != null)
            serviceproviderArrayList = bundle.getParcelableArrayList("servicelist");
        }





       // setListAdapter(new ServiceAdapter( getActivity().getApplicationContext()));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.service_provider_fragment, container, false);
        listView = ((ListView) rootView.findViewById(R.id.listView));

        listView.setAdapter(new ServiceAdapter( getActivity().getApplicationContext(),serviceproviderArrayList));
        listView.setOnItemClickListener(this);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Restore the previously serialized activated item position.
        if (savedInstanceState != null
                && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
            setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION));
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // Activities containing this fragment must implement its callbacks.
        if (!(activity instanceof Callbacks)) {
            throw new IllegalStateException("Activity must implement fragment's callbacks.");
        }

        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        // Reset the active callbacks interface to the dummy implementation.
        mCallbacks = sDummyCallbacks;
    }

 /*@Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);

        // Notify the active callbacks interface (the activity, if the
        // fragment is attached to one) that an item has been selected.
        mCallbacks.onItemSelected("1");
    }*/

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mActivatedPosition != ListView.INVALID_POSITION) {
            // Serialize and persist the activated item position.
            outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
        }
    }



    public void setActivateOnItemClick(boolean activateOnItemClick) {
        // When setting CHOICE_MODE_SINGLE, ListView will automatically
        // give items the 'activated' state when touched.
        listView.setChoiceMode(activateOnItemClick
                ? ListView.CHOICE_MODE_SINGLE
                : ListView.CHOICE_MODE_NONE);
    }

    private void setActivatedPosition(int position) {
        if (position == ListView.INVALID_POSITION) {
            listView.setItemChecked(mActivatedPosition, false);
        } else {
            listView.setItemChecked(position, true);
        }

        mActivatedPosition = position;
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mCallbacks.onItemSelected("1");
    }
}
