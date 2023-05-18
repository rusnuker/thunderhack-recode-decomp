/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1657
 *  net.minecraft.class_238
 *  net.minecraft.class_243
 *  net.minecraft.class_640
 */
package com.mrzak34.thunderhack.util;

import com.mrzak34.thunderhack.util.Util;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_640;

public class PlayerUtil
implements Util {
    public static boolean playerHere(class_243 sound) {
        try {
            for (class_1657 player : PlayerUtil.mc.field_1687.method_18456()) {
                if (player != PlayerUtil.mc.field_1687.method_8335(null, new class_238(sound.field_1352 - 1.0, sound.field_1351 - 1.0, sound.field_1350 - 1.0, sound.field_1352 + 1.0, sound.field_1351 + 2.0, sound.field_1350 + 1.0))) continue;
                return false;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return true;
    }

    public static boolean thereIsSomeoneHere(class_243 sound, float radius) {
        for (class_1657 player : PlayerUtil.mc.field_1687.method_18456()) {
            class_238 box = new class_238(player.method_23317() - (double)radius, player.method_23318() - (double)radius, player.method_23321() - (double)radius, player.method_23317() + (double)radius, player.method_23318() + (double)radius, player.method_23321() + (double)radius);
            class_238 box2 = new class_238(player.field_6038 - (double)radius, player.field_5971 - (double)radius, player.field_5989 - (double)radius, player.field_6038 + (double)radius, player.field_5971 + (double)radius, player.field_5989 + (double)radius);
            if (!box.method_993(sound, sound) && !box2.method_993(sound, sound)) continue;
            return false;
        }
        return true;
    }

    public static boolean isPlayerRendering(class_640 playerListEntry) {
        AtomicReference<Boolean> isrendering = new AtomicReference<Boolean>(false);
        Objects.requireNonNull(PlayerUtil.mc.field_1687).method_18456().forEach(player -> isrendering.set(playerListEntry.method_2966().getId().equals(player.method_5667())));
        return isrendering.get();
    }

    public static boolean playerHereWithPos(class_243 sound, float radius) {
        try {
            for (class_1297 entity : PlayerUtil.mc.field_1687.method_8335(null, new class_238(sound.field_1352 - (double)radius, sound.field_1351 - 0.5, sound.field_1350 - (double)radius, sound.field_1352 + (double)radius, sound.field_1351 + 2.0, sound.field_1350 + (double)radius))) {
                if (!(entity instanceof class_1657)) continue;
                return false;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return true;
    }

    public static boolean isMoving() {
        return PlayerUtil.mc.field_1724.field_3913.field_3905 > 0.0f || PlayerUtil.mc.field_1724.field_3913.field_3907 > 0.0f;
    }
}

