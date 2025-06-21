package fr.solmey.clienthings.mixin.firework;

import net.minecraft.client.network.ClientPlayerEntity;

import fr.solmey.clienthings.util.Entities;

import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.entity.Entity;


import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FireworkRocketEntity.class)
public class FireworkRocketEntityMixin {
	@Inject(at = @At("HEAD"), method = "tick")
	private void tick(CallbackInfo info) {
		FireworkRocketEntity fireworkEntity = (FireworkRocketEntity)(Object) this;
        FireworkRocketEntityAccessor accessor = (FireworkRocketEntityAccessor) fireworkEntity;
        if(accessor.getLife() > accessor.getLifeTime() && accessor.getLifeTime() != 0)
        	Entities.remove(fireworkEntity, Entity.RemovalReason.DISCARDED); //.discard();   fireworkEntity.explodeAndRemove(world); in vanilla but doesn't work with
	}
}