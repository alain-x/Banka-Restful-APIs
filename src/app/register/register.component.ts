import { CommonModule } from '@angular/common';
import { Component, EventEmitter, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../services/api.service';
import { Router, RouterLink, RouterModule } from '@angular/router';
import { firstValueFrom } from 'rxjs';
@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
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
      this.message == null;
    }, 3000);
  }

  async handelSubmit() {
    if (
      !this.formData.email ||
      !this.formData.name ||
      !this.formData.password ||
      !this.formData.phoneNumber
    ) {
      this.showMessage('All this are required');
    }

    try {
      const response: any = await firstValueFrom(
        this.apiService.registerUser(this.formData)
      );

      if (response.status === 200) {
        this.showMessage('Registration Successful');
        this.router.navigate(['/login']);
      }
    } catch (error: any) {
      console.log(error);

      if (error.status === 409) {
        this.showMessage('User already exists. Please log in.');
      } else {
        this.showMessage(
          error.error?.message || error.message || 'Unable to register'
        );
      }
    }
  }
}
