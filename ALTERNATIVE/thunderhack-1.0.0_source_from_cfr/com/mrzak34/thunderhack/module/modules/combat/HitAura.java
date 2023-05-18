/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.Subscribe
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_1268
 *  net.minecraft.class_1294
 *  net.minecraft.class_1297
 *  net.minecraft.class_1308
 *  net.minecraft.class_1309
 *  net.minecraft.class_1429
 *  net.minecraft.class_1646
 *  net.minecraft.class_1657
 *  net.minecraft.class_1743
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1829
 *  net.minecraft.class_1839
 *  net.minecraft.class_243
 *  net.minecraft.class_2596
 *  net.minecraft.class_2848
 *  net.minecraft.class_2848$class_2849
 *  net.minecraft.class_2868
 *  net.minecraft.class_2886
 *  net.minecraft.class_327
 *  net.minecraft.class_3532
 *  net.minecraft.class_4184
 *  net.minecraft.class_4587
 *  net.minecraft.class_5611
 *  net.minecraft.class_745
 *  net.minecraft.class_746
 *  net.minecraft.class_7833
 */
package com.mrzak34.thunderhack.module.modules.combat;

import com.google.common.eventbus.Subscribe;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mrzak34.thunderhack.event.events.MovementEvent;
import com.mrzak34.thunderhack.event.events.RenderWorldEvent;
import com.mrzak34.thunderhack.manager.RotationManager;
import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.BooleanSetting;
import com.mrzak34.thunderhack.settings.ModeSetting;
import com.mrzak34.thunderhack.settings.NumberSetting;
import com.mrzak34.thunderhack.settings.ParentSetting;
import com.mrzak34.thunderhack.util.FriendUtil;
import com.mrzak34.thunderhack.util.MathUtil;
import com.mrzak34.thunderhack.util.MoveUtil;
import com.mrzak34.thunderhack.util.RaycastUtil;
import com.mrzak34.thunderhack.util.RenderUtil;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.class_1268;
import net.minecraft.class_1294;
import net.minecraft.class_1297;
import net.minecraft.class_1308;
import net.minecraft.class_1309;
import net.minecraft.class_1429;
import net.minecraft.class_1646;
import net.minecraft.class_1657;
import net.minecraft.class_1743;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1829;
import net.minecraft.class_1839;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2848;
import net.minecraft.class_2868;
import net.minecraft.class_2886;
import net.minecraft.class_327;
import net.minecraft.class_3532;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_5611;
import net.minecraft.class_745;
import net.minecraft.class_746;
import net.minecraft.class_7833;

