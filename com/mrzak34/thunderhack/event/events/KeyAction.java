//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.event.events;

public enum KeyAction
{
    Press, 
    Repeat, 
    Release;
    
    public static KeyAction get(final int action) {
        if (action == 1) {
            return KeyAction.Press;
        }
        return (action == 0) ? KeyAction.Release : KeyAction.Repeat;
    }
    
    private static /* synthetic */ KeyAction[] $values() {
        return new KeyAction[] { KeyAction.Press, KeyAction.Repeat, KeyAction.Release };
    }
    
    static {
        $VALUES = $values();
    }
}
