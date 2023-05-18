/*
 * Decompiled with CFR 0.150.
 */
package com.mrzak34.thunderhack.module.modules.movement;

import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.ModeSetting;

public class Sprint
extends Module {
    public ModeSetting mode = new ModeSetting("mode", "client", "client", "rage");

    public Sprint() {
        super("Sprint", 0, false, Category.MOVEMENT);
        this.addSettings(this.mode);
    }

    @Override
    public void onTick() {
        super.onTick();
        if (Sprint.mc.field_1724 != null) {
            Sprint.mc.field_1724.method_5728(true);
        }
    }
}

