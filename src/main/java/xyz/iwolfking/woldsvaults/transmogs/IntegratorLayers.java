package xyz.iwolfking.woldsvaults.transmogs;

import iskallia.vault.dynamodel.model.armor.ArmorLayers;
import iskallia.vault.dynamodel.model.armor.ArmorPieceModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public class IntegratorLayers extends ArmorLayers {
    @OnlyIn(Dist.CLIENT)
    /*     */   public Supplier<LayerDefinition> getGeometrySupplier(EquipmentSlot equipmentSlot) {
        /*  19 */     return (equipmentSlot == EquipmentSlot.LEGS) ?
                /*  20 */       LeggingsLayer::createBodyLayer :
                /*  21 */       MainLayer::createBodyLayer;
        /*     */   }
    /*     */
    /*     */
    /*     */   @OnlyIn(Dist.CLIENT)
    /*     */   public ArmorLayers.VaultArmorLayerSupplier<? extends ArmorLayers.BaseLayer> getLayerSupplier(EquipmentSlot equipmentSlot) {
        /*  27 */     return (equipmentSlot == EquipmentSlot.LEGS) ?
                /*  28 */       LeggingsLayer::new :
                /*  29 */       MainLayer::new;
        /*     */   }
    /*     */
    /*     */   public static class MainLayer
            /*     */     extends ArmorLayers.MainLayer {
        /*     */     public MainLayer(ArmorPieceModel definition, ModelPart root) {
            /*  35 */       super(definition, root);
            /*     */     }
        /*     */
        /*     */     public static LayerDefinition createBodyLayer() {
            /*  39 */       MeshDefinition meshdefinition = createBaseLayer();
            /*  40 */       PartDefinition partdefinition = meshdefinition.getRoot();
            /*     */
            /*  42 */       PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(66, 62).addBox(-5.0F, -9.0F, -5.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
/*  43 */           .texOffs(16, 66).addBox(3.0F, -9.0F, -5.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
/*  44 */           .texOffs(8, 66).addBox(3.0F, -9.0F, 3.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
/*  45 */           .texOffs(48, 64).addBox(-5.0F, -9.0F, 3.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
/*  46 */           .texOffs(10, 46).addBox(-5.0F, -9.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
/*  47 */           .texOffs(40, 44).addBox(3.0F, -9.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
/*  48 */           .texOffs(30, 42).addBox(3.0F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
/*  49 */           .texOffs(42, 26).addBox(-5.0F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
/*  50 */           .texOffs(64, 58).addBox(-3.0F, -1.0F, 3.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
/*  51 */           .texOffs(64, 46).addBox(-3.0F, -9.0F, 3.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
/*  52 */           .texOffs(64, 42).addBox(-3.0F, -9.0F, -5.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
/*  53 */           .texOffs(32, 64).addBox(-3.0F, -1.0F, -5.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
            /*     */
            /*  55 */       PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(60, 36).addBox(-3.0F, 10.0F, -5.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
/*  56 */           .texOffs(58, 30).addBox(-3.0F, 2.0F, -5.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
/*  57 */           .texOffs(56, 12).addBox(-3.0F, 2.0F, 3.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
/*  58 */           .texOffs(56, 4).addBox(-3.0F, 10.0F, 3.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
/*  59 */           .texOffs(42, 18).addBox(-5.0F, 10.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
/*  60 */           .texOffs(20, 40).addBox(3.0F, 10.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
/*  61 */           .texOffs(40, 8).addBox(-5.0F, 2.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
/*  62 */           .texOffs(40, 0).addBox(3.0F, 2.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
/*  63 */           .texOffs(24, 62).addBox(-5.0F, 2.0F, 3.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
/*  64 */           .texOffs(58, 60).addBox(3.0F, 2.0F, 3.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
/*  65 */           .texOffs(0, 60).addBox(3.0F, 2.0F, -5.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
/*  66 */           .texOffs(58, 48).addBox(-5.0F, 2.0F, -5.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
            /*     */
            /*  68 */       PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(50, 40).addBox(-8.0F, 3.0F, -5.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
/*  69 */           .texOffs(50, 8).addBox(-8.0F, -5.0F, -5.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
/*  70 */           .texOffs(50, 0).addBox(-8.0F, -5.0F, 3.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
/*  71 */           .texOffs(46, 34).addBox(-8.0F, 3.0F, 3.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
/*  72 */           .texOffs(32, 24).addBox(-10.0F, 3.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
/*  73 */           .texOffs(32, 16).addBox(-2.0F, 3.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
/*  74 */           .texOffs(16, 30).addBox(-10.0F, -5.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
/*  75 */           .texOffs(34, 50).addBox(-10.0F, -5.0F, 3.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
/*  76 */           .texOffs(22, 22).addBox(-2.0F, -5.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
/*  77 */           .texOffs(26, 50).addBox(-2.0F, -5.0F, 3.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
/*  78 */           .texOffs(0, 48).addBox(-2.0F, -5.0F, -5.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
/*  79 */           .texOffs(16, 0).addBox(-10.0F, -5.0F, -5.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
/*  80 */           .texOffs(24, 0).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(1.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));
            /*     */
            /*  82 */       PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(52, 26).addBox(2.0F, 3.0F, -5.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
/*  83 */           .texOffs(52, 20).addBox(2.0F, -5.0F, -5.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
/*  84 */           .texOffs(52, 16).addBox(2.0F, -5.0F, 3.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
/*  85 */           .texOffs(50, 44).addBox(2.0F, 3.0F, 3.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
/*  86 */           .texOffs(0, 40).addBox(0.0F, 3.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
/*  87 */           .texOffs(10, 38).addBox(8.0F, 3.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
/*  88 */           .texOffs(36, 34).addBox(0.0F, -5.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
/*  89 */           .texOffs(16, 54).addBox(0.0F, -5.0F, 3.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
/*  90 */           .texOffs(26, 32).addBox(8.0F, -5.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
/*  91 */           .texOffs(8, 54).addBox(8.0F, -5.0F, 3.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
/*  92 */           .texOffs(50, 52).addBox(8.0F, -5.0F, -5.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
/*  93 */           .texOffs(42, 52).addBox(0.0F, -5.0F, -5.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
/*  94 */           .texOffs(0, 0).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(1.0F)), PartPose.offset(5.0F, 2.0F, 0.0F));
            /*     */
            /*  96 */       PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 24).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(1.0F)), PartPose.offset(-1.9F, 12.0F, 0.0F));
            /*     */
            /*  98 */       PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(12, 12).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(1.0F)), PartPose.offset(1.9F, 12.0F, 0.0F));
            /*     */
            /* 100 */       return LayerDefinition.create(meshdefinition, 128, 128);
            /*     */     }
        /*     */   }
    /*     */
    /*     */   public static class LeggingsLayer
            /*     */     extends ArmorLayers.LeggingsLayer
            /*     */   {
        /*     */     public LeggingsLayer(ArmorPieceModel definition, ModelPart root) {
            /* 108 */       super(definition, root);
            /*     */     }
        /*     */
        /*     */     public static LayerDefinition createBodyLayer() {
            /* 112 */       MeshDefinition meshdefinition = createBaseLayer();
            /* 113 */       PartDefinition partdefinition = meshdefinition.getRoot();
            /*     */
            /* 115 */       PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.51F))
/* 116 */           .texOffs(0, 16).addBox(-2.0F, 12.0F, 2.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
/* 117 */           .texOffs(0, 16).addBox(-2.0F, 10.0F, 2.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
/* 118 */           .texOffs(0, 0).addBox(1.0F, 11.0F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
/* 119 */           .texOffs(0, 0).addBox(-2.0F, 11.0F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
            /*     */
            /* 121 */       PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(-1.9F, 12.0F, 0.0F));
            /*     */
            /* 123 */       PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)).mirror(false), PartPose.offset(1.9F, 12.0F, 0.0F));
            /*     */
            /* 125 */       return LayerDefinition.create(meshdefinition, 32, 32);
            /*     */     }
        /*     */   }
}
