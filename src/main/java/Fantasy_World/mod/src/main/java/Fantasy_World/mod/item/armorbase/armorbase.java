package Fantasy_World.mod.item.armorbase;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import Fantasy_World.mod.fantasy_world;

public class armorbase extends ItemArmor{
	public armorbase(int type) {
		super(fantasy_world.FwmToolMaterial.magic_armors, 0, type);
		this.setCreativeTab(fantasy_world.Tabs.ItemTabs);
	}

	@Override
	public String getArmorTexture(ItemStack itemStack, Entity entity, int slot, String type) {
		if (this.armorType == 2) {
			return "fantasy_world:textures/armors/magic_armors_layer_2.png";
		}
		return "fantasy_world:textures/armors/magic_armors_layer_1.png";
	}

}