package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class CollegeModel extends BaseModel<CollegeBean> {

	@Override
	public long add(CollegeBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;

		CollegeBean duplicateCollegeName = findByName(bean.getName());

		if (duplicateCollegeName != null) {
			throw new DuplicateRecordException("College Name already exists");
		}
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO ST_COLLEGE VALUES(?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, nextPK());
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getAddress());
			pstmt.setString(4, bean.getState());
			pstmt.setString(5, bean.getCity());
			pstmt.setString(6, bean.getPhoneNo());
			pstmt.setString(7, bean.getCreatedBy());
			pstmt.setString(8, bean.getModifiedBy());
			pstmt.setTimestamp(9, bean.getCreatedDatetime());
			pstmt.setTimestamp(10, bean.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();

		} catch (Exception e) {
			JDBCDataSource.trnRollBack(conn);
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return bean.getId();
	}

	@Override
	public void update(CollegeBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;

		CollegeBean beanExist = findByName(bean.getName());

		// Check if updated College already exist
		if (beanExist != null && beanExist.getId() != bean.getId()) {

			throw new DuplicateRecordException("College is already exist");
		}		

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE ST_COLLEGE SET NAME=?,ADDRESS=?,STATE=?,CITY=?,PHONE_NO=?,MODIFIED_BY=?,MODIFIED_DATETIME=? WHERE ID=?");
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getAddress());
			pstmt.setString(3, bean.getState());
			pstmt.setString(4, bean.getCity());
			pstmt.setString(5, bean.getPhoneNo());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getModifiedDatetime());
			pstmt.setLong(8, bean.getId());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	@Override
	public String getWhereClause(CollegeBean bean) {
		StringBuffer sql = new StringBuffer();

		if(bean!=null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" AND NAME like '" + bean.getName() + "%'");
			}
			if (bean.getAddress() != null && bean.getAddress().length() > 0) {
				sql.append(" AND ADDRESS like '" + bean.getAddress() + "%'");
			}
			if (bean.getState() != null && bean.getState().length() > 0) {
				sql.append(" AND STATE like '" + bean.getState() + "%'");
			}
			if (bean.getCity() != null && bean.getCity().length() > 0) {
				sql.append(" AND CITY like '" + bean.getCity() + "%'");
			}
			if (bean.getPhoneNo() != null && bean.getPhoneNo().length() > 0) {
				sql.append(" AND PHONE_NO = " + bean.getPhoneNo());
			}
		}
		return sql.toString();
	}
	
	
	public CollegeBean findByName(String name) {

		CollegeBean bean = findByUniqueColumn("name", name);

		return bean;
	}

	@Override
	public String getTable() {
		return "st_college";
	}

	@Override
	public CollegeBean getBean() {
		return new CollegeBean();
	}

}
