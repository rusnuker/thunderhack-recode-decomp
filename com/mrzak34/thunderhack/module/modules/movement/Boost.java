//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.module.modules.movement;

import com.mrzak34.thunderhack.module.*;
import com.mrzak34.thunderhack.settings.*;
import com.mrzak34.thunderhack.event.events.*;
import net.minecraft.*;
import com.google.common.eventbus.*;

public class Boost extends Module
{
    public static NumberSetting speed;
    public static NumberSetting minVelocity;
    public BooleanSetting up;
    int velocity;
    int upvelocity;
    
    public Boost() {
        super("Boost", 0, false, Category.MOVEMENT);
        this.up = new BooleanSetting("up", false);
        this.velocity = 0;
        this.upvelocity = 0;
        this.addSettings(new Setting[] { Boost.speed, Boost.minVelocity, this.up });
    }
    
    @Subscribe
    public void onPacketReceive(final PacketEvent.Receive event) {
        if (event.getPacket() instanceof class_2743 && ((class_2743)event.getPacket()).method_11818() == Boost.mc.field_1724.method_5628()) {
            final class_2743 pack = (class_2743)event.getPacket();
            int vX = pack.method_11815();
            int vY = pack.method_11816();
            int vZ = pack.method_11819();
            if (vX < 0) {
                vX *= -1;
            }
            if (vY < 0) {
                vY *= -1;
            }
            if (vZ < 0) {
                vZ *= -1;
            }
            this.upvelocity = vY;
            if (this.up.isEnabled()) {
                this.velocity += vX + vY + vZ;
            }
            else {
                this.velocity += vX + vZ;
            }
        }
    }
    
    public void onTick() {
        super.onTick();
        if (this.velocity > Boost.minVelocity.getValue()) {
            final double[] dir = directionSpeed(this.velocity / 8000.0f);
            Boost.mc.field_1724.method_5762(dir[0] * Boost.speed.getValue(), 0.0, dir[1] * Boost.speed.getValue());
            this.velocity = 0;
        }
    }
    
    public static double[] directionSpeed(final double speed) {
        float forward = Boost.mc.field_1724.field_3913.field_3905;
        float side = Boost.mc.field_1724.field_3913.field_3907;
        float yaw = Boost.mc.field_1724.field_5982 + (Boost.mc.field_1724.field_6241 - Boost.mc.field_1724.field_5982) * Boost.mc.method_1488();
        if (forward != 0.0f) {
            if (side > 0.0f) {
                yaw += ((forward > 0.0f) ? -45 : 45);
            }
            else if (side < 0.0f) {
                yaw += ((forward > 0.0f) ? 45 : -45);
            }
            side = 0.0f;
            if (forward > 0.0f) {
                forward = 1.0f;
            }
            else if (forward < 0.0f) {
                forward = -1.0f;
            }
        }
        final double sin = Math.sin(Math.toRadians(yaw + 90.0f));
        final double cos = Math.cos(Math.toRadians(yaw + 90.0f));
        final double posX = forward * speed * cos + side * speed * sin;
        final double posZ = forward * speed * sin - side * speed * cos;
        return new double[] { posX, posZ };
    }
    
    static {
        Boost.speed = new NumberSetting("speed", 1.0f, 0.1f, 1.0f, true);
        Boost.minVelocity = new NumberSetting("minVelocity", 50000.0f, 0.1f, 100000.0f, true);
    }
}
