//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.util;

import org.apache.commons.lang3.*;
import net.minecraft.*;
import java.util.*;
import com.mrzak34.thunderhack.module.modules.combat.*;

public class DeadMathUtil implements Util
{
    public static double a;
    
    public static float a(final class_1297 entity) {
        float f2 = entity.method_36454();
        return f2 *= 0.017453292f;
    }
    
    public static float[] a(final class_1297 entity, final DeadAura.Hitbox cy_02, final float f2) {
        final class_1657 entityPlayerSP = (class_1657)DeadMathUtil.mc.field_1724;
        final double d3 = entity.method_23317() - entityPlayerSP.method_23317();
        final double d4 = entity.method_23321() - entityPlayerSP.method_23321();
        double d5;
        if (entity instanceof class_1657) {
            final class_1657 entityLivingBase = (class_1657)entity;
            final float f3 = randomFloat((float)(entityLivingBase.method_23318() + entityLivingBase.method_23320() / 1.5), (float)(entityLivingBase.method_23318() + entityLivingBase.method_23320() - entityLivingBase.method_23320() / 3.0));
            d5 = f3 - (entityPlayerSP.method_23318() + entityPlayerSP.method_23320());
        }
        else {
            d5 = randomFloat((float)entity.method_5829().field_1322, (float)entity.method_5829().field_1325) - (entityPlayerSP.method_23318() + entityPlayerSP.method_23320());
        }
        switch (cy_02) {
            case HEAD: {
                d5 += 0.6;
                break;
            }
            case TIGHS: {
                d5 -= 0.6;
                break;
            }
        }
        final double d6 = Math.sqrt(d3 * d3 + d4 * d4);
        float f4 = (float)(Math.atan2(d4, d3) * 180.0 / 3.141592653589793) - 90.0f;
        float f5 = (float)(-(Math.atan2(d5, d6) * 180.0 / 3.141592653589793));
        if (f2 > 0.0) {
            f4 += randomFloat(-f2, f2);
            f5 += randomFloat(-f2, f2);
        }
        final float f6 = (float)((double)DeadMathUtil.mc.field_1690.method_42495().method_41753() * 0.6000000238418579 + 0.20000000298023224);
        final float f7 = f6 * f6 * f6 * 1.2f;
        f4 -= f4 % f7;
        f5 -= f5 % f7;
        final double d7 = entityPlayerSP.method_5739(entity);
        if (d7 < 0.5) {
            f5 = (float)RandomUtils.nextInt(80, 90);
        }
        return new float[] { f4, f5 };
    }
    
    public static float[] a(final class_1297 entity, final DeadAura.Hitbox cy_02) {
        final double d3 = entity.method_23317() - DeadMathUtil.mc.field_1724.method_23317();
        final double d4 = entity.method_23321() - DeadMathUtil.mc.field_1724.method_23321();
        double d5;
        if (entity instanceof class_1657) {
            final class_1657 entityLivingBase = (class_1657)entity;
            d5 = entityLivingBase.method_23318() + entityLivingBase.method_23320() - (DeadMathUtil.mc.field_1724.method_23318() + DeadMathUtil.mc.field_1724.method_23320());
        }
        else {
            d5 = (entity.method_5829().field_1322 + entity.method_5829().field_1325) / 2.0 - (DeadMathUtil.mc.field_1724.method_23318() + DeadMathUtil.mc.field_1724.method_23320());
        }
        switch (cy_02) {
            case CHEST: {
                d5 -= 0.5;
                break;
            }
            case TIGHS: {
                d5 -= 1.4;
                break;
            }
        }
        final double d6 = Math.sqrt(d3 * d3 + d4 * d4);
        final float f2 = (float)(Math.atan2(d4, d3) * 180.0 / 3.141592653589793) - 90.0f;
        final float f3 = (float)(-(Math.atan2(d5, d6) * 180.0 / 3.141592653589793));
        final float f4 = f2;
        final float f5 = f3;
        return new float[] { f4, f5 };
    }
    
