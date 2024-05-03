package in.ashokit.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.binding.EducationDetails;
import in.ashokit.binding.IncomeDetails;
import in.ashokit.binding.KidDetails;
import in.ashokit.binding.KidsInfo;
import in.ashokit.binding.PlanSelction;
import in.ashokit.binding.Summary;
import in.ashokit.entity.CaseEntity;
import in.ashokit.entity.CitizineAppEntity;
import in.ashokit.entity.EducationEntity;
import in.ashokit.entity.IncomeEntity;
import in.ashokit.entity.KidEntity;
import in.ashokit.entity.PlanEntity;
import in.ashokit.repo.CaseRepo;
import in.ashokit.repo.CitizineAppRepo;
import in.ashokit.repo.EducationRepo;
import in.ashokit.repo.IncomeRepo;
import in.ashokit.repo.KidsRepo;
import in.ashokit.repo.PlanRepo;
import in.ashokit.service.DcService;

@Service
public class DcServiceImpl implements DcService {

	@Autowired
	private CitizineAppRepo citizineAppRepo;

	@Autowired
	private CaseRepo caseRepo;

	@Autowired
	private PlanRepo planRepo;

	@Autowired
	private IncomeRepo incomeRepo;

	@Autowired
	private EducationRepo educationRepo;
	
	@Autowired
	private KidsRepo kidsRepo;

	@Override
	public PlanSelction createPlan(Integer appId) {

		PlanSelction plansel = new PlanSelction();

		Optional<CitizineAppEntity> findById = citizineAppRepo.findById(appId);
		if (findById.isPresent()) {
			// create case
			CaseEntity entity = new CaseEntity();
			entity.setAppId(appId);
			caseRepo.save(entity);

			// fetch plan names to insert into DB

			List<PlanEntity> plans = planRepo.findAll();

			Map<Integer, String> planmap = new HashMap<>();
			plans.forEach(plan -> {
				planmap.put(plan.getPalnId(), plan.getPlanName());
			});

			// preparing responceData

			plansel.setPlansInfo(planmap);
			plansel.setCaseNum(entity.getCaseNum());
		}
		return plansel;
	}

	@Override
	public long updateCitizinePlan(PlanSelction planSelction) {

		long caseNum = planSelction.getCaseNum();
		Integer planId = planSelction.getPlanId();

		Optional<CaseEntity> findById = caseRepo.findById(caseNum);
		if (findById.isPresent()) {
			CaseEntity caseEntity = findById.get();
			caseEntity.setPlanId(planId);
			caseRepo.save(caseEntity);
		}

		return caseNum;
	}

	@Override
	public long saveIncomeDetails(IncomeDetails incomeDetails) {

		IncomeEntity entity = new IncomeEntity();
		BeanUtils.copyProperties(incomeDetails, entity);

		incomeRepo.save(entity);
		long caseNum = incomeDetails.getCaseNum();

		return caseNum;
	}

	@Override
	public long saveEducationDetails(EducationDetails educationDetails) {

		EducationEntity entity = new EducationEntity();
		BeanUtils.copyProperties(educationDetails, entity);
		educationRepo.save(entity);
		long caseNum = educationDetails.getCaseNum();
		return caseNum;
	}

	@Override
	public Summary saveKidsDetails(KidsInfo kidsInfo) {
		
		long caseNum = kidsInfo.getCaseNum();
		
		List<KidDetails> kids = kidsInfo.getKids();
		
		List<KidEntity> kidsEntities =new ArrayList<>();
		kids.forEach(KidDetails->{
			
			KidEntity entity=new KidEntity();
			BeanUtils.copyProperties(KidDetails, entity); 
			entity.setCaseNum(caseNum);
			kidsEntities.add(entity);
		});
		kidsRepo.saveAll(kidsEntities);
		
		return getSummary(caseNum);
	}
	
	private Summary getSummary(Long caseNum) {
		Optional<CaseEntity> caseSummary = caseRepo.findById(caseNum);
		
		
		Integer planId = caseSummary.get().getPlanId();
		Integer appId = caseSummary.get().getAppId();
		
		
		
		Optional<PlanEntity> plan = planRepo.findById(planId);
		String planName = plan.get().getPlanName();
		
		Optional<CitizineAppEntity> app = citizineAppRepo.findById(appId);
	    String fullName = app.get().getFullName();
	    Integer ssn = app.get().getSsn();
		
		
		  IncomeEntity incomeSummary = incomeRepo.findByCaseNum(caseNum);
		   EducationEntity educationSummary = educationRepo.findByCaseNum(caseNum);
	       List<KidEntity> kid = kidsRepo.findByCaseNum(caseNum);
		
		
		
		Summary summary=new Summary();
		summary.setPlanName(planName);
		summary.setFullName(fullName);
		summary.setSsn(ssn);
		
		
		
		
		return summary;
		
	}

}
