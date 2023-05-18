/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package com.mrzak34.thunderhack.Account;

import com.mrzak34.thunderhack.Account.Account;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import org.jetbrains.annotations.NotNull;

public class OfflineAccount
implements Account {
    private final String name;
    private final UUID uuid;

    public OfflineAccount(String name, UUID uuid) {
        this.name = name;
        this.uuid = uuid;
    }

    @Override
    @NotNull
    public UUID uuid() {
        return this.uuid;
    }

    @Override
    @NotNull
    public String name() {
        return this.name;
    }

    @Override
    @NotNull
    public @NotNull CompletableFuture<@NotNull Account.AuthData> login(@NotNull @NotNull BiConsumer<@NotNull String, @NotNull Object[]> progressHandler) {
        return CompletableFuture.completedFuture(new Account.AuthData(this.name(), this.uuid(), "0", "legacy"));
    }
}

