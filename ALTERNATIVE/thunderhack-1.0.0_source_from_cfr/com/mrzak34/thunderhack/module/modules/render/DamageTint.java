/*
 * Decompiled with CFR 0.150.
 */
package com.mrzak34.thunderhack.module.modules.render;

import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.NumberSetting;

public class DamageTint
extends Module {
    public static NumberSetting health = new NumberSetting("health", 20.0f, 1.0f, 36.0f, false);
    public static NumberSetting power = new NumberSetting("power", 1.0f, 0.0f, 2.0f, true);
    private static DamageTint INSTANCE = new DamageTint();

    public DamageTint() {
        super("DamageTint", 0, false, Category.RENDER);
        this.addSettings(health, power);
        this.setInstance();
    }

    public static DamageTint getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DamageTint();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }
}

