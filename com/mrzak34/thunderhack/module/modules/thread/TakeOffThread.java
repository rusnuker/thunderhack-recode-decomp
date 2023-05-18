//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.module.modules.thread;

import net.minecraft.*;

public class TakeOffThread extends Thread
{
    public static class_310 mc;
    int delay;
    
    public TakeOffThread(final int delay) {
        this.delay = delay;
    }
    
    @Override
    public void run() {
        try {
            Thread.sleep(this.delay);
        }
        catch (Exception ex) {}
        TakeOffThread.mc.field_1724.field_3944.method_2883((class_2596)new class_2848((class_1297)TakeOffThread.mc.field_1724, class_2848.class_2849.field_12982));
        super.run();
    }
    
    static {
        TakeOffThread.mc = class_310.method_1551();
    }
}
