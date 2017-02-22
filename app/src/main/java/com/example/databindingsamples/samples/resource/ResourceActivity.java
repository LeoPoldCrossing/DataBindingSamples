package com.example.databindingsamples.samples.resource;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.example.databindingsamples.R;
import com.example.databindingsamples.databinding.ActivityResourceBinding;
import com.example.databindingsamples.samples.BaseActivity;

/**
 * Created by LeoPoldCrossing on 2017/2/14.
 */

public class ResourceActivity extends BaseActivity {

    private ActivityResourceBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_resource);
        binding.setFirstName("leo");
        binding.setLastName("wang");
        binding.setLarge(true);
        binding.setBananaCount(1);
        binding.setOrangeCount1(1);
        binding.setOrangeCount2(1);
        binding.setHandler(new EventHandler());
    }

    public class EventHandler {
        public void changeBackground(View view) {
            binding.setLarge(!binding.getLarge());
        }
    }
}
