package Fantasy_World.mod.conf;

import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import Fantasy_World.mod.fantasy_world;
import cpw.mods.fml.client.IModGuiFactory;
import cpw.mods.fml.client.config.GuiConfig;

public class guifactory implements IModGuiFactory {
	@Override
	public void initialize(Minecraft minecraftInstance) {
	}

	@Override
	public Class<? extends GuiScreen> mainConfigGuiClass() {
		return AluminiumModConfigGui.class;
	}

	@Override
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
		return null;
	}

	@Override
	public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) {
		return null;
	}

	public static class AluminiumModConfigGui extends GuiConfig {
		public AluminiumModConfigGui(GuiScreen parent) {
			super(parent, (new ConfigElement<Object>(ModConfigCore.cfg.getCategory(ModConfigCore.GENERAL))).getChildElements(), fantasy_world.instance.modID, false, false, fantasy_world.instance.name);
		}
	}
}