package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.co.rays.proj4.bean.MarksheetBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class MarksheetModel extends BaseModel<MarksheetBean> {

	@Override
	public long add(MarksheetBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;

		MarksheetBean existBean = findByRollno(bean.getRollNo());

		if (existBean != null) {
			throw new DuplicateRecordException("rollno already exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO ST_MARKSHEET VALUES(?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, nextPK());
			pstmt.setString(2, bean.getRollNo());
			pstmt.setLong(3, bean.getStudentId());
			pstmt.setString(4, bean.getName());
			pstmt.setInt(5, bean.getPhysics());
			pstmt.setInt(6, bean.getChemistry());
			pstmt.setInt(7, bean.getMaths());
			pstmt.setString(8, bean.getCreatedBy());
			pstmt.setString(9, bean.getModifiedBy());
			pstmt.setTimestamp(10, bean.getCreatedDatetime());
			pstmt.setTimestamp(11, bean.getModifiedDatetime());
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("Data inserted successfully " + i + " row affected");

		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return bean.getId();
	}

	@Override
	public void update(MarksheetBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		MarksheetBean beanExist = findByRollno(bean.getRollNo());

		// Check if updated Roll no already exist
		if (beanExist != null && beanExist.getId() != bean.getId()) {
			throw new DuplicateRecordException("Roll No is already exist");
		}

		try {
			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE ST_MARKSHEET SET ROLL_NO=?,STUDENT_ID=?,NAME=?,PHYSICS=?,CHEMISTRY=?,MATHS=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
			pstmt.setString(1, bean.getRollNo());
			pstmt.setLong(2, bean.getStudentId());
			pstmt.setString(3, bean.getName());
			pstmt.setInt(4, bean.getPhysics());
			pstmt.setInt(5, bean.getChemistry());
			pstmt.setInt(6, bean.getMaths());
			pstmt.setString(7, bean.getCreatedBy());
			pstmt.setString(8, bean.getModifiedBy());
			pstmt.setTimestamp(9, bean.getCreatedDatetime());
			pstmt.setTimestamp(10, bean.getModifiedDatetime());
			pstmt.setLong(11, bean.getId());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	@Override
	public String getWhereClause(MarksheetBean bean) {
		StringBuffer sql = new StringBuffer();

		if (bean != null) {
			System.out.println("service" + bean.getName());
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getRollNo() != null && bean.getRollNo().length() > 0) {
				sql.append(" AND roll_no like '" + bean.getRollNo() + "%'");
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" AND name like '" + bean.getName() + "%'");
			}
			if (bean.getPhysics() > 0) {
				sql.append(" AND physics = " + bean.getPhysics());
			}
			if (bean.getChemistry() > 0) {
				sql.append(" AND chemistry = " + bean.getChemistry());
			}
			if (bean.getMaths() > 0) {
				sql.append(" AND maths = '" + bean.getMaths());
			}

		}
		return sql.toString();

	}

	public MarksheetBean findByRollno(String name) {

		MarksheetBean bean = findByUniqueColumn("name", name);

		return bean;
	}

	@Override
	public String getTable() {
		return "st_marksheet";

	}

	@Override
	public MarksheetBean getBean() {
		return new MarksheetBean();

	}

}
