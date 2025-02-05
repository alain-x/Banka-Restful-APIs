import { HttpClient, HttpHeaders } from '@angular/common/http';
import { EventEmitter, Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  AuthStatusChanged = new EventEmitter<void>();
  private static BASE_URL = 'http://localhost:8090';
  constructor(private http: HttpClient) {}

  private getHeaders(): HttpHeaders {
    const token = localStorage.getItem('token');
    return new HttpHeaders({
      Authorization: `Bearer ${token}`,
    });
  }

  registerUser(registration: any): Observable<any> {
    return this.http.post(`${ApiService.BASE_URL}/auth/register`, registration);
  }

  loginUser(loginDetails: any): Observable<any> {
    return this.http.post(`${ApiService.BASE_URL}/auth/login`, loginDetails);
  }

  logOut(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('role');
  }
  isAdmin(): boolean {
    const role = localStorage.getItem('role');
    return role === 'ADMIN' || role === 'STAFF';
  }
  isAutheticated(): boolean {
    const token = localStorage.getItem('token');
    return !!token;
  }
}
