import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http'; // Importe HttpClientModule e HTTP_INTERCEPTORS
import { FormsModule, ReactiveFormsModule } from '@angular/forms'; // Para formulários

import { LoginComponent } from './components/login/login.component';
import { EventsListComponent } from './components/events-list/events-list.component';
import { AuthInterceptor } from './interceptors/auth.interceptor';
import { EventsCreateComponent } from './components/events-create/events-create.component';
import { EventEditComponent } from './components/event-edit/event-edit.component'; // Importe o interceptor


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    EventsListComponent,
    EventsCreateComponent,
    EventEditComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
