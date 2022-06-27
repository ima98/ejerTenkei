package concesionario;



import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;



@Service
public class PDFGenerator {
	public void export(HttpServletResponse response, Iterator<EntityModel<Coche>> iterator) throws IOException {
		 Document document = new Document(PageSize.A4);
	        PdfWriter.getInstance(document, response.getOutputStream());

	        document.open();
	        Table table=new Table(3);
	        while(iterator.hasNext()) {
	        	EntityModel<Coche> a = iterator.next();
				Coche o = a.getContent();

				table.addCell(o.getMarca());
				table.addCell(o.getMatricula());
				table.addCell(o.getModelo());
				table.complete();
	        }
	        document.add(table);
	        
	        document.close();
    }
}
