package application.ecommerce.view;

import application.ecommerce.db.filtro.Filtro;
import application.ecommerce.db.sort.Sort;
import application.ecommerce.endpoints.produtos.ProdutoRestController;
import application.ecommerce.exceptions.NotFoundException;
import application.ecommerce.models.produto.Produto;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class ListaProdutoController {

    @Autowired
    private ProdutoRestController produtoRestController;

    @GetMapping("/lista-produtos")
    public String listaProdutos(
            Model model
    ) {
        var a = produtoRestController.todos();
        System.out.println(a);
        model.addAttribute("products", a);
        return "lista-produtos";
    }

    @GetMapping("/criar-produto")
    public String criarProduto(
            Produto produto,
            Model model
    ) {
        model.addAttribute("product", produto);
        model.addAttribute("editMode", false);
        return "criar-produto";
    }

    @PostMapping("/criar-produto")
    public String postCriarProduto(Produto produto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "criar-produto";
        }

        produtoRestController.novo(produto);
        return "redirect:/lista-produtos";
    }

    @PostMapping("/editar-produto")
    public String postEditarProduto(Produto produto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "criar-produto";
        }

        produtoRestController.replace(produto, produto.getId());
        return "redirect:/lista-produtos";
    }

    @GetMapping("/excluir/{id}")
    public String deletarProduto(@PathVariable("id") Long id) {
        produtoRestController.delete(id);

        return "redirect:/lista-produtos";
    }

    @GetMapping("/edit/{id}")
    public String editarProduto(@PathVariable("id") Long id, Model model) {
        try {
            var produto = produtoRestController.um(id);
            model.addAttribute("product", produto);
            model.addAttribute("editMode", true);
            return "criar-produto";
        } catch (NotFoundException exception) {
            return "redirect:/lista-produtos";
        }
    }
}
