//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.event;

import meteordevelopment.orbit.*;

public class Cancellable implements ICancellable
{
    private boolean cancelled;
    
    public Cancellable() {
        this.cancelled = false;
    }
    
    public void setCancelled(final boolean cancelled) {
        this.cancelled = cancelled;
    }
    
    public boolean isCancelled() {
        return this.cancelled;
    }
}
