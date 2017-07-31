package Fantasy_World.mod.item;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import Fantasy_World.mod.fantasy_world;
import Fantasy_World.mod.item.base.fast_drag;

public class FwmFood{
	public FwmFood(){
		fantasy_world.foods = new fast_drag(new PotionEffect[]{new PotionEffect(Potion.heal.id, 2, 0)}).setCreativeTab(fantasy_world.Tabs.ItemTabs).setUnlocalizedName("fast_drag").setTextureName("fantasy_world:fast_drag");
//.setPotionEffect(Potion.heal.id, 60, 1, 1.0F)
	}
}