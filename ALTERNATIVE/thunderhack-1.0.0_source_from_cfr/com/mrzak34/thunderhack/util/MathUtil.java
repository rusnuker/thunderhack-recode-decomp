/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_243
 *  net.minecraft.class_3532
 */
package com.mrzak34.thunderhack.util;

import com.mrzak34.thunderhack.util.Util;
import net.minecraft.class_243;
import net.minecraft.class_3532;

public class MathUtil {
    public static double degToRad(double deg) {
        return deg * 0.01745329238474369;
    }

    public static class_243 direction(float yaw) {
        return new class_243(Math.cos(MathUtil.degToRad(yaw + 90.0f)), 0.0, Math.sin(MathUtil.degToRad(yaw + 90.0f)));
    }

    public static float[] calcAngle(class_243 from, class_243 to) {
        double difX = to.field_1352 - from.field_1352;
        double difY = (to.field_1351 - from.field_1351) * -1.0;
        double difZ = to.field_1350 - from.field_1350;
        double dist = class_3532.method_15355((float)((float)(difX * difX + difZ * difZ)));
        return new float[]{(float)class_3532.method_15338((double)(Math.toDegrees(Math.atan2(difZ, difX)) - 90.0)), (float)class_3532.method_15338((double)Math.toDegrees(Math.atan2(difY, dist)))};
    }

    public static double roundToDecimal(double n, int point) {
        if (point == 0) {
            return Math.floor(n);
        }
        double factor = Math.pow(10.0, point);
        return (double)Math.round(n * factor) / factor;
    }

    public static double square(double value) {
        return value * value;
    }

    public static double[] directionSpeed(double speed) {
        float forward = Util.mc.field_1724.field_3913.field_3905;
        float side = Util.mc.field_1724.field_3913.field_3907;
        float yaw = Util.mc.field_1724.field_5982 + (Util.mc.field_1724.method_36454() - Util.mc.field_1724.field_5982) * Util.mc.method_1488();
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
        side = class_3532.method_15363((float)side, (float)-1.0f, (float)1.0f);
        double sin = Math.sin(Math.toRadians(yaw + 90.0f));
        double cos = Math.cos(Math.toRadians(yaw + 90.0f));
        double posX = (double)forward * speed * cos + (double)side * speed * sin;
        double posZ = (double)forward * speed * sin - (double)side * speed * cos;
        return new double[]{posX, posZ};
    }
}

