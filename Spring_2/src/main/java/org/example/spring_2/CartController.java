package org.example.spring_2;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

@RestController
@RequestMapping("/cart")
public class CartController {

    @PostMapping("/add")
    public ResponseEntity<Void> addToCart(@RequestBody CartItem cartItem, HttpServletResponse response, HttpServletRequest request) {
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

        List<CartItem> cartItems = cartJson.isEmpty() ? new ArrayList<>() : new Gson().fromJson(cartJson, new TypeToken<List<CartItem>>(){}.getType());
        cartItems.add(cartItem);

        String updatedCartJson = URLEncoder.encode(new Gson().toJson(cartItems), StandardCharsets.UTF_8);
        Cookie cartCookie = new Cookie("cart", updatedCartJson);
        cartCookie.setPath("/");
        cartCookie.setHttpOnly(false);
        response.addCookie(cartCookie);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<CartItem>> getCart(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();



        if (cookies == null || cookies.length == 0) {
            return ResponseEntity.ok(new ArrayList<>());
        } else  {

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

            List<CartItem> cartItems = new Gson().fromJson(cartJson, new TypeToken<List<CartItem>>() {}.getType());
            return ResponseEntity.ok(cartItems);
        }




    }
}
