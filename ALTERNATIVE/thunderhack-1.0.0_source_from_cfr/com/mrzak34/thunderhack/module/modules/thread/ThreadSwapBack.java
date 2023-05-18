/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_310
 */
package com.mrzak34.thunderhack.module.modules.thread;

import com.mrzak34.thunderhack.util.InventoryUtil;
import net.minecraft.class_310;

public class ThreadSwapBack
extends Thread {
    public static class_310 mc = class_310.method_1551();
    int swapBackSlot;
    long swapBackDelay;
    boolean silent;

    public ThreadSwapBack(int swapBackSlot, long swapBackDelay, boolean silent) {
        this.swapBackSlot = swapBackSlot;
        this.swapBackDelay = swapBackDelay;
        this.silent = silent;
    }

    @Override
    public void run() {
        try {
            ThreadSwapBack.sleep(this.swapBackDelay);
        }
        catch (Exception exception) {
            // empty catch block
        }
        InventoryUtil.switchToHotbarSlot(this.swapBackSlot, this.silent);
        super.run();
    }
}

