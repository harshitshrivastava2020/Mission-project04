package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.bean.FacultyBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class FacultyModel extends BaseModel<FacultyBean> {

	@Override
	public long add(FacultyBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;

		FacultyBean existBean = findByEmail(bean.getEmail());

		if (existBean != null) {
			throw new DuplicateRecordException("email id already exist");
		}

		CollegeModel cmodel = new CollegeModel();
		CollegeBean cbean = cmodel.findByPk(bean.getCollegeId());
		bean.setCollegeName(cbean.getName());

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn
					.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setLong(1, nextPK());
			pstmt.setLong(2, bean.getCollegeId());
			pstmt.setString(3, bean.getFirstName());
			pstmt.setString(4, bean.getLastName());
			pstmt.setString(5, bean.getEmail());
			pstmt.setString(6, bean.getMobileNo());
			pstmt.setString(7, bean.getAddress());
			pstmt.setString(8, bean.getGender());
			pstmt.setDate(9, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(10, bean.getCreatedBy());
			pstmt.setString(11, bean.getModifiedBy());
			pstmt.setTimestamp(12, bean.getCreatedDatetime());
			pstmt.setTimestamp(13, bean.getModifiedDatetime());

			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("Data inserted successfully " + i + " row affected");
		} catch (SQLException e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean.getId();
	}

	@Override
	public void update(FacultyBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;

		FacultyBean existBean = findByEmail(bean.getEmail());

		if (existBean != null && existBean.getId() != bean.getId()) {
			throw new DuplicateRecordException("email id already exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("update " + getTable()
					+ " set college_id=?, college_name=?, first_name=?, last_name=?, email=?, mobile_no=?, address=?, gender=?, date_of_birth=?, modified_by=?, modified_datetime=? where id=?");
			pstmt.setLong(1, bean.getCollegeId());
			pstmt.setString(2, bean.getFirstName());
			pstmt.setString(3, bean.getLastName());
			pstmt.setString(4, bean.getEmail());
			pstmt.setString(5, bean.getMobileNo());
			pstmt.setString(6, bean.getAddress());
			pstmt.setString(7, bean.getGender());
			pstmt.setDate(8, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(9, bean.getModifiedBy());
			pstmt.setTimestamp(10, bean.getModifiedDatetime());
			pstmt.setLong(11, bean.getId());

			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("Data updated successfully " + i + " row affected");
		} catch (SQLException e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	@Override
	public String getWhereClause(FacultyBean bean) {
		StringBuffer sql = new StringBuffer("");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getCollegeId() > 0) {
				sql.append(" and college_id = " + bean.getCollegeId());
			}
			if (bean.getCollegeName() != null && bean.getCollegeName().length() > 0) {
				sql.append(" and college_name like'" + bean.getCollegeName() + "'");
			}
			if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
				sql.append(" and first_name like '" + bean.getFirstName() + "'");
			}
			if (bean.getLastName() != null && bean.getLastName().length() > 0) {
				sql.append(" and last_name like '" + bean.getLastName() + "'");
			}
			if (bean.getEmail() != null && bean.getEmail().length() > 0) {
				sql.append(" and email like '" + bean.getEmail() + "'");
			}
			if (bean.getMobileNo() != null && bean.getMobileNo().length() > 10) {
				sql.append(" and mobie_no like '" + bean.getMobileNo() + "'");
			}
			if (bean.getAddress() != null && bean.getAddress().length() > 0) {
				sql.append(" and address = '" + bean.getAddress() + "%'");
			}
			if (bean.getGender() != null && bean.getGender().length() > 0) {
				sql.append(" and gender like '" + bean.getGender() + "'");
			}
			if (bean.getDob() != null) {
				sql.append(" and date_of_birth = " + bean.getDob());
			}
			if (bean.getCreatedBy() != null && bean.getCreatedBy().length() > 0) {
				sql.append(" and created_by like'" + bean.getCreatedBy() + "'");
			}
			if (bean.getModifiedBy() != null && bean.getModifiedBy().length() > 0) {
				sql.append(" and modified_by like'" + bean.getModifiedBy() + "'");
			}
			if (bean.getCreatedDatetime() != null) {
				sql.append(" and created_datetime like'" + bean.getCreatedDatetime() + "'");
			}
			if (bean.getModifiedDatetime() != null) {
				sql.append(" and modified_datetime like'" + bean.getModifiedDatetime() + "'");
			}
		}
		return sql.toString();
	}

	public FacultyBean findByEmail(String name) {

		FacultyBean bean = findByUniqueColumn("name", name);

		return bean;
	}

	@Override
	public String getTable() {
		return "st_faculty";

	}

	@Override
	public FacultyBean getBean() {
		return new FacultyBean();

	}

}
