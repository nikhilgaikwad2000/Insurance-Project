package in.ashokit.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import in.ashokit.entity.CitizineAppEntity;
import in.ashokit.repo.CitizineAppRepo;
import in.ashokit.service.AppIdService;

@Service
public class AppIdServiceImpl implements AppIdService {

	@Autowired
	private CitizineAppRepo appIdRepo;

	@Override
	public String saveAppID(CitizineAppEntity appId) {
		appId = appIdRepo.save(appId);
		
			return "Citizen data saved....!";
		}
	}

