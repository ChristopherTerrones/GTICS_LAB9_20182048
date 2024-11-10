package com.example.gtics_lab9_20182048.controller;

import com.example.gtics_lab9_20182048.dao.CategoriaDao;
import com.example.gtics_lab9_20182048.entity.Favorite;
import com.example.gtics_lab9_20182048.repository.FavoriteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/telemeal")
public class TelemealController {
    final CategoriaDao categoriaDao;
    final FavoriteRepository favoriteRepository;

    public TelemealController(CategoriaDao categoriaDao, FavoriteRepository favoriteRepository) {
        this.categoriaDao = categoriaDao;
        this.favoriteRepository = favoriteRepository;
    }

    @GetMapping({"/list", "", "/"})
    public String listarCategorias(Model model) {
        model.addAttribute("listaCategorias", categoriaDao.listar());
        return "categoria/list";
    }
    @GetMapping("/favoritos")
    public String listarFavoritos(Model model) {
        List<Favorite> listaFavoritos = favoriteRepository.findAll();
        model.addAttribute("listaFavoritos", listaFavoritos);
        return "categoria/favorites";
    }

}
