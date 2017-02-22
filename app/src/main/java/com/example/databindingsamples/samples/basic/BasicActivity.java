package com.example.databindingsamples.samples.basic;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.example.databindingsamples.R;
import com.example.databindingsamples.databinding.ActivityBasicBinding;
import com.example.databindingsamples.model.User;
import com.example.databindingsamples.samples.BaseActivity;

/**
 * Created by LeoPoldCrossing on 2017/2/14.
 */

public class BasicActivity extends BaseActivity {
    private User user;
    private ActivityBasicBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_basic);
        user = new User("leo", "wang", 20);
        binding.setUser(user);
        binding.setHandler(new EventHandler());
//        binding.setVariable(BR.user, user);
    }

    public class EventHandler {
        public void onDisplayNameClick(View view) {
            Toast.makeText(view.getContext(), "DisplayName : " + user.getDisplayName(), Toast.LENGTH_SHORT).show();
        }

        public void onFirstNameClick(User user) {
            Toast.makeText(BasicActivity.this, "FirstName : " + user.getFirstName(), Toast.LENGTH_SHORT).show();
        }

        public void onLastNameClick(View view, User user) {
            Toast.makeText(BasicActivity.this, "LastName : " + user.getLastName(), Toast.LENGTH_SHORT).show();
        }

        public void onAgeClick(View view) {
            if (user.getAge() > 18) {
                user.setAge(16);
            } else {
                user.setAge(28);
            }
            binding.setUser(user);
        }
    }
}
