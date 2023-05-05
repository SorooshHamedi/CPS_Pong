package com.example.cps_pong;

import android.graphics.Canvas;
import android.view.SurfaceHolder;
public class PongThread extends Thread {
    private final float frameRate = 30;
    private final float frameTime = 1000 / frameRate; //duration of each frame in ms

    private PongSurface gameSurface;
    private SurfaceHolder surfaceHolder;
    private boolean running;

    public PongThread(PongSurface gameSurface1, SurfaceHolder surfaceHolder1) {
        gameSurface = gameSurface1;
        surfaceHolder = surfaceHolder1;
    }

    @Override
    public void run() {
        float startTime = System.currentTimeMillis();
        while(running) {
            Canvas canvas = null;
            try {
                canvas = surfaceHolder.lockCanvas();

                 synchronized (canvas) {
                     gameSurface.update(canvas);
                     gameSurface.draw(canvas);
                 }
            } catch(Exception e) {

            } finally {
                if(canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }

            float endTime = System.currentTimeMillis();
            long deltaTime = (long) (frameTime - (endTime - startTime));
            try {
                Thread.sleep(deltaTime);
            }  catch (InterruptedException e) {

            }
            startTime = System.currentTimeMillis();
        }

    }

    public void setRunning(boolean running1) {
        running = running1;
    }


}
