# MealApp (or <YOUR_PROJECT_NAME>)

A brief description of your project. For example:
MealApp is an Android application built to demonstrate fetching and displaying meal recipes from an external API, following modern Android development practices and a Clean Architecture pattern.

## Features

*   Search Meals by Name

## Project Architecture: Clean Architecture

This project implements the principles of Clean Architecture to create a separation of concerns, making the codebase scalable, maintainable, and robustly testable. The architecture is divided into three main layers: Presentation, Domain, and Data.

### 1. Presentation Layer

*   **Purpose:** Handles all UI and user interaction logic. It's responsible for displaying data to the user and capturing user input. This layer is built using Jetpack Compose.
*   **Key Responsibilities & Components:**
    *   **UI (Composables):** Screens and UI elements built with Jetpack Compose (e.g., `MealListScreen.kt`, `MealDetailScreen.kt`, `MealItem.kt`). These observe state from ViewModels.
    *   **ViewModels:** Prepare and manage data for the UI. They execute Use Cases from the Domain layer and expose UI state (often using `StateFlow` or `LiveData`) to the Composables. They are lifecycle-aware and survive configuration changes. (e.g., `MealsViewModel.kt`, `MealDetailsViewModel.kt`).
    *   **UI State:** Data classes or sealed classes representing the information to be displayed on the screen, including loading states, error states, and the actual data.
    *   **Navigation:** Managed by Jetpack Navigation Compose, defining how users move between different screens.

### 2. Domain Layer

*   **Purpose:** Contains the core business logic of the application (the "what the app does"). This layer is the most independent part of the application and should not have any dependencies on Android frameworks or the Data/Presentation layers (except for its own defined interfaces).
*   **Key Responsibilities & Components:**
    *   **Entities/Models:** Plain Kotlin data classes representing the core business objects (e.g., `Meal.kt`, `Category.kt`). These are independent of any specific data source or UI representation.
    *   **Use Cases (Interactors):** Encapsulate specific business rules or operations. Each use case typically represents a single user interaction or system action (e.g., `GetMealsByCategoryUseCase.kt`, `GetMealDetailsUseCase.kt`). They orchestrate the flow of data by interacting with Repository interfaces.
    *   **Repository Interfaces:** Define contracts (abstractions) for how data is accessed and managed. These interfaces are implemented by the Data layer, allowing the Domain layer to be independent of specific data sources. (e.g., `MealRepository.kt`).

### 3. Data Layer

*   **Purpose:** Responsible for providing data to the application. It implements the Repository interfaces defined in the Domain layer and handles the actual data retrieval and storage logic.
*   **Key Responsibilities & Components:**
    *   **Repository Implementations:** Concrete implementations of the Repository interfaces from the Domain layer. They decide whether to fetch data from a remote source, local cache, or a combination. (e.g., `MealRepositoryImpl.kt`).
    *   **Data Sources:**
        *   **Remote Data Sources:** Interact with external APIs (e.g., using Retrofit to fetch meal data from <YOUR_API_DESCRIPTION>, like TheMealDB API).
        *   **Local Data Sources:** Manage local data storage, such as a database (e.g., using Room for caching data - if implemented).
    *   **DTOs (Data Transfer Objects):** Data classes that model the structure of data coming from external sources (like an API). These are typically mapped to/from Domain models within this layer. (e.g., `MealDto.kt`).
    *   **Mappers:** Utility functions or classes responsible for converting data between different representations (e.g., DTOs to Domain models, Domain models to Database entities, etc.).

### Data Flow Example (Simplified)

1.  **UI (Presentation):** User interacts with a Composable (e.g., clicks a category).
2.  **ViewModel (Presentation):** The Composable calls a function in its ViewModel.
3.  **Use Case (Domain):** The ViewModel executes a specific Use Case.
4.  **Repository Interface (Domain):** The Use Case calls a method on a Repository interface.
5.  **Repository Implementation (Data):** The concrete Repository implementation fetches data from a Data Source (e.g., calls an API service).
6.  **Data Source (Data):** The API service returns DTOs.
7.  **Mapping (Data):** DTOs are mapped to Domain models.
8.  **Return Flow:** Data flows back through the Repository -> Use Case -> ViewModel.
9.  **UI Update (Presentation):** The ViewModel updates its UI State, which the Composable observes and re-renders to display the new data.

## Key Technologies Used

*   **Kotlin:** Primary programming language.
*   **Jetpack Compose:** For building the declarative UI.
*   **Coroutines & Flow:** For asynchronous operations and reactive data streams.
*   **ViewModel:** For managing UI-related data in a lifecycle-conscious way.
*   **Navigation Compose:** For in-app navigation.
*   **Retrofit:** For type-safe HTTP requests to the API.
*   **<Gson:** For JSON parsing.
*   **<Coil>:** For image loading.
*   **<Koin>:** For dependency injection.

