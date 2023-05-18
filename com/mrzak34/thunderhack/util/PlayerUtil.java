//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.util;

import java.util.concurrent.atomic.*;
import java.util.*;
import net.minecraft.*;

public class PlayerUtil implements Util
{
    public static boolean playerHere(final class_243 sound) {
        try {
            for (final class_1657 player : PlayerUtil.mc.field_1687.method_18456()) {
                if (player == PlayerUtil.mc.field_1687.method_8335((class_1297)null, new class_238(sound.field_1352 - 1.0, sound.field_1351 - 1.0, sound.field_1350 - 1.0, sound.field_1352 + 1.0, sound.field_1351 + 2.0, sound.field_1350 + 1.0))) {
                    return false;
                }
            }
        }
        catch (Exception ex) {}
        return true;
    }
    
    public static boolean thereIsSomeoneHere(final class_243 sound, final float radius) {
        for (final class_1657 player : PlayerUtil.mc.field_1687.method_18456()) {
            final class_238 box = new class_238(player.method_23317() - radius, player.method_23318() - radius, player.method_23321() - radius, player.method_23317() + radius, player.method_23318() + radius, player.method_23321() + radius);
            final class_238 box2 = new class_238(player.field_6038 - radius, player.field_5971 - radius, player.field_5989 - radius, player.field_6038 + radius, player.field_5971 + radius, player.field_5989 + radius);
            if (box.method_993(sound, sound) || box2.method_993(sound, sound)) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isPlayerRendering(final class_640 playerListEntry) {
        final AtomicReference<Boolean> isrendering = new AtomicReference<Boolean>(false);
        Objects.requireNonNull(PlayerUtil.mc.field_1687).method_18456().forEach(player -> isrendering.set(playerListEntry.method_2966().getId().equals(player.method_5667())));
        return isrendering.get();
    }
    
    public static boolean playerHereWithPos(final class_243 sound, final float radius) {
        try {
            for (final class_1297 entity : PlayerUtil.mc.field_1687.method_8335((class_1297)null, new class_238(sound.field_1352 - radius, sound.field_1351 - 0.5, sound.field_1350 - radius, sound.field_1352 + radius, sound.field_1351 + 2.0, sound.field_1350 + radius))) {
                if (entity instanceof class_1657) {
                    return false;
                }
            }
        }
        catch (Exception ex) {}
        return true;
    }
    
    public static boolean isMoving() {
        return PlayerUtil.mc.field_1724.field_3913.field_3905 > 0.0f || PlayerUtil.mc.field_1724.field_3913.field_3907 > 0.0f;
    }
}
