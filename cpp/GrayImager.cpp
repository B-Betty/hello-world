//
// Created by Betty on 2016/12/19.
//
#include <cwchar>
#include "jni.h"
//#include "GrayImager.h"
#include <opencv2/core/core.hpp>
#include <string>
extern "C"{
JNIEXPORT jintArray JNICALL
Java_com_betty_grayimage_NDKLoader_getGraryImage(JNIEnv *env, jclass type, jintArray pixels_,
                                                 jint w, jint h) {
    jint *pixels = env->GetIntArrayElements(pixels_, NULL);

    // TODO
    if(pixels==NULL){
        return NULL;
    }
    cv::Mat imgData(h, w, CV_8UC4, pixels);
    uchar *ptr = imgData.ptr(0);
    for (int i = 0; i < w * h; i++) {
        int grayScale = (int) (ptr[4 * i + 2] * 0.299 + ptr[4 * i + 1] * 0.587
                               + ptr[4 * i + 0] * 0.114);
        ptr[4 * i + 1] = (uchar) grayScale;
        ptr[4 * i + 2] = (uchar) grayScale;
        ptr[4 * i + 0] = (uchar) grayScale;
    }

    int size = w * h;
    jintArray result = env->NewIntArray(size);
    env->SetIntArrayRegion(result, 0, size, pixels);
    env->ReleaseIntArrayElements(pixels_, pixels, 0);
    return result;
}

JNIEXPORT jstring JNICALL
Java_com_betty_grayimage_NDKLoader_stringFromJNI(JNIEnv *env, jclass type) {

    // TODO
    std::string hello = "opencv图像处理";

    return env->NewStringUTF(hello.c_str());
}

JNIEXPORT jstring JNICALL
Java_com_betty_grayimage_NDKLoader_validate(JNIEnv *env, jclass type, jlong matAddrGr,
                                            jlong matAddrRgba) {

    // TODO
    std::string hello2 = "Hello from validate";
    return env->NewStringUTF(hello2.c_str());
}
}
