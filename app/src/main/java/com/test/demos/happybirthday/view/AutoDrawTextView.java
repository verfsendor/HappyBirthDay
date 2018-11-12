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
    /**
     * 比例和布局排放相关
     */
    private final int PER_WIDTH = 200;
    private final int PER_HEIGHT = 200;
    PositionBean lastPosition;
    PositionBean nowPosition;
    Picture picture1;
    Picture picture2;
    private float startX = 0;
    private float startY = 0;
    boolean showPicture2;
    Paint paint;
    private long i = 0;

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
        Log.v("verf","时间点 " + System.currentTimeMillis());
        Log.v("verf","recordPicture");
        if(!showPicture2){
            Canvas canvas = picture1.beginRecording(getMeasuredWidth(), getMeasuredHeight());
            if(picture2 != null){
                canvas.drawPicture(picture2);
            }
            if(lastPosition != null && nowPosition != null && !nowPosition.isStart()){
                if(nowPosition.getStartx() != -1){
                    canvas.drawLine(nowPosition.getStartx() + lastPosition.getX(),nowPosition.getY() + lastPosition.getY(), nowPosition.getStartx() + nowPosition.getX(), nowPosition.getY() + nowPosition.getY(), paint);
                }else {
                    canvas.drawLine(startX + lastPosition.getX(),startY + lastPosition.getY(), startX + nowPosition.getX(), startY + nowPosition.getY(), paint);
                }
            }

            picture1.endRecording();
        }
        if(showPicture2){
            Canvas canvas = picture2.beginRecording(getMeasuredWidth(), getMeasuredHeight());
            if(picture1 != null){
                canvas.drawPicture(picture1);
            }
            if(lastPosition != null && nowPosition != null && !nowPosition.isStart()){
                if(nowPosition.getStartx() != -1){
                    canvas.drawLine(nowPosition.getStartx() +lastPosition.getX(),nowPosition.getY() + lastPosition.getY(), nowPosition.getStartx() + nowPosition.getX(), nowPosition.getY() + nowPosition.getY(), paint);
                }else {
                    canvas.drawLine(startX +lastPosition.getX(),startY + lastPosition.getY(), startX + nowPosition.getX(), startY + nowPosition.getY(), paint);
                }
            }
            picture2.endRecording();
        }
    }


    public void showPoint(PositionBean positionBean){

        lastPosition = nowPosition;
        nowPosition = positionBean;
        Log.v("verf","view画点2 " + positionBean.getX() + " " + positionBean.getY());
        if(nowPosition.isCharacterStart() && lastPosition != null && lastPosition.getStartx() == -1){
            startX = startX + DataManager.windowWidth * lastPosition.getScaluex()/100;
            if((startX + DataManager.windowWidth * nowPosition.getScaluex()/100) > DataManager.windowWidth){
                startY = startY + DataManager.windowHeight * lastPosition.getScaluey()/100 - 50;
                startX = 0;
            }
        }

        postInvalidate();
    }

    public void setPaint(int alpha, String color){
       paint.setAlpha(alpha);
       paint.setColor(Color.parseColor(color));
    }
    public void resetPaint(int alpha, int color){
        paint.setAlpha(100);
        paint.setColor(Color.parseColor("#000000"));
    }
}
