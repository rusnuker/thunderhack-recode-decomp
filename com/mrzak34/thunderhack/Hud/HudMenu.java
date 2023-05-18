//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.Hud;

public enum HudMenu
{
    HUD("HUD", 320, 10, false, true);
    
    public String name;
    public int posX;
    public int posY;
    public boolean mouseClicked;
    public boolean showHuds;
    
    private HudMenu(final String name, final int posX, final int posY, final boolean mouseClicked, final boolean showHuds) {
        this.name = name;
        this.posX = posX;
        this.posY = posY;
        this.mouseClicked = mouseClicked;
        this.showHuds = showHuds;
    }
    
    private static /* synthetic */ HudMenu[] $values() {
        return new HudMenu[] { HudMenu.HUD };
    }
    
    static {
        $VALUES = $values();
    }
}
