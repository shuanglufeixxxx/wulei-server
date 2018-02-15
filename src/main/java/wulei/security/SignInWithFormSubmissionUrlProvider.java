package wulei.security;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SignInWithFormSubmissionUrlProvider {

    private final String formSignInUrl = "/account/signInWithFormSubmission";

    public String getUrlWithParams(String username, String password, boolean rememberMe) {
        return formSignInUrl
                + "?username=" + username
                + "&password=" + password
                + ( rememberMe ? "&rememberMe=Yes" : "" );
    }

    public String getUrl() {
        return formSignInUrl;
    }
}
