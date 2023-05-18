//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.module.modules.combat;

import com.mrzak34.thunderhack.module.*;
import com.mrzak34.thunderhack.settings.*;
import com.mrzak34.thunderhack.event.events.*;
import com.google.common.eventbus.*;
import org.apache.commons.lang3.*;
import java.util.*;
import com.mrzak34.thunderhack.util.*;
import net.minecraft.*;

public class AttackAura extends Module
{
    ModeSetting rotationMode;
    ModeSetting targetMode;
    ModeSetting targetPrio;
    ModeSetting aimMode;
    ModeSetting shieldBreakMode;
    NumberSetting distance;
    NumberSetting fov;
    NumberSetting minCPS;
    NumberSetting maxCPS;
    BooleanSetting autocrit;
    BooleanSetting rayrace;
    BooleanSetting look;
    BooleanSetting stopSprinting;
    BooleanSetting onlyWeapon;
    BooleanSetting shieldBlock;
    BooleanSetting shieldBreak;
    BooleanSetting autoDisable;
    BooleanSetting throughWalls;
    BooleanSetting teleport;
    BooleanSetting swing;
    BooleanSetting cooldown;
    public static TimerUtil D;
    public TimerUtil E;
    public TimerUtil F;
    public TimerUtil G;
    public TimerUtil H;
    public TimerUtil I;
    public static class_1657 target;
    public static float[] K;
    public float L;
    public boolean M;
    public double N;
    public int O;
    public boolean P;
    public float Q;
    public float R;
    public float serverYaw;
    public float serverPitch;
    public static double prevCircleStep;
    public static double circleStep;
    public static ArrayList<class_1657> targets;
    
    public AttackAura() {
        super("AttackAura", 0, false, Category.COMBAT);
        this.rotationMode = new ModeSetting("rotation", "default", new String[] { "default", "matrix", "aac", "sunrise" });
        this.targetMode = new ModeSetting("mode", "switch", new String[] { "switch", "single" });
        this.targetPrio = new ModeSetting("target prio", "closest", new String[] { "closest", "health", "equip" });
        this.aimMode = new ModeSetting("aim mode", "body", new String[] { "head", "body", "legs", "all" });
        this.shieldBreakMode = new ModeSetting("shield break mode", "old", new String[] { "old", "new" });
        this.distance = new NumberSetting("distance", 3.8f, 1.0f, 7.0f, true);
        this.fov = new NumberSetting("fov", 360.0f, 0.0f, 360.0f, false);
        this.minCPS = new NumberSetting("min cps", 6.0f, 1.0f, 30.0f, false);
        this.maxCPS = new NumberSetting("max cps", 12.0f, 1.0f, 30.0f, false);
        this.autocrit = new BooleanSetting("autocrit", true);
        this.rayrace = new BooleanSetting("raytrace", false);
        this.look = new BooleanSetting("look", false);
        this.stopSprinting = new BooleanSetting("stop sprint", false);
        this.onlyWeapon = new BooleanSetting("weapon only", false);
        this.shieldBlock = new BooleanSetting("shield block", false);
        this.shieldBreak = new BooleanSetting("shield breack", false);
        this.autoDisable = new BooleanSetting("auto disable", false);
        this.throughWalls = new BooleanSetting("through walls", false);
        this.teleport = new BooleanSetting("teleport", false);
        this.swing = new BooleanSetting("swing", true);
        this.cooldown = new BooleanSetting("cooldown", false);
        this.E = new TimerUtil();
        this.F = new TimerUtil();
        this.G = new TimerUtil();
        this.H = new TimerUtil();
        this.I = new TimerUtil();
        this.L = 0.1f;
        this.O = -2;
        this.serverYaw = 0.0f;
        this.serverPitch = 0.0f;
        this.addSettings(new Setting[] { this.rotationMode, this.targetMode, this.targetPrio, this.aimMode, this.shieldBreakMode, this.distance, this.fov, this.minCPS, this.maxCPS, this.autocrit, this.rayrace, this.look, this.stopSprinting, this.onlyWeapon, this.shieldBreak, this.autoDisable, this.throughWalls, this.teleport, this.swing, this.cooldown });
    }
    
