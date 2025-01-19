package org.example.spring_2;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
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

@AllArgsConstructor
@Controller
@RequestMapping("/cart")
public class CartController {

    private final ProductService productService;

    @PostMapping
    public String addToCart(@RequestParam String name,
                            @RequestParam double price,
                            @RequestParam int quantity,
                            HttpServletResponse response,
                            HttpServletRequest request) {

        // Odczytanie istniejącego ciasteczka koszyka
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

        // Aktualizacja koszyka
        List<CartItem> cartItems = cartJson.isEmpty() ? new ArrayList<>() : new Gson().fromJson(cartJson, new TypeToken<List<CartItem>>() {}.getType());
        cartItems.add(new CartItem(name, quantity, price));

        // Zapis zaktualizowanego koszyka w ciasteczku
        String updatedCartJson = URLEncoder.encode(new Gson().toJson(cartItems), StandardCharsets.UTF_8);
        Cookie cartCookie = new Cookie("cart", updatedCartJson);
        cartCookie.setPath("/");
        cartCookie.setHttpOnly(false);
        response.addCookie(cartCookie);

        return "redirect:/cart";
    }



    @GetMapping
    public String getCart(HttpServletRequest request, Model model) {
        // Odczytanie ciasteczka koszyka
        Cookie[] cookies = request.getCookies();
        List<CartItem> cartItems = new ArrayList<>();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("cart".equals(cookie.getName())) {
                    String cartJson = URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8);
                    cartItems = new Gson().fromJson(cartJson, new TypeToken<List<CartItem>>() {}.getType());
                    break;
                }
            }
        }

        // Obliczanie sumy
        double totalSum = cartItems.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalSum", Math.round(totalSum * 100.0) / 100.0);

        return "cart";
    }





    @GetMapping("/add")
    public String addProductForm(Model model) {
        model.addAttribute("products", productService.findAll());
        return "addToCart"; }





//    AddToCart website showing

        @GetMapping("/addToCart")
        public String addToCartPage (Model model){
            model.addAttribute("message", "You have been redirected to Add to Cart page!");
            return "addToCart2"; // Plik widoku "addToCart.html" w katalogu templates
        }


    }

