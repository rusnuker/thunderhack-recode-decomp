//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.Hud;

import com.mrzak34.thunderhack.Hud.HudElements.*;
import java.util.*;
import com.mrzak34.thunderhack.*;

public class HudManager
{
    public static ArrayList<Hud> huds;
    
    public HudManager() {
        (HudManager.huds = new ArrayList<Hud>()).clear();
        HudManager.huds.add((Hud)new Radar());
    }
    
    public Hud getHud(final String name) {
        for (final Hud h : HudManager.huds) {
            if (h.getName().equalsIgnoreCase(name)) {
                return h;
            }
        }
        return null;
    }
    
    public ArrayList<Hud> getHudList() {
        return HudManager.huds;
    }
    
    public static List<Hud> getModulesByCategory(final HudMenu hudmenu) {
        final List<Hud> huds = new ArrayList<Hud>();
        final HudManager hudManager = Main.hudManager;
        for (final Hud h : HudManager.huds) {
            if (h.getHud() == hudmenu) {
                huds.add(h);
            }
        }
        return huds;
    }
}
