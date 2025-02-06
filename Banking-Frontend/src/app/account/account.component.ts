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
  accountId!: number; // You can dynamically set this value
  transactions: any[] = [];

  constructor(private router: Router, private apiService: ApiService) {}

  ngOnInit(): void {
    const accountIdFromStorage = localStorage.getItem('accountId');

    if (accountIdFromStorage) {
      this.accountId = +accountIdFromStorage;
      console.log('AccountId from localStorage:', this.accountId); // Debugging
      this.loadTransactionHistory();
    } else {
      console.error('Account ID is not found.');
    }
  }

  loadTransactionHistory(): void {
    this.apiService.getTransactionHistory(this.accountId).subscribe(
      (response) => {
        // Check if the response has the data key and map it to the transactions
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
