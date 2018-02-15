package wulei.service;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ServiceConfig {

    private SqlSessionFactory sqlSessionFactory = null;

    @Bean
    public SqlSessionFactory SqlSessionFactoryInstance() {
        if (this.sqlSessionFactory == null) {
            String resource = "wulei/mapper/databaseConfig.xml";
            try {
                InputStream inputStream = Resources.getResourceAsStream(resource);
                SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
                this.sqlSessionFactory = builder.build(inputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return this.sqlSessionFactory;
    }

    private PostLikeService postLikeService = null;

    @Bean
    PostLikeService likeServiceInstance() {
        if (this.postLikeService == null) {
            this.postLikeService = new PostLikeService();
        }

        return this.postLikeService;
    }

    private PictureService pictureService = null;

    @Bean
    PictureService pictureServiceInstance() {
        if (this.pictureService == null) {
            this.pictureService = new PictureService();
        }

        return this.pictureService;
    }

    private PostService postService = null;

    @Bean
    PostService postServiceInstance() {
        if (this.postService == null) {
            this.postService = new PostService();
        }

        return this.postService;
    }

    private AccountService accountService = null;

    @Bean
    AccountService userServiceInstance() {
        if (this.accountService == null) {
            this.accountService = new AccountService();
        }

        return this.accountService;
    }

    private ImageService imageService = null;

    @Bean
    ImageService imageServiceInstance() {
        if (this.imageService == null) {
            this.imageService = new ImageService();
        }

        return this.imageService;
    }

    private FeaturedPostService featuredPostService = null;

    @Bean
    FeaturedPostService featuredPostServiceInstance() {
        if (this.featuredPostService == null) {
            this.featuredPostService = new FeaturedPostService();
        }

        return this.featuredPostService;
    }

    private FeaturedPictureService featuredPictureService = null;

    @Bean
    FeaturedPictureService featuredPictureServiceInstance() {
        if (this.featuredPictureService == null) {
            this.featuredPictureService = new FeaturedPictureService();
        }

        return this.featuredPictureService;
    }

    private TokenService tokenService = null;

    @Bean
    TokenService tokenServiceInstance() {
        if (this.tokenService == null) {
            this.tokenService = new TokenService();
        }

        return this.tokenService;
    }
}