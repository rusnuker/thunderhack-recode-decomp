//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.module.modules.thread;

import net.minecraft.*;
import com.mrzak34.thunderhack.util.*;

public class ThreadSwapBack extends Thread
{
    public static class_310 mc;
    int swapBackSlot;
    long swapBackDelay;
    boolean silent;
    
    public ThreadSwapBack(final int swapBackSlot, final long swapBackDelay, final boolean silent) {
        this.swapBackSlot = swapBackSlot;
        this.swapBackDelay = swapBackDelay;
        this.silent = silent;
    }
    
    @Override
    public void run() {
        try {
            Thread.sleep(this.swapBackDelay);
        }
        catch (Exception ex) {}
        InventoryUtil.switchToHotbarSlot(this.swapBackSlot, this.silent);
        super.run();
    }
    
    static {
        ThreadSwapBack.mc = class_310.method_1551();
    }
}
