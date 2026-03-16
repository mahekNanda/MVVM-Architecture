# 🛍️ MVVM Product Listing App (Android)

An Android application built using **MVVM Architecture** that fetches product data from the **Fake Store API** and displays it in a clean RecyclerView interface. Users can view product details, see images and descriptions, and simulate adding items to a cart.

---

## 🚀 Features

* MVVM Architecture
* Retrofit API integration
* Coroutines for background operations
* RecyclerView with DiffUtil
* Navigation Component
* Glide for image loading
* Detail screen with product information
* Add to Cart button
* Back navigation
* Sealed UI State management

  * Loading
  * Success
  * Error
* Progress indicator while fetching data

---

## 🌐 API Used

Fake Store API

https://fakestoreapi.com/products

Example Response:

```
{
  "id": 1,
  "title": "Fjallraven Backpack",
  "price": 109.95,
  "description": "Your perfect pack for everyday use...",
  "image": "https://fakestoreapi.com/img/..."
}
```

---

## 🏗️ Architecture

This project follows **MVVM (Model-View-ViewModel)** architecture.

```
UI (Fragments)
     ↓
ViewModel
     ↓
Repository
     ↓
Retrofit API Service
     ↓
Fake Store API
```

### Layer Explanation

**UI Layer**

* HomeFragment
* DetailFragment
* Displays data and observes ViewModel state

**ViewModel Layer**

* Manages UI state
* Handles coroutine calls

**Repository Layer**

* Fetches data from API
* Handles data operations

**Network Layer**

* Retrofit configuration
* API interface

---

## 📁 Project Structure

```
com.example.itemlist3

adapter
 ├── ProductAdapter.kt
 └── ProductDiffUtil.kt

data
 └── ProductRepository.kt

model
 ├── Product.kt
 └── UiState.kt

network
 ├── ProductApiService.kt
 └── RetrofitInstance.kt

ui
 ├── HomeFragment.kt
 └── DetailFragment.kt

viewmodel
 └── ProductViewModel.kt
```

---

## 📱 Screens

### Product List Screen

Displays all products retrieved from the Fake Store API.

* Image
* Product title
* Product price

### Product Detail Screen

Displays detailed product information.

* Product image
* Product title
* Product price
* Product description

Buttons:

* **Add to Cart**
* **Back**

---

## 🔄 UI State Handling

The app uses a **Sealed UI State** for better state management.

```
sealed class UiState {

    object Loading : UiState()

    data class Success(val data: List<Product>) : UiState()

    data class Error(val message: String) : UiState()
}
```

This helps manage:

* API loading state
* Successful data retrieval
* Error handling

---

## 📸 Screenshots

Add your screenshots here.

Example:

### Product List

### Product Detail

---

## 🛠️ Libraries Used

* Retrofit
* Gson Converter
* Glide
* Coroutines
* Navigation Component
* Lifecycle ViewModel
* RecyclerView

---

## 📦 Dependencies

```
Retrofit
Glide
Navigation Component
Lifecycle ViewModel
RecyclerView
```

---

## ▶️ How to Run

1. Clone the repository

```
git clone https://github.com/mahekNanda/MVVM-Architecture.git
```

2. Open in **Android Studio**

3. Sync Gradle

4. Run the app on an emulator or device

---

## 🎯 Learning Objectives

This project demonstrates:

* Clean architecture in Android
* API integration using Retrofit
* MVVM implementation
* UI state management
* RecyclerView optimization
* Navigation between fragments

---

## 👨‍💻 Author

Mahek Nanda

---
