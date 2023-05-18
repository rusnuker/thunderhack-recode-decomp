//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.settings;

public class KeybindSetting extends Setting
{
    public int code;
    public boolean binding;
    public boolean keyDown;
    
    public KeybindSetting(final String name, final int code, final boolean keyDown) {
        this.binding = false;
        this.name = name;
        this.code = code;
        this.keyDown = keyDown;
    }
    
    public int getKeyCode() {
        return this.code;
    }
    
    public int setKeyCode(final int code) {
        return this.code = code;
    }
}
