import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Review } from '../services/review.service';
import { Teacher } from '../services/teacher.service';

export interface Course {
  id: number;
  courseNumber: string;
  title: string;
  description: string;
  teacher: Teacher;
  reviews: Review[];
  rating: number;
  schedule: string;
}

@Injectable({
  providedIn: 'root'
})
export class Course {
  private apiUrl = 'http://localhost:8080/api/courses';
  
  constructor(private http: HttpClient) { }

  getCourses(): Observable<Course[]> {
    return this.http.get<Course[]>(this.apiUrl);
  }

  addCourse(course: Course): Observable<Course> {
    return this.http.post<Course>(this.apiUrl, course);
  }

}
