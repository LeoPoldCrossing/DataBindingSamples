package com.example.databindingsamples.samples.attributesetter;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.databindingsamples.R;
import com.example.databindingsamples.databinding.ActivityAutoSetterBinding;
import com.example.databindingsamples.model.User;
import com.example.databindingsamples.samples.BaseActivity;

/**
 * Created by LeoPoldCrossing on 2017/2/21.
 */

public class AutoAttributeSetterActivity extends BaseActivity {

    private ActivityAutoSetterBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_auto_setter);
        User user = new User("leo", "messi", 29);
        binding.setUser(user);

        binding.setUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1487756435266&di=75af5f1adab95234a60cba959292e5ca&imgtype=0&src=http%3A%2F%2Fk.zol-img.com.cn%2Fdcbbs%2F24965%2Fa24964298_s.jpg");
    }
}
