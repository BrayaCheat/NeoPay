# 🧠 Business Rules – Mini Bank System

## 👤 User & Role

- A **User** must be assigned a **Role** (`CUSTOMER`, `ADMIN`, etc.).
- Each user must have a **unique email address**.
- A user can have **multiple Accounts**.

## 💼 Account

- Each **Account** belongs to exactly **one User**.
- Account must have a **unique account number**.
- Account must have an **account type** (e.g., `SAVINGS`, `CHECKING`).
- Each Account must have a **status** (`ACTIVE`, `BLOCKED`, `SUSPENDED`) defined via `AccountStatus`.
- An Account can have **multiple Cards**.

## 💳 Card

- A **Card** is associated with an **Account**.
- A Card must have a **type** (e.g., `DEBIT`, `CREDIT`) from `CardType`.
- A Card must also have a **status** (`ACTIVE`, `EXPIRED`, `BLOCKED`) from `CardStatus`.
- Each Card must have:
    - a **unique card number**
    - a **CVV**
    - an **expiry date**

## 🔁 Transaction

- A **Transaction** is a **transfer of funds** between two Accounts.
- Requires a **TransactionType** (`DEPOSIT`, `WITHDRAW`, `TRANSFER`).
- Transaction **amount must be positive**.
- May include an optional **description**.
- Tracks a **timestamp** for logging and auditing.

## 📝 Audit Log

- Logs **sensitive or critical user/system actions** (e.g., login, transfer, account updates).
- Each log stores:
    - The **user** who performed the action
    - The **action type**
    - The **target type** (e.g., `Account`, `Transaction`)
    - The **target ID**
    - The **timestamp** of the event
