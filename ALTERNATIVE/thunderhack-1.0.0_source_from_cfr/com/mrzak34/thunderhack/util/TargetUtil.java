/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1657
 */
package com.mrzak34.thunderhack.util;

import com.mrzak34.thunderhack.util.FriendUtil;
import com.mrzak34.thunderhack.util.Util;
import net.minecraft.class_1297;
import net.minecraft.class_1657;

public class TargetUtil
implements Util {
    private static class_1657 entityPlayer;

    public static class_1657 getTarget(double range) {
        return TargetUtil.mc.field_1687.method_18456().size() == 0 ? null : (class_1657)TargetUtil.mc.field_1687.method_18456().stream().filter(entityPlayer -> entityPlayer != TargetUtil.mc.field_1724).filter(entityPlayer -> !entityPlayer.method_7337()).filter(entityPlayer -> (double)TargetUtil.mc.field_1724.method_5739((class_1297)entityPlayer) <= range).filter(entityPlayer -> !FriendUtil.isFriend(entityPlayer.method_5477().getString())).findFirst().orElse(null);
    }

    public static class_1657 getTargetWithPlayer(class_1657 player, double range) {
        return TargetUtil.mc.field_1687.method_18456().size() == 0 ? null : (class_1657)TargetUtil.mc.field_1687.method_18456().stream().filter(entityPlayer -> (double)player.method_5739((class_1297)entityPlayer) <= range).findFirst().orElse(null);
    }
}

