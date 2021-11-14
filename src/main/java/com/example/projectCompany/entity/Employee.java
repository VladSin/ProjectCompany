package com.example.projectCompany.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;
import java.util.Set;

import javax.persistence.*;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "^[A-Z]+[a-z]+$",
            message = "Name must be alphanumeric with no spaces and first capital")
    @NotNull(message = "Name must be specified")
    @Size(min = 2, message = "Name must be greater than three")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Pattern(regexp = "^[A-Z]+[a-z]+$",
            message = "Last Name must be alphanumeric with no spaces and first capital")
    @NotNull(message = "Last name must be specified")
    @Size(min = 2, message = "Last name must be greater than three")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "birthday")
    private Date birthday;

    @Pattern(regexp = "[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+",
            message = "Wrong email entered")
    @NotNull(message = "Email must be set")
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @DecimalMin(value = "0", message = "Salary must be a greater than 0")
    @Column(name = "salary")
    private double salary;

    @Column(name = "married")
    private boolean married;

    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;

    @OneToOne(mappedBy = "headId", cascade = CascadeType.ALL)
    private Department headInDepartment;

    @Override
    public String toString() {
        return String.format("firstname: [%s], lastname: [%s], email: [%s]",
                firstName, lastName, email);
    }

    public static void validate(Object object, Validator validator) {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object);

        System.out.println(object);
        System.out.println(String.format("Кол-во ошибок: %d", constraintViolations.size()));

        for (ConstraintViolation<Object> cv : constraintViolations)
            System.out.println(String.format(
                    "Внимание, ошибка! property: [%s], value: [%s], message: [%s]",
                    cv.getPropertyPath(), cv.getInvalidValue(), cv.getMessage()));
    }

//    ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
//    Validator validator = vf.getValidator();
}
