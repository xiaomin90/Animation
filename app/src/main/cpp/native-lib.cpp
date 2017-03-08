#include <jni.h>
#include <string>

extern "C"
jstring
Java_com_minjs_cn_animation_AnimationActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
