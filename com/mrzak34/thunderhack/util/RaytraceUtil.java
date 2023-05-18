//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.util;

import java.util.function.*;
import java.util.*;
import net.minecraft.*;

public class RaytraceUtil implements Util
{
    public static double a() {
        return RaytraceUtil.mc.field_1761.method_2904() - 1.5;
    }
    
    public static double b() {
        final double d2 = a();
        return d2;
    }
    
    public static class_1297 b(final double d2, final float f2, final float f3) {
        return b(d2, f2, f3, RaytraceUtil.mc.method_1488());
    }
    
    public static class_1297 b(final double d2, final float f2, final float f3, final float f4) {
        final class_1297 entity = RaytraceUtil.mc.method_1560();
        final class_243 vec3d = entity.method_33571();
        final class_243 vec3d2 = a(f3, f2);
        final class_243 vec3d3 = vec3d.method_1031(vec3d2.field_1352 * d2, vec3d2.field_1351 * d2, vec3d2.field_1350 * d2);
        return a(entity, d2, vec3d, vec3d2, vec3d3);
    }
    
    public static class_1297 a(final class_1297 entity, double d2, final class_243 vec3d, final class_243 vec3d2, final class_243 vec3d3) {
        class_1297 entity2 = null;
        try {
            final List list = RaytraceUtil.mc.field_1687.method_8333(entity, entity.method_5829().method_1009(vec3d2.field_1352 * d2, vec3d2.field_1351 * d2, vec3d2.field_1350 * d2).method_35580(1.0, 1.0, 1.0), (Predicate)new HUtil());
            for (int i2 = 0; i2 < list.size(); ++i2) {
                final class_1297 entity3 = list.get(i2);
                final class_238 axisAlignedBB = entity3.method_5829();
                final Optional<class_243> rayTraceResult = (Optional<class_243>)axisAlignedBB.method_992(vec3d, vec3d3);
                if (axisAlignedBB.method_1006(vec3d)) {
                    if (d2 >= 0.0) {
                        entity2 = entity3;
                        d2 = 0.0;
                    }
                }
                else if (rayTraceResult != null) {
                    final double d3;
                    if ((d3 = vec3d.method_1022((class_243)rayTraceResult.get())) < d2 || d2 == 0.0) {
                        if (entity3.method_5854() == entity.method_5854() && !entity3.field_23807) {
                            if (d2 == 0.0) {
                                entity2 = entity3;
                            }
                        }
                        else {
                            entity2 = entity3;
                        }
                    }
                }
            }
        }
        catch (Exception ex) {}
        return entity2;
    }
    
    public static class_243 a(final float f2, final float f3) {
        final float f4 = class_3532.method_15362(-f3 * 0.017453292f - 3.1415927f);
        final float f5 = class_3532.method_15374(-f3 * 0.017453292f - 3.1415927f);
        final float f6 = -class_3532.method_15362(-f2 * 0.017453292f);
        final float f7 = class_3532.method_15374(-f2 * 0.017453292f);
        return new class_243((double)(f5 * f6), (double)f7, (double)(f4 * f6));
    }
}
