//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.module.modules.client;

import com.mrzak34.thunderhack.module.*;
import com.mrzak34.thunderhack.settings.*;

public class Notifications extends Module
{
    public BooleanSetting toggle;
    public BooleanSetting pops;
    private static Notifications INSTANCE;
    
    public Notifications() {
        super("Notifications", 0, false, Category.CLIENT);
        this.toggle = new BooleanSetting("toggle", true);
        this.pops = new BooleanSetting("pops", true);
        this.addSettings(new Setting[] { this.toggle, this.pops });
        this.setInstance();
    }
    
    public static Notifications getInstance() {
        if (Notifications.INSTANCE == null) {
            Notifications.INSTANCE = new Notifications();
        }
        return Notifications.INSTANCE;
    }
    
    private void setInstance() {
        Notifications.INSTANCE = this;
    }
    
    static {
        Notifications.INSTANCE = new Notifications();
    }
}
