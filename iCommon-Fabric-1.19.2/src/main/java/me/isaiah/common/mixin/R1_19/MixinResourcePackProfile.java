package me.isaiah.common.mixin.R1_19;


import org.spongepowered.asm.mixin.Mixin;

import me.isaiah.common.ICommonMod;
import me.isaiah.common.cmixin.IMixinResourcePackProfile;
import net.minecraft.resource.ResourcePack;
import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.resource.metadata.PackResourceMetadata;

@Mixin(ResourcePackProfile.class)
public class MixinResourcePackProfile implements IMixinResourcePackProfile {

	@Override
	public ResourcePack IC$open_pack(String id) {
		// No on <= 1.20.2
		
		ResourcePackProfile handle = ((ResourcePackProfile)(Object)this);
		
		/*try (ResourcePack pack = handle.packFactory.open(this.handle.getId())) {
        	this.resourcePackInfo = pack.parseMetadata(PackResourceMetadata.SERIALIZER);
        } catch (IOException e) { // This is already called in NMS then if in NMS not happen is secure this not throw here
        	throw new RuntimeException(e);
        }*/
		return null;
	}

	@Override
	public PackResourceMetadata IC$open_and_parse_metadata() {
		// No on <= 1.20.2
		return null;
	}

	@Override
	public String IC$get_raw_id() {
		return ((ResourcePackProfile)(Object)this).getName();
	}

	@Override
	public boolean IC$is_required() {
		return ((ResourcePackProfile)(Object)this).isAlwaysEnabled();
	}

	@Override
	public boolean IC$is_enabled() {
		return ICommonMod.getIServer().getMinecraft().getDataPackManager().getEnabledNames().contains(this.IC$get_raw_id());
	}

}
