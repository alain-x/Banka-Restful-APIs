import { Component } from '@angular/core';
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
export class AccountComponent {
  constructor(private apiService: ApiService, private router: Router) {}

  formData1 = {
    email: '',
    accountType: '',
  };

  message: any = null;

  showMessage(message: string) {
    this.message = message;
    setTimeout(() => {
      this.message = null;
    }, 3000);
  }

  async handleSubmit() {
    if (!this.formData1.email || !this.formData1.accountType) {
      this.showMessage('All fields are required');
      return;
    }

    try {
      const response: any = await firstValueFrom(
        this.apiService.createAccount(this.formData1)
      );
      if (response.status === 200) {
        this.showMessage('Account created successfully');
        console.log(response);
        this.router.navigate(['/account']);
      } else {
        this.showMessage(response.message || 'Account creation failed');
      }
    } catch (error) {
      console.error('Error:', error);
      this.showMessage('An error occurred while creating the account');
    }
  }
}