    @Subscribe
    public void MeveEventPre(final MoveEvent event) {
        if (this.rotationMode.getMode().equals("matrix") || this.rotationMode.getMode().equals("sunrise") || this.rotationMode.getMode().equals("static")) {}
        if (this.shieldBlock.isEnabled()) {
            this.setShield();
        }
        this.z();
    }
    
    public void onEnable() {
        AttackAura.target = null;
        AttackAura.K[0] = 0.0f;
        AttackAura.K[1] = 0.0f;
        this.Q = this.d();
        this.R = this.e();
        if (!this.look.isEnabled() && !this.rotationMode.getMode().equals("aac")) {
            efUtil.j();
        }
        efUtil.l();
        this.L = (this.rotationMode.getMode().equals("sunrise") ? 0.5f : 0.1f);
        this.N = 0.0;
    }
    
    public void onDisable() {
        AttackAura.target = null;
        AttackAura.K[0] = 0.0f;
        AttackAura.K[1] = 0.0f;
        this.Q = this.d();
        this.R = this.e();
        if (!this.look.isEnabled() && !this.rotationMode.getMode().equals("aac")) {
            efUtil.j();
        }
        efUtil.l();
        this.L = (this.rotationMode.getMode().equals("sunrise") ? 0.5f : 0.1f);
        this.a(false);
    }
    
    public void onTick() {
        super.onTick();
        AttackAura.prevCircleStep = AttackAura.circleStep;
        AttackAura.circleStep += 0.15;
        if (AttackAura.target != null) {
            RotationUtil.lookAtEntity((class_1297)AttackAura.mc.field_1724, (class_1297)AttackAura.target);
        }
        if (!this.autoDisable.isEnabled()) {
            return;
        }
        if (AttackAura.mc.field_1724.method_6032() == 0.0f || AttackAura.mc.field_1724.method_29504()) {
            this.toggle();
        }
    }
    
    public static int checkAxe() {
        int n2 = -2;
        for (int i2 = 0; i2 <= 8; ++i2) {
            if (AttackAura.mc.field_1724.method_31548().method_5438(i2).method_7909() instanceof class_1743) {
                n2 = i2;
            }
        }
        return n2;
    }
    
    public static boolean checkShield(final class_1657 player) {
        final class_1799 itemStack = player.method_6079();
        final class_1799 itemStack2 = player.method_6047();
        return (!isEmpty(itemStack) && itemStack.method_7909() == class_1802.field_8255 && player.method_6048() > 0) || (!isEmpty(itemStack2) && itemStack2.method_7909() == class_1802.field_8255 && player.method_6048() > 0);
    }
    
    public static boolean isEmpty(final class_1799 itemStack) {
        return itemStack == null || itemStack.method_7960();
    }
    
    public void a(final class_1657 player) {
        final int n2;
        if (checkShield(player) && (n2 = checkAxe()) != -2) {
            this.O = AttackAura.mc.field_1724.method_31548().field_7545;
            AttackAura.mc.field_1724.field_3944.method_2883((class_2596)new class_2868(n2));
        }
    }
    
    public void b(final class_1657 player) {
        final int n2;
        if (player.method_6039() && (n2 = checkAxe()) != -2) {
            AttackAura.mc.field_1724.field_3944.method_2883((class_2596)new class_2868(n2));
            AttackAura.mc.field_1761.method_2918((class_1657)AttackAura.mc.field_1724, (class_1297)player);
            AttackAura.mc.field_1724.method_6104(class_1268.field_5808);
            AttackAura.mc.field_1724.field_3944.method_2883((class_2596)new class_2868(AttackAura.mc.field_1724.method_31548().field_7545));
        }
    }
    
