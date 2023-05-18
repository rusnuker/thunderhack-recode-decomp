//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.util;

import net.minecraft.*;

public class EntityUtil implements Util
{
    public static int getHealth(final class_1309 player) {
        final float absorption = player.method_6067();
        final int health = (int)Math.ceil(player.method_6032() + absorption);
        return health;
    }
    
    public static int getMaxHealth(final class_1309 player) {
        final float absorption = player.method_6067();
        final int health = (int)Math.ceil(player.method_6063() + absorption);
        return health;
    }
}
