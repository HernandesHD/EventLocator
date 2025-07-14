import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-events-list',
  templateUrl: './events-list.component.html',
  styleUrls: ['./events-list.component.css']
})
export class EventsListComponent implements OnInit {

  private apiUrl = 'http://localhost:8083/api/v1/';

  events: any[] = [];
  errorMessage: string | null = null;
  currentPage = 0;
  pageSize = 10;
  totalPages = 0;

  constructor(private http: HttpClient, private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
    this.loadEvents();
  }

  loadEvents(): void {
    this.errorMessage = null;
    this.http.get(`${this.apiUrl}events?page=${this.currentPage}&size=${this.pageSize}`).subscribe({
      next: (response: any) => {
        this.events = response.body.content;
        this.totalPages = response.totalPages;
        console.log('Events loaded:', this.events);
      },
      error: (error) => {
        this.errorMessage = 'Erro ao carregar eventos. Talvez sua sessão expirou.';
        console.error('Error loading events:', error);
        if (error.status === 401 || error.status === 403) {
          this.authService.logout();
        }
      }
    });
  }

  goToPage(page: number): void {
    this.currentPage = page;
    this.loadEvents();
  }

  logout(): void {
    this.authService.logout();
  }

  createEvent(): void {
    this.router.navigate(['/events/new']);
  }

  editEvent(event: any): void {
    this.router.navigate(['/events/edit', event.id]);
  }

  deleteEvent(eventId: number): void {
    if (confirm('Deseja realmente excluir este evento?')) {
      this.http.delete(`${this.apiUrl}events/${eventId}`).subscribe({
        next: () => {
          this.loadEvents(); 
        },
        error: (error) => {
          this.errorMessage = 'Erro ao excluir evento.';
          console.error('Delete error:', error);
        }
      });
    }
  }

}
