package wulei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import wulei.domain.Account;
import wulei.modelpublic.SignInInfo;
import wulei.modelpublic.SignUpInfo;
import wulei.modelpublic.Value;
import wulei.repository.AccountRepository;
import wulei.security.AccountDetails;
import wulei.security.SignInWithFormSubmissionUrlProvider;

@Controller
@RequestMapping("/account")
class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private SignInWithFormSubmissionUrlProvider urlProvider;

    @GetMapping("/exist")
    @ResponseBody
    public Value<Boolean> exist(@RequestParam String username) {
        Account account = new Account();
        account.setUsername(username);
        return new Value<Boolean>( this.accountRepository.exists( Example.of(account)) );
    }

    @PostMapping(value = "/signUp")
    public String signUp(@RequestBody SignUpInfo signUpInfo, @RequestParam(defaultValue = "Yes") String rememberMe) {
        Account account = new Account();
        account.setUsername( signUpInfo.getUsername() );

        if( this.accountRepository.exists( Example.of(account)) ) {
            return "forward:/account/signInFailed";
        }

        account.setPassword( signUpInfo.getPassword() );

        this.accountRepository.save(account);

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
    public Value<Account> signInSucceed(@AuthenticationPrincipal AccountDetails accountDetails) {
        Account account = new Account( accountDetails.getUsername(), null );
        account.setId(accountDetails.getId());

        return new Value<Account>(account);
    }

    @RequestMapping(value = "/signInFailed")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public Value<Account> signInFailed() {
        return new Value<Account>(null);
    }

    @RequestMapping(value = "/retrieveAccountSignedIn")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Value<Account> retrieveAccountSignedIn(@AuthenticationPrincipal AccountDetails accountDetails) {
        Account account = accountDetails == null ?
                null : new Account( accountDetails.getUsername(), null );

        if(account != null) {
            account.setId(accountDetails.getId());
        }

        return new Value<Account>(account);
    }

    @RequestMapping(value = "/signOutSucceed")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Value<String> signOutSucceed() {
        return new Value<String>(null);
    }
}