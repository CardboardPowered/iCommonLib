package me.isaiah.common.fabric;

import me.isaiah.common.IDatapack;
import me.isaiah.common.cmixin.IMixinResourcePackProfile;
import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.resource.metadata.PackResourceMetadata;

public class FabricDatapack implements IDatapack {

    private ResourcePackProfile handle;
    private PackResourceMetadata resourcePackInfo;

    public FabricDatapack(ResourcePackProfile handler) {
        this.handle = handler;

        IMixinResourcePackProfile im = ((IMixinResourcePackProfile)(Object)handle);
        PackResourceMetadata data = im.IC$open_and_parse_metadata();
        if (null != data) {
        	resourcePackInfo = data;
        }
    }
    
    @Override
    public PackResourceMetadata get_metadata() {
    	if (null == resourcePackInfo) {
    		return null;
    	}
    	return resourcePackInfo;
    }
	
	@Override
	public ResourcePackProfile get_minecraft() {
		return handle;
	}

	@Override
	public String get_raw_id() {
		return ((IMixinResourcePackProfile)(Object)handle).IC$get_raw_id();
	}

	@Override
	public boolean is_required() {
		return ((IMixinResourcePackProfile)(Object)handle).IC$is_required();
	}

	@Override
	public boolean is_enabled() {
		return ((IMixinResourcePackProfile)(Object)handle).IC$is_enabled();
	}

}
