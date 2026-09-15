package br.com.projeto.controller;

import br.com.projeto.service.TransacaoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.GetMapping;
import org.springframework.web.bind.RequestMapping;

@Controller
@RequestMapping("/transacoes")
public class TransacaoController {

    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("transacoes", transacaoService.listarTodas());
        return "transacoes/listar";
    }
}

