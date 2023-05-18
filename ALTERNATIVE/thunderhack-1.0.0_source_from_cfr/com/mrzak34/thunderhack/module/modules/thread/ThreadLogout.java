/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_310
 */
package com.mrzak34.thunderhack.module.modules.thread;

import net.minecraft.class_310;

public class ThreadLogout
extends Thread {
    public static class_310 mc = class_310.method_1551();

    @Override
    public void run() {
        try {
            ThreadLogout.sleep(300L);
        }
        catch (Exception exception) {
            // empty catch block
        }
        ThreadLogout.mc.field_1724.field_3944.method_45730("logout");
        super.run();
    }
}

