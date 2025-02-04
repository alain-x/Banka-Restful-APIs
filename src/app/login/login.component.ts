import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { ApiService } from '../services/api.service';
import { firstValueFrom } from 'rxjs';

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

  formData = {
    email: '',
    password: '',
  };

  message: any = null;

  showMessage(message: string) {
    this.message = message;
    setTimeout(() => {
      this.message == null;
    }, 3000);
  }

  async handelSubmit() {
    if (!this.formData.email || !this.formData.password) {
      this.showMessage('All this Are required');
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
        this.apiService.AuthStatusChanged.emit();
        this.router.navigate(['/dashboard']);
      }
    } catch (error: any) {
      console.log(error);

      this.showMessage(
        error.error?.message || error?.message || 'enable to login'
      );
    }
  }
}
