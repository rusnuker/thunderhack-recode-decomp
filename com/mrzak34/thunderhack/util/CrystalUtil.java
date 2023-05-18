//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.util;

import java.util.*;
import net.minecraft.*;

public class CrystalUtil implements Util
{
    public static boolean needCrystal(final class_1657 target, final double minDMG) {
        for (final class_1297 crystal : CrystalUtil.mc.field_1687.method_18112()) {
            if (crystal instanceof class_1511 && calculateDamage(crystal.method_19538(), 6.0f, (class_1309)target) >= minDMG) {
                return false;
            }
        }
        return true;
    }
    
    public static class_1511 getCrystalUnderHere(final class_2338 pos) {
        for (final class_1297 entity : CrystalUtil.mc.field_1687.method_8335((class_1297)null, new class_238((double)pos.method_10263(), (double)(pos.method_10264() + 1), (double)pos.method_10260(), (double)(pos.method_10263() + 1), (double)(pos.method_10264() + 2), (double)(pos.method_10260() + 1)))) {
            if (entity instanceof class_1511) {
                return (class_1511)entity;
            }
        }
        return null;
    }
    
    public static void placeCrystal(final class_2338 blockPos, final class_2350 direction, final boolean packet) {
        if (packet) {
            CrystalUtil.mc.field_1724.field_3944.method_2883((class_2596)new class_2885(class_1268.field_5808, new class_3965(BlockUtil.closestVec3d(blockPos), class_2350.field_11036, blockPos, true), 0));
        }
        else {
            CrystalUtil.mc.field_1761.method_2896(CrystalUtil.mc.field_1724, class_1268.field_5808, new class_3965(BlockUtil.closestVec3d(blockPos), class_2350.field_11036, blockPos, true));
        }
    }
    
    public static class_238 getBoundingBox(final class_2338 pos) {
        return new class_238((double)pos.method_10263(), (double)pos.method_10264(), (double)pos.method_10260(), (double)(pos.method_10263() + 1), (double)(pos.method_10264() + 1), (double)(pos.method_10260() + 1));
    }
    
    public static class_243 closestVec3d(final class_2338 blockPos) {
        return closestVec3d(getBoundingBox(blockPos));
    }
    
    private static class_243 closestVec3d(final class_238 box) {
        if (box == null) {
            return new class_243(0.0, 0.0, 0.0);
        }
        final class_243 eyePos = new class_243(CrystalUtil.mc.field_1724.method_23317(), CrystalUtil.mc.field_1724.method_23318() + CrystalUtil.mc.field_1724.method_18381(CrystalUtil.mc.field_1724.method_18376()), CrystalUtil.mc.field_1724.method_23321());
        final double x = class_3532.method_15350(eyePos.method_10216(), box.field_1323, box.field_1320);
        final double y = class_3532.method_15350(eyePos.method_10214(), box.field_1322, box.field_1325);
        final double z = class_3532.method_15350(eyePos.method_10215(), box.field_1321, box.field_1324);
        return new class_243(x, y, z);
    }
    
    public static float calculateDamage(final class_243 explosionPos, final float power, final class_1309 target) {
        if (CrystalUtil.mc.field_1687.method_8407() == class_1267.field_5801) {
            return 0.0f;
        }
        final double maxDist = power * 2.0f;
        if (!CrystalUtil.mc.field_1687.method_8335((class_1297)null, new class_238((double)class_3532.method_15357(explosionPos.field_1352 - maxDist - 1.0), (double)class_3532.method_15357(explosionPos.field_1351 - maxDist - 1.0), (double)class_3532.method_15357(explosionPos.field_1350 - maxDist - 1.0), (double)class_3532.method_15357(explosionPos.field_1352 + maxDist + 1.0), (double)class_3532.method_15357(explosionPos.field_1351 + maxDist + 1.0), (double)class_3532.method_15357(explosionPos.field_1350 + maxDist + 1.0))).contains(target)) {
            return 0.0f;
        }
        if (!target.method_5659() && !target.method_5655()) {
            final double distExposure = Math.sqrt(target.method_5707(explosionPos)) / maxDist;
            if (distExposure <= 1.0) {
                final double xDiff = target.method_23317() - explosionPos.field_1352;
                final double yDiff = target.method_23320() - explosionPos.field_1351;
                final double zDiff = target.method_23321() - explosionPos.field_1350;
                final double diff = Math.sqrt(xDiff * xDiff + yDiff * yDiff + zDiff * zDiff);
                if (diff != 0.0) {
                    final double exposure = class_1927.method_17752(explosionPos, (class_1297)target);
                    final double finalExposure = (1.0 - distExposure) * exposure;
                    float toDamage = (float)Math.floor((finalExposure * finalExposure + finalExposure) / 2.0 * 7.0 * maxDist + 1.0);
                    if (target instanceof class_1657) {
                        if (CrystalUtil.mc.field_1687.method_8407() == class_1267.field_5805) {
                            toDamage = Math.min(toDamage / 2.0f + 1.0f, toDamage);
                        }
                        else if (CrystalUtil.mc.field_1687.method_8407() == class_1267.field_5807) {
                            toDamage = toDamage * 3.0f / 2.0f;
                        }
                    }
                    toDamage = class_1280.method_5496(toDamage, (float)target.method_6096(), (float)target.method_5996(class_5134.field_23725).method_6194());
                    if (target.method_6059(class_1294.field_5907)) {
                        final int resistance = 25 - (target.method_6112(class_1294.field_5907).method_5578() + 1) * 5;
                        final float resistance_1 = toDamage * resistance;
                        toDamage = Math.max(resistance_1 / 25.0f, 0.0f);
                    }
                    if (toDamage <= 0.0f) {
                        toDamage = 0.0f;
                    }
                    else {
                        final int protAmount = class_1890.method_8219(target.method_5661(), new class_1282((class_6880)class_8111.field_42331));
                        if (protAmount > 0) {
                            toDamage = class_1280.method_5497(toDamage, (float)protAmount);
                        }
                    }
                    return toDamage;
                }
            }
        }
        return 0.0f;
    }
    
