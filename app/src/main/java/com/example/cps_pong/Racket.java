package com.example.cps_pong;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import android.util.Log;

public class Racket extends PongObject {
    float pixelPerMeter;
    public Racket(float frameRate1) {
        super(frameRate1);
        paint.setColor(Color.argb(255,184,53,74));
    }

    @Override
    public void update(Canvas canvas) {
        this.xVelocity += this.xAcceleration * (1.0F / frameRate);
        //leftSide += xVelocity * (1.0F / frameRate) * pixelPerMeter + 0.5F * xAcceleration * (0.001111) * pixelPerMeter;
        leftSide += xVelocity * (1.0F / frameRate);
        if(leftSide > canvas.getWidth()) {
            leftSide = canvas.getWidth();
            xVelocity = 0;
        }
        else if(leftSide < -width){
            leftSide = -width;
            xVelocity = 0;
        }

    }

    public void updateAcceleration(float acceleration, float dpi){

        this.xAcceleration = acceleration * (1.0F / 2.54F) * dpi;
    }

    @Override
    public void reset(Canvas canvas) {
        pixelPerMeter =canvas.getWidth()/0.5F;
        width = canvas.getWidth() / 3.0F;
        leftSide = (canvas.getWidth() - width) / 2.0F;
        topSide = (float) ((canvas.getHeight() * 0.75));
        xVelocity = 0;
        yVelocity = 0;
    }
    @Override
    public void draw(Canvas canvas) {
        Log.e("RacketMovement", String.format("Location: %f", leftSide));
        Log.e("RacketMovement", String.format("Velocity: %f", xVelocity));
        Log.e("RacketMovement", String.format("Acceleration: %f", xAcceleration));
        if(isVisible) {
            canvas.drawRect(leftSide, topSide, leftSide + width, topSide + height, paint);
        }
    }
    private float leftSide;
    private float topSide;
    private final float height = 50;
    private float width;

    @Override
    public RectF getHitbox() {
        return new RectF(leftSide, topSide, leftSide + width, topSide + height);
    }

    @Override
    public void handleCollisionWithObject(PongObject object) {

    }


}
