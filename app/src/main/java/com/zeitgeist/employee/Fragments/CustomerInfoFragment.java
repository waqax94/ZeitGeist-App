package com.zeitgeist.employee.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zeitgeist.employee.Models.Customer;
import com.zeitgeist.employee.Models.IpClass;
import com.zeitgeist.employee.R;
import com.zeitgeist.employee.Services.APIService;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomerInfoFragment extends Fragment {

    public String customerId;
    public String orderId;
    IpClass ipClass = new IpClass();
    ProgressDialog pDialog;
    TextView cId,cName,cPhone,cEmail,cCity,cAddress;


    public CustomerInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_customer_info, container, false);

        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Please Wait...");
        pDialog.setCancelable(false);

        cId = (TextView) rootView.findViewById(R.id.customer_id_value);
        cName = (TextView) rootView.findViewById(R.id.customer_name_value);
        cPhone = (TextView) rootView.findViewById(R.id.customer_phone_value);
        cEmail = (TextView) rootView.findViewById(R.id.customer_email_value);
        cCity = (TextView) rootView.findViewById(R.id.customer_city_value);
        cAddress = (TextView) rootView.findViewById(R.id.customer_address_value);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ipClass.ipAddress).
                        addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService service = retrofit.create(APIService.class);

        showpDialog();

        Call<Customer> customerInfo = service.SearchCustomerById(customerId);

        customerInfo.enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Response<Customer> response, Retrofit retrofit) {

                hidepDialog();

                Customer customer = response.body();

                if(response.body() != null){
                    cId.setText(customer.getCustomerId());
                    cName.setText(customer.getCustomerName());
                    cPhone.setText(customer.getCustomerPrimaryPhone());
                    cEmail.setText(customer.getCustomerEmail());
                    cCity.setText(customer.getCustomerCity());
                    cAddress.setText(customer.getCustomerAddress());

                }
            }

            @Override
            public void onFailure(Throwable t) {
                hidepDialog();
                Toast.makeText(getActivity().getApplicationContext(),"Connection Error",Toast.LENGTH_LONG).show();
            }
        });

        return rootView;
    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }
    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

}
