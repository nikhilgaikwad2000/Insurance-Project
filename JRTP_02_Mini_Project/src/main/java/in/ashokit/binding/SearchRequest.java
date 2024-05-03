package in.ashokit.binding;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data

public class SearchRequest {

	
	private String planName;
	private String planStatus;
	private String gender;
	
}