    public static boolean etf() {
        return !isEmpty(AttackAura.mc.field_1724.method_6079()) && AttackAura.mc.field_1724.method_6079().method_7909() == class_1802.field_8255;
    }
    
    public void setShield() {
        if (!etf()) {
            return;
        }
        final class_1799 itemStack = AttackAura.mc.field_1724.method_6047();
        final class_1839 enumAction;
        if (!isEmpty(itemStack) && !AttackAura.mc.field_1690.field_1849.method_1434() && ((enumAction = itemStack.method_7909().method_7853(itemStack)) != class_1839.field_8952 || itemStack.method_7909() instanceof class_1747)) {
            this.a(false);
            this.M = false;
            return;
        }
        if (AttackAura.target == null) {
            if (this.M) {
                this.a(this.M = false);
            }
            return;
        }
        if (AttackAura.mc.field_1724.method_7261(0.0f) >= 0.75f) {
            this.M = false;
        }
        if (AttackAura.mc.field_1724.method_7261(0.0f) <= 0.1f) {
            this.M = true;
        }
        if (this.G.passedMs(RandomUtils.nextInt(0, 55))) {
            this.a(this.M);
            this.G.reset();
        }
    }
    
    public void a(final boolean bl) {
        if (AttackAura.mc.field_1724.method_6079().method_7909() instanceof class_1819) {
            AttackAura.mc.field_1690.field_1904.method_23481(bl);
        }
    }
    
    public float d() {
        return (this.look.isEnabled() || this.rotationMode.getMode().equals("aac")) ? AttackAura.mc.field_1724.method_36455() : efUtil.e();
    }
    
    public float e() {
        return (this.look.isEnabled() || this.rotationMode.getMode().equals("aac")) ? AttackAura.mc.field_1724.method_36454() : efUtil.d();
    }
    
    public void doRotate() {
        if (AttackAura.target == null) {
            return;
        }
        if (this.rotationMode.getMode().equals("default")) {
            if (this.look.isEnabled() || this.rotationMode.getMode().equals("aac")) {
                AttackAura.K[0] = RotationUtil.lookAtEntity((class_1297)AttackAura.mc.field_1724, (class_1297)AttackAura.target).field_1343;
                AttackAura.K[1] = RotationUtil.lookAtEntity((class_1297)AttackAura.mc.field_1724, (class_1297)AttackAura.target).field_1342;
                AttackAura.mc.field_1724.method_36456(AttackAura.K[0]);
                AttackAura.mc.field_1724.method_36457(AttackAura.K[1]);
            }
        }
        else {
            if (!this.rotationMode.getMode().equals("static")) {
                float f2 = 2.2f - RandomUtils.nextFloat();
                if (this.rotationMode.getMode().equals("sunrise")) {
                    f2 = 5.0f - RandomUtils.nextFloat();
                    f2 += RandomUtils.nextFloat();
                }
                AttackAura.K[0] = RotationUtil.lookAtEntity((class_1297)AttackAura.mc.field_1724, (class_1297)AttackAura.target).field_1343;
                AttackAura.K[1] = RotationUtil.lookAtEntity((class_1297)AttackAura.mc.field_1724, (class_1297)AttackAura.target).field_1342;
                if (this.rotationMode.getMode().equals("sunrise")) {
                    this.R = DeadMathUtil.c(this.e(), AttackAura.K[0], 75.0f + RandomUtils.nextFloat(0.1f, 1.0f));
                    this.Q = DeadMathUtil.c(this.d(), AttackAura.K[1], 2.0f + RandomUtils.nextFloat(0.1f, 1.0f));
                    AttackAura.K[0] = this.R;
                    AttackAura.K[1] = this.Q;
                }
            }
            else {
                AttackAura.K[0] = RotationUtil.lookAtEntity((class_1297)AttackAura.mc.field_1724, (class_1297)AttackAura.target).field_1343;
                AttackAura.K[1] = RotationUtil.lookAtEntity((class_1297)AttackAura.mc.field_1724, (class_1297)AttackAura.target).field_1342;
            }
            efUtil.a(AttackAura.K);
            AttackAura.mc.field_1724.method_36457(AttackAura.K[1]);
            AttackAura.mc.field_1724.method_36456((float)(AttackAura.K[0] + 1.0E-4));
            if (this.E.passedS(1.0)) {
                AttackAura.mc.field_1724.method_36456((float)(AttackAura.K[0] + 1.0E-4));
            }
            if (this.E.passedS(2.0)) {
                AttackAura.mc.field_1724.method_36456((float)(AttackAura.K[0] - 2.0E-4));
                this.E.reset();
            }
        }
        this.serverYaw = AttackAura.K[0];
        this.serverPitch = AttackAura.K[1];
    }
    
