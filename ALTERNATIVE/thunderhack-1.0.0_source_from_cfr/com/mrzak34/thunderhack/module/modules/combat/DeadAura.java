/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.Subscribe
 *  net.minecraft.class_1268
 *  net.minecraft.class_1297
 *  net.minecraft.class_1657
 *  net.minecraft.class_1743
 *  net.minecraft.class_1747
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1819
 *  net.minecraft.class_1829
 *  net.minecraft.class_1839
 *  net.minecraft.class_2596
 *  net.minecraft.class_2824
 *  net.minecraft.class_2828$class_2830
 *  net.minecraft.class_2828$class_2831
 *  net.minecraft.class_2848
 *  net.minecraft.class_2848$class_2849
 *  net.minecraft.class_2868
 *  net.minecraft.class_2879
 *  net.minecraft.class_746
 *  org.apache.commons.lang3.RandomUtils
 */
package com.mrzak34.thunderhack.module.modules.combat;

import com.google.common.eventbus.Subscribe;
import com.mrzak34.thunderhack.event.events.MoveEvent;
import com.mrzak34.thunderhack.event.events.PacketEvent;
import com.mrzak34.thunderhack.manager.NotificationManager;
import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.BooleanSetting;
import com.mrzak34.thunderhack.settings.ModeSetting;
import com.mrzak34.thunderhack.settings.NumberSetting;
import com.mrzak34.thunderhack.util.DeadMathUtil;
import com.mrzak34.thunderhack.util.FriendUtil;
import com.mrzak34.thunderhack.util.RaytraceUtil;
import com.mrzak34.thunderhack.util.TargetUtil;
import com.mrzak34.thunderhack.util.ThunderDamageUtil;
import com.mrzak34.thunderhack.util.TimerUtil;
import com.mrzak34.thunderhack.util.efUtil;
import java.util.ArrayList;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1743;
import net.minecraft.class_1747;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1819;
import net.minecraft.class_1829;
import net.minecraft.class_1839;
import net.minecraft.class_2596;
import net.minecraft.class_2824;
import net.minecraft.class_2828;
import net.minecraft.class_2848;
import net.minecraft.class_2868;
import net.minecraft.class_2879;
import net.minecraft.class_746;
import org.apache.commons.lang3.RandomUtils;

