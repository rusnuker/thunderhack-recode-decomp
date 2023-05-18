/*
 * Decompiled with CFR 0.150.
 */
package com.mrzak34.thunderhack.manager;

import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.module.modules.client.ClickGUIModule;
import com.mrzak34.thunderhack.module.modules.client.HudEditorModule;
import com.mrzak34.thunderhack.module.modules.client.Notifications;
import com.mrzak34.thunderhack.module.modules.combat.ArmorBreaker;
import com.mrzak34.thunderhack.module.modules.combat.AutoBuff;
import com.mrzak34.thunderhack.module.modules.combat.AutoCrystal;
import com.mrzak34.thunderhack.module.modules.combat.AutoCrystalRewrite;
import com.mrzak34.thunderhack.module.modules.combat.AutoPot;
import com.mrzak34.thunderhack.module.modules.combat.AutoTotem;
import com.mrzak34.thunderhack.module.modules.combat.BowSpam;
import com.mrzak34.thunderhack.module.modules.combat.Bowbomb;
import com.mrzak34.thunderhack.module.modules.combat.Burrow;
import com.mrzak34.thunderhack.module.modules.combat.CevBreaker;
import com.mrzak34.thunderhack.module.modules.combat.CrystalAura;
import com.mrzak34.thunderhack.module.modules.combat.DeadAura;
import com.mrzak34.thunderhack.module.modules.combat.FastProjectile;
import com.mrzak34.thunderhack.module.modules.combat.HitAura;
import com.mrzak34.thunderhack.module.modules.combat.HoleBreaker;
import com.mrzak34.thunderhack.module.modules.combat.SelfTrap;
import com.mrzak34.thunderhack.module.modules.combat.TickBase;
import com.mrzak34.thunderhack.module.modules.combat.TridentSpam;
import com.mrzak34.thunderhack.module.modules.combat.Velocity;
import com.mrzak34.thunderhack.module.modules.misc.AntiPacket;
import com.mrzak34.thunderhack.module.modules.misc.AutoSign;
import com.mrzak34.thunderhack.module.modules.misc.BlockTweaks;
import com.mrzak34.thunderhack.module.modules.misc.DesyncModule;
import com.mrzak34.thunderhack.module.modules.misc.EntityDesync;
import com.mrzak34.thunderhack.module.modules.misc.FakePlayer;
import com.mrzak34.thunderhack.module.modules.misc.FastUse;
import com.mrzak34.thunderhack.module.modules.misc.FpsLimiter;
import com.mrzak34.thunderhack.module.modules.misc.Freez;
import com.mrzak34.thunderhack.module.modules.misc.GodModule;
import com.mrzak34.thunderhack.module.modules.misc.LiquidPlaceBypass;
import com.mrzak34.thunderhack.module.modules.misc.MiddleClickFriends;
import com.mrzak34.thunderhack.module.modules.misc.OffhandCrash;
import com.mrzak34.thunderhack.module.modules.misc.PacketLogger;
import com.mrzak34.thunderhack.module.modules.misc.TabScanner;
import com.mrzak34.thunderhack.module.modules.misc.TeleportExploit;
import com.mrzak34.thunderhack.module.modules.misc.TestModule;
import com.mrzak34.thunderhack.module.modules.misc.Timer;
import com.mrzak34.thunderhack.module.modules.misc.XCarry;
import com.mrzak34.thunderhack.module.modules.movement.AntiHunger;
import com.mrzak34.thunderhack.module.modules.movement.AutoFlyme;
import com.mrzak34.thunderhack.module.modules.movement.Boost;
import com.mrzak34.thunderhack.module.modules.movement.ChorusControl;
import com.mrzak34.thunderhack.module.modules.movement.ElytraFly;
import com.mrzak34.thunderhack.module.modules.movement.Flight;
import com.mrzak34.thunderhack.module.modules.movement.HClip;
import com.mrzak34.thunderhack.module.modules.movement.InventoryMove;
import com.mrzak34.thunderhack.module.modules.movement.LongJump;
import com.mrzak34.thunderhack.module.modules.movement.NoSlow;
import com.mrzak34.thunderhack.module.modules.movement.PacketFly;
import com.mrzak34.thunderhack.module.modules.movement.PearlBait;
import com.mrzak34.thunderhack.module.modules.movement.PreElytraFly;
import com.mrzak34.thunderhack.module.modules.movement.SafeWalk;
import com.mrzak34.thunderhack.module.modules.movement.ScaffoldRH;
import com.mrzak34.thunderhack.module.modules.movement.ScaffoldSalhack;
import com.mrzak34.thunderhack.module.modules.movement.Sprint;
import com.mrzak34.thunderhack.module.modules.movement.VClip;
import com.mrzak34.thunderhack.module.modules.render.Chams;
import com.mrzak34.thunderhack.module.modules.render.ChorusTracker;
import com.mrzak34.thunderhack.module.modules.render.ContainerPrieview;
import com.mrzak34.thunderhack.module.modules.render.DamageTint;
import com.mrzak34.thunderhack.module.modules.render.FullBright;
import com.mrzak34.thunderhack.module.modules.render.GhostRender;
import com.mrzak34.thunderhack.module.modules.render.Nametags;
import com.mrzak34.thunderhack.module.modules.render.NoRender;
import com.mrzak34.thunderhack.module.modules.render.PopRender;
import com.mrzak34.thunderhack.module.modules.render.Tracers;
import com.mrzak34.thunderhack.module.modules.render.WebESP;
import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    public static ArrayList<Module> modules;

    public ModuleManager() {
        modules = new ArrayList();
        modules.clear();
        modules.add(new TickBase());
        modules.add(new ArmorBreaker());
        modules.add(new HitAura());
        modules.add(new DeadAura());
        modules.add(new AutoBuff());
        modules.add(new FastProjectile());
        modules.add(new AutoCrystalRewrite());
        modules.add(new Burrow());
        modules.add(new CrystalAura());
        modules.add(new HoleBreaker());
        modules.add(new AutoPot());
        modules.add(new CevBreaker());
        modules.add(new BowSpam());
        modules.add(new TridentSpam());
        modules.add(new AutoCrystal());
        modules.add(new Bowbomb());
        modules.add(new AutoTotem());
        modules.add(new Velocity());
        modules.add(new SelfTrap());
        modules.add(new TabScanner());
        modules.add(new AntiPacket());
        modules.add(new Freez());
        modules.add(new BlockTweaks());
        modules.add(new LiquidPlaceBypass());
        modules.add(new FpsLimiter());
        modules.add(new Timer());
        modules.add(new FastUse());
        modules.add(new DesyncModule());
        modules.add(new GodModule());
        modules.add(new EntityDesync());
        modules.add(new TeleportExploit());
        modules.add(new AutoSign());
        modules.add(new FakePlayer());
        modules.add(new MiddleClickFriends());
        modules.add(new TestModule());
        modules.add(new OffhandCrash());
        modules.add(new SafeWalk());
        modules.add(new AutoFlyme());
        modules.add(new ScaffoldRH());
        modules.add(new ScaffoldSalhack());
        modules.add(new AntiHunger());
        modules.add(new PearlBait());
        modules.add(new InventoryMove());
        modules.add(new XCarry());
        modules.add(new HClip());
        modules.add(new VClip());
        modules.add(new PacketFly());
        modules.add(new LongJump());
        modules.add(new Flight());
        modules.add(new ChorusControl());
        modules.add(new Boost());
        modules.add(new PreElytraFly());
        modules.add(new ElytraFly());
        modules.add(new NoSlow());
        modules.add(new Sprint());
        modules.add(new Tracers());
        modules.add(new ChorusTracker());
        modules.add(new DamageTint());
        modules.add(new ContainerPrieview());
        modules.add(new PopRender());
        modules.add(new GhostRender());
        modules.add(new FullBright());
        modules.add(new Chams());
        modules.add(new Nametags());
        modules.add(new NoRender());
        modules.add(new WebESP());
        modules.add(new ClickGUIModule());
        modules.add(new HudEditorModule());
        modules.add(new Notifications());
        modules.add(new PacketLogger());
    }

    public Module getModule(String name) {
        for (Module m : modules) {
            if (!m.getName().equalsIgnoreCase(name)) continue;
            return m;
        }
        return null;
    }

    public ArrayList<Module> getModuleList() {
        return modules;
    }

    public static List<Module> getModulesByCategory(Category c) {
        ArrayList module = new ArrayList();
        for (Module m : modules) {
            if (m.getCategory() != c) continue;
            modules.add(m);
        }
        return modules;
    }
}

