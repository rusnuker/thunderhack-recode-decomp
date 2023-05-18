//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.module.modules.misc;

import com.mrzak34.thunderhack.module.*;
import com.mrzak34.thunderhack.settings.*;
import com.mrzak34.thunderhack.*;

public class Timer extends Module
{
    public static NumberSetting multiplier;
    private static Timer INSTANCE;
    
    public Timer() {
        super("Timer", 0, false, Category.MISC);
        this.addSettings(new Setting[] { Timer.multiplier });
        this.setInstance();
    }
    
    public static Timer getInstance() {
        if (Timer.INSTANCE == null) {
            Timer.INSTANCE = new Timer();
        }
        return Timer.INSTANCE;
    }
    
    private void setInstance() {
        Timer.INSTANCE = this;
    }
    
    public void onTick() {
        super.onTick();
        Main.TICK_TIMER = (float)Timer.multiplier.getValue();
    }
    
    static {
        Timer.multiplier = new NumberSetting("multiplier", 0.5f, 0.0f, 5.0f, true);
        Timer.INSTANCE = new Timer();
    }
}
