package de.luwallia.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(LivingEntity.class)
public class PlayerDamageMixin {

    private static final Random RANDOM = new Random();

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void onPlayerDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity entity = (LivingEntity) (Object) this;

        // Check if the entity is a player
        if (entity instanceof PlayerEntity player) {
            // 50% chance to avoid damage
            if (RANDOM.nextDouble() < 0.5) {
                // Move the player 2 blocks backward
                Vec3d direction = player.getRotationVec(1.0F);
                Vec3d backwardMovement = direction.multiply(-1).normalize().multiply(1.0); // Increase the distance to 2 blocks
                player.addVelocity(backwardMovement.x, 0.3, backwardMovement.z); // Slightly increase the Y-axis for a better leap
                player.velocityModified = true; // Ensure the movement is applied

                // Play a sound when the player dodges
                player.getWorld().playSound(
                        null, // Use null to play the sound for all nearby players
                        player.getX(),
                        player.getY(),
                        player.getZ(),
                        SoundEvents.ENTITY_WITHER_SHOOT, // Choose an appropriate sound, can be changed
                        SoundCategory.PLAYERS, // Play the sound in the player's category
                        0.2F, // Volume (1.0 = normal)
                        2F  // Pitch (1.0 = normal)
                );


                // Cancel the damage
                cir.setReturnValue(false);
            }
        }
    }
}
