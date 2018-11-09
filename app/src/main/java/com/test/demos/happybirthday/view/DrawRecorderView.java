package com.test.demos.happybirthday.view;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.test.demos.happybirthday.data.PositionBean;
import com.test.demos.happybirthday.data.DataManager;

public class DrawRecorderView extends View {
    PositionBean lastPosition;
    PositionBean nowPosition;
    Picture picture;
    Picture picture1;
    Picture picture2;
    int i =1;
    boolean showPicture2;
    Paint paint;
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
        paint.setStrokeWidth(5f);
        paint.setTextSize(30);
        paint.setColor(Color.parseColor("#234567"));
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
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
            if (Math.sqrt(x * x + y * y) < 2){
                return false;
            }
        }
        return true;
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

    public void refreshRecord(){
        picture1 = new Picture();
        picture2 = new Picture();
        showPicture2 = false;
        invalidate();
    }
}
