package in.ashokit.serviceImpl;

import java.time.LocalDate;

import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.hibernate.sql.ast.tree.predicate.BetweenPredicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.binding.EligResponce;
import in.ashokit.entity.CaseEntity;
import in.ashokit.entity.CitizineAppEntity;
import in.ashokit.entity.CoTrgEntity;
import in.ashokit.entity.EdEligDtlsEntity;
import in.ashokit.entity.EducationEntity;
import in.ashokit.entity.IncomeEntity;
import in.ashokit.entity.KidEntity;
import in.ashokit.entity.PlanEntity;
import in.ashokit.repo.CaseRepo;
import in.ashokit.repo.CitizineAppRepo;
import in.ashokit.repo.CoTriggerRepository;
import in.ashokit.repo.EdEligRepository;
import in.ashokit.repo.EducationRepo;
import in.ashokit.repo.IncomeRepo;
import in.ashokit.repo.KidsRepo;
import in.ashokit.repo.PlanRepo;
import in.ashokit.service.EdService;

@Service
public class EdServiceImpl implements EdService {

	@Autowired
	private IncomeRepo incomeRepo;

	@Autowired
	private CaseRepo caseRepo;
	
	@Autowired
	private PlanRepo planRepo;

	@Autowired
	private KidsRepo kidsRepo;
	
	@Autowired
	private CitizineAppRepo citizineAppRepo;
	
	@Autowired
	private EducationRepo educationRepo;
	
	@Autowired
	private CoTriggerRepository coTrgRepo;
	
	@Autowired
	private EdEligRepository eligRepository;
	
	boolean noKids=false;
	boolean ageFlag=true;

	@Override
	public EligResponce determineEligibility(Long caseNum) {

		Integer planId = null;
		String planName = null;
		Integer appId=null;
		EligResponce responce = new EligResponce();

		Optional<IncomeEntity> findById = incomeRepo.findById(caseNum);
		IncomeEntity income = findById.get();
		
		List<KidEntity> kids = kidsRepo.findByCaseNum(caseNum);

		Optional<CaseEntity> caseEntity = caseRepo.findById(caseNum);
		if (caseEntity.isPresent()) {
			planId = caseEntity.get().getPlanId();
			 appId = caseEntity.get().getAppId();
		}
		
		Optional<PlanEntity> planEntity = planRepo.findById(planId);
		if (planEntity.isPresent()) {
			planName = planEntity.get().getPlanName();
		}
		
		Optional<CitizineAppEntity> appEntity = citizineAppRepo.findById(appId);
		CitizineAppEntity citizineApp = appEntity.get();
		

		if ("SNAP".equals(planName)) {

			
			if (income.getMonthalySalaryIncome() > 300) {
				responce.setPlanStatus("DENIDE");
				responce.setDenialReason("High Income");
				}
			
			}else if ("CCAP".equals(planName)) {
				

				if (!kids.isEmpty()) {
					kids.forEach(kid -> {
						LocalDate dob = kid.getKidsDob();
						LocalDate today = LocalDate.now();
						Period p = Period.between(dob, today);
						int years = p.getYears();
						if (years > 16) {
							ageFlag=false;
						}
					});
				}else {
					responce.setDenialReason("No Kids Avaible");
					noKids=true; 
				}
			
			if(income.getMonthalySalaryIncome()>300) {
				responce.setDenialReason("High income");
			}
			if(noKids && income.getMonthalySalaryIncome()>300) {
				responce.setDenialReason("High income + No Kids");
			}
			
			if(!ageFlag) {
				responce.setDenialReason("kids age >16");
              } 
			if(income.getMonthalySalaryIncome()>300 && !ageFlag){
				responce.setDenialReason("High income + kids age >16");
			}
			
			
			}
			else if ("Medicaid".equals(planName)) {
				long salaryIncome = income.getMonthalySalaryIncome();
				long propertyIncome = income.getPropertyIncome();
				long rentIncome = income.getRentIncome();
				if(rentIncome>0) {
					responce.setDenialReason("Rent Income Is available");
				}
				if(propertyIncome >0) {
					responce.setDenialReason("property Income Is available");
				}
				if(rentIncome >0 && propertyIncome >0) {
					responce.setDenialReason("Rent + property Income Is available ");
				}
				if(income.getMonthalySalaryIncome()>300 && rentIncome >0 && propertyIncome >0) {
					responce.setDenialReason("Sqalary Incomr +Rent + property Income Is available ");
				}
				
			} 
			else if ("Medicare".equals(planName)) {
				LocalDate dob = citizineApp.getDob();
				LocalDate now = LocalDate.now();
				Period p = Period.between(dob, now);
				  int years = p.getYears();
				  if(years<65) {
					  responce.setDenialReason("Age<65 Years");
				  }
			} 
			else if ("RIW".equals(planName)) {
				EducationEntity educationEntity = educationRepo.findByCaseNum(caseNum);
				Integer graductionYear = educationEntity.getGraductionYear();
				if (graductionYear <=0) {
					responce.setDenialReason("Not Graduated");
				}if(income.getMonthalySalaryIncome()>0) {
					responce.setDenialReason("Already an Employee");
				}
               
			}
              responce.setPlanName(planName);
              if(responce.getDenialReason()!=null) {
            	  responce.setPlanStatus("Denide");
              }else {
            	  responce.setPlanStatus("Approved"); 
            	  responce.setPlanStartDate(LocalDate.now().plusDays(1));
            	  responce.setPlanEndDate(LocalDate.now().plusMonths(3));
            	  responce.setBenefitAmt(350.00);
              }
              
              EdEligDtlsEntity edEntity=new EdEligDtlsEntity();
              BeanUtils.copyProperties(responce, edEntity);
              
              eligRepository.save(edEntity);
              
              CoTrgEntity coEntity=new CoTrgEntity();
              coEntity.setCaseNum(caseNum);
              coEntity.setTrgStatus("Pending");
              
              
              coTrgRepo.save(coEntity);
	
		return responce;
		
	}
}

