package com.example.cps_pong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class PongSurface extends SurfaceView implements SurfaceHolder.Callback {
    private PongThread pongThread;
    private Racket racket;
    private Ball ball;
    private Paint backgroundPaint;
    private boolean gameStart;
    private final float frameRate = 30;
    final private float practiceAreaSize = 50;
    private float xPhone;
    private float phoneXVelocity;
    private float phoneXAcceleration;

    public PongSurface(Context context){
        super(context);
        this.setFocusable(true);
        this.getHolder().addCallback(this);
        backgroundPaint = new Paint();
        gameStart = true;
        backgroundPaint.setColor(Color.argb(255,23,116,211));
        backgroundPaint.setStyle(Paint.Style.FILL);
        racket = new Racket(frameRate);
        ball = new Ball(frameRate);
    }

    public void updatePhoneXAcceleration(float xValue) {
        phoneXAcceleration = xValue * 100.F;
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        pongThread = new PongThread(this, holder, frameRate);
        pongThread.setRunning(true);
        pongThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        while(true) {
            try {
                pongThread.setRunning(false);
                pongThread.join();
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(Canvas canvas) {
        if(gameStart) {
            racket.reset(canvas);
            ball.reset(canvas);
            gameStart = false;
            xPhone = practiceAreaSize / 2.0F;
            phoneXVelocity = 0;
            phoneXAcceleration = 0;
        }
        else {

            updatePhoneMovement();
            Log.e("PhoneMovement", String.format("Location: %f", xPhone));
            Log.e("PhoneMovement", String.format("Velocity: %f", phoneXVelocity));
            Log.e("PhoneMovement", String.format("Acceleration: %f", phoneXAcceleration));
            racket.updateAcceleration(phoneXAcceleration, getResources().getDisplayMetrics().densityDpi);
            CollisionHandler.twoObjectCollision(ball, racket);
            ball.handleCollisionWithWall(canvas);
            racket.update(canvas);
            ball.update(canvas);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawPaint(backgroundPaint);

        racket.draw(canvas);
        ball.draw(canvas);
    }
    private void updatePhoneMovement(){
        phoneXVelocity += phoneXAcceleration * (1.0F / frameRate);
        xPhone += phoneXVelocity * (1.0F / frameRate);
        if(phoneXVelocity > 0) {
            xPhone = Math.min(practiceAreaSize, xPhone);
        }
        else {
            xPhone = Math.max(0, xPhone);
        }
    }

    private boolean isPhoneInArea() {
        float tempVelocity = phoneXVelocity + phoneXAcceleration * (1.0F / frameRate);
        float tempXPhone = xPhone + tempVelocity * (1.0F / frameRate);
        return tempXPhone >= 0 && tempXPhone <= practiceAreaSize;
    }


}
