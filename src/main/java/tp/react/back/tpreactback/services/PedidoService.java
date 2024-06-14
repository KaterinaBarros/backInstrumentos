package tp.react.back.tpreactback.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tp.react.back.tpreactback.modelo.Instrumento;
import tp.react.back.tpreactback.modelo.Pedido;
import tp.react.back.tpreactback.modelo.PedidoDetalle;
import tp.react.back.tpreactback.repository.IInstrumentoRepository;
import tp.react.back.tpreactback.repository.IPedidoRepository;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private IPedidoRepository pedidoRepos;

    @Autowired
    private IInstrumentoRepository instrumentoRepos;

    public List<Pedido> traerListaPedidos(){
        List<Pedido> listaPedido = pedidoRepos.findAll();
        return listaPedido;
    }


    public Pedido traerPedido(long id){
        return pedidoRepos.findById(id).orElse(null);
    }

    public Pedido cargarPedido(Pedido pedido){
        double total=0;
        for (PedidoDetalle detalle : pedido.getPedidoDetalle()) {
            detalle.setPedido(pedido); // Asegurarte de que el PedidoDetalle tiene una referencia al Pedido
            Instrumento instrumento = detalle.getInstrumento();
            if (instrumento != null ) {
                Instrumento managedInstrumento = instrumentoRepos.findById(instrumento.getId());
                managedInstrumento.setCantidadVendida(managedInstrumento.getCantidadVendida() + detalle.getCantidad());
                instrumentoRepos.save(managedInstrumento);

                if (managedInstrumento != null) {
                    detalle.setInstrumento(managedInstrumento);
                    detalle.calcularSubtotal(); // Calcular el subtotal después de establecer el instrumento
                } else {
                    throw new RuntimeException("Instrumento no encontrado con ID: " + instrumento.getId());
                }
            }

            total += detalle.getSubtotal();
        }
        pedido.setTotalPedido(total);
        return pedidoRepos.save(pedido);
    }

    public void borrarPedido(long id){
        pedidoRepos.deleteById(id);
    }

    public Pedido modificarPedido(Pedido pedidoModificado){
        double total=0;
        Pedido pedidoOriginal = pedidoRepos.findById(pedidoModificado.getId()).orElse(null);
        if(pedidoOriginal != null){
            pedidoOriginal.getPedidoDetalle().removeIf(detalle ->
                    pedidoModificado.getPedidoDetalle().stream().noneMatch(modificadoDetalle ->
                            modificadoDetalle.getId() == detalle.getId()));

            for(PedidoDetalle modificadoDetalle : pedidoModificado.getPedidoDetalle()){
                PedidoDetalle originalDetalle = pedidoOriginal.getPedidoDetalle().stream()
                        .filter(detalle -> detalle.getId() == modificadoDetalle.getId())
                        .findFirst()
                        .orElse(null);

                if(originalDetalle != null){
                    originalDetalle.setCantidad(modificadoDetalle.getCantidad());
                    originalDetalle.calcularSubtotal(); // Calcular el subtotal después de establecer la cantidad
                } else {
                    Instrumento instrumento = instrumentoRepos.findById(modificadoDetalle.getInstrumento().getId());
                    if (instrumento != null) {
                        modificadoDetalle.setInstrumento(instrumento);
                        modificadoDetalle.setPedido(pedidoOriginal); // Establecer la entidad administrada como padre
                        pedidoOriginal.getPedidoDetalle().add(modificadoDetalle);
                    } else {
                        throw new RuntimeException("Instrumento no encontrado con ID: " + modificadoDetalle.getInstrumento().getId());
                    }
                }
            }

            for(PedidoDetalle detalle : pedidoOriginal.getPedidoDetalle()){
                total += detalle.getSubtotal();
            }
            pedidoOriginal.setTotalPedido(total);
            return pedidoRepos.save(pedidoOriginal);
        } else {
            throw new RuntimeException("Pedido no encontrado");
        }
    }


}
