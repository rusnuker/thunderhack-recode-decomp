/*
 * Decompiled with CFR 0.150.
 */
package com.mrzak34.thunderhack.settings;

import com.mrzak34.thunderhack.settings.Setting;
import java.util.Arrays;
import java.util.List;

public class ModeSetting
extends Setting {
    public int index;
    public List<String> modes;

    public ModeSetting(String name, String defaultMode, String ... modes) {
        this.name = name;
        this.modes = Arrays.asList(modes);
        this.index = this.modes.indexOf(defaultMode);
    }

    public String getMode() {
        return this.modes.get(this.index);
    }

    public boolean is(String mode) {
        return this.index == this.modes.indexOf(mode);
    }

    public void cycle() {
        this.index = this.index < this.modes.size() - 1 ? ++this.index : 0;
    }

    public void revcycle() {
        this.index = this.index > 0 ? --this.index : this.modes.size() - 1;
    }
}

