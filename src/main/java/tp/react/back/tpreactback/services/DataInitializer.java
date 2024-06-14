package tp.react.back.tpreactback.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tp.react.back.tpreactback.modelo.Categoria;
import tp.react.back.tpreactback.modelo.Instrumento;
import tp.react.back.tpreactback.repository.ICategoriaRepository;
import tp.react.back.tpreactback.repository.IInstrumentoRepository;

import java.io.IOException;
import java.util.List;


@Component
public class DataInitializer {

    @Autowired
    private ICategoriaRepository categoriaRepos;
    @Autowired
    private IInstrumentoRepository instrumentoRepos;

    @PostConstruct
    public void init() {

        if(categoriaRepos.count() == 0){

            Categoria cat1 = new Categoria();
            cat1.setId(1);
            cat1.setCodigo(100);
            cat1.setDenominacion("Cuerdas");

            Categoria cat2 = new Categoria();
            cat2.setId(2);
            cat2.setCodigo(200);
            cat2.setDenominacion("Vientos");

            Categoria cat3 = new Categoria();
            cat3.setId(3);
            cat3.setCodigo(300);
            cat3.setDenominacion("Percusion");

            Categoria cat4 = new Categoria();
            cat4.setId(4);
            cat4.setCodigo(400);
            cat4.setDenominacion("Teclado");

            Categoria cat5 = new Categoria();
            cat5.setId(5);
            cat5.setCodigo(500);
            cat5.setDenominacion("Electronico");

            categoriaRepos.save(cat1);
            categoriaRepos.save(cat2);
            categoriaRepos.save(cat3);
            categoriaRepos.save(cat4);
            categoriaRepos.save(cat5);

        }

    }

