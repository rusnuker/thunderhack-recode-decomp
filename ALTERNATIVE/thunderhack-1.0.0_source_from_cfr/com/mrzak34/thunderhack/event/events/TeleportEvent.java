/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_3218
 */
package com.mrzak34.thunderhack.event.events;

import com.mrzak34.thunderhack.event.Cancellable;
import net.minecraft.class_3218;

public class TeleportEvent
extends Cancellable {
    class_3218 destination;

    public TeleportEvent(class_3218 destination) {
        this.destination = destination;
    }
}

