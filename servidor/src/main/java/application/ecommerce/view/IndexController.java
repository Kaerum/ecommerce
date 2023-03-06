package application.ecommerce.view;

import application.ecommerce.endpoints.produtos.ProdutoRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping(value = {"", "/", "/index"})
    public String index(Model model) {
        return "redirect:/lista-produtos";
    }
}
