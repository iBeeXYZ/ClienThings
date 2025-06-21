package fr.solmey.clienthings.mixin.crystals;

import net.minecraft.client.network.ClientPlayNetworkHandler;

import fr.solmey.clienthings.config.Config;
import fr.solmey.clienthings.util.Entities;

import net.minecraft.entity.Entity;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.network.packet.s2c.play.ExplosionS2CPacket;
import net.minecraft.network.packet.s2c.play.EntitiesDestroyS2CPacket;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {
	@Inject(method = "onEntitySpawn", at = @At("HEAD"), cancellable = true)
  private void onEntitySpawn(EntitySpawnS2CPacket packet, CallbackInfo info) {
    if (Config.crystals) {
      if(Entities.needToCancel(packet))
        info.cancel();
    }
  }

  @Inject(method = "onEntitiesDestroy", at = @At("HEAD"), cancellable = true)
  private void onEntitiesDestroy(EntitiesDestroyS2CPacket packet, CallbackInfo info) {
    //info.cancel();
  }

	/*@Inject(method = "onExplosion", at = @At("HEAD"), cancellable = true)
  private void onExplosion(ExplosionS2CPacket packet, CallbackInfo info) {
    if (Config.crystals) {
      info.cancel();
    }
  } */
}