package Fantasy_World.mod.register;

import net.minecraftforge.oredict.OreDictionary;
import Fantasy_World.mod.fantasy_world;
import Fantasy_World.mod.item.base.magic_tree_leaves_item;
import cpw.mods.fml.common.registry.GameRegistry;



public class RegisterBlocks{
	public RegisterBlocks(){
		// ブロック
		GameRegistry.registerBlock(fantasy_world.blocks.crystal_block, "blockAluminium");
		GameRegistry.registerBlock(fantasy_world.blocks.crystal_ore, "crystal_ore");

		// 木
		GameRegistry.registerBlock(fantasy_world.blocks.magic_wood_sapling, "magic_wood_sapling");
		GameRegistry.registerBlock(fantasy_world.blocks.magic_wood_log, "magic_wood_log");
		GameRegistry.registerBlock(fantasy_world.blocks.magic_wood_leaves, magic_tree_leaves_item.class, "magic_wood_leaves");
		GameRegistry.registerBlock(fantasy_world.blocks.magic_wood_block, "magic_wood_block");

		// 木生成
		OreDictionary.registerOre("magic_wood_sapling", fantasy_world.blocks.magic_wood_sapling);
		OreDictionary.registerOre("magic_wood_log", fantasy_world.blocks.magic_wood_log);
		OreDictionary.registerOre("magic_wood_leaves", fantasy_world.blocks.magic_wood_leaves);

		// 作物
		GameRegistry.registerBlock(fantasy_world.blocks.herb_corp, "herb_corp");
	}
}