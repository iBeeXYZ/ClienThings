package fr.solmey.clienthings.mixin.crystals;

import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.entity.Entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ClientPlayNetworkHandler.class)
public interface ClientPlayNetworkHandlerAccessor {
    @Invoker("createEntity")
    Entity invokeCreateEntity(EntitySpawnS2CPacket packet);
}