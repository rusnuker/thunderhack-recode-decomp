//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.module.modules.combat;

import com.mrzak34.thunderhack.module.*;
import com.mrzak34.thunderhack.settings.*;
import com.google.common.eventbus.*;
import java.awt.*;
import com.mojang.blaze3d.systems.*;
import com.mrzak34.thunderhack.manager.*;
import com.mrzak34.thunderhack.event.events.*;
import java.util.*;
import com.mrzak34.thunderhack.util.*;
import net.minecraft.*;

public class HitAura extends Module
{
    public NumberSetting fov;
    public NumberSetting distance;
    public NumberSetting rotate;
    public ModeSetting rotation;
    public static NumberSetting cooldown;
    public static NumberSetting crit_cooldown;
    public BooleanSetting players;
    public BooleanSetting mobs;
    public BooleanSetting villagers;
    public BooleanSetting friends;
    public BooleanSetting animals;
    ParentSetting targets;
    public BooleanSetting troughWalls;
    BooleanSetting raytrace;
    public static NumberSetting depth;
    public BooleanSetting weaponOnly;
    public BooleanSetting shieldBreaker;
    public BooleanSetting ignoreNaked;
    public BooleanSetting ignoreInvisible;
    public ModeSetting criticals;
    public BooleanSetting criticals_autojump;
    public BooleanSetting hurtSync;
    public BooleanSetting waitTarget;
    public ParentSetting shieldDesync;
    public BooleanSetting renderPoints;
    public static NumberSetting radius;
    public ParentSetting circle;
    public static class_1309 target;
    public static boolean hitTick;
    public float prevAdditionYaw;
    public static int minCPS;
    public boolean thisContextRotatedBefore;
    boolean hit;
    private static HitAura INSTANCE;
    
    public HitAura() {
        super("HitAura", 0, false, Category.COMBAT);
        this.fov = new NumberSetting("fov", 180.0f, 0.0f, 180.0f, true);
        this.distance = new NumberSetting("distance", 2.0f, 0.0f, 6.0f, true);
        this.rotate = new NumberSetting("rot-dist", 2.0f, 0.0f, 3.0f, true);
        this.rotation = new ModeSetting("rotation", "matrix", new String[] { "matrix", "aac" });
        this.players = new BooleanSetting("players", true);
        this.mobs = new BooleanSetting("mobs", false);
        this.villagers = new BooleanSetting("villagers", false);
        this.friends = new BooleanSetting("friends", false);
        this.animals = new BooleanSetting("animals", false);
        this.targets = new ParentSetting("targets", false, false, new Setting[] { this.players, this.mobs, this.villagers, this.friends, this.animals });
        this.troughWalls = new BooleanSetting("trough walls", false);
        this.raytrace = new BooleanSetting("raytrace", true);
        this.weaponOnly = new BooleanSetting("weapon only", true);
        this.shieldBreaker = new BooleanSetting("shield-breaker", true);
        this.ignoreNaked = new BooleanSetting("ignore naked", false);
        this.ignoreInvisible = new BooleanSetting("ignore invisible", false);
        this.criticals = new ModeSetting("criticals", "smart", new String[] { "off", "on", "smart" });
        this.criticals_autojump = new BooleanSetting("crit autojump", false);
        this.hurtSync = new BooleanSetting("hurt-sync", true);
        this.waitTarget = new BooleanSetting("wait target", false);
        this.shieldDesync = new ParentSetting("shield-desync", false, true, new Setting[] { this.waitTarget });
        this.renderPoints = new BooleanSetting("render points", true);
        this.circle = new ParentSetting("circle", false, true, new Setting[] { HitAura.radius });
        this.hit = false;
        this.addSettings(new Setting[] { this.fov, this.distance, this.rotate, this.rotation, HitAura.cooldown, HitAura.crit_cooldown, this.targets, this.troughWalls, this.raytrace, HitAura.depth, this.weaponOnly, this.shieldBreaker, this.ignoreNaked, this.ignoreInvisible, this.criticals, this.criticals_autojump, this.hurtSync, this.shieldDesync, this.renderPoints, this.circle });
        this.setInstance();
    }
    
    public static HitAura getInstance() {
        if (HitAura.INSTANCE == null) {
            HitAura.INSTANCE = new HitAura();
        }
        return HitAura.INSTANCE;
    }
    
