//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package meteordevelopment.orbit.listeners;

public interface IListener
{
    void call(final Object p0);
    
    Class<?> getTarget();
    
    int getPriority();
    
    boolean isStatic();
}
