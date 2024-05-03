package in.ashokit.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import in.ashokit.binding.CitizineApp;
import in.ashokit.entity.CitizineAppEntity;
import in.ashokit.repo.CitizineAppRepo;

@Service
public class CitizineAppRegistrationServiceImpl implements CitizineAppRegistrationService {
	@Autowired
	private CitizineAppRepo citizineAppRepo;

	private static String REST_URL = "";

	@Override
	public String regestration(CitizineApp app) {

		Integer ssn = app.getSsn();

		WebClient webClient = WebClient.create();
		String stateName = webClient.get().uri(REST_URL, ssn).retrieve().bodyToMono(String.class).block();
		if ("Rhode Island".equals(stateName)) {
			CitizineAppEntity entity = new CitizineAppEntity();
			BeanUtils.copyProperties(app, entity);

			entity = citizineAppRepo.save(entity);

			return "Citizine App Created ,App Id:" + entity.getAppId();

		}

		return "Citizine not belongs to RI....";
	}

}
