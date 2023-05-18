/*
 * Decompiled with CFR 0.150.
 */
package com.mrzak34.thunderhack.module.modules.misc;

import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.NumberSetting;

public class FpsLimiter
extends Module {
    public static NumberSetting limit = new NumberSetting("limit", 1.0f, 1.0f, 60.0f, false);
    private static FpsLimiter INSTANCE = new FpsLimiter();

    public FpsLimiter() {
        super("FpsLimiter", 0, false, Category.MISC);
        this.addSettings(limit);
        this.setInstance();
    }

    public static FpsLimiter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FpsLimiter();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }
}

