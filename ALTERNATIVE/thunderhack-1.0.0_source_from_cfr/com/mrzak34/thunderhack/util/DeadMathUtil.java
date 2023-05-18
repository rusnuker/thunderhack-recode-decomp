/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1657
 *  net.minecraft.class_2338
 *  net.minecraft.class_238
 *  net.minecraft.class_241
 *  net.minecraft.class_243
 *  net.minecraft.class_3532
 *  net.minecraft.class_746
 *  org.apache.commons.lang3.RandomUtils
 */
package com.mrzak34.thunderhack.util;

import com.mrzak34.thunderhack.module.modules.combat.AttackAura;
import com.mrzak34.thunderhack.module.modules.combat.DeadAura;
import com.mrzak34.thunderhack.util.RotationUtil;
import com.mrzak34.thunderhack.util.Util;
import com.mrzak34.thunderhack.util.csUtil;
import com.mrzak34.thunderhack.util.efUtil;
import java.util.Random;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_241;
import net.minecraft.class_243;
import net.minecraft.class_3532;
import net.minecraft.class_746;
import org.apache.commons.lang3.RandomUtils;

public class DeadMathUtil
implements Util {
    public static double a;

    public static float a(class_1297 entity) {
        float f2 = entity.method_36454();
        return f2 *= (float)Math.PI / 180;
    }

    public static float[] a(class_1297 entity, DeadAura.Hitbox cy_02, float f2) {
        double d2;
        class_746 entityPlayerSP = DeadMathUtil.mc.field_1724;
        double d3 = entity.method_23317() - entityPlayerSP.method_23317();
        double d4 = entity.method_23321() - entityPlayerSP.method_23321();
        if (entity instanceof class_1657) {
            class_1657 entityLivingBase = (class_1657)entity;
            float f3 = DeadMathUtil.randomFloat((float)(entityLivingBase.method_23318() + entityLivingBase.method_23320() / 1.5), (float)(entityLivingBase.method_23318() + entityLivingBase.method_23320() - entityLivingBase.method_23320() / 3.0));
            d2 = (double)f3 - (entityPlayerSP.method_23318() + entityPlayerSP.method_23320());
        } else {
            d2 = (double)DeadMathUtil.randomFloat((float)entity.method_5829().field_1322, (float)entity.method_5829().field_1325) - (entityPlayerSP.method_23318() + entityPlayerSP.method_23320());
        }
        switch (cy_02) {
            case HEAD: {
                d2 += 0.6;
                break;
            }
            case TIGHS: {
                d2 -= 0.6;
            }
        }
        double d5 = Math.sqrt(d3 * d3 + d4 * d4);
        float f4 = (float)(Math.atan2(d4, d3) * 180.0 / Math.PI) - 90.0f;
        float f5 = (float)(-(Math.atan2(d2, d5) * 180.0 / Math.PI));
        if ((double)f2 > 0.0) {
            f4 += DeadMathUtil.randomFloat(-f2, f2);
            f5 += DeadMathUtil.randomFloat(-f2, f2);
        }
        float f6 = (float)((Double)DeadMathUtil.mc.field_1690.method_42495().method_41753() * (double)0.6f + (double)0.2f);
        float f7 = f6 * f6 * f6 * 1.2f;
        f4 -= f4 % f7;
        f5 -= f5 % f7;
        double d6 = entityPlayerSP.method_5739(entity);
        if (d6 < 0.5) {
            f5 = RandomUtils.nextInt((int)80, (int)90);
        }
        return new float[]{f4, f5};
    }

    public static float[] a(class_1297 entity, DeadAura.Hitbox cy_02) {
        double d2;
        double d3 = entity.method_23317() - DeadMathUtil.mc.field_1724.method_23317();
        double d4 = entity.method_23321() - DeadMathUtil.mc.field_1724.method_23321();
        if (entity instanceof class_1657) {
            class_1657 entityLivingBase = (class_1657)entity;
            d2 = entityLivingBase.method_23318() + entityLivingBase.method_23320() - (DeadMathUtil.mc.field_1724.method_23318() + DeadMathUtil.mc.field_1724.method_23320());
        } else {
            d2 = (entity.method_5829().field_1322 + entity.method_5829().field_1325) / 2.0 - (DeadMathUtil.mc.field_1724.method_23318() + DeadMathUtil.mc.field_1724.method_23320());
        }
        switch (cy_02) {
            case CHEST: {
                d2 -= 0.5;
                break;
            }
            case TIGHS: {
                d2 -= 1.4;
            }
        }
        double d5 = Math.sqrt(d3 * d3 + d4 * d4);
        float f2 = (float)(Math.atan2(d4, d3) * 180.0 / Math.PI) - 90.0f;
        float f3 = (float)(-(Math.atan2(d2, d5) * 180.0 / Math.PI));
        float f4 = f2;
        float f5 = f3;
        return new float[]{f4, f5};
    }

    public static double a(boolean bl) {
        class_746 player = DeadMathUtil.mc.field_1724;
        float f2 = player.field_6241;
        if (player.field_6250 < 0.0f) {
            f2 += 180.0f;
        }
        float f3 = 1.0f;
        if (player.field_6250 < 0.0f) {
            f3 = -0.5f;
        } else if (player.field_6250 > 0.0f) {
            f3 = 0.5f;
        }
        if (player.field_6212 > 0.0f) {
            f2 -= 90.0f * f3;
        }
        if (player.field_6212 < 0.0f) {
            f2 += 90.0f * f3;
        }
        return bl ? Math.toRadians(f2) : (double)f2;
    }

    public static void a(class_1297 entity, boolean bl, boolean bl2, float f2) {
        float[] fArray;
        float f;
        class_746 player = DeadMathUtil.mc.field_1724;
        double d2 = entity.method_23317() + (bl2 ? (entity.method_23317() - entity.field_6014) * (double)f2 : 0.0) - (player.method_23317() + (bl2 ? player.method_23317() - player.field_6014 : 0.0));
        double d3 = entity.method_5829().field_1322 + (bl2 ? (entity.method_5829().field_1322 - entity.field_6036) * (double)f2 : 0.0) + entity.method_23320() - 0.15 - (player.method_5829().field_1322 + (bl2 ? player.method_23318() - player.field_6036 : 0.0)) - player.method_23320();
        double d4 = entity.method_23321() + (bl2 ? (entity.method_23321() - entity.field_5969) * (double)f2 : 0.0) - (player.method_23321() + (bl2 ? player.method_23321() - player.field_5969 : 0.0));
        double d5 = Math.sqrt(d2 * d2 + d4 * d4);
        float f3 = (float)player.method_6048() / 20.0f;
        f3 = (f3 * f3 + f3 * 2.0f) / 3.0f;
        if (f > 1.0f) {
            f3 = 1.0f;
        }
        float f4 = (float)(Math.atan2(d4, d2) * 180.0 / Math.PI) - 90.0f;
        float f5 = (float)(-Math.toDegrees(Math.atan(((double)(f3 * f3) - Math.sqrt((double)(f3 * f3 * f3 * f3) - (double)0.006f * ((double)0.006f * (d5 * d5) + 2.0 * d3 * (double)(f3 * f3)))) / ((double)0.006f * d5))));
        if (f3 < 0.1f) {
            fArray = DeadMathUtil.a(DeadMathUtil.a(entity.method_5829()), true);
            f4 = fArray[0];
            f5 = fArray[1];
        }
        if (bl) {
            efUtil.a(f4, f5);
            DeadMathUtil.mc.field_1724.field_3932 = efUtil.d();
            DeadMathUtil.mc.field_1724.field_6241 = efUtil.d();
            return;
        }
        fArray = DeadMathUtil.a(new float[]{player.method_36454(), player.method_36455()}, new float[]{f4, f5}, (float)(10 + RandomUtils.nextInt((int)1, (int)5)));
        if (fArray == null) {
            return;
        }
        player.field_6241 = fArray[0];
        player.field_6004 = fArray[1];
    }

    public static float[] a(class_243 vec3d, boolean bl) {
        class_243 vec3d2 = DeadMathUtil.a();
        if (bl) {
            vec3d2.method_1031(DeadMathUtil.mc.field_1724.method_18798().field_1352, DeadMathUtil.mc.field_1724.method_18798().field_1351, DeadMathUtil.mc.field_1724.method_18798().field_1350);
        }
        double d2 = vec3d.field_1352 - vec3d2.field_1352;
        double d3 = vec3d.field_1351 - vec3d2.field_1351;
        double d4 = vec3d.field_1350 - vec3d2.field_1350;
        double d5 = Math.sqrt(d2 * d2 + d4 * d4);
        float f2 = (float)Math.toDegrees(Math.atan2(d4, d2)) - 90.0f;
        float f3 = (float)(-Math.toDegrees(Math.atan2(d3, d5)));
        return new float[]{class_3532.method_15393((float)f2), class_3532.method_15393((float)f3)};
    }

    public static class_243 a(class_238 axisAlignedBB) {
        return new class_243(axisAlignedBB.field_1323 + (axisAlignedBB.field_1320 - axisAlignedBB.field_1323) * 0.5, axisAlignedBB.field_1322 + (axisAlignedBB.field_1325 - axisAlignedBB.field_1322) * 0.5, axisAlignedBB.field_1321 + (axisAlignedBB.field_1324 - axisAlignedBB.field_1321) * 0.5);
    }

    public static boolean a(class_1297 entity, class_1297 entity2, double d2) {
        double d3 = DeadMathUtil.a(entity.method_36454(), DeadMathUtil.a(entity, entity2.method_23317(), entity2.method_23318(), entity2.method_23321())[0]);
        return d3 > 0.0 && d3 < (d2 *= 0.5) || -d2 < d3 && d3 < 0.0;
    }

    public static boolean a(class_1297 entity, double d2) {
        return DeadMathUtil.a((class_1297)DeadMathUtil.mc.field_1724, entity, d2);
    }

    public static boolean a(class_2338 blockPos, double d2) {
        double d3 = DeadMathUtil.a(DeadMathUtil.mc.field_1724.method_36454(), DeadMathUtil.a((class_1297)DeadMathUtil.mc.field_1724, blockPos.method_10263(), blockPos.method_10264(), (double)blockPos.method_10260())[0]);
        return d3 > 0.0 && d3 < (d2 *= 0.5) || -d2 < d3 && d3 < 0.0;
    }

    public static float a(float f2, float f3) {
        float f4 = Math.abs(f3 - f2) % 360.0f;
        float f5 = f4 > 180.0f ? 360.0f - f4 : f4;
        return f5;
    }

    public static float a(float f2, float f3, float f4) {
        float f;
        float f6 = class_3532.method_15393((float)(f3 - f2));
        if (f6 > f4) {
            f6 = f4;
        }
        if (f6 < -f4) {
            f6 = -f4;
        }
        float f5 = f2 + f6;
        if (f < 0.0f) {
            f5 += 360.0f;
        } else if (f5 > 360.0f) {
            f5 -= 360.0f;
        }
        return f5;
    }

    public static float b(float f2, float f3) {
        return ((f2 - f3) % 360.0f + 540.0f) % 360.0f - 180.0f;
    }

    public static float b(float f2, float f3, float f4) {
        float f5 = DeadMathUtil.b(f3, f2);
        return f2 + (f5 > f4 ? f4 : Math.max(f5, -f4));
    }

    public static float[] a(float[] fArray, float[] fArray2, float f2) {
        double d2 = DeadMathUtil.b(fArray2[0], fArray[0]);
        double d3 = DeadMathUtil.b(fArray2[1], fArray[1]);
        boolean bl = false;
        fArray[0] = fArray[0] + (float)(d2 > (double)f2 ? (double)f2 : (d2 < (double)(-f2) ? (double)(-f2) : d2));
        boolean bl2 = true;
        fArray[1] = fArray[1] + (float)(d3 > (double)f2 ? (double)f2 : (d3 < (double)(-f2) ? (double)(-f2) : d3));
        return fArray;
    }

    public static float[] d(class_1297 entity) {
        return DeadMathUtil.a(entity, 6.5f);
    }

    public static float[] a(class_1297 entity, float f2) {
        float f3 = f2 / DeadMathUtil.mc.field_1724.method_5739(entity);
        float f4 = 6.0f;
        return new float[]{f3 * entity.method_17681() * f4, f3 * entity.method_17682() * f4};
    }

    public static float c(float f2, float f3) {
        float f4 = f3 - f2;
        if (f4 > 180.0f) {
            f4 = -(360.0f - f3 + f2);
        } else if (f4 < -180.0f) {
            f4 = 360.0f - f2 + f3;
        }
        return f4;
    }

    public static double d(float f2, float f3) {
        return Math.sqrt(Math.pow(Math.abs(DeadMathUtil.b(efUtil.d() % 360.0f, f2)), 2.0) + Math.pow(Math.abs(DeadMathUtil.b(efUtil.e(), f3)), 2.0));
    }

    public static class_243 a() {
        return new class_243(csUtil.a.d(), csUtil.a.e() + DeadMathUtil.mc.field_1724.method_23320(), csUtil.a.f());
    }

    public static class_243 e(float f2, float f3) {
        float f4 = class_3532.method_15362((float)(-f3 * ((float)Math.PI / 180) - (float)Math.PI));
        float f5 = class_3532.method_15374((float)(-f3 * ((float)Math.PI / 180) - (float)Math.PI));
        float f6 = -class_3532.method_15362((float)(-f2 * ((float)Math.PI / 180)));
        float f7 = class_3532.method_15374((float)(-f2 * ((float)Math.PI / 180)));
        return new class_243((double)(f5 * f6), (double)f7, (double)(f4 * f6));
    }

    public static float c(float f2, float f3, float f4) {
        float f5 = class_3532.method_15393((float)(f3 - f2));
        if (f5 > f4) {
            f5 = f4;
        }
        if (f5 < -f4) {
            f5 = -f4;
        }
        return f2 + f5;
    }

    public static float[] a2(class_1297 entity, boolean bl) {
        class_241 rot = RotationUtil.lookAtEntity((class_1297)DeadMathUtil.mc.field_1724, entity);
        return new float[]{rot.field_1343, rot.field_1342};
    }

    public static float[] a(class_1297 entity, boolean bl) {
        class_746 player = DeadMathUtil.mc.field_1724;
        float f2 = bl ? player.method_36454() : efUtil.d();
        float f3 = bl ? player.method_36455() : efUtil.e();
        float f4 = 4.096f;
        double d2 = entity.method_23317() - player.method_23317();
        double d3 = entity.method_33571().field_1351 - player.method_33571().field_1351;
        double d4 = entity.method_23321() - player.method_23321();
        double d5 = Math.sqrt(Math.pow(d2, 2.0) + Math.pow(d4, 2.0));
        float f5 = (float)class_3532.method_15338((double)(Math.toDegrees(Math.atan2(d4, d2)) - 90.0));
        float f6 = (float)(-Math.toDegrees(Math.atan2(d3, d5))) + 10.0f;
        float f7 = class_3532.method_15393((float)(f5 - f2));
        float f8 = f6 - f3;
        int n2 = (int)Math.abs(f7);
        int n3 = (int)Math.abs(f8);
        float f9 = f2;
        float f10 = f3;
        if (n2 > 4 || n3 > 4) {
            if (f7 > 180.0f) {
                f7 -= 180.0f;
            }
            int n4 = (int)(Math.abs(Math.sin(a)) * 7.0);
            int n5 = (int)(Math.abs(Math.sin(a)) * 3.0);
            if (n2 < 10) {
                n4 = 0;
            }
            if (n3 < 10) {
                n5 = 0;
            }
            if (n4 == 0 && new Random().nextBoolean()) {
                n4 = 1;
            }
            f9 = f2 + (float)(f7 > 0.0f ? n4 : -n4) * f4;
            f10 = f3 + (float)(f8 > 0.0f ? n5 : -n5) * f4;
            a += new Random().nextDouble() * 0.19666;
        }
        return new float[]{f9, f10};
    }

    public static float[] a(class_1297 entity, AttackAura.Hitbox cy_02, float f2) {
        double d2;
        class_746 player = DeadMathUtil.mc.field_1724;
        double d3 = entity.method_23317() - player.method_23317();
        double d4 = entity.method_23321() - player.method_23321();
        if (entity instanceof class_1657) {
            class_1657 entityLivingBase = (class_1657)entity;
            float f3 = DeadMathUtil.randomFloat((float)(entityLivingBase.method_23318() + entityLivingBase.method_23320() / 1.5), (float)(entityLivingBase.method_23318() + entityLivingBase.method_23320() - entityLivingBase.method_23320() / 3.0));
            d2 = (double)f3 - (player.method_23318() + player.method_23320());
        } else {
            d2 = (double)DeadMathUtil.randomFloat((float)entity.method_5829().field_1322, (float)entity.method_5829().field_1325) - (player.method_23318() + player.method_23320());
        }
        switch (cy_02) {
            case HEAD: {
                d2 += 0.6;
                break;
            }
            case TIGHS: {
                d2 -= 0.6;
            }
        }
        double d5 = Math.sqrt(d3 * d3 + d4 * d4);
        float f4 = (float)(Math.atan2(d4, d3) * 180.0 / Math.PI) - 90.0f;
        float f5 = (float)(-(Math.atan2(d2, d5) * 180.0 / Math.PI));
        if ((double)f2 > 0.0) {
            f4 += DeadMathUtil.randomFloat(-f2, f2);
            f5 += DeadMathUtil.randomFloat(-f2, f2);
        }
        float f6 = (float)DeadMathUtil.mc.field_1690.method_42495().hashCode() * 0.6f + 0.2f;
        float f7 = f6 * f6 * f6 * 1.2f;
        f4 -= f4 % f7;
        f5 -= f5 % f7;
        double d6 = Math.sqrt(player.method_5649(entity.method_23317(), player.method_23318(), entity.method_23321()));
        if (d6 < 0.5) {
            f5 = RandomUtils.nextInt((int)80, (int)90);
        }
        return new float[]{f4, f5};
    }

    public static float[] a(class_1297 entity, AttackAura.Hitbox cy_02) {
        double d2;
        double d3 = entity.method_23317() - DeadMathUtil.mc.field_1724.method_23317();
        double d4 = entity.method_23321() - DeadMathUtil.mc.field_1724.method_23321();
        if (entity instanceof class_1657) {
            class_1657 entityLivingBase = (class_1657)entity;
            d2 = entityLivingBase.method_23318() + entityLivingBase.method_23320() - (DeadMathUtil.mc.field_1724.method_23318() + DeadMathUtil.mc.field_1724.method_23320());
        } else {
            d2 = (entity.method_5829().field_1322 + entity.method_5829().field_1325) / 2.0 - (DeadMathUtil.mc.field_1724.method_23318() + DeadMathUtil.mc.field_1724.method_23320());
        }
        switch (cy_02) {
            case CHEST: {
                d2 -= 0.5;
                break;
            }
            case TIGHS: {
                d2 -= 1.4;
            }
        }
        double d5 = Math.sqrt(d3 * d3 + d4 * d4);
        float f2 = (float)(Math.atan2(d4, d3) * 180.0 / Math.PI) - 90.0f;
        float f3 = (float)(-(Math.atan2(d2, d5) * 180.0 / Math.PI));
        float f4 = f2;
        float f5 = f3;
        return new float[]{f4, f5};
    }

    public static float[] a(class_1297 entity, double d2, double d3, double d4) {
        double d5 = d2 + 0.5 - entity.method_23317();
        double d6 = d3 - entity.method_23318();
        double d7 = d4 + 0.5 - entity.method_23321();
        double d8 = Math.sqrt(d5 * d5 + d7 * d7);
        float f2 = (float)(Math.atan2(d7, d5) * 180.0 / Math.PI) - 90.0f;
        float f3 = (float)(-(Math.atan2(d6, d8) * 180.0 / Math.PI));
        return new float[]{f2, f3};
    }

    public static float[] a(class_2338 blockPos) {
        double d2 = (double)blockPos.method_10263() + 0.5 - csUtil.a.d();
        double d3 = (double)blockPos.method_10264() + 0.5 - (DeadMathUtil.mc.field_1724.method_5829().field_1322 + DeadMathUtil.mc.field_1724.method_23320());
        double d4 = (double)blockPos.method_10260() + 0.5 - csUtil.a.f();
        double d5 = Math.sqrt(d2 * d2 + d4 * d4);
        float f2 = (float)(Math.atan2(d4, d2) * 180.0 / Math.PI) - 90.0f;
        float f3 = (float)(-(Math.atan2(d3, d5) * 180.0 / Math.PI));
        float f4 = (float)DeadMathUtil.mc.field_1690.method_42495().hashCode() * 0.6f + 0.2f;
        float f5 = f4 * f4 * f4 * 1.2f;
        f2 -= f2 % f5;
        f3 -= f3 % f5;
        return new float[]{f2, f3};
    }

    public static float randomFloat(float f2, float f3) {
        if (f2 == f3 || f3 - f2 <= 0.0f) {
            return f2;
        }
        return (float)((double)f2 + (double)(f3 - f2) * Math.random());
    }
}

