package Fantasy_World.mod.blocks.base;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.IGrowable;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.terraingen.TerrainGen;
import Fantasy_World.mod.fantasy_world;
import Fantasy_World.mod.generater.magic_wood_generator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class magic_sapling_block extends BlockSapling implements IPlantable, IGrowable{
	private static final EnumPlantType Plains = null;

	public magic_sapling_block(){
		super();
		this.setCreativeTab(fantasy_world.Tabs.ItemTabs);
		this.setTickRandomly(true);
		this.setHardness(0F);
		this.setStepSound(soundTypeGrass);
		float f = 0.4F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		// Blockで上書き可能なブロックかどうかの判定をしているが、BlockBushでcanBlockStayの判定を追加している。
		return world.getBlock(x, y, z).isReplaceable(world, x, y, z) && this.canBlockStay(world, x, y, z);
	}

	@Override
	protected boolean canPlaceBlockOn(Block block) {
		// 草、土、耕された土ならtrueを返す。
		return block == Blocks.grass || block == Blocks.dirt || block == Blocks.farmland;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		this.checkAndDropBlock(world, x, y, z);
	}

	@Override
	protected void checkAndDropBlock(World world, int x, int y, int z) {
		if (!this.canBlockStay(world, x, y, z)) {
			this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
			world.setBlockToAir(x, y, z);
		}
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		// 下のブロックのcanSustainPlantで判定している。
		return world.getBlock(x, y - 1, z).canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		// 当たり判定を消すため、nullを返している。
		return null;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getRenderType() {
		return 1;
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
		return Plains;
	}

	@Override
	public Block getPlant(IBlockAccess world, int x, int y, int z) {
		return this;
	}

	@Override
	public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random random) {
		// サーバー側で、checkAndDropBlock・明るさの判定を行い、条件を満たしていれば1/7の確率で成長する。
		if (!world.isRemote) {
			this.checkAndDropBlock(world, x, y, z);
			if (world.getBlockLightValue(x, y + 1, z) >= 9 && random.nextInt(7) == 0) {
				this.func_149879_c(world, x, y, z, random);
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		// Blockと同様。
		return blockIcon;
	}

	@Override
	public void func_149879_c(World world, int x, int y, int z, Random random) {
		// 一度も成長していないなら一段階成長させ、二段階目なら木を生成する。
		// 一段階目の成長が終わったかどうかは、メタデータの二進数四桁目で判断する。
		int meta = world.getBlockMetadata(x, y, z);
		if ((meta & 8) == 0) {
			world.setBlockMetadataWithNotify(x, y, z, meta | 8, 4);
		} else {
			this.func_149878_d(world, x, y, z, random);
		}
	}

	@Override
	public void func_149878_d(World world, int x, int y, int z, Random random) {
		// Eventを呼び出しているが、他MODでキャンセルされたくなければこの部分は削除してよい。
		if (!TerrainGen.saplingGrowTree(world, random, x, y, z))
			return;
		// メタデータを利用して複数種類の木を追加したい場合はバニラの苗木を参考にするとよい。
		int meta = 0;
		// 大木を生成したい場合はこの部分を参考にするとよい。
		// Object object = random.nextInt(10) == 0 ? new WorldGenBigTree(true) : new WorldGenTrees(true);
		Object object = new magic_wood_generator(true);
		world.setBlock(x, y, z, Blocks.air, 0, 4);
		if (!((WorldGenerator) object).generate(world, random, x, y, z)) {
			world.setBlock(x, y, z, this, meta, 4);
		}
	}

	@Override
	public boolean func_149880_a(World world, int x, int y, int z, int type) {
		return world.getBlock(x, y, z) == this && (world.getBlockMetadata(x, y, z) & 7) == type;
	}

	@Override
	public int damageDropped(int meta) {
		// Blockと同様。
		return 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		// Blockと同様。
		list.add(new ItemStack(item, 1, 0));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		// Blockと同様。
		blockIcon = register.registerIcon(this.getTextureName());
	}

	@Override
	public boolean func_149851_a(World world, int x, int y, int z, boolean isRemote) {
		return true;
	}

	@Override
	public boolean func_149852_a(World world, Random random, int x, int y, int z) {
		return world.rand.nextFloat() < 0.45D;
	}

	@Override
	public void func_149853_b(World world, Random random, int x, int y, int z) {
		this.func_149879_c(world, x, y, z, random);
	}
}