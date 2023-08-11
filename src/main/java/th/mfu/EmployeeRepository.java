package th.mfu;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employees, Long>{
    public List<Employees> findAll();
    //find Firstname of Employee A->Z or Z->A
    public List<Employees> findByOrderByFirstNameAsc();
    public List<Employees> findByOrderByFirstNameDesc();

    public List<Employees> findByFirstName(String firstName);
    List<Employees> findByFirstNameStartingWith(String prefix);
}
