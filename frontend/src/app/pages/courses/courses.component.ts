import { Component } from '@angular/core';
import { NgFor } from '@angular/common';
import { RouterLink } from '@angular/router';
import { Course} from '../../services/course.service';

@Component({
  selector: 'app-courses',
  imports: [NgFor, RouterLink],
  templateUrl: './courses.component.html',
  styleUrl: './courses.component.scss'
})
export class CoursesComponent {

  constructor(private courseService: Course) {}
// Hard-coded courses for now
  courses = [
    {id: 1, name: 'Web Development', code: 'CS1520', professor : 'Paulo Brasko', credits: 3, description: 'This course teaches you about...'},
    {id: 2, name: 'Software Engineering', code: 'CS1530', professor : 'Nadine von Frankenberg', credits: 3, description: 'This course teaches you about...'},
    {id: 3, name: 'Data Structures & Algorithms 2', code: 'CS1501', professor : 'Nicholas Farnan', credits: 3, description: 'This course teaches you about...'},
  ];

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
