/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.Subscribe
 *  net.minecraft.class_1268
 *  net.minecraft.class_1297
 *  net.minecraft.class_1747
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_243
 *  net.minecraft.class_2596
 *  net.minecraft.class_2868
 *  net.minecraft.class_2879
 *  net.minecraft.class_2885
 *  net.minecraft.class_3532
 *  net.minecraft.class_3965
 */
package com.mrzak34.thunderhack.module.modules.movement;

import com.google.common.eventbus.Subscribe;
import com.mrzak34.thunderhack.event.events.MoveEvent;
import com.mrzak34.thunderhack.manager.NotificationManager;
import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.BooleanSetting;
import com.mrzak34.thunderhack.settings.NumberSetting;
import com.mrzak34.thunderhack.util.TimerUtil;
import java.awt.Color;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1747;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2868;
import net.minecraft.class_2879;
import net.minecraft.class_2885;
import net.minecraft.class_3532;
import net.minecraft.class_3965;

public class ScaffoldRH
extends Module {
    public Color color = new Color(Color.CYAN.getRed(), Color.CYAN.getGreen(), Color.CYAN.getBlue(), 50);
    BooleanSetting rotate = new BooleanSetting("rotate", true);
    BooleanSetting autoswap = new BooleanSetting("autoswap", true);
    BooleanSetting tower = new BooleanSetting("tower", true);
    BooleanSetting safewalk = new BooleanSetting("safewalk", true);
    NumberSetting flySpeed = new NumberSetting("fly speed", 0.42f, 0.1f, 1.0f, true);
    private TimerUtil timer = new TimerUtil();
    private BlockPosWithFacing currentblock;
    int n;
    class_2338 blockPos;

    public ScaffoldRH() {
        super("ScaffoldRH", 0, false, Category.MOVEMENT);
        this.addSettings(this.rotate, this.autoswap, this.tower, this.safewalk, this.flySpeed);
    }

    private boolean isBlockValid(class_2248 block) {
        return block.method_9564().method_26207().method_15799();
    }

    private BlockPosWithFacing checkNearBlocks(class_2338 blockPos) {
        if (this.isBlockValid(ScaffoldRH.mc.field_1687.method_8320(blockPos.method_10069(0, -1, 0)).method_26204())) {
            return new BlockPosWithFacing(blockPos.method_10069(0, -1, 0), class_2350.field_11036);
        }
        if (this.isBlockValid(ScaffoldRH.mc.field_1687.method_8320(blockPos.method_10069(-1, 0, 0)).method_26204())) {
            return new BlockPosWithFacing(blockPos.method_10069(-1, 0, 0), class_2350.field_11034);
        }
        if (this.isBlockValid(ScaffoldRH.mc.field_1687.method_8320(blockPos.method_10069(1, 0, 0)).method_26204())) {
            return new BlockPosWithFacing(blockPos.method_10069(1, 0, 0), class_2350.field_11039);
        }
        if (this.isBlockValid(ScaffoldRH.mc.field_1687.method_8320(blockPos.method_10069(0, 0, 1)).method_26204())) {
            return new BlockPosWithFacing(blockPos.method_10069(0, 0, 1), class_2350.field_11043);
        }
        if (this.isBlockValid(ScaffoldRH.mc.field_1687.method_8320(blockPos.method_10069(0, 0, -1)).method_26204())) {
            return new BlockPosWithFacing(blockPos.method_10069(0, 0, -1), class_2350.field_11035);
        }
        return null;
    }

    private int findBlockToPlace() {
        if (ScaffoldRH.mc.field_1724.method_6047().method_7909() instanceof class_1747 && this.isBlockValid(((class_1747)ScaffoldRH.mc.field_1724.method_6047().method_7909()).method_7711())) {
            return ScaffoldRH.mc.field_1724.method_31548().field_7545;
        }
        int n = 0;
        int n2 = 0;
        while (n2 < 9) {
            if (ScaffoldRH.mc.field_1724.method_31548().method_5438(n).method_7947() != 0 && ScaffoldRH.mc.field_1724.method_31548().method_5438(n).method_7909() instanceof class_1747 && this.isBlockValid(((class_1747)ScaffoldRH.mc.field_1724.method_31548().method_5438(n).method_7909()).method_7711())) {
                return n;
            }
            n2 = ++n;
        }
        return -1;
    }

    private BlockPosWithFacing checkNearBlocksExtended(class_2338 blockPos) {
        BlockPosWithFacing ret = null;
        ret = this.checkNearBlocks(blockPos);
        if (ret != null) {
            return ret;
        }
        ret = this.checkNearBlocks(blockPos.method_10069(-1, 0, 0));
        if (ret != null) {
            return ret;
        }
        ret = this.checkNearBlocks(blockPos.method_10069(1, 0, 0));
        if (ret != null) {
            return ret;
        }
        ret = this.checkNearBlocks(blockPos.method_10069(0, 0, 1));
        if (ret != null) {
            return ret;
        }
        ret = this.checkNearBlocks(blockPos.method_10069(0, 0, -1));
        if (ret != null) {
            return ret;
        }
        ret = this.checkNearBlocks(blockPos.method_10069(-2, 0, 0));
        if (ret != null) {
            return ret;
        }
        ret = this.checkNearBlocks(blockPos.method_10069(2, 0, 0));
        if (ret != null) {
            return ret;
        }
        ret = this.checkNearBlocks(blockPos.method_10069(0, 0, 2));
        if (ret != null) {
            return ret;
        }
        ret = this.checkNearBlocks(blockPos.method_10069(0, 0, -2));
        if (ret != null) {
            return ret;
        }
        ret = this.checkNearBlocks(blockPos.method_10069(0, -1, 0));
        class_2338 blockPos2 = blockPos.method_10069(0, -1, 0);
        if (ret != null) {
            return ret;
        }
        ret = this.checkNearBlocks(blockPos2.method_10069(1, 0, 0));
        if (ret != null) {
            return ret;
        }
        ret = this.checkNearBlocks(blockPos2.method_10069(-1, 0, 0));
        if (ret != null) {
            return ret;
        }
        ret = this.checkNearBlocks(blockPos2.method_10069(0, 0, 1));
        if (ret != null) {
            return ret;
        }
        return this.checkNearBlocks(blockPos2.method_10069(0, 0, -1));
    }

    private int countValidBlocks() {
        int n2 = 0;
        for (int n = 0; n < 9; ++n) {
            class_1799 itemStack;
            if (ScaffoldRH.mc.field_1724.method_31548().method_5438(n).method_7909() instanceof class_1747) {
                NotificationManager.notif("block found");
                itemStack = ScaffoldRH.mc.field_1724.method_31548().method_5438(n);
                if (!this.isBlockValid(((class_1747)itemStack.method_7909()).method_7711())) continue;
                n2 += itemStack.method_7947();
                continue;
            }
            itemStack = ScaffoldRH.mc.field_1724.method_31548().method_5438(n);
            NotificationManager.notif("stack is: " + itemStack.method_7909().method_7848().getString());
        }
        return n2;
    }

    private class_243 getEyePosition() {
        return new class_243(ScaffoldRH.mc.field_1724.method_23317(), ScaffoldRH.mc.field_1724.method_23318() + ScaffoldRH.mc.field_1724.method_23320(), ScaffoldRH.mc.field_1724.method_23321());
    }

    private float[] getRotations(class_2338 blockPos, class_2350 enumFacing) {
        class_243 vec3d = new class_243((double)blockPos.method_10263() + 0.5, (double)blockPos.method_10264() - 0.01, (double)blockPos.method_10260() + 0.5);
        vec3d = vec3d.method_1019(new class_243(enumFacing.method_23955()));
        class_243 vec3d2 = this.getEyePosition();
        double d = vec3d.field_1352 - vec3d2.field_1352;
        double d2 = vec3d.field_1351 - vec3d2.field_1351;
        double d3 = vec3d.field_1350 - vec3d2.field_1350;
        double d6 = Math.sqrt(d * d + d3 * d3);
        float f = (float)(Math.toDegrees(Math.atan2(d3, d)) - 90.0);
        float f2 = (float)(-Math.toDegrees(Math.atan2(d2, d6)));
        float[] ret = new float[]{ScaffoldRH.mc.field_1724.method_36454() + class_3532.method_15393((float)(f - ScaffoldRH.mc.field_1724.method_36454())), ScaffoldRH.mc.field_1724.method_36455() + class_3532.method_15393((float)(f2 - ScaffoldRH.mc.field_1724.method_36455()))};
        return ret;
    }

    private void doSafeWalk(MoveEvent event) {
        double x = event.getX();
        double y = event.getY();
        double z = event.getZ();
        if (ScaffoldRH.mc.field_1724.method_24828() && !ScaffoldRH.mc.field_1724.field_5960) {
            double increment = 0.05;
            while (x != 0.0 && this.isOffsetBBEmpty(x, 0.0)) {
                if (x < increment && x >= -increment) {
                    x = 0.0;
                    continue;
                }
                if (x > 0.0) {
                    x -= increment;
                    continue;
                }
                x += increment;
            }
            while (z != 0.0 && this.isOffsetBBEmpty(0.0, z)) {
                if (z < increment && z >= -increment) {
                    z = 0.0;
                    continue;
                }
                if (z > 0.0) {
                    z -= increment;
                    continue;
                }
                z += increment;
            }
            while (x != 0.0 && z != 0.0 && this.isOffsetBBEmpty(x, z)) {
                x = x < increment && x >= -increment ? 0.0 : (x > 0.0 ? (x -= increment) : (x += increment));
                if (z < increment && z >= -increment) {
                    z = 0.0;
                    continue;
                }
                if (z > 0.0) {
                    z -= increment;
                    continue;
                }
                z += increment;
            }
        }
        event.setX(x);
        event.setY(y);
        event.setZ(z);
        event.setCancelled(true);
    }

    @Subscribe
    public void onMove(MoveEvent event) {
        if (ScaffoldRH.fullNullCheck()) {
            return;
        }
        if (this.safewalk.isEnabled()) {
            this.doSafeWalk(event);
        }
    }

    private boolean isOffsetBBEmpty(double x, double z) {
        return ScaffoldRH.mc.field_1687.method_8600((class_1297)ScaffoldRH.mc.field_1724, ScaffoldRH.mc.field_1724.method_5829().method_989(x, -2.0, z)) != null;
    }

    @Override
    public void onTick() {
        int n3;
        super.onTick();
        if (this.countValidBlocks() <= 0) {
            NotificationManager.notif("blocks not found");
            this.currentblock = null;
            return;
        }
        NotificationManager.notif("valid detector: valid");
        if (ScaffoldRH.mc.field_1724.method_23318() < 257.0 && this.autoswap.isEnabled()) {
            this.currentblock = null;
            if (ScaffoldRH.mc.field_1724.method_5715()) {
                return;
            }
            int n2 = this.findBlockToPlace();
            if (n2 == -1) {
                return;
            }
            class_1792 item = ScaffoldRH.mc.field_1724.method_31548().method_5438(n2).method_7909();
            if (!(item instanceof class_1747)) {
                return;
            }
            class_2248 block = ((class_1747)item).method_7711();
            boolean bl = true;
            double d = bl ? 1.0 : 0.01;
            class_2338 blockPos2 = new class_2338((int)ScaffoldRH.mc.field_1724.method_23317(), (int)(ScaffoldRH.mc.field_1724.method_23318() - d), (int)ScaffoldRH.mc.field_1724.method_23321());
            if (!ScaffoldRH.mc.field_1687.method_8320(blockPos2).method_26207().method_15800()) {
                return;
            }
            if (bl) {
                this.currentblock = this.checkNearBlocksExtended(blockPos2);
                if (this.currentblock != null && this.rotate.isEnabled()) {
                    float[] arrf = this.getRotations(this.currentblock.blockPos, this.currentblock.enumFacing);
                }
            }
        }
        if (this.currentblock == null) {
            return;
        }
        NotificationManager.notif("currentblock: not null");
        this.n = ScaffoldRH.mc.field_1724.method_31548().field_7545;
        if (!(ScaffoldRH.mc.field_1724.method_6047().method_7909() instanceof class_1747) && this.autoswap.isEnabled() && (n3 = this.findBlockToPlace()) != -1) {
            ScaffoldRH.mc.field_1724.method_31548().field_7545 = n3;
            ScaffoldRH.mc.field_1724.field_3944.method_2883((class_2596)new class_2868(ScaffoldRH.mc.field_1724.method_31548().field_7545));
        }
        if (this.isBlockValid(((class_1747)ScaffoldRH.mc.field_1724.method_6047().method_7909()).method_7711())) {
            if (!ScaffoldRH.mc.field_1724.field_3913.field_3904 || ScaffoldRH.mc.field_1724.field_6250 != 0.0f || ScaffoldRH.mc.field_1724.field_6212 != 0.0f || !this.tower.isEnabled()) {
                this.timer.reset();
            } else {
                ScaffoldRH.mc.field_1724.method_18800(0.0, this.flySpeed.getValue(), 0.0);
                if (this.timer.passedMs(1500L)) {
                    ScaffoldRH.mc.field_1724.method_18800(0.0, -0.28, 0.0);
                    this.timer.reset();
                }
            }
            class_2338 blockPos3 = this.blockPos = this.currentblock.blockPos;
            boolean rightclicable = false;
            if (rightclicable) {
                // empty if block
            }
            ScaffoldRH.mc.field_1724.field_3944.method_2883((class_2596)new class_2885(class_1268.field_5808, new class_3965(new class_243((double)this.blockPos.method_10263() + Math.random(), (double)this.blockPos.method_10264(), (double)this.blockPos.method_10260() + Math.random()), this.currentblock.enumFacing, this.blockPos, false), 1));
            ScaffoldRH.mc.field_1724.field_3944.method_2883((class_2596)new class_2879(class_1268.field_5808));
            if (rightclicable) {
                // empty if block
            }
            ScaffoldRH.mc.field_1724.method_31548().field_7545 = this.n;
            ScaffoldRH.mc.field_1724.field_3944.method_2883((class_2596)new class_2868(ScaffoldRH.mc.field_1724.method_31548().field_7545));
        }
    }

    public static class BlockPosWithFacing {
        public class_2338 blockPos;
        public class_2350 enumFacing;

        public BlockPosWithFacing(class_2338 blockPos, class_2350 enumFacing) {
            this.blockPos = blockPos;
            this.enumFacing = enumFacing;
        }
    }
}

