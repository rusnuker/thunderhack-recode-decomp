//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.module.modules.movement;

import com.mrzak34.thunderhack.util.*;
import com.mrzak34.thunderhack.module.*;
import com.mrzak34.thunderhack.settings.*;
import com.mrzak34.thunderhack.event.events.*;
import com.mrzak34.thunderhack.mixin.*;
import com.google.common.eventbus.*;
import net.minecraft.*;

public class PreElytraFly extends Module
{
    ModeSetting mode;
    static NumberSetting speed;
    NumberSetting glideSpeed;
    NumberSetting falling;
    NumberSetting delay;
    NumberSetting lagBackDelay;
    NumberSetting pitch;
    TimerUtil takeofTimer;
    boolean reTakeOff;
    private boolean elytraIsEquipped;
    private int elytraDurability;
    private boolean outOfDurability;
    private boolean wasInLiquid;
    private boolean isFlying;
    private boolean isPacketFlying;
    private boolean isStandingStillH;
    private boolean isStandingStill;
    private float speedPercentage;
    private double hoverTarget;
    private static float packetYaw;
    private float packetPitch;
    private boolean hoverState;
    private int boostingTick;
    
    public PreElytraFly() {
        super("PreElytraFly", 0, false, Category.MOVEMENT);
        this.mode = new ModeSetting("mode", "vanilla", new String[] { "vanilla", "packet", "static" });
        this.glideSpeed = new NumberSetting("glideSpeed", 0.5f, 0.0f, 0.3f, true);
        this.falling = new NumberSetting("falling", 0.25f, 0.0f, 2.0f, true);
        this.delay = new NumberSetting("delay", 250.0f, 0.0f, 300.0f, false);
        this.lagBackDelay = new NumberSetting("lagBackDelay", 200.0f, 200.0f, 1000.0f, false);
        this.pitch = new NumberSetting("pitch", -2.3f, -90.0f, 90.0f, true);
        this.takeofTimer = new TimerUtil();
        this.reTakeOff = false;
        this.elytraIsEquipped = false;
        this.elytraDurability = 0;
        this.outOfDurability = false;
        this.wasInLiquid = false;
        this.isFlying = false;
        this.isPacketFlying = false;
        this.isStandingStillH = false;
        this.isStandingStill = false;
        this.speedPercentage = 0.0f;
        this.hoverTarget = -1.0;
        this.packetPitch = 0.0f;
        this.hoverState = false;
        this.boostingTick = 0;
        this.addSettings(new Setting[] { this.mode, PreElytraFly.speed, this.glideSpeed, this.falling, this.delay, this.lagBackDelay, this.pitch });
    }
    
    @Subscribe
    public void onPacketSend(final PacketEvent.Send event) {
        if (event.getPacket() instanceof class_2828) {
            final class_2828 packet = (class_2828)event.getPacket();
            ((IPlayerMoveC2SPacket)packet).setPitch((float)this.pitch.getValue());
        }
    }
    
    private static float getYaw() {
        final float yawRad = calcMoveYaw();
        PreElytraFly.packetYaw = (float)Math.toDegrees(yawRad);
        return yawRad;
    }
    
    public static float calcMoveYaw() {
        float strafe = 90.0f * PreElytraFly.mc.field_1724.field_3913.field_3907;
        strafe *= ((PreElytraFly.mc.field_1724.field_3913.field_3905 != 0.0f) ? (PreElytraFly.mc.field_1724.field_3913.field_3905 * 0.5f) : 1.0f);
        float yaw = PreElytraFly.mc.field_1724.method_36454() - strafe;
        yaw -= ((PreElytraFly.mc.field_1724.field_3913.field_3905 < 0.0f) ? 180.0f : 0.0f);
        return (float)Math.toRadians(yaw);
    }
    
    private static void setSpeed(final double yaw) {
        final double acceleratedSpeed = PreElytraFly.speed.getValue();
        PreElytraFly.mc.field_1724.method_18800(Math.sin(-yaw) * acceleratedSpeed, PreElytraFly.mc.field_1724.method_18798().field_1351, Math.cos(yaw) * acceleratedSpeed);
    }
    
    public void onEnable() {
        super.onEnable();
        PreElytraFly.mc.field_1724.field_3944.method_2883((class_2596)new class_2848((class_1297)PreElytraFly.mc.field_1724, class_2848.class_2849.field_12982));
    }
    
    static {
        PreElytraFly.speed = new NumberSetting("speed", 4.0f, 0.0f, 10.0f, true);
        PreElytraFly.packetYaw = 0.0f;
    }
}
