package dev.bernouy.cms.common;

import java.nio.file.Files;
import java.nio.file.Path;

public class FileSystem {

    public final static String CLASS_PATH = FileSystem.class.getResource("/").getPath();
    public final static String COMPONENT_PATH = CLASS_PATH + "component";

    public static void initFileSystem(){
        try{

            Files.createDirectory(Path.of(COMPONENT_PATH + "/css"));
            Files.createDirectory(Path.of(COMPONENT_PATH + "/js"));

        } catch ( Exception e ){ e.printStackTrace() ; }
    }
}