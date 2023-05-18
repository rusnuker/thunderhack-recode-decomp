/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2596
 *  net.minecraft.class_2828$class_5911
 *  net.minecraft.class_2846
 *  net.minecraft.class_2846$class_2847
 */
package com.mrzak34.thunderhack.module.modules.misc;

import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.BooleanSetting;
import com.mrzak34.thunderhack.settings.NumberSetting;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2596;
import net.minecraft.class_2828;
import net.minecraft.class_2846;

public class OffhandCrash
extends Module {
    NumberSetting switches = new NumberSetting("switches", 420.0f, 0.0f, 2000.0f, false);
    BooleanSetting playerpackets = new BooleanSetting("player packets", false);

    public OffhandCrash() {
        super("OffhandCrash", 0, false, Category.MISC);
        this.addSettings(this.switches, this.playerpackets);
    }

    @Override
    public void onTick() {
        super.onTick();
        int i = 0;
        while ((double)i < this.switches.getValue()) {
            OffhandCrash.mc.field_1724.field_3944.method_2883((class_2596)new class_2846(class_2846.class_2847.field_12969, class_2338.field_10980, class_2350.field_11033));
            if (this.playerpackets.isEnabled()) {
                OffhandCrash.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_5911(true));
            }
            ++i;
        }
    }
}

