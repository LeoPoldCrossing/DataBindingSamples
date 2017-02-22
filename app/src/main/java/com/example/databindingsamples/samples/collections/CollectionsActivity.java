package com.example.databindingsamples.samples.collections;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.SparseArray;

import com.example.databindingsamples.R;
import com.example.databindingsamples.databinding.ActivityCollectionsBinding;
import com.example.databindingsamples.samples.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LeoPoldCrossing on 2017/2/15.
 */

public class CollectionsActivity extends BaseActivity {

    private ActivityCollectionsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_collections);

        SparseArray sparseArray = new SparseArray(2);
        sparseArray.put(0, "Messi");
        sparseArray.put(1, "Havi");
        List<String> list = new ArrayList<String>();
        list.add("dataBinding");
        list.add("collections reference");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("firstName", "Messi");
        map.put("lastName", "Lenoa");
        map.put("age", 30);

        int index = 0;
        String key = "firstName";
        binding.setSparse(sparseArray);
        binding.setList(list);
        binding.setMap(map);
        binding.setIndex(index);
        binding.setKey(key);
    }
}
