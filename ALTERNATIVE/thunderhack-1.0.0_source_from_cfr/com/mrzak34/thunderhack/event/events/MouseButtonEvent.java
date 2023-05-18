/*
 * Decompiled with CFR 0.150.
 */
package com.mrzak34.thunderhack.event.events;

import com.mrzak34.thunderhack.event.Cancellable;

public class MouseButtonEvent
extends Cancellable {
    int button;
    int action;

    public MouseButtonEvent(int button, int action) {
        this.button = button;
        this.action = action;
    }

    public int getButton() {
        return this.button;
    }

    public int getAction() {
        return this.action;
    }
}