    @PostConstruct
    public void init2() {
        if (instrumentoRepos.count() == 0) { // Verifica si la tabla está vacía
            String instrumentosJson = "[{\n" +
                    "    \"id\": 1,\n" +
                    "    \"instrumento\": \"Mandolina Instrumento Musical Stagg Sunburst\",\n" +
                    "    \"marca\": \"Stagg\",\n" +
                    "    \"modelo\": \"M20\",\n" +
                    "    \"imagen\": \"nro10.jpg\",\n" +
                    "    \"precio\": 2450,\n" +
                    "    \"costoEnvio\": \"G\",\n" +
                    "    \"cantidadVendida\": 28,\n" +
                    "    \"descripcion\": \"Estas viendo una excelente mandolina de la marca Stagg, con un sonido muy dulce, tapa aros y fondo de tilo, y diapasón de palisandro. Es un instrumento acústico (no se enchufa) de cuerdas dobles (4 pares) con la caja ovalada y cóncava, y el mástil corto. Su utilización abarca variados ámbitos, desde rock, folk, country y ensambles experimentales.\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"id\": 2,\n" +
                    "    \"instrumento\": \"Pandereta Pandero Instrumento Musical \",\n" +
                    "    \"marca\": \"DyM ventas\",\n" +
                    "    \"modelo\": \"32 sonajas\",\n" +
                    "    \"imagen\": \"nro9.jpg\",\n" +
                    "    \"precio\": 325,\n" +
                    "    \"costoEnvio\": \"150\",\n" +
                    "    \"cantidadVendida\": 10,\n" +
                    "    \"descripcion\": \"1 Pandereta - 32 sonajas metálicas. Más de 8 años vendiendo con 100 % de calificaciones POSITIVAS y clientes satisfechos !! \"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"id\": 3,\n" +
                    "    \"instrumento\": \"Triangulo Musical 24 Cm Percusion\",\n" +
                    "    \"marca\": \"LBP\",\n" +
                    "    \"modelo\": \"24\",\n" +
                    "    \"imagen\": \"nro8.jpg\",\n" +
                    "    \"precio\": 260,\n" +
                    "    \"costoEnvio\": \"250\",\n" +
                    "    \"cantidadVendida\": 3,\n" +
                    "    \"descripcion\": \"Triangulo Musical de 24 Centímetros De Acero. ENVIOS POR CORREO O ENCOMIENDA: Se le deberán adicionar $40 en concepto de Despacho y el Costo del envío se abonará al recibir el producto en Terminal, Sucursal OCA o Domicilio\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"id\": 4,\n" +
                    "    \"instrumento\": \"Bar Chimes Lp Cortina Musical 72 Barras \",\n" +
                    "    \"marca\": \"FM\",\n" +
                    "    \"modelo\": \"LATIN\",\n" +
                    "    \"imagen\": \"nro7.jpg\",\n" +
                    "    \"precio\": 2250,\n" +
                    "    \"costoEnvio\": \"G\",\n" +
                    "    \"cantidadVendida\": 2,\n" +
                    "    \"descripcion\": \"BARCHIME CORTINA MUSICAL DE 25 BARRAS LATIN CUSTOM. Emitimos factura A y B\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"id\": 5,\n" +
                    "    \"instrumento\": \"Shekeres. Instrumento. Música. Artesanía. \",\n" +
                    "    \"marca\": \"Azalea Artesanías\",\n" +
                    "    \"modelo\": \"Cuentas de madera\",\n" +
                    "    \"imagen\": \"nro6.jpg\",\n" +
                    "    \"precio\": 850,\n" +
                    "    \"costoEnvio\": \"300\",\n" +
                    "    \"cantidadVendida\": 5,\n" +
                    "    \"descripcion\": \"Las calabazas utilizadas para nuestras artesanías son sembradas y cosechadas por nosotros, quienes seleccionamos el mejor fruto para garantizar la calidad del producto y ofrecerle algo creativo y original.\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"id\": 6,\n" +
                    "    \"instrumento\": \"Antiguo Piano Aleman Con Candelabros. \",\n" +
                    "    \"marca\": \"Neumeyer\",\n" +
                    "    \"modelo\": \"Stratus\",\n" +
                    "    \"imagen\": \"nro3.jpg\",\n" +
                    "    \"precio\": 17000,\n" +
                    "    \"costoEnvio\": \"2000\",\n" +
                    "    \"cantidadVendida\": 0,\n" +
                    "    \"descripcion\": \"Buen dia! Sale a la venta este Piano Alemán Neumeyer con candelabros incluidos. Tiene una talla muy bonita en la madera. Una pieza de calidad.\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"id\": 7,\n" +
                    "    \"instrumento\": \"Guitarra Ukelele Infantil Grande 60cm\",\n" +
                    "    \"marca\": \"GUITARRA\",\n" +
                    "    \"modelo\": \"UKELELE\",\n" +
                    "    \"imagen\": \"nro4.jpg\",\n" +
                    "    \"precio\": 500,\n" +
                    "    \"costoEnvio\": \"G\",\n" +
                    "    \"cantidadVendida\": 5,\n" +
                    "    \"descripcion\": \"Material: Plástico smil madera 4 Cuerdas longitud: 60cm, el mejor regalo para usted, su familia y amigos, adecuado para 3-18 años de edad\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"id\": 8,\n" +
                    "    \"instrumento\": \"Teclado Organo Electronico Musical Instrumento 54 Teclas \",\n" +
                    "    \"marca\": \"GADNIC\",\n" +
                    "    \"modelo\": \"T01\",\n" +
                    "    \"imagen\": \"nro2.jpg\",\n" +
                    "    \"precio\": 2250,\n" +
                    "    \"costoEnvio\": \"G\",\n" +
                    "    \"cantidadVendida\": 1375,\n" +
                    "    \"descripcion\": \"Organo Electrónico GADNIC T01. Display de Led. 54 Teclas. 100 Timbres / 100 Ritmos. 4 1/2 octavas. 8 Percusiones. 8 Canciones de muestra. Grabación y reproducción. Entrada para Micrófono. Salida de Audio (Auriculares / Amplificador). Vibrato. Sustain Incluye Atril Apoya partitura y Micrófono. Dimensiones: 84,5 x 32,5 x 11 cm\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"id\": 9,\n" +
                    "    \"instrumento\": \"Instrumentos De Percusión Niños Set Musical Con Estuche \",\n" +
                    "    \"marca\": \"KNIGHT\",\n" +
                    "    \"modelo\": \"LB17\",\n" +
                    "    \"imagen\": \"nro1.jpg\",\n" +
                    "    \"precio\": 2700,\n" +
                    "    \"costoEnvio\": \"300\",\n" +
                    "    \"cantidadVendida\": 15,\n" +
                    "    \"descripcion\": \"Estas viendo un excelente y completísimo set de percusion para niños con estuche rígido, equipado con los instrumentos mas divertidos! De gran calidad y sonoridad. Ideal para jardines, escuelas primarias, musicoterapeutas o chicos que se quieran iniciar en la música de la mejor manera. Es un muy buen producto que garantiza entretenimiento en cualquier casa o reunión, ya que esta equipado para que varias personas al mismo tiempo estén tocando un instrumento.\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"id\": 10,\n" +
                    "    \"instrumento\": \"Batería Musical Infantil Juguete Niño 9 Piezas Palillos \",\n" +
                    "    \"marca\": \"Bateria\",\n" +
                    "    \"modelo\": \"Infantil\",\n" +
                    "    \"imagen\": \"nro5.jpg\",\n" +
                    "    \"precio\": 850,\n" +
                    "    \"costoEnvio\": \"250\",\n" +
                    "    \"cantidadVendida\": 380,\n" +
                    "    \"descripcion\": \"DESCRIPCIÓN: DE 1 A 3 AÑOS. EL SET INCLUYE 5 TAMBORES, PALILLOS Y EL PLATILLO TAL CUAL LAS FOTOS. SONIDOS REALISTAS Y FÁCIL DE MONTAR. MEDIDAS: 40X20X46 CM\"\n" +
                    "  }]";

            ObjectMapper mapper = new ObjectMapper();
            try {
                List<Instrumento> instrumentos = mapper.readValue(instrumentosJson, new TypeReference<List<Instrumento>>(){});
                for (Instrumento instrumento : instrumentos) {
                    instrumentoRepos.save(instrumento);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }





}