    public static double a(final boolean bl) {
        final class_1657 player = (class_1657)DeadMathUtil.mc.field_1724;
        float f2 = player.field_6241;
        if (player.field_6250 < 0.0f) {
            f2 += 180.0f;
        }
        float f3 = 1.0f;
        if (player.field_6250 < 0.0f) {
            f3 = -0.5f;
        }
        else if (player.field_6250 > 0.0f) {
            f3 = 0.5f;
        }
        if (player.field_6212 > 0.0f) {
            f2 -= 90.0f * f3;
        }
        if (player.field_6212 < 0.0f) {
            f2 += 90.0f * f3;
        }
        return bl ? Math.toRadians(f2) : f2;
    }
    
    public static void a(final class_1297 entity, final boolean bl, final boolean bl2, final float f2) {
        final class_1657 player = (class_1657)DeadMathUtil.mc.field_1724;
        final double d2 = entity.method_23317() + (bl2 ? ((entity.method_23317() - entity.field_6014) * f2) : 0.0) - (player.method_23317() + (bl2 ? (player.method_23317() - player.field_6014) : 0.0));
        final double d3 = entity.method_5829().field_1322 + (bl2 ? ((entity.method_5829().field_1322 - entity.field_6036) * f2) : 0.0) + entity.method_23320() - 0.15 - (player.method_5829().field_1322 + (bl2 ? (player.method_23318() - player.field_6036) : 0.0)) - player.method_23320();
        final double d4 = entity.method_23321() + (bl2 ? ((entity.method_23321() - entity.field_5969) * f2) : 0.0) - (player.method_23321() + (bl2 ? (player.method_23321() - player.field_5969) : 0.0));
        final double d5 = Math.sqrt(d2 * d2 + d4 * d4);
        float f3 = player.method_6048() / 20.0f;
        if ((f3 = (f3 * f3 + f3 * 2.0f) / 3.0f) > 1.0f) {
            f3 = 1.0f;
        }
        float f4 = (float)(Math.atan2(d4, d2) * 180.0 / 3.141592653589793) - 90.0f;
        float f5 = (float)(-Math.toDegrees(Math.atan((f3 * f3 - Math.sqrt(f3 * f3 * f3 * f3 - 0.006000000052154064 * (0.006000000052154064 * (d5 * d5) + 2.0 * d3 * (f3 * f3)))) / (0.006000000052154064 * d5))));
        if (f3 < 0.1f) {
            final float[] fArray = a(a(entity.method_5829()), true);
            f4 = fArray[0];
            f5 = fArray[1];
        }
        if (bl) {
            efUtil.a(f4, f5);
            DeadMathUtil.mc.field_1724.field_3932 = efUtil.d();
            DeadMathUtil.mc.field_1724.field_6241 = efUtil.d();
            return;
        }
        final float[] fArray = a(new float[] { player.method_36454(), player.method_36455() }, new float[] { f4, f5 }, (float)(10 + RandomUtils.nextInt(1, 5)));
        if (fArray == null) {
            return;
        }
        player.field_6241 = fArray[0];
        player.field_6004 = fArray[1];
    }
    
    public static float[] a(final class_243 vec3d, final boolean bl) {
        final class_243 vec3d2 = a();
        if (bl) {
            vec3d2.method_1031(DeadMathUtil.mc.field_1724.method_18798().field_1352, DeadMathUtil.mc.field_1724.method_18798().field_1351, DeadMathUtil.mc.field_1724.method_18798().field_1350);
        }
        final double d2 = vec3d.field_1352 - vec3d2.field_1352;
        final double d3 = vec3d.field_1351 - vec3d2.field_1351;
        final double d4 = vec3d.field_1350 - vec3d2.field_1350;
        final double d5 = Math.sqrt(d2 * d2 + d4 * d4);
        final float f2 = (float)Math.toDegrees(Math.atan2(d4, d2)) - 90.0f;
        final float f3 = (float)(-Math.toDegrees(Math.atan2(d3, d5)));
        return new float[] { class_3532.method_15393(f2), class_3532.method_15393(f3) };
    }
    
