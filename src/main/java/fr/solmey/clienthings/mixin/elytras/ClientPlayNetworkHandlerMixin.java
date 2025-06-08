package fr.solmey.clienthings.mixin.elytras;

import net.minecraft.client.network.ClientPlayNetworkHandler;

import fr.solmey.clienthings.config.Config;
import fr.solmey.clienthings.util.Elytras;

import java.util.List;
import java.util.ArrayList;

import net.minecraft.entity.Entity;
import net.minecraft.network.packet.s2c.play.EntityTrackerUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.GameJoinS2CPacket;
import net.minecraft.entity.data.TrackedDataHandler;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.MinecraftClient;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {

    private static final int FLAGS_INDEX = 0;
    private static final int GLIDING_FLAG_INDEX = 7;

    @ModifyVariable(
        method = "onEntityTrackerUpdate(Lnet/minecraft/network/packet/s2c/play/EntityTrackerUpdateS2CPacket;)V",
        at = @At("HEAD"),
        argsOnly = true
    )
    private EntityTrackerUpdateS2CPacket modifierPacket(EntityTrackerUpdateS2CPacket originalPacket) {
      if (Config.elytras) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        
        if(player != null) {
          Entity entity = player.getWorld().getEntityById(originalPacket.id());
          if(player == entity) {
            List<DataTracker.SerializedEntry<?>> newEntries = new ArrayList();
            for (DataTracker.SerializedEntry<?> entry : originalPacket.trackedValues()) {
              if (entry.id() == FLAGS_INDEX) {

                Byte oldFlags = (Byte) entry.value();
                Byte newFlags = oldFlags;

                if(!Elytras.bypass && !Elytras.bypass2) {
                  if (player.isGliding())
                      newFlags = (byte)(oldFlags | (1 << GLIDING_FLAG_INDEX));
                  else
                      newFlags = (byte)(oldFlags & ~(1 << GLIDING_FLAG_INDEX));
                }
                else {
                  if(Elytras.bypass)
                    Elytras.bypass = false;
                  else
                    Elytras.bypass2 = false;
                }

                TrackedDataHandler<Byte> handler = (TrackedDataHandler<Byte>) entry.handler();
                DataTracker.SerializedEntry<Byte> newEntry = new DataTracker.SerializedEntry<>(
                    entry.id(),
                    handler,
                    newFlags
                );

                newEntries.add(newEntry);
              }
              else {
                newEntries.add(entry);
              }
            }

            EntityTrackerUpdateS2CPacket copyPacket = new EntityTrackerUpdateS2CPacket(
              originalPacket.id(),
              new ArrayList<>(newEntries)
            );
            return copyPacket;
          }
        }
      }
      return originalPacket;
    }
}

@Mixin(ClientPlayNetworkHandler.class)
class ClientPlayNetworkHandlerMixinGameJoin {

  @Inject(method = "onGameJoin", at = @At("HEAD"), cancellable = true)
  private void onGameJoin(GameJoinS2CPacket packet, CallbackInfo info) {
    if(Config.elytras) {
      Elytras.bypass = true;
      Elytras.bypass2 = true;
    }
  }
}