package Fantasy_World.mod.recipe;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import Fantasy_World.mod.fantasy_world;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockRecipe{
	public BlockRecipe(){
		// クリスタルブロック
		GameRegistry.addRecipe(new ItemStack(fantasy_world.blocks.crystal_block, 1),
		" X ",
		"XYX",
		" X ",
		'X', fantasy_world.items.crystal_stone,
		'Y', Items.glowstone_dust);

		// 魔法の葉ブロック
		GameRegistry.addRecipe(new ItemStack(fantasy_world.blocks.magic_wood_leaves, 1),
				"XX ",
				"XX",
				'X', fantasy_world.items.magic_leaf);

		/*GameRegistry.registerFuelHandler(new IFuelHandler(){
			@Override
			public int getBurnTime(ItemStack fuel) {
				if (fuel.getItem() == (fantasy_world.blocks.magic_wood_leaves)) {
					return 100;
				}
				return 0;
			}
		});*/

		// 魔法の木材
		GameRegistry.addShapelessRecipe(new ItemStack(fantasy_world.blocks.magic_wood_block, 4),
				fantasy_world.blocks.magic_wood_log);






		// バニラブロック
		// 作業台
		GameRegistry.addRecipe(new ItemStack(Blocks.crafting_table, 1),
				"XX",
				"XX",
				'X', fantasy_world.blocks.magic_wood_block);

		// フェンスゲート
		GameRegistry.addRecipe(new ItemStack(Blocks.fence_gate, 1),
				"YXY",
				"YXY",
				'X', fantasy_world.blocks.magic_wood_block,
				'Y', Items.stick);

		// ベッド
		/*GameRegistry.addRecipe(new ItemStack(Blocks.bed, 1),new Object[]{
				"YYY",
				"XXX",
				'X', fantasy_world.blocks.magic_wood_block,
				'Y', new ItemStack(Blocks.wool, 1, OreDictionary.WILDCARD_VALUE)});*/
















	}
}