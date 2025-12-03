import { Component } from '@angular/core';
import { RouterLink, ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-course-detail',
  standalone: true,
  imports: [RouterLink, CommonModule],
  templateUrl: './course-detail.component.html',
  styleUrls: ['./course-detail.component.scss']
})
export class CourseDetailComponent {
  // Component logic goes here
  // Hard-coded for now
  courses = [
    {id: 1, name: 'Web Development', code: 'CS1520', professor : 'Paulo Brasko', credits: 3, description: 'This course teaches you about...'},
    {id: 2, name: 'Software Engineering', code: 'CS1530', professor : 'Nadine von Frankenberg', credits: 3, description: 'This course teaches you about...'},
    {id: 3, name: 'Data Structures & Algorithms 2', code: 'CS1501', professor : 'Nicholas Farnan', credits: 3, description: 'This course teaches you about...'},
  ];
  course: any;

  constructor(private route: ActivatedRoute) {}

  ngOnInit() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.course = this.courses.find(c => c.id === id);
  }
}