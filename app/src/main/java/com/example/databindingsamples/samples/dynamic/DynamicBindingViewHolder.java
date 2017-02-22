package com.example.databindingsamples.samples.dynamic;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.databindingsamples.databinding.DynamicListItemBinding;
import com.example.databindingsamples.model.User;

/**
 * Created by LeoPoldCrossing on 2017/2/20.
 */

public class DynamicBindingViewHolder extends RecyclerView.ViewHolder {
//    public ViewDataBinding getBinding() {
//        return binding;
//    }
//
//    public void setBinding(ViewDataBinding binding) {
//        this.binding = binding;
//    }

//    private ViewDataBinding binding;

    private DynamicListItemBinding binding;
    public DynamicBindingViewHolder(View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }

    public void bind(User user){
        binding.setUser(user);
    }
}
