//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.util;

import net.minecraft.*;

public class MathUtil
{
    public static double degToRad(final double deg) {
        return deg * 0.01745329238474369;
    }
    
    public static class_243 direction(final float yaw) {
        return new class_243(Math.cos(degToRad(yaw + 90.0f)), 0.0, Math.sin(degToRad(yaw + 90.0f)));
    }
    
    public static float[] calcAngle(final class_243 from, final class_243 to) {
        final double difX = to.field_1352 - from.field_1352;
        final double difY = (to.field_1351 - from.field_1351) * -1.0;
        final double difZ = to.field_1350 - from.field_1350;
        final double dist = class_3532.method_15355((float)(difX * difX + difZ * difZ));
        return new float[] { (float)class_3532.method_15338(Math.toDegrees(Math.atan2(difZ, difX)) - 90.0), (float)class_3532.method_15338(Math.toDegrees(Math.atan2(difY, dist))) };
    }
    
    public static double roundToDecimal(final double n, final int point) {
        if (point == 0) {
            return Math.floor(n);
        }
        final double factor = Math.pow(10.0, point);
        return Math.round(n * factor) / factor;
    }
    
    public static double square(final double value) {
        return value * value;
    }
    
    public static double[] directionSpeed(final double speed) {
        float forward = Util.mc.field_1724.field_3913.field_3905;
        float side = Util.mc.field_1724.field_3913.field_3907;
        float yaw = Util.mc.field_1724.field_5982 + (Util.mc.field_1724.method_36454() - Util.mc.field_1724.field_5982) * Util.mc.method_1488();
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
        side = class_3532.method_15363(side, -1.0f, 1.0f);
        final double sin = Math.sin(Math.toRadians(yaw + 90.0f));
        final double cos = Math.cos(Math.toRadians(yaw + 90.0f));
        final double posX = forward * speed * cos + side * speed * sin;
        final double posZ = forward * speed * sin - side * speed * cos;
        return new double[] { posX, posZ };
    }
}
