package Fantasy_World.mod.recipe;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import Fantasy_World.mod.fantasy_world;
import cpw.mods.fml.common.registry.GameRegistry;


public class ItemRecipe{
	public ItemRecipe(){
		GameRegistry.addRecipe(new ItemStack(fantasy_world.items.crystal_stone, 1),
				"XXX",
				"XYX",
				"XXX",
				'X', fantasy_world.items.crystal,
				'Y', Items.redstone);
	}
}