/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2586
 *  net.minecraft.class_4587
 *  net.minecraft.class_4597
 */
package com.mrzak34.thunderhack.event.events;

import com.mrzak34.thunderhack.event.Cancellable;
import net.minecraft.class_2586;
import net.minecraft.class_4587;
import net.minecraft.class_4597;

public class RenderSignEvent
extends Cancellable {
    class_2586 signBlockEntity;
    float f;
    class_4587 matrices;
    class_4597 vertexConsumerProvider;
    int i;
    int j;

    public RenderSignEvent(class_2586 signBlockEntity, float f, class_4587 matrices, class_4597 vertexConsumerProvider, int i, int j) {
        this.signBlockEntity = signBlockEntity;
        this.f = f;
        this.matrices = matrices;
        this.vertexConsumerProvider = vertexConsumerProvider;
        this.i = i;
        this.j = j;
    }

    public class_4587 getMatrices() {
        return this.matrices;
    }

    public int getI() {
        return this.i;
    }

    public int getJ() {
        return this.j;
    }

    public class_2586 getSignBlockEntity() {
        return this.signBlockEntity;
    }
}

