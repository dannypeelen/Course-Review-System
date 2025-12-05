import { TestBed } from '@angular/core/testing';

import { Course } from './course.service';

describe('CourseService', () => {
  let service: Course;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Course);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
