//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.module.modules.movement;

import com.mrzak34.thunderhack.module.*;
import com.mrzak34.thunderhack.settings.*;
import com.mrzak34.thunderhack.event.events.*;
import com.google.common.eventbus.*;

public class Flight extends Module
{
    ModeSetting mode;
    NumberSetting speed;
    NumberSetting glide;
    
    public Flight() {
        super("Flight", 0, false, Category.MOVEMENT);
        this.mode = new ModeSetting("mode", "vanilla", new String[] { "vanilla", "static" });
        this.speed = new NumberSetting("speed", 1.0f, 0.0f, 10.0f, true);
        this.glide = new NumberSetting("glide", 0.5f, 0.0f, 3.0f, true);
        this.addSettings(new Setting[] { this.mode, this.speed, this.glide });
    }
    
    @Subscribe
    public void onMovePost(final MoveEvent event) {
        final String mode = this.mode.getMode();
        switch (mode) {
            case "static": {
                Flight.mc.field_1724.method_18800(0.0, -(this.glide.getValue() / 10.0), 0.0);
                if (Flight.mc.field_1690.field_1903.method_1434()) {
                    Flight.mc.field_1724.method_5762(0.0, this.speed.getValue() / 2.0, 0.0);
                }
                if (Flight.mc.field_1690.field_1832.method_1434()) {
                    Flight.mc.field_1724.method_5762(0.0, -(this.speed.getValue() / 2.0), 0.0);
                    break;
                }
                break;
            }
            case "vanilla": {
                if (this.glide.getValue() != 0.0 && !Flight.mc.field_1690.field_1903.method_1434() && !Flight.mc.field_1690.field_1832.method_1434()) {
                    Flight.mc.field_1724.method_5762(0.0, -this.glide.getValue() / 10.0, 0.0);
                    break;
                }
                break;
            }
        }
    }
    
    @Subscribe
    public void onMovePre(final MoveEvent event) {
        final String mode = this.mode.getMode();
        switch (mode) {
            case "static": {
                Flight.mc.field_1724.method_31549().field_7479 = true;
                Flight.mc.field_1724.method_31549().method_7248((float)this.speed.getValue());
                break;
            }
            case "vanilla": {
                Flight.mc.field_1724.method_31549().field_7479 = true;
                Flight.mc.field_1724.method_31549().method_7248((float)((float)this.speed.getValue() / 11.11));
                break;
            }
        }
    }
    
    public void onDisable() {
        super.onDisable();
        Flight.mc.field_1724.method_31549().field_7479 = false;
        Flight.mc.field_1724.method_31549().method_7248(0.05f);
    }
}
