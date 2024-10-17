package de.luwallia.cookie.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class JumpBackItem extends Item {
    public JumpBackItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (!world.isClient) {
            // Get the player's facing direction
            Vec3d direction = player.getRotationVec(1.0F);

            // Calculate the opposite direction for backward movement
            Vec3d backwardMovement = direction.multiply(-1).normalize().multiply(0.5);

            // Apply the velocity to the player
            player.addVelocity(backwardMovement.x, 0.2, backwardMovement.z);
            player.velocityModified = true; // Ensure the movement is applied

            // Optionally, you can play a sound or show particles here
        }

        return TypedActionResult.success(player.getStackInHand(hand));
    }
}
