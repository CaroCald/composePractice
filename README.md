# PracticeCompose - Optimized Android App

A modern Android application built with Jetpack Compose, following Clean Architecture principles and optimized for maintainability and scalability.

## 🏗️ Architecture Overview

This project follows **Clean Architecture** with a modular structure optimized for:

- **Separation of Concerns**: Clear boundaries between UI, business logic, and data layers
- **Testability**: Each layer can be tested independently
- **Maintainability**: Organized code structure for easy navigation and updates
- **Scalability**: Modular design allows for easy feature additions

## 📁 Project Structure

```
app/src/main/java/com/example/practicecompose/
├── data/                           # Data Layer
│   ├── local/                      # Local data sources
│   │   ├── managers/               # Data managers
│   │   └── preferences/            # Encrypted preferences
│   └── remote/                     # Remote data sources
│       ├── constants/              # API constants
│       ├── impl/                   # Service implementations
│       ├── interceptors/           # Network interceptors
│       ├── models/                 # Data models
│       ├── repositories/           # Repository implementations
│       └── services/               # API service interfaces
├── domain/                         # Domain Layer
│   ├── commons/                    # Shared components
│   │   ├── components/             # Reusable UI components
│   │   ├── modules/                # Dependency injection modules
│   │   └── utils/                  # Utility classes
│   ├── entities/                   # Domain entities
│   │   └── generics/               # Generic base classes
│   └── use_cases/                  # Business logic
│       ├── api/                    # API-related use cases
│       └── forms/                  # Form validation use cases
├── features/                       # Feature modules
│   ├── auth/                       # Authentication feature
│   │   ├── login/                  # Login screen and logic
│   │   └── profile/                # Profile screen and logic
│   ├── home/                       # Home screen
│   ├── movies/                     # Movies feature
│   │   ├── movieDetail/            # Movie detail screen
│   │   └── moviesList/             # Movie list screen
│   ├── splash/                     # Splash screen
│   └── welcome/                    # Welcome screen
├── navigation/                     # Navigation components
├── ui/                            # UI theme and styling
├── MainActivity.kt                # Main activity
└── MainApplication.kt             # Application class
```

## 🎨 Design System

### Custom Color Palette
- **Primary**: Orange Accent (#F29559) - For primary actions and highlights
- **Secondary**: Cream Yellow (#F2D492) - For secondary elements
- **Tertiary**: Warm Beige (#B8B08D) - For tertiary elements and outlines
- **Background**: Dark Blue (#202C39) - Main background color
- **Surface**: Medium Blue (#283845) - Surface elements

### Typography
Complete Material Design 3 typography system with proper hierarchy and spacing.

## 🚀 Key Features

- **Firebase Authentication** - Secure user authentication
- **Movie API Integration** - Fetch and display movie data
- **Hilt Dependency Injection** - Clean dependency management
- **State Management** - Optimized state handling with StateFlow
- **Navigation** - Type-safe navigation with Compose Navigation
- **Encrypted Preferences** - Secure local data storage
- **Custom UI Components** - Reusable, themed components

## 🛠️ Technology Stack

- **UI**: Jetpack Compose
- **Architecture**: Clean Architecture + MVVM
- **Dependency Injection**: Hilt
- **Networking**: Retrofit + OkHttp
- **Image Loading**: Coil
- **Authentication**: Firebase Auth
- **Local Storage**: Encrypted SharedPreferences
- **Navigation**: Compose Navigation
- **State Management**: StateFlow + Coroutines

## 📱 Screens

1. **Splash Screen** - App initialization
2. **Welcome Screen** - App introduction
3. **Login Screen** - User authentication
4. **Movie List Screen** - Browse movies
5. **Movie Detail Screen** - Movie information
6. **Profile Screen** - User profile management
7. **Home Screen** - Main dashboard

## 🔧 Optimization Highlights

### State Management
- Removed `@Composable` functions from ViewModels
- Implemented proper state flow patterns
- Created utility classes for state processing

### Component Architecture
- Modular UI components with consistent theming
- Reusable components with proper accessibility
- Clean separation between UI and business logic

### Performance
- Optimized build configuration
- Efficient dependency management
- Proper memory management with StateFlow

## 🧪 Testing

The project is structured to support:
- Unit tests for business logic
- UI tests for components
- Integration tests for features

## 📦 Dependencies

All dependencies are managed through the version catalog in `gradle/libs.versions.toml` for consistent versioning across the project.

## 🚀 Getting Started

1. Clone the repository
2. Open in Android Studio
3. Sync Gradle files
4. Run the application

## 📄 License

This project is for educational purposes and demonstrates modern Android development practices.
  
