/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2767
 *  net.minecraft.class_310
 */
package com.mrzak34.thunderhack.module.modules.thread;

import com.mrzak34.thunderhack.util.FakePlayerEntity;
import com.mrzak34.thunderhack.util.FakePlayerUtil;
import net.minecraft.class_2767;
import net.minecraft.class_310;

public class ThreadDeathPlayer
extends Thread {
    public static class_310 mc = class_310.method_1551();
    class_2767 sound;
    long life;
    FakePlayerEntity fakePlayer;

    public ThreadDeathPlayer(class_2767 sound, long life, FakePlayerEntity fakePlayer) {
        this.sound = sound;
        this.life = life;
        this.fakePlayer = fakePlayer;
    }

    @Override
    public void run() {
        try {
            ThreadDeathPlayer.sleep(this.life);
        }
        catch (Exception exception) {
            // empty catch block
        }
        FakePlayerUtil.remove(this.fakePlayer);
        super.run();
    }
}

