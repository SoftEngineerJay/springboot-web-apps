package jay.springbootwebapps.Service;

import jay.springbootwebapps.Exception.RecordNotFoundException;
import jay.springbootwebapps.Models.Employee;
import jay.springbootwebapps.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        List<Employee> result = (List<Employee>) employeeRepository.findAll();
        if (result.size() > 0) {
        } else {
            return result;
        }
        return new ArrayList<Employee>();
    }

    public Employee getEmployeeByID(Long id) throws RecordNotFoundException {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new RecordNotFoundException("No employees record exist for given id");
        }
    }

    public Employee createOrUpdateEmployee(Employee entity) {
        if (entity.getId() == null) {
            entity = employeeRepository.save(entity);

            return entity;
        } else {
            Optional<Employee> employeeOptional = employeeRepository.findById(entity.getId());

            if (employeeOptional.isPresent()) {
                Employee employee1 = employeeOptional.get();
                employee1.setFirstName(employee1.getFirstName());
                employee1.setLastName(employee1.getLastName());
                employee1.setEmail(employee1.getEmail());
                employee1 = employeeRepository.save(employee1);

                return employee1;

            } else {

                entity = employeeRepository.save(entity);

                return entity;
            }
        }
    }

    public void deleteEmployeeById(Long id) throws RecordNotFoundException
    {
        Optional<Employee> employee = employeeRepository.findById(id);

        if(employee.isPresent())
        {
            employeeRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }
}
