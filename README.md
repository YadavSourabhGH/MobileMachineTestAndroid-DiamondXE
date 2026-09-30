# Mobile Machine Test — Product Listing App (Android)

Simple Android app with **Login → Product Listing → Product Details** flow, built with Kotlin + Jetpack Compose.

## Submission Details

- **Candidate:** Sourabh Yadav
- **Platform:** Android
- **Technology:** Kotlin, Jetpack Compose, Retrofit
- **Development time:** 2 hours
- **GitHub repository:** https://github.com/YadavSourabhGH/MobileMachineTestAndroid-DiamondXE
- **APK download:** https://github.com/YadavSourabhGH/MobileMachineTestAndroid-DiamondXE/releases/latest

## Technology Used

- Kotlin
- Android Studio
- Jetpack Compose (Material 3) + Navigation Compose
- Retrofit + OkHttp + Gson (REST API)
- Coil (image loading)
- Coroutines + ViewModel + StateFlow
- REST API: `https://fakestoreapi.com/products`

## Requirements

- Android Studio: Ladybug (2024.2.1) or newer / Koala+ recommended (any reasonably current stable version works)
- Android Gradle Plugin: 8.5.2 (configured via Gradle wrapper — no manual install needed)
- Gradle: 8.7 (via included `gradlew` wrapper)
- JDK: 17 (set `JAVA_HOME` to a JDK 17 install to build from terminal)
- SDK: `compileSdk 34`, `targetSdk 34`, `minSdk 24`
- Internet access (app fetches products from FakeStore API at runtime; first Gradle sync also downloads dependencies)

## How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/YadavSourabhGH/MobileMachineTestAndroid-DiamondXE.git
   cd MobileMachineTestAndroid-DiamondXE
   ```
2. Open the project in Android Studio (open the root folder containing `settings.gradle.kts`).
3. Let Gradle sync automatically (dependencies resolve from Maven Central/Google — no manual setup).
4. Build the project: **Build > Make Project** (or `./gradlew assembleDebug` from terminal with JDK 17).
5. Run the application: select an emulator (API 24+) or a physical device and press **Run ▶**.

Terminal build:

```bash
export JAVA_HOME=<path-to-jdk-17>
./gradlew assembleDebug
# APK: app/build/outputs/apk/debug/app-debug.apk
```

No API keys, local files, environment variables, or extra configuration are required.

## Test Credentials (mock login)

- Email: `test@example.com`
- Password: `password123`

Any other combination shows `Invalid email or password.` Empty/invalid email and empty/short password show field-level validation errors and block navigation.

## API Used

- Product list: `GET https://fakestoreapi.com/products`
- Product details: `GET https://fakestoreapi.com/products/{id}`

## Features

- Login validation (required fields, email format, mock auth, error messages)
- Product list (image, title, category, price) with loading / error + retry / empty states
- Product details (image, title, description, category, price, rating) with back navigation and retry
- Network errors mapped to: `Unable to connect to the server. Please check your internet connection and try again.`
- Empty list mapped to: `No products available.`

## Project Structure

```
app/src/main/java/com/example/productapp/
  MainActivity.kt
  data/model/Product.kt
  data/remote/FakeStoreApi.kt
  data/remote/RetrofitInstance.kt
  data/repository/ProductRepository.kt
  ui/login/LoginScreen.kt
  ui/login/LoginValidator.kt
  ui/products/ProductListScreen.kt
  ui/products/ProductListViewModel.kt
  ui/details/ProductDetailsScreen.kt
  ui/details/ProductDetailsViewModel.kt
  ui/navigation/AppNavHost.kt
  ui/theme/Theme.kt
```

## Known Limitations

- Login is a local mock (no real backend / session persistence).
- No pagination, search, or offline cache — out of scope for the test.
- Prices shown in USD (`$`) as returned by the API.
