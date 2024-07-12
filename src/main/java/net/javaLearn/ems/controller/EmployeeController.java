package net.javaLearn.ems.controller;

import lombok.AllArgsConstructor;
import net.javaLearn.ems.dto.EmployeeDto;
import net.javaLearn.ems.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<EmployeeDto>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long employeeId){
        EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(employeeDto);
    }
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployee(){

        List<EmployeeDto> employeeDto = employeeService.getAllEmployee();
        return ResponseEntity.ok(employeeDto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeData){
        EmployeeDto updatedEmployee = employeeService.updateEmployee(id, employeeData);
        return new ResponseEntity<EmployeeDto>(updatedEmployee, HttpStatus.OK);
    }
    @DeleteMapping("/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("employeeId") Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee deleted successfully!");
    }
}
