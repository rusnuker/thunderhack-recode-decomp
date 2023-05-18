//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.module.modules.misc;

import com.mrzak34.thunderhack.module.*;
import com.mrzak34.thunderhack.settings.*;

public class FpsLimiter extends Module
{
    public static NumberSetting limit;
    private static FpsLimiter INSTANCE;
    
    public FpsLimiter() {
        super("FpsLimiter", 0, false, Category.MISC);
        this.addSettings(new Setting[] { FpsLimiter.limit });
        this.setInstance();
    }
    
    public static FpsLimiter getInstance() {
        if (FpsLimiter.INSTANCE == null) {
            FpsLimiter.INSTANCE = new FpsLimiter();
        }
        return FpsLimiter.INSTANCE;
    }
    
    private void setInstance() {
        FpsLimiter.INSTANCE = this;
    }
    
    static {
        FpsLimiter.limit = new NumberSetting("limit", 1.0f, 1.0f, 60.0f, false);
        FpsLimiter.INSTANCE = new FpsLimiter();
    }
}
