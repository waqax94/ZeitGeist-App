package com.zeitgeist.employee.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zeitgeist.employee.Adapters.OrderListAdapter;
import com.zeitgeist.employee.Models.IpClass;
import com.zeitgeist.employee.Models.ReplaceFont;
import com.zeitgeist.employee.Models.ZeitGeist;
import com.zeitgeist.employee.R;

import java.io.File;

public class CameraActivity extends AppCompatActivity {

    TextView title;
    ImageButton backButton;
    Toolbar toolbar;
    Typeface typeface;
    ImageButton addPhotoOne,addPhotoTwo,addPhotoThree,addPhotoFour,addPhotoFive,addPhotoSix,addPhotoSeven,addPhotoEight,addPhotoNine,addPhotoTen;
    ImageButton removePhotoOne,removePhotoTwo,removePhotoThree,removePhotoFour,removePhotoFive,removePhotoSix,removePhotoSeven,removePhotoEight,removePhotoNine,removePhotoTen;
    RelativeLayout photoOneLayout,photoTwoLayout,photoThreeLayout,photoFourLayout,photoFiveLayout,photoSixLayout,photoSevenLayout,photoEightLayout,photoNineLayout,photoTenLayout;
    ImageView photoOne,photoTwo,photoThree,photoFour,photoFive,photoSix,photoSeven,photoEight,photoNine,photoTen;
    File image_file1,image_file2,image_file3,image_file4,image_file5,image_file6,image_file7,image_file8,image_file9,image_file10;
    Bitmap images[] = new Bitmap[10];
    boolean imageStatus[] = new boolean[10];
    static final int PHOTO_REQUEST_ONE = 1;
    static final int PHOTO_REQUEST_TWO = 2;
    static final int PHOTO_REQUEST_THREE = 3;
    static final int PHOTO_REQUEST_FOUR = 4;
    static final int PHOTO_REQUEST_FIVE = 5;
    static final int PHOTO_REQUEST_SIX = 6;
    static final int PHOTO_REQUEST_SEVEN = 7;
    static final int PHOTO_REQUEST_EIGHT = 8;
    static final int PHOTO_REQUEST_NINE = 9;
    static final int PHOTO_REQUEST_TEN = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        typeface = Typeface.createFromAsset(getAssets(),"fonts/Raleway-Light.ttf");
        ReplaceFont.replaceFont(this,"DEFAULT","fonts/Raleway-Light.ttf");

        backButton = (ImageButton) findViewById(R.id.back_btn);
        title = (TextView) findViewById(R.id.toolbar_heading);
        title.setTypeface(typeface);
        title.setText("TAKE PHOTOS");
        toolbar = (Toolbar) findViewById(R.id.c_toolbar);
        setSupportActionBar(toolbar);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        photoOneLayout = (RelativeLayout) findViewById(R.id.photo_one_layout);
        photoTwoLayout = (RelativeLayout) findViewById(R.id.photo_two_layout);
        photoThreeLayout = (RelativeLayout) findViewById(R.id.photo_three_layout);
        photoFourLayout = (RelativeLayout) findViewById(R.id.photo_four_layout);
        photoFiveLayout = (RelativeLayout) findViewById(R.id.photo_five_layout);
        photoSixLayout = (RelativeLayout) findViewById(R.id.photo_six_layout);
        photoSevenLayout = (RelativeLayout) findViewById(R.id.photo_seven_layout);
        photoEightLayout = (RelativeLayout) findViewById(R.id.photo_eight_layout);
        photoNineLayout = (RelativeLayout) findViewById(R.id.photo_nine_layout);
        photoTenLayout = (RelativeLayout) findViewById(R.id.photo_ten_layout);

        photoOne = (ImageView) findViewById(R.id.photo_one);
        photoTwo = (ImageView) findViewById(R.id.photo_two);
        photoThree = (ImageView) findViewById(R.id.photo_three);
        photoFour = (ImageView) findViewById(R.id.photo_four);
        photoFive = (ImageView) findViewById(R.id.photo_five);
        photoSix = (ImageView) findViewById(R.id.photo_six);
        photoSeven = (ImageView) findViewById(R.id.photo_seven);
        photoEight = (ImageView) findViewById(R.id.photo_eight);
        photoNine = (ImageView) findViewById(R.id.photo_nine);
        photoTen = (ImageView) findViewById(R.id.photo_ten);

