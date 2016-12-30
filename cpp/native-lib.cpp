#include <jni.h>
#include <string>
#include <opencv2/highgui/highgui.hpp>
#include <opencv2/imgcodecs/imgcodecs.hpp>

extern "C"
jstring
Java_com_betty_grayimage_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";

    return env->NewStringUTF(hello.c_str());
}
