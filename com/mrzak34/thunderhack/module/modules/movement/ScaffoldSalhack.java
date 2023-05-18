//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.module.modules.movement;

import com.mrzak34.thunderhack.util.*;
import com.mrzak34.thunderhack.module.*;
import com.mrzak34.thunderhack.settings.*;
import com.google.common.eventbus.*;
import com.mrzak34.thunderhack.event.events.*;
import net.minecraft.*;

public class ScaffoldSalhack extends Module
{
    ModeSetting mode;
    BooleanSetting stopMotion;
    NumberSetting delay;
    private TimerUtil _timer;
    private TimerUtil _towerPauseTimer;
    private TimerUtil _towerTimer;
    private TimerUtil _eatTimer;
    
    public ScaffoldSalhack() {
        super("ScaffoldSalhack", 0, false, Category.MOVEMENT);
        this.mode = new ModeSetting("mode", "tower", new String[] { "tower", "normal" });
        this.stopMotion = new BooleanSetting("stop motion", true);
        this.delay = new NumberSetting("delay", 0.0f, 0.0f, 1.0f, true);
        this._timer = new TimerUtil();
        this._towerPauseTimer = new TimerUtil();
        this._towerTimer = new TimerUtil();
        this._eatTimer = new TimerUtil();
        this.addSettings(new Setting[] { this.mode, this.stopMotion, this.delay });
    }
    
