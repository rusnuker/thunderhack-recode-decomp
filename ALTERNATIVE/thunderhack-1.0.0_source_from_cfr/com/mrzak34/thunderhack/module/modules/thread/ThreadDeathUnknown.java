/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2767
 *  net.minecraft.class_310
 */
package com.mrzak34.thunderhack.module.modules.thread;

import com.mrzak34.thunderhack.module.modules.render.GhostRender;
import net.minecraft.class_2767;
import net.minecraft.class_310;

public class ThreadDeathUnknown
extends Thread {
    public static class_310 mc = class_310.method_1551();
    class_2767 sound;
    long life;

    public ThreadDeathUnknown(class_2767 sound, long life) {
        this.sound = sound;
        this.life = life;
    }

    @Override
    public void run() {
        try {
            ThreadDeathUnknown.sleep(this.life);
        }
        catch (Exception exception) {
            // empty catch block
        }
        GhostRender.getInstance().sounds.remove((Object)this.sound);
        super.run();
    }
}

