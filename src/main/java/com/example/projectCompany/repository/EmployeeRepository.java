package com.example.projectCompany.repository;

import com.example.projectCompany.entity.Department;
import com.example.projectCompany.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByEmail(String email);

    Employee findByFirstNameAndLastName(String firstName, String lastName);

    List<Employee> findAllByMarried(boolean married);

    List<Employee> findAllBySalaryAfter(double salary);

    List<Employee> findAllBySalaryBefore(double salary);

    List<Employee> findAllBySalaryBetween(double minSalary, double maxSalary);

    List<Employee> findAllByDepartment(Department department);


    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update Employee set firstName = :firstName where id = :id")
    void updateFirstName(@Param("id") Long id, @Param("firstName") String firstName);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update Employee set lastName = :lastName where id = :id")
    void updateLastName(@Param("id") Long id, @Param("lastName") String lastName);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update Employee set birthday = :birthday where id = :id")
    void updateBirthday(@Param("id") Long id, @Param("birthday") String birthday);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update Employee set email = :email where id = :id")
    void updateEmail(@Param("id") Long id, @Param("email") String email);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update Employee set salary = :salary where id = :id")
    void updateSalary(@Param("id") Long id, @Param("salary") double salary);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update Employee set married = :married where id = :id")
    void updateMarried(@Param("id") Long id, @Param("married") boolean married);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update Employee set firstName = :firstName, lastName = :lastName, birthday = :birthday, email = :email, salary = :salary, " +
            "married = :married, department =:department where id = :id")
    void updateEmployeeData(@Param("id") Long id,
                            @Param("firstName") String firstName,
                            @Param("lastName") String lastName,
                            @Param("birthday") Date birthday,
                            @Param("email") String email,
                            @Param("salary") double salary,
                            @Param("married") boolean married,
                            @Param("department") Department department);
}
