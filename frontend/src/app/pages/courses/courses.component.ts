import { Component } from '@angular/core';
import { NgFor } from '@angular/common'
import { RouterLink } from '@angular/router'

@Component({
  selector: 'app-courses',
  imports: [NgFor, RouterLink],
  templateUrl: './courses.component.html',
  styleUrl: './courses.component.scss'
})
export class CoursesComponent {
// Hard-coded courses for now
  courses = [
    {id: 1, name: 'CS1520 - Web Development', professor : 'Paulo Brasko', credits: 3},
    {id: 2, name: 'CS1530 - Software Engineering', professor : 'Nadine von Frankenberg', credits: 3},
    {id: 3, name: 'CS1501 - Data Structures & Algorithms 2', professor : 'Nicholas Farnan', credits: 3},
  ];
}
