package Employee.Employee;

import java.util.List;

public interface EmployeeService {

	public List<EmployeeDTO> getAllEmployees();

	public void createEmployee(EmployeeDTO emDTO);
}
