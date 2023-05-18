/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1675
 *  net.minecraft.class_238
 *  net.minecraft.class_239$class_240
 *  net.minecraft.class_243
 *  net.minecraft.class_3532
 *  net.minecraft.class_3959
 *  net.minecraft.class_3959$class_242
 *  net.minecraft.class_3959$class_3960
 *  net.minecraft.class_3965
 *  net.minecraft.class_3966
 */
package com.mrzak34.thunderhack.util;

import com.mrzak34.thunderhack.util.Util;
import java.util.Optional;
import net.minecraft.class_1297;
import net.minecraft.class_1675;
import net.minecraft.class_238;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_3532;
import net.minecraft.class_3959;
import net.minecraft.class_3965;
import net.minecraft.class_3966;

public class RaycastUtil
implements Util {
    public static boolean raycastBox(class_238 box, double dist, float yaw, float pitch, boolean troughWalls) {
        class_243 veclook;
        class_243 vec3d2max;
        class_243 vec3dmin = RaycastUtil.mc.field_1724.method_33571();
        Optional optional = box.method_992(vec3dmin, vec3d2max = vec3dmin.method_1031((veclook = RaycastUtil.getRotationVec(yaw, pitch)).method_10216() * dist, veclook.method_10214() * dist, veclook.method_10215() * dist));
        if (optional.isPresent()) {
            class_3965 blockHit = RaycastUtil.rayTraceBlock(dist, yaw, pitch);
            return troughWalls || !blockHit.method_17783().equals((Object)class_239.class_240.field_1332) || !(RaycastUtil.mc.field_1724.method_33571().method_1025(blockHit.method_17784()) < RaycastUtil.mc.field_1724.method_33571().method_1025(box.method_1005()));
        }
        return false;
    }

    public static class_3966 rayTraceEntity2(double blockReachDistance, float yaw, float pitch) {
        class_243 vec3d = RaycastUtil.mc.field_1724.method_33571();
        class_243 vec3d1 = RaycastUtil.getRotationVec(yaw, pitch);
        class_243 vec3d2 = vec3d.method_1031(vec3d1.field_1352 * blockReachDistance, vec3d1.field_1351 * blockReachDistance, vec3d1.field_1350 * blockReachDistance);
        return RaycastUtil.performEntityRaycast((class_1297)RaycastUtil.mc.field_1724, vec3d, vec3d2);
    }

    public static class_3965 rayTraceBlock(double blockReachDistance, float yaw, float pitch) {
        class_243 vec3d = RaycastUtil.mc.field_1724.method_33571();
        class_243 vec3d1 = RaycastUtil.getRotationVec(yaw, pitch);
        class_243 vec3d2 = vec3d.method_1031(vec3d1.field_1352 * blockReachDistance, vec3d1.field_1351 * blockReachDistance, vec3d1.field_1350 * blockReachDistance);
        return RaycastUtil.mc.field_1687.method_17742(new class_3959(vec3d, vec3d2, class_3959.class_3960.field_17558, class_3959.class_242.field_1345, (class_1297)RaycastUtil.mc.field_1724));
    }

    public static class_3966 performEntityRaycast(class_1297 source, class_243 origin, class_243 target) {
        class_243 ray = target.method_1020(origin);
        class_238 box = source.method_5829().method_18804(ray).method_1009(1.0, 1.0, 1.0);
        return class_1675.method_18075((class_1297)source, (class_243)origin, (class_243)target, (class_238)box, entityx -> !entityx.method_7325(), (double)ray.method_1027());
    }

    public static final class_243 getRotationVec(float yaw, float pitch) {
        return RaycastUtil.getRotationVector(yaw, pitch);
    }

    protected static final class_243 getRotationVector(float yaw, float pitch) {
        float f = pitch * ((float)Math.PI / 180);
        float g = -yaw * ((float)Math.PI / 180);
        float h = class_3532.method_15362((float)g);
        float i = class_3532.method_15374((float)g);
        float j = class_3532.method_15362((float)f);
        float k = class_3532.method_15374((float)f);
        return new class_243((double)(i * j), (double)(-k), (double)(h * j));
    }
}

