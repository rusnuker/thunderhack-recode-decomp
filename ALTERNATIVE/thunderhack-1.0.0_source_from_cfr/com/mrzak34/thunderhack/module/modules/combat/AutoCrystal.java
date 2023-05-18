/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_1268
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_1511
 *  net.minecraft.class_1657
 *  net.minecraft.class_1802
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2374
 *  net.minecraft.class_2382
 *  net.minecraft.class_243
 *  net.minecraft.class_2596
 *  net.minecraft.class_2824
 *  net.minecraft.class_287
 *  net.minecraft.class_289
 *  net.minecraft.class_327
 *  net.minecraft.class_332
 *  net.minecraft.class_4184
 *  net.minecraft.class_4587
 *  net.minecraft.class_4597
 *  net.minecraft.class_4597$class_4598
 *  net.minecraft.class_7833
 */
package com.mrzak34.thunderhack.module.modules.combat;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.BooleanSetting;
import com.mrzak34.thunderhack.settings.ModeSetting;
import com.mrzak34.thunderhack.settings.NumberSetting;
import com.mrzak34.thunderhack.util.BlockUtil;
import com.mrzak34.thunderhack.util.CrystalPosition;
import com.mrzak34.thunderhack.util.CrystalUtil;
import com.mrzak34.thunderhack.util.InventoryUtil;
import com.mrzak34.thunderhack.util.MathUtil;
import com.mrzak34.thunderhack.util.RenderUtil;
import com.mrzak34.thunderhack.util.TargetUtil;
import com.mrzak34.thunderhack.util.TimerUtil;
import com.mrzak34.thunderhack.util.WorldUtil;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1511;
import net.minecraft.class_1657;
import net.minecraft.class_1802;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2374;
import net.minecraft.class_2382;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2824;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_327;
import net.minecraft.class_332;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_7833;

