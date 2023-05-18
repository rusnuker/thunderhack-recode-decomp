//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.module.modules.combat;

import com.mrzak34.thunderhack.module.*;
import com.mrzak34.thunderhack.settings.*;
import com.mojang.blaze3d.systems.*;
import java.util.function.*;
import java.util.*;
import com.google.common.collect.*;
import com.mrzak34.thunderhack.util.*;
import net.minecraft.*;
import java.util.stream.*;

public class AutoCrystal extends Module
{
    NumberSetting minDMG;
    NumberSetting targetRange;
    BooleanSetting place;
    NumberSetting placeRange;
    BooleanSetting predict;
    ModeSetting placeMode;
    NumberSetting wallRange;
    NumberSetting breakRange;
    NumberSetting maxSelfDMG;
    NumberSetting placeDelay;
    NumberSetting breakDelay;
    BooleanSetting autoSwitch;
    public BooleanSetting pauseHealthAllow;
    public NumberSetting pauseHealth;
    public BooleanSetting pauseWhileMining;
    public BooleanSetting pauseWhileEating;
    BooleanSetting breakCrys;
    public static class_1657 target;
    CrystalPosition tempPosition;
    float dmg;
    private CrystalPosition crystal;
    private final TimerUtil breakTimer;
    private final TimerUtil placeTimer;
    int targetColor;
    
    public AutoCrystal() {
        super("AutoCrystal", 0, false, Category.COMBAT);
        this.minDMG = new NumberSetting("min DMG", 2.5f, 1.0f, 36.0f, true);
        this.targetRange = new NumberSetting("target range", 8.0f, 3.0f, 10.0f, true);
        this.place = new BooleanSetting("place", true);
        this.placeRange = new NumberSetting("place range", 4.2f, 0.0f, 8.0f, true);
        this.predict = new BooleanSetting("predict", true);
        this.placeMode = new ModeSetting("place mode", "new", new String[] { "old", "new" });
        this.wallRange = new NumberSetting("wall range", 4.2f, 0.0f, 8.0f, true);
        this.breakRange = new NumberSetting("break range", 4.2f, 0.0f, 8.0f, true);
        this.maxSelfDMG = new NumberSetting("self DMG", 36.0f, 1.0f, 36.0f, true);
        this.placeDelay = new NumberSetting("placeDelay", 1.0f, 0.0f, 20.0f, false);
        this.breakDelay = new NumberSetting("breakDelay", 1.0f, 0.0f, 20.0f, false);
        this.autoSwitch = new BooleanSetting("autoSwitch", true);
        this.pauseHealthAllow = new BooleanSetting("pauseHP", false);
        this.pauseHealth = new NumberSetting("pauseHealth", 11.0f, 0.0f, 36.0f, true);
        this.pauseWhileMining = new BooleanSetting("pauseWhileMining", false);
        this.pauseWhileEating = new BooleanSetting("pauseWhileEating", false);
        this.breakCrys = new BooleanSetting("break", true);
        this.crystal = new CrystalPosition(null, 0.0, 0.0);
        this.breakTimer = new TimerUtil();
        this.placeTimer = new TimerUtil();
        this.targetColor = -1;
        this.addSettings(new Setting[] { this.minDMG, this.targetRange, this.place, this.breakCrys, this.placeRange, this.predict, this.placeMode, this.wallRange, this.breakRange, this.maxSelfDMG, this.placeDelay, this.breakDelay, this.autoSwitch });
    }
    
    private void renderPlacePos(final CrystalPosition crystal) {
        final class_327 tr = AutoCrystal.mc.field_1772;
        final String displayTag = String.valueOf(crystal.getTargetDamage());
        final int width = tr.method_1727(displayTag) / 2;
        final class_4184 camera = AutoCrystal.mc.field_1773.method_19418();
        final double distance = crystal.getPosition().method_19770((class_2374)AutoCrystal.mc.field_1724.method_19538());
        double scale = distance * 10.0 / 100.0;
        if (distance <= 8.0) {
            scale = 1.0;
        }
        final class_4587 matrices = RenderUtil.matrixFrom(new class_243(crystal.getPosition().method_10263() + 0.5, crystal.getPosition().method_10264() + 0.5, crystal.getPosition().method_10260() + 0.5));
        matrices.method_22907(class_7833.field_40716.rotationDegrees(-camera.method_19330()));
        matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        matrices.method_22904(0.0, 0.0, 0.0);
        matrices.method_22905(-0.025f * (float)scale, -0.025f * (float)scale, 1.0f);
        final class_4597.class_4598 immediate = class_4597.method_22991(class_289.method_1348().method_1349());
        class_332.method_25303(matrices, AutoCrystal.mc.field_1772, displayTag, -(width / 2), 0, -1);
    }
    
