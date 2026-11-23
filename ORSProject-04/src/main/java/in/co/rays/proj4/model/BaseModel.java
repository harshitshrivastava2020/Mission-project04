package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public abstract class BaseModel<T extends BaseBean> {

	public abstract long add(T bean) throws ApplicationException, DuplicateRecordException;

	public abstract void update(T bean) throws ApplicationException, DuplicateRecordException;

	public abstract String getWhereClause(T bean);

	public abstract String getTable();

	public abstract T getBean();

	public Integer nextPK() throws DatabaseException {

		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM " + getTable());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();

		} catch (SQLException e) {
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk + 1;

	}

	public void delete(int id) throws DatabaseException {

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM " + getTable() + " WHERE ID = ?");

			pstmt.setInt(1, id);

			pstmt.executeUpdate();

			conn.commit();
			System.out.println("record deleted successfully");

		} catch (SQLException e) {
			JDBCDataSource.trnRollBack(conn);
			throw new DatabaseException("Exception : Exception in Delete Record");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	public T findByPk(long id) throws DatabaseException {

		Connection conn = null;
		T bean = null;

		try {
			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM " + getTable() + " WHERE ID = ?");

			pstmt.setLong(1, id);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = getBean();
				bean.setResultset(rs);
			}

		} catch (SQLException e) {
			throw new DatabaseException("Exception : Exception in Find By Pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return bean;

	}

	public T findByUniqueColumn(String column, String value) throws DatabaseException {

		Connection conn = null;
		T bean = null;

		try {
			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn
					.prepareStatement("SELECT * FROM " + getTable() + " WHERE " + column + " = ?");

			pstmt.setString(1, value);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = getBean();
				bean.setResultset(rs);
			}

		} catch (SQLException e) {
			throw new DatabaseException("Exception : Exception in Find By Unique Column");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return bean;

	}

	public List<T> search(T bean, int pageNo, int pageSize) throws DatabaseException {

		Connection conn = null;
		List<T> list = new ArrayList<T>();
		StringBuffer sql = new StringBuffer("SELECT * FROM " + getTable() + " WHERE 1=1");

		try {

			sql.append(getWhereClause(bean));

			if (pageSize > 0) {
				int index = (pageNo - 1) * pageSize;
				sql.append(" LIMIT " + index + ", " + pageSize);
			}

			conn = JDBCDataSource.getConnection();

			System.out.println("sql ========> " + sql.toString());

			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = getBean();
				bean.setResultset(rs);
				list.add(bean);
			}

		} catch (SQLException e) {
			throw new DatabaseException("Exception : Exception in Find By Unique Column");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return list;

	}

}
