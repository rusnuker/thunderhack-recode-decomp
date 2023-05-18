/*
 * Decompiled with CFR 0.150.
 */
package com.mrzak34.thunderhack.settings;

import com.mrzak34.thunderhack.settings.Setting;

public class KeybindSetting
extends Setting {
    public int code;
    public boolean binding = false;
    public boolean keyDown;

    public KeybindSetting(String name, int code, boolean keyDown) {
        this.name = name;
        this.code = code;
        this.keyDown = keyDown;
    }

    public int getKeyCode() {
        return this.code;
    }

    public int setKeyCode(int code) {
        this.code = code;
        return code;
    }
}

