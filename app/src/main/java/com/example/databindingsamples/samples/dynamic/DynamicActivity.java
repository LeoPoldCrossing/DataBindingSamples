package com.example.databindingsamples.samples.dynamic;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.example.databindingsamples.R;
import com.example.databindingsamples.databinding.ActivityDynamicBinding;
import com.example.databindingsamples.model.User;
import com.example.databindingsamples.samples.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class DynamicActivity extends BaseActivity {

    private ActivityDynamicBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dynamic);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DynamicAdapter adapter = new DynamicAdapter();
        binding.recyclerView.setAdapter(adapter);
        adapter.setData(makeData());
    }

    public List<User> makeData() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User("Messi", "Leo", 29);
            users.add(user);
        }
        return users;
    }
}
