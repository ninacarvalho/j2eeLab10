package com.humber.employeemanagementsystem.controller;

import com.humber.employeemanagementsystem.model.Employee;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private List<Employee> employeeList = new ArrayList<>();

    @GetMapping
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeList);
        return "employee-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee-form";
    }

    @PostMapping("/add")
    public String addEmployee(@Valid @ModelAttribute Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            return "employee-form";
        }
        employee.setId(employeeList.size() + 1);
        employeeList.add(employee);
        return "redirect:/employees";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Employee employee = employeeList.stream()
                .filter(emp -> emp.getId() == id)
                .findFirst()
                .orElse(null);
        model.addAttribute("employee", employee);
        return "employee-form";
    }

    @PostMapping("/edit/{id}")
    public String editEmployee(@PathVariable int id, @Valid @ModelAttribute Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            return "employee-form";
        }

        Employee existingEmployee = employeeList.stream()
                .filter(emp -> emp.getId() == id)
                .findFirst()
                .orElse(null);

        if (existingEmployee != null) {
            existingEmployee.setName(employee.getName());
            existingEmployee.setEmail(employee.getEmail());
        }

        return "redirect:/employees";
    }

    @GetMapping ("/delete/{id}")
    public String deleteEmployee(@PathVariable int id) {

        employeeList.removeIf(emp -> emp.getId() == id);

        return "redirect:/employees";
    }
}
