/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1309
 */
package com.mrzak34.thunderhack.util;

import com.mrzak34.thunderhack.util.Util;
import net.minecraft.class_1309;

public class EntityUtil
implements Util {
    public static int getHealth(class_1309 player) {
        float absorption = player.method_6067();
        int health = (int)Math.ceil(player.method_6032() + absorption);
        return health;
    }

    public static int getMaxHealth(class_1309 player) {
        float absorption = player.method_6067();
        int health = (int)Math.ceil(player.method_6063() + absorption);
        return health;
    }
}

