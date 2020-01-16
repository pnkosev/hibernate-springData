import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.lang.annotation.Native;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Engine implements Runnable {
    private final EntityManager entityManager;

    public Engine(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void run() {
        this.getMaxiSalariesPerDepartment();
    }

    /**
     * 2. Remove Objects
     */
    private void removeObjects() {
        this.entityManager.getTransaction().begin();

        List<Town> towns = this.entityManager.createQuery("FROM Town", Town.class).getResultList();

        // Detach towns with length > 5
        for (Town town : towns) {
            if (town.getName().length() > 5) {
                this.entityManager.remove(town);
            }
        }

        // Transform town names
        for (Town town : towns) {
            town.setName(town.getName().toLowerCase());
        }

        // Persist towns (attach)
        for (Town town : towns) {
            this.entityManager.persist(town);
        }

        //Print towns
        for (Town town : towns) {
            System.out.println(town.getId() + " " + town.getName());
        }
    }

    /**
     * 3. ContainsEmployee
     */
    private void containsEmployee() {
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        this.entityManager.getTransaction().begin();

        try {
//            Hibernate query
            Employee employee = this.entityManager
                    .createQuery("FROM Employee WHERE concat(firstName, ' ', lastName) = :name", Employee.class)
                    .setParameter("name", name)
                    .getSingleResult();

//            Native query
            Employee employee1 = (Employee) this.entityManager
                    .createNativeQuery("SELECT * FROM employees WHERE concat(first_name, ' ', last_name) = '" + name + "';", Employee.class)
                    .getSingleResult();
            System.out.println("Yes");
        } catch (NoResultException e) {
            System.out.println("No");
        }

        this.entityManager.getTransaction().commit();
    }

    /**
     * 4. Employees with salary over 50 000
     */
    private void getEmployeesWithSalaryOver50k() {
        this.entityManager.createQuery("select e from Employee AS e where e.salary > 50000", Employee.class)
                .getResultList()
                .forEach(e -> System.out.println(e.getFirstName()));
    }

    /**
     * 5. Employees from Department
     */
    private void getAllEmployeesFromDepartment(String departmentName) {
        this.entityManager
                .createQuery("select e from Employee as e where e.department.name = '" + departmentName + "' order by e.salary, e.id", Employee.class)
                .getResultList()
                .forEach(e -> System.out.println(String.format("%s %s from %s - %.2f",
                        e.getFirstName(),
                        e.getLastName(),
                        e.getDepartment().getName(),
                        e.getSalary())));
    }

    /**
     * 6. Adding a New Address and Updating Employee
     */
    private void addNewAddressAndUpdateEmployee(String newAddress) {
        Scanner scanner = new Scanner(System.in);
        String employeeLastName = scanner.nextLine();

        try {
            this.entityManager.getTransaction().begin();

            Town sofia = this.entityManager
                    .createQuery("select t from Town as t where t.name = 'Sofia'", Town.class)
                    .getSingleResult();


            List<Address> addresses = this.entityManager
                    .createQuery("FROM Address WHERE text = '" + newAddress + "'", Address.class)
                    .getResultList();

            Address address;
            if (addresses.isEmpty()) {
                address = new Address();
                address.setText(newAddress);
                address.setTown(sofia);
                addresses.add(address);
                this.entityManager.persist(address);
            } else {
                address = addresses.get(0);
            }

            Employee employee = this.entityManager
                    .createQuery("select e from Employee as e where e.lastName = :lastName", Employee.class)
                    .setParameter("lastName", employeeLastName)
                    .getSingleResult();

            employee.setAddress(address);

            this.entityManager.getTransaction().commit();

            System.out.println(String.format("%s %s changed address to %s, %s%n",
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getAddress().getText(),
                    employee.getAddress().getTown().getName()));

        } catch (NoResultException e) {
            System.out.println();
        }
    }

    /**
     * 7. Addresses with Employee Count
     */
    private void addressWithEmployeeCount() {
        this.entityManager.getTransaction().begin();

        this.entityManager
                .createQuery("from Address order by employees.size desc, town.id asc", Address.class)
                .setMaxResults(10)
                .getResultList()
                .forEach(v -> System.out.println(String.format("%s, %s - %d employees",
                        v.getText(),
                        v.getTown().getName(),
                        v.getEmployees().size())));

        this.entityManager.getTransaction().commit();
    }

    /**
     * 8. Get Employee with Project
     */
    public void getEmployeeWithProject() {
        Scanner scanner = new Scanner(System.in);
        int id = Integer.parseInt(scanner.nextLine());

        this.entityManager.getTransaction().begin();

        Employee employee = this.entityManager
                .createQuery("from Employee where id = :id", Employee.class)
                .setParameter("id", id)
                .getSingleResult();

        System.out.printf("%s %s - %s",
                employee.getFirstName(),
                employee.getLastName(),
                employee.getJobTitle()).println();

        employee.getProjects().stream()
                .sorted(Comparator.comparing(Project::getName))
                .forEach(p -> {
                    System.out.printf("\t%s\n", p.getName());
                });

        this.entityManager.getTransaction().commit();
    }

    /**
     * 9. Find Latest 10 Projects
     */
    private void getTenLatestProjects() {
        this.entityManager.getTransaction().begin();

        this.entityManager
                .createQuery("from Project order by name asc", Project.class)
                .getResultList()
                .forEach(v -> {
                    System.out.printf("Project name: %s\n" +
                                    "Project Description: %s\n" +
                                    "Project Start Date: %s\n" +
                                    "Project End Date: %s",
                            v.getName(),
                            v.getDescription(),
                            v.getStartDate(),
                            v.getEndDate()).println();
                });

        this.entityManager.getTransaction().commit();
    }

    /**
     * 10. Increase Salaries
     */
    private void increaseSalaries() {
        this.entityManager.getTransaction().begin();

        this.entityManager
                .createQuery("from Employee where department.name in ('Engineering', 'Tool Design', 'Marketing', 'Information Services')", Employee.class)
                .getResultList()
                .forEach(e -> {
                    e.setSalary(e.getSalary().multiply(BigDecimal.valueOf(1.12)));

                    System.out.printf("%s %s (%.2f)\n",
                            e.getFirstName(),
                            e.getLastName(),
                            e.getSalary());
                });

        this.entityManager.getTransaction().commit();
    }

    /**
     * 11. Remove Towns
     */
    private void removeTowns() {
        Scanner scanner = new Scanner(System.in);
        String townName = scanner.nextLine();

        try {
            this.entityManager.getTransaction().begin();

            Town town = this.entityManager
                    .createQuery("from Town where name = :name", Town.class)
                    .setParameter("name", townName)
                    .getSingleResult();

            List<Address> addresses = this.entityManager
                    .createQuery("from Address where town.id = :townId", Address.class)
                    .setParameter("townId", town.getId())
                    .getResultList();

            System.out.println(String.format("%d address%s in %s deleted",
                    addresses.size(),
                    addresses.size() != 1 ? "es" : "",
                    town.getName()));

            addresses.forEach(a -> {
                a.getEmployees().forEach(e -> {
                    e.setAddress(null);
                });
            });

            this.entityManager.remove(town);

        } catch (NoResultException | IllegalArgumentException ex) {
            System.out.println("You have entered invalid town!");
            this.entityManager.getTransaction().rollback();
        }

        this.entityManager.getTransaction().commit();
    }

    /**
     * 12. Find Employees by First Name
     */
    private void getEmployeesByFirstName() {
        Scanner scanner = new Scanner(System.in);
        String pattern = scanner.nextLine().toLowerCase() + "%";

        this.entityManager.getTransaction().begin();

        this.entityManager
                .createQuery("from Employee where firstName like :pattern", Employee.class)
                .setParameter("pattern", pattern)
                .getResultList()
                .forEach(e -> {
                    System.out.println(String.format("%s %s - %s - ($%.2f)",
                            e.getFirstName(),
                            e.getLastName(),
                            e.getJobTitle(),
                            e.getSalary()));
                });

        this.entityManager.getTransaction().commit();
    }

    /**
     * 13. Employees Maximum Salaries
     */
    private void getMaxiSalariesPerDepartment() {
        this.entityManager.getTransaction().begin();

        this.entityManager
                .createQuery("from Employee where salary not between 30000 and 70000 group by department order by salary", Employee.class)
                .getResultList()
                .forEach(e -> System.out.println(String.format("%s - %.2f",
                        e.getDepartment().getName(),
                        e.getSalary())));

        this.entityManager.getTransaction().commit();
    }
}
