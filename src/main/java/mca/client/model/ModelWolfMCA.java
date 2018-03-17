/**
 * 
 */
package mca.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * @author Michael M. Adkins
 *
 */
public class ModelWolfMCA extends ModelBase {
	ModelRenderer MouthB;
	ModelRenderer Nose2;
	ModelRenderer Neck;
	ModelRenderer Neck2;
	ModelRenderer LSide;
	ModelRenderer RSide;
	ModelRenderer REar2;
	ModelRenderer Nose;
	ModelRenderer Mouth;
	ModelRenderer MouthOpen;
	ModelRenderer REar;
	ModelRenderer LEar2;
	ModelRenderer LEar;
	ModelRenderer UTeeth;
	ModelRenderer LTeeth;
	ModelRenderer Chest;
	ModelRenderer Body;
	ModelRenderer TailA;
	ModelRenderer TailB;
	ModelRenderer TailC;
	ModelRenderer TailD;
	ModelRenderer Leg4A;
	ModelRenderer Leg4D;
	ModelRenderer Leg4B;
	ModelRenderer Leg4C;
	ModelRenderer Leg3B;
	ModelRenderer Leg2A;
	ModelRenderer Leg2B;
	ModelRenderer Leg2C;
	ModelRenderer Leg3D;
	ModelRenderer Leg3C;
	ModelRenderer Leg3A;
	ModelRenderer Leg1A;
	ModelRenderer Leg1B;
	ModelRenderer Leg1C;
	private ModelRenderer Head;

