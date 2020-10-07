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
import java.io.InputStream;

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

    @Bean
    CommandLineRunner initDatabase(PostService postService, FeaturedPostRepository featuredPostRepository) {
        return (evt) -> {
            String postStr = IOUtils.toString(new FileInputStream("./temp/data.json"), "UTF-8");
            Gson gson = new Gson();

            Posts posts = gson.fromJson(postStr, Posts.class);

            NonDramaPost[] nonDramaPosts = posts.post_gallery_1;
            DramaPost[] dramaPosts = posts.post_gallery_2;

            int label = 0;
            for(NonDramaPost nonDramaPost: nonDramaPosts) {
                Long postId = postService.insert(nonDramaPost.pictures, null, null, nonDramaPost.essay,
                    nonDramaPost.classifyName, nonDramaPost.previewPictures, nonDramaPost.previewStyle, String.valueOf(label));
                label ++;
                featuredPostRepository.save( new FeaturedPost( postId ));
            }

            label = 0;
            for(DramaPost dramaPost: dramaPosts) {
                String[] previewPictures = new String[1];
                previewPictures[0] = dramaPost.playbill;
                Long postId = postService.insert(dramaPost.pictures, dramaPost.playbill, dramaPost.title,
                    dramaPost.essay, dramaPost.classifyName, previewPictures, "1*1", "label" + String.valueOf(label));
                label ++;
                featuredPostRepository.save( new FeaturedPost( postId ));
            }
        };
    }

//    @Bean
    CommandLineRunner init(PostService postService, FeaturedPostRepository featuredPostRepository) {
        return (evt) -> {
//            featuredPostRepository.modify();
//            System.out.println("------------------------------------------------------aaaa");
//            System.out.print("----------------------------------------------");
//            System.out.println(Paths.get("").resolve("data").toUri());


            InputStream inputStream = new FileInputStream("data");
            String total = IOUtils.toString(inputStream, "UTF-8");

//            System.out.print("----------------------------------------------");
//            System.out.println(total);
//            System.out.print("----------------------------------------------");
//            System.out.println( Paths.get("").resolve("images/7fad39afgy1fc0pr1ew50j22io1w0kjl.jpg").toUri().toString() );

//            byte[] bytes = IOUtils.toByteArray( Paths.get("").resolve("images/7fad39afgy1fc0pr1ew50j22io1w0kjl.jpg").toUri() );

//            System.out.print("----------------------------------------------");
//            System.out.println( bytes.length );




            String[] _items= total.trim().split("]]]]");
            int label = 1;

            int times = 5;
            int length = 15;

            String[] items = new String[5];
            System.arraycopy(_items, (times - 1) * length, items, 0, 5);

            for(String item: items) {
                String[] subItems = item.split("\\[\\[\\[\\[");

                String essay = subItems[0].trim();

                String[] _pictureFileNames = subItems[1].trim().split("jpg");
                String[] pictureFileNames = new String[ _pictureFileNames.length ];
                for(int i = 0; i < _pictureFileNames.length; i++) {
                    pictureFileNames[i] = "images/" + _pictureFileNames[i].trim() + "jpg";
                }

                String[] previewPictureFileNames;
                String previewStyle;
                String previewInfo = subItems[2].trim();
                if( previewInfo.contains("jpg") ) {
                    previewPictureFileNames = new String[1];
                    previewPictureFileNames[0] = "images/" + previewInfo;
                    previewStyle = "1*1";
                }
                else {
                    String amountString = previewInfo.substring(0,1);
                    int amount = Integer.parseInt( amountString );
                    previewPictureFileNames = new String[amount];
                    for( int i = 0; i < amount; i++ ) {
                        previewPictureFileNames[i] = pictureFileNames[i];
                    }

                    if( previewInfo.substring(1).compareToIgnoreCase("x") == 0 ) {
                        String sqrtString = String.valueOf( (int) Math.sqrt( amount ) );
                        previewStyle = sqrtString + "*" + sqrtString;
                    }
                    else {
                        previewStyle = amountString + "*1";
                    }
                }

                String classifyNameInfo = subItems[3].trim();
                String classifyName = null;
                if( classifyNameInfo.contains("ad") ) {
                    classifyName = "advertisement";
                }
                else if( classifyNameInfo.contains("f")) {
                    classifyName = "fashion";
                }
                else if ( classifyNameInfo.contains("a") ) {
                    classifyName = "activity";
                }
                else if ( classifyNameInfo.contains("d") ) {
                    classifyName = "daily-life";
                }

                Long postId = postService.insert(pictureFileNames, null, null, essay,
                        classifyName, previewPictureFileNames, previewStyle, String.valueOf(label));
                label++;
                featuredPostRepository.save( new FeaturedPost( postId ));
            }
        };
    }

//    @Bean
    CommandLineRunner init2(PostService postService, FeaturedPostRepository featuredPostRepository) {
        return (evt) -> {
//            System.out.print("----------------------------------------------");
//            System.out.println(Paths.get("").resolve("data2").toUri());
            InputStream inputStream = new FileInputStream("data2");
            String total = IOUtils.toString(inputStream, "UTF-8");
            String[] items= total.trim().split("]]]]");
            int label = 1;

            for(String item: items) {
                String[] subItems = item.split("\\[\\[\\[\\[");

                String title = subItems[0].trim();
                String essay = subItems[1].trim();
                String playbillFileName = "images/" + subItems[2].trim();

                String _pictureFileNamesInfo = subItems[3].trim();
                String[] pictureFileNames;
                String[] previewPictureFileNames;
                String previewStyle = "1*1";

                if(_pictureFileNamesInfo.length() == 0) {
                    pictureFileNames = new String[0];
                    previewPictureFileNames = new String[0];
                }
                else {
                    String[] _pictureFileNames = _pictureFileNamesInfo.split("[\\r\\n]");
                    pictureFileNames = new String[ _pictureFileNames.length ];
                    for(int i = 0; i < _pictureFileNames.length; i++) {
                        pictureFileNames[i] = "images/" + _pictureFileNames[i].trim();
                    }
                    previewPictureFileNames = new String[1];
                    previewPictureFileNames[0] = pictureFileNames[0];
                }

                String classifyName = subItems[4].contains("t") ? "tv-series" : "movie";
                Long postId = postService.insert(pictureFileNames, playbillFileName, title, essay,
                        classifyName, previewPictureFileNames, previewStyle, "label" + String.valueOf(label));
                label++;
                featuredPostRepository.save( new FeaturedPost( postId ));
            }
        };
    }

//    @Bean
    CommandLineRunner init3(FeaturedPictureRepository featuredPictureRepository) {
        return (evt) -> {
            featuredPictureRepository.save(new FeaturedPicture((long) 91, "sign-in"));
            featuredPictureRepository.save(new FeaturedPicture((long) 291, "sign-up"));
        };
    }
}