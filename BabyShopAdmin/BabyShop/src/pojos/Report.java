package pojos;

import java.util.HashMap;
import java.util.List;

public class Report {
	private String name;
	private String displayName;
	private String link;
	private String paramName;
	private List<String> paramValues;
	
	public String getName(){
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public List<String> getParamValues() {
		return paramValues;
	}

	public void setParamValues(List<String> paramValues) {
		this.paramValues = paramValues;
	}

	
	
	
}
