package egov.minwon.web.auth;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    @GetMapping("/login.do")
    public String loginForm() {
        return "auth/login";
    }

    @PostMapping("/login.do")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session,
                        RedirectAttributes redirectAttributes) {

        if ("admin".equals(username) && "1234".equals(password)) {
            session.setAttribute("LOGIN_USER", username);
            return "redirect:/minwon/list.do";
        }

        redirectAttributes.addFlashAttribute("message", "로그인 실패: 계정을 확인하세요.");
        return "redirect:/login.do";
    }

    @PostMapping("/logout.do")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login.do";
    }
}
