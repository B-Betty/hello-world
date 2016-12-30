package com.betty.grayimage;

/**
 * Created by Betty on 2016/12/19.
 */

public class NDKLoader {
    static {
        System.loadLibrary("native-lib");
        System.loadLibrary("opencv_java3");
        System.loadLibrary("GrayImager");
    }
    public  native String stringFromJNI();

    public  native String validate(long matAddrGr, long matAddrRgba);
    //图像处理
    public  native int[] getGraryImage(int[] pixels,int w,int h);
}
