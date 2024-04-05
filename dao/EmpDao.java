package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.EmpDto;
import utils.ConnectionHelper;

public class EmpDao {
	
	//전체 조회
	public List<EmpDto> getEmpList() {
		
		List<EmpDto> empList = new ArrayList<EmpDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionHelper.getConnection("oracle");
			String sql = "select * from emp";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				EmpDto empDto = EmpDto
								.builder()
								.empno(rs.getInt("empno"))
								.ename(rs.getString("ename"))
								.job(rs.getString("job"))
								.mgr(rs.getInt("mgr"))
								.hiredate(rs.getDate("hiredate"))
								.sal(rs.getInt("sal"))
								.comm(rs.getInt("comm"))
								.deptno(rs.getInt("deptno"))
								.build();
				empList.add(empDto);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionHelper.close(rs);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return empList;
	}
	
	
	//조건조회
	public EmpDto getEmpByEmpno(int empno) {
		
		EmpDto empByEmpno = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionHelper.getConnection("oracle");
			String sql = "select * emp where empno=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, empno);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				empByEmpno = EmpDto
							 .builder()
							 .empno(rs.getInt("empno"))
							 .ename(rs.getString("ename"))
							 .job(rs.getString("job"))
							 .mgr(rs.getInt("mgr"))
							 .hiredate(rs.getDate("hiredate"))
							 .sal(rs.getInt("sal"))
							 .comm(rs.getInt("comm"))
							 .deptno(rs.getInt("deptno"))
							 .build();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionHelper.close(rs);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return empByEmpno;
	}
	
	
	//삽입
	public int insert(EmpDto empDto) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		
		try {
			conn = ConnectionHelper.getConnection("oracle");
			String sql = "insert into emp(empno,ename,job,mgr,hiredate,sal,comm,deptno) values(?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, empDto.getEmpno());
			pstmt.setString(2, empDto.getEname());
			pstmt.setString(3, empDto.getJob());
			pstmt.setInt(4, empDto.getMgr());
			pstmt.setDate(5, empDto.getHiredate());
			pstmt.setInt(6, empDto.getSal());
			pstmt.setInt(7, empDto.getComm());
			pstmt.setInt(8, empDto.getDeptno());
			
			rowCount = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return rowCount;
	}
	
	//수정
	public int update(EmpDto empDto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		
		try {
			conn = ConnectionHelper.getConnection("oracle");
			String sql = "update emp set ename=?,job=?,sal=?, hiredate=? where empno=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, empDto.getEname());
			pstmt.setString(2, empDto.getJob());
			pstmt.setInt(3, empDto.getSal());
			pstmt.setDate(4, empDto.getHiredate());
			pstmt.setInt(5, empDto.getEmpno());
			
			rowCount = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return rowCount;
	}
	
	
	//삭제
	public int delete(int empno) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		
		try {
			conn = ConnectionHelper.getConnection("oracle");
			String sql = "delete from emp where empno=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, empno);
			
			rowCount = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return rowCount;
	}
	
	
	//like 검색
	public List<EmpDto> findEmpByEname(String ename) {
		
		List<EmpDto> empList = new ArrayList<EmpDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionHelper.getConnection("oracle");
			String sql = "select * from emp where ename like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+ename+"%");
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				EmpDto empDto = EmpDto
								.builder()
								.empno(rs.getInt("empno"))
								.ename(rs.getString("ename"))
								.job(rs.getString("job"))
								.mgr(rs.getInt("mgr"))
								.hiredate(rs.getDate("hiredate"))
								.sal(rs.getInt("sal"))
								.comm(rs.getInt("comm"))
								.deptno(rs.getInt("deptno"))
								.build();
				empList.add(empDto);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionHelper.close(rs);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return empList;
	}
}
