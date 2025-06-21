package fr.solmey.clienthings.mixin.elytras;

import net.minecraft.client.network.ClientPlayerEntity;

import fr.solmey.clienthings.config.Config;
import fr.solmey.clienthings.util.Elytras;
import fr.solmey.clienthings.mixin.elytras.EntityAccessor;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;

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
		if (Config.elytras) {
			if(Elytras.setFalseFlag == true) {
				ClientPlayerEntity player = MinecraftClient.getInstance().player;
				Elytras.setFalseFlag = false;
				((EntityAccessor)player).invokeSetFlag(Elytras.GLIDING_FLAG_INDEX, false);
			}
		}
	}
}