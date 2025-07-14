import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-events-create',
  templateUrl: './events-create.component.html',
  styleUrls: ['./events-create.component.css']
})
export class EventsCreateComponent {

  eventForm: FormGroup;
  errorMessage: string | null = null;

  private apiUrl = 'http://localhost:8083/api/v1/events';

  constructor(
    private fb: FormBuilder,
    private http: HttpClient,
    private router: Router
  ) {
    this.eventForm = this.fb.group({
      title: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(100)]],
      description: ['', [Validators.required, Validators.minLength(10), Validators.maxLength(1000)]],
      eventDate: ['', Validators.required],
      place: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(200)]],
      capacity: [null, Validators.min(1)]
    });
  }

  onSubmit(): void {
    if (this.eventForm.valid) {
      this.http.post(this.apiUrl, this.eventForm.value).subscribe({
        next: () => this.router.navigate(['/events']),
        error: (err) => {
          console.error('Erro ao criar evento:', err);
          this.errorMessage = 'Erro ao criar evento.';
        }
      });
    }
  }

}
