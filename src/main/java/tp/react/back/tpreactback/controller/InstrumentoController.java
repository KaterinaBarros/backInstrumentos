package tp.react.back.tpreactback.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tp.react.back.tpreactback.gestor.InstrumentoPrintManager;
import tp.react.back.tpreactback.modelo.Instrumento;
import org.springframework.beans.factory.annotation.Autowired;
import tp.react.back.tpreactback.services.InstrumentoService;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/Instrumento")
public class InstrumentoController {

    @Autowired
    private InstrumentoService instruServ;
    @Autowired
    private InstrumentoPrintManager instrumentoPrintManager;


    @GetMapping("/traer-lista")
    public List<Instrumento> getInstrumento(){
        return  instruServ.getInstrumento();
    }
    @GetMapping("/traer/{id}")
    public Instrumento getInstrumento(@PathVariable long id){
        return instruServ.obtenerInstrumento(id);
    }

    @PostMapping("/cargar-instrumentos")
    public ResponseEntity<String> cargarInstrumentosDesdeJson(@RequestParam String rutaArchivo) {
        System.out.println("Recibida la solicitud para cargar instrumentos desde el archivo: " + rutaArchivo);
        instruServ.cargarInstrumentosDesdeJson(rutaArchivo);
        return ResponseEntity.ok("Datos cargados exitosamente en la base de datos.");
    }

    @PostMapping("/guardar")
    public Instrumento guardarInstrumento(@RequestBody Instrumento instrumento){
        return instruServ.guardarInstrumento(instrumento);
    }

//    @PostMapping("/guardar")
//    public Instrumento guardarInstrumento(@RequestBody Instrumento instrumento, @RequestParam Long categoriaId) {
//        return instruServ.guardarInstrumento(instrumento, categoriaId);
//    }


    @PutMapping("/actualizar")
    public Instrumento modificarInstrumento(@RequestBody Instrumento instrumento){
        return instruServ.modificarInstrumento(instrumento);
    }

    @DeleteMapping("/borrar/{id}")
    public void eliminarInstrumento(@PathVariable long id){
        instruServ.eliminarInstrumento(id);
    }

    @GetMapping("api/downloadExcelPlatos")
    public ResponseEntity<byte[]> downloadExcelPlatos() throws SQLException {
        try {
            //InstrumentoPrintManager mPrintPlato = new InstrumentoPrintManager();
            SXSSFWorkbook libroExcel = instrumentoPrintManager.imprimirExcelPlatos();
            // Escribir el libro de trabajo en un flujo de bytes
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            libroExcel.write(outputStream);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
            headers.setContentDispositionFormData("attachment", "datos.xlsx");
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

            return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("api/downloadPdfPlato/{idPlato}")
    public ResponseEntity<byte[]> downloadPdf(@PathVariable Integer idPlato) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            //InstrumentoPrintManager mPrintPlato = new InstrumentoPrintManager();
            // Crear un nuevo documento
            instrumentoPrintManager.imprimirPlatoPdf(Long.parseLong(String.valueOf(idPlato)), outputStream);

            // Establecer las cabeceras de la respuesta
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/pdf"));
            headers.setContentDispositionFormData("attachment", "documento.pdf");
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

            // Devolver el archivo PDF como parte de la respuesta HTTP
            return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("api/datachartline")
//    public List<List<Object>> getDataChartLine() throws SQLException {
//        List<List<Object>> data = new ArrayList<>();
//        data.add(Arrays.asList("Articulo", "Precio Compra", "Precio Venta"));
//
//        //ChartManager mChart = new ChartManager();
//        ResultSet rs = instruServ.getDatosChart();
//        while (rs.next()) {
//            data.add(Arrays.asList(rs.getString("denominacionArticulo"), FuncionApp.getFormatNumberToDecimal(rs.getDouble("montoCompra")), FuncionApp.getFormatNumberToDecimal(rs.getDouble("montoVenta"))));
//        }
//        return data;
//    }

//    @GetMapping("api/datachartpie")
//    public List<List<Object>> getDataChartPie() throws SQLException {
//        List<List<Object>> data = new ArrayList<>();
//        data.add(Arrays.asList("Instrumento", "Cantidad vendida"));
//
//        //ChartManager mChart = new ChartManager();
//        ResultSet rs = instruServ.getDatosChart();
//        while (rs.next()) {
//            data.add(Arrays.asList(rs.getString("denominacionArticulo"), rs.getInt("cantVendida")));
//        }
//        return data;
//    }

    @GetMapping("/chart")
    public List<Instrumento> getDatosChart() {
        return instruServ.getDatosChart();
    }

}

