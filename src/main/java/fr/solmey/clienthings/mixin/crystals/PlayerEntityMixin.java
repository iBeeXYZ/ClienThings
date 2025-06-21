package fr.solmey.clienthings.mixin.crystals;

import net.minecraft.entity.player.PlayerEntity;

import fr.solmey.clienthings.config.Config;
import fr.solmey.clienthings.util.Entities;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Inject(method = "resetLastAttackedTicks", at = @At("HEAD"), cancellable = true)
    private void resetLastAttackedTicks(CallbackInfo info) {
        if (Config.crystals) {
            PlayerEntity player = (PlayerEntity) (Object) this;
            Entities.attackCooldownProgress = player.getAttackCooldownProgress(0.5F);
        }
    }
}