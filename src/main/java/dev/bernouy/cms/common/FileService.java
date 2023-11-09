package dev.bernouy.cms.common;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;

@Service
public class FileService {

    public final static String CLASS_PATH           = FileService.class.getResource("/").getPath();
    public final static String COMPONENT_PATH       = CLASS_PATH + "component";
    public final static String WEBSITE_PATH         = CLASS_PATH + "structure/websites";
    public final static String WEBSITE_MODEL_PATH   = CLASS_PATH + "structure/model";

    public static void initFileSystem(){
        try{
            //Files.createDirectory(Path.of(WEBSITE_PATH));
            Files.createDirectory(Path.of(COMPONENT_PATH));
            Files.createDirectory(Path.of(COMPONENT_PATH + "/css"));
            Files.createDirectory(Path.of(COMPONENT_PATH + "/js"));
        } catch ( Exception e ){ e.printStackTrace() ; }
    }

    public static void copyDirectory(Path sourceDir, Path targetDir) throws IOException {
        EnumSet<FileVisitOption> options = EnumSet.of(FileVisitOption.FOLLOW_LINKS);

        Files.walkFileTree(sourceDir, options, Integer.MAX_VALUE, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                Path target = targetDir.resolve(sourceDir.relativize(dir));
                Files.createDirectories(target);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Path target = targetDir.resolve(sourceDir.relativize(file));
                Files.copy(file, target, StandardCopyOption.REPLACE_EXISTING);
                return FileVisitResult.CONTINUE;
            }
        });
    }
}