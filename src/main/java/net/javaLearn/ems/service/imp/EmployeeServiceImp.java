package net.javaLearn.ems.service.imp;

import lombok.AllArgsConstructor;
import net.javaLearn.ems.dto.EmployeeDto;
import net.javaLearn.ems.entity.Employee;
import net.javaLearn.ems.exception.ResourceNotFound;
import net.javaLearn.ems.mapper.EmployeeMapper;
import net.javaLearn.ems.repository.EmployeeRepository;
import net.javaLearn.ems.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImp implements EmployeeService {
    private EmployeeRepository employeeRepository;
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto){
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }
    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFound("Employee is not exist"));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployee(){
        List<Employee> allEmployee = employeeRepository.findAll();
        return allEmployee.stream().map(EmployeeMapper::mapToEmployeeDto).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto employeeData){
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFound("Employee not found"));

        employee.setEmail(employeeData.getEmail());
        employee.setFirstname(employeeData.getFirstName());
        employee.setLastname(employeeData.getLastName());

        Employee updatedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long employeeId){
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFound("Employee not found"));

        employeeRepository.deleteById(employeeId);

    }
}