    @Subscribe
    public void onMove(final MoveEvent event) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: ifeq            7
        //     6: return         
        //     7: aload_0         /* this */
        //     8: getfield        com/mrzak34/thunderhack/module/modules/movement/ScaffoldSalhack._timer:Lcom/mrzak34/thunderhack/util/TimerUtil;
        //    11: aload_0         /* this */
        //    12: getfield        com/mrzak34/thunderhack/module/modules/movement/ScaffoldSalhack.delay:Lcom/mrzak34/thunderhack/settings/NumberSetting;
        //    15: invokevirtual   com/mrzak34/thunderhack/settings/NumberSetting.getValue:()D
        //    18: ldc2_w          1000.0
        //    21: dmul           
        //    22: d2l            
        //    23: invokevirtual   com/mrzak34/thunderhack/util/TimerUtil.passedMs:(J)Z
        //    26: ifne            30
        //    29: return         
        //    30: getstatic       com/mrzak34/thunderhack/module/modules/movement/ScaffoldSalhack.mc:Lnet/minecraft/class_310;
        //    33: getfield        net/minecraft/class_310.field_1724:Lnet/minecraft/class_746;
        //    36: invokevirtual   net/minecraft/class_746.method_6047:()Lnet/minecraft/class_1799;
        //    39: astore_2        /* stack */
        //    40: iconst_m1      
        //    41: istore_3        /* prevSlot */
        //    42: aload_0         /* this */
        //    43: aload_2         /* stack */
        //    44: invokevirtual   com/mrzak34/thunderhack/module/modules/movement/ScaffoldSalhack.verifyStack:(Lnet/minecraft/class_1799;)Z
        //    47: ifne            128
        //    50: iconst_0       
        //    51: istore          i
        //    53: iload           i
        //    55: bipush          9
        //    57: if_icmpge       128
        //    60: getstatic       com/mrzak34/thunderhack/module/modules/movement/ScaffoldSalhack.mc:Lnet/minecraft/class_310;
        //    63: getfield        net/minecraft/class_310.field_1724:Lnet/minecraft/class_746;
        //    66: invokevirtual   net/minecraft/class_746.method_31548:()Lnet/minecraft/class_1661;
        //    69: iload           i
        //    71: invokevirtual   net/minecraft/class_1661.method_5438:(I)Lnet/minecraft/class_1799;
        //    74: astore_2        /* stack */
        //    75: aload_0         /* this */
        //    76: aload_2         /* stack */
        //    77: invokevirtual   com/mrzak34/thunderhack/module/modules/movement/ScaffoldSalhack.verifyStack:(Lnet/minecraft/class_1799;)Z
        //    80: ifeq            122
        //    83: getstatic       com/mrzak34/thunderhack/module/modules/movement/ScaffoldSalhack.mc:Lnet/minecraft/class_310;
        //    86: getfield        net/minecraft/class_310.field_1724:Lnet/minecraft/class_746;
        //    89: invokevirtual   net/minecraft/class_746.method_31548:()Lnet/minecraft/class_1661;
        //    92: getfield        net/minecraft/class_1661.field_7545:I
        //    95: istore_3        /* prevSlot */
        //    96: getstatic       com/mrzak34/thunderhack/module/modules/movement/ScaffoldSalhack.mc:Lnet/minecraft/class_310;
        //    99: getfield        net/minecraft/class_310.field_1724:Lnet/minecraft/class_746;
        //   102: invokevirtual   net/minecraft/class_746.method_31548:()Lnet/minecraft/class_1661;
        //   105: iload           i
        //   107: putfield        net/minecraft/class_1661.field_7545:I
        //   110: getstatic       com/mrzak34/thunderhack/module/modules/movement/ScaffoldSalhack.mc:Lnet/minecraft/class_310;
        //   113: getfield        net/minecraft/class_310.field_1761:Lnet/minecraft/class_636;
        //   116: invokevirtual   net/minecraft/class_636.method_2927:()V
        //   119: goto            128
        //   122: iinc            i, 1
        //   125: goto            53
        //   128: aload_0         /* this */
        //   129: aload_2         /* stack */
        //   130: invokevirtual   com/mrzak34/thunderhack/module/modules/movement/ScaffoldSalhack.verifyStack:(Lnet/minecraft/class_1799;)Z
        //   133: ifne            137
        //   136: return         
        //   137: aload_0         /* this */
        //   138: getfield        com/mrzak34/thunderhack/module/modules/movement/ScaffoldSalhack._timer:Lcom/mrzak34/thunderhack/util/TimerUtil;
        //   141: invokevirtual   com/mrzak34/thunderhack/util/TimerUtil.reset:()Lcom/mrzak34/thunderhack/util/TimerUtil;
        //   144: pop            
        //   145: aconst_null    
        //   146: astore          toPlaceAt
        //   148: getstatic       com/mrzak34/thunderhack/module/modules/movement/ScaffoldSalhack.mc:Lnet/minecraft/class_310;
        //   151: getfield        net/minecraft/class_310.field_1724:Lnet/minecraft/class_746;
        //   154: invokevirtual   net/minecraft/class_746.method_24515:()Lnet/minecraft/class_2338;
        //   157: invokevirtual   net/minecraft/class_2338.method_10074:()Lnet/minecraft/class_2338;
        //   160: astore          feetBlock
        //   162: getstatic       com/mrzak34/thunderhack/module/modules/movement/ScaffoldSalhack.mc:Lnet/minecraft/class_310;
        //   165: getfield        net/minecraft/class_310.field_1687:Lnet/minecraft/class_638;
        //   168: aload           feetBlock
        //   170: invokevirtual   net/minecraft/class_638.method_8320:(Lnet/minecraft/class_2338;)Lnet/minecraft/class_2680;
        //   173: invokevirtual   net/minecraft/class_2680.method_26207:()Lnet/minecraft/class_3614;
        //   176: invokevirtual   net/minecraft/class_3614.method_15800:()Z
        //   179: istore          placeAtFeet
        //   181: ldc             "tower verify"
        //   183: invokestatic    invokestatic   !!! ERROR
        //   186: aload_0         /* this */
        //   187: getfield        com/mrzak34/thunderhack/module/modules/movement/ScaffoldSalhack.mode:Lcom/mrzak34/thunderhack/settings/ModeSetting;
        //   190: invokevirtual   com/mrzak34/thunderhack/settings/ModeSetting.getMode:()Ljava/lang/String;
        //   193: ldc             "tower"
        //   195: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   198: ifeq            307
        //   201: iload           placeAtFeet
        //   203: ifeq            307
        //   206: getstatic       com/mrzak34/thunderhack/module/modules/movement/ScaffoldSalhack.mc:Lnet/minecraft/class_310;
        //   209: getfield        net/minecraft/class_310.field_1724:Lnet/minecraft/class_746;
        //   212: getfield        net/minecraft/class_746.field_3913:Lnet/minecraft/class_744;
        //   215: getfield        net/minecraft/class_744.field_3904:Z
        //   218: ifeq            307
        //   221: aload_0         /* this */
        //   222: getfield        com/mrzak34/thunderhack/module/modules/movement/ScaffoldSalhack._towerTimer:Lcom/mrzak34/thunderhack/util/TimerUtil;
        //   225: ldc2_w          250
        //   228: invokevirtual   com/mrzak34/thunderhack/util/TimerUtil.passedMs:(J)Z
        //   231: ifeq            307
        //   234: getstatic       com/mrzak34/thunderhack/module/modules/movement/ScaffoldSalhack.mc:Lnet/minecraft/class_310;
        //   237: getfield        net/minecraft/class_310.field_1724:Lnet/minecraft/class_746;
        //   240: invokevirtual   net/minecraft/class_746.method_6128:()Z
        //   243: ifne            307
        //   246: ldc             "tower success"
        //   248: invokestatic    invokestatic   !!! ERROR
        //   251: aload_0         /* this */
        //   252: getfield        com/mrzak34/thunderhack/module/modules/movement/ScaffoldSalhack._towerPauseTimer:Lcom/mrzak34/thunderhack/util/TimerUtil;
        //   255: ldc2_w          1500
        //   258: invokevirtual   com/mrzak34/thunderhack/util/TimerUtil.passedMs:(J)Z
        //   261: ifeq            289
        //   264: aload_0         /* this */
        //   265: getfield        com/mrzak34/thunderhack/module/modules/movement/ScaffoldSalhack._towerPauseTimer:Lcom/mrzak34/thunderhack/util/TimerUtil;
        //   268: invokevirtual   com/mrzak34/thunderhack/util/TimerUtil.reset:()Lcom/mrzak34/thunderhack/util/TimerUtil;
        //   271: pop            
        //   272: getstatic       com/mrzak34/thunderhack/module/modules/movement/ScaffoldSalhack.mc:Lnet/minecraft/class_310;
        //   275: getfield        net/minecraft/class_310.field_1724:Lnet/minecraft/class_746;
        //   278: dconst_0       
        //   279: ldc2_w          -0.28
        //   282: dconst_0       
        //   283: invokevirtual   net/minecraft/class_746.method_18800:(DDD)V
        //   286: goto            307
        //   289: ldc             0.42
        //   291: fstore          towerMotion
        //   293: getstatic       com/mrzak34/thunderhack/module/modules/movement/ScaffoldSalhack.mc:Lnet/minecraft/class_310;
        //   296: getfield        net/minecraft/class_310.field_1724:Lnet/minecraft/class_746;
        //   299: dconst_0       
        //   300: ldc2_w          0.41999998688697815
        //   303: dconst_0       
        //   304: invokevirtual   net/minecraft/class_746.method_18800:(DDD)V
        //   307: iload           placeAtFeet
        //   309: ifeq            319
        //   312: aload           feetBlock
        //   314: astore          toPlaceAt
        //   316: goto            508
        //   319: aload           feetBlock
        //   321: invokestatic    com/mrzak34/thunderhack/module/modules/movement/ScaffoldSalhack.valid:(Lnet/minecraft/class_2338;)Lcom/mrzak34/thunderhack/module/modules/movement/ScaffoldSalhack$ValidResult;
        //   324: astore          result
        //   326: aload           result
        //   328: getstatic       com/mrzak34/thunderhack/module/modules/movement/ScaffoldSalhack$ValidResult.Ok:Lcom/mrzak34/thunderhack/module/modules/movement/ScaffoldSalhack$ValidResult;
        //   331: if_acmpeq       508
        //   334: aload           result
        //   336: getstatic       com/mrzak34/thunderhack/module/modules/movement/ScaffoldSalhack$ValidResult.AlreadyBlockThere:Lcom/mrzak34/thunderhack/module/modules/movement/ScaffoldSalhack$ValidResult;
        //   339: if_acmpeq       508
        //   342: iconst_4       
        //   343: anewarray       Lnet/minecraft/class_2338;
        //   346: dup            
        //   347: iconst_0       
        //   348: aload           feetBlock
        //   350: invokevirtual   net/minecraft/class_2338.method_10095:()Lnet/minecraft/class_2338;
        //   353: aastore        
        //   354: dup            
        //   355: iconst_1       
        //   356: aload           feetBlock
        //   358: invokevirtual   net/minecraft/class_2338.method_10072:()Lnet/minecraft/class_2338;
        //   361: aastore        
        //   362: dup            
        //   363: iconst_2       
        //   364: aload           feetBlock
        //   366: invokevirtual   net/minecraft/class_2338.method_10078:()Lnet/minecraft/class_2338;
        //   369: aastore        
        //   370: dup            
        //   371: iconst_3       
        //   372: aload           feetBlock
        //   374: invokevirtual   net/minecraft/class_2338.method_10067:()Lnet/minecraft/class_2338;
        //   377: aastore        
        //   378: astore          array
        //   380: aconst_null    
        //   381: astore          toSelect
        //   383: ldc2_w          420.0
        //   386: dstore          lastDistance
        //   388: aload           array
        //   390: astore          12
        //   392: aload           12
        //   394: arraylength    
        //   395: istore          13
        //   397: iconst_0       
        //   398: istore          14
        //   400: iload           14
        //   402: iload           13
        //   404: if_icmpge       499
        //   407: aload           12
        //   409: iload           14
        //   411: aaload         
        //   412: astore          pos
        //   414: getstatic       com/mrzak34/thunderhack/module/modules/movement/ScaffoldSalhack.mc:Lnet/minecraft/class_310;
        //   417: getfield        net/minecraft/class_310.field_1687:Lnet/minecraft/class_638;
        //   420: aload           pos
        //   422: invokevirtual   net/minecraft/class_638.method_8320:(Lnet/minecraft/class_2338;)Lnet/minecraft/class_2680;
        //   425: invokevirtual   net/minecraft/class_2680.method_26207:()Lnet/minecraft/class_3614;
        //   428: invokevirtual   net/minecraft/class_3614.method_15800:()Z
        //   431: ifne            437
        //   434: goto            493
        //   437: aload           pos
        //   439: getstatic       com/mrzak34/thunderhack/module/modules/movement/ScaffoldSalhack.mc:Lnet/minecraft/class_310;
        //   442: getfield        net/minecraft/class_310.field_1724:Lnet/minecraft/class_746;
        //   445: invokevirtual   net/minecraft/class_746.method_23317:()D
        //   448: d2i            
        //   449: i2d            
        //   450: getstatic       com/mrzak34/thunderhack/module/modules/movement/ScaffoldSalhack.mc:Lnet/minecraft/class_310;
        //   453: getfield        net/minecraft/class_310.field_1724:Lnet/minecraft/class_746;
        //   456: invokevirtual   net/minecraft/class_746.method_23318:()D
        //   459: d2i            
        //   460: i2d            
        //   461: getstatic       com/mrzak34/thunderhack/module/modules/movement/ScaffoldSalhack.mc:Lnet/minecraft/class_310;
        //   464: getfield        net/minecraft/class_310.field_1724:Lnet/minecraft/class_746;
        //   467: invokevirtual   net/minecraft/class_746.method_23321:()D
        //   470: d2i            
        //   471: i2d            
        //   472: invokevirtual   net/minecraft/class_2338.method_40081:(DDD)D
        //   475: dstore          dist
        //   477: dload           lastDistance
        //   479: dload           dist
        //   481: dcmpl          
        //   482: ifle            493
        //   485: dload           dist
        //   487: dstore          lastDistance
        //   489: aload           pos
        //   491: astore          toSelect
        //   493: iinc            14, 1
        //   496: goto            400
        //   499: aload           toSelect
        //   501: ifnull          508
        //   504: aload           toSelect
        //   506: astore          toPlaceAt
        //   508: aload           toPlaceAt
        //   510: ifnull          790
        //   513: new             Lnet/minecraft/class_243;
        //   516: dup            
        //   517: getstatic       com/mrzak34/thunderhack/module/modules/movement/ScaffoldSalhack.mc:Lnet/minecraft/class_310;
        //   520: getfield        net/minecraft/class_310.field_1724:Lnet/minecraft/class_746;
        //   523: invokevirtual   net/minecraft/class_746.method_23317:()D
        //   526: getstatic       com/mrzak34/thunderhack/module/modules/movement/ScaffoldSalhack.mc:Lnet/minecraft/class_310;
        //   529: getfield        net/minecraft/class_310.field_1724:Lnet/minecraft/class_746;
        //   532: invokevirtual   net/minecraft/class_746.method_23318:()D
        //   535: getstatic       com/mrzak34/thunderhack/module/modules/movement/ScaffoldSalhack.mc:Lnet/minecraft/class_310;
        //   538: getfield        net/minecraft/class_310.field_1724:Lnet/minecraft/class_746;
        //   541: invokevirtual   net/minecraft/class_746.method_23320:()D
        //   544: dadd           
        //   545: getstatic       com/mrzak34/thunderhack/module/modules/movement/ScaffoldSalhack.mc:Lnet/minecraft/class_310;
        //   548: getfield        net/minecraft/class_310.field_1724:Lnet/minecraft/class_746;
        //   551: invokevirtual   net/minecraft/class_746.method_23321:()D
        //   554: invokespecial   net/minecraft/class_243.<init>:(DDD)V
        //   557: astore          eyesPos
        //   559: invokestatic    net/minecraft/class_2350.values:()[Lnet/minecraft/class_2350;
        //   562: astore          8
        //   564: aload           8
        //   566: arraylength    
        //   567: istore          9
        //   569: iconst_0       
        //   570: istore          10
        //   572: iload           10
        //   574: iload           9
        //   576: if_icmpge       693
        //   579: aload           8
        //   581: iload           10
        //   583: aaload         
        //   584: astore          side
        //   586: aload           toPlaceAt
        //   588: aload           side
        //   590: invokevirtual   net/minecraft/class_2338.method_10093:(Lnet/minecraft/class_2350;)Lnet/minecraft/class_2338;
        //   593: astore          neighbor
        //   595: aload           side
        //   597: invokevirtual   net/minecraft/class_2350.method_10153:()Lnet/minecraft/class_2350;
        //   600: astore          side2
        //   602: new             Lnet/minecraft/class_243;
        //   605: dup            
        //   606: aload           neighbor
        //   608: invokevirtual   net/minecraft/class_2338.method_10263:()I
        //   611: i2d            
        //   612: aload           neighbor
        //   614: invokevirtual   net/minecraft/class_2338.method_10264:()I
        //   617: i2d            
        //   618: aload           neighbor
        //   620: invokevirtual   net/minecraft/class_2338.method_10260:()I
        //   623: i2d            
        //   624: invokespecial   net/minecraft/class_243.<init>:(DDD)V
        //   627: ldc2_w          0.5
        //   630: ldc2_w          0.5
        //   633: ldc2_w          0.5
        //   636: invokevirtual   net/minecraft/class_243.method_1031:(DDD)Lnet/minecraft/class_243;
        //   639: new             Lnet/minecraft/class_243;
        //   642: dup            
        //   643: aload           side2
        //   645: invokevirtual   net/minecraft/class_2350.method_23955:()Lorg/joml/Vector3f;
        //   648: invokespecial   net/minecraft/class_243.<init>:(Lorg/joml/Vector3f;)V
        //   651: invokevirtual   net/minecraft/class_243.method_1019:(Lnet/minecraft/class_243;)Lnet/minecraft/class_243;
        //   654: astore          hitVec
        //   656: aload           eyesPos
        //   658: aload           hitVec
        //   660: invokevirtual   net/minecraft/class_243.method_1022:(Lnet/minecraft/class_243;)D
        //   663: ldc2_w          5.0
        //   666: dcmpg          
        //   667: ifgt            687
        //   670: aload_0         /* this */
        //   671: aload           toPlaceAt
        //   673: aload           side
        //   675: invokevirtual   com/mrzak34/thunderhack/module/modules/movement/ScaffoldSalhack.getRotations:(Lnet/minecraft/class_2338;Lnet/minecraft/class_2350;)[F
        //   678: astore          rot
        //   680: aload_1         /* event */
        //   681: invokevirtual   com/mrzak34/thunderhack/event/events/MoveEvent.cancel:()V
        //   684: goto            693
        //   687: iinc            10, 1
        //   690: goto            572
        //   693: getstatic       com/mrzak34/thunderhack/module/modules/movement/ScaffoldSalhack.mc:Lnet/minecraft/class_310;
        //   696: getfield        net/minecraft/class_310.field_1724:Lnet/minecraft/class_746;
        //   699: getfield        net/minecraft/class_746.field_3944:Lnet/minecraft/class_634;
        //   702: new             Lnet/minecraft/class_2885;
        //   705: dup            
        //   706: getstatic       net/minecraft/class_1268.field_5808:Lnet/minecraft/class_1268;
        //   709: new             Lnet/minecraft/class_3965;
        //   712: dup            
        //   713: new             Lnet/minecraft/class_243;
        //   716: dup            
        //   717: aload           toPlaceAt
        //   719: invokevirtual   net/minecraft/class_2338.method_10263:()I
        //   722: i2d            
        //   723: invokestatic    java/lang/Math.random:()D
        //   726: dadd           
        //   727: aload           toPlaceAt
        //   729: invokevirtual   net/minecraft/class_2338.method_10264:()I
        //   732: i2d            
        //   733: aload           toPlaceAt
        //   735: invokevirtual   net/minecraft/class_2338.method_10260:()I
        //   738: i2d            
        //   739: invokestatic    java/lang/Math.random:()D
        //   742: dadd           
        //   743: invokespecial   net/minecraft/class_243.<init>:(DDD)V
        //   746: aload_0         /* this */
        //   747: aload           toPlaceAt
        //   749: invokevirtual   com/mrzak34/thunderhack/module/modules/movement/ScaffoldSalhack.getStrictDirection:(Lnet/minecraft/class_2338;)Lnet/minecraft/class_2350;
        //   752: aload           toPlaceAt
        //   754: iconst_0       
        //   755: invokespecial   net/minecraft/class_3965.<init>:(Lnet/minecraft/class_243;Lnet/minecraft/class_2350;Lnet/minecraft/class_2338;Z)V
        //   758: iconst_1       
        //   759: invokespecial   net/minecraft/class_2885.<init>:(Lnet/minecraft/class_1268;Lnet/minecraft/class_3965;I)V
        //   762: invokevirtual   net/minecraft/class_634.method_2883:(Lnet/minecraft/class_2596;)V
        //   765: getstatic       com/mrzak34/thunderhack/module/modules/movement/ScaffoldSalhack.mc:Lnet/minecraft/class_310;
        //   768: getfield        net/minecraft/class_310.field_1724:Lnet/minecraft/class_746;
        //   771: getfield        net/minecraft/class_746.field_3944:Lnet/minecraft/class_634;
        //   774: new             Lnet/minecraft/class_2879;
        //   777: dup            
        //   778: getstatic       net/minecraft/class_1268.field_5808:Lnet/minecraft/class_1268;
        //   781: invokespecial   net/minecraft/class_2879.<init>:(Lnet/minecraft/class_1268;)V
        //   784: invokevirtual   net/minecraft/class_634.method_2883:(Lnet/minecraft/class_2596;)V
        //   787: goto            798
        //   790: aload_0         /* this */
        //   791: getfield        com/mrzak34/thunderhack/module/modules/movement/ScaffoldSalhack._towerPauseTimer:Lcom/mrzak34/thunderhack/util/TimerUtil;
        //   794: invokevirtual   com/mrzak34/thunderhack/util/TimerUtil.reset:()Lcom/mrzak34/thunderhack/util/TimerUtil;
        //   797: pop            
        //   798: iload_3         /* prevSlot */
        //   799: iconst_m1      
        //   800: if_icmpeq       825
        //   803: getstatic       com/mrzak34/thunderhack/module/modules/movement/ScaffoldSalhack.mc:Lnet/minecraft/class_310;
        //   806: getfield        net/minecraft/class_310.field_1724:Lnet/minecraft/class_746;
        //   809: invokevirtual   net/minecraft/class_746.method_31548:()Lnet/minecraft/class_1661;
        //   812: iload_3         /* prevSlot */
        //   813: putfield        net/minecraft/class_1661.field_7545:I
        //   816: getstatic       com/mrzak34/thunderhack/module/modules/movement/ScaffoldSalhack.mc:Lnet/minecraft/class_310;
        //   819: getfield        net/minecraft/class_310.field_1761:Lnet/minecraft/class_636;
        //   822: invokevirtual   net/minecraft/class_636.method_2927:()V
        //   825: return         
        //    MethodParameters:
        //  Name   Flags  
        //  -----  -----
        //  event  
        //    StackMapTable: 00 14 07 16 FE 00 16 07 00 80 01 01 FB 00 44 FA 00 05 08 FE 00 97 07 00 A1 07 00 A1 01 11 0B FF 00 50 00 0E 07 00 02 07 01 00 07 00 80 01 07 00 A1 07 00 A1 01 07 00 07 07 01 02 07 00 A1 03 07 01 02 01 01 00 00 FC 00 24 07 00 A1 FA 00 37 F8 00 05 FF 00 08 00 07 07 00 02 07 01 00 07 00 80 01 07 00 A1 07 00 A1 01 00 00 FF 00 3F 00 0B 07 00 02 07 01 00 07 00 80 01 07 00 A1 07 00 A1 01 07 01 11 07 01 1E 01 01 00 00 FB 00 72 F8 00 05 FA 00 60 07 1A
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
    
