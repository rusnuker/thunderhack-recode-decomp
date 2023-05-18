//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.util;

import net.minecraft.*;

public class RotationUtil implements Util
{
    public static class_241 lookAtEntity(final class_1297 entity1, final class_1297 entity2) {
        final float[] angle = MathUtil.calcAngle(entity1.method_5836(RotationUtil.mc.method_1488()), entity2.method_5836(RotationUtil.mc.method_1488()));
        return new class_241(angle[0], angle[1]);
    }
    
    public static class_241 lookAtVec3d(final class_243 vec3d) {
        final float[] angle = MathUtil.calcAngle(RotationUtil.mc.field_1724.method_33571(), new class_243(vec3d.field_1352, vec3d.field_1351, vec3d.field_1350));
        return new class_241(angle[0], angle[1]);
    }
}
