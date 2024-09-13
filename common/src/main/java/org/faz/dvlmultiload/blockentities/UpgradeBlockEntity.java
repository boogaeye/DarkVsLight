package org.faz.dvlmultiload.blockentities;

import dev.architectury.fluid.FluidStack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import org.faz.dvlmultiload.Factory.FactoryManager;
import org.faz.dvlmultiload.Factory.IFluidTank;
import org.faz.dvlmultiload.block.UpgradeBlock;
import org.faz.dvlmultiload.recipetype.UpgradeBlockRecipe;
import org.faz.dvlmultiload.registers.ModBlockEntities;
import org.faz.dvlmultiload.registers.ModSounds;
import org.faz.dvlmultiload.screen.UpgradeMenu;
import org.faz.dvlmultiload.utils.*;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

import static net.minecraft.world.Containers.dropContents;

public class UpgradeBlockEntity extends BlockEntity implements MenuProvider
{
    protected final ContainerData data;

    private int progress = 0;

    private int maxProgress = 0;

    private int ticksRunning;

    private int cooldown;

    private final IFluidTank tank;

    private final DvlEnergyStorage energyStorage = new DvlEnergyStorage(); // not complete

    private String methodReason = "darkvslight.upgrade_block.NAMR";

    public int EnergyRequirement = 32;

    public void setMethodReason(String reason)
    {
        methodReason = reason;
    }

    public String getMethodReason()
    {
        return methodReason;
    }

    public void SetFluid(FluidStack stack)
    {
        tank.setFluid(stack);
    }

    public FluidStack GetFluid()
    {
        return tank.getFluid();
    }

    public ContainerData getContainerData()
    {
        return data;
    }

    public final int SLOTMAX = 3;

    public DvlItemHandler inventory = new DvlItemHandler(SLOTMAX);

    public UpgradeBlockEntity(BlockPos blockPos, BlockState blockState)
    {
        super(ModBlockEntities.UpgradeBlockAsEntity.get(), blockPos, blockState);
        tank = FactoryManager.fluidTankFactory.create(64000);
        tank.addListener(() ->
        {
            setChanged();
            // Sync Mod Messages
        });
        data = new ContainerData()
        {
            @Override
            public int get(int i)
            {
                return switch (i)
                {
                    case 0 -> progress;
                    case 1 -> maxProgress;
                    case 2 -> cooldown;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index)
                {
                    case 0 -> UpgradeBlockEntity.this.progress = value;
                    case 1 -> UpgradeBlockEntity.this.maxProgress = value;
                    case 2 -> UpgradeBlockEntity.this.cooldown = value;
                }
            }

            @Override
            public int getCount() {
                return 3;
            }
        };
    }

