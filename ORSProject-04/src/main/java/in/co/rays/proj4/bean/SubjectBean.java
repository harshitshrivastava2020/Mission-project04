package in.co.rays.proj4.bean;

public class SubjectBean extends BaseBean {
	
	private String name;
	private String description;
	private long couseId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getCourseId() {
		return couseId;
	}

	public void setCourseId(long couseId) {
		this.couseId = couseId;
	}

	@Override
	public String getValue() {
		return null;
	}

}
