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
import android.view.Surface;
import android.view.SurfaceView;
import android.view.View;
import com.test.demos.happybirthday.data.PositionBean;
import com.test.demos.happybirthday.data.DataManager;

public class DrawRecorderView extends SurfaceView {
    PositionBean lastPosition;
    PositionBean nowPosition;
    Picture picture1;
    Picture picture2;
    boolean showPicture2;
    Paint paint;
    Paint linepaint;
    String bgColor = "#ffffff";
    public DrawRecorderView(Context context) {
        super(context);
        init();
    }

    public DrawRecorderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawRecorderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        picture1 = new Picture();
        picture2 = new Picture();
        paint = new Paint();
        paint.setStrokeWidth(8f);
        paint.setTextSize(30);
        paint.setColor(Color.parseColor("#234567"));
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);

        linepaint = new Paint();
        linepaint.setStrokeWidth(8f);
        linepaint.setTextSize(30);
        linepaint.setColor(Color.parseColor("#f0f0f0"));
        linepaint.setAntiAlias(true);
        linepaint.setStyle(Paint.Style.FILL);
    }

    PositionBean positionBean;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(canRecord(event.getX(), event.getY())){
            positionBean = new PositionBean(event.getX(), event.getY());
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                positionBean.setStart(true);
            }
            positionBean.setTime(System.currentTimeMillis());
            DataManager.addPoint(positionBean);
        }
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastPosition = new PositionBean(event.getX(),event.getY());
                nowPosition = new PositionBean(event.getX(),event.getY());
                nowPosition.setTime(System.currentTimeMillis());
                lastPosition.setTime(System.currentTimeMillis());
                break;
            case MotionEvent.ACTION_MOVE:
                if(nowPosition != null) {
                    nowPosition.setX(event.getX());
                    nowPosition.setY(event.getY());
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                lastPosition = null;
                nowPosition = null;
                break;
        }
        return super.onTouchEvent(event);
    }

    public boolean canRecord(float valuex, float valuey){
        if(lastPosition != null) {
            float x = valuex - lastPosition.getX();
            float y = valuey - lastPosition.getY();
            if (Math.sqrt(x * x + y * y) < 5){
                return false;
            }
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.parseColor(bgColor));
//        canvas.drawLine(0,(getMeasuredHeight() - getMeasuredWidth())/2, getMeasuredWidth(),(getMeasuredHeight() - getMeasuredWidth())/2,linepaint) ;
//        canvas.drawLine(0,getMeasuredHeight() - (getMeasuredHeight() - getMeasuredWidth())/2, getMeasuredWidth(),getMeasuredHeight() - (getMeasuredHeight() - getMeasuredWidth())/2,linepaint) ;
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
            lastPosition.setTime(nowPosition.getTime());
        }
    }

    /**
     * 将固定的表盘部分绘制在picture上进行复用
     */
    private void recordPicture(){
        if(!showPicture2){
            Canvas canvas = picture1.beginRecording(getMeasuredWidth(), getMeasuredHeight());
            if(picture2 != null){
                canvas.drawPicture(picture2);
            }
            if(lastPosition != null && nowPosition != null){
                canvas.drawLine(lastPosition.getX(),lastPosition.getY(), nowPosition.getX(), nowPosition.getY(), paint);
            }
            picture1.endRecording();
        }
        if(showPicture2){
            Canvas canvas = picture2.beginRecording(getMeasuredWidth(), getMeasuredHeight());
            if(picture1 != null){
                canvas.drawPicture(picture1);
            }
            if(lastPosition != null && nowPosition != null){
                canvas.drawLine(lastPosition.getX(),lastPosition.getY(), nowPosition.getX(), nowPosition.getY(), paint);
            }
            picture2.endRecording();
        }
    }

    public void setBgColor(boolean black){
        if(black){
            paint.setColor(Color.parseColor("#ffffff"));
            bgColor = "#000000";
        }else {
            paint.setColor(Color.parseColor("#000000"));
            bgColor = "#ffffff";
        }
    }

    public void refreshRecord(){
        picture1 = new Picture();
        picture2 = new Picture();
        showPicture2 = false;
        invalidate();
    }
}
