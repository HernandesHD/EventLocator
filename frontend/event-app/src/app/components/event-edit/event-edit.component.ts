import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-event-edit',
  templateUrl: './event-edit.component.html',
  styleUrls: ['./event-edit.component.css']
})
export class EventEditComponent implements OnInit {

  eventForm: FormGroup;
  errorMessage: string | null = null;
  private apiUrl = 'http://localhost:8083/api/v1/events';
  private eventId!: number;

  constructor(
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private http: HttpClient,
    private router: Router
  ) {
    this.eventForm = this.fb.group({
      title: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(100)]],
      description: ['', [Validators.required, Validators.minLength(10), Validators.maxLength(1000)]],
      eventDate: ['', Validators.required],
      place: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(200)]],
      capacity: [null, Validators.required]
    });
  }

  ngOnInit(): void {
    this.eventId = Number(this.route.snapshot.paramMap.get('id'));
    this.loadEvent();
  }

  loadEvent(): void {
    this.http.get(`${this.apiUrl}/${this.eventId}`).subscribe({
      next: (event: any) => {
        this.eventForm.patchValue({
          title: event.name,
          description: event.description,
          eventDate: event.eventDate,
          place: event.place,
          capacity: event.capacity
        });
      },
      error: () => this.errorMessage = 'Erro ao carregar evento.'
    });
  }

  onSubmit(): void {
    if (this.eventForm.valid) {
      this.http.put(`${this.apiUrl}/${this.eventId}`, this.eventForm.value).subscribe({
        next: () => this.router.navigate(['/events']),
        error: () => this.errorMessage = 'Erro ao atualizar evento.'
      });
    }
  }

}
