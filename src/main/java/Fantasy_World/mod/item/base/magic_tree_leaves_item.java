package Fantasy_World.mod.item.base;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.item.ItemLeaves;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class magic_tree_leaves_item extends ItemLeaves{
	protected final Block leaves;
	 
	public magic_tree_leaves_item(Block block) {
		super((BlockLeaves) block);
		this.leaves = block;
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}
 
	@Override
	public int getMetadata(int meta) {
		// 設置時は二進数三桁目が1になる。
		return meta | 4;
	}
 
	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		return leaves.getUnlocalizedName();
	}
 
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		return leaves.getIcon(0, meta);
	}
 
	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack itemStack, int pass) {
		return leaves.getRenderColor(itemStack.getItemDamage());
	}
}