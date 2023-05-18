/*
 * Decompiled with CFR 0.150.
 */
package com.mrzak34.thunderhack.module.modules.misc;

import com.mrzak34.thunderhack.Main;
import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.NumberSetting;

public class Timer
extends Module {
    public static NumberSetting multiplier = new NumberSetting("multiplier", 0.5f, 0.0f, 5.0f, true);
    private static Timer INSTANCE = new Timer();

    public Timer() {
        super("Timer", 0, false, Category.MISC);
        this.addSettings(multiplier);
        this.setInstance();
    }

    public static Timer getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Timer();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }

    @Override
    public void onTick() {
        super.onTick();
        Main.TICK_TIMER = (float)multiplier.getValue();
    }
}

