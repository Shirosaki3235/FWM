package Fantasy_World.mod.conf;

import Fantasy_World.mod.fantasy_world;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ModEventHandler {
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		// コンフィグが変更された時に呼ばれる。
		if (event.modID.equals(fantasy_world.instance.modID))
			ModConfigCore.syncConfig();
	}
}
