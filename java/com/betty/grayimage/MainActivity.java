package com.betty.grayimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Mat;

public class MainActivity extends AppCompatActivity {
    private ImageView show_image;
    private static String TAG = "OpenCVLoader";
    private Button pic,gray_pic;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("opencv_java3");
        System.loadLibrary("native-lib");
        System.loadLibrary("GrayImager");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pic = (Button)findViewById(R.id.pic);
        gray_pic = (Button)findViewById(R.id.gray_pic);
        initView();

    }
    public void initView(){
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(new NDKLoader().stringFromJNI());
        show_image = (ImageView)findViewById(R.id.show_image);
        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backPic();//恢复
            }
        });
        gray_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grayPic();//灰度化
            }
        });
    }
    //恢复
    public void backPic(){
        show_image.setImageResource(R.drawable.image);
    }

    //灰度化
    public void grayPic(){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.image);
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        //装图片的像素
        int[] pixels = new int[w*h];
        //将图片转换成数组
        bitmap.getPixels(pixels,0,w,0,0,w,h);
        //recall JNI，把数组传给c++处理
        int[] rsultInt = new NDKLoader().getGraryImage(pixels,w,h);
        //将创建好的数组重新生成图片
        Bitmap resultImg = Bitmap.createBitmap(pixels,w,h,Bitmap.Config.ARGB_8888);

//        resultImg.setPixels(rsultInt,0,w,0,0,w,h);
        //将图片位图展示出来
        show_image.setImageBitmap(resultImg);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (OpenCVLoader.initDebug()){
            Log.i("TAG","**************OpenCV initialize success*****************");
        }else {
            Log.i("TAG","**************OpenCV initialize failed*****************");
        }

    }

}
