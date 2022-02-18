package it.polito.university;

public class Student {
	private int id;
	private String firstName;
	private String lastName;

	private Course[] courses;
	private Exam[] exams;

	private int nextExam = 0;
	
	public Student(int id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		
		courses = new Course[University.MAX_STUDENT_PER_COURSE];
		exams = new Exam[University.MAX_COURSE_PER_STUDENT];
	}
	
	public void enroll(Course course) {
		for (int i=0;i<courses.length;++i) {
			if (courses[i]==null) {
				courses[i]=course;
				break;
			}
			
		}
	}

	public String plan() {
		StringBuffer result = new StringBuffer();
		
		for(Course c : courses) {
			if(c!=null) {
				result.append(c.toString()).append("\n");
			}
		}
		return result.toString();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
//		return id + " " + firstName + " " + lastName;
//		Optimisation with StringBuffer
		return (new StringBuffer()).
				append(id).append(" ").
				append(firstName).append(" ").
				append(lastName).toString();
	}
	
	
	//Extended exercise version
	public void addExam(Exam e) {
		exams[nextExam++]=e;
	}
	
	public double avgStudent() {
		int sum=0;
		if(nextExam==0) return Double.NaN;
		for(Exam e : exams) {
			if(e==null) break;
			sum += e.getGrade();
		}
		return sum/nextExam;
	}	
	
	public int nextCourseCode() {
		int nextCourseCode=0;
		for(int i=0; i<courses.length;++i) {
			if (courses[i]!=null) {
				nextCourseCode++;
			}
		}
		return nextCourseCode;
	}
	
	public double getScore() {
		double avg = avgStudent();
		if (Double.isNaN(avg)) return -1;
		avg+=nextExam/nextCourseCode()*10;
		return avg;
	}
}
