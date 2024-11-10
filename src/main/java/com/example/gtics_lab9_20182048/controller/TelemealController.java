package com.example.gtics_lab9_20182048.controller;

import com.example.gtics_lab9_20182048.dao.CategoriaDao;
import com.example.gtics_lab9_20182048.dao.MealDao;
import com.example.gtics_lab9_20182048.entity.Favorite;
import com.example.gtics_lab9_20182048.entity.Meal;
import com.example.gtics_lab9_20182048.repository.FavoriteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/telemeal")
public class TelemealController {
    final CategoriaDao categoriaDao;
    final MealDao mealDao;
    final FavoriteRepository favoriteRepository;

    public TelemealController(CategoriaDao categoriaDao, MealDao mealDao, FavoriteRepository favoriteRepository) {
        this.categoriaDao = categoriaDao;
        this.mealDao = mealDao;
        this.favoriteRepository = favoriteRepository;
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
        Meal meal = mealDao.buscarPorId(id);
        boolean favorito = favoriteRepository.existsByMealId(Integer.valueOf(meal.getIdMeal()));
        model.addAttribute("detalleComida", mealDao.buscarPorId(id));
        model.addAttribute("favorito", favorito);
        return "meal/detalles";
    }
    @PostMapping("/agregarFavorito")
    public String agregarAFavoritos(@RequestParam("id") String id, Model model) {

        Meal meal = mealDao.buscarPorId(id);
        if (!favoriteRepository.existsByMealId(Integer.valueOf(meal.getIdMeal()))) {
            Favorite favorite = new Favorite();
            favorite.setMealId(Integer.valueOf(meal.getIdMeal()));
            favorite.setMealName(meal.getStrMeal());
            favorite.setMealCategory(meal.getStrCategory());
            favorite.setMealImage(meal.getStrMealThumb());
            favoriteRepository.save(favorite);
            model.addAttribute("favorito", true);
        } else {
            model.addAttribute("favorito", true);
        }
        model.addAttribute("detalleComida", meal);
        return "meal/detalles";
    }
    @GetMapping("/favoritos")
    public String listarFavoritos(Model model) {
        List<Favorite> listaFavoritos = favoriteRepository.findAll();
        model.addAttribute("listaFavoritos", listaFavoritos);
        return "categoria/favorites";
    }

}
