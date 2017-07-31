package Fantasy_World.mod.register;

import Fantasy_World.mod.fantasy_world;
import cpw.mods.fml.common.registry.GameRegistry;



public class RegisterBlocks{
	public RegisterBlocks(){
		// ブロック
		GameRegistry.registerBlock(fantasy_world.blocks.crystal_block, "blockAluminium");
		GameRegistry.registerBlock(fantasy_world.blocks.crystal_ore, "crystal_ore");
	}
}