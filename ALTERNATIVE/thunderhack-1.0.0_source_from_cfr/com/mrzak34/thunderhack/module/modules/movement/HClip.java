/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_243
 *  net.minecraft.class_2561
 */
package com.mrzak34.thunderhack.module.modules.movement;

import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.NumberSetting;
import com.mrzak34.thunderhack.util.MathUtil;
import net.minecraft.class_243;
import net.minecraft.class_2561;

public class HClip
extends Module {
    NumberSetting distance = new NumberSetting("distance", 108.0f, 1.0f, 1000.0f, false);

    public HClip() {
        super("HClip", 0, false, Category.MOVEMENT);
        this.addSettings(this.distance);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        class_243 direction = MathUtil.direction(HClip.mc.field_1724.field_6241);
        if (direction != null) {
            HClip.mc.field_1724.method_5814(HClip.mc.field_1724.method_23317() + direction.field_1352 * this.distance.getValue(), HClip.mc.field_1724.method_23318(), HClip.mc.field_1724.method_23321() + direction.field_1350 * this.distance.getValue());
            HClip.mc.field_1724.method_43496(class_2561.method_30163((String)("Teleported to " + this.distance.getValue() + " with forward")));
            this.setToggled(false);
        }
    }
}

