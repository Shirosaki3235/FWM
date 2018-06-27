package Fantasy_World.mod.render.entity.mob;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import Fantasy_World.mod.model.entity.mob.seyana_model;

public class seyana_render extends RenderLiving {
    public static final ResourceLocation texture = new ResourceLocation("fantasy_world:subfolder/entity/mob/seyana_mob.png");
    public seyana_render() {
        // 引数:(ModelBase以降を継承したクラスのインスタンス、影の大きさ)subfolder/entity/npc
        super(new seyana_model(), 0.6f);
    }
 //	protected ResourceLocation getEntityTexture(EntityLiving entity) { return texture; }

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		// TODO 自動生成されたメソッド・スタブ
		return texture;
	}
}