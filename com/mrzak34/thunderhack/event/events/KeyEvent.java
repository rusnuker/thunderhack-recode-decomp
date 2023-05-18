//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.event.events;

import com.mrzak34.thunderhack.event.*;

public class KeyEvent extends Cancellable
{
    public int key;
    public int action;
    
    public KeyEvent(final int key, final int action) {
        this.key = key;
        this.action = action;
    }
}
