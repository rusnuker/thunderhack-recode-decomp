//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.settings;

import com.mrzak34.thunderhack.util.*;

public class BooleanSetting extends Setting
{
    public boolean enabled;
    public TimerUtil loadingTimer;
    public int loading;
    
    public BooleanSetting(final String name, final boolean enabled) {
        this.loadingTimer = new TimerUtil();
        this.loading = 0;
        this.name = name;
        this.enabled = enabled;
    }
    
    public boolean isEnabled() {
        return this.enabled;
    }
    
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }
    
    public void toggle() {
        this.enabled = !this.enabled;
    }
}
