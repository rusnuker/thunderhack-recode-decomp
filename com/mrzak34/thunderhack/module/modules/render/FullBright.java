//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.module.modules.render;

import com.mrzak34.thunderhack.module.*;
import com.mrzak34.thunderhack.settings.*;
import net.minecraft.*;

public class FullBright extends Module
{
    ModeSetting mode;
    NumberSetting gamma;
    
    public FullBright() {
        super("FullBright", 0, false, Category.RENDER);
        this.mode = new ModeSetting("mode", "gamma", new String[] { "gamma", "potion" });
        this.gamma = new NumberSetting("gamma", 9.0f, 1.0f, 12.0f, false);
        this.addSettings(new Setting[] { this.mode, this.gamma });
    }
    
    public void onDisable() {
        if ((double)FullBright.mc.field_1690.method_42473().method_41753() > 1.0) {
            for (double g = (double)FullBright.mc.field_1690.method_42473().method_41753(); g > 1.0; g -= 1.6) {
                final double nextStep = Math.max(g - 1.6, 1.0);
                FullBright.mc.field_1690.method_42473().method_41748((Object)nextStep);
            }
        }
        FullBright.mc.field_1724.method_6016(class_1294.field_5925);
        super.onDisable();
    }
    
    public void onTick() {
        super.onTick();
        if (this.mode.getMode().equals("gamma")) {
            if ((double)FullBright.mc.field_1690.method_42473().method_41753() < this.gamma.getValue()) {
                FullBright.mc.field_1690.method_42473().method_41748((Object)Math.min((double)FullBright.mc.field_1690.method_42473().method_41753() + 1.0, this.gamma.getValue()));
            }
            else if ((double)FullBright.mc.field_1690.method_42473().method_41753() > this.gamma.getValue()) {
                FullBright.mc.field_1690.method_42473().method_41748((Object)Math.max((double)FullBright.mc.field_1690.method_42473().method_41753() - 1.0, this.gamma.getValue()));
            }
        }
        else if (this.mode.getMode().equals("potion")) {
            FullBright.mc.field_1724.method_6092(new class_1293(class_1294.field_5925, 500, 0));
        }
    }
}
