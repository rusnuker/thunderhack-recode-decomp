//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.module.modules.misc;

import com.mrzak34.thunderhack.module.*;
import com.mrzak34.thunderhack.settings.*;
import com.mrzak34.thunderhack.util.*;

public class FakePlayer extends Module
{
    NumberSetting health;
    BooleanSetting copyInv;
    
    public FakePlayer() {
        super("FakePlayer", 0, false, Category.MISC);
        this.health = new NumberSetting("health", 20.0f, 1.0f, 36.0f, true);
        this.copyInv = new BooleanSetting("copy inv", true);
        this.addSettings(new Setting[] { this.health, this.copyInv });
    }
    
    public void onEnable() {
        super.onEnable();
        FakePlayerUtil.add("Putin", (float)this.health.getValue(), this.copyInv.isEnabled());
    }
    
    public void onDisable() {
        super.onDisable();
        FakePlayerUtil.clear();
    }
}
