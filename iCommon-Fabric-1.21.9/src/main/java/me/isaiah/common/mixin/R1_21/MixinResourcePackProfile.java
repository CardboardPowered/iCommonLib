package me.isaiah.common.mixin.R1_21;


import java.io.IOException;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import me.isaiah.common.ICommonMod;
import me.isaiah.common.cmixin.IMixinResourcePackProfile;
import net.minecraft.resource.ResourcePack;
import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.resource.metadata.PackResourceMetadata;

@Deprecated
@Mixin(ResourcePackProfile.class)
public class MixinResourcePackProfile implements IMixinResourcePackProfile {

	// Lnet/minecraft/resource/ResourcePackProfile;packFactory:Lnet/minecraft/resource/ResourcePackProfile$PackFactory;
	@Shadow
	public ResourcePackProfile.PackFactory packFactory;
	
	@Override
	public ResourcePack IC$open_pack(String id) {
		ResourcePackProfile handle = ((ResourcePackProfile)(Object)this);
		try (ResourcePack pack = packFactory.open(handle.getInfo())) {
        	return pack;
			//this.resourcePackInfo = pack.parseMetadata(PackResourceMetadata.SERIALIZER);
        } catch (Exception e) {
        	throw new RuntimeException(e);
        }
	}

	@Override
	public PackResourceMetadata IC$open_and_parse_metadata() {
		ResourcePackProfile handle = ((ResourcePackProfile)(Object)this);
		try (ResourcePack pack = packFactory.open(handle.getInfo())) {
			
        	return pack.parseMetadata(PackResourceMetadata.SERVER_DATA_SERIALIZER);
        } catch (IOException e) {
        	throw new RuntimeException(e);
        }
	}

	@Override
	public String IC$get_raw_id() {
		return ((ResourcePackProfile)(Object)this).getId();
	}

	@Override
	public boolean IC$is_required() {
		return ((ResourcePackProfile)(Object)this).isRequired();
	}

	@Override
	public boolean IC$is_enabled() {
		return ICommonMod.getIServer().getMinecraft().getDataPackManager().getEnabledIds().contains(this.IC$get_raw_id());
	}

}
