//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.util;

import java.util.*;
import net.minecraft.*;

public class RaycastUtil implements Util
{
    public static boolean raycastBox(final class_238 box, final double dist, final float yaw, final float pitch, final boolean troughWalls) {
        final class_243 vec3dmin = RaycastUtil.mc.field_1724.method_33571();
        final class_243 veclook = getRotationVec(yaw, pitch);
        final class_243 vec3d2max = vec3dmin.method_1031(veclook.method_10216() * dist, veclook.method_10214() * dist, veclook.method_10215() * dist);
        final Optional<class_243> optional = (Optional<class_243>)box.method_992(vec3dmin, vec3d2max);
        if (optional.isPresent()) {
            final class_3965 blockHit = rayTraceBlock(dist, yaw, pitch);
            return troughWalls || !blockHit.method_17783().equals((Object)class_239.class_240.field_1332) || RaycastUtil.mc.field_1724.method_33571().method_1025(blockHit.method_17784()) >= RaycastUtil.mc.field_1724.method_33571().method_1025(box.method_1005());
        }
        return false;
    }
    
    public static class_3966 rayTraceEntity2(final double blockReachDistance, final float yaw, final float pitch) {
        final class_243 vec3d = RaycastUtil.mc.field_1724.method_33571();
        final class_243 vec3d2 = getRotationVec(yaw, pitch);
        final class_243 vec3d3 = vec3d.method_1031(vec3d2.field_1352 * blockReachDistance, vec3d2.field_1351 * blockReachDistance, vec3d2.field_1350 * blockReachDistance);
        return performEntityRaycast((class_1297)RaycastUtil.mc.field_1724, vec3d, vec3d3);
    }
    
    public static class_3965 rayTraceBlock(final double blockReachDistance, final float yaw, final float pitch) {
        final class_243 vec3d = RaycastUtil.mc.field_1724.method_33571();
        final class_243 vec3d2 = getRotationVec(yaw, pitch);
        final class_243 vec3d3 = vec3d.method_1031(vec3d2.field_1352 * blockReachDistance, vec3d2.field_1351 * blockReachDistance, vec3d2.field_1350 * blockReachDistance);
        return RaycastUtil.mc.field_1687.method_17742(new class_3959(vec3d, vec3d3, class_3959.class_3960.field_17558, class_3959.class_242.field_1345, (class_1297)RaycastUtil.mc.field_1724));
    }
    
    public static class_3966 performEntityRaycast(final class_1297 source, final class_243 origin, final class_243 target) {
        final class_243 ray = target.method_1020(origin);
        final class_238 box = source.method_5829().method_18804(ray).method_1009(1.0, 1.0, 1.0);
        return class_1675.method_18075(source, origin, target, box, entityx -> !entityx.method_7325(), ray.method_1027());
    }
    
    public static final class_243 getRotationVec(final float yaw, final float pitch) {
        return getRotationVector(yaw, pitch);
    }
    
    protected static final class_243 getRotationVector(final float yaw, final float pitch) {
        final float f = pitch * 0.017453292f;
        final float g = -yaw * 0.017453292f;
        final float h = class_3532.method_15362(g);
        final float i = class_3532.method_15374(g);
        final float j = class_3532.method_15362(f);
        final float k = class_3532.method_15374(f);
        return new class_243((double)(i * j), (double)(-k), (double)(h * j));
    }
}
