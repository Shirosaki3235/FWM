package Fantasy_World.mod.item.base;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import Fantasy_World.mod.fantasy_world;

public class herb_seed extends ItemSeeds implements IPlantable {
	private Block field_150925_a;
	// 土台となるブロックのインスタンス。使われていない。
	//	private Block soilBlockID;

	public herb_seed() {
		super(fantasy_world.blocks.herb_corp, Blocks.farmland);
		// 以下はItemSeedsのコンストラクタ。
		this.field_150925_a = fantasy_world.blocks.herb_corp;
		//		this.soilBlockID = Blocks.farmland;
		this.setCreativeTab(fantasy_world.Tabs.ItemTabs);
	}

	/** アイテムを使用した時の処理。 */
	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if(!player.isSneaking()){
			if (side != 1) {
				return false;
			} else if (player.canPlayerEdit(x, y, z, side, itemStack) && player.canPlayerEdit(x, y + 1, z, side, itemStack)) {
				Block soil = world.getBlock(x, y, z);
				// 上からの使用で、プレイヤーが編集可能で、右クリックしたブロックが耕地であり、その上が空気の時。
				if (soil != null && soil.canSustainPlant(world, x, y, z, ForgeDirection.UP, this) && world.isAirBlock(x, y + 1, z)) {
					// 作物を設置する。.field_150925_a
					world.setBlock(x, y + 1, z, this.field_150925_a);
					// スタック数を減らす。
					--itemStack.stackSize;
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/** 作物の種別を返す。 */
	@Override
	public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
		// IPlantableの実装。作物。耕地の上に設置する。
		return EnumPlantType.Crop;
	}

	/** 作物ブロックのインスタンスを返す。 */
	@Override
	public Block getPlant(IBlockAccess world, int x, int y, int z) {
		// IPlantableの実装。
		return field_150925_a;
	}

	/** 作物のメタデータを返す。 */
	@Override
	public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
		// IPlantableの実装。
		return 0;
	}
}
