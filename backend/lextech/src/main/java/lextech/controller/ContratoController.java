package lextech.controller;

import java.io.File;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

@RestController
@RequestMapping("/api/contratos")
@CrossOrigin(origins = "*")
public class ContratoController {

    @PostMapping("/upload")
    public ResponseEntity<String> subirContrato(
            @RequestParam("archivo") MultipartFile archivo) {

        System.out.println("===== SUBIDA DE CONTRATO =====");
        System.out.println("Nombre: " + archivo.getOriginalFilename());
        System.out.println("Vacío: " + archivo.isEmpty());

        try {

            File carpeta = new File("C:/Users/sipho/Desktop/LexTechAI/backend/lextech/uploads");

            if (!carpeta.exists()) {
                carpeta.mkdirs();
            }

            File destino = new File(carpeta, archivo.getOriginalFilename());

            archivo.transferTo(destino);

            PDDocument documento = Loader.loadPDF(destino);

            PDFTextStripper lector = new PDFTextStripper();

            String texto = lector.getText(documento);

            System.out.println("========== CONTENIDO DEL PDF ==========");
            System.out.println(texto);

            documento.close();

            return ResponseEntity.ok("Contrato subido correctamente.");

        } catch (Exception e) {

            e.printStackTrace();

            return ResponseEntity.badRequest()
                    .body("Error: " + e.getMessage());

        }
    }

}
