package wulei;

import org.apache.commons.io.IOUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import wulei.domain.FeaturedPost;
import wulei.service.FeaturedPostService;
import wulei.service.PostService;
import wulei.service.UUIDUtil;

import java.io.FileInputStream;
import java.io.InputStream;

@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true)
@Controller
public class WuleiApplication {

    public static void main(String[] args) {
        SpringApplication.run(WuleiApplication.class, args);
    }

    @GetMapping(value = "/{path:[^.]+}/**")
    public String SinglePageApplicationSupport() {
        return "forward:/";
    }

//    @Bean
    CommandLineRunner init(PostService postService, FeaturedPostService featuredPostService) {
        return (evt) -> {
            InputStream inputStream = new FileInputStream("data");
            String total = IOUtils.toString(inputStream, "UTF-8");
            String[] items= total.trim().split("]]]]");

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

                String postId = postService.insert(pictureFileNames, null, null, essay,
                        classifyName, previewPictureFileNames, previewStyle);

                featuredPostService.insert( new FeaturedPost( UUIDUtil.getInstance().generateId(), postId ));
            }
        };
    }

//    @Bean
    CommandLineRunner init2(PostService postService, FeaturedPostService featuredPostService) {
        return (evt) -> {
            InputStream inputStream = new FileInputStream("data2");
            String total = IOUtils.toString(inputStream, "UTF-8");
            String[] items= total.trim().split("]]]]");

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
                String postId = postService.insert(pictureFileNames, playbillFileName, title, essay,
                        classifyName, previewPictureFileNames, previewStyle);
                featuredPostService.insert( new FeaturedPost( UUIDUtil.getInstance().generateId(), postId ));
            }
        };
    }
}
