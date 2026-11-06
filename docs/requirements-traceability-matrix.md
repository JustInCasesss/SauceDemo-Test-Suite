# Requirements Traceability Matrix (RTM)

Maps each test case to its source requirement (SR). Every requirement is covered by at least one test; every test traces back to a requirement.

| Requirement ID | Requirement | Test Case(s) | Automation Coverage |
|----------------|-------------|--------------|---------------------|
| SR-01 | Login with valid credentials grants access to inventory | TC-01 | `loginWithValidCredentials` |
| SR-02 | Locked-out users cannot log in, with clear error | TC-02 | `loginWithLockedOutUser` |
| SR-03 | Empty credentials show validation errors | TC-03 | (manual) |
| SR-04 | Inventory lists all products with details | TC-04 | `verifyProductListing` |
| SR-05 | Adding a product to cart updates the badge | TC-05 | `completePurchaseFlow` (step add-to-cart) |
| SR-06 | Cart lists added items and allows checkout | TC-06 | `completePurchaseFlow` |
| SR-07 | Complete purchase flow ends with confirmation | TC-07 | `completePurchaseFlow` |
| SR-08 | Checkout validates required fields | TC-08 | (manual) |
| SR-09 | Invalid password rejected with clear message | TC-09 | (manual) |
| SR-10 | REST API contract: status codes and schema | (Postman collection) | `postman/SauceDemo_API_Tests.postman_collection.json` |

## Coverage summary
- Requirements covered by automation: SR-01, 02, 04, 05, 06, 07 (6/9 UI + API)
- Requirements covered by manual tests: all 9 UI requirements
- API contract tests: 4 Postman cases (200/201/404 + schema integrity)
