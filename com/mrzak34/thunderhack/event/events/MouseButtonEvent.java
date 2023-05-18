//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.event.events;

import com.mrzak34.thunderhack.event.*;

public class MouseButtonEvent extends Cancellable
{
    int button;
    int action;
    
    public MouseButtonEvent(final int button, final int action) {
        this.button = button;
        this.action = action;
    }
    
    public int getButton() {
        return this.button;
    }
    
    public int getAction() {
        return this.action;
    }
}
