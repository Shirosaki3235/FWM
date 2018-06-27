package Fantasy_World.mod.model.entity.mob;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class seyana_model extends ModelBase {
	//variables init:
	public ModelRenderer body;
	public ModelRenderer head;
	public ModelRenderer hier1;
	public ModelRenderer hier2;
	public ModelRenderer hier3;
	public ModelRenderer hier4;


    public seyana_model()
    {/*
    	body = new ModelRenderer(this, TEX座標x, TEX座標y);
        body.addBox(オフセっとxF, オフセっとyF, オフセっとzF, 幅, 高さ, 奥行);
        body.setRotationPoint(ベースxF, ベースyF, ベースzF);
        */
        textureWidth = 64;
        textureHeight = 32;
       //constructor:
        body = new ModelRenderer(this, 0, 18);
        body.addBox(-5F, -2F, -6F, 10, 2, 12);
        body.setRotationPoint(0F, 24F, 0F);

        head = new ModelRenderer(this, 0, 4);
        head.addBox(-4F, -6F, -3F, 8, 6, 8);
        head.setRotationPoint(0F, 22F, 0F);

        hier1 = new ModelRenderer(this, 0, 18);
        hier1.addBox(0F, -1F, 0F, 3, 2, 0);
        hier1.setRotationPoint(4F, 16F, 1F);

        hier2 = new ModelRenderer(this, 0, 20);
        hier2.addBox(0F, -1F, 0F, 3, 2, 0);
        hier2.setRotationPoint(4F, 16F, 1F);

        hier3 = new ModelRenderer(this, 0, 22);
        hier3.addBox(-1F, -6F, 0F, 1, 5, 0);
        hier3.setRotationPoint(4F, 16F, 1F);

        hier4 = new ModelRenderer(this, 2, 22);
        hier4.addBox(-1F, -6F, 0F, 1, 5, 0);
        hier4.setRotationPoint(4F, 16F, 1F);

    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5);
        //render:
        body.render(f5);
        head.render(f5);
        hier1.render(f5);
        hier2.render(f5);
        hier3.render(f5);
        hier4.render(f5);

    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6)
    {
    	hier1.rotateAngleX = 6.161012259539984F;
    	hier1.rotateAngleY = 5.934119456780721F;
    	hier1.rotateAngleZ = 2.7401669256310974F*2F;
    	hier2.rotateAngleX = 6.161012259539984F;
    	hier2.rotateAngleY = 5.934119456780721F;
    	hier2.rotateAngleZ = 3.246312408709453F*2F;
    	hier3.rotateAngleX = 6.161012259539984F;
    	hier3.rotateAngleY = 5.934119456780721F;
    	hier3.rotateAngleZ = 2.8099800957108707F;
    	hier4.rotateAngleX = 6.161012259539984F;
    	hier4.rotateAngleY = 5.934119456780721F;
    	hier4.rotateAngleZ = 3.01941960595019F;

    }
}

/*
//variables init:
public ModelRenderer body;
public ModelRenderer head;
public ModelRenderer hier1;
public ModelRenderer hier2;
public ModelRenderer hier3;
public ModelRenderer hier4;

//constructor:
body = new ModelRenderer(0, 18);
body.addBox(-5F, -2F, -6F, 10, 2, 12);
body.setRotationPoint(0F, 24F, 0F);

head = new ModelRenderer(0, 4);
head.addBox(-4F, -6F, -3F, 8, 6, 8);
head.setRotationPoint(0F, 24F, 0F);

hier1 = new ModelRenderer(0, 18);
hier1.addBox(0F, -1F, 0F, 3, 2, 0);
hier1.setRotationPoint(-4F, 18F, 1F);

hier2 = new ModelRenderer(0, 20);
hier2.addBox(0F, -1F, 0F, 3, 2, 0);
hier2.setRotationPoint(-4F, 18F, 1F);

hier3 = new ModelRenderer(0, 22);
hier3.addBox(0F, -6F, 0F, 1, 5, 0);
hier3.setRotationPoint(-4F, 18F, 1F);

hier4 = new ModelRenderer(2, 22);
hier4.addBox(0F, -6F, 0F, 1, 5, 0);
hier4.setRotationPoint(-4F, 18F, 1F);

hier1.rotateAngleX = 6.161012259539984F;
hier1.rotateAngleY = 5.934119456780721F;
hier1.rotateAngleZ = 2.7401669256310974F;
hier2.rotateAngleX = 6.161012259539984F;
hier2.rotateAngleY = 5.934119456780721F;
hier2.rotateAngleZ = 3.246312408709453F;
hier3.rotateAngleX = 6.161012259539984F;
hier3.rotateAngleY = 5.934119456780721F;
hier3.rotateAngleZ = 2.8099800957108707F;
hier4.rotateAngleX = 6.161012259539984F;
hier4.rotateAngleY = 5.934119456780721F;
hier4.rotateAngleZ = 3.01941960595019F;

//render:
body.render(f5);
head.render(f5);
hier1.render(f5);
hier2.render(f5);
hier3.render(f5);
hier4.render(f5);

//variables init:
public ModelRenderer body;
public ModelRenderer head;
public ModelRenderer hier1;
public ModelRenderer hier2;
public ModelRenderer hier3;
public ModelRenderer hier4;

//constructor:
body = new ModelRenderer(0, 18);
body.addBox(-5F, -2F, -6F, 10, 2, 12);
body.setPosition(0F, 24F, 0F);

head = new ModelRenderer(0, 4);
head.addBox(-4F, -6F, -3F, 8, 6, 8);
head.setPosition(0F, 24F, 0F);

hier1 = new ModelRenderer(0, 18);
hier1.addBox(0F, -1F, 0F, 3, 2, 0);
hier1.setPosition(-4F, 18F, 1F);
hier1.rotateAngleX = 6.161012259539984F;
hier1.rotateAngleY = 5.934119456780721F;
hier1.rotateAngleZ = 2.7401669256310974F;

hier2 = new ModelRenderer(0, 20);
hier2.addBox(0F, -1F, 0F, 3, 2, 0);
hier2.setPosition(-4F, 18F, 1F);
hier2.rotateAngleX = 6.161012259539984F;
hier2.rotateAngleY = 5.934119456780721F;
hier2.rotateAngleZ = 3.246312408709453F;

hier3 = new ModelRenderer(0, 22);
hier3.addBox(0F, -6F, 0F, 1, 5, 0);
hier3.setPosition(-4F, 18F, 1F);
hier3.rotateAngleX = 6.161012259539984F;
hier3.rotateAngleY = 5.934119456780721F;
hier3.rotateAngleZ = 3.2986722862692828F;
hier4.rotateAngleX = 6.161012259539984F;
hier4.rotateAngleY = 5.934119456780721F;
hier4.rotateAngleZ = 3.4382986264288293F;

hier4 = new ModelRenderer(2, 22);
hier4.addBox(0F, -6F, 0F, 1, 5, 0);
hier4.setPosition(-4F, 18F, 1F);

//render:
body.render(f5);
head.render(f5);
hier1.render(f5);
hier2.render(f5);
hier3.render(f5);
hier4.render(f5);



setPosition

*/