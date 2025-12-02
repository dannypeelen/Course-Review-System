import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { CoursesComponent } from './pages/courses/courses.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'home', redirectTo: '', pathMatch: 'full' },
  { path: 'courses', component: CoursesComponent},
  // TODO: Add more routes for courses, reviews, etc.
  { path: '**', redirectTo: '', pathMatch: 'full' }
];
