package Employee.Employee;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	public EmployeeRepository empRepo;

	@Autowired
	public AddressRepository addRepo;

	@Override
	public List<EmployeeDTO> getAllEmployees() {

		List<Employee> elist = empRepo.getAllEmployees();
		System.out.println("Total list of all employees :" + elist.size());

		List<EmployeeDTO> elistDTO = new ArrayList<EmployeeDTO>();
		for (Employee e : elist) {
			EmployeeDTO empDTO = new EmployeeDTO();
			empDTO.setCompany(e.getCompany());
			empDTO.setDesignation(e.getDesignation());
			empDTO.setName(e.getName());
			empDTO.setSalary(e.getSalary());
			empDTO.setId(e.getId());

			List<AddressDTO> addressList = new ArrayList<>();

			for (Address a : e.getAddresses()) {

				AddressDTO adto = new AddressDTO();
				adto.setId(a.getId());
				adto.setCity(a.getCity());
				adto.setSeason(a.getSeason());
				adto.setEmpId(e.getId()); 

				addressList.add(adto);
			}

			empDTO.setAddresses(addressList);
			elistDTO.add(empDTO);
		}

		return elistDTO;
	}

	@Override
	public void createEmployee(EmployeeDTO emDTO) {
		System.out.println("Creating employee");

		Employee emp = new Employee();
		emp.setCompany(emDTO.getCompany());
		emp.setDesignation(emDTO.getDesignation());
		emp.setName(emDTO.getName());
		emp.setSalary(emDTO.getSalary());
		// emp.setId(emDTO.getId());

		List<Address> al = new ArrayList<Address>();
		for (AddressDTO adto : emDTO.getAddresses()) {
			Address add = new Address();
			add.setCity(adto.getCity());
			// add.setId(adto.getId());
			add.setSeason(adto.getSeason());
			add.setEmployee(emp);
			al.add(add);
		}

		emp.setAddresses(al);
		emp = empRepo.save(emp);
	}

}
