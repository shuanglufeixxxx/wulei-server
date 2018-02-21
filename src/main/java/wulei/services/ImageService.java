package wulei.services;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageService {

    private final Path rootPath = Paths.get("images");

    public Resource loadAsResource(String fileName) {
        try {
            Resource resource = new UrlResource( rootPath.resolve(fileName).toUri() );
            return resource.exists() || resource.isReadable() ? resource : null;
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
