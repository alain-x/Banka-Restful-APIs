# Banka - Restful APIs

**Banka** is a lightweight core banking application designed to streamline banking operations. The app allows users to sign up, create accounts, and view transaction histories. While users can perform online account management, deposits and withdrawals require a visit to the bank branch.

## Features:

### User (Client)
- Sign up and log in
  - http://localhost:8090/auth/register
  - http://localhost:8090/auth/login
- Create a bank account
   - http://localhost:8090/api/accounts/create
     
- View account transaction history
   - http://localhost:8090/api/transactions/history/{userId}
- View specific account transactions
   - http://localhost:8090/api/transactions/{id}

### Staff (Cashier)
- Debit and credit client accounts
   - http://localhost:8090/api/transactions/credit/{id}?amount={value of amount}
   - http://localhost:8090/api/transactions/debit/{id}?amount={value of amount}

### Admin/Staff
- View all user accounts
   - http://localhost:8090/api/accounts/
- View specific user accounts
  
   - http://localhost:8090/api/accounts/email/{email}
  
   - http://localhost:8090/api/accounts/id/{id}
- Activate or deactivate user accounts
   - http://localhost:8090/api/admin/activate/{userId} Activate
   - http://localhost:8090/api/admin/deactivate/{userId} Deactivate
- Delete specific user accounts
   http://localhost:8090/api/admin/delete/{userId}
- Create staff and admin accounts
   - http://localhost:8090/api/users/create-staff
     
