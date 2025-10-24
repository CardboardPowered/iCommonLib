package me.isaiah.common;

import java.util.HashMap;
import java.util.regex.Pattern;

import net.fabricmc.loader.api.FabricLoader;

/**
 * List of our supported versions
 */
public class McVersion {

	public static final HashMap<Double, McVersion> verList = new HashMap<>();
	public static final HashMap<String, McVersion> strList = new HashMap<>();
	
	public static McVersion R1182 = new McVersion(118.2, "1.18.2");
	public static McVersion R1192 = new McVersion(119.2, "1.19.2");
	public static McVersion R1194 = new McVersion(119.4, "1.19.4");
	public static McVersion R1201 = new McVersion(120.1, "1.20.1");
	public static McVersion R1204 = new McVersion(120.4, "1.20.2", "1.20.4"); // aka 1.20.2
	public static McVersion R1206 = new McVersion(120.6, "1.20.5", "1.20.6"); // aka 1.20.5
	public static McVersion R1211 = new McVersion(121.1, "1.21.1", "1.21.2");
	public static McVersion R1214 = new McVersion(121.4, "1.21.3", "1.21.4");
	public static McVersion R1215 = new McVersion(121.5, "1.21.5", "1.21.6", "1.21.7", "1.21.8");
	public static McVersion R1219 = new McVersion(121.9, "1.21.9");
	public static McVersion R12110 = new McVersion(121.91, "1.21.10");
	
	public static McVersion FUTURE = new McVersion(299.9);
	
	private double id;
	
	private double major;
	private double minor;
	
	
	public McVersion(double id, String... strs) {
		this.id = id;
		verList.put(this.id, this);
		for (String s : strs) {
			strList.put(s, this);
		}
	}
	
	public int check(McVersion ver) {
		if (ver.id > this.id) {
			return 1;
		}
		if (ver.id == this.id) {
			return 0;
		}
		if (ver.id < this.id) {
			return -1;
		}
		return 0;
	}
	
	public double id() {
		return id;
	}

	public String string() {
		return strList.toString();
	}
	
	public static McVersion string(String mcver) {
		if (strList.containsKey(mcver)) {
			return strList.get(mcver);
		}
		// TODO
		return FUTURE;
	}
	
	// test
	public static void main(String[] args) {
		McVersion running = getRunning();
		System.out.println(running.id());
	}
	
	public static McVersion getRunning() {
		String mcver = FabricLoader.getInstance().getModContainer("minecraft").get().getMetadata().getVersion().getFriendlyString();
		
		String ver2 = mcver.replaceFirst(Pattern.quote("."), "");
		
		if (strList.containsKey(mcver)) {
			return strList.get(mcver);
		}

		try {
			double d = Double.valueOf(ver2);
			if (!verList.containsKey(d)) {
				if (strList.containsKey(mcver)) {
					return strList.get(mcver);
				}
				return FUTURE;
			}
			return verList.get(d);
		} catch (NumberFormatException e) {
			if (strList.containsKey(mcver)) {
				return strList.get(mcver);
			}
			return FUTURE;
		}
	}
	
	

}