package com.zeitgeist.employee.Activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.zeitgeist.employee.Adapters.FabricLiningAdapter;
import com.zeitgeist.employee.Adapters.LiningAdapter;
import com.zeitgeist.employee.Adapters.SearchTypeAdapter;
import com.zeitgeist.employee.Adapters.StylingAdapter;
import com.zeitgeist.employee.Models.Consumption;
import com.zeitgeist.employee.Models.Customer;
import com.zeitgeist.employee.Models.FabricLiningItem;
import com.zeitgeist.employee.Models.GridListItem;
import com.zeitgeist.employee.Models.IpClass;
import com.zeitgeist.employee.Models.LiningItem;
import com.zeitgeist.employee.Models.Measurements;
import com.zeitgeist.employee.Models.OrderDetail;
import com.zeitgeist.employee.Models.ReplaceFont;
import com.zeitgeist.employee.Models.Stylings;
import com.zeitgeist.employee.Models.ZeitGeist;
import com.zeitgeist.employee.R;
import com.zeitgeist.employee.Services.APIService;
import com.zeitgeist.employee.Services.GridViewScrollable;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class OrderTakingPant extends AppCompatActivity {

    String orderCondition;

    IpClass ipClass = new IpClass();

    String shopId,itemType,orderId,fabricConnsumption,liningConsumption,garmentId = "5";
    TextView title;
    ImageButton backButton;
    EditText name,contactPrimaryTwo,contactPrimarySeven,contactSecondaryTwo,contactSecondarySeven,email,address;
    ImageButton addSecondContact;
    Button customerInfoBtn,fabricBtn,measurementBtn,suitStylingBtn,invoiceBtn;
    Button nextBtnC,dateBtn;
    Toolbar toolbar;
    Typeface typeface;
    RelativeLayout customerInfoLayout,fabricLayout,measurementLayout,suitStylingLayout,invoiceLayout;
    RelativeLayout secondaryContactLayout;
    Spinner orderType,city,baseSize,basePattern,fabricChoice;
    List<String> fabricChoices = new ArrayList<String>();
    List<String> orderTypes = new ArrayList<String>();
    ArrayList<String> cities = new ArrayList<String>();
    ArrayList<String> baseSizes = new ArrayList<String>();
    ArrayList<String> basePatterns = new ArrayList<String>();
    TextView baseSizeText,basePatternText;
    String customerId,nameValue,contactPrimaryValue,contactSecondaryValue,emailValue,addressValue,fabricChoiceValue,expectedDateValue = "",
            orderTypeValue,cityValue,basePatternValue,baseSizeValue,previousPrimaryContact,previousEmail;
    boolean uniquePhoneNumber = true,uniqueEmail = true,validPhoneNumber = true,validSecondaryPhone = true;
    int cityIndex,fabricChoiceIndex,orderTypeIndex,basePatternIndex,baseSizeIndex;


    TextView fabricText;
    GridView fabricStyle;
    RelativeLayout container;
    FabricLiningAdapter fabricStyleAdapter;
    Button nextBtnF,clearFabricSelection;
    SearchView fabricSearch;
    List<FabricLiningItem> fabricList,fabricListSearchable;
    String fabricIdValue,fabricNumberValue,previousFabricId;
    int fabricPrice;
    ImageView expandedImageView;


    List<GridListItem> stanceList,hipDescriptionList;
    GridViewScrollable stance,hipDescription;
    StylingAdapter stanceAdapter,hipDescriptionAdapter;
    EditText hip,fullBackLength,trouserWaist,trouserOutseam,trouserInseam,thigh,knee;
    ImageView hipImage,fullBackLengthImage,trouserWaistImage,trouserOutseamImage,trouserInseamImage,thighImage,kneeImage;
    ImageButton hipZoomIcon,fullBackLengthZoomIcon,trouserWaistZoomIcon,trouserOutseamZoomIcon,trouserInseamZoomIcon,thighZoomIcon,kneeZoomIcon;
    Button nextBtnM;
    Button hipSignBtn,fullBackLengthSignBtn,trouserWaistSignBtn,trouserOutseamSignBtn,trouserInseamSignBtn,thighSignBtn,kneeSignBtn;
    String  measurementId,stanceId,hipDescriptionId,stanceValue,stanceImgValue,hipDescriptionValue,hipDescriptionImgValue,hipValue,fullBackLengthValue,
            trouserWaistValue,trouserOutseamValue,trouserInseamValue,thighValue,kneeValue;


    TextView fabricNumberData;
    List<GridListItem> trouserPleatsList,trouserBackPocketList,trouserCuffList,trouserLoopTabList,trouserFitList;
    GridViewScrollable trouserPleats,trouserBackPocket,trouserCuff,trouserLoopTab,trouserFit;
    StylingAdapter trouserPleatsAdapter,trouserBackPocketAdapter,trouserCuffAdapter,trouserLoopTabAdapter,trouserFitAdapter;
    Drawable selected,unselected;
    Button nextBtnS;
    String  stylingId,trouserPleatsId,trouserBackPocketId,trouserCuffId,trouserLoopTabId,trouserFitId,trouserPleatsValue,trouserBackPocketValue,trouserCuffValue,
            trouserLoopTabValue,trouserFitValue,trouserPleatsImgValue,trouserBackPocketImgValue,trouserCuffImgValue,trouserLoopTabImgValue,trouserFitImgValue;
    int     trouserPleatsPrice,trouserBackPocketPrice,trouserCuffPrice,trouserLoopTabPrice,trouserFitPrice;



    TextView calculatedAmount,bill,discountTypeSign;
    EditText discount;
    RadioGroup discountType;
    RadioButton percenttagerb,rupeesrb;
    Button submit;
    String billValue;

    FloatingActionButton cameraFloatBtn;
    ProgressDialog pDialog;
    Drawable checked,unchecked;
    private int mShortAnimationDuration = 200;
    private Animator mCurrentAnimator;
    File image_file1,image_file2,image_file3,image_file4,image_file5,image_file6,image_file7,image_file8,image_file9,image_file10;
    Bitmap images[] = new Bitmap[10];
    boolean imageStatus[] = new boolean[10];
    int mday, mmonth, myear;
    int dayFinal, monthFinal , yearFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_taking_pant);
        setupUI(findViewById(R.id.content_order_taking_pant));
        orderCondition = getIntent().getExtras().getString("orderCondition");
        orderTypes.add("Bespoke");
        orderTypes.add("MTM");
        fabricChoices.add("ZeitGeist");
        fabricChoices.add("Self");

        typeface = Typeface.createFromAsset(getAssets(),"fonts/Raleway-Light.ttf");
        ReplaceFont.replaceFont(this,"DEFAULT","fonts/Raleway-Light.ttf");

        title = (TextView) findViewById(R.id.toolbar_heading);
        title.setTypeface(typeface);
        title.setText("ORDER TAKING");
        toolbar = (Toolbar) findViewById(R.id.c_toolbar);
        setSupportActionBar(toolbar);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please Wait...");
        pDialog.setCancelable(false);

        checked = getResources().getDrawable(R.drawable.side_btn_c_bg);
        unchecked = getResources().getDrawable(R.drawable.side_btn_u_bg);

        backButton = (ImageButton) findViewById(R.id.back_btn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(OrderTakingPant.this);
                builder.setTitle("Are you Sure?");
                builder.setMessage("Form data will be lost if you go back");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();

                    }
                });
                builder.setNegativeButton("No",null);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        SharedPreferences loginData = getSharedPreferences("shopInfo", Context.MODE_PRIVATE);
        shopId = loginData.getString("shopId","");
        itemType = "Pant";

        customerInfoLayout = (RelativeLayout) findViewById(R.id.customer_info_layout);
        fabricLayout = (RelativeLayout) findViewById(R.id.fabric_layout);
        measurementLayout = (RelativeLayout) findViewById(R.id.measurement_layout);
        suitStylingLayout = (RelativeLayout) findViewById(R.id.suit_styling_layout);
        invoiceLayout = (RelativeLayout) findViewById(R.id.invoice_layout);

        customerInfoBtn = (Button) findViewById(R.id.customer_info_btn);
        fabricBtn = (Button) findViewById(R.id.fabric_btn);
        measurementBtn = (Button) findViewById(R.id.measurement_btn);
        suitStylingBtn = (Button) findViewById(R.id.suit_styling_btn);
        invoiceBtn = (Button) findViewById(R.id.invoice_btn);

        secondaryContactLayout = (RelativeLayout) findViewById(R.id.secondary_contact_layout);
        addSecondContact = (ImageButton) findViewById(R.id.add_secondary_btn);

        nextBtnC = (Button) findViewById(R.id.next_btn_c);
        dateBtn = (Button) findViewById(R.id.date_data);

        name = (EditText) findViewById(R.id.name_data);
        contactPrimaryTwo = (EditText) findViewById(R.id.contact_two_digits_p);
        contactPrimarySeven = (EditText) findViewById(R.id.contact_seven_digits_p);
        contactSecondaryTwo = (EditText) findViewById(R.id.contact_two_digits_s);
        contactSecondarySeven = (EditText) findViewById(R.id.contact_seven_digits_s);
        email = (EditText) findViewById(R.id.email_data);
        address = (EditText) findViewById(R.id.address_data);
        fabricChoice = (Spinner) findViewById(R.id.fabric_choice_data);
        orderType = (Spinner) findViewById(R.id.order_type_data);
        basePatternText = (TextView) findViewById(R.id.base_pattern_text);
        basePattern = (Spinner) findViewById(R.id.base_pattern_data);
        baseSizeText = (TextView) findViewById(R.id.base_size_text);
        baseSize = (Spinner) findViewById(R.id.base_size_data);
        city = (Spinner) findViewById(R.id.city_data);

        selected = getResources().getDrawable(R.drawable.grid_bg_selected);
        unselected = getResources().getDrawable(R.drawable.grid_bg_unselected);

        expandedImageView = (ImageView) findViewById(R.id.fabric_expanded_image);
        container = (RelativeLayout) findViewById(R.id.content_order_taking_pant);
        fabricText = (TextView) findViewById(R.id.fabrics_text);
        fabricStyle = (GridView) findViewById(R.id.fabric_list);
        fabricStyleAdapter = new FabricLiningAdapter(getApplicationContext(),R.layout.fabric_grid_layout,selected,unselected,expandedImageView,container);
        clearFabricSelection = (Button) findViewById(R.id.fabric_selection_clear);
        fabricSearch = (SearchView) findViewById(R.id.fabric_search_data);
        nextBtnF = (Button) findViewById(R.id.next_btn_f);

        stance = (GridViewScrollable) findViewById(R.id.stance_list);
        stanceAdapter = new StylingAdapter(getApplicationContext(),R.layout.gridview_layout,selected,unselected,expandedImageView,container);
        hipDescription = (GridViewScrollable) findViewById(R.id.hip_description_list);
        hipDescriptionAdapter = new StylingAdapter(getApplicationContext(),R.layout.gridview_layout,selected,unselected,expandedImageView,container);
        nextBtnM = (Button) findViewById(R.id.next_btn_m);

        hipSignBtn = (Button) findViewById(R.id.hip_sign_btn);
        fullBackLengthSignBtn = (Button) findViewById(R.id.full_back_length_sign_btn);
        trouserWaistSignBtn = (Button) findViewById(R.id.trouser_waist_sign_btn);
        trouserOutseamSignBtn = (Button) findViewById(R.id.trouser_outseam_sign_btn);
        trouserInseamSignBtn = (Button) findViewById(R.id.trouser_inseam_sign_btn);
        thighSignBtn = (Button) findViewById(R.id.thigh_sign_btn);
        kneeSignBtn = (Button) findViewById(R.id.knee_sign_btn);

        hip = (EditText) findViewById(R.id.hip_data);
        fullBackLength = (EditText) findViewById(R.id.full_back_length_data);
        trouserWaist = (EditText) findViewById(R.id.trouser_waist_data);
        trouserOutseam = (EditText) findViewById(R.id.trouser_outseam_data);
        trouserInseam = (EditText) findViewById(R.id.trouser_inseam_data);
        thigh = (EditText) findViewById(R.id.thigh_data);
        knee = (EditText) findViewById(R.id.knee_data);

        hipImage = (ImageView) findViewById(R.id.hip_img);
        fullBackLengthImage = (ImageView) findViewById(R.id.full_back_length_img);
        trouserWaistImage = (ImageView) findViewById(R.id.trouser_waist_img);
        trouserOutseamImage = (ImageView) findViewById(R.id.trouser_outseam_img);
        trouserInseamImage = (ImageView) findViewById(R.id.trouser_inseam_img);
        thighImage = (ImageView) findViewById(R.id.thigh_img);
        kneeImage = (ImageView) findViewById(R.id.knee_img);

        hipZoomIcon = (ImageButton) findViewById(R.id.hip_zoom_icon);
        fullBackLengthZoomIcon = (ImageButton) findViewById(R.id.full_back_length_zoom_icon);
        trouserWaistZoomIcon = (ImageButton) findViewById(R.id.trouser_waist_zoom_icon);
        trouserOutseamZoomIcon = (ImageButton) findViewById(R.id.trouser_outseam_zoom_icon);
        trouserInseamZoomIcon = (ImageButton) findViewById(R.id.trouser_inseam_zoom_icon);
        thighZoomIcon = (ImageButton) findViewById(R.id.thigh_zoom_icon);
        kneeZoomIcon = (ImageButton) findViewById(R.id.knee_zoom_icon);

        fabricNumberData = (TextView) findViewById(R.id.fabric_data);
        trouserPleats = (GridViewScrollable) findViewById(R.id.trouser_pleats_list);
        trouserPleatsAdapter = new StylingAdapter(getApplicationContext(),R.layout.gridview_layout,selected,unselected,expandedImageView,container);
        trouserBackPocket = (GridViewScrollable) findViewById(R.id.trouser_back_pocket_list);
        trouserBackPocketAdapter = new StylingAdapter(getApplicationContext(),R.layout.gridview_layout,selected,unselected,expandedImageView,container);
        trouserCuff = (GridViewScrollable) findViewById(R.id.trouser_cuff_list);
        trouserCuffAdapter = new StylingAdapter(getApplicationContext(),R.layout.gridview_layout,selected,unselected,expandedImageView,container);
        trouserLoopTab = (GridViewScrollable) findViewById(R.id.trouser_loop_tab_list);
        trouserLoopTabAdapter = new StylingAdapter(getApplicationContext(),R.layout.gridview_layout,selected,unselected,expandedImageView,container);
        trouserFit = (GridViewScrollable) findViewById(R.id.trouser_fit_list);
        trouserFitAdapter = new StylingAdapter(getApplicationContext(),R.layout.gridview_layout,selected,unselected,expandedImageView,container);
        nextBtnS = (Button) findViewById(R.id.next_btn_s);


        calculatedAmount = (TextView) findViewById(R.id.calculated_amount);
        discountTypeSign = (TextView) findViewById(R.id.discount_type_sign);
        discountType = (RadioGroup) findViewById(R.id.discount_type);
        discount = (EditText) findViewById(R.id.discount);
        percenttagerb = (RadioButton) findViewById(R.id.percentage);
        rupeesrb = (RadioButton) findViewById(R.id.rupees);
        bill = (TextView) findViewById(R.id.final_bill);
        submit = (Button) findViewById(R.id.submit_btn);

        customerInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customerInfoBtn.setTextColor(getResources().getColor(R.color.side_btn_text_c));
                customerInfoBtn.setBackground(checked);
                fabricBtn.setTextColor(getResources().getColor(R.color.side_btn_text_u));
                fabricBtn.setBackground(unchecked);
                measurementBtn.setTextColor(getResources().getColor(R.color.side_btn_text_u));
                measurementBtn.setBackground(unchecked);
                suitStylingBtn.setTextColor(getResources().getColor(R.color.side_btn_text_u));
                suitStylingBtn.setBackground(unchecked);
                invoiceBtn.setTextColor(getResources().getColor(R.color.side_btn_text_u));
                invoiceBtn.setBackground(unchecked);
                fabricLayout.setVisibility(View.GONE);
                measurementLayout.setVisibility(View.GONE);
                suitStylingLayout.setVisibility(View.GONE);
                invoiceLayout.setVisibility(View.GONE);
                customerInfoLayout.setVisibility(View.VISIBLE);
            }
        });

        fabricBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customerInfoBtn.setTextColor(getResources().getColor(R.color.side_btn_text_u));
                customerInfoBtn.setBackground(unchecked);
                fabricBtn.setTextColor(getResources().getColor(R.color.side_btn_text_c));
                fabricBtn.setBackground(checked);
                invoiceBtn.setTextColor(getResources().getColor(R.color.side_btn_text_u));
                invoiceBtn.setBackground(unchecked);
                measurementBtn.setTextColor(getResources().getColor(R.color.side_btn_text_u));
                measurementBtn.setBackground(unchecked);
                suitStylingBtn.setTextColor(getResources().getColor(R.color.side_btn_text_u));
                suitStylingBtn.setBackground(unchecked);
                customerInfoLayout.setVisibility(View.GONE);
                suitStylingLayout.setVisibility(View.GONE);
                measurementLayout.setVisibility(View.GONE);
                invoiceLayout.setVisibility(View.GONE);
                fabricLayout.setVisibility(View.VISIBLE);
            }
        });

        measurementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customerInfoBtn.setTextColor(getResources().getColor(R.color.side_btn_text_u));
                customerInfoBtn.setBackground(unchecked);
                fabricBtn.setTextColor(getResources().getColor(R.color.side_btn_text_u));
                fabricBtn.setBackground(unchecked);
                invoiceBtn.setTextColor(getResources().getColor(R.color.side_btn_text_u));
                invoiceBtn.setBackground(unchecked);
                measurementBtn.setTextColor(getResources().getColor(R.color.side_btn_text_c));
                measurementBtn.setBackground(checked);
                suitStylingBtn.setTextColor(getResources().getColor(R.color.side_btn_text_u));
                suitStylingBtn.setBackground(unchecked);
                customerInfoLayout.setVisibility(View.GONE);
                fabricLayout.setVisibility(View.GONE);
                suitStylingLayout.setVisibility(View.GONE);
                invoiceLayout.setVisibility(View.GONE);
                measurementLayout.setVisibility(View.VISIBLE);
            }
        });

        suitStylingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customerInfoBtn.setTextColor(getResources().getColor(R.color.side_btn_text_u));
                customerInfoBtn.setBackground(unchecked);
                fabricBtn.setTextColor(getResources().getColor(R.color.side_btn_text_u));
                fabricBtn.setBackground(unchecked);
                measurementBtn.setTextColor(getResources().getColor(R.color.side_btn_text_u));
                measurementBtn.setBackground(unchecked);
                invoiceBtn.setTextColor(getResources().getColor(R.color.side_btn_text_u));
                invoiceBtn.setBackground(unchecked);
                suitStylingBtn.setTextColor(getResources().getColor(R.color.side_btn_text_c));
                suitStylingBtn.setBackground(checked);
                measurementLayout.setVisibility(View.GONE);
                customerInfoLayout.setVisibility(View.GONE);
                fabricLayout.setVisibility(View.GONE);
                invoiceLayout.setVisibility(View.GONE);
                suitStylingLayout.setVisibility(View.VISIBLE);
            }
        });

        invoiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customerInfoBtn.setTextColor(getResources().getColor(R.color.side_btn_text_u));
                customerInfoBtn.setBackground(unchecked);
                fabricBtn.setTextColor(getResources().getColor(R.color.side_btn_text_u));
                fabricBtn.setBackground(unchecked);
                measurementBtn.setTextColor(getResources().getColor(R.color.side_btn_text_u));
                measurementBtn.setBackground(unchecked);
                suitStylingBtn.setTextColor(getResources().getColor(R.color.side_btn_text_u));
                suitStylingBtn.setBackground(unchecked);
                invoiceBtn.setTextColor(getResources().getColor(R.color.side_btn_text_c));
                invoiceBtn.setBackground(checked);
                measurementLayout.setVisibility(View.GONE);
                customerInfoLayout.setVisibility(View.GONE);
                fabricLayout.setVisibility(View.GONE);
                suitStylingLayout.setVisibility(View.GONE);
                invoiceLayout.setVisibility(View.VISIBLE);
                calculateAmount();
                calculateBill();
            }
        });


        contactSecondarySeven.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus && !contactSecondaryTwo.getText().toString().equals("") && !contactSecondarySeven.getText().toString().equals("")){
                    contactSecondaryValue = "03" + contactSecondaryTwo.getText().toString() + contactSecondarySeven.getText().toString();
                    if(contactSecondaryValue.trim().length() < 11){
                        validSecondaryPhone = false;
                        Toast.makeText(getApplicationContext(),"Invalid Secondary Phone number",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        validSecondaryPhone = true;
                    }
                }
            }
        });


        addSecondContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondaryContactLayout.setVisibility(View.VISIBLE);
            }
        });

        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                myear = c.get(Calendar.YEAR);
                mmonth = c.get(Calendar.MONTH);
                mday = c.get(Calendar.DAY_OF_MONTH);



                DatePickerDialog datePickerDialog = new DatePickerDialog(OrderTakingPant.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        yearFinal = year;
                        monthFinal = month + 1;
                        dayFinal = dayOfMonth;


                        dateBtn.setText(dayFinal+"/"+monthFinal+"/"+yearFinal);
                        expectedDateValue = yearFinal + "-" + monthFinal + "-" + dayFinal;
                    }
                },myear,mmonth,mday){

                };
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });


        basePattern.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                basePatternValue = basePatterns.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        baseSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                baseSizeValue  = baseSizes.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SearchTypeAdapter fabricChoiceAdapter = new SearchTypeAdapter(getApplicationContext(),fabricChoices,typeface);
        fabricChoice.setAdapter(fabricChoiceAdapter);

        fabricChoice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fabricPrice = 0;
                if(fabricChoices.get(position).equals("Self")){
                    fabricIdValue = "0";
                    fabricNumberValue = "Self";
                    fabricChoiceValue = "Self";
                    fabricNumberData.setText(fabricNumberValue);
                    fabricStyleAdapter.removeSelection();
                    fabricStyleAdapter.notifyDataSetChanged();
                    fabricText.setVisibility(View.GONE);
                    clearFabricSelection.setVisibility(View.GONE);
                    fabricStyle.setVisibility(View.GONE);
                    fabricSearch.setVisibility(View.GONE);

                }
                else{
                    fabricIdValue = "";
                    fabricNumberValue = "";
                    fabricChoiceValue = "ZeitGeist";
                    fabricNumberData.setText(fabricNumberValue);
                    fabricText.setVisibility(View.VISIBLE);
                    clearFabricSelection.setVisibility(View.VISIBLE);
                    fabricStyle.setVisibility(View.VISIBLE);
                    fabricSearch.setVisibility(View.VISIBLE);
                }
                calculateAmount();
                calculateBill();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SearchTypeAdapter orderTypeAdapter = new SearchTypeAdapter(getApplicationContext(),orderTypes,typeface);
        orderType.setAdapter(orderTypeAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ipClass.ipAddress).
                        addConverterFactory(GsonConverterFactory.create())
                .build();
        final APIService service = retrofit.create(APIService.class);

        showpDialog();

        Call<Consumption> consumptionData = service.RetriveConsumption(garmentId);
        consumptionData.enqueue(new Callback<Consumption>() {
            @Override
            public void onResponse(Response<Consumption> response, Retrofit retrofit) {
                fabricConnsumption = response.body().getFabricConsumption();
                liningConsumption = response.body().getLiningConsumption();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(),"consumption error",Toast.LENGTH_SHORT).show();
            }
        });

        Call<ArrayList<String>> basePatternData = service.ProcessBasePattern(garmentId);

        basePatternData.enqueue(new Callback<ArrayList<String>>() {
            @Override
            public void onResponse(Response<ArrayList<String>> response, Retrofit retrofit) {
                basePatterns = response.body();
                SearchTypeAdapter basePatternAdapter = new SearchTypeAdapter(getApplicationContext(),basePatterns,typeface);
                basePattern.setAdapter(basePatternAdapter);
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(),"base pattern error",Toast.LENGTH_SHORT).show();
            }
        });

        Call<ArrayList<String>> baseSizeData = service.ProcessBaseSize(garmentId);

        baseSizeData.enqueue(new Callback<ArrayList<String>>() {
            @Override
            public void onResponse(Response<ArrayList<String>> response, Retrofit retrofit) {
                baseSizes = response.body();
                SearchTypeAdapter baseSizeAdapter = new SearchTypeAdapter(getApplicationContext(),baseSizes,typeface);
                baseSize.setAdapter(baseSizeAdapter);
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(),"base size error",Toast.LENGTH_SHORT).show();
            }
        });


        Call<ArrayList<String>> cityData = service.ProcessCities();
        cityData.enqueue(new Callback<ArrayList<String>>() {
            @Override
            public void onResponse(Response<ArrayList<String>> response, Retrofit retrofit) {
                cities = response.body();
                SearchTypeAdapter cityAdapter = new SearchTypeAdapter(getApplicationContext(),cities,typeface);
                city.setAdapter(cityAdapter);
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(),"city error",Toast.LENGTH_SHORT).show();
            }
        });

        cameraFloatBtn = (FloatingActionButton) findViewById(R.id.camera_float_btn);
        cameraFloatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CameraActivity.class);
                startActivity(intent);
            }
        });



        nextBtnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customerInfoBtn.setTextColor(getResources().getColor(R.color.side_btn_text_u));
                customerInfoBtn.setBackground(unchecked);
                fabricBtn.setTextColor(getResources().getColor(R.color.side_btn_text_c));
                fabricBtn.setBackground(checked);
                measurementBtn.setTextColor(getResources().getColor(R.color.side_btn_text_u));
                measurementBtn.setBackground(unchecked);
                suitStylingBtn.setTextColor(getResources().getColor(R.color.side_btn_text_u));
                suitStylingBtn.setBackground(unchecked);
                customerInfoLayout.setVisibility(View.GONE);
                suitStylingLayout.setVisibility(View.GONE);
                measurementLayout.setVisibility(View.GONE);
                fabricLayout.setVisibility(View.VISIBLE);
            }
        });

        /*--------------------------------------------------------------------------------*/


        Call<List<FabricLiningItem>> fabricData = service.ProcessFabricItem("pant");

        fabricData.enqueue(new Callback<List<FabricLiningItem>>() {
            @Override
            public void onResponse(Response<List<FabricLiningItem>> response, Retrofit retrofit) {
                fabricList = response.body();
                fabricStyleAdapter.addItemList(fabricList);
                fabricStyle.setAdapter(fabricStyleAdapter);

                fabricStyle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        fabricListSearchable = fabricStyleAdapter.getFilterList();
                        Toast.makeText(getApplicationContext(),fabricListSearchable.get(position).getItemNumber(),Toast.LENGTH_LONG).show();
                        fabricNumberValue = fabricListSearchable.get(position).getItemNumber();
                        fabricIdValue = fabricListSearchable.get(position).getItemId();
                        fabricNumberData.setText(fabricNumberValue);
                        fabricStyleAdapter.makeAllUnselect(position);
                        fabricStyleAdapter.notifyDataSetChanged();
                        orderTypeValue = orderTypes.get(Integer.parseInt(orderType.getSelectedItem().toString()));
                        if(orderTypeValue.equals("Bespoke") && !fabricChoices.get(Integer.parseInt(fabricChoice.getSelectedItem().toString())).equals("Self")){
                            fabricPrice = Integer.parseInt(fabricListSearchable.get(position).getItemBsTrouserPrice());
                        }
                        else if(orderTypeValue.equals("MTM") && !fabricChoices.get(Integer.parseInt(fabricChoice.getSelectedItem().toString())).equals("Self")) {
                            fabricPrice = Integer.parseInt(fabricListSearchable.get(position).getItemMtmTrouserPrice());
                        }

                        calculateAmount();
                        calculateBill();
                    }
                });

                clearFabricSelection.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fabricNumberValue = "";
                        fabricIdValue = "";
                        fabricNumberData.setText("");
                        fabricPrice = 0;
                        fabricStyleAdapter.removeSelection();
                        fabricStyleAdapter.notifyDataSetChanged();
                        calculateAmount();
                        calculateBill();
                    }
                });
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });



        fabricSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                fabricStyleAdapter.getFilter().filter(newText);
                try{
                    fabricStyleAdapter.removeSelection();
                    fabricPrice = 0;
                }
                catch (NullPointerException e){

                }
                return false;
            }
        });



        nextBtnF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customerInfoBtn.setTextColor(getResources().getColor(R.color.side_btn_text_u));
                customerInfoBtn.setBackground(unchecked);
                fabricBtn.setTextColor(getResources().getColor(R.color.side_btn_text_u));
                fabricBtn.setBackground(unchecked);
                measurementBtn.setTextColor(getResources().getColor(R.color.side_btn_text_c));
                measurementBtn.setBackground(checked);
                suitStylingBtn.setTextColor(getResources().getColor(R.color.side_btn_text_u));
                suitStylingBtn.setBackground(unchecked);
                customerInfoLayout.setVisibility(View.GONE);
                suitStylingLayout.setVisibility(View.GONE);
                fabricLayout.setVisibility(View.GONE);
                measurementLayout.setVisibility(View.VISIBLE);
            }
        });


        /*--------------------------------------------------------------------------------*/


        Call<List<GridListItem>> stanceData = service.ProcessListItem("27");

        stanceData.enqueue(new Callback<List<GridListItem>>() {
            @Override
            public void onResponse(Response<List<GridListItem>> response, Retrofit retrofit) {
                stanceList = response.body();
                stanceAdapter.addItemList(stanceList);
                stance.setAdapter(stanceAdapter);

                stance.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(getApplicationContext(),stanceList.get(position).getItemName(),Toast.LENGTH_LONG).show();
                        stanceId = stanceList.get(position).getItemId();
                        stanceValue = stanceList.get(position).getItemName();
                        stanceImgValue = stanceList.get(position).getItemImageSource();
                        stanceAdapter.makeAllUnselect(position);
                        stanceAdapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });



        Call<List<GridListItem>> hipDescriptionData = service.ProcessListItem("31");

        hipDescriptionData.enqueue(new Callback<List<GridListItem>>() {
            @Override
            public void onResponse(Response<List<GridListItem>> response, Retrofit retrofit) {
                hipDescriptionList = response.body();
                hipDescriptionAdapter.addItemList(hipDescriptionList);
                hipDescription.setAdapter(hipDescriptionAdapter);

                hipDescription.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(getApplicationContext(),hipDescriptionList.get(position).getItemName(),Toast.LENGTH_LONG).show();
                        hipDescriptionId = hipDescriptionList.get(position).getItemId();
                        hipDescriptionValue = hipDescriptionList.get(position).getItemName();
                        hipDescriptionImgValue = hipDescriptionList.get(position).getItemImageSource();
                        hipDescriptionAdapter.makeAllUnselect(position);
                        hipDescriptionAdapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });


        Call<String> hipData = service.ProcessMeasurementImage("9");

        hipData.enqueue(new Callback<String>() {
            @Override
            public void onResponse(final Response<String> response, Retrofit retrofit) {
                if(!response.body().equals("")){
                    Picasso.with(getApplicationContext()).load(ipClass.ipAddress + response.body()).into(hipImage);
                    hipZoomIcon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            zoomImageFromThumb(hipImage,response.body());
                        }
                    });
                }
            }
            @Override
            public void onFailure(Throwable t) {
            }
        });


        Call<String> fullBackLengthData = service.ProcessMeasurementImage("15");

        fullBackLengthData.enqueue(new Callback<String>() {
            @Override
            public void onResponse(final Response<String> response, Retrofit retrofit) {
                if(!response.body().equals("")){
                    Picasso.with(getApplicationContext()).load(ipClass.ipAddress + response.body()).into(fullBackLengthImage);
                    fullBackLengthZoomIcon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            zoomImageFromThumb(fullBackLengthImage,response.body());
                        }
                    });
                }
            }
            @Override
            public void onFailure(Throwable t) {
            }
        });


        Call<String> trouserWaistData = service.ProcessMeasurementImage("17");

        trouserWaistData.enqueue(new Callback<String>() {
            @Override
            public void onResponse(final Response<String> response, Retrofit retrofit) {
                if(!response.body().equals("")){
                    Picasso.with(getApplicationContext()).load(ipClass.ipAddress + response.body()).into(trouserWaistImage);
                    trouserWaistZoomIcon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            zoomImageFromThumb(trouserWaistImage,response.body());
                        }
                    });
                }
            }
            @Override
            public void onFailure(Throwable t) {
            }
        });

        Call<String> trouserOutseamData = service.ProcessMeasurementImage("18");

        trouserOutseamData.enqueue(new Callback<String>() {
            @Override
            public void onResponse(final Response<String> response, Retrofit retrofit) {
                if(!response.body().equals("")){
                    Picasso.with(getApplicationContext()).load(ipClass.ipAddress + response.body()).into(trouserOutseamImage);
                    trouserOutseamZoomIcon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            zoomImageFromThumb(trouserOutseamImage,response.body());
                        }
                    });
                }
            }
            @Override
            public void onFailure(Throwable t) {
            }
        });

        Call<String> trouserInseamData = service.ProcessMeasurementImage("19");

        trouserInseamData.enqueue(new Callback<String>() {
            @Override
            public void onResponse(final Response<String> response, Retrofit retrofit) {
                if(!response.body().equals("")){
                    Picasso.with(getApplicationContext()).load(ipClass.ipAddress + response.body()).into(trouserInseamImage);
                    trouserInseamZoomIcon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            zoomImageFromThumb(trouserInseamImage,response.body());
                        }
                    });
                }
            }
            @Override
            public void onFailure(Throwable t) {
            }
        });


        Call<String> thighData = service.ProcessMeasurementImage("21");

        thighData.enqueue(new Callback<String>() {
            @Override
            public void onResponse(final Response<String> response, Retrofit retrofit) {
                if(!response.body().equals("")){
                    Picasso.with(getApplicationContext()).load(ipClass.ipAddress + response.body()).into(thighImage);
                    thighZoomIcon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            zoomImageFromThumb(thighImage,response.body());
                        }
                    });
                }
            }
            @Override
            public void onFailure(Throwable t) {
            }
        });

        Call<String> kneeData = service.ProcessMeasurementImage("22");

        kneeData.enqueue(new Callback<String>() {
            @Override
            public void onResponse(final Response<String> response, Retrofit retrofit) {
                if(!response.body().equals("")){
                    Picasso.with(getApplicationContext()).load(ipClass.ipAddress + response.body()).into(kneeImage);
                    kneeZoomIcon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            zoomImageFromThumb(kneeImage,response.body());
                        }
                    });
                }
            }
            @Override
            public void onFailure(Throwable t) {
            }
        });





        hipSignBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hipSignBtn.getText().toString().equals("+")){
                    hipSignBtn.setText("-");
                }
                else {
                    hipSignBtn.setText("+");
                }
            }
        });

        fullBackLengthSignBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fullBackLengthSignBtn.getText().toString().equals("+")){
                    fullBackLengthSignBtn.setText("-");
                }
                else {
                    fullBackLengthSignBtn.setText("+");
                }
            }
        });

        trouserWaistSignBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(trouserWaistSignBtn.getText().toString().equals("+")){
                    trouserWaistSignBtn.setText("-");
                }
                else {
                    trouserWaistSignBtn.setText("+");
                }
            }
        });

        trouserOutseamSignBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(trouserOutseamSignBtn.getText().toString().equals("+")){
                    trouserOutseamSignBtn.setText("-");
                }
                else {
                    trouserOutseamSignBtn.setText("+");
                }
            }
        });

        trouserInseamSignBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(trouserInseamSignBtn.getText().toString().equals("+")){
                    trouserInseamSignBtn.setText("-");
                }
                else {
                    trouserInseamSignBtn.setText("+");
                }
            }
        });

        thighSignBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(thighSignBtn.getText().toString().equals("+")){
                    thighSignBtn.setText("-");
                }
                else {
                    thighSignBtn.setText("+");
                }
            }
        });

        kneeSignBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(kneeSignBtn.getText().toString().equals("+")){
                    kneeSignBtn.setText("-");
                }
                else {
                    kneeSignBtn.setText("+");
                }
            }
        });

        nextBtnM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customerInfoBtn.setTextColor(getResources().getColor(R.color.side_btn_text_u));
                customerInfoBtn.setBackground(unchecked);
                fabricBtn.setTextColor(getResources().getColor(R.color.side_btn_text_u));
                fabricBtn.setBackground(unchecked);
                measurementBtn.setTextColor(getResources().getColor(R.color.side_btn_text_u));
                measurementBtn.setBackground(unchecked);
                suitStylingBtn.setTextColor(getResources().getColor(R.color.side_btn_text_c));
                suitStylingBtn.setBackground(checked);
                customerInfoLayout.setVisibility(View.GONE);
                fabricLayout.setVisibility(View.GONE);
                measurementLayout.setVisibility(View.GONE);
                suitStylingLayout.setVisibility(View.VISIBLE);
            }
        });

        /*--------------------------------------------------------------------------------*/

        showpDialog();



        Call<List<GridListItem>> trouserPleatsData = service.ProcessListItem("8");

        trouserPleatsData.enqueue(new Callback<List<GridListItem>>() {
            @Override
            public void onResponse(Response<List<GridListItem>> response, Retrofit retrofit) {
                trouserPleatsList = response.body();
                trouserPleatsAdapter.addItemList(trouserPleatsList);
                trouserPleats.setAdapter(trouserPleatsAdapter);

                trouserPleats.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(getApplicationContext(),trouserPleatsList.get(position).getItemName(),Toast.LENGTH_LONG).show();
                        trouserPleatsId = trouserPleatsList.get(position).getItemId();
                        trouserPleatsValue = trouserPleatsList.get(position).getItemName();
                        trouserPleatsImgValue = trouserPleatsList.get(position).getItemImageSource();
                        trouserPleatsPrice = Integer.parseInt(trouserPleatsList.get(position).getItemPrice());
                        trouserPleatsAdapter.makeAllUnselect(position);
                        trouserPleatsAdapter.notifyDataSetChanged();
                        calculateAmount();
                        calculateBill();
                    }
                });
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });



        Call<List<GridListItem>> trouserBackPocketData = service.ProcessListItem("9");

        trouserBackPocketData.enqueue(new Callback<List<GridListItem>>() {
            @Override
            public void onResponse(Response<List<GridListItem>> response, Retrofit retrofit) {
                trouserBackPocketList = response.body();
                trouserBackPocketAdapter.addItemList(trouserBackPocketList);
                trouserBackPocket.setAdapter(trouserBackPocketAdapter);

                trouserBackPocket.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(getApplicationContext(),trouserBackPocketList.get(position).getItemName(),Toast.LENGTH_LONG).show();
                        trouserBackPocketId = trouserBackPocketList.get(position).getItemId();
                        trouserBackPocketValue = trouserBackPocketList.get(position).getItemName();
                        trouserBackPocketImgValue = trouserBackPocketList.get(position).getItemImageSource();
                        trouserBackPocketPrice = Integer.parseInt(trouserBackPocketList.get(position).getItemPrice());
                        trouserBackPocketAdapter.makeAllUnselect(position);
                        trouserBackPocketAdapter.notifyDataSetChanged();
                        calculateAmount();
                        calculateBill();
                    }
                });
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });



        Call<List<GridListItem>> trouserCuffData = service.ProcessListItem("10");

        trouserCuffData.enqueue(new Callback<List<GridListItem>>() {
            @Override
            public void onResponse(Response<List<GridListItem>> response, Retrofit retrofit) {
                trouserCuffList = response.body();
                trouserCuffAdapter.addItemList(trouserCuffList);
                trouserCuff.setAdapter(trouserCuffAdapter);

                trouserCuff.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(getApplicationContext(),trouserCuffList.get(position).getItemName(),Toast.LENGTH_LONG).show();
                        trouserCuffId = trouserCuffList.get(position).getItemId();
                        trouserCuffValue = trouserCuffList.get(position).getItemName();
                        trouserCuffImgValue = trouserCuffList.get(position).getItemImageSource();
                        trouserCuffPrice = Integer.parseInt(trouserCuffList.get(position).getItemPrice());
                        trouserCuffAdapter.makeAllUnselect(position);
                        trouserCuffAdapter.notifyDataSetChanged();
                        calculateAmount();
                        calculateBill();
                    }
                });
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });




        Call<List<GridListItem>> trouserLoopTabData = service.ProcessListItem("11");

        trouserLoopTabData.enqueue(new Callback<List<GridListItem>>() {
            @Override
            public void onResponse(Response<List<GridListItem>> response, Retrofit retrofit) {
                trouserLoopTabList = response.body();
                trouserLoopTabAdapter.addItemList(trouserLoopTabList);
                trouserLoopTab.setAdapter(trouserLoopTabAdapter);

                trouserLoopTab.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(getApplicationContext(),trouserLoopTabList.get(position).getItemName(),Toast.LENGTH_LONG).show();
                        trouserLoopTabId = trouserLoopTabList.get(position).getItemId();
                        trouserLoopTabValue = trouserLoopTabList.get(position).getItemName();
                        trouserLoopTabImgValue = trouserLoopTabList.get(position).getItemImageSource();
                        trouserLoopTabPrice = Integer.parseInt(trouserLoopTabList.get(position).getItemPrice());
                        trouserLoopTabAdapter.makeAllUnselect(position);
                        trouserLoopTabAdapter.notifyDataSetChanged();
                        calculateAmount();
                        calculateBill();
                    }
                });
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });


        Call<List<GridListItem>> fitData = service.ProcessListItem("23");

        fitData.enqueue(new Callback<List<GridListItem>>() {
            @Override
            public void onResponse(Response<List<GridListItem>> response, Retrofit retrofit) {
                hidepDialog();
                trouserFitList = response.body();
                trouserFitAdapter.addItemList(trouserFitList);
                trouserFit.setAdapter(trouserFitAdapter);

                trouserFit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(getApplicationContext(),trouserFitList.get(position).getItemName(),Toast.LENGTH_LONG).show();
                        trouserFitId = trouserFitList.get(position).getItemId();
                        trouserFitValue = trouserFitList.get(position).getItemName();
                        trouserFitImgValue = trouserFitList.get(position).getItemImageSource();
                        trouserFitPrice = Integer.parseInt(trouserFitList.get(position).getItemPrice());
                        trouserFitAdapter.makeAllUnselect(position);
                        trouserFitAdapter.notifyDataSetChanged();
                        calculateAmount();
                        calculateBill();
                    }
                });
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

        nextBtnS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customerInfoBtn.setTextColor(getResources().getColor(R.color.side_btn_text_u));
                customerInfoBtn.setBackground(unchecked);
                fabricBtn.setTextColor(getResources().getColor(R.color.side_btn_text_u));
                fabricBtn.setBackground(unchecked);
                measurementBtn.setTextColor(getResources().getColor(R.color.side_btn_text_u));
                measurementBtn.setBackground(unchecked);
                suitStylingBtn.setTextColor(getResources().getColor(R.color.side_btn_text_u));
                suitStylingBtn.setBackground(unchecked);
                invoiceBtn.setTextColor(getResources().getColor(R.color.side_btn_text_c));
                invoiceBtn.setBackground(checked);
                measurementLayout.setVisibility(View.GONE);
                customerInfoLayout.setVisibility(View.GONE);
                fabricLayout.setVisibility(View.GONE);
                suitStylingLayout.setVisibility(View.GONE);
                invoiceLayout.setVisibility(View.VISIBLE);
                calculateAmount();
                calculateBill();
            }
        });

        /*-----------------------------------------------------------------------*/


        discountType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.percentage){
                    discount.setText("0");
                    discount.setFilters(new InputFilter[] {new InputFilter.LengthFilter(2)});
                    discountTypeSign.setText("%");
                }
                else if(checkedId == R.id.rupees){
                    discount.setText("0");
                    discount.setFilters(new InputFilter[] {new InputFilter.LengthFilter(5)});
                    discountTypeSign.setText("Rupees");

                }
            }
        });

        discount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculateBill();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        /*-----------------------------------------------------------------------*/


        if(orderCondition.equals("New Customer")){

            contactPrimarySeven.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(!hasFocus){
                        contactPrimaryValue = "03" + contactPrimaryTwo.getText().toString() + contactPrimarySeven.getText().toString();
                        if(contactPrimaryValue.trim().length() < 11){
                            validPhoneNumber = false;
                            Toast.makeText(getApplicationContext(),"Invalid Phone number",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            validPhoneNumber = true;
                            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(ipClass.ipAddress).
                                            addConverterFactory(GsonConverterFactory.create())
                                    .build();
                            final APIService service = retrofit.create(APIService.class);
                            showpDialog();

                            Call<String> phoneCheck = service.ProcessPhoneNumber(contactPrimaryValue);

                            phoneCheck.enqueue(new Callback<String>() {
                                @Override
                                public void onResponse(Response<String> response, Retrofit retrofit) {
                                    hidepDialog();
                                    if(response.body().equals("Yes")){
                                        uniquePhoneNumber = true;
                                    }
                                    else {
                                        uniquePhoneNumber = false;
                                        Toast.makeText(getApplicationContext(),"Customer already exists with this number \n Change number or Place order as Existing Customer",Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onFailure(Throwable t) {
                                    hidepDialog();
                                    Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    }
                }
            });

            email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(ipClass.ipAddress).
                                    addConverterFactory(GsonConverterFactory.create())
                            .build();
                    final APIService service = retrofit.create(APIService.class);
                    showpDialog();

                    Call<String> emailCheck = service.ProcessEmail(email.getText().toString());

                    emailCheck.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Response<String> response, Retrofit retrofit) {
                            hidepDialog();
                            if(response.body().equals("Yes")){
                                uniqueEmail = true;
                            }
                            else {
                                uniqueEmail = false;
                                Toast.makeText(getApplicationContext(),"Customer already exists with this email \n Change email or Place order as Existing Customer",Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            hidepDialog();
                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            });


            orderType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if(orderTypes.get(position).equals("Bespoke")){
                        basePatternValue = "";
                        baseSizeValue = "";

                        basePatternText.setVisibility(View.GONE);
                        basePattern.setVisibility(View.GONE);
                        baseSizeText.setVisibility(View.GONE);
                        baseSize.setVisibility(View.GONE);

                        hipSignBtn.setVisibility(View.GONE);
                        fullBackLengthSignBtn.setVisibility(View.GONE);
                        trouserWaistSignBtn.setVisibility(View.GONE);
                        trouserOutseamSignBtn.setVisibility(View.GONE);
                        trouserInseamSignBtn.setVisibility(View.GONE);
                        thighSignBtn.setVisibility(View.GONE);
                        kneeSignBtn.setVisibility(View.GONE);

                        hip.setText("");
                        fullBackLength.setText("");
                        trouserWaist.setText("");
                        trouserOutseam.setText("");
                        trouserInseam.setText("");
                        thigh.setText("");
                        knee.setText("");
                    }
                    else if(orderTypes.get(position).equals("MTM")){
                        basePatternValue = basePatterns.get(Integer.parseInt(basePattern.getSelectedItem().toString()));
                        baseSizeValue = baseSizes.get(Integer.parseInt(baseSize.getSelectedItem().toString()));

                        basePatternText.setVisibility(View.VISIBLE);
                        basePattern.setVisibility(View.VISIBLE);
                        baseSizeText.setVisibility(View.VISIBLE);
                        baseSize.setVisibility(View.VISIBLE);

                        hipSignBtn.setVisibility(View.VISIBLE);
                        fullBackLengthSignBtn.setVisibility(View.VISIBLE);
                        trouserWaistSignBtn.setVisibility(View.VISIBLE);
                        trouserOutseamSignBtn.setVisibility(View.VISIBLE);
                        trouserInseamSignBtn.setVisibility(View.VISIBLE);
                        thighSignBtn.setVisibility(View.VISIBLE);
                        kneeSignBtn.setVisibility(View.VISIBLE);

                        hip.setText("0");
                        fullBackLength.setText("0");
                        trouserWaist.setText("0");
                        trouserOutseam.setText("0");
                        trouserInseam.setText("0");
                        thigh.setText("0");
                        knee.setText("0");
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            Toast.makeText(getApplicationContext(),""+orderCondition,Toast.LENGTH_SHORT).show();
        }
        else if(orderCondition.equals("Existing Customer")){

            orderType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if(orderTypes.get(position).equals("Bespoke")){
                        basePatternValue = "";
                        baseSizeValue = "";

                        basePatternText.setVisibility(View.GONE);
                        basePattern.setVisibility(View.GONE);
                        baseSizeText.setVisibility(View.GONE);
                        baseSize.setVisibility(View.GONE);

                        hipSignBtn.setVisibility(View.GONE);
                        fullBackLengthSignBtn.setVisibility(View.GONE);
                        trouserWaistSignBtn.setVisibility(View.GONE);
                        trouserOutseamSignBtn.setVisibility(View.GONE);
                        trouserInseamSignBtn.setVisibility(View.GONE);
                        thighSignBtn.setVisibility(View.GONE);
                        kneeSignBtn.setVisibility(View.GONE);

                        hip.setText("");
                        fullBackLength.setText("");
                        trouserWaist.setText("");
                        trouserOutseam.setText("");
                        trouserInseam.setText("");
                        thigh.setText("");
                        knee.setText("");
                    }
                    else if(orderTypes.get(position).equals("MTM")){
                        basePatternValue = basePatterns.get(Integer.parseInt(basePattern.getSelectedItem().toString()));
                        baseSizeValue = baseSizes.get(Integer.parseInt(baseSize.getSelectedItem().toString()));

                        basePatternText.setVisibility(View.VISIBLE);
                        basePattern.setVisibility(View.VISIBLE);
                        baseSizeText.setVisibility(View.VISIBLE);
                        baseSize.setVisibility(View.VISIBLE);

                        hipSignBtn.setVisibility(View.VISIBLE);
                        fullBackLengthSignBtn.setVisibility(View.VISIBLE);
                        trouserWaistSignBtn.setVisibility(View.VISIBLE);
                        trouserOutseamSignBtn.setVisibility(View.VISIBLE);
                        trouserInseamSignBtn.setVisibility(View.VISIBLE);
                        thighSignBtn.setVisibility(View.VISIBLE);
                        kneeSignBtn.setVisibility(View.VISIBLE);

                        hip.setText("0");
                        fullBackLength.setText("0");
                        trouserWaist.setText("0");
                        trouserOutseam.setText("0");
                        trouserInseam.setText("0");
                        thigh.setText("0");
                        knee.setText("0");
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            customerId = getIntent().getExtras().getString("customerId");

            showpDialog();

            Call<Customer> customerData = service.SearchCustomerById(customerId);
            customerData.enqueue(new Callback<Customer>() {
                @Override
                public void onResponse(Response<Customer> response, Retrofit retrofit) {
                    if(response.body() != null){
                        hidepDialog();
                        name.setText(response.body().getCustomerName());
                        previousPrimaryContact = response.body().getCustomerPrimaryPhone();
                        contactPrimaryValue = response.body().getCustomerPrimaryPhone();
                        contactPrimaryTwo.setText(contactPrimaryValue.substring(2,4));
                        contactPrimarySeven.setText(contactPrimaryValue.substring(4,11));
                        contactSecondaryValue = response.body().getCustomerSecondaryPhone();
                        if(!contactSecondaryValue.equals("")){
                            contactSecondaryTwo.setText(contactSecondaryValue.substring(2,4));
                            contactSecondarySeven.setText(contactSecondaryValue.substring(4,11));
                        }

                        previousEmail = response.body().getCustomerEmail();
                        email.setText(response.body().getCustomerEmail());
                        address.setText(response.body().getCustomerAddress());
                        for(int i = 0 ; i < cities.size() ; i++){
                            if(response.body().getCustomerCity().equals(cities.get(i))){
                                cityIndex = i;
                                break;
                            }
                        }
                        city.setSelection(cityIndex);
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    hidepDialog();
                    Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                }
            });


            contactPrimarySeven.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    contactPrimaryValue = "03" + contactPrimaryTwo.getText().toString() + contactPrimarySeven.getText().toString();
                    if(!hasFocus && !contactPrimaryValue.equals(previousPrimaryContact)){
                        if(contactPrimaryValue.trim().length() < 11){
                            validPhoneNumber = false;
                            Toast.makeText(getApplicationContext(),"Invalid Phone number",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            validPhoneNumber = true;
                            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(ipClass.ipAddress).
                                            addConverterFactory(GsonConverterFactory.create())
                                    .build();
                            final APIService service = retrofit.create(APIService.class);
                            showpDialog();

                            Call<String> phoneCheck = service.ProcessPhoneNumber(contactPrimaryValue);

                            phoneCheck.enqueue(new Callback<String>() {
                                @Override
                                public void onResponse(Response<String> response, Retrofit retrofit) {
                                    hidepDialog();
                                    if(response.body().equals("Yes")){
                                        uniquePhoneNumber = true;
                                    }
                                    else {
                                        uniquePhoneNumber = false;
                                        Toast.makeText(getApplicationContext(),"Customer is already registered with this number \n Change number",Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onFailure(Throwable t) {
                                    hidepDialog();
                                    Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                    else {
                        validPhoneNumber = true;
                        uniquePhoneNumber = true;
                    }
                }
            });

            email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(!hasFocus && !email.getText().toString().equals(previousEmail)){
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(ipClass.ipAddress).
                                        addConverterFactory(GsonConverterFactory.create())
                                .build();
                        final APIService service = retrofit.create(APIService.class);
                        showpDialog();

                        Call<String> emailCheck = service.ProcessEmail(email.getText().toString());

                        emailCheck.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Response<String> response, Retrofit retrofit) {
                                hidepDialog();
                                if(response.body().equals("Yes")){
                                    uniqueEmail = true;
                                }
                                else {
                                    uniqueEmail = false;
                                    Toast.makeText(getApplicationContext(),"Customer is already registered with this email \n Change email",Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Throwable t) {
                                hidepDialog();
                                Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    else {
                        uniqueEmail = true;
                    }
                }
            });

        }
        else if(orderCondition.equals("As new order") || orderCondition.equals("Alter order")){

            try {

                orderType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (orderTypes.get(position).equals("Bespoke")) {
                            basePatternValue = "";
                            baseSizeValue = "";

                            basePatternText.setVisibility(View.GONE);
                            basePattern.setVisibility(View.GONE);
                            baseSizeText.setVisibility(View.GONE);
                            baseSize.setVisibility(View.GONE);

                            hipSignBtn.setVisibility(View.GONE);
                            fullBackLengthSignBtn.setVisibility(View.GONE);
                            trouserWaistSignBtn.setVisibility(View.GONE);
                            trouserOutseamSignBtn.setVisibility(View.GONE);
                            trouserInseamSignBtn.setVisibility(View.GONE);
                            thighSignBtn.setVisibility(View.GONE);
                            kneeSignBtn.setVisibility(View.GONE);

                        } else if (orderTypes.get(position).equals("MTM")) {

                            /*Call<ArrayList<String>> basePatternData = service.ProcessBasePattern(garmentId);

                            basePatternData.enqueue(new Callback<ArrayList<String>>() {
                                @Override
                                public void onResponse(Response<ArrayList<String>> response, Retrofit retrofit) {
                                    basePatterns = response.body();
                                    SearchTypeAdapter basePatternAdapter = new SearchTypeAdapter(getApplicationContext(),basePatterns,typeface);
                                    basePattern.setAdapter(basePatternAdapter);
                                    basePatternValue = basePatterns.get(Integer.parseInt(basePattern.getSelectedItem().toString()));
                                }

                                @Override
                                public void onFailure(Throwable t) {
                                    Toast.makeText(getApplicationContext(),"base pattern error",Toast.LENGTH_SHORT).show();
                                }
                            });

                            Call<ArrayList<String>> baseSizeData = service.ProcessBaseSize(garmentId);

                            baseSizeData.enqueue(new Callback<ArrayList<String>>() {
                                @Override
                                public void onResponse(Response<ArrayList<String>> response, Retrofit retrofit) {
                                    baseSizes = response.body();
                                    SearchTypeAdapter baseSizeAdapter = new SearchTypeAdapter(getApplicationContext(),baseSizes,typeface);
                                    baseSize.setAdapter(baseSizeAdapter);
                                    baseSizeValue = baseSizes.get(Integer.parseInt(baseSize.getSelectedItem().toString()));
                                }

                                @Override
                                public void onFailure(Throwable t) {
                                    Toast.makeText(getApplicationContext(),"base size error",Toast.LENGTH_SHORT).show();
                                }
                            });*/

                            try{
                                basePatternValue = basePatterns.get(Integer.parseInt(basePattern.getSelectedItem().toString()));
                                baseSizeValue = baseSizes.get(Integer.parseInt(baseSize.getSelectedItem().toString()));
                            }
                            catch (NullPointerException e){

                            }

                            basePatternText.setVisibility(View.VISIBLE);
                            basePattern.setVisibility(View.VISIBLE);
                            baseSizeText.setVisibility(View.VISIBLE);
                            baseSize.setVisibility(View.VISIBLE);

                            hipSignBtn.setVisibility(View.VISIBLE);
                            fullBackLengthSignBtn.setVisibility(View.VISIBLE);
                            trouserWaistSignBtn.setVisibility(View.VISIBLE);
                            trouserOutseamSignBtn.setVisibility(View.VISIBLE);
                            trouserInseamSignBtn.setVisibility(View.VISIBLE);
                            thighSignBtn.setVisibility(View.VISIBLE);
                            kneeSignBtn.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                customerId = getIntent().getExtras().getString("customerId");
                orderId = getIntent().getExtras().getString("orderId");
                orderTypeValue = getIntent().getExtras().getString("orderType");

                Call<Customer> customerData = service.SearchCustomerById(customerId);
                customerData.enqueue(new Callback<Customer>() {
                    @Override
                    public void onResponse(Response<Customer> response, Retrofit retrofit) {
                        if (response.body() != null) {
                            hidepDialog();
                            name.setText(response.body().getCustomerName());
                            previousPrimaryContact = response.body().getCustomerPrimaryPhone();
                            contactPrimaryValue = response.body().getCustomerPrimaryPhone();
                            contactPrimaryTwo.setText(contactPrimaryValue.substring(2, 4));
                            contactPrimarySeven.setText(contactPrimaryValue.substring(4, 11));
                            contactSecondaryValue = response.body().getCustomerSecondaryPhone();
                            if (!contactSecondaryValue.equals("")) {
                                contactSecondaryTwo.setText(contactSecondaryValue.substring(2, 4));
                                contactSecondarySeven.setText(contactSecondaryValue.substring(4, 11));
                            }

                            previousEmail = response.body().getCustomerEmail();
                            email.setText(response.body().getCustomerEmail());
                            address.setText(response.body().getCustomerAddress());
                            for (int i = 0; i < cities.size(); i++) {
                                if (response.body().getCustomerCity().equals(cities.get(i))) {
                                    cityIndex = i;
                                    break;
                                }
                            }
                            city.setSelection(cityIndex);
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        hidepDialog();
                        Toast.makeText(getApplicationContext(), "Connection Error", Toast.LENGTH_SHORT).show();
                    }
                });

                contactPrimarySeven.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        contactPrimaryValue = "03" + contactPrimaryTwo.getText().toString() + contactPrimarySeven.getText().toString();
                        if(!hasFocus && !contactPrimaryValue.equals(previousPrimaryContact)){
                            if(contactPrimaryValue.trim().length() < 11){
                                validPhoneNumber = false;
                                Toast.makeText(getApplicationContext(),"Invalid Phone number",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                validPhoneNumber = true;
                                Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl(ipClass.ipAddress).
                                                addConverterFactory(GsonConverterFactory.create())
                                        .build();
                                final APIService service = retrofit.create(APIService.class);
                                showpDialog();

                                Call<String> phoneCheck = service.ProcessPhoneNumber(contactPrimaryValue);

                                phoneCheck.enqueue(new Callback<String>() {
                                    @Override
                                    public void onResponse(Response<String> response, Retrofit retrofit) {
                                        hidepDialog();
                                        if(response.body().equals("Yes")){
                                            uniquePhoneNumber = true;
                                        }
                                        else {
                                            uniquePhoneNumber = false;
                                            Toast.makeText(getApplicationContext(),"Customer is already registered with this number \n Change number",Toast.LENGTH_LONG).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Throwable t) {
                                        hidepDialog();
                                        Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                        }
                        else {
                            validPhoneNumber = true;
                            uniquePhoneNumber = true;
                        }
                    }
                });

                email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if(!hasFocus && !email.getText().toString().equals(previousEmail)){
                            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(ipClass.ipAddress).
                                            addConverterFactory(GsonConverterFactory.create())
                                    .build();
                            final APIService service = retrofit.create(APIService.class);
                            showpDialog();

                            Call<String> emailCheck = service.ProcessEmail(email.getText().toString());

                            emailCheck.enqueue(new Callback<String>() {
                                @Override
                                public void onResponse(Response<String> response, Retrofit retrofit) {
                                    hidepDialog();
                                    if(response.body().equals("Yes")){
                                        uniqueEmail = true;
                                    }
                                    else {
                                        uniqueEmail = false;
                                        Toast.makeText(getApplicationContext(),"Customer is already registered with this email \n Change email",Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onFailure(Throwable t) {
                                    hidepDialog();
                                    Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        else {
                            uniqueEmail = true;
                        }
                    }
                });

                showpDialog();
                Call<OrderDetail> orderData = service.ProcessOrderDetail(orderId);

                orderData.enqueue(new Callback<OrderDetail>() {
                    @Override
                    public void onResponse(Response<OrderDetail> response, Retrofit retrofit) {
                        if (response.body() != null) {
                            hidepDialog();
                            expectedDateValue = "";
                            for (int i = 0; i < orderTypes.size(); i++) {
                                if (response.body().getOrderType().equals(orderTypes.get(i))) {
                                    orderTypeIndex = i;
                                    break;
                                }
                            }
                            orderType.setSelection(orderTypeIndex);
                            orderType.setEnabled(false);
                            orderType.setClickable(false);
                            orderTypeValue = orderTypes.get(orderTypeIndex);
                            for (int i = 0; i < basePatterns.size(); i++) {
                                if (response.body().getBasePattern().equals(basePatterns.get(i))) {
                                    basePatternIndex = i;
                                    break;
                                }
                            }
                            basePattern.setSelection(basePatternIndex);
                            for (int i = 0; i < baseSizes.size(); i++) {
                                if (response.body().getBaseSize().equals(baseSizes.get(i))) {
                                    baseSizeIndex = i;
                                    break;
                                }
                            }
                            baseSize.setSelection(baseSizeIndex);
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        hidepDialog();
                    }
                });


                showpDialog();
                Call<Measurements> measurementsData = service.ProcessMeasurement(customerId, orderId);

                measurementsData.enqueue(new Callback<Measurements>() {
                    @Override
                    public void onResponse(Response<Measurements> response, Retrofit retrofit) {
                        if (response.body() != null) {
                            hidepDialog();

                            measurementId = response.body().getMeasurementId();

                            stanceId = response.body().getStanceId();
                            stanceValue = setValue(stanceList, makeSelection(stanceList, stanceId, stanceAdapter));
                            stanceImgValue = setImgValue(stanceList, makeSelection(stanceList, stanceId, stanceAdapter));

                            hipDescriptionId = response.body().getHipId();
                            hipDescriptionValue = setValue(hipDescriptionList, makeSelection(hipDescriptionList, hipDescriptionId, hipDescriptionAdapter));
                            hipDescriptionImgValue = setImgValue(hipDescriptionList, makeSelection(hipDescriptionList, hipDescriptionId, hipDescriptionAdapter));


                            if (orderTypeValue.equals("MTM")) {


                                hipSignBtn.setText(response.body().getHipMeasurement().substring(0, 1));
                                hip.setText(response.body().getHipMeasurement().substring(1));

                                fullBackLengthSignBtn.setText(response.body().getFullBackLength().substring(0, 1));
                                fullBackLength.setText(response.body().getFullBackLength().substring(1));

                                trouserWaistSignBtn.setText(response.body().getTrouserWaist().substring(0, 1));
                                trouserWaist.setText(response.body().getTrouserWaist().substring(1));

                                trouserOutseamSignBtn.setText(response.body().getTrouserOutseam().substring(0, 1));
                                trouserOutseam.setText(response.body().getTrouserOutseam().substring(1));

                                trouserInseamSignBtn.setText(response.body().getTrouserInseam().substring(0, 1));
                                trouserInseam.setText(response.body().getTrouserInseam().substring(1));

                                thighSignBtn.setText(response.body().getThigh().substring(0, 1));
                                thigh.setText(response.body().getThigh().substring(1));

                                kneeSignBtn.setText(response.body().getKnee().substring(0, 1));
                                knee.setText(response.body().getKnee().substring(1));

                            } else {

                                hip.setText(response.body().getHipMeasurement());

                                fullBackLength.setText(response.body().getFullBackLength());

                                trouserWaist.setText(response.body().getTrouserWaist());

                                trouserOutseam.setText(response.body().getTrouserOutseam());

                                trouserInseam.setText(response.body().getTrouserInseam());

                                thigh.setText(response.body().getThigh());

                                knee.setText(response.body().getKnee());

                            }
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        hidepDialog();
                        Toast.makeText(getApplicationContext(),"Error in MM",Toast.LENGTH_LONG).show();
                    }
                });

                showpDialog();
                Call<Stylings> stylingsData = service.ProcessStyling(customerId, orderId);

                stylingsData.enqueue(new Callback<Stylings>() {
                    @Override
                    public void onResponse(Response<Stylings> response, Retrofit retrofit) {
                        hidepDialog();
                        if (response.body() != null) {

                            stylingId = response.body().getStylingId();

                            previousFabricId = response.body().getFabricId();

                            fabricIdValue = response.body().getFabricId();
                            if(fabricIdValue.equals("0")){
                                for (int i = 0; i < fabricChoices.size(); i++) {
                                    if (response.body().getFabricNumber().equals(fabricChoices.get(i))) {
                                        fabricChoiceIndex = i;
                                        break;
                                    }
                                }
                                fabricChoice.setSelection(fabricChoiceIndex);
                                fabricChoiceValue = fabricChoices.get(fabricChoiceIndex);
                                fabricNumberValue = fabricChoices.get(fabricChoiceIndex);
                                fabricNumberData.setText(fabricNumberValue);
                                fabricPrice = 0;
                                fabricText.setVisibility(View.GONE);
                                clearFabricSelection.setVisibility(View.GONE);
                                fabricStyle.setVisibility(View.GONE);
                                fabricSearch.setVisibility(View.GONE);
                                calculateAmount();
                                calculateBill();
                            }
                            else {
                                makeFabricLiningSelection(fabricList, fabricIdValue, fabricNumberValue, fabricStyleAdapter, fabricNumberData);
                                if(orderTypeValue.equals("Bespoke")){
                                    fabricPrice = Integer.parseInt(fabricList.get(getFabricLiningIndex(fabricList,fabricIdValue)).getItemBsTrouserPrice());
                                }
                                else if(orderTypeValue.equals("MTM")) {
                                    fabricPrice = Integer.parseInt(fabricList.get(getFabricLiningIndex(fabricList,fabricIdValue)).getItemMtmTrouserPrice());
                                }


                            }
                            calculateAmount();
                            calculateBill();

                            trouserPleatsId = response.body().getTrouserPleatId();
                            trouserPleatsValue = setValue(trouserPleatsList, makeSelection(trouserPleatsList, trouserPleatsId, trouserPleatsAdapter));
                            trouserPleatsImgValue = setImgValue(trouserPleatsList, makeSelection(trouserPleatsList, trouserPleatsId, trouserPleatsAdapter));
                            trouserPleatsPrice = setPriceValue(trouserPleatsList, makeSelection(trouserPleatsList, trouserPleatsId, trouserPleatsAdapter));


                            trouserBackPocketId = response.body().getTrouserBackPocketId();
                            trouserBackPocketValue = setValue(trouserBackPocketList, makeSelection(trouserBackPocketList, trouserBackPocketId, trouserBackPocketAdapter));
                            trouserBackPocketImgValue = setImgValue(trouserBackPocketList, makeSelection(trouserBackPocketList, trouserBackPocketId, trouserBackPocketAdapter));
                            trouserBackPocketPrice = setPriceValue(trouserBackPocketList, makeSelection(trouserBackPocketList, trouserBackPocketId, trouserBackPocketAdapter));


                            trouserCuffId = response.body().getTrouserCuffId();
                            trouserCuffValue = setValue(trouserCuffList, makeSelection(trouserCuffList, trouserCuffId, trouserCuffAdapter));
                            trouserCuffImgValue = setImgValue(trouserCuffList, makeSelection(trouserCuffList, trouserCuffId, trouserCuffAdapter));
                            trouserCuffPrice = setPriceValue(trouserCuffList, makeSelection(trouserCuffList, trouserCuffId, trouserCuffAdapter));


                            trouserLoopTabId = response.body().getTrouserLoopTabId();
                            trouserLoopTabValue = setValue(trouserLoopTabList, makeSelection(trouserLoopTabList, trouserLoopTabId, trouserLoopTabAdapter));
                            trouserLoopTabImgValue = setImgValue(trouserLoopTabList, makeSelection(trouserLoopTabList, trouserLoopTabId, trouserLoopTabAdapter));
                            trouserLoopTabPrice = setPriceValue(trouserLoopTabList, makeSelection(trouserLoopTabList, trouserLoopTabId, trouserLoopTabAdapter));


                            trouserFitId = response.body().getTrouserFitId();
                            trouserFitValue = setValue(trouserFitList, makeSelection(trouserFitList, trouserFitId, trouserFitAdapter));
                            trouserFitImgValue = setImgValue(trouserFitList, makeSelection(trouserFitList, trouserFitId, trouserFitAdapter));
                            trouserFitPrice = setPriceValue(trouserFitList, makeSelection(trouserFitList, trouserFitId, trouserFitAdapter));


                            calculateAmount();
                            calculateBill();
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        hidepDialog();
                        Toast.makeText(getApplicationContext(), "Connection Error", Toast.LENGTH_SHORT).show();
                    }
                });

            }

            catch (NullPointerException e){
                finish();
                startActivity(getIntent());
            }
        }

        hidepDialog();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imageStatus = ((ZeitGeist) getApplication()).getImageStatus();
                images = ((ZeitGeist) getApplication()).getImages();
                image_file1 = ((ZeitGeist) getApplication()).getImage_file1();
                image_file2 = ((ZeitGeist) getApplication()).getImage_file2();
                image_file3 = ((ZeitGeist) getApplication()).getImage_file3();
                image_file4 = ((ZeitGeist) getApplication()).getImage_file4();
                image_file5 = ((ZeitGeist) getApplication()).getImage_file5();
                image_file6 = ((ZeitGeist) getApplication()).getImage_file6();
                image_file7 = ((ZeitGeist) getApplication()).getImage_file7();
                image_file8 = ((ZeitGeist) getApplication()).getImage_file8();
                image_file9 = ((ZeitGeist) getApplication()).getImage_file9();
                image_file10 = ((ZeitGeist) getApplication()).getImage_file10();

                AlertDialog.Builder builder = new AlertDialog.Builder(OrderTakingPant.this);
                builder.setTitle("Place Order Now?");
                builder.setMessage("Edit details or place order");
                builder.setPositiveButton("Place Order", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        submit.setEnabled(false);

                        if(orderCondition.equals("New Customer")){

                            nameValue = name.getText().toString();
                            contactPrimaryValue = "03" + contactPrimaryTwo.getText().toString() + contactPrimarySeven.getText().toString();

                            if(!contactSecondarySeven.getText().toString().equals("") && !contactSecondaryTwo.getText().toString().equals("")){
                                contactSecondaryValue = "03" + contactSecondaryTwo.getText().toString() + contactSecondarySeven.getText().toString();
                            }
                            else {
                                contactSecondaryValue = "";
                            }
                            emailValue = email.getText().toString();
                            addressValue = address.getText().toString();
                            orderTypeValue = orderTypes.get(0);
                            orderTypeValue = orderTypes.get(Integer.parseInt(orderType.getSelectedItem().toString()));
                            cityValue = cities.get(0);
                            cityValue = cities.get(Integer.parseInt(city.getSelectedItem().toString()));
                            billValue = bill.getText().toString();

                            if(!nameValue.equals("") && !contactPrimaryValue.equals("") && !emailValue.equals("") && !addressValue.equals("") && !billValue.equals("") && !expectedDateValue.equals("") && uniquePhoneNumber && validPhoneNumber && validSecondaryPhone && uniqueEmail){

                                hipValue = hip.getText().toString();
                                fullBackLengthValue = fullBackLength.getText().toString();
                                trouserWaistValue = trouserWaist.getText().toString();
                                trouserOutseamValue = trouserOutseam.getText().toString();
                                trouserInseamValue = trouserInseam.getText().toString();
                                thighValue = thigh.getText().toString();
                                kneeValue = knee.getText().toString();

                                fabricNumberValue = fabricNumberData.getText().toString();

                                if(!fabricNumberValue.equals("") && trouserPleatsValue != null && trouserBackPocketValue != null && trouserCuffValue != null && trouserLoopTabValue != null && trouserFitValue != null && trouserPleatsImgValue != null && trouserBackPocketImgValue != null && trouserCuffImgValue != null && trouserLoopTabImgValue != null && trouserFitImgValue != null){

                                    if(orderTypeValue.equals("Bespoke")) {
                                        if (stanceValue != null && stanceImgValue != null && hipDescriptionValue != null && hipDescriptionImgValue != null && !hipValue.equals("") && !fullBackLengthValue.equals("") && !trouserWaistValue.equals("") && !trouserOutseamValue.equals("") && !trouserInseamValue.equals("") && !thighValue.equals("") && !kneeValue.equals("")) {

                                            Toast.makeText(getApplicationContext(),"Please wait...",Toast.LENGTH_LONG).show();

                                            Retrofit retrofit = new Retrofit.Builder()
                                                    .baseUrl(ipClass.ipAddress).
                                                            addConverterFactory(GsonConverterFactory.create())
                                                    .build();
                                            final APIService service = retrofit.create(APIService.class);


                                            Call<String> fabricConsumptionData = service.ProcessFabricConsumption(fabricConnsumption,fabricIdValue);

                                            fabricConsumptionData.enqueue(new Callback<String>() {
                                                @Override
                                                public void onResponse(Response<String> response, Retrofit retrofit) {
                                                    if(response.body().equals("Yes")){

                                                        Call<String> newCustomerData = service.ProcessNewCustomerDetails(nameValue,contactPrimaryValue,contactSecondaryValue,emailValue,cityValue,addressValue);

                                                        newCustomerData.enqueue(new Callback<String>() {
                                                            @Override
                                                            public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                customerId = response.body();

                                                                if(customerId != null){
                                                                    orderId = orderTypes.get(Integer.parseInt(orderType.getSelectedItem().toString()))+getCurrentTimeStamp()+"_"+customerId;

                                                                    Retrofit retrofit1 = new Retrofit.Builder()
                                                                            .baseUrl(ipClass.ipAddress).
                                                                                    addConverterFactory(GsonConverterFactory.create())
                                                                            .build();
                                                                    final APIService service = retrofit1.create(APIService.class);


                                                                    Call<String> orderData = service.InsertOrder(orderId,customerId,shopId,itemType,"Pending Review",billValue,expectedDateValue,orderTypeValue,basePatternValue,baseSizeValue,fabricIdValue,"");

                                                                    orderData.enqueue(new Callback<String>() {
                                                                        @Override
                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {

                                                                            if(response.body().equals("Order Inserted")){
                                                                                if(imageStatus[0]){
                                                                                    Retrofit retrofit2 = new Retrofit.Builder()
                                                                                            .baseUrl(ipClass.ipAddress).
                                                                                                    addConverterFactory(GsonConverterFactory.create())
                                                                                            .build();
                                                                                    final APIService service = retrofit2.create(APIService.class);

                                                                                    Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_1","1",imageToString(images[0]));

                                                                                    imageData.enqueue(new Callback<String>() {
                                                                                        @Override
                                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                            if(image_file1.exists()) {
                                                                                                image_file1.delete();
                                                                                            }
                                                                                        }

                                                                                        @Override
                                                                                        public void onFailure(Throwable t) {
                                                                                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                    });
                                                                                }

                                                                                if(imageStatus[1]){
                                                                                    Retrofit retrofit2 = new Retrofit.Builder()
                                                                                            .baseUrl(ipClass.ipAddress).
                                                                                                    addConverterFactory(GsonConverterFactory.create())
                                                                                            .build();
                                                                                    final APIService service = retrofit2.create(APIService.class);

                                                                                    Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_2","2",imageToString(images[1]));

                                                                                    imageData.enqueue(new Callback<String>() {
                                                                                        @Override
                                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                            if(image_file2.exists()) {
                                                                                                image_file2.delete();
                                                                                            }
                                                                                        }

                                                                                        @Override
                                                                                        public void onFailure(Throwable t) {
                                                                                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                    });
                                                                                }

                                                                                if(imageStatus[2]){
                                                                                    Retrofit retrofit2 = new Retrofit.Builder()
                                                                                            .baseUrl(ipClass.ipAddress).
                                                                                                    addConverterFactory(GsonConverterFactory.create())
                                                                                            .build();
                                                                                    final APIService service = retrofit2.create(APIService.class);

                                                                                    Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_3","3",imageToString(images[2]));

                                                                                    imageData.enqueue(new Callback<String>() {
                                                                                        @Override
                                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                            if(image_file3.exists()) {
                                                                                                image_file3.delete();
                                                                                            }
                                                                                        }

                                                                                        @Override
                                                                                        public void onFailure(Throwable t) {
                                                                                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                    });
                                                                                }

                                                                                if(imageStatus[3]){
                                                                                    Retrofit retrofit2 = new Retrofit.Builder()
                                                                                            .baseUrl(ipClass.ipAddress).
                                                                                                    addConverterFactory(GsonConverterFactory.create())
                                                                                            .build();
                                                                                    final APIService service = retrofit2.create(APIService.class);

                                                                                    Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_4","4",imageToString(images[3]));

                                                                                    imageData.enqueue(new Callback<String>() {
                                                                                        @Override
                                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                            if(image_file4.exists()) {
                                                                                                image_file4.delete();
                                                                                            }
                                                                                        }

                                                                                        @Override
                                                                                        public void onFailure(Throwable t) {
                                                                                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                    });
                                                                                }

                                                                                if(imageStatus[4]){
                                                                                    Retrofit retrofit2 = new Retrofit.Builder()
                                                                                            .baseUrl(ipClass.ipAddress).
                                                                                                    addConverterFactory(GsonConverterFactory.create())
                                                                                            .build();
                                                                                    final APIService service = retrofit2.create(APIService.class);

                                                                                    Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_5","5",imageToString(images[4]));

                                                                                    imageData.enqueue(new Callback<String>() {
                                                                                        @Override
                                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                            if(image_file5.exists()) {
                                                                                                image_file5.delete();
                                                                                            }
                                                                                        }

                                                                                        @Override
                                                                                        public void onFailure(Throwable t) {
                                                                                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                    });
                                                                                }

                                                                                if(imageStatus[5]){
                                                                                    Retrofit retrofit2 = new Retrofit.Builder()
                                                                                            .baseUrl(ipClass.ipAddress).
                                                                                                    addConverterFactory(GsonConverterFactory.create())
                                                                                            .build();
                                                                                    final APIService service = retrofit2.create(APIService.class);

                                                                                    Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_6","6",imageToString(images[5]));

                                                                                    imageData.enqueue(new Callback<String>() {
                                                                                        @Override
                                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                            if(image_file6.exists()) {
                                                                                                image_file6.delete();
                                                                                            }
                                                                                        }

                                                                                        @Override
                                                                                        public void onFailure(Throwable t) {
                                                                                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                    });
                                                                                }

                                                                                if(imageStatus[6]){
                                                                                    Retrofit retrofit2 = new Retrofit.Builder()
                                                                                            .baseUrl(ipClass.ipAddress).
                                                                                                    addConverterFactory(GsonConverterFactory.create())
                                                                                            .build();
                                                                                    final APIService service = retrofit2.create(APIService.class);

                                                                                    Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_7","7",imageToString(images[6]));

                                                                                    imageData.enqueue(new Callback<String>() {
                                                                                        @Override
                                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                            if(image_file7.exists()) {
                                                                                                image_file7.delete();
                                                                                            }
                                                                                        }

                                                                                        @Override
                                                                                        public void onFailure(Throwable t) {
                                                                                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                    });
                                                                                }

                                                                                if(imageStatus[7]){
                                                                                    Retrofit retrofit2 = new Retrofit.Builder()
                                                                                            .baseUrl(ipClass.ipAddress).
                                                                                                    addConverterFactory(GsonConverterFactory.create())
                                                                                            .build();
                                                                                    final APIService service = retrofit2.create(APIService.class);

                                                                                    Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_8","8",imageToString(images[7]));

                                                                                    imageData.enqueue(new Callback<String>() {
                                                                                        @Override
                                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                            if(image_file8.exists()) {
                                                                                                image_file8.delete();
                                                                                            }
                                                                                        }

                                                                                        @Override
                                                                                        public void onFailure(Throwable t) {
                                                                                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                    });
                                                                                }

                                                                                if(imageStatus[8]){
                                                                                    Retrofit retrofit2 = new Retrofit.Builder()
                                                                                            .baseUrl(ipClass.ipAddress).
                                                                                                    addConverterFactory(GsonConverterFactory.create())
                                                                                            .build();
                                                                                    final APIService service = retrofit2.create(APIService.class);

                                                                                    Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_9","9",imageToString(images[8]));

                                                                                    imageData.enqueue(new Callback<String>() {
                                                                                        @Override
                                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                            if(image_file9.exists()) {
                                                                                                image_file9.delete();
                                                                                            }
                                                                                        }

                                                                                        @Override
                                                                                        public void onFailure(Throwable t) {
                                                                                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                    });
                                                                                }

                                                                                if(imageStatus[9]){
                                                                                    Retrofit retrofit2 = new Retrofit.Builder()
                                                                                            .baseUrl(ipClass.ipAddress).
                                                                                                    addConverterFactory(GsonConverterFactory.create())
                                                                                            .build();
                                                                                    final APIService service = retrofit2.create(APIService.class);

                                                                                    Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_10","10",imageToString(images[9]));

                                                                                    imageData.enqueue(new Callback<String>() {
                                                                                        @Override
                                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                            if(image_file10.exists()) {
                                                                                                image_file10.delete();
                                                                                            }
                                                                                        }

                                                                                        @Override
                                                                                        public void onFailure(Throwable t) {
                                                                                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                    });
                                                                                }

                                                                                if(fabricChoiceValue.equals("Self")){
                                                                                    Call<String> customFabricData = service.InsertCustomerFabric(orderId);

                                                                                    customFabricData.enqueue(new Callback<String>() {
                                                                                        @Override
                                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                        }

                                                                                        @Override
                                                                                        public void onFailure(Throwable t) {
                                                                                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                    });
                                                                                }

                                                                                Call<String> measurementData = service.InsertMeasurement(orderId,customerId,stanceId,stanceValue,stanceImgValue,"",
                                                                                        "","","","","","",
                                                                                        "","",hipDescriptionId,hipDescriptionValue,hipDescriptionImgValue,"","",
                                                                                        "","","","","","",hipValue,"",
                                                                                        "","","","",fullBackLengthValue,"",
                                                                                        trouserWaistValue,trouserOutseamValue,trouserInseamValue,"",thighValue,kneeValue,"","");


                                                                                measurementData.enqueue(new Callback<String>() {
                                                                                    @Override
                                                                                    public void onResponse(Response<String> response, Retrofit retrofit) {


                                                                                        Call<String> stylingData = service.InsertStyling(orderId,customerId,fabricIdValue,fabricNumberValue,"","",trouserFitId,trouserFitValue,trouserFitImgValue,
                                                                                                "","","","","","",
                                                                                                "","","","","","","","","",
                                                                                                "","","","","","",
                                                                                                "","","","","","","","","",
                                                                                                "","","","","","","",
                                                                                                "","",trouserPleatsId,trouserPleatsValue,trouserPleatsImgValue,trouserBackPocketId,trouserBackPocketValue,
                                                                                                trouserBackPocketImgValue,trouserCuffId,trouserCuffValue,trouserCuffImgValue,trouserLoopTabId,trouserLoopTabValue,trouserLoopTabImgValue,
                                                                                                "","","","","","","",
                                                                                                "","","","","","","",
                                                                                                "","","","","","","",
                                                                                                "","","","","","","","","");


                                                                                        stylingData.enqueue(new Callback<String>() {
                                                                                            @Override
                                                                                            public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                                if(response.body().equals("Successful")){

                                                                                                    Call<String> timeline = service.ProcessStatusTimeline(orderId,"Pending Review");

                                                                                                    timeline.enqueue(new Callback<String>() {
                                                                                                        @Override
                                                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {

                                                                                                            Intent intent = new Intent(getApplicationContext(),PlaceOrderActivity.class);
                                                                                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                                                                            startActivity(intent);
                                                                                                            finish();
                                                                                                        }

                                                                                                        @Override
                                                                                                        public void onFailure(Throwable t) {

                                                                                                        }
                                                                                                    });

                                                                                                }
                                                                                                else{
                                                                                                    Toast.makeText(getApplicationContext(),"Can't place order",Toast.LENGTH_LONG).show();
                                                                                                }
                                                                                            }

                                                                                            @Override
                                                                                            public void onFailure(Throwable t) {
                                                                                                Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                            }
                                                                                        });
                                                                                    }

                                                                                    @Override
                                                                                    public void onFailure(Throwable t) {
                                                                                        Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                    }
                                                                                });

                                                                            }

                                                                        }

                                                                        @Override
                                                                        public void onFailure(Throwable t) {
                                                                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_LONG).show();
                                                                        }
                                                                    });
                                                                }
                                                            }

                                                            @Override
                                                            public void onFailure(Throwable t) {
                                                                Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_LONG).show();
                                                            }
                                                        });


                                                    }
                                                    else {
                                                        Toast.makeText(getApplicationContext(),"Fabric not available",Toast.LENGTH_SHORT).show();
                                                    }
                                                }

                                                @Override
                                                public void onFailure(Throwable t) {
                                                    Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                }
                                            });

                                        }
                                        else {
                                            Toast.makeText(getApplicationContext(), "Please complete Measurement Section", Toast.LENGTH_LONG).show();
                                        }


                                    }

                                    else{

                                        if (stanceValue != null && stanceImgValue != null && hipDescriptionValue != null && hipDescriptionImgValue != null){


                                            Toast.makeText(getApplicationContext(),"Please wait...",Toast.LENGTH_LONG).show();

                                            Retrofit retrofit = new Retrofit.Builder()
                                                    .baseUrl(ipClass.ipAddress).
                                                            addConverterFactory(GsonConverterFactory.create())
                                                    .build();
                                            final APIService service = retrofit.create(APIService.class);

                                            Call<String> fabricConsumptionData = service.ProcessFabricConsumption(fabricConnsumption,fabricIdValue);

                                            fabricConsumptionData.enqueue(new Callback<String>() {
                                                @Override
                                                public void onResponse(Response<String> response, Retrofit retrofit) {
                                                    if(response.body().equals("Yes")){

                                                        Call<String> newCustomerData = service.ProcessNewCustomerDetails(nameValue,contactPrimaryValue,contactSecondaryValue,emailValue,cityValue,addressValue);

                                                        newCustomerData.enqueue(new Callback<String>() {
                                                            @Override
                                                            public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                customerId = response.body();
                                                                if(customerId != null){
                                                                    orderId = orderTypes.get(Integer.parseInt(orderType.getSelectedItem().toString()))+getCurrentTimeStamp()+"_"+customerId;
                                                                    Retrofit retrofit1 = new Retrofit.Builder()
                                                                            .baseUrl(ipClass.ipAddress).
                                                                                    addConverterFactory(GsonConverterFactory.create())
                                                                            .build();
                                                                    final APIService service = retrofit1.create(APIService.class);


                                                                    Call<String> orderData = service.InsertOrder(orderId,customerId,shopId,itemType,"Pending Review",billValue,expectedDateValue,orderTypeValue,basePatternValue,baseSizeValue,fabricIdValue,"");

                                                                    orderData.enqueue(new Callback<String>() {
                                                                        @Override
                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {

                                                                            if(response.body().equals("Order Inserted")){
                                                                                if(imageStatus[0]){
                                                                                    Retrofit retrofit2 = new Retrofit.Builder()
                                                                                            .baseUrl(ipClass.ipAddress).
                                                                                                    addConverterFactory(GsonConverterFactory.create())
                                                                                            .build();
                                                                                    final APIService service = retrofit2.create(APIService.class);

                                                                                    Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_1","1",imageToString(images[0]));

                                                                                    imageData.enqueue(new Callback<String>() {
                                                                                        @Override
                                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                            if(image_file1.exists()) {
                                                                                                image_file1.delete();
                                                                                            }
                                                                                        }

                                                                                        @Override
                                                                                        public void onFailure(Throwable t) {
                                                                                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                    });
                                                                                }

                                                                                if(imageStatus[1]){
                                                                                    Retrofit retrofit2 = new Retrofit.Builder()
                                                                                            .baseUrl(ipClass.ipAddress).
                                                                                                    addConverterFactory(GsonConverterFactory.create())
                                                                                            .build();
                                                                                    final APIService service = retrofit2.create(APIService.class);

                                                                                    Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_2","2",imageToString(images[1]));

                                                                                    imageData.enqueue(new Callback<String>() {
                                                                                        @Override
                                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                            if(image_file2.exists()) {
                                                                                                image_file2.delete();
                                                                                            }
                                                                                        }

                                                                                        @Override
                                                                                        public void onFailure(Throwable t) {
                                                                                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                    });
                                                                                }

                                                                                if(imageStatus[2]){
                                                                                    Retrofit retrofit2 = new Retrofit.Builder()
                                                                                            .baseUrl(ipClass.ipAddress).
                                                                                                    addConverterFactory(GsonConverterFactory.create())
                                                                                            .build();
                                                                                    final APIService service = retrofit2.create(APIService.class);

                                                                                    Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_3","3",imageToString(images[2]));

                                                                                    imageData.enqueue(new Callback<String>() {
                                                                                        @Override
                                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                            if(image_file3.exists()) {
                                                                                                image_file3.delete();
                                                                                            }
                                                                                        }

                                                                                        @Override
                                                                                        public void onFailure(Throwable t) {
                                                                                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                    });
                                                                                }

                                                                                if(imageStatus[3]){
                                                                                    Retrofit retrofit2 = new Retrofit.Builder()
                                                                                            .baseUrl(ipClass.ipAddress).
                                                                                                    addConverterFactory(GsonConverterFactory.create())
                                                                                            .build();
                                                                                    final APIService service = retrofit2.create(APIService.class);

                                                                                    Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_4","4",imageToString(images[3]));

                                                                                    imageData.enqueue(new Callback<String>() {
                                                                                        @Override
                                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                            if(image_file4.exists()) {
                                                                                                image_file4.delete();
                                                                                            }
                                                                                        }

                                                                                        @Override
                                                                                        public void onFailure(Throwable t) {
                                                                                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                    });
                                                                                }

                                                                                if(imageStatus[4]){
                                                                                    Retrofit retrofit2 = new Retrofit.Builder()
                                                                                            .baseUrl(ipClass.ipAddress).
                                                                                                    addConverterFactory(GsonConverterFactory.create())
                                                                                            .build();
                                                                                    final APIService service = retrofit2.create(APIService.class);

                                                                                    Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_5","5",imageToString(images[4]));

                                                                                    imageData.enqueue(new Callback<String>() {
                                                                                        @Override
                                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                            if(image_file5.exists()) {
                                                                                                image_file5.delete();
                                                                                            }
                                                                                        }

                                                                                        @Override
                                                                                        public void onFailure(Throwable t) {
                                                                                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                    });
                                                                                }

                                                                                if(imageStatus[5]){
                                                                                    Retrofit retrofit2 = new Retrofit.Builder()
                                                                                            .baseUrl(ipClass.ipAddress).
                                                                                                    addConverterFactory(GsonConverterFactory.create())
                                                                                            .build();
                                                                                    final APIService service = retrofit2.create(APIService.class);

                                                                                    Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_6","6",imageToString(images[5]));

                                                                                    imageData.enqueue(new Callback<String>() {
                                                                                        @Override
                                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                            if(image_file6.exists()) {
                                                                                                image_file6.delete();
                                                                                            }
                                                                                        }

                                                                                        @Override
                                                                                        public void onFailure(Throwable t) {
                                                                                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                    });
                                                                                }

                                                                                if(imageStatus[6]){
                                                                                    Retrofit retrofit2 = new Retrofit.Builder()
                                                                                            .baseUrl(ipClass.ipAddress).
                                                                                                    addConverterFactory(GsonConverterFactory.create())
                                                                                            .build();
                                                                                    final APIService service = retrofit2.create(APIService.class);

                                                                                    Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_7","7",imageToString(images[6]));

                                                                                    imageData.enqueue(new Callback<String>() {
                                                                                        @Override
                                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                            if(image_file7.exists()) {
                                                                                                image_file7.delete();
                                                                                            }
                                                                                        }

                                                                                        @Override
                                                                                        public void onFailure(Throwable t) {
                                                                                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                    });
                                                                                }

                                                                                if(imageStatus[7]){
                                                                                    Retrofit retrofit2 = new Retrofit.Builder()
                                                                                            .baseUrl(ipClass.ipAddress).
                                                                                                    addConverterFactory(GsonConverterFactory.create())
                                                                                            .build();
                                                                                    final APIService service = retrofit2.create(APIService.class);

                                                                                    Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_8","8",imageToString(images[7]));

                                                                                    imageData.enqueue(new Callback<String>() {
                                                                                        @Override
                                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                            if(image_file8.exists()) {
                                                                                                image_file8.delete();
                                                                                            }
                                                                                        }

                                                                                        @Override
                                                                                        public void onFailure(Throwable t) {
                                                                                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                    });
                                                                                }

                                                                                if(imageStatus[8]){
                                                                                    Retrofit retrofit2 = new Retrofit.Builder()
                                                                                            .baseUrl(ipClass.ipAddress).
                                                                                                    addConverterFactory(GsonConverterFactory.create())
                                                                                            .build();
                                                                                    final APIService service = retrofit2.create(APIService.class);

                                                                                    Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_9","9",imageToString(images[8]));

                                                                                    imageData.enqueue(new Callback<String>() {
                                                                                        @Override
                                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                            if(image_file9.exists()) {
                                                                                                image_file9.delete();
                                                                                            }
                                                                                        }

                                                                                        @Override
                                                                                        public void onFailure(Throwable t) {
                                                                                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                    });
                                                                                }

                                                                                if(imageStatus[9]){
                                                                                    Retrofit retrofit2 = new Retrofit.Builder()
                                                                                            .baseUrl(ipClass.ipAddress).
                                                                                                    addConverterFactory(GsonConverterFactory.create())
                                                                                            .build();
                                                                                    final APIService service = retrofit2.create(APIService.class);

                                                                                    Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_10","10",imageToString(images[9]));

                                                                                    imageData.enqueue(new Callback<String>() {
                                                                                        @Override
                                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                            if(image_file10.exists()) {
                                                                                                image_file10.delete();
                                                                                            }
                                                                                        }

                                                                                        @Override
                                                                                        public void onFailure(Throwable t) {
                                                                                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                    });
                                                                                }

                                                                                if(fabricChoiceValue.equals("Self")){
                                                                                    Call<String> customFabricData = service.InsertCustomerFabric(orderId);

                                                                                    customFabricData.enqueue(new Callback<String>() {
                                                                                        @Override
                                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                        }

                                                                                        @Override
                                                                                        public void onFailure(Throwable t) {
                                                                                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                    });
                                                                                }

                                                                                Call<String> measurementData = service.InsertMeasurement(orderId,customerId,stanceId,stanceValue,stanceImgValue,"",
                                                                                        "","","","","","",
                                                                                        "","",hipDescriptionId,hipDescriptionValue,hipDescriptionImgValue,"","",
                                                                                        "","","","","","",hipSignBtn.getText().toString() + hipValue,"",
                                                                                        "","","","",fullBackLengthSignBtn.getText().toString() + fullBackLengthValue,"",
                                                                                        trouserWaistSignBtn.getText().toString() + trouserWaistValue,trouserOutseamSignBtn.getText().toString() + trouserOutseamValue,trouserInseamSignBtn.getText().toString() + trouserInseamValue,"",thighSignBtn.getText().toString() + thighValue,kneeSignBtn.getText().toString() + kneeValue,"","");


                                                                                measurementData.enqueue(new Callback<String>() {
                                                                                    @Override
                                                                                    public void onResponse(Response<String> response, Retrofit retrofit) {


                                                                                        Call<String> stylingData = service.InsertStyling(orderId,customerId,fabricIdValue,fabricNumberValue,"","",trouserFitId,trouserFitValue,trouserFitImgValue,
                                                                                                "","","","","","",
                                                                                                "","","","","","","","","",
                                                                                                "","","","","","",
                                                                                                "","","","","","","","","",
                                                                                                "","","","","","","",
                                                                                                "","",trouserPleatsId,trouserPleatsValue,trouserPleatsImgValue,trouserBackPocketId,trouserBackPocketValue,
                                                                                                trouserBackPocketImgValue,trouserCuffId,trouserCuffValue,trouserCuffImgValue,trouserLoopTabId,trouserLoopTabValue,trouserLoopTabImgValue,
                                                                                                "","","","","","","",
                                                                                                "","","","","","","",
                                                                                                "","","","","","","",
                                                                                                "","","","","","","","","");


                                                                                        stylingData.enqueue(new Callback<String>() {
                                                                                            @Override
                                                                                            public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                                if(response.body().equals("Successful")){

                                                                                                    Call<String> timeline = service.ProcessStatusTimeline(orderId,"Pending Review");

                                                                                                    timeline.enqueue(new Callback<String>() {
                                                                                                        @Override
                                                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {

                                                                                                            Intent intent = new Intent(getApplicationContext(),PlaceOrderActivity.class);
                                                                                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                                                                            startActivity(intent);
                                                                                                            finish();
                                                                                                        }

                                                                                                        @Override
                                                                                                        public void onFailure(Throwable t) {

                                                                                                        }
                                                                                                    });

                                                                                                }
                                                                                                else{
                                                                                                    Toast.makeText(getApplicationContext(),"Can't place order",Toast.LENGTH_LONG).show();
                                                                                                }
                                                                                            }

                                                                                            @Override
                                                                                            public void onFailure(Throwable t) {
                                                                                                Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                            }
                                                                                        });
                                                                                    }

                                                                                    @Override
                                                                                    public void onFailure(Throwable t) {
                                                                                        Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                    }
                                                                                });

                                                                            }

                                                                        }

                                                                        @Override
                                                                        public void onFailure(Throwable t) {
                                                                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_LONG).show();
                                                                        }
                                                                    });
                                                                }
                                                            }

                                                            @Override
                                                            public void onFailure(Throwable t) {
                                                                Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_LONG).show();
                                                            }
                                                        });

                                                    }
                                                    else {
                                                        Toast.makeText(getApplicationContext(),"Fabric not available",Toast.LENGTH_SHORT).show();
                                                    }
                                                }

                                                @Override
                                                public void onFailure(Throwable t) {
                                                    Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                        }
                                        else {
                                            Toast.makeText(getApplicationContext(),"Make all selections in Measurement Section",Toast.LENGTH_LONG).show();
                                        }

                                    }

                                }
                                else {
                                    Toast.makeText(getApplicationContext(),"Make all selections in Suit Styling Section",Toast.LENGTH_LONG).show();
                                }
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"Complete Form in Customer Info Section \n Check Phone Number and Email",Toast.LENGTH_LONG).show();
                            }

                        }
                        else if(orderCondition.equals("Existing Customer") || orderCondition.equals("As new order") ){

                            nameValue = name.getText().toString();
                            contactPrimaryValue = "03" + contactPrimaryTwo.getText().toString() + contactPrimarySeven.getText().toString();

                            if(!contactSecondarySeven.getText().toString().equals("") && !contactSecondaryTwo.getText().toString().equals("")){
                                contactSecondaryValue = "03" + contactSecondaryTwo.getText().toString() + contactSecondarySeven.getText().toString();
                            }
                            else {
                                contactSecondaryValue = "";
                            }
                            emailValue = email.getText().toString();
                            addressValue = address.getText().toString();
                            billValue = bill.getText().toString();
                            orderTypeValue = orderTypes.get(Integer.parseInt(orderType.getSelectedItem().toString()));
                            cityValue = cities.get(Integer.parseInt(city.getSelectedItem().toString()));

                            if(!nameValue.equals("") && !contactPrimaryValue.equals("") && !emailValue.equals("") && !addressValue.equals("") && !billValue.equals("") && !expectedDateValue.equals("")&& uniquePhoneNumber && validPhoneNumber && validSecondaryPhone && uniqueEmail){

                                hipValue = hip.getText().toString();
                                fullBackLengthValue = fullBackLength.getText().toString();
                                trouserWaistValue = trouserWaist.getText().toString();
                                trouserOutseamValue = trouserOutseam.getText().toString();
                                trouserInseamValue = trouserInseam.getText().toString();
                                //crotchValue = crotch.getText().toString();
                                thighValue = thigh.getText().toString();
                                kneeValue = knee.getText().toString();

                                fabricNumberValue = fabricNumberData.getText().toString();

                                if(!fabricNumberValue.equals("") && trouserPleatsValue != null && trouserBackPocketValue != null && trouserCuffValue != null && trouserLoopTabValue != null && trouserFitValue != null && trouserPleatsImgValue != null && trouserBackPocketImgValue != null && trouserCuffImgValue != null && trouserLoopTabImgValue != null && trouserFitImgValue != null){

                                    if(orderTypeValue.equals("Bespoke")) {
                                        if (stanceValue != null && stanceImgValue != null && hipDescriptionValue != null && hipDescriptionImgValue != null && !hipValue.equals("") && !fullBackLengthValue.equals("") && !trouserWaistValue.equals("") && !trouserOutseamValue.equals("") && !trouserInseamValue.equals("") && !thighValue.equals("") && !kneeValue.equals("")) {

                                            Toast.makeText(getApplicationContext(),"Please wait...",Toast.LENGTH_LONG).show();

                                            Retrofit retrofit = new Retrofit.Builder()
                                                    .baseUrl(ipClass.ipAddress).
                                                            addConverterFactory(GsonConverterFactory.create())
                                                    .build();
                                            final APIService service = retrofit.create(APIService.class);


                                            Call<String> fabricConsumptionData = service.ProcessFabricConsumption(fabricConnsumption,fabricIdValue);

                                            fabricConsumptionData.enqueue(new Callback<String>() {
                                                @Override
                                                public void onResponse(Response<String> response, Retrofit retrofit) {
                                                    if(response.body().equals("Yes")){

                                                        Call<String> existingCustomerData = service.ProcessExistingCustomerDetails(customerId,nameValue,contactPrimaryValue,contactSecondaryValue,emailValue,cityValue,addressValue);

                                                        existingCustomerData.enqueue(new Callback<String>() {
                                                            @Override
                                                            public void onResponse(Response<String> response, Retrofit retrofit) {

                                                                if(response.body().equals("Updated")){

                                                                    orderId = orderTypeValue+getCurrentTimeStamp()+"_"+customerId;

                                                                    Retrofit retrofit1 = new Retrofit.Builder()
                                                                            .baseUrl(ipClass.ipAddress).
                                                                                    addConverterFactory(GsonConverterFactory.create())
                                                                            .build();
                                                                    final APIService service = retrofit1.create(APIService.class);


                                                                    Call<String> orderData = service.InsertOrder(orderId,customerId,shopId,itemType,"Pending Review",billValue,expectedDateValue,orderTypeValue,basePatternValue,baseSizeValue,fabricIdValue,"");

                                                                    orderData.enqueue(new Callback<String>() {
                                                                        @Override
                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {

                                                                            if(response.body().equals("Order Inserted")){
                                                                                if(imageStatus[0]){
                                                                                    Retrofit retrofit2 = new Retrofit.Builder()
                                                                                            .baseUrl(ipClass.ipAddress).
                                                                                                    addConverterFactory(GsonConverterFactory.create())
                                                                                            .build();
                                                                                    final APIService service = retrofit2.create(APIService.class);

                                                                                    Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_1","1",imageToString(images[0]));

                                                                                    imageData.enqueue(new Callback<String>() {
                                                                                        @Override
                                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                            if(image_file1.exists()) {
                                                                                                image_file1.delete();
                                                                                            }
                                                                                        }

                                                                                        @Override
                                                                                        public void onFailure(Throwable t) {
                                                                                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                    });
                                                                                }

                                                                                if(imageStatus[1]){
                                                                                    Retrofit retrofit2 = new Retrofit.Builder()
                                                                                            .baseUrl(ipClass.ipAddress).
                                                                                                    addConverterFactory(GsonConverterFactory.create())
                                                                                            .build();
                                                                                    final APIService service = retrofit2.create(APIService.class);

                                                                                    Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_2","2",imageToString(images[1]));

                                                                                    imageData.enqueue(new Callback<String>() {
                                                                                        @Override
                                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                            if(image_file2.exists()) {
                                                                                                image_file2.delete();
                                                                                            }
                                                                                        }

                                                                                        @Override
                                                                                        public void onFailure(Throwable t) {
                                                                                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                    });
                                                                                }

                                                                                if(imageStatus[2]){
                                                                                    Retrofit retrofit2 = new Retrofit.Builder()
                                                                                            .baseUrl(ipClass.ipAddress).
                                                                                                    addConverterFactory(GsonConverterFactory.create())
                                                                                            .build();
                                                                                    final APIService service = retrofit2.create(APIService.class);

                                                                                    Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_3","3",imageToString(images[2]));

                                                                                    imageData.enqueue(new Callback<String>() {
                                                                                        @Override
                                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                            if(image_file3.exists()) {
                                                                                                image_file3.delete();
                                                                                            }
                                                                                        }

                                                                                        @Override
                                                                                        public void onFailure(Throwable t) {
                                                                                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                    });
                                                                                }

                                                                                if(imageStatus[3]){
                                                                                    Retrofit retrofit2 = new Retrofit.Builder()
                                                                                            .baseUrl(ipClass.ipAddress).
                                                                                                    addConverterFactory(GsonConverterFactory.create())
                                                                                            .build();
                                                                                    final APIService service = retrofit2.create(APIService.class);

                                                                                    Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_4","4",imageToString(images[3]));

                                                                                    imageData.enqueue(new Callback<String>() {
                                                                                        @Override
                                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                            if(image_file4.exists()) {
                                                                                                image_file4.delete();
                                                                                            }
                                                                                        }

                                                                                        @Override
                                                                                        public void onFailure(Throwable t) {
                                                                                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                    });
                                                                                }

                                                                                if(imageStatus[4]){
                                                                                    Retrofit retrofit2 = new Retrofit.Builder()
                                                                                            .baseUrl(ipClass.ipAddress).
                                                                                                    addConverterFactory(GsonConverterFactory.create())
                                                                                            .build();
                                                                                    final APIService service = retrofit2.create(APIService.class);

                                                                                    Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_5","5",imageToString(images[4]));

                                                                                    imageData.enqueue(new Callback<String>() {
                                                                                        @Override
                                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                            if(image_file5.exists()) {
                                                                                                image_file5.delete();
                                                                                            }
                                                                                        }

                                                                                        @Override
                                                                                        public void onFailure(Throwable t) {
                                                                                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                    });
                                                                                }

                                                                                if(imageStatus[5]){
                                                                                    Retrofit retrofit2 = new Retrofit.Builder()
                                                                                            .baseUrl(ipClass.ipAddress).
                                                                                                    addConverterFactory(GsonConverterFactory.create())
                                                                                            .build();
                                                                                    final APIService service = retrofit2.create(APIService.class);

                                                                                    Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_6","6",imageToString(images[5]));

                                                                                    imageData.enqueue(new Callback<String>() {
                                                                                        @Override
                                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                            if(image_file6.exists()) {
                                                                                                image_file6.delete();
                                                                                            }
                                                                                        }

                                                                                        @Override
                                                                                        public void onFailure(Throwable t) {
                                                                                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                    });
                                                                                }

                                                                                if(imageStatus[6]){
                                                                                    Retrofit retrofit2 = new Retrofit.Builder()
                                                                                            .baseUrl(ipClass.ipAddress).
                                                                                                    addConverterFactory(GsonConverterFactory.create())
                                                                                            .build();
                                                                                    final APIService service = retrofit2.create(APIService.class);

                                                                                    Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_7","7",imageToString(images[6]));

                                                                                    imageData.enqueue(new Callback<String>() {
                                                                                        @Override
                                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                            if(image_file7.exists()) {
                                                                                                image_file7.delete();
                                                                                            }
                                                                                        }

                                                                                        @Override
                                                                                        public void onFailure(Throwable t) {
                                                                                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                    });
                                                                                }

                                                                                if(imageStatus[7]){
                                                                                    Retrofit retrofit2 = new Retrofit.Builder()
                                                                                            .baseUrl(ipClass.ipAddress).
                                                                                                    addConverterFactory(GsonConverterFactory.create())
                                                                                            .build();
                                                                                    final APIService service = retrofit2.create(APIService.class);

                                                                                    Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_8","8",imageToString(images[7]));

                                                                                    imageData.enqueue(new Callback<String>() {
                                                                                        @Override
                                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                            if(image_file8.exists()) {
                                                                                                image_file8.delete();
                                                                                            }
                                                                                        }

                                                                                        @Override
                                                                                        public void onFailure(Throwable t) {
                                                                                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                    });
                                                                                }

                                                                                if(imageStatus[8]){
                                                                                    Retrofit retrofit2 = new Retrofit.Builder()
                                                                                            .baseUrl(ipClass.ipAddress).
                                                                                                    addConverterFactory(GsonConverterFactory.create())
                                                                                            .build();
                                                                                    final APIService service = retrofit2.create(APIService.class);

                                                                                    Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_9","9",imageToString(images[8]));

                                                                                    imageData.enqueue(new Callback<String>() {
                                                                                        @Override
                                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                            if(image_file9.exists()) {
                                                                                                image_file9.delete();
                                                                                            }
                                                                                        }

                                                                                        @Override
                                                                                        public void onFailure(Throwable t) {
                                                                                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                    });
                                                                                }

                                                                                if(imageStatus[9]){
                                                                                    Retrofit retrofit2 = new Retrofit.Builder()
                                                                                            .baseUrl(ipClass.ipAddress).
                                                                                                    addConverterFactory(GsonConverterFactory.create())
                                                                                            .build();
                                                                                    final APIService service = retrofit2.create(APIService.class);

                                                                                    Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_10","10",imageToString(images[9]));

                                                                                    imageData.enqueue(new Callback<String>() {
                                                                                        @Override
                                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                            if(image_file10.exists()) {
                                                                                                image_file10.delete();
                                                                                            }
                                                                                        }

                                                                                        @Override
                                                                                        public void onFailure(Throwable t) {
                                                                                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                    });
                                                                                }

                                                                                if(fabricChoiceValue.equals("Self")){
                                                                                    Call<String> customFabricData = service.InsertCustomerFabric(orderId);

                                                                                    customFabricData.enqueue(new Callback<String>() {
                                                                                        @Override
                                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                        }

                                                                                        @Override
                                                                                        public void onFailure(Throwable t) {
                                                                                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                    });
                                                                                }

                                                                                Call<String> measurementData = service.InsertMeasurement(orderId,customerId,stanceId,stanceValue,stanceImgValue,"",
                                                                                        "","","","","","",
                                                                                        "","",hipDescriptionId,hipDescriptionValue,hipDescriptionImgValue,"","",
                                                                                        "","","","","","",hipValue,"",
                                                                                        "","","","",fullBackLengthValue,"",
                                                                                        trouserWaistValue,trouserOutseamValue,trouserInseamValue,"",thighValue,kneeValue,"","");


                                                                                measurementData.enqueue(new Callback<String>() {
                                                                                    @Override
                                                                                    public void onResponse(Response<String> response, Retrofit retrofit) {


                                                                                        Call<String> stylingData = service.InsertStyling(orderId,customerId,fabricIdValue,fabricNumberValue,"","",trouserFitId,trouserFitValue,trouserFitImgValue,
                                                                                                "","","","","","",
                                                                                                "","","","","","","","","",
                                                                                                "","","","","","",
                                                                                                "","","","","","","","","",
                                                                                                "","","","","","","",
                                                                                                "","",trouserPleatsId,trouserPleatsValue,trouserPleatsImgValue,trouserBackPocketId,trouserBackPocketValue,
                                                                                                trouserBackPocketImgValue,trouserCuffId,trouserCuffValue,trouserCuffImgValue,trouserLoopTabId,trouserLoopTabValue,trouserLoopTabImgValue,
                                                                                                "","","","","","","",
                                                                                                "","","","","","","",
                                                                                                "","","","","","","",
                                                                                                "","","","","","","","","");


                                                                                        stylingData.enqueue(new Callback<String>() {
                                                                                            @Override
                                                                                            public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                                if(response.body().equals("Successful")){

                                                                                                    Call<String> timeline = service.ProcessStatusTimeline(orderId,"Pending Review");

                                                                                                    timeline.enqueue(new Callback<String>() {
                                                                                                        @Override
                                                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {

                                                                                                            Intent intent = new Intent(getApplicationContext(),PlaceOrderActivity.class);
                                                                                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                                                                            startActivity(intent);
                                                                                                            finish();
                                                                                                        }

                                                                                                        @Override
                                                                                                        public void onFailure(Throwable t) {

                                                                                                        }
                                                                                                    });

                                                                                                }
                                                                                                else{
                                                                                                    Toast.makeText(getApplicationContext(),"Can't place order",Toast.LENGTH_LONG).show();
                                                                                                }
                                                                                            }

                                                                                            @Override
                                                                                            public void onFailure(Throwable t) {
                                                                                                Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                            }
                                                                                        });
                                                                                    }

                                                                                    @Override
                                                                                    public void onFailure(Throwable t) {
                                                                                        Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                    }
                                                                                });

                                                                            }

                                                                        }

                                                                        @Override
                                                                        public void onFailure(Throwable t) {
                                                                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_LONG).show();
                                                                        }
                                                                    });
                                                                }
                                                            }

                                                            @Override
                                                            public void onFailure(Throwable t) {
                                                                Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_LONG).show();
                                                            }
                                                        });


                                                    }
                                                    else {
                                                        Toast.makeText(getApplicationContext(),"Fabric not available",Toast.LENGTH_SHORT).show();
                                                    }
                                                }

                                                @Override
                                                public void onFailure(Throwable t) {
                                                    Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                }
                                            });

                                        }
                                        else {
                                            Toast.makeText(getApplicationContext(), "Please complete Measurement Section", Toast.LENGTH_LONG).show();
                                        }


                                    }

                                    else{

                                        if (stanceValue != null && stanceImgValue != null && hipDescriptionValue != null && hipDescriptionImgValue != null){

                                            Toast.makeText(getApplicationContext(),"Please wait...",Toast.LENGTH_LONG).show();

                                            Retrofit retrofit = new Retrofit.Builder()
                                                    .baseUrl(ipClass.ipAddress).
                                                            addConverterFactory(GsonConverterFactory.create())
                                                    .build();
                                            final APIService service = retrofit.create(APIService.class);

                                            Call<String> fabricConsumptionData = service.ProcessFabricConsumption(fabricConnsumption,fabricIdValue);

                                            fabricConsumptionData.enqueue(new Callback<String>() {
                                                @Override
                                                public void onResponse(Response<String> response, Retrofit retrofit) {
                                                    if(response.body().equals("Yes")){

                                                        Call<String> existingCustomerData = service.ProcessExistingCustomerDetails(customerId,nameValue,contactPrimaryValue,contactSecondaryValue,emailValue,cityValue,addressValue);

                                                        existingCustomerData.enqueue(new Callback<String>() {
                                                            @Override
                                                            public void onResponse(Response<String> response, Retrofit retrofit) {

                                                                if(response.body().equals("Updated")){

                                                                    orderId = orderTypeValue+getCurrentTimeStamp()+"_"+customerId;

                                                                    Retrofit retrofit1 = new Retrofit.Builder()
                                                                            .baseUrl(ipClass.ipAddress).
                                                                                    addConverterFactory(GsonConverterFactory.create())
                                                                            .build();
                                                                    final APIService service = retrofit1.create(APIService.class);


                                                                    Call<String> orderData = service.InsertOrder(orderId,customerId,shopId,itemType,"Pending Review",billValue,expectedDateValue,orderTypeValue,basePatternValue,baseSizeValue,fabricIdValue,"");

                                                                    orderData.enqueue(new Callback<String>() {
                                                                        @Override
                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {


                                                                            if(response.body().equals("Order Inserted")){
                                                                                if(imageStatus[0]){
                                                                                    Retrofit retrofit2 = new Retrofit.Builder()
                                                                                            .baseUrl(ipClass.ipAddress).
                                                                                                    addConverterFactory(GsonConverterFactory.create())
                                                                                            .build();
                                                                                    final APIService service = retrofit2.create(APIService.class);

                                                                                    Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_1","1",imageToString(images[0]));

                                                                                    imageData.enqueue(new Callback<String>() {
                                                                                        @Override
                                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                            if(image_file1.exists()) {
                                                                                                image_file1.delete();
                                                                                            }
                                                                                        }

                                                                                        @Override
                                                                                        public void onFailure(Throwable t) {
                                                                                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                    });
                                                                                }

                                                                                if(imageStatus[1]){
                                                                                    Retrofit retrofit2 = new Retrofit.Builder()
                                                                                            .baseUrl(ipClass.ipAddress).
                                                                                                    addConverterFactory(GsonConverterFactory.create())
                                                                                            .build();
                                                                                    final APIService service = retrofit2.create(APIService.class);

                                                                                    Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_2","2",imageToString(images[1]));

                                                                                    imageData.enqueue(new Callback<String>() {
                                                                                        @Override
                                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                            if(image_file2.exists()) {
                                                                                                image_file2.delete();
                                                                                            }
                                                                                        }

                                                                                        @Override
                                                                                        public void onFailure(Throwable t) {
                                                                                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                    });
                                                                                }

                                                                                if(imageStatus[2]){
                                                                                    Retrofit retrofit2 = new Retrofit.Builder()
                                                                                            .baseUrl(ipClass.ipAddress).
                                                                                                    addConverterFactory(GsonConverterFactory.create())
                                                                                            .build();
                                                                                    final APIService service = retrofit2.create(APIService.class);

                                                                                    Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_3","3",imageToString(images[2]));

                                                                                    imageData.enqueue(new Callback<String>() {
                                                                                        @Override
                                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                            if(image_file3.exists()) {
                                                                                                image_file3.delete();
                                                                                            }
                                                                                        }

                                                                                        @Override
                                                                                        public void onFailure(Throwable t) {
                                                                                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                    });
                                                                                }

                                                                                if(imageStatus[3]){
                                                                                    Retrofit retrofit2 = new Retrofit.Builder()
                                                                                            .baseUrl(ipClass.ipAddress).
                                                                                                    addConverterFactory(GsonConverterFactory.create())
                                                                                            .build();
                                                                                    final APIService service = retrofit2.create(APIService.class);

                                                                                    Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_4","4",imageToString(images[3]));

                                                                                    imageData.enqueue(new Callback<String>() {
                                                                                        @Override
                                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                            if(image_file4.exists()) {
                                                                                                image_file4.delete();
                                                                                            }
                                                                                        }

                                                                                        @Override
                                                                                        public void onFailure(Throwable t) {
                                                                                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                    });
                                                                                }

                                                                                if(imageStatus[4]){
                                                                                    Retrofit retrofit2 = new Retrofit.Builder()
                                                                                            .baseUrl(ipClass.ipAddress).
                                                                                                    addConverterFactory(GsonConverterFactory.create())
                                                                                            .build();
                                                                                    final APIService service = retrofit2.create(APIService.class);

                                                                                    Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_5","5",imageToString(images[4]));

                                                                                    imageData.enqueue(new Callback<String>() {
                                                                                        @Override
                                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                            if(image_file5.exists()) {
                                                                                                image_file5.delete();
                                                                                            }
                                                                                        }

                                                                                        @Override
                                                                                        public void onFailure(Throwable t) {
                                                                                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                    });
                                                                                }

                                                                                if(imageStatus[5]){
                                                                                    Retrofit retrofit2 = new Retrofit.Builder()
                                                                                            .baseUrl(ipClass.ipAddress).
                                                                                                    addConverterFactory(GsonConverterFactory.create())
                                                                                            .build();
                                                                                    final APIService service = retrofit2.create(APIService.class);

                                                                                    Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_6","6",imageToString(images[5]));

                                                                                    imageData.enqueue(new Callback<String>() {
                                                                                        @Override
                                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                            if(image_file6.exists()) {
                                                                                                image_file6.delete();
                                                                                            }
                                                                                        }

                                                                                        @Override
                                                                                        public void onFailure(Throwable t) {
                                                                                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                    });
                                                                                }

                                                                                if(imageStatus[6]){
                                                                                    Retrofit retrofit2 = new Retrofit.Builder()
                                                                                            .baseUrl(ipClass.ipAddress).
                                                                                                    addConverterFactory(GsonConverterFactory.create())
                                                                                            .build();
                                                                                    final APIService service = retrofit2.create(APIService.class);

                                                                                    Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_7","7",imageToString(images[6]));

                                                                                    imageData.enqueue(new Callback<String>() {
                                                                                        @Override
                                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                            if(image_file7.exists()) {
                                                                                                image_file7.delete();
                                                                                            }
                                                                                        }

                                                                                        @Override
                                                                                        public void onFailure(Throwable t) {
                                                                                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                    });
                                                                                }

                                                                                if(imageStatus[7]){
                                                                                    Retrofit retrofit2 = new Retrofit.Builder()
                                                                                            .baseUrl(ipClass.ipAddress).
                                                                                                    addConverterFactory(GsonConverterFactory.create())
                                                                                            .build();
                                                                                    final APIService service = retrofit2.create(APIService.class);

                                                                                    Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_8","8",imageToString(images[7]));

                                                                                    imageData.enqueue(new Callback<String>() {
                                                                                        @Override
                                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                            if(image_file8.exists()) {
                                                                                                image_file8.delete();
                                                                                            }
                                                                                        }

                                                                                        @Override
                                                                                        public void onFailure(Throwable t) {
                                                                                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                    });
                                                                                }

                                                                                if(imageStatus[8]){
                                                                                    Retrofit retrofit2 = new Retrofit.Builder()
                                                                                            .baseUrl(ipClass.ipAddress).
                                                                                                    addConverterFactory(GsonConverterFactory.create())
                                                                                            .build();
                                                                                    final APIService service = retrofit2.create(APIService.class);

                                                                                    Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_9","9",imageToString(images[8]));

                                                                                    imageData.enqueue(new Callback<String>() {
                                                                                        @Override
                                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                            if(image_file9.exists()) {
                                                                                                image_file9.delete();
                                                                                            }
                                                                                        }

                                                                                        @Override
                                                                                        public void onFailure(Throwable t) {
                                                                                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                    });
                                                                                }

                                                                                if(imageStatus[9]){
                                                                                    Retrofit retrofit2 = new Retrofit.Builder()
                                                                                            .baseUrl(ipClass.ipAddress).
                                                                                                    addConverterFactory(GsonConverterFactory.create())
                                                                                            .build();
                                                                                    final APIService service = retrofit2.create(APIService.class);

                                                                                    Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_10","10",imageToString(images[9]));

                                                                                    imageData.enqueue(new Callback<String>() {
                                                                                        @Override
                                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                            if(image_file10.exists()) {
                                                                                                image_file10.delete();
                                                                                            }
                                                                                        }

                                                                                        @Override
                                                                                        public void onFailure(Throwable t) {
                                                                                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                    });
                                                                                }

                                                                                if(fabricChoiceValue.equals("Self")){
                                                                                    Call<String> customFabricData = service.InsertCustomerFabric(orderId);

                                                                                    customFabricData.enqueue(new Callback<String>() {
                                                                                        @Override
                                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                        }

                                                                                        @Override
                                                                                        public void onFailure(Throwable t) {
                                                                                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                    });
                                                                                }

                                                                                Call<String> measurementData = service.InsertMeasurement(orderId,customerId,stanceId,stanceValue,stanceImgValue,"",
                                                                                        "","","","","","",
                                                                                        "","",hipDescriptionId,hipDescriptionValue,hipDescriptionImgValue,"","",
                                                                                        "","","","","","",hipSignBtn.getText().toString() + hipValue,"",
                                                                                        "","","","",fullBackLengthSignBtn.getText().toString() + fullBackLengthValue,"",
                                                                                        trouserWaistSignBtn.getText().toString() + trouserWaistValue,trouserOutseamSignBtn.getText().toString() + trouserOutseamValue,trouserInseamSignBtn.getText().toString() + trouserInseamValue,"",thighSignBtn.getText().toString() + thighValue,kneeSignBtn.getText().toString() + kneeValue,"","");


                                                                                measurementData.enqueue(new Callback<String>() {
                                                                                    @Override
                                                                                    public void onResponse(Response<String> response, Retrofit retrofit) {


                                                                                        Call<String> stylingData = service.InsertStyling(orderId,customerId,fabricIdValue,fabricNumberValue,"","",trouserFitId,trouserFitValue,trouserFitImgValue,
                                                                                                "","","","","","",
                                                                                                "","","","","","","","","",
                                                                                                "","","","","","",
                                                                                                "","","","","","","","","",
                                                                                                "","","","","","","",
                                                                                                "","",trouserPleatsId,trouserPleatsValue,trouserPleatsImgValue,trouserBackPocketId,trouserBackPocketValue,
                                                                                                trouserBackPocketImgValue,trouserCuffId,trouserCuffValue,trouserCuffImgValue,trouserLoopTabId,trouserLoopTabValue,trouserLoopTabImgValue,
                                                                                                "","","","","","","",
                                                                                                "","","","","","","",
                                                                                                "","","","","","","",
                                                                                                "","","","","","","","","");


                                                                                        stylingData.enqueue(new Callback<String>() {
                                                                                            @Override
                                                                                            public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                                if(response.body().equals("Successful")){

                                                                                                    Call<String> timeline = service.ProcessStatusTimeline(orderId,"Pending Review");

                                                                                                    timeline.enqueue(new Callback<String>() {
                                                                                                        @Override
                                                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {

                                                                                                            Intent intent = new Intent(getApplicationContext(),PlaceOrderActivity.class);
                                                                                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                                                                            startActivity(intent);
                                                                                                            finish();
                                                                                                        }

                                                                                                        @Override
                                                                                                        public void onFailure(Throwable t) {

                                                                                                        }
                                                                                                    });

                                                                                                }
                                                                                                else{
                                                                                                    Toast.makeText(getApplicationContext(),"Can't place order",Toast.LENGTH_LONG).show();
                                                                                                }
                                                                                            }

                                                                                            @Override
                                                                                            public void onFailure(Throwable t) {
                                                                                                Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                            }
                                                                                        });
                                                                                    }

                                                                                    @Override
                                                                                    public void onFailure(Throwable t) {
                                                                                        Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                    }
                                                                                });

                                                                            }

                                                                        }

                                                                        @Override
                                                                        public void onFailure(Throwable t) {
                                                                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_LONG).show();
                                                                        }
                                                                    });
                                                                }
                                                            }

                                                            @Override
                                                            public void onFailure(Throwable t) {
                                                                Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_LONG).show();
                                                            }
                                                        });


                                                    }
                                                    else {
                                                        Toast.makeText(getApplicationContext(),"Fabric not available",Toast.LENGTH_SHORT).show();
                                                    }
                                                }

                                                @Override
                                                public void onFailure(Throwable t) {
                                                    Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                        }
                                        else {
                                            Toast.makeText(getApplicationContext(),"Make all selections in Measurement Section",Toast.LENGTH_LONG).show();
                                        }

                                    }

                                }
                                else {
                                    Toast.makeText(getApplicationContext(),"Make all selections in Suit Styling Section",Toast.LENGTH_LONG).show();
                                }
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"Complete Form in Customer Info Section \n Check Phone Number and Email",Toast.LENGTH_LONG).show();
                            }

                        }
                        else if(orderCondition.equals("Alter order")){

                            nameValue = name.getText().toString();
                            contactPrimaryValue = "03" + contactPrimaryTwo.getText().toString() + contactPrimarySeven.getText().toString();

                            if(!contactSecondarySeven.getText().toString().equals("") && !contactSecondaryTwo.getText().toString().equals("")){
                                contactSecondaryValue = "03" + contactSecondaryTwo.getText().toString() + contactSecondarySeven.getText().toString();
                            }
                            else {
                                contactSecondaryValue = "";
                            }
                            emailValue = email.getText().toString();
                            addressValue = address.getText().toString();
                            billValue = bill.getText().toString();
                            orderTypeValue = orderTypes.get(Integer.parseInt(orderType.getSelectedItem().toString()));
                            cityValue = cities.get(Integer.parseInt(city.getSelectedItem().toString()));

                            if(!nameValue.equals("") && !contactPrimaryValue.equals("") && !emailValue.equals("") && !addressValue.equals("") && !billValue.equals("") && !expectedDateValue.equals("")&& uniquePhoneNumber && validPhoneNumber && validSecondaryPhone && uniqueEmail){

                                hipValue = hip.getText().toString();
                                fullBackLengthValue = fullBackLength.getText().toString();
                                trouserWaistValue = trouserWaist.getText().toString();
                                trouserOutseamValue = trouserOutseam.getText().toString();
                                trouserInseamValue = trouserInseam.getText().toString();
                                thighValue = thigh.getText().toString();
                                kneeValue = knee.getText().toString();

                                fabricNumberValue = fabricNumberData.getText().toString();

                                if(!fabricNumberValue.equals("") && trouserPleatsValue != null && trouserBackPocketValue != null && trouserCuffValue != null && trouserLoopTabValue != null && trouserFitValue != null && trouserPleatsImgValue != null && trouserBackPocketImgValue != null && trouserCuffImgValue != null && trouserLoopTabImgValue != null && trouserFitImgValue != null){

                                    if(orderTypeValue.equals("Bespoke")) {
                                        if (stanceValue != null && stanceImgValue != null && hipDescriptionValue != null && hipDescriptionImgValue != null && !hipValue.equals("") && !fullBackLengthValue.equals("") && !trouserWaistValue.equals("") && !trouserOutseamValue.equals("") && !trouserInseamValue.equals("") && !thighValue.equals("") && !kneeValue.equals("")) {

                                            Toast.makeText(getApplicationContext(),"Please wait...",Toast.LENGTH_LONG).show();

                                            Retrofit retrofit = new Retrofit.Builder()
                                                    .baseUrl(ipClass.ipAddress).
                                                            addConverterFactory(GsonConverterFactory.create())
                                                    .build();
                                            final APIService service = retrofit.create(APIService.class);

                                            if(fabricChoiceValue.equals("Self")){
                                                Call<String> customFabricData = service.InsertCustomerFabric(orderId);

                                                customFabricData.enqueue(new Callback<String>() {
                                                    @Override
                                                    public void onResponse(Response<String> response, Retrofit retrofit) {
                                                    }

                                                    @Override
                                                    public void onFailure(Throwable t) {
                                                        Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                            }

                                            if(!previousFabricId.equals(fabricIdValue)){

                                                Call<String> fabricConsumptionData = service.ProcessFabricConsumption(fabricConnsumption,fabricIdValue);

                                                fabricConsumptionData.enqueue(new Callback<String>() {
                                                    @Override
                                                    public void onResponse(Response<String> response, Retrofit retrofit) {
                                                        if(response.body().equals("Yes")){

                                                            Call<String> fabricAdditionData = service.ProcessFabricAddition(fabricConnsumption,previousFabricId);

                                                            fabricAdditionData.enqueue(new Callback<String>() {
                                                                @Override
                                                                public void onResponse(Response<String> response, Retrofit retrofit) {

                                                                }

                                                                @Override
                                                                public void onFailure(Throwable t) {

                                                                }
                                                            });

                                                            Call<String> existingCustomerData = service.ProcessExistingCustomerDetails(customerId,nameValue,contactPrimaryValue,contactSecondaryValue,emailValue,cityValue,addressValue);

                                                            existingCustomerData.enqueue(new Callback<String>() {
                                                                @Override
                                                                public void onResponse(Response<String> response, Retrofit retrofit) {

                                                                    if(response.body().equals("Updated")){

                                                                        Retrofit retrofit1 = new Retrofit.Builder()
                                                                                .baseUrl(ipClass.ipAddress).
                                                                                        addConverterFactory(GsonConverterFactory.create())
                                                                                .build();
                                                                        final APIService service = retrofit1.create(APIService.class);


                                                                        Call<String> existingOrderData = service.UpdateOrder(orderId,shopId,itemType,"Pending Review",billValue,expectedDateValue,orderTypeValue,basePatternValue,baseSizeValue,fabricIdValue,"");

                                                                        existingOrderData.enqueue(new Callback<String>() {
                                                                            @Override
                                                                            public void onResponse(Response<String> response, Retrofit retrofit) {

                                                                                if(response.body().equals("Updated")){
                                                                                    if(imageStatus[0]){
                                                                                        Retrofit retrofit2 = new Retrofit.Builder()
                                                                                                .baseUrl(ipClass.ipAddress).
                                                                                                        addConverterFactory(GsonConverterFactory.create())
                                                                                                .build();
                                                                                        final APIService service = retrofit2.create(APIService.class);

                                                                                        Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_1","1",imageToString(images[0]));

                                                                                        imageData.enqueue(new Callback<String>() {
                                                                                            @Override
                                                                                            public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                                if(image_file1.exists()) {
                                                                                                    image_file1.delete();
                                                                                                }
                                                                                            }

                                                                                            @Override
                                                                                            public void onFailure(Throwable t) {
                                                                                                Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                            }
                                                                                        });
                                                                                    }

                                                                                    if(imageStatus[1]){
                                                                                        Retrofit retrofit2 = new Retrofit.Builder()
                                                                                                .baseUrl(ipClass.ipAddress).
                                                                                                        addConverterFactory(GsonConverterFactory.create())
                                                                                                .build();
                                                                                        final APIService service = retrofit2.create(APIService.class);

                                                                                        Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_2","2",imageToString(images[1]));

                                                                                        imageData.enqueue(new Callback<String>() {
                                                                                            @Override
                                                                                            public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                                if(image_file2.exists()) {
                                                                                                    image_file2.delete();
                                                                                                }
                                                                                            }

                                                                                            @Override
                                                                                            public void onFailure(Throwable t) {
                                                                                                Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                            }
                                                                                        });
                                                                                    }

                                                                                    if(imageStatus[2]){
                                                                                        Retrofit retrofit2 = new Retrofit.Builder()
                                                                                                .baseUrl(ipClass.ipAddress).
                                                                                                        addConverterFactory(GsonConverterFactory.create())
                                                                                                .build();
                                                                                        final APIService service = retrofit2.create(APIService.class);

                                                                                        Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_3","3",imageToString(images[2]));

                                                                                        imageData.enqueue(new Callback<String>() {
                                                                                            @Override
                                                                                            public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                                if(image_file3.exists()) {
                                                                                                    image_file3.delete();
                                                                                                }
                                                                                            }

                                                                                            @Override
                                                                                            public void onFailure(Throwable t) {
                                                                                                Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                            }
                                                                                        });
                                                                                    }

                                                                                    if(imageStatus[3]){
                                                                                        Retrofit retrofit2 = new Retrofit.Builder()
                                                                                                .baseUrl(ipClass.ipAddress).
                                                                                                        addConverterFactory(GsonConverterFactory.create())
                                                                                                .build();
                                                                                        final APIService service = retrofit2.create(APIService.class);

                                                                                        Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_4","4",imageToString(images[3]));

                                                                                        imageData.enqueue(new Callback<String>() {
                                                                                            @Override
                                                                                            public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                                if(image_file4.exists()) {
                                                                                                    image_file4.delete();
                                                                                                }
                                                                                            }

                                                                                            @Override
                                                                                            public void onFailure(Throwable t) {
                                                                                                Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                            }
                                                                                        });
                                                                                    }

                                                                                    if(imageStatus[4]){
                                                                                        Retrofit retrofit2 = new Retrofit.Builder()
                                                                                                .baseUrl(ipClass.ipAddress).
                                                                                                        addConverterFactory(GsonConverterFactory.create())
                                                                                                .build();
                                                                                        final APIService service = retrofit2.create(APIService.class);

                                                                                        Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_5","5",imageToString(images[4]));

                                                                                        imageData.enqueue(new Callback<String>() {
                                                                                            @Override
                                                                                            public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                                if(image_file5.exists()) {
                                                                                                    image_file5.delete();
                                                                                                }
                                                                                            }

                                                                                            @Override
                                                                                            public void onFailure(Throwable t) {
                                                                                                Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                            }
                                                                                        });
                                                                                    }

                                                                                    if(imageStatus[5]){
                                                                                        Retrofit retrofit2 = new Retrofit.Builder()
                                                                                                .baseUrl(ipClass.ipAddress).
                                                                                                        addConverterFactory(GsonConverterFactory.create())
                                                                                                .build();
                                                                                        final APIService service = retrofit2.create(APIService.class);

                                                                                        Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_6","6",imageToString(images[5]));

                                                                                        imageData.enqueue(new Callback<String>() {
                                                                                            @Override
                                                                                            public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                                if(image_file6.exists()) {
                                                                                                    image_file6.delete();
                                                                                                }
                                                                                            }

                                                                                            @Override
                                                                                            public void onFailure(Throwable t) {
                                                                                                Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                            }
                                                                                        });
                                                                                    }

                                                                                    if(imageStatus[6]){
                                                                                        Retrofit retrofit2 = new Retrofit.Builder()
                                                                                                .baseUrl(ipClass.ipAddress).
                                                                                                        addConverterFactory(GsonConverterFactory.create())
                                                                                                .build();
                                                                                        final APIService service = retrofit2.create(APIService.class);

                                                                                        Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_7","7",imageToString(images[6]));

                                                                                        imageData.enqueue(new Callback<String>() {
                                                                                            @Override
                                                                                            public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                                if(image_file7.exists()) {
                                                                                                    image_file7.delete();
                                                                                                }
                                                                                            }

                                                                                            @Override
                                                                                            public void onFailure(Throwable t) {
                                                                                                Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                            }
                                                                                        });
                                                                                    }

                                                                                    if(imageStatus[7]){
                                                                                        Retrofit retrofit2 = new Retrofit.Builder()
                                                                                                .baseUrl(ipClass.ipAddress).
                                                                                                        addConverterFactory(GsonConverterFactory.create())
                                                                                                .build();
                                                                                        final APIService service = retrofit2.create(APIService.class);

                                                                                        Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_8","8",imageToString(images[7]));

                                                                                        imageData.enqueue(new Callback<String>() {
                                                                                            @Override
                                                                                            public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                                if(image_file8.exists()) {
                                                                                                    image_file8.delete();
                                                                                                }
                                                                                            }

                                                                                            @Override
                                                                                            public void onFailure(Throwable t) {
                                                                                                Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                            }
                                                                                        });
                                                                                    }

                                                                                    if(imageStatus[8]){
                                                                                        Retrofit retrofit2 = new Retrofit.Builder()
                                                                                                .baseUrl(ipClass.ipAddress).
                                                                                                        addConverterFactory(GsonConverterFactory.create())
                                                                                                .build();
                                                                                        final APIService service = retrofit2.create(APIService.class);

                                                                                        Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_9","9",imageToString(images[8]));

                                                                                        imageData.enqueue(new Callback<String>() {
                                                                                            @Override
                                                                                            public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                                if(image_file9.exists()) {
                                                                                                    image_file9.delete();
                                                                                                }
                                                                                            }

                                                                                            @Override
                                                                                            public void onFailure(Throwable t) {
                                                                                                Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                            }
                                                                                        });
                                                                                    }

                                                                                    if(imageStatus[9]){
                                                                                        Retrofit retrofit2 = new Retrofit.Builder()
                                                                                                .baseUrl(ipClass.ipAddress).
                                                                                                        addConverterFactory(GsonConverterFactory.create())
                                                                                                .build();
                                                                                        final APIService service = retrofit2.create(APIService.class);

                                                                                        Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_10","10",imageToString(images[9]));

                                                                                        imageData.enqueue(new Callback<String>() {
                                                                                            @Override
                                                                                            public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                                if(image_file10.exists()) {
                                                                                                    image_file10.delete();
                                                                                                }
                                                                                            }

                                                                                            @Override
                                                                                            public void onFailure(Throwable t) {
                                                                                                Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                            }
                                                                                        });
                                                                                    }

                                                                                    Call<String> updateMeasurementData = service.UpdateMeasurement(measurementId,stanceId,stanceValue,stanceImgValue,"",
                                                                                            "","","","","","",
                                                                                            "","",hipDescriptionId,hipDescriptionValue,hipDescriptionImgValue,"","",
                                                                                            "","","","","","",hipValue,"",
                                                                                            "","","","",fullBackLengthValue,"",
                                                                                            trouserWaistValue,trouserOutseamValue,trouserInseamValue,"",thighValue,kneeValue,"","");


                                                                                    updateMeasurementData.enqueue(new Callback<String>() {
                                                                                        @Override
                                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {

                                                                                            if(response.body().equals("Successful")){


                                                                                                Call<String> updateStylingData = service.UpdateStyling(stylingId,fabricIdValue,fabricNumberValue,"","",trouserFitId,trouserFitValue,trouserFitImgValue,
                                                                                                        "","","","","","",
                                                                                                        "","","","","","","","","",
                                                                                                        "","","","","","",
                                                                                                        "","","","","","","","","",
                                                                                                        "","","","","","","",
                                                                                                        "","",trouserPleatsId,trouserPleatsValue,trouserPleatsImgValue,trouserBackPocketId,trouserBackPocketValue,
                                                                                                        trouserBackPocketImgValue,trouserCuffId,trouserCuffValue,trouserCuffImgValue,trouserLoopTabId,trouserLoopTabValue,trouserLoopTabImgValue,
                                                                                                        "","","","","","","",
                                                                                                        "","","","","","","",
                                                                                                        "","","","","","","",
                                                                                                        "","","","","","","","","");


                                                                                                updateStylingData.enqueue(new Callback<String>() {
                                                                                                    @Override
                                                                                                    public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                                        if(response.body().equals("Successful")){

                                                                                                            Call<String> timeline = service.UpdateStatusTimeline(orderId,"Pending Review");

                                                                                                            timeline.enqueue(new Callback<String>() {
                                                                                                                @Override
                                                                                                                public void onResponse(Response<String> response, Retrofit retrofit) {

                                                                                                                    Intent intent = new Intent(getApplicationContext(),PlaceOrderActivity.class);
                                                                                                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                                                                                    startActivity(intent);
                                                                                                                    finish();
                                                                                                                }

                                                                                                                @Override
                                                                                                                public void onFailure(Throwable t) {

                                                                                                                }
                                                                                                            });

                                                                                                        }
                                                                                                        else{
                                                                                                            Toast.makeText(getApplicationContext(),"Can't place order",Toast.LENGTH_LONG).show();
                                                                                                        }
                                                                                                    }

                                                                                                    @Override
                                                                                                    public void onFailure(Throwable t) {
                                                                                                        Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                                    }
                                                                                                });
                                                                                            }
                                                                                        }

                                                                                        @Override
                                                                                        public void onFailure(Throwable t) {
                                                                                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                    });

                                                                                }

                                                                            }

                                                                            @Override
                                                                            public void onFailure(Throwable t) {
                                                                                Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_LONG).show();
                                                                            }
                                                                        });
                                                                    }
                                                                }

                                                                @Override
                                                                public void onFailure(Throwable t) {
                                                                    Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_LONG).show();
                                                                }
                                                            });

                                                        }
                                                        else {
                                                            Toast.makeText(getApplicationContext(),"Fabric not available",Toast.LENGTH_SHORT).show();
                                                        }
                                                    }

                                                    @Override
                                                    public void onFailure(Throwable t) {
                                                        Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                    }
                                                });

                                            }
                                            else {

                                                Call<String> existingCustomerData = service.ProcessExistingCustomerDetails(customerId,nameValue,contactPrimaryValue,contactSecondaryValue,emailValue,cityValue,addressValue);

                                                existingCustomerData.enqueue(new Callback<String>() {
                                                    @Override
                                                    public void onResponse(Response<String> response, Retrofit retrofit) {

                                                        if(response.body().equals("Updated")){

                                                            Retrofit retrofit1 = new Retrofit.Builder()
                                                                    .baseUrl(ipClass.ipAddress).
                                                                            addConverterFactory(GsonConverterFactory.create())
                                                                    .build();
                                                            final APIService service = retrofit1.create(APIService.class);


                                                            Call<String> existingOrderData = service.UpdateOrder(orderId,shopId,itemType,"Pending Review",billValue,expectedDateValue,orderTypeValue,basePatternValue,baseSizeValue,fabricIdValue,"");

                                                            existingOrderData.enqueue(new Callback<String>() {
                                                                @Override
                                                                public void onResponse(Response<String> response, Retrofit retrofit) {

                                                                    if(response.body().equals("Updated")){
                                                                        if(imageStatus[0]){
                                                                            Retrofit retrofit2 = new Retrofit.Builder()
                                                                                    .baseUrl(ipClass.ipAddress).
                                                                                            addConverterFactory(GsonConverterFactory.create())
                                                                                    .build();
                                                                            final APIService service = retrofit2.create(APIService.class);

                                                                            Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_1","1",imageToString(images[0]));

                                                                            imageData.enqueue(new Callback<String>() {
                                                                                @Override
                                                                                public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                    if(image_file1.exists()) {
                                                                                        image_file1.delete();
                                                                                    }
                                                                                }

                                                                                @Override
                                                                                public void onFailure(Throwable t) {
                                                                                    Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                }
                                                                            });
                                                                        }

                                                                        if(imageStatus[1]){
                                                                            Retrofit retrofit2 = new Retrofit.Builder()
                                                                                    .baseUrl(ipClass.ipAddress).
                                                                                            addConverterFactory(GsonConverterFactory.create())
                                                                                    .build();
                                                                            final APIService service = retrofit2.create(APIService.class);

                                                                            Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_2","2",imageToString(images[1]));

                                                                            imageData.enqueue(new Callback<String>() {
                                                                                @Override
                                                                                public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                    if(image_file2.exists()) {
                                                                                        image_file2.delete();
                                                                                    }
                                                                                }

                                                                                @Override
                                                                                public void onFailure(Throwable t) {
                                                                                    Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                }
                                                                            });
                                                                        }

                                                                        if(imageStatus[2]){
                                                                            Retrofit retrofit2 = new Retrofit.Builder()
                                                                                    .baseUrl(ipClass.ipAddress).
                                                                                            addConverterFactory(GsonConverterFactory.create())
                                                                                    .build();
                                                                            final APIService service = retrofit2.create(APIService.class);

                                                                            Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_3","3",imageToString(images[2]));

                                                                            imageData.enqueue(new Callback<String>() {
                                                                                @Override
                                                                                public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                    if(image_file3.exists()) {
                                                                                        image_file3.delete();
                                                                                    }
                                                                                }

                                                                                @Override
                                                                                public void onFailure(Throwable t) {
                                                                                    Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                }
                                                                            });
                                                                        }

                                                                        if(imageStatus[3]){
                                                                            Retrofit retrofit2 = new Retrofit.Builder()
                                                                                    .baseUrl(ipClass.ipAddress).
                                                                                            addConverterFactory(GsonConverterFactory.create())
                                                                                    .build();
                                                                            final APIService service = retrofit2.create(APIService.class);

                                                                            Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_4","4",imageToString(images[3]));

                                                                            imageData.enqueue(new Callback<String>() {
                                                                                @Override
                                                                                public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                    if(image_file4.exists()) {
                                                                                        image_file4.delete();
                                                                                    }
                                                                                }

                                                                                @Override
                                                                                public void onFailure(Throwable t) {
                                                                                    Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                }
                                                                            });
                                                                        }

                                                                        if(imageStatus[4]){
                                                                            Retrofit retrofit2 = new Retrofit.Builder()
                                                                                    .baseUrl(ipClass.ipAddress).
                                                                                            addConverterFactory(GsonConverterFactory.create())
                                                                                    .build();
                                                                            final APIService service = retrofit2.create(APIService.class);

                                                                            Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_5","5",imageToString(images[4]));

                                                                            imageData.enqueue(new Callback<String>() {
                                                                                @Override
                                                                                public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                    if(image_file5.exists()) {
                                                                                        image_file5.delete();
                                                                                    }
                                                                                }

                                                                                @Override
                                                                                public void onFailure(Throwable t) {
                                                                                    Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                }
                                                                            });
                                                                        }

                                                                        if(imageStatus[5]){
                                                                            Retrofit retrofit2 = new Retrofit.Builder()
                                                                                    .baseUrl(ipClass.ipAddress).
                                                                                            addConverterFactory(GsonConverterFactory.create())
                                                                                    .build();
                                                                            final APIService service = retrofit2.create(APIService.class);

                                                                            Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_6","6",imageToString(images[5]));

                                                                            imageData.enqueue(new Callback<String>() {
                                                                                @Override
                                                                                public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                    if(image_file6.exists()) {
                                                                                        image_file6.delete();
                                                                                    }
                                                                                }

                                                                                @Override
                                                                                public void onFailure(Throwable t) {
                                                                                    Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                }
                                                                            });
                                                                        }

                                                                        if(imageStatus[6]){
                                                                            Retrofit retrofit2 = new Retrofit.Builder()
                                                                                    .baseUrl(ipClass.ipAddress).
                                                                                            addConverterFactory(GsonConverterFactory.create())
                                                                                    .build();
                                                                            final APIService service = retrofit2.create(APIService.class);

                                                                            Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_7","7",imageToString(images[6]));

                                                                            imageData.enqueue(new Callback<String>() {
                                                                                @Override
                                                                                public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                    if(image_file7.exists()) {
                                                                                        image_file7.delete();
                                                                                    }
                                                                                }

                                                                                @Override
                                                                                public void onFailure(Throwable t) {
                                                                                    Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                }
                                                                            });
                                                                        }

                                                                        if(imageStatus[7]){
                                                                            Retrofit retrofit2 = new Retrofit.Builder()
                                                                                    .baseUrl(ipClass.ipAddress).
                                                                                            addConverterFactory(GsonConverterFactory.create())
                                                                                    .build();
                                                                            final APIService service = retrofit2.create(APIService.class);

                                                                            Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_8","8",imageToString(images[7]));

                                                                            imageData.enqueue(new Callback<String>() {
                                                                                @Override
                                                                                public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                    if(image_file8.exists()) {
                                                                                        image_file8.delete();
                                                                                    }
                                                                                }

                                                                                @Override
                                                                                public void onFailure(Throwable t) {
                                                                                    Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                }
                                                                            });
                                                                        }

                                                                        if(imageStatus[8]){
                                                                            Retrofit retrofit2 = new Retrofit.Builder()
                                                                                    .baseUrl(ipClass.ipAddress).
                                                                                            addConverterFactory(GsonConverterFactory.create())
                                                                                    .build();
                                                                            final APIService service = retrofit2.create(APIService.class);

                                                                            Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_9","9",imageToString(images[8]));

                                                                            imageData.enqueue(new Callback<String>() {
                                                                                @Override
                                                                                public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                    if(image_file9.exists()) {
                                                                                        image_file9.delete();
                                                                                    }
                                                                                }

                                                                                @Override
                                                                                public void onFailure(Throwable t) {
                                                                                    Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                }
                                                                            });
                                                                        }

                                                                        if(imageStatus[9]){
                                                                            Retrofit retrofit2 = new Retrofit.Builder()
                                                                                    .baseUrl(ipClass.ipAddress).
                                                                                            addConverterFactory(GsonConverterFactory.create())
                                                                                    .build();
                                                                            final APIService service = retrofit2.create(APIService.class);

                                                                            Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_10","10",imageToString(images[9]));

                                                                            imageData.enqueue(new Callback<String>() {
                                                                                @Override
                                                                                public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                    if(image_file10.exists()) {
                                                                                        image_file10.delete();
                                                                                    }
                                                                                }

                                                                                @Override
                                                                                public void onFailure(Throwable t) {
                                                                                    Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                }
                                                                            });
                                                                        }

                                                                        Call<String> updateMeasurementData = service.UpdateMeasurement(measurementId,stanceId,stanceValue,stanceImgValue,"",
                                                                                "","","","","","",
                                                                                "","",hipDescriptionId,hipDescriptionValue,hipDescriptionImgValue,"","",
                                                                                "","","","","","",hipValue,"",
                                                                                "","","","",fullBackLengthValue,"",
                                                                                trouserWaistValue,trouserOutseamValue,trouserInseamValue,"",thighValue,kneeValue,"","");


                                                                        updateMeasurementData.enqueue(new Callback<String>() {
                                                                            @Override
                                                                            public void onResponse(Response<String> response, Retrofit retrofit) {

                                                                                if(response.body().equals("Successful")){


                                                                                    Call<String> updateStylingData = service.UpdateStyling(stylingId,fabricIdValue,fabricNumberValue,"","",trouserFitId,trouserFitValue,trouserFitImgValue,
                                                                                            "","","","","","",
                                                                                            "","","","","","","","","",
                                                                                            "","","","","","",
                                                                                            "","","","","","","","","",
                                                                                            "","","","","","","",
                                                                                            "","",trouserPleatsId,trouserPleatsValue,trouserPleatsImgValue,trouserBackPocketId,trouserBackPocketValue,
                                                                                            trouserBackPocketImgValue,trouserCuffId,trouserCuffValue,trouserCuffImgValue,trouserLoopTabId,trouserLoopTabValue,trouserLoopTabImgValue,
                                                                                            "","","","","","","",
                                                                                            "","","","","","","",
                                                                                            "","","","","","","",
                                                                                            "","","","","","","","","");


                                                                                    updateStylingData.enqueue(new Callback<String>() {
                                                                                        @Override
                                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                            if(response.body().equals("Successful")){

                                                                                                Call<String> timeline = service.UpdateStatusTimeline(orderId,"Pending Review");

                                                                                                timeline.enqueue(new Callback<String>() {
                                                                                                    @Override
                                                                                                    public void onResponse(Response<String> response, Retrofit retrofit) {

                                                                                                        Intent intent = new Intent(getApplicationContext(),PlaceOrderActivity.class);
                                                                                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                                                                        startActivity(intent);
                                                                                                        finish();
                                                                                                    }

                                                                                                    @Override
                                                                                                    public void onFailure(Throwable t) {

                                                                                                    }
                                                                                                });

                                                                                            }
                                                                                            else{
                                                                                                Toast.makeText(getApplicationContext(),"Can't place order",Toast.LENGTH_LONG).show();
                                                                                            }
                                                                                        }

                                                                                        @Override
                                                                                        public void onFailure(Throwable t) {
                                                                                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                    });
                                                                                }
                                                                            }

                                                                            @Override
                                                                            public void onFailure(Throwable t) {
                                                                                Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                            }
                                                                        });

                                                                    }

                                                                }

                                                                @Override
                                                                public void onFailure(Throwable t) {
                                                                    Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_LONG).show();
                                                                }
                                                            });
                                                        }
                                                    }

                                                    @Override
                                                    public void onFailure(Throwable t) {
                                                        Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_LONG).show();
                                                    }
                                                });

                                            }


                                        }
                                        else {
                                            Toast.makeText(getApplicationContext(), "Please complete Measurement Section", Toast.LENGTH_LONG).show();
                                        }


                                    }

                                    else{

                                        if (stanceValue != null && stanceImgValue != null && hipDescriptionValue != null && hipDescriptionImgValue != null){

                                            Toast.makeText(getApplicationContext(),"Please wait...",Toast.LENGTH_LONG).show();

                                            Retrofit retrofit = new Retrofit.Builder()
                                                    .baseUrl(ipClass.ipAddress).
                                                            addConverterFactory(GsonConverterFactory.create())
                                                    .build();
                                            final APIService service = retrofit.create(APIService.class);


                                            if(fabricChoiceValue.equals("Self")){
                                                Call<String> customFabricData = service.InsertCustomerFabric(orderId);

                                                customFabricData.enqueue(new Callback<String>() {
                                                    @Override
                                                    public void onResponse(Response<String> response, Retrofit retrofit) {
                                                    }

                                                    @Override
                                                    public void onFailure(Throwable t) {
                                                        Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                            }


                                            if(!previousFabricId.equals(fabricIdValue)){

                                                Call<String> fabricConsumptionData = service.ProcessFabricConsumption(fabricConnsumption,fabricIdValue);

                                                fabricConsumptionData.enqueue(new Callback<String>() {
                                                    @Override
                                                    public void onResponse(Response<String> response, Retrofit retrofit) {
                                                        if(response.body().equals("Yes")){

                                                            Call<String> fabricAdditionData = service.ProcessFabricAddition(fabricConnsumption,previousFabricId);

                                                            fabricAdditionData.enqueue(new Callback<String>() {
                                                                @Override
                                                                public void onResponse(Response<String> response, Retrofit retrofit) {

                                                                }

                                                                @Override
                                                                public void onFailure(Throwable t) {

                                                                }
                                                            });

                                                            Call<String> existingCustomerData = service.ProcessExistingCustomerDetails(customerId,nameValue,contactPrimaryValue,contactSecondaryValue,emailValue,cityValue,addressValue);

                                                            existingCustomerData.enqueue(new Callback<String>() {
                                                                @Override
                                                                public void onResponse(Response<String> response, Retrofit retrofit) {

                                                                    if(response.body().equals("Updated")){

                                                                        Retrofit retrofit1 = new Retrofit.Builder()
                                                                                .baseUrl(ipClass.ipAddress).
                                                                                        addConverterFactory(GsonConverterFactory.create())
                                                                                .build();
                                                                        final APIService service = retrofit1.create(APIService.class);


                                                                        Call<String> existingOrderData = service.UpdateOrder(orderId,shopId,itemType,"Pending Review",billValue,expectedDateValue,orderTypeValue,basePatternValue,baseSizeValue,fabricIdValue,"");

                                                                        existingOrderData.enqueue(new Callback<String>() {
                                                                            @Override
                                                                            public void onResponse(Response<String> response, Retrofit retrofit) {

                                                                                if(response.body().equals("Updated")){
                                                                                    if(imageStatus[0]){
                                                                                        Retrofit retrofit2 = new Retrofit.Builder()
                                                                                                .baseUrl(ipClass.ipAddress).
                                                                                                        addConverterFactory(GsonConverterFactory.create())
                                                                                                .build();
                                                                                        final APIService service = retrofit2.create(APIService.class);

                                                                                        Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_1","1",imageToString(images[0]));

                                                                                        imageData.enqueue(new Callback<String>() {
                                                                                            @Override
                                                                                            public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                                if(image_file1.exists()) {
                                                                                                    image_file1.delete();
                                                                                                }
                                                                                            }

                                                                                            @Override
                                                                                            public void onFailure(Throwable t) {
                                                                                                Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                            }
                                                                                        });
                                                                                    }

                                                                                    if(imageStatus[1]){
                                                                                        Retrofit retrofit2 = new Retrofit.Builder()
                                                                                                .baseUrl(ipClass.ipAddress).
                                                                                                        addConverterFactory(GsonConverterFactory.create())
                                                                                                .build();
                                                                                        final APIService service = retrofit2.create(APIService.class);

                                                                                        Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_2","2",imageToString(images[1]));

                                                                                        imageData.enqueue(new Callback<String>() {
                                                                                            @Override
                                                                                            public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                                if(image_file2.exists()) {
                                                                                                    image_file2.delete();
                                                                                                }
                                                                                            }

                                                                                            @Override
                                                                                            public void onFailure(Throwable t) {
                                                                                                Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                            }
                                                                                        });
                                                                                    }

                                                                                    if(imageStatus[2]){
                                                                                        Retrofit retrofit2 = new Retrofit.Builder()
                                                                                                .baseUrl(ipClass.ipAddress).
                                                                                                        addConverterFactory(GsonConverterFactory.create())
                                                                                                .build();
                                                                                        final APIService service = retrofit2.create(APIService.class);

                                                                                        Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_3","3",imageToString(images[2]));

                                                                                        imageData.enqueue(new Callback<String>() {
                                                                                            @Override
                                                                                            public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                                if(image_file3.exists()) {
                                                                                                    image_file3.delete();
                                                                                                }
                                                                                            }

                                                                                            @Override
                                                                                            public void onFailure(Throwable t) {
                                                                                                Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                            }
                                                                                        });
                                                                                    }

                                                                                    if(imageStatus[3]){
                                                                                        Retrofit retrofit2 = new Retrofit.Builder()
                                                                                                .baseUrl(ipClass.ipAddress).
                                                                                                        addConverterFactory(GsonConverterFactory.create())
                                                                                                .build();
                                                                                        final APIService service = retrofit2.create(APIService.class);

                                                                                        Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_4","4",imageToString(images[3]));

                                                                                        imageData.enqueue(new Callback<String>() {
                                                                                            @Override
                                                                                            public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                                if(image_file4.exists()) {
                                                                                                    image_file4.delete();
                                                                                                }
                                                                                            }

                                                                                            @Override
                                                                                            public void onFailure(Throwable t) {
                                                                                                Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                            }
                                                                                        });
                                                                                    }

                                                                                    if(imageStatus[4]){
                                                                                        Retrofit retrofit2 = new Retrofit.Builder()
                                                                                                .baseUrl(ipClass.ipAddress).
                                                                                                        addConverterFactory(GsonConverterFactory.create())
                                                                                                .build();
                                                                                        final APIService service = retrofit2.create(APIService.class);

                                                                                        Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_5","5",imageToString(images[4]));

                                                                                        imageData.enqueue(new Callback<String>() {
                                                                                            @Override
                                                                                            public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                                if(image_file5.exists()) {
                                                                                                    image_file5.delete();
                                                                                                }
                                                                                            }

                                                                                            @Override
                                                                                            public void onFailure(Throwable t) {
                                                                                                Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                            }
                                                                                        });
                                                                                    }

                                                                                    if(imageStatus[5]){
                                                                                        Retrofit retrofit2 = new Retrofit.Builder()
                                                                                                .baseUrl(ipClass.ipAddress).
                                                                                                        addConverterFactory(GsonConverterFactory.create())
                                                                                                .build();
                                                                                        final APIService service = retrofit2.create(APIService.class);

                                                                                        Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_6","6",imageToString(images[5]));

                                                                                        imageData.enqueue(new Callback<String>() {
                                                                                            @Override
                                                                                            public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                                if(image_file6.exists()) {
                                                                                                    image_file6.delete();
                                                                                                }
                                                                                            }

                                                                                            @Override
                                                                                            public void onFailure(Throwable t) {
                                                                                                Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                            }
                                                                                        });
                                                                                    }

                                                                                    if(imageStatus[6]){
                                                                                        Retrofit retrofit2 = new Retrofit.Builder()
                                                                                                .baseUrl(ipClass.ipAddress).
                                                                                                        addConverterFactory(GsonConverterFactory.create())
                                                                                                .build();
                                                                                        final APIService service = retrofit2.create(APIService.class);

                                                                                        Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_7","7",imageToString(images[6]));

                                                                                        imageData.enqueue(new Callback<String>() {
                                                                                            @Override
                                                                                            public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                                if(image_file7.exists()) {
                                                                                                    image_file7.delete();
                                                                                                }
                                                                                            }

                                                                                            @Override
                                                                                            public void onFailure(Throwable t) {
                                                                                                Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                            }
                                                                                        });
                                                                                    }

                                                                                    if(imageStatus[7]){
                                                                                        Retrofit retrofit2 = new Retrofit.Builder()
                                                                                                .baseUrl(ipClass.ipAddress).
                                                                                                        addConverterFactory(GsonConverterFactory.create())
                                                                                                .build();
                                                                                        final APIService service = retrofit2.create(APIService.class);

                                                                                        Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_8","8",imageToString(images[7]));

                                                                                        imageData.enqueue(new Callback<String>() {
                                                                                            @Override
                                                                                            public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                                if(image_file8.exists()) {
                                                                                                    image_file8.delete();
                                                                                                }
                                                                                            }

                                                                                            @Override
                                                                                            public void onFailure(Throwable t) {
                                                                                                Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                            }
                                                                                        });
                                                                                    }

                                                                                    if(imageStatus[8]){
                                                                                        Retrofit retrofit2 = new Retrofit.Builder()
                                                                                                .baseUrl(ipClass.ipAddress).
                                                                                                        addConverterFactory(GsonConverterFactory.create())
                                                                                                .build();
                                                                                        final APIService service = retrofit2.create(APIService.class);

                                                                                        Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_9","9",imageToString(images[8]));

                                                                                        imageData.enqueue(new Callback<String>() {
                                                                                            @Override
                                                                                            public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                                if(image_file9.exists()) {
                                                                                                    image_file9.delete();
                                                                                                }
                                                                                            }

                                                                                            @Override
                                                                                            public void onFailure(Throwable t) {
                                                                                                Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                            }
                                                                                        });
                                                                                    }

                                                                                    if(imageStatus[9]){
                                                                                        Retrofit retrofit2 = new Retrofit.Builder()
                                                                                                .baseUrl(ipClass.ipAddress).
                                                                                                        addConverterFactory(GsonConverterFactory.create())
                                                                                                .build();
                                                                                        final APIService service = retrofit2.create(APIService.class);

                                                                                        Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_10","10",imageToString(images[9]));

                                                                                        imageData.enqueue(new Callback<String>() {
                                                                                            @Override
                                                                                            public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                                if(image_file10.exists()) {
                                                                                                    image_file10.delete();
                                                                                                }
                                                                                            }

                                                                                            @Override
                                                                                            public void onFailure(Throwable t) {
                                                                                                Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                            }
                                                                                        });
                                                                                    }

                                                                                    Call<String> updateMeasurementData = service.UpdateMeasurement(measurementId,stanceId,stanceValue,stanceImgValue,"",
                                                                                            "","","","","","",
                                                                                            "","",hipDescriptionId,hipDescriptionValue,hipDescriptionImgValue,"","",
                                                                                            "","","","","","",hipSignBtn.getText().toString() + hipValue,"",
                                                                                            "","","","",fullBackLengthSignBtn.getText().toString() + fullBackLengthValue,"",
                                                                                            trouserWaistSignBtn.getText().toString() + trouserWaistValue,trouserOutseamSignBtn.getText().toString() + trouserOutseamValue,trouserInseamSignBtn.getText().toString() + trouserInseamValue,"",thighSignBtn.getText().toString() + thighValue,kneeSignBtn.getText().toString() + kneeValue,"","");


                                                                                    updateMeasurementData.enqueue(new Callback<String>() {
                                                                                        @Override
                                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {

                                                                                            if(response.body().equals("Successful")){


                                                                                                Call<String> updateStylingData = service.UpdateStyling(stylingId,fabricIdValue,fabricNumberValue,"","",trouserFitId,trouserFitValue,trouserFitImgValue,
                                                                                                        "","","","","","",
                                                                                                        "","","","","","","","","",
                                                                                                        "","","","","","",
                                                                                                        "","","","","","","","","",
                                                                                                        "","","","","","","",
                                                                                                        "","",trouserPleatsId,trouserPleatsValue,trouserPleatsImgValue,trouserBackPocketId,trouserBackPocketValue,
                                                                                                        trouserBackPocketImgValue,trouserCuffId,trouserCuffValue,trouserCuffImgValue,trouserLoopTabId,trouserLoopTabValue,trouserLoopTabImgValue,
                                                                                                        "","","","","","","",
                                                                                                        "","","","","","","",
                                                                                                        "","","","","","","",
                                                                                                        "","","","","","","","","");


                                                                                                updateStylingData.enqueue(new Callback<String>() {
                                                                                                    @Override
                                                                                                    public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                                        if(response.body().equals("Successful")){

                                                                                                            Call<String> timeline = service.UpdateStatusTimeline(orderId,"Pending Review");

                                                                                                            timeline.enqueue(new Callback<String>() {
                                                                                                                @Override
                                                                                                                public void onResponse(Response<String> response, Retrofit retrofit) {

                                                                                                                    Intent intent = new Intent(getApplicationContext(),PlaceOrderActivity.class);
                                                                                                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                                                                                    startActivity(intent);
                                                                                                                    finish();
                                                                                                                }

                                                                                                                @Override
                                                                                                                public void onFailure(Throwable t) {

                                                                                                                }
                                                                                                            });

                                                                                                        }
                                                                                                        else{
                                                                                                            Toast.makeText(getApplicationContext(),"Can't place order",Toast.LENGTH_LONG).show();
                                                                                                        }
                                                                                                    }

                                                                                                    @Override
                                                                                                    public void onFailure(Throwable t) {
                                                                                                        Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                                    }
                                                                                                });
                                                                                            }
                                                                                        }

                                                                                        @Override
                                                                                        public void onFailure(Throwable t) {
                                                                                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                    });

                                                                                }

                                                                            }

                                                                            @Override
                                                                            public void onFailure(Throwable t) {
                                                                                Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_LONG).show();
                                                                            }
                                                                        });
                                                                    }
                                                                }

                                                                @Override
                                                                public void onFailure(Throwable t) {
                                                                    Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_LONG).show();
                                                                }
                                                            });

                                                        }
                                                        else {
                                                            Toast.makeText(getApplicationContext(),"Fabric not available",Toast.LENGTH_SHORT).show();
                                                        }
                                                    }

                                                    @Override
                                                    public void onFailure(Throwable t) {
                                                        Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                    }
                                                });


                                            }
                                            else{

                                                Call<String> existingCustomerData = service.ProcessExistingCustomerDetails(customerId,nameValue,contactPrimaryValue,contactSecondaryValue,emailValue,cityValue,addressValue);

                                                existingCustomerData.enqueue(new Callback<String>() {
                                                    @Override
                                                    public void onResponse(Response<String> response, Retrofit retrofit) {

                                                        if(response.body().equals("Updated")){

                                                            Retrofit retrofit1 = new Retrofit.Builder()
                                                                    .baseUrl(ipClass.ipAddress).
                                                                            addConverterFactory(GsonConverterFactory.create())
                                                                    .build();
                                                            final APIService service = retrofit1.create(APIService.class);


                                                            Call<String> existingOrderData = service.UpdateOrder(orderId,shopId,itemType,"Pending Review",billValue,expectedDateValue,orderTypeValue,basePatternValue,baseSizeValue,fabricIdValue,"");

                                                            existingOrderData.enqueue(new Callback<String>() {
                                                                @Override
                                                                public void onResponse(Response<String> response, Retrofit retrofit) {

                                                                    if(response.body().equals("Updated")){
                                                                        if(imageStatus[0]){
                                                                            Retrofit retrofit2 = new Retrofit.Builder()
                                                                                    .baseUrl(ipClass.ipAddress).
                                                                                            addConverterFactory(GsonConverterFactory.create())
                                                                                    .build();
                                                                            final APIService service = retrofit2.create(APIService.class);

                                                                            Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_1","1",imageToString(images[0]));

                                                                            imageData.enqueue(new Callback<String>() {
                                                                                @Override
                                                                                public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                    if(image_file1.exists()) {
                                                                                        image_file1.delete();
                                                                                    }
                                                                                }

                                                                                @Override
                                                                                public void onFailure(Throwable t) {
                                                                                    Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                }
                                                                            });
                                                                        }

                                                                        if(imageStatus[1]){
                                                                            Retrofit retrofit2 = new Retrofit.Builder()
                                                                                    .baseUrl(ipClass.ipAddress).
                                                                                            addConverterFactory(GsonConverterFactory.create())
                                                                                    .build();
                                                                            final APIService service = retrofit2.create(APIService.class);

                                                                            Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_2","2",imageToString(images[1]));

                                                                            imageData.enqueue(new Callback<String>() {
                                                                                @Override
                                                                                public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                    if(image_file2.exists()) {
                                                                                        image_file2.delete();
                                                                                    }
                                                                                }

                                                                                @Override
                                                                                public void onFailure(Throwable t) {
                                                                                    Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                }
                                                                            });
                                                                        }

                                                                        if(imageStatus[2]){
                                                                            Retrofit retrofit2 = new Retrofit.Builder()
                                                                                    .baseUrl(ipClass.ipAddress).
                                                                                            addConverterFactory(GsonConverterFactory.create())
                                                                                    .build();
                                                                            final APIService service = retrofit2.create(APIService.class);

                                                                            Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_3","3",imageToString(images[2]));

                                                                            imageData.enqueue(new Callback<String>() {
                                                                                @Override
                                                                                public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                    if(image_file3.exists()) {
                                                                                        image_file3.delete();
                                                                                    }
                                                                                }

                                                                                @Override
                                                                                public void onFailure(Throwable t) {
                                                                                    Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                }
                                                                            });
                                                                        }

                                                                        if(imageStatus[3]){
                                                                            Retrofit retrofit2 = new Retrofit.Builder()
                                                                                    .baseUrl(ipClass.ipAddress).
                                                                                            addConverterFactory(GsonConverterFactory.create())
                                                                                    .build();
                                                                            final APIService service = retrofit2.create(APIService.class);

                                                                            Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_4","4",imageToString(images[3]));

                                                                            imageData.enqueue(new Callback<String>() {
                                                                                @Override
                                                                                public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                    if(image_file4.exists()) {
                                                                                        image_file4.delete();
                                                                                    }
                                                                                }

                                                                                @Override
                                                                                public void onFailure(Throwable t) {
                                                                                    Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                }
                                                                            });
                                                                        }

                                                                        if(imageStatus[4]){
                                                                            Retrofit retrofit2 = new Retrofit.Builder()
                                                                                    .baseUrl(ipClass.ipAddress).
                                                                                            addConverterFactory(GsonConverterFactory.create())
                                                                                    .build();
                                                                            final APIService service = retrofit2.create(APIService.class);

                                                                            Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_5","5",imageToString(images[4]));

                                                                            imageData.enqueue(new Callback<String>() {
                                                                                @Override
                                                                                public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                    if(image_file5.exists()) {
                                                                                        image_file5.delete();
                                                                                    }
                                                                                }

                                                                                @Override
                                                                                public void onFailure(Throwable t) {
                                                                                    Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                }
                                                                            });
                                                                        }

                                                                        if(imageStatus[5]){
                                                                            Retrofit retrofit2 = new Retrofit.Builder()
                                                                                    .baseUrl(ipClass.ipAddress).
                                                                                            addConverterFactory(GsonConverterFactory.create())
                                                                                    .build();
                                                                            final APIService service = retrofit2.create(APIService.class);

                                                                            Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_6","6",imageToString(images[5]));

                                                                            imageData.enqueue(new Callback<String>() {
                                                                                @Override
                                                                                public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                    if(image_file6.exists()) {
                                                                                        image_file6.delete();
                                                                                    }
                                                                                }

                                                                                @Override
                                                                                public void onFailure(Throwable t) {
                                                                                    Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                }
                                                                            });
                                                                        }

                                                                        if(imageStatus[6]){
                                                                            Retrofit retrofit2 = new Retrofit.Builder()
                                                                                    .baseUrl(ipClass.ipAddress).
                                                                                            addConverterFactory(GsonConverterFactory.create())
                                                                                    .build();
                                                                            final APIService service = retrofit2.create(APIService.class);

                                                                            Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_7","7",imageToString(images[6]));

                                                                            imageData.enqueue(new Callback<String>() {
                                                                                @Override
                                                                                public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                    if(image_file7.exists()) {
                                                                                        image_file7.delete();
                                                                                    }
                                                                                }

                                                                                @Override
                                                                                public void onFailure(Throwable t) {
                                                                                    Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                }
                                                                            });
                                                                        }

                                                                        if(imageStatus[7]){
                                                                            Retrofit retrofit2 = new Retrofit.Builder()
                                                                                    .baseUrl(ipClass.ipAddress).
                                                                                            addConverterFactory(GsonConverterFactory.create())
                                                                                    .build();
                                                                            final APIService service = retrofit2.create(APIService.class);

                                                                            Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_8","8",imageToString(images[7]));

                                                                            imageData.enqueue(new Callback<String>() {
                                                                                @Override
                                                                                public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                    if(image_file8.exists()) {
                                                                                        image_file8.delete();
                                                                                    }
                                                                                }

                                                                                @Override
                                                                                public void onFailure(Throwable t) {
                                                                                    Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                }
                                                                            });
                                                                        }

                                                                        if(imageStatus[8]){
                                                                            Retrofit retrofit2 = new Retrofit.Builder()
                                                                                    .baseUrl(ipClass.ipAddress).
                                                                                            addConverterFactory(GsonConverterFactory.create())
                                                                                    .build();
                                                                            final APIService service = retrofit2.create(APIService.class);

                                                                            Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_9","9",imageToString(images[8]));

                                                                            imageData.enqueue(new Callback<String>() {
                                                                                @Override
                                                                                public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                    if(image_file9.exists()) {
                                                                                        image_file9.delete();
                                                                                    }
                                                                                }

                                                                                @Override
                                                                                public void onFailure(Throwable t) {
                                                                                    Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                }
                                                                            });
                                                                        }

                                                                        if(imageStatus[9]){
                                                                            Retrofit retrofit2 = new Retrofit.Builder()
                                                                                    .baseUrl(ipClass.ipAddress).
                                                                                            addConverterFactory(GsonConverterFactory.create())
                                                                                    .build();
                                                                            final APIService service = retrofit2.create(APIService.class);

                                                                            Call<String> imageData = service.ProcessImageUpload(customerId,orderId,"photo_10","10",imageToString(images[9]));

                                                                            imageData.enqueue(new Callback<String>() {
                                                                                @Override
                                                                                public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                    if(image_file10.exists()) {
                                                                                        image_file10.delete();
                                                                                    }
                                                                                }

                                                                                @Override
                                                                                public void onFailure(Throwable t) {
                                                                                    Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                }
                                                                            });
                                                                        }

                                                                        Call<String> updateMeasurementData = service.UpdateMeasurement(measurementId,stanceId,stanceValue,stanceImgValue,"",
                                                                                "","","","","","",
                                                                                "","",hipDescriptionId,hipDescriptionValue,hipDescriptionImgValue,"","","","","","","","",hipSignBtn.getText().toString() + hipValue,"",
                                                                                "","","","",fullBackLengthSignBtn.getText().toString() + fullBackLengthValue,"",
                                                                                trouserWaistSignBtn.getText().toString() + trouserWaistValue,trouserOutseamSignBtn.getText().toString() + trouserOutseamValue,trouserInseamSignBtn.getText().toString() + trouserInseamValue,"",thighSignBtn.getText().toString() + thighValue,kneeSignBtn.getText().toString() + kneeValue,"","");


                                                                        updateMeasurementData.enqueue(new Callback<String>() {
                                                                            @Override
                                                                            public void onResponse(Response<String> response, Retrofit retrofit) {

                                                                                if(response.body().equals("Successful")){


                                                                                    Call<String> updateStylingData = service.UpdateStyling(stylingId,fabricIdValue,fabricNumberValue,"","",trouserFitId,trouserFitValue,trouserFitImgValue,
                                                                                            "","","","","","",
                                                                                            "","","","","","","","","",
                                                                                            "","","","","","",
                                                                                            "","","","","","","","","",
                                                                                            "","","","","","","",
                                                                                            "","",trouserPleatsId,trouserPleatsValue,trouserPleatsImgValue,trouserBackPocketId,trouserBackPocketValue,
                                                                                            trouserBackPocketImgValue,trouserCuffId,trouserCuffValue,trouserCuffImgValue,trouserLoopTabId,trouserLoopTabValue,trouserLoopTabImgValue,
                                                                                            "","","","","","","",
                                                                                            "","","","","","","",
                                                                                            "","","","","","","",
                                                                                            "","","","","","","","","");


                                                                                    updateStylingData.enqueue(new Callback<String>() {
                                                                                        @Override
                                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {
                                                                                            if(response.body().equals("Successful")){

                                                                                                Call<String> timeline = service.UpdateStatusTimeline(orderId,"Pending Review");

                                                                                                timeline.enqueue(new Callback<String>() {
                                                                                                    @Override
                                                                                                    public void onResponse(Response<String> response, Retrofit retrofit) {

                                                                                                        Intent intent = new Intent(getApplicationContext(),PlaceOrderActivity.class);
                                                                                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                                                                        startActivity(intent);
                                                                                                        finish();
                                                                                                    }

                                                                                                    @Override
                                                                                                    public void onFailure(Throwable t) {

                                                                                                    }
                                                                                                });

                                                                                            }
                                                                                            else{
                                                                                                Toast.makeText(getApplicationContext(),"Can't place order",Toast.LENGTH_LONG).show();
                                                                                            }
                                                                                        }

                                                                                        @Override
                                                                                        public void onFailure(Throwable t) {
                                                                                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                    });
                                                                                }
                                                                            }

                                                                            @Override
                                                                            public void onFailure(Throwable t) {
                                                                                Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                                                                            }
                                                                        });

                                                                    }

                                                                }

                                                                @Override
                                                                public void onFailure(Throwable t) {
                                                                    Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_LONG).show();
                                                                }
                                                            });
                                                        }
                                                    }

                                                    @Override
                                                    public void onFailure(Throwable t) {
                                                        Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_LONG).show();
                                                    }
                                                });

                                            }

                                        }
                                        else {
                                            Toast.makeText(getApplicationContext(),"Make all selections in Measurement Section",Toast.LENGTH_LONG).show();
                                        }

                                    }

                                }
                                else {
                                    Toast.makeText(getApplicationContext(),"Make all selections in Suit Styling Section",Toast.LENGTH_LONG).show();
                                }
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"Complete Form in Customer Info Section \n Check Phone Number and Email",Toast.LENGTH_LONG).show();
                            }
                        }
                        submit.setEnabled(true);
                    }
                });
                builder.setNegativeButton("Edit",null);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        File folder = new File("sdcard/zeitgeist_photos");
        deleteRecursive(folder);

        Arrays.fill(imageStatus, Boolean.FALSE);
        Arrays.fill(images, null);
        ((ZeitGeist) getApplication()).setImages(images);
        ((ZeitGeist) getApplication()).setImageStatus(imageStatus);
        ((ZeitGeist) this.getApplication()).setImage_file1(null);
        ((ZeitGeist) this.getApplication()).setImage_file2(null);
        ((ZeitGeist) this.getApplication()).setImage_file3(null);
        ((ZeitGeist) this.getApplication()).setImage_file4(null);
        ((ZeitGeist) this.getApplication()).setImage_file5(null);
        ((ZeitGeist) this.getApplication()).setImage_file6(null);
        ((ZeitGeist) this.getApplication()).setImage_file7(null);
        ((ZeitGeist) this.getApplication()).setImage_file8(null);
        ((ZeitGeist) this.getApplication()).setImage_file9(null);
        ((ZeitGeist) this.getApplication()).setImage_file10(null);
    }

    public void deleteRecursive(File fileOrDirectory) {

        if (fileOrDirectory.isDirectory()) {
            for (File child : fileOrDirectory.listFiles()) {
                deleteRecursive(child);
            }
        }

        fileOrDirectory.delete();
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(OrderTakingPant.this);
        builder.setTitle("Are you Sure?");
        builder.setMessage("Form data will be lost if you go back");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("No",null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }
    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }



    private String imageToString(Bitmap image){
        if (image != null){
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
            byte[] imageByte = byteArrayOutputStream.toByteArray();
            return Base64.encodeToString(imageByte,Base64.DEFAULT);
        }
        else {
            return "";
        }
    }

    public void setupUI(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(OrderTakingPant.this);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        try {
            inputMethodManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(), 0);
        }
        catch (NullPointerException e){
        }
    }

    public int getIndex(List<GridListItem> listItems,String itemId){

        try{
            for(int i = 0 ; i < listItems.size() ; i++){
                if(itemId.equals(listItems.get(i).getItemId())){
                    return i;
                }
            }
        }
        catch (NullPointerException e){

        }
        return -1;
    }

    public int makeSelection(List<GridListItem> listItems,String itemId,StylingAdapter adapter){
        int index;
        index = getIndex(listItems,itemId);
        if(index != -1){
            adapter.makeAllUnselect(index);
            adapter.notifyDataSetChanged();
        }
        return index;
    }

    public String setValue(List<GridListItem> listItems,int index){
        if(index != -1){
            return listItems.get(index).getItemName();
        }
        return null;
    }

    public String setImgValue(List<GridListItem> listItems,int index){
        if(index != -1){
            return listItems.get(index).getItemImageSource();
        }
        return null;
    }
    public int setPriceValue(List<GridListItem> listItems,int index){
        if(index != -1){
            return Integer.parseInt(listItems.get(index).getItemPrice());
        }
        return 0;
    }

    public int getFabricLiningIndex(List<FabricLiningItem> listItems,String itemId){
        for(int i = 0 ; i < listItems.size() ; i++){
            if(itemId.equals(listItems.get(i).getItemId())){
                return i;
            }
        }
        return -1;
    }

    public void makeFabricLiningSelection(List<FabricLiningItem> listItems,String itemId,String number,FabricLiningAdapter adapter,TextView numberDisplay){
        int index;
        index = getFabricLiningIndex(listItems,itemId);
        if(index != -1){
            number = listItems.get(index).getItemNumber();
            numberDisplay.setText(number);
            adapter.makeAllUnselect(index);
            adapter.notifyDataSetChanged();
        }
    }


    public static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMddHHmmssSSS");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

    public void calculateBill(){

        int selectedId = discountType.getCheckedRadioButtonId();
        int amount = Integer.parseInt(calculatedAmount.getText().toString());
        int discountVal;

        if(discount.getText().toString().trim().length() < 1){
            discountVal = 0;
            discount.setText("0");
        }
        else {
            discountVal = Integer.parseInt(discount.getText().toString());
        }

        if(selectedId == percenttagerb.getId()){
            int percentAmnt = (amount * discountVal) / 100;
            bill.setText(String.valueOf(amount - percentAmnt));
        }
        else if(selectedId == rupeesrb.getId()) {
            bill.setText(String.valueOf(amount - discountVal));
        }
        else{
            bill.setText(String.valueOf(amount));
        }
    }

    public void calculateAmount(){
        int totalAmount = fabricPrice + trouserPleatsPrice + trouserBackPocketPrice + trouserCuffPrice + trouserLoopTabPrice + trouserFitPrice;
        calculatedAmount.setText(String.valueOf(totalAmount));
    }

    private void zoomImageFromThumb(final View thumbView,String imageSource){

        if (mCurrentAnimator != null) {
            mCurrentAnimator.cancel();
        }

        Picasso.with(getApplicationContext()).load(ipClass.ipAddress + imageSource).into(expandedImageView);


        final Rect startBounds = new Rect();
        final Rect finalBounds = new Rect();
        final Point globalOffset = new Point();


        thumbView.getGlobalVisibleRect(startBounds);
        container.getGlobalVisibleRect(finalBounds,globalOffset);
        startBounds.offset(-globalOffset.x,-globalOffset.y);
        finalBounds.offset(-globalOffset.x,-globalOffset.y);

        float startScale;
        if((float) finalBounds.width() / finalBounds.height() > (float) startBounds.width() / startBounds.height()){
            startScale = (float) startBounds.height() / finalBounds.height();
            float startWidth = startScale * finalBounds.width();
            float deltaWidth = (startWidth - startBounds.width()) / 2;
            startBounds.left -= deltaWidth;
            startBounds.right += deltaWidth;
        }
        else {
            startScale = (float) startBounds.width() / finalBounds.width();
            float startHeight = startScale * finalBounds.height();
            float deltaHeight = (startHeight - startBounds.height()) / 2;
            startBounds.top -= deltaHeight;
            startBounds.bottom += deltaHeight;
        }

        thumbView.setAlpha(0f);
        expandedImageView.setVisibility(View.VISIBLE);

        expandedImageView.setPivotX(0f);
        expandedImageView.setPivotY(0f);


        AnimatorSet set = new AnimatorSet();
        set.play(
                ObjectAnimator.ofFloat(expandedImageView,View.X,startBounds.left,finalBounds.left))
                .with(ObjectAnimator.ofFloat(expandedImageView,View.Y,startBounds.top,finalBounds.top))
                .with(ObjectAnimator.ofFloat(expandedImageView,View.SCALE_X,startScale, 1f))
                .with(ObjectAnimator.ofFloat(expandedImageView,View.SCALE_Y,startScale,1f));
        set.setDuration(mShortAnimationDuration);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationCancel(Animator animation) {
                mCurrentAnimator = null;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mCurrentAnimator = null;
            }
        });

        set.start();
        mCurrentAnimator = set;


        final float startScaleFinal = startScale;
        expandedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCurrentAnimator != null) {
                    mCurrentAnimator.cancel();
                }


                AnimatorSet set = new AnimatorSet();
                set.play(
                        ObjectAnimator.ofFloat(expandedImageView, View.X,
                                startBounds.left))
                        .with(ObjectAnimator.ofFloat(expandedImageView, View.Y,
                                startBounds.top))
                        .with(ObjectAnimator.ofFloat(expandedImageView,
                                View.SCALE_X, startScaleFinal))
                        .with(ObjectAnimator.ofFloat(expandedImageView,
                                View.SCALE_Y, startScaleFinal));
                set.setDuration(mShortAnimationDuration);
                set.setInterpolator(new DecelerateInterpolator());
                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        mCurrentAnimator = null;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        mCurrentAnimator = null;
                    }
                });
                set.start();
                mCurrentAnimator = set;
            }
        });

    }

}