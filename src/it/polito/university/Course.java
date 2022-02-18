package it.polito.university;

public class Course {
	private int courseId;
	private String courseName;
	private String teacherName;
	
	private Student[] courseAttendees;
	private Exam[] exams;
	
	private int nextExam = 0;
	
	public Course(int courseId, String courseName, String teacherName) {
		this.courseId = courseId;
		this.courseName = courseName;
		this.teacherName = teacherName;	
		
		courseAttendees=new Student[University.MAX_STUDENT_PER_COURSE];
		exams=new Exam[University.MAX_COURSE_PER_STUDENT];
	}
	
	public void enroll(Student student) {
		for (int i=0;i<courseAttendees.length;++i) {
			if (courseAttendees[i]==null) {
				courseAttendees[i]=student;
				break;
			}
			
		}
	}

//	StringBuffer is better than String for this job.
//	String is slow and consumes more memory when we concatenate too many strings
//	because every time it creates new instance.
	public String attendees() {
		StringBuffer result = new StringBuffer();
		
		for(Student s : courseAttendees) {
			if(s!=null) {
				result.append(s.toString()).append("\n");
			}
		}
		return result.toString();		
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	@Override
	public String toString() {
//		return courseId + "," + courseName + "," + teacherName;
//		Optimisation with StringBuffer
		return (new StringBuffer()).
				append(courseId).append(",").
				append(courseName).append(",").
				append(teacherName).toString();
	}
	
	// Extended exercise version
	public void addExam(Exam e) {
		exams[nextExam++]=e;
	}
	public double avgCourse() {
		int sum=0;
		if(nextExam==0) return Double.NaN;
		for(Exam e : exams) {
			if(e==null) break;
			sum += e.getGrade();
		}
		return sum/nextExam;
	}
}