    public static class_243 a(final class_238 axisAlignedBB) {
        return new class_243(axisAlignedBB.field_1323 + (axisAlignedBB.field_1320 - axisAlignedBB.field_1323) * 0.5, axisAlignedBB.field_1322 + (axisAlignedBB.field_1325 - axisAlignedBB.field_1322) * 0.5, axisAlignedBB.field_1321 + (axisAlignedBB.field_1324 - axisAlignedBB.field_1321) * 0.5);
    }
    
    public static boolean a(final class_1297 entity, final class_1297 entity2, double d2) {
        final double d3 = a(entity.method_36454(), a(entity, entity2.method_23317(), entity2.method_23318(), entity2.method_23321())[0]);
        return (d3 > 0.0 && d3 < (d2 *= 0.5)) || (-d2 < d3 && d3 < 0.0);
    }
    
    public static boolean a(final class_1297 entity, final double d2) {
        return a((class_1297)DeadMathUtil.mc.field_1724, entity, d2);
    }
    
    public static boolean a(final class_2338 blockPos, double d2) {
        final double d3 = a(DeadMathUtil.mc.field_1724.method_36454(), a((class_1297)DeadMathUtil.mc.field_1724, blockPos.method_10263(), blockPos.method_10264(), blockPos.method_10260())[0]);
        return (d3 > 0.0 && d3 < (d2 *= 0.5)) || (-d2 < d3 && d3 < 0.0);
    }
    
    public static float a(final float f2, final float f3) {
        final float f4 = Math.abs(f3 - f2) % 360.0f;
        final float f5 = (f4 > 180.0f) ? (360.0f - f4) : f4;
        return f5;
    }
    
    public static float a(final float f2, final float f3, final float f4) {
        float f5 = class_3532.method_15393(f3 - f2);
        if (f5 > f4) {
            f5 = f4;
        }
        if (f5 < -f4) {
            f5 = -f4;
        }
        float f6;
        if ((f6 = f2 + f5) < 0.0f) {
            f6 += 360.0f;
        }
        else if (f6 > 360.0f) {
            f6 -= 360.0f;
        }
        return f6;
    }
    
    public static float b(final float f2, final float f3) {
        return ((f2 - f3) % 360.0f + 540.0f) % 360.0f - 180.0f;
    }
    
    public static float b(final float f2, final float f3, final float f4) {
        final float f5 = b(f3, f2);
        return f2 + ((f5 > f4) ? f4 : Math.max(f5, -f4));
    }
    
    public static float[] a(final float[] fArray, final float[] fArray2, final float f2) {
        final double d2 = b(fArray2[0], fArray[0]);
        final double d3 = b(fArray2[1], fArray[1]);
        final boolean bl = false;
        fArray[0] += (float)((d2 > f2) ? f2 : ((d2 < -f2) ? (-f2) : d2));
        final boolean bl2 = true;
        fArray[1] += (float)((d3 > f2) ? f2 : ((d3 < -f2) ? (-f2) : d3));
        return fArray;
    }
    
    public static float[] d(final class_1297 entity) {
        return a(entity, 6.5f);
    }
    
    public static float[] a(final class_1297 entity, final float f2) {
        final float f3 = f2 / DeadMathUtil.mc.field_1724.method_5739(entity);
        final float f4 = 6.0f;
        return new float[] { f3 * entity.method_17681() * f4, f3 * entity.method_17682() * f4 };
    }
    
    public static float c(final float f2, final float f3) {
        float f4 = f3 - f2;
        if (f4 > 180.0f) {
            f4 = -(360.0f - f3 + f2);
        }
        else if (f4 < -180.0f) {
            f4 = 360.0f - f2 + f3;
        }
        return f4;
    }
    
    public static double d(final float f2, final float f3) {
        return Math.sqrt(Math.pow(Math.abs(b(efUtil.d() % 360.0f, f2)), 2.0) + Math.pow(Math.abs(b(efUtil.e(), f3)), 2.0));
    }
    
    public static class_243 a() {
        return new class_243(csUtil.a.d(), csUtil.a.e() + DeadMathUtil.mc.field_1724.method_23320(), csUtil.a.f());
    }
    
