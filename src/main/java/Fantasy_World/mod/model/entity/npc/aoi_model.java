package Fantasy_World.mod.model.entity.npc;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class aoi_model extends ModelBase {
	 //fields
	public ModelRenderer head;
    public ModelRenderer head2;
    public ModelRenderer body;
    public ModelRenderer rightarm;
    public ModelRenderer leftarm;
    public ModelRenderer rightleg;
    public ModelRenderer leftleg;

    public aoi_model()
    {
        textureWidth = 64;
        textureHeight = 32;
        head = new ModelRenderer(this, 0, 0);
        head.addBox(-4F, -8F, -4F, 8, 8, 8);
        head.setRotationPoint(0F, 0F, 0F);
        head.setTextureSize(64, 32);
        head.mirror = true;
        head2 = new ModelRenderer(this, 32, 0);
		head2.addBox(-4F, -8F, -4F, 8, 8, 8, 0.5F);
		head2.setRotationPoint(0F, 0F, 0F);
        head2.setTextureSize(64, 32);

        body = new ModelRenderer(this, 16, 16);
        body.addBox(-4F, 0F, -2F, 8, 12, 4);
        body.setRotationPoint(0F, 0F, 0F);
        body.setTextureSize(64, 32);
        setRotation(body, 0F, 0F, 0F);
        rightarm = new ModelRenderer(this, 40, 16);
        rightarm.addBox(-3F, -2F, -2F, 4, 12, 4);
        rightarm.setRotationPoint(-5F, 2F, 0F);
        rightarm.setTextureSize(64, 32);
        setRotation(rightarm, 0F, 0F, 0F);
        leftarm = new ModelRenderer(this, 40, 16);
        leftarm.mirror = true;
        leftarm.addBox(-1F, -2F, -2F, 4, 12, 4);
        leftarm.setRotationPoint(5F, 2F, 0F);
        leftarm.setTextureSize(64, 32);
        setRotation(leftarm, 0F, 0F, 0F);
        rightleg = new ModelRenderer(this, 0, 16);
        rightleg.mirror = true;
        rightleg.addBox(-2F, 0F, -2F, 4, 12, 4);
        rightleg.setRotationPoint(-2F, 12F, 0F);
        rightleg.setTextureSize(64, 32);
        setRotation(rightleg, 0F, 0F, 0F);
        leftleg = new ModelRenderer(this, 0, 16);
        leftleg.addBox(-2F, 0F, -2F, 4, 12, 4);
        leftleg.setRotationPoint(2F, 12F, 0F);
        leftleg.setTextureSize(64, 32);
        setRotation(leftleg, 0F, 0F, 0F);

        /*
		//constructor:
		body = new ModelRenderer(this, 16, 16);
		body.mirror = true;
		body.addBox(-4F, 0F, -2F, 8, 12, 4);
		body.setRotationPoint(0F, 24F, 0F);

		head = new ModelRenderer(this, 0, 0);
		head.addBox(-4F, -8F, -4F, 8, 8, 8);
		head.setRotationPoint(0F, 24F, 0F);

		head2 = new ModelRenderer(this, 32, 0);
		head2.addBox(-5F, -9F, -5F, 8, 8, 8, 1F);
		head2.setRotationPoint(0F, 24F, 0F);

		leftarm = new ModelRenderer(this, 40, 16);
		leftarm.mirror = true;
		leftarm.addBox(0F, -2F, -2F, 4, 12, 4);
		leftarm.setRotationPoint(-4F, 22F, 0F);

		leftleg = new ModelRenderer(this, 0, 16);
		leftleg.mirror = true;
		leftleg.addBox(-2F, 0F, -2F, 4, 12, 4);
		leftleg.setRotationPoint(-2F, 12F, 0F);

		rightarm = new ModelRenderer(this, 40, 16);
		rightarm.addBox(-4F, -2F, -2F, 4, 12, 4);
		rightarm.setRotationPoint(4F, 22F, 0F);

		rightleg = new ModelRenderer(this, 0, 16);
		rightleg.addBox(-2F, 0F, -2F, 4, 12, 4);
		rightleg.setRotationPoint(2F, 12F, 0F);*/
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5);
        head.render(f5);
        head2.render(f5);
        body.render(f5);
        rightarm.render(f5);
        leftarm.render(f5);
        rightleg.render(f5);
        leftleg.render(f5);

    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6)
    {

        head.rotateAngleY = par4 / (180F / (float)Math.PI);
        head.rotateAngleX = par5 / (180F / (float)Math.PI);
        head2.rotateAngleY = par4 / (180F / (float)Math.PI);
        head2.rotateAngleX = par5 / (180F / (float)Math.PI);
        rightarm.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F;
		//rightarm.rotateAngleY = 3.141592653589793F * 2F;
        leftarm.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
        rightleg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
        leftleg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;

/*
		body.rotateAngleZ = 3.141592653589793F;
		head.rotateAngleZ = 3.141592653589793F;
		head2.rotateAngleZ = 3.141592653589793F;
		leftarm.rotateAngleX = 3.141592653589793F;
		leftarm.rotateAngleY = 3.141592653589793F;
		leftleg.rotateAngleX = 3.141592653589793F;
		leftleg.rotateAngleY = 3.141592653589793F;
		rightarm.rotateAngleX = 3.141592653589793F;
		rightarm.rotateAngleY = 3.141592653589793F;
		rightleg.rotateAngleX = 3.141592653589793F;
		rightleg.rotateAngleY = 3.141592653589793F;
*/

    }
}

