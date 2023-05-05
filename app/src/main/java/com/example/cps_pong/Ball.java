package com.example.cps_pong;

import android.graphics.Canvas;
import android.graphics.Color;

public class Ball extends PongObject{
    private float cx;
    private float cy;
    private final float radius = 30;

    public Ball() {
        paint.setColor(Color.WHITE);
        cy = radius;
    }

    @Override
    public void update(Canvas canvas) {
        updateGravity(canvas);

        cx = canvas.getWidth() / 2.0F;
        cy += yVelocity;
    }


    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(cx, cy, radius, paint);
    }

    private float calculateGravity(Canvas canvas) {
        float factor = (2.0F/3.0F) / (3600.F);
        return factor * (float)(canvas.getHeight());
    }

    private void updateGravity(Canvas canvas) {
        yAcceleration = calculateGravity(canvas);
        yVelocity += yAcceleration;
    }
}
