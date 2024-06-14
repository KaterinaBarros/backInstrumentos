package tp.react.back.tpreactback.gestor;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfCell;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tp.react.back.tpreactback.controller.InstrumentoController;
import tp.react.back.tpreactback.modelo.Instrumento;
import tp.react.back.tpreactback.services.InstrumentoService;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
//import lab4.tp4.Mappers.InstrumentoMapper;
@Component
public class InstrumentoPrintManager {
    protected static Font titulo = new Font(Font.UNDEFINED, 30, Font.BOLD);
    protected static Font greyFont = new Font(Font.UNDEFINED, 16, Font.NORMAL, Color.GRAY);
    protected static Font precio = new Font(Font.UNDEFINED, 28, Font.BOLD);
    protected static Font texto = new Font(Font.UNDEFINED, 16, Font.NORMAL);
    protected static Font textoBold = new Font(Font.UNDEFINED, 16, Font.BOLD);
    protected static Font textoM = new Font(Font.UNDEFINED, 18, Font.BOLD);
    protected static Font textoMiniBold = new Font(Font.COURIER, 8, Font.BOLD);
    protected static Font textoBig = new Font(Font.COURIER, 50, Font.BOLD);
    LocalDateTime hoy = LocalDateTime.now();
    @Autowired
    private InstrumentoService instruServ;

    //private InstrumentoService instrumentoService;
   // public InstrumentoMapper instrumentoMapper;
    public SXSSFWorkbook imprimirExcelPlatos() throws IOException, SQLException{

        // Se crea el libro
        SXSSFWorkbook libro = new SXSSFWorkbook(50); //importante !! el 50 indica el tamaño de paginación
        // Se crea una hoja dentro del libro
        SXSSFSheet hoja = libro.createSheet();
        //estilo
        XSSFFont font = (XSSFFont) libro.createFont();
        font.setBold(true);
        XSSFCellStyle style = (XSSFCellStyle) libro.createCellStyle();
        style.setFont(font);
        int nroColumna = 0;
        // Se crea una fila dentro de la hoja
        SXSSFRow row = hoja.createRow(0);
        // Se crea una celda dentro de la fila
        SXSSFCell cell = row.createCell(nroColumna);
        cell.setCellValue("Fecha Pedido");
        cell.setCellStyle(style);
        cell = row.createCell(++nroColumna);
        cell.setCellValue("Instrumento");
        cell.setCellStyle(style);
        cell = row.createCell(++nroColumna);
        cell.setCellValue("Marca");
        cell.setCellStyle(style);
        cell = row.createCell(++nroColumna);
        cell.setCellValue("Modelo");
        cell = row.createCell(++nroColumna);
        cell.setCellValue("Cantidad");
        cell = row.createCell(++nroColumna);
        cell.setCellValue("Precio");
        cell = row.createCell(++nroColumna);
        cell.setCellValue("Subtotal");
        cell.setCellStyle(style);

        int nroFila = 1;
        nroColumna = 0;
        //InstrumentoController mPlato = new InstrumentoController();
        List<Instrumento> platos = instruServ.getInstrumento();
        if(platos != null){
            for (Instrumento plato : platos) {
                nroColumna = 0;
                row = hoja.createRow(nroFila);
                ++nroFila;
                cell = row.createCell(nroColumna);
                cell.setCellValue(String.valueOf(hoy));
                cell = row.createCell(++nroColumna);
                cell.setCellValue(plato.getInstrumento());
                cell = row.createCell(++nroColumna);
                cell.setCellValue(plato.getMarca());
                cell = row.createCell(++nroColumna);
                cell.setCellValue(plato.getModelo());
                cell = row.createCell(++nroColumna);
                cell.setCellValue(plato.getCantidadVendida());
                cell = row.createCell(++nroColumna);
                cell.setCellValue(plato.getPrecio());
                cell = row.createCell(++nroColumna);
                cell.setCellValue(plato.getPrecio()*plato.getCantidadVendida());
            }
        }
        return libro;

    }

    public static void addMetaData(Document document) {
        document.addTitle("Reporte PDF");
        document.addSubject("Ejemplo PDF");
        document.addKeywords("PDF");
        document.addAuthor("Kate");
        document.addCreator("Kate");
    }

