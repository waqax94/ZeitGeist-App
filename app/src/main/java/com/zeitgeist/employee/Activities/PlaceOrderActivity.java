package com.zeitgeist.employee.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zeitgeist.employee.Models.ReplaceFont;
import com.zeitgeist.employee.Models.ZeitGeist;
import com.zeitgeist.employee.R;

import java.io.File;
import java.util.Arrays;

public class PlaceOrderActivity extends AppCompatActivity {

    TextView orderText,placeText;
    ImageView okIcon;
    Animation orderFadein,placeFadein,iconBlink;
    public File folder;
    Bitmap images[] = new Bitmap[10];
    boolean imageStatus[] = new boolean[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);

        ReplaceFont.replaceFont(this,"DEFAULT","fonts/Raleway-Light.ttf");

        orderText = (TextView) findViewById(R.id.order_text);
        placeText = (TextView) findViewById(R.id.placed_text);
        okIcon = (ImageView) findViewById(R.id.ok_icon);

        folder = new File("sdcard/zeitgeist_photos");
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

        orderFadein = AnimationUtils.loadAnimation(this,R.anim.fadein);
        orderFadein.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                placeText.startAnimation(placeFadein);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        placeFadein = AnimationUtils.loadAnimation(this,R.anim.fadein);
        placeFadein.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                okIcon.startAnimation(iconBlink);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        iconBlink = AnimationUtils.loadAnimation(this,R.anim.blink);
        iconBlink.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        orderText.startAnimation(orderFadein);

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(),"Can not go back at this stage",Toast.LENGTH_SHORT).show();
    }

    public void deleteRecursive(File fileOrDirectory) {

        if (fileOrDirectory.isDirectory()) {
            for (File child : fileOrDirectory.listFiles()) {
                deleteRecursive(child);
            }
        }

        fileOrDirectory.delete();
    }
}
