/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_2596
 *  net.minecraft.class_2848
 *  net.minecraft.class_2848$class_2849
 *  net.minecraft.class_310
 */
package com.mrzak34.thunderhack.module.modules.thread;

import net.minecraft.class_1297;
import net.minecraft.class_2596;
import net.minecraft.class_2848;
import net.minecraft.class_310;

public class TakeOffThread
extends Thread {
    public static class_310 mc = class_310.method_1551();
    int delay;

    public TakeOffThread(int delay) {
        this.delay = delay;
    }

    @Override
    public void run() {
        try {
            TakeOffThread.sleep(this.delay);
        }
        catch (Exception exception) {
            // empty catch block
        }
        TakeOffThread.mc.field_1724.field_3944.method_2883((class_2596)new class_2848((class_1297)TakeOffThread.mc.field_1724, class_2848.class_2849.field_12982));
        super.run();
    }
}

