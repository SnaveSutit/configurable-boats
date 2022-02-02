package net.snavesutit.configurable_boats.mixin;

import net.minecraft.entity.vehicle.BoatEntity;
import net.snavesutit.configurable_boats.config.Config;
import net.snavesutit.configurable_boats.config.ConfigManager;
import org.apache.commons.lang3.ObjectUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BoatEntity.class)
public class BoatEntityMixin {

	@Inject(at = @At("HEAD"), method = "isCollidable", cancellable = true)
	public void isCollidable(CallbackInfoReturnable<Boolean> callbackInfo) {
		Config config = ConfigManager.getConfig();
		callbackInfo.setReturnValue(config.enableHardCollisions);
	}

	@Inject(at = @At("HEAD"), method = "canCollide", cancellable = true)
	private static void canCollide(CallbackInfoReturnable<Boolean> callbackInfo) {
		Config config = ConfigManager.getConfig();
		boolean enabled = false;
		try {
			enabled = config.enableHardCollisions;
		} catch (NullPointerException e) {}
		callbackInfo.setReturnValue(enabled);
	}

}