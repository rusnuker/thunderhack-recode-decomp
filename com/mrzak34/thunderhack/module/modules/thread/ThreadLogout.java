//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.module.modules.thread;

import net.minecraft.*;

public class ThreadLogout extends Thread
{
    public static class_310 mc;
    
    @Override
    public void run() {
        try {
            Thread.sleep(300L);
        }
        catch (Exception ex) {}
        ThreadLogout.mc.field_1724.field_3944.method_45730("logout");
        super.run();
    }
    
    static {
        ThreadLogout.mc = class_310.method_1551();
    }
}
