/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_4587
 */
package com.mrzak34.thunderhack.event.events;

import com.mrzak34.thunderhack.event.Cancellable;
import net.minecraft.class_4587;

public class RenderOverlayEvent
extends Cancellable {
    protected float partialTicks;
    protected class_4587 matrices;

    public RenderOverlayEvent(class_4587 matrices, float partialTicks) {
        this.partialTicks = partialTicks;
        this.matrices = matrices;
    }

    public float getPartialTicks() {
        return this.partialTicks;
    }

    public class_4587 getMatrices() {
        return this.matrices;
    }
}

