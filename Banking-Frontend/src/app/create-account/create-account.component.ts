import { Component } from '@angular/core';
import { ApiService } from '../services/api.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { firstValueFrom } from 'rxjs';

@Component({
  selector: 'app-create-account',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './create-account.component.html',
  styleUrl: './create-account.component.css',
})
export class CreateAccountComponent {
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

      console.log('API Response:', response); // Log the full response

      if (response && response.status === 'SUCCESS') {
        this.showMessage('Account created successfully');
        this.router.navigate(['/account']);
      } else {
        this.showMessage(response.message || 'Account creation failed');
      }
    } catch (error: any) {
      console.error('API Error:', error); // Log full error details
      this.showMessage(error.error?.message || 'An error occurred');
    }
  }
}
