package com.example.projectCompany.repository;

import com.example.projectCompany.entity.Company;
import com.example.projectCompany.entity.Department;
import com.example.projectCompany.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Department findByName(String name);

    List<Department> findAllByLocation(String location);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update Department set name = :name where id = :id")
    void updateName(@Param("id") Long id, @Param("name") String name);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update Department set website = :website where id = :id")
    void updateWebsite(@Param("id") Long id, @Param("website") String website);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update Department set location = :location where id = :id")
    void updateLocation(@Param("id") Long id, @Param("location") String location);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update Department set company = :company where id = :id")
    void updateCompany(@Param("id") Long id, @Param("company") Company company);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update Department set name = :name, website = :website, location = :location, " +
            "company = :company, employees =:employees where id = :id")
    void updateDepartmentData(@Param("id") Long id,
                            @Param("name") String name,
                            @Param("website") String website,
                            @Param("location") String location,
                            @Param("budget") Company company,
                            @Param("department") List<Employee> employees);
}
