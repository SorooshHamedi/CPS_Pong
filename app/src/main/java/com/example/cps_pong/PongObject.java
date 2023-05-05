package com.example.cps_pong;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class PongObject {

    protected Paint paint;
    public PongObject() {
        paint = new Paint();
    }

    public abstract void update(Canvas canvas);
    public abstract void draw(Canvas canvas);

}
