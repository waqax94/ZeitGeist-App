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
import android.support.design.widget.Snackbar;
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

public class OrderTakingWaistCoat extends AppCompatActivity {

    String orderCondition;

    IpClass ipClass = new IpClass();

    String shopId,itemType,orderId,fabricConnsumption,liningConsumption,garmentId = "4";
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
    GridView fabricStyle,liningNumber;
    RelativeLayout container;
    FabricLiningAdapter fabricStyleAdapter;
    LiningAdapter liningNumberAdapter;
    Button nextBtnF,clearFabricSelection,clearLiningSelection;
    SearchView fabricSearch,liningSearch;
    List<FabricLiningItem> fabricList,fabricListSearchable;
    List<LiningItem> liningList,liningListSearchable;
    String fabricIdValue,fabricNumberValue,liningIdValue,liningNumberValue,previousFabricId,previousLiningId;
    int fabricPrice,liningPrice;
    ImageView expandedImageView;


    List<GridListItem> stanceList,shoulderSlopeList,chestDescriptionList,stomachDescriptionList,hipDescriptionList;
    GridViewScrollable stance,shoulderSlope,chestDescription,stomachDescription,hipDescription;
    StylingAdapter stanceAdapter,shoulderSlopeAdapter,chestDescriptionAdapter,stomachDescriptionAdapter,hipDescriptionAdapter;
    EditText neck,fullChest,fullShoulderWidth,waistStomach,hip,frontChestWidth,
            backWidth,halfShoulderWidthLeft,halfShoulderWidthRight,fullBackLength,halfBackLength;
    ImageView neckImage,fullChestImage,fullShoulderWidthImage,waistStomachImage,hipImage,frontChestWidthImage,
            backWidthImage,halfShoulderWidthLeftImage,halfShoulderWidthRightImage,fullBackLengthImage,halfBackLengthImage;
    ImageButton neckZoomIcon,fullChestZoomIcon,fullShoulderWidthZoomIcon,waistStomachZoomIcon,hipZoomIcon,frontChestWidthZoomIcon,
            backWidthZoomIcon,halfShoulderWidthLeftZoomIcon,halfShoulderWidthRightZoomIcon,fullBackLengthZoomIcon,halfBackLengthZoomIcon;
    Button nextBtnM;
    Button neckSignBtn,fullChestSignBtn,fullShoulderWidthSignBtn,waistStomachSignBtn,hipSignBtn,frontChestWidthSignBtn,
            backWidthSignBtn,halfShoulderWidthLeftSignBtn,halfShoulderWidthRightSignBtn,fullBackLengthSignBtn,halfBackLengthSignBtn;
    String  measurementId,stanceId,shoulderSlopeId,chestDescriptionId,stomachDescriptionId,hipDescriptionId,stanceValue,stanceImgValue,
            shoulderSlopeValue,shoulderSlopeImgValue,chestDescriptionValue,chestDescriptionImgValue,
            stomachDescriptionValue,stomachDescriptionImgValue,hipDescriptionValue,hipDescriptionImgValue,neckValue,fullChestValue,
            fullShoulderWidthValue,waistStomachValue,hipValue,
            frontChestWidthValue,backWidthValue,halfShoulderWidthLeftValue,halfShoulderWidthRightValue,fullBackLengthValue,halfBackLengthValue;


    TextView fabricNumberData,liningNumberData;
    List<GridListItem> waistCoatList,waistCoatPocketTypeList,waistCoatBackList;
    GridViewScrollable waistCoat,waistCoatPocketType,waistCoatBack;
    StylingAdapter waistCoatAdapter,waistCoatPocketTypeAdapter,waistCoatBackAdapter;
    Drawable selected,unselected;
    Button nextBtnS;
    String  stylingId,waistCoatId,waistCoatPocketTypeId,waistCoatBackId,waistCoatValue,waistCoatPocketTypeValue,waistCoatBackValue,waistCoatImgValue,waistCoatPocketTypeImgValue,
            waistCoatBackImgValue;
    int     waistCoatPrice,waistCoatPocketTypePrice,waistCoatBackPrice;


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
        setContentView(R.layout.activity_order_taking_waist_coat);
        setupUI(findViewById(R.id.content_order_taking_waist_coat));
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
                AlertDialog.Builder builder = new AlertDialog.Builder(OrderTakingWaistCoat.this);
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
        itemType = "Waist Coat";

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
        container = (RelativeLayout) findViewById(R.id.content_order_taking_waist_coat);
        fabricText = (TextView) findViewById(R.id.fabrics_text);
        fabricStyle = (GridView) findViewById(R.id.fabric_list);
        fabricStyleAdapter = new FabricLiningAdapter(getApplicationContext(),R.layout.fabric_grid_layout,selected,unselected,expandedImageView,container);
        liningNumber = (GridView) findViewById(R.id.lining_list);
        liningNumberAdapter = new LiningAdapter(getApplicationContext(),R.layout.fabric_grid_layout,selected,unselected,expandedImageView,container);
        clearFabricSelection = (Button) findViewById(R.id.fabric_selection_clear);
        clearLiningSelection = (Button) findViewById(R.id.lining_selection_clear);
        fabricSearch = (SearchView) findViewById(R.id.fabric_search_data);
        liningSearch = (SearchView) findViewById(R.id.lining_search_data);
        nextBtnF = (Button) findViewById(R.id.next_btn_f);

        stance = (GridViewScrollable) findViewById(R.id.stance_list);
        stanceAdapter = new StylingAdapter(getApplicationContext(),R.layout.gridview_layout,selected,unselected,expandedImageView,container);
        shoulderSlope = (GridViewScrollable) findViewById(R.id.shoulder_slope_list);
        shoulderSlopeAdapter = new StylingAdapter(getApplicationContext(),R.layout.gridview_layout,selected,unselected,expandedImageView,container);
        chestDescription = (GridViewScrollable) findViewById(R.id.chest_description_list);
        chestDescriptionAdapter = new StylingAdapter(getApplicationContext(),R.layout.gridview_layout,selected,unselected,expandedImageView,container);
        stomachDescription = (GridViewScrollable) findViewById(R.id.stomach_description_list);
        stomachDescriptionAdapter = new StylingAdapter(getApplicationContext(),R.layout.gridview_layout,selected,unselected,expandedImageView,container);
        hipDescription = (GridViewScrollable) findViewById(R.id.hip_description_list);
        hipDescriptionAdapter = new StylingAdapter(getApplicationContext(),R.layout.gridview_layout,selected,unselected,expandedImageView,container);
        nextBtnM = (Button) findViewById(R.id.next_btn_m);

        neckSignBtn = (Button) findViewById(R.id.neck_sign_btn);
        fullChestSignBtn = (Button) findViewById(R.id.full_chest_sign_btn);
        fullShoulderWidthSignBtn = (Button) findViewById(R.id.full_shoulder_width_sign_btn);
        waistStomachSignBtn = (Button) findViewById(R.id.waist_stomach_sign_btn);
        hipSignBtn = (Button) findViewById(R.id.hip_sign_btn);
        frontChestWidthSignBtn = (Button) findViewById(R.id.front_chest_width_sign_btn);
        backWidthSignBtn = (Button) findViewById(R.id.back_width_sign_btn);
        halfShoulderWidthLeftSignBtn = (Button) findViewById(R.id.half_shoulder_width_left_sign_btn);
        halfShoulderWidthRightSignBtn = (Button) findViewById(R.id.half_shoulder_width_right_sign_btn);
        fullBackLengthSignBtn = (Button) findViewById(R.id.full_back_length_sign_btn);
        halfBackLengthSignBtn = (Button) findViewById(R.id.half_back_length_sign_btn);

        neck = (EditText) findViewById(R.id.neck_data);
        fullChest = (EditText) findViewById(R.id.full_chest_data);
        fullShoulderWidth = (EditText) findViewById(R.id.full_shoulder_width_data);
        waistStomach = (EditText) findViewById(R.id.waist_stomach_data);
        hip = (EditText) findViewById(R.id.hip_data);
        frontChestWidth = (EditText) findViewById(R.id.front_chest_width_data);
        backWidth = (EditText) findViewById(R.id.back_width_data);
        halfShoulderWidthLeft = (EditText) findViewById(R.id.half_shoulder_width_left_data);
        halfShoulderWidthRight = (EditText) findViewById(R.id.half_shoulder_width_right_data);
        fullBackLength = (EditText) findViewById(R.id.full_back_length_data);
        halfBackLength = (EditText) findViewById(R.id.half_back_length_data);

        neckImage = (ImageView) findViewById(R.id.neck_img);
        fullChestImage = (ImageView) findViewById(R.id.full_chest_img);
        fullShoulderWidthImage = (ImageView) findViewById(R.id.full_shoulder_width_img);
        waistStomachImage = (ImageView) findViewById(R.id.waist_stomach_img);
        hipImage = (ImageView) findViewById(R.id.hip_img);
        frontChestWidthImage = (ImageView) findViewById(R.id.front_chest_width_img);
        backWidthImage = (ImageView) findViewById(R.id.back_width_img);
        halfShoulderWidthLeftImage = (ImageView) findViewById(R.id.half_shoulder_width_left_img);
        halfShoulderWidthRightImage = (ImageView) findViewById(R.id.half_shoulder_width_right_img);
        fullBackLengthImage = (ImageView) findViewById(R.id.full_back_length_img);
        halfBackLengthImage = (ImageView) findViewById(R.id.half_back_length_img);

        neckZoomIcon = (ImageButton) findViewById(R.id.neck_zoom_icon);
        fullChestZoomIcon = (ImageButton) findViewById(R.id.full_chest_zoom_icon);
        fullShoulderWidthZoomIcon = (ImageButton) findViewById(R.id.full_shoulder_width_zoom_icon);
        waistStomachZoomIcon = (ImageButton) findViewById(R.id.waist_stomach_zoom_icon);
        hipZoomIcon = (ImageButton) findViewById(R.id.hip_zoom_icon);
        frontChestWidthZoomIcon = (ImageButton) findViewById(R.id.front_chest_width_zoom_icon);
        backWidthZoomIcon = (ImageButton) findViewById(R.id.back_width_zoom_icon);
        halfShoulderWidthLeftZoomIcon = (ImageButton) findViewById(R.id.half_shoulder_width_left_zoom_icon);
        halfShoulderWidthRightZoomIcon = (ImageButton) findViewById(R.id.half_shoulder_width_right_zoom_icon);
        fullBackLengthZoomIcon = (ImageButton) findViewById(R.id.full_back_length_zoom_icon);
        halfBackLengthZoomIcon = (ImageButton) findViewById(R.id.half_back_length_zoom_icon);

