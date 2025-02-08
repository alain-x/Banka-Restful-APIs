import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class AccountService {
  private apiUrl = 'http://localhost:8090/api/accounts';

  constructor(private http: HttpClient) {}

  createAccount(email: string, accountType: string): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    const body = { email, accountType };
    return this.http.post(`${this.apiUrl}/create`, body, { headers });
  }

  getAllAccounts(): Observable<any> {
    return this.http.get(`${this.apiUrl}/`);
  }

  getAccountByEmail(email: string): Observable<any> {
    return this.http.get(`${this.apiUrl}/email/${email}`);
  }

  getAccountById(id: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/id/${id}`);
  }
}
