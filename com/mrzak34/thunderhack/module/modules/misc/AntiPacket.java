//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.module.modules.misc;

import com.mrzak34.thunderhack.module.*;
import com.mrzak34.thunderhack.settings.*;
import com.mrzak34.thunderhack.event.events.*;
import com.google.common.eventbus.*;
import net.minecraft.*;

public class AntiPacket extends Module
{
    BooleanSetting TeleportConfirmC2SPacket;
    BooleanSetting PlayerPositionLookS2CPacket;
    BooleanSetting KeepAliveC2SPacket;
    BooleanSetting LookAndOnGround;
    BooleanSetting OnGroundOnly;
    BooleanSetting PositionAndOnGround;
    BooleanSetting Full;
    ParentSetting PlayerMoveC2SPacket;
    
    public AntiPacket() {
        super("AntiPacket", 0, false, Category.MISC);
        this.TeleportConfirmC2SPacket = new BooleanSetting("TeleportConfirmC2SPacket", true);
        this.PlayerPositionLookS2CPacket = new BooleanSetting("PlayerPositionLookS2CPacket", true);
        this.KeepAliveC2SPacket = new BooleanSetting("KeepAliveC2SPacket", true);
        this.LookAndOnGround = new BooleanSetting("LookAndOnGround", true);
        this.OnGroundOnly = new BooleanSetting("OnGroundOnly", true);
        this.PositionAndOnGround = new BooleanSetting("PositionAndOnGround", true);
        this.Full = new BooleanSetting("Full", true);
        this.PlayerMoveC2SPacket = new ParentSetting("PlayerMoveC2SPacket", false, false, new Setting[] { this.LookAndOnGround, this.OnGroundOnly, this.PositionAndOnGround, this.Full });
        this.addSettings(new Setting[] { this.TeleportConfirmC2SPacket, this.KeepAliveC2SPacket, this.PlayerMoveC2SPacket, this.PlayerPositionLookS2CPacket });
    }
    
    @Subscribe
    public void PacketSend(final PacketEvent.Send event) {
        if (this.TeleportConfirmC2SPacket.isEnabled() && event.getPacket() instanceof class_2793) {
            event.setCancelled(true);
        }
        if (this.KeepAliveC2SPacket.isEnabled() && event.getPacket() instanceof class_2827) {
            event.setCancelled(true);
        }
        if (this.LookAndOnGround.isEnabled() && event.getPacket() instanceof class_2828.class_2831) {
            event.setCancelled(true);
        }
        if (this.PositionAndOnGround.isEnabled() && event.getPacket() instanceof class_2828.class_2829) {
            event.setCancelled(true);
        }
        if (this.Full.isEnabled() && event.getPacket() instanceof class_2828.class_2830) {
            event.setCancelled(true);
        }
        if (this.OnGroundOnly.isEnabled() && event.getPacket() instanceof class_2828.class_5911) {
            event.setCancelled(true);
        }
    }
    
    @Subscribe
    public void PacketReceive(final PacketEvent.Receive event) {
        if (this.PlayerPositionLookS2CPacket.isEnabled() && event.getPacket() instanceof class_2708) {
            event.setCancelled(true);
        }
    }
}
