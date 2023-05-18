//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.module.modules.render;

import com.mrzak34.thunderhack.module.*;
import com.mrzak34.thunderhack.settings.*;

public class DamageTint extends Module
{
    public static NumberSetting health;
    public static NumberSetting power;
    private static DamageTint INSTANCE;
    
    public DamageTint() {
        super("DamageTint", 0, false, Category.RENDER);
        this.addSettings(new Setting[] { DamageTint.health, DamageTint.power });
        this.setInstance();
    }
    
    public static DamageTint getInstance() {
        if (DamageTint.INSTANCE == null) {
            DamageTint.INSTANCE = new DamageTint();
        }
        return DamageTint.INSTANCE;
    }
    
    private void setInstance() {
        DamageTint.INSTANCE = this;
    }
    
    static {
        DamageTint.health = new NumberSetting("health", 20.0f, 1.0f, 36.0f, false);
        DamageTint.power = new NumberSetting("power", 1.0f, 0.0f, 2.0f, true);
        DamageTint.INSTANCE = new DamageTint();
    }
}
