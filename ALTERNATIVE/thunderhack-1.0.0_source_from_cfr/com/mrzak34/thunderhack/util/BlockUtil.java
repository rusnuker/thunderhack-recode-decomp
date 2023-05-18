/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1657
 *  net.minecraft.class_1922
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_238
 *  net.minecraft.class_243
 *  net.minecraft.class_259
 *  net.minecraft.class_2596
 *  net.minecraft.class_2680
 *  net.minecraft.class_2879
 *  net.minecraft.class_2885
 *  net.minecraft.class_3532
 *  net.minecraft.class_3965
 */
package com.mrzak34.thunderhack.util;

import com.mrzak34.thunderhack.util.CrystalUtil;
import com.mrzak34.thunderhack.util.Util;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1268;
import net.minecraft.class_1657;
import net.minecraft.class_1922;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_259;
import net.minecraft.class_2596;
import net.minecraft.class_2680;
import net.minecraft.class_2879;
import net.minecraft.class_2885;
import net.minecraft.class_3532;
import net.minecraft.class_3965;

public class BlockUtil
implements Util {
    public static boolean canBreak(class_2338 blockPos, class_2680 state) {
        if (!BlockUtil.mc.field_1724.method_7337() && state.method_26214((class_1922)BlockUtil.mc.field_1687, blockPos) < 0.0f) {
            return false;
        }
        return state.method_26218((class_1922)BlockUtil.mc.field_1687, blockPos) != class_259.method_1073();
    }

    public static boolean canBreak(class_2338 blockPos) {
        return BlockUtil.canBreak(blockPos, BlockUtil.mc.field_1687.method_8320(blockPos));
    }

    public static List<class_2338> getNearbyBlocks(class_1657 entityPlayer, double blockRange, boolean motion) {
        ArrayList<class_2338> nearbyBlocks = new ArrayList<class_2338>();
        int range = (int)Math.floor(blockRange);
        if (motion) {
            entityPlayer.method_19538().method_1031(entityPlayer.method_18798().field_1352, entityPlayer.method_18798().field_1351, entityPlayer.method_18798().field_1350);
        }
        for (int x = -range; x <= range; ++x) {
            for (int y = -range; y <= range; ++y) {
                for (int z = -range; z <= range; ++z) {
                    nearbyBlocks.add(entityPlayer.method_24515().method_10069(x, y, z));
                }
            }
        }
        return nearbyBlocks;
    }

    public static List<class_2338> getNearbyPlacePos(class_1657 entityPlayer, double blockRange, boolean motion, boolean thirteen) {
        ArrayList<class_2338> nearbyBlocks = new ArrayList<class_2338>();
        int range = (int)Math.floor(blockRange);
        if (motion) {
            entityPlayer.method_19538().method_1031(entityPlayer.method_18798().field_1352, entityPlayer.method_18798().field_1351, entityPlayer.method_18798().field_1350);
        }
        for (int x = -range; x <= range; ++x) {
            for (int y = -range; y <= 1; ++y) {
                for (int z = -range; z <= range; ++z) {
                    if (!CrystalUtil.canPlaceCrystal(new class_2338(x, y, z), false, thirteen)) continue;
                    nearbyBlocks.add(entityPlayer.method_24515().method_10069(x, y, z));
                }
            }
        }
        return nearbyBlocks;
    }

    public static class_2350 calcSide(class_2338 pos) {
        class_243 vec3d = new class_243((double)pos.method_10263() + 0.5, (double)pos.method_10264() + 0.5, (double)pos.method_10260() + 0.5);
        class_2350 side = (double)pos.method_10264() > BlockUtil.mc.field_1724.method_23320() ? class_2350.field_11033 : class_2350.field_11036;
        return side;
    }

    public static boolean placeBlock(class_2338 pos) {
        class_2248 block = BlockUtil.mc.field_1687.method_8320(pos).method_26204();
        class_2350 direction = class_2350.field_11033;
        if (direction == null) {
            return false;
        }
        BlockUtil.mc.field_1724.field_3944.method_2883((class_2596)new class_2885(class_1268.field_5808, new class_3965(new class_243((double)pos.method_10263(), (double)pos.method_10264(), (double)pos.method_10260()), direction, pos, false), 1));
        BlockUtil.mc.field_1724.field_3944.method_2883((class_2596)new class_2879(class_1268.field_5808));
        BlockUtil.mc.field_1761.method_2927();
        return true;
    }

    public static class_238 getBoundingBox(class_2338 pos) {
        return new class_238((double)pos.method_10263(), (double)pos.method_10264(), (double)pos.method_10260(), (double)(pos.method_10263() + 1), (double)(pos.method_10264() + 1), (double)(pos.method_10260() + 1));
    }

    public static class_243 closestVec3d(class_2338 blockPos) {
        return BlockUtil.closestVec3d(BlockUtil.getBoundingBox(blockPos));
    }

    private static class_243 closestVec3d(class_238 box) {
        if (box == null) {
            return new class_243(0.0, 0.0, 0.0);
        }
        class_243 eyePos = new class_243(BlockUtil.mc.field_1724.method_23317(), BlockUtil.mc.field_1724.method_23318() + (double)BlockUtil.mc.field_1724.method_18381(BlockUtil.mc.field_1724.method_18376()), BlockUtil.mc.field_1724.method_23321());
        double x = class_3532.method_15350((double)eyePos.method_10216(), (double)box.field_1323, (double)box.field_1320);
        double y = class_3532.method_15350((double)eyePos.method_10214(), (double)box.field_1322, (double)box.field_1325);
        double z = class_3532.method_15350((double)eyePos.method_10215(), (double)box.field_1321, (double)box.field_1324);
        return new class_243(x, y, z);
    }

    public static class_2248 getBlock(class_2338 blockPos) {
        return BlockUtil.mc.field_1687.method_8320(blockPos).method_26204();
    }
}

