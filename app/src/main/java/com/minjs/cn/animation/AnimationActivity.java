package com.minjs.cn.animation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AnimationActivity extends AppCompatActivity  implements View.OnClickListener {

    //在代码中定义 动画实例对象
    private Animation myAnimation_Alpha;
    private Animation myAnimation_Scale;
    private Animation myAnimation_Translate;
    private Animation myAnimation_Rotate;

    private ImageView mImgePic;
    private Button mAlphaBtn;
    private Button mScaleBtn;
    private Button mTranslateBtn;
    private Button mRotateBtn;



    //使用AnimationUtils类的静态方法loadAnimation()来加载XML中的动画XML文件
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());
        tv.setVisibility(View.GONE);
        mImgePic =  (ImageView) this.findViewById(R.id.imageview);
        mAlphaBtn = (Button) this.findViewById(R.id.alphabtn);
        mTranslateBtn = (Button) this.findViewById(R.id.transbtn);
        mRotateBtn = (Button) this.findViewById(R.id.rotatebtn);
        mScaleBtn = (Button) this.findViewById(R.id.scalebtn);

        mAlphaBtn.setOnClickListener(this);
        mTranslateBtn.setOnClickListener(this);
        mRotateBtn.setOnClickListener(this);
        mScaleBtn.setOnClickListener(this);

        // 透明动画
        initAlphaAnimation();
        // 伸缩动画
        initScaleAnimation();
        // 移动动画
        initTranslateAnimation();
        // 旋转动画
        initRotateAnimation();
    }

    /**
     * 透明动画效果
     */
    public void initAlphaAnimation() {
        //0.1 fromAlpha为 动画开始时候透明度 |  1.0 toAlpha为 动画结束时候透明度
        myAnimation_Alpha = new AlphaAnimation(0.1f, 1.0f);
        //设置时间持续时间为 5000毫秒
        myAnimation_Alpha.setDuration(5000);
    }

    /**
     * 伸缩动画
     */
    public void initScaleAnimation() {
        //第一个参数fromX为动画起始时 X坐标上的伸缩尺寸 | 第二个参数toX为动画结束时 X坐标上的伸缩尺寸 | 第三个参数fromY为动画起始时Y坐标上的伸缩尺寸 | 第四个参数toY为动画结束时Y坐标上的伸缩尺寸
        /*说明:
                    以上四种属性值
                    0.0表示收缩到没有
                    1.0表示正常无伸缩
                    值小于1.0表示收缩
                    值大于1.0表示放大
        */
        //第五个参数pivotXType为动画在X轴相对于物件位置类型 | 第六个参数pivotXValue为动画相对于物件的X坐标的开始位置 | 第七个参数pivotXType为动画在Y轴相对于物件位置类型 | 第八个参数pivotYValue为动画相对于物件的Y坐标的开始位置
        myAnimation_Scale = new ScaleAnimation(0.0f, 1.4f, 0.0f, 1.4f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        //设置时间持续时间为 700毫秒
        myAnimation_Scale.setDuration(700);
    }

    /**
     * 设置移动动画
     */
    public void initTranslateAnimation() {
        //第一个参数fromXDelta为动画起始时 X坐标上的移动位置 | 第二个参数toXDelta为动画结束时 X坐标上的移动位置 | 第三个参数fromYDelta为动画起始时Y坐标上的移动位置 | 第四个参数toYDelta为动画结束时Y坐标上的移动位置
        myAnimation_Translate = new TranslateAnimation(30.0f, -80.0f, 30.0f, 300.0f);
        myAnimation_Translate.setDuration(2000);
    }

    /**
     * 设置旋转动画
     */
    public void initRotateAnimation() {
        //第一个参数fromDegrees为动画起始时的旋转角度 | 第二个参数toDegrees为动画旋转到的角度 |第三个参数pivotXType为动画在X轴相对于物件位置类型
        //第四个参数pivotXValue为动画相对于物件的X坐标的开始位置 | 第五个参数pivotXType为动画在Y轴相对于物件位置类型 | 第六个参数pivotYValue为动画相对于物件的Y坐标的开始位置
        myAnimation_Rotate = new RotateAnimation(0.0f, +350.0f,Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        myAnimation_Rotate.setDuration(3000);
    }


    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.alphabtn:
              myAnimation_Alpha = AnimationUtils.loadAnimation(this,R.anim.alpha_anim);
                mImgePic.startAnimation(myAnimation_Alpha);
                break;
            case R.id.rotatebtn:
              myAnimation_Rotate = AnimationUtils.loadAnimation(this,R.anim.rotate_anim);
                mImgePic.startAnimation(myAnimation_Rotate);
                break;
            case R.id.scalebtn:
                myAnimation_Scale = AnimationUtils.loadAnimation(this,R.anim.scale_anim);
                mImgePic.startAnimation(myAnimation_Scale);
                break;
            case R.id.transbtn:
//                myAnimation_Translate = AnimationUtils.loadAnimation(this,R.anim.translate_anim);
                myAnimation_Translate.setRepeatCount(Animation.INFINITE);
                mImgePic.startAnimation(myAnimation_Translate);
                break;
            default:
                mImgePic.clearAnimation();
                break;
        }
    }
}
