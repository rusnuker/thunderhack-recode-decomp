/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1293
 *  net.minecraft.class_1294
 */
package com.mrzak34.thunderhack.module.modules.render;

import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.ModeSetting;
import com.mrzak34.thunderhack.settings.NumberSetting;
import net.minecraft.class_1293;
import net.minecraft.class_1294;

public class FullBright
extends Module {
    ModeSetting mode = new ModeSetting("mode", "gamma", "gamma", "potion");
    NumberSetting gamma = new NumberSetting("gamma", 9.0f, 1.0f, 12.0f, false);

    public FullBright() {
        super("FullBright", 0, false, Category.RENDER);
        this.addSettings(this.mode, this.gamma);
    }

    @Override
    public void onDisable() {
        if ((Double)FullBright.mc.field_1690.method_42473().method_41753() > 1.0) {
            for (double g = ((Double)FullBright.mc.field_1690.method_42473().method_41753()).doubleValue(); g > 1.0; g -= 1.6) {
                double nextStep = Math.max(g - 1.6, 1.0);
                FullBright.mc.field_1690.method_42473().method_41748((Object)nextStep);
            }
        }
        FullBright.mc.field_1724.method_6016(class_1294.field_5925);
        super.onDisable();
    }

    @Override
    public void onTick() {
        super.onTick();
        if (this.mode.getMode().equals("gamma")) {
            if ((Double)FullBright.mc.field_1690.method_42473().method_41753() < this.gamma.getValue()) {
                FullBright.mc.field_1690.method_42473().method_41748((Object)Math.min((Double)FullBright.mc.field_1690.method_42473().method_41753() + 1.0, this.gamma.getValue()));
            } else if ((Double)FullBright.mc.field_1690.method_42473().method_41753() > this.gamma.getValue()) {
                FullBright.mc.field_1690.method_42473().method_41748((Object)Math.max((Double)FullBright.mc.field_1690.method_42473().method_41753() - 1.0, this.gamma.getValue()));
            }
        } else if (this.mode.getMode().equals("potion")) {
            FullBright.mc.field_1724.method_6092(new class_1293(class_1294.field_5925, 500, 0));
        }
    }
}