        addPhotoOne = (ImageButton) findViewById(R.id.add_photo_one);
        removePhotoOne = (ImageButton) findViewById(R.id.remove_photo_one);
        addPhotoTwo = (ImageButton) findViewById(R.id.add_photo_two);
        removePhotoTwo = (ImageButton) findViewById(R.id.remove_photo_two);
        addPhotoThree = (ImageButton) findViewById(R.id.add_photo_three);
        removePhotoThree = (ImageButton) findViewById(R.id.remove_photo_three);
        addPhotoFour = (ImageButton) findViewById(R.id.add_photo_four);
        removePhotoFour = (ImageButton) findViewById(R.id.remove_photo_four);
        addPhotoFive = (ImageButton) findViewById(R.id.add_photo_five);
        removePhotoFive = (ImageButton) findViewById(R.id.remove_photo_five);
        addPhotoSix = (ImageButton) findViewById(R.id.add_photo_six);
        removePhotoSix = (ImageButton) findViewById(R.id.remove_photo_six);
        addPhotoSeven = (ImageButton) findViewById(R.id.add_photo_seven);
        removePhotoSeven = (ImageButton) findViewById(R.id.remove_photo_seven);
        addPhotoEight = (ImageButton) findViewById(R.id.add_photo_eight);
        removePhotoEight = (ImageButton) findViewById(R.id.remove_photo_eight);
        addPhotoNine = (ImageButton) findViewById(R.id.add_photo_nine);
        removePhotoNine = (ImageButton) findViewById(R.id.remove_photo_nine);
        addPhotoTen = (ImageButton) findViewById(R.id.add_photo_ten);
        removePhotoTen = (ImageButton) findViewById(R.id.remove_photo_ten);

        images = ((ZeitGeist) this.getApplication()).getImages();
        imageStatus = ((ZeitGeist) this.getApplication()).getImageStatus();
        image_file1 = ((ZeitGeist) this.getApplication()).getImage_file1();
        image_file2 = ((ZeitGeist) this.getApplication()).getImage_file2();
        image_file3 = ((ZeitGeist) this.getApplication()).getImage_file3();
        image_file4 = ((ZeitGeist) this.getApplication()).getImage_file4();
        image_file5 = ((ZeitGeist) this.getApplication()).getImage_file5();
        image_file6 = ((ZeitGeist) this.getApplication()).getImage_file6();
        image_file7 = ((ZeitGeist) this.getApplication()).getImage_file7();
        image_file8 = ((ZeitGeist) this.getApplication()).getImage_file8();
        image_file9 = ((ZeitGeist) this.getApplication()).getImage_file9();
        image_file10 = ((ZeitGeist) this.getApplication()).getImage_file10();


