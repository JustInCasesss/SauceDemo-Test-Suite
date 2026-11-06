# Manual Test Cases — SauceDemo

Functional test cases for https://www.saucedemo.com. Each case lists preconditions, steps, expected result, and priority. Logged defects use severity/priority and reproduction steps.

## TC-01: Login with valid credentials
- **Priority:** High | **Type:** Positive
- **Preconditions:** None (public site).
- **Steps:**
  1. Open https://www.saucedemo.com.
  2. Enter username `standard_user`.
  3. Enter password `secret_sauce`.
  4. Click **LOGIN**.
- **Expected:** Redirected to inventory page; URL contains `inventory.html`; product list is visible.
- **Result:** Pass.

## TC-02: Login with locked-out user
- **Priority:** High | **Type:** Negative
- **Steps:**
  1. Open the login page.
  2. Enter username `locked_out_user`, password `secret_sauce`.
  3. Click **LOGIN**.
- **Expected:** Error banner shown: "Sorry, this user has been locked out." User stays on login page.
- **Result:** Pass (defect logged if error text absent).

## TC-03: Login with empty credentials
- **Priority:** Medium | **Type:** Negative
- **Steps:** Click **LOGIN** without entering credentials.
- **Expected:** Error: "Username is required."
- **Result:** Pass.

## TC-04: Verify product listing loads
- **Priority:** High | **Type:** Positive
- **Steps:** Login as `standard_user`, observe inventory page.
- **Expected:** At least 6 products displayed, each with name, description, price, and Add to Cart button.
- **Result:** Pass.

## TC-05: Add item to cart updates badge
- **Priority:** High | **Type:** Positive
- **Steps:** On inventory page, click **Add to cart** on the first product.
- **Expected:** Cart badge shows `1`.
- **Result:** Pass.

## TC-06: Cart reflects added item
- **Priority:** High | **Type:** Positive
- **Steps:** Add an item, then click the cart icon.
- **Expected:** Cart page lists exactly 1 item; Checkout button enabled.
- **Result:** Pass.

## TC-07: Complete checkout flow
- **Priority:** High | **Type:** Positive
- **Steps:**
  1. Add item, open cart, click **Checkout**.
  2. Fill First Name, Last Name, Postal Code.
  3. Click **Continue**, then **Finish**.
- **Expected:** Order-complete page shows "Thank you for your order!".
- **Result:** Pass.

## TC-08: Checkout with empty required fields
- **Priority:** Medium | **Type:** Negative
- **Steps:** Go to checkout, leave all fields blank, click **Continue**.
- **Expected:** Error: "First Name is required."
- **Result:** Pass.

## TC-09: Verify error message for wrong password
- **Priority:** Medium | **Type:** Negative
- **Steps:** Login with correct username, wrong password.
- **Expected:** Error: "Username and password do not match any user in this service."
- **Result:** Pass.
