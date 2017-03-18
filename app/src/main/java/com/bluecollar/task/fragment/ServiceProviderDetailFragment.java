package com.bluecollar.task.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bluecollar.task.activity.R;
import com.bluecollar.task.activity.ServiceProviderDetailActivity;
import com.bluecollar.task.activity.ServiceProviderListActivity;

/**
 *
 */
public class ServiceProviderDetailFragment extends Fragment {

    public static final String TAG = "ServiceProviderDetailFragment";
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";



    TextView nameviewValue = null;
    TextView govtidValue  = null;
    TextView mobilevalue = null;
    TextView addressvalue = null;
    TextView descriptionValue = null;


    /**
     * The dummy content this fragment is presenting.
     */
    //private ServiceProvider.ServcieProviderInfo mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ServiceProviderDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
         //   mItem = ServiceProvider.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.service_provider_detail_fragment, container, false);




        nameviewValue = ((TextView) rootView.findViewById(R.id.nameval));
         govtidValue  = ((TextView) rootView.findViewById(R.id.id_proof_val));
         mobilevalue = ((TextView) rootView.findViewById(R.id.mobile_val));
         addressvalue = ((TextView) rootView.findViewById(R.id.addres_val));
         descriptionValue = ((TextView) rootView.findViewById(R.id.desc_val));


        nameviewValue.setText("Vijay");
        govtidValue.setText("AHGP2282A");
        mobilevalue.setText("9535703798");
        addressvalue.setText("No :6A 4th street indra nagar byepass road Ambur - 635802");
        descriptionValue.setText("Our electricians are highly skilled and can help you with electric installation,\n" +
                "removal, repair, and more. We ensure that they are professional, and follow all safety measures");

        return rootView;
    }
}
