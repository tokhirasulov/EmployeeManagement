package net.javaLearn.ems.mapper;

import net.javaLearn.ems.dto.EmployeeDto;
import net.javaLearn.ems.entity.Employee;

public class EmployeeMapper {
    public static EmployeeDto mapToEmployeeDto(Employee employee){
        return new EmployeeDto(
                employee.getId(),
                employee.getFirstname(),
                employee.getLastname(),
                employee.getEmail()
        );
    }
    public static Employee mapToEmployee(EmployeeDto employeeDto){
        return new Employee(
                employeeDto.getId(),
                employeeDto.getEmail(),
                employeeDto.getFirstName(),
                employeeDto.getLastName()
        );
    }
}