    public static void addEmptyLine(Document document, int number) {
        try {
            Paragraph espacio = new Paragraph();
            for (int i = 0; i < number; i++) {
                espacio.add(new Paragraph(" "));
            }
            document.add(espacio);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setLineaReporte(Document document) throws DocumentException, MalformedURLException, IOException{
        PdfPTable linea = new PdfPTable(1);
        linea.setWidthPercentage(100.0f);
        PdfPCell cellOne = new PdfPCell(new Paragraph(""));
        cellOne.setBorder(Rectangle.BOTTOM);
        cellOne.setBorder(Rectangle.TOP);
        linea.addCell(cellOne);

        document.add(linea);
    }

    public void imprimirPlatoPdf(Long idPlato, ByteArrayOutputStream outputStream) throws SQLException  {
        try{
            Document document = new Document(PageSize.A4, 30, 30, 30, 30);//float marginLeft, float marginRight, float marginTop, float marginBottom
            addMetaData(document);

           // InstrumentoService mPlato = new InstrumentoService();
            Instrumento plato = instruServ.obtenerInstrumento(idPlato);

            PdfWriter.getInstance(document, outputStream); // Code 2
            document.open();

//            Paragraph paragraph = new Paragraph(plato.getInstrumento().toUpperCase(), titulo);
//            paragraph.setAlignment(Element.ALIGN_CENTER);
//            document.add(paragraph);

            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);

            // Columna 1: Imagen y Descripción
            PdfPCell cell1 = new PdfPCell();
            cell1.setBorder(Rectangle.NO_BORDER);

            // Aquí debes cargar la imagen desde tu archivo
            Image img = Image.getInstance("images/"+plato.getImagenPath());
            cell1.addElement(img);

            // Agregar descripción
            Paragraph contenido = new Paragraph();
            contenido.add(new Phrase("Descripcion: ", textoBold));
            contenido.add(new Phrase(plato.getDescripcion(), texto));
            cell1.addElement(contenido);

            // Columna 2: Características
            PdfPCell cell2 = new PdfPCell();
            cell2.setBorder(Rectangle.NO_BORDER);

            //parrafos cantidad vendida, nombre, precio, marca, modelo, envio
            Paragraph cantidad = new Paragraph();
            cantidad.add(new Phrase(String.valueOf(plato.getCantidadVendida()), greyFont));
            cantidad.add(new Phrase(" vendidos",  greyFont));
            cantidad.setSpacingAfter(20); // Espacio después del párrafo
            cell2.addElement(cantidad);

            //addEmptyLine(document, 6);

            Paragraph contenido2 = new Paragraph();
            //contenido2.add(new Phrase("Nombre: ", titulo));
            contenido2.add(new Phrase(plato.getInstrumento(), titulo));
            contenido2.setSpacingAfter(20); // Espacio después del párrafo
            cell2.addElement(contenido2);

            //addEmptyLine(document, 6);

            Paragraph contenido3 = new Paragraph();
            contenido3.add(new Phrase("$", precio));
            contenido3.add(new Phrase(String.valueOf(plato.getPrecio()), precio));
            contenido3.setSpacingAfter(20); // Espacio después del párrafo
            cell2.addElement(contenido3);

            //addEmptyLine(document, 6);

            Paragraph contenido4 = new Paragraph();
            contenido4.add(new Phrase("Marca: ", textoM));
            contenido4.add(new Phrase(plato.getMarca(), textoM));
            contenido4.setSpacingAfter(20); // Espacio después del párrafo
            cell2.addElement(contenido4);

            //addEmptyLine(document, 6);

            Paragraph contenido5 = new Paragraph();
            contenido5.add(new Phrase("Modelo: ", textoM));
            contenido5.add(new Phrase(plato.getModelo(), textoM));
            contenido5.setSpacingAfter(20); // Espacio después del párrafo
            cell2.addElement(contenido5);

            //addEmptyLine(document, 6);

            Paragraph contenido6 = new Paragraph();
            contenido6.add(new Phrase("Costo Envio: ", texto));
            contenido6.add(new Phrase(plato.getCostoEnvio(), texto));
            contenido6.setSpacingAfter(20); // Espacio después del párrafo
            cell2.addElement(contenido6);

            table.addCell(cell1);
            table.addCell(cell2);

            document.add(table);

            document.close();

        }catch(DocumentException | IOException e){
            e.printStackTrace();
        }
    }

}
