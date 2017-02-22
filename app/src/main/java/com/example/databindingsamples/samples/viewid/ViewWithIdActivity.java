package com.example.databindingsamples.samples.viewid;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.databindingsamples.R;
import com.example.databindingsamples.databinding.ActivityViewidBinding;
import com.example.databindingsamples.samples.BaseActivity;

/**
 * Created by LeoPoldCrossing on 2017/2/14.
 */

public class ViewWithIdActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityViewidBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_viewid);
        binding.tvEmail.setText("110112119@163.com");
        binding.tvName.setText("报警");
        binding.tvMobile.setText("12580");
    }
}
