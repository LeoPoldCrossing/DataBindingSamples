package com.example.databindingsamples.samples.dynamic;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.databindingsamples.BR;
import com.example.databindingsamples.R;
import com.example.databindingsamples.model.User;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by LeoPoldCrossing on 2017/2/20.
 */

public class DynamicAdapter extends RecyclerView.Adapter<DynamicBindingViewHolder> {
    private List<User> users = new ArrayList<>();

    public void setData(List<User> users) {
        this.users = users;
    }
    @Override
    public DynamicBindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.dynamic_list_item, parent, false);
//        DynamicBindingViewHolder holder = new DynamicBindingViewHolder(viewDataBinding.getRoot());
//        holder.setBinding(viewDataBinding);
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.dynamic_list_item, parent,false);
        return new DynamicBindingViewHolder(inflate);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    @Override
    public void onBindViewHolder(DynamicBindingViewHolder holder, int position) {
        User user = users.get(position);
        holder.bind(user);
    }
}
