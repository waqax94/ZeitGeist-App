package com.zeitgeist.employee.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.zeitgeist.employee.Models.GridListItem;
import com.zeitgeist.employee.Models.IpClass;
import com.zeitgeist.employee.Models.Measurements;
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
public class MeasurementInfoFragment extends Fragment {

    public String customerId;
    public String orderId;
    IpClass ipClass = new IpClass();
    ProgressDialog pDialog;
    ImageView stanceImg,shoulderImg,chestImg,stomachImg,hipImg;
    TextView stance,shoulder,chest,stomach,hipDescp;
    TextView neck,fullChest,fullShoulderWidth,rightSleeve,leftSleeve,bicep,wrist,waistStomach,hip,frontJacketLength,frontChestWidth,
            backWidth,halfShoulderWidthLeft,halfShoulderWidthRight,fullBackLength,halfBackLength,trouserWaist,trouserOutseam,
            trouserInseam,crotch,thigh,knee,rightFullSleeve,leftFullSleeve;
    TableRow neckR,fullChestR,fullShoulderWidthR,rightSleeveR,leftSleeveR,bicepR,wristR,waistStomachR,hipR,frontJacketLengthR,frontChestWidthR,
            backWidthR,halfShoulderWidthLeftR,halfShoulderWidthRightR,fullBackLengthR,halfBackLengthR,trouserWaistR,trouserOutseamR,
            trouserInseamR,crotchR,thighR,kneeR,rightFullSleeveR,leftFullSleeveR;


