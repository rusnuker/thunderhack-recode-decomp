/*
 * Decompiled with CFR 0.150.
 */
package com.mrzak34.thunderhack.event.events;

import com.mrzak34.thunderhack.event.Cancellable;

public class KeyEvent
extends Cancellable {
    public int key;
    public int action;

    public KeyEvent(int key, int action) {
        this.key = key;
        this.action = action;
    }
}