    public static class_243 e(final float f2, final float f3) {
        final float f4 = class_3532.method_15362(-f3 * 0.017453292f - 3.1415927f);
        final float f5 = class_3532.method_15374(-f3 * 0.017453292f - 3.1415927f);
        final float f6 = -class_3532.method_15362(-f2 * 0.017453292f);
        final float f7 = class_3532.method_15374(-f2 * 0.017453292f);
        return new class_243((double)(f5 * f6), (double)f7, (double)(f4 * f6));
    }
    
    public static float c(final float f2, final float f3, final float f4) {
        float f5 = class_3532.method_15393(f3 - f2);
        if (f5 > f4) {
            f5 = f4;
        }
        if (f5 < -f4) {
            f5 = -f4;
        }
        return f2 + f5;
    }
    
    public static float[] a2(final class_1297 entity, final boolean bl) {
        final class_241 rot = RotationUtil.lookAtEntity((class_1297)DeadMathUtil.mc.field_1724, entity);
        return new float[] { rot.field_1343, rot.field_1342 };
    }
    
    public static float[] a(final class_1297 entity, final boolean bl) {
        final class_1657 player = (class_1657)DeadMathUtil.mc.field_1724;
        final float f2 = bl ? player.method_36454() : efUtil.d();
        final float f3 = bl ? player.method_36455() : efUtil.e();
        final float f4 = 4.096f;
        final double d2 = entity.method_23317() - player.method_23317();
        final double d3 = entity.method_33571().field_1351 - player.method_33571().field_1351;
        final double d4 = entity.method_23321() - player.method_23321();
        final double d5 = Math.sqrt(Math.pow(d2, 2.0) + Math.pow(d4, 2.0));
        final float f5 = (float)class_3532.method_15338(Math.toDegrees(Math.atan2(d4, d2)) - 90.0);
        final float f6 = (float)(-Math.toDegrees(Math.atan2(d3, d5))) + 10.0f;
        float f7 = class_3532.method_15393(f5 - f2);
        final float f8 = f6 - f3;
        final int n2 = (int)Math.abs(f7);
        final int n3 = (int)Math.abs(f8);
        float f9 = f2;
        float f10 = f3;
        if (n2 > 4 || n3 > 4) {
            if (f7 > 180.0f) {
                f7 -= 180.0f;
            }
            int n4 = (int)(Math.abs(Math.sin(DeadMathUtil.a)) * 7.0);
            int n5 = (int)(Math.abs(Math.sin(DeadMathUtil.a)) * 3.0);
            if (n2 < 10) {
                n4 = 0;
            }
            if (n3 < 10) {
                n5 = 0;
            }
            if (n4 == 0 && new Random().nextBoolean()) {
                n4 = 1;
            }
            f9 = f2 + ((f7 > 0.0f) ? n4 : (-n4)) * f4;
            f10 = f3 + ((f8 > 0.0f) ? n5 : (-n5)) * f4;
            DeadMathUtil.a += new Random().nextDouble() * 0.19666;
        }
        return new float[] { f9, f10 };
    }
    
    public static float[] a(final class_1297 entity, final AttackAura.Hitbox cy_02, final float f2) {
        final class_1657 player = (class_1657)DeadMathUtil.mc.field_1724;
        final double d3 = entity.method_23317() - player.method_23317();
        final double d4 = entity.method_23321() - player.method_23321();
        double d5;
        if (entity instanceof class_1657) {
            final class_1657 entityLivingBase = (class_1657)entity;
            final float f3 = randomFloat((float)(entityLivingBase.method_23318() + entityLivingBase.method_23320() / 1.5), (float)(entityLivingBase.method_23318() + entityLivingBase.method_23320() - entityLivingBase.method_23320() / 3.0));
            d5 = f3 - (player.method_23318() + player.method_23320());
        }
        else {
            d5 = randomFloat((float)entity.method_5829().field_1322, (float)entity.method_5829().field_1325) - (player.method_23318() + player.method_23320());
        }
        switch (cy_02) {
            case HEAD: {
                d5 += 0.6;
                break;
            }
            case TIGHS: {
                d5 -= 0.6;
                break;
            }
        }
        final double d6 = Math.sqrt(d3 * d3 + d4 * d4);
        float f4 = (float)(Math.atan2(d4, d3) * 180.0 / 3.141592653589793) - 90.0f;
        float f5 = (float)(-(Math.atan2(d5, d6) * 180.0 / 3.141592653589793));
        if (f2 > 0.0) {
            f4 += randomFloat(-f2, f2);
            f5 += randomFloat(-f2, f2);
        }
        final float f6 = DeadMathUtil.mc.field_1690.method_42495().hashCode() * 0.6f + 0.2f;
        final float f7 = f6 * f6 * f6 * 1.2f;
        f4 -= f4 % f7;
        f5 -= f5 % f7;
        final double d7 = Math.sqrt(player.method_5649(entity.method_23317(), player.method_23318(), entity.method_23321()));
        if (d7 < 0.5) {
            f5 = (float)RandomUtils.nextInt(80, 90);
        }
        return new float[] { f4, f5 };
    }
    
