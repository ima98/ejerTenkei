package concesionario;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.catalina.connector.Response;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
class Controlador {

	private final Repositorio repository;
	private final ModelAssembler assembler;
	private final PDFGenerator pdfGenerator;

	Controlador(Repositorio repository, ModelAssembler assembler, PDFGenerator pdfGenerator) {
		this.repository = repository;
		this.assembler = assembler;
		this.pdfGenerator = pdfGenerator;
	}

	@GetMapping("/coches")
	CollectionModel<EntityModel<Coche>> all() {

		List<EntityModel<Coche>> coches = repository.findAll().stream() //
				.map(assembler::toModel) //
				.collect(Collectors.toList());

		return CollectionModel.of(coches, linkTo(methodOn(Controlador.class).all()).withSelfRel());
	}

	@GetMapping("/coches/marca/{marca}")
	CollectionModel<EntityModel<Coche>> findBymarca(@PathVariable String marca) {

		List<EntityModel<Coche>> coches = repository.findBymarca(marca).stream() //
				.map(assembler::toModel) //
				.collect(Collectors.toList());

		return CollectionModel.of(coches, linkTo(methodOn(Controlador.class).all()).withSelfRel());
	}

	@PostMapping("/newCoche")
	Coche newCoche(@RequestBody Coche nCoche) {
		return repository.save(nCoche);
	}
	 
	 @GetMapping("/pdf/{marca}")
	    public void generate(HttpServletResponse response, @PathVariable String marca) throws IOException {
	        response.setContentType("application/pdf");
	        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
	        String currentDateTime = dateFormatter.format(new Date());

	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
	        response.setHeader(headerKey, headerValue);
	        CollectionModel<EntityModel<Coche>> c = findBymarca(marca);

			Iterator<EntityModel<Coche>> i = c.iterator();
	 
	        this.pdfGenerator.export(response, i);
	    }
}