        if(imageStatus[0]){
            String path = "sdcard/zeitgeist_photos/001.jpg";
            photoOne.setImageBitmap(decodeSampledBitmapFromPath(path,50,50));
            photoOne.setVisibility(View.VISIBLE);
            addPhotoOne.setVisibility(View.INVISIBLE);
            removePhotoOne.setVisibility(View.VISIBLE);
        }
        if(imageStatus[1]){
            String path = "sdcard/zeitgeist_photos/002.jpg";
            photoTwo.setImageBitmap(decodeSampledBitmapFromPath(path,50,50));
            photoTwo.setVisibility(View.VISIBLE);
            addPhotoTwo.setVisibility(View.INVISIBLE);
            removePhotoTwo.setVisibility(View.VISIBLE);
        }
        if(imageStatus[2]){
            String path = "sdcard/zeitgeist_photos/003.jpg";
            photoThree.setImageBitmap(decodeSampledBitmapFromPath(path,50,50));
            photoThree.setVisibility(View.VISIBLE);
            addPhotoThree.setVisibility(View.INVISIBLE);
            removePhotoThree.setVisibility(View.VISIBLE);
        }
        if(imageStatus[3]){
            String path = "sdcard/zeitgeist_photos/004.jpg";
            photoFour.setImageBitmap(decodeSampledBitmapFromPath(path,50,50));
            photoFour.setVisibility(View.VISIBLE);
            addPhotoFour.setVisibility(View.INVISIBLE);
            removePhotoFour.setVisibility(View.VISIBLE);
        }
        if(imageStatus[4]){
            String path = "sdcard/zeitgeist_photos/005.jpg";
            photoFive.setImageBitmap(decodeSampledBitmapFromPath(path,50,50));
            photoFive.setVisibility(View.VISIBLE);
            addPhotoFive.setVisibility(View.INVISIBLE);
            removePhotoFive.setVisibility(View.VISIBLE);
        }
        if(imageStatus[5]){
            String path = "sdcard/zeitgeist_photos/006.jpg";
            photoSix.setImageBitmap(decodeSampledBitmapFromPath(path,50,50));
            photoSix.setVisibility(View.VISIBLE);
            addPhotoSix.setVisibility(View.INVISIBLE);
            removePhotoSix.setVisibility(View.VISIBLE);
        }
        if(imageStatus[6]){
            String path = "sdcard/zeitgeist_photos/007.jpg";
            photoSeven.setImageBitmap(decodeSampledBitmapFromPath(path,50,50));
            photoSeven.setVisibility(View.VISIBLE);
            addPhotoSeven.setVisibility(View.INVISIBLE);
            removePhotoSeven.setVisibility(View.VISIBLE);
        }
        if(imageStatus[7]){
            String path = "sdcard/zeitgeist_photos/008.jpg";
            photoEight.setImageBitmap(decodeSampledBitmapFromPath(path,50,50));
            photoEight.setVisibility(View.VISIBLE);
            addPhotoEight.setVisibility(View.INVISIBLE);
            removePhotoEight.setVisibility(View.VISIBLE);
        }
        if(imageStatus[8]){
            String path = "sdcard/zeitgeist_photos/009.jpg";
            photoNine.setImageBitmap(decodeSampledBitmapFromPath(path,50,50));
            photoNine.setVisibility(View.VISIBLE);
            addPhotoNine.setVisibility(View.INVISIBLE);
            removePhotoNine.setVisibility(View.VISIBLE);
        }
        if(imageStatus[9]){
            String path = "sdcard/zeitgeist_photos/010.jpg";
            photoTen.setImageBitmap(decodeSampledBitmapFromPath(path,50,50));
            photoTen.setVisibility(View.VISIBLE);
            addPhotoTen.setVisibility(View.INVISIBLE);
            removePhotoTen.setVisibility(View.VISIBLE);
        }




        addPhotoOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = getFile("001");
                camIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(camIntent,PHOTO_REQUEST_ONE);

            }
        });
        addPhotoTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = getFile("002");
                camIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(camIntent,PHOTO_REQUEST_TWO);

            }
        });
        addPhotoThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = getFile("003");
                camIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(camIntent,PHOTO_REQUEST_THREE);

            }
        });
        addPhotoFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = getFile("004");
                camIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(camIntent,PHOTO_REQUEST_FOUR);

            }
        });
        addPhotoFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = getFile("005");
                camIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(camIntent,PHOTO_REQUEST_FIVE);

            }
        });
        addPhotoSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = getFile("006");
                camIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(camIntent,PHOTO_REQUEST_SIX);

            }
        });
        addPhotoSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = getFile("007");
                camIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(camIntent,PHOTO_REQUEST_SEVEN);

            }
        });
        addPhotoEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = getFile("008");
                camIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(camIntent,PHOTO_REQUEST_EIGHT);

            }
        });
        addPhotoNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = getFile("009");
                camIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(camIntent,PHOTO_REQUEST_NINE);

            }
        });
        addPhotoTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = getFile("010");
                camIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(camIntent,PHOTO_REQUEST_TEN);

            }
        });

        removePhotoOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(image_file1.exists()){
                    if(image_file1.delete()){
                        imageStatus[0] = false;
                        images[0] = null;
                        ((ZeitGeist) getApplication()).setImages(images);
                        ((ZeitGeist) getApplication()).setImageStatus(imageStatus);
                        photoOne.setVisibility(View.INVISIBLE);
                        addPhotoOne.setVisibility(View.VISIBLE);
                        removePhotoOne.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });
        removePhotoTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(image_file2.exists()){
                    if(image_file2.delete()){
                        imageStatus[1] = false;
                        images[1] = null;
                        ((ZeitGeist) getApplication()).setImages(images);
                        ((ZeitGeist) getApplication()).setImageStatus(imageStatus);
                        photoTwo.setVisibility(View.INVISIBLE);
                        addPhotoTwo.setVisibility(View.VISIBLE);
                        removePhotoTwo.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });
        removePhotoThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(image_file3.exists()){
                    if(image_file3.delete()){
                        imageStatus[2] = false;
                        images[2] = null;
                        ((ZeitGeist) getApplication()).setImages(images);
                        ((ZeitGeist) getApplication()).setImageStatus(imageStatus);
                        photoThree.setVisibility(View.INVISIBLE);
                        addPhotoThree.setVisibility(View.VISIBLE);
                        removePhotoThree.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });
        removePhotoFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(image_file4.exists()){
                    if(image_file4.delete()){
                        imageStatus[3] = false;
                        images[3] = null;
                        ((ZeitGeist) getApplication()).setImages(images);
                        ((ZeitGeist) getApplication()).setImageStatus(imageStatus);
                        photoFour.setVisibility(View.INVISIBLE);
                        addPhotoFour.setVisibility(View.VISIBLE);
                        removePhotoFour.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });
        removePhotoFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(image_file5.exists()){
                    if(image_file5.delete()){
                        imageStatus[4] = false;
                        images[4] = null;
                        ((ZeitGeist) getApplication()).setImages(images);
                        ((ZeitGeist) getApplication()).setImageStatus(imageStatus);
                        photoFive.setVisibility(View.INVISIBLE);
                        addPhotoFive.setVisibility(View.VISIBLE);
                        removePhotoFive.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });
        removePhotoSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(image_file6.exists()){
                    if(image_file6.delete()){
                        imageStatus[5] = false;
                        images[5] = null;
                        ((ZeitGeist) getApplication()).setImages(images);
                        ((ZeitGeist) getApplication()).setImageStatus(imageStatus);
                        photoSix.setVisibility(View.INVISIBLE);
                        addPhotoSix.setVisibility(View.VISIBLE);
                        removePhotoSix.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });
        removePhotoSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(image_file7.exists()){
                    if(image_file7.delete()){
                        imageStatus[6] = false;
                        images[6] = null;
                        ((ZeitGeist) getApplication()).setImages(images);
                        ((ZeitGeist) getApplication()).setImageStatus(imageStatus);
                        photoSeven.setVisibility(View.INVISIBLE);
                        addPhotoSeven.setVisibility(View.VISIBLE);
                        removePhotoSeven.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });
        removePhotoEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(image_file8.exists()){
                    if(image_file8.delete()){
                        imageStatus[7] = false;
                        images[7] = null;
                        ((ZeitGeist) getApplication()).setImages(images);
                        ((ZeitGeist) getApplication()).setImageStatus(imageStatus);
                        photoEight.setVisibility(View.INVISIBLE);
                        addPhotoEight.setVisibility(View.VISIBLE);
                        removePhotoEight.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });
        removePhotoNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(image_file9.exists()){
                    if(image_file9.delete()){
                        imageStatus[8] = false;
                        images[8] = null;
                        ((ZeitGeist) getApplication()).setImages(images);
                        ((ZeitGeist) getApplication()).setImageStatus(imageStatus);
                        photoNine.setVisibility(View.INVISIBLE);
                        addPhotoNine.setVisibility(View.VISIBLE);
                        removePhotoNine.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });
        removePhotoTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(image_file10.exists()){
                    if(image_file10.delete()){
                        imageStatus[9] = false;
                        images[9] = null;
                        ((ZeitGeist) getApplication()).setImages(images);
                        ((ZeitGeist) getApplication()).setImageStatus(imageStatus);
                        photoTen.setVisibility(View.INVISIBLE);
                        addPhotoTen.setVisibility(View.VISIBLE);
                        removePhotoTen.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });

        photoOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ViewImageActivity.class);
                intent.putExtra("path", "sdcard/zeitgeist_photos/001.jpg");
                startActivity(intent);
            }
        });
        photoTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ViewImageActivity.class);
                intent.putExtra("path", "sdcard/zeitgeist_photos/002.jpg");
                startActivity(intent);
            }
        });
        photoThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ViewImageActivity.class);
                intent.putExtra("path", "sdcard/zeitgeist_photos/003.jpg");
                startActivity(intent);
            }
        });
        photoFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ViewImageActivity.class);
                intent.putExtra("path", "sdcard/zeitgeist_photos/004.jpg");
                startActivity(intent);
            }
        });
        photoFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ViewImageActivity.class);
                intent.putExtra("path", "sdcard/zeitgeist_photos/005.jpg");
                startActivity(intent);
            }
        });
        photoSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ViewImageActivity.class);
                intent.putExtra("path", "sdcard/zeitgeist_photos/006.jpg");
                startActivity(intent);
            }
        });
        photoSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ViewImageActivity.class);
                intent.putExtra("path", "sdcard/zeitgeist_photos/007.jpg");
                startActivity(intent);
            }
        });
        photoEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ViewImageActivity.class);
                intent.putExtra("path", "sdcard/zeitgeist_photos/008.jpg");
                startActivity(intent);
            }
        });
        photoNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ViewImageActivity.class);
                intent.putExtra("path", "sdcard/zeitgeist_photos/009.jpg");
                startActivity(intent);
            }
        });
        photoTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ViewImageActivity.class);
                intent.putExtra("path", "sdcard/zeitgeist_photos/010.jpg");
                startActivity(intent);
            }
        });


    }

    private File getFile(String name){
        File folder = new File("sdcard/zeitgeist_photos");

        if(!folder.exists()){
            folder.mkdir();
        }
        if(name == "001") {
            image_file1 = new File(folder, name + ".jpg");
            ((ZeitGeist) this.getApplication()).setImage_file1(image_file1);
            return image_file1;
        }
        else if(name == "002") {
            image_file2 = new File(folder, name + ".jpg");
            ((ZeitGeist) this.getApplication()).setImage_file2(image_file2);
            return image_file2;
        }
        else if(name == "003") {
            image_file3 = new File(folder, name + ".jpg");
            ((ZeitGeist) this.getApplication()).setImage_file3(image_file3);
            return image_file3;
        }
        else if(name == "004") {
            image_file4 = new File(folder, name + ".jpg");
            ((ZeitGeist) this.getApplication()).setImage_file4(image_file4);
            return image_file4;
        }
        else if(name == "005") {
            image_file5 = new File(folder, name + ".jpg");
            ((ZeitGeist) this.getApplication()).setImage_file5(image_file5);
            return image_file5;
        }
        else if(name == "006") {
            image_file6 = new File(folder, name + ".jpg");
            ((ZeitGeist) this.getApplication()).setImage_file6(image_file6);
            return image_file6;
        }
        else if(name == "007") {
            image_file7 = new File(folder, name + ".jpg");
            ((ZeitGeist) this.getApplication()).setImage_file7(image_file7);
            return image_file7;
        }
        else if(name == "008") {
            image_file8 = new File(folder, name + ".jpg");
            ((ZeitGeist) this.getApplication()).setImage_file8(image_file8);
            return image_file8;
        }
        else if(name == "009") {
            image_file9 = new File(folder, name + ".jpg");
            ((ZeitGeist) this.getApplication()).setImage_file9(image_file9);
            return image_file9;
        }
        else if(name == "010") {
            image_file10 = new File(folder, name + ".jpg");
            ((ZeitGeist) this.getApplication()).setImage_file10(image_file10);
            return image_file10;
        }
        else {
            return null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        if(requestCode == PHOTO_REQUEST_ONE){
            switch (resultCode){
                case Activity.RESULT_OK:
                    if(image_file1.exists()){
                        String path = "sdcard/zeitgeist_photos/001.jpg";
                        images[0] = decodeSampledBitmapFromPath(path,150,150);
                        imageStatus[0] = true;
                        ((ZeitGeist) this.getApplication()).setImages(images);
                        ((ZeitGeist) this.getApplication()).setImageStatus(imageStatus);
                        photoOne.setImageBitmap(decodeSampledBitmapFromPath(path,50,50));
                        photoOne.setVisibility(View.VISIBLE);
                        addPhotoOne.setVisibility(View.INVISIBLE);
                        removePhotoOne.setVisibility(View.VISIBLE);
                    }
                    break;
                case Activity.RESULT_CANCELED:
                    break;
            }
        }
        if(requestCode == PHOTO_REQUEST_TWO){
            switch (resultCode){
                case Activity.RESULT_OK:
                    if(image_file2.exists()){
                        String path = "sdcard/zeitgeist_photos/002.jpg";
                        images[1] = decodeSampledBitmapFromPath(path,150,150);
                        imageStatus[1] = true;
                        ((ZeitGeist) this.getApplication()).setImages(images);
                        ((ZeitGeist) this.getApplication()).setImageStatus(imageStatus);
                        photoTwo.setImageBitmap(decodeSampledBitmapFromPath(path,50,50));
                        photoTwo.setVisibility(View.VISIBLE);
                        addPhotoTwo.setVisibility(View.INVISIBLE);
                        removePhotoTwo.setVisibility(View.VISIBLE);
                    }
                    break;
                case Activity.RESULT_CANCELED:
                    break;
            }
        }
        if(requestCode == PHOTO_REQUEST_THREE){
            switch (resultCode){
                case Activity.RESULT_OK:
                    if(image_file3.exists()){
                        String path = "sdcard/zeitgeist_photos/003.jpg";
                        images[2] = decodeSampledBitmapFromPath(path,150,150);
                        imageStatus[2] = true;
                        ((ZeitGeist) this.getApplication()).setImages(images);
                        ((ZeitGeist) this.getApplication()).setImageStatus(imageStatus);
                        photoThree.setImageBitmap(decodeSampledBitmapFromPath(path,50,50));
                        photoThree.setVisibility(View.VISIBLE);
                        addPhotoThree.setVisibility(View.INVISIBLE);
                        removePhotoThree.setVisibility(View.VISIBLE);
                    }
                    break;
                case Activity.RESULT_CANCELED:
                    break;
            }
        }
        if(requestCode == PHOTO_REQUEST_FOUR){
            switch (resultCode){
                case Activity.RESULT_OK:
                    if(image_file4.exists()){
                        String path = "sdcard/zeitgeist_photos/004.jpg";
                        images[3] = decodeSampledBitmapFromPath(path,150,150);
                        imageStatus[3] = true;
                        ((ZeitGeist) this.getApplication()).setImages(images);
                        ((ZeitGeist) this.getApplication()).setImageStatus(imageStatus);
                        photoFour.setImageBitmap(decodeSampledBitmapFromPath(path,50,50));
                        photoFour.setVisibility(View.VISIBLE);
                        addPhotoFour.setVisibility(View.INVISIBLE);
                        removePhotoFour.setVisibility(View.VISIBLE);
                     }
                    break;
                case Activity.RESULT_CANCELED:
                    break;
            }
        }
        if(requestCode == PHOTO_REQUEST_FIVE){
            switch (resultCode){
                case Activity.RESULT_OK:
                    if(image_file5.exists()){
                        String path = "sdcard/zeitgeist_photos/005.jpg";
                        images[4] = decodeSampledBitmapFromPath(path,150,150);
                        imageStatus[4] = true;
                        ((ZeitGeist) this.getApplication()).setImages(images);
                        ((ZeitGeist) this.getApplication()).setImageStatus(imageStatus);
                        photoFive.setImageBitmap(decodeSampledBitmapFromPath(path,50,50));
                        photoFive.setVisibility(View.VISIBLE);
                        addPhotoFive.setVisibility(View.INVISIBLE);
                        removePhotoFive.setVisibility(View.VISIBLE);
                     }
                    break;
                case Activity.RESULT_CANCELED:
                    break;
            }
        }
        if(requestCode == PHOTO_REQUEST_SIX){
            switch (resultCode){
                case Activity.RESULT_OK:
                    if(image_file6.exists()){
                        String path = "sdcard/zeitgeist_photos/006.jpg";
                        images[5] = decodeSampledBitmapFromPath(path,150,150);
                        imageStatus[5] = true;
                        ((ZeitGeist) this.getApplication()).setImages(images);
                        ((ZeitGeist) this.getApplication()).setImageStatus(imageStatus);
                        photoSix.setImageBitmap(decodeSampledBitmapFromPath(path,50,50));
                        photoSix.setVisibility(View.VISIBLE);
                        addPhotoSix.setVisibility(View.INVISIBLE);
                        removePhotoSix.setVisibility(View.VISIBLE);
                    }
                    break;
                case Activity.RESULT_CANCELED:
                    break;
            }
        }
        if(requestCode == PHOTO_REQUEST_SEVEN){
            switch (resultCode){
                case Activity.RESULT_OK:
                    if(image_file7.exists()){
                        String path = "sdcard/zeitgeist_photos/007.jpg";
                        images[6] = decodeSampledBitmapFromPath(path,150,150);
                        imageStatus[6] = true;
                        ((ZeitGeist) this.getApplication()).setImages(images);
                        ((ZeitGeist) this.getApplication()).setImageStatus(imageStatus);
                        photoSeven.setImageBitmap(decodeSampledBitmapFromPath(path,50,50));
                        photoSeven.setVisibility(View.VISIBLE);
                        addPhotoSeven.setVisibility(View.INVISIBLE);
                        removePhotoSeven.setVisibility(View.VISIBLE);
                    }
                    break;
                case Activity.RESULT_CANCELED:
                    break;
            }
        }
        if(requestCode == PHOTO_REQUEST_EIGHT){
            switch (resultCode){
                case Activity.RESULT_OK:
                    if(image_file8.exists()){
                        String path = "sdcard/zeitgeist_photos/008.jpg";
                        images[7] = decodeSampledBitmapFromPath(path,150,150);
                        imageStatus[7] = true;
                        ((ZeitGeist) this.getApplication()).setImages(images);
                        ((ZeitGeist) this.getApplication()).setImageStatus(imageStatus);
                        photoEight.setImageBitmap(decodeSampledBitmapFromPath(path,50,50));
                        photoEight.setVisibility(View.VISIBLE);
                        addPhotoEight.setVisibility(View.INVISIBLE);
                        removePhotoEight.setVisibility(View.VISIBLE);
                    }
                    break;
                case Activity.RESULT_CANCELED:
                    break;
            }
        }
        if(requestCode == PHOTO_REQUEST_NINE){
            switch (resultCode){
                case Activity.RESULT_OK:
                    if(image_file9.exists()){
                        String path = "sdcard/zeitgeist_photos/009.jpg";
                        images[8] = decodeSampledBitmapFromPath(path,150,150);
                        imageStatus[8] = true;
                        ((ZeitGeist) this.getApplication()).setImages(images);
                        ((ZeitGeist) this.getApplication()).setImageStatus(imageStatus);
                        photoNine.setImageBitmap(decodeSampledBitmapFromPath(path,50,50));
                        photoNine.setVisibility(View.VISIBLE);
                        addPhotoNine.setVisibility(View.INVISIBLE);
                        removePhotoNine.setVisibility(View.VISIBLE);
                    }
                    break;
                case Activity.RESULT_CANCELED:
                    break;
            }
        }
        if(requestCode == PHOTO_REQUEST_TEN){
            switch (resultCode){
                case Activity.RESULT_OK:
                    if(image_file10.exists()){
                        String path = "sdcard/zeitgeist_photos/010.jpg";
                        images[9] = decodeSampledBitmapFromPath(path,150,150);
                        imageStatus[9] = true;
                        ((ZeitGeist) this.getApplication()).setImages(images);
                        ((ZeitGeist) this.getApplication()).setImageStatus(imageStatus);
                        photoTen.setImageBitmap(decodeSampledBitmapFromPath(path,50,50));
                        photoTen.setVisibility(View.VISIBLE);
                        addPhotoTen.setVisibility(View.INVISIBLE);
                        removePhotoTen.setVisibility(View.VISIBLE);
                    }
                    break;
                case Activity.RESULT_CANCELED:
                    break;
            }
        }
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromPath(String path ,int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, options);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
