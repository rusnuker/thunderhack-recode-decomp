/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.Subscribe
 *  net.minecraft.class_2743
 */
package com.mrzak34.thunderhack.module.modules.movement;

import com.google.common.eventbus.Subscribe;
import com.mrzak34.thunderhack.event.events.PacketEvent;
import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.BooleanSetting;
import com.mrzak34.thunderhack.settings.NumberSetting;
import net.minecraft.class_2743;

public class Boost
extends Module {
    public static NumberSetting speed = new NumberSetting("speed", 1.0f, 0.1f, 1.0f, true);
    public static NumberSetting minVelocity = new NumberSetting("minVelocity", 50000.0f, 0.1f, 100000.0f, true);
    public BooleanSetting up = new BooleanSetting("up", false);
    int velocity = 0;
    int upvelocity = 0;

    public Boost() {
        super("Boost", 0, false, Category.MOVEMENT);
        this.addSettings(speed, minVelocity, this.up);
    }

    @Subscribe
    public void onPacketReceive(PacketEvent.Receive event) {
        if (event.getPacket() instanceof class_2743 && ((class_2743)event.getPacket()).method_11818() == Boost.mc.field_1724.method_5628()) {
            class_2743 pack = (class_2743)event.getPacket();
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
            this.velocity = this.up.isEnabled() ? (this.velocity += vX + vY + vZ) : (this.velocity += vX + vZ);
        }
    }

    @Override
    public void onTick() {
        super.onTick();
        if ((double)this.velocity > minVelocity.getValue()) {
            double[] dir = Boost.directionSpeed((float)this.velocity / 8000.0f);
            Boost.mc.field_1724.method_5762(dir[0] * speed.getValue(), 0.0, dir[1] * speed.getValue());
            this.velocity = 0;
        }
    }

    public static double[] directionSpeed(double speed) {
        float forward = Boost.mc.field_1724.field_3913.field_3905;
        float side = Boost.mc.field_1724.field_3913.field_3907;
        float yaw = Boost.mc.field_1724.field_5982 + (Boost.mc.field_1724.field_6241 - Boost.mc.field_1724.field_5982) * mc.method_1488();
        if (forward != 0.0f) {
            if (side > 0.0f) {
                yaw += (float)(forward > 0.0f ? -45 : 45);
            } else if (side < 0.0f) {
                yaw += (float)(forward > 0.0f ? 45 : -45);
            }
            side = 0.0f;
            if (forward > 0.0f) {
                forward = 1.0f;
            } else if (forward < 0.0f) {
                forward = -1.0f;
            }
        }
        double sin = Math.sin(Math.toRadians(yaw + 90.0f));
        double cos = Math.cos(Math.toRadians(yaw + 90.0f));
        double posX = (double)forward * speed * cos + (double)side * speed * sin;
        double posZ = (double)forward * speed * sin - (double)side * speed * cos;
        return new double[]{posX, posZ};
    }
}