    public static boolean ete() {
        final class_1799 itemStack = AttackAura.mc.field_1724.method_6047();
        return !isEmpty(itemStack) && (itemStack.method_7909() instanceof class_1829 || itemStack.method_7909() instanceof class_1743);
    }
    
    private void getTarget() {
        if (this.targetPrio.getMode().equals("closest")) {
            AttackAura.target = this.getClosest(this.distance.getValue());
        }
        if (this.targetPrio.getMode().equals("equip")) {
            AttackAura.target = this.getArmorLess();
        }
        if (this.targetPrio.getMode().equals("health")) {
            AttackAura.target = this.getHealthDown();
        }
    }
    
    private class_1657 getArmorLess() {
        AttackAura.targets.clear();
        double armor = 1815.0;
        class_1657 target = null;
        for (final class_1297 object : AttackAura.mc.field_1687.method_18112()) {
            if (object instanceof class_1657) {
                final class_1657 player = (class_1657)object;
                if (!this.canAttack(player)) {
                    continue;
                }
                final double currentarm = ThunderDamageUtil.ChekTotalarmorDamage(player);
                if (currentarm > armor) {
                    continue;
                }
                armor = currentarm;
                target = player;
                AttackAura.targets.add(player);
            }
        }
        return target;
    }
    
    private class_1657 getClosest(final double range) {
        AttackAura.targets.clear();
        double dist = range;
        class_1657 target = null;
        for (final class_1297 object : AttackAura.mc.field_1687.method_18112()) {
            if (object instanceof class_1657) {
                final class_1657 player = (class_1657)object;
                if (!this.canAttack(player)) {
                    continue;
                }
                final double currentDist = AttackAura.mc.field_1724.method_5739((class_1297)player);
                if (currentDist > dist) {
                    continue;
                }
                dist = currentDist;
                target = player;
                AttackAura.targets.add(player);
            }
        }
        return target;
    }
    
    public boolean canAttack(final class_1657 player) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getstatic       com/mrzak34/thunderhack/module/modules/combat/AttackAura.mc:Lnet/minecraft/class_310;
        //     4: getfield        net/minecraft/class_310.field_1724:Lnet/minecraft/class_746;
        //     7: if_acmpne       12
        //    10: iconst_0       
        //    11: ireturn        
        //    12: aload_1         /* player */
        //    13: invokevirtual   net/minecraft/class_1657.method_5477:()Lnet/minecraft/class_2561;
        //    16: invokeinterface net/minecraft/class_2561.getString:()Ljava/lang/String;
        //    21: invokestatic    invokestatic   !!! ERROR
        //    24: ifeq            29
        //    27: iconst_0       
        //    28: ireturn        
        //    29: getstatic       com/mrzak34/thunderhack/module/modules/combat/AttackAura.mc:Lnet/minecraft/class_310;
        //    32: getfield        net/minecraft/class_310.field_1724:Lnet/minecraft/class_746;
        //    35: aload_1         /* player */
        //    36: invokevirtual   net/minecraft/class_746.method_5739:(Lnet/minecraft/class_1297;)F
        //    39: f2d            
        //    40: aload_0         /* this */
        //    41: getfield        com/mrzak34/thunderhack/module/modules/combat/AttackAura.distance:Lcom/mrzak34/thunderhack/settings/NumberSetting;
        //    44: invokevirtual   com/mrzak34/thunderhack/settings/NumberSetting.getValue:()D
        //    47: dcmpl          
        //    48: ifgt            55
        //    51: iconst_1       
        //    52: goto            56
        //    55: iconst_0       
        //    56: ireturn        
        //    MethodParameters:
        //  Name    Flags  
        //  ------  -----
        //  player  
        //    StackMapTable: 00 04 0C 10 19 40 01
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
    
