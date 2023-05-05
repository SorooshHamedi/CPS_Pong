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
        cx = canvas.getWidth() / 2.0F;
        cy += 10;//temp
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(cx, cy, radius, paint);
    }
}
