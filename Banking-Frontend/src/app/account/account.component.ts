import { Component, OnInit } from '@angular/core';
import { ApiService } from '../services/api.service';
import { Router } from '@angular/router';
import { firstValueFrom } from 'rxjs';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-account',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css'],
})
export class AccountComponent implements OnInit {
  accountId?: number; // Optional to handle cases where the user has no account
  transactions: any[] = [];

  constructor(private router: Router, private apiService: ApiService) {}

  ngOnInit(): void {
    // Retrieve user data from localStorage (assuming it's stored after login)
    const user = JSON.parse(localStorage.getItem('user') || '{}');

    // Check if the user has an accountId
    if (user && user.accountId) {
      this.accountId = user.accountId;
      console.log('Account ID:', this.accountId); // Debugging
      this.loadTransactionHistory();
    } else {
      console.warn('User does not have an associated account.');
    }
  }

  loadTransactionHistory(): void {
    if (!this.accountId) {
      console.error('No account ID available, cannot fetch transactions.');
      return;
    }

    this.apiService.getTransactionHistory(this.accountId).subscribe(
      (response) => {
        if (response && response.data && Array.isArray(response.data)) {
          this.transactions = response.data;
          console.log('Transaction History:', this.transactions);
        } else {
          console.error('Invalid response format:', response);
        }
      },
      (error) => {
        console.error('Error fetching transaction history:', error);
      }
    );
  }

  navigateTo(path: string) {
    this.router.navigate([path]);
  }
}
