//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.util;

import java.util.*;
import net.minecraft.*;
import com.google.common.collect.*;

public class WorldUtil
{
    protected static final class_310 mc;
    public static final Set<class_2248> RIGHTCLICKABLE_BLOCKS;
    public static final Set<class_3614> FLUIDS;
    
    public static List<class_2818> getLoadedChunks() {
        final List<class_2818> chunks = new ArrayList<class_2818>();
        for (int viewDist = (int)WorldUtil.mc.field_1690.method_42503().method_41753(), x = -viewDist; x <= viewDist; ++x) {
            for (int z = -viewDist; z <= viewDist; ++z) {
                final class_2818 chunk = WorldUtil.mc.field_1687.method_2935().method_21730((int)WorldUtil.mc.field_1724.method_23317() / 16 + x, (int)WorldUtil.mc.field_1724.method_23321() / 16 + z);
                if (chunk != null) {
                    chunks.add(chunk);
                }
            }
        }
        return chunks;
    }
    
    public static List<class_2586> getBlockEntities() {
        final List<class_2586> list = new ArrayList<class_2586>();
        getLoadedChunks().forEach(c -> list.addAll(c.method_12214().values()));
        return list;
    }
    
    public static boolean isFluid(final class_2338 pos) {
        return WorldUtil.FLUIDS.contains(WorldUtil.mc.field_1687.method_8320(pos).method_26207());
    }
    
