/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.Subscribe
 *  net.minecraft.class_1268
 *  net.minecraft.class_1297
 *  net.minecraft.class_1747
 *  net.minecraft.class_1799
 *  net.minecraft.class_2246
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_238
 *  net.minecraft.class_243
 *  net.minecraft.class_2596
 *  net.minecraft.class_2680
 *  net.minecraft.class_2708
 *  net.minecraft.class_2879
 *  net.minecraft.class_2885
 *  net.minecraft.class_3532
 *  net.minecraft.class_3965
 */
package com.mrzak34.thunderhack.module.modules.movement;

import com.google.common.eventbus.Subscribe;
import com.mrzak34.thunderhack.event.events.MoveEvent;
import com.mrzak34.thunderhack.event.events.PacketEvent;
import com.mrzak34.thunderhack.manager.NotificationManager;
import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.BooleanSetting;
import com.mrzak34.thunderhack.settings.ModeSetting;
import com.mrzak34.thunderhack.settings.NumberSetting;
import com.mrzak34.thunderhack.util.TimerUtil;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1747;
import net.minecraft.class_1799;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2680;
import net.minecraft.class_2708;
import net.minecraft.class_2879;
import net.minecraft.class_2885;
import net.minecraft.class_3532;
import net.minecraft.class_3965;

