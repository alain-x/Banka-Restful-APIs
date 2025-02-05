import { Component } from '@angular/core';
import { ApiService } from '../services/api.service';
import { Router } from '@angular/router';
import { firstValueFrom } from 'rxjs';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css',
})
export class RegisterComponent {
  constructor(
    private readonly apiService: ApiService,
    private router: Router
  ) {}

  formData = {
    email: '',
    name: '',
    phoneNumber: '',
    password: '',
  };

  message: any = null;

  showMessage(message: string) {
    this.message = message;
    setTimeout(() => {
      this.message = null;
    }, 3000);
  }

  async handleSubmit() {
    if (
      !this.formData.email ||
      !this.formData.name ||
      !this.formData.password ||
      !this.formData.phoneNumber
    ) {
      this.showMessage('All fields are required');
      return;
    }

    try {
      const response: any = await firstValueFrom(
        this.apiService.registerUser(this.formData)
      );
      if (response.status === 200) {
        this.showMessage('Registration Successful');
        setTimeout(() => {
          this.router.navigate(['/login']);
        }, 2000);
      }
    } catch (error: any) {
      console.log(error);

      if (error.error?.message?.includes('Email already exists')) {
        this.showMessage(
          'Email is already registered. Please use another email.'
        );
      } else {
        this.showMessage(
          error.error?.message || error.message || 'Failed to register'
        );
      }
    }
  }
}
