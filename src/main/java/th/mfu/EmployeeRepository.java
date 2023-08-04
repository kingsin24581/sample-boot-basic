package th.mfu;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employees, Long>{
    public List<Employees> findAll();
    public List<Employees> findByFirstName(String firstName);
    List<Employees> findByFirstNameStartingWith(String prefix);
}
