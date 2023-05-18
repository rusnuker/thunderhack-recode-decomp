/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Sets
 *  net.minecraft.class_1268
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_1922
 *  net.minecraft.class_2246
 *  net.minecraft.class_2248
 *  net.minecraft.class_2261
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2350$class_2351
 *  net.minecraft.class_238
 *  net.minecraft.class_2382
 *  net.minecraft.class_239$class_240
 *  net.minecraft.class_2397
 *  net.minecraft.class_243
 *  net.minecraft.class_2586
 *  net.minecraft.class_2596
 *  net.minecraft.class_2680
 *  net.minecraft.class_2818
 *  net.minecraft.class_2828$class_2831
 *  net.minecraft.class_2848
 *  net.minecraft.class_2848$class_2849
 *  net.minecraft.class_2879
 *  net.minecraft.class_2902$class_2903
 *  net.minecraft.class_310
 *  net.minecraft.class_3532
 *  net.minecraft.class_3614
 *  net.minecraft.class_3959
 *  net.minecraft.class_3959$class_242
 *  net.minecraft.class_3959$class_3960
 *  net.minecraft.class_3965
 */
package com.mrzak34.thunderhack.util;

import com.google.common.collect.Sets;
import com.mrzak34.thunderhack.util.InventoryUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1922;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2261;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_2382;
import net.minecraft.class_239;
import net.minecraft.class_2397;
import net.minecraft.class_243;
import net.minecraft.class_2586;
import net.minecraft.class_2596;
import net.minecraft.class_2680;
import net.minecraft.class_2818;
import net.minecraft.class_2828;
import net.minecraft.class_2848;
import net.minecraft.class_2879;
import net.minecraft.class_2902;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_3614;
import net.minecraft.class_3959;
import net.minecraft.class_3965;

public class WorldUtil {
    protected static final class_310 mc = class_310.method_1551();
    public static final Set<class_2248> RIGHTCLICKABLE_BLOCKS = Sets.newHashSet((Object[])new class_2248[]{class_2246.field_10034, class_2246.field_10380, class_2246.field_10443, class_2246.field_10199, class_2246.field_10407, class_2246.field_10063, class_2246.field_10203, class_2246.field_10600, class_2246.field_10275, class_2246.field_10051, class_2246.field_10140, class_2246.field_10320, class_2246.field_10532, class_2246.field_10268, class_2246.field_10605, class_2246.field_10373, class_2246.field_10055, class_2246.field_10068, class_2246.field_10371, class_2246.field_10535, class_2246.field_16332, class_2246.field_10057, class_2246.field_10278, class_2246.field_10417, class_2246.field_10493, class_2246.field_10553, class_2246.field_10066, class_2246.field_10494, class_2246.field_10377, class_2246.field_10450, class_2246.field_10188, class_2246.field_10291, class_2246.field_10513, class_2246.field_10041, class_2246.field_10196, class_2246.field_10457, class_2246.field_10333, class_2246.field_10200, class_2246.field_10228, class_2246.field_10363, class_2246.field_10179, class_2246.field_10223, class_2246.field_10327, class_2246.field_10461, class_2246.field_10527, class_2246.field_10288, class_2246.field_10109, class_2246.field_10141, class_2246.field_10561, class_2246.field_10621, class_2246.field_10326, class_2246.field_10180, class_2246.field_10230, class_2246.field_10410, class_2246.field_10610, class_2246.field_10019, class_2246.field_10069, class_2246.field_10120, class_2246.field_10356, class_2246.field_10181, class_2246.field_10149, class_2246.field_10521, class_2246.field_10352, class_2246.field_10627, class_2246.field_10232, class_2246.field_10403, class_2246.field_10183, class_2246.field_10485, class_2246.field_10081, class_2246.field_10312, class_2246.field_10263, class_2246.field_10525, class_2246.field_10395, class_2246.field_9980, class_2246.field_10608, class_2246.field_10486, class_2246.field_10246, class_2246.field_10017, class_2246.field_10137, class_2246.field_10323, class_2246.field_10183, class_2246.field_10284, class_2246.field_10401, class_2246.field_10231, class_2246.field_10391, class_2246.field_10330, class_2246.field_10265, class_2246.field_10544, class_2246.field_10587, class_2246.field_10121, class_2246.field_10187, class_2246.field_10411, class_2246.field_10088, class_2246.field_22104, class_2246.field_22106, class_2246.field_22105, class_2246.field_22107, class_2246.field_16333, class_2246.field_16334, class_2246.field_16336, class_2246.field_16337, class_2246.field_16330, class_2246.field_10083, class_2246.field_16335, class_2246.field_16329});
    public static final Set<class_3614> FLUIDS = Sets.newHashSet((Object[])new class_3614[]{class_3614.field_15920, class_3614.field_15922, class_3614.field_15947, class_3614.field_15926});

