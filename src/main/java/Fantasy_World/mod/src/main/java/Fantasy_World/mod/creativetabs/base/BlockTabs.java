package Fantasy_World.mod.creativetabs.base;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public class BlockTabs extends CreativeTabs{
	public BlockTabs(String label){
		super(label);
	}

	@Override
	public Item getTabIconItem() {
		// TODO 自動生成されたメソッド・スタブ
		return Item.getItemFromBlock(Blocks.dirt);
	}
}