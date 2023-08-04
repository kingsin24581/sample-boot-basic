package th.mfu;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Employees {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstName ;
    private String lastName;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birtDate;
    private long salary;


    //this a conducter can have all any pralamiter
    public Employees() {
    }

    //conducter
    public Employees(long id,String firstName, String lastName, Date birtDate,  long salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birtDate = birtDate;
        this.salary = salary;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public Date getBirtDate() {
        return birtDate;
    }
    public void setBirtDate(Date birtDate) {
        this.birtDate = birtDate;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getSalary() {
        return salary;
    }
    public void setSalary(long salary) {
        this.salary = salary;
    }
    
    
    
}
