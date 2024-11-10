package com.example.gtics_lab9_20182048.controller;

import com.example.gtics_lab9_20182048.dao.CategoriaDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/telemeal")
public class TelemealController {
    final CategoriaDao categoriaDao;

    public TelemealController(CategoriaDao categoriaDao) {
        this.categoriaDao = categoriaDao;
    }

    @GetMapping({"/list", "", "/"})
    public String listarCategorias(Model model) {
        model.addAttribute("listaCategorias", categoriaDao.listar());
        return "categoria/list";
    }

}
