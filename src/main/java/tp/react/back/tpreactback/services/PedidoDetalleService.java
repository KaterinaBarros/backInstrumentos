package tp.react.back.tpreactback.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tp.react.back.tpreactback.modelo.Instrumento;
import tp.react.back.tpreactback.modelo.PedidoDetalle;
import tp.react.back.tpreactback.repository.IPedidoDetalleRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PedidoDetalleService {

    @Autowired
    private IPedidoDetalleRepository pedidoDetalleRepos;

    public void borrarPedidoDetalle(long id){
        pedidoDetalleRepos.deleteById(id);
    }
    public PedidoDetalle CargarPedidoDetalle(PedidoDetalle pedidoDetalle){
        return pedidoDetalleRepos.save(pedidoDetalle);
    }
    public PedidoDetalle modificarPedidoDetalle(PedidoDetalle pedidoDetalle){
        PedidoDetalle pedidoDetalleNuevo = pedidoDetalleRepos.findById(pedidoDetalle.getId()).orElse(null);

        if(pedidoDetalle.getId() != 0 ){
            pedidoDetalleNuevo.setId(pedidoDetalle.getId());
        }

        if(pedidoDetalle.getCantidad() != 0 ){
            pedidoDetalleNuevo.setCantidad(pedidoDetalle.getCantidad());
        }

        if(pedidoDetalle.getInstrumento() != null){
            pedidoDetalleNuevo.setInstrumento(pedidoDetalle.getInstrumento());
        }



        if(pedidoDetalle.getSubtotal() != 0 ){
            pedidoDetalleNuevo.setSubtotal(pedidoDetalle.getSubtotal());
        }

        return pedidoDetalleRepos.save(pedidoDetalleNuevo);
    }

    public PedidoDetalle traerPedidoDetalle(long id){
        return pedidoDetalleRepos.findById(id).orElse(null);
    }

    public List<PedidoDetalle> traerListaPedidoDetalle(){
        List<PedidoDetalle> listaPedidoDetalle = pedidoDetalleRepos.findAll();
        return listaPedidoDetalle;
    }

//    public List<PedidoDetalle> getDatosChartPedido() {
//        return pedidoDetalleRepos.findDatosChartPedido();
//    }

    public List<List<Object>> getDatosChartPedidoPie() {
        List<Object[]> resultados = pedidoDetalleRepos.findDatosChartPedidoPie();
        List<List<Object>> datos = new ArrayList<>();
        datos.add(Arrays.asList("Instrumento", "Cantidad"));  // Encabezado
        for (Object[] resultado : resultados) {
            datos.add(Arrays.asList(resultado[0], resultado[1]));
        }
        return datos;
    }


//    public List<List<Object>> getDatosChartPedidoLine() {
//        List<Object[]> resultados = pedidoDetalleRepos.findDatosChartPedidoLine();
//        List<List<Object>> datos = new ArrayList<>();
//        datos.add(Arrays.asList("Fecha", "Valor"));  // Encabezado
//        for (Object[] resultado : resultados) {
//            datos.add(Arrays.asList(resultado[0], resultado[1]));
//        }
//        return datos;
//    }


}
