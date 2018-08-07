package com.example.zhangzhixin.test.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;



public class MyCircleImageView extends android.support.v7.widget.AppCompatImageView {
    private Matrix mShaderMatrix = new Matrix();

    public MyCircleImageView(Context context) {
        super(context);
        init();

    }

    public MyCircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public MyCircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public void init() {
        this.setScaleType(ScaleType.CENTER_CROP);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        int radius;
        int drawableWidth, drawableHeight;

        drawableHeight = getHeight();
        drawableWidth = getWidth();

        Drawable drawable = this.getDrawable();
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        Bitmap bitmap = bitmapDrawable.getBitmap();

        Paint paint = new Paint();
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        BitmapShader shader = new BitmapShader(bitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);

        float scaleX, scaleY;
        scaleX = (float) drawableWidth/ (float) width;
        scaleY = (float) drawableHeight/ (float) height;
        mShaderMatrix.set(null);
        mShaderMatrix.setScale(scaleX, scaleY);
        if(drawableWidth>drawableHeight){
            radius = drawableHeight/2;
        }else{
            radius = drawableWidth/2;
        }
        shader.setLocalMatrix(mShaderMatrix);
        paint.setShader(shader);
        paint.setAntiAlias(true);

        canvas.drawCircle(drawableWidth/2, drawableHeight/2, radius, paint);

    }
}
