package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Iterator;

import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.model.RoleModel;

//1 = Admit
//2 = student
//3 = faculty
//4 = college
//5 = KIOSK

public class TestRoleModel {
	public static void main(String[] args) {
//		testAdd();
		testUpdate();
//		testDelete();
//		testFindByPk();
//		testSearch();
	}

	private static void testAdd() {
		RoleBean bean = new RoleBean();
		RoleModel model = new RoleModel();

		bean.setName("Admin");
		bean.setDescription("Admin Roll");
		bean.setCreatedBy("Harshit");
		bean.setModifiedBy("Harshit");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.add(bean);

	}

	private static void testUpdate() {
		RoleBean bean = new RoleBean();
		RoleModel model = new RoleModel();

		// bean.setId(1);
		bean.setName("Admin");
		bean.setDescription("Admin Roll");
		bean.setCreatedBy("Harshit");
		bean.setModifiedBy("Harshit");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		bean.setId(1);

		model.update(bean);
	}

	private static void testDelete() {
		RoleModel model = new RoleModel();

		model.delete(6);

	}

	private static void testFindByPk() {
		RoleBean bean = new RoleBean();
		RoleModel model = new RoleModel();

		bean = model.findByPk(1);
		System.out.println(bean.getName());
		System.out.println(bean.getDescription());
		System.out.println(bean.getCreatedBy());
		System.out.println(bean.getModifiedBy());
		System.out.println(bean.getCreatedDatetime());
		System.out.println(bean.getModifiedDatetime());
	}

	private static void testSearch() {
		RoleBean bean = new RoleBean();
		RoleModel model = new RoleModel();

		List<RoleBean> list = model.search(bean, 1, 5);

		java.util.Iterator<RoleBean> it = list.iterator();
		while (it.hasNext()) {
			bean = it.next();
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getDescription());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());

			System.out.println("--------------------------------------");
		}
	}

}
