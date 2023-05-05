package com.example.cps_pong;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class Racket {

    public Racket() {
        paint = new Paint();
        paint.setColor(Color.BLUE);
    }

    public void update(Canvas canvas) {
        width = canvas.getWidth() / 3.0F;
        leftSide = (canvas.getWidth() - width) / 2.0F;

        topSide = (float) ((canvas.getHeight() * 0.75));
    }

    public void Draw(Canvas canvas) {
        canvas.drawRect(leftSide, topSide, leftSide + width, topSide + height, paint);
    }
    private float leftSide;
    private float topSide;
    private final float height = 30;
    private float width;
    private Paint paint;

}
