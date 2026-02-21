package br.com.miguel.produtos.api.controller;

import br.com.miguel.produtos.domain.entity.Produto;
import br.com.miguel.produtos.domain.service.ProdutoService;
import br.com.miguel.produtos.exception.ProdutoNotFoundException;
import br.com.miguel.produtos.exception.ProdutoSemNomeException;
import br.com.miguel.produtos.exception.ValorInvalidoException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.util.List;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Double.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                if (text == null || text.trim().isEmpty()) {
                    setValue(null);
                    return;
                }
                try {
                    setValue(Double.parseDouble(text.trim().replace(",", ".")));
                } catch (NumberFormatException e) {
                    setValue(null);
                }
            }
        });

        // registrar para double primitivo também
        binder.registerCustomEditor(double.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                if (text == null || text.trim().isEmpty()) {
                    setValue(0.0);
                    return;
                }
                try {
                    setValue(Double.parseDouble(text.trim().replace(",", ".")));
                } catch (NumberFormatException e) {
                    setValue(0.0);
                }
            }
        });
    }

    // Listar todos os produtos
    @GetMapping
    public String findAll(Model model) {
        List<Produto> produtos = produtoService.findAll();
        model.addAttribute("produtos", produtos);
        model.addAttribute("menu", "produtos");
        return "produtos/list"; // template Thymeleaf
    }

    // Exibir formulário para criar novo produto
    @GetMapping("/novo")
    public String createForm(Model model) {
        model.addAttribute("produto", new Produto());
        return "produtos/form";
    }

    // Salvar produto (novo ou editado)
    @PostMapping("/salvar")
    public String save(@Valid @ModelAttribute Produto produto, BindingResult result, Model model) {
        // validação automática do Bean Validation
        if (result.hasErrors()) {
            model.addAttribute("produto", produto);
            return "produtos/form";
        }

        try {
            produtoService.save(produto);
        } catch (ProdutoSemNomeException | ValorInvalidoException e) {
            result.rejectValue("preco", "valor.invalido", e.getMessage());
            model.addAttribute("produto", produto);
            return "produtos/form";
        }

        return "redirect:/produtos";
    }

    // Exibir formulário para editar produto
    @GetMapping("/editar/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        try {
            Produto produto = produtoService.findById(id);
            model.addAttribute("produto", produto);
        } catch (ProdutoNotFoundException e) {
            model.addAttribute("erro", e.getMessage());
            return "redirect:/produtos";
        }
        return "produtos/form";
    }

    // Deletar produto
    @GetMapping("/deletar/{id}")
    public String delete(@PathVariable Long id, Model model) {
        try {
            produtoService.delete(id);
        } catch (ProdutoNotFoundException e) {
            model.addAttribute("erro", e.getMessage());
        }
        return "redirect:/produtos";
    }

    // Buscar produtos por nome
    @GetMapping("/buscar")
    public String findByNome(@RequestParam String nome, Model model) {
        List<Produto> produtosEncontrados = produtoService.findByNome(nome);
        model.addAttribute("produtos", produtosEncontrados);
        model.addAttribute("menu", "produtos");
        return "produtos/list";
    }
}