public class DeadAura
extends Module {
    ModeSetting rotationMode = new ModeSetting("rotation", "default", "default", "matrix", "aac", "sunrise");
    ModeSetting targetMode = new ModeSetting("mode", "switch", "switch", "single");
    ModeSetting targetPrio = new ModeSetting("target prio", "closest", "closest", "health", "equip");
    ModeSetting aimMode = new ModeSetting("aim mode", "body", "head", "body", "legs", "all");
    ModeSetting shieldBreakMode = new ModeSetting("shield break mode", "old", "old", "new");
    NumberSetting distance = new NumberSetting("distance", 3.8f, 1.0f, 7.0f, true);
    NumberSetting fov = new NumberSetting("fov", 360.0f, 0.0f, 360.0f, false);
    NumberSetting minCPS = new NumberSetting("min cps", 6.0f, 1.0f, 30.0f, false);
    NumberSetting maxCPS = new NumberSetting("max cps", 12.0f, 1.0f, 30.0f, false);
    BooleanSetting autocrit = new BooleanSetting("autocrit", true);
    BooleanSetting rayrace = new BooleanSetting("raytrace", false);
    BooleanSetting look = new BooleanSetting("look", false);
    BooleanSetting stopSprinting = new BooleanSetting("stop sprint", false);
    BooleanSetting onlyWeapon = new BooleanSetting("weapon only", false);
    BooleanSetting shieldBlock = new BooleanSetting("shield block", false);
    BooleanSetting shieldBreak = new BooleanSetting("shield breack", false);
    BooleanSetting autoDisable = new BooleanSetting("auto disable", false);
    BooleanSetting throughWalls = new BooleanSetting("through walls", false);
    BooleanSetting teleport = new BooleanSetting("teleport", false);
    BooleanSetting swing = new BooleanSetting("swing", true);
    BooleanSetting cooldown = new BooleanSetting("cooldown", false);
    public static TimerUtil D = new TimerUtil();
    public TimerUtil E = new TimerUtil();
    public TimerUtil F = new TimerUtil();
    public TimerUtil G = new TimerUtil();
    public TimerUtil H = new TimerUtil();
    public TimerUtil I = new TimerUtil();
    public static class_1657 target;
    public static float[] K;
    public float L = 0.1f;
    public boolean M;
    public double N;
    public int O = -2;
    public boolean P;
    public float Q;
    public float R;
    public float serverYaw = 0.0f;
    public float serverPitch = 0.0f;
    public static double prevCircleStep;
    public static double circleStep;
    public static ArrayList<class_1657> targets;

    public DeadAura() {
        super("DeadAura", 0, false, Category.COMBAT);
        this.addSettings(this.rotationMode, this.targetMode, this.targetPrio, this.aimMode, this.shieldBreakMode, this.distance, this.fov, this.minCPS, this.maxCPS, this.autocrit, this.rayrace, this.look, this.stopSprinting, this.onlyWeapon, this.shieldBreak, this.autoDisable, this.throughWalls, this.teleport, this.swing, this.cooldown);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        target = null;
        DeadAura.K[0] = 0.0f;
        DeadAura.K[1] = 0.0f;
        this.Q = this.d();
        this.R = this.e();
        if (!this.look.isEnabled() && !this.rotationMode.getMode().equals("aac")) {
            efUtil.j();
        }
        efUtil.l();
        this.L = this.rotationMode.getMode().equals("sunrise") ? 0.5f : 0.1f;
        this.N = 0.0;
    }

    @Override
    public void onDisable() {
        super.onDisable();
        target = null;
        DeadAura.K[0] = 0.0f;
        DeadAura.K[1] = 0.0f;
        this.Q = this.d();
        this.R = this.e();
        if (!this.look.isEnabled() && !this.rotationMode.getMode().equals("aac")) {
            efUtil.j();
        }
        efUtil.l();
        this.L = this.rotationMode.getMode().equals("sunrise") ? 0.5f : 0.1f;
        this.a(false);
    }

    @Override
    public void onTick() {
        super.onTick();
        prevCircleStep = circleStep;
        circleStep += 0.15;
        if (!this.autoDisable.isEnabled()) {
            return;
        }
        if (DeadAura.mc.field_1724.method_6032() == 0.0f || DeadAura.mc.field_1724.method_29504()) {
            this.toggle();
        }
    }

    @Subscribe
    public void onMove(MoveEvent event) {
        NotificationManager.notif("MoveEvent");
        if (this.rotationMode.getMode().equals("matrix") || this.rotationMode.getMode().equals("sunrise") || this.rotationMode.getMode().equals("static")) {
            this.doRotate(event);
        }
        if (this.shieldBlock.isEnabled()) {
            this.setShield();
        }
        NotificationManager.notif("z");
        this.z();
    }

    @Subscribe
    public void PacketReceive(PacketEvent.Receive event) {
        if (DeadAura.fullNullCheck()) {
            return;
        }
        if ((event.getPacket() instanceof class_2828.class_2830 || event.getPacket() instanceof class_2828.class_2831) && (this.rotationMode.getMode().equals("matrix") || this.rotationMode.getMode().equals("sunrise")) && !this.look.isEnabled() && !this.rotationMode.getMode().equals("aac")) {
            this.z();
        }
    }

    public static int checkAxe() {
        int n2 = -2;
        for (int i2 = 0; i2 <= 8; ++i2) {
            if (!(DeadAura.mc.field_1724.method_31548().method_5438(i2).method_7909() instanceof class_1743)) continue;
            n2 = i2;
        }
        return n2;
    }

    public static boolean checkShield(class_1657 entityPlayer) {
        class_1799 itemStack = entityPlayer.method_6079();
        class_1799 itemStack2 = entityPlayer.method_6047();
        return !DeadAura.isEmpty(itemStack) && itemStack.method_7909() == class_1802.field_8255 && entityPlayer.method_6048() > 0 || !DeadAura.isEmpty(itemStack2) && itemStack2.method_7909() == class_1802.field_8255 && entityPlayer.method_6048() > 0;
    }

    public static boolean isEmpty(class_1799 itemStack) {
        return itemStack == null || itemStack.method_7960();
    }

    public void a(class_1657 entityPlayer) {
        int n2 = DeadAura.checkAxe();
        if (n2 != -2) {
            this.O = DeadAura.mc.field_1724.method_31548().field_7545;
            DeadAura.mc.field_1724.field_3944.method_2883((class_2596)new class_2868(n2));
        }
    }

    public void b(class_1657 entityPlayer) {
    }

    public static boolean etf() {
        return !DeadAura.isEmpty(DeadAura.mc.field_1724.method_6079()) && DeadAura.mc.field_1724.method_6079().method_7909() == class_1802.field_8255;
    }

    public void setShield() {
        class_1839 enumAction;
        if (!DeadAura.etf()) {
            return;
        }
        class_1799 itemStack = DeadAura.mc.field_1724.method_6047();
        if (!(DeadAura.isEmpty(itemStack) || DeadAura.mc.field_1690.field_1849.method_1434() || (enumAction = itemStack.method_7909().method_7853(itemStack)) == class_1839.field_8952 && !(itemStack.method_7909() instanceof class_1747))) {
            this.a(false);
            this.M = false;
            return;
        }
        if (target == null) {
            if (this.M) {
                this.M = false;
                this.a(this.M);
            }
            return;
        }
        if (DeadAura.mc.field_1724.method_7261(0.0f) >= 0.75f) {
            this.M = false;
        }
        if (DeadAura.mc.field_1724.method_7261(0.0f) <= 0.1f) {
            this.M = true;
        }
        if (this.G.passedMs(RandomUtils.nextInt((int)0, (int)55))) {
            this.a(this.M);
            this.G.reset();
        }
    }

    public void a(boolean bl) {
        if (DeadAura.mc.field_1724.method_6079().method_7909() instanceof class_1819) {
            DeadAura.mc.field_1690.field_1904.method_23481(bl);
        }
    }

    public float d() {
        return this.look.isEnabled() || this.rotationMode.getMode().equals("aac") ? DeadAura.mc.field_1724.method_36455() : efUtil.e();
    }

    public float e() {
        return this.look.isEnabled() || this.rotationMode.getMode().equals("aac") ? DeadAura.mc.field_1724.method_36454() : efUtil.d();
    }

    public void doRotate(MoveEvent event) {
        if (target == null) {
            return;
        }
        if (this.rotationMode.getMode().equals("default")) {
            if (this.look.isEnabled() || this.rotationMode.getMode().equals("aac")) {
                K = DeadMathUtil.a((class_1297)target, this.getHitbox());
                DeadAura.mc.field_1724.method_36456(K[0]);
                DeadAura.mc.field_1724.method_36457(K[1]);
            }
        } else {
            if (!this.rotationMode.getMode().equals("static")) {
                float f2 = 2.2f - RandomUtils.nextFloat();
                if (this.rotationMode.getMode().equals("sunrise")) {
                    f2 = 5.0f - RandomUtils.nextFloat();
                    f2 += RandomUtils.nextFloat();
                }
                K = DeadMathUtil.a((class_1297)target, this.getHitbox(), f2);
                if (this.rotationMode.getMode().equals("sunrise")) {
                    this.R = DeadMathUtil.c(this.e(), K[0], 75.0f + RandomUtils.nextFloat((float)0.1f, (float)1.0f));
                    this.Q = DeadMathUtil.c(this.d(), K[1], 2.0f + RandomUtils.nextFloat((float)0.1f, (float)1.0f));
                    DeadAura.K[0] = this.R;
                    DeadAura.K[1] = this.Q;
                }
            } else {
                K = DeadMathUtil.a2((class_1297)target, this.look.isEnabled() || this.rotationMode.getMode().equals("aac"));
            }
            efUtil.a(K);
            DeadAura.mc.field_1724.method_36457(K[1]);
            DeadAura.mc.field_1724.method_36456((float)((double)K[0] + 1.0E-4));
            if (this.E.passedS(1.0)) {
                DeadAura.mc.field_1724.method_36456((float)((double)K[0] + 1.0E-4));
            }
            if (this.E.passedS(2.0)) {
                DeadAura.mc.field_1724.method_36456((float)((double)K[0] - 2.0E-4));
                this.E.reset();
            }
        }
        this.serverYaw = K[0];
        this.serverPitch = K[1];
    }

    public static boolean ete() {
        class_1799 itemStack = DeadAura.mc.field_1724.method_6047();
        return !DeadAura.isEmpty(itemStack) && (itemStack.method_7909() instanceof class_1829 || itemStack.method_7909() instanceof class_1743);
    }

    private void getTarget() {
        if (this.targetPrio.getMode().equals("closest")) {
            target = this.getClosest(this.distance.getValue());
        }
        if (this.targetPrio.getMode().equals("equip")) {
            target = this.getArmorLess();
        }
        if (this.targetPrio.getMode().equals("health")) {
            target = this.getHealthDown();
        }
    }

    private void getTarget2() {
        target = TargetUtil.getTarget(this.distance.getValue());
    }

    private class_1657 getArmorLess() {
        targets.clear();
        double armor = 1815.0;
        class_1657 target = null;
        for (class_1297 object : DeadAura.mc.field_1687.method_18112()) {
            double currentarm;
            class_1657 player;
            if (!(object instanceof class_1657) || !this.canAttack(player = (class_1657)object) || !((currentarm = (double)ThunderDamageUtil.ChekTotalarmorDamage(player)) <= armor)) continue;
            armor = currentarm;
            target = player;
            targets.add(player);
        }
        return target;
    }

    private class_1657 getClosest(double range) {
        targets.clear();
        double dist = range;
        class_1657 target = null;
        for (class_1297 object : DeadAura.mc.field_1687.method_18112()) {
            double currentDist;
            class_1657 player;
            if (!(object instanceof class_1657) || !this.canAttack(player = (class_1657)object) || !((currentDist = (double)DeadAura.mc.field_1724.method_5739((class_1297)player)) <= dist)) continue;
            dist = currentDist;
            target = player;
            targets.add(player);
        }
        return target;
    }

    public boolean canAttack(class_1657 nigger) {
        if (nigger == DeadAura.mc.field_1724) {
            return false;
        }
        if (FriendUtil.isFriend(nigger.method_5477().getString())) {
            return false;
        }
        return !((double)DeadAura.mc.field_1724.method_5739((class_1297)nigger) > this.distance.getValue());
    }

    private class_1657 getHealthDown() {
        targets.clear();
        double health = 36.0;
        class_1657 target = null;
        for (class_1297 object : DeadAura.mc.field_1687.method_18112()) {
            double currenhealth;
            class_1657 player;
            if (!(object instanceof class_1657) || !this.canAttack(player = (class_1657)object) || !((currenhealth = (double)player.method_6032()) <= health)) continue;
            health = currenhealth;
            target = player;
            targets.add(player);
        }
        return target;
    }

    public class_1297 h() {
        class_1297 entity = null;
        if (this.rotationMode.getMode().equals("matrix") || this.rotationMode.getMode().equals("sunrise") || this.rotationMode.getMode().equals("aac") || this.rotationMode.getMode().equals("static")) {
            float f2 = this.serverYaw;
            float f3 = this.serverPitch;
            if (!this.look.isEnabled() && !this.rotationMode.getMode().equals("aac")) {
                f2 = efUtil.d();
                f3 = efUtil.e();
            }
            entity = RaytraceUtil.b((float)(DeadAura.mc.field_1724.method_5624() ? this.distance.getValue() - 0.1 : this.distance.getValue()), f2, f3);
        }
        return entity;
    }

    public void z() {
        if (!this.g(target)) {
            this.getTarget();
        }
        if (target == null) {
            NotificationManager.notif("target is null");
            return;
        }
        if (this.onlyWeapon.isEnabled() && !DeadAura.ete()) {
            return;
        }
        class_746 entityPlayerSP = DeadAura.mc.field_1724;
        if (!this.e(target)) {
            NotificationManager.notif("target not e");
            return;
        }
        if (this.cooldown.isEnabled()) {
            boolean crit = false;
            float f2 = this.autocrit.isEnabled() && crit ? 1.0f : 0.92f;
            float f3 = f2;
            if (entityPlayerSP.method_7261(0.0f) >= f2) {
                this.c(target);
                entityPlayerSP.method_7350();
            }
            if (this.P && this.H.passedMs(800L)) {
                this.c(target);
                entityPlayerSP.method_7350();
                efUtil.l();
                this.P = false;
                this.H.reset();
            }
        } else {
            int n2 = RandomUtils.nextInt((int)((int)this.minCPS.getValue()), (int)this.costyl((int)this.minCPS.getValue(), (int)this.maxCPS.getValue()));
            if (D.passedMs(1000 / n2)) {
                this.c(target);
                D.reset();
            }
        }
        if (!this.g(target) && this.targetMode.getMode().equals("switch")) {
            this.getTarget();
        }
    }

    public int costyl(int a, int b) {
        if (a >= b) {
            return b - 2;
        }
        return a;
    }

    public void c(class_1657 entityLivingBase) {
        class_746 entityPlayerSP = DeadAura.mc.field_1724;
        if (this.teleport.isEnabled()) {
            entityPlayerSP.method_5814(entityLivingBase.method_23317(), entityPlayerSP.method_23318(), entityLivingBase.method_23321());
        }
        if (this.shieldBlock.isEnabled() && DeadAura.etf() && this.M) {
            return;
        }
        if (this.stopSprinting.isEnabled()) {
            DeadAura.mc.field_1724.field_3944.method_2883((class_2596)new class_2848((class_1297)DeadAura.mc.field_1724, class_2848.class_2849.field_12985));
        }
        if (this.rotationMode.getMode().equals("aac") && target != null) {
            float f2 = 2.2f - RandomUtils.nextFloat();
            K = DeadMathUtil.a((class_1297)target, this.getHitbox(), f2);
            DeadAura.mc.field_1724.method_36456(K[0]);
            DeadAura.mc.field_1724.method_36457(K[1]);
        }
        class_1657 entityLivingBase2 = entityLivingBase;
        if (this.rotationMode.getMode().equals("matrix") || this.rotationMode.getMode().equals("sunrise") || this.rotationMode.getMode().equals("aac") || this.rotationMode.getMode().equals("static") && (this.rayrace.isEnabled() || this.rotationMode.getMode().equals("sunrise"))) {
            class_1297 entity = this.h();
            if (entity == null) {
                return;
            }
            entityLivingBase2 = (class_1657)entity;
        }
        if (this.shieldBreak.isEnabled() && this.shieldBreakMode.getMode().equals("old")) {
            this.a(entityLivingBase2);
        }
        boolean bl = entityPlayerSP.field_6017 > 0.0f && !entityPlayerSP.method_24828() && !entityPlayerSP.method_21754() && !entityPlayerSP.method_5799() && entityPlayerSP.method_5854() == null;
        boolean bl2 = bl;
        if (bl) {
            // empty if block
        }
        mc.method_1562().method_2883((class_2596)class_2824.method_34206((class_1297)entityLivingBase2, (boolean)DeadAura.mc.field_1724.method_5715()));
        if (this.swing.isEnabled()) {
            DeadAura.mc.field_1724.method_6104(class_1268.field_5808);
        } else {
            DeadAura.mc.field_1724.field_3944.method_2883((class_2596)new class_2879(class_1268.field_5808));
        }
        if (this.shieldBreak.isEnabled() && this.shieldBreakMode.getMode().equals("new")) {
            this.b(entityLivingBase2);
        }
        if (this.O != -2) {
            DeadAura.mc.field_1724.field_3944.method_2883((class_2596)new class_2868(this.O));
            this.O = -2;
        }
        this.M = true;
    }

    public Hitbox getHitbox() {
        if (this.aimMode.getMode().equals("head")) {
            return Hitbox.HEAD;
        }
        if (this.aimMode.getMode().equals("body")) {
            return Hitbox.CHEST;
        }
        if (this.aimMode.getMode().equals("legs")) {
            return Hitbox.TIGHS;
        }
        if (this.aimMode.getMode().equals("all")) {
            int n2 = RandomUtils.nextInt((int)0, (int)3);
            return Hitbox.values()[n2];
        }
        return Hitbox.CHEST;
    }

    public boolean d(class_1657 entityLivingBase) {
        return this.targetPrio.getMode().equals("closest") && DeadAura.eQc(entityLivingBase, target) || this.targetPrio.getMode().equals("health") && DeadAura.eQa(entityLivingBase, target) || this.targetPrio.getMode().equals("equip") && DeadAura.eQb(entityLivingBase, target);
    }

    public static boolean eQa(class_1657 entityLivingBase, class_1657 entityLivingBase2) {
        return entityLivingBase2 == null || entityLivingBase.method_6032() < entityLivingBase2.method_6032();
    }

    public static boolean eQb(class_1657 entityLivingBase, class_1657 entityLivingBase2) {
        return entityLivingBase2 == null || !(entityLivingBase instanceof class_1657) || !(entityLivingBase2 instanceof class_1657) || DeadAura.etd(entityLivingBase) > DeadAura.etd(entityLivingBase2);
    }

    public static int etd(class_1657 entityPlayer) {
        ArrayList<class_1799> arrayList = new ArrayList<class_1799>();
        class_1799 itemStack = entityPlayer.method_6047();
        class_1799 itemStack2 = (class_1799)entityPlayer.method_31548().field_7548.get(0);
        class_1799 itemStack3 = (class_1799)entityPlayer.method_31548().field_7548.get(1);
        class_1799 itemStack4 = (class_1799)entityPlayer.method_31548().field_7548.get(2);
        class_1799 itemStack5 = (class_1799)entityPlayer.method_31548().field_7548.get(3);
        class_1799[] itemStackArray = new class_1799[]{itemStack, itemStack5, itemStack4, itemStack3, itemStack2};
        for (class_1799 itemStack6 : itemStackArray) {
            if (DeadAura.isEmpty(itemStack6)) continue;
            arrayList.add(itemStack6);
        }
        return arrayList.size();
    }

    public static boolean eQc(class_1657 entityLivingBase, class_1657 entityLivingBase2) {
        return entityLivingBase2 == null || DeadAura.mc.field_1724.method_5739((class_1297)entityLivingBase) < DeadAura.mc.field_1724.method_5739((class_1297)entityLivingBase2);
    }

    public boolean e(class_1657 entityLivingBase) {
        return entityLivingBase.method_5739((class_1297)DeadAura.mc.field_1724) <= (float)(DeadAura.mc.field_1724.method_5624() ? this.distance.getValue() - 0.1 : this.distance.getValue()) - this.L;
    }

    public boolean f(class_1657 entityLivingBase) {
        return entityLivingBase.method_5739((class_1297)DeadAura.mc.field_1724) <= (float)(DeadAura.mc.field_1724.method_5624() ? this.distance.getValue() - 0.1 : this.distance.getValue()) + this.L;
    }

    public boolean a(class_1657 entityLivingBase, float f2) {
        return entityLivingBase.method_5739((class_1297)DeadAura.mc.field_1724) <= (float)(DeadAura.mc.field_1724.method_5624() ? this.distance.getValue() - 0.1 : this.distance.getValue()) + f2;
    }

    public boolean g(class_1657 entityLivingBase) {
        return !(entityLivingBase == null || entityLivingBase.method_29504() || entityLivingBase.field_6213 > 0 || !this.f(entityLivingBase) && this.targetMode.getMode().equals("switch") || !this.a(entityLivingBase, 2.0f) && this.targetMode.getMode().equals("single"));
    }

    public boolean h(class_1657 entityLivingBase) {
        return !(entityLivingBase instanceof class_1657) && !DeadAura.checkent(entityLivingBase) && entityLivingBase != DeadAura.mc.field_1724 && !entityLivingBase.method_29504() && entityLivingBase.field_6213 <= 0 && DeadAura.checkent(entityLivingBase) && DeadAura.checkent(entityLivingBase) && this.f(entityLivingBase) && DeadAura.checkent(entityLivingBase) && DeadMathUtil.a((class_1297)entityLivingBase, this.fov.getValue()) && (this.throughWalls.isEnabled() || DeadAura.mc.field_1724.method_6057((class_1297)entityLivingBase)) && this.d(entityLivingBase);
    }

    public static boolean checkent(class_1657 entityLivingBase) {
        return !(entityLivingBase instanceof class_1657) || !FriendUtil.isFriend(entityLivingBase.method_5477().getString());
    }

    static {
        K = new float[2];
        targets = new ArrayList();
    }

    public static final class Hitbox
    extends Enum<Hitbox> {
        public static final /* enum */ Hitbox HEAD = new Hitbox();
        public static final /* enum */ Hitbox CHEST = new Hitbox();
        public static final /* enum */ Hitbox TIGHS = new Hitbox();
        private static final /* synthetic */ Hitbox[] $VALUES;

        public static Hitbox[] values() {
            return (Hitbox[])$VALUES.clone();
        }

        public static Hitbox valueOf(String name) {
            return Enum.valueOf(Hitbox.class, name);
        }

        private static /* synthetic */ Hitbox[] $values() {
            return new Hitbox[]{HEAD, CHEST, TIGHS};
        }

        static {
            $VALUES = Hitbox.$values();
        }
    }
}

