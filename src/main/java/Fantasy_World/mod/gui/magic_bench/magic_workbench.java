package Fantasy_World.mod.gui.magic_bench;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import Fantasy_World.mod.fantasy_world;
import Fantasy_World.mod.gui.magic_bench.tileentity.magic_workbench_tileentity;

public class magic_workbench extends Block implements ITileEntityProvider{
	private Random random = new Random();

	public magic_workbench() {
		super(Material.rock);
		this.setCreativeTab(fantasy_world.Tabs.BlockTabs);
		this.setHardness(5.0F);
		this.setResistance(1.0F);
		this.setStepSound(soundTypeMetal);
		isBlockContainer = true;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new magic_workbench_tileentity();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		// GUIを開く。
		player.openGui(fantasy_world.instance, 1, world, x, y, z);
		return true;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		// TileEntityの内部にあるアイテムをドロップさせる。
		magic_workbench_tileentity tileentity = (magic_workbench_tileentity) world.getTileEntity(x, y, z);
		if (tileentity != null) {
			for (int i = 0; i < tileentity.getSizeInventory(); i++) {
				ItemStack itemStack = tileentity.getStackInSlot(i);

				if (itemStack != null) {
					float f = random.nextFloat() * 0.6F + 0.1F;
					float f1 = random.nextFloat() * 0.6F + 0.1F;
					float f2 = random.nextFloat() * 0.6F + 0.1F;

					while (itemStack.stackSize > 0) {
						int j = random.nextInt(21) + 10;

						if (j > itemStack.stackSize) {
							j = itemStack.stackSize;
						}

						itemStack.stackSize -= j;
						EntityItem entityItem = new EntityItem(world, x + f, y + f1, z + f2,
								new ItemStack(itemStack.getItem(), j, itemStack.getItemDamage()));

						if (itemStack.hasTagCompound()) {
							entityItem.getEntityItem()
									.setTagCompound(((NBTTagCompound) itemStack.getTagCompound().copy()));
						}

						float f3 = 0.025F;
						entityItem.motionX = (float) random.nextGaussian() * f3;
						entityItem.motionY = (float) random.nextGaussian() * f3 + 0.1F;
						entityItem.motionZ = (float) random.nextGaussian() * f3;
						world.spawnEntityInWorld(entityItem);
					}
				}
			}
			world.func_147453_f(x, y, z, block);
		}
		super.breakBlock(world, x, y, z, block, meta);
	}
}