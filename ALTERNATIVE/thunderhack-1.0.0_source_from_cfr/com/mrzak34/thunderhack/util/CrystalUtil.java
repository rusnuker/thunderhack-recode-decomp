/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1267
 *  net.minecraft.class_1268
 *  net.minecraft.class_1280
 *  net.minecraft.class_1282
 *  net.minecraft.class_1294
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_1511
 *  net.minecraft.class_1657
 *  net.minecraft.class_1890
 *  net.minecraft.class_1927
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_238
 *  net.minecraft.class_243
 *  net.minecraft.class_2596
 *  net.minecraft.class_2680
 *  net.minecraft.class_2885
 *  net.minecraft.class_3532
 *  net.minecraft.class_3965
 *  net.minecraft.class_5134
 *  net.minecraft.class_6880
 *  net.minecraft.class_8111
 */
package com.mrzak34.thunderhack.util;

import com.mrzak34.thunderhack.util.BlockUtil;
import com.mrzak34.thunderhack.util.Util;
import net.minecraft.class_1267;
import net.minecraft.class_1268;
import net.minecraft.class_1280;
import net.minecraft.class_1282;
import net.minecraft.class_1294;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1511;
import net.minecraft.class_1657;
import net.minecraft.class_1890;
import net.minecraft.class_1927;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2680;
import net.minecraft.class_2885;
import net.minecraft.class_3532;
import net.minecraft.class_3965;
import net.minecraft.class_5134;
import net.minecraft.class_6880;
import net.minecraft.class_8111;

