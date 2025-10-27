package me.isaiah.common;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

// import net.minecraft.registry.Registries;
// import net.minecraft.registry.Registry;
// import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class AttributeMappings {

    private static final Map<UUID, Identifier> ATTRIBUTE_MODIFIER_IDS = new HashMap<UUID, Identifier>();
    private static final Map<String, UUID> ATTRIBUTE_UUIDS = new HashMap<String, UUID>();


    public AttributeMappings() {
    }

    public static Identifier uuid_to_key(UUID uuid) {
    	Identifier key = ATTRIBUTE_MODIFIER_IDS.get(uuid);
        if (key != null) {
            return key;
        }
        return Identifier.of((String)uuid.toString());
    }
    
    public static UUID id_to_uuid(Identifier id) {
    	UUID key = ATTRIBUTE_UUIDS.get(id.toString());
    	if (null == key) {
    		for (UUID map_key : ATTRIBUTE_MODIFIER_IDS.keySet()) {
    			Identifier ident = ATTRIBUTE_MODIFIER_IDS.get(map_key);
    			if (ident.equals(id) || (id.getNamespace().equals(ident.getNamespace()) && id.getPath().equals(ident.getPath()))) {
    				ATTRIBUTE_UUIDS.put(id.toString(), map_key);
    				return map_key;
    			}
    		}
    	}
    	return key;
    }

    private static void add(long msb, long lsb, String id) {
        UUID uuid = new UUID(msb, lsb);
        ATTRIBUTE_MODIFIER_IDS.put(uuid, Identifier.of((String)id));
    }

    static {
        AttributeMappings.add(-4483571535397864886L, -5989644940537681742L, "armor.body");
        AttributeMappings.add(8144722948526719024L, -7778190119041365872L, "effect.slowness");
        AttributeMappings.add(6967552254039378640L, -9116175396973475259L, "enchantment.aqua_affinity");
        AttributeMappings.add(5279725409867744698L, -5150363631200102632L, "attacking");
        AttributeMappings.add(148071211714102867L, -7685811609035173472L, "attacking");
        AttributeMappings.add(6196088217904236654L, -7493791321850887290L, "effect.minining_fatigue");
        AttributeMappings.add(-5084161844451524480L, -8859020046251006329L, "enchantment.soul_speed");
        AttributeMappings.add(-7907339078496465106L, -8112074600724210224L, "enchantment.swift_sneak");
        AttributeMappings.add(6688265815086220243L, -6545541163342161890L, "drinking");
        AttributeMappings.add(8315164243412860989L, -6631520853640075966L, "creative_mode_block_range");
        AttributeMappings.add(4389663563256579765L, -4827163546944004714L, "enchantment.efficiency");
        AttributeMappings.add(6732612758648800940L, -5145707699103688244L, "effect.health_boost");
        AttributeMappings.add(9079981369298536661L, -6728494925450710401L, "covered");
        AttributeMappings.add(-1521481167610687786L, -8630419745734927691L, "effect.absorption");
        AttributeMappings.add(-7473408062188862076L, -5872005994337939597L, "creative_mode_entity_range");
        AttributeMappings.add(-3721396875562958315L, -5317020504214661337L, "effect.unluck");
        AttributeMappings.add(-2861585646493481178L, -6113244764726669811L, "armor.leggings");
        AttributeMappings.add(6718535547217657911L, -5386630269401489641L, "enchantment.sweeping_edge");
        AttributeMappings.add(-7949229004988660584L, -7828611303000832459L, "effect.speed");
        AttributeMappings.add(-8650171790042118250L, -5749650997644763080L, "enchantment.soul_speed");
        AttributeMappings.add(551224288813600377L, -8734740027710371860L, "enchantment.respiration");
        AttributeMappings.add(-7046399332347654691L, -6723081531683397260L, "suffocating");
        AttributeMappings.add(7361814797886573596L, -8641397326606817395L, "sprinting");
        AttributeMappings.add(-6972338111383059132L, -8978659762232839026L, "armor.chestplate");
        AttributeMappings.add(-5371971015925809039L, -6062243582569928137L, "enchantment.fire_protection");
        AttributeMappings.add(7245570952092733273L, -8449101711440750679L, "effect.strength");
        AttributeMappings.add(-422425648963762075L, -5756800103171642205L, "base_attack_speed");
        AttributeMappings.add(-4607081316629330256L, -7008565754814018370L, "effect.jump_boost");
        AttributeMappings.add(271280981090454338L, -8746077033958322898L, "effect.luck");
        AttributeMappings.add(2211131075215181206L, -5513857709499300658L, "powder_snow");
        AttributeMappings.add(-8908768238899017377L, -8313820693701227669L, "armor.boots");
        AttributeMappings.add(-5797418877589107702L, -6181652684028920077L, "effect.haste");
        AttributeMappings.add(3086076556416732775L, -5150312587563650736L, "armor.helmet");
        AttributeMappings.add(-5082757096938257406L, -4891139119377885130L, "baby");
        AttributeMappings.add(2478452629826324956L, -7247530463494186011L, "effect.weakness");
        AttributeMappings.add(4659420831966187055L, -5191473055587376048L, "enchantment.blast_protection");
        AttributeMappings.add(7301951777949303281L, -6753860660653972126L, "evil");
        AttributeMappings.add(8533189226688352746L, -8254757081029716377L, "baby");
        AttributeMappings.add(1286946037536540352L, -5768092872487507967L, "enchantment.depth_strider");
        AttributeMappings.add(-3801225194067177672L, -6586624321849018929L, "base_attack_damage");
    }

}
