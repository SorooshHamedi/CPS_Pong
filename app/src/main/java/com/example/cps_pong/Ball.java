package com.example.cps_pong;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;

public class Ball extends PongObject{
    private float cx;
    private float cy;
    private final float radius = 30;

    public Ball(float frameRate1) {
        super(frameRate1);
        paint.setColor(Color.WHITE);
        cy = radius;
        cx =
        xVelocity = 5;
    }

    @Override
    public void update(Canvas canvas) {
        yVelocity += yAcceleration;
        xVelocity += xAcceleration;
        cx += xVelocity;
        cy += yVelocity;

    }

    @Override
    public void reset(Canvas canvas) {
        cx = canvas.getWidth() / 2.0F;
        cy = radius;
        yAcceleration = calculateGravity(canvas);
        xAcceleration = 0;
        yVelocity = 0;
        xVelocity = 0;
    }

    @Override
    public void draw(Canvas canvas) {
        if(isVisible){
            canvas.drawCircle(cx, cy, radius, paint);
        }
    }

    private float calculateGravity(Canvas canvas) {
        float factor = (2.0F/3.0F) / (frameRate * frameRate);
        return factor * (float)(canvas.getHeight());
    }


    @Override
    public RectF getHitbox() {
        return new RectF(cx - (radius/2), cy - (radius/2), cx + (radius/2), cy + (radius/2));
    }

    @Override
    public void handleCollisionWithObject(PongObject a) {
        //flat racket collision
        yVelocity *= -1.0F;
        //TODO collision with racket at an angle
    }

    public void handleCollisionWithWall(Canvas canvas) {
        RectF hitbox = this.getHitbox();
        boolean collides = false;
        if(hitbox.left <= 0) {
            xVelocity *= -1.0F;
            setVisible(false);
            collides = true;
        }
        if(hitbox.top <= 0) {
            yVelocity *= -1.0F;
            setVisible(false);
            collides = true;
        }
        if(hitbox.right >= canvas.getWidth()) {
            xVelocity *= -1.0F;
            setVisible(false);
            collides = true;
        }
        if(hitbox.bottom >= canvas.getHeight()) {
            yVelocity *= -1.0F;
            setVisible(false);
            collides = true;
        }

        if(!collides) {
            setVisible(true);
        }
    }
}
