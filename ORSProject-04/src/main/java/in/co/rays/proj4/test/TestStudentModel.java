package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.model.StudentModel;

public class TestStudentModel {
	public static void main(String[] args) throws ParseException {
//		testAdd();
		testUpdate();
//		testDelete();
//		testFindByPk();
//		testSearch();
	}

	private static void testAdd() throws ParseException {
		StudentBean bean = new StudentBean();
		StudentModel model = new StudentModel();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		// bean.setId(1);
		bean.setCollegeId(2);
		bean.setCollegeName("IPS Academy");
		bean.setFirstName("Shreya");
		bean.setLastName("Daya");
		bean.setDob(sdf.parse("2002-11-08"));
		bean.setMobileNo("7064987445");
		bean.setEmail("neha@gmail.com");
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.add(bean);
	}

	private static void testUpdate() throws ParseException {
		StudentBean bean = new StudentBean();
		StudentModel model = new StudentModel();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setCollegeId(2);
		bean.setCollegeName("IPS Academy");
		bean.setFirstName("Aaradhiya");
		bean.setLastName("Chouhan");
		bean.setDob(sdf.parse("2002-11-08"));
		bean.setMobileNo("7064987445");
		bean.setEmail("neha@gmail.com");
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		bean.setId(4);

		model.update(bean);
	}

	private static void testDelete() {
		StudentModel model = new StudentModel();
		model.delete(5);

	}

	private static void testFindByPk() {
		StudentBean bean = new StudentBean();
		StudentModel model = new StudentModel();

		bean = model.findByPk(3);

		System.out.println("id- " + bean.getId());
		System.out.println("college id- " + bean.getCollegeId());
		System.out.println("college name- " + bean.getCollegeName());
		System.out.println("First name- " + bean.getFirstName());
		System.out.println("Last name- " + bean.getLastName());
		System.out.println("Dob- " + bean.getDob());
		System.out.println("Mobile no- " + bean.getMobileNo());
		System.out.println("Email- " + bean.getEmail());
		System.out.println("created by- " + bean.getCreatedBy());
		System.out.println("modified by- " + bean.getModifiedBy());
		System.out.println("created datetime- " + bean.getCreatedDatetime());
		System.out.println("modified datetime- " + bean.getModifiedDatetime());

	}

	private static void testSearch() {
		StudentBean bean = new StudentBean();
		StudentModel model = new StudentModel();

		bean.setFirstName("Aaradhiya");
		List<StudentBean> list = model.search(bean, 1, 3);

		Iterator<StudentBean> it = list.iterator();
		while (it.hasNext()) {
			bean = it.next();

			System.out.println("id- " + bean.getId());
			System.out.println("college id- " + bean.getCollegeId());
			System.out.println("college name- " + bean.getCollegeName());
			System.out.println("First name- " + bean.getFirstName());
			System.out.println("Last name- " + bean.getLastName());
			System.out.println("Dob- " + bean.getDob());
			System.out.println("Mobile no- " + bean.getMobileNo());
			System.out.println("Email- " + bean.getEmail());
			System.out.println("created by- " + bean.getCreatedBy());
			System.out.println("modified by- " + bean.getModifiedBy());
			System.out.println("created datetime- " + bean.getCreatedDatetime());
			System.out.println("modified datetime- " + bean.getModifiedDatetime());

			System.out.println("--------------------------------------------------------------------");
		}

	}
}
