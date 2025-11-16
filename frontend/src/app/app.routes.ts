import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'home', redirectTo: '', pathMatch: 'full' },
  // TODO: Add more routes for courses, reviews, etc.
  { path: '**', redirectTo: '', pathMatch: 'full' }
];
