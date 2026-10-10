package com.java.streamsExample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Emp {
	Integer eid;
	String fname;
	String lname;
	String job;
	Double sal;
	Integer dno;
	String dname;
	String deptLoc;
	String city;
	String state;

	public Emp() {

	}

	public Emp(Integer eid, String fname, String lname, String job, Double sal, Integer dno, String dname,
			String deptLoc, String city, String state) {
		this.eid = eid;
		this.fname = fname;
		this.lname = lname;
		this.job = job;
		this.sal = sal;
		this.dno = dno;
		this.dname = dname;
		this.deptLoc = deptLoc;
		this.city = city;
		this.state = state;
	}

	@Override
	public String toString() {
		return "Eid=" + eid + ", First name=" + fname + ", Last Name=" + lname + ", Job=" + job + ", Salary =" + sal
				+ ", Department Number =" + dno + ", Department Name =" + dname + ", Department Location=" + deptLoc
				+ ", City=" + city + ", State=" + state /* +"\n" */;
	}

}

class FetchDataFromDB {

	public static List<Emp> get() {
		Emp e = null;
		List<Emp> elist = new ArrayList<Emp>();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company_1", "root", "Manju@9611");
			String query = "Select * from " + "emp e1 join dept d1 on  e1.dno = d1.dno "
					+ "join location l1 on d1.lid = l1.lid";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				e = new Emp();
				e.eid = rs.getInt("eid");
				e.fname = rs.getString("fname");
				e.lname = rs.getString("lname");
				e.job = rs.getString("job");
				e.sal = rs.getDouble("sal");
				e.dno = rs.getInt("dno");
				e.dname = rs.getString("dname");
				e.deptLoc = rs.getString("location");
				e.city = rs.getString("city");
				e.state = rs.getString("state");
				elist.add(e);
			}

		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		return elist;
	}
}

public class EmployeeStream {

