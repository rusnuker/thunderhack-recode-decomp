//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.event.events;

public class ClipAtLedgeEvent
{
    private static final ClipAtLedgeEvent INSTANCE;
    private boolean set;
    private boolean clip;
    
    public void reset() {
        this.set = false;
    }
    
    public void setClip(final boolean clip) {
        this.set = true;
        this.clip = clip;
    }
    
    public boolean isSet() {
        return this.set;
    }
    
    public boolean isClip() {
        return this.clip;
    }
    
    public static ClipAtLedgeEvent get() {
        ClipAtLedgeEvent.INSTANCE.reset();
        return ClipAtLedgeEvent.INSTANCE;
    }
    
    static {
        INSTANCE = new ClipAtLedgeEvent();
    }
}
