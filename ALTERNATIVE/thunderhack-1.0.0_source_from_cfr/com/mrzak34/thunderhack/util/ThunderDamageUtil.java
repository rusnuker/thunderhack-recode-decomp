/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1657
 *  net.minecraft.class_1799
 */
package com.mrzak34.thunderhack.util;

import com.mrzak34.thunderhack.util.Util;
import net.minecraft.class_1657;
import net.minecraft.class_1799;

public class ThunderDamageUtil
implements Util {
    public static int ChekTotalarmorDamage(class_1657 player) {
        Integer damage_vsey_broni = 0;
        for (class_1799 piece : player.method_31548().field_7548) {
            if (piece == null) {
                damage_vsey_broni = damage_vsey_broni + 0;
                continue;
            }
            damage_vsey_broni = damage_vsey_broni + ThunderDamageUtil.getItemDamage(piece);
        }
        return damage_vsey_broni;
    }

    public static int getItemDamage(class_1799 stack) {
        return stack.method_7936() - stack.method_7919();
    }
}