/*
		//variables init:
		public ModelRenderer body;
		public ModelRenderer head;
		public ModelRenderer head2;
		public ModelRenderer leftarm;
		public ModelRenderer leftleg;
		public ModelRenderer rightarm;
		public ModelRenderer rightleg;

		head = new ModelRenderer(this, 0, 0);
        head.addBox(-4F, -8F, -4F, 8, 8, 8);
        head.setPosition(0F, 0F, 0F);
        head.setTextureSize(64, 32);
        head.mirror = true;
        head2 = new ModelRenderer(this, 32, 0);
		head2.addBox(-4F, -8F, -4F, 8, 8, 8, 0.5F);
		head2.setPosition(0F, 0F, 0F);
        head2.setTextureSize(64, 32);

        body = new ModelRenderer(this, 16, 16);
        body.addBox(-4F, 0F, -2F, 8, 12, 4);
        body.setPosition(0F, 0F, 0F);
        body.setTextureSize(64, 32);
        setRotation(body, 0F, 0F, 0F);
        rightarm = new ModelRenderer(this, 40, 16);
        rightarm.addBox(-3F, -2F, -2F, 4, 12, 4);
        rightarm.setPosition(-5F, 2F, 0F);
        rightarm.setTextureSize(64, 32);
        setRotation(rightarm, 0F, 0F, 0F);
        leftarm = new ModelRenderer(this, 40, 16);
        leftarm.mirror = true;
        leftarm.addBox(-1F, -2F, -2F, 4, 12, 4);
        leftarm.setPosition(5F, 2F, 0F);
        leftarm.setTextureSize(64, 32);
        setRotation(leftarm, 0F, 0F, 0F);
        rightleg = new ModelRenderer(this, 0, 16);
        rightleg.mirror = true;
        rightleg.addBox(-2F, 0F, -2F, 4, 12, 4);
        rightleg.setPosition(-2F, 12F, 0F);
        rightleg.setTextureSize(64, 32);
        setRotation(rightleg, 0F, 0F, 0F);
        leftleg = new ModelRenderer(this, 0, 16);
        leftleg.addBox(-2F, 0F, -2F, 4, 12, 4);
        leftleg.setPosition(2F, 12F, 0F);
        leftleg.setTextureSize(64, 32);
        setRotation(leftleg, 0F, 0F, 0F);

        head.render(f5);
        head2.render(f5);
        body.render(f5);
        rightarm.render(f5);
        leftarm.render(f5);
        rightleg.render(f5);
        leftleg.render(f5);

*/