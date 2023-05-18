/*
 * Decompiled with CFR 0.150.
 */
package com.mrzak34.thunderhack.module.modules.misc;

import com.mrzak34.thunderhack.manager.NotificationManager;
import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.NumberSetting;

public class Freez
extends Module {
    NumberSetting freezValue = new NumberSetting("freez", 1000.0f, 0.0f, 1000.0f, false);

    public Freez() {
        super("Freez", 0, false, Category.MISC);
        this.addSettings(this.freezValue);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        try {
            NotificationManager.notif("sleep");
            Thread.sleep((long)this.freezValue.getValue());
        }
        catch (InterruptedException interruptedException) {
            // empty catch block
        }
    }
}

