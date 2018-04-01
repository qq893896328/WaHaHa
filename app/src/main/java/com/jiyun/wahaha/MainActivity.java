package com.jiyun.wahaha;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private static int REQUEST_CAMERA_1 = 1;
    private static int REQUEST_CAMERA_2 = 2;
    //获取拍照后的地址
    private String mFilePath;
    private EditText vin;
    private ImageView pai;
    private EditText shousuo;
    private GridView grid;
    private ArrayList<Car> mlist;
    private GridBasAdapter adapter;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();

    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            mlist.add(new Car("大众", R.mipmap.ic_launcher));
        }
        adapter.notifyDataSetChanged();
    }


    private void initView() {
        mlist = new ArrayList<>();
        adapter = new GridBasAdapter(mlist, this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.mipmap.aa);
        toolbar.setTitle("爱车品牌");
        mFilePath = Environment.getExternalStorageDirectory().getPath();// 获取SD卡路径
        mFilePath = mFilePath + "/" + "temp.png";// 指定路径
        vin = (EditText) findViewById(R.id.vin);
        vin.setOnClickListener(this);
        pai = (ImageView) findViewById(R.id.pai);
        pai.setOnClickListener(this);
        shousuo = (EditText) findViewById(R.id.shousuo);
        shousuo.setOnClickListener(this);
        grid = (GridView) findViewById(R.id.grid);
        grid.setNumColumns(5);
        grid.setAdapter(adapter);
        list = (ListView) findViewById(R.id.list);
        list.setOnClickListener(this);
    }

    // 拍照并显示图片
    private void openCamera_1() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// 启动系统相机
        startActivityForResult(intent, REQUEST_CAMERA_1);
    }

    // 拍照后存储并显示图片
    private void openCamera_2() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// 启动系统相机
        Uri photoUri = Uri.fromFile(new File(mFilePath)); // 传递路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);// 更改系统默认存储路径
        startActivityForResult(intent, REQUEST_CAMERA_2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) { // 如果返回数据
            if (requestCode == REQUEST_CAMERA_1) { // 判断请求码是否为REQUEST_CAMERA,如果是代表是这个页面传过去的，需要进行获取
                Bundle bundle = data.getExtras(); // 从data中取出传递回来缩略图的信息，图片质量差，适合传递小图片
                Bitmap bitmap = (Bitmap) bundle.get("data"); // 将data中的信息流解析为Bitmap类型
            } else if (requestCode == REQUEST_CAMERA_2) {
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(mFilePath); // 根据路径获取数据
                    Bitmap bitmap = BitmapFactory.decodeStream(fis);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        fis.close();// 关闭流
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    @Override
    public void onClick(View v) {

    }
}
