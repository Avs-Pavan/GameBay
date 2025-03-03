# GameBay Android Project

## Overview

"GameBay" is an Android application designed to display a list of game schedules for various seasons. It emphasizes modularity, scalability, and a superior user experience. The app leverages modern Android architecture and tools, including:

* **Room:** For local data caching and persistence.
* **Retrofit:** For network requests to fetch game schedule data.
* **Hilt:** For dependency injection, managing object creation and lifecycle.
* **Coil:** For efficient image loading (e.g., team logos).
* **RapidQA:** For Network Tracing and exporting network logs as Text and Postman Collection. [RapidQA](https://github.com/Avs-Pavan/RapidQA)
* **Kotlin:** As the primary programming language, ensuring clean and safe code.
* **MVVM (Model-View-ViewModel):** For clear separation of UI logic and business logic.
* **Clean Architecture:** For a maintainable and testable codebase.
* **Offline-First Approach:** Prioritizing local data and syncing with remote data when available.
* **BaseLine Profile:** To increase the application's performance by creating builds with AOT-compiled ahead of time, reducing startup latency and runtime overhead.
* **BaseLine Profile Results:** `The median startup time is 1.85% faster with None, but the minimum and maximum times are 2.89% and 7.14% slower, respectively, compared to BaselineProfiles.` 

The application is structured into modular components (App, Core, and Feature/GameSchedule) to ensure separation of concerns, reusability, and maintainability.


## Screenshots/Videos

<img src="https://drive.google.com/uc?id=11vvShtA-_asjYSsdwPIRkApaxtW1yhHA" height="350">  <img src="https://drive.google.com/uc?id=1sqWpsl6tLL5vCiqdB_dDe08bFisVDTf8" height="350">  <img src="https://drive.google.com/uc?id=1m0PNahfFzxmMBiS4QwjzA5DSdnMbZCse" height="350">   <img src="https://drive.google.com/uc?id=1Bh0gv89JgIzGBTULgMapXk2q6-OMjZR_" height="350">
[![IMAGE ALT TEXT HERE](https://img.youtube.com/vi/CcWcZpLdRY8/0.jpg)](https://www.youtube.com/watch?v=CcWcZpLdRY8)

## BaseLine Profile Images:

#### Baseline Profile - Start-up
<img src="https://drive.google.com/uc?id=1SQApMfjgElOJBo7qMgjklBFv7LAyQPKZ" height="350">

#### BaseLine Profile - Scrolling
<img src="https://drive.google.com/uc?id=12-f46s3bIWIWkkRQeO0DHmV8oPI2VGF2" height="350">


## Detailed Module Descriptions

## Simple App Architecture

```
GameBay/
├── app
└── core
    ├── data
    ├── domain
    └── presentation/designsystem
└── feature/gameschedule
    ├── data
    ├── domain
    └── presentation
```


## Detailed file structure:
```
GameBay/
├── app/
│   ├── GameBayApplication.kt
│   └── MainActivity.kt
├── core/
│   ├── data/
│   │   ├── SafeNetwork.kt
│   │   ├── AppModule.kt
│   │   └── NetworkModule.kt
│   ├── domain/
│   │   ├── CoreDomainConstants.kt
│   │   ├── IMapper.kt
│   │   ├── MError.kt
│   │   ├── Qualifiers.kt
│   │   ├── Result.kt
│   │   ├── ValidationBlock.kt
│   │   └── ValidationContext.kt
│   └── presentation/designsystem/
│       ├── ui/
│       │   ├── components/
│       │   │   ├── LottieUI.kt
│       │   │   ├── NetworkImage.kt
│       │   │   ├── Qualifiers.kt
│       │   │   ├── UIState.kt
│       │   │   └── UIText.kt
│       │   └── theme/
│       │       ├── Color.kt
│       │       ├── Theme.kt
│       │       └── Type.kt
└── feature/gameschedule/
├── data/
│   ├── GameScheduleBindModule.kt
│   ├── GameScheduleModule.kt
│   ├── UseCaseModule.kt
│   ├── local/
│   │   ├── dao/
│   │   │   └── GameScheduleDao.kt
│   │   ├── entities/
│   │   │   ├── GameEntity.kt
│   │   │   ├── GameSectionEntity.kt
│   │   │   ├── Relations.kt
│   │   │   ├── ScheduleEntity.kt
│   │   │   └── TeamEntity.kt
│   │   └── mapper/
│   │       └── GameScheduleRoomMapper.kt
│   ├── remote/
│   │   ├── api/
│   │   │   └── GameScheduleAPI.kt
│   │   ├── datasource/
│   │   │   └── GameScheduleRemoteDataSource.kt
│   │   ├── mapper/
│   │   │   └── GameScheduleRemoteDataMapper.kt
│   │   └── model/
│   │       ├── GameResponse.kt
│   │       ├── GameSectionResponse.kt
│   │       ├── MDateResponse.kt
│   │       ├── ScheduleResponse.kt
│   │       └── TeamResponse.kt
│   ├── repo/
│   │   └── GameScheduleRepo.kt
│   └── room/
│       └── GameScheduleDatabase.kt
├── domain/
│   ├── Game.kt
│   ├── GameDate.kt
│   ├── GameOutcome.kt
│   ├── GameSection.kt
│   ├── GameType.kt
│   ├── Schedule.kt
│   ├── Team.kt
│   ├── IGameScheduleRepo.kt
│   ├── GetGameScheduleUseCase.kt
│   └── RefreshGameScheduleUseCase.kt
└── presentation/
├── GameScheduleScreen.kt
├── GameScheduleUIMappings.kt
├── GameScheduleUIState.kt
├── GameScheduleViewModel.kt
├── PreviewConstants.kt
├── game/
│   ├── GameCardUI.kt
│   ├── GameCardUIEvents.kt
│   ├── GameUIModel.kt
│   └── TeamUIModel.kt
├── gamesection/
│   ├── GameSectionUI.kt
│   └── GameSectionUIModel.kt
└── toolbar/
├── AppToolBarUI.kt
└── AppToolBarUIEvents.kt
```


### App Module

The App module is the application's entry point and runtime environment. It handles:

* Initialization of global application settings.
* Integration with the Core and Feature modules.
* Management of the app's lifecycle and navigation.
* Setting up the Hilt dependency injection framework.
* Coordinating the display of the game schedule feature.
* Uses Coil to load images.
* Starts the main activity.
* Ensures that the app uses cached data from Room before making network requests.

### Core Module

The Core module provides shared functionality across the application. It's divided into Data, Domain, and Presentation/DesignSystem sub-modules.

#### Core/Data

* Handles data-related logic and networking.
* Provides safe network operation utilities using Retrofit.
* Configures dependency injection with Hilt for network and data-related dependencies (e.g., Retrofit services, Room databases).
* Abstracts data access, enabling interaction with local (Room) and remote (Retrofit) data sources.
* Prioritizes local data from Room for offline functionality.

#### Core/Domain

* Defines the application's business logic and rules.
* Includes constants, error handling, data mapping interfaces, Hilt qualifiers, result wrappers, and validation logic.
* Establishes a consistent domain model shared across features.
* Remains independant of data sources and UI.

#### Core/Presentation/DesignSystem

* Provides reusable UI components and a cohesive design system.
* Includes components for animations (Lottie), image loading (Coil), UI state management, text rendering, and theme configurations.
* Ensures a consistent look and feel across the application.
* Provides reusable UI components.

### Feature Module: GameSchedule

The Feature/GameSchedule module is dedicated to displaying and managing game schedules. It's divided into Data, Domain, and Presentation sub-modules.

#### Feature/GameSchedule/Data

* Manages data operations for game schedules.
* Uses Room for local caching and persistence of game schedules, teams, and seasons.
* Uses Retrofit for fetching remote data from a game schedule API.
* Uses Hilt for dependency injection of Room databases, Retrofit services, and data mappers.
* Provides repository logic to bridge local and remote data sources.
* Handles data transformation and caching strategies.

#### Feature/GameSchedule/Domain

* Defines domain models, business rules, and use cases specific to game schedules.
* Includes domain models for games, dates, outcomes, sections, types, schedules, and teams.
* Provides a repository interface and use cases for fetching and refreshing game schedules.
* Encapsulates the feature's core logic, adhering to the application's domain standards.
* Supports the offline-first approach.

#### Feature/GameSchedule/Presentation

* Manages the user interface and presentation logic for displaying game schedules.
* Includes screens, UI state management, view models, UI mappings, preview constants, and reusable UI components.
* Leverages the design system from Core/Presentation/DesignSystem.
* Uses Coil for loading visual assets.
* Integrates with view models that interact with the domain and data layers.
* Ensures an interactive and responsive user experience.
* Prioritizes offline data, while syncing with remote data.

## GameBay App Functionality

The "GameBay" app's core functionality is straightforward: it displays a list of game schedules. Here's how the architecture and technologies support this:

1.  **Data Fetching:**
    * When the app starts, the `GameScheduleViewModel` in the Presentation layer requests game schedule data from the Domain layer's `GetGameScheduleUseCase`.
    * The `GetGameScheduleUseCase` then interacts with the Data layer's `GameScheduleRepo`.
    * The `GameScheduleRepo` first checks the local Room database. If data is available, it's returned immediately, providing an offline-first experience.
    * Simultaneously, or if local data is unavailable, the `GameScheduleRepo` uses Retrofit to fetch data from the remote API.
    * The fetched data is then mapped to domain models and stored in the Room database for future offline access.

2.  **Data Caching:**
    * Room persists the game schedule data, ensuring that the app can display content even without an internet connection.
    * This caching strategy improves performance by reducing the need for repeated network requests.

3.  **Dependency Injection (Hilt):**
    * Hilt manages the creation and injection of dependencies, such as Retrofit services, Room databases, and repositories.
    * This simplifies the code and improves testability by decoupling components.

4.  **Image Loading (Coil):**
    * Coil efficiently loads and caches images, such as team logos, enhancing the app's visual appeal and performance.

5.  **UI Presentation (MVVM):**
    * The Presentation layer's `GameScheduleScreen` observes the `GameScheduleUIState` from the `GameScheduleViewModel`.
    * The `GameScheduleViewModel` transforms the domain models into UI models and updates the UI state.
    * This separation of concerns ensures that the UI remains responsive and testable.

6.  **Clean Architecture:**
    * The app adheres to Clean Architecture principles, separating the UI, business logic, and data layers.
    * This makes the app maintainable, testable, and adaptable to future changes.

7.  **Offline-First Approach:**
    * The application displays locally stored data first, and then updates the data when a network connection is available. This means that users always have access to their previously viewed data.