public class ScaffoldSalhack
extends Module {
    ModeSetting mode = new ModeSetting("mode", "tower", "tower", "normal");
    BooleanSetting stopMotion = new BooleanSetting("stop motion", true);
    NumberSetting delay = new NumberSetting("delay", 0.0f, 0.0f, 1.0f, true);
    private TimerUtil _timer = new TimerUtil();
    private TimerUtil _towerPauseTimer = new TimerUtil();
    private TimerUtil _towerTimer = new TimerUtil();
    private TimerUtil _eatTimer = new TimerUtil();

    public ScaffoldSalhack() {
        super("ScaffoldSalhack", 0, false, Category.MOVEMENT);
        this.addSettings(this.mode, this.stopMotion, this.delay);
    }

    @Subscribe
    public void onMove(MoveEvent event) {
        if (ScaffoldSalhack.fullNullCheck()) {
            return;
        }
        if (!this._timer.passedMs((long)(this.delay.getValue() * 1000.0))) {
            return;
        }
        class_1799 stack = ScaffoldSalhack.mc.field_1724.method_6047();
        int prevSlot = -1;
        if (!this.verifyStack(stack)) {
            for (int i = 0; i < 9; ++i) {
                stack = ScaffoldSalhack.mc.field_1724.method_31548().method_5438(i);
                if (!this.verifyStack(stack)) continue;
                prevSlot = ScaffoldSalhack.mc.field_1724.method_31548().field_7545;
                ScaffoldSalhack.mc.field_1724.method_31548().field_7545 = i;
                ScaffoldSalhack.mc.field_1761.method_2927();
                break;
            }
        }
        if (!this.verifyStack(stack)) {
            return;
        }
        this._timer.reset();
        class_2338 toPlaceAt = null;
        class_2338 feetBlock = ScaffoldSalhack.mc.field_1724.method_24515().method_10074();
        boolean placeAtFeet = ScaffoldSalhack.mc.field_1687.method_8320(feetBlock).method_26207().method_15800();
        NotificationManager.notif("tower verify");
        if (this.mode.getMode().equals("tower") && placeAtFeet && ScaffoldSalhack.mc.field_1724.field_3913.field_3904 && this._towerTimer.passedMs(250L) && !ScaffoldSalhack.mc.field_1724.method_6128()) {
            NotificationManager.notif("tower success");
            if (this._towerPauseTimer.passedMs(1500L)) {
                this._towerPauseTimer.reset();
                ScaffoldSalhack.mc.field_1724.method_18800(0.0, -0.28, 0.0);
            } else {
                float towerMotion = 0.42f;
                ScaffoldSalhack.mc.field_1724.method_18800(0.0, (double)0.42f, 0.0);
            }
        }
        if (placeAtFeet) {
            toPlaceAt = feetBlock;
        } else {
            ValidResult result = ScaffoldSalhack.valid(feetBlock);
            if (result != ValidResult.Ok && result != ValidResult.AlreadyBlockThere) {
                class_2338[] array = new class_2338[]{feetBlock.method_10095(), feetBlock.method_10072(), feetBlock.method_10078(), feetBlock.method_10067()};
                class_2338 toSelect = null;
                double lastDistance = 420.0;
                for (class_2338 pos : array) {
                    double dist;
                    if (!ScaffoldSalhack.mc.field_1687.method_8320(pos).method_26207().method_15800() || !(lastDistance > (dist = pos.method_40081((double)((int)ScaffoldSalhack.mc.field_1724.method_23317()), (double)((int)ScaffoldSalhack.mc.field_1724.method_23318()), (double)((int)ScaffoldSalhack.mc.field_1724.method_23321()))))) continue;
                    lastDistance = dist;
                    toSelect = pos;
                }
                if (toSelect != null) {
                    toPlaceAt = toSelect;
                }
            }
        }
        if (toPlaceAt != null) {
            class_243 eyesPos = new class_243(ScaffoldSalhack.mc.field_1724.method_23317(), ScaffoldSalhack.mc.field_1724.method_23318() + ScaffoldSalhack.mc.field_1724.method_23320(), ScaffoldSalhack.mc.field_1724.method_23321());
            for (class_2350 side : class_2350.values()) {
                class_2338 neighbor = toPlaceAt.method_10093(side);
                class_2350 side2 = side.method_10153();
                class_243 hitVec = new class_243((double)neighbor.method_10263(), (double)neighbor.method_10264(), (double)neighbor.method_10260()).method_1031(0.5, 0.5, 0.5).method_1019(new class_243(side2.method_23955()));
                if (!(eyesPos.method_1022(hitVec) <= 5.0)) continue;
                float[] rot = this.getRotations(toPlaceAt, side);
                event.cancel();
                break;
            }
            ScaffoldSalhack.mc.field_1724.field_3944.method_2883((class_2596)new class_2885(class_1268.field_5808, new class_3965(new class_243((double)toPlaceAt.method_10263() + Math.random(), (double)toPlaceAt.method_10264(), (double)toPlaceAt.method_10260() + Math.random()), this.getStrictDirection(toPlaceAt), toPlaceAt, false), 1));
            ScaffoldSalhack.mc.field_1724.field_3944.method_2883((class_2596)new class_2879(class_1268.field_5808));
        } else {
            this._towerPauseTimer.reset();
        }
        if (prevSlot != -1) {
            ScaffoldSalhack.mc.field_1724.method_31548().field_7545 = prevSlot;
            ScaffoldSalhack.mc.field_1761.method_2927();
        }
    }

    private class_2350 getStrictDirection(class_2338 blockPos) {
        if (this.isBlockValid(ScaffoldSalhack.mc.field_1687.method_8320(blockPos.method_10069(0, -1, 0)).method_26204())) {
            return class_2350.field_11036;
        }
        if (this.isBlockValid(ScaffoldSalhack.mc.field_1687.method_8320(blockPos.method_10069(-1, 0, 0)).method_26204())) {
            return class_2350.field_11034;
        }
        if (this.isBlockValid(ScaffoldSalhack.mc.field_1687.method_8320(blockPos.method_10069(1, 0, 0)).method_26204())) {
            return class_2350.field_11039;
        }
        if (this.isBlockValid(ScaffoldSalhack.mc.field_1687.method_8320(blockPos.method_10069(0, 0, 1)).method_26204())) {
            return class_2350.field_11043;
        }
        if (this.isBlockValid(ScaffoldSalhack.mc.field_1687.method_8320(blockPos.method_10069(0, 0, -1)).method_26204())) {
            return class_2350.field_11035;
        }
        return null;
    }

    private boolean isBlockValid(class_2248 block) {
        return block.method_9564().method_26207().method_15799();
    }

    private float[] getRotations(class_2338 blockPos, class_2350 enumFacing) {
        class_243 vec3d = new class_243((double)blockPos.method_10263() + 0.5, (double)blockPos.method_10264() - 0.01, (double)blockPos.method_10260() + 0.5);
        vec3d = vec3d.method_1019(new class_243(enumFacing.method_23955()));
        class_243 vec3d2 = ScaffoldSalhack.mc.field_1724.method_33571();
        double d = vec3d.field_1352 - vec3d2.field_1352;
        double d2 = vec3d.field_1351 - vec3d2.field_1351;
        double d3 = vec3d.field_1350 - vec3d2.field_1350;
        double d6 = Math.sqrt(d * d + d3 * d3);
        float f = (float)(Math.toDegrees(Math.atan2(d3, d)) - 90.0);
        float f2 = (float)(-Math.toDegrees(Math.atan2(d2, d6)));
        float[] ret = new float[]{ScaffoldSalhack.mc.field_1724.method_36454() + class_3532.method_15393((float)(f - ScaffoldSalhack.mc.field_1724.method_36454())), ScaffoldSalhack.mc.field_1724.method_36455() + class_3532.method_15393((float)(f2 - ScaffoldSalhack.mc.field_1724.method_36455()))};
        return ret;
    }

    public static ValidResult valid(class_2338 pos) {
        if (!ScaffoldSalhack.mc.field_1687.method_39454((class_1297)ScaffoldSalhack.mc.field_1724, new class_238(pos))) {
            return ValidResult.NoEntityCollision;
        }
        if (ScaffoldSalhack.mc.field_1687.method_8320(pos.method_10074()).method_26204() == class_2246.field_10382) {
            return ValidResult.Ok;
        }
        if (!ScaffoldSalhack.checkForNeighbours(pos)) {
            return ValidResult.NoNeighbors;
        }
        class_2680 l_State = ScaffoldSalhack.mc.field_1687.method_8320(pos);
        if (l_State.method_26204() == class_2246.field_10124) {
            return ValidResult.NoNeighbors;
        }
        return ValidResult.AlreadyBlockThere;
    }

    public static boolean checkForNeighbours(class_2338 blockPos) {
        if (!ScaffoldSalhack.hasNeighbour(blockPos)) {
            for (class_2350 side : class_2350.values()) {
                class_2338 neighbour = blockPos.method_10093(side);
                if (ScaffoldSalhack.hasNeighbour(neighbour)) {
                    return true;
                }
                if (side != class_2350.field_11036 || ScaffoldSalhack.mc.field_1687.method_8320(blockPos).method_26204() != class_2246.field_10382 || ScaffoldSalhack.mc.field_1687.method_8320(blockPos.method_10084()).method_26204() != class_2246.field_10124) continue;
                return true;
            }
            return false;
        }
        return true;
    }

    public static boolean hasNeighbour(class_2338 blockPos) {
        for (class_2350 side : class_2350.values()) {
            class_2338 neighbour = blockPos.method_10093(side);
            if (ScaffoldSalhack.mc.field_1687.method_8320(neighbour).method_26207().method_15800()) continue;
            return true;
        }
        return false;
    }

    @Subscribe
    public void PacketReceive(PacketEvent.Receive event) {
        if (event.getPacket() instanceof class_2708) {
            this._towerTimer.reset();
        }
    }

    private boolean verifyStack(class_1799 stack) {
        return !stack.method_7960() && stack.method_7909() instanceof class_1747;
    }

    public static final class ValidResult
    extends Enum<ValidResult> {
        public static final /* enum */ ValidResult NoEntityCollision = new ValidResult();
        public static final /* enum */ ValidResult AlreadyBlockThere = new ValidResult();
        public static final /* enum */ ValidResult NoNeighbors = new ValidResult();
        public static final /* enum */ ValidResult Ok = new ValidResult();
        private static final /* synthetic */ ValidResult[] $VALUES;

        public static ValidResult[] values() {
            return (ValidResult[])$VALUES.clone();
        }

        public static ValidResult valueOf(String name) {
            return Enum.valueOf(ValidResult.class, name);
        }

        private static /* synthetic */ ValidResult[] $values() {
            return new ValidResult[]{NoEntityCollision, AlreadyBlockThere, NoNeighbors, Ok};
        }

        static {
            $VALUES = ValidResult.$values();
        }
    }
}

