package com.example.cps_pong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class PongSurface extends SurfaceView implements SurfaceHolder.Callback {
    private PongThread pongThread;
    private Racket racket;
    private Ball ball;
    private Paint backgroundPaint;
    private boolean gameStart;
    private final float frameRate = 30;
    final private float practiceAreaSize = 50;
    private float xPhone;
    private float phoneXAcceleration;
    private float phoneZAngularVelocity;
    private float phoneXVelocity;
    private float phoneAngle;
    final float FRICTION_CONSTANT = 0.5F;


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
        this.setOnTouchListener(onTouchListener);
    }

    private OnTouchListener onTouchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                if(racket.contains(event.getX(), event.getY())) {
                    gameStart = true;
                }
            }
            return false;
        }
    };


    public void updatePhoneXAcceleration(float xValue) {
        phoneXAcceleration = xValue * 100.F;
       /* if(phoneXAcceleration * (1.0F/ frameRate) * (1.0F / frameRate) < 0.5) {
            phoneXAcceleration = 0;
        }*/
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        pongThread = new PongThread(this, holder, frameRate);
        pongThread.setRunning(true);
        pongThread.start();
    }

    public void updateZVelocity(float zVelocity) {
        phoneZAngularVelocity = zVelocity;
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
            phoneXAcceleration = 0;
            phoneAngle = 0;
            phoneZAngularVelocity = 0;
            phoneXVelocity = 0;
        }
        else {

            updatePhoneMovement();
            Log.e("PhoneMovement", String.format("Location: %f", xPhone));
            Log.e("PhoneMovement", String.format("Acceleration: %f", phoneXAcceleration));
            racket.setAngularVelocity(phoneZAngularVelocity);
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

        ball.draw(canvas);
        racket.draw(canvas);
    }
    private void updatePhoneMovement(){
        phoneAngle += phoneZAngularVelocity * (1.0F / frameRate);
        phoneXVelocity += phoneXAcceleration * (1.0F / frameRate);
        xPhone +=  0.5F * phoneXAcceleration * (1.0F/frameRate);
        if(xPhone > practiceAreaSize) {
            xPhone = practiceAreaSize;
            phoneXVelocity = 0;
        }
        else if(xPhone < 0) {
            xPhone = 0;
            phoneXVelocity = 0;
        }
        //phoneXVelocity *= Math.pow(FRICTION_CONSTANT, (1.0F / frameRate));
    }



}
