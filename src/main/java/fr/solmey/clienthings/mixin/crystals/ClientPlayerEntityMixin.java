package fr.solmey.clienthings.mixin.crystals;

import net.minecraft.client.network.ClientPlayerEntity;

import fr.solmey.clienthings.config.Config;
import fr.solmey.clienthings.util.Entities;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {
	@Inject(at = @At("HEAD"), method = "tick")
	private void tick(CallbackInfo info) {
		if (Config.crystals) {
			Entities.clear();
		}
	}
}