package com.zeitgeist.employee.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.zeitgeist.employee.Models.FabricLiningItem;
import com.zeitgeist.employee.Models.IpClass;
import com.zeitgeist.employee.Models.LiningItem;
import com.zeitgeist.employee.Models.Stylings;
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
public class StylinngInfoFragment extends Fragment {

    public String customerId;
    public String orderId;
    IpClass ipClass = new IpClass();
    ProgressDialog pDialog;
    RelativeLayout fabricLayout,liningLayout;
    TextView fabricNumber,liningNumber,fabricNumberH,liningNumberH;
    ImageView fabricImg,liningImg;
    RelativeLayout jacketStyleGrid,lapelStyleGrid,ventStyleGrid,breastPocketGrid,sidePocketGrid,ticketPocketGrid,cuffButtonStyleGrid,trouserPleatsGrid,
            trouserBackPocketGrid,trouserCuffGrid,trouserLoopTabGrid,waistCoatGrid,waistCoatPocketTypeGrid,waistCoatBackGrid,lapelCurvatureGrid,buttonsGrid,
            lapelEyeletColorGrid,cuffEyeletColorGrid,pipingColorGrid,meltonUndercollarNumberGrid,jacketFitGrid,trouserFitGrid,functionalCuffsGrid,frontPanelRoundnessGrid,
            jacketLengthGrid,lapelPickStitchGrid,shoulderConstructionGrid,lapelSizeGrid;
    TextView jacketStyle,lapelStyle,ventStyle,breastPocket,sidePocket,ticketPocket,cuffButtonStyle,trouserPleats,
            trouserBackPocket,trouserCuff,trouserLoopTab,waistCoat,waistCoatPocketType,waistCoatBack,lapelCurvature,buttons,
            lapelEyeletColor,cuffEyeletColor,pipingColor,meltonUndercollarNumber,jacketFit,trouserFit,functionalCuffs,frontPanelRoundness,
            jacketLength,lapelPickStitch,shoulderConstruction,lapelSize;
    ImageView jacketStyleImg,lapelStyleImg,ventStyleImg,breastPocketImg,sidePocketImg,ticketPocketImg,cuffButtonStyleImg,trouserPleatsImg,
            trouserBackPocketImg,trouserCuffImg,trouserLoopTabImg,waistCoatImg,waistCoatPocketTypeImg,waistCoatBackImg,lapelCurvatureImg,buttonsImg,
            lapelEyeletColorImg,cuffEyeletColorImg,pipingColorImg,meltonUndercollarNumberImg,jacketFitImg,trouserFitImg,functionalCuffsImg,frontPanelRoundnessImg,
            jacketLengthImg,lapelPickStitchImg,shoulderConstructionImg,lapelSizeImg;


