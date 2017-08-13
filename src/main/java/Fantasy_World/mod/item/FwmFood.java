package Fantasy_World.mod.item;

import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import Fantasy_World.mod.fantasy_world;
import Fantasy_World.mod.item.base.fast_drag;
import Fantasy_World.mod.item.base.herb_seed;

public class FwmFood{
	public FwmFood(){
		fantasy_world.foods.fast_drag = new fast_drag(new PotionEffect[]{new PotionEffect(Potion.heal.id, 2, 0)}).setCreativeTab(fantasy_world.Tabs.ItemTabs).setUnlocalizedName("fast_drag").setTextureName("fantasy_world:fast_drag");
//.setPotionEffect(Potion.heal.id, 60, 1, 1.0F)
		// 種のインスタンス生成。
		fantasy_world.foods.herb_seed = new herb_seed().setUnlocalizedName("herb_seed").setTextureName("fantasy_world:herb_seed");

		// 小麦のインスタンス生成。
		fantasy_world.foods.herb_laef = new Item().setUnlocalizedName("herb_laef").setTextureName("fantasy_world:herb_leaf").setCreativeTab(fantasy_world.Tabs.ItemTabs);

	}
}