    private void setInstance() {
        HitAura.INSTANCE = this;
    }
    
    @Subscribe
    public void onRenderWorld(final RenderWorldEvent event) {
        if (HitAura.target != null) {
            if (this.renderPoints.isEnabled()) {
                this.renderHitboxPoints((class_1297)HitAura.target);
            }
            if (this.circle.isEnabled()) {
                this.renderTargetCircle(HitAura.target);
            }
        }
    }
    
    private void renderTargetCircle(final class_1309 livingEntity) {
        final class_4587 matrices = RenderUtil.matrixFrom(livingEntity.method_19538());
        RenderUtil.drawCircle(matrices.method_23760().method_23761(), 0.0f, 0.0f, (float)HitAura.radius.getValue(), Color.CYAN);
    }
    
    private void renderHitboxPoints(final class_1297 entity) {
        final class_327 tr = HitAura.mc.field_1772;
        final class_4184 camera = HitAura.mc.field_1773.method_19418();
        final double scale = 1.0;
        for (final Hitbox hitbox : Hitbox.values()) {
            for (int pointCode = 0; pointCode < 8; ++pointCode) {
                final class_243 pointPos = findHitboxCoord(hitbox, pointCode, entity, (float)HitAura.depth.getValue());
                final class_243 bestPoint = getBestPoint(entity, this.distance.getValue(), (float)HitAura.depth.getValue(), this.troughWalls.isEnabled());
                final boolean isVisible = isPointVisible(entity, pointPos, 4.0, this.troughWalls.isEnabled());
                final class_4587 matrices = RenderUtil.matrixFrom(pointPos);
                matrices.method_22907(class_7833.field_40716.rotationDegrees(-camera.method_19330()));
                matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
                RenderSystem.enableBlend();
                RenderSystem.defaultBlendFunc();
                matrices.method_22904(0.0, 0.0, 0.0);
                matrices.method_22905(-0.025f * (float)scale, -0.025f * (float)scale, 1.0f);
                RenderUtil.drawString(matrices, String.valueOf(pointCode), 0, 0, isVisible ? (pointPos.equals((Object)bestPoint) ? Color.GREEN.getRGB() : -1) : Color.RED.getRGB());
            }
        }
    }
    
