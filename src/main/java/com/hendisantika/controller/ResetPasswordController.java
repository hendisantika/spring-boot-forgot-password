package com.hendisantika.controller;

import com.hendisantika.entity.PasswordReset;
import com.hendisantika.entity.PasswordResetToken;
import com.hendisantika.entity.User;
import com.hendisantika.service.framework.PasswordResetTokenService;
import com.hendisantika.service.framework.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Locale;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-reset-password
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/04/22
 * Time: 08.49
 */
@Controller
@RequestMapping("/reset-password")
public class ResetPasswordController {
    private final PasswordResetTokenService tokenService;
    private final MessageSource messageSource;
    private final UserService userService;

    @Autowired
    public ResetPasswordController(PasswordResetTokenService tokenService, MessageSource messageSource,
                                   UserService userService) {
        this.tokenService = tokenService;
        this.messageSource = messageSource;
        this.userService = userService;
    }

    @GetMapping
    public String viewPage(@RequestParam(name = "token", required = false) String token,
                           Model model) {
        PasswordResetToken passwordResetToken = tokenService.findByToken(token);
        if (passwordResetToken == null) {
            model.addAttribute("error", messageSource.getMessage("TOKEN_NOT_FOUND", new Object[]{}, Locale.ENGLISH));
        } else if (passwordResetToken.getExpirationDate().isBefore(LocalDateTime.now())) {
            model.addAttribute("error", messageSource.getMessage("TOKEN_EXPIRED", new Object[]{}, Locale.ENGLISH));
        } else {
            model.addAttribute("token", passwordResetToken.getToken());
        }
        return "reset-password";
    }

    @PostMapping
    public String resetPassword(@Valid @ModelAttribute("passwordReset") PasswordReset passwordReset,
                                BindingResult result,
                                RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("passwordReset", passwordReset);
            return "redirect:/reset-password?token=" + passwordReset.getToken();
        }
        PasswordResetToken token = tokenService.findByToken(passwordReset.getToken());
        User user = token.getUser();
        user.setPassword(passwordReset.getPassword());
        userService.updatePassword(user);
        return "redirect:/login";
    }

    @ModelAttribute("passwordReset")
    public PasswordReset passwordReset() {
        return new PasswordReset();
    }
}