    private void renderNameTag(final class_1657 target, final class_243 pos) {
        final class_327 tr = AutoCrystal.mc.field_1772;
        final String displayTag = "target";
        final int width = tr.method_1727(displayTag) / 2;
        final class_4184 camera = AutoCrystal.mc.field_1773.method_19418();
        final double distance = AutoCrystal.mc.field_1724.method_5739((class_1297)target);
        double scale = distance * 10.0 / 100.0;
        if (distance <= 8.0) {
            scale = 1.0;
        }
        final class_4587 matrices = RenderUtil.matrixFrom(pos);
        matrices.method_22907(class_7833.field_40716.rotationDegrees(-camera.method_19330()));
        matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        matrices.method_22904(0.0, 0.0, 0.0);
        matrices.method_22905(-0.025f * (float)scale, -0.025f * (float)scale, 1.0f);
        final class_4597.class_4598 immediate = class_4597.method_22991(class_289.method_1348().method_1349());
        class_332.method_25303(matrices, AutoCrystal.mc.field_1772, displayTag, -(width / 2), 0, this.targetColor);
    }
    
    public void onTick() {
        super.onTick();
        AutoCrystal.target = TargetUtil.getTarget(this.targetRange.getValue());
        if (AutoCrystal.target != null) {
            if (CrystalUtil.needCrystal(AutoCrystal.target, this.minDMG.getValue())) {
                if (this.place.isEnabled()) {
                    this.placeCrystal2();
                }
            }
            else if (this.breakCrys.isEnabled()) {
                this.breakCrystal();
            }
        }
    }
    
    private void placeCrystal2() {
        if (this.place.isEnabled()) {
            final List<CrystalPosition> crystalPositions = new ArrayList<CrystalPosition>();
            for (final class_2338 blockPos : BlockUtil.getNearbyPlacePos((class_1657)AutoCrystal.mc.field_1724, this.placeRange.getValue(), this.predict.isEnabled(), this.placeMode.getMode().equals("new"))) {
                if (Math.sqrt(blockPos.method_19770((class_2374)AutoCrystal.mc.field_1724.method_19538())) >= Math.pow(this.wallRange.getValue(), 2.0)) {
                    continue;
                }
                if (Math.sqrt(blockPos.method_19770((class_2374)AutoCrystal.mc.field_1724.method_19538())) >= Math.pow(this.breakRange.getValue(), 2.0)) {
                    continue;
                }
                final double calculatedTargetDamage = CrystalUtil.calculateDamage(new class_243(blockPos.method_10263() + 0.5, (double)(blockPos.method_10264() + 1), blockPos.method_10260() + 0.5), 6.0f, (class_1309)AutoCrystal.target);
                final double calculatedSelfDamage = AutoCrystal.mc.field_1724.method_7337() ? 0.0 : CrystalUtil.calculateDamage(new class_243(blockPos.method_10263() + 0.5, (double)(blockPos.method_10264() + 1), blockPos.method_10260() + 0.5), 6.0f, (class_1309)AutoCrystal.mc.field_1724);
                if (calculatedTargetDamage < this.minDMG.getValue()) {
                    continue;
                }
                if (calculatedSelfDamage > this.maxSelfDMG.getValue()) {
                    continue;
                }
                crystalPositions.add(new CrystalPosition(blockPos, calculatedTargetDamage, calculatedSelfDamage));
            }
            this.tempPosition = crystalPositions.stream().max(Comparator.comparing((Function<? super CrystalPosition, ? extends Comparable>)CrystalPosition::getTargetDamage)).orElse(null);
            try {
                this.dmg = CrystalUtil.calculateDamage(new class_243(this.tempPosition.getPosition().method_10263() + 0.5, (double)(this.tempPosition.getPosition().method_10264() + 1), this.tempPosition.getPosition().method_10260() + 0.5), 6.0f, (class_1309)AutoCrystal.target);
            }
            catch (Exception ex) {}
            if (this.tempPosition == null) {
                this.crystal = null;
                return;
            }
            this.crystal = this.tempPosition;
            if (this.crystal.getPosition() != null && this.placeTimer.passedMs((long)(this.placeDelay.getValue() * 60.0)) && this.autoSwitch.isEnabled()) {
                InventoryUtil.switchToHotbarSlot(class_1802.field_8301, false);
            }
            if (AutoCrystal.mc.field_1724.method_6047().method_7909() == class_1802.field_8301 || (AutoCrystal.mc.field_1724.method_6079().method_7909() == class_1802.field_8301 && this.placeTimer.passedMs((long)(this.placeDelay.getValue() * 60.0)))) {
                CrystalUtil.placeCrystal(this.crystal.getPosition(), getDirection(this.crystal.getPosition()), false);
                this.placeTimer.reset();
            }
        }
    }
    
