package Fantasy_World.mod.blocks.base;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class fantasy_world_block extends Block {

	public fantasy_world_block(Material material) {
		super(material);
		//硬さの設定
		this.setHardness(5.0F);
		//爆破耐性の設定
		this.setResistance(10.0F);
		//ブロックの上を歩いた時の音を登録する。
		this.setStepSound(Block.soundTypeMetal);
		//回収するのに必要なツールを設定する。
		this.setHarvestLevel("pickaxe", 2);
		//明るさの設定
		this.setLightLevel(1.0F);

	}


}