package fr.solmey.clienthings.mixin.firework;

import net.minecraft.entity.projectile.FireworkRocketEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(FireworkRocketEntity.class)
public interface FireworkRocketEntityAccessor {
    @Accessor("life")
    int getLife();

    @Accessor("lifeTime")
    int getLifeTime();
}