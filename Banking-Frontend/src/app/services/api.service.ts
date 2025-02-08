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

  // Authentication APIs
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

  isAuthenticated(): boolean {
    const token = localStorage.getItem('token');
    return !!token;
  }

  // Account APIs
  createAccount(data: any) {
    return this.http.post(`${ApiService.BASE_URL}/api/accounts/create`, data, {
      headers: this.getHeaders(),
    });
  }

  getAllAccounts(): Observable<any> {
    return this.http.get(`${ApiService.BASE_URL}/api/accounts/`, {
      headers: this.getHeaders(),
    });
  }

  getAccountByEmail(email: string): Observable<any> {
    return this.http.get(`${ApiService.BASE_URL}/api/accounts/email/${email}`, {
      headers: this.getHeaders(),
    });
  }

  getAccountById(id: number): Observable<any> {
    return this.http.get(`${ApiService.BASE_URL}/api/accounts/id/${id}`, {
      headers: this.getHeaders(),
    });
  }

  // Transaction APIs
  debitAccount(accountId: number, amount: number): Observable<any> {
    return this.http.post(
      `${ApiService.BASE_URL}/api/transactions/debit/${accountId}`,
      null,
      {
        headers: this.getHeaders(),
        params: { amount: amount.toString() },
      }
    );
  }

  creditAccount(accountId: number, amount: number): Observable<any> {
    return this.http.post(
      `${ApiService.BASE_URL}/api/transactions/credit/${accountId}`,
      null,
      {
        headers: this.getHeaders(),
        params: { amount: amount.toString() },
      }
    );
  }

  getTransactionHistory(accountId: any): Observable<any> {
    return this.http.get(
      `${ApiService.BASE_URL}/api/transactions/my-transactions/`,
      accountId
    );
  }

  getSpecificTransaction(transactionId: number): Observable<any> {
    return this.http.get(
      `${ApiService.BASE_URL}/api/transactions/${transactionId}`,
      {
        headers: this.getHeaders(),
      }
    );
  }

  // Admin APIs
  activateAccount(userId: number): Observable<any> {
    return this.http.post(
      `${ApiService.BASE_URL}/api/admin/activate/${userId}`,
      {},
      {
        headers: this.getHeaders(),
      }
    );
  }

  deactivateAccount(userId: number): Observable<any> {
    return this.http.post(
      `${ApiService.BASE_URL}/api/admin/deactivate/${userId}`,
      {},
      {
        headers: this.getHeaders(),
      }
    );
  }

  deleteAccount(userId: number): Observable<any> {
    return this.http.delete(
      `${ApiService.BASE_URL}/api/admin/delete/${userId}`,
      {
        headers: this.getHeaders(),
      }
    );
  }

  createAdminUser(adminData: any): Observable<any> {
    return this.http.post(
      `${ApiService.BASE_URL}/api/admin/users/create-admin`,
      adminData,
      {
        headers: this.getHeaders(),
      }
    );
  }

  createStaffUser(staffData: any): Observable<any> {
    return this.http.post(
      `${ApiService.BASE_URL}/api/admin/users/create-staff`,
      staffData,
      {
        headers: this.getHeaders(),
      }
    );
  }
}
