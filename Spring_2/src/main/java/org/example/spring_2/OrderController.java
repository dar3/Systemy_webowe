package org.example.spring_2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderController {

    @PostMapping("/cart/confirm")
    public String confirmOrder(@RequestParam String address,
                               @RequestParam String paymentMethod,
                               @RequestParam String deliveryMethod,
                               Model model) {
        // Przekaż dane zamówienia do widoku
        model.addAttribute("address", address);
        model.addAttribute("paymentMethod", paymentMethod);
        model.addAttribute("deliveryMethod", deliveryMethod);

        // Zwróć nazwę widoku checkout.html
        return "checkout";
    }
}
