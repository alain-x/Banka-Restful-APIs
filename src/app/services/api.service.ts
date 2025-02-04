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

  isAdmin(): boolean {
    const role = localStorage.getItem('role');
    return role === 'ADMIN' || role === 'STAFF';
  }

  isAuthenticated(): boolean {
    const token = localStorage.getItem('token');
    return !!token;
  }

  logOut(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('role');
  }

  getTransactionHistory(accountId: string): Observable<any> {
    return this.http.get(
      `${ApiService.BASE_URL}/api/transactions/history/${accountId}`,
      { headers: this.getHeaders() } // Ensure headers are included if needed
    );
  }

  // Fetch a specific transaction by ID
  getSpecificTransaction(transactionId: number): Observable<any> {
    return this.http.get<any>(
      `${ApiService.BASE_URL}/api/transactions/${transactionId}`,
      { headers: this.getHeaders() }
    );
  }
}