    public void onTick() {
        super.onTick();
        if (HitAura.target == null) {
            return;
        }
        if (this.canAttack() && HitAura.minCPS == 0 && getBestPoint((class_1297)HitAura.target, this.distance.getValue(), (float)HitAura.depth.getValue(), this.troughWalls.isEnabled()) != null) {
            if (this.rotation.getMode().equals("matrix") && this.raytrace.isEnabled() && !RaycastUtil.raycastBox(HitAura.target.method_5829(), this.distance.getValue(), RotationManager.getServerYaw(), RotationManager.getServerPitch(), this.troughWalls.isEnabled())) {
                return;
            }
            if (this.hurtSync.isEnabled() && HitAura.target.field_6235 != 0) {
                return;
            }
            final boolean blocking = HitAura.mc.field_1724.method_6058() != null && HitAura.mc.field_1724.method_6030().method_7909().method_7853(HitAura.mc.field_1724.method_6030()) == class_1839.field_8949;
            if (blocking) {
                HitAura.mc.field_1761.method_2897((class_1657)HitAura.mc.field_1724);
            }
            final boolean needSwap = false;
            final boolean sprinting = HitAura.mc.field_1724.method_5624();
            HitAura.minCPS = 10;
            HitAura.hitTick = true;
            if (sprinting) {
                HitAura.mc.field_1724.field_3944.method_2883((class_2596)new class_2848((class_1297)HitAura.mc.field_1724, class_2848.class_2849.field_12985));
            }
            HitAura.mc.field_1761.method_2918((class_1657)HitAura.mc.field_1724, (class_1297)HitAura.target);
            if (sprinting) {
                HitAura.mc.field_1724.field_3944.method_2883((class_2596)new class_2848((class_1297)HitAura.mc.field_1724, class_2848.class_2849.field_12981));
                HitAura.mc.field_1724.method_5728(true);
            }
            HitAura.mc.field_1724.method_6104(class_1268.field_5808);
            if (getAxe() >= 0 && this.shieldBreaker.isEnabled() && HitAura.target instanceof class_1657 && isActiveItemStackBlocking((class_1657)HitAura.target, 1)) {
                HitAura.mc.field_1724.field_3944.method_2883((class_2596)new class_2868(getAxe()));
                this.shieldBreaker((class_1657)HitAura.target);
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
    public void onMovmentPre(final MovementEvent.Pre event) {
        this.aura(event);
        if (HitAura.mc.field_1724.method_24828() && HitAura.target != null && this.criticals_autojump.isEnabled()) {
            HitAura.mc.field_1724.method_6043();
        }
    }
    
    public boolean weaponOnly() {
        return !this.weaponOnly.isEnabled() || HitAura.mc.field_1724.method_6047().method_7909() instanceof class_1829 || HitAura.mc.field_1724.method_6047().method_7909() instanceof class_1743;
    }
    
    public void aura(final MovementEvent.Pre event) {
        boolean shieldDesyncActive = this.shieldDesync.isEnabled();
        if (this.waitTarget.isEnabled() && HitAura.target == null) {
            shieldDesyncActive = false;
        }
        if (isActiveItemStackBlocking((class_1657)HitAura.mc.field_1724, 4 + new Random().nextInt(4)) && shieldDesyncActive) {
            HitAura.mc.field_1761.method_2897((class_1657)HitAura.mc.field_1724);
        }
        if (HitAura.minCPS > 0) {
            --HitAura.minCPS;
        }
        if (HitAura.target != null && !this.isEntityValid((class_1297)HitAura.target)) {
            HitAura.target = null;
        }
        if (HitAura.target == null) {
            HitAura.target = this.findTarget();
        }
        if (HitAura.target == null) {
            return;
        }
        if (!this.weaponOnly()) {
            return;
        }
        this.thisContextRotatedBefore = false;
        this.rotate((class_1297)HitAura.target, true);
        this.hit = true;
        if (!this.thisContextRotatedBefore) {
            this.rotate((class_1297)HitAura.target, false);
        }
    }
    
    public void attack(final class_1297 base) {
        if (this.canAttack() && HitAura.minCPS == 0 && getBestPoint(base, this.distance.getValue(), (float)HitAura.depth.getValue(), this.troughWalls.isEnabled()) != null) {
            if (this.rotation.getMode().equals("matrix") && this.raytrace.isEnabled() && !RaycastUtil.raycastBox(HitAura.target.method_5829(), this.distance.getValue(), RotationManager.getServerYaw(), RotationManager.getServerPitch(), this.troughWalls.isEnabled())) {
                return;
            }
            this.rotate((class_1297)HitAura.target, true);
            final boolean blocking = HitAura.mc.field_1724.method_6058() != null && HitAura.mc.field_1724.method_6030().method_7909().method_7853(HitAura.mc.field_1724.method_6030()) == class_1839.field_8949;
            if (blocking) {
                HitAura.mc.field_1761.method_2897((class_1657)HitAura.mc.field_1724);
            }
            final boolean needSwap = false;
            HitAura.minCPS = 10;
            HitAura.hitTick = true;
            HitAura.mc.field_1761.method_2918((class_1657)HitAura.mc.field_1724, base);
            HitAura.mc.field_1724.method_6104(class_1268.field_5808);
            if (getAxe() >= 0 && this.shieldBreaker.isEnabled() && base instanceof class_1657 && isActiveItemStackBlocking((class_1657)base, 1)) {
                HitAura.mc.field_1724.field_3944.method_2883((class_2596)new class_2868(getAxe()));
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
    
    public void shieldBreaker(final class_1657 base) {
        HitAura.mc.field_1761.method_2918((class_1657)HitAura.mc.field_1724, (class_1297)base);
        HitAura.mc.field_1724.method_6104(class_1268.field_5808);
    }
    
    public boolean isNakedPlayer(final class_1309 base) {
        return base instanceof class_745 && base.method_6096() == 0;
    }
    
    public boolean isInvisible(final class_1309 base) {
        return base instanceof class_745 && base.method_5767();
    }
    
    public void rotate(final class_1297 base, final boolean attackContext) {
        this.thisContextRotatedBefore = true;
        class_243 bestHitbox = getBestPoint(base, this.rotate.getValue() + this.distance.getValue(), (float)HitAura.depth.getValue(), this.troughWalls.isEnabled());
        if (bestHitbox == null) {
            bestHitbox = base.method_19538();
        }
        float pitchToHead = 0.0f;
        final class_1657 client = (class_1657)HitAura.mc.field_1724;
        final double x = bestHitbox.field_1352 - client.method_23317();
        final double y = base.method_33571().field_1351 - client.method_33571().field_1351;
        final double z = bestHitbox.field_1350 - client.method_23321();
        final double dst = Math.sqrt(Math.pow(x, 2.0) + Math.pow(z, 2.0));
        pitchToHead = (float)(-Math.toDegrees(Math.atan2(y, dst)));
        final float sensitivity = 1.0001f;
        final double x2 = bestHitbox.field_1352 - client.method_23317();
        final double y2 = bestHitbox.field_1351 - client.method_33571().field_1351;
        final double z2 = bestHitbox.field_1350 - client.method_23321();
        final double dst2 = Math.sqrt(Math.pow(x2, 2.0) + Math.pow(z2, 2.0));
        final float yawToTarget = (float)class_3532.method_15338(Math.toDegrees(Math.atan2(z2, x2)) - 90.0);
        final float pitchToTarget = (float)(-Math.toDegrees(Math.atan2(y2, dst2)));
        float yawDelta = class_3532.method_15393(yawToTarget - RotationManager.getServerYaw()) / sensitivity;
        final float pitchDelta = (pitchToTarget - RotationManager.getServerPitch()) / sensitivity;
        if (yawDelta > 180.0f) {
            yawDelta -= 180.0f;
        }
        final int yawDeltaAbs = (int)Math.abs(yawDelta);
        if (yawDeltaAbs < this.fov.getValue()) {
            final String mode = this.rotation.getMode();
            switch (mode) {
                case "matrix": {
                    final float pitchDeltaAbs = Math.abs(pitchDelta);
                    float additionYaw = (float)Math.min(Math.max(yawDeltaAbs, 1), 80);
                    final float additionPitch = Math.max(attackContext ? pitchDeltaAbs : 1.0f, 2.0f);
                    if (Math.abs(additionYaw - this.prevAdditionYaw) <= 3.0f) {
                        additionYaw = this.prevAdditionYaw + 3.1f;
                    }
                    final float newYaw = RotationManager.getServerYaw() + ((yawDelta > 0.0f) ? additionYaw : (-additionYaw)) * sensitivity;
                    final float newPitch = class_3532.method_15363(RotationManager.getServerPitch() + ((pitchDelta > 0.0f) ? additionPitch : (-additionPitch)) * sensitivity, -90.0f, 90.0f);
                    HitAura.mc.field_1724.method_36456(newYaw);
                    HitAura.mc.field_1724.method_36457(newPitch);
                    this.prevAdditionYaw = additionYaw;
                    break;
                }
                case "aac": {
                    if (attackContext) {
                        final int pitchDeltaAbs2 = (int)Math.abs(pitchDelta);
                        final int additionYaw2 = yawDeltaAbs;
                        final int additionPitch2 = pitchDeltaAbs2;
                        final float newYaw = RotationManager.getServerYaw() + ((yawDelta > 0.0f) ? additionYaw2 : (-additionYaw2)) * sensitivity;
                        final float newPitch = class_3532.method_15363(RotationManager.getServerPitch() + ((pitchDelta > 0.0f) ? additionPitch2 : (-additionPitch2)) * sensitivity, -90.0f, 90.0f);
                        HitAura.mc.field_1724.method_36456(newYaw);
                        HitAura.mc.field_1724.method_36457(newPitch);
                        break;
                    }
                    break;
                }
            }
        }
    }
    
    public static float getAttackCooldown() {
        if (HitAura.mc.field_1724.method_24828()) {
            return (float)HitAura.cooldown.getValue();
        }
        return (float)HitAura.crit_cooldown.getValue();
    }
    
    public boolean canAttack() {
        final boolean reasonForCancelCritical = HitAura.mc.field_1724.method_6059(class_1294.field_5919) || HitAura.mc.field_1724.method_21754() || MoveUtil.isInLiquid();
        if (HitAura.mc.field_1724.method_7261(1.5f) < HitAura.cooldown.getValue()) {
            return false;
        }
        if (!reasonForCancelCritical && (this.criticals.getMode().equals("on") || (this.criticals.getMode().equals("smart") && HitAura.mc.field_1690.field_1903.method_1434()))) {
            final int r = (int)HitAura.mc.field_1724.method_23318();
            final int c = (int)Math.ceil(HitAura.mc.field_1724.method_23318());
            return (r != c && HitAura.mc.field_1724.method_24828() && MoveUtil.isBlockAboveHead()) || (!HitAura.mc.field_1724.method_24828() && HitAura.mc.field_1724.field_6017 > 0.0f);
        }
        return true;
    }
    
    public static int getAxe() {
        for (int i = 0; i < 9; ++i) {
            final class_1799 s = HitAura.mc.field_1724.method_31548().method_5438(i);
            if (s.method_7909() instanceof class_1743) {
                return i;
            }
        }
        return -1;
    }
    
    public class_1309 findTarget() {
        final List<class_1309> targets = new ArrayList<class_1309>();
        for (final class_1297 entity : HitAura.mc.field_1687.method_18112()) {
            if (entity instanceof class_1309 && this.isEntityValid(entity)) {
                targets.add((class_1309)entity);
            }
        }
        final int dst1;
        final int dst2;
        targets.sort((e1, e2) -> {
            dst1 = (int)(HitAura.mc.field_1724.method_5739(e1) * 1000.0f);
            dst2 = (int)(HitAura.mc.field_1724.method_5739(e2) * 1000.0f);
            return dst1 - dst2;
        });
        return targets.isEmpty() ? null : targets.get(0);
    }
    
    public boolean isEntityValid(final class_1297 entity) {
        if (entity.equals((Object)HitAura.mc.field_1724)) {
            return false;
        }
        if (entity instanceof class_1309) {
            final class_1309 livingEntity = (class_1309)entity;
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
        return !entity.method_31481() && getBestPoint(entity, this.rotate.getValue() + this.distance.getValue(), (float)HitAura.depth.getValue(), this.troughWalls.isEnabled()) != null && entity.method_5858((class_1297)HitAura.mc.field_1724) <= Math.pow(this.distance.getValue() + this.rotate.getValue(), 2.0) && canSee(entity, this.distance.getValue() + this.rotate.getValue(), (float)HitAura.depth.getValue(), this.troughWalls.isEnabled());
    }
    
    public static boolean canSee(final class_1297 target, final double dist, final float depth, final boolean troughWalls) {
        for (final Hitbox hitbox : Hitbox.values()) {
            for (int pointCode = 0; pointCode < 8; ++pointCode) {
                final class_243 pointPos = findHitboxCoord(hitbox, pointCode, target, depth);
                if (isPointVisible(target, pointPos, dist, troughWalls)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static class_243 getBestPoint(final class_1297 target, final double rotateDistance, final float depth, final boolean troughWalls) {
        final ArrayList<class_243> points = new ArrayList<class_243>();
        if (HitAura.mc.field_1724.method_5858(target) > Math.pow(rotateDistance, 2.0)) {
            return null;
        }
        for (final Hitbox hitbox : Hitbox.values()) {
            for (int pointCode = 0; pointCode < 8; ++pointCode) {
                final class_243 pointPos = findHitboxCoord(hitbox, pointCode, target, depth);
                points.add(pointPos);
            }
        }
        points.removeIf(point -> !isPointVisible(target, point, rotateDistance, troughWalls));
        points.removeIf(point -> HitAura.mc.field_1724.method_33571().method_1025(point) > Math.pow(rotateDistance, 2.0));
        if (points.isEmpty()) {
            return null;
        }
        final class_5611 r1;
        final class_5611 r2;
        final float y1;
        final float y2;
        points.sort((d1, d2) -> {
            r1 = getDeltaForCoord(new class_5611(RotationManager.getServerYaw(), RotationManager.getServerPitch()), d1);
            r2 = getDeltaForCoord(new class_5611(RotationManager.getServerYaw(), RotationManager.getServerPitch()), d2);
            y1 = Math.abs(r1.method_32119());
            y2 = Math.abs(r2.method_32119());
            return (int)((y1 - y2) * 1000.0f);
        });
        return points.get(0);
    }
    
    public boolean targetsCheck(final class_1309 entity) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokevirtual   net/minecraft/class_1309.method_29504:()Z
        //     4: ifeq            9
        //     7: iconst_0       
        //     8: ireturn        
        //     9: aload_0         /* this */
        //    10: getfield        com/mrzak34/thunderhack/module/modules/combat/HitAura.players:Lcom/mrzak34/thunderhack/settings/BooleanSetting;
        //    13: invokevirtual   com/mrzak34/thunderhack/settings/BooleanSetting.isEnabled:()Z
        //    16: ifeq            59
        //    19: aload_1         /* entity */
        //    20: instanceof      Lnet/minecraft/class_1657;
        //    23: ifeq            59
        //    26: aload_0         /* this */
        //    27: getfield        com/mrzak34/thunderhack/module/modules/combat/HitAura.friends:Lcom/mrzak34/thunderhack/settings/BooleanSetting;
        //    30: invokevirtual   com/mrzak34/thunderhack/settings/BooleanSetting.isEnabled:()Z
        //    33: ifne            57
        //    36: aload_1         /* entity */
        //    37: invokevirtual   net/minecraft/class_1309.method_5477:()Lnet/minecraft/class_2561;
        //    40: invokeinterface net/minecraft/class_2561.getString:()Ljava/lang/String;
        //    45: invokestatic    invokestatic   !!! ERROR
        //    48: ifne            55
        //    51: iconst_1       
        //    52: goto            56
        //    55: iconst_0       
        //    56: ireturn        
        //    57: iconst_1       
        //    58: ireturn        
        //    59: aload_0         /* this */
        //    60: getfield        com/mrzak34/thunderhack/module/modules/combat/HitAura.mobs:Lcom/mrzak34/thunderhack/settings/BooleanSetting;
        //    63: invokevirtual   com/mrzak34/thunderhack/settings/BooleanSetting.isEnabled:()Z
        //    66: ifeq            78
        //    69: aload_1         /* entity */
        //    70: instanceof      Lnet/minecraft/class_1308;
        //    73: ifeq            78
        //    76: iconst_1       
        //    77: ireturn        
        //    78: aload_0         /* this */
        //    79: getfield        com/mrzak34/thunderhack/module/modules/combat/HitAura.animals:Lcom/mrzak34/thunderhack/settings/BooleanSetting;
        //    82: invokevirtual   com/mrzak34/thunderhack/settings/BooleanSetting.isEnabled:()Z
        //    85: ifeq            97
        //    88: aload_1         /* entity */
        //    89: instanceof      Lnet/minecraft/class_1429;
        //    92: ifeq            97
        //    95: iconst_1       
        //    96: ireturn        
        //    97: aload_0         /* this */
        //    98: getfield        com/mrzak34/thunderhack/module/modules/combat/HitAura.villagers:Lcom/mrzak34/thunderhack/settings/BooleanSetting;
        //   101: invokevirtual   com/mrzak34/thunderhack/settings/BooleanSetting.isEnabled:()Z
        //   104: ifeq            116
        //   107: aload_1         /* entity */
        //   108: instanceof      Lnet/minecraft/class_1646;
        //   111: ifeq            116
        //   114: iconst_1       
        //   115: ireturn        
        //   116: iconst_0       
        //   117: ireturn        
        //    MethodParameters:
        //  Name    Flags  
        //  ------  -----
        //  entity  
        //    StackMapTable: 00 08 09 2D 40 01 00 01 12 12 12
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Invalid BootstrapMethods attribute entry: 2 additional arguments required for method java/lang/invoke/StringConcatFactory.makeConcatWithConstants, but only 1 specified.
        //     at com.strobel.assembler.ir.Error.invalidBootstrapMethodEntry(Error.java:244)
        //     at com.strobel.assembler.ir.MetadataReader.readAttributeCore(MetadataReader.java:280)
        //     at com.strobel.assembler.metadata.ClassFileReader.readAttributeCore(ClassFileReader.java:261)
        //     at com.strobel.assembler.ir.MetadataReader.inflateAttributes(MetadataReader.java:439)
        //     at com.strobel.assembler.metadata.ClassFileReader.visitAttributes(ClassFileReader.java:1134)
        //     at com.strobel.assembler.metadata.ClassFileReader.readClass(ClassFileReader.java:439)
        //     at com.strobel.assembler.metadata.ClassFileReader.readClass(ClassFileReader.java:377)
        //     at com.strobel.assembler.metadata.MetadataSystem.resolveType(MetadataSystem.java:129)
        //     at com.strobel.assembler.metadata.MetadataSystem.resolveCore(MetadataSystem.java:81)
        //     at com.strobel.assembler.metadata.MetadataResolver.resolve(MetadataResolver.java:104)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.resolve(CoreMetadataFactory.java:616)
        //     at com.strobel.assembler.metadata.MetadataResolver.resolve(MetadataResolver.java:128)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.resolve(CoreMetadataFactory.java:626)
        //     at com.strobel.assembler.metadata.MethodReference.resolve(MethodReference.java:177)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2438)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:672)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:655)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferBinaryExpression(TypeAnalysis.java:2104)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1531)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:778)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1551)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:672)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:655)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:365)
        //     at com.strobel.decompiler.ast.TypeAnalysis.run(TypeAnalysis.java:96)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:109)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.Decompiler.decompile(Decompiler.java:70)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompile(Deobfuscator3000.java:538)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompileAndDeobfuscate(Deobfuscator3000.java:552)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.processMod(Deobfuscator3000.java:510)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.lambda$21(Deobfuscator3000.java:329)
        //     at java.lang.Thread.run(Unknown Source)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static class_5611 getDeltaForCoord(final class_5611 rot, final class_243 point) {
        final double x = point.field_1352 - HitAura.mc.field_1724.method_23317();
        final double y = point.field_1351 - HitAura.mc.field_1724.method_33571().method_10214();
        final double z = point.field_1350 - HitAura.mc.field_1724.method_23321();
        final double dst = Math.sqrt(Math.pow(x, 2.0) + Math.pow(z, 2.0));
        final float yawToTarget = (float)class_3532.method_15338(Math.toDegrees(Math.atan2(z, x)) - 90.0);
        final float pitchToTarget = (float)(-Math.toDegrees(Math.atan2(y, dst)));
        final float yawDelta = class_3532.method_15393(yawToTarget - rot.method_32118());
        final float pitchDelta = pitchToTarget - rot.method_32119();
        return new class_5611(yawDelta, pitchDelta);
    }
    
    public static boolean isPointVisible(final class_1297 entity, final class_243 pointPos, final double dist, final boolean troughWalls) {
        final float[] angle = MathUtil.calcAngle(HitAura.mc.field_1724.method_33571(), pointPos);
        return RaycastUtil.raycastBox(entity.method_5829(), dist, angle[0], angle[1], troughWalls);
    }
    
    public static class_243 findHitboxCoord(final Hitbox box, final int pointCode, final class_1297 target, final float depth) {
        double xCoord = 0.0;
        double yCoord = 0.0;
        double zCoord = 0.0;
        final float hitboxWidth = target.method_17681() / 2.0f - depth - 0.001f;
        final float hitboxHeight = target.method_17682();
        switch (box) {
            case HEAD: {
                yCoord = hitboxHeight - 0.2;
                break;
            }
            case CHEST: {
                yCoord = hitboxHeight / 2.0f;
                break;
            }
            case LEGS: {
                yCoord = 0.2;
                break;
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
                break;
            }
        }
        return target.method_19538().method_1031(xCoord, yCoord, zCoord);
    }
    
    public static boolean isActiveItemStackBlocking(final class_1657 other, final int time) {
        if (!other.method_6030().method_7960()) {
            final class_1792 item = other.method_6030().method_7909();
            return item.method_7853(other.method_6030()) == class_1839.field_8949 && item.method_7881(other.method_6030()) - other.method_6014() >= time;
        }
        return false;
    }
    
    static {
        HitAura.cooldown = new NumberSetting("cooldown", 1.0f, 0.0f, 1.0f, true);
        HitAura.crit_cooldown = new NumberSetting("crit-cooldown", 0.85f, 0.0f, 1.0f, true);
        HitAura.depth = new NumberSetting("depth", 0.1f, 0.0f, 0.2f, true);
        HitAura.radius = new NumberSetting("depth", 1.0f, 0.0f, 2.0f, true);
        HitAura.INSTANCE = new HitAura();
    }
    
    public enum Hitbox
    {
        HEAD, 
        CHEST, 
        LEGS;
        
        private static /* synthetic */ Hitbox[] $values() {
            return new Hitbox[] { Hitbox.HEAD, Hitbox.CHEST, Hitbox.LEGS };
        }
        
        static {
            $VALUES = $values();
        }
    }
}
