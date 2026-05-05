package Employee.Employee;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	@Query(" select DISTINCT e from Employee e join e.addresses a")
	public List<Employee> getAllEmployees();

}
