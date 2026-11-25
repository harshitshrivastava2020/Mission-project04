package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.FacultyBean;
import in.co.rays.proj4.model.FacultyModel;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/FacultyCtl")
public class FacultyCtl extends BaseCtl<FacultyBean, FacultyModel> {

	@Override
	public void preload() {
		super.preload();
	}

	@Override
	public boolean validate(HttpServletRequest request) {
		return super.validate(request);
	}

	@Override
	public FacultyBean populateBean(HttpServletRequest request) {
		return super.populateBean(request);
	}

	@Override
	public String getView() {
		return ORSView.FACULTY_CTL;
	}

	@Override
	public FacultyModel getModel() {
		return new FacultyModel();
	}

}