    private class_1657 getHealthDown() {
        AttackAura.targets.clear();
        double health = 36.0;
        class_1657 target = null;
        for (final class_1297 object : AttackAura.mc.field_1687.method_18112()) {
            if (object instanceof class_1657) {
                final class_1657 player = (class_1657)object;
                if (!this.canAttack(player)) {
                    continue;
                }
                final double currenhealth = player.method_6032();
                if (currenhealth > health) {
                    continue;
                }
                health = currenhealth;
                target = player;
                AttackAura.targets.add(player);
            }
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
            entity = RaytraceUtil.b((float)(AttackAura.mc.field_1724.method_5624() ? (this.distance.getValue() - 0.1) : this.distance.getValue()), f2, f3);
        }
        return entity;
    }
    
    public void z() {
        if (!this.g(AttackAura.target)) {
            this.getTarget();
        }
        if (AttackAura.target == null) {
            return;
        }
        if (this.onlyWeapon.isEnabled() && !ete()) {
            return;
        }
        final class_1657 PlayerEntitySP = (class_1657)AttackAura.mc.field_1724;
        if (!this.e(AttackAura.target)) {
            return;
        }
        if (this.cooldown.isEnabled()) {
            final boolean crit = false;
            final float f3;
            final float f2 = f3 = ((this.autocrit.isEnabled() && crit) ? 1.0f : 0.92f);
            if (PlayerEntitySP.method_7261(0.0f) >= f2) {
                this.c(AttackAura.target);
                PlayerEntitySP.method_7350();
            }
            if (this.P && this.H.passedMs(800L)) {
                this.c(AttackAura.target);
                PlayerEntitySP.method_7350();
                efUtil.l();
                this.P = false;
                this.H.reset();
            }
        }
        else {
            final int n2 = RandomUtils.nextInt((int)this.minCPS.getValue(), this.costyl((int)this.minCPS.getValue(), (int)this.maxCPS.getValue()));
            if (AttackAura.D.passedMs(1000 / n2)) {
                this.c(AttackAura.target);
                AttackAura.D.reset();
            }
        }
        if (!this.g(AttackAura.target) && this.targetMode.getMode().equals("switch")) {
            this.getTarget();
        }
    }
    
    public int costyl(final int a, final int b) {
        if (a >= b) {
            return b - 2;
        }
        return a;
    }
    