	public ModelWolfMCA() {
		super();
		this.Head = new ModelRenderer(this, 0, 0);
		this.Head.addBox(-4.0F, -3.0F, -6.0F, 8, 8, 6);

        this.MouthB = new ModelRenderer(this, 16, 33);
		this.MouthB.addBox(-2.0F, 4.0F, -7.0F, 4, 1, 2);

        this.Nose2 = new ModelRenderer(this, 0, 25);
        this.Nose2.addBox(-2.0F, 2.0F, -12.0F, 4, 2, 6);
        
        this.Neck = new ModelRenderer(this, 28, 0);
        this.Neck.addBox(-3.5F, -3.0F, -7.0F, 7, 8, 7);
        
        this.setRotation(this.Neck, -0.4537856F, 0.0F, 0.0F);
        this.Neck2 = new ModelRenderer(this, 0, 14);
		this.Neck2.addBox(-1.5F, -2.0F, -5.0F, 3, 4, 7);
		// this.Neck2.setRotationPoint(0.0F, 14.0F, -10.0F);
        this.setRotation(this.Neck2, -0.4537856F, 0.0F, 0.0F);
        this.LSide = new ModelRenderer(this, 28, 33);
		this.LSide.addBox(3.0F, -0.5F, -2.0F, 2, 6, 6);
		// this.LSide.setRotationPoint(0.0F, 7.0F, -10.0F);
        this.setRotation(this.LSide, -0.2094395F, 0.418879F, -0.0872665F);
        this.RSide = new ModelRenderer(this, 28, 45);
		this.RSide.addBox(-5.0F, -0.5F, -2.0F, 2, 6, 6);
		this.RSide.setRotationPoint(0.0F, 7.0F, -10.0F);
        this.setRotation(this.RSide, -0.2094395F, -0.418879F, 0.0872665F);
        this.Nose = new ModelRenderer(this, 44, 33);
		this.Nose.addBox(-1.5F, -1.8F, -12.4F, 3, 2, 7);
		this.Nose.setRotationPoint(0.0F, 7.0F, -10.0F);
        this.setRotation(this.Nose, 0.2792527F, 0.0F, 0.0F);
        this.Mouth = new ModelRenderer(this, 1, 34);
		this.Mouth.addBox(-2.0F, 4.0F, -11.5F, 4, 1, 5);
		this.Mouth.setRotationPoint(0.0F, 7.0F, -10.0F);
        this.UTeeth = new ModelRenderer(this, 46, 18);
		this.UTeeth.addBox(-2.0F, 4.0F, -12.0F, 4, 2, 5);
		this.UTeeth.setRotationPoint(0.0F, 7.0F, -10.0F);
        this.LTeeth = new ModelRenderer(this, 20, 109);
		this.LTeeth.addBox(-1.5F, -12.9F, 1.2F, 3, 5, 2);
		this.LTeeth.setRotationPoint(0.0F, 7.0F, -10.0F);
        this.setRotation(this.LTeeth, 2.5307274F, 0.0F, 0.0F);
        this.MouthOpen = new ModelRenderer(this, 42, 69);
		this.MouthOpen.addBox(-1.5F, -12.9F, -0.81F, 3, 9, 2);
		this.MouthOpen.setRotationPoint(0.0F, 7.0F, -10.0F);
        this.setRotation(this.MouthOpen, 2.5307274F, 0.0F, 0.0F);
        this.REar = new ModelRenderer(this, 22, 0);
		this.REar.addBox(-3.5F, -7.0F, -1.5F, 3, 5, 1);
		this.REar.setRotationPoint(0.0F, 7.0F, -10.0F);
        this.setRotation(this.REar, 0.0F, 0.0F, -0.1745329F);
        this.LEar = new ModelRenderer(this, 13, 14);
		this.LEar.addBox(0.5F, -7.0F, -1.5F, 3, 5, 1);
		this.LEar.setRotationPoint(0.0F, 7.0F, -10.0F);
        this.setRotation(this.LEar, 0.0F, 0.0F, 0.1745329F);
        this.Chest = new ModelRenderer(this, 20, 15);
		this.Chest.addBox(-4.0F, -11.0F, -12.0F, 8, 8, 10);
		this.Chest.setRotationPoint(0.0F, 5.0F, 2.0F);
        this.setRotation(this.Chest, 1.570796F, 0.0F, 0.0F);
        this.Body = new ModelRenderer(this, 0, 40);
		this.Body.addBox(-3.0F, -8.0F, -9.0F, 6, 16, 8);
		this.Body.setRotationPoint(0.0F, 6.5F, 2.0F);
        this.setRotation(this.Body, 1.570796F, 0.0F, 0.0F);
        this.TailA = new ModelRenderer(this, 52, 42);
		this.TailA.addBox(-1.5F, 0.0F, -1.5F, 3, 4, 3);
		this.TailA.setRotationPoint(0.0F, 8.5F, 9.0F);
        this.setRotation(this.TailA, 1.064651F, 0.0F, 0.0F);
        this.TailB = new ModelRenderer(this, 48, 49);
		this.TailB.addBox(-2.0F, 3.0F, -1.0F, 4, 6, 4);
		this.TailB.setRotationPoint(0.0F, 8.5F, 9.0F);
        this.setRotation(this.TailB, 0.7504916F, 0.0F, 0.0F);
        this.TailC = new ModelRenderer(this, 48, 59);
		this.TailC.addBox(-2.0F, 7.8F, -4.1F, 4, 6, 4);
		this.TailC.setRotationPoint(0.0F, 8.5F, 9.0F);
        this.setRotation(this.TailC, 1.099557F, 0.0F, 0.0F);
        this.TailD = new ModelRenderer(this, 52, 69);
		this.TailD.addBox(-1.5F, 9.8F, -3.6F, 3, 5, 3);
		this.TailD.setRotationPoint(0.0F, 8.5F, 9.0F);
        this.setRotation(this.TailD, 1.099557F, 0.0F, 0.0F);
        this.Leg1A = new ModelRenderer(this, 28, 57);
		this.Leg1A.addBox(0.01F, -4.0F, -2.5F, 2, 8, 4);
		this.Leg1A.setRotationPoint(4.0F, 12.5F, -5.5F);
        this.setRotation(this.Leg1A, 0.2617994F, 0.0F, 0.0F);
        this.Leg1B = new ModelRenderer(this, 28, 69);
		this.Leg1B.addBox(0.0F, 3.2F, 0.5F, 2, 8, 2);
		this.Leg1B.setRotationPoint(4.0F, 12.5F, -5.5F);
        this.setRotation(this.Leg1B, -0.1745329F, 0.0F, 0.0F);
        this.Leg1C = new ModelRenderer(this, 28, 79);
		this.Leg1C.addBox(-0.5066667F, 9.5F, -2.5F, 3, 2, 3);
		this.Leg1C.setRotationPoint(4.0F, 12.5F, -5.5F);
        this.Leg2A = new ModelRenderer(this, 28, 84);
		this.Leg2A.addBox(-2.01F, -4.0F, -2.5F, 2, 8, 4);
		this.Leg2A.setRotationPoint(-4.0F, 12.5F, -5.5F);
        this.setRotation(this.Leg2A, 0.2617994F, 0.0F, 0.0F);
        this.Leg2B = new ModelRenderer(this, 28, 96);
		this.Leg2B.addBox(-2.0F, 3.2F, 0.5F, 2, 8, 2);
		this.Leg2B.setRotationPoint(-4.0F, 12.5F, -5.5F);
        this.setRotation(this.Leg2B, -0.1745329F, 0.0F, 0.0F);
        this.Leg2C = new ModelRenderer(this, 28, 106);
		this.Leg2C.addBox(-2.506667F, 9.5F, -2.5F, 3, 2, 3);
		this.Leg2C.setRotationPoint(-4.0F, 12.5F, -5.5F);
        this.Leg3A = new ModelRenderer(this, 0, 64);
		this.Leg3A.addBox(0.0F, -3.8F, -3.5F, 2, 7, 5);
		this.Leg3A.setRotationPoint(3.0F, 12.5F, 7.0F);
        this.setRotation(this.Leg3A, -0.3665191F, 0.0F, 0.0F);
        this.Leg3B = new ModelRenderer(this, 0, 76);
		this.Leg3B.addBox(-0.1F, 1.9F, -1.8F, 2, 2, 5);
		this.Leg3B.setRotationPoint(3.0F, 12.5F, 7.0F);
        this.setRotation(this.Leg3B, -0.7330383F, 0.0F, 0.0F);
        this.Leg3C = new ModelRenderer(this, 0, 83);
		this.Leg3C.addBox(0.0F, 3.2F, 0.0F, 2, 8, 2);
		this.Leg3C.setRotationPoint(3.0F, 12.5F, 7.0F);
        this.setRotation(this.Leg3C, -0.1745329F, 0.0F, 0.0F);
        this.Leg3D = new ModelRenderer(this, 0, 93);
		this.Leg3D.addBox(-0.5066667F, 9.5F, -3.0F, 3, 2, 3);
		this.Leg3D.setRotationPoint(3.0F, 12.5F, 7.0F);
        this.Leg4A = new ModelRenderer(this, 14, 64);
		this.Leg4A.addBox(-2.0F, -3.8F, -3.5F, 2, 7, 5);
		this.Leg4A.setRotationPoint(-3.0F, 12.5F, 7.0F);
        this.setRotation(this.Leg4A, -0.3665191F, 0.0F, 0.0F);
        this.Leg4B = new ModelRenderer(this, 14, 76);
		this.Leg4B.addBox(-1.9F, 1.9F, -1.8F, 2, 2, 5);
		this.Leg4B.setRotationPoint(-3.0F, 12.5F, 7.0F);
        this.setRotation(this.Leg4B, -0.7330383F, 0.0F, 0.0F);
        this.Leg4C = new ModelRenderer(this, 14, 83);
		this.Leg4C.addBox(-2.0F, 3.2F, 0.0F, 2, 8, 2);
		this.Leg4C.setRotationPoint(-3.0F, 12.5F, 7.0F);
        this.setRotation(this.Leg4C, -0.1745329F, 0.0F, 0.0F);
        this.Leg4D = new ModelRenderer(this, 14, 93);
		this.Leg4D.addBox(-2.506667F, 9.5F, -3.0F, 3, 2, 3);

	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	/**
	 * 
	 * @see net.minecraft.client.model.ModelQuadruped#render(net.minecraft.entity.Entity,
	 *      float, float, float, float, float, float)
	 */
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, boolean tail) {
		this.Head.rotateAngleX = f4 / 57.29578F;
		this.Head.rotateAngleY = f3 / 57.29578F;
		float LLegX = MathHelper.abs(f * 0.6662F) * 0.8F * f1;
		float RLegX = MathHelper.abs(f * 0.6662F + 3.141593F) * 0.8F * f1;
		this.Mouth.rotateAngleX = this.Head.rotateAngleX;
		this.Mouth.rotateAngleY = this.Head.rotateAngleY;
		this.MouthB.rotateAngleX = this.Head.rotateAngleX;
		this.MouthB.rotateAngleY = this.Head.rotateAngleY;
		this.UTeeth.rotateAngleX = this.Head.rotateAngleX;
		this.UTeeth.rotateAngleY = this.Head.rotateAngleY;
		this.MouthOpen.rotateAngleX = 2.5307274F + this.Head.rotateAngleX;
		this.MouthOpen.rotateAngleY = this.Head.rotateAngleY;
		this.LTeeth.rotateAngleX = 2.5307274F + this.Head.rotateAngleX;
		this.LTeeth.rotateAngleY = this.Head.rotateAngleY;
		this.Nose.rotateAngleX = 0.27925268F + this.Head.rotateAngleX;
		this.Nose.rotateAngleY = this.Head.rotateAngleY;
		this.Nose2.rotateAngleX = this.Head.rotateAngleX;
		this.Nose2.rotateAngleY = this.Head.rotateAngleY;
		this.LSide.rotateAngleX = -0.2094395F + this.Head.rotateAngleX;
		this.LSide.rotateAngleY = 0.418879F + this.Head.rotateAngleY;
		this.RSide.rotateAngleX = -0.2094395F + this.Head.rotateAngleX;
		this.RSide.rotateAngleY = -0.418879F + this.Head.rotateAngleY;
		this.REar.rotateAngleX = this.Head.rotateAngleX;
		this.REar.rotateAngleY = this.Head.rotateAngleY;
		this.LEar.rotateAngleX = this.Head.rotateAngleX;
		this.LEar.rotateAngleY = this.Head.rotateAngleY;
		this.Leg1A.rotateAngleX = 0.2617994F + LLegX;
		this.Leg1B.rotateAngleX = -0.17453292F + LLegX;
		this.Leg1C.rotateAngleX = LLegX;
		this.Leg2A.rotateAngleX = 0.2617994F + RLegX;
		this.Leg2B.rotateAngleX = -0.17453292F + RLegX;
		this.Leg2C.rotateAngleX = RLegX;
		this.Leg3A.rotateAngleX = -0.36651915F + RLegX;
		this.Leg3B.rotateAngleX = -0.7330383F + RLegX;
		this.Leg3C.rotateAngleX = -0.17453292F + RLegX;
		this.Leg3D.rotateAngleX = RLegX;
		this.Leg4A.rotateAngleX = -0.36651915F + LLegX;
		this.Leg4B.rotateAngleX = -0.7330383F + LLegX;
		this.Leg4C.rotateAngleX = -0.17453292F + LLegX;
		this.Leg4D.rotateAngleX = LLegX;
		float tailMov = -1.3089F + f1 * 1.5F;
		if (tail) {
			this.TailA.rotateAngleY = MathHelper.abs(f2 * 0.5F);
			tailMov = 0.0F;
		}
		else {
			this.TailA.rotateAngleY = 0.0F;
		}

		this.TailA.rotateAngleX = 1.0647582F - tailMov;
		this.TailB.rotateAngleX = 0.75056726F - tailMov;
		this.TailC.rotateAngleX = 1.0996684F - tailMov;
		this.TailD.rotateAngleX = 1.0996684F - tailMov;
		this.TailB.rotateAngleY = this.TailA.rotateAngleY;
		this.TailC.rotateAngleY = this.TailA.rotateAngleY;
		this.TailD.rotateAngleY = this.TailA.rotateAngleY;
	}
}