	public static void main(String[] args) {
		List<Emp> empList = FetchDataFromDB.get();
//		empList.forEach(System.out::println);
//		System.out.println("=====================================");
//		Stream<Emp> empStream = empList.stream();
//		empStream.forEach(System.out::println);
//		System.out.println("=====================================");

		// using Stream API
		// 1. WAP to display emp data where job is salesman
		Stream<Emp> s1 = empList.stream();

		Predicate<Emp> p1 = (a) -> a.job.equals("Salesman");
		Stream<Emp> finalOut = s1.filter(p1);
		finalOut.forEach(System.out::println);
		System.out.println();
		System.out.println("=================================== job is salesman");
		// Optimizing it with stream api
		empList.stream().filter((a) -> a.job.equals("Salesman")).forEach(System.out::println);

		System.out.println();
		System.out.println("======================================firstname is Aman");
		// 2.WAP to where firstname is Aman
		empList.stream().filter((a) -> a.fname.equals("Aman")).forEach(System.out::println);

		System.out.println();
		System.out.println("======================================salary > 45000");
		// 3. WAP to display emp data where salary > 45000
		empList.stream().filter((s) -> s.sal > 45000).forEach(System.out::println);

		// 6-Oct-26
		System.out.println();
		System.out.println("======================================firstname startwith 'S'");
		// 4. WAP to display the emp data whose firstname startwith 'S'
		empList.stream().filter((s) -> s.fname.startsWith("S")).forEach(System.out::println);

		System.out.println();
		System.out.println("======================================If emp working in Koramangala");
		// 5. If emp working in Kormangle
		empList.stream().filter((s) -> s.deptLoc.equals("Koramangala")).forEach(System.out::println);

		System.out.println();
		// 6. WAP to display emp records if the emp working in chennai city
		System.out.println("======================================emp working in chennai city");
		empList.stream().filter((s) -> s.city.equals("Chennai")).forEach(System.out::println);

		System.out.println();
		// 7. emp salary > 35000 but less than 70000
		System.out.println("======================================emp salary > 35000 but less than 70000");
		empList.stream().filter((s) -> s.sal > 35000 && s.sal < 75000).forEach(System.out::println);

		System.out.println();
		// 8. sal > 30000 and dept = IT
		System.out.println("======================================sal > 30000 and dept = IT");
		empList.stream().filter((s) -> s.sal > 30000 && s.dname.equals("IT")).forEach(System.out::println);

		// 9. Display emp fname and lname starts with S or A
		System.out.println();
		System.out.println(
				"======================================Display emp fname and lname where fname starts with S or A");
		empList.stream().filter((s) -> s.fname.startsWith("S") || s.fname.startsWith("A"))
				.forEach((s) -> System.out.println(s.fname + " " + s.lname));

		// Performing the same question with map() method
		//map()-> same as JS array method transforms the original array/object to a different new array/object and returns it
		System.out.println();
		System.out.println(
				"======================================Display emp fname and lname where fname starts with S or A using map()");
		Stream<Emp> st = empList.stream();
		Predicate<Emp> p = (e) -> e.fname.startsWith("S") || e.fname.startsWith("A"); // Condition
		Stream<Emp> st1 = st.filter(p); // Filter or where clause
		Function<Emp, String> f = (e) -> e.fname + " " + e.lname; // For just outputting
		Stream<String> st2 = st1.map(f); // mapping
		st2.forEach(System.out::println); // outptu final

		System.out.println();
		System.out.println(
				"======================================Display emp fname and lname where fname starts with S or A using map() optimized");
		empList.stream().filter((e) -> e.fname.startsWith("S") || e.fname.startsWith("A"))
				.map((e) -> e.fname + " " + e.lname).forEach(System.out::println);

		// 10. dis emp fname,job,sal works in HR
		System.out.println();
		System.out.println("=======================================================dis emp fname,job,sal works in HR");
		empList.stream().filter((e) -> e.dname.equals("HR")).map((e) -> e.fname + " " + e.job + " " + e.sal)
				.forEach(System.out::println);

		// 11. WAP to diply the fullname of all emp
		System.out.println();
		System.out
				.println("=======================================================WAP to diply the fullname of all emp");
		empList.stream().map((e) -> e.fname + " " + e.lname).forEach(System.out::println);

		// 12. WAP to diplay the emp name in below format
		// e.fname=Siddarth e.lname=Patil -> Siddarth.P
		System.out.println();
		System.out.println(
				"======================================================= WAP to diplay the emp name in below format\r\n"
						+ "		//e.fname=Siddarth e.lname=Patil -> Siddarth.P");
		empList.stream().filter((e) -> e.fname.equals("Siddarth") && e.lname.equals("Patil"))
				.map((e) -> e.fname + "." + e.lname.substring(0, 1)).forEach(System.out::println);

		// 13. WAP to display the first half of the fname
		System.out.println();
		System.out.println(
				"======================================================= WAP to display the first half of the fname");
		empList.stream().map((e) -> e.fname.substring(0, e.fname.length() / 2)).forEach(System.out::println);

		// 14. WAP to display the second half of the lname
		System.out.println();
		System.out.println(
				"======================================================= WAP to display the second half of the fname");
		empList.stream().map((e) -> e.fname.substring(e.fname.length() / 2)).forEach(System.out::println);

		// 15. WAP to display the first half inlowercase and secong half in upper case
		System.out.println();
		System.out.println(
				"======================================================= WAP to display the first half inlowercase and secong half in upper case");
		empList.stream().map((e) -> e.fname.substring(0, e.fname.length() / 2).toLowerCase()
				+ e.fname.substring(e.fname.length() / 2).toUpperCase()).forEach(System.out::println);

		// 16. WAP to display all the job roles of emp(distinct)
		System.out.println();
		System.out.println(
				"======================================================= WAP to display all the job roles of emp(distinct)");
		empList.stream().map((e) -> e.job).distinct().forEach(System.out::println);

		// 17. WAP to disp the dept and its loc from the emp
		System.out.println();
		System.out.println(
				"=======================================================WAP to disp the dept and its loc from the emp");
		empList.stream().map((e) -> e.dname + "->" + e.deptLoc).distinct().forEach(System.out::println);

		// 18. WAP to disp the first 5 emp data from emp
		System.out.println();
		System.out.println(
				"=======================================================WAP to disp the first 5 emp data from emp");
		empList.stream().map(e -> e).limit(5).forEach(System.out::println);

		// 19. WAP to disp the first 8 emp data from emp
		System.out.println();
		System.out.println(
				"=======================================================WAP to disp the first 8 emp data from emp");
		empList.stream().map(e -> e).limit(8).forEach(System.out::println);
		
		//20. WAP to display the first 3 emp full names
		System.out.println();
		System.out.println("=======================================================WAP to display the first 3 emp full names");
		empList.stream().map(e->e.fname+" "+e.lname).limit(3).forEach(System.out::println);
		
		
		//21 numbering mistake
		
		//22. WAP to disp the 2nd records from emp
		System.out.println();
		System.out.println("=======================================================WAP to disp the 2nd records from emp");
		empList.stream().map(e->e).skip(1).limit(1).forEach(System.out::println);
		
		//23. WAP to disp the 7th record 
		System.out.println();
		System.out.println("=======================================================WAP to disp the 7th record ");
		empList.stream().map(e->e).skip(6).limit(1).forEach(System.out::println);
		
		
		//24. WAP to display the 10th and 11th record from emp
		System.out.println();
		System.out.println("=======================================================WAP to display the 10th and 11th record from emp");
		empList.stream().map(e->e).skip(9).limit(2).forEach(System.out::println);
		
		
		//sorting
		//25. WAP to display the records in asc order based on empId
		System.out.println();
		System.out.println("=======================================================WAP to display the records in asc order based on empId");
		empList.stream().sorted(Comparator.comparing((e)->e.eid)).forEach(System.out::println);
		
		
		//26. WAP disp emp firstname in alphabetical order
		System.out.println();
		System.out.println("=======================================================WWAP disp emp firstname in alphabetical order");
		empList.stream().map(e->e.fname).sorted(Comparator.comparing(e->e)).forEach(System.out::println);
		
		
		//27. WAP disp last emp record
		System.out.println();
		System.out.println("======================================================= WAP disp last emp record");
		empList.stream().sorted(Comparator.comparing( (Emp e)->e.eid).reversed()).limit(1).forEach(System.out::println);
		
		
		//28. WAP to disp last 3 emp records
		System.out.println();
		System.out.println("=======================================================WAP to disp last 3 emp records");
		empList.stream().sorted(Comparator.comparing((Emp e)->e.eid).reversed()).limit(3).forEach(System.out::println);
		
		
		//29. WAP to display the names of the emps if the emp fname starts with s or k or a and arrange the info based on names in alphabetical order
		System.out.println();
		System.out.println("=======================================================System.out.println();\r\n"
				+ "		System.out.println(\"=======================================================WAP to disp last 3 emp records\");\r\n"
				+ "		");
		empList.stream().filter(e->e.fname.startsWith("S") || e.fname.startsWith("K") || e.fname.startsWith("A")).map(e->e.fname+" "+e.lname).sorted(Comparator.comparing((e) -> e)).forEach(System.out::println);
		
		
		//30. WAP to display the emp info if the emp is from karnataka and display the first 3 emps from output
		System.out.println();
		System.out.println("=======================================================WAP to display the emp info if the emp is from karnataka and display the first 3 emps from output");
		empList.stream().filter(e->e.state.equals("Karnataka")).limit(3).forEach(System.out::println);
		
		
		//31. WAP to display the emp with even id
		System.out.println();
		System.out.println("=======================================================WAP to display the emp with even id");
		empList.stream().filter(e->(e.eid%2==0)).forEach(System.out::println);
		
		
		//32. WAP to display the top 3 salaries from emp list
		System.out.println();
		System.out.println("=======================================================WAP to display the top 3 salaries from emp list");
		empList.stream().map(e->e.sal).sorted(Comparator.comparing((Double e)->e).reversed()).distinct().limit(3).forEach(System.out::println);
		
		
		//33. WAP to display the top 3 min sal from emp list
		System.out.println();
		System.out.println("=======================================================WAP to display the top 3 min sal from emp list");
		empList.stream().map(e->e.sal).sorted(Comparator.comparing(e->e)).distinct().limit(3).forEach(System.out::println);
		
		
		
		//Terminal methods
		
		//34. WAP to diplay the number of emps in the emplIst
		System.out.println();
		System.out.println("=======================================================WAP to diplay the number of emps in the emplIst");
		System.out.println(empList.stream().count());
		
		
		//35. WAP to disp nu of emps who is working as salesman
		System.out.println();
		System.out.println("=======================================================WAP to disp nu of emps who is working as salesman");
		System.out.println(empList.stream().filter(e->e.job.equals("Salesman")).count());
		
		
		//36. WAP to disp no of emp working in chennai
		System.out.println();
		System.out.println("=======================================================WAP to disp no of emp working in chennai");
		System.out.println(empList.stream().filter(e->e.city.equals("Chennai")).count());
		
		//37. WAP to disp the first emp record from empList
		System.out.println();
		System.out.println("=======================================================WAP to disp the first emp record from empList");
		empList.stream().limit(1).forEach(System.out::println);
		System.out.println("=======Second approach===========");
		Emp e1 = empList.stream().findFirst().orElse(null);
		System.out.println(e1);
		
		
		//38. WAP to disp the first salesman record
		System.out.println();
		System.out.println("=======================================================WAP to disp the first salesman record");
		Emp firstSalesman = empList.stream().filter(e->e.job.equals("Salesman")).findFirst().orElse(null);
		System.out.println(firstSalesman);
			
		//39. WAP to disp data of first emp records based on alphabetical order
		System.out.println();
		System.out.println("=======================================================WAP to disp data of first emp records based on alphabetical order");
		System.out.println(empList.stream().sorted(Comparator.comparing((e)->e.fname)).findFirst().orElse(null)); //this is will always return the object Emp or other predefined object.class,wrapper classes etcc
		
		//40. Wap to check whether any emp belongs to karnataka or not.
		System.out.println();
		System.out.println("=======================================================Wap to check whether any emp belongs to karnataka or not.");
		boolean status = empList.stream().anyMatch(e->e.state.equals("Karnataka"));
		System.out.println(status);
		
		
		//41. WAP to check all the emps belong to Bangalore or not
		System.out.println();
		System.out.println("=======================================================WAP to check all the emps belong to Bangalore or not.");
		boolean secu = empList.stream().allMatch(e->e.city.equals("Security"));
		System.out.println(secu);
		
		//42. WAP to check the emp works in security dept or not
		System.out.println();
		System.out.println("=======================================================WAP to check the emp works in security dept or not.");
		System.out.println(empList.stream().anyMatch(e->e.dname.equals("Security")));
		
		//43. WAP to disp if there are any emps who are working in sales dept
		System.out.println();
		System.out.println("=======================================================WAP to disp emp if there are any emps who are working in sales dept.");
		System.out.println(empList.stream().anyMatch(e->e.dname.equals("Sales")));
		
		
		//44. WAP to disp no of emps in dept no 113
		System.out.println();
		System.out.println("=======================================================WAP to disp no of emps in dept no 113.");
		Long count = empList.stream().filter(e->e.dno==113).count();
		System.out.println(count);
		
		
		//45. WAP to disp max salary from empList
		System.out.println();
		System.out.println("=======================================================WAP to disp max salary from empList.");
		//basic approach
		empList.stream().sorted(Comparator.comparing((Emp e)->e.sal).reversed()).distinct().limit(1).forEach(System.out::println);
		
		//max() method    
		//if we are not using .orElse(null) then we need to assign the variable to Optional<Emp>
		System.out.println("======max()=========");
		Emp maxSalary = 
				empList.stream().max(Comparator.comparing(e->e.sal)).orElse(null);
		System.out.println(maxSalary);
		
		//min()
		//46. Wap to display the min salary from empList
		System.out.println();
		System.out.println("=======================================================Wap to display the main salary from empList.");
		Optional<Emp> minsal = empList.stream().min(Comparator.comparing(e->e.sal));
		System.out.println(minsal);
		
		
		//47. Wap to display the max sal in dept 113
		System.out.println();
		System.out.println("=======================================================Wap to display the max sal in dept 113.");
		Emp maxSal113 = empList.stream().filter(e->e.dno==113).max(Comparator.comparing(e->e.sal)).orElse(null);
		System.out.println(maxSal113);
		
		
		//collect(Collectors.toWhat?()) is used to convert the stream to any of the collections object(stack,queue,set,list,etc)
		//toWhat?()-> can be anything toList(),toMap() etc
		//collect()
		//48. COnverting stream to any type of COllection objects
		System.out.println();
		System.out.println("======================================================= COnverting stream to any type of COllection objects.");
		List<Emp> salCollection = empList.stream().filter(e->e.sal>99000).collect(Collectors.toList());
		System.out.println(salCollection);
		
		
		//map()
		//49.Converting to a map
		System.out.println();
		System.out.println("=======================================================Converting to a map.");
		Map<Integer,Emp> mapFun = 
				empList.stream().filter(e->e.dname.equals("IT")).collect(Collectors.toMap(e->e.eid, e->e));
		for(Integer emp : mapFun.keySet()) {
			System.out.println(mapFun.get(emp));
		}
		
		
		//50. WAP to convert the emp data whose fname starts with S or K int a list
		System.out.println();
		System.out.println("======================================================= WAP to convert the emp data whose fname starts with S or K int a list.");
		List<Emp> SorKList = 
				empList.stream().filter(e->e.fname.startsWith("S")|| e.fname.startsWith("K")).collect(Collectors.toList());
		System.out.println(SorKList);
		
		//51. WAP to convert emp data whose sal is more than 40000 into a map.Take eid as key and value as emp obj
		System.out.println();
		System.out.println("======================================================= WAP to convert emp data whose sal is more than 40000 into a map.Take eid as key and value as emp obj.");
		Map<Integer,Emp> sal40000Map = empList.stream().filter(e->e.sal>40000).collect(Collectors.toMap(e->e.eid, e->e));
		for(Integer emp : sal40000Map.keySet()) {
			System.out.println(sal40000Map.get(emp));
		}
		
		//52. WAP to disp the max in each dept
		System.out.println();
		System.out.println("=======================================================WAP to disp the max in each dept.");
//		Map<String,List<Emp>> maxOfEachDept = 
//			empList.stream().collect(Collectors.toMap(e->e.dname, ));
	}

}