        fabricNumberData = (TextView) findViewById(R.id.fabric_data);
        liningNumberData = (TextView) findViewById(R.id.lining_data);
        waistCoat = (GridViewScrollable) findViewById(R.id.waist_coat_list);
        waistCoatAdapter = new StylingAdapter(getApplicationContext(),R.layout.gridview_layout,selected,unselected,expandedImageView,container);
        waistCoatPocketType = (GridViewScrollable) findViewById(R.id.waist_coat_pocket_type_list);
        waistCoatPocketTypeAdapter = new StylingAdapter(getApplicationContext(),R.layout.gridview_layout,selected,unselected,expandedImageView,container);
        waistCoatBack = (GridViewScrollable) findViewById(R.id.waist_coat_back_list);
        waistCoatBackAdapter = new StylingAdapter(getApplicationContext(),R.layout.gridview_layout,selected,unselected,expandedImageView,container);
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



                DatePickerDialog datePickerDialog = new DatePickerDialog(OrderTakingWaistCoat.this, new DatePickerDialog.OnDateSetListener() {
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


        Call<List<FabricLiningItem>> fabricData = service.ProcessFabricItem("waist_coat");

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
                            fabricPrice = Integer.parseInt(fabricListSearchable.get(position).getItemBsWaistCoatPrice());
                        }
                        else if(orderTypeValue.equals("MTM") && !fabricChoices.get(Integer.parseInt(fabricChoice.getSelectedItem().toString())).equals("Self")) {
                            fabricPrice = Integer.parseInt(fabricListSearchable.get(position).getItemMtmWaistCoatPrice());
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


        Call<List<LiningItem>> liningData = service.ProcessLiningItem("waist_coat");

        liningData.enqueue(new Callback<List<LiningItem>>() {
            @Override
            public void onResponse(Response<List<LiningItem>> response, Retrofit retrofit) {
                liningList = response.body();
                liningNumberAdapter.addItemList(liningList);
                liningNumber.setAdapter(liningNumberAdapter);

                liningNumber.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        liningListSearchable = liningNumberAdapter.getFilterList();
                        Toast.makeText(getApplicationContext(),liningListSearchable.get(position).getItemNumber(),Toast.LENGTH_LONG).show();
                        liningNumberValue = liningListSearchable.get(position).getItemNumber();
                        liningIdValue = liningListSearchable.get(position).getItemId();
                        liningNumberData.setText(liningNumberValue);
                        liningNumberAdapter.makeAllUnselect(position);
                        liningNumberAdapter.notifyDataSetChanged();
                        liningPrice = Integer.parseInt(liningListSearchable.get(position).getItemWaistCoatPrice());

                        calculateAmount();
                        calculateBill();
                    }
                });

                clearLiningSelection.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        liningNumberValue = "";
                        liningIdValue = "";
                        liningNumberData.setText("");
                        liningPrice = 0;
                        liningNumberAdapter.removeSelection();
                        liningNumberAdapter.notifyDataSetChanged();
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

        liningSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                liningNumberAdapter.getFilter().filter(newText);
                try{
                    liningNumberAdapter.removeSelection();
                    liningPrice = 0;
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


        Call<List<GridListItem>> shoulderSlopeData = service.ProcessListItem("28");

        shoulderSlopeData.enqueue(new Callback<List<GridListItem>>() {
            @Override
            public void onResponse(Response<List<GridListItem>> response, Retrofit retrofit) {
                shoulderSlopeList = response.body();
                shoulderSlopeAdapter.addItemList(shoulderSlopeList);
                shoulderSlope.setAdapter(shoulderSlopeAdapter);

                shoulderSlope.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(getApplicationContext(),shoulderSlopeList.get(position).getItemName(),Toast.LENGTH_LONG).show();
                        shoulderSlopeId = shoulderSlopeList.get(position).getItemId();
                        shoulderSlopeValue = shoulderSlopeList.get(position).getItemName();
                        shoulderSlopeImgValue = shoulderSlopeList.get(position).getItemImageSource();
                        shoulderSlopeAdapter.makeAllUnselect(position);
                        shoulderSlopeAdapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });

        Call<List<GridListItem>> chestDescriptionData = service.ProcessListItem("29");

        chestDescriptionData.enqueue(new Callback<List<GridListItem>>() {
            @Override
            public void onResponse(Response<List<GridListItem>> response, Retrofit retrofit) {
                chestDescriptionList = response.body();
                chestDescriptionAdapter.addItemList(chestDescriptionList);
                chestDescription.setAdapter(chestDescriptionAdapter);

                chestDescription.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(getApplicationContext(),chestDescriptionList.get(position).getItemName(),Toast.LENGTH_LONG).show();
                        chestDescriptionId = chestDescriptionList.get(position).getItemId();
                        chestDescriptionValue = chestDescriptionList.get(position).getItemName();
                        chestDescriptionImgValue = chestDescriptionList.get(position).getItemImageSource();
                        chestDescriptionAdapter.makeAllUnselect(position);
                        chestDescriptionAdapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });

        Call<List<GridListItem>> stomachDescriptionData = service.ProcessListItem("30");

        stomachDescriptionData.enqueue(new Callback<List<GridListItem>>() {
            @Override
            public void onResponse(Response<List<GridListItem>> response, Retrofit retrofit) {
                stomachDescriptionList = response.body();
                stomachDescriptionAdapter.addItemList(stomachDescriptionList);
                stomachDescription.setAdapter(stomachDescriptionAdapter);

                stomachDescription.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(getApplicationContext(),stomachDescriptionList.get(position).getItemName(),Toast.LENGTH_LONG).show();
                        stomachDescriptionId = stomachDescriptionList.get(position).getItemId();
                        stomachDescriptionValue = stomachDescriptionList.get(position).getItemName();
                        stomachDescriptionImgValue = stomachDescriptionList.get(position).getItemImageSource();
                        stomachDescriptionAdapter.makeAllUnselect(position);
                        stomachDescriptionAdapter.notifyDataSetChanged();
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

        Call<String> neckData = service.ProcessMeasurementImage("1");

        neckData.enqueue(new Callback<String>() {
            @Override
            public void onResponse(final Response<String> response, Retrofit retrofit) {
                if(!response.body().equals("")){
                    Picasso.with(getApplicationContext()).load(ipClass.ipAddress + response.body()).into(neckImage);
                    neckZoomIcon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            zoomImageFromThumb(neckImage,response.body());
                        }
                    });
                }
            }
            @Override
            public void onFailure(Throwable t) {
            }
        });

        Call<String> fullChestData = service.ProcessMeasurementImage("2");

        fullChestData.enqueue(new Callback<String>() {
            @Override
            public void onResponse(final Response<String> response, Retrofit retrofit) {
                if(!response.body().equals("")){
                    Picasso.with(getApplicationContext()).load(ipClass.ipAddress + response.body()).into(fullChestImage);
                    fullChestZoomIcon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            zoomImageFromThumb(fullChestImage,response.body());
                        }
                    });
                }
            }
            @Override
            public void onFailure(Throwable t) {
            }
        });

        Call<String> fullShoulderWidthData = service.ProcessMeasurementImage("3");

        fullShoulderWidthData.enqueue(new Callback<String>() {
            @Override
            public void onResponse(final Response<String> response, Retrofit retrofit) {
                if(!response.body().equals("")){
                    Picasso.with(getApplicationContext()).load(ipClass.ipAddress + response.body()).into(fullShoulderWidthImage);
                    fullShoulderWidthZoomIcon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            zoomImageFromThumb(fullShoulderWidthImage,response.body());
                        }
                    });
                }
            }
            @Override
            public void onFailure(Throwable t) {
            }
        });

        Call<String> waistStomachData = service.ProcessMeasurementImage("8");

        waistStomachData.enqueue(new Callback<String>() {
            @Override
            public void onResponse(final Response<String> response, Retrofit retrofit) {
                if(!response.body().equals("")){
                    Picasso.with(getApplicationContext()).load(ipClass.ipAddress + response.body()).into(waistStomachImage);
                    waistStomachZoomIcon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            zoomImageFromThumb(waistStomachImage,response.body());
                        }
                    });
                }
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


        Call<String> frontChestWidthData = service.ProcessMeasurementImage("11");

        frontChestWidthData.enqueue(new Callback<String>() {
            @Override
            public void onResponse(final Response<String> response, Retrofit retrofit) {
                if(!response.body().equals("")){
                    Picasso.with(getApplicationContext()).load(ipClass.ipAddress + response.body()).into(frontChestWidthImage);
                    frontChestWidthZoomIcon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            zoomImageFromThumb(frontChestWidthImage,response.body());
                        }
                    });
                }
            }
            @Override
            public void onFailure(Throwable t) {
            }
        });

        Call<String> backWidthData = service.ProcessMeasurementImage("12");

        backWidthData.enqueue(new Callback<String>() {
            @Override
            public void onResponse(final Response<String> response, Retrofit retrofit) {
                if(!response.body().equals("")){
                    Picasso.with(getApplicationContext()).load(ipClass.ipAddress + response.body()).into(backWidthImage);
                    backWidthZoomIcon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            zoomImageFromThumb(backWidthImage,response.body());
                        }
                    });
                }
            }
            @Override
            public void onFailure(Throwable t) {
            }
        });

        Call<String> halfShoulderWidthLeftData = service.ProcessMeasurementImage("13");

        halfShoulderWidthLeftData.enqueue(new Callback<String>() {
            @Override
            public void onResponse(final Response<String> response, Retrofit retrofit) {
                if(!response.body().equals("")){
                    Picasso.with(getApplicationContext()).load(ipClass.ipAddress + response.body()).into(halfShoulderWidthLeftImage);
                    halfShoulderWidthLeftZoomIcon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            zoomImageFromThumb(halfShoulderWidthLeftImage,response.body());
                        }
                    });
                }
            }
            @Override
            public void onFailure(Throwable t) {
            }
        });

        Call<String> halfShoulderWidthRightData = service.ProcessMeasurementImage("14");

        halfShoulderWidthRightData.enqueue(new Callback<String>() {
            @Override
            public void onResponse(final Response<String> response, Retrofit retrofit) {
                if(!response.body().equals("")){
                    Picasso.with(getApplicationContext()).load(ipClass.ipAddress + response.body()).into(halfShoulderWidthRightImage);
                    halfShoulderWidthRightZoomIcon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            zoomImageFromThumb(halfShoulderWidthRightImage,response.body());
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

        Call<String> halfBackLengthData = service.ProcessMeasurementImage("16");

        halfBackLengthData.enqueue(new Callback<String>() {
            @Override
            public void onResponse(final Response<String> response, Retrofit retrofit) {
                if(!response.body().equals("")){
                    Picasso.with(getApplicationContext()).load(ipClass.ipAddress + response.body()).into(halfBackLengthImage);
                    halfBackLengthZoomIcon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            zoomImageFromThumb(halfBackLengthImage,response.body());
                        }
                    });
                }
            }
            @Override
            public void onFailure(Throwable t) {
            }
        });




        neckSignBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(neckSignBtn.getText().toString().equals("+")){
                    neckSignBtn.setText("-");
                }
                else {
                    neckSignBtn.setText("+");
                }
            }
        });

        fullChestSignBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fullChestSignBtn.getText().toString().equals("+")){
                    fullChestSignBtn.setText("-");
                }
                else {
                    fullChestSignBtn.setText("+");
                }
            }
        });

        fullShoulderWidthSignBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fullShoulderWidthSignBtn.getText().toString().equals("+")){
                    fullShoulderWidthSignBtn.setText("-");
                }
                else {
                    fullShoulderWidthSignBtn.setText("+");
                }
            }
        });

        waistStomachSignBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(waistStomachSignBtn.getText().toString().equals("+")){
                    waistStomachSignBtn.setText("-");
                }
                else {
                    waistStomachSignBtn.setText("+");
                }
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

        frontChestWidthSignBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(frontChestWidthSignBtn.getText().toString().equals("+")){
                    frontChestWidthSignBtn.setText("-");
                }
                else {
                    frontChestWidthSignBtn.setText("+");
                }
            }
        });

        backWidthSignBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(backWidthSignBtn.getText().toString().equals("+")){
                    backWidthSignBtn.setText("-");
                }
                else {
                    backWidthSignBtn.setText("+");
                }
            }
        });

        halfShoulderWidthLeftSignBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(halfShoulderWidthLeftSignBtn.getText().toString().equals("+")){
                    halfShoulderWidthLeftSignBtn.setText("-");
                }
                else {
                    halfShoulderWidthLeftSignBtn.setText("+");
                }
            }
        });

        halfShoulderWidthRightSignBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(halfShoulderWidthRightSignBtn.getText().toString().equals("+")){
                    halfShoulderWidthRightSignBtn.setText("-");
                }
                else {
                    halfShoulderWidthRightSignBtn.setText("+");
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

        halfBackLengthSignBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(halfBackLengthSignBtn.getText().toString().equals("+")){
                    halfBackLengthSignBtn.setText("-");
                }
                else {
                    halfBackLengthSignBtn.setText("+");
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



        Call<List<GridListItem>> waistCoatData = service.ProcessListItem("12");

        waistCoatData.enqueue(new Callback<List<GridListItem>>() {
            @Override
            public void onResponse(Response<List<GridListItem>> response, Retrofit retrofit) {
                waistCoatList = response.body();
                waistCoatAdapter.addItemList(waistCoatList);
                waistCoat.setAdapter(waistCoatAdapter);

                waistCoat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(getApplicationContext(),waistCoatList.get(position).getItemName(),Toast.LENGTH_LONG).show();
                        waistCoatId = waistCoatList.get(position).getItemId();
                        waistCoatValue = waistCoatList.get(position).getItemName();
                        waistCoatImgValue = waistCoatList.get(position).getItemImageSource();
                        waistCoatPrice = Integer.parseInt(waistCoatList.get(position).getItemPrice());
                        waistCoatAdapter.makeAllUnselect(position);
                        waistCoatAdapter.notifyDataSetChanged();
                        calculateAmount();
                        calculateBill();
                    }
                });
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });




        Call<List<GridListItem>> waistCoatPocketTypeData = service.ProcessListItem("13");

        waistCoatPocketTypeData.enqueue(new Callback<List<GridListItem>>() {
            @Override
            public void onResponse(Response<List<GridListItem>> response, Retrofit retrofit) {
                waistCoatPocketTypeList = response.body();
                waistCoatPocketTypeAdapter.addItemList(waistCoatPocketTypeList);
                waistCoatPocketType.setAdapter(waistCoatPocketTypeAdapter);

                waistCoatPocketType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(getApplicationContext(),waistCoatPocketTypeList.get(position).getItemName(),Toast.LENGTH_LONG).show();
                        waistCoatPocketTypeId = waistCoatPocketTypeList.get(position).getItemId();
                        waistCoatPocketTypeValue = waistCoatPocketTypeList.get(position).getItemName();
                        waistCoatPocketTypeImgValue = waistCoatPocketTypeList.get(position).getItemImageSource();
                        waistCoatPocketTypePrice = Integer.parseInt(waistCoatPocketTypeList.get(position).getItemPrice());
                        waistCoatPocketTypeAdapter.makeAllUnselect(position);
                        waistCoatPocketTypeAdapter.notifyDataSetChanged();
                        calculateAmount();
                        calculateBill();
                    }
                });
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });




        Call<List<GridListItem>> waistCoatBackData = service.ProcessListItem("14");

        waistCoatBackData.enqueue(new Callback<List<GridListItem>>() {
            @Override
            public void onResponse(Response<List<GridListItem>> response, Retrofit retrofit) {
                hidepDialog();
                waistCoatBackList = response.body();
                waistCoatBackAdapter.addItemList(waistCoatBackList);
                waistCoatBack.setAdapter(waistCoatBackAdapter);

                waistCoatBack.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(getApplicationContext(),waistCoatBackList.get(position).getItemName(),Toast.LENGTH_LONG).show();
                        waistCoatBackId = waistCoatBackList.get(position).getItemId();
                        waistCoatBackValue = waistCoatBackList.get(position).getItemName();
                        waistCoatBackImgValue = waistCoatBackList.get(position).getItemImageSource();
                        waistCoatBackPrice = Integer.parseInt(waistCoatBackList.get(position).getItemPrice());
                        waistCoatBackAdapter.makeAllUnselect(position);
                        waistCoatBackAdapter.notifyDataSetChanged();
                        calculateAmount();
                        calculateBill();
                    }
                });
            }

            @Override
            public void onFailure(Throwable t) {
                hidepDialog();
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

                        neckSignBtn.setVisibility(View.GONE);
                        fullChestSignBtn.setVisibility(View.GONE);
                        fullShoulderWidthSignBtn.setVisibility(View.GONE);
                        waistStomachSignBtn.setVisibility(View.GONE);
                        hipSignBtn.setVisibility(View.GONE);
                        frontChestWidthSignBtn.setVisibility(View.GONE);
                        backWidthSignBtn.setVisibility(View.GONE);
                        halfShoulderWidthLeftSignBtn.setVisibility(View.GONE);
                        halfShoulderWidthRightSignBtn.setVisibility(View.GONE);
                        fullBackLengthSignBtn.setVisibility(View.GONE);
                        halfBackLengthSignBtn.setVisibility(View.GONE);

                        neck.setText("");
                        fullChest.setText("");
                        fullShoulderWidth.setText("");
                        waistStomach.setText("");
                        hip.setText("");
                        frontChestWidth.setText("");
                        backWidth.setText("");
                        halfShoulderWidthLeft.setText("");
                        halfShoulderWidthRight.setText("");
                        fullBackLength.setText("");
                        halfBackLength.setText("");

                    }
                    else if(orderTypes.get(position).equals("MTM")){
                        basePatternValue = basePatterns.get(Integer.parseInt(basePattern.getSelectedItem().toString()));
                        baseSizeValue = baseSizes.get(Integer.parseInt(baseSize.getSelectedItem().toString()));

                        basePatternText.setVisibility(View.VISIBLE);
                        basePattern.setVisibility(View.VISIBLE);
                        baseSizeText.setVisibility(View.VISIBLE);
                        baseSize.setVisibility(View.VISIBLE);

                        neckSignBtn.setVisibility(View.VISIBLE);
                        fullChestSignBtn.setVisibility(View.VISIBLE);
                        fullShoulderWidthSignBtn.setVisibility(View.VISIBLE);
                        waistStomachSignBtn.setVisibility(View.VISIBLE);
                        hipSignBtn.setVisibility(View.VISIBLE);
                        frontChestWidthSignBtn.setVisibility(View.VISIBLE);
                        backWidthSignBtn.setVisibility(View.VISIBLE);
                        halfShoulderWidthLeftSignBtn.setVisibility(View.VISIBLE);
                        halfShoulderWidthRightSignBtn.setVisibility(View.VISIBLE);
                        fullBackLengthSignBtn.setVisibility(View.VISIBLE);
                        halfBackLengthSignBtn.setVisibility(View.VISIBLE);

                        neck.setText("0");
                        fullChest.setText("0");
                        fullShoulderWidth.setText("0");
                        waistStomach.setText("0");
                        hip.setText("0");
                        frontChestWidth.setText("0");
                        backWidth.setText("0");
                        halfShoulderWidthLeft.setText("0");
                        halfShoulderWidthRight.setText("0");
                        fullBackLength.setText("0");
                        halfBackLength.setText("0");
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

                        neckSignBtn.setVisibility(View.GONE);
                        fullChestSignBtn.setVisibility(View.GONE);
                        fullShoulderWidthSignBtn.setVisibility(View.GONE);
                        waistStomachSignBtn.setVisibility(View.GONE);
                        hipSignBtn.setVisibility(View.GONE);
                        frontChestWidthSignBtn.setVisibility(View.GONE);
                        backWidthSignBtn.setVisibility(View.GONE);
                        halfShoulderWidthLeftSignBtn.setVisibility(View.GONE);
                        halfShoulderWidthRightSignBtn.setVisibility(View.GONE);
                        fullBackLengthSignBtn.setVisibility(View.GONE);
                        halfBackLengthSignBtn.setVisibility(View.GONE);

                        neck.setText("");
                        fullChest.setText("");
                        fullShoulderWidth.setText("");
                        waistStomach.setText("");
                        hip.setText("");
                        frontChestWidth.setText("");
                        backWidth.setText("");
                        halfShoulderWidthLeft.setText("");
                        halfShoulderWidthRight.setText("");
                        fullBackLength.setText("");
                        halfBackLength.setText("");
                    }
                    else if(orderTypes.get(position).equals("MTM")){
                        basePatternValue = basePatterns.get(Integer.parseInt(basePattern.getSelectedItem().toString()));
                        baseSizeValue = baseSizes.get(Integer.parseInt(baseSize.getSelectedItem().toString()));

                        basePatternText.setVisibility(View.VISIBLE);
                        basePattern.setVisibility(View.VISIBLE);
                        baseSizeText.setVisibility(View.VISIBLE);
                        baseSize.setVisibility(View.VISIBLE);

                        neckSignBtn.setVisibility(View.VISIBLE);
                        fullChestSignBtn.setVisibility(View.VISIBLE);
                        fullShoulderWidthSignBtn.setVisibility(View.VISIBLE);
                        waistStomachSignBtn.setVisibility(View.VISIBLE);
                        hipSignBtn.setVisibility(View.VISIBLE);
                        frontChestWidthSignBtn.setVisibility(View.VISIBLE);
                        backWidthSignBtn.setVisibility(View.VISIBLE);
                        halfShoulderWidthLeftSignBtn.setVisibility(View.VISIBLE);
                        halfShoulderWidthRightSignBtn.setVisibility(View.VISIBLE);
                        fullBackLengthSignBtn.setVisibility(View.VISIBLE);
                        halfBackLengthSignBtn.setVisibility(View.VISIBLE);

                        neck.setText("0");
                        fullChest.setText("0");
                        fullShoulderWidth.setText("0");
                        waistStomach.setText("0");
                        hip.setText("0");
                        frontChestWidth.setText("0");
                        backWidth.setText("0");
                        halfShoulderWidthLeft.setText("0");
                        halfShoulderWidthRight.setText("0");
                        fullBackLength.setText("0");
                        halfBackLength.setText("0");
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

                            neckSignBtn.setVisibility(View.GONE);
                            fullChestSignBtn.setVisibility(View.GONE);
                            fullShoulderWidthSignBtn.setVisibility(View.GONE);
                            waistStomachSignBtn.setVisibility(View.GONE);
                            hipSignBtn.setVisibility(View.GONE);
                            frontChestWidthSignBtn.setVisibility(View.GONE);
                            backWidthSignBtn.setVisibility(View.GONE);
                            halfShoulderWidthLeftSignBtn.setVisibility(View.GONE);
                            halfShoulderWidthRightSignBtn.setVisibility(View.GONE);
                            fullBackLengthSignBtn.setVisibility(View.GONE);
                            halfBackLengthSignBtn.setVisibility(View.GONE);

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

                            neckSignBtn.setVisibility(View.VISIBLE);
                            fullChestSignBtn.setVisibility(View.VISIBLE);
                            fullShoulderWidthSignBtn.setVisibility(View.VISIBLE);
                            waistStomachSignBtn.setVisibility(View.VISIBLE);
                            hipSignBtn.setVisibility(View.VISIBLE);
                            frontChestWidthSignBtn.setVisibility(View.VISIBLE);
                            backWidthSignBtn.setVisibility(View.VISIBLE);
                            halfShoulderWidthLeftSignBtn.setVisibility(View.VISIBLE);
                            halfShoulderWidthRightSignBtn.setVisibility(View.VISIBLE);
                            fullBackLengthSignBtn.setVisibility(View.VISIBLE);
                            halfBackLengthSignBtn.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                customerId = getIntent().getExtras().getString("customerId");
                orderId = getIntent().getExtras().getString("orderId");
                orderTypeValue = getIntent().getExtras().getString("orderType");

                showpDialog();

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

                            shoulderSlopeId = response.body().getShoulderId();
                            shoulderSlopeValue = setValue(shoulderSlopeList, makeSelection(shoulderSlopeList, shoulderSlopeId, shoulderSlopeAdapter));
                            shoulderSlopeImgValue = setImgValue(shoulderSlopeList, makeSelection(shoulderSlopeList, shoulderSlopeId, shoulderSlopeAdapter));

                            chestDescriptionId = response.body().getChestId();
                            chestDescriptionValue = setValue(chestDescriptionList, makeSelection(chestDescriptionList, chestDescriptionId, chestDescriptionAdapter));
                            chestDescriptionImgValue = setImgValue(chestDescriptionList, makeSelection(chestDescriptionList, chestDescriptionId, chestDescriptionAdapter));

                            stomachDescriptionId = response.body().getStomachId();
                            stomachDescriptionValue = setValue(stomachDescriptionList, makeSelection(stomachDescriptionList, stomachDescriptionId, stomachDescriptionAdapter));
                            stomachDescriptionImgValue = setImgValue(stomachDescriptionList, makeSelection(stomachDescriptionList, stomachDescriptionId, stomachDescriptionAdapter));

                            hipDescriptionId = response.body().getHipId();
                            hipDescriptionValue = setValue(hipDescriptionList, makeSelection(hipDescriptionList, hipDescriptionId, hipDescriptionAdapter));
                            hipDescriptionImgValue = setImgValue(hipDescriptionList, makeSelection(hipDescriptionList, hipDescriptionId, hipDescriptionAdapter));


                            if (orderTypeValue.equals("MTM")) {

                                neckSignBtn.setText(response.body().getNeck().substring(0, 1));
                                neck.setText(response.body().getNeck().substring(1));

                                fullChestSignBtn.setText(response.body().getFullChest().substring(0, 1));
                                fullChest.setText(response.body().getFullChest().substring(1));

                                fullShoulderWidthSignBtn.setText(response.body().getShoulderWidth().substring(0, 1));
                                fullShoulderWidth.setText(response.body().getShoulderWidth().substring(1));

                                waistStomachSignBtn.setText(response.body().getWaistStomach().substring(0, 1));
                                waistStomach.setText(response.body().getWaistStomach().substring(1));

                                hipSignBtn.setText(response.body().getHipMeasurement().substring(0, 1));
                                hip.setText(response.body().getHipMeasurement().substring(1));

                                frontChestWidthSignBtn.setText(response.body().getFrontChestWidth().substring(0, 1));
                                frontChestWidth.setText(response.body().getFrontChestWidth().substring(1));

                                backWidthSignBtn.setText(response.body().getBackWidth().substring(0, 1));
                                backWidth.setText(response.body().getBackWidth().substring(1));

                                halfShoulderWidthLeftSignBtn.setText(response.body().getHalfShoulderWidthLeft().substring(0, 1));
                                halfShoulderWidthLeft.setText(response.body().getHalfShoulderWidthLeft().substring(1));

                                halfShoulderWidthRightSignBtn.setText(response.body().getHalfShoulderWidthRight().substring(0, 1));
                                halfShoulderWidthRight.setText(response.body().getHalfShoulderWidthRight().substring(1));

                                fullBackLengthSignBtn.setText(response.body().getFullBackLength().substring(0, 1));
                                fullBackLength.setText(response.body().getFullBackLength().substring(1));

                                halfBackLengthSignBtn.setText(response.body().getHalfBackLength().substring(0, 1));
                                halfBackLength.setText(response.body().getHalfBackLength().substring(1));

                            } else {

                                neck.setText(response.body().getNeck());

                                fullChest.setText(response.body().getFullChest());

                                fullShoulderWidth.setText(response.body().getShoulderWidth());

                                waistStomach.setText(response.body().getWaistStomach());

                                hip.setText(response.body().getHipMeasurement());

                                frontChestWidth.setText(response.body().getFrontChestWidth());

                                backWidth.setText(response.body().getBackWidth());

                                halfShoulderWidthLeft.setText(response.body().getHalfShoulderWidthLeft());

                                halfShoulderWidthRight.setText(response.body().getHalfShoulderWidthRight());

                                fullBackLength.setText(response.body().getFullBackLength());

                                halfBackLength.setText(response.body().getHalfBackLength());

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
                            previousLiningId = response.body().getLiningId();

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
                                    fabricPrice = Integer.parseInt(fabricList.get(getFabricLiningIndex(fabricList,fabricIdValue)).getItemBsWaistCoatPrice());
                                }
                                else if(orderTypeValue.equals("MTM")) {
                                    fabricPrice = Integer.parseInt(fabricList.get(getFabricLiningIndex(fabricList,fabricIdValue)).getItemMtmWaistCoatPrice());
                                }

                                calculateAmount();
                                calculateBill();
                            }

                            liningIdValue = response.body().getLiningId();
                            makeLiningSelection(liningList, liningIdValue, liningNumberValue, liningNumberAdapter, liningNumberData);
                            liningPrice = Integer.parseInt(liningList.get(getLiningIndex(liningList,liningIdValue)).getItemWaistCoatPrice());

                            calculateAmount();
                            calculateBill();

                            waistCoatId = response.body().getWaistCoatTypeId();
                            waistCoatValue = setValue(waistCoatList, makeSelection(waistCoatList, waistCoatId, waistCoatAdapter));
                            waistCoatImgValue = setImgValue(waistCoatList, makeSelection(waistCoatList, waistCoatId, waistCoatAdapter));
                            waistCoatPrice = setPriceValue(waistCoatList, makeSelection(waistCoatList, waistCoatId, waistCoatAdapter));


                            waistCoatPocketTypeId = response.body().getWaistCoatPocketTypeId();
                            waistCoatPocketTypeValue = setValue(waistCoatPocketTypeList, makeSelection(waistCoatPocketTypeList, waistCoatPocketTypeId, waistCoatPocketTypeAdapter));
                            waistCoatPocketTypeImgValue = setImgValue(waistCoatPocketTypeList, makeSelection(waistCoatPocketTypeList, waistCoatPocketTypeId, waistCoatPocketTypeAdapter));
                            waistCoatPocketTypePrice = setPriceValue(waistCoatPocketTypeList, makeSelection(waistCoatPocketTypeList, waistCoatPocketTypeId, waistCoatPocketTypeAdapter));


                            waistCoatBackId = response.body().getBackId();
                            waistCoatBackValue = setValue(waistCoatBackList, makeSelection(waistCoatBackList, waistCoatBackId, waistCoatBackAdapter));
                            waistCoatBackImgValue = setImgValue(waistCoatBackList, makeSelection(waistCoatBackList, waistCoatBackId, waistCoatBackAdapter));
                            waistCoatBackPrice = setPriceValue(waistCoatBackList, makeSelection(waistCoatBackList, waistCoatBackId, waistCoatBackAdapter));


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

                AlertDialog.Builder builder = new AlertDialog.Builder(OrderTakingWaistCoat.this);
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

                                neckValue = neck.getText().toString();
                                fullChestValue = fullChest.getText().toString();
                                fullShoulderWidthValue = fullShoulderWidth.getText().toString();
                                waistStomachValue = waistStomach.getText().toString();
                                hipValue = hip.getText().toString();
                                frontChestWidthValue = frontChestWidth.getText().toString();
                                backWidthValue = backWidth.getText().toString();
                                halfShoulderWidthLeftValue = halfShoulderWidthLeft.getText().toString();
                                halfShoulderWidthRightValue = halfShoulderWidthRight.getText().toString();
                                fullBackLengthValue = fullBackLength.getText().toString();
                                halfBackLengthValue = halfBackLength.getText().toString();

                                fabricNumberValue = fabricNumberData.getText().toString();
                                liningNumberValue = liningNumberData.getText().toString();

                                if(!fabricNumberValue.equals("") && !liningNumberValue.equals("") && waistCoatValue != null && waistCoatPocketTypeValue != null && waistCoatBackValue != null && waistCoatImgValue != null && waistCoatPocketTypeImgValue != null && waistCoatBackImgValue != null){

                                    if(orderTypeValue.equals("Bespoke")) {
                                        if (stanceValue != null && stanceImgValue != null && shoulderSlopeValue != null && shoulderSlopeImgValue != null && chestDescriptionValue != null && chestDescriptionImgValue != null && stomachDescriptionValue != null && stomachDescriptionImgValue != null && hipDescriptionValue != null && hipDescriptionImgValue != null && !neckValue.equals("") && !fullChestValue.equals("") && !fullShoulderWidthValue.equals("") && !waistStomachValue.equals("") && !hipValue.equals("") && !frontChestWidthValue.equals("") && !backWidthValue.equals("") && !halfShoulderWidthLeftValue.equals("") && !halfShoulderWidthRightValue.equals("") && !fullBackLengthValue.equals("") && !halfBackLengthValue.equals("")) {

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

                                                        Call<String> liningConsumptionData = service.ProcessLiningConsumption(liningConsumption,liningIdValue);

                                                        liningConsumptionData.enqueue(new Callback<String>() {
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


                                                                                Call<String> orderData = service.InsertOrder(orderId,customerId,shopId,itemType,"Pending Review",billValue,expectedDateValue,orderTypeValue,basePatternValue,baseSizeValue,fabricIdValue,liningIdValue);

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

                                                                                            Call<String> measurementData = service.InsertMeasurement(orderId,customerId,stanceId,stanceValue,stanceImgValue,shoulderSlopeId,
                                                                                                    shoulderSlopeValue,shoulderSlopeImgValue,chestDescriptionId,chestDescriptionValue,chestDescriptionImgValue,stomachDescriptionId,
                                                                                                    stomachDescriptionValue,stomachDescriptionImgValue,hipDescriptionId,hipDescriptionValue,hipDescriptionImgValue,neckValue,fullChestValue,
                                                                                                    fullShoulderWidthValue,"","","","",waistStomachValue,hipValue,"",
                                                                                                    frontChestWidthValue,backWidthValue,halfShoulderWidthLeftValue,halfShoulderWidthRightValue,fullBackLengthValue,halfBackLengthValue,
                                                                                                    "","","","","","","","");


                                                                                            measurementData.enqueue(new Callback<String>() {
                                                                                                @Override
                                                                                                public void onResponse(Response<String> response, Retrofit retrofit) {


                                                                                                    Call<String> stylingData = service.InsertStyling(orderId,customerId,fabricIdValue,fabricNumberValue,liningIdValue,liningNumberValue,"","","",
                                                                                                            "","","","","","",
                                                                                                            "","","","","","","","","",
                                                                                                            "","","","","","",
                                                                                                            "","","","","","","","","",
                                                                                                            "","","","","","","",
                                                                                                            "","","","","","","",
                                                                                                            "","","","","","","",
                                                                                                            waistCoatId,waistCoatValue,waistCoatImgValue,waistCoatPocketTypeId,waistCoatPocketTypeValue,waistCoatPocketTypeImgValue,waistCoatBackId,
                                                                                                            waistCoatBackValue,waistCoatBackImgValue,"","","","","",
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
                                                                    Toast.makeText(getApplicationContext(),"Lining not available",Toast.LENGTH_SHORT).show();
                                                                }
                                                            }

                                                            @Override
                                                            public void onFailure(Throwable t) {
                                                                Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
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

                                        if (stanceValue != null && stanceImgValue != null && shoulderSlopeValue != null && shoulderSlopeImgValue != null && chestDescriptionValue != null && chestDescriptionImgValue != null && stomachDescriptionValue != null && stomachDescriptionImgValue != null && hipDescriptionValue != null && hipDescriptionImgValue != null){


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

                                                        Call<String> liningConsumptionData = service.ProcessLiningConsumption(liningConsumption,liningIdValue);

                                                        liningConsumptionData.enqueue(new Callback<String>() {
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


                                                                                Call<String> orderData = service.InsertOrder(orderId,customerId,shopId,itemType,"Pending Review",billValue,expectedDateValue,orderTypeValue,basePatternValue,baseSizeValue,fabricIdValue,liningIdValue);

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

                                                                                            Call<String> measurementData = service.InsertMeasurement(orderId,customerId,stanceId,stanceValue,stanceImgValue,shoulderSlopeId,
                                                                                                    shoulderSlopeValue,shoulderSlopeImgValue,chestDescriptionId,chestDescriptionValue,chestDescriptionImgValue,stomachDescriptionId,
                                                                                                    stomachDescriptionValue,stomachDescriptionImgValue,hipDescriptionId,hipDescriptionValue,hipDescriptionImgValue,neckSignBtn.getText().toString() + neckValue,fullChestSignBtn.getText().toString() + fullChestValue,
                                                                                                    fullShoulderWidthSignBtn.getText().toString() + fullShoulderWidthValue,"","","","",waistStomachSignBtn.getText().toString() + waistStomachValue,hipSignBtn.getText().toString() + hipValue,"",
                                                                                                    frontChestWidthSignBtn.getText().toString() + frontChestWidthValue,backWidthSignBtn.getText().toString() + backWidthValue,halfShoulderWidthLeftSignBtn.getText().toString() + halfShoulderWidthLeftValue,halfShoulderWidthRightSignBtn.getText().toString() + halfShoulderWidthRightValue,fullBackLengthSignBtn.getText().toString() + fullBackLengthValue,halfBackLengthSignBtn.getText().toString() + halfBackLengthValue,
                                                                                                    "","","","","","","","");


                                                                                            measurementData.enqueue(new Callback<String>() {
                                                                                                @Override
                                                                                                public void onResponse(Response<String> response, Retrofit retrofit) {


                                                                                                    Call<String> stylingData = service.InsertStyling(orderId,customerId,fabricIdValue,fabricNumberValue,liningIdValue,liningNumberValue,"","","",
                                                                                                            "","","","","","",
                                                                                                            "","","","","","","","","",
                                                                                                            "","","","","","",
                                                                                                            "","","","","","","","","",
                                                                                                            "","","","","","","",
                                                                                                            "","","","","","","",
                                                                                                            "","","","","","","",
                                                                                                            waistCoatId,waistCoatValue,waistCoatImgValue,waistCoatPocketTypeId,waistCoatPocketTypeValue,waistCoatPocketTypeImgValue,waistCoatBackId,
                                                                                                            waistCoatBackValue,waistCoatBackImgValue,"","","","","",
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
                                                                    Toast.makeText(getApplicationContext(),"Lining not available",Toast.LENGTH_SHORT).show();
                                                                }
                                                            }

                                                            @Override
                                                            public void onFailure(Throwable t) {
                                                                Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
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

                                neckValue = neck.getText().toString();
                                fullChestValue = fullChest.getText().toString();
                                fullShoulderWidthValue = fullShoulderWidth.getText().toString();
                                waistStomachValue = waistStomach.getText().toString();
                                hipValue = hip.getText().toString();
                                frontChestWidthValue = frontChestWidth.getText().toString();
                                backWidthValue = backWidth.getText().toString();
                                halfShoulderWidthLeftValue = halfShoulderWidthLeft.getText().toString();
                                halfShoulderWidthRightValue = halfShoulderWidthRight.getText().toString();
                                fullBackLengthValue = fullBackLength.getText().toString();
                                halfBackLengthValue = halfBackLength.getText().toString();

                                fabricNumberValue = fabricNumberData.getText().toString();
                                liningNumberValue = liningNumberData.getText().toString();

                                if(!fabricNumberValue.equals("") && !liningNumberValue.equals("") && waistCoatValue != null && waistCoatPocketTypeValue != null && waistCoatBackValue != null && waistCoatImgValue != null && waistCoatPocketTypeImgValue != null && waistCoatBackImgValue != null){

                                    if(orderTypeValue.equals("Bespoke")) {
                                        if (stanceValue != null && stanceImgValue != null && shoulderSlopeValue != null && shoulderSlopeImgValue != null && chestDescriptionValue != null && chestDescriptionImgValue != null && stomachDescriptionValue != null && stomachDescriptionImgValue != null && hipDescriptionValue != null && hipDescriptionImgValue != null && !neckValue.equals("") && !fullChestValue.equals("") && !fullShoulderWidthValue.equals("") && !waistStomachValue.equals("") && !hipValue.equals("") && !frontChestWidthValue.equals("") && !backWidthValue.equals("") && !halfShoulderWidthLeftValue.equals("") && !halfShoulderWidthRightValue.equals("") && !fullBackLengthValue.equals("") && !halfBackLengthValue.equals("")) {

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

                                                        Call<String> liningConsumptionData = service.ProcessLiningConsumption(liningConsumption,liningIdValue);

                                                        liningConsumptionData.enqueue(new Callback<String>() {
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


                                                                                Call<String> orderData = service.InsertOrder(orderId,customerId,shopId,itemType,"Pending Review",billValue,expectedDateValue,orderTypeValue,basePatternValue,baseSizeValue,fabricIdValue,liningIdValue);

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

                                                                                            Call<String> measurementData = service.InsertMeasurement(orderId,customerId,stanceId,stanceValue,stanceImgValue,shoulderSlopeId,
                                                                                                    shoulderSlopeValue,shoulderSlopeImgValue,chestDescriptionId,chestDescriptionValue,chestDescriptionImgValue,stomachDescriptionId,
                                                                                                    stomachDescriptionValue,stomachDescriptionImgValue,hipDescriptionId,hipDescriptionValue,hipDescriptionImgValue,neckValue,fullChestValue,
                                                                                                    fullShoulderWidthValue,"","","","",waistStomachValue,hipValue,"",
                                                                                                    frontChestWidthValue,backWidthValue,halfShoulderWidthLeftValue,halfShoulderWidthRightValue,fullBackLengthValue,halfBackLengthValue,
                                                                                                    "","","","","","","","");


                                                                                            measurementData.enqueue(new Callback<String>() {
                                                                                                @Override
                                                                                                public void onResponse(Response<String> response, Retrofit retrofit) {


                                                                                                    Call<String> stylingData = service.InsertStyling(orderId,customerId,fabricIdValue,fabricNumberValue,liningIdValue,liningNumberValue,"","","",
                                                                                                            "","","","","","",
                                                                                                            "","","","","","","","","",
                                                                                                            "","","","","","",
                                                                                                            "","","","","","","","","",
                                                                                                            "","","","","","","",
                                                                                                            "","","","","","","",
                                                                                                            "","","","","","","",
                                                                                                            waistCoatId,waistCoatValue,waistCoatImgValue,waistCoatPocketTypeId,waistCoatPocketTypeValue,waistCoatPocketTypeImgValue,waistCoatBackId,
                                                                                                            waistCoatBackValue,waistCoatBackImgValue,"","","","","",
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
                                                                    Toast.makeText(getApplicationContext(),"Lining not available",Toast.LENGTH_SHORT).show();
                                                                }
                                                            }

                                                            @Override
                                                            public void onFailure(Throwable t) {
                                                                Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
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

                                        if (stanceValue != null && stanceImgValue != null && shoulderSlopeValue != null && shoulderSlopeImgValue != null && chestDescriptionValue != null && chestDescriptionImgValue != null && stomachDescriptionValue != null && stomachDescriptionImgValue != null && hipDescriptionValue != null && hipDescriptionImgValue != null){

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

                                                        Call<String> liningConsumptionData = service.ProcessLiningConsumption(liningConsumption,liningIdValue);

                                                        liningConsumptionData.enqueue(new Callback<String>() {
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


                                                                                Call<String> orderData = service.InsertOrder(orderId,customerId,shopId,itemType,"Pending Review",billValue,expectedDateValue,orderTypeValue,basePatternValue,baseSizeValue,fabricIdValue,liningIdValue);

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

                                                                                            Call<String> measurementData = service.InsertMeasurement(orderId,customerId,stanceId,stanceValue,stanceImgValue,shoulderSlopeId,
                                                                                                    shoulderSlopeValue,shoulderSlopeImgValue,chestDescriptionId,chestDescriptionValue,chestDescriptionImgValue,stomachDescriptionId,
                                                                                                    stomachDescriptionValue,stomachDescriptionImgValue,hipDescriptionId,hipDescriptionValue,hipDescriptionImgValue,neckSignBtn.getText().toString() + neckValue,fullChestSignBtn.getText().toString() + fullChestValue,
                                                                                                    fullShoulderWidthSignBtn.getText().toString() + fullShoulderWidthValue,"","","","",waistStomachSignBtn.getText().toString() + waistStomachValue,hipSignBtn.getText().toString() + hipValue,"",
                                                                                                    frontChestWidthSignBtn.getText().toString() + frontChestWidthValue,backWidthSignBtn.getText().toString() + backWidthValue,halfShoulderWidthLeftSignBtn.getText().toString() + halfShoulderWidthLeftValue,halfShoulderWidthRightSignBtn.getText().toString() + halfShoulderWidthRightValue,fullBackLengthSignBtn.getText().toString() + fullBackLengthValue,halfBackLengthSignBtn.getText().toString() + halfBackLengthValue,
                                                                                                    "","","","","","","","");


                                                                                            measurementData.enqueue(new Callback<String>() {
                                                                                                @Override
                                                                                                public void onResponse(Response<String> response, Retrofit retrofit) {


                                                                                                    Call<String> stylingData = service.InsertStyling(orderId,customerId,fabricIdValue,fabricNumberValue,liningIdValue,liningNumberValue,"","","",
                                                                                                            "","","","","","",
                                                                                                            "","","","","","","","","",
                                                                                                            "","","","","","",
                                                                                                            "","","","","","","","","",
                                                                                                            "","","","","","","",
                                                                                                            "","","","","","","",
                                                                                                            "","","","","","","",
                                                                                                            waistCoatId,waistCoatValue,waistCoatImgValue,waistCoatPocketTypeId,waistCoatPocketTypeValue,waistCoatPocketTypeImgValue,waistCoatBackId,
                                                                                                            waistCoatBackValue,waistCoatBackImgValue,"","","","","",
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
                                                                    Toast.makeText(getApplicationContext(),"Lining not available",Toast.LENGTH_SHORT).show();
                                                                }
                                                            }

                                                            @Override
                                                            public void onFailure(Throwable t) {
                                                                Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
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

                                neckValue = neck.getText().toString();
                                fullChestValue = fullChest.getText().toString();
                                fullShoulderWidthValue = fullShoulderWidth.getText().toString();
                                waistStomachValue = waistStomach.getText().toString();
                                hipValue = hip.getText().toString();
                                frontChestWidthValue = frontChestWidth.getText().toString();
                                backWidthValue = backWidth.getText().toString();
                                halfShoulderWidthLeftValue = halfShoulderWidthLeft.getText().toString();
                                halfShoulderWidthRightValue = halfShoulderWidthRight.getText().toString();
                                fullBackLengthValue = fullBackLength.getText().toString();
                                halfBackLengthValue = halfBackLength.getText().toString();

                                fabricNumberValue = fabricNumberData.getText().toString();
                                liningNumberValue = liningNumberData.getText().toString();

                                if(!fabricNumberValue.equals("") && !liningNumberValue.equals("") && waistCoatValue != null && waistCoatPocketTypeValue != null && waistCoatBackValue != null && waistCoatImgValue != null && waistCoatPocketTypeImgValue != null && waistCoatBackImgValue != null){

                                    if(orderTypeValue.equals("Bespoke")) {
                                        if (stanceValue != null && stanceImgValue != null && shoulderSlopeValue != null && shoulderSlopeImgValue != null && chestDescriptionValue != null && chestDescriptionImgValue != null && stomachDescriptionValue != null && stomachDescriptionImgValue != null && hipDescriptionValue != null && hipDescriptionImgValue != null && !neckValue.equals("") && !fullChestValue.equals("") && !fullShoulderWidthValue.equals("") && !waistStomachValue.equals("") && !hipValue.equals("") && !frontChestWidthValue.equals("") && !backWidthValue.equals("") && !halfShoulderWidthLeftValue.equals("") && !halfShoulderWidthRightValue.equals("") && !fullBackLengthValue.equals("") && !halfBackLengthValue.equals("")) {

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

                                                if(!previousLiningId.equals(liningIdValue)){

                                                    Call<String> fabricConsumptionData = service.ProcessFabricConsumption(fabricConnsumption,fabricIdValue);

                                                    fabricConsumptionData.enqueue(new Callback<String>() {
                                                        @Override
                                                        public void onResponse(Response<String> response, Retrofit retrofit) {
                                                            if(response.body().equals("Yes")){

                                                                Call<String> liningConsumptionData = service.ProcessLiningConsumption(liningConsumption,liningIdValue);

                                                                liningConsumptionData.enqueue(new Callback<String>() {
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

                                                                            Call<String> liningAdditionData = service.ProcessLiningAddition(liningConsumption,previousLiningId);

                                                                            liningAdditionData.enqueue(new Callback<String>() {
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


                                                                                        Call<String> existingOrderData = service.UpdateOrder(orderId,shopId,itemType,"Pending Review",billValue,expectedDateValue,orderTypeValue,basePatternValue,baseSizeValue,fabricIdValue,liningIdValue);

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

                                                                                                    Call<String> updateMeasurementData = service.UpdateMeasurement(measurementId,stanceId,stanceValue,stanceImgValue,shoulderSlopeId,
                                                                                                            shoulderSlopeValue,shoulderSlopeImgValue,chestDescriptionId,chestDescriptionValue,chestDescriptionImgValue,stomachDescriptionId,
                                                                                                            stomachDescriptionValue,stomachDescriptionImgValue,hipDescriptionId,hipDescriptionValue,hipDescriptionImgValue,neckValue,fullChestValue,
                                                                                                            fullShoulderWidthValue,"","","","",waistStomachValue,hipValue,"",
                                                                                                            frontChestWidthValue,backWidthValue,halfShoulderWidthLeftValue,halfShoulderWidthRightValue,fullBackLengthValue,halfBackLengthValue,
                                                                                                            "","","","","","","","");


                                                                                                    updateMeasurementData.enqueue(new Callback<String>() {
                                                                                                        @Override
                                                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {

                                                                                                            if(response.body().equals("Successful")){


                                                                                                                Call<String> updateStylingData = service.UpdateStyling(stylingId,fabricIdValue,fabricNumberValue,liningIdValue,liningNumberValue,"","","",
                                                                                                                        "","","","","","",
                                                                                                                        "","","","","","","","","",
                                                                                                                        "","","","","","",
                                                                                                                        "","","","","","","","","",
                                                                                                                        "","","","","","","",
                                                                                                                        "","","","","","","",
                                                                                                                        "","","","","","","",
                                                                                                                        waistCoatId,waistCoatValue,waistCoatImgValue,waistCoatPocketTypeId,waistCoatPocketTypeValue,waistCoatPocketTypeImgValue,waistCoatBackId,
                                                                                                                        waistCoatBackValue,waistCoatBackImgValue,"","","","","",
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
                                                                            Toast.makeText(getApplicationContext(),"Lining not available",Toast.LENGTH_SHORT).show();
                                                                        }
                                                                    }

                                                                    @Override
                                                                    public void onFailure(Throwable t) {
                                                                        Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
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


                                                                            Call<String> existingOrderData = service.UpdateOrder(orderId,shopId,itemType,"Pending Review",billValue,expectedDateValue,orderTypeValue,basePatternValue,baseSizeValue,fabricIdValue,liningIdValue);

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

                                                                                        Call<String> updateMeasurementData = service.UpdateMeasurement(measurementId,stanceId,stanceValue,stanceImgValue,shoulderSlopeId,
                                                                                                shoulderSlopeValue,shoulderSlopeImgValue,chestDescriptionId,chestDescriptionValue,chestDescriptionImgValue,stomachDescriptionId,
                                                                                                stomachDescriptionValue,stomachDescriptionImgValue,hipDescriptionId,hipDescriptionValue,hipDescriptionImgValue,neckValue,fullChestValue,
                                                                                                fullShoulderWidthValue,"","","","",waistStomachValue,hipValue,"",
                                                                                                frontChestWidthValue,backWidthValue,halfShoulderWidthLeftValue,halfShoulderWidthRightValue,fullBackLengthValue,halfBackLengthValue,
                                                                                                "","","","","","","","");


                                                                                        updateMeasurementData.enqueue(new Callback<String>() {
                                                                                            @Override
                                                                                            public void onResponse(Response<String> response, Retrofit retrofit) {

                                                                                                if(response.body().equals("Successful")){


                                                                                                    Call<String> updateStylingData = service.UpdateStyling(stylingId,fabricIdValue,fabricNumberValue,liningIdValue,liningNumberValue,"","","",
                                                                                                            "","","","","","",
                                                                                                            "","","","","","","","","",
                                                                                                            "","","","","","",
                                                                                                            "","","","","","","","","",
                                                                                                            "","","","","","","",
                                                                                                            "","","","","","","",
                                                                                                            "","","","","","","",
                                                                                                            waistCoatId,waistCoatValue,waistCoatImgValue,waistCoatPocketTypeId,waistCoatPocketTypeValue,waistCoatPocketTypeImgValue,waistCoatBackId,
                                                                                                            waistCoatBackValue,waistCoatBackImgValue,"","","","","",
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
                                            }
                                            else{
                                                if(!previousLiningId.equals(liningIdValue)){

                                                    Call<String> liningConsumptionData = service.ProcessLiningConsumption(liningConsumption,liningIdValue);

                                                    liningConsumptionData.enqueue(new Callback<String>() {
                                                        @Override
                                                        public void onResponse(Response<String> response, Retrofit retrofit) {
                                                            if(response.body().equals("Yes")){

                                                                Call<String> liningAdditionData = service.ProcessLiningAddition(liningConsumption,previousLiningId);

                                                                liningAdditionData.enqueue(new Callback<String>() {
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


                                                                            Call<String> existingOrderData = service.UpdateOrder(orderId,shopId,itemType,"Pending Review",billValue,expectedDateValue,orderTypeValue,basePatternValue,baseSizeValue,fabricIdValue,liningIdValue);

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

                                                                                        Call<String> updateMeasurementData = service.UpdateMeasurement(measurementId,stanceId,stanceValue,stanceImgValue,shoulderSlopeId,
                                                                                                shoulderSlopeValue,shoulderSlopeImgValue,chestDescriptionId,chestDescriptionValue,chestDescriptionImgValue,stomachDescriptionId,
                                                                                                stomachDescriptionValue,stomachDescriptionImgValue,hipDescriptionId,hipDescriptionValue,hipDescriptionImgValue,neckValue,fullChestValue,
                                                                                                fullShoulderWidthValue,"","","","",waistStomachValue,hipValue,"",
                                                                                                frontChestWidthValue,backWidthValue,halfShoulderWidthLeftValue,halfShoulderWidthRightValue,fullBackLengthValue,halfBackLengthValue,
                                                                                                "","","","","","","","");


                                                                                        updateMeasurementData.enqueue(new Callback<String>() {
                                                                                            @Override
                                                                                            public void onResponse(Response<String> response, Retrofit retrofit) {

                                                                                                if(response.body().equals("Successful")){


                                                                                                    Call<String> updateStylingData = service.UpdateStyling(stylingId,fabricIdValue,fabricNumberValue,liningIdValue,liningNumberValue,"","","",
                                                                                                            "","","","","","",
                                                                                                            "","","","","","","","","",
                                                                                                            "","","","","","",
                                                                                                            "","","","","","","","","",
                                                                                                            "","","","","","","",
                                                                                                            "","","","","","","",
                                                                                                            "","","","","","","",
                                                                                                            waistCoatId,waistCoatValue,waistCoatImgValue,waistCoatPocketTypeId,waistCoatPocketTypeValue,waistCoatPocketTypeImgValue,waistCoatBackId,
                                                                                                            waistCoatBackValue,waistCoatBackImgValue,"","","","","",
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
                                                                Toast.makeText(getApplicationContext(),"Lining not available",Toast.LENGTH_SHORT).show();
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


                                                                Call<String> existingOrderData = service.UpdateOrder(orderId,shopId,itemType,"Pending Review",billValue,expectedDateValue,orderTypeValue,basePatternValue,baseSizeValue,fabricIdValue,liningIdValue);

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

                                                                            Call<String> updateMeasurementData = service.UpdateMeasurement(measurementId,stanceId,stanceValue,stanceImgValue,shoulderSlopeId,
                                                                                    shoulderSlopeValue,shoulderSlopeImgValue,chestDescriptionId,chestDescriptionValue,chestDescriptionImgValue,stomachDescriptionId,
                                                                                    stomachDescriptionValue,stomachDescriptionImgValue,hipDescriptionId,hipDescriptionValue,hipDescriptionImgValue,neckValue,fullChestValue,
                                                                                    fullShoulderWidthValue,"","","","",waistStomachValue,hipValue,"",
                                                                                    frontChestWidthValue,backWidthValue,halfShoulderWidthLeftValue,halfShoulderWidthRightValue,fullBackLengthValue,halfBackLengthValue,
                                                                                    "","","","","","","","");


                                                                            updateMeasurementData.enqueue(new Callback<String>() {
                                                                                @Override
                                                                                public void onResponse(Response<String> response, Retrofit retrofit) {

                                                                                    if(response.body().equals("Successful")){


                                                                                        Call<String> updateStylingData = service.UpdateStyling(stylingId,fabricIdValue,fabricNumberValue,liningIdValue,liningNumberValue,"","","",
                                                                                                "","","","","","",
                                                                                                "","","","","","","","","",
                                                                                                "","","","","","",
                                                                                                "","","","","","","","","",
                                                                                                "","","","","","","",
                                                                                                "","","","","","","",
                                                                                                "","","","","","","",
                                                                                                waistCoatId,waistCoatValue,waistCoatImgValue,waistCoatPocketTypeId,waistCoatPocketTypeValue,waistCoatPocketTypeImgValue,waistCoatBackId,
                                                                                                waistCoatBackValue,waistCoatBackImgValue,"","","","","",
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


                                        }
                                        else {
                                            Toast.makeText(getApplicationContext(), "Please complete Measurement Section", Toast.LENGTH_LONG).show();
                                        }


                                    }

                                    else{

                                        if (stanceValue != null && stanceImgValue != null && shoulderSlopeValue != null && shoulderSlopeImgValue != null && chestDescriptionValue != null && chestDescriptionImgValue != null && stomachDescriptionValue != null && stomachDescriptionImgValue != null && hipDescriptionValue != null && hipDescriptionImgValue != null){

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

                                                if(!previousLiningId.equals(liningIdValue)){

                                                    Call<String> fabricConsumptionData = service.ProcessFabricConsumption(fabricConnsumption,fabricIdValue);

                                                    fabricConsumptionData.enqueue(new Callback<String>() {
                                                        @Override
                                                        public void onResponse(Response<String> response, Retrofit retrofit) {
                                                            if(response.body().equals("Yes")){

                                                                Call<String> liningConsumptionData = service.ProcessLiningConsumption(liningConsumption,liningIdValue);

                                                                liningConsumptionData.enqueue(new Callback<String>() {
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

                                                                            Call<String> liningAdditionData = service.ProcessLiningAddition(liningConsumption,previousLiningId);

                                                                            liningAdditionData.enqueue(new Callback<String>() {
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


                                                                                        Call<String> existingOrderData = service.UpdateOrder(orderId,shopId,itemType,"Pending Review",billValue,expectedDateValue,orderTypeValue,basePatternValue,baseSizeValue,fabricIdValue,liningIdValue);

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

                                                                                                    Call<String> updateMeasurementData = service.UpdateMeasurement(measurementId,stanceId,stanceValue,stanceImgValue,shoulderSlopeId,
                                                                                                            shoulderSlopeValue,shoulderSlopeImgValue,chestDescriptionId,chestDescriptionValue,chestDescriptionImgValue,stomachDescriptionId,
                                                                                                            stomachDescriptionValue,stomachDescriptionImgValue,hipDescriptionId,hipDescriptionValue,hipDescriptionImgValue,neckSignBtn.getText().toString() + neckValue,fullChestSignBtn.getText().toString() + fullChestValue,
                                                                                                            fullShoulderWidthSignBtn.getText().toString() + fullShoulderWidthValue,"","","","",waistStomachSignBtn.getText().toString() + waistStomachValue,hipSignBtn.getText().toString() + hipValue,"",
                                                                                                            frontChestWidthSignBtn.getText().toString() + frontChestWidthValue,backWidthSignBtn.getText().toString() + backWidthValue,halfShoulderWidthLeftSignBtn.getText().toString() + halfShoulderWidthLeftValue,halfShoulderWidthRightSignBtn.getText().toString() + halfShoulderWidthRightValue,fullBackLengthSignBtn.getText().toString() + fullBackLengthValue,halfBackLengthSignBtn.getText().toString() + halfBackLengthValue,
                                                                                                            "","","","","","","","");


                                                                                                    updateMeasurementData.enqueue(new Callback<String>() {
                                                                                                        @Override
                                                                                                        public void onResponse(Response<String> response, Retrofit retrofit) {

                                                                                                            if(response.body().equals("Successful")){


                                                                                                                Call<String> updateStylingData = service.UpdateStyling(stylingId,fabricIdValue,fabricNumberValue,liningIdValue,liningNumberValue,"","","",
                                                                                                                        "","","","","","",
                                                                                                                        "","","","","","","","","",
                                                                                                                        "","","","","","",
                                                                                                                        "","","","","","","","","",
                                                                                                                        "","","","","","","",
                                                                                                                        "","","","","","","",
                                                                                                                        "","","","","","","",
                                                                                                                        waistCoatId,waistCoatValue,waistCoatImgValue,waistCoatPocketTypeId,waistCoatPocketTypeValue,waistCoatPocketTypeImgValue,waistCoatBackId,
                                                                                                                        waistCoatBackValue,waistCoatBackImgValue,"","","","","",
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
                                                                            Toast.makeText(getApplicationContext(),"Lining not available",Toast.LENGTH_SHORT).show();
                                                                        }
                                                                    }

                                                                    @Override
                                                                    public void onFailure(Throwable t) {
                                                                        Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
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


                                                                            Call<String> existingOrderData = service.UpdateOrder(orderId,shopId,itemType,"Pending Review",billValue,expectedDateValue,orderTypeValue,basePatternValue,baseSizeValue,fabricIdValue,liningIdValue);

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

                                                                                        Call<String> updateMeasurementData = service.UpdateMeasurement(measurementId,stanceId,stanceValue,stanceImgValue,shoulderSlopeId,
                                                                                                shoulderSlopeValue,shoulderSlopeImgValue,chestDescriptionId,chestDescriptionValue,chestDescriptionImgValue,stomachDescriptionId,
                                                                                                stomachDescriptionValue,stomachDescriptionImgValue,hipDescriptionId,hipDescriptionValue,hipDescriptionImgValue,neckSignBtn.getText().toString() + neckValue,fullChestSignBtn.getText().toString() + fullChestValue,
                                                                                                fullShoulderWidthSignBtn.getText().toString() + fullShoulderWidthValue,"","","","",waistStomachSignBtn.getText().toString() + waistStomachValue,hipSignBtn.getText().toString() + hipValue,"",
                                                                                                frontChestWidthSignBtn.getText().toString() + frontChestWidthValue,backWidthSignBtn.getText().toString() + backWidthValue,halfShoulderWidthLeftSignBtn.getText().toString() + halfShoulderWidthLeftValue,halfShoulderWidthRightSignBtn.getText().toString() + halfShoulderWidthRightValue,fullBackLengthSignBtn.getText().toString() + fullBackLengthValue,halfBackLengthSignBtn.getText().toString() + halfBackLengthValue,
                                                                                                "","","","","","","","");


                                                                                        updateMeasurementData.enqueue(new Callback<String>() {
                                                                                            @Override
                                                                                            public void onResponse(Response<String> response, Retrofit retrofit) {

                                                                                                if(response.body().equals("Successful")){


                                                                                                    Call<String> updateStylingData = service.UpdateStyling(stylingId,fabricIdValue,fabricNumberValue,liningIdValue,liningNumberValue,"","","",
                                                                                                            "","","","","","",
                                                                                                            "","","","","","","","","",
                                                                                                            "","","","","","",
                                                                                                            "","","","","","","","","",
                                                                                                            "","","","","","","",
                                                                                                            "","","","","","","",
                                                                                                            "","","","","","","",
                                                                                                            waistCoatId,waistCoatValue,waistCoatImgValue,waistCoatPocketTypeId,waistCoatPocketTypeValue,waistCoatPocketTypeImgValue,waistCoatBackId,
                                                                                                            waistCoatBackValue,waistCoatBackImgValue,"","","","","",
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
                                            }
                                            else{
                                                if(!previousLiningId.equals(liningIdValue)){

                                                    Call<String> liningConsumptionData = service.ProcessLiningConsumption(liningConsumption,liningIdValue);

                                                    liningConsumptionData.enqueue(new Callback<String>() {
                                                        @Override
                                                        public void onResponse(Response<String> response, Retrofit retrofit) {
                                                            if(response.body().equals("Yes")){

                                                                Call<String> liningAdditionData = service.ProcessLiningAddition(liningConsumption,previousLiningId);

                                                                liningAdditionData.enqueue(new Callback<String>() {
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


                                                                            Call<String> existingOrderData = service.UpdateOrder(orderId,shopId,itemType,"Pending Review",billValue,expectedDateValue,orderTypeValue,basePatternValue,baseSizeValue,fabricIdValue,liningIdValue);

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

                                                                                        Call<String> updateMeasurementData = service.UpdateMeasurement(measurementId,stanceId,stanceValue,stanceImgValue,shoulderSlopeId,
                                                                                                shoulderSlopeValue,shoulderSlopeImgValue,chestDescriptionId,chestDescriptionValue,chestDescriptionImgValue,stomachDescriptionId,
                                                                                                stomachDescriptionValue,stomachDescriptionImgValue,hipDescriptionId,hipDescriptionValue,hipDescriptionImgValue,neckSignBtn.getText().toString() + neckValue,fullChestSignBtn.getText().toString() + fullChestValue,
                                                                                                fullShoulderWidthSignBtn.getText().toString() + fullShoulderWidthValue,"","","","",waistStomachSignBtn.getText().toString() + waistStomachValue,hipSignBtn.getText().toString() + hipValue,"",
                                                                                                frontChestWidthSignBtn.getText().toString() + frontChestWidthValue,backWidthSignBtn.getText().toString() + backWidthValue,halfShoulderWidthLeftSignBtn.getText().toString() + halfShoulderWidthLeftValue,halfShoulderWidthRightSignBtn.getText().toString() + halfShoulderWidthRightValue,fullBackLengthSignBtn.getText().toString() + fullBackLengthValue,halfBackLengthSignBtn.getText().toString() + halfBackLengthValue,
                                                                                                "","","","","","","","");


                                                                                        updateMeasurementData.enqueue(new Callback<String>() {
                                                                                            @Override
                                                                                            public void onResponse(Response<String> response, Retrofit retrofit) {

                                                                                                if(response.body().equals("Successful")){


                                                                                                    Call<String> updateStylingData = service.UpdateStyling(stylingId,fabricIdValue,fabricNumberValue,liningIdValue,liningNumberValue,"","","",
                                                                                                            "","","","","","",
                                                                                                            "","","","","","","","","",
                                                                                                            "","","","","","",
                                                                                                            "","","","","","","","","",
                                                                                                            "","","","","","","",
                                                                                                            "","","","","","","",
                                                                                                            "","","","","","","",
                                                                                                            waistCoatId,waistCoatValue,waistCoatImgValue,waistCoatPocketTypeId,waistCoatPocketTypeValue,waistCoatPocketTypeImgValue,waistCoatBackId,
                                                                                                            waistCoatBackValue,waistCoatBackImgValue,"","","","","",
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
                                                                Toast.makeText(getApplicationContext(),"Lining not available",Toast.LENGTH_SHORT).show();
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


                                                                Call<String> existingOrderData = service.UpdateOrder(orderId,shopId,itemType,"Pending Review",billValue,expectedDateValue,orderTypeValue,basePatternValue,baseSizeValue,fabricIdValue,liningIdValue);

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

                                                                            Call<String> updateMeasurementData = service.UpdateMeasurement(measurementId,stanceId,stanceValue,stanceImgValue,shoulderSlopeId,
                                                                                    shoulderSlopeValue,shoulderSlopeImgValue,chestDescriptionId,chestDescriptionValue,chestDescriptionImgValue,stomachDescriptionId,
                                                                                    stomachDescriptionValue,stomachDescriptionImgValue,hipDescriptionId,hipDescriptionValue,hipDescriptionImgValue,neckSignBtn.getText().toString() + neckValue,fullChestSignBtn.getText().toString() + fullChestValue,
                                                                                    fullShoulderWidthSignBtn.getText().toString() + fullShoulderWidthValue,"","","","",waistStomachSignBtn.getText().toString() + waistStomachValue,hipSignBtn.getText().toString() + hipValue,"",
                                                                                    frontChestWidthSignBtn.getText().toString() + frontChestWidthValue,backWidthSignBtn.getText().toString() + backWidthValue,halfShoulderWidthLeftSignBtn.getText().toString() + halfShoulderWidthLeftValue,halfShoulderWidthRightSignBtn.getText().toString() + halfShoulderWidthRightValue,fullBackLengthSignBtn.getText().toString() + fullBackLengthValue,halfBackLengthSignBtn.getText().toString() + halfBackLengthValue,
                                                                                    "","","","","","","","");


                                                                            updateMeasurementData.enqueue(new Callback<String>() {
                                                                                @Override
                                                                                public void onResponse(Response<String> response, Retrofit retrofit) {

                                                                                    if(response.body().equals("Successful")){


                                                                                        Call<String> updateStylingData = service.UpdateStyling(stylingId,fabricIdValue,fabricNumberValue,liningIdValue,liningNumberValue,"","","",
                                                                                                "","","","","","",
                                                                                                "","","","","","","","","",
                                                                                                "","","","","","",
                                                                                                "","","","","","","","","",
                                                                                                "","","","","","","",
                                                                                                "","","","","","","",
                                                                                                "","","","","","","",
                                                                                                waistCoatId,waistCoatValue,waistCoatImgValue,waistCoatPocketTypeId,waistCoatPocketTypeValue,waistCoatPocketTypeImgValue,waistCoatBackId,
                                                                                                waistCoatBackValue,waistCoatBackImgValue,"","","","","",
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

        AlertDialog.Builder builder = new AlertDialog.Builder(OrderTakingWaistCoat.this);
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
                    hideSoftKeyboard(OrderTakingWaistCoat.this);
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

    public int getLiningIndex(List<LiningItem> listItems,String itemId){
        for(int i = 0 ; i < listItems.size() ; i++){
            if(itemId.equals(listItems.get(i).getItemId())){
                return i;
            }
        }
        return -1;
    }

    public void makeLiningSelection(List<LiningItem> listItems,String itemId,String number,LiningAdapter adapter,TextView numberDisplay){
        int index;
        index = getLiningIndex(listItems,itemId);
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
        int totalAmount = fabricPrice + liningPrice + waistCoatPrice + waistCoatPocketTypePrice + waistCoatBackPrice;
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
