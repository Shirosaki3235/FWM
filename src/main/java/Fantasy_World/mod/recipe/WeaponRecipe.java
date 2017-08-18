package Fantasy_World.mod.recipe;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import Fantasy_World.mod.fantasy_world;
import cpw.mods.fml.common.registry.GameRegistry;

public class WeaponRecipe{
	public WeaponRecipe(){
		// crystal block
		GameRegistry.addRecipe(new ItemStack(fantasy_world.weapons.crystal_sword, 1),
				"X",
				"X",
				"Y",
				'X', fantasy_world.items.crystal,
				'Y', Items.stick);

		// 魔法の杖
		GameRegistry.addRecipe(new ItemStack(fantasy_world.weapons.magic_rod, 1),
				"XZX",
				" Y ",
				" Y ",
				'X', fantasy_world.items.crystal,
				'Y', fantasy_world.items.rod_stick,
				'Z', fantasy_world.items.normal_element);

		// 短剣
		GameRegistry.addRecipe(new ItemStack(fantasy_world.weapons.short_sword, 1),
				"X",
				"Y",
				'X', Items.iron_ingot,
				'Y', Items.stick);
	}
}