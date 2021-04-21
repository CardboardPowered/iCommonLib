package me.isaiah.common.extras;

import java.io.File;
import java.net.URL;
import org.apache.commons.io.FileUtils;

public class ModuleDownloader {

    public static void downloadCyberPerms() throws Exception {
        FileUtils.copyURLToFile(
                new URL("https://edge.forgecdn.net/files/3156/546/CyberPermissions-1.1.jar"), 
                new File("mods" + File.separator + "CyberPermissions.jar"), 
                50000, 50000);
    }

}