    public void c(final class_1657 entityLivingBase) {
        final class_1657 PlayerEntitySP = (class_1657)AttackAura.mc.field_1724;
        if (this.teleport.isEnabled()) {
            PlayerEntitySP.method_5814(entityLivingBase.method_23317(), PlayerEntitySP.method_23318(), entityLivingBase.method_23321());
        }
        if (this.shieldBlock.isEnabled() && etf() && this.M) {
            return;
        }
        if (this.stopSprinting.isEnabled()) {
            AttackAura.mc.field_1724.field_3944.method_2883((class_2596)new class_2848((class_1297)AttackAura.mc.field_1724, class_2848.class_2849.field_12985));
        }
        if (this.rotationMode.getMode().equals("aac") && AttackAura.target != null) {
            final float f2 = 2.2f - RandomUtils.nextFloat();
            AttackAura.K = DeadMathUtil.a((class_1297)AttackAura.target, this.getHitbox(), f2);
            AttackAura.mc.field_1724.method_36456(AttackAura.K[0]);
            AttackAura.mc.field_1724.method_36457(AttackAura.K[1]);
        }
        class_1657 entityLivingBase2 = entityLivingBase;
        if (this.rotationMode.getMode().equals("matrix") || this.rotationMode.getMode().equals("sunrise") || this.rotationMode.getMode().equals("aac") || (this.rotationMode.getMode().equals("static") && (this.rayrace.isEnabled() || this.rotationMode.getMode().equals("sunrise")))) {
            final class_1297 entity = this.h();
            if (entity == null) {
                return;
            }
            entityLivingBase2 = (class_1657)entity;
        }
        if (this.shieldBreak.isEnabled() && this.shieldBreakMode.getMode().equals("old")) {
            this.a(entityLivingBase2);
        }
        final boolean bl2;
        final boolean bl = bl2 = (PlayerEntitySP.field_6017 > 0.0f && !PlayerEntitySP.method_24828() && !PlayerEntitySP.method_21754() && !PlayerEntitySP.method_5799() && PlayerEntitySP.method_5854() == null);
        if (bl) {}
        AttackAura.mc.method_1562().method_2883((class_2596)class_2824.method_34206((class_1297)entityLivingBase2, AttackAura.mc.field_1724.method_5715()));
        if (this.swing.isEnabled()) {
            AttackAura.mc.field_1724.method_6104(class_1268.field_5808);
        }
        if (this.shieldBreak.isEnabled() && this.shieldBreakMode.getMode().equals("new")) {
            this.b(entityLivingBase2);
        }
        AttackAura.mc.field_1761.method_2918((class_1657)AttackAura.mc.field_1724, (class_1297)entityLivingBase);
        if (this.O != -2) {
            AttackAura.mc.field_1724.field_3944.method_2883((class_2596)new class_2868(this.O));
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
            final int n2 = RandomUtils.nextInt(0, 3);
            return Hitbox.values()[n2];
        }
        return Hitbox.CHEST;
    }
    
    public boolean d(final class_1657 entityLivingBase) {
        return (this.targetPrio.getMode().equals("closest") && eQc(entityLivingBase, AttackAura.target)) || (this.targetPrio.getMode().equals("health") && eQa(entityLivingBase, AttackAura.target)) || (this.targetPrio.getMode().equals("equip") && eQb(entityLivingBase, AttackAura.target));
    }
    
    public static boolean eQa(final class_1657 entityLivingBase, final class_1657 entityLivingBase2) {
        return entityLivingBase2 == null || entityLivingBase.method_6032() < entityLivingBase2.method_6032();
    }
    
    public static boolean eQb(final class_1657 entityLivingBase, final class_1657 entityLivingBase2) {
        return entityLivingBase2 == null || !(entityLivingBase instanceof class_1657) || !(entityLivingBase2 instanceof class_1657) || etd(entityLivingBase) > etd(entityLivingBase2);
    }
    
    public static int etd(final class_1657 PlayerEntity) {
        final ArrayList<class_1799> arrayList = new ArrayList<class_1799>();
        final class_1263 inventoryPlayer = (class_1263)PlayerEntity.method_31548();
        final class_1799 itemStack = PlayerEntity.method_6047();
        final class_1799 itemStack2 = AttackAura.mc.field_1724.method_31548().method_7372(0);
        final class_1799 itemStack3 = AttackAura.mc.field_1724.method_31548().method_7372(1);
        final class_1799 itemStack4 = AttackAura.mc.field_1724.method_31548().method_7372(2);
        final class_1799 itemStack5 = AttackAura.mc.field_1724.method_31548().method_7372(3);
        final class_1799[] array;
        final class_1799[] itemStackArray = array = new class_1799[] { itemStack, itemStack5, itemStack4, itemStack3, itemStack2 };
        for (final class_1799 itemStack6 : array) {
            if (!isEmpty(itemStack6)) {
                arrayList.add(itemStack6);
            }
        }
        return arrayList.size();
    }
    
