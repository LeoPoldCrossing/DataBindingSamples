package com.example.databindingsamples.samples.custombinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.example.databindingsamples.BR;
import com.example.databindingsamples.ContactBinding;
import com.example.databindingsamples.R;
import com.example.databindingsamples.model.Contact;
import com.example.databindingsamples.samples.BaseActivity;

public class CutomBindingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ContactBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_cutom_binding);
        Contact contact = new Contact("Messi", "122134567", "2345@gmail.com");
        binding.setVariable(BR.contact, contact);
    }
}
