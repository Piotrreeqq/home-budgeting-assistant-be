# Home budget assistant app

Application handles operations on 4 pre-defined registers (Wallet, Savings, Insurance policy and Food expenses)
A register can be recharged with a given amount. Balance can be transferred between registers.

Initial setup is defined and happens during application launch. If a register is not found,
it is created with initial balance. Wallet and Savings start with 1000 and 5000 respectively. The rest of registers starts with 0.



## Test:
```bash
mvn test
```
### Compile:
```bash
mvn clean install
```
#### Run:
```bash
cd target
```

```bash
java -jar budgeting-0.0.1-SNAPSHOT.jar
```
## API Docs & Tryout:

For Tryout and Api DOCs visit: (Sample data is available for userId: "1")


[Try Online (Heruko)](https://home-budgeting-assistant-be.herokuapp.com/swagger-ui.html#/)

```bash
http://localhost:8080/swagger-ui.html)
```

## API Endpoints

```bash
/api/budget/{userId}/registry
	
Response body:
[
  {
    "id": "1",
    "label": "Wallet",
    "amount": 1000
  },
  {
    "id": "2",
    "label": "Savings",
    "amount": 5000
  },
  {
    "id": "3",
    "label": "Insurance Policy",
    "amount": 0
  },
  {
    "id": "4",
    "label": "Food Expenses",
    "amount": 0
  }
]
```

```bash
/api/budget/{userId}/registry/{registryId}/recharge
JSON:
{
  "amount": 0
}

```

```bash
/api/budget/{userId}/registry/{registryId}/transfer
JSON:
{
  "amount": 0,
  "targetRegistryId": "string"
}

```
