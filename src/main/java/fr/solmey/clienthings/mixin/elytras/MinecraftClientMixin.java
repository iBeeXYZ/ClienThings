package fr.solmey.clienthings.mixin.elytras;

import net.minecraft.client.MinecraftClient;

import fr.solmey.clienthings.config.Config;
import fr.solmey.clienthings.util.Elytras;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
class MinecraftClientMixin {

  @Inject(method = "onDisconnected", at = @At("HEAD"), cancellable = true)
  private void onDisconnected(CallbackInfo info) {
    if(Config.elytras) {
      Elytras.bypass = true;
      Elytras.bypass2 = true;
    }
  }
}