    public static List<class_2818> getLoadedChunks() {
        ArrayList<class_2818> chunks = new ArrayList<class_2818>();
        int viewDist = (Integer)WorldUtil.mc.field_1690.method_42503().method_41753();
        for (int x = -viewDist; x <= viewDist; ++x) {
            for (int z = -viewDist; z <= viewDist; ++z) {
                class_2818 chunk = WorldUtil.mc.field_1687.method_2935().method_21730((int)WorldUtil.mc.field_1724.method_23317() / 16 + x, (int)WorldUtil.mc.field_1724.method_23321() / 16 + z);
                if (chunk == null) continue;
                chunks.add(chunk);
            }
        }
        return chunks;
    }

    public static List<class_2586> getBlockEntities() {
        ArrayList<class_2586> list = new ArrayList<class_2586>();
        WorldUtil.getLoadedChunks().forEach(c -> list.addAll(c.method_12214().values()));
        return list;
    }

    public static boolean isFluid(class_2338 pos) {
        return FLUIDS.contains((Object)WorldUtil.mc.field_1687.method_8320(pos).method_26207());
    }

    public static boolean doesBoxTouchBlock(class_238 box, class_2248 block) {
        int x = (int)Math.floor(box.field_1323);
        while ((double)x < Math.ceil(box.field_1320)) {
            int y = (int)Math.floor(box.field_1322);
            while ((double)y < Math.ceil(box.field_1325)) {
                int z = (int)Math.floor(box.field_1321);
                while ((double)z < Math.ceil(box.field_1324)) {
                    if (WorldUtil.mc.field_1687.method_8320(new class_2338(x, y, z)).method_26204() == block) {
                        return true;
                    }
                    ++z;
                }
                ++y;
            }
            ++x;
        }
        return false;
    }

    public static boolean doesBoxCollide(class_238 box) {
        int x = (int)Math.floor(box.field_1323);
        while ((double)x < Math.ceil(box.field_1320)) {
            int y = (int)Math.floor(box.field_1322);
            while ((double)y < Math.ceil(box.field_1325)) {
                int z = (int)Math.floor(box.field_1321);
                while ((double)z < Math.ceil(box.field_1324)) {
                    int fx = x;
                    int fy = y;
                    int fz = z;
                    if (WorldUtil.mc.field_1687.method_8320(new class_2338(x, y, z)).method_26220((class_1922)WorldUtil.mc.field_1687, new class_2338(x, y, z)).method_1090().stream().anyMatch(b -> b.method_989((double)fx, (double)fy, (double)fz).method_994(box))) {
                        return true;
                    }
                    ++z;
                }
                ++y;
            }
            ++x;
        }
        return false;
    }

