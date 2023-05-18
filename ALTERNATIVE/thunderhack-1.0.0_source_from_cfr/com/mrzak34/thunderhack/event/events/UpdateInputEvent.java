/*
 * Decompiled with CFR 0.150.
 */
package com.mrzak34.thunderhack.event.events;

import com.mrzak34.thunderhack.event.Cancellable;

public class UpdateInputEvent
extends Cancellable {
    float sidewaysSpeed;
    float forwardSpeed;
    boolean jumping;
    boolean sneaking;

    public static class Post
    extends UpdateInputEvent {
        public Post(float sidewaysSpeed, float forwardSpeed, boolean jumping, boolean sneaking) {
            this.sidewaysSpeed = sidewaysSpeed;
            this.forwardSpeed = forwardSpeed;
            this.jumping = jumping;
            this.sneaking = sneaking;
        }
    }

    public static class Pre
    extends UpdateInputEvent {
        public Pre(float sidewaysSpeed, float forwardSpeed, boolean jumping, boolean sneaking) {
            this.sidewaysSpeed = sidewaysSpeed;
            this.forwardSpeed = forwardSpeed;
            this.jumping = jumping;
            this.sneaking = sneaking;
        }
    }
}

