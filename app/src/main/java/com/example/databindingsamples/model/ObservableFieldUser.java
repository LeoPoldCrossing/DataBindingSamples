package com.example.databindingsamples.model;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ObservableLong;

/**
 * Created by LeoPoldCrossing on 2017/2/15.
 */

public class ObservableFieldUser {
    public final ObservableField<String> name = new ObservableField<String>();
    public final ObservableLong NO = new ObservableLong();
    public final ObservableInt age = new ObservableInt();
}
