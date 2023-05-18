//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.manager;

import com.mrzak34.thunderhack.util.*;
import com.mrzak34.thunderhack.module.*;
import com.google.common.eventbus.*;
import com.mrzak34.thunderhack.event.events.*;
import com.mrzak34.thunderhack.mixin.*;
import net.minecraft.*;

public class RotationManager implements Util
{
    private static float yaw;
    private static float serverYaw;
    private static float pitch;
    private static float serverPitch;
    
    @Subscribe
    public void onMovmentPost(final MovementEvent.Post event) {
        if (!Module.fullNullCheck()) {
            this.restoreRotations();
        }
    }
    
    public void updateRotations() {
        RotationManager.yaw = RotationManager.mc.field_1724.method_36454();
        RotationManager.pitch = RotationManager.mc.field_1724.method_36455();
    }
    
    public void restoreRotations() {
        RotationManager.mc.field_1724.method_36456(RotationManager.yaw);
        RotationManager.mc.field_1724.method_36457(RotationManager.pitch);
    }
    
    @Subscribe
    public void onMovmentPre(final MovementEvent.Pre event) {
        if (!Module.fullNullCheck()) {
            this.updateRotations();
        }
    }
    
    @Subscribe
    public void PacketSend(final PacketEvent.Send event) {
        if (event.getPacket() instanceof class_2828.class_2831) {
            final class_2828 packet = (class_2828)event.getPacket();
            RotationManager.serverYaw = ((IPlayerMoveC2SPacket)packet).getYaw();
            RotationManager.serverPitch = ((IPlayerMoveC2SPacket)packet).getPitch();
        }
        if (event.getPacket() instanceof class_2828.class_2830) {
            final class_2828 packet = (class_2828)event.getPacket();
            RotationManager.serverYaw = ((IPlayerMoveC2SPacket)packet).getYaw();
            RotationManager.serverPitch = ((IPlayerMoveC2SPacket)packet).getPitch();
        }
    }
    
    @Subscribe
    public void PacketReceive(final PacketEvent.Receive event) {
        if (event.getPacket() instanceof class_2708) {
            final class_2708 packet = (class_2708)event.getPacket();
            RotationManager.serverYaw = packet.method_11736();
            RotationManager.serverPitch = packet.method_11739();
        }
    }
    
    public static float getServerYaw() {
        return RotationManager.serverYaw;
    }
    
    public static float getServerPitch() {
        return RotationManager.serverPitch;
    }
}
