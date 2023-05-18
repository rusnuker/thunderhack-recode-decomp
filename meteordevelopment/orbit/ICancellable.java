//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package meteordevelopment.orbit;

public interface ICancellable
{
    void setCancelled(final boolean p0);
    
    default void cancel() {
        this.setCancelled(true);
    }
    
    boolean isCancelled();
}