    public void tick(BlockState state)
    {
        if (level.isClientSide)
        {
            return;
        }

        System.out.println(tank.getFluidAmount());

        // Test transfer power
        if (hasCooldown())
        {
            if (cooldown == (Math.max(maxProgress, (120 / ((UpgradeBlock)getBlockState().getBlock()).getTier())) / ((UpgradeBlock)getBlockState().getBlock()).getTier()))
            {
                level.playSound(null, getBlockPos(), ModSounds.UpgradeStart.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
                state = state.setValue(UpgradeBlock.COOLDOWN, true).setValue(UpgradeBlock.LIT, false);
                level.setBlockAndUpdate(worldPosition, state);
                level.playSound(null, getBlockPos(), ModSounds.UpgradeEnd.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
            }
            cooldown--;
            if (cooldown == 0)
            {
                state = state.setValue(UpgradeBlock.COOLDOWN, false);
                level.setBlockAndUpdate(worldPosition, state);
            }
            setChanged(level, getBlockPos(), getBlockState());
        }

        if (hasRecipe() && hasEnergy(progress == 0) && hasFluid() && !hasCooldown())
        {
            if (ticksRunning == 0)
            {
                level.playSound(null, getBlockPos(), ModSounds.UpgradePrestart.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
            }
            if (ticksRunning == 20)
            {
                level.playSound(null, getBlockPos(), ModSounds.UpgradeStart.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
                state = state.setValue(UpgradeBlock.LIT, true);
                level.setBlockAndUpdate(worldPosition, state);
            }
            else if (ticksRunning % 38 == 0 && ticksRunning > 20)
            {
                level.playSound(null, getBlockPos(), ModSounds.UpgradeLoop.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
            }
            progress++;
            ticksRunning++;
            extractEnergy();
            setChanged(level, getBlockPos(), getBlockState());
            if (progress >= maxProgress)
            {
                engageItem();
                ticksRunning = 0;
                cooldown = (((UpgradeBlock)getBlockState().getBlock()).getTier() == 5) ? 0 : (Math.max(maxProgress, (120 / ((UpgradeBlock)getBlockState().getBlock()).getTier())) / ((UpgradeBlock)getBlockState().getBlock()).getTier());
            }
        }
        else
        {
            if (ticksRunning != 0)
            {
                level.playSound(null, getBlockPos(), ModSounds.UpgradeEnd.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
            }
            progress = 0;
            ticksRunning = 0;
            state = state.setValue(UpgradeBlock.LIT, false);
            level.setBlockAndUpdate(worldPosition, state);
            setChanged(level, getBlockPos(), getBlockState());
        }

        if (hasFluidItem())
        {
            transferFluidToTank();
        }
    }

    private boolean hasFluid()
    {
        return tank.getFluid().getAmount() > 0;
    }

    private void transferFluidToTank()
    {
        ItemStack container = inventory.getSlot(0);
        Item item = container.getItem();
        if (container.is(Items.WATER_BUCKET))
        {
            FillTank(FluidStack.create(Fluids.WATER, 1000), container);
        }
    }

    private void FillTank(FluidStack stack, ItemStack container)
    {
        level.playSound(null, worldPosition, SoundEvents.BEACON_ACTIVATE, SoundSource.BLOCKS, 1.0F, 1.5F);
        level.playSound(null, worldPosition, SoundEvents.BUCKET_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
        tank.fill(stack, false);
        if (container.is(Items.WATER_BUCKET))
        {
            inventory.setSlot(0, new ItemStack(Items.BUCKET));
        }
        setChanged();
    }

    private boolean hasFluidItem()
    {
        return inventory.getSlot(0).getCount() > 0;
    }

    private void extractEnergy()
    {
        //throw new UnsupportedOperationException("Not implemented");
        //energyStorage.extractEnergy(EnergyRequirement);
    }

    public boolean hasEnergy(boolean initial)
    {
        return true; //throw new UnsupportedOperationException("Not implemented");
        //if (!hasRecipe())
        //    return true;
        //return initial ? (EnergyRequirement <= EnergyStorage.getEnergyStored()) : (EnergyRequirement / maxProgress <= EnergyStorage.getEnergyStored());
    }

    private boolean hasCooldown()
    {
        return cooldown > 0;
    }

    private void engageItem()
    {
        Level lvl = getLevel();
        SimpleContainer inv = new FluidSimpleContainer(SLOTMAX, Optional.of(tank.getFluid()));
        for (int i = 0; i < SLOTMAX; i++)
        {
            inv.setItem(i, inventory.getSlot(i));
        }
        Optional<UpgradeBlockRecipe> recipe = lvl.getRecipeManager().getRecipeFor(UpgradeBlockRecipe.Type.INSTANCE, inv, lvl);

        if (hasRecipe())
        {

        }
    }

    private boolean hasRecipe()
    {
        Level lvl = getLevel();
        SimpleContainer inv = new FluidSimpleContainer(SLOTMAX, Optional.of(tank.getFluid()));
        for (int i = 0; i < SLOTMAX; i++)
        {
            inv.setItem(i, inventory.getSlot(i));
        }
        Optional<UpgradeBlockRecipe> recipe = lvl.getRecipeManager().getRecipeFor(UpgradeBlockRecipe.Type.INSTANCE, inv, lvl);
        if (recipe.isPresent())
        {
            EnergyRequirement = recipe.get().getEnergyOutput();
            maxProgress = recipe.get().getHardness();
            methodReason = recipe.get().getMethod();
            EnergyRequirement = recipe.get().getEnergyOutput() * maxProgress;
            // Sync Mod Messages
        }
        return recipe.isPresent() && canInsert(inv) && canOutput(inv, recipe.get().getResultItem());
    }

    private boolean canInsert(SimpleContainer inv)
    {
        return inv.getItem(2).getCount() < inv.getItem(2).getMaxStackSize();
    }

    private boolean canOutput(SimpleContainer inv, ItemStack stack)
    {
        return inv.getItem(2).getItem() == stack.getItem() || inv.getItem(2).isEmpty();
    }

    @Override
    public void load(CompoundTag tag)
    {
        super.load(tag);
        inventory.deserialize(tag.getCompound("inventory"));
        progress = tag.getInt("upgrade_block.upgrading");
        //energyStorage.read(tag);
        FluidStack.read(tag.getCompound("fluid"));
    }

    @Override
    protected void saveAdditional(CompoundTag tag)
    {
        tag.put("inventory", inventory.serialize());
        tag.putInt("upgrade_block.upgrading", progress);
        //energyStorage.write(tag);
        FluidStack stack = tank.getFluid();
        tag.put("fluid", stack.write(new CompoundTag()));
        super.saveAdditional(tag);
    }

    public Component getDisplayName()
    {
        return Component.literal("Upgrade Block");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player)
    {
        //Sync Mod Messages
        return new UpgradeMenu(i, inventory, this, data);
    }

    public void drops()
    {
        SimpleContainer inv = new SimpleContainer(SLOTMAX);
        for (int i = 0; i < SLOTMAX; i++)
        {
            inv.setItem(i, inventory.getSlot(i));
        }

        dropContents(level, worldPosition, inv);
    }
}
