package Employee.Employee;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeService empService;

	@GetMapping(value = "/employees")
	public List<EmployeeDTO> getEmployees(HttpServletRequest request) {

		 String header = request.getHeader("Authorization");

		    if (header == null || !header.equals("Bearer abc123")) {
		        throw new RuntimeException("Unauthorized");
		    }
		    
		System.out.println("GET method to fetch all employees");
		return empService.getAllEmployees();
	}

	@PostMapping(value = "/employee")
	public void createEmployee(@RequestBody EmployeeDTO employeeDTO) {

		System.out.println("POST method to create new employee");

		empService.createEmployee(employeeDTO);
	}

}
