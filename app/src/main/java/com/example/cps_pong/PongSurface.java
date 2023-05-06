package com.example.cps_pong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class PongSurface extends SurfaceView implements SurfaceHolder.Callback {
    private PongThread pongThread;
    private Racket racket;
    private Ball ball;
    private Paint backgroundPaint;
    private boolean gameStart;
    public PongSurface(Context context){
        super(context);
        this.setFocusable(true);
        this.getHolder().addCallback(this);

        backgroundPaint = new Paint();
        gameStart = true;
        backgroundPaint.setColor(Color.BLACK);
        backgroundPaint.setStyle(Paint.Style.FILL);
        racket = new Racket();
        ball = new Ball();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        pongThread = new PongThread(this, holder);
        pongThread.setRunning(true);
        pongThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while(retry) {
            try {
                pongThread.setRunning(false);
                pongThread.join();
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
            retry = true;
        }
    }

    public void update(Canvas canvas) {
        if(gameStart) {
            racket.reset(canvas);
            ball.reset(canvas);
            gameStart = false;
        }
        else {
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


}
