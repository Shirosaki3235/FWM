package Fantasy_World.mod.item;

import Fantasy_World.mod.fantasy_world;
import Fantasy_World.mod.item.armorbase.armorbase;

public class FwmArmors{
	public static final int HELMET   = 0;
	public static final int PLATE    = 1;
	public static final int LEGGINGS = 2;
	public static final int BOOTS    = 3;

	public FwmArmors(){
		fantasy_world.armors.magic_armor_helmet = new armorbase(0)
		.setUnlocalizedName("magic_armor_helmet")
		.setTextureName("aluminiummod:magic_armor_helmet");

		fantasy_world.armors.magic_armor_chest = new armorbase(1)
		.setUnlocalizedName("magic_armor_chest")
		.setTextureName("aluminiummod:magic_armor_chest");

		fantasy_world.armors.magic_armor_leggins = new armorbase(2)
		.setUnlocalizedName("magic_armor_leggins")
		.setTextureName("aluminiummod:magic_armor_leggins");

		fantasy_world.armors.magic_armor_boots = new armorbase(3)
		.setUnlocalizedName("magic_armor_boots")
		.setTextureName("aluminiummod:magic_armor_boots");
	}
}