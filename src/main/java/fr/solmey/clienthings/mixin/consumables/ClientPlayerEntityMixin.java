package fr.solmey.clienthings.mixin.consumables;

import net.minecraft.client.network.ClientPlayerEntity;

import fr.solmey.clienthings.util.Sounds;
import fr.solmey.clienthings.config.Config;

import java.util.List;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.Consumable;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.consume.TeleportRandomlyConsumeEffect;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.consume.ConsumeEffect;
import net.minecraft.item.consume.PlaySoundConsumeEffect;
import net.minecraft.item.consume.UseAction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.math.MathHelper;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

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
		if (Config.consumables) {
			ClientPlayerEntity player = (ClientPlayerEntity)(Object) this;

			ItemStack itemStack = player.getActiveItem();
			ConsumableComponent consumableComponent = itemStack.get(DataComponentTypes.CONSUMABLE);

			if(player.getItemUseTimeLeft() <= 0 && consumableComponent != null && itemStack != null) {

				SoundEvent finishSound = null;
				for (ConsumeEffect effect : consumableComponent.onConsumeEffects()) {
					if (effect instanceof PlaySoundConsumeEffect soundEffect) {
						finishSound = soundEffect.sound().value();
					}
					if (!(effect instanceof TeleportRandomlyConsumeEffect)) {
						effect.onConsume(player.getWorld(), itemStack, player);
					}
				}

				// FoodComponent
				// https://bugs.mojang.com/browse/MC-188359 btw
				// https://report.bugs.mojang.com/servicedesk/customer/portal/2/MC-188359
				Random random = player.getRandom();
				player.getWorld().playSound(player, player.getX(), player.getY(), player.getZ(), consumableComponent.sound().value(), SoundCategory.NEUTRAL, 1.0F, random.nextTriangular(1.0F, 0.4F));
				Sounds.set(System.currentTimeMillis(), player.getX(), player.getY(), player.getZ(), consumableComponent.sound().value(), Sounds.CONSUMABLES);
				if(consumableComponent.useAction() == UseAction.EAT || itemStack.getItem() == Items.HONEY_BOTTLE)
					player.getWorld().playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, MathHelper.nextBetween(random, 0.9F, 1.0F));
					Sounds.set(System.currentTimeMillis(), player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_PLAYER_BURP, Sounds.CONSUMABLES);
				if(finishSound != null) {
					player.getWorld().playSound(player, player.getX(), player.getY(), player.getZ(), finishSound, player.getSoundCategory(), 1.0F, 1.0F);
					Sounds.set(System.currentTimeMillis(), player.getX(), player.getY(), player.getZ(), finishSound, Sounds.CONSUMABLES);
				}

				//Last thing
				((LivingEntityInvoker) player).invokeConsumeItem();
			}
		}
	}
}

@Mixin(LivingEntity.class)
interface LivingEntityInvoker {

    @Invoker("consumeItem")
    void invokeConsumeItem();
}