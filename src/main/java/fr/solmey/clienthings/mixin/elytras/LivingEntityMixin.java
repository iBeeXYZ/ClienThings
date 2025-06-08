package fr.solmey.clienthings.mixin.elytras;

import fr.solmey.clienthings.config.Config;

import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Redirect(
        method = "tickGliding",
        at = @At(
            value = "FIELD",
            target = "Lnet/minecraft/world/World;isClient:Z"
        )
    )
    private boolean tickGliding(World world) {
        if (Config.elytras)
            return false;
        else
            return world.isClient;
    }
}