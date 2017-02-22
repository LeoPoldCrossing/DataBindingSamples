package com.example.databindingsamples.samples.observable;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableArrayMap;
import android.databinding.ObservableList;
import android.databinding.ObservableMap;
import android.os.Bundle;
import android.view.View;

import com.example.databindingsamples.R;
import com.example.databindingsamples.databinding.ActivityObservableBinding;
import com.example.databindingsamples.model.ObservableFieldUser;
import com.example.databindingsamples.model.ObservableUser;
import com.example.databindingsamples.samples.BaseActivity;

/**
 * Created by LeoPoldCrossing on 2017/2/15.
 */

public class ObservableActivity extends BaseActivity {
    private ActivityObservableBinding binding;
    private int count;

    private ObservableUser observableUser = new ObservableUser();
    private ObservableFieldUser observableFieldUser = new ObservableFieldUser();
    private ObservableList<Object> observableArrayList = new ObservableArrayList();
    private ObservableMap<String, Object> observableArrayMap = new ObservableArrayMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_observable);

        binding.setUser(observableUser);
        binding.setFieldUser(observableFieldUser);
        binding.setList(observableArrayList);
        binding.setMap(observableArrayMap);
        binding.setHandler(new EventHandler());
    }

    public class EventHandler {
        public void changeData(View view) {
            if (count % 2 == 0) {
                observableUser.setName("Messi");
                observableUser.setIdNumber(10);
                observableUser.setAge(30);

                observableFieldUser.name.set("James");
                observableFieldUser.NO.set(23);
                observableFieldUser.age.set(31);

                observableFieldUser.name.get();
                observableFieldUser.age.get();
                observableFieldUser.NO.get();


                if (observableArrayList.size() > 0) {
                    observableArrayList.set(0, "Android");
                } else {
                    observableArrayList.add(0, "Android");
                }
                observableArrayMap.put("system", "Android");
                observableArrayMap.put("brand", "三星");
                observableArrayMap.put("version", "6.0.1");
            } else {
                observableUser.setName("Havi");
                observableUser.setIdNumber(6);
                observableUser.setAge(36);

                observableFieldUser.name.set("Kobe");
                observableFieldUser.NO.set(24);
                observableFieldUser.age.set(38);

                if (observableArrayList.size() > 0) {
                    observableArrayList.set(0, "IOS");
                } else {
                    observableArrayList.add(0, "IOS");
                }

                observableArrayMap.put("system", "IOS");
                observableArrayMap.put("brand", "苹果");
                observableArrayMap.put("version", "ios10");
            }
            count++;
        }
    }
}
