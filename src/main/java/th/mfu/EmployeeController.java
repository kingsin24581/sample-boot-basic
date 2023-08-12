package th.mfu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    // // Create hashmap for employee
    // private HashMap<Long, Employees> employeeDB = new HashMap<Long, Employees>();

    // Select all employeex
    @GetMapping("/employees")
    public Collection<Employees> getAllemployees() {
        return employeeRepository.findByOrderByFirstNameAsc();
    }

    // Select employee By ID
    @GetMapping("/employees/{id}")
    public ResponseEntity getAllemployeeById(@PathVariable long id) {
        Optional<Employees> optEmployee = employeeRepository.findById(id);
        
        // check if id exists in db
        if (!optEmployee.isPresent()) {
            // return error message 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee Not Found");
        
        }
        Employees emp = optEmployee.get();
        return ResponseEntity.ok(emp);
    }
    // Select employee By ID
    @GetMapping("/employees/fristName/{firstName}")
    public ResponseEntity getAllemployeeByFirstName(@PathVariable String firstName) {
        List<Employees> fnameEmp = employeeRepository.findByFirstName(firstName);
        
        // check if FirstName have to in list
        if (fnameEmp.isEmpty()) {
            // return error message 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee Not Found");
        
        }
        
        return ResponseEntity.ok(fnameEmp);
    }

    @GetMapping("/employees/fristName/search/{firstName}")
    public ResponseEntity getAllfindByFirstNameStartingWith(@PathVariable String SfirstName) {
        List<Employees> SfnameEmp = employeeRepository.findByFirstNameStartingWith(SfirstName);
        
        // check if FirstName have to in list
        if (SfnameEmp.isEmpty()) {
            // return error message 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee Not Found");
        
        }
        
        return ResponseEntity.ok(SfnameEmp);
    }

    // Create new employee
    @PostMapping("/employees")
    public ResponseEntity<String> createEmployee(@RequestBody Employees employee) {
              
        // add employee to hashmap
        employeeRepository.save(employee);

        // return created success message
        return ResponseEntity.ok("Employee created");
    }

    // update employee
    @PutMapping("/employees")
    public ResponseEntity<String> updateEmployee( @RequestBody Employees employee) {
        
        //Can't find id to Update
        if(!employeeRepository.existsById(employee.getId())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Employee id already exists");
        }

        // update employee
        employeeRepository.save(employee);

        // return success message
        return ResponseEntity.ok("Employee updated");
    }

    // update employee
    @PatchMapping("/employees/{id}")
    public ResponseEntity<String> updateEmployee( @PathVariable Long id,@RequestBody EmployeeDTO empDto) {
        
        Optional<Employees> optEmployee = employeeRepository.findById(id);
        //Can't find id to Update
        if(!optEmployee.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
        }

        // update employee
        Employees emp = optEmployee.get();

        employeeMapper.updateEmployeeFromDto(empDto, emp);

        employeeRepository.save(emp);

        // return success message
        return ResponseEntity.ok("Employee updated");
    }

    // Delete employee
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable long id) {
        
        //Can't find id to delete
        if(!employeeRepository.existsById(id)){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Employee id Deleted");
        }

        // delete employee
        employeeRepository.deleteById(id);

        // return success message
        return ResponseEntity.ok("Employee had Deleted");
    }

    
    // //update employee with some fields using patch
    // @PatchMapping("/employees/{id}")
    // public ResponseEntity<String> patchEmployee(@RequestBody HashMap<String, Object> fieldstoupdate){
    //      //check if id not exists
    //      if(!employeeRepository.containsKey(id)){
    //         //return error message
    //         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
    //     }

    //     //get employee from db
    //     Employees emp = employeeDB.get(id);
    //     //loop throught fields to update
    //     fieldstoupdate.forEach((key,value) -> {
    //         //check if field is firstname
    //         if(key.equals("firstName")){
    //             //update firstname
    //             emp.setFirstName((String)value);
    //         }
    //         //check if field is lastname
    //         if(key.equals("lastName")){
    //             //update lastname
    //             emp.setLastName((String)value);
    //         }
    //         //check if field is salary
    //         if(key.equals("birtDate")){
    //             //update salary
    //             SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    //             try{
    //                 emp.setBirtDate(formatter.parse((String)value));
    //             }catch(ParseException e){
    //                 e.printStackTrace();
    //             }
    //         }

    //         //check if field is salary
    //         if(key.equals("salary")){
    //             //update salary
    //             emp.setSalary(Long.valueOf(""+value));
    //         }

    //     });

    //     //update employee
    //     employeeDB.put(id, emp);


    //     //return success message
    //     return ResponseEntity.ok("Employee updated");
    // }

    
    
}
