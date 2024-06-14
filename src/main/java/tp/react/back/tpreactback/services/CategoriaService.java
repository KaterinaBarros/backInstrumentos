package tp.react.back.tpreactback.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tp.react.back.tpreactback.modelo.Categoria;
import tp.react.back.tpreactback.repository.ICategoriaRepository;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private ICategoriaRepository categoriaRepos;

    public List<Categoria> getListaCategoria(){
        List<Categoria> listaCategoria = categoriaRepos.findAll();
        return listaCategoria;
    }

    public Categoria obtenerCategoria(long id){
        return categoriaRepos.findById(id).orElse(null);
    }

    public Categoria guardarCategoria(Categoria categoria){
        return categoriaRepos.save(categoria);
    }
    public Categoria obtenerCategoriaCodigo(long codigo){
        return categoriaRepos.findByCodigo(codigo);
    }


}
