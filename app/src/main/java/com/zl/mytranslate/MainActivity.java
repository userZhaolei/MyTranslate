package com.zl.mytranslate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.zl.mytranslate.bean.TranslationBean;
import com.zl.mytranslate.core.GetRequest_Interface;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button translate_ok;
    private EditText translate_edit;
    private TextView translate_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setClick();
    }

    private void setClick() {
        translate_ok.setOnClickListener(this);
    }

    private void initView() {
        translate_ok = findViewById(R.id.translate_ok);
        translate_edit = findViewById(R.id.translate_edit);
        translate_text = findViewById(R.id.translate_text);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.translate_ok:
                String trim = translate_edit.getText().toString().trim();
                if (trim.equals("")) {
                    return;
                }
                netWorkData(trim);
                break;
        }
    }

    private void netWorkData(String text) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava
                .build();

        GetRequest_Interface getRequest_interface = retrofit.create(GetRequest_Interface.class);
        Observable<TranslationBean> observable = getRequest_interface.getCall(text);
        // 步骤7：发送网络请求
        observable.subscribeOn(Schedulers.io())               // 在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())  // 回到主线程 处理请求结果
                .subscribe(new Observer<TranslationBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TranslationBean translationBean) {
                        TranslationBean.content content = translationBean.getContent();
                        if (null == content.out) {
                            Toast.makeText(MainActivity.this, "没有搜索到相应结果，换个试试", Toast.LENGTH_SHORT).show();
                        } else {
                            translate_text.setText(content.out);
                            Toast.makeText(MainActivity.this, "查询成功", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
