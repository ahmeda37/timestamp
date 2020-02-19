package com.ahmed.main;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Scanner;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.ahmed.configuration.AppConfig;
import com.ahmed.model.Employee;
import com.ahmed.model.Payment;
import com.ahmed.model.PunchInOut;
import com.ahmed.model.Report;
import com.ahmed.model.Process;
import com.ahmed.service.EmployeeService;
import com.ahmed.service.PunchService;

public class App {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		EmployeeService empService = (EmployeeService) context.getBean("employeeService");
		PunchService punchService = (PunchService) context.getBean("punchService");
		System.out.println("Welcome to Timesheet\n\n\n");
		Scanner sc = new Scanner(System.in);
		char page = 'h';
		do {
			if (page == 'h' || page == 'H') {
				System.out.println(home());
				page = sc.next().charAt(0);
			} else if (page == 'a' || page == 'A') {
				page = addEmployee(sc, empService);
			} else if (page == 'b' || page == 'B') {
				page = punch(sc, empService, punchService);
			} else if (page == 'c' || page == 'C') {
				page = getProcess(true, sc, empService, punchService);
			} else if (page == 'd' || page == 'D') {
				page = updateEmployee(sc, empService);
			} else if (page == 'e' || page == 'E') {
				page = deleteEmployee(sc, empService);
			} else if (page == 'f' || page == 'F') {
				page = getProcess(false, sc, empService, punchService);
			} else if (page == 'g' || page == 'G') {
				System.out.println("\n\nThank You for Using Timesheet");
				break;
			}
		} while (page != 'g' || page != 'G');
		sc.close();
		context.close();
	}

	public static String home() {
		String result = "\n\n\n";
		result += "A) Add New Employee\n";
		result += "B) Punch In/Out\n";
		result += "C) Report\n";
		result += "D) Update Employee Details\n";
		result += "E) Delete Employee Details\n";
		result += "F) Calculate Amount For Employees\n";
		result += "G) Exit\n";
		return result;

	}

	public static char addEmployee(Scanner sc, EmployeeService emps) throws ParseException {
		char addAnother;
		do {
			Employee e = new Employee();
			String input;
			sc.nextLine();
			System.out.println("Please enter New Employee Information.");

			input = getInput("First Name (Required): ", sc);
			if (input.equalsIgnoreCase("h")) {
				return 'h';
			} else {
				e.setFname(input);
			}

			input = getInput("Last Name (Required): ", sc);
			if (input.equalsIgnoreCase("h")) {
				return 'h';
			} else {
				e.setLname(input);
			}

			input = getInput("Phone (Required): ", sc);
			if (input.equalsIgnoreCase("h")) {
				return 'h';
			} else {
				e.setPhone(input);
			}

			System.out.println("Hired Date (Leave Blank FOR Today || MM/DD/YYYY): ");
			input = sc.nextLine();
			if (input.isEmpty()) {
				LocalDate d = new LocalDate();
				e.setHiredDate(new Date(d.toDate().getTime()));
			} else {
				LocalDate d = new LocalDate(DateTimeFormat.forPattern("MM/dd/YYYY").parseDateTime(input));
				e.setHiredDate(new Date(d.toDate().getTime()));
			}

			input = getInput("Rate (Required 00.00): ", sc);
			try {
				e.setRate(Double.parseDouble(input));
			} catch (NumberFormatException err) {
				System.out.println("Could not parse: " + input
						+ " into a string... Employee will still be save, please use option d in the main menu to update the rate!");
			}
			emps.addEmployee(e);
			System.out.println("New Employee Details: \n" + e);
			System.out.print("Would you like to add another employee? (Y/N): ");
			addAnother = sc.next().charAt(0);
		} while (addAnother == 'y' || addAnother == 'Y');
		return 'h';
	}

	public static char updateEmployee(Scanner sc, EmployeeService emps) throws ParseException {
		String input;
		Employee e = findEmp(sc, emps);
		if (e == null) {
			return 'h';
		}
		sc.nextLine();
		System.out.println(e);

		System.out.println("First Name (Leave Blank to Keep Old Value): ");
		input = sc.nextLine();
		if (!input.isEmpty()) {
			e.setFname(input);
		}

		System.out.println("Last Name (Leave Blank to Keep Old Value): ");
		input = sc.nextLine();
		if (!input.isEmpty()) {
			e.setLname(input);
		}

		System.out.println("Phone (Leave Blank to Keep Old Value): ");
		input = sc.nextLine();
		if (!input.isEmpty()) {
			e.setPhone(input);
		}

		System.out.println("Hired Date (Leave Blank to Keep Old Value): ");
		input = sc.nextLine();
		if (!input.isEmpty()) {
			LocalDate d = new LocalDate(DateTimeFormat.forPattern("MM/dd/YYYY").parseDateTime(input));
			e.setHiredDate(new Date(d.toDate().getTime()));
		}
		System.out.println("Hourly Rate (Leave Blank to Keep Old Value): ");
		input = sc.nextLine();
		if (!input.isEmpty()) {
			e.setRate(Double.parseDouble(input));
		}
		emps.updateEmployee(e);
		System.out.println(e);
		System.out.println("Press Any Key To Continue");
		sc.nextLine();
		return 'h';

	}

	public static char deleteEmployee(Scanner sc, EmployeeService emps) {
		Employee e = findEmp(sc, emps);
		if (e == null) {
			return 'h';
		}
		sc.nextLine();
		System.out.println(e);
		System.out.println("Are you sure you want to delete this employee (Forever!): (Y/N)");
		char input = sc.next().charAt(0);
		if (input == 'Y' || input == 'y') {
			emps.removeEmployee(e.getEid());
			System.out.println("Employee Has Been Deleted.");
		} else {
			System.out.println("Employee Was Not Deleted!");
		}
		return 'h';
	}

	public static char punch(Scanner sc, EmployeeService emps, PunchService puns) {
		String input;
		String type;
		PunchInOut p;

		// find employee
		Employee e = findEmp(sc, emps);
		if (e == null) {
			return 'h';
		}
		sc.nextLine();

		// Get Input
		type = getInput("Enter 'I' for Punch In and 'O' for Punch Out.", sc);
		if (type.equalsIgnoreCase("o") || type.equalsIgnoreCase("i")) {

			// Get Date and punch
			input = getInput("Enter Date (MM/DD/YYYY)", sc);
			if (input.length() == 10) {
				LocalDate d = new LocalDate(DateTimeFormat.forPattern("MM/dd/YYYY").parseDateTime(input));
				Date newDate = new Date(d.toDate().getTime());
				p = puns.getPuncyByIdDate(e, newDate);

				if (type.equalsIgnoreCase("i")) {
					if (p == null) {
						addPunchIn(input, e, newDate, d.getDayOfWeek(), puns, sc);
					} else {
						System.out.println("Employee has already punch in for: " + newDate);
					}
				} else if (type.equalsIgnoreCase("o")) {
					if (p == null) {
						System.out.println("Employee has not punched in for: " + newDate);
						System.out.println("Error: Employee is not punched in, please punch in");
						p = addPunchIn(input, e, newDate, d.getDayOfWeek(), puns, sc);
						if (p != null) {
							addPunchOut(p, puns, sc);
						}

					} else {
						addPunchOut(p, puns, sc);
					}
				}
			}
		}
		System.out.println("Press Any Key to Continue!");
		sc.nextLine();
		return 'h';
	}

	public static boolean addPunchOut(PunchInOut p, PunchService puns, Scanner sc) {
		String input = getInput("Enter Punch Out Time (HH:mm): ", sc);
		if (input.equalsIgnoreCase("h")) {
			return false;
		}
		DateTime i = DateTime.parse(p.getpDate() + " " + input, DateTimeFormat.forPattern("YYYY-MM-dd HH:mm"));
		Timestamp ts = new Timestamp(i.getMillis());
		Long timeDiff = ts.getTime() - p.getpIn().getTime();
		if (timeDiff >= 0) {
			p.setpOut(ts);

			p.setHoursWorked(timeDiff / (3.6 * 1000000.0));
			puns.updatePunch(p);
			System.out.println(p);
			return true;
		} else {
			System.out.println("Error: Punch Out must Come After Punch In for Same Date...");
			return false;
		}
	}

	public static PunchInOut addPunchIn(String old, Employee e, Date newDate, int dayOfWeek, PunchService puns,
			Scanner sc) {
		String input = getInput("Enter Punch In Time (HH:mm)", sc);
		if (input.equalsIgnoreCase("h")) {
			return null;
		}
		PunchInOut p = new PunchInOut();
		p.setEmployee(e);
		p.setpDate(newDate);
		p.setDayOfWeek(dayOfWeek);
		DateTime i = DateTime.parse(old + " " + input, DateTimeFormat.forPattern("MM/dd/YYYY HH:mm"));
		p.setpIn(new Timestamp(i.getMillis()));
		puns.addPunch(p);
		System.out.println(p);
		return p;
	}

	public static char getProcess(boolean report, Scanner sc, EmployeeService emps, PunchService puns) {
		Process p;
		if (report) {
			System.out.println("Time Clock Reporting");
			p = new Report();
		} else {
			System.out.println("Time Clock Payments");
			p = new Payment();
		}
		sc.nextLine();
		String input = getInput("Enter 'A' for All Employees or 'I' for Individual Employee", sc);
		if (input.equalsIgnoreCase("i")) {
			try {
				Employee e = findEmp(sc, emps);
				if (e == null) {
					return 'h';
				}
				p.setE(e);
				p.setPuns(puns);
				System.out.println(p.getEmployee());
			} catch (NumberFormatException e) {
				System.out.println("Error: Could not parse id...");
			}

		} else if (input.equalsIgnoreCase("a")) {
			p.setPuns(puns);
			p.setEmps(emps);
			System.out.println(p.getEmployees());
		}
		System.out.println("Press Any Key To Continue");
		sc.nextLine();
		return 'h';
	}

	public static String getInput(String st, Scanner sc) {
		int err = 0;
		String input;
		System.out.print(st);
		input = sc.nextLine();
		while (input.isEmpty()) {
			System.out.println("Error: Field Cannot Be Blank.");
			System.out.print(st);
			input = sc.nextLine();
			err += 1;
			if (err > 1) {
				System.out.println("Error: Too Many Wrong Inputs, Going back Home.");
				return "h";
			}
		}
		return input;
	}

	public static Employee findEmp(Scanner sc, EmployeeService emps) {
		int eid;
		int err = 0;
		Employee e;
		System.out.print("Enter Employee ID: ");
		eid = sc.nextInt();
		e = emps.getEmployeeById(eid);
		while (e == null) {
			System.out.println("Error: Could not find employee with ID: " + eid + " , Please try Again.");
			System.out.print("Enter Employee ID: ");
			eid = sc.nextInt();
			e = emps.getEmployeeById(eid);
			err++;
			if (err > 1) {
				System.out.println("Error: Too many attemps, returning to homepage.");
				return null;
			}
		}
		return e;
	}

}
