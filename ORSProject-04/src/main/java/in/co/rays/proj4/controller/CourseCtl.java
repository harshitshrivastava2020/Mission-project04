package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.model.CourseModel;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/CourseCtl")
public class CourseCtl extends BaseCtl<CourseBean, CourseModel> {

	@Override
	public void preload() {
		super.preload();
	}

	@Override
	public boolean validate(HttpServletRequest request) {
		return super.validate(request);
	}

	@Override
	public CourseBean populateBean(HttpServletRequest request) {
		return super.populateBean(request);
	}

	@Override
	public String getView() {
		return ORSView.COURSE_CTL;
	}

	@Override
	public CourseModel getModel() {
		return new CourseModel();
	}

}
