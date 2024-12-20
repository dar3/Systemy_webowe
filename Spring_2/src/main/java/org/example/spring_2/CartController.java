package org.example.spring_2;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/cart")
public class CartController {

    @PostMapping("/add")
    public String addToCart(@RequestBody CartItem cartItem, HttpServletResponse response, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String cartJson = "";

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("cart".equals(cookie.getName())) {
                    cartJson = URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8);
                    break;
                }
            }
        }

        List<CartItem> cartItems = cartJson.isEmpty() ? new ArrayList<>() : new Gson().fromJson(cartJson, new TypeToken<List<CartItem>>() {
        }.getType());
        cartItems.add(cartItem);

        String updatedCartJson = URLEncoder.encode(new Gson().toJson(cartItems), StandardCharsets.UTF_8);
        Cookie cartCookie = new Cookie("cart", updatedCartJson);
        cartCookie.setPath("/");
        cartCookie.setHttpOnly(false);
        response.addCookie(cartCookie);

        return "redirect:/cart";
//        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<CartItem>> getCart(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();


        if (cookies == null || cookies.length == 0) {
            return ResponseEntity.ok(new ArrayList<>());
        } else {

            String cartJson = "";
            for (Cookie cookie : cookies) {
                if ("cart".equals(cookie.getName())) {
                    cartJson = URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8);
                    break;
                }
            }

            if (cartJson.isEmpty()) {
                // Brak ciasteczka "cart"
                return ResponseEntity.ok(new ArrayList<>());
            }

            List<CartItem> cartItems = new Gson().fromJson(cartJson, new TypeToken<List<CartItem>>() {
            }.getType());
            return ResponseEntity.ok(cartItems);
        }


    }

    @GetMapping("/add")
    public String addProductForm(Model model, HttpServletRequest request) {
      return "redirect:/cart/addToCart";
    }

    @GetMapping("/addToCart")
    public String addToCartPage(Model model) {
        model.addAttribute("message", "You have been redirected to Add to Cart page!");
        return "addToCart"; // Plik widoku "addToCart.html" w katalogu templates
    }




}