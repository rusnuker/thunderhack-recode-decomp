/*
 * Decompiled with CFR 0.150.
 */
package meteordevelopment.orbit;

public interface ICancellable {
    public void setCancelled(boolean var1);

    default public void cancel() {
        this.setCancelled(true);
    }

    public boolean isCancelled();
}

