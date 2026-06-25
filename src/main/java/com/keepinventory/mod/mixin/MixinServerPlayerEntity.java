package com.keepinventory.mod.mixin;

import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin ini mencegat method dropInventory() pada ServerPlayerEntity.
 * Ketika player mati, game memanggil dropInventory() untuk menjatuhkan semua item.
 * Dengan mem-cancel method ini, semua item tetap di inventory player.
 *
 * Berlaku untuk: Minecraft Java 1.21.1 + Fabric
 */
@Mixin(ServerPlayerEntity.class)
public class MixinServerPlayerEntity {

    @Inject(at = @At("HEAD"), method = "dropInventory", cancellable = true)
    private void keepInventoryOnDeath(CallbackInfo ci) {
        // Cancel drop — item tidak jatuh saat mati
        ci.cancel();
    }
}
