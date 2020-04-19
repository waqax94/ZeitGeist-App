package com.zeitgeist.employee.Models;

import android.app.Application;
import android.graphics.Bitmap;

import java.io.File;

/**
 * Created by User on 12/15/2017.
 */

public class ZeitGeist extends Application {
    private File image_file1,image_file2,image_file3,image_file4,image_file5,image_file6,image_file7,image_file8,image_file9,image_file10;
    private Bitmap images[] = new Bitmap[10];
    private boolean imageStatus[] = new boolean[10];


    public File getImage_file1() {
        return image_file1;
    }

    public void setImage_file1(File image_file1) {
        this.image_file1 = image_file1;
    }

    public File getImage_file2() {
        return image_file2;
    }

    public void setImage_file2(File image_file2) {
        this.image_file2 = image_file2;
    }

    public File getImage_file3() {
        return image_file3;
    }

    public void setImage_file3(File image_file3) {
        this.image_file3 = image_file3;
    }

    public File getImage_file4() {
        return image_file4;
    }

    public void setImage_file4(File image_file4) {
        this.image_file4 = image_file4;
    }

    public File getImage_file5() {
        return image_file5;
    }

    public void setImage_file5(File image_file5) {
        this.image_file5 = image_file5;
    }

    public File getImage_file6() {
        return image_file6;
    }

    public void setImage_file6(File image_file6) {
        this.image_file6 = image_file6;
    }

    public File getImage_file7() {
        return image_file7;
    }

    public void setImage_file7(File image_file7) {
        this.image_file7 = image_file7;
    }

    public File getImage_file8() {
        return image_file8;
    }

    public void setImage_file8(File image_file8) {
        this.image_file8 = image_file8;
    }

    public File getImage_file9() {
        return image_file9;
    }

    public void setImage_file9(File image_file9) {
        this.image_file9 = image_file9;
    }

    public File getImage_file10() {
        return image_file10;
    }

    public void setImage_file10(File image_file10) {
        this.image_file10 = image_file10;
    }

    public Bitmap[] getImages() {
        return images;
    }

    public void setImages(Bitmap[] images) {
        this.images = images;
    }

    public boolean[] getImageStatus() {
        return imageStatus;
    }

    public void setImageStatus(boolean[] imageStatus) {
        this.imageStatus = imageStatus;
    }
}
