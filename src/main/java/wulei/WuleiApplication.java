package wulei;

import org.apache.commons.io.IOUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import wulei.domain.FeaturedPicture;
import wulei.domain.FeaturedPost;
import wulei.repository.FeaturedPictureRepository;
import wulei.repository.FeaturedPostRepository;
import wulei.services.PostService;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

import com.google.gson.Gson;

class NonDramaPost {
    String classifyName;
    String essay;
    String[] pictures;
    String previewStyle;
    String[] previewPictures;
};

class DramaPost {
    String classifyName;
    String title;
    String essay;
    String playbill;
    String[] pictures;
};

class Posts {
    NonDramaPost[] post_gallery_1;
    DramaPost[] post_gallery_2;
};

@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true)
@Controller
public class WuleiApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WuleiApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(WuleiApplication.class, args);
    }

    @GetMapping(value = "/{path:[^.]+}/**")
    public String SinglePageApplicationSupport() {
        return "forward:/";
    }

    private byte[] getFileData(String path) {
        try {
            return IOUtils.toByteArray(Paths.get("./init_data/pictures").resolve(path).toUri());
        }
        catch(IOException e) {
            return null;
        }
    }

    private byte[][] getFileDataArray(String[] paths) {
        byte[][] r = new byte[paths.length][];
        for(int i = 0; i < paths.length; i++) {
            r[i] = getFileData(paths[i]);
        }
        return r;
    }

    // @Bean
    CommandLineRunner initDatabase(PostService postService, FeaturedPostRepository featuredPostRepository) {
        return (evt) -> {
            String postStr = IOUtils.toString(new FileInputStream("./init_data/data.json"), "UTF-8");
            Gson gson = new Gson();

            Posts posts = gson.fromJson(postStr, Posts.class);

            NonDramaPost[] nonDramaPosts = posts.post_gallery_1;
            DramaPost[] dramaPosts = posts.post_gallery_2;

            for(NonDramaPost nonDramaPost: nonDramaPosts) {
                Long postId = postService.insert(
                    this.getFileDataArray(nonDramaPost.pictures),
                    null,
                    null,
                    nonDramaPost.essay,
                    nonDramaPost.classifyName,
                    this.getFileDataArray(nonDramaPost.previewPictures),
                    nonDramaPost.previewStyle
                );

                featuredPostRepository.save( new FeaturedPost( postId ));
            }
            
            for(DramaPost dramaPost: dramaPosts) {
                String[] previewPictures = new String[1];
                previewPictures[0] = dramaPost.playbill;
                Long postId = postService.insert(
                    this.getFileDataArray(dramaPost.pictures),
                    this.getFileData(dramaPost.playbill),
                    dramaPost.title,
                    dramaPost.essay,
                    dramaPost.classifyName,
                    this.getFileDataArray(previewPictures),
                    "1*1"
                );
                featuredPostRepository.save( new FeaturedPost( postId ));
            }
        };
    }

//    @Bean
    CommandLineRunner initFeaturedPictureDB(FeaturedPictureRepository featuredPictureRepository) {
        return (evt) -> {
            featuredPictureRepository.save(new FeaturedPicture((long) 91, "sign-in"));
            featuredPictureRepository.save(new FeaturedPicture((long) 291, "sign-up"));
        };
    }
}