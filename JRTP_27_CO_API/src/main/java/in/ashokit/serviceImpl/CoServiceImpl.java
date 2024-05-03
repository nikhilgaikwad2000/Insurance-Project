package in.ashokit.serviceImpl;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.ashokit.binding.CoResponce;
import in.ashokit.entity.CaseEntity;
import in.ashokit.entity.CitizineAppEntity;
import in.ashokit.entity.CoTrgEntity;
import in.ashokit.entity.EdEligDtlsEntity;
import in.ashokit.repo.CaseRepo;
import in.ashokit.repo.CitizineAppRepo;
import in.ashokit.repo.CoTriggerRepository;
import in.ashokit.repo.EdEligRepository;
import in.ashokit.service.CoService;

public class CoServiceImpl implements CoService {
	
	@Autowired
	private CoTriggerRepository coTrgRepo;
	
	@Autowired
	private CitizineAppRepo appRepo;
	
	@Autowired
	private EdEligRepository eligRepo;
	
	@Autowired 
	private CaseRepo caseRepo;
	


	@Override
	public CoResponce processesPendingTriggers() throws Exception {
		
		final Long failed=0l;
		final Long success=0l;
		
		CoResponce responce=new CoResponce();
		   
		List<CoTrgEntity> pendingTrgs = coTrgRepo.findByTrgStatus("Pending");
		
		
		for(CoTrgEntity trigger:pendingTrgs) {
			processesTrigger(responce,trigger);
		}
		responce.setTotalTriggers(Long.valueOf(pendingTrgs.size()));
		responce.setSuccessTriggers(success);
		responce.setFailedTriggers(failed);
		
		
		return responce;
	}

	private CitizineAppEntity processesTrigger(CoResponce responce,CoTrgEntity entity) throws Exception {
		CitizineAppEntity appEntity=null;
	
		
		// get elig  data based on casenum
		EdEligDtlsEntity elig = eligRepo.findByCaseNum(entity.getCaseNum());
		
		// get citizen data based on case num
		
		Optional<CaseEntity> findById = caseRepo.findById(entity.getCaseNum());
		if(findById.isPresent()) {
			CaseEntity caseEntity = findById.get();
			Integer appId = caseEntity.getAppId();
			Optional<CitizineAppEntity> appEntityOptional = appRepo.findById(appId);
			if(appEntityOptional.isPresent()) {
		      appEntity = appEntityOptional.get();
			}
		}
		generateAndSendPdf(elig,appEntity);
		
		return appEntity;
	}
	
	private void generateAndSendPdf(EdEligDtlsEntity eligData,CitizineAppEntity appEntity)throws Exception{
		Document document = new Document(PageSize.A4);
		File file =new File(eligData.getCaseNum()+".pdf");
		FileOutputStream fos=null;
		try {
			fos=new FileOutputStream(file);
		}catch (FileNotFoundException e) {
	               	e.printStackTrace();
		}
		PdfWriter.getInstance(document,fos);
		document.open();
		
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLUE);
		
		Paragraph p = new Paragraph("Eligibility Report", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);
		
		document.add(p);
		
		
		PdfPTable table = new PdfPTable(7);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 1.5f, 3.5f, 3.0f, 3.0f, 1.5f, 1.5f,3.5f });
		table.setSpacingBefore(10);
		
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.WHITE);
		cell.setPadding(5);
		
		cell.setPhrase(new Phrase("Citizine Name", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Plan Name", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Plan Status", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Plan Start Date", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Plan End Date", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Benifit Amount", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Denile Reason", font));
		table.addCell(cell);
		
		table.addCell(appEntity.getFullName());
		table.addCell(eligData.getPlanName());
		table.addCell(eligData.getPlanStatus());
		table.addCell(eligData.getPlanStartDate()+"");
		table.addCell(eligData.getPlanEndDate()+"");
		table.addCell(eligData.getBenefitAmt()+"");
		table.addCell(eligData.getDenialReason()+"");
		
		document.add(table);
			document.close();
			
			
			String subject="HIS Eligibility Info";
			String body="HIS Eligibility Info";
			
			updateTrigger(eligData.getCaseNum(),file);
			file.delete();
	}
	private void updateTrigger(Long caseNum,File file) throws Exception{
		CoTrgEntity coEntity = coTrgRepo.findByCaseNum(caseNum);
		byte[]arr =new byte[(byte)file.length()];
		FileInputStream fis=new FileInputStream(file);
		fis.read(arr);
		coEntity.setPdf(arr);
		coEntity.setTrgStatus("Completed");
		coTrgRepo.save(coEntity);
		fis.close();
		
	}

}