    public static float[] a(final class_1297 entity, final AttackAura.Hitbox cy_02) {
        final double d3 = entity.method_23317() - DeadMathUtil.mc.field_1724.method_23317();
        final double d4 = entity.method_23321() - DeadMathUtil.mc.field_1724.method_23321();
        double d5;
        if (entity instanceof class_1657) {
            final class_1657 entityLivingBase = (class_1657)entity;
            d5 = entityLivingBase.method_23318() + entityLivingBase.method_23320() - (DeadMathUtil.mc.field_1724.method_23318() + DeadMathUtil.mc.field_1724.method_23320());
        }
        else {
            d5 = (entity.method_5829().field_1322 + entity.method_5829().field_1325) / 2.0 - (DeadMathUtil.mc.field_1724.method_23318() + DeadMathUtil.mc.field_1724.method_23320());
        }
        switch (cy_02) {
            case CHEST: {
                d5 -= 0.5;
                break;
            }
            case TIGHS: {
                d5 -= 1.4;
                break;
            }
        }
        final double d6 = Math.sqrt(d3 * d3 + d4 * d4);
        final float f2 = (float)(Math.atan2(d4, d3) * 180.0 / 3.141592653589793) - 90.0f;
        final float f3 = (float)(-(Math.atan2(d5, d6) * 180.0 / 3.141592653589793));
        final float f4 = f2;
        final float f5 = f3;
        return new float[] { f4, f5 };
    }
    
    public static float[] a(final class_1297 entity, final double d2, final double d3, final double d4) {
        final double d5 = d2 + 0.5 - entity.method_23317();
        final double d6 = d3 - entity.method_23318();
        final double d7 = d4 + 0.5 - entity.method_23321();
        final double d8 = Math.sqrt(d5 * d5 + d7 * d7);
        final float f2 = (float)(Math.atan2(d7, d5) * 180.0 / 3.141592653589793) - 90.0f;
        final float f3 = (float)(-(Math.atan2(d6, d8) * 180.0 / 3.141592653589793));
        return new float[] { f2, f3 };
    }
    
    public static float[] a(final class_2338 blockPos) {
        final double d2 = blockPos.method_10263() + 0.5 - csUtil.a.d();
        final double d3 = blockPos.method_10264() + 0.5 - (DeadMathUtil.mc.field_1724.method_5829().field_1322 + DeadMathUtil.mc.field_1724.method_23320());
        final double d4 = blockPos.method_10260() + 0.5 - csUtil.a.f();
        final double d5 = Math.sqrt(d2 * d2 + d4 * d4);
        float f2 = (float)(Math.atan2(d4, d2) * 180.0 / 3.141592653589793) - 90.0f;
        float f3 = (float)(-(Math.atan2(d3, d5) * 180.0 / 3.141592653589793));
        final float f4 = DeadMathUtil.mc.field_1690.method_42495().hashCode() * 0.6f + 0.2f;
        final float f5 = f4 * f4 * f4 * 1.2f;
        f2 -= f2 % f5;
        f3 -= f3 % f5;
        return new float[] { f2, f3 };
    }
    
    public static float randomFloat(final float f2, final float f3) {
        if (f2 == f3 || f3 - f2 <= 0.0f) {
            return f2;
        }
        return (float)(f2 + (f3 - f2) * Math.random());
    }
}
