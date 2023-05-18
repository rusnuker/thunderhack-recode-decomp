/*
 * Decompiled with CFR 0.150.
 */
package com.mrzak34.thunderhack.event.events;

public class ClipAtLedgeEvent {
    private static final ClipAtLedgeEvent INSTANCE = new ClipAtLedgeEvent();
    private boolean set;
    private boolean clip;

    public void reset() {
        this.set = false;
    }

    public void setClip(boolean clip) {
        this.set = true;
        this.clip = clip;
    }

    public boolean isSet() {
        return this.set;
    }

    public boolean isClip() {
        return this.clip;
    }

    public static ClipAtLedgeEvent get() {
        INSTANCE.reset();
        return INSTANCE;
    }
}

