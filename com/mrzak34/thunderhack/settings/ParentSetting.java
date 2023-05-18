//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.settings;

import java.util.*;

public class ParentSetting extends Setting
{
    public boolean enabled;
    public boolean showSettings;
    public List<Setting> settings;
    public boolean bool;
    public int progress;
    
    public ParentSetting(final String name, final boolean enabled, final boolean bool, final Setting... settings) {
        this.progress = 0;
        this.name = name;
        this.enabled = enabled;
        this.bool = bool;
        this.settings = Arrays.asList(settings);
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
