package com.example.cps_pong;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public abstract class PongObject {

    protected Paint paint;
    protected boolean isVisible = true;
    protected float xAcceleration = 0;
    protected float yAcceleration = 0;
    
    protected float xVelocity = 0;
    protected float yVelocity = 0;
    public PongObject() {
        paint = new Paint();
    }

    public abstract void update(Canvas canvas);
    public abstract void draw(Canvas canvas);
    public abstract void reset(Canvas canvas);
    public abstract RectF getHitbox();
    public abstract void handleCollisionWithObject(PongObject object);

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public boolean isInvisible() {
        return isVisible == false;
    }

}
