package pn.controllers;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import pn.models.dtos.EmployeeDto;
import pn.models.dtos.EmployeeWithManagerDto;
import pn.models.dtos.ManagerDto;
import pn.models.entities.Employee;
import pn.repositories.EmployeeRepository;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
public class AppController implements CommandLineRunner {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) throws Exception {
        seedEmployees();

        ModelMapper mapper = new ModelMapper();

//        Employee employee = this.employeeRepository.findById(3).orElse(null);
//        if (employee != null) {
//            EmployeeDto employeeDto = mapper.map(employee, EmployeeDto.class);
//            System.out.println(employeeDto.toString());
//        }

//        Employee manager = this.employeeRepository.findById(1).orElse(null);
//        if (manager != null) {
//            ManagerDto managerDto = mapper.map(manager, ManagerDto.class);
//            System.out.println(managerDto.toString());
//        }

        Converter<String, String> toUpperCase = ctx ->
                ctx.getSource() == null
                        ? null
                        : ctx.getSource().toUpperCase();

//        TypeMap<Employee, EmployeeWithManagerDto> typeMap = mapper
//                .createTypeMap(Employee.class, EmployeeWithManagerDto.class);
//
//        typeMap.addMappings(m -> m.using(toUpperCase).map(
//                src -> src.getManager().getLastName(),
//                EmployeeWithManagerDto::setManagerName)
//        );
//
//        this.employeeRepository
//                .findAllByIdGreaterThan(1)
//                .stream()
//                .map(typeMap::map)
//                .forEach(System.out::println);

//        PropertyMap<Employee, EmployeeWithManagerDto> propertyMap = new PropertyMap<>() {
//                @Override
//                protected void configure() {
//                    using(toUpperCase).map().setManagerName(source.getManager().getLastName());
//                }
//            };
//
//        Employee emp = this.employeeRepository.findById(1).orElse(null);
//
//        EmployeeWithManagerDto employeeWithManagerDto = new EmployeeWithManagerDto();
//
//        mapper.addMappings(propertyMap).map(emp, employeeWithManagerDto);
//
//        System.out.println(employeeWithManagerDto.toString());

        mapper.addMappings(new PropertyMap<Employee, EmployeeWithManagerDto>() {
            @Override
            protected void configure() {
                using(toUpperCase).map().setManagerName(source.getManager().getLastName());
            }
        });

        this.employeeRepository
                .findAllByIdGreaterThan(0)
                .stream()
                .map(emp -> mapper.map(emp, EmployeeWithManagerDto.class))
                .forEach(System.out::println);


        System.out.println("yoyo");
    }

    private void seedEmployees() throws ParseException {
        if (this.employeeRepository.count() == 0) {
            Employee employee1 = new Employee();
            employee1.setFirstName("Pesho");
            employee1.setLastName("Peshov");
            employee1.setSalary(new BigDecimal("50"));
            employee1.setBirthDay(new SimpleDateFormat("dd/MM/yyyy").parse("17/02/1988"));
            employee1.setAddress("blablabla 147");
            employee1.setOnHoliday(false);

            Employee employee2 = new Employee();
            employee2.setFirstName("Gosho");
            employee2.setLastName("Goshov");
            employee2.setSalary(new BigDecimal("50"));
            employee2.setBirthDay(new SimpleDateFormat("dd/MM/yyyy").parse("17/02/1988"));
            employee2.setAddress("blablabla 148");
            employee2.setOnHoliday(false);

            Employee employee3 = new Employee();
            employee3.setFirstName("Simo");
            employee3.setLastName("Simov");
            employee3.setSalary(new BigDecimal("50"));
            employee3.setBirthDay(new SimpleDateFormat("dd/MM/yyyy").parse("17/02/1988"));
            employee3.setAddress("blablabla 146");
            employee3.setOnHoliday(false);

            Employee manager = new Employee();
            manager.setFirstName("Goshko");
            manager.setLastName("Geshev");
            manager.setSalary(BigDecimal.valueOf(4000));
            manager.setBirthDay(new SimpleDateFormat("dd/MM/yyyy").parse("17/02/1988"));
            manager.setOnHoliday(false);

            manager.getEmployees().add(employee1);
            manager.getEmployees().add(employee2);
            manager.getEmployees().add(employee3);

            employee1.setManager(manager);
            employee2.setManager(manager);
            employee3.setManager(manager);

            this.employeeRepository.save(manager);
        }
    }
}
