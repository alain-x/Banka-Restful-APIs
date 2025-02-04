import { Component, OnInit } from '@angular/core';
import { Chart, registerables } from 'chart.js';
import { ApiService } from '../services/api.service';
import { CommonModule, DatePipe } from '@angular/common';
import { Router, ActivatedRoute } from '@angular/router';
import { catchError } from 'rxjs/operators';
import { of } from 'rxjs';

Chart.register(...registerables);

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [
    CommonModule, // Ensure CommonModule is imported
  ],
  providers: [DatePipe],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent implements OnInit {
  error: any = null;
  transactions: any[] = [];
  accountId: string | null = null;

  constructor(
    private apiService: ApiService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.accountId = this.route.snapshot.paramMap.get('accountId');
    console.log('Account ID:', this.accountId);
    this.loadTransactionHistory();
  }
  async loadTransactionHistory(): Promise<void> {
    if (this.accountId) {
      this.apiService
        .getTransactionHistory(this.accountId)
        .pipe(
          catchError((error) => {
            console.error('Error fetching transaction history:', error); // Use console.error for errors
            this.error = error.message;
            return of(null);
          })
        )
        .subscribe((data: any) => {
          console.log('Transaction Data:', data);
          if (data) {
            this.transactions = data;
          }
        });
    }
  }
}
