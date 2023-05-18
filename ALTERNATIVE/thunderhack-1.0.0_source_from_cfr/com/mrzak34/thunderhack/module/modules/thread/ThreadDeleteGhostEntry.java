/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_310
 */
package com.mrzak34.thunderhack.module.modules.thread;

import com.mrzak34.thunderhack.module.modules.render.GhostRender;
import net.minecraft.class_310;

public class ThreadDeleteGhostEntry
extends Thread {
    public static class_310 mc = class_310.method_1551();
    long pvpTimer;

    public ThreadDeleteGhostEntry(long pvpTimer) {
        this.pvpTimer = pvpTimer;
    }

    @Override
    public void run() {
        try {
            ThreadDeleteGhostEntry.sleep(this.pvpTimer);
        }
        catch (Exception exception) {
            // empty catch block
        }
        GhostRender.getInstance().ghost = null;
        super.run();
    }
}

