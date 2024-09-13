# Bank Demo Application
Spring Boot app to support a web application that allows an user to view transactions on any of the accounts they hold.

# Requirement Assumptions
- All Transactions in a Account of a particular Currency Type will be of the same Currency Type
- Users can query Transactions for a particular account (or) across all Accounts


# Technical decisions
- We are not depending on Database to generate unique identifiers for Account and Transaction entities

# Entities
- User
- Account
- Transaction

# Frameworks, Technologies, Architectures leveraged
- Spring boot
- Spring boot Web
- REST architecture
- Spring Data JPA
- Hibernate
- H2 Database
- Lombok
- Slf4j
- Mockito
- Junit5

# REST endpoints
- Account List: GET http://localhost:8080/accounts
- Account Transaction List: GET http://localhost:8080/account/585309209/transactions

### Account List
- Account List: GET http://localhost:8080/accounts 200
```json
{
    "status": "SUCCESS",
    "errors": null,
    "pagination": {
        "offset": 0,
        "limit": 10,
        "totalCount": 1
    },
    "accounts": [
        {
            "accountNumber": "585309209",
            "accountName": "SGSavings726",
            "accountType": "SAVINGS",
            "balanceDate": "2018-11-08",
            "currency": "SGD",
            "openingAvailableBalance": 84327.51
        }
    ]
}
```
### Account Transaction List
- Account Transaction List: GET http://localhost:8080/account/585309209/transactions 200
```json
{
    "status": "SUCCESS",
    "errors": null,
    "pagination": {
        "offset": 0,
        "limit": 100,
        "totalCount": 1
    },
    "transactions": [
        {
            "transactionId": "1",
            "accountName": "SGSavings726",
            "accountNumber": "585309209",
            "currency": "SGD",
            "valueDate": "2012-01-02",
            "amount": 9540.98,
            "transactionType": "CREDIT",
            "transactionNarrative": ""
        }
    ]
}
```

- Account Transaction List: GET http://localhost:8080/account/585309209/transactions 401
```json
{
    "status": "ERROR",
    "errors": [
        {
            "errorCode": "ACCOUNT_ACCESS_DENIED",
            "errorMessage": "You do not have permission to access this account.",
            "errorDetail": "User=2 doesn't have permission to access Account=585309209"
        }
    ],
    "pagination": null,
    "transactions": []
}
```
# Postman collection

# Unit Tests
- Unit tests are written, can be executed from Intellij or CLI

# Integration Tests
- TODO: Explore how to leverage H2 in-memory database for Integration testing
