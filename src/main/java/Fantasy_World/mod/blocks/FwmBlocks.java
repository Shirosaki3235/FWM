package Fantasy_World.mod.blocks;

import net.minecraft.block.material.Material;
import Fantasy_World.mod.fantasy_world;
import Fantasy_World.mod.blocks.base.c_ore;
import Fantasy_World.mod.blocks.base.fantasy_world_block;
import Fantasy_World.mod.blocks.base.herb_corp_block;
import Fantasy_World.mod.blocks.base.magic_log_block;
import Fantasy_World.mod.blocks.base.magic_sapling_block;
import Fantasy_World.mod.blocks.base.magic_tree_leaves_block;
import Fantasy_World.mod.blocks.base.wood_block;


public class FwmBlocks{
	public FwmBlocks(){
		//Blockを継承したクラスのインスタンスを生成し、代入する。e
		fantasy_world.blocks.crystal_block = new fantasy_world_block(Material.rock).setCreativeTab(fantasy_world.Tabs.BlockTabs).setBlockName("crystal_block").setBlockTextureName("fantasy_world:crystal_block");

		fantasy_world.blocks.crystal_ore = new c_ore().setCreativeTab(fantasy_world.Tabs.BlockTabs).setBlockName("crystal_ore").setBlockTextureName("fantasy_world:crystal_ore");

		// 木
		fantasy_world.blocks.magic_wood_sapling = new magic_sapling_block().setBlockName("magic_wood_sapling").setBlockTextureName("fantasy_world:magic_wood_sapling");
		fantasy_world.blocks.magic_wood_log = new magic_log_block().setBlockName("magic_wood_log").setBlockTextureName("fantasy_world:magic_wood_log");
		fantasy_world.blocks.magic_wood_leaves = new magic_tree_leaves_block().setBlockName("magic_wood_leaves").setBlockTextureName("fantasy_world:magic_wood_leaves");
		fantasy_world.blocks.magic_wood_block = new wood_block(Material.wood).setCreativeTab(fantasy_world.Tabs.BlockTabs).setBlockName("magic_wood_block").setBlockTextureName("fantasy_world:magic_wood_block");

		// 作物
		fantasy_world.blocks.herb_corp = new herb_corp_block().setBlockName("herb_corp").setBlockTextureName("fantasy_world:herb_corp");
	}
}