public class AutoCrystal
extends Module {
    NumberSetting minDMG = new NumberSetting("min DMG", 2.5f, 1.0f, 36.0f, true);
    NumberSetting targetRange = new NumberSetting("target range", 8.0f, 3.0f, 10.0f, true);
    BooleanSetting place = new BooleanSetting("place", true);
    NumberSetting placeRange = new NumberSetting("place range", 4.2f, 0.0f, 8.0f, true);
    BooleanSetting predict = new BooleanSetting("predict", true);
    ModeSetting placeMode = new ModeSetting("place mode", "new", "old", "new");
    NumberSetting wallRange = new NumberSetting("wall range", 4.2f, 0.0f, 8.0f, true);
    NumberSetting breakRange = new NumberSetting("break range", 4.2f, 0.0f, 8.0f, true);
    NumberSetting maxSelfDMG = new NumberSetting("self DMG", 36.0f, 1.0f, 36.0f, true);
    NumberSetting placeDelay = new NumberSetting("placeDelay", 1.0f, 0.0f, 20.0f, false);
    NumberSetting breakDelay = new NumberSetting("breakDelay", 1.0f, 0.0f, 20.0f, false);
    BooleanSetting autoSwitch = new BooleanSetting("autoSwitch", true);
    public BooleanSetting pauseHealthAllow = new BooleanSetting("pauseHP", false);
    public NumberSetting pauseHealth = new NumberSetting("pauseHealth", 11.0f, 0.0f, 36.0f, true);
    public BooleanSetting pauseWhileMining = new BooleanSetting("pauseWhileMining", false);
    public BooleanSetting pauseWhileEating = new BooleanSetting("pauseWhileEating", false);
    BooleanSetting breakCrys = new BooleanSetting("break", true);
    public static class_1657 target;
    CrystalPosition tempPosition;
    float dmg;
    private CrystalPosition crystal = new CrystalPosition(null, 0.0, 0.0);
    private final TimerUtil breakTimer = new TimerUtil();
    private final TimerUtil placeTimer = new TimerUtil();
    int targetColor = -1;

    public AutoCrystal() {
        super("AutoCrystal", 0, false, Category.COMBAT);
        this.addSettings(this.minDMG, this.targetRange, this.place, this.breakCrys, this.placeRange, this.predict, this.placeMode, this.wallRange, this.breakRange, this.maxSelfDMG, this.placeDelay, this.breakDelay, this.autoSwitch);
    }

    private void renderPlacePos(CrystalPosition crystal) {
        class_327 tr = AutoCrystal.mc.field_1772;
        String displayTag = String.valueOf(crystal.getTargetDamage());
        int width = tr.method_1727(displayTag) / 2;
        class_4184 camera = AutoCrystal.mc.field_1773.method_19418();
        double distance = crystal.getPosition().method_19770((class_2374)AutoCrystal.mc.field_1724.method_19538());
        double scale = distance * 10.0 / 100.0;
        if (distance <= 8.0) {
            scale = 1.0;
        }
        class_4587 matrices = RenderUtil.matrixFrom(new class_243((double)crystal.getPosition().method_10263() + 0.5, (double)crystal.getPosition().method_10264() + 0.5, (double)crystal.getPosition().method_10260() + 0.5));
        matrices.method_22907(class_7833.field_40716.rotationDegrees(-camera.method_19330()));
        matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        matrices.method_22904(0.0, 0.0, 0.0);
        matrices.method_22905(-0.025f * (float)scale, -0.025f * (float)scale, 1.0f);
        class_4597.class_4598 immediate = class_4597.method_22991((class_287)class_289.method_1348().method_1349());
        class_332.method_25303((class_4587)matrices, (class_327)AutoCrystal.mc.field_1772, (String)displayTag, (int)(-(width / 2)), (int)0, (int)-1);
    }

    private void renderNameTag(class_1657 target, class_243 pos) {
        class_327 tr = AutoCrystal.mc.field_1772;
        String displayTag = "target";
        int width = tr.method_1727(displayTag) / 2;
        class_4184 camera = AutoCrystal.mc.field_1773.method_19418();
        double distance = AutoCrystal.mc.field_1724.method_5739((class_1297)target);
        double scale = distance * 10.0 / 100.0;
        if (distance <= 8.0) {
            scale = 1.0;
        }
        class_4587 matrices = RenderUtil.matrixFrom(pos);
        matrices.method_22907(class_7833.field_40716.rotationDegrees(-camera.method_19330()));
        matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        matrices.method_22904(0.0, 0.0, 0.0);
        matrices.method_22905(-0.025f * (float)scale, -0.025f * (float)scale, 1.0f);
        class_4597.class_4598 immediate = class_4597.method_22991((class_287)class_289.method_1348().method_1349());
        class_332.method_25303((class_4587)matrices, (class_327)AutoCrystal.mc.field_1772, (String)displayTag, (int)(-(width / 2)), (int)0, (int)this.targetColor);
    }

    @Override
    public void onTick() {
        super.onTick();
        target = TargetUtil.getTarget(this.targetRange.getValue());
        if (target != null) {
            if (CrystalUtil.needCrystal(target, this.minDMG.getValue())) {
                if (this.place.isEnabled()) {
                    this.placeCrystal2();
                }
            } else if (this.breakCrys.isEnabled()) {
                this.breakCrystal();
            }
        }
    }

    private void placeCrystal2() {
        if (this.place.isEnabled()) {
            ArrayList<CrystalPosition> crystalPositions = new ArrayList<CrystalPosition>();
            for (class_2338 blockPos : BlockUtil.getNearbyPlacePos((class_1657)AutoCrystal.mc.field_1724, this.placeRange.getValue(), this.predict.isEnabled(), this.placeMode.getMode().equals("new"))) {
                double calculatedSelfDamage;
                if (Math.sqrt(blockPos.method_19770((class_2374)AutoCrystal.mc.field_1724.method_19538())) >= Math.pow(this.wallRange.getValue(), 2.0) || Math.sqrt(blockPos.method_19770((class_2374)AutoCrystal.mc.field_1724.method_19538())) >= Math.pow(this.breakRange.getValue(), 2.0)) continue;
                double calculatedTargetDamage = CrystalUtil.calculateDamage(new class_243((double)blockPos.method_10263() + 0.5, (double)(blockPos.method_10264() + 1), (double)blockPos.method_10260() + 0.5), 6.0f, (class_1309)target);
                double d = calculatedSelfDamage = AutoCrystal.mc.field_1724.method_7337() ? 0.0 : (double)CrystalUtil.calculateDamage(new class_243((double)blockPos.method_10263() + 0.5, (double)(blockPos.method_10264() + 1), (double)blockPos.method_10260() + 0.5), 6.0f, (class_1309)AutoCrystal.mc.field_1724);
                if (calculatedTargetDamage < this.minDMG.getValue() || calculatedSelfDamage > this.maxSelfDMG.getValue()) continue;
                crystalPositions.add(new CrystalPosition(blockPos, calculatedTargetDamage, calculatedSelfDamage));
            }
            this.tempPosition = crystalPositions.stream().max(Comparator.comparing(CrystalPosition::getTargetDamage)).orElse(null);
            try {
                this.dmg = CrystalUtil.calculateDamage(new class_243((double)this.tempPosition.getPosition().method_10263() + 0.5, (double)(this.tempPosition.getPosition().method_10264() + 1), (double)this.tempPosition.getPosition().method_10260() + 0.5), 6.0f, (class_1309)target);
            }
            catch (Exception exception) {
                // empty catch block
            }
            if (this.tempPosition == null) {
                this.crystal = null;
                return;
            }
            this.crystal = this.tempPosition;
            if (this.crystal.getPosition() != null && this.placeTimer.passedMs((long)(this.placeDelay.getValue() * 60.0)) && this.autoSwitch.isEnabled()) {
                InventoryUtil.switchToHotbarSlot(class_1802.field_8301, false);
            }
            if (AutoCrystal.mc.field_1724.method_6047().method_7909() == class_1802.field_8301 || AutoCrystal.mc.field_1724.method_6079().method_7909() == class_1802.field_8301 && this.placeTimer.passedMs((long)(this.placeDelay.getValue() * 60.0))) {
                CrystalUtil.placeCrystal(this.crystal.getPosition(), AutoCrystal.getDirection(this.crystal.getPosition()), false);
                this.placeTimer.reset();
            }
        }
    }

    private void placeCrystal() {
        if (this.place.isEnabled()) {
            ArrayList<CrystalPosition> crystalPositions = new ArrayList<CrystalPosition>();
            for (class_2338 blockPos : AutoCrystal.crystalBlocks((class_1657)AutoCrystal.mc.field_1724, this.placeRange.getValue(), this.predict.isEnabled(), this.placeMode.getMode().equals("new"))) {
                double calculatedSelfDamage;
                if (Math.sqrt(blockPos.method_19770((class_2374)AutoCrystal.mc.field_1724.method_19538())) >= Math.pow(this.wallRange.getValue(), 2.0) || Math.sqrt(blockPos.method_19770((class_2374)AutoCrystal.mc.field_1724.method_19538())) >= Math.pow(this.breakRange.getValue(), 2.0)) continue;
                double calculatedTargetDamage = CrystalUtil.calculateDamage(new class_243((double)blockPos.method_10263() + 0.5, (double)(blockPos.method_10264() + 1), (double)blockPos.method_10260() + 0.5), 6.0f, (class_1309)target);
                double d = calculatedSelfDamage = AutoCrystal.mc.field_1724.method_7337() ? 0.0 : (double)CrystalUtil.calculateDamage(new class_243((double)blockPos.method_10263() + 0.5, (double)(blockPos.method_10264() + 1), (double)blockPos.method_10260() + 0.5), 6.0f, (class_1309)AutoCrystal.mc.field_1724);
                if (calculatedTargetDamage < this.minDMG.getValue() || calculatedSelfDamage > this.maxSelfDMG.getValue()) continue;
                crystalPositions.add(new CrystalPosition(blockPos, calculatedTargetDamage, calculatedSelfDamage));
            }
            this.tempPosition = crystalPositions.stream().max(Comparator.comparing(CrystalPosition::getTargetDamage)).orElse(null);
            try {
                this.dmg = CrystalUtil.calculateDamage(new class_243((double)this.tempPosition.getPosition().method_10263() + 0.5, (double)(this.tempPosition.getPosition().method_10264() + 1), (double)this.tempPosition.getPosition().method_10260() + 0.5), 6.0f, (class_1309)target);
            }
            catch (Exception exception) {
                // empty catch block
            }
            if (this.tempPosition == null) {
                this.crystal = null;
                return;
            }
            this.crystal = this.tempPosition;
            if (this.crystal.getPosition() != null && this.placeTimer.passedMs((long)(this.placeDelay.getValue() * 60.0)) && this.autoSwitch.isEnabled()) {
                InventoryUtil.switchToHotbarSlot(class_1802.field_8301, false);
            }
            if (AutoCrystal.mc.field_1724.method_6047().method_7909() == class_1802.field_8301 || AutoCrystal.mc.field_1724.method_6079().method_7909() == class_1802.field_8301 && this.placeTimer.passedMs((long)(this.placeDelay.getValue() * 60.0))) {
                CrystalUtil.placeCrystal(this.crystal.getPosition(), AutoCrystal.getDirection(this.crystal.getPosition()), false);
                this.placeTimer.reset();
            }
        }
    }

    public static class_2350 getDirection(class_2338 block) {
        class_243 eyeVec = AutoCrystal.mc.field_1724.method_33571();
        class_243 vec = class_243.method_26410((class_2382)block, (double)1.0);
        for (class_2350 d : class_2350.values()) {
            class_243 vd = WorldUtil.getLegitLookPos(block, d, true, 5);
            if (vd == null || !(eyeVec.method_1022(vd) <= eyeVec.method_1022(vec))) continue;
            return d;
        }
        return (double)block.method_10264() > AutoCrystal.mc.field_1724.method_23320() ? class_2350.field_11033 : class_2350.field_11036;
    }

    private void breakCrystal() {
        ArrayList crystals = Lists.newArrayList();
        for (class_1297 entity2 : AutoCrystal.mc.field_1687.method_18112()) {
            if (!(entity2 instanceof class_1511)) continue;
            crystals.add((class_1511)entity2);
        }
        class_1511 entityEnderCrystal = crystals.stream().filter(entity -> entity instanceof class_1511).min(Comparator.comparing(entity -> Float.valueOf(AutoCrystal.mc.field_1724.method_5739((class_1297)entity)))).orElse(null);
        if (entityEnderCrystal != null && this.breakTimer.passedMs((long)(this.breakDelay.getValue() * 60.0))) {
            if (!(AutoCrystal.mc.field_1724.method_5858((class_1297)entityEnderCrystal) <= MathUtil.square(this.breakRange.getValue()))) {
                return;
            }
            if (!AutoCrystal.mc.field_1724.method_6057((class_1297)entityEnderCrystal) && AutoCrystal.mc.field_1724.method_5858((class_1297)entityEnderCrystal) >= MathUtil.square(this.wallRange.getValue())) {
                return;
            }
            AutoCrystal.attackEntity(entityEnderCrystal.method_5628());
            this.breakTimer.reset();
        }
    }

    public static void attackEntity(int entityId) {
        if (entityId == 0) {
            return;
        }
        AutoCrystal.mc.field_1724.field_3944.method_2883((class_2596)class_2824.method_34206((class_1297)AutoCrystal.mc.field_1687.method_8469(entityId), (boolean)AutoCrystal.mc.field_1724.method_5715()));
        AutoCrystal.mc.field_1724.method_6104(class_1268.field_5808);
    }

    public static List<class_2338> crystalBlocks(class_1657 playerEntity, double placeRange, boolean prediction, boolean thirteen) {
        return BlockUtil.getNearbyBlocks(playerEntity, placeRange, prediction).stream().filter(blockPos -> CrystalUtil.canPlaceCrystal(blockPos, false, thirteen)).collect(Collectors.toList());
    }
}

