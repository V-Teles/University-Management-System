package it.polito.university;

import java.util.Arrays;
import java.util.Comparator;

public class University {
	protected static final int MAX_COURSES = 50;
	protected static final int MIN_COURSE_ID = 10;
	protected static final int MAX_STUDENTS = 1000;
	protected static final int MIN_STUDENT_ID = 10000;
	protected static final int MAX_COURSE_PER_STUDENT = 25;
	protected static final int MAX_STUDENT_PER_COURSE = 100;
	
	private int nextStudentId=MIN_STUDENT_ID;
	private int nextCourseId=MIN_COURSE_ID;
	
	private String name;
	private String rector;
	
	private Student[] enrolledStudents;
	private Course[] courseCatalog;

	
	public University(String name) {
		this.name = name;
		this.rector = "<unknown>";
		
		enrolledStudents = new Student[MAX_STUDENTS];
		courseCatalog = new Course[MAX_COURSES];
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRector() {
		return rector;
	}
	public void setRector(String firstName, String lastName) {
		this.rector = firstName+" "+lastName;
	}
	
	public int enroll(String firstName, String lastName) {		
		Student student = new Student(nextStudentId, firstName, lastName);
		enrolledStudents[nextStudentId-MIN_STUDENT_ID]=student;
		return nextStudentId++;
	}
	
	public String student(int id) {
		Student student = enrolledStudents[id-MIN_STUDENT_ID];
		return student.toString();
		
//		//This also works
//		return enrolledStudents[id-MIN_STUDENT_ID].toString();
	}
	
	public int activate(String courseName, String teacherName) {
		Course course = new Course(nextCourseId, courseName, teacherName);
		
		courseCatalog[nextCourseId-MIN_COURSE_ID]=course;
		
		return nextCourseId++;
	}
	
	public String course(int courseId) {
		return courseCatalog[courseId-MIN_COURSE_ID].toString();
	}

	public void register(int studentId ,int courseId) {
		Course course = courseCatalog[courseId-MIN_COURSE_ID];
		Student student = enrolledStudents[studentId-MIN_STUDENT_ID];
		
		course.enroll(student);
		student.enroll(course);
		
	}
	
	public String listAttendees(int courseId) {
		Course course = courseCatalog[courseId-MIN_COURSE_ID];
		return course.attendees();
	}
	
	
	public String studyPlan(int studentId) {
		Student student =  enrolledStudents[studentId-MIN_STUDENT_ID];
		return student.plan();
	}
	
	// Extended exercise version
	public Student getStudents(int studentId) {
		if(studentId<MIN_STUDENT_ID) {
			return null;
		}
		return enrolledStudents[studentId-MIN_STUDENT_ID];
	}

	public Course getCourse(int courseId) {
		if(courseId<MIN_COURSE_ID) {
			return null;
		}
		return courseCatalog[courseId-MIN_COURSE_ID];
	}
	
	protected Student[] top(int n, Comparator<Student> cmp) {
		int numStudents=nextStudentId-MIN_STUDENT_ID;
		n=Math.min(n, numStudents);
		Student[] sorted=Arrays.copyOf(enrolledStudents, numStudents);
		Arrays.sort(sorted, cmp.reversed());
		return Arrays.copyOf(sorted, n);
	}
}
