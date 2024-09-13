# bankdemo
Spring Boot app to support a web application that allows an user to view transactions on any of the accounts they hold.

# Requirement Assumptions
- All Transactions in a Account of a particular Currency Type will be of the same Currency Type
- Users can query Transactions for a particular account (or) across all Accounts


# Technical decisions
- We are not depending on Database to generate unique identifiers for Account and Transaction entities

# REST endpoints
- Account List: GET http://localhost:8080/accounts
- Account Transaction List: GET http://localhost:8080/account/585309209/transactions

# Entities
- User
- Account
- Transaction

