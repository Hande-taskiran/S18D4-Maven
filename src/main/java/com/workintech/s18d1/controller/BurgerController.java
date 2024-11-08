package com.workintech.s18d1.controller;

import com.workintech.s18d1.dao.BurgerDao;
import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.util.BurgerValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/burger")
@CrossOrigin(origins = "*")
public class BurgerController {
    private BurgerDao burgerDao;

    @Autowired
    public BurgerController(BurgerDao burgerDao){
        this.burgerDao = burgerDao;
    }

    @GetMapping
    public List<Burger> getAllBurgers() {
        return burgerDao.findAll();
    }

    @GetMapping("/{id}")
    public Burger getBurgerById(@PathVariable Long id) {
        return burgerDao.findById(id);
    }

    @PostMapping
    public Burger addBurger(@RequestBody Burger burger) {
        BurgerValidation.checkName(burger.getName());
        return burgerDao.save(burger);
    }

    @PutMapping
    public Burger updateBurger(@RequestBody Burger burger) {
        BurgerValidation.checkName(burger.getName());
        return burgerDao.update(burger);
    }

    @DeleteMapping("/{id}")
    public Burger deleteBurger(@PathVariable Long id) {
        return burgerDao.remove(id);
    }

    @GetMapping("/price/{price}")
    public List<Burger> findBurgersByPrice(@PathVariable("price") Double price) {
        return burgerDao.findByPrice(price);
    }

    @GetMapping("/breadType/{breadType}")
    public List<Burger> findBurgersByBreadType(@PathVariable("breadType") String breadType) {
        BreadType btEnum = BreadType.valueOf(breadType);
        return burgerDao.findByBreadType(btEnum);
    }

    @GetMapping("/content/{content}")
    public List<Burger> findBurgersByContent(@PathVariable("content") String content) {
        return burgerDao.findByContent(content);
    }
}
