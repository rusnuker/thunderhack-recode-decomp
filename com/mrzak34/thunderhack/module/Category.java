//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.module;

public enum Category
{
    COMBAT("Combat", 10, 10, false, true), 
    MISC("Misc", 120, 10, false, true), 
    MOVEMENT("Movement", 230, 10, false, true), 
    RENDER("Render", 340, 10, false, true), 
    CLIENT("Client", 450, 10, false, true);
    
    public String name;
    public int posX;
    public int posY;
    public boolean mouseClicked;
    public boolean showModules;
    public int modules;
    
    private Category(final String name, final int posX, final int posY, final boolean mouseClicked, final boolean showModules) {
        this.modules = 0;
        this.name = name;
        this.posX = posX;
        this.posY = posY;
        this.mouseClicked = mouseClicked;
        this.showModules = showModules;
    }
    
    private static /* synthetic */ Category[] $values() {
        return new Category[] { Category.COMBAT, Category.MISC, Category.MOVEMENT, Category.RENDER, Category.CLIENT };
    }
    
    static {
        $VALUES = $values();
    }
}
