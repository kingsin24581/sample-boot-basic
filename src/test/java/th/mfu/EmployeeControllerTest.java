package th.mfu;

import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class EmployeeControllerTest {
    
    @Autowired
    EmployeeController controller;

    //test query All
    @Test
    public void testGetAllEmployees() {
        // Act
        Collection<Employees> response = controller.getAllemployees();
        // Assert
        assertEquals(10, response.size());
    }
    
    //test Create 
    @Test
    public void testCreate() {
        Employees newemp = new Employees();
        newemp.setFirstname("David");
        newemp.setLastName("Miller");
        newemp.setBirtDate(new Date());
        newemp.setSalary(100000);
        // Act
        ResponseEntity response =  controller.createEmployee(newemp);
        // Assert check if response is ok
        assertEquals(200, response.getStatusCodeValue());

        ResponseEntity response2 = controller.getAllemployeeByFirstName("David");
        //check if response2 returned with proper status code
        assertEquals(200, response2.getStatusCodeValue());
        
    }
    
    //test Update
    @Test
    public void testUpdate(){
        // get employee with id 91
        ResponseEntity response = controller.getAllemployeeById(91);

        Employees emp = (Employees)response.getBody();

        //update employee
        emp.setFirstname("David");

        // Act
        ResponseEntity response2 = controller.updateEmployee(emp);

        // Assert
        assertEquals(200, response2.getStatusCodeValue());

        //check if employee is updated
        ResponseEntity response3 = controller.getAllemployeeById(91);
        //check if employee firstname is David
        assertEquals("David", ((Employees)response3.getBody()).getFirstName());

    }

    //test Delete
    @Test
    public void testDelete() {

        // Act
        ResponseEntity response = controller.deleteEmployee(90);
        // Assert
        assertEquals(200, response.getStatusCodeValue());

        //try to check if employee is deleted
        ResponseEntity response2 = controller.getAllemployeeById(90);
        //check if response2 returned with proper status code
        assertEquals(404, response2.getStatusCodeValue());
    }

}
