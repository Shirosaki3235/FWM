package Fantasy_World.mod.enums;

import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;



public class FwmEnumToolMaterial{

	// 武器
	public static final Item.ToolMaterial crystal_sword_mat = EnumHelper.addToolMaterial("CrystalSword", 0, 130, 2.0F, 2.0F , 5);
	public static final Item.ToolMaterial short_sword_mat = EnumHelper.addToolMaterial  ("ShortSword"  , 2, 150, 4.0F, 10.0F, 30);
																					//("BLUE      ", 0, 150, 1.0F, 10.0F, 30);
	// 杖
	public static final Item.ToolMaterial magic_rod_mat = EnumHelper.addToolMaterial("MagicRod", 0, 80, 1.0F, 1.0F, 20);

	// 防具
	public static final ArmorMaterial magic_robe = EnumHelper.addArmorMaterial("MagicRobe",150 , new int[] {1, 1, 1, 1}, 10);

	// 道具



}