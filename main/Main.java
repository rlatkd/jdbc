import java.sql.Date;
import java.util.List;

import dao.EmpDao;
import dto.EmpDto;

public class Main {
	public static void main(String[] args) {
		EmpDao empDao = new EmpDao();
		
		System.out.println("전체조회");
		List<EmpDto> empList = empDao.getEmpList();
		if (empList != null) {
			empPrint(empList);
		} else {
			System.out.println("전체조회 실패");
		}
		
		
		System.out.println("조건조회");
		EmpDto empByEmpno = empDao.getEmpByEmpno(7788);
		if (empByEmpno != null) {
			empPrint(empByEmpno);
		} else {
			System.out.println("조건조회 실패");
		}
		
		
		System.out.println("데이터 삽입");
		EmpDto insert = EmpDto
						.builder()
						.empno(9999)
						.ename("김상훈")
						.job("백수")
						.mgr(1111)
						.hiredate(Date.valueOf("1996-04-29"))
						.sal(230)
						.comm(15)
						.deptno(10)
						.build();
		
		int insertRow = empDao.insert(insert);
		if (insertRow > 0) {
			System.out.println("INSERT ROW: " + insertRow);
		} else {
			System.out.println("INSERT FAIL");
		}
		
		
		System.out.println("데이터 수정");
		EmpDto update = EmpDto
						.builder()
						.ename("김훈상")
						.job("무직")
						.sal(250)
						.hiredate(Date.valueOf("1990-04-29"))
						.empno(9999)
						.build();

		int updateRow = empDao.update(update);
		if (updateRow > 0) {
			System.out.println("UPDATE ROW: " + updateRow);
		} else {
			System.out.println("UPDATE FAIL");
		}
		
		
		
		System.out.println("데이터 삭제");
		int deleteRow = empDao.delete(9999);
		if (deleteRow > 0) {
			System.out.println("UPDATE ROW: " + deleteRow);
		} else {
			System.out.println("UPDATE FAIL");
		}
		
		
		System.out.println("like 조회");
		List<EmpDto> empByEname = empDao.findEmpByEname("훈");
		if (empByEname != null) {
			empPrint(empByEname);
		} else {
			System.out.println("like 조회 실패");
		}
		
	}

	static void empPrint(EmpDto empDto) {
		System.out.println(empDto.toString());
	}
	
	static void empPrint(List<EmpDto> list) {
		for (EmpDto data : list) {
			System.out.println(data.toString());
		}
	}
}