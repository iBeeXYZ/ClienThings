package fr.solmey.clienthings.mixin.firework;

import net.minecraft.client.network.ClientPlayNetworkHandler;

import fr.solmey.clienthings.config.Config;
import fr.solmey.clienthings.util.Sounds;
import fr.solmey.clienthings.util.Firework;

import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {
	@Inject(method = "onPlaySound", at = @At("HEAD"), cancellable = true)
  private void onPlaySound(PlaySoundS2CPacket packet, CallbackInfo info) {
    if (Config.firework && Sounds.needToCancel(packet.getSound().value(), packet.getX(), packet.getY(), packet.getZ()))
      info.cancel();
  }

  @Inject(method = "onEntitySpawn", at = @At("HEAD"), cancellable = true)
  private void onEntity(EntitySpawnS2CPacket packet, CallbackInfo info) {
    if (Config.firework && Firework.needToCancel(packet))
      info.cancel();
  }
}