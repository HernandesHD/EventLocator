import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { EventsListComponent } from './components/events-list/events-list.component';
import { EventsCreateComponent } from './components/events-create/events-create.component';
import { EventEditComponent } from './components/event-edit/event-edit.component';
import { AuthGuard } from './guards/auth.guard';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'events', component: EventsListComponent, canActivate: [AuthGuard] }, 
  { path: 'events/new', component: EventsCreateComponent, canActivate: [AuthGuard] },
  { path: 'events/edit/:id', component: EventEditComponent, canActivate: [AuthGuard] },
  { path: '', redirectTo: '/events', pathMatch: 'full' }, 
  { path: '**', redirectTo: '/events' } 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }