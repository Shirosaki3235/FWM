package Fantasy_World.mod.recipe;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import Fantasy_World.mod.fantasy_world;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockRecipe{
	public BlockRecipe(){
		GameRegistry.addRecipe(new ItemStack(fantasy_world.blocks.crystal_block, 1),
		" X ",
		"XYX",
		" X ",
		'X', fantasy_world.items.crystal_stone,
		'Y', Items.glowstone_dust);
	}
}