    private void placeCrystal() {
        if (this.place.isEnabled()) {
            final List<CrystalPosition> crystalPositions = new ArrayList<CrystalPosition>();
            for (final class_2338 blockPos : crystalBlocks((class_1657)AutoCrystal.mc.field_1724, this.placeRange.getValue(), this.predict.isEnabled(), this.placeMode.getMode().equals("new"))) {
                if (Math.sqrt(blockPos.method_19770((class_2374)AutoCrystal.mc.field_1724.method_19538())) >= Math.pow(this.wallRange.getValue(), 2.0)) {
                    continue;
                }
                if (Math.sqrt(blockPos.method_19770((class_2374)AutoCrystal.mc.field_1724.method_19538())) >= Math.pow(this.breakRange.getValue(), 2.0)) {
                    continue;
                }
                final double calculatedTargetDamage = CrystalUtil.calculateDamage(new class_243(blockPos.method_10263() + 0.5, (double)(blockPos.method_10264() + 1), blockPos.method_10260() + 0.5), 6.0f, (class_1309)AutoCrystal.target);
                final double calculatedSelfDamage = AutoCrystal.mc.field_1724.method_7337() ? 0.0 : CrystalUtil.calculateDamage(new class_243(blockPos.method_10263() + 0.5, (double)(blockPos.method_10264() + 1), blockPos.method_10260() + 0.5), 6.0f, (class_1309)AutoCrystal.mc.field_1724);
                if (calculatedTargetDamage < this.minDMG.getValue()) {
                    continue;
                }
                if (calculatedSelfDamage > this.maxSelfDMG.getValue()) {
                    continue;
                }
                crystalPositions.add(new CrystalPosition(blockPos, calculatedTargetDamage, calculatedSelfDamage));
            }
            this.tempPosition = crystalPositions.stream().max(Comparator.comparing((Function<? super CrystalPosition, ? extends Comparable>)CrystalPosition::getTargetDamage)).orElse(null);
            try {
                this.dmg = CrystalUtil.calculateDamage(new class_243(this.tempPosition.getPosition().method_10263() + 0.5, (double)(this.tempPosition.getPosition().method_10264() + 1), this.tempPosition.getPosition().method_10260() + 0.5), 6.0f, (class_1309)AutoCrystal.target);
            }
            catch (Exception ex) {}
            if (this.tempPosition == null) {
                this.crystal = null;
                return;
            }
            this.crystal = this.tempPosition;
            if (this.crystal.getPosition() != null && this.placeTimer.passedMs((long)(this.placeDelay.getValue() * 60.0)) && this.autoSwitch.isEnabled()) {
                InventoryUtil.switchToHotbarSlot(class_1802.field_8301, false);
            }
            if (AutoCrystal.mc.field_1724.method_6047().method_7909() == class_1802.field_8301 || (AutoCrystal.mc.field_1724.method_6079().method_7909() == class_1802.field_8301 && this.placeTimer.passedMs((long)(this.placeDelay.getValue() * 60.0)))) {
                CrystalUtil.placeCrystal(this.crystal.getPosition(), getDirection(this.crystal.getPosition()), false);
                this.placeTimer.reset();
            }
        }
    }
    
    public static class_2350 getDirection(final class_2338 block) {
        final class_243 eyeVec = AutoCrystal.mc.field_1724.method_33571();
        final class_243 vec = class_243.method_26410((class_2382)block, 1.0);
        for (final class_2350 d : class_2350.values()) {
            final class_243 vd = WorldUtil.getLegitLookPos(block, d, true, 5);
            if (vd != null && eyeVec.method_1022(vd) <= eyeVec.method_1022(vec)) {
                return d;
            }
        }
        return (block.method_10264() > AutoCrystal.mc.field_1724.method_23320()) ? class_2350.field_11033 : class_2350.field_11036;
    }
    
    private void breakCrystal() {
        final List<class_1511> crystals = (List<class_1511>)Lists.newArrayList();
        for (final class_1297 entity2 : AutoCrystal.mc.field_1687.method_18112()) {
            if (entity2 instanceof class_1511) {
                crystals.add((class_1511)entity2);
            }
        }
        final class_1511 entityEnderCrystal = crystals.stream().filter(entity -> entity instanceof class_1511).min(Comparator.comparing(entity -> AutoCrystal.mc.field_1724.method_5739(entity))).orElse(null);
        if (entityEnderCrystal != null && this.breakTimer.passedMs((long)(this.breakDelay.getValue() * 60.0))) {
            if (AutoCrystal.mc.field_1724.method_5858((class_1297)entityEnderCrystal) > MathUtil.square(this.breakRange.getValue())) {
                return;
            }
            if (!AutoCrystal.mc.field_1724.method_6057((class_1297)entityEnderCrystal) && AutoCrystal.mc.field_1724.method_5858((class_1297)entityEnderCrystal) >= MathUtil.square(this.wallRange.getValue())) {
                return;
            }
            attackEntity(entityEnderCrystal.method_5628());
            this.breakTimer.reset();
        }
    }
    
    public static void attackEntity(final int entityId) {
        if (entityId == 0) {
            return;
        }
        AutoCrystal.mc.field_1724.field_3944.method_2883((class_2596)class_2824.method_34206(AutoCrystal.mc.field_1687.method_8469(entityId), AutoCrystal.mc.field_1724.method_5715()));
        AutoCrystal.mc.field_1724.method_6104(class_1268.field_5808);
    }
    
    public static List<class_2338> crystalBlocks(final class_1657 playerEntity, final double placeRange, final boolean prediction, final boolean thirteen) {
        return BlockUtil.getNearbyBlocks(playerEntity, placeRange, prediction).stream().filter(blockPos -> CrystalUtil.canPlaceCrystal(blockPos, false, thirteen)).collect((Collector<? super Object, ?, List<class_2338>>)Collectors.toList());
    }
}
