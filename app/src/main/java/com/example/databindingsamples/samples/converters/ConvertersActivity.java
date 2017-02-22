package com.example.databindingsamples.samples.converters;

import android.databinding.BindingConversion;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableBoolean;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.databindingsamples.R;
import com.example.databindingsamples.databinding.ActivityConvertersBinding;
import com.example.databindingsamples.samples.BaseActivity;

/**
 * Created by LeoPoldCrossing on 2017/2/22.
 */

public class ConvertersActivity extends BaseActivity {

    private ObservableBoolean isError = new ObservableBoolean();

    private ActivityConvertersBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_converters);
        isError.set(true);
        binding.setIsError(isError);
    }
    public void onToggle(View view) {
        isError.set(!isError.get());
    }

    @BindingConversion
    public static ColorDrawable convertColorToDrawable(int color){
        return new ColorDrawable(color);
    }
}
