# Manual & Automation Test Suite — SauceDemo

A complete, working QA test suite for the [SauceDemo](https://www.saucedemo.com) e-commerce app. It covers manual test cases, automated Selenium regression coverage (Page Object Model), REST API validation in Postman, and a requirements traceability matrix — all version-controlled on GitHub.

## What's inside

| Part | Location | What it does |
|------|----------|--------------|
| Manual test cases | `docs/manual-test-cases.md` | 9 functional cases (positive/negative/edge) with steps, expected results, priority |
| Automated UI tests | `src/test/java/.../SauceDemoTests.java` | Selenium WebDriver (Java) + TestNG, POM-based regression suite |
| Page Objects | `src/main/java/com/saucedemo/pages/` | LoginPage, InventoryPage, CartPage, CheckoutPage |
| API validation | `postman/SauceDemo_API_Tests.postman_collection.json` | Postman collection asserting status codes, schemas, JSON integrity |
| Traceability | `docs/requirements-traceability-matrix.md` | RTM mapping requirements to test cases + automation coverage |
| CI/CD | `.github/workflows/ci.yml` | Runs the suite on GitHub Actions on every push, uploads report |
| Reporting | ExtentReports + Log4j2 | HTML test report + structured logs |

## Tech stack
Selenium WebDriver 4, Java 11, TestNG 7, Maven, ExtentReports, Log4j2, Apache POI, Postman/Newman, GitHub Actions.

## Run locally
```bash
mvn clean test
```

Run on a specific browser: `mvn test -Dbrowser=firefox` / `-Dbrowser=edge`.

Results: open `reports/ExtentReport.html`; logs in `logs/automation.log`.

## Run the Postman collection
Import `postman/SauceDemo_API_Tests.postman_collection.json` into Postman, or run headless with Newman:
```bash
npx newman run postman/SauceDemo_API_Tests.postman_collection.json
```

## CI/CD
Pushing to `main` triggers the workflow: checkout → JDK 11 → Chrome install → `mvn test` → artifacts (ExtentReport + logs).

## Security & hygiene
- No credentials in code (config externalized in `config.properties`, browser passed via system property).
- Locators encapsulated in Page Objects.
- No untrusted inputs interpolated into shell commands in CI.
