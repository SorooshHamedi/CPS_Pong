package com.example.cps_pong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class MyCustomView extends View {
    public MyCustomView(Context context){
        super(context);
        racketPaint = new Paint();
        ballPaint = new Paint();
        backgroundPaint = new Paint();
    }

    private Paint racketPaint;
    private Paint ballPaint;
    private Paint backgroundPaint;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setColors();
        canvas.drawPaint(backgroundPaint);
        initialRacketDraw(canvas);
    }

    private void setColors() {
        racketPaint.setColor(Color.WHITE);
        backgroundPaint.setColor(Color.BLACK);
        backgroundPaint.setStyle(Paint.Style.FILL);


    }

    private void initialRacketDraw(Canvas canvas) {
        canvas.drawRect(
                (float) ((canvas.getWidth() - getRacketWidth(canvas))/2),
                (float) (canvas.getHeight() * (0.75)),
                (float)(canvas.getWidth() + getRacketWidth(canvas))/2,
                (float) (canvas.getHeight() * (0.75) + 30),
                racketPaint
        );
    }

    private int getRacketWidth(Canvas canvas) {
        return canvas.getWidth() / 3;
    }

}
