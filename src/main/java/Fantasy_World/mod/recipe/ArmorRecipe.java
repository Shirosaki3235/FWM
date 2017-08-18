package Fantasy_World.mod.recipe;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import Fantasy_World.mod.fantasy_world;
import cpw.mods.fml.common.registry.GameRegistry;

public class ArmorRecipe{
	public ArmorRecipe(){
		GameRegistry.addRecipe(new ItemStack(fantasy_world.armors.magic_armor_helmet, 1),
				"YXY",
				"X X",
				'Y', fantasy_world.items.normal_element,
				'X', Items.iron_ingot);
		GameRegistry.addRecipe(new ItemStack(fantasy_world.armors.magic_armor_chest, 1),
				"XYX",
				"XYX",
				"XXX",
				'Y', fantasy_world.items.normal_element,
				'X', Items.iron_ingot);
		GameRegistry.addRecipe(new ItemStack(fantasy_world.armors.magic_armor_leggins, 1),
				"XYX",
				"XYX",
				"X X",
				'Y', fantasy_world.items.normal_element,
				'X', Items.iron_ingot);
		GameRegistry.addRecipe(new ItemStack(fantasy_world.armors.magic_armor_boots, 1),
				"Y Y",
				"X X",
				'Y', fantasy_world.items.normal_element,
				'X', Items.iron_ingot);
	}
}