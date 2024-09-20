package org.faz.dvlmultiload.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.faz.dvlmultiload.block.ClimbableBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class LocalPlayerMixin
{
    @Shadow public abstract boolean isLocalPlayer();

    @Inject(method = "tick", at = @At("HEAD"))
    public void tick(CallbackInfo ci)
    {
        var ply = (Player) (Object) this;
        if (isLocalPlayer())
        {
            if (ply.getBlockStateOn().getBlock() instanceof ClimbableBlock climbableBlock)
            {
                ply.fallDistance = 0;
                try
                {
                    var jumping = LivingEntity.class.getDeclaredField("jumping");
                    jumping.setAccessible(true);

                    if (jumping.getBoolean(ply))
                    {
                        var motion = ply.getDeltaMovement();
                        ply.setDeltaMovement(motion.x(), climbableBlock.getClimbSpeed(), motion.z());
                    }
                    else
                    {
                        var motion = ply.getDeltaMovement();
                        if (ply.isCrouching())
                            ply.setDeltaMovement(motion.x(), 0, motion.z());
                        else
                        {
                            ply.setDeltaMovement(motion.x(), -climbableBlock.getClimbSpeed(), motion.z());
                        }
                    }
                    jumping.setAccessible(false);
                }
                catch (NoSuchFieldException | IllegalAccessException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
