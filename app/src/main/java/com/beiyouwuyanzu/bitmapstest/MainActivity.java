package com.beiyouwuyanzu.bitmapstest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.LoginFilter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    @ViewInject(R.id.lv_list)
    private ListView lvList;
    BitmapUtils mbitmap;
    private ArrayList<moban.numdata.newslist> mNewsList;

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
//        Log.i(TAG, "onCreate: 数据下载完成");
//
//        loadlist();
        //等下复原！！！！！！！！！！！！！！

    }

    private void loadlist() {
        Log.i(TAG, "loadlist: 开始加载列表");
//        View view = View.inflate(getApplicationContext(), R.layout.pager_tab_detail, null);
//        ViewUtils.inject(this, view);
        //加载界面显示
        NewsAdapter newAdapter = new NewsAdapter();
        Log.i(TAG, "loadlist: adapter创建完成");
        lvList.setAdapter(newAdapter);
        Log.i(TAG, "loadlist: setadapter执行完毕 ");
        


    }
    class NewsAdapter extends BaseAdapter{
        
        private BitmapUtils mBitmapUtils;
        
        public NewsAdapter() {
            Log.i(TAG, "NewsAdapter: adapter里面创建完了adapter");
            mBitmapUtils = new BitmapUtils(getApplicationContext());
            mBitmapUtils.configDefaultLoadingImage(R.drawable.news_pic_default);
            Log.i(TAG, "NewsAdapter: newsadapter穿件完成");
        }
        @Override
        public int getCount() {
             return mNewsList.size();
//            Log.i(TAG, "getCount: getcount没问题");
        }

        @Override
        public moban.numdata.newslist getItem(int position) {
            return mNewsList.get(position);
//            Log.i(TAG, "getItem: getItem没问题");
        }

        @Override
        public long getItemId(int position) {
             return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Log.i(TAG, "getView: getview方法开始执行了");
            ViewHolder holder;
            Log.i(TAG, "getView: 开始getview");
            convertView = View.inflate(getApplicationContext(), R.layout.list_item_news,
                    null);
            holder = new ViewHolder();
            holder.ivIcon = (ImageView) convertView
                    .findViewById(R.id.iv_icon);
            holder.tvTitle = (TextView) convertView
                    .findViewById(R.id.tv_title);
            holder.tvDate = (TextView) convertView
                    .findViewById(R.id.tv_date);
            convertView.setTag(holder);
            moban.numdata.newslist news = getItem(position);
            holder.tvTitle.setText(news.title);
            holder.tvDate.setText(news.url);

            mBitmapUtils.display(holder.ivIcon, "http://exp.bdstatic.com/static/common/w" +
                    "idget/top-search-box/logo_1e63520.png");
            Log.i(TAG, "getView: getview完毕");

            return convertView;
        }
    }

    private void loadtext() {
        Log.i(TAG, "loadtext: 进入loadtext");
        TextView text = (TextView) findViewById(R.id.textView);
        Gson json=new Gson();
        moban mresult=json.fromJson(result,moban.class);
        /*
        * 开始给列表中的数据赋值啦
        * */
        mNewsList=mresult.data.get(0).children;
        Log.i(TAG, "loadtext: json获取完成");
        Log.i(TAG, "loadtext: mNewlist获取完成");
        text.setText(mresult.data.get(0).children.get(1).title);
      
        Log.i(TAG, "loadtext: 标题设置完毕"+mresult.data.get(0).children.get(1).title);
        Toast.makeText(getApplicationContext(),"标题加载完毕",Toast.LENGTH_LONG);

}

    private void download() {
        HttpUtils utils = new HttpUtils();
        utils.send(HttpMethod.GET, "http://1743s95o31.iok.la:43185//categories.json",
                new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
              result = responseInfo.result;

                System.out.println("服务器返回结果"+ result);

               
                loadtext();
                Log.i(TAG, "onCreate: 数据加载完成");
                loadlist();
                //开始加载列表

            }

            @Override
            public void onFailure(HttpException error, String msg) {
                error.printStackTrace();
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT)
                        .show();
                Log.i(TAG, "onFailure: shu数据加载失败");

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
