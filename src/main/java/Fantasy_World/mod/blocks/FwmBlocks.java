package Fantasy_World.mod.blocks;

import net.minecraft.block.material.Material;
import Fantasy_World.mod.fantasy_world;
import Fantasy_World.mod.blocks.base.c_ore;
import Fantasy_World.mod.blocks.base.fantasy_world_block;


public class FwmBlocks{
	public FwmBlocks(){
		//Blockを継承したクラスのインスタンスを生成し、代入する。e
		fantasy_world.blocks.crystal_block = new fantasy_world_block(Material.rock).setCreativeTab(fantasy_world.Tabs.BlockTabs).setBlockName("crystal_block").setBlockTextureName("fantasy_world:crystal_block");

		fantasy_world.blocks.crystal_ore = new c_ore().setCreativeTab(fantasy_world.Tabs.BlockTabs).setBlockName("crystal_ore").setBlockTextureName("fantasy_world:crystal_ore");


	}
}