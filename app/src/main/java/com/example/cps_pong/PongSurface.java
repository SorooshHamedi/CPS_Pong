package com.example.cps_pong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class PongSurface extends SurfaceView implements SurfaceHolder.Callback {
    private PongThread pongThread;
    final private int frameRate = 120;
    private Racket racket;
    private Ball ball;
    private Paint backgroundPaint;
    private boolean gameStart;
    private float phoneXAcceleration;
    private float phoneXCoordination;
    private float phoneXVelocity;
    final private float trainingAreaLength = 50;
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

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        pongThread = new PongThread(this, frameRate);
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

    public void pause() {
        while(true) {
            try {
                pongThread.setRunning(false);
                pongThread.join();
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void resume() {
        pongThread = new PongThread(this, frameRate);
        pongThread.setRunning(true);
        pongThread.start();
    }

    public void update(Canvas canvas) {
        if(gameStart) {
            racket.reset(canvas);
            ball.reset(canvas);
            gameStart = false;
            phoneXVelocity = 0;
            phoneXCoordination = trainingAreaLength / 2;
        }
        else {
            CollisionHandler.twoObjectCollision(ball, racket);
            ball.handleCollisionWithWall(canvas);
            racket.handleCollisionWithWall(canvas);
            Log.e("PhoneMovement", String.format("Acceleration: %f", phoneXAcceleration));
            Log.e("PhoneMovement", String.format("Velocity: %f", phoneXVelocity));
            Log.e("PhoneMovement", String.format("place: %f", phoneXCoordination));
            if(phoneXCoordination + phoneXVelocity >= 0 && phoneXCoordination + phoneXVelocity <= trainingAreaLength) {
                phoneXCoordination += phoneXVelocity;
                racket.update(canvas, (phoneXVelocity) / trainingAreaLength);
            }
            else {
                racket.update(canvas);
            }


            ball.update(canvas);
        }
    }

    public void updatePhoneXAcceleration(float newXAcceleration) {
        phoneXAcceleration = newXAcceleration * 100F;
        phoneXVelocity += phoneXAcceleration;

    }

    private float getPhoneXDistanceInFrame() {
        return 0.5F * phoneXAcceleration * (1.0F / 30.F);
    }
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawPaint(backgroundPaint);

        racket.draw(canvas);
        ball.draw(canvas);
    }


}
