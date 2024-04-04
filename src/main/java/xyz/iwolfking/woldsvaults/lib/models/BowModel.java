package xyz.iwolfking.woldsvaults.lib.models;

import iskallia.vault.dynamodel.DynamicModel;
import iskallia.vault.init.ModDynamicModels;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.HashMap;
import java.util.Map;

public class BowModel extends DynamicModel<BowModel> {
    public BowModel(ResourceLocation id, String displayName) {
        super(id, displayName);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public BlockModel generateItemModel(Map<String, ResourceLocation> textures) {
        String jsonPattern = "{\n" +
                "  \"parent\": \"item/generated\",\n" +
                "  \"textures\": {\n" +
                "    \"layer0\": \"minecraft:item/bow\"\n" +
                "  },\n" +
                "  \"display\": {\n" +
                "    \"thirdperson_righthand\": {\n" +
                "      \"rotation\": [\n" +
                "        -80,\n" +
                "        260,\n" +
                "        -40\n" +
                "      ],\n" +
                "      \"translation\": [\n" +
                "        -1,\n" +
                "        -2,\n" +
                "        2.5\n" +
                "      ],\n" +
                "      \"scale\": [\n" +
                "        0.9,\n" +
                "        0.9,\n" +
                "        0.9\n" +
                "      ]\n" +
                "    },\n" +
                "    \"thirdperson_lefthand\": {\n" +
                "      \"rotation\": [\n" +
                "        -80,\n" +
                "        -280,\n" +
                "        40\n" +
                "      ],\n" +
                "      \"translation\": [\n" +
                "        -1,\n" +
                "        -2,\n" +
                "        2.5\n" +
                "      ],\n" +
                "      \"scale\": [\n" +
                "        0.9,\n" +
                "        0.9,\n" +
                "        0.9\n" +
                "      ]\n" +
                "    },\n" +
                "    \"firstperson_righthand\": {\n" +
                "      \"rotation\": [\n" +
                "        0,\n" +
                "        -90,\n" +
                "        25\n" +
                "      ],\n" +
                "      \"translation\": [\n" +
                "        1.13,\n" +
                "        3.2,\n" +
                "        1.13\n" +
                "      ],\n" +
                "      \"scale\": [\n" +
                "        0.68,\n" +
                "        0.68,\n" +
                "        0.68\n" +
                "      ]\n" +
                "    },\n" +
                "    \"firstperson_lefthand\": {\n" +
                "      \"rotation\": [\n" +
                "        0,\n" +
                "        90,\n" +
                "        -25\n" +
                "      ],\n" +
                "      \"translation\": [\n" +
                "        1.13,\n" +
                "        3.2,\n" +
                "        1.13\n" +
                "      ],\n" +
                "      \"scale\": [\n" +
                "        0.68,\n" +
                "        0.68,\n" +
                "        0.68\n" +
                "      ]\n" +
                "    }\n" +
                "  },\n" +
                "  \"overrides\": [\n" +
                "    {\n" +
                "      \"predicate\": {\n" +
                "        \"pulling\": 1\n" +
                "      },\n" +
                "      \"model\": \"minecraft:item/bow_pulling_0\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"predicate\": {\n" +
                "        \"pulling\": 1,\n" +
                "        \"pull\": 0.65\n" +
                "      },\n" +
                "      \"model\": \"minecraft:item/bow_pulling_1\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"predicate\": {\n" +
                "        \"pulling\": 1,\n" +
                "        \"pull\": 0.9\n" +
                "      },\n" +
                "      \"model\": \"minecraft:item/bow_pulling_2\"\n" +
                "    },\n" +
                "{\n" +
                "            \"predicate\": {\n" +
                "                \"custom_model_data\": 1\n" +
                "            },\n" +
                "            \"model\": \"item/custom_bow\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"predicate\": {\n" +
                "                \"custom_model_data\": 1,\n" +
                "                \"pulling\": 1\n" +
                "            },\n" +
                "            \"model\": \"item/custom_bow_pulling_0\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"predicate\": {\n" +
                "                \"custom_model_data\": 1,\n" +
                "                \"pulling\": 1,\n" +
                "                \"pull\": 0.65\n" +
                "            },\n" +
                "            \"model\": \"item/custom_bow_pulling_1\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"predicate\": {\n" +
                "                \"custom_model_data\": 1,\n" +
                "                \"pulling\": 1,\n" +
                "                \"pull\": 0.9\n" +
                "            },\n" +
                "            \"model\": \"item/custom_bow_pulling_2\"\n" +
                "        }" +
                "  ]\n" +
                "}";
        return this.createUnbakedModel(jsonPattern, textures);
    }

    @Override
    protected BlockModel createUnbakedModel(String jsonPattern, Map<String, ResourceLocation> textures) {
//        String texturesJson = (String)textures.entrySet().stream().map((entry) -> {
//            if(entry.getKey().equals("bow_pulling_0") || entry.getKey().equals("bow_pulling_1") || entry.getKey().equals("bow_pulling_2")) {
//                return null;
//            }
//            String var10000 = (String)entry.getKey();
//            return "\"" + var10000 + "\": \"" + entry.getValue() + "\"";
//        }).collect(Collectors.joining(", ", "{", "}"));
//        String modelJson = jsonPattern.replace("{{textures}}", texturesJson);
//        modelJson = modelJson.replace("{{bow_pulling_0}}", textures.get("bow_pulling_model_0").toString());
//        modelJson = modelJson.replace("{{bow_pulling_1}}", textures.get("bow_pulling_model_1").toString());
//        modelJson = modelJson.replace("{{bow_pulling_2}}", textures.get("bow_pulling_model_2").toString());
//        System.out.println(modelJson);
//        WoldsVaults.LOGGER.info(modelJson);
        return BlockModel.fromString(jsonPattern);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public Map<String, ResourceLocation> resolveTextures(ResourceManager resourceManager, ResourceLocation resourceLocation) {
        HashMap<String, ResourceLocation> textures = new HashMap<>();
        textures.put("layer0", ModDynamicModels.textureExists(resourceManager, resourceLocation) ? resourceLocation : ModDynamicModels.EMPTY_TEXTURE);
        if (ModDynamicModels.textureExists(resourceManager, appendToId(resourceLocation, "_overlay"))) {
            textures.put("layer1", appendToId(resourceLocation, "_overlay"));
        }

        for(int i = 0; i < 10; ++i) {
            if (ModDynamicModels.textureExists(resourceManager, appendToId(resourceLocation, "_layer" + i))) {
                textures.put("layer" + i, appendToId(resourceLocation, "_layer" + i));
            }
        }

        String modelName = resourceLocation.toString().substring(resourceLocation.toString().lastIndexOf('/') + 1);
        if(ModDynamicModels.jsonModelExists(resourceManager, appendToId(resourceLocation, "_pulling_0"))) {
            textures.put("bow_pulling_model_0", new ResourceLocation("the_vault", "models/item/gear/bow/" + modelName + "_pulling_0"));
        }

        if(ModDynamicModels.jsonModelExists(resourceManager, appendToId(resourceLocation, "_pulling_1"))) {
            textures.put("bow_pulling_model_1", new ResourceLocation("the_vault", "models/item/gear/bow/" + modelName + "_pulling_1"));
        }

        if(ModDynamicModels.jsonModelExists(resourceManager, appendToId(resourceLocation, "_pulling_2"))) {
            textures.put("bow_pulling_model_2", new ResourceLocation("the_vault", "models/item/gear/bow/" + modelName + "_pulling_2"));
        }

        return textures;
    }
}
