import { Component } from '@angular/core';
import { NgFor } from '@angular/common';
import { RouterLink } from '@angular/router';
import { Course, CourseService } from '../../services/course.service';

@Component({
  selector: 'app-courses',
  imports: [NgFor, RouterLink],
  templateUrl: './courses.component.html',
  styleUrl: './courses.component.scss'
})
export class CoursesComponent {

  constructor(private courseService: CourseService) {}
// // Hard-coded courses for now (matching new schema)
//   courses = [
//     {courseId: 1, courseName: 'Web Development', courseCode: 'CS1520', professor: 'Paulo Brasko', numberOfCredits: 3, description: 'This course teaches you about...', createdAt: ''},
//     {courseId: 2, courseName: 'Software Engineering', courseCode: 'CS1530', professor: 'Nadine von Frankenberg', numberOfCredits: 3, description: 'This course teaches you about...', createdAt: ''},
//     {courseId: 3, courseName: 'Data Structures & Algorithms 2', courseCode: 'CS1501', professor: 'Nicholas Farnan', numberOfCredits: 3, description: 'This course teaches you about...', createdAt: ''},
//   ];

  realCourses: Course[] = [];

  ngOnInit(): void {
    this.loadCourses();
  }

  loadCourses(): void {
    this.courseService.getCourses().subscribe(courses => {
      this.realCourses = courses;
      console.log(this.realCourses);
    });
  }


}
