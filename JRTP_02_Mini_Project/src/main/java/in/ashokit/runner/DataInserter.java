package in.ashokit.runner;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import in.ashokit.binding.CitizinePlan;
import in.ashokit.repository.Citizinerepository;

@Component
public class DataInserter implements ApplicationRunner {

	@Autowired
	private Citizinerepository repo;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		CitizinePlan cp1 = new CitizinePlan();
		cp1.setCname("John");
		cp1.setGender("Male");
		cp1.setPhno(90909);
		cp1.setPlanName("SNAP");
		cp1.setPlanStatus("Approved");
		cp1.setSsn(797979);

		CitizinePlan cp2 = new CitizinePlan();
		cp2.setCname("Smith");
		cp2.setGender("Male");
		cp2.setPhno(90909);
		cp2.setPlanName("SNAP");
		cp2.setPlanStatus("Denied");
		cp2.setSsn(797979);

		CitizinePlan cp3 = new CitizinePlan();
		cp3.setCname("Charles");
		cp3.setGender("Fe-Male");
		cp3.setPhno(90909);
		cp3.setPlanName("CCAP");
		cp3.setPlanStatus("Approved");
		cp3.setSsn(797979);

		CitizinePlan cp4 = new CitizinePlan();
		cp4.setCname("Jenny");
		cp4.setGender("Fe-Male");
		cp4.setPhno(90909);
		cp4.setPlanName("CCAP");
		cp4.setPlanStatus("Denied");
		cp4.setSsn(797979);
		List<CitizinePlan> list = Arrays.asList(cp1, cp2, cp3, cp4);

		repo.saveAll(list);

	}

}
