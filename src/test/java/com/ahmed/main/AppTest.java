package com.ahmed.main;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.text.ParseException;
import java.util.Scanner;

import com.ahmed.main.service.EmployeeService;
import com.ahmed.main.service.PunchService;
import com.ahmed.main.service.RunApp;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {

	@Autowired
	EmployeeService emps;

	@Autowired
	PunchService puns;

	@Autowired
	RunApp run;

//	@Test
//	public void contextLoads() {
//	}
//
//	@Test
//	public void getEmployees() {
//		System.out.println(emps.getEmployeeById(4).getFname());
//	}
//
//	@Test
//	public void getPunch() {
//		System.out.println(puns.getPunchById(2));
//	}
//
//	@Test
//	public void getPunchByDate() {
//		Date d = new Date(Calendar.getInstance().getTimeInMillis());
//
//		System.out.println(d);
//		System.out.println(puns.getPuncyByIdDate(emps.getEmployeeById(5), d));
//	}
//
//	@Test
//	public void getPunchesForEmployee() {
//		Date d = new Date(Calendar.getInstance().getTimeInMillis());
//		Date d2 = new Date(Calendar.getInstance().getTimeInMillis() - 1209600000);
//
//		List<PunchInOut> p1 = puns.punchesForEmployee(emps.getEmployeeById(5), d2, d);
//
//		for (PunchInOut p : p1) {
//			System.out.println(p);
//		}
//	}

	@Test
	public void runApp() {
		System.out.println("Welcome to Timesheet\n\n\n");
		Scanner sc = new Scanner(System.in);
		char page = 'h';
		do {
			if (page == 'h' || page == 'H') {
				System.out.println(run.home());
				page = sc.next().charAt(0);
			} else if (page == 'a' || page == 'A') {
				try {
					page = run.addEmployee(sc, emps);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (page == 'b' || page == 'B') {
				page = run.punch(sc, puns, emps);
			} else if (page == 'c' || page == 'C') {
				page = run.getProcess(true, sc, puns, emps);
			} else if (page == 'd' || page == 'D') {
				try {
					page = run.updateEmployee(sc, emps);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (page == 'e' || page == 'E') {
				page = run.deleteEmployee(sc, emps);
			} else if (page == 'f' || page == 'F') {
				page = run.getProcess(false, sc, puns, emps);
			} else if (page == 'g' || page == 'G') {
				System.out.println("\n\nThank You for Using Timesheet");
				break;
			}
		} while (page != 'g' || page != 'G');
		sc.close();
	}
}
