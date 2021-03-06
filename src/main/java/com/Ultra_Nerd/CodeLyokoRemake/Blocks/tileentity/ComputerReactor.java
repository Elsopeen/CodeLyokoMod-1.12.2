package com.Ultra_Nerd.CodeLyokoRemake.Blocks.tileentity;

import com.Ultra_Nerd.CodeLyokoRemake.RF.EG;
import com.Ultra_Nerd.CodeLyokoRemake.init.ModItems;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class ComputerReactor extends TileEntity implements ITickable
{
	public ItemStackHandler handle = new ItemStackHandler(1);
	private EG store = new EG(1000000000);
	private String Cust;
	public int Fission;
	public int energy = store.getEnergyStored();
	
	@Override
	public void update() {
		if(!handle.getStackInSlot(0).isEmpty() && isItemFuel(handle.getStackInSlot(0)));
		{
			Fission++;
			if(Fission == 40)
			{
				energy += getFuelValue(handle.getStackInSlot(0));
				handle.getStackInSlot(0).shrink(1);
				Fission = 0;
			}
		}
		
	}
	
	private boolean isItemFuel(ItemStack stackInSlot) {
		// TODO Auto-generated method stub
		return getFuelValue(stackInSlot) > 0;
	}

	private int getFuelValue(ItemStack stackInSlot) {
		if(stackInSlot.getItem() == ModItems.URANIUM_ISOTOPE23S5) return 20000;
		else return 0;
		
			
		
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if(capability == CapabilityEnergy.ENERGY) return(T)this.store;
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return (T)this.handle;
		return super.getCapability(capability, facing);
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		
		if(capability == CapabilityEnergy.ENERGY) return true;
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return true;
		return super.hasCapability(capability, facing);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		// TODO Auto-generated method stub
		super.writeToNBT(compound);
		compound.setTag("Inventory", this.handle.serializeNBT());
		compound.setInteger("Fission", this.Fission);
		compound.setInteger("GUIEN", this.energy);
		compound.setString("name", getDisplayName().toString());
		this.store.WRTNBT(compound);
		return compound;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		// TODO Auto-generated method stub
		super.readFromNBT(compound);
		this.handle.deserializeNBT(compound.getCompoundTag("Inventory"));
		this.Fission = compound.getInteger("Fission");
		this.energy = compound.getInteger("GUIEN");
		this.Cust = compound.getString("name");
		this.store.RFNBT(compound);
		
	}
	@Override
	public ITextComponent getDisplayName()
	{
		return new TextComponentTranslation("container.Reactor");
	}
	public int getEnergy()
	{
		return this.energy;
	}
	public int Maxen()
	{
		return this.store.getMaxEnergyStored();
	}
	
	public int get(int id)
	{
		switch(id)
		{
		case 0:
		return this.energy;
		case 1:
		return this.Fission;
		default:
			return 0;
		}
	}
	public void set(int id, int val)
	{
		switch(id)
		{
		case 0:
			this.energy = val;
			
		case 1:
			this.Fission = val;
			
			
		}
	}
	public boolean isUsableByPlayer(EntityPlayer player) 
	{
		return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}
}
