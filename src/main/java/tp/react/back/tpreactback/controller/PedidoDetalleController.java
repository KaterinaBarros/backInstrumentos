package tp.react.back.tpreactback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tp.react.back.tpreactback.modelo.Instrumento;
import tp.react.back.tpreactback.modelo.PedidoDetalle;
import tp.react.back.tpreactback.services.PedidoDetalleService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/PedidoDetalle")
public class PedidoDetalleController {

    @Autowired
    private PedidoDetalleService pedidoDetalleServ;

    @GetMapping("/traer-lista")
    public List<PedidoDetalle> getPedidoDetalle(){
        return pedidoDetalleServ.traerListaPedidoDetalle();
    }

    @GetMapping("/traer/{id}")
    public PedidoDetalle getPedidoDetalle(@PathVariable long id){
        return pedidoDetalleServ.traerPedidoDetalle(id);
    }

    @PostMapping("/cargar")
    public PedidoDetalle cargarPedidoDetalle(@RequestBody PedidoDetalle pedidoDetalle){
        return pedidoDetalleServ.CargarPedidoDetalle(pedidoDetalle);
    }

    @DeleteMapping("/borrar/{id}")
    public void borrarPedidoDetalle(@PathVariable long id){
        pedidoDetalleServ.borrarPedidoDetalle(id);
    }
    @PutMapping("/modificar")
    public PedidoDetalle modificarPedidoDetalle(@RequestBody PedidoDetalle pedidoDetalle){
        return pedidoDetalleServ.modificarPedidoDetalle(pedidoDetalle);
    }

//    @GetMapping("/chartPedido")
//    public List<PedidoDetalle> getDatosChartPedido() {
//        return pedidoDetalleServ.getDatosChartPedidoPie();
//    }

//    @GetMapping("api/datachartpie")
//    public List<List<Object>> getDataChartPie() throws SQLException {
//        List<List<Object>> data = new ArrayList<>();
//        data.add(Arrays.asList("Articulo", "Cantidad"));
//        data.add(Arrays.asList(pedidoDetalleServ.getDatosChartPedido()));
//        data.add(Arrays.asList("Articulo", "Cantidad"));
////        ChartManager mChart = new ChartManager();
////        ResultSet rs = pedidoDetalleServ.getDatosChartPedido();
////        while (rs.next()) {
////            data.add(Arrays.asList(rs.getString("denominacionArticulo"), rs.getInt("cantVendida")));
////        }
//        return data;
//    }

//    @GetMapping("/api/datachartpie")
//    public List<List<Object>> getDataChartPie() throws SQLException {
//        List<List<Object>> data = new ArrayList<>();
//        data.add(Arrays.asList("Articulo", "Cantidad"));  // Encabezados de las columnas
//        List<PedidoDetalle> detalles = pedidoDetalleServ.getDatosChartPedidoPie();
//        for (PedidoDetalle detalle : detalles) {
//            data.add(Arrays.asList(detalle.getInstrumento(), detalle.getCantidad()));
//        }
//        return data;
//    }
//
//    @GetMapping("/api/datachartline")
//    public List<List<Object>> getDataChartLine() throws SQLException {
//        List<List<Object>> data = new ArrayList<>();
//        data.add(Arrays.asList("Fecha", "Valor"));  // Encabezados de las columnas, ajusta según tu estructura de datos
//        List<PedidoDetalle> detalles = pedidoDetalleServ.getDatosChartPedido(); // Ajusta según tu lógica
//        for (PedidoDetalle detalle : detalles) {
//            data.add(Arrays.asList(detalle.getFecha(), detalle.getValor())); // Ajusta según tu estructura de datos
//        }
//        return data;
//    }
        @GetMapping("/datachartpie")
            public List<List<Object>> getDataChartPie() throws SQLException {
            return pedidoDetalleServ.getDatosChartPedidoPie();
        }

//    @GetMapping("/datachartline")
//    public List<List<Object>> getDataChartLine() throws SQLException {
//        return pedidoDetalleServ.getDatosChartPedidoLine();
//    }

//    @GetMapping("api/datachartpie")
//    public List<List<Object>> getDataChartPie() throws SQLException {
//        List<List<Object>> data = new ArrayList<>();
//        data.add(Arrays.asList("Articulo", "Cantidad"));  // Encabezados de las columnas
//        List<PedidoDetalle> detalles = pedidoDetalleServ.getDatosChartPedido();
//        for (PedidoDetalle detalle : detalles) {
//            data.add(Arrays.asList(detalle.getInstrumento(), detalle.getCantidad()));
//        }
//        return data;
//    }


}