    private class_2350 getStrictDirection(final class_2338 blockPos) {
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
    
    private boolean isBlockValid(final class_2248 block) {
        return block.method_9564().method_26207().method_15799();
    }
    
    private float[] getRotations(final class_2338 blockPos, final class_2350 enumFacing) {
        class_243 vec3d = new class_243(blockPos.method_10263() + 0.5, blockPos.method_10264() - 0.01, blockPos.method_10260() + 0.5);
        vec3d = vec3d.method_1019(new class_243(enumFacing.method_23955()));
        final class_243 vec3d2 = ScaffoldSalhack.mc.field_1724.method_33571();
        final double d = vec3d.field_1352 - vec3d2.field_1352;
        final double d2 = vec3d.field_1351 - vec3d2.field_1351;
        final double d3 = vec3d.field_1350 - vec3d2.field_1350;
        final double d4 = Math.sqrt(d * d + d3 * d3);
        final float f = (float)(Math.toDegrees(Math.atan2(d3, d)) - 90.0);
        final float f2 = (float)(-Math.toDegrees(Math.atan2(d2, d4)));
        final float[] ret = { ScaffoldSalhack.mc.field_1724.method_36454() + class_3532.method_15393(f - ScaffoldSalhack.mc.field_1724.method_36454()), ScaffoldSalhack.mc.field_1724.method_36455() + class_3532.method_15393(f2 - ScaffoldSalhack.mc.field_1724.method_36455()) };
        return ret;
    }
    
    public static ValidResult valid(final class_2338 pos) {
        if (!ScaffoldSalhack.mc.field_1687.method_39454((class_1297)ScaffoldSalhack.mc.field_1724, new class_238(pos))) {
            return ValidResult.NoEntityCollision;
        }
        if (ScaffoldSalhack.mc.field_1687.method_8320(pos.method_10074()).method_26204() == class_2246.field_10382) {
            return ValidResult.Ok;
        }
        if (!checkForNeighbours(pos)) {
            return ValidResult.NoNeighbors;
        }
        final class_2680 l_State = ScaffoldSalhack.mc.field_1687.method_8320(pos);
        if (l_State.method_26204() == class_2246.field_10124) {
            return ValidResult.NoNeighbors;
        }
        return ValidResult.AlreadyBlockThere;
    }
    
    public static boolean checkForNeighbours(final class_2338 blockPos) {
        if (!hasNeighbour(blockPos)) {
            for (final class_2350 side : class_2350.values()) {
                final class_2338 neighbour = blockPos.method_10093(side);
                if (hasNeighbour(neighbour)) {
                    return true;
                }
                if (side == class_2350.field_11036 && ScaffoldSalhack.mc.field_1687.method_8320(blockPos).method_26204() == class_2246.field_10382 && ScaffoldSalhack.mc.field_1687.method_8320(blockPos.method_10084()).method_26204() == class_2246.field_10124) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }
    
    public static boolean hasNeighbour(final class_2338 blockPos) {
        for (final class_2350 side : class_2350.values()) {
            final class_2338 neighbour = blockPos.method_10093(side);
            if (!ScaffoldSalhack.mc.field_1687.method_8320(neighbour).method_26207().method_15800()) {
                return true;
            }
        }
        return false;
    }
    
    @Subscribe
    public void PacketReceive(final PacketEvent.Receive event) {
        if (event.getPacket() instanceof class_2708) {
            this._towerTimer.reset();
        }
    }
    
    private boolean verifyStack(final class_1799 stack) {
        return !stack.method_7960() && stack.method_7909() instanceof class_1747;
    }
    
    public enum ValidResult
    {
        NoEntityCollision, 
        AlreadyBlockThere, 
        NoNeighbors, 
        Ok;
        
        private static /* synthetic */ ValidResult[] $values() {
            return new ValidResult[] { ValidResult.NoEntityCollision, ValidResult.AlreadyBlockThere, ValidResult.NoNeighbors, ValidResult.Ok };
        }
        
        static {
            $VALUES = $values();
        }
    }
}
