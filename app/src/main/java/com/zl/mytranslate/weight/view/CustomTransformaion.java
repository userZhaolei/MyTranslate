package com.zl.mytranslate.weight.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zl.mytranslate.R;

/**
 * Zhaolei
 * 时间:2018/4/4
 */

public class CustomTransformaion extends LinearLayout implements View.OnClickListener {

    public CustomTransformaion(Context context) {
        super(context);
        initView(context);
    }

    public CustomTransformaion(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_btn, this, true);
        Button language_after_btn = view.findViewById(R.id.language_after_btn);
        Button language_before_btn = view.findViewById(R.id.language_before_btn);
        ImageView language_transformation_img = view.findViewById(R.id.language_transformation_img);
        TextView language_before_txt = view.findViewById(R.id.language_after_txt);
        TextView language_after_txt = view.findViewById(R.id.language_before_txt);

        language_after_btn.setOnClickListener(this);
        language_before_btn.setOnClickListener(this);
        language_before_txt.setOnClickListener(this);
        language_after_txt.setOnClickListener(this);
        language_transformation_img.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.language_after_btn:
                break;
            case R.id.language_before_btn:
                break;
            case R.id.language_transformation_img:
                break;
            case R.id.language_before_txt:
                Toast.makeText(getContext(), "点击before", Toast.LENGTH_SHORT).show();
                break;
            case R.id.language_after_txt:
                Toast.makeText(getContext(), "点击before", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
