/*
 * Decompiled with CFR 0.150.
 */
package com.mrzak34.thunderhack.settings;

import com.mrzak34.thunderhack.settings.Setting;
import com.mrzak34.thunderhack.util.TimerUtil;

public class BooleanSetting
extends Setting {
    public boolean enabled;
    public TimerUtil loadingTimer = new TimerUtil();
    public int loading = 0;

    public BooleanSetting(String name, boolean enabled) {
        this.name = name;
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void toggle() {
        this.enabled = !this.enabled;
    }
}

