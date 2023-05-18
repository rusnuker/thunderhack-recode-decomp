/*
 * Decompiled with CFR 0.150.
 */
package com.mrzak34.thunderhack.Hud;

import com.mrzak34.thunderhack.Hud.Hud;
import com.mrzak34.thunderhack.Hud.HudElements.Radar;
import com.mrzak34.thunderhack.Hud.HudMenu;
import java.util.ArrayList;
import java.util.List;

public class HudManager {
    public static ArrayList<Hud> huds;

    public HudManager() {
        huds = new ArrayList();
        huds.clear();
        huds.add(new Radar());
    }

    public Hud getHud(String name) {
        for (Hud h : huds) {
            if (!h.getName().equalsIgnoreCase(name)) continue;
            return h;
        }
        return null;
    }

    public ArrayList<Hud> getHudList() {
        return huds;
    }

    public static List<Hud> getModulesByCategory(HudMenu hudmenu) {
        ArrayList<Hud> huds = new ArrayList<Hud>();
        for (Hud h : HudManager.huds) {
            if (h.getHud() != hudmenu) continue;
            huds.add(h);
        }
        return huds;
    }
}

