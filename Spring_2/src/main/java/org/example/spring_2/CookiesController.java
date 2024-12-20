package org.example.spring_2;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cookies")
public class CookiesController {

    @GetMapping("/set")
    public String setCookies(HttpServletResponse response) {
        Cookie browserSessionCookie = new Cookie("name", "Dariusz");
        response.addCookie(browserSessionCookie);

        Cookie weekCookie = new Cookie("color", "red");
        weekCookie.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days
        response.addCookie(weekCookie);

        Cookie fastCookie = new Cookie("isactive", "true");
        fastCookie.setMaxAge(10); // expires in 10 seconds
        response.addCookie(fastCookie);

        Cookie notForJsCookie = new Cookie("hiddenToken", "AB5F6E");
        notForJsCookie.setMaxAge(60); // expires in 1 minute
        notForJsCookie.setHttpOnly(true); // not for JS
        response.addCookie(notForJsCookie);

        return "redirect:/cookies/show";
    }

    @GetMapping("/show")
    public String showCookies(HttpServletRequest request, Model model) {
        Cookie[] cookies = request.getCookies();
        model.addAttribute("cookies", cookies);
        return "cookies/show"; // Plik widoku cookies/show.html
    }
}
