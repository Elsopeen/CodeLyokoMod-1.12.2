package com.Ultra_Nerd.CodeLyokoRemake.items.tools;

import com.Ultra_Nerd.CodeLyokoRemake.init.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSword;

public class Zweihander extends ItemSword {
	
	private float attackDamage;
	public Zweihander(String name, ToolMaterial material)
	{
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.COMBAT);
		this.attackDamage = 3.0F + material.getAttackDamage();
		ModItems.Items.add(this);
	}
	

}



