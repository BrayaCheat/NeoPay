# 🏦 Mini Bank System – Feature Overview

## ✅ All Features

### 🔐 Authentication & Authorization
- User Registration
- User Login (JWT)
- Role-Based Access Control (e.g., `ADMIN`, `CUSTOMER`)

---

### 👤 User Management
- Create / Update / Delete Users *(Admin only)*
- View User Profile
- Block User
- Change Password
- Forgot Password

---

### 💼 Account Management
- Create Bank Account
- View Account Details
- Update Account Status (`ACTIVE`, `BLOCKED`, `SUSPENDED`)
- List All Accounts Belonging to a User

---

### 💳 Card Management
- Issue Card to an Account
- View Card Information
- Block / Expire Card

---

### 🔁 Transaction Management
- Deposit / Withdraw / Transfer Funds
- View Transaction History per Account
- Validate Account Status and Balance
- Prevent Overdraft / Negative Balances

---

### 📝 Audit Logging
- Record Sensitive Actions (e.g., Login, Transfer, Account Changes)
- Track: User, Action Type, Target Type, Target ID, Timestamp

---

### ⚙️ System Setup & Configuration
- Create / Update:
    - Roles
    - Card Types & Statuses
    - Account Statuses
    - Transaction Types

---

## 🧱 Core Features (Implement First)

These are essential for the system to work:

1. **🔐 Authentication & Authorization**
    - Secure access and control user roles

2. **💼 Account Management**
    - Fundamental unit that links users and handles funds

3. **🔁 Transaction Management**
    - Core functionality for any banking system

4. **👤 Basic User Management**
    - User registration, profile, and role assignment

---

Once core features are stable, you can expand to:

- 💳 Card Features (Issue, Block, Expire)
- 📝 Audit Logs for accountability
- 📊 Admin Dashboards, Notifications, and Reports
