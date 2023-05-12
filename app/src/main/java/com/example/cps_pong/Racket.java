package com.example.cps_pong;

import static java.lang.Math.abs;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import android.widget.Toast;

public class Racket extends PongObject {
    float pixelPerMeter;
    public Racket() {
        paint.setColor(Color.BLUE);
    }

    @Override
    public void update(Canvas canvas) {
        this.xVelocity+=this.xAcceleration*(0.03333);
        leftSide += xVelocity*(0.03333)*pixelPerMeter+0.5F*xAcceleration*(0.001111)*pixelPerMeter;
    }

    public void updateVelocity(float acceleration){
        this.xAcceleration=acceleration;
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

    public void move(){

    }


}
