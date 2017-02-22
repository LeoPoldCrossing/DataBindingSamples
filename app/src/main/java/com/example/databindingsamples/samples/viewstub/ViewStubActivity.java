package com.example.databindingsamples.samples.viewstub;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;

import com.example.databindingsamples.R;
import com.example.databindingsamples.databinding.ActivityViewStubBinding;
import com.example.databindingsamples.databinding.ViewStubBinding;
import com.example.databindingsamples.model.Contact;
import com.example.databindingsamples.samples.BaseActivity;

/**
 * Created by LeoPoldCrossing on 2017/2/16.
 */

public class ViewStubActivity extends BaseActivity {

    private ActivityViewStubBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_stub);
        binding.viewStub.setOnInflateListener(new ViewStub.OnInflateListener() {
            @Override
            public void onInflate(ViewStub stub, View inflated) {
                ViewStubBinding viewStubBinding = DataBindingUtil.bind(inflated);
                Contact contact = new Contact("Messi", "122134567", "2345@gmail.com");
                viewStubBinding.setContact(contact);
            }
        });
    }

    public void inflateViewStub(View view) {
        if (!binding.viewStub.isInflated()) {
            binding.viewStub.getViewStub().inflate();
        }
    }

}
