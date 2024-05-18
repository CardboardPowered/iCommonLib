package me.isaiah.common.cmixin;

import net.minecraft.resource.ResourcePack;
import net.minecraft.resource.metadata.PackResourceMetadata;

public interface IMixinResourcePackProfile {

	/**
	 * 1.20.2+ Only
	 * NULL on <=1.20.1
	 */
    public ResourcePack IC$open_pack(String id);
    
	/**
	 * 1.20.2+ Only
	 * NULL on <=1.20.1
	 */
    public PackResourceMetadata IC$open_and_parse_metadata();
    
    /**
     */
	public String IC$get_raw_id();

	/**
	 */
	public boolean IC$is_required();

	/**
	 */
	public boolean IC$is_enabled();
	
}
