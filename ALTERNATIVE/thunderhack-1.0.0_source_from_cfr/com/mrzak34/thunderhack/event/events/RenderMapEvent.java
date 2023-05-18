/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_22
 *  net.minecraft.class_4587
 *  net.minecraft.class_4597
 */
package com.mrzak34.thunderhack.event.events;

import com.mrzak34.thunderhack.event.Cancellable;
import net.minecraft.class_22;
import net.minecraft.class_4587;
import net.minecraft.class_4597;

public class RenderMapEvent
extends Cancellable {
    class_4587 matrices;
    class_4597 vertexConsumers;
    int id;
    class_22 state;
    boolean hidePlayerIcons;
    int light;

    public RenderMapEvent(class_4587 matrices, class_4597 vertexConsumers, int id, class_22 state, boolean hidePlayerIcons, int light) {
        this.matrices = matrices;
        this.vertexConsumers = vertexConsumers;
        this.id = id;
        this.state = state;
        this.hidePlayerIcons = hidePlayerIcons;
        this.light = light;
    }

    public class_4587 getMatrices() {
        return this.matrices;
    }

    public int getId() {
        return this.id;
    }
}

