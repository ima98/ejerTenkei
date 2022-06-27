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

@RestController
class Controlador {

	private final Repositorio repository;
	private final ModelAssembler assembler;

	Controlador(Repositorio repository, ModelAssembler assembler) {
		this.repository = repository;
		this.assembler = assembler;
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
	void pdf(@PathVariable String marca) {
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream("concesionario.pdf"));
		} catch (FileNotFoundException | DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		document.open();
		try {
			document.add(new Chunk(""));
		} catch (DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		PdfPTable table = new PdfPTable(3);

		CollectionModel<EntityModel<Coche>> c = findBymarca(marca);

		Iterator<EntityModel<Coche>> i = c.iterator();

		while (i.hasNext()) {
			EntityModel<Coche> a = i.next();
			Coche o = a.getContent();

			table.addCell(o.getMarca());
			table.addCell(o.getMatricula());
			table.addCell(o.getModelo());
			table.completeRow();
		}

		try {
			document.add(table);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		document.close();
		
	}

}