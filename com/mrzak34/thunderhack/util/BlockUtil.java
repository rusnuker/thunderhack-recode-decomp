//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.util;

import java.util.*;
import net.minecraft.*;

public class BlockUtil implements Util
{
    public static boolean canBreak(final class_2338 blockPos, final class_2680 state) {
        return (BlockUtil.mc.field_1724.method_7337() || state.method_26214((class_1922)BlockUtil.mc.field_1687, blockPos) >= 0.0f) && state.method_26218((class_1922)BlockUtil.mc.field_1687, blockPos) != class_259.method_1073();
    }
    
    public static boolean canBreak(final class_2338 blockPos) {
        return canBreak(blockPos, BlockUtil.mc.field_1687.method_8320(blockPos));
    }
    
    public static List<class_2338> getNearbyBlocks(final class_1657 entityPlayer, final double blockRange, final boolean motion) {
        final List<class_2338> nearbyBlocks = new ArrayList<class_2338>();
        final int range = (int)Math.floor(blockRange);
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
    
    public static List<class_2338> getNearbyPlacePos(final class_1657 entityPlayer, final double blockRange, final boolean motion, final boolean thirteen) {
        final List<class_2338> nearbyBlocks = new ArrayList<class_2338>();
        final int range = (int)Math.floor(blockRange);
        if (motion) {
            entityPlayer.method_19538().method_1031(entityPlayer.method_18798().field_1352, entityPlayer.method_18798().field_1351, entityPlayer.method_18798().field_1350);
        }
        for (int x = -range; x <= range; ++x) {
            for (int y = -range; y <= 1; ++y) {
                for (int z = -range; z <= range; ++z) {
                    if (CrystalUtil.canPlaceCrystal(new class_2338(x, y, z), false, thirteen)) {
                        nearbyBlocks.add(entityPlayer.method_24515().method_10069(x, y, z));
                    }
                }
            }
        }
        return nearbyBlocks;
    }
    
    public static class_2350 calcSide(final class_2338 pos) {
        final class_243 vec3d = new class_243(pos.method_10263() + 0.5, pos.method_10264() + 0.5, pos.method_10260() + 0.5);
        final class_2350 side = (pos.method_10264() > BlockUtil.mc.field_1724.method_23320()) ? class_2350.field_11033 : class_2350.field_11036;
        return side;
    }
    
    public static boolean placeBlock(final class_2338 pos) {
        final class_2248 block = BlockUtil.mc.field_1687.method_8320(pos).method_26204();
        final class_2350 direction = class_2350.field_11033;
        if (direction == null) {
            return false;
        }
        BlockUtil.mc.field_1724.field_3944.method_2883((class_2596)new class_2885(class_1268.field_5808, new class_3965(new class_243((double)pos.method_10263(), (double)pos.method_10264(), (double)pos.method_10260()), direction, pos, false), 1));
        BlockUtil.mc.field_1724.field_3944.method_2883((class_2596)new class_2879(class_1268.field_5808));
        BlockUtil.mc.field_1761.method_2927();
        return true;
    }
    
    public static class_238 getBoundingBox(final class_2338 pos) {
        return new class_238((double)pos.method_10263(), (double)pos.method_10264(), (double)pos.method_10260(), (double)(pos.method_10263() + 1), (double)(pos.method_10264() + 1), (double)(pos.method_10260() + 1));
    }
    
    public static class_243 closestVec3d(final class_2338 blockPos) {
        return closestVec3d(getBoundingBox(blockPos));
    }
    
    private static class_243 closestVec3d(final class_238 box) {
        if (box == null) {
            return new class_243(0.0, 0.0, 0.0);
        }
        final class_243 eyePos = new class_243(BlockUtil.mc.field_1724.method_23317(), BlockUtil.mc.field_1724.method_23318() + BlockUtil.mc.field_1724.method_18381(BlockUtil.mc.field_1724.method_18376()), BlockUtil.mc.field_1724.method_23321());
        final double x = class_3532.method_15350(eyePos.method_10216(), box.field_1323, box.field_1320);
        final double y = class_3532.method_15350(eyePos.method_10214(), box.field_1322, box.field_1325);
        final double z = class_3532.method_15350(eyePos.method_10215(), box.field_1321, box.field_1324);
        return new class_243(x, y, z);
    }
    
    public static class_2248 getBlock(final class_2338 blockPos) {
        return BlockUtil.mc.field_1687.method_8320(blockPos).method_26204();
    }
}
