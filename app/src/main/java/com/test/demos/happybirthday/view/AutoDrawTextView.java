package com.test.demos.happybirthday.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.test.demos.happybirthday.data.DataManager;
import com.test.demos.happybirthday.data.PositionBean;

public class AutoDrawTextView extends View {
    PositionBean lastPosition;
    PositionBean nowPosition;
    Picture picture;
    Picture picture1;
    Picture picture2;
    int i =1;
    boolean showPicture2;
    Paint paint;
    public AutoDrawTextView(Context context) {
        super(context);
        init();
    }

    public AutoDrawTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AutoDrawTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        picture1 = new Picture();
        picture2 = new Picture();
        paint = new Paint();
        paint.setStrokeWidth(5f);
        paint.setTextSize(30);
        paint.setColor(Color.parseColor("#234567"));
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        Log.v("verf","DrawTextView onTouchEvent " + event.getAction());
//        switch (event.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                lastPosition = new PositionBean(event.getX(),event.getY());
//                nowPosition = new PositionBean(event.getX(),event.getY());
//                break;
//            case MotionEvent.ACTION_MOVE:
//                if(nowPosition != null) {
//                    nowPosition.setX(event.getX());
//                    nowPosition.setY(event.getY());
//                }
//                invalidate();
//                break;
//            case MotionEvent.ACTION_UP:
//                lastPosition = null;
//                nowPosition = null;
//                break;
//        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.v("verf","invalidate onDrawing");
        recordPicture();
        if(showPicture2){
            canvas.drawPicture(picture2);
        }else {
            canvas.drawPicture(picture1);
        }
        showPicture2 = !showPicture2;
        if(lastPosition != null && nowPosition != null) {
            lastPosition.setX(nowPosition.getX());
            lastPosition.setY(nowPosition.getY());
        }
        DataManager.wait = false;
    }

    /**
     * 将固定的表盘部分绘制在picture上进行复用
     */
    private void recordPicture(){
        Log.v("verf","recordPicture");
        if(!showPicture2){
            Canvas canvas = picture1.beginRecording(getMeasuredWidth(), getMeasuredHeight());
            if(picture2 != null){
                canvas.drawPicture(picture2);
            }
            if(lastPosition != null && nowPosition != null && !nowPosition.isStart()){
                canvas.drawLine(lastPosition.getX(),lastPosition.getY(), nowPosition.getX(), nowPosition.getY(), paint);
            }
            picture1.endRecording();
        }
        if(showPicture2){
            Canvas canvas = picture2.beginRecording(getMeasuredWidth(), getMeasuredHeight());
            if(picture1 != null){
                canvas.drawPicture(picture1);
            }
            if(lastPosition != null && nowPosition != null && !nowPosition.isStart()){
                canvas.drawLine(lastPosition.getX(),lastPosition.getY(), nowPosition.getX(), nowPosition.getY(), paint);
            }
            picture2.endRecording();
        }
    }


    public void showPoint(final PositionBean positionBean){
        Log.v("verf","postInvaliData1 " + positionBean.getX() + " " + positionBean.getY());
        lastPosition = nowPosition;
        nowPosition = positionBean;
        Log.v("verf","postInvaliData2 " + positionBean.getX() + " " + positionBean.getY());
//        invalidate();
        postInvalidate();
    }

}
