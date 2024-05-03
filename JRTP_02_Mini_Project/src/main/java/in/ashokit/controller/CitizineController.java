package in.ashokit.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.binding.CitizinePlan;
import in.ashokit.binding.SearchRequest;
import in.ashokit.serviceImpl.CitizineServiceImpl;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class CitizineController {

	@Autowired

	private CitizineServiceImpl service;

	@GetMapping(value = "/planname")
	public ResponseEntity<List<String>> getPlanname() {
		List<String> planeName = service.getPlaneName();
		return new ResponseEntity<>(planeName, HttpStatus.OK);
	}

	@GetMapping(value = "/planstatus")
	public ResponseEntity<List<String>> getPlanstatus() {
		List<String> planeStatus = service.getPlaneStatus();
		return new ResponseEntity<>(planeStatus, HttpStatus.OK);
	}

	@PostMapping(value="search")
	public ResponseEntity<List<CitizinePlan>> getplan(@RequestBody SearchRequest request) {
		List<CitizinePlan> citizensPlan = service.getCitizensPlan(request);

		return new ResponseEntity<>(citizensPlan, HttpStatus.OK);
	}

	@GetMapping(value = "/excel")
	public void exportExcel(HttpServletResponse response) throws Exception {
		response.setContentType("application/octet-stream");
		String key = "Content-Disposition";
		String value = "attachment;filename=citizens.xls";
		response.setHeader(key, value);
		service.exportExcel(response);
		response.flushBuffer();
	}

	@GetMapping(value = "/pdf")
	public void exportpdf(HttpServletResponse response) throws Exception {
		response.setContentType("application/pdf");
		String key = "Content-Disposition";
		String value = "attachment;filename=plans.pdf";
		response.setHeader(key, value);
		service.exportPdf(response);
		response.flushBuffer();
	}
}
