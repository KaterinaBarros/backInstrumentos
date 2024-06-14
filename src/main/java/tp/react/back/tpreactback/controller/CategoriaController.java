package tp.react.back.tpreactback.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import tp.react.back.tpreactback.modelo.Categoria;
import tp.react.back.tpreactback.services.CategoriaService;

import java.util.List;

@RestController
@RequestMapping("/Categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaServ;

    @GetMapping("/traer-lista")
    public List<Categoria> getListaCategoria(){
        return  categoriaServ.getListaCategoria();
    }

    @GetMapping("/traer/{id}")
    public Categoria getCategoria(@PathVariable long id){
        return categoriaServ.obtenerCategoria(id);
    }

    @PostMapping("/guardar")
    public Categoria guardarCategoria(Categoria categoria){
        return categoriaServ.guardarCategoria(categoria);
    }
    @GetMapping("/traerxcodigo/{codigo}")
    public Categoria getCategoriaPorCodigo(@PathVariable long codigo){
        return categoriaServ.obtenerCategoriaCodigo(codigo);
    }




}
