import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Review {
  reviewId: number;
  courseId: number;
  createdAt: string;
  review: string;
  rating: number;
  difficulty: number;
  timeCommitment: number;
  numberOfExams: number;
  numberOfProjects: number;
}

@Injectable({
  providedIn: 'root'
})
export class ReviewService {
  private apiUrl = 'http://localhost:8080/api/reviews';
  
  constructor(private http: HttpClient) { }

  getReviewsByCourse(courseId: number): Observable<Review[]> {
    return this.http.get<Review[]>(`${this.apiUrl}/course/${courseId}`);
  }

  addReview(review: Partial<Review>): Observable<Review> {
    return this.http.post<Review>(this.apiUrl, review);
  }

}