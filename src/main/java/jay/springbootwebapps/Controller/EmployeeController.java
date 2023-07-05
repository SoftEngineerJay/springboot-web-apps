package jay.springbootwebapps.Controller;


import jay.springbootwebapps.Exception.RecordNotFoundException;
import jay.springbootwebapps.Models.Employee;
import jay.springbootwebapps.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class EmployeeController {

    /**
     * AUTOWIRED
     **/
    @Autowired
    EmployeeService employeeService;


    /**
     * LIST DATA
     **/
    @RequestMapping
    public String getAllEmployees(Model model) {
        List<Employee> list = employeeService.getAllEmployees();
        model.addAttribute("employees", list);
        return "views/list-employees";
    }

    /**
     * UPDATE
     **/
    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String editEmployeeById(Model model, @PathVariable("id") Optional<Long> id)
            throws RecordNotFoundException {
        if (id.isPresent()) {
            Employee employee = employeeService.getEmployeeByID(id.get());
            model.addAttribute("employee", employee);
        } else {
            model.addAttribute("employee", new Employee());
        }
        return "views/add-edit-employee";
    }

    /**
     * DELETE
     **/
    @RequestMapping(path = "/delete/{id}")
    public String deleteEmployeeById(Model model, @PathVariable("id") Long id)
            throws RecordNotFoundException {
        employeeService.deleteEmployeeById(id);
        return "redirect:/";
    }

    /**
     * CREATE
     **/
    @RequestMapping(path = "/createEmployee", method = RequestMethod.POST)
    public String createOrUpdateEmployee(Employee employee) {
        employeeService.createOrUpdateEmployee(employee);
        return "redirect:/";
    }
}
