package com.example.databindingsamples.samples;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.databindingsamples.R;
import com.example.databindingsamples.samples.attributesetter.AutoAttributeSetterActivity;
import com.example.databindingsamples.samples.basic.BasicActivity;
import com.example.databindingsamples.samples.collections.CollectionsActivity;
import com.example.databindingsamples.samples.converters.ConvertersActivity;
import com.example.databindingsamples.samples.custombinding.CutomBindingActivity;
import com.example.databindingsamples.samples.dynamic.DynamicActivity;
import com.example.databindingsamples.samples.includes.IncludesActivity;
import com.example.databindingsamples.samples.observable.ObservableActivity;
import com.example.databindingsamples.samples.resource.ResourceActivity;
import com.example.databindingsamples.samples.viewid.ViewWithIdActivity;
import com.example.databindingsamples.samples.viewstub.ViewStubActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openViewId(View view) {
        startActivity(new Intent(this, ViewWithIdActivity.class));
    }

    public void openBasic(View view) {
        startActivity(new Intent(this, BasicActivity.class));
    }

    public void openCustomBinding(View view) {
        startActivity(new Intent(this, CutomBindingActivity.class));
    }

    public void openInclude(View view) {
        startActivity(new Intent(this, IncludesActivity.class));
    }

    public void openResource(View view) {
        startActivity(new Intent(this, ResourceActivity.class));
    }

    public void openCollections(View view) {
        startActivity(new Intent(this, CollectionsActivity.class));
    }

    public void openObservable(View view) {
        startActivity(new Intent(this, ObservableActivity.class));
    }

    public void openViewStub(View view) {
        startActivity(new Intent(this, ViewStubActivity.class));
    }
    public void openDynamic(View view){
        startActivity(new Intent(this, DynamicActivity.class));
    }

    public void openAutoAttributeSetter(View view){
        startActivity(new Intent(this, AutoAttributeSetterActivity.class));

    }

    public void openConverters(View view){
        startActivity(new Intent(this, ConvertersActivity.class));
    }
}
