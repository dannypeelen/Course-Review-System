import { TestBed } from '@angular/core/testing';

import { Review } from './review.service';

describe('ReviewService', () => {
  let service: Review;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Review);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
