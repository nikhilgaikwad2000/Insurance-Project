package in.ashokit.serviceImpl;

import java.awt.Color;




import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.ashokit.binding.CitizinePlan;
import in.ashokit.binding.SearchRequest;
import in.ashokit.repository.Citizinerepository;
import in.ashokit.service.CitizineService;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class CitizineServiceImpl implements CitizineService {

	@Autowired
	private Citizinerepository repo;

	@Override
	public List<String> getPlaneName() {

		List<String> planNames = repo.getPlanNames();

		return planNames;

	}

	@Override
	public List<String> getPlaneStatus() {

		List<String> getplanStatus = repo.getplanStatus();
		return getplanStatus;
	}

	@Override
	public List<CitizinePlan> getCitizensPlan(SearchRequest request) {

		CitizinePlan entity = new CitizinePlan();

		if (request.getPlanName() != null && !request.getPlanName().equals("")) {
			entity.setPlanName(request.getPlanName());
		}
		if (request.getPlanStatus() != null && !request.getPlanStatus().equals("")) {
			entity.setPlanStatus(request.getPlanStatus());
		}

		if (request.getGender() != null && !request.getGender().equals("")) {
			entity.setGender(request.getGender());
		}
		    Example<CitizinePlan> example = Example.of(entity);  

		List<CitizinePlan> records = repo.findAll(example);
		return records;
	}

	@Override
	public void exportExcel(HttpServletResponse responce) {

		XSSFWorkbook workbook = new XSSFWorkbook();

		XSSFSheet sheet = workbook.createSheet("citizens_plans_info");
		XSSFRow headerrow = sheet.createRow(0);
		headerrow.createCell(0).setCellValue("Id");
		headerrow.createCell(0).setCellValue("Name ");
		headerrow.createCell(0).setCellValue("SSN");
		headerrow.createCell(0).setCellValue("Gender");
		headerrow.createCell(0).setCellValue("Plan Name");
		headerrow.createCell(0).setCellValue("Plan Status");

		List<CitizinePlan> records = repo.findAll();
		int dataRowIndex = 1;
		for (CitizinePlan record : records) {
			XSSFRow dataRow = sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(record.getCid());
			dataRow.createCell(0).setCellValue(record.getCname());
			dataRow.createCell(0).setCellValue(record.getSsn());
			dataRow.createCell(0).setCellValue(record.getGender());
			dataRow.createCell(0).setCellValue(record.getPlanName());
			dataRow.createCell(0).setCellValue(record.getPlanStatus());

			dataRowIndex++;
		}
	}

	@Override
	public void exportPdf(HttpServletResponse response) throws Exception {

		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();

		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLUE);

		Paragraph p = new Paragraph("List of Users", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(p);

		PdfPTable table = new PdfPTable(6);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 1.5f, 3.5f, 3.0f, 3.0f, 1.5f, 1.5f });
		table.setSpacingBefore(10);

		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5);

		Font f = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);

		cell.setPhrase(new Phrase(" ID", f));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Name ", f));
		table.addCell(cell);

		cell.setPhrase(new Phrase("SSN", f));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Gender", f));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Plan Name", f));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Plan status", f));
		table.addCell(cell);

		List<CitizinePlan> records = repo.findAll();

		for (CitizinePlan record : records) {
			table.addCell(String.valueOf(record.getCid()));
			table.addCell(record.getCname());
			table.addCell(String.valueOf(record.getSsn()));
			table.addCell(record.getGender());
			table.addCell(record.getPlanName());
			table.addCell(record.getPlanStatus());

		}

		document.add(table);
		document.close();
	}

}
