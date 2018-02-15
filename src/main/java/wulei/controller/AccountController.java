package wulei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import wulei.modelpublic.AccountPublic;
import wulei.modelpublic.SignInInfo;
import wulei.modelpublic.SignUpInfo;
import wulei.modelpublic.Value;
import wulei.security.AccountDetails;
import wulei.security.SignInWithFormSubmissionUrlProvider;
import wulei.service.AccountService;

@Controller
@RequestMapping("/account")
class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private SignInWithFormSubmissionUrlProvider urlProvider;

    @GetMapping("/exist")
    @ResponseBody
    public Value<Boolean> exist(@RequestParam String username) {
        return new Value<Boolean>( this.accountService.countByUsername(username) > 0 );
    }

    @PostMapping(value = "/signUp")
    public String signUp(@RequestBody SignUpInfo signUpInfo, @RequestParam(defaultValue = "Yes") String rememberMe) {
        if( this.accountService.countByUsername( signUpInfo.getUsername() ) > 0 ) {
            return "forward:/account/signInFailed";
        }

        this.accountService.insert( signUpInfo.getUsername(), signUpInfo.getPassword() );

        return "forward:" + urlProvider.getUrlWithParams(signUpInfo.getUsername(),
                signUpInfo.getPassword(), "Yes".equals(rememberMe));
    }

    @PostMapping(value = "/signIn")
    public String signIn(@RequestBody SignInInfo signInInfo, @RequestParam(defaultValue = "Yes") String rememberMe) {

        return "forward:" + urlProvider.getUrlWithParams(signInInfo.getUsername(),
                signInInfo.getPassword(), "Yes".equals(rememberMe));
    }

    @RequestMapping(value = "/signInSucceed")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Value<AccountPublic> signInSucceed(@AuthenticationPrincipal AccountDetails accountDetails) {
        return new Value<AccountPublic>( new AccountPublic( accountDetails.getId(), accountDetails.getUsername() ) );
    }

    @RequestMapping(value = "/signInFailed")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public Value<AccountPublic> signInFailed() {
        return new Value<AccountPublic>(null);
    }

    @RequestMapping(value = "/retrieveAccountSignedIn")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Value<AccountPublic> retrieveAccountSignedIn(@AuthenticationPrincipal AccountDetails accountDetails) {
        AccountPublic accountPublic = accountDetails == null ?
                null : new AccountPublic( accountDetails.getId(), accountDetails.getUsername() );
        return new Value<AccountPublic>(accountPublic);
    }

    @RequestMapping(value = "/signOutSucceed")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Value<String> signOutSucceed() {
        return new Value<String>(null);
    }
}