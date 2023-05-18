//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.module.modules.thread;

import net.minecraft.*;
import com.mrzak34.thunderhack.util.*;

public class ThreadDeathPlayer extends Thread
{
    public static class_310 mc;
    class_2767 sound;
    long life;
    FakePlayerEntity fakePlayer;
    
    public ThreadDeathPlayer(final class_2767 sound, final long life, final FakePlayerEntity fakePlayer) {
        this.sound = sound;
        this.life = life;
        this.fakePlayer = fakePlayer;
    }
    
    @Override
    public void run() {
        try {
            Thread.sleep(this.life);
        }
        catch (Exception ex) {}
        FakePlayerUtil.remove(this.fakePlayer);
        super.run();
    }
    
    static {
        ThreadDeathPlayer.mc = class_310.method_1551();
    }
}
