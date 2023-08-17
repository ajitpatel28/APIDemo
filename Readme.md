# API Demo App

This is a simple Android application that demonstrates fetching data from a web service, storing it in a local Room database, and displaying it in a RecyclerView using Room Paging library. The app also uses Moshi for JSON parsing.

## Features

- Fetches data from a web service and stores it in a local Room database.
- Implements lazy loading pagination using Room Paging library to display data in a RecyclerView.
- Supports pull-to-refresh functionality to update data from the web service.
- Uses Moshi for efficient JSON parsing.
- Uses the Android Architecture Components and Hilt for dependency injection.
- Implements one-way data binding using ViewModels and data binding.

## Architecture

The app follows the MVVM (Model-View-ViewModel) architecture pattern:

- **Model**: The repository handles data operations. It fetches data from the web service, stores it in Room database, and provides data to the ViewModel.
- **View**: The Activity is responsible for displaying UI components. It binds UI elements to the ViewModel using data binding.
- **ViewModel**: The ViewModel manages the UI-related data and logic. It exposes LiveData and Flow for the UI to observe.

## Getting Started

1. Clone this repository to your local machine.
2. Open the project in Android Studio.
3. Build and run the app on an emulator or a physical device.

## Dependencies

The app uses the following libraries and components:

- Android Architecture Components (ViewModel, LiveData, Room)
- Hilt for dependency injection
- Retrofit and OkHttp for networking
- Room Paging library for lazy loading pagination
- Moshi for efficient JSON parsing
- Data binding for one-way data binding
- Coroutines for asynchronous programming

## Usage

1. Launch the app on your device/emulator.
2. The app will fetch data from the web service and store it locally.
3. The RecyclerView will display the fetched data with lazy loading pagination.
4. Swipe down to refresh the data. New data will be fetched and stored in the local database.
5. The SwipeRefreshLayout's progress bar will indicate when data is being refreshed.

## Contributing

If you would like to contribute to this project, you can:

- Fork the repository.
- Create a new branch for your changes.
- Make your changes and commit them.
- Push your changes to your fork.
- Create a pull request to the main repository.
