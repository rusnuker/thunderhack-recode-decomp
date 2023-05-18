/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2828
 */
package com.mrzak34.thunderhack.util;

import com.mrzak34.thunderhack.mixin.IPlayerMoveC2SPacket;
import com.mrzak34.thunderhack.util.Util;
import net.minecraft.class_2828;

public class efUtil
implements Util {
    private static double e;
    private static double f;
    private static double g;
    public static boolean a;
    private static double[] h;
    private static float i;
    private static float j;
    public static boolean b;
    private static float[] k;
    private static boolean l;
    public static boolean c;
    public static boolean d;

    public static boolean a(Object object) {
        if (object instanceof class_2828) {
            class_2828 playerMoveC2SPacket = (class_2828)object;
            try {
                double field = ((IPlayerMoveC2SPacket)playerMoveC2SPacket).getX();
                double field2 = ((IPlayerMoveC2SPacket)playerMoveC2SPacket).getY();
                double field3 = ((IPlayerMoveC2SPacket)playerMoveC2SPacket).getZ();
                boolean field5 = ((IPlayerMoveC2SPacket)playerMoveC2SPacket).getOnGround();
                double field6 = ((IPlayerMoveC2SPacket)playerMoveC2SPacket).getYaw();
                double field7 = ((IPlayerMoveC2SPacket)playerMoveC2SPacket).getPitch();
                if (a) {
                    field = e;
                    field2 = f;
                    field3 = g;
                }
                if (b) {
                    field6 = i;
                    field7 = j;
                }
                if (c) {
                    field5 = l;
                }
                d = field5;
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        return true;
    }

    public static double a() {
        return h[0];
    }

    public static double b() {
        return h[1];
    }

    public static double c() {
        return h[2];
    }

    public static float d() {
        return k[0];
    }

    public static float e() {
        return k[1];
    }

    public static float[] f() {
        return k;
    }

    public static double[] g() {
        return h;
    }

    public static boolean h() {
        return d;
    }

    public static void a(boolean bl) {
        l = bl;
        c = true;
    }

    public static void a(double d2, double d3, double d4, float f2, float f3) {
        efUtil.a(d2, d3, d4);
        efUtil.a(f2, f3);
    }

    public static void a(float[] fArray) {
        efUtil.a(fArray[0], fArray[1]);
        efUtil.mc.field_1724.field_3932 = fArray[0];
        efUtil.mc.field_1724.field_6241 = fArray[0];
    }

    public static void a(float f2, float f3) {
        if (Double.isNaN(f2) || Double.isNaN(f3)) {
            return;
        }
        i = f2;
        j = f3;
        b = true;
    }

    public static void a(double d2, double d3, double d4) {
        if (Double.isNaN(d2) || Double.isNaN(d3) || Double.isNaN(d4)) {
            return;
        }
        e = d2;
        f = d3;
        g = d4;
        a = true;
    }

    public static void i() {
        efUtil.j();
        efUtil.k();
        efUtil.l();
    }

    public static void j() {
        b = false;
        i = 0.0f;
        j = 0.0f;
    }

    public static void k() {
        a = false;
        e = 0.0;
        f = 0.0;
        g = 0.0;
    }

    public static void l() {
        c = false;
        l = efUtil.mc.field_1724.method_24828();
    }

    static {
        h = new double[]{0.0, 0.0, 0.0};
        k = new float[]{0.0f, 0.0f};
    }
}

