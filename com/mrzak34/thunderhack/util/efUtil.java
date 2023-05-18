//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.util;

import net.minecraft.*;
import com.mrzak34.thunderhack.mixin.*;

public class efUtil implements Util
{
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
    
    public static boolean a(final Object object) {
        if (object instanceof class_2828) {
            final class_2828 playerMoveC2SPacket = (class_2828)object;
            try {
                double field = ((IPlayerMoveC2SPacket)playerMoveC2SPacket).getX();
                double field2 = ((IPlayerMoveC2SPacket)playerMoveC2SPacket).getY();
                double field3 = ((IPlayerMoveC2SPacket)playerMoveC2SPacket).getZ();
                boolean field4 = ((IPlayerMoveC2SPacket)playerMoveC2SPacket).getOnGround();
                double field5 = ((IPlayerMoveC2SPacket)playerMoveC2SPacket).getYaw();
                double field6 = ((IPlayerMoveC2SPacket)playerMoveC2SPacket).getPitch();
                if (efUtil.a) {
                    field = efUtil.e;
                    field2 = efUtil.f;
                    field3 = efUtil.g;
                }
                if (efUtil.b) {
                    field5 = efUtil.i;
                    field6 = efUtil.j;
                }
                if (efUtil.c) {
                    field4 = efUtil.l;
                }
                efUtil.d = field4;
            }
            catch (Exception ex) {}
        }
        return true;
    }
    
    public static double a() {
        return efUtil.h[0];
    }
    
    public static double b() {
        return efUtil.h[1];
    }
    
    public static double c() {
        return efUtil.h[2];
    }
    
    public static float d() {
        return efUtil.k[0];
    }
    
    public static float e() {
        return efUtil.k[1];
    }
    
    public static float[] f() {
        return efUtil.k;
    }
    
    public static double[] g() {
        return efUtil.h;
    }
    
    public static boolean h() {
        return efUtil.d;
    }
    
    public static void a(final boolean bl) {
        efUtil.l = bl;
        efUtil.c = true;
    }
    
    public static void a(final double d2, final double d3, final double d4, final float f2, final float f3) {
        a(d2, d3, d4);
        a(f2, f3);
    }
    
    public static void a(final float[] fArray) {
        a(fArray[0], fArray[1]);
        efUtil.mc.field_1724.field_3932 = fArray[0];
        efUtil.mc.field_1724.field_6241 = fArray[0];
    }
    
    public static void a(final float f2, final float f3) {
        if (Double.isNaN(f2) || Double.isNaN(f3)) {
            return;
        }
        efUtil.i = f2;
        efUtil.j = f3;
        efUtil.b = true;
    }
    
    public static void a(final double d2, final double d3, final double d4) {
        if (Double.isNaN(d2) || Double.isNaN(d3) || Double.isNaN(d4)) {
            return;
        }
        efUtil.e = d2;
        efUtil.f = d3;
        efUtil.g = d4;
        efUtil.a = true;
    }
    
    public static void i() {
        j();
        k();
        l();
    }
    
    public static void j() {
        efUtil.b = false;
        efUtil.i = 0.0f;
        efUtil.j = 0.0f;
    }
    
    public static void k() {
        efUtil.a = false;
        efUtil.e = 0.0;
        efUtil.f = 0.0;
        efUtil.g = 0.0;
    }
    
    public static void l() {
        efUtil.c = false;
        efUtil.l = efUtil.mc.field_1724.method_24828();
    }
    
    static {
        efUtil.h = new double[] { 0.0, 0.0, 0.0 };
        efUtil.k = new float[] { 0.0f, 0.0f };
    }
}
