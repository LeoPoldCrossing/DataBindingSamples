package com.example.databindingsamples.samples.includes;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.example.databindingsamples.R;
import com.example.databindingsamples.databinding.ActivityIncludesBinding;
import com.example.databindingsamples.model.User;
import com.example.databindingsamples.samples.BaseActivity;

public class IncludesActivity extends BaseActivity {

    private ActivityIncludesBinding binding;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_includes);
        user = new User("狗剩", "Tony", 38);
        binding.setUser(user);
        binding.layoutInput.setHandler(new EventHandler());
    }


    public class EventHandler {
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            user.setFirstName(s.toString());
            if (user.getAge() > 18) {
                user.setAge(16);
            } else {
                user.setAge(28);
            }
            binding.setUser(user);
        }
    }

}
