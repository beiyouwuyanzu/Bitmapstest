package com.beiyouwuyanzu.bitmapstest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;



public class MainActivity extends AppCompatActivity {
    BitmapUtils mbitmap;
    private String result;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate: 创建完成");
//        BitmapUtils bitmapUtils = new BitmapUtils(this);
//
//        bitmapUtils.display();
        loadpic();
        Log.i(TAG, "onCreate: 图片加载完成");
        
        download();
        Log.i(TAG, "onCreate: 数据下载完成");

    }

    private void loadtext() {
        TextView text = (TextView) findViewById(R.id.textView);
        Gson json=new Gson();
        moban mresult=json.fromJson(result,moban.class);
        text.setText(mresult.data.get(0).children.get(1).title);
        Toast.makeText(getApplicationContext(),"标题加载完毕",Toast.LENGTH_LONG);

}

    private void download() {
        HttpUtils utils = new HttpUtils();
        utils.send(HttpMethod.GET, "http://1743s95o31.iok.la:43185/data/data.json",
                new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                result = responseInfo.result;
                System.out.println("服务器返回结果"+ result);
                loadtext();
                Log.i(TAG, "onCreate: 数据加载完成");

            }

            @Override
            public void onFailure(HttpException error, String msg) {
                error.printStackTrace();
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT)
                        .show();

            }
        });
    }

    private void loadpic() {
        BitmapUtils bitmapUtils = new BitmapUtils(this);
        View pic = findViewById(R.id.imageView);
        pic.setVisibility(View.VISIBLE);
        bitmapUtils.display(pic,"http://1743s95o31.iok.la:43185/10007/1505891536Z82T.jpg");
        Toast.makeText(this,"图片加载完成",Toast.LENGTH_LONG).show();

    }
}
