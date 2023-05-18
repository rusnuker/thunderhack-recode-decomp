//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.manager;

import com.mrzak34.thunderhack.module.*;
import java.util.*;
import com.mrzak34.thunderhack.*;

public class ModuleManager
{
    public static ArrayList<Module> modules;
    
    public ModuleManager() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokespecial   java/lang/Object.<init>:()V
        //     4: new             Ljava/util/ArrayList;
        //     7: dup            
        //     8: invokespecial   java/util/ArrayList.<init>:()V
        //    11: dup            
        //    12: putstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //    15: invokevirtual   java/util/ArrayList.clear:()V
        //    18: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //    21: new             new            !!! ERROR
        //    24: dup            
        //    25: invokespecial   invokespecial  !!! ERROR
        //    28: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //    31: pop            
        //    32: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //    35: new             Lcom/mrzak34/thunderhack/module/modules/combat/ArmorBreaker;
        //    38: dup            
        //    39: invokespecial   com/mrzak34/thunderhack/module/modules/combat/ArmorBreaker.<init>:()V
        //    42: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //    45: pop            
        //    46: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //    49: new             Lcom/mrzak34/thunderhack/module/modules/combat/HitAura;
        //    52: dup            
        //    53: invokespecial   com/mrzak34/thunderhack/module/modules/combat/HitAura.<init>:()V
        //    56: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //    59: pop            
        //    60: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //    63: new             Lcom/mrzak34/thunderhack/module/modules/combat/DeadAura;
        //    66: dup            
        //    67: invokespecial   com/mrzak34/thunderhack/module/modules/combat/DeadAura.<init>:()V
        //    70: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //    73: pop            
        //    74: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //    77: new             new            !!! ERROR
        //    80: dup            
        //    81: invokespecial   invokespecial  !!! ERROR
        //    84: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //    87: pop            
        //    88: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //    91: new             Lcom/mrzak34/thunderhack/module/modules/combat/FastProjectile;
        //    94: dup            
        //    95: invokespecial   com/mrzak34/thunderhack/module/modules/combat/FastProjectile.<init>:()V
        //    98: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   101: pop            
        //   102: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   105: new             Lcom/mrzak34/thunderhack/module/modules/combat/AutoCrystalRewrite;
        //   108: dup            
        //   109: invokespecial   com/mrzak34/thunderhack/module/modules/combat/AutoCrystalRewrite.<init>:()V
        //   112: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   115: pop            
        //   116: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   119: new             Lcom/mrzak34/thunderhack/module/modules/combat/Burrow;
        //   122: dup            
        //   123: invokespecial   com/mrzak34/thunderhack/module/modules/combat/Burrow.<init>:()V
        //   126: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   129: pop            
        //   130: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   133: new             Lcom/mrzak34/thunderhack/module/modules/combat/CrystalAura;
        //   136: dup            
        //   137: invokespecial   com/mrzak34/thunderhack/module/modules/combat/CrystalAura.<init>:()V
        //   140: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   143: pop            
        //   144: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   147: new             Lcom/mrzak34/thunderhack/module/modules/combat/HoleBreaker;
        //   150: dup            
        //   151: invokespecial   com/mrzak34/thunderhack/module/modules/combat/HoleBreaker.<init>:()V
        //   154: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   157: pop            
        //   158: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   161: new             Lcom/mrzak34/thunderhack/module/modules/combat/AutoPot;
        //   164: dup            
        //   165: invokespecial   com/mrzak34/thunderhack/module/modules/combat/AutoPot.<init>:()V
        //   168: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   171: pop            
        //   172: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   175: new             Lcom/mrzak34/thunderhack/module/modules/combat/CevBreaker;
        //   178: dup            
        //   179: invokespecial   com/mrzak34/thunderhack/module/modules/combat/CevBreaker.<init>:()V
        //   182: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   185: pop            
        //   186: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   189: new             Lcom/mrzak34/thunderhack/module/modules/combat/BowSpam;
        //   192: dup            
        //   193: invokespecial   com/mrzak34/thunderhack/module/modules/combat/BowSpam.<init>:()V
        //   196: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   199: pop            
        //   200: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   203: new             Lcom/mrzak34/thunderhack/module/modules/combat/TridentSpam;
        //   206: dup            
        //   207: invokespecial   com/mrzak34/thunderhack/module/modules/combat/TridentSpam.<init>:()V
        //   210: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   213: pop            
        //   214: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   217: new             Lcom/mrzak34/thunderhack/module/modules/combat/AutoCrystal;
        //   220: dup            
        //   221: invokespecial   com/mrzak34/thunderhack/module/modules/combat/AutoCrystal.<init>:()V
        //   224: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   227: pop            
        //   228: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   231: new             Lcom/mrzak34/thunderhack/module/modules/combat/Bowbomb;
        //   234: dup            
        //   235: invokespecial   com/mrzak34/thunderhack/module/modules/combat/Bowbomb.<init>:()V
        //   238: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   241: pop            
        //   242: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   245: new             Lcom/mrzak34/thunderhack/module/modules/combat/AutoTotem;
        //   248: dup            
        //   249: invokespecial   com/mrzak34/thunderhack/module/modules/combat/AutoTotem.<init>:()V
        //   252: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   255: pop            
        //   256: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   259: new             Lcom/mrzak34/thunderhack/module/modules/combat/Velocity;
        //   262: dup            
        //   263: invokespecial   com/mrzak34/thunderhack/module/modules/combat/Velocity.<init>:()V
        //   266: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   269: pop            
        //   270: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   273: new             Lcom/mrzak34/thunderhack/module/modules/combat/SelfTrap;
        //   276: dup            
        //   277: invokespecial   com/mrzak34/thunderhack/module/modules/combat/SelfTrap.<init>:()V
        //   280: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   283: pop            
        //   284: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   287: new             new            !!! ERROR
        //   290: dup            
        //   291: invokespecial   invokespecial  !!! ERROR
        //   294: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   297: pop            
        //   298: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   301: new             Lcom/mrzak34/thunderhack/module/modules/misc/AntiPacket;
        //   304: dup            
        //   305: invokespecial   com/mrzak34/thunderhack/module/modules/misc/AntiPacket.<init>:()V
        //   308: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   311: pop            
        //   312: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   315: new             Lcom/mrzak34/thunderhack/module/modules/misc/Freez;
        //   318: dup            
        //   319: invokespecial   com/mrzak34/thunderhack/module/modules/misc/Freez.<init>:()V
        //   322: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   325: pop            
        //   326: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   329: new             Lcom/mrzak34/thunderhack/module/modules/misc/BlockTweaks;
        //   332: dup            
        //   333: invokespecial   com/mrzak34/thunderhack/module/modules/misc/BlockTweaks.<init>:()V
        //   336: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   339: pop            
        //   340: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   343: new             Lcom/mrzak34/thunderhack/module/modules/misc/LiquidPlaceBypass;
        //   346: dup            
        //   347: invokespecial   com/mrzak34/thunderhack/module/modules/misc/LiquidPlaceBypass.<init>:()V
        //   350: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   353: pop            
        //   354: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   357: new             Lcom/mrzak34/thunderhack/module/modules/misc/FpsLimiter;
        //   360: dup            
        //   361: invokespecial   com/mrzak34/thunderhack/module/modules/misc/FpsLimiter.<init>:()V
        //   364: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   367: pop            
        //   368: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   371: new             Lcom/mrzak34/thunderhack/module/modules/misc/Timer;
        //   374: dup            
        //   375: invokespecial   com/mrzak34/thunderhack/module/modules/misc/Timer.<init>:()V
        //   378: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   381: pop            
        //   382: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   385: new             Lcom/mrzak34/thunderhack/module/modules/misc/FastUse;
        //   388: dup            
        //   389: invokespecial   com/mrzak34/thunderhack/module/modules/misc/FastUse.<init>:()V
        //   392: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   395: pop            
        //   396: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   399: new             Lcom/mrzak34/thunderhack/module/modules/misc/DesyncModule;
        //   402: dup            
        //   403: invokespecial   com/mrzak34/thunderhack/module/modules/misc/DesyncModule.<init>:()V
        //   406: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   409: pop            
        //   410: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   413: new             Lcom/mrzak34/thunderhack/module/modules/misc/GodModule;
        //   416: dup            
        //   417: invokespecial   com/mrzak34/thunderhack/module/modules/misc/GodModule.<init>:()V
        //   420: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   423: pop            
        //   424: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   427: new             Lcom/mrzak34/thunderhack/module/modules/misc/EntityDesync;
        //   430: dup            
        //   431: invokespecial   com/mrzak34/thunderhack/module/modules/misc/EntityDesync.<init>:()V
        //   434: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   437: pop            
        //   438: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   441: new             Lcom/mrzak34/thunderhack/module/modules/misc/TeleportExploit;
        //   444: dup            
        //   445: invokespecial   com/mrzak34/thunderhack/module/modules/misc/TeleportExploit.<init>:()V
        //   448: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   451: pop            
        //   452: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   455: new             new            !!! ERROR
        //   458: dup            
        //   459: invokespecial   invokespecial  !!! ERROR
        //   462: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   465: pop            
        //   466: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   469: new             Lcom/mrzak34/thunderhack/module/modules/misc/FakePlayer;
        //   472: dup            
        //   473: invokespecial   com/mrzak34/thunderhack/module/modules/misc/FakePlayer.<init>:()V
        //   476: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   479: pop            
        //   480: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   483: new             new            !!! ERROR
        //   486: dup            
        //   487: invokespecial   invokespecial  !!! ERROR
        //   490: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   493: pop            
        //   494: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   497: new             new            !!! ERROR
        //   500: dup            
        //   501: invokespecial   invokespecial  !!! ERROR
        //   504: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   507: pop            
        //   508: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   511: new             Lcom/mrzak34/thunderhack/module/modules/misc/OffhandCrash;
        //   514: dup            
        //   515: invokespecial   com/mrzak34/thunderhack/module/modules/misc/OffhandCrash.<init>:()V
        //   518: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   521: pop            
        //   522: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   525: new             Lcom/mrzak34/thunderhack/module/modules/movement/SafeWalk;
        //   528: dup            
        //   529: invokespecial   com/mrzak34/thunderhack/module/modules/movement/SafeWalk.<init>:()V
        //   532: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   535: pop            
        //   536: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   539: new             Lcom/mrzak34/thunderhack/module/modules/movement/AutoFlyme;
        //   542: dup            
        //   543: invokespecial   com/mrzak34/thunderhack/module/modules/movement/AutoFlyme.<init>:()V
        //   546: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   549: pop            
        //   550: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   553: new             new            !!! ERROR
        //   556: dup            
        //   557: invokespecial   invokespecial  !!! ERROR
        //   560: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   563: pop            
        //   564: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   567: new             Lcom/mrzak34/thunderhack/module/modules/movement/ScaffoldSalhack;
        //   570: dup            
        //   571: invokespecial   com/mrzak34/thunderhack/module/modules/movement/ScaffoldSalhack.<init>:()V
        //   574: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   577: pop            
        //   578: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   581: new             Lcom/mrzak34/thunderhack/module/modules/movement/AntiHunger;
        //   584: dup            
        //   585: invokespecial   com/mrzak34/thunderhack/module/modules/movement/AntiHunger.<init>:()V
        //   588: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   591: pop            
        //   592: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   595: new             Lcom/mrzak34/thunderhack/module/modules/movement/PearlBait;
        //   598: dup            
        //   599: invokespecial   com/mrzak34/thunderhack/module/modules/movement/PearlBait.<init>:()V
        //   602: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   605: pop            
        //   606: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   609: new             Lcom/mrzak34/thunderhack/module/modules/movement/InventoryMove;
        //   612: dup            
        //   613: invokespecial   com/mrzak34/thunderhack/module/modules/movement/InventoryMove.<init>:()V
        //   616: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   619: pop            
        //   620: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   623: new             Lcom/mrzak34/thunderhack/module/modules/misc/XCarry;
        //   626: dup            
        //   627: invokespecial   com/mrzak34/thunderhack/module/modules/misc/XCarry.<init>:()V
        //   630: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   633: pop            
        //   634: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   637: new             new            !!! ERROR
        //   640: dup            
        //   641: invokespecial   invokespecial  !!! ERROR
        //   644: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   647: pop            
        //   648: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   651: new             new            !!! ERROR
        //   654: dup            
        //   655: invokespecial   invokespecial  !!! ERROR
        //   658: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   661: pop            
        //   662: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   665: new             Lcom/mrzak34/thunderhack/module/modules/movement/PacketFly;
        //   668: dup            
        //   669: invokespecial   com/mrzak34/thunderhack/module/modules/movement/PacketFly.<init>:()V
        //   672: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   675: pop            
        //   676: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   679: new             Lcom/mrzak34/thunderhack/module/modules/movement/LongJump;
        //   682: dup            
        //   683: invokespecial   com/mrzak34/thunderhack/module/modules/movement/LongJump.<init>:()V
        //   686: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   689: pop            
        //   690: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   693: new             Lcom/mrzak34/thunderhack/module/modules/movement/Flight;
        //   696: dup            
        //   697: invokespecial   com/mrzak34/thunderhack/module/modules/movement/Flight.<init>:()V
        //   700: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   703: pop            
        //   704: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   707: new             Lcom/mrzak34/thunderhack/module/modules/movement/ChorusControl;
        //   710: dup            
        //   711: invokespecial   com/mrzak34/thunderhack/module/modules/movement/ChorusControl.<init>:()V
        //   714: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   717: pop            
        //   718: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   721: new             Lcom/mrzak34/thunderhack/module/modules/movement/Boost;
        //   724: dup            
        //   725: invokespecial   com/mrzak34/thunderhack/module/modules/movement/Boost.<init>:()V
        //   728: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   731: pop            
        //   732: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   735: new             Lcom/mrzak34/thunderhack/module/modules/movement/PreElytraFly;
        //   738: dup            
        //   739: invokespecial   com/mrzak34/thunderhack/module/modules/movement/PreElytraFly.<init>:()V
        //   742: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   745: pop            
        //   746: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   749: new             Lcom/mrzak34/thunderhack/module/modules/movement/ElytraFly;
        //   752: dup            
        //   753: invokespecial   com/mrzak34/thunderhack/module/modules/movement/ElytraFly.<init>:()V
        //   756: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   759: pop            
        //   760: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   763: new             Lcom/mrzak34/thunderhack/module/modules/movement/NoSlow;
        //   766: dup            
        //   767: invokespecial   com/mrzak34/thunderhack/module/modules/movement/NoSlow.<init>:()V
        //   770: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   773: pop            
        //   774: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   777: new             Lcom/mrzak34/thunderhack/module/modules/movement/Sprint;
        //   780: dup            
        //   781: invokespecial   com/mrzak34/thunderhack/module/modules/movement/Sprint.<init>:()V
        //   784: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   787: pop            
        //   788: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   791: new             Lcom/mrzak34/thunderhack/module/modules/render/Tracers;
        //   794: dup            
        //   795: invokespecial   com/mrzak34/thunderhack/module/modules/render/Tracers.<init>:()V
        //   798: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   801: pop            
        //   802: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   805: new             new            !!! ERROR
        //   808: dup            
        //   809: invokespecial   invokespecial  !!! ERROR
        //   812: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   815: pop            
        //   816: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   819: new             Lcom/mrzak34/thunderhack/module/modules/render/DamageTint;
        //   822: dup            
        //   823: invokespecial   com/mrzak34/thunderhack/module/modules/render/DamageTint.<init>:()V
        //   826: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   829: pop            
        //   830: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   833: new             Lcom/mrzak34/thunderhack/module/modules/render/ContainerPrieview;
        //   836: dup            
        //   837: invokespecial   com/mrzak34/thunderhack/module/modules/render/ContainerPrieview.<init>:()V
        //   840: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   843: pop            
        //   844: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   847: new             Lcom/mrzak34/thunderhack/module/modules/render/PopRender;
        //   850: dup            
        //   851: invokespecial   com/mrzak34/thunderhack/module/modules/render/PopRender.<init>:()V
        //   854: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   857: pop            
        //   858: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   861: new             new            !!! ERROR
        //   864: dup            
        //   865: invokespecial   invokespecial  !!! ERROR
        //   868: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   871: pop            
        //   872: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   875: new             Lcom/mrzak34/thunderhack/module/modules/render/FullBright;
        //   878: dup            
        //   879: invokespecial   com/mrzak34/thunderhack/module/modules/render/FullBright.<init>:()V
        //   882: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   885: pop            
        //   886: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   889: new             Lcom/mrzak34/thunderhack/module/modules/render/Chams;
        //   892: dup            
        //   893: invokespecial   com/mrzak34/thunderhack/module/modules/render/Chams.<init>:()V
        //   896: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   899: pop            
        //   900: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   903: new             new            !!! ERROR
        //   906: dup            
        //   907: invokespecial   invokespecial  !!! ERROR
        //   910: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   913: pop            
        //   914: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   917: new             Lcom/mrzak34/thunderhack/module/modules/render/NoRender;
        //   920: dup            
        //   921: invokespecial   com/mrzak34/thunderhack/module/modules/render/NoRender.<init>:()V
        //   924: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   927: pop            
        //   928: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   931: new             Lcom/mrzak34/thunderhack/module/modules/render/WebESP;
        //   934: dup            
        //   935: invokespecial   com/mrzak34/thunderhack/module/modules/render/WebESP.<init>:()V
        //   938: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   941: pop            
        //   942: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   945: new             Lcom/mrzak34/thunderhack/module/modules/client/ClickGUIModule;
        //   948: dup            
        //   949: invokespecial   com/mrzak34/thunderhack/module/modules/client/ClickGUIModule.<init>:()V
        //   952: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   955: pop            
        //   956: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   959: new             Lcom/mrzak34/thunderhack/module/modules/client/HudEditorModule;
        //   962: dup            
        //   963: invokespecial   com/mrzak34/thunderhack/module/modules/client/HudEditorModule.<init>:()V
        //   966: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   969: pop            
        //   970: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   973: new             Lcom/mrzak34/thunderhack/module/modules/client/Notifications;
        //   976: dup            
        //   977: invokespecial   com/mrzak34/thunderhack/module/modules/client/Notifications.<init>:()V
        //   980: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   983: pop            
        //   984: getstatic       com/mrzak34/thunderhack/manager/ModuleManager.modules:Ljava/util/ArrayList;
        //   987: new             Lcom/mrzak34/thunderhack/module/modules/misc/PacketLogger;
        //   990: dup            
        //   991: invokespecial   com/mrzak34/thunderhack/module/modules/misc/PacketLogger.<init>:()V
        //   994: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   997: pop            
        //   998: return         
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
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createConstructor(AstBuilder.java:713)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:549)
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
    
    public Module getModule(final String name) {
        for (final Module m : ModuleManager.modules) {
            if (m.getName().equalsIgnoreCase(name)) {
                return m;
            }
        }
        return null;
    }
    
    public ArrayList<Module> getModuleList() {
        return ModuleManager.modules;
    }
    
    public static List<Module> getModulesByCategory(final Category c) {
        final List<Module> module = new ArrayList<Module>();
        final ModuleManager moduleManager = Main.moduleManager;
        for (final Module m : ModuleManager.modules) {
            if (m.getCategory() == c) {
                ModuleManager.modules.add(m);
            }
        }
        return ModuleManager.modules;
    }
}
