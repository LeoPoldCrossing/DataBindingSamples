package com.example.databindingsamples.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.databindingsamples.R;
import com.example.databindingsamples.model.User;

/**
 * Created by LeoPoldCrossing on 2017/2/22.
 */

public class NameCard extends LinearLayout {

    private TextView firstName;
    private TextView lastName;
    private TextView age;

    public NameCard(Context context) {
        this(context, null);
    }

    public NameCard(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NameCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.namecard, this);
        setOrientation(LinearLayout.VERTICAL);

        firstName = (TextView) findViewById(R.id.firstname);
        lastName = (TextView) findViewById(R.id.lastname);
        age = (TextView) findViewById(R.id.age);
    }

    public void setObject(User user) {
        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        age.setText(String.valueOf(user.getAge()));
    }

}
