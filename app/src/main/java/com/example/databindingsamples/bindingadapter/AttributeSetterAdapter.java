package com.example.databindingsamples.bindingadapter;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by LeoPoldCrossing on 2017/2/22.
 */

public class AttributeSetterAdapter {
    
    /*
    * 不需要主动调用
    * */
    @BindingAdapter({"imageUrl","error"})
    public static void loadImage(ImageView view, String url, Drawable error){
        Glide.with(view.getContext()).load(url).error(error).into(view);
    }
}
