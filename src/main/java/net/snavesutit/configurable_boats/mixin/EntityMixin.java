package net.snavesutit.configurable_boats.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.Box;
import net.snavesutit.configurable_boats.config.Config;
import net.snavesutit.configurable_boats.config.ConfigManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @Shadow @Final private EntityType<?> type;

		@Shadow public abstract double getX();

    @Shadow public abstract double getY();

    @Shadow public abstract double getZ();

    @Inject(at = @At("HEAD"), method = "getBoundingBox", cancellable = true)
    private void getBoundingBox(CallbackInfoReturnable<Box> cir) {
        if (this.type == EntityType.BOAT) {
            Config config = ConfigManager.getConfig();
            float boxSizeX = config.collisionBoxSizeX / 2;
            float boxSizeY = config.collisionBoxSizeY;
            float boxSizeZ = config.collisionBoxSizeZ / 2;

            cir.setReturnValue(new Box(
                    this.getX() - boxSizeX,this.getY(),this.getZ() - boxSizeZ,
                    this.getX() + boxSizeX,this.getY() + boxSizeY,this.getZ() + boxSizeZ
            ));
            cir.cancel();
        }
    }
}