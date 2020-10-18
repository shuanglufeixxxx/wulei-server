package wulei.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;

import wulei.domain.Account;
import wulei.modelpublic.Value;
import wulei.repository.AccountRepository;
import wulei.security.AccountDetails;

@Controller
@RequestMapping("/account")
class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/exist")
    @ResponseBody
    public Value<Boolean> exist(@RequestParam String username) {
        Account account = new Account();
        account.setUsername(username);
        return new Value<Boolean>( this.accountRepository.exists( Example.of(account)) );
    }

    @PostMapping(value = "/signUp")
    public String signUp(@RequestParam String username, @RequestParam String password
            , @RequestParam(defaultValue = "Yes") String rememberMe , HttpServletRequest request) {

        Account account = new Account();
        account.setUsername( username );

        if( this.accountRepository.exists( Example.of(account)) ) {
            return "forward:/account/signInFailed";
        }

        account.setPassword( password );

        this.accountRepository.save(account);

        request.setAttribute( View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT );

        return "redirect:/account/signIn";
    }

    @RequestMapping(value = "/toSignInPage")
    public String toSignIn() {
        return "redirect:/(action:sign-in)";
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