    public static boolean canPlace(final class_2338 basePos, final boolean threeten) {
        final class_2680 baseState = CrystalUtil.mc.field_1687.method_8320(basePos);
        if (baseState.method_26204() != class_2246.field_9987 && baseState.method_26204() != class_2246.field_10540) {
            return false;
        }
        final boolean oldPlace = !threeten;
        final class_2338 placePos = basePos.method_10084();
        return CrystalUtil.mc.field_1687.method_22347(placePos) && (!oldPlace || CrystalUtil.mc.field_1687.method_22347(placePos.method_10084())) && CrystalUtil.mc.field_1687.method_8335((class_1297)null, new class_238(placePos, placePos.method_10086(oldPlace ? 2 : 1))).isEmpty();
    }
    
    public static boolean canPlaceCrystal(final class_2338 blockPos, final boolean multiPlace, final boolean thirteen) {
        try {
            if (CrystalUtil.mc.field_1687.method_8320(blockPos).method_26204() != class_2246.field_9987 && CrystalUtil.mc.field_1687.method_8320(blockPos).method_26204() != class_2246.field_10540) {
                return false;
            }
            for (final class_1297 entity : CrystalUtil.mc.field_1687.method_8335((class_1297)null, new class_238(blockPos.method_10069(0, 1, 0)))) {
                if (!entity.method_31481()) {
                    if (multiPlace && entity instanceof class_1511) {
                        continue;
                    }
                    return false;
                }
            }
            if (!thirteen) {
                for (final class_1297 entity : CrystalUtil.mc.field_1687.method_8335((class_1297)null, new class_238(blockPos.method_10069(0, 2, 0)))) {
                    if (!entity.method_31481()) {
                        if (multiPlace && entity instanceof class_1511) {
                            continue;
                        }
                        return false;
                    }
                }
            }
        }
        catch (Exception exception) {
            return false;
        }
        return true;
    }
    
    public static boolean canPlaceCrystal2(final class_2338 blockPos, final boolean thirteen, final boolean specialEntityCheck) {
        final class_2338 boost = blockPos.method_10069(0, 1, 0);
        final class_2338 boost2 = blockPos.method_10069(0, 2, 0);
        try {
            if (CrystalUtil.mc.field_1687.method_8320(blockPos).method_26204() != class_2246.field_9987 && CrystalUtil.mc.field_1687.method_8320(blockPos).method_26204() != class_2246.field_10540) {
                return false;
            }
            if (CrystalUtil.mc.field_1687.method_8320(boost).method_26204() != class_2246.field_10124 || (CrystalUtil.mc.field_1687.method_8320(boost2).method_26204() != class_2246.field_10124 && !thirteen)) {
                return false;
            }
            if (!specialEntityCheck) {
                return CrystalUtil.mc.field_1687.method_8335((class_1297)null, new class_238(blockPos, blockPos.method_10086(thirteen ? 2 : 1))).isEmpty();
            }
            for (final Object entity : CrystalUtil.mc.field_1687.method_8335((class_1297)null, new class_238(blockPos, blockPos.method_10086(thirteen ? 2 : 1)))) {
                if (!(entity instanceof class_1511)) {
                    return false;
                }
            }
            for (final Object entity : CrystalUtil.mc.field_1687.method_8335((class_1297)null, new class_238(blockPos, blockPos.method_10086(thirteen ? 2 : 1)))) {
                if (!(entity instanceof class_1511)) {
                    return false;
                }
            }
            for (final Object entity : CrystalUtil.mc.field_1687.method_8335((class_1297)null, new class_238(blockPos, blockPos.method_10086(thirteen ? 2 : 1)))) {
                if (entity instanceof class_1511) {
                    return false;
                }
            }
        }
        catch (Exception ignored) {
            return false;
        }
        return true;
    }
}
