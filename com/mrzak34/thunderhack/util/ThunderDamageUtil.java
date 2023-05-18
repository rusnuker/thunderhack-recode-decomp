//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.util;

import net.minecraft.*;
import java.util.*;

public class ThunderDamageUtil implements Util
{
    public static int ChekTotalarmorDamage(final class_1657 player) {
        Integer damage_vsey_broni = 0;
        for (final class_1799 piece : player.method_31548().field_7548) {
            if (piece == null) {
                damage_vsey_broni += 0;
            }
            else {
                damage_vsey_broni += getItemDamage(piece);
            }
        }
        return damage_vsey_broni;
    }
    
    public static int getItemDamage(final class_1799 stack) {
        return stack.method_7936() - stack.method_7919();
    }
}
