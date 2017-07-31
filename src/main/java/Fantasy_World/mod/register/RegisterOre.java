package Fantasy_World.mod.register;

import Fantasy_World.mod.generater.crystal_generator;
import cpw.mods.fml.common.registry.GameRegistry;

public class RegisterOre{
	public RegisterOre(){
		// 鉱石生成
		GameRegistry.registerWorldGenerator(new crystal_generator(), 0);
	}
}