    public MeasurementInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_measurement_info, container, false);

        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Please Wait...");
        pDialog.setCancelable(false);

        stanceImg = (ImageView) rootView.findViewById(R.id.stance_image);
        shoulderImg = (ImageView) rootView.findViewById(R.id.shoulder_slope_image);
        chestImg = (ImageView) rootView.findViewById(R.id.chest_description_image);
        stomachImg = (ImageView) rootView.findViewById(R.id.stomach_description_image);
        hipImg = (ImageView) rootView.findViewById(R.id.hip_description_image);

        stance = (TextView) rootView.findViewById(R.id.stance_name);
        shoulder = (TextView) rootView.findViewById(R.id.shoulder_slope_name);
        chest = (TextView) rootView.findViewById(R.id.chest_description_name);
        stomach = (TextView) rootView.findViewById(R.id.stomach_description_name);
        hipDescp = (TextView) rootView.findViewById(R.id.hip_description_name);

        neckR = (TableRow) rootView.findViewById(R.id.neck_row);
        fullChestR = (TableRow) rootView.findViewById(R.id.full_chest_row);
        fullShoulderWidthR = (TableRow) rootView.findViewById(R.id.full_shoulder_width_row);
        rightSleeveR = (TableRow) rootView.findViewById(R.id.right_sleeve_row);
        leftSleeveR = (TableRow) rootView.findViewById(R.id.left_sleeve_row);
        bicepR = (TableRow) rootView.findViewById(R.id.bicep_row);
        wristR = (TableRow) rootView.findViewById(R.id.wrist_row);
        waistStomachR = (TableRow) rootView.findViewById(R.id.waist_stomach_row);
        hipR = (TableRow) rootView.findViewById(R.id.hip_row);
        frontJacketLengthR = (TableRow) rootView.findViewById(R.id.front_jacket_length_row);
        frontChestWidthR = (TableRow) rootView.findViewById(R.id.front_chest_width_row);
        backWidthR = (TableRow) rootView.findViewById(R.id.back_width_row);
        halfShoulderWidthLeftR = (TableRow) rootView.findViewById(R.id.half_shoulder_width_left_row);
        halfShoulderWidthRightR = (TableRow) rootView.findViewById(R.id.half_shoulder_width_right_row);
        fullBackLengthR = (TableRow) rootView.findViewById(R.id.full_back_length_row);
        halfBackLengthR = (TableRow) rootView.findViewById(R.id.half_back_length_row);
        trouserWaistR = (TableRow) rootView.findViewById(R.id.trouser_waist_row);
        trouserOutseamR = (TableRow) rootView.findViewById(R.id.trouser_outseam_row);
        trouserInseamR = (TableRow) rootView.findViewById(R.id.trouser_inseam_row);
        crotchR = (TableRow) rootView.findViewById(R.id.crotch_row);
        thighR = (TableRow) rootView.findViewById(R.id.thigh_row);
        kneeR = (TableRow) rootView.findViewById(R.id.knee_row);
        rightFullSleeveR = (TableRow) rootView.findViewById(R.id.right_full_sleeve_row);
        leftFullSleeveR = (TableRow) rootView.findViewById(R.id.left_full_sleeve_row);

        neck = (TextView) rootView.findViewById(R.id.neck_measurement);
        fullChest = (TextView) rootView.findViewById(R.id.full_chest_measurement);
        fullShoulderWidth = (TextView) rootView.findViewById(R.id.full_shoulder_width_measurement);
        rightSleeve = (TextView) rootView.findViewById(R.id.right_sleeve_measurement);
        leftSleeve = (TextView) rootView.findViewById(R.id.left_sleeve_measurement);
        bicep = (TextView) rootView.findViewById(R.id.bicep_measurement);
        wrist = (TextView) rootView.findViewById(R.id.wrist_measurement);
        waistStomach = (TextView) rootView.findViewById(R.id.waist_stomach_measurement);
        hip = (TextView) rootView.findViewById(R.id.hip_measurement);
        frontJacketLength = (TextView) rootView.findViewById(R.id.front_jacket_length_measurement);
        frontChestWidth = (TextView) rootView.findViewById(R.id.front_chest_width_measurement);
        backWidth = (TextView) rootView.findViewById(R.id.back_width_measurement);
        halfShoulderWidthLeft = (TextView) rootView.findViewById(R.id.half_shoulder_width_left_measurement);
        halfShoulderWidthRight = (TextView) rootView.findViewById(R.id.half_shoulder_width_right_measurement);
        fullBackLength = (TextView) rootView.findViewById(R.id.full_back_length_measurement);
        halfBackLength = (TextView) rootView.findViewById(R.id.half_back_length_measurement);
        trouserWaist = (TextView) rootView.findViewById(R.id.trouser_waist_measurement);
        trouserOutseam = (TextView) rootView.findViewById(R.id.trouser_outseam_measurement);
        trouserInseam = (TextView) rootView.findViewById(R.id.trouser_inseam_measurement);
        crotch = (TextView) rootView.findViewById(R.id.crotch_measurement);
        thigh = (TextView) rootView.findViewById(R.id.thigh_measurement);
        knee = (TextView) rootView.findViewById(R.id.knee_measurement);
        rightFullSleeve = (TextView) rootView.findViewById(R.id.right_full_sleeve_measurement);
        leftFullSleeve = (TextView) rootView.findViewById(R.id.left_full_sleeve_measurement);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ipClass.ipAddress).
                        addConverterFactory(GsonConverterFactory.create())
                .build();
        final APIService service = retrofit.create(APIService.class);

        showpDialog();

        Call<Measurements> measurementsInfo = service.ProcessMeasurement(customerId,orderId);

        measurementsInfo.enqueue(new Callback<Measurements>() {
            @Override
            public void onResponse(Response<Measurements> response, Retrofit retrofit) {
                hidepDialog();
                Measurements measurements = response.body();

                if(response.body() != null){
                    Picasso.with(getActivity().getApplicationContext()).load(ipClass.ipAddress + measurements.getStanceImg()).into(stanceImg);
                    stance.setText(measurements.getStance());
                    Picasso.with(getActivity().getApplicationContext()).load(ipClass.ipAddress + measurements.getShoulderImg()).into(shoulderImg);
                    shoulder.setText(measurements.getShoulder());
                    Picasso.with(getActivity().getApplicationContext()).load(ipClass.ipAddress + measurements.getChestImg()).into(chestImg);
                    chest.setText(measurements.getChest());
                    Picasso.with(getActivity().getApplicationContext()).load(ipClass.ipAddress + measurements.getStomachImg()).into(stomachImg);
                    stomach.setText(measurements.getStomach());
                    Picasso.with(getActivity().getApplicationContext()).load(ipClass.ipAddress + measurements.getHipImg()).into(hipImg);
                    hipDescp.setText(measurements.getHip());


                    if(measurements.getNeck().equals("")){
                        neckR.setVisibility(View.GONE);
                    }
                    else{
                        neckR.setVisibility(View.VISIBLE);
                        neck.setText(measurements.getNeck());
                    }
                    if(measurements.getFullChest().equals("")){
                        fullChestR.setVisibility(View.GONE);
                    }
                    else{
                        fullChestR.setVisibility(View.VISIBLE);
                        fullChest.setText(measurements.getFullChest());
                    }
                    if(measurements.getShoulderWidth().equals("")){
                        fullShoulderWidthR.setVisibility(View.GONE);
                    }
                    else{
                        fullShoulderWidthR.setVisibility(View.VISIBLE);
                        fullShoulderWidth.setText(measurements.getShoulderWidth());
                    }
                    if(measurements.getRightSleeve().equals("")){
                        rightSleeveR.setVisibility(View.GONE);
                    }
                    else{
                        rightSleeveR.setVisibility(View.VISIBLE);
                        rightSleeve.setText(measurements.getRightSleeve());
                    }
                    if(measurements.getLeftSleeve().equals("")){
                        leftSleeveR.setVisibility(View.GONE);
                    }
                    else{
                        leftSleeveR.setVisibility(View.VISIBLE);
                        leftSleeve.setText(measurements.getLeftSleeve());
                    }
                    if(measurements.getBicep().equals("")){
                        bicepR.setVisibility(View.GONE);
                    }
                    else{
                        bicepR.setVisibility(View.VISIBLE);
                        bicep.setText(measurements.getBicep());
                    }
                    if(measurements.getWrist().equals("")){
                        wristR.setVisibility(View.GONE);
                    }
                    else{
                        wristR.setVisibility(View.VISIBLE);
                        wrist.setText(measurements.getWrist());
                    }
                    if(measurements.getWaistStomach().equals("")){
                        waistStomachR.setVisibility(View.GONE);
                    }
                    else{
                        waistStomachR.setVisibility(View.VISIBLE);
                        waistStomach.setText(measurements.getWaistStomach());
                    }
                    if(measurements.getHip().equals("")){
                        hipR.setVisibility(View.GONE);
                    }
                    else{
                        hipR.setVisibility(View.VISIBLE);
                        hip.setText(measurements.getHipMeasurement());
                    }
                    if(measurements.getFrontJacketLength().equals("")){
                        frontJacketLengthR.setVisibility(View.GONE);
                    }
                    else{
                        frontJacketLengthR.setVisibility(View.VISIBLE);
                        frontJacketLength.setText(measurements.getFrontJacketLength());
                    }
                    if(measurements.getFrontChestWidth().equals("")){
                        frontChestWidthR.setVisibility(View.GONE);
                    }
                    else{
                        frontChestWidthR.setVisibility(View.VISIBLE);
                        frontChestWidth.setText(measurements.getFrontChestWidth());
                    }
                    if(measurements.getBackWidth().equals("")){
                        backWidthR.setVisibility(View.GONE);
                    }
                    else{
                        backWidthR.setVisibility(View.VISIBLE);
                        backWidth.setText(measurements.getBackWidth());
                    }
                    if(measurements.getHalfShoulderWidthLeft().equals("")){
                        halfShoulderWidthLeftR.setVisibility(View.GONE);
                    }
                    else{
                        halfShoulderWidthLeftR.setVisibility(View.VISIBLE);
                        halfShoulderWidthLeft.setText(measurements.getHalfShoulderWidthLeft());
                    }
                    if(measurements.getHalfShoulderWidthRight().equals("")){
                        halfShoulderWidthRightR.setVisibility(View.GONE);
                    }
                    else{
                        halfShoulderWidthRightR.setVisibility(View.VISIBLE);
                        halfShoulderWidthRight.setText(measurements.getHalfShoulderWidthRight());
                    }
                    if(measurements.getFullBackLength().equals("")){
                        fullBackLengthR.setVisibility(View.GONE);
                    }
                    else{
                        fullBackLengthR.setVisibility(View.VISIBLE);
                        fullBackLength.setText(measurements.getFullBackLength());
                    }
                    if(measurements.getHalfBackLength().equals("")){
                        halfBackLengthR.setVisibility(View.GONE);
                    }
                    else{
                        halfBackLengthR.setVisibility(View.VISIBLE);
                        halfBackLength.setText(measurements.getHalfBackLength());
                    }
                    if(measurements.getTrouserWaist().equals("")){
                        trouserWaistR.setVisibility(View.GONE);
                    }
                    else{
                        trouserWaistR.setVisibility(View.VISIBLE);
                        trouserWaist.setText(measurements.getTrouserWaist());
                    }
                    if(measurements.getTrouserOutseam().equals("")){
                        trouserOutseamR.setVisibility(View.GONE);
                    }
                    else{
                        trouserOutseamR.setVisibility(View.VISIBLE);
                        trouserOutseam.setText(measurements.getTrouserOutseam());
                    }
                    if(measurements.getTrouserInseam().equals("")){
                        trouserInseamR.setVisibility(View.GONE);
                    }
                    else{
                        trouserInseamR.setVisibility(View.VISIBLE);
                        trouserInseam.setText(measurements.getTrouserInseam());
                    }
                    if(measurements.getCrotch().equals("")){
                        crotchR.setVisibility(View.GONE);
                    }
                    else{
                        crotchR.setVisibility(View.VISIBLE);
                        crotch.setText(measurements.getCrotch());
                    }
                    if(measurements.getThigh().equals("")){
                        thighR.setVisibility(View.GONE);
                    }
                    else{
                        thighR.setVisibility(View.VISIBLE);
                        thigh.setText(measurements.getThigh());
                    }
                    if(measurements.getKnee().equals("")){
                        kneeR.setVisibility(View.GONE);
                    }
                    else{
                        kneeR.setVisibility(View.VISIBLE);
                        knee.setText(measurements.getKnee());
                    }
                    if(measurements.getRightFullSleeve().equals("")){
                        rightFullSleeveR.setVisibility(View.GONE);
                    }
                    else{
                        rightFullSleeveR.setVisibility(View.VISIBLE);
                        rightFullSleeve.setText(measurements.getRightFullSleeve());
                    }
                    if(measurements.getLeftFullSleeve().equals("")){
                        leftFullSleeveR.setVisibility(View.GONE);
                    }
                    else{
                        leftFullSleeveR.setVisibility(View.VISIBLE);
                        leftFullSleeve.setText(measurements.getLeftFullSleeve());
                    }

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
