package com.example.cps_pong;

import android.graphics.Canvas;
import android.graphics.Color;

public class Racket extends PongObject {

    public Racket() {
        paint.setColor(Color.BLUE);
    }

    @Override
    public void update(Canvas canvas) {
        width = canvas.getWidth() / 3.0F;
        leftSide = (canvas.getWidth() - width) / 2.0F;

        topSide = (float) ((canvas.getHeight() * 0.75));
    }
    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(leftSide, topSide, leftSide + width, topSide + height, paint);
    }
    private float leftSide;
    private float topSide;
    private final float height = 50;
    private float width;

}
