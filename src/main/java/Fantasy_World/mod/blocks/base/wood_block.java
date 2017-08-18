package Fantasy_World.mod.blocks.base;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class wood_block extends Block{
	public wood_block(Material material){
		super(material);
		//硬さの設定
		this.setHardness(2.0F);
		//爆破耐性の設定
		this.setResistance(1.0F);
		//ブロックの上を歩いた時の音を登録する。
		this.setStepSound(Block.soundTypeWood);
		//回収するのに必要なツールを設定する。
		this.setHarvestLevel("axe", 0);
		//明るさの設定
		this.setLightLevel(0.0F);
	}
}