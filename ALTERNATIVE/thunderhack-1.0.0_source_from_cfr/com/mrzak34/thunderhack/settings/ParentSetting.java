/*
 * Decompiled with CFR 0.150.
 */
package com.mrzak34.thunderhack.settings;

import com.mrzak34.thunderhack.settings.Setting;
import java.util.Arrays;
import java.util.List;

public class ParentSetting
extends Setting {
    public boolean enabled;
    public boolean showSettings;
    public List<Setting> settings;
    public boolean bool;
    public int progress = 0;

    public ParentSetting(String name, boolean enabled, boolean bool, Setting ... settings) {
        this.name = name;
        this.enabled = enabled;
        this.bool = bool;
        this.settings = Arrays.asList(settings);
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

