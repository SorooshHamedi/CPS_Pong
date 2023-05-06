package com.example.cps_pong;

import android.graphics.Canvas;
import android.graphics.RectF;

public class CollisionHandler {

    public static void twoObjectCollision(PongObject a, PongObject b) {
        RectF hitboxA = a.getHitbox();
        RectF hitboxB = b.getHitbox();

        if(hitboxA.intersect(hitboxB)) {
            a.setVisible(false);
            b.setVisible(false);
            a.handleCollisionWithObject(b);
            b.handleCollisionWithObject(a);
        }
        else if(a.isInvisible()) {
            a.setVisible(true);
        }
        else if(b.isInvisible()) {
            b.setVisible(true);
        }
    }
}
