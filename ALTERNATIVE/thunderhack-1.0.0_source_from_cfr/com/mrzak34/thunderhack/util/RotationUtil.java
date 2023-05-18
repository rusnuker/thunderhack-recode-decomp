/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_241
 *  net.minecraft.class_243
 */
package com.mrzak34.thunderhack.util;

import com.mrzak34.thunderhack.util.MathUtil;
import com.mrzak34.thunderhack.util.Util;
import net.minecraft.class_1297;
import net.minecraft.class_241;
import net.minecraft.class_243;

public class RotationUtil
implements Util {
    public static class_241 lookAtEntity(class_1297 entity1, class_1297 entity2) {
        float[] angle = MathUtil.calcAngle(entity1.method_5836(mc.method_1488()), entity2.method_5836(mc.method_1488()));
        return new class_241(angle[0], angle[1]);
    }

    public static class_241 lookAtVec3d(class_243 vec3d) {
        float[] angle = MathUtil.calcAngle(RotationUtil.mc.field_1724.method_33571(), new class_243(vec3d.field_1352, vec3d.field_1351, vec3d.field_1350));
        return new class_241(angle[0], angle[1]);
    }
}

