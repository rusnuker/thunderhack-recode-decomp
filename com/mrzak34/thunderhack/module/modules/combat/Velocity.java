//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.module.modules.combat;

import com.mrzak34.thunderhack.module.*;
import com.mrzak34.thunderhack.settings.*;
import com.mrzak34.thunderhack.event.events.*;
import net.minecraft.*;
import com.mrzak34.thunderhack.mixin.*;
import com.google.common.eventbus.*;

public class Velocity extends Module
{
    public NumberSetting horizontal;
    public NumberSetting vertical;
    public BooleanSetting fishingHook;
    public static BooleanSetting noPush;
    
    public Velocity() {
        super("Velocity", 0, false, Category.COMBAT);
        this.horizontal = new NumberSetting("horizontal", 100.0f, 0.0f, 100.0f, false);
        this.vertical = new NumberSetting("vertical", 100.0f, 0.0f, 100.0f, false);
        this.fishingHook = new BooleanSetting("fishingHook", true);
        this.addSettings(new Setting[] { this.horizontal, this.vertical, this.fishingHook, Velocity.noPush });
    }
    
    @Subscribe
    public void onPacketReceive(final PacketEvent.Receive event) {
        if (event.getPacket() instanceof class_2743) {
            final class_2743 sPacketEntityVelocity = (class_2743)event.getPacket();
            if (Velocity.mc.field_1687.method_8469(sPacketEntityVelocity.method_11818()) == Velocity.mc.field_1724) {
                if (this.horizontal.getValue() > 0.0 || this.vertical.getValue() > 0.0) {
                    ((IEntityVelocityUpdateS2CPacket)sPacketEntityVelocity).setVelocityX((int)(sPacketEntityVelocity.method_11815() * (this.horizontal.getValue() / 100.0)));
                    ((IEntityVelocityUpdateS2CPacket)sPacketEntityVelocity).setVelocityY((int)(sPacketEntityVelocity.method_11816() * (this.vertical.getValue() / 100.0)));
                    ((IEntityVelocityUpdateS2CPacket)sPacketEntityVelocity).setVelocityZ((int)(sPacketEntityVelocity.method_11819() * (this.horizontal.getValue() / 100.0)));
                }
                event.setCancelled(true);
            }
        }
        else if (event.getPacket() instanceof class_2664) {
            final class_2664 sPacketExplosion = (class_2664)event.getPacket();
            ((IExplosionS2CPacket)sPacketExplosion).setVelocityX((float)(int)(sPacketExplosion.method_11472() * (this.horizontal.getValue() / 100.0)));
            ((IExplosionS2CPacket)sPacketExplosion).setVelocityY((float)(int)(sPacketExplosion.method_11473() * (this.vertical.getValue() / 100.0)));
            ((IExplosionS2CPacket)sPacketExplosion).setVelocityZ((float)(int)(sPacketExplosion.method_11474() * (this.horizontal.getValue() / 100.0)));
        }
    }
    
    static {
        Velocity.noPush = new BooleanSetting("nopush", true);
    }
}
