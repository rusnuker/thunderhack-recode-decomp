/*
 * Decompiled with CFR 0.150.
 */
package com.mrzak34.thunderhack.module.modules.client;

import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.BooleanSetting;

public class Notifications
extends Module {
    public BooleanSetting toggle = new BooleanSetting("toggle", true);
    public BooleanSetting pops = new BooleanSetting("pops", true);
    private static Notifications INSTANCE = new Notifications();

    public Notifications() {
        super("Notifications", 0, false, Category.CLIENT);
        this.addSettings(this.toggle, this.pops);
        this.setInstance();
    }

    public static Notifications getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Notifications();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }
}

