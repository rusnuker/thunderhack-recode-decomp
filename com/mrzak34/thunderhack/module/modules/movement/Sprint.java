//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.module.modules.movement;

import com.mrzak34.thunderhack.module.*;
import com.mrzak34.thunderhack.settings.*;

public class Sprint extends Module
{
    public ModeSetting mode;
    
    public Sprint() {
        super("Sprint", 0, false, Category.MOVEMENT);
        this.mode = new ModeSetting("mode", "client", new String[] { "client", "rage" });
        this.addSettings(new Setting[] { this.mode });
    }
    
    public void onTick() {
        super.onTick();
        if (Sprint.mc.field_1724 != null) {
            Sprint.mc.field_1724.method_5728(true);
        }
    }
}