public class HitAura
extends Module {
    public NumberSetting fov = new NumberSetting("fov", 180.0f, 0.0f, 180.0f, true);
    public NumberSetting distance = new NumberSetting("distance", 2.0f, 0.0f, 6.0f, true);
    public NumberSetting rotate = new NumberSetting("rot-dist", 2.0f, 0.0f, 3.0f, true);
    public ModeSetting rotation = new ModeSetting("rotation", "matrix", "matrix", "aac");
    public static NumberSetting cooldown = new NumberSetting("cooldown", 1.0f, 0.0f, 1.0f, true);
    public static NumberSetting crit_cooldown = new NumberSetting("crit-cooldown", 0.85f, 0.0f, 1.0f, true);
    public BooleanSetting players = new BooleanSetting("players", true);
    public BooleanSetting mobs = new BooleanSetting("mobs", false);
    public BooleanSetting villagers = new BooleanSetting("villagers", false);
    public BooleanSetting friends = new BooleanSetting("friends", false);
    public BooleanSetting animals = new BooleanSetting("animals", false);
    ParentSetting targets = new ParentSetting("targets", false, false, this.players, this.mobs, this.villagers, this.friends, this.animals);
    public BooleanSetting troughWalls = new BooleanSetting("trough walls", false);
    BooleanSetting raytrace = new BooleanSetting("raytrace", true);
    public static NumberSetting depth = new NumberSetting("depth", 0.1f, 0.0f, 0.2f, true);
    public BooleanSetting weaponOnly = new BooleanSetting("weapon only", true);
    public BooleanSetting shieldBreaker = new BooleanSetting("shield-breaker", true);
    public BooleanSetting ignoreNaked = new BooleanSetting("ignore naked", false);
    public BooleanSetting ignoreInvisible = new BooleanSetting("ignore invisible", false);
    public ModeSetting criticals = new ModeSetting("criticals", "smart", "off", "on", "smart");
    public BooleanSetting criticals_autojump = new BooleanSetting("crit autojump", false);
    public BooleanSetting hurtSync = new BooleanSetting("hurt-sync", true);
    public BooleanSetting waitTarget = new BooleanSetting("wait target", false);
    public ParentSetting shieldDesync = new ParentSetting("shield-desync", false, true, this.waitTarget);
    public BooleanSetting renderPoints = new BooleanSetting("render points", true);
    public static NumberSetting radius = new NumberSetting("depth", 1.0f, 0.0f, 2.0f, true);
    public ParentSetting circle = new ParentSetting("circle", false, true, radius);
    public static class_1309 target;
    public static boolean hitTick;
    public float prevAdditionYaw;
    public static int minCPS;
    public boolean thisContextRotatedBefore;
    boolean hit = false;
    private static HitAura INSTANCE;

    public HitAura() {
        super("HitAura", 0, false, Category.COMBAT);
        this.addSettings(this.fov, this.distance, this.rotate, this.rotation, cooldown, crit_cooldown, this.targets, this.troughWalls, this.raytrace, depth, this.weaponOnly, this.shieldBreaker, this.ignoreNaked, this.ignoreInvisible, this.criticals, this.criticals_autojump, this.hurtSync, this.shieldDesync, this.renderPoints, this.circle);
        this.setInstance();
    }

    public static HitAura getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new HitAura();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }

    @Subscribe
    public void onRenderWorld(RenderWorldEvent event) {
        if (target != null) {
            if (this.renderPoints.isEnabled()) {
                this.renderHitboxPoints((class_1297)target);
            }
            if (this.circle.isEnabled()) {
                this.renderTargetCircle(target);
            }
        }
    }

    private void renderTargetCircle(class_1309 livingEntity) {
        class_4587 matrices = RenderUtil.matrixFrom(livingEntity.method_19538());
        RenderUtil.drawCircle(matrices.method_23760().method_23761(), 0.0f, 0.0f, (float)radius.getValue(), Color.CYAN);
    }

    private void renderHitboxPoints(class_1297 entity) {
        class_327 tr = HitAura.mc.field_1772;
        class_4184 camera = HitAura.mc.field_1773.method_19418();
        double scale = 1.0;
        for (Hitbox hitbox : Hitbox.values()) {
            for (int pointCode = 0; pointCode < 8; ++pointCode) {
                class_243 pointPos = HitAura.findHitboxCoord(hitbox, pointCode, entity, (float)depth.getValue());
                class_243 bestPoint = HitAura.getBestPoint(entity, this.distance.getValue(), (float)depth.getValue(), this.troughWalls.isEnabled());
                boolean isVisible = HitAura.isPointVisible(entity, pointPos, 4.0, this.troughWalls.isEnabled());
                class_4587 matrices = RenderUtil.matrixFrom(pointPos);
                matrices.method_22907(class_7833.field_40716.rotationDegrees(-camera.method_19330()));
                matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
                RenderSystem.enableBlend();
                RenderSystem.defaultBlendFunc();
                matrices.method_22904(0.0, 0.0, 0.0);
                matrices.method_22905(-0.025f * (float)scale, -0.025f * (float)scale, 1.0f);
                RenderUtil.drawString(matrices, String.valueOf(pointCode), 0, 0, !isVisible ? Color.RED.getRGB() : (pointPos.equals((Object)bestPoint) ? Color.GREEN.getRGB() : -1));
            }
        }
    }

    @Override
    public void onTick() {
        super.onTick();
        if (target == null) {
            return;
        }
        if (this.canAttack() && minCPS == 0 && HitAura.getBestPoint((class_1297)target, this.distance.getValue(), (float)depth.getValue(), this.troughWalls.isEnabled()) != null) {
            boolean blocking;
            if (this.rotation.getMode().equals("matrix") && this.raytrace.isEnabled() && !RaycastUtil.raycastBox(target.method_5829(), this.distance.getValue(), RotationManager.getServerYaw(), RotationManager.getServerPitch(), this.troughWalls.isEnabled())) {
                return;
            }
            if (this.hurtSync.isEnabled() && HitAura.target.field_6235 != 0) {
                return;
            }
            boolean bl = blocking = HitAura.mc.field_1724.method_6058() != null && HitAura.mc.field_1724.method_6030().method_7909().method_7853(HitAura.mc.field_1724.method_6030()) == class_1839.field_8949;
            if (blocking) {
                HitAura.mc.field_1761.method_2897((class_1657)HitAura.mc.field_1724);
            }
            boolean needSwap = false;
            boolean sprinting = HitAura.mc.field_1724.method_5624();
            minCPS = 10;
            hitTick = true;
            if (sprinting) {
                HitAura.mc.field_1724.field_3944.method_2883((class_2596)new class_2848((class_1297)HitAura.mc.field_1724, class_2848.class_2849.field_12985));
            }
            HitAura.mc.field_1761.method_2918((class_1657)HitAura.mc.field_1724, (class_1297)target);
            if (sprinting) {
                HitAura.mc.field_1724.field_3944.method_2883((class_2596)new class_2848((class_1297)HitAura.mc.field_1724, class_2848.class_2849.field_12981));
                HitAura.mc.field_1724.method_5728(true);
            }
            HitAura.mc.field_1724.method_6104(class_1268.field_5808);
            if (HitAura.getAxe() >= 0 && this.shieldBreaker.isEnabled() && target instanceof class_1657 && HitAura.isActiveItemStackBlocking((class_1657)target, 1)) {
                HitAura.mc.field_1724.field_3944.method_2883((class_2596)new class_2868(HitAura.getAxe()));
                this.shieldBreaker((class_1657)target);
                HitAura.mc.field_1724.field_3944.method_2883((class_2596)new class_2868(HitAura.mc.field_1724.method_31548().field_7545));
            }
            if (blocking) {
                HitAura.mc.field_1724.field_3944.method_2883((class_2596)new class_2886(HitAura.mc.field_1724.method_6058(), 0));
            }
            if (needSwap) {
                HitAura.mc.field_1724.field_3944.method_2883((class_2596)new class_2848((class_1297)HitAura.mc.field_1724, class_2848.class_2849.field_12981));
            }
        }
    }

    @Subscribe
    public void onMovmentPre(MovementEvent.Pre event) {
        this.aura(event);
        if (HitAura.mc.field_1724.method_24828() && target != null && this.criticals_autojump.isEnabled()) {
            HitAura.mc.field_1724.method_6043();
        }
    }

    public boolean weaponOnly() {
        if (!this.weaponOnly.isEnabled()) {
            return true;
        }
        return HitAura.mc.field_1724.method_6047().method_7909() instanceof class_1829 || HitAura.mc.field_1724.method_6047().method_7909() instanceof class_1743;
    }

    public void aura(MovementEvent.Pre event) {
        boolean shieldDesyncActive = this.shieldDesync.isEnabled();
        if (this.waitTarget.isEnabled() && target == null) {
            shieldDesyncActive = false;
        }
        if (HitAura.isActiveItemStackBlocking((class_1657)HitAura.mc.field_1724, 4 + new Random().nextInt(4)) && shieldDesyncActive) {
            HitAura.mc.field_1761.method_2897((class_1657)HitAura.mc.field_1724);
        }
        if (minCPS > 0) {
            --minCPS;
        }
        if (target != null && !this.isEntityValid((class_1297)target)) {
            target = null;
        }
        if (target == null) {
            target = this.findTarget();
        }
        if (target == null) {
            return;
        }
        if (!this.weaponOnly()) {
            return;
        }
        this.thisContextRotatedBefore = false;
        this.rotate((class_1297)target, true);
        this.hit = true;
        if (!this.thisContextRotatedBefore) {
            this.rotate((class_1297)target, false);
        }
    }

    public void attack(class_1297 base) {
        if (this.canAttack() && minCPS == 0 && HitAura.getBestPoint(base, this.distance.getValue(), (float)depth.getValue(), this.troughWalls.isEnabled()) != null) {
            boolean blocking;
            if (this.rotation.getMode().equals("matrix") && this.raytrace.isEnabled() && !RaycastUtil.raycastBox(target.method_5829(), this.distance.getValue(), RotationManager.getServerYaw(), RotationManager.getServerPitch(), this.troughWalls.isEnabled())) {
                return;
            }
            this.rotate((class_1297)target, true);
            boolean bl = blocking = HitAura.mc.field_1724.method_6058() != null && HitAura.mc.field_1724.method_6030().method_7909().method_7853(HitAura.mc.field_1724.method_6030()) == class_1839.field_8949;
            if (blocking) {
                HitAura.mc.field_1761.method_2897((class_1657)HitAura.mc.field_1724);
            }
            boolean needSwap = false;
            minCPS = 10;
            hitTick = true;
            HitAura.mc.field_1761.method_2918((class_1657)HitAura.mc.field_1724, base);
            HitAura.mc.field_1724.method_6104(class_1268.field_5808);
            if (HitAura.getAxe() >= 0 && this.shieldBreaker.isEnabled() && base instanceof class_1657 && HitAura.isActiveItemStackBlocking((class_1657)base, 1)) {
                HitAura.mc.field_1724.field_3944.method_2883((class_2596)new class_2868(HitAura.getAxe()));
                this.shieldBreaker((class_1657)base);
                HitAura.mc.field_1724.field_3944.method_2883((class_2596)new class_2868(HitAura.mc.field_1724.method_31548().field_7545));
            }
            if (blocking) {
                HitAura.mc.field_1724.field_3944.method_2883((class_2596)new class_2886(HitAura.mc.field_1724.method_6058(), 0));
            }
            if (needSwap) {
                HitAura.mc.field_1724.field_3944.method_2883((class_2596)new class_2848((class_1297)HitAura.mc.field_1724, class_2848.class_2849.field_12981));
            }
        }
    }

    public void shieldBreaker(class_1657 base) {
        HitAura.mc.field_1761.method_2918((class_1657)HitAura.mc.field_1724, (class_1297)base);
        HitAura.mc.field_1724.method_6104(class_1268.field_5808);
    }

    public boolean isNakedPlayer(class_1309 base) {
        if (!(base instanceof class_745)) {
            return false;
        }
        return base.method_6096() == 0;
    }

    public boolean isInvisible(class_1309 base) {
        if (!(base instanceof class_745)) {
            return false;
        }
        return base.method_5767();
    }

    public void rotate(class_1297 base, boolean attackContext) {
        int yawDeltaAbs;
        this.thisContextRotatedBefore = true;
        class_243 bestHitbox = HitAura.getBestPoint(base, this.rotate.getValue() + this.distance.getValue(), (float)depth.getValue(), this.troughWalls.isEnabled());
        if (bestHitbox == null) {
            bestHitbox = base.method_19538();
        }
        float pitchToHead = 0.0f;
        class_746 client = HitAura.mc.field_1724;
        double x = bestHitbox.field_1352 - client.method_23317();
        double y = base.method_33571().field_1351 - client.method_33571().field_1351;
        double z = bestHitbox.field_1350 - client.method_23321();
        double dst = Math.sqrt(Math.pow(x, 2.0) + Math.pow(z, 2.0));
        pitchToHead = (float)(-Math.toDegrees(Math.atan2(y, dst)));
        float sensitivity = 1.0001f;
        double x2 = bestHitbox.field_1352 - client.method_23317();
        double y2 = bestHitbox.field_1351 - client.method_33571().field_1351;
        double z2 = bestHitbox.field_1350 - client.method_23321();
        double dst2 = Math.sqrt(Math.pow(x2, 2.0) + Math.pow(z2, 2.0));
        float yawToTarget = (float)class_3532.method_15338((double)(Math.toDegrees(Math.atan2(z2, x2)) - 90.0));
        float pitchToTarget = (float)(-Math.toDegrees(Math.atan2(y2, dst2)));
        float yawDelta = class_3532.method_15393((float)(yawToTarget - RotationManager.getServerYaw())) / sensitivity;
        float pitchDelta = (pitchToTarget - RotationManager.getServerPitch()) / sensitivity;
        if (yawDelta > 180.0f) {
            yawDelta -= 180.0f;
        }
        if ((double)(yawDeltaAbs = (int)Math.abs(yawDelta)) < this.fov.getValue()) {
            switch (this.rotation.getMode()) {
                case "matrix": {
                    float pitchDeltaAbs = Math.abs(pitchDelta);
                    float additionYaw = Math.min(Math.max(yawDeltaAbs, 1), 80);
                    float additionPitch = Math.max(attackContext ? pitchDeltaAbs : 1.0f, 2.0f);
                    if (Math.abs(additionYaw - this.prevAdditionYaw) <= 3.0f) {
                        additionYaw = this.prevAdditionYaw + 3.1f;
                    }
                    float newYaw = RotationManager.getServerYaw() + (yawDelta > 0.0f ? additionYaw : -additionYaw) * sensitivity;
                    float newPitch = class_3532.method_15363((float)(RotationManager.getServerPitch() + (pitchDelta > 0.0f ? additionPitch : -additionPitch) * sensitivity), (float)-90.0f, (float)90.0f);
                    HitAura.mc.field_1724.method_36456(newYaw);
                    HitAura.mc.field_1724.method_36457(newPitch);
                    this.prevAdditionYaw = additionYaw;
                    break;
                }
                case "aac": {
                    if (!attackContext) break;
                    int pitchDeltaAbs = (int)Math.abs(pitchDelta);
                    int additionYaw = yawDeltaAbs;
                    int additionPitch = pitchDeltaAbs;
                    float newYaw = RotationManager.getServerYaw() + (float)(yawDelta > 0.0f ? additionYaw : -additionYaw) * sensitivity;
                    float newPitch = class_3532.method_15363((float)(RotationManager.getServerPitch() + (float)(pitchDelta > 0.0f ? additionPitch : -additionPitch) * sensitivity), (float)-90.0f, (float)90.0f);
                    HitAura.mc.field_1724.method_36456(newYaw);
                    HitAura.mc.field_1724.method_36457(newPitch);
                }
            }
        }
    }

    public static float getAttackCooldown() {
        if (HitAura.mc.field_1724.method_24828()) {
            return (float)cooldown.getValue();
        }
        return (float)crit_cooldown.getValue();
    }

    public boolean canAttack() {
        boolean reasonForCancelCritical;
        boolean bl = reasonForCancelCritical = HitAura.mc.field_1724.method_6059(class_1294.field_5919) || HitAura.mc.field_1724.method_21754() || MoveUtil.isInLiquid();
        if ((double)HitAura.mc.field_1724.method_7261(1.5f) < cooldown.getValue()) {
            return false;
        }
        if (!reasonForCancelCritical && (this.criticals.getMode().equals("on") || this.criticals.getMode().equals("smart") && HitAura.mc.field_1690.field_1903.method_1434())) {
            int c;
            int r = (int)HitAura.mc.field_1724.method_23318();
            if (r != (c = (int)Math.ceil(HitAura.mc.field_1724.method_23318())) && HitAura.mc.field_1724.method_24828() && MoveUtil.isBlockAboveHead()) {
                return true;
            }
            return !HitAura.mc.field_1724.method_24828() && HitAura.mc.field_1724.field_6017 > 0.0f;
        }
        return true;
    }

    public static int getAxe() {
        for (int i = 0; i < 9; ++i) {
            class_1799 s = HitAura.mc.field_1724.method_31548().method_5438(i);
            if (!(s.method_7909() instanceof class_1743)) continue;
            return i;
        }
        return -1;
    }

    public class_1309 findTarget() {
        ArrayList<class_1309> targets = new ArrayList<class_1309>();
        for (class_1297 entity : HitAura.mc.field_1687.method_18112()) {
            if (!(entity instanceof class_1309) || !this.isEntityValid((class_1297)((class_1309)entity))) continue;
            targets.add((class_1309)entity);
        }
        targets.sort((e1, e2) -> {
            int dst1 = (int)(HitAura.mc.field_1724.method_5739((class_1297)e1) * 1000.0f);
            int dst2 = (int)(HitAura.mc.field_1724.method_5739((class_1297)e2) * 1000.0f);
            return dst1 - dst2;
        });
        return targets.isEmpty() ? null : (class_1309)targets.get(0);
    }

    public boolean isEntityValid(class_1297 entity) {
        if (entity.equals((Object)HitAura.mc.field_1724)) {
            return false;
        }
        if (entity instanceof class_1309) {
            class_1309 livingEntity = (class_1309)entity;
            if (livingEntity.method_29504()) {
                return false;
            }
            if (this.ignoreNaked.isEnabled() && this.isNakedPlayer(livingEntity)) {
                return false;
            }
            if (this.ignoreInvisible.isEnabled() && this.isInvisible(livingEntity)) {
                return false;
            }
            if (livingEntity.method_6032() <= 0.0f) {
                return false;
            }
            if (!this.targetsCheck(livingEntity)) {
                return false;
            }
        }
        if (entity.method_31481()) {
            return false;
        }
        if (HitAura.getBestPoint(entity, this.rotate.getValue() + this.distance.getValue(), (float)depth.getValue(), this.troughWalls.isEnabled()) == null) {
            return false;
        }
        if (entity.method_5858((class_1297)HitAura.mc.field_1724) > Math.pow(this.distance.getValue() + this.rotate.getValue(), 2.0)) {
            return false;
        }
        return HitAura.canSee(entity, this.distance.getValue() + this.rotate.getValue(), (float)depth.getValue(), this.troughWalls.isEnabled());
    }

    public static boolean canSee(class_1297 target, double dist, float depth, boolean troughWalls) {
        for (Hitbox hitbox : Hitbox.values()) {
            for (int pointCode = 0; pointCode < 8; ++pointCode) {
                class_243 pointPos = HitAura.findHitboxCoord(hitbox, pointCode, target, depth);
                if (!HitAura.isPointVisible(target, pointPos, dist, troughWalls)) continue;
                return true;
            }
        }
        return false;
    }

    public static class_243 getBestPoint(class_1297 target, double rotateDistance, float depth, boolean troughWalls) {
        ArrayList<class_243> points = new ArrayList<class_243>();
        if (HitAura.mc.field_1724.method_5858(target) > Math.pow(rotateDistance, 2.0)) {
            return null;
        }
        for (Hitbox hitbox : Hitbox.values()) {
            for (int pointCode = 0; pointCode < 8; ++pointCode) {
                class_243 pointPos = HitAura.findHitboxCoord(hitbox, pointCode, target, depth);
                points.add(pointPos);
            }
        }
        points.removeIf(point -> !HitAura.isPointVisible(target, point, rotateDistance, troughWalls));
        points.removeIf(point -> HitAura.mc.field_1724.method_33571().method_1025(point) > Math.pow(rotateDistance, 2.0));
        if (points.isEmpty()) {
            return null;
        }
        points.sort((d1, d2) -> {
            class_5611 r1 = HitAura.getDeltaForCoord(new class_5611(RotationManager.getServerYaw(), RotationManager.getServerPitch()), d1);
            class_5611 r2 = HitAura.getDeltaForCoord(new class_5611(RotationManager.getServerYaw(), RotationManager.getServerPitch()), d2);
            float y1 = Math.abs(r1.method_32119());
            float y2 = Math.abs(r2.method_32119());
            return (int)((y1 - y2) * 1000.0f);
        });
        return (class_243)points.get(0);
    }

    public boolean targetsCheck(class_1309 entity) {
        if (entity.method_29504()) {
            return false;
        }
        if (this.players.isEnabled() && entity instanceof class_1657) {
            if (!this.friends.isEnabled()) {
                return !FriendUtil.isFriend(entity.method_5477().getString());
            }
            return true;
        }
        if (this.mobs.isEnabled() && entity instanceof class_1308) {
            return true;
        }
        if (this.animals.isEnabled() && entity instanceof class_1429) {
            return true;
        }
        return this.villagers.isEnabled() && entity instanceof class_1646;
    }

    public static class_5611 getDeltaForCoord(class_5611 rot, class_243 point) {
        double x = point.field_1352 - HitAura.mc.field_1724.method_23317();
        double y = point.field_1351 - HitAura.mc.field_1724.method_33571().method_10214();
        double z = point.field_1350 - HitAura.mc.field_1724.method_23321();
        double dst = Math.sqrt(Math.pow(x, 2.0) + Math.pow(z, 2.0));
        float yawToTarget = (float)class_3532.method_15338((double)(Math.toDegrees(Math.atan2(z, x)) - 90.0));
        float pitchToTarget = (float)(-Math.toDegrees(Math.atan2(y, dst)));
        float yawDelta = class_3532.method_15393((float)(yawToTarget - rot.method_32118()));
        float pitchDelta = pitchToTarget - rot.method_32119();
        return new class_5611(yawDelta, pitchDelta);
    }

    public static boolean isPointVisible(class_1297 entity, class_243 pointPos, double dist, boolean troughWalls) {
        float[] angle = MathUtil.calcAngle(HitAura.mc.field_1724.method_33571(), pointPos);
        return RaycastUtil.raycastBox(entity.method_5829(), dist, angle[0], angle[1], troughWalls);
    }

    public static class_243 findHitboxCoord(Hitbox box, int pointCode, class_1297 target, float depth) {
        double xCoord = 0.0;
        double yCoord = 0.0;
        double zCoord = 0.0;
        float hitboxWidth = target.method_17681() / 2.0f - depth - 0.001f;
        float hitboxHeight = target.method_17682();
        switch (box) {
            case HEAD: {
                yCoord = (double)hitboxHeight - 0.2;
                break;
            }
            case CHEST: {
                yCoord = hitboxHeight / 2.0f;
                break;
            }
            case LEGS: {
                yCoord = 0.2;
            }
        }
        switch (pointCode) {
            case 0: {
                xCoord = hitboxWidth;
                zCoord = -hitboxWidth;
                break;
            }
            case 1: {
                xCoord = hitboxWidth;
                zCoord = 0.0;
                break;
            }
            case 2: {
                xCoord = hitboxWidth;
                zCoord = hitboxWidth;
                break;
            }
            case 3: {
                xCoord = 0.0;
                zCoord = hitboxWidth;
                break;
            }
            case 4: {
                xCoord = -hitboxWidth;
                zCoord = hitboxWidth;
                break;
            }
            case 5: {
                xCoord = -hitboxWidth;
                zCoord = 0.0;
                break;
            }
            case 6: {
                xCoord = -hitboxWidth;
                zCoord = -hitboxWidth;
                break;
            }
            case 7: {
                xCoord = 0.0;
                zCoord = -hitboxWidth;
            }
        }
        return target.method_19538().method_1031(xCoord, yCoord, zCoord);
    }

    public static boolean isActiveItemStackBlocking(class_1657 other, int time) {
        if (!other.method_6030().method_7960()) {
            class_1792 item = other.method_6030().method_7909();
            if (item.method_7853(other.method_6030()) != class_1839.field_8949) {
                return false;
            }
            return item.method_7881(other.method_6030()) - other.method_6014() >= time;
        }
        return false;
    }

    static {
        INSTANCE = new HitAura();
    }

    public static final class Hitbox
    extends Enum<Hitbox> {
        public static final /* enum */ Hitbox HEAD = new Hitbox();
        public static final /* enum */ Hitbox CHEST = new Hitbox();
        public static final /* enum */ Hitbox LEGS = new Hitbox();
        private static final /* synthetic */ Hitbox[] $VALUES;

        public static Hitbox[] values() {
            return (Hitbox[])$VALUES.clone();
        }

        public static Hitbox valueOf(String name) {
            return Enum.valueOf(Hitbox.class, name);
        }

        private static /* synthetic */ Hitbox[] $values() {
            return new Hitbox[]{HEAD, CHEST, LEGS};
        }

        static {
            $VALUES = Hitbox.$values();
        }
    }
}

