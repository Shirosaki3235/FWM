package Fantasy_World.mod.creativetabs.base;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class ItemTabs extends CreativeTabs{
	public ItemTabs(String label){
		super(label);
	}
	@Override
	public Item getTabIconItem() {
		return //Items.diamond
				Items.golden_apple;
	}
}