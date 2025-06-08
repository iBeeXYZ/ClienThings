package fr.solmey.clienthings.mixin.consumables;

import net.minecraft.client.network.ClientPlayNetworkHandler;

import fr.solmey.clienthings.config.Config;
import fr.solmey.clienthings.util.Sounds;

import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.Nullable;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.entity.Entity;

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
    if (Config.consumables && Sounds.needToCancel(packet.getSound().value(), packet.getX(), packet.getY(), packet.getZ()))
      info.cancel();
  }
}