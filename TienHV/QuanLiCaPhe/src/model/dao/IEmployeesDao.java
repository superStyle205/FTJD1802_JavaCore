package model.dao;

import java.util.List;

import model.bean.Employees;


public interface IEmployeesDao {
	
	List<Employees> getAllEmployees();
	
	boolean  deleteEmployees(Employees employees);

	boolean insertEmployees(Employees employees);

	boolean updateEmployees(Employees employees);
	
	List<Employees> getAllInfoEmployees();
}