    public static boolean doesBoxTouchBlock(final class_238 box, final class_2248 block) {
        for (int x = (int)Math.floor(box.field_1323); x < Math.ceil(box.field_1320); ++x) {
            for (int y = (int)Math.floor(box.field_1322); y < Math.ceil(box.field_1325); ++y) {
                for (int z = (int)Math.floor(box.field_1321); z < Math.ceil(box.field_1324); ++z) {
                    if (WorldUtil.mc.field_1687.method_8320(new class_2338(x, y, z)).method_26204() == block) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public static boolean doesBoxCollide(final class_238 box) {
        for (int x = (int)Math.floor(box.field_1323); x < Math.ceil(box.field_1320); ++x) {
            for (int y = (int)Math.floor(box.field_1322); y < Math.ceil(box.field_1325); ++y) {
                for (int z = (int)Math.floor(box.field_1321); z < Math.ceil(box.field_1324); ++z) {
                    final int fx = x;
                    final int fy = y;
                    final int fz = z;
                    if (WorldUtil.mc.field_1687.method_8320(new class_2338(x, y, z)).method_26220((class_1922)WorldUtil.mc.field_1687, new class_2338(x, y, z)).method_1090().stream().anyMatch(b -> b.method_989((double)fx, (double)fy, (double)fz).method_994(box))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public static boolean placeBlock(final class_2338 pos, final int slot, final int rotateMode, final boolean forceLegit, final boolean airPlace, final boolean swingHand) {
        if (!WorldUtil.mc.field_1687.method_24794(pos) || !isBlockEmpty(pos)) {
            return false;
        }
        for (final class_2350 d : class_2350.values()) {
            Label_0442: {
                if (WorldUtil.mc.field_1687.method_24794(pos.method_10093(d))) {
                    final class_2248 neighborBlock = WorldUtil.mc.field_1687.method_8320(pos.method_10093(d)).method_26204();
                    if (airPlace || !neighborBlock.method_9564().method_26207().method_15800()) {
                        class_243 vec = getLegitLookPos(pos.method_10093(d), d.method_10153(), true, 5);
                        if (vec == null) {
                            if (forceLegit) {
                                break Label_0442;
                            }
                            vec = getLegitLookPos(pos.method_10093(d), d.method_10153(), false, 5);
                            if (vec == null) {
                                break Label_0442;
                            }
                        }
                        final int prevSlot = WorldUtil.mc.field_1724.method_31548().field_7545;
                        final class_1268 hand = InventoryUtil.selectSlot(slot);
                        if (hand == null) {
                            return false;
                        }
                        if (rotateMode == 1) {
                            facePosPacket(vec.field_1352, vec.field_1351, vec.field_1350);
                        }
                        else if (rotateMode == 2) {
                            facePos(vec.field_1352, vec.field_1351, vec.field_1350);
                        }
                        if (WorldUtil.RIGHTCLICKABLE_BLOCKS.contains(neighborBlock)) {
                            WorldUtil.mc.field_1724.field_3944.method_2883((class_2596)new class_2848((class_1297)WorldUtil.mc.field_1724, class_2848.class_2849.field_12979));
                        }
                        if (swingHand) {
                            WorldUtil.mc.field_1724.method_6104(hand);
                        }
                        else {
                            WorldUtil.mc.field_1724.field_3944.method_2883((class_2596)new class_2879(hand));
                        }
                        WorldUtil.mc.field_1761.method_2896(WorldUtil.mc.field_1724, hand, new class_3965(class_243.method_24954((class_2382)pos), airPlace ? d : d.method_10153(), airPlace ? pos : pos.method_10093(d), false));
                        if (WorldUtil.RIGHTCLICKABLE_BLOCKS.contains(neighborBlock)) {
                            WorldUtil.mc.field_1724.field_3944.method_2883((class_2596)new class_2848((class_1297)WorldUtil.mc.field_1724, class_2848.class_2849.field_12984));
                        }
                        WorldUtil.mc.field_1724.method_31548().field_7545 = prevSlot;
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public static class_243 getLegitLookPos(final class_2338 pos, final class_2350 dir, final boolean raycast, final int res) {
        return getLegitLookPos(new class_238(pos), dir, raycast, res, 0.01);
    }
    
    public static class_243 getLegitLookPos(final class_238 box, final class_2350 dir, final boolean raycast, final int res, final double extrude) {
        final class_243 eyePos = WorldUtil.mc.field_1724.method_33571();
        final class_243 blockPos = new class_243(box.field_1323, box.field_1322, box.field_1321).method_1031((dir == class_2350.field_11039) ? (-extrude) : (dir.method_10148() * box.method_17939() + extrude), (dir == class_2350.field_11033) ? (-extrude) : (dir.method_10164() * box.method_17940() + extrude), (dir == class_2350.field_11043) ? (-extrude) : (dir.method_10165() * box.method_17941() + extrude));
        for (double i = 0.0; i <= 1.0; i += 1.0 / res) {
            for (double j = 0.0; j <= 1.0; j += 1.0 / res) {
                final class_243 lookPos = blockPos.method_1031((dir.method_10166() == class_2350.class_2351.field_11048) ? 0.0 : (i * box.method_17939()), (dir.method_10166() == class_2350.class_2351.field_11052) ? 0.0 : ((dir.method_10166() == class_2350.class_2351.field_11051) ? (j * box.method_17940()) : (i * box.method_17940())), (dir.method_10166() == class_2350.class_2351.field_11051) ? 0.0 : (j * box.method_17941()));
                if (eyePos.method_1022(lookPos) <= 4.55) {
                    if (!raycast) {
                        return lookPos;
                    }
                    if (WorldUtil.mc.field_1687.method_17742(new class_3959(eyePos, lookPos, class_3959.class_3960.field_17558, class_3959.class_242.field_1348, (class_1297)WorldUtil.mc.field_1724)).method_17783() == class_239.class_240.field_1333) {
                        return lookPos;
                    }
                }
            }
        }
        return null;
    }
    
    public static boolean isBlockEmpty(final class_2338 pos) {
        if (!WorldUtil.mc.field_1687.method_8320(pos).method_26207().method_15800()) {
            return false;
        }
        final class_238 box = new class_238(pos);
        for (final class_1297 e : WorldUtil.mc.field_1687.method_18112()) {
            if (e instanceof class_1309 && box.method_994(e.method_5829())) {
                return false;
            }
        }
        return true;
    }
    
    public static void facePos(final double x, final double y, final double z) {
        final double diffX = x - WorldUtil.mc.field_1724.method_23317();
        final double diffY = y - (WorldUtil.mc.field_1724.method_23318() + WorldUtil.mc.field_1724.method_18381(WorldUtil.mc.field_1724.method_18376()));
        final double diffZ = z - WorldUtil.mc.field_1724.method_23321();
        final double diffXZ = Math.sqrt(diffX * diffX + diffZ * diffZ);
        final float yaw = (float)Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0f;
        final float pitch = (float)(-Math.toDegrees(Math.atan2(diffY, diffXZ)));
        WorldUtil.mc.field_1724.method_36456(WorldUtil.mc.field_1724.method_36454() + class_3532.method_15393(yaw - WorldUtil.mc.field_1724.method_36454()));
        WorldUtil.mc.field_1724.method_36457(WorldUtil.mc.field_1724.method_36455() + class_3532.method_15393(pitch - WorldUtil.mc.field_1724.method_36455()));
    }
    
    public static void facePosPacket(final double x, final double y, final double z) {
        final double diffX = x - WorldUtil.mc.field_1724.method_23317();
        final double diffY = y - (WorldUtil.mc.field_1724.method_23318() + WorldUtil.mc.field_1724.method_18381(WorldUtil.mc.field_1724.method_18376()));
        final double diffZ = z - WorldUtil.mc.field_1724.method_23321();
        final double diffXZ = Math.sqrt(diffX * diffX + diffZ * diffZ);
        final float yaw = (float)Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0f;
        final float pitch = (float)(-Math.toDegrees(Math.atan2(diffY, diffXZ)));
        if (!WorldUtil.mc.field_1724.method_5765()) {
            WorldUtil.mc.field_1724.field_6241 = WorldUtil.mc.field_1724.method_36454() + class_3532.method_15393(yaw - WorldUtil.mc.field_1724.method_36454());
            WorldUtil.mc.field_1724.field_6283 = WorldUtil.mc.field_1724.method_36454() + class_3532.method_15393(yaw - WorldUtil.mc.field_1724.method_36454());
            WorldUtil.mc.field_1724.field_3916 = WorldUtil.mc.field_1724.method_36455() + class_3532.method_15393(pitch - WorldUtil.mc.field_1724.method_36455());
        }
        WorldUtil.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2831(WorldUtil.mc.field_1724.method_36454() + class_3532.method_15393(yaw - WorldUtil.mc.field_1724.method_36454()), WorldUtil.mc.field_1724.method_36455() + class_3532.method_15393(pitch - WorldUtil.mc.field_1724.method_36455()), WorldUtil.mc.field_1724.method_24828()));
    }
    
    public static int getTopBlockIgnoreLeaves(final int x, final int z) {
        int top;
        for (top = WorldUtil.mc.field_1687.method_8624(class_2902.class_2903.field_13202, x, z) - 1; top > WorldUtil.mc.field_1687.method_31607(); --top) {
            final class_2680 state = WorldUtil.mc.field_1687.method_8320(new class_2338(x, top, z));
            if (!state.method_26215() && !(state.method_26204() instanceof class_2397) && !(state.method_26204() instanceof class_2261)) {
                break;
            }
        }
        return top;
    }
    
    static {
        mc = class_310.method_1551();
        RIGHTCLICKABLE_BLOCKS = Sets.newHashSet((Object[])new class_2248[] { class_2246.field_10034, class_2246.field_10380, class_2246.field_10443, class_2246.field_10199, class_2246.field_10407, class_2246.field_10063, class_2246.field_10203, class_2246.field_10600, class_2246.field_10275, class_2246.field_10051, class_2246.field_10140, class_2246.field_10320, class_2246.field_10532, class_2246.field_10268, class_2246.field_10605, class_2246.field_10373, class_2246.field_10055, class_2246.field_10068, class_2246.field_10371, class_2246.field_10535, class_2246.field_16332, class_2246.field_10057, class_2246.field_10278, class_2246.field_10417, class_2246.field_10493, class_2246.field_10553, class_2246.field_10066, class_2246.field_10494, class_2246.field_10377, class_2246.field_10450, class_2246.field_10188, class_2246.field_10291, class_2246.field_10513, class_2246.field_10041, class_2246.field_10196, class_2246.field_10457, class_2246.field_10333, class_2246.field_10200, class_2246.field_10228, class_2246.field_10363, class_2246.field_10179, class_2246.field_10223, class_2246.field_10327, class_2246.field_10461, class_2246.field_10527, class_2246.field_10288, class_2246.field_10109, class_2246.field_10141, class_2246.field_10561, class_2246.field_10621, class_2246.field_10326, class_2246.field_10180, class_2246.field_10230, class_2246.field_10410, class_2246.field_10610, class_2246.field_10019, class_2246.field_10069, class_2246.field_10120, class_2246.field_10356, class_2246.field_10181, class_2246.field_10149, class_2246.field_10521, class_2246.field_10352, class_2246.field_10627, class_2246.field_10232, class_2246.field_10403, class_2246.field_10183, class_2246.field_10485, class_2246.field_10081, class_2246.field_10312, class_2246.field_10263, class_2246.field_10525, class_2246.field_10395, class_2246.field_9980, class_2246.field_10608, class_2246.field_10486, class_2246.field_10246, class_2246.field_10017, class_2246.field_10137, class_2246.field_10323, class_2246.field_10183, class_2246.field_10284, class_2246.field_10401, class_2246.field_10231, class_2246.field_10391, class_2246.field_10330, class_2246.field_10265, class_2246.field_10544, class_2246.field_10587, class_2246.field_10121, class_2246.field_10187, class_2246.field_10411, class_2246.field_10088, class_2246.field_22104, class_2246.field_22106, class_2246.field_22105, class_2246.field_22107, class_2246.field_16333, class_2246.field_16334, class_2246.field_16336, class_2246.field_16337, class_2246.field_16330, class_2246.field_10083, class_2246.field_16335, class_2246.field_16329 });
        FLUIDS = Sets.newHashSet((Object[])new class_3614[] { class_3614.field_15920, class_3614.field_15922, class_3614.field_15947, class_3614.field_15926 });
    }
}
