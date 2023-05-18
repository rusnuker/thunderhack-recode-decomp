/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_238
 *  net.minecraft.class_243
 *  net.minecraft.class_3532
 */
package com.mrzak34.thunderhack.util;

import com.mrzak34.thunderhack.util.HUtil;
import com.mrzak34.thunderhack.util.Util;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import net.minecraft.class_1297;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_3532;

public class RaytraceUtil
implements Util {
    public static double a() {
        return (double)RaytraceUtil.mc.field_1761.method_2904() - 1.5;
    }

    public static double b() {
        double d2 = RaytraceUtil.a();
        return d2;
    }

    public static class_1297 b(double d2, float f2, float f3) {
        return RaytraceUtil.b(d2, f2, f3, mc.method_1488());
    }

    public static class_1297 b(double d2, float f2, float f3, float f4) {
        class_1297 entity = mc.method_1560();
        class_243 vec3d = entity.method_33571();
        class_243 vec3d2 = RaytraceUtil.a(f3, f2);
        class_243 vec3d3 = vec3d.method_1031(vec3d2.field_1352 * d2, vec3d2.field_1351 * d2, vec3d2.field_1350 * d2);
        return RaytraceUtil.a(entity, d2, vec3d, vec3d2, vec3d3);
    }

    public static class_1297 a(class_1297 entity, double d2, class_243 vec3d, class_243 vec3d2, class_243 vec3d3) {
        class_1297 entity2 = null;
        try {
            List list = RaytraceUtil.mc.field_1687.method_8333(entity, entity.method_5829().method_1009(vec3d2.field_1352 * d2, vec3d2.field_1351 * d2, vec3d2.field_1350 * d2).method_35580(1.0, 1.0, 1.0), (Predicate)((Object)new HUtil()));
            for (int i2 = 0; i2 < list.size(); ++i2) {
                double d3;
                class_1297 entity3 = (class_1297)list.get(i2);
                class_238 axisAlignedBB = entity3.method_5829();
                Optional rayTraceResult = axisAlignedBB.method_992(vec3d, vec3d3);
                if (axisAlignedBB.method_1006(vec3d)) {
                    if (!(d2 >= 0.0)) continue;
                    entity2 = entity3;
                    d2 = 0.0;
                    continue;
                }
                if (rayTraceResult == null || !((d3 = vec3d.method_1022((class_243)rayTraceResult.get())) < d2) && d2 != 0.0) continue;
                if (entity3.method_5854() == entity.method_5854() && !entity3.field_23807) {
                    if (d2 != 0.0) continue;
                    entity2 = entity3;
                    continue;
                }
                entity2 = entity3;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return entity2;
    }

    public static class_243 a(float f2, float f3) {
        float f4 = class_3532.method_15362((float)(-f3 * ((float)Math.PI / 180) - (float)Math.PI));
        float f5 = class_3532.method_15374((float)(-f3 * ((float)Math.PI / 180) - (float)Math.PI));
        float f6 = -class_3532.method_15362((float)(-f2 * ((float)Math.PI / 180)));
        float f7 = class_3532.method_15374((float)(-f2 * ((float)Math.PI / 180)));
        return new class_243((double)(f5 * f6), (double)f7, (double)(f4 * f6));
    }
}

