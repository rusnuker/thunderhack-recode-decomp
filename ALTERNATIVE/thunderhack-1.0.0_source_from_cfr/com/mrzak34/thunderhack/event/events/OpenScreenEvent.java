/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_437
 */
package com.mrzak34.thunderhack.event.events;

import com.mrzak34.thunderhack.event.Cancellable;
import net.minecraft.class_437;

public class OpenScreenEvent
extends Cancellable {
    public class_437 screen;

    public OpenScreenEvent(class_437 screen) {
        this.screen = screen;
    }
}

