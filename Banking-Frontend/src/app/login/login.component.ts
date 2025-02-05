import { Component } from '@angular/core';
import { ApiService } from '../services/api.service';
import { Router } from '@angular/router';
import { firstValueFrom } from 'rxjs';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  constructor(
    private readonly apiService: ApiService,
    private router: Router
  ) {}

  formData: any = {
    email: '',
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
    if (!this.formData.email || !this.formData.password) {
      this.showMessage('All fields are required');
      return;
    }

    try {
      const response: any = await firstValueFrom(
        this.apiService.loginUser(this.formData)
      );
      if (response.status === 200) {
        this.showMessage('Login successful');
        localStorage.setItem('token', response.token);
        localStorage.setItem('role', response.role);
        setTimeout(() => {
          this.router.navigate(['/account']);
          this.apiService.AuthStatusChanged?.emit();
        }, 2000);
      }
    } catch (error: any) {
      console.log(error);
      this.showMessage(
        error.error?.message || error.message || 'Failed to login'
      );
    }
  }
}
