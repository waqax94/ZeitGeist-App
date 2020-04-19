package com.zeitgeist.employee.Adapters;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
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
import android.widget.Filter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zeitgeist.employee.Models.FabricLiningItem;
import com.zeitgeist.employee.Models.GridListItem;
import com.zeitgeist.employee.Models.IpClass;
import com.zeitgeist.employee.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by User on 9/11/2017.
 */

public class FabricLiningAdapter extends ArrayAdapter {
    List fabricLiningItems =  new ArrayList();
    List<FabricLiningItem> filterList =  new ArrayList<FabricLiningItem>();
    List<FabricLiningItem> returnList =  new ArrayList<FabricLiningItem>();
    CustomeFilter filter;
    Context context;
    IpClass ipClass = new IpClass();
    Drawable selected,unselected;
    ImageView expandedImageView;
    RelativeLayout container;
    private Animator mCurrentAnimator;
    private int mShortAnimationDuration = 200;
    public HashMap<Integer, Boolean> hashMapSelected;

    public FabricLiningAdapter(Context context, int resource, Drawable selected, Drawable unselected, ImageView expandedImageView, RelativeLayout container) {
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
        TextView stock;
    }

    @Override
    public void add(Object object) {
        super.add(object);
    }

    public void addItemList(List<FabricLiningItem> obj){
        fabricLiningItems = obj;
        this.filterList = obj;
        this.returnList = obj;
        hashMapSelected = new HashMap<>();
        for (int i = 0; i < fabricLiningItems.size(); i++) {
            hashMapSelected.put(i, false);
        }
    }

    public void removeSelection(){
        try{
            for (int i = 0; i < hashMapSelected.size(); i++) {
                hashMapSelected.put(i, false);
            }
        }
        catch (NullPointerException e){

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
        return this.fabricLiningItems.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return this.fabricLiningItems.get(position);
    }

    @NonNull
    @Override
    public Filter getFilter() {
        if(filter == null){
            filter = new CustomeFilter();
        }
        return filter;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View grid;
        grid = convertView;
        final FabricLiningAdapter.Handler handler;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            grid = inflater.inflate(R.layout.fabric_grid_layout,parent,false);
            handler = new FabricLiningAdapter.Handler();
            handler.gridText = (TextView) grid.findViewById(R.id.fabric_grid_text);
            handler.gridImg = (ImageView) grid.findViewById(R.id.fabric_grid_image);
            handler.checkIcon = (ImageView) grid.findViewById(R.id.fabric_check_icon);
            handler.zoomImg = (ImageButton) grid.findViewById(R.id.fabric_zoom_icon);
            handler.stock = (TextView) grid.findViewById(R.id.fabric_stock_data);

            grid.setTag(handler);
        }
        else {
            handler = (FabricLiningAdapter.Handler)grid.getTag();
        }

        final FabricLiningItem fabricLiningItem;
        fabricLiningItem = (FabricLiningItem) this.getItem(position);

        Picasso.with(this.getContext()).load(ipClass.ipAddress + fabricLiningItem.getItemImageSource()).into(handler.gridImg);
        handler.gridText.setText(fabricLiningItem.getItemNumber());

        if(Double.parseDouble(fabricLiningItem.getStock()) < 5){
            handler.stock.setText("Out of Stock");
        }
        else {
            handler.stock.setText(fabricLiningItem.getStock() + "m");
        }
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
                zoomImageFromThumb(handler.gridImg,fabricLiningItem.getItemImageSource());
            }
        });

        return grid;
    }


    class CustomeFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults results = new FilterResults();

            List<FabricLiningItem> filters = new ArrayList<FabricLiningItem>();

            if(constraint != null && constraint.length() > 0){
                constraint = constraint.toString().toUpperCase();

                for (int i = 0 ; i < filterList.size() ; i++){

                    if (filterList.get(i).getItemNumber().toUpperCase().contains(constraint)){

                        FabricLiningItem fl = new FabricLiningItem(filterList.get(i).getItemId(),filterList.get(i).getItemNumber(),filterList.get(i).getItemImageSource(),filterList.get(i).getStock(),filterList.get(i).getItemMtm3suitPrice(),filterList.get(i).getItemMtm2suitPrice(),filterList.get(i).getItemMtmJacketPrice(),filterList.get(i).getItemMtmWaistCoatPrice(),filterList.get(i).getItemMtmTrouserPrice(),filterList.get(i).getItemMtmShirtPrice(),filterList.get(i).getItemBs3suitPrice(),filterList.get(i).getItemBs2suitPrice(),filterList.get(i).getItemBsJacketPrice(),filterList.get(i).getItemBsWaistCoatPrice(),filterList.get(i).getItemBsTrouserPrice(),filterList.get(i).getItemBsShirtPrice());
                        filters.add(fl);

                    }
                }
                results.count = filters.size();
                results.values = filters;
            }
            else {
                filters = filterList;
                results.count = filterList.size();
                results.values = filterList;
            }

            setResultList(filters);

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            fabricLiningItems = (List) results.values;
            notifyDataSetChanged();
        }
    }

    public void setResultList(List<FabricLiningItem> filterList){
        returnList = filterList;
    }

    public List<FabricLiningItem> getFilterList(){
        return returnList;
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
