package org.faz.dvlmultiload.block;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.state.BlockState;

public class ClimbableBlock extends Block
{
    private final double climbSpeed;
    private final SoundEvent climbSound;
    public ClimbableBlock(double climbSpeed, SoundEvent SE, Properties properties)
    {
        super(properties);
        this.climbSpeed = climbSpeed;
        this.climbSound = SE;
    }

    public double getClimbSpeed()
    {
        return climbSpeed;
    }

    public SoundEvent getClimbSound()
    {
        if (climbSound == null)
            return SoundEvents.LADDER_STEP;
        return climbSound;
    }
}
