/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2561
 */
package com.mrzak34.thunderhack.module.modules.movement;

import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.NumberSetting;
import net.minecraft.class_2561;

public class VClip
extends Module {
    NumberSetting distance = new NumberSetting("distance", 1.0f, -100.0f, 100.0f, false);

    public VClip() {
        super("VClip", 0, false, Category.MOVEMENT);
        this.addSettings(this.distance);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        VClip.mc.field_1724.method_5814(VClip.mc.field_1724.method_23317(), VClip.mc.field_1724.method_23318() + this.distance.getValue(), VClip.mc.field_1724.method_23321());
        if (this.distance.getValue() > 0.0) {
            VClip.mc.field_1724.method_43496(class_2561.method_30163((String)("Teleported to " + this.distance.getValue() + " with up")));
        } else {
            VClip.mc.field_1724.method_43496(class_2561.method_30163((String)("Teleported to " + this.distance.getValue() + " with down")));
        }
        this.setToggled(false);
    }
}

