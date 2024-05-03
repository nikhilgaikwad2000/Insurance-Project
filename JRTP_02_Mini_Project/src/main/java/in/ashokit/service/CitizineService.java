package in.ashokit.service;

import java.util.List;

import in.ashokit.binding.CitizinePlan;
import in.ashokit.binding.SearchRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface CitizineService {

	public List<String> getPlaneName();

	public List<String> getPlaneStatus();

	public List<CitizinePlan> getCitizensPlan(SearchRequest request);

	public void exportExcel(HttpServletResponse responce) throws Exception;

	public void exportPdf(HttpServletResponse responce) throws Exception;
}