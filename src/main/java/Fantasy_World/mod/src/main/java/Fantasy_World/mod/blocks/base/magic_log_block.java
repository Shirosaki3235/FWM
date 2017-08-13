package Fantasy_World.mod.blocks.base;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import Fantasy_World.mod.fantasy_world;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class magic_log_block extends BlockLog {
	private IIcon[] iicon = new IIcon[2];

	public magic_log_block(){
		super();
		this.setCreativeTab(fantasy_world.Tabs.BlockTabs);
		this.setHardness(2.0F);
		this.setStepSound(soundTypeWood);
	}
	@Override
	public int getRenderType() {
		return 31;
	}

	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float posX, float posY, float posZ, int meta) {
		// 設置された方向に応じてメタデータを設定する。
		int metaType = meta & 3;
		byte direction = 0;

		switch (side) {
		case 0:
		case 1:
			direction = 0;
			break;
		case 2:
		case 3:
			direction = 8;
			break;
		case 4:
		case 5:
			direction = 4;
		}

		return metaType | direction;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		int k = meta & 12;
		int l = meta & 3;
		return k == 0 && (side == 1 || side == 0) ? this.getTopIcon(l) : (k == 4 && (side == 5 || side == 4) ? this.getTopIcon(l) : (k == 8 && (side == 2 || side == 3) ? this.getTopIcon(l) : this.getSideIcon(l)));
	}

	@Override
	public int damageDropped(int meta) {
		return meta & 3;
	}

	@Override
	@SideOnly(Side.CLIENT)
	protected IIcon getSideIcon(int meta) {
		return this.iicon[1];
	}

	@Override
	@SideOnly(Side.CLIENT)
	protected IIcon getTopIcon(int meta) {
		return this.iicon[0];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iicon) {
		this.iicon[0] = iicon.registerIcon(this.getTextureName() + "_top");
		this.iicon[1] = iicon.registerIcon(this.getTextureName() + "_side");
	}

	@Override
	protected ItemStack createStackedBlock(int meta) {
		return new ItemStack(Item.getItemFromBlock(this), 1, meta & 3);
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		// 周囲の葉ブロックの消滅を始める。
		byte b0 = 4;
		int i1 = b0 + 1;

		if (!world.checkChunksExist(x - i1, y - i1, z - i1, x + i1, y + i1, z + i1))
			return;
		for (int ix = -b0; ix <= b0; ++ix) {
			for (int iy = -b0; iy <= b0; ++iy) {
				for (int iz = -b0; iz <= b0; ++iz) {
					Block block1 = world.getBlock(x + ix, y + iy, z + iz);
					if (block1.isLeaves(world, x + ix, y + iy, z + iz)) {
						block1.beginLeavesDecay(world, x + ix, y + iy, z + iz);
					}
				}
			}
		}
	}

	@Override
	public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z) {
		return true;
	}

	@Override
	public boolean isWood(IBlockAccess world, int x, int y, int z) {
		return true;
	}
}