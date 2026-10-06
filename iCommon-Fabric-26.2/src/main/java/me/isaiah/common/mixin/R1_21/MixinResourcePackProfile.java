package me.isaiah.common.mixin.R1_21;


import java.io.IOException;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import me.isaiah.common.ICommonMod;
import me.isaiah.common.cmixin.IMixinResourcePackProfile;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.server.packs.repository.Pack;

@Deprecated
@Mixin(Pack.class)
public class MixinResourcePackProfile implements IMixinResourcePackProfile {

	// Lnet/minecraft/resource/ResourcePackProfile;packFactory:Lnet/minecraft/resource/ResourcePackProfile$PackFactory;
	@Shadow
	public Pack.ResourcesSupplier resources;
	
	@Override
	public PackResources IC$open_pack(String id) {
		Pack handle = ((Pack)(Object)this);
		try (PackResources pack = resources.openPrimary(handle.location())) {
        	return pack;
			//this.resourcePackInfo = pack.parseMetadata(PackResourceMetadata.SERIALIZER);
        } catch (Exception e) {
        	throw new RuntimeException(e);
        }
	}

	@Override
	public PackMetadataSection IC$open_and_parse_metadata() {
		Pack handle = ((Pack)(Object)this);
		try (PackResources pack = resources.openPrimary(handle.location())) {
			
        	return pack.getMetadataSection(PackMetadataSection.SERVER_TYPE);
        } catch (IOException e) {
        	throw new RuntimeException(e);
        }
	}

	@Override
	public String IC$get_raw_id() {
		return ((Pack)(Object)this).getId();
	}

	@Override
	public boolean IC$is_required() {
		return ((Pack)(Object)this).isRequired();
	}

	@Override
	public boolean IC$is_enabled() {
		return ICommonMod.getIServer().getMinecraft().getPackRepository().getSelectedIds().contains(this.IC$get_raw_id());
	}

}