public class CrystalUtil
implements Util {
    public static boolean needCrystal(class_1657 target, double minDMG) {
        for (class_1297 crystal : CrystalUtil.mc.field_1687.method_18112()) {
            if (!(crystal instanceof class_1511) || !((double)CrystalUtil.calculateDamage(crystal.method_19538(), 6.0f, (class_1309)target) >= minDMG)) continue;
            return false;
        }
        return true;
    }

    public static class_1511 getCrystalUnderHere(class_2338 pos) {
        for (class_1297 entity : CrystalUtil.mc.field_1687.method_8335(null, new class_238((double)pos.method_10263(), (double)(pos.method_10264() + 1), (double)pos.method_10260(), (double)(pos.method_10263() + 1), (double)(pos.method_10264() + 2), (double)(pos.method_10260() + 1)))) {
            if (!(entity instanceof class_1511)) continue;
            return (class_1511)entity;
        }
        return null;
    }

    public static void placeCrystal(class_2338 blockPos, class_2350 direction, boolean packet) {
        if (packet) {
            CrystalUtil.mc.field_1724.field_3944.method_2883((class_2596)new class_2885(class_1268.field_5808, new class_3965(BlockUtil.closestVec3d(blockPos), class_2350.field_11036, blockPos, true), 0));
        } else {
            CrystalUtil.mc.field_1761.method_2896(CrystalUtil.mc.field_1724, class_1268.field_5808, new class_3965(BlockUtil.closestVec3d(blockPos), class_2350.field_11036, blockPos, true));
        }
    }

    public static class_238 getBoundingBox(class_2338 pos) {
        return new class_238((double)pos.method_10263(), (double)pos.method_10264(), (double)pos.method_10260(), (double)(pos.method_10263() + 1), (double)(pos.method_10264() + 1), (double)(pos.method_10260() + 1));
    }

    public static class_243 closestVec3d(class_2338 blockPos) {
        return CrystalUtil.closestVec3d(CrystalUtil.getBoundingBox(blockPos));
    }

    private static class_243 closestVec3d(class_238 box) {
        if (box == null) {
            return new class_243(0.0, 0.0, 0.0);
        }
        class_243 eyePos = new class_243(CrystalUtil.mc.field_1724.method_23317(), CrystalUtil.mc.field_1724.method_23318() + (double)CrystalUtil.mc.field_1724.method_18381(CrystalUtil.mc.field_1724.method_18376()), CrystalUtil.mc.field_1724.method_23321());
        double x = class_3532.method_15350((double)eyePos.method_10216(), (double)box.field_1323, (double)box.field_1320);
        double y = class_3532.method_15350((double)eyePos.method_10214(), (double)box.field_1322, (double)box.field_1325);
        double z = class_3532.method_15350((double)eyePos.method_10215(), (double)box.field_1321, (double)box.field_1324);
        return new class_243(x, y, z);
    }

    public static float calculateDamage(class_243 explosionPos, float power, class_1309 target) {
        double zDiff;
        double yDiff;
        double xDiff;
        double diff;
        double distExposure;
        if (CrystalUtil.mc.field_1687.method_8407() == class_1267.field_5801) {
            return 0.0f;
        }
        double maxDist = power * 2.0f;
        if (!CrystalUtil.mc.field_1687.method_8335(null, new class_238((double)class_3532.method_15357((double)(explosionPos.field_1352 - maxDist - 1.0)), (double)class_3532.method_15357((double)(explosionPos.field_1351 - maxDist - 1.0)), (double)class_3532.method_15357((double)(explosionPos.field_1350 - maxDist - 1.0)), (double)class_3532.method_15357((double)(explosionPos.field_1352 + maxDist + 1.0)), (double)class_3532.method_15357((double)(explosionPos.field_1351 + maxDist + 1.0)), (double)class_3532.method_15357((double)(explosionPos.field_1350 + maxDist + 1.0)))).contains((Object)target)) {
            return 0.0f;
        }
        if (!target.method_5659() && !target.method_5655() && (distExposure = Math.sqrt(target.method_5707(explosionPos)) / maxDist) <= 1.0 && (diff = Math.sqrt((xDiff = target.method_23317() - explosionPos.field_1352) * xDiff + (yDiff = target.method_23320() - explosionPos.field_1351) * yDiff + (zDiff = target.method_23321() - explosionPos.field_1350) * zDiff)) != 0.0) {
            double exposure = class_1927.method_17752((class_243)explosionPos, (class_1297)target);
            double finalExposure = (1.0 - distExposure) * exposure;
            float toDamage = (float)Math.floor((finalExposure * finalExposure + finalExposure) / 2.0 * 7.0 * maxDist + 1.0);
            if (target instanceof class_1657) {
                if (CrystalUtil.mc.field_1687.method_8407() == class_1267.field_5805) {
                    toDamage = Math.min(toDamage / 2.0f + 1.0f, toDamage);
                } else if (CrystalUtil.mc.field_1687.method_8407() == class_1267.field_5807) {
                    toDamage = toDamage * 3.0f / 2.0f;
                }
            }
            toDamage = class_1280.method_5496((float)toDamage, (float)target.method_6096(), (float)((float)target.method_5996(class_5134.field_23725).method_6194()));
            if (target.method_6059(class_1294.field_5907)) {
                int resistance = 25 - (target.method_6112(class_1294.field_5907).method_5578() + 1) * 5;
                float resistance_1 = toDamage * (float)resistance;
                toDamage = Math.max(resistance_1 / 25.0f, 0.0f);
            }
            if (toDamage <= 0.0f) {
                toDamage = 0.0f;
            } else {
                int protAmount = class_1890.method_8219((Iterable)target.method_5661(), (class_1282)new class_1282((class_6880)class_8111.field_42331));
                if (protAmount > 0) {
                    toDamage = class_1280.method_5497((float)toDamage, (float)protAmount);
                }
            }
            return toDamage;
        }
        return 0.0f;
    }

    public static boolean canPlace(class_2338 basePos, boolean threeten) {
        class_2680 baseState = CrystalUtil.mc.field_1687.method_8320(basePos);
        if (baseState.method_26204() != class_2246.field_9987 && baseState.method_26204() != class_2246.field_10540) {
            return false;
        }
        boolean oldPlace = !threeten;
        class_2338 placePos = basePos.method_10084();
        if (!CrystalUtil.mc.field_1687.method_22347(placePos) || oldPlace && !CrystalUtil.mc.field_1687.method_22347(placePos.method_10084())) {
            return false;
        }
        return CrystalUtil.mc.field_1687.method_8335(null, new class_238(placePos, placePos.method_10086(oldPlace ? 2 : 1))).isEmpty();
    }

    public static boolean canPlaceCrystal(class_2338 blockPos, boolean multiPlace, boolean thirteen) {
        try {
            if (CrystalUtil.mc.field_1687.method_8320(blockPos).method_26204() != class_2246.field_9987 && CrystalUtil.mc.field_1687.method_8320(blockPos).method_26204() != class_2246.field_10540) {
                return false;
            }
            for (class_1297 entity : CrystalUtil.mc.field_1687.method_8335(null, new class_238(blockPos.method_10069(0, 1, 0)))) {
                if (entity.method_31481() || multiPlace && entity instanceof class_1511) continue;
                return false;
            }
            if (!thirteen) {
                for (class_1297 entity : CrystalUtil.mc.field_1687.method_8335(null, new class_238(blockPos.method_10069(0, 2, 0)))) {
                    if (entity.method_31481() || multiPlace && entity instanceof class_1511) continue;
                    return false;
                }
            }
        }
        catch (Exception exception) {
            return false;
        }
        return true;
    }

    public static boolean canPlaceCrystal2(class_2338 blockPos, boolean thirteen, boolean specialEntityCheck) {
        class_2338 boost = blockPos.method_10069(0, 1, 0);
        class_2338 boost2 = blockPos.method_10069(0, 2, 0);
        try {
            if (CrystalUtil.mc.field_1687.method_8320(blockPos).method_26204() != class_2246.field_9987 && CrystalUtil.mc.field_1687.method_8320(blockPos).method_26204() != class_2246.field_10540) {
                return false;
            }
            if (CrystalUtil.mc.field_1687.method_8320(boost).method_26204() != class_2246.field_10124 || CrystalUtil.mc.field_1687.method_8320(boost2).method_26204() != class_2246.field_10124 && !thirteen) {
                return false;
            }
            if (!specialEntityCheck) {
                return CrystalUtil.mc.field_1687.method_8335(null, new class_238(blockPos, blockPos.method_10086(thirteen ? 2 : 1))).isEmpty();
            }
            for (Object entity : CrystalUtil.mc.field_1687.method_8335(null, new class_238(blockPos, blockPos.method_10086(thirteen ? 2 : 1)))) {
                if (entity instanceof class_1511) continue;
                return false;
            }
            for (Object entity : CrystalUtil.mc.field_1687.method_8335(null, new class_238(blockPos, blockPos.method_10086(thirteen ? 2 : 1)))) {
                if (entity instanceof class_1511) continue;
                return false;
            }
            for (Object entity : CrystalUtil.mc.field_1687.method_8335(null, new class_238(blockPos, blockPos.method_10086(thirteen ? 2 : 1)))) {
                if (!(entity instanceof class_1511)) continue;
                return false;
            }
        }
        catch (Exception ignored) {
            return false;
        }
        return true;
    }
}

