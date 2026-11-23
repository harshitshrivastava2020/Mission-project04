package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.SubjectBean;
import in.co.rays.proj4.model.SubjectModel;

public class TestSubjectModel {
	public static void main(String[] args) {
		testAdd();
		testUpdate();
		testDelete();
		testFindByPk();
		testSearch();
	}

	private static void testAdd() {
		SubjectBean bean = new SubjectBean();
		SubjectModel model = new SubjectModel();

		// bean.setId(1);
		bean.setName("Marketing management");
		bean.setDescription("Marketing strategies");
		bean.setCourseId(6);
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.add(bean);

	}

	private static void testUpdate() {
		SubjectBean bean = new SubjectBean();
		SubjectModel model = new SubjectModel();

		bean.setName("Financial management");
		bean.setDescription("Financial decision");
		bean.setCourseId(6);
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		bean.setId(5);

		model.update(bean);
	}

	private static void testDelete() {
		SubjectModel model = new SubjectModel();
		model.delete(7);

	}

	private static void testFindByPk() {
		SubjectBean bean = new SubjectBean();
		SubjectModel model = new SubjectModel();

		bean = model.findByPk(2);

		System.out.println("Id- " + bean.getId());
		System.out.println("name- " + bean.getName());
		System.out.println("description- " + bean.getDescription());
		System.out.println("course id- " + bean.getCourseId());
		System.out.println("created by- " + bean.getCreatedBy());
		System.out.println("modified by- " + bean.getModifiedBy());
		System.out.println("created datetime- " + bean.getCreatedDatetime());
		System.out.println("modified datetime- " + bean.getModifiedDatetime());
	}

	private static void testSearch() {
		SubjectBean bean = new SubjectBean();
		SubjectModel model = new SubjectModel();

		List<SubjectBean> list = model.search(bean, 1, 3);
		Iterator<SubjectBean> it = list.iterator();
		while (it.hasNext()) {
			bean = it.next();
			System.out.println("Id- " + bean.getId());
			System.out.println("name- " + bean.getName());
			System.out.println("description- " + bean.getDescription());
			System.out.println("course id- " + bean.getCourseId());
			System.out.println("created by- " + bean.getCreatedBy());
			System.out.println("modified by- " + bean.getModifiedBy());
			System.out.println("created datetime- " + bean.getCreatedDatetime());
			System.out.println("modified datetime- " + bean.getModifiedDatetime());
			System.out.println("-----------------------------------------------------");
		}
	}
}
