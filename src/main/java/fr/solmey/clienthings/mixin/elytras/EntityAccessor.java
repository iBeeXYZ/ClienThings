package fr.solmey.clienthings.mixin.elytras;


import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;


@Mixin(Entity.class)
public interface EntityAccessor {
    @Invoker("setFlag")
    void invokeSetFlag(int index, boolean value);
}
