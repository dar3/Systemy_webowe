package org.example.spring_2;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CartService {

    private final ProductService productService; // Dostęp do danych o produktach

//
//    public void addToCart(int productId, int quantity, HttpServletRequest request, HttpServletResponse response) {
//        // Pobranie produktu z bazy danych
//        Product product = productService.findById(productId);
//        if (product == null) {
//            throw new IllegalArgumentException("Produkt o ID " + productId + " nie istnieje.");
//        }
//
//        // Pobranie koszyka z ciasteczek
//        List<CartItem> cartItems = getCartItems(request);
//
//        // Sprawdzenie, czy produkt już istnieje w koszyku
//        boolean exists = false;
//        for (CartItem item : cartItems) {
//            if (item.getProductId() == productId) {
//                item.setQuantity(item.getQuantity() + quantity); // Zwiększenie ilości
//                exists = true;
//                break;
//            }
//        }
//
//        // Jeśli produkt nie istnieje, dodaj nowy
//        if (!exists) {
//            CartItem newItem = new CartItem(productId, product.getName(), product.getPrice(), product.getWeight(), product.getCategory().getName(), quantity);
//            cartItems.add(newItem);
//        }
//
//        // Zapis koszyka do ciasteczek
//        saveCartItems(cartItems, response);
//    }
//
//    /**
//     * Pobiera listę elementów koszyka.
//     */
//    public List<CartItem> getCartItems(HttpServletRequest request) {
//        String cartJson = "";
//        Cookie[] cookies = request.getCookies();
//
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if ("cart".equals(cookie.getName())) {
//                    cartJson = URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8);
//                    break;
//                }
//            }
//        }
//
//        return cartJson.isEmpty() ? new ArrayList<>() : new Gson().fromJson(cartJson, new TypeToken<List<CartItem>>() {}.getType());
//    }
//
//    /**
//     * Usuwa produkt z koszyka.
//     */
//    public void removeFromCart(int productId, HttpServletRequest request, HttpServletResponse response) {
//        List<CartItem> cartItems = getCartItems(request);
//
//        // Usuń produkt z listy
//        cartItems.removeIf(item -> item.getProductId() == productId);
//
//        // Zapis koszyka do ciasteczek
//        saveCartItems(cartItems, response);
//    }

//    /**
//     * Zapisuje koszyk do ciasteczek.
//     */
//    private void saveCartItems(List<CartItem> cartItems, HttpServletResponse response) {
//        String cartJson = URLEncoder.encode(new Gson().toJson(cartItems), StandardCharsets.UTF_8);
//        Cookie cartCookie = new Cookie("cart", cartJson);
//        cartCookie.setPath("/");
//        cartCookie.setHttpOnly(false);
//        cartCookie.setMaxAge(7 * 24 * 60 * 60); // Ciasteczko ważne przez 7 dni
//        response.addCookie(cartCookie);
//    }
//
//    /**
//     * Czyści koszyk.
//     */
//    public void clearCart(HttpServletResponse response) {
//        Cookie cartCookie = new Cookie("cart", "");
//        cartCookie.setPath("/");
//        cartCookie.setHttpOnly(false);
//        cartCookie.setMaxAge(0); // Ustawienie czasu ważności na 0 usuwa ciasteczko
//        response.addCookie(cartCookie);
//    }
}
