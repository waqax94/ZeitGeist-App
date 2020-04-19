package com.zeitgeist.employee.Adapters;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zeitgeist.employee.Models.GridListItem;
import com.zeitgeist.employee.Models.IpClass;
import com.zeitgeist.employee.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by User on 8/27/2017.
 */

public class StylingAdapter extends ArrayAdapter {
    List gridItems =  new ArrayList();
    Context context;
    IpClass ipClass = new IpClass();
    Drawable selected,unselected;
    ImageView expandedImageView;
    RelativeLayout container;
    private Animator mCurrentAnimator;
    private int mShortAnimationDuration = 200;
    public HashMap<Integer, Boolean> hashMapSelected;

    public StylingAdapter(Context context, int resource, Drawable selected,Drawable unselected,  ImageView expandedImageView, RelativeLayout container) {
        super(context, resource);
        this.context = context;
        this.selected = selected;
        this.unselected = unselected;
        this.expandedImageView = expandedImageView;
        this.container = container;
    }


    static class Handler{
        TextView gridText;
        ImageView gridImg;
        ImageView checkIcon;
        ImageButton zoomImg;
    }

    @Override
    public void add(Object object) {
        super.add(object);
    }

    public void addItemList(List<GridListItem> obj){
        gridItems = obj;
        hashMapSelected = new HashMap<>();
        boolean check = true;
        try{
            int er = gridItems.size();
        }
        catch (NullPointerException e){
            check = false;
        }
        if(check){
            for (int i = 0; i < gridItems.size(); i++) {
                hashMapSelected.put(i, false);
            }
        }
    }

    public void makeAllUnselect(int position) {
        hashMapSelected.put(position, true);
        for (int i = 0; i < hashMapSelected.size(); i++) {
            if (i != position)
                hashMapSelected.put(i, false);
        }
    }

    @Override
    public int getCount() {
        return this.gridItems.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return this.gridItems.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View grid;
        grid = convertView;
        final StylingAdapter.Handler handler;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            grid = inflater.inflate(R.layout.gridview_layout,parent,false);
            handler = new StylingAdapter.Handler();
            handler.gridText = (TextView) grid.findViewById(R.id.grid_text);
            handler.gridImg = (ImageView) grid.findViewById(R.id.grid_image);
            handler.checkIcon = (ImageView) grid.findViewById(R.id.check_icon);
            handler.zoomImg = (ImageButton) grid.findViewById(R.id.zoom_icon);

            grid.setTag(handler);
        }
        else {
            handler = (StylingAdapter.Handler)grid.getTag();
        }

        final GridListItem gridListItem;
        gridListItem = (GridListItem) this.getItem(position);

        Picasso.with(this.getContext()).load(ipClass.ipAddress + gridListItem.getItemImageSource()).into(handler.gridImg);
        handler.gridText.setText(gridListItem.getItemName());

        if (hashMapSelected.get(position) == true) {
            handler.gridImg.setBackground(selected);
            handler.checkIcon.setVisibility(View.VISIBLE);
        } else {
            handler.gridImg.setBackground(unselected);
            handler.checkIcon.setVisibility(View.INVISIBLE);
        }

        handler.zoomImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zoomImageFromThumb(handler.gridImg,gridListItem.getItemImageSource());
            }
        });

        return grid;
    }

    private void zoomImageFromThumb(final View thumbView,String imageSource){

        if (mCurrentAnimator != null) {
            mCurrentAnimator.cancel();
        }

        Picasso.with(this.getContext()).load(ipClass.ipAddress + imageSource).into(expandedImageView);


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