    public static boolean eQc(final class_1657 entityLivingBase, final class_1657 entityLivingBase2) {
        return entityLivingBase2 == null || AttackAura.mc.field_1724.method_5739((class_1297)entityLivingBase) < AttackAura.mc.field_1724.method_5739((class_1297)entityLivingBase2);
    }
    
    public boolean e(final class_1657 entityLivingBase) {
        return entityLivingBase.method_5739((class_1297)AttackAura.mc.field_1724) <= (float)(AttackAura.mc.field_1724.method_5624() ? (this.distance.getValue() - 0.1) : this.distance.getValue()) - this.L;
    }
    
    public boolean f(final class_1657 entityLivingBase) {
        return entityLivingBase.method_5739((class_1297)AttackAura.mc.field_1724) <= (float)(AttackAura.mc.field_1724.method_5624() ? (this.distance.getValue() - 0.1) : this.distance.getValue()) + this.L;
    }
    
    public boolean a(final class_1657 entityLivingBase, final float f2) {
        return entityLivingBase.method_5739((class_1297)AttackAura.mc.field_1724) <= (float)(AttackAura.mc.field_1724.method_5624() ? (this.distance.getValue() - 0.1) : this.distance.getValue()) + f2;
    }
    
    public boolean g(final class_1657 entityLivingBase) {
        return entityLivingBase != null && !entityLivingBase.method_29504() && entityLivingBase.field_6213 <= 0 && (this.f(entityLivingBase) || this.targetMode.getMode().equals("switch")) && (this.a(entityLivingBase, 2.0f) || this.targetMode.getMode().equals("single"));
    }
    
    public boolean h(final class_1657 entityLivingBase) {
        return !(entityLivingBase instanceof class_1657) && !checkent(entityLivingBase) && entityLivingBase != AttackAura.mc.field_1724 && !entityLivingBase.method_29504() && entityLivingBase.field_6213 <= 0 && checkent(entityLivingBase) && checkent(entityLivingBase) && this.f(entityLivingBase) && checkent(entityLivingBase) && DeadMathUtil.a((class_1297)entityLivingBase, this.fov.getValue()) && (this.throughWalls.isEnabled() || AttackAura.mc.field_1724.method_6057((class_1297)entityLivingBase)) && this.d(entityLivingBase);
    }
    
    public static boolean checkent(final class_1657 entityLivingBase) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: instanceof      Lnet/minecraft/class_1657;
        //     4: ifeq            22
        //     7: aload_0         /* entityLivingBase */
        //     8: invokevirtual   net/minecraft/class_1657.method_5477:()Lnet/minecraft/class_2561;
        //    11: invokeinterface net/minecraft/class_2561.getString:()Ljava/lang/String;
        //    16: invokestatic    invokestatic   !!! ERROR
        //    19: ifne            26
        //    22: iconst_1       
        //    23: goto            27
        //    26: iconst_0       
        //    27: ireturn        
        //    MethodParameters:
        //  Name              Flags  
        //  ----------------  -----
        //  entityLivingBase  
        //    StackMapTable: 00 03 16 03 40 01
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
    
    static {
        AttackAura.D = new TimerUtil();
        AttackAura.K = new float[2];
        AttackAura.targets = new ArrayList<class_1657>();
    }
    
    public enum Hitbox
    {
        HEAD, 
        CHEST, 
        TIGHS;
        
        private static /* synthetic */ Hitbox[] $values() {
            return new Hitbox[] { Hitbox.HEAD, Hitbox.CHEST, Hitbox.TIGHS };
        }
        
        static {
            $VALUES = $values();
        }
    }
}
