package com.example.cps_pong;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;

public class Racket extends PongObject {

    public Racket() {
        paint.setColor(Color.BLUE);
    }

    @Override
    public void update(Canvas canvas) {
        leftSide += xVelocity;
        topSide += yVelocity;
    }

    @Override
    public void reset(Canvas canvas) {
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
}