    public StylinngInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_stylinng_info, container, false);

        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Please Wait...");
        pDialog.setCancelable(false);

        fabricLayout = (RelativeLayout) rootView.findViewById(R.id.fabric_data_display);
        fabricNumber = (TextView) rootView.findViewById(R.id.fabric_value);
        fabricNumberH = (TextView) rootView.findViewById(R.id.fabric_heading);
        fabricImg = (ImageView) rootView.findViewById(R.id.fabric_img);

        liningLayout = (RelativeLayout) rootView.findViewById(R.id.lining_data_display);
        liningNumber = (TextView) rootView.findViewById(R.id.lining_value);
        liningNumberH = (TextView) rootView.findViewById(R.id.lining_heading);
        liningImg = (ImageView) rootView.findViewById(R.id.lining_img);

        jacketStyleGrid = (RelativeLayout) rootView.findViewById(R.id.jacket_style_grid);
        lapelStyleGrid = (RelativeLayout) rootView.findViewById(R.id.lapel_style_sb_grid);
        lapelCurvatureGrid = (RelativeLayout) rootView.findViewById(R.id.lapel_curvature_grid);
        ventStyleGrid = (RelativeLayout) rootView.findViewById(R.id.vent_style_grid);
        breastPocketGrid = (RelativeLayout) rootView.findViewById(R.id.breast_pocket_grid);
        sidePocketGrid = (RelativeLayout) rootView.findViewById(R.id.side_pocket_grid);
        ticketPocketGrid = (RelativeLayout) rootView.findViewById(R.id.ticket_pocket_grid);
        cuffButtonStyleGrid = (RelativeLayout) rootView.findViewById(R.id.cuff_button_style_grid);
        trouserPleatsGrid = (RelativeLayout) rootView.findViewById(R.id.trouser_pleats_grid);
        trouserBackPocketGrid = (RelativeLayout) rootView.findViewById(R.id.trouser_back_pocket_grid);
        trouserCuffGrid = (RelativeLayout) rootView.findViewById(R.id.trouser_cuff_grid);
        trouserLoopTabGrid = (RelativeLayout) rootView.findViewById(R.id.trouser_loop_tab_grid);
        waistCoatGrid = (RelativeLayout) rootView.findViewById(R.id.waist_coat_grid);
        waistCoatPocketTypeGrid = (RelativeLayout) rootView.findViewById(R.id.waist_coat_pocket_type_grid);
        waistCoatBackGrid = (RelativeLayout) rootView.findViewById(R.id.waist_coat_back_grid);
        jacketLengthGrid = (RelativeLayout) rootView.findViewById(R.id.jacket_length_grid);
        lapelPickStitchGrid = (RelativeLayout) rootView.findViewById(R.id.lapel_pick_stitch_grid);
        shoulderConstructionGrid = (RelativeLayout) rootView.findViewById(R.id.shoulder_construction_grid);
        lapelSizeGrid = (RelativeLayout) rootView.findViewById(R.id.lapel_size_grid);
        buttonsGrid = (RelativeLayout) rootView.findViewById(R.id.buttons_grid);
        lapelEyeletColorGrid = (RelativeLayout) rootView.findViewById(R.id.lapel_eyelet_color_grid);
        cuffEyeletColorGrid = (RelativeLayout) rootView.findViewById(R.id.cuff_eyelet_color_grid);
        pipingColorGrid = (RelativeLayout) rootView.findViewById(R.id.piping_color_grid);
        meltonUndercollarNumberGrid = (RelativeLayout) rootView.findViewById(R.id.melton_undercollar_number_grid);
        jacketFitGrid = (RelativeLayout) rootView.findViewById(R.id.jacket_fit_grid);
        trouserFitGrid = (RelativeLayout) rootView.findViewById(R.id.trouser_fit_grid);
        functionalCuffsGrid = (RelativeLayout) rootView.findViewById(R.id.functional_cuffs_grid);
        frontPanelRoundnessGrid = (RelativeLayout) rootView.findViewById(R.id.front_panel_roundness_grid);


        jacketStyle = (TextView) rootView.findViewById(R.id.jacket_style_name);
        lapelStyle = (TextView) rootView.findViewById(R.id.lapel_style_sb_name);
        lapelCurvature = (TextView) rootView.findViewById(R.id.lapel_curvature_name);
        ventStyle = (TextView) rootView.findViewById(R.id.vent_style_name);
        breastPocket = (TextView) rootView.findViewById(R.id.breast_pocket_name);
        sidePocket = (TextView) rootView.findViewById(R.id.side_pocket_name);
        ticketPocket = (TextView) rootView.findViewById(R.id.ticket_pocket_name);
        cuffButtonStyle = (TextView) rootView.findViewById(R.id.cuff_button_style_name);
        trouserPleats = (TextView) rootView.findViewById(R.id.trouser_pleats_name);
        trouserBackPocket = (TextView) rootView.findViewById(R.id.trouser_back_pocket_name);
        trouserCuff = (TextView) rootView.findViewById(R.id.trouser_cuff_name);
        trouserLoopTab = (TextView) rootView.findViewById(R.id.trouser_loop_tab_name);
        waistCoat = (TextView) rootView.findViewById(R.id.waist_coat_name);
        waistCoatPocketType = (TextView) rootView.findViewById(R.id.waist_coat_pocket_type_name);
        waistCoatBack = (TextView) rootView.findViewById(R.id.waist_coat_back_name);
        jacketLength = (TextView) rootView.findViewById(R.id.jacket_length_name);
        lapelPickStitch = (TextView) rootView.findViewById(R.id.lapel_pick_stitch_name);
        shoulderConstruction = (TextView) rootView.findViewById(R.id.shoulder_construction_name);
        lapelSize = (TextView) rootView.findViewById(R.id.lapel_size_name);
        buttons = (TextView) rootView.findViewById(R.id.buttons_name);
        lapelEyeletColor = (TextView) rootView.findViewById(R.id.lapel_eyelet_color_name);
        cuffEyeletColor = (TextView) rootView.findViewById(R.id.cuff_eyelet_color_name);
        pipingColor = (TextView) rootView.findViewById(R.id.piping_color_name);
        meltonUndercollarNumber = (TextView) rootView.findViewById(R.id.melton_undercollar_number_name);
        jacketFit = (TextView) rootView.findViewById(R.id.jacket_fit_name);
        trouserFit = (TextView) rootView.findViewById(R.id.trouser_fit_name);
        functionalCuffs = (TextView) rootView.findViewById(R.id.functional_cuffs_name);
        frontPanelRoundness = (TextView) rootView.findViewById(R.id.front_panel_roundness_name);


        jacketStyleImg = (ImageView) rootView.findViewById(R.id.jacket_style_image);
        lapelStyleImg = (ImageView) rootView.findViewById(R.id.lapel_style_sb_image);
        lapelCurvatureImg = (ImageView) rootView.findViewById(R.id.lapel_curvature_image);
        ventStyleImg = (ImageView) rootView.findViewById(R.id.vent_style_image);
        breastPocketImg = (ImageView) rootView.findViewById(R.id.breast_pocket_image);
        sidePocketImg = (ImageView) rootView.findViewById(R.id.side_pocket_image);
        ticketPocketImg = (ImageView) rootView.findViewById(R.id.ticket_pocket_image);
        cuffButtonStyleImg = (ImageView) rootView.findViewById(R.id.cuff_button_style_image);
        trouserPleatsImg = (ImageView) rootView.findViewById(R.id.trouser_pleats_image);
        trouserBackPocketImg = (ImageView) rootView.findViewById(R.id.trouser_back_pocket_image);
        trouserCuffImg = (ImageView) rootView.findViewById(R.id.trouser_cuff_image);
        trouserLoopTabImg = (ImageView) rootView.findViewById(R.id.trouser_loop_tab_image);
        waistCoatImg = (ImageView) rootView.findViewById(R.id.waist_coat_image);
        waistCoatPocketTypeImg = (ImageView) rootView.findViewById(R.id.waist_coat_pocket_type_image);
        waistCoatBackImg = (ImageView) rootView.findViewById(R.id.waist_coat_back_image);
        jacketLengthImg = (ImageView) rootView.findViewById(R.id.jacket_length_image);
        lapelPickStitchImg = (ImageView) rootView.findViewById(R.id.lapel_pick_stitch_image);
        shoulderConstructionImg = (ImageView) rootView.findViewById(R.id.shoulder_construction_image);
        lapelSizeImg = (ImageView) rootView.findViewById(R.id.lapel_size_image);
        buttonsImg = (ImageView) rootView.findViewById(R.id.buttons_image);
        lapelEyeletColorImg = (ImageView) rootView.findViewById(R.id.lapel_eyelet_color_image);
        cuffEyeletColorImg = (ImageView) rootView.findViewById(R.id.cuff_eyelet_color_image);
        pipingColorImg = (ImageView) rootView.findViewById(R.id.piping_color_image);
        meltonUndercollarNumberImg = (ImageView) rootView.findViewById(R.id.melton_undercollar_number_image);
        jacketFitImg = (ImageView) rootView.findViewById(R.id.jacket_fit_image);
        trouserFitImg = (ImageView) rootView.findViewById(R.id.trouser_fit_image);
        functionalCuffsImg = (ImageView) rootView.findViewById(R.id.functional_cuffs_image);
        frontPanelRoundnessImg = (ImageView) rootView.findViewById(R.id.front_panel_roundness_image);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ipClass.ipAddress).
                        addConverterFactory(GsonConverterFactory.create())
                .build();
        final APIService service = retrofit.create(APIService.class);

        showpDialog();

        Call<Stylings> stylingsInfo = service.ProcessStyling(customerId,orderId);

        stylingsInfo.enqueue(new Callback<Stylings>() {
            @Override
            public void onResponse(Response<Stylings> response, Retrofit retrofit) {
                hidepDialog();
                Stylings stylings = response.body();

                if(response.body() != null){

                    if(stylings.getFabricNumber().equals("")){
                        fabricLayout.setVisibility(View.GONE);
                        fabricNumberH.setVisibility(View.GONE);
                    }
                    else {
                        fabricLayout.setVisibility(View.VISIBLE);
                        fabricNumberH.setVisibility(View.VISIBLE);

                        if(stylings.getFabricId().equals("0")){

                            Call<String> selfFabricData = service.RetriveCustomFabricNumber(orderId);

                            selfFabricData.enqueue(new Callback<String>() {
                                @Override
                                public void onResponse(Response<String> response, Retrofit retrofit) {
                                    fabricNumber.setText(response.body());
                                }

                                @Override
                                public void onFailure(Throwable t) {

                                }
                            });
                        }
                        else {
                            Call<FabricLiningItem> fabricInfo = service.RetriveFabric(stylings.getFabricId());

                            fabricInfo.enqueue(new Callback<FabricLiningItem>() {
                                @Override
                                public void onResponse(Response<FabricLiningItem> response, Retrofit retrofit) {
                                    if(response.body() != null){
                                        fabricNumber.setText(response.body().getItemNumber());
                                        Picasso.with(getActivity().getApplicationContext()).load(ipClass.ipAddress + response.body().getItemImageSource()).into(fabricImg);
                                    }
                                }

                                @Override
                                public void onFailure(Throwable t) {

                                }
                            });
                        }
                    }

                    if(stylings.getLiningNumber().equals("")){
                        liningLayout.setVisibility(View.GONE);
                        liningNumberH.setVisibility(View.GONE);
                    }
                    else {
                        liningLayout.setVisibility(View.VISIBLE);
                        liningNumberH.setVisibility(View.VISIBLE);

                        Call<LiningItem> liningInfo = service.RetriveLining(stylings.getLiningId());

                        liningInfo.enqueue(new Callback<LiningItem>() {
                            @Override
                            public void onResponse(Response<LiningItem> response, Retrofit retrofit) {
                                if(response.body() != null){
                                    liningNumber.setText(response.body().getItemNumber());
                                    Picasso.with(getActivity().getApplicationContext()).load(ipClass.ipAddress + response.body().getItemImageSource()).into(liningImg);
                                }
                            }

                            @Override
                            public void onFailure(Throwable t) {

                            }
                        });
                    }


                    if(stylings.getJacketStyle().equals("")){
                        jacketStyleGrid.setVisibility(View.GONE);
                    }
                    else {
                        jacketStyleGrid.setVisibility(View.VISIBLE);
                        jacketStyle.setText(stylings.getJacketStyle());
                        Picasso.with(getActivity().getApplicationContext()).load(ipClass.ipAddress + stylings.getJacketStyleImg()).into(jacketStyleImg);
                    }

                    if(stylings.getLapelStyle().equals("")){
                        lapelStyleGrid.setVisibility(View.GONE);
                    }
                    else {
                        lapelStyleGrid.setVisibility(View.VISIBLE);
                        lapelStyle.setText(stylings.getLapelStyle());
                        Picasso.with(getActivity().getApplicationContext()).load(ipClass.ipAddress + stylings.getLapelStyleImg()).into(lapelStyleImg);
                    }

                    if(stylings.getLapelCurvature().equals("")){
                        lapelCurvatureGrid.setVisibility(View.GONE);
                    }
                    else {
                        lapelCurvatureGrid.setVisibility(View.VISIBLE);
                        lapelCurvature.setText(stylings.getLapelCurvature());
                        Picasso.with(getActivity().getApplicationContext()).load(ipClass.ipAddress + stylings.getLapelCurvatureImg()).into(lapelCurvatureImg);
                    }

                    if(stylings.getVentStyle().equals("")){
                        ventStyleGrid.setVisibility(View.GONE);
                    }
                    else {
                        ventStyleGrid.setVisibility(View.VISIBLE);
                        ventStyle.setText(stylings.getVentStyle());
                        Picasso.with(getActivity().getApplicationContext()).load(ipClass.ipAddress + stylings.getVentStyleImg()).into(ventStyleImg);
                    }

                    if(stylings.getBreastPocket().equals("")){
                        breastPocketGrid.setVisibility(View.GONE);
                    }
                    else {
                        breastPocketGrid.setVisibility(View.VISIBLE);
                        breastPocket.setText(stylings.getBreastPocket());
                        Picasso.with(getActivity().getApplicationContext()).load(ipClass.ipAddress + stylings.getBreastPocketImg()).into(breastPocketImg);
                    }

                    if(stylings.getSidePocket().equals("")){
                        sidePocketGrid.setVisibility(View.GONE);
                    }
                    else {
                        sidePocketGrid.setVisibility(View.VISIBLE);
                        sidePocket.setText(stylings.getSidePocket());
                        Picasso.with(getActivity().getApplicationContext()).load(ipClass.ipAddress + stylings.getSidePocketImg()).into(sidePocketImg);
                    }

                    if(stylings.getTicketPocket().equals("")){
                        ticketPocketGrid.setVisibility(View.GONE);
                    }
                    else {
                        ticketPocketGrid.setVisibility(View.VISIBLE);
                        ticketPocket.setText(stylings.getTicketPocket());
                        Picasso.with(getActivity().getApplicationContext()).load(ipClass.ipAddress + stylings.getTicketPocketImg()).into(ticketPocketImg);
                    }

                    if(stylings.getCuffButton().equals("")){
                        cuffButtonStyleGrid.setVisibility(View.GONE);
                    }
                    else {
                        cuffButtonStyleGrid.setVisibility(View.VISIBLE);
                        cuffButtonStyle.setText(stylings.getCuffButton());
                        Picasso.with(getActivity().getApplicationContext()).load(ipClass.ipAddress + stylings.getCuffButtonImg()).into(cuffButtonStyleImg);
                    }

                    if(stylings.getTrouserPleat().equals("")){
                        trouserPleatsGrid.setVisibility(View.GONE);
                    }
                    else {
                        trouserPleatsGrid.setVisibility(View.VISIBLE);
                        trouserPleats.setText(stylings.getTrouserPleat());
                        Picasso.with(getActivity().getApplicationContext()).load(ipClass.ipAddress + stylings.getTrouserPleatImg()).into(trouserPleatsImg);
                    }

                    if(stylings.getTrouserBackPocket().equals("")){
                        trouserBackPocketGrid.setVisibility(View.GONE);
                    }
                    else {
                        trouserBackPocketGrid.setVisibility(View.VISIBLE);
                        trouserBackPocket.setText(stylings.getTrouserBackPocket());
                        Picasso.with(getActivity().getApplicationContext()).load(ipClass.ipAddress + stylings.getTrouserBackPocketImg()).into(trouserBackPocketImg);
                    }

                    if(stylings.getTrouserCuff().equals("")){
                        trouserCuffGrid.setVisibility(View.GONE);
                    }
                    else {
                        trouserCuffGrid.setVisibility(View.VISIBLE);
                        trouserCuff.setText(stylings.getTrouserCuff());
                        Picasso.with(getActivity().getApplicationContext()).load(ipClass.ipAddress + stylings.getTrouserCuffImg()).into(trouserCuffImg);
                    }

                    if(stylings.getTrouserLoopTab().equals("")){
                        trouserLoopTabGrid.setVisibility(View.GONE);
                    }
                    else {
                        trouserLoopTabGrid.setVisibility(View.VISIBLE);
                        trouserLoopTab.setText(stylings.getTrouserLoopTab());
                        Picasso.with(getActivity().getApplicationContext()).load(ipClass.ipAddress + stylings.getTrouserLoopTabImg()).into(trouserLoopTabImg);
                    }

                    if(stylings.getWaistCoatType().equals("")){
                        waistCoatGrid.setVisibility(View.GONE);
                    }
                    else {
                        waistCoatGrid.setVisibility(View.VISIBLE);
                        waistCoat.setText(stylings.getWaistCoatType());
                        Picasso.with(getActivity().getApplicationContext()).load(ipClass.ipAddress + stylings.getWaistCoatTypeImg()).into(waistCoatImg);
                    }

                    if(stylings.getWaistCoatPocketType().equals("")){
                        waistCoatPocketTypeGrid.setVisibility(View.GONE);
                    }
                    else {
                        waistCoatPocketTypeGrid.setVisibility(View.VISIBLE);
                        waistCoatPocketType.setText(stylings.getWaistCoatPocketType());
                        Picasso.with(getActivity().getApplicationContext()).load(ipClass.ipAddress + stylings.getWaistCoatPocketTypeImg()).into(waistCoatPocketTypeImg);
                    }

                    if(stylings.getBack().equals("")){
                        waistCoatBackGrid.setVisibility(View.GONE);
                    }
                    else {
                        waistCoatBackGrid.setVisibility(View.VISIBLE);
                        waistCoatBack.setText(stylings.getBack());
                        Picasso.with(getActivity().getApplicationContext()).load(ipClass.ipAddress + stylings.getBackImg()).into(waistCoatBackImg);
                    }

                    if(stylings.getJacketLength().equals("")){
                        jacketLengthGrid.setVisibility(View.GONE);
                    }
                    else {
                        jacketLengthGrid.setVisibility(View.VISIBLE);
                        jacketLength.setText(stylings.getJacketLength());
                        Picasso.with(getActivity().getApplicationContext()).load(ipClass.ipAddress + stylings.getJacketLengthImg()).into(jacketLengthImg);
                    }

                    if(stylings.getLapelPickStitch().equals("")){
                        lapelPickStitchGrid.setVisibility(View.GONE);
                    }
                    else {
                        lapelPickStitchGrid.setVisibility(View.VISIBLE);
                        lapelPickStitch.setText(stylings.getLapelPickStitch());
                        Picasso.with(getActivity().getApplicationContext()).load(ipClass.ipAddress + stylings.getLapelPickStitchImg()).into(lapelPickStitchImg);
                    }

                    if(stylings.getShoulderConstruction().equals("")){
                        shoulderConstructionGrid.setVisibility(View.GONE);
                    }
                    else {
                        shoulderConstructionGrid.setVisibility(View.VISIBLE);
                        shoulderConstruction.setText(stylings.getShoulderConstruction());
                        Picasso.with(getActivity().getApplicationContext()).load(ipClass.ipAddress + stylings.getShoulderConstructionImg()).into(shoulderConstructionImg);
                    }

                    if(stylings.getLapelSize().equals("")){
                        lapelSizeGrid.setVisibility(View.GONE);
                    }
                    else {
                        lapelSizeGrid.setVisibility(View.VISIBLE);
                        lapelSize.setText(stylings.getLapelSize());
                        Picasso.with(getActivity().getApplicationContext()).load(ipClass.ipAddress + stylings.getLapelSizeImg()).into(lapelSizeImg);
                    }

                    if(stylings.getButtons().equals("")){
                        buttonsGrid.setVisibility(View.GONE);
                    }
                    else {
                        buttonsGrid.setVisibility(View.VISIBLE);
                        buttons.setText(stylings.getButtons());
                        Picasso.with(getActivity().getApplicationContext()).load(ipClass.ipAddress + stylings.getButtonsImg()).into(buttonsImg);
                    }

                    if(stylings.getLapelEyeletColor().equals("")){
                        lapelEyeletColorGrid.setVisibility(View.GONE);
                    }
                    else {
                        lapelEyeletColorGrid.setVisibility(View.VISIBLE);
                        lapelEyeletColor.setText(stylings.getLapelEyeletColor());
                        Picasso.with(getActivity().getApplicationContext()).load(ipClass.ipAddress + stylings.getLapelEyeletColorImg()).into(lapelEyeletColorImg);
                    }

                    if(stylings.getCuffEyeletColor().equals("")){
                        cuffEyeletColorGrid.setVisibility(View.GONE);
                    }
                    else {
                        cuffEyeletColorGrid.setVisibility(View.VISIBLE);
                        cuffEyeletColor.setText(stylings.getCuffEyeletColor());
                        Picasso.with(getActivity().getApplicationContext()).load(ipClass.ipAddress + stylings.getCuffEyeletColorImg()).into(cuffEyeletColorImg);
                    }

                    if(stylings.getPipingColor().equals("")){
                        pipingColorGrid.setVisibility(View.GONE);
                    }
                    else {
                        pipingColorGrid.setVisibility(View.VISIBLE);
                        pipingColor.setText(stylings.getPipingColor());
                        Picasso.with(getActivity().getApplicationContext()).load(ipClass.ipAddress + stylings.getPipingColorImg()).into(pipingColorImg);
                    }

                    if(stylings.getMeltonUndercollarNumber().equals("")){
                        meltonUndercollarNumberGrid.setVisibility(View.GONE);
                    }
                    else {
                        meltonUndercollarNumberGrid.setVisibility(View.VISIBLE);
                        meltonUndercollarNumber.setText(stylings.getMeltonUndercollarNumber());
                        Picasso.with(getActivity().getApplicationContext()).load(ipClass.ipAddress + stylings.getMeltonUndercollarNumberImg()).into(meltonUndercollarNumberImg);
                    }

                    if(stylings.getJacketFit().equals("")){
                        jacketFitGrid.setVisibility(View.GONE);
                    }
                    else {
                        jacketFitGrid.setVisibility(View.VISIBLE);
                        jacketFit.setText(stylings.getJacketFit());
                        Picasso.with(getActivity().getApplicationContext()).load(ipClass.ipAddress + stylings.getJacketFitImg()).into(jacketFitImg);
                    }

                    if(stylings.getTrouserFit().equals("")){
                        trouserFitGrid.setVisibility(View.GONE);
                    }
                    else {
                        trouserFitGrid.setVisibility(View.VISIBLE);
                        trouserFit.setText(stylings.getTrouserFit());
                        Picasso.with(getActivity().getApplicationContext()).load(ipClass.ipAddress + stylings.getTrouserFitImg()).into(trouserFitImg);
                    }

                    if(stylings.getFunctionalCuff().equals("")){
                        functionalCuffsGrid.setVisibility(View.GONE);
                    }
                    else {
                        functionalCuffsGrid.setVisibility(View.VISIBLE);
                        functionalCuffs.setText(stylings.getFunctionalCuff());
                        Picasso.with(getActivity().getApplicationContext()).load(ipClass.ipAddress + stylings.getFunctionalCuffImg()).into(functionalCuffsImg);
                    }

                    if(stylings.getFrontPanelRoundness().equals("")){
                        frontPanelRoundnessGrid.setVisibility(View.GONE);
                    }
                    else {
                        frontPanelRoundnessGrid.setVisibility(View.VISIBLE);
                        frontPanelRoundness.setText(stylings.getFrontPanelRoundness());
                        Picasso.with(getActivity().getApplicationContext()).load(ipClass.ipAddress + stylings.getFrontPanelRoundnessImg()).into(frontPanelRoundnessImg);
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