    public static boolean placeBlock(class_2338 pos, int slot, int rotateMode, boolean forceLegit, boolean airPlace, boolean swingHand) {
        if (!WorldUtil.mc.field_1687.method_24794(pos) || !WorldUtil.isBlockEmpty(pos)) {
            return false;
        }
        for (class_2350 d : class_2350.values()) {
            class_243 vec;
            if (!WorldUtil.mc.field_1687.method_24794(pos.method_10093(d))) continue;
            class_2248 neighborBlock = WorldUtil.mc.field_1687.method_8320(pos.method_10093(d)).method_26204();
            if (!airPlace && neighborBlock.method_9564().method_26207().method_15800() || (vec = WorldUtil.getLegitLookPos(pos.method_10093(d), d.method_10153(), true, 5)) == null && (forceLegit || (vec = WorldUtil.getLegitLookPos(pos.method_10093(d), d.method_10153(), false, 5)) == null)) continue;
            int prevSlot = WorldUtil.mc.field_1724.method_31548().field_7545;
            class_1268 hand = InventoryUtil.selectSlot(slot);
            if (hand == null) {
                return false;
            }
            if (rotateMode == 1) {
                WorldUtil.facePosPacket(vec.field_1352, vec.field_1351, vec.field_1350);
            } else if (rotateMode == 2) {
                WorldUtil.facePos(vec.field_1352, vec.field_1351, vec.field_1350);
            }
            if (RIGHTCLICKABLE_BLOCKS.contains((Object)neighborBlock)) {
                WorldUtil.mc.field_1724.field_3944.method_2883((class_2596)new class_2848((class_1297)WorldUtil.mc.field_1724, class_2848.class_2849.field_12979));
            }
            if (swingHand) {
                WorldUtil.mc.field_1724.method_6104(hand);
            } else {
                WorldUtil.mc.field_1724.field_3944.method_2883((class_2596)new class_2879(hand));
            }
            WorldUtil.mc.field_1761.method_2896(WorldUtil.mc.field_1724, hand, new class_3965(class_243.method_24954((class_2382)pos), airPlace ? d : d.method_10153(), airPlace ? pos : pos.method_10093(d), false));
            if (RIGHTCLICKABLE_BLOCKS.contains((Object)neighborBlock)) {
                WorldUtil.mc.field_1724.field_3944.method_2883((class_2596)new class_2848((class_1297)WorldUtil.mc.field_1724, class_2848.class_2849.field_12984));
            }
            WorldUtil.mc.field_1724.method_31548().field_7545 = prevSlot;
            return true;
        }
        return false;
    }

    public static class_243 getLegitLookPos(class_2338 pos, class_2350 dir, boolean raycast, int res) {
        return WorldUtil.getLegitLookPos(new class_238(pos), dir, raycast, res, 0.01);
    }

    public static class_243 getLegitLookPos(class_238 box, class_2350 dir, boolean raycast, int res, double extrude) {
        class_243 eyePos = WorldUtil.mc.field_1724.method_33571();
        class_243 blockPos = new class_243(box.field_1323, box.field_1322, box.field_1321).method_1031(dir == class_2350.field_11039 ? -extrude : (double)dir.method_10148() * box.method_17939() + extrude, dir == class_2350.field_11033 ? -extrude : (double)dir.method_10164() * box.method_17940() + extrude, dir == class_2350.field_11043 ? -extrude : (double)dir.method_10165() * box.method_17941() + extrude);
        for (double i = 0.0; i <= 1.0; i += 1.0 / (double)res) {
            for (double j = 0.0; j <= 1.0; j += 1.0 / (double)res) {
                class_243 lookPos = blockPos.method_1031(dir.method_10166() == class_2350.class_2351.field_11048 ? 0.0 : i * box.method_17939(), dir.method_10166() == class_2350.class_2351.field_11052 ? 0.0 : (dir.method_10166() == class_2350.class_2351.field_11051 ? j * box.method_17940() : i * box.method_17940()), dir.method_10166() == class_2350.class_2351.field_11051 ? 0.0 : j * box.method_17941());
                if (eyePos.method_1022(lookPos) > 4.55) continue;
                if (raycast) {
                    if (WorldUtil.mc.field_1687.method_17742(new class_3959(eyePos, lookPos, class_3959.class_3960.field_17558, class_3959.class_242.field_1348, (class_1297)WorldUtil.mc.field_1724)).method_17783() != class_239.class_240.field_1333) continue;
                    return lookPos;
                }
                return lookPos;
            }
        }
        return null;
    }

