package fr.solmey.clienthings.mixin.firework;

import net.minecraft.client.network.ClientPlayerEntity;

import fr.solmey.clienthings.util.Firework;

import net.minecraft.entity.projectile.FireworkRocketEntity;


import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FireworkRocketEntity.class)
public class FireworkRocketEntityMixin {
	@Inject(at = @At("HEAD"), method = "tick")
	private void tick(CallbackInfo info) {
		FireworkRocketEntity fireworkEntity = (FireworkRocketEntity)(Object) this;
		Firework.tick(fireworkEntity);
	}
}