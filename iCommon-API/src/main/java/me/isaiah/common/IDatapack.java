package me.isaiah.common;

import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.resource.metadata.PackResourceMetadata;

public interface IDatapack {

    public ResourcePackProfile get_minecraft();

    public String get_raw_id();

    public boolean is_required();

    public boolean is_enabled();

    /**
     * 1.20.2+ only
     * NULL on <=1.20.1
     */
	PackResourceMetadata get_metadata();

	
}