    public static boolean isBlockEmpty(class_2338 pos) {
        if (!WorldUtil.mc.field_1687.method_8320(pos).method_26207().method_15800()) {
            return false;
        }
        class_238 box = new class_238(pos);
        for (class_1297 e : WorldUtil.mc.field_1687.method_18112()) {
            if (!(e instanceof class_1309) || !box.method_994(e.method_5829())) continue;
            return false;
        }
        return true;
    }

    public static void facePos(double x, double y, double z) {
        double diffX = x - WorldUtil.mc.field_1724.method_23317();
        double diffY = y - (WorldUtil.mc.field_1724.method_23318() + (double)WorldUtil.mc.field_1724.method_18381(WorldUtil.mc.field_1724.method_18376()));
        double diffZ = z - WorldUtil.mc.field_1724.method_23321();
        double diffXZ = Math.sqrt(diffX * diffX + diffZ * diffZ);
        float yaw = (float)Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0f;
        float pitch = (float)(-Math.toDegrees(Math.atan2(diffY, diffXZ)));
        WorldUtil.mc.field_1724.method_36456(WorldUtil.mc.field_1724.method_36454() + class_3532.method_15393((float)(yaw - WorldUtil.mc.field_1724.method_36454())));
        WorldUtil.mc.field_1724.method_36457(WorldUtil.mc.field_1724.method_36455() + class_3532.method_15393((float)(pitch - WorldUtil.mc.field_1724.method_36455())));
    }

    public static void facePosPacket(double x, double y, double z) {
        double diffX = x - WorldUtil.mc.field_1724.method_23317();
        double diffY = y - (WorldUtil.mc.field_1724.method_23318() + (double)WorldUtil.mc.field_1724.method_18381(WorldUtil.mc.field_1724.method_18376()));
        double diffZ = z - WorldUtil.mc.field_1724.method_23321();
        double diffXZ = Math.sqrt(diffX * diffX + diffZ * diffZ);
        float yaw = (float)Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0f;
        float pitch = (float)(-Math.toDegrees(Math.atan2(diffY, diffXZ)));
        if (!WorldUtil.mc.field_1724.method_5765()) {
            WorldUtil.mc.field_1724.field_6241 = WorldUtil.mc.field_1724.method_36454() + class_3532.method_15393((float)(yaw - WorldUtil.mc.field_1724.method_36454()));
            WorldUtil.mc.field_1724.field_6283 = WorldUtil.mc.field_1724.method_36454() + class_3532.method_15393((float)(yaw - WorldUtil.mc.field_1724.method_36454()));
            WorldUtil.mc.field_1724.field_3916 = WorldUtil.mc.field_1724.method_36455() + class_3532.method_15393((float)(pitch - WorldUtil.mc.field_1724.method_36455()));
        }
        WorldUtil.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2831(WorldUtil.mc.field_1724.method_36454() + class_3532.method_15393((float)(yaw - WorldUtil.mc.field_1724.method_36454())), WorldUtil.mc.field_1724.method_36455() + class_3532.method_15393((float)(pitch - WorldUtil.mc.field_1724.method_36455())), WorldUtil.mc.field_1724.method_24828()));
    }

    public static int getTopBlockIgnoreLeaves(int x, int z) {
        class_2680 state;
        int top;
        for (top = WorldUtil.mc.field_1687.method_8624(class_2902.class_2903.field_13202, x, z) - 1; top > WorldUtil.mc.field_1687.method_31607() && ((state = WorldUtil.mc.field_1687.method_8320(new class_2338(x, top, z))).method_26215() || state.method_26204() instanceof class_2397 || state.method_26204() instanceof class_2261); --top) {
        }
        return top;
    }
}

