package com.example.cps_pong;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class PongObject {

    protected Paint paint;
    protected float xAcceleration = 0;
    protected float yAcceleration = 0;
    protected float xVelocity = 0;
    protected float yVelocity = 0;
    public PongObject() {
        paint = new Paint();
    }

    public abstract void update(Canvas canvas);
    public abstract void draw(Canvas canvas);

}
