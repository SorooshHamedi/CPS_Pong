package com.example.cps_pong;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import android.util.Log;
import android.widget.Toast;

public class Racket extends PongObject {

    public Racket(int frameRate1) {
        super(frameRate1);
        paint.setColor(Color.argb(255,184,53,74));
    }


    @Override
    public void update(Canvas canvas) {
    }

    /**
     * updates the Racket's position according to the phone's horizontal movement
     * @param canvas
     * @param horizontalMovementFactor
     * Phone's movement as a ratio.
     *
     * The formula: horizontal movement in cm / practiceArea's width in cm
     */
    public void update(Canvas canvas, float horizontalMovementFactor) {
        leftSide += horizontalMovementFactor * canvas.getWidth();
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

    public void updateXAcceleration(float newXAcceleration) {
        xAcceleration = newXAcceleration;
    }

    @Override
    public void handleCollisionWithWall(Canvas canvas) {
        RectF hitbox = getHitbox();
        boolean collides = false;
        if(hitbox.left <= 0) {
            setVisible(false);
            collides = true;
        }
        if(hitbox.right >= canvas.getWidth()) {
            setVisible(false);
            collides = true;
        }

        if(!collides) {
            setVisible(true);
        }
    }
}
