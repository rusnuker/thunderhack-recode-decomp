//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.util;

import net.minecraft.*;

public class MoveUtil implements Util
{
    public static boolean isBlockAboveHead() {
        final class_238 axisAlignedBB = new class_238(MoveUtil.mc.field_1724.method_23317() - 0.3, MoveUtil.mc.field_1724.method_23320(), MoveUtil.mc.field_1724.method_23321() + 0.3, MoveUtil.mc.field_1724.method_23317() + 0.3, MoveUtil.mc.field_1724.method_23318() + (MoveUtil.mc.field_1724.method_24828() ? 2.5 : 1.5), MoveUtil.mc.field_1724.method_23321() - 0.3);
        return !MoveUtil.mc.field_1687.method_8600((class_1297)MoveUtil.mc.field_1724, axisAlignedBB).equals(null);
    }
    
    public static boolean isInLiquid() {
        return MoveUtil.mc.field_1724.method_5799() || MoveUtil.mc.field_1724.method_5771();
    }
    
    public static boolean isMoving() {
        return MoveUtil.mc.field_1724.field_3913.field_3907 != 0.0f || MoveUtil.mc.field_1724.field_3913.field_3905 != 0.0f;
    }
}
