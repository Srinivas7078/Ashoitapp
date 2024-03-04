package in.ashokit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.ashokit.entity.Course;

public interface CourseRepo extends JpaRepository<Course, Integer> {
	 @Query("select distinct(courseName) from Course")
		public List<String> getCourses();
}
