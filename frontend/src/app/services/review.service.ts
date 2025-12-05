import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Course } from '../services/course.service';
import { Student } from '../services/student.service';

export interface Review {
  id: number;
  writer: Student;
  reviewContent: string;
  likes: number;
  dislikes: number;
  course: Course;
}

@Injectable({
  providedIn: 'root'
})
export class Review {
  private apiUrl = 'http://localhost:8080/api/reviews';
  
  constructor(private http: HttpClient) { }

  getReviews(): Observable<Review[]> {
    return this.http.get<Review[]>(this.apiUrl);
  }

  addReview(review: Review): Observable<Review> {
    return this.http.post<Review>(this.apiUrl, review);
  }

}