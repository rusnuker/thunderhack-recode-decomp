/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2561
 *  net.minecraft.class_310
 *  net.minecraft.class_327
 *  net.minecraft.class_437
 *  net.minecraft.class_4587
 */
package com.mrzak34.thunderhack.ui;

import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_327;
import net.minecraft.class_437;
import net.minecraft.class_4587;

public class HudOverlay
extends class_437 {
    private class_310 mc = class_310.method_1551();
    class_327 tr;

    protected HudOverlay(class_2561 title) {
        super(title);
        this.tr = this.mc.field_1772;
    }

    public void method_25394(class_4587 matrices, int x, int y, float partialTicks) {
        this.tr.method_1720(matrices, "ThunderHack", 1.0f, 1.0f, -1);
    }
}

