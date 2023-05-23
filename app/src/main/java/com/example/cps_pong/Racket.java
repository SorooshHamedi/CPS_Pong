package com.example.cps_pong;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import android.util.Log;

public class Racket extends PongObject {
    private float angularVelocity;
    private float angle;
    final float FRICTION_CONSTANT = 0.5F;
    public Racket(float frameRate1) {
        super(frameRate1);
        paint.setColor(Color.argb(255,184,53,74));
    }

    @Override
    public void update(Canvas canvas) {
        angle += angularVelocity * (1.0F / frameRate);
        xVelocity += xAcceleration * (1.0F / frameRate);
        leftSide += xVelocity * (1.0F /frameRate);
        if(leftSide > canvas.getWidth()) {
            leftSide = canvas.getWidth();
            xVelocity = 0;
        }
        else if(leftSide < -width){
            leftSide = -width;
            xVelocity = 0;
        }
        //xVelocity *= Math.pow(FRICTION_CONSTANT, (1.0 / frameRate));
    }

    public void updateAcceleration(float acceleration, float dpi){
        this.xAcceleration = acceleration * (1.0F / 2.54F) * dpi;
    }

    @Override
    public void reset(Canvas canvas) {
        width = canvas.getWidth() / 3.0F;
        leftSide = (canvas.getWidth() - width) / 2.0F;
        topSide = (float) ((canvas.getHeight() * 0.75));
        xVelocity = 0;
        yVelocity = 0;
        xAcceleration = 0;
        angle = 0;
    }
    @Override
    public void draw(Canvas canvas) {
        Log.e("RacketMovement", String.format("Location: %f", leftSide));
        Log.e("RacketMovement", String.format("Velocity: %f", xVelocity));
        Log.e("RacketMovement", String.format("Acceleration: %f", xAcceleration));
        if(isVisible) {
            float angleInDegrees = angle * 180.F / (float)(Math.PI);
            canvas.rotate(angleInDegrees, getHitbox().centerX(), getHitbox().centerY());
            canvas.drawRect(leftSide, topSide, leftSide + width, topSide + height, paint);
            canvas.restore();
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

    public void setAngularVelocity(float angularVelocity1) {
        angularVelocity = angularVelocity1;
    }

    public float getAngle() {
        return angle;
    }
}
