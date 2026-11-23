package in.co.rays.proj4.bean;

import java.sql.ResultSet;

public class RoleBean extends BaseBean {

	public static final int ADMIN = 1;
	public static final int STUDENT = 2;
	public static final int COLLEGE = 3;
	public static final int FACULTY = 4;
	public static final int KIOSK = 5;

	public String name;
	public String description;

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
	
	@Override
	public void setResultset(ResultSet rs) {
		try {
			this.setName(rs.getString("NAME"));
			this.setDescription(rs.getString("DESCRIPTION"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.setResultset(rs);
	}

	@Override
	public String getValue() {
		return name;
	}

}
