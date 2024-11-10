package com.example.gtics_lab9_20182048.controller;

import com.example.gtics_lab9_20182048.dao.CategoriaDao;
import com.example.gtics_lab9_20182048.dao.MealDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/telemeal")
public class TelemealController {
    final CategoriaDao categoriaDao;
    final MealDao mealDao;

    public TelemealController(CategoriaDao categoriaDao, MealDao mealDao) {
        this.categoriaDao = categoriaDao;
        this.mealDao = mealDao;
    }

    @GetMapping({"/list", "", "/"})
    public String listarCategorias(Model model) {
        model.addAttribute("listaCategorias", categoriaDao.listar());
        return "categoria/list";
    }
    @GetMapping("/buscar")
    public String buscarComidas(@RequestParam("nombre") String nombre, Model model) {
        model.addAttribute("listaComidas", mealDao.buscarPorNombre(nombre));
        return "meal/list";
    }

    @GetMapping("/detalles")
    public String detalleComida(@RequestParam("id") String id, Model model) {
        model.addAttribute("detalleComida", mealDao.buscarPorId(id));
        return "meal/detalles";
    }

}
