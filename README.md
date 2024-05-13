# Newsify

## Overview
This is a News Application developed in Kotlin and Java, using the Android framework. The application fetches news data from an API and displays it to the user. The user can interact with the application through various features such as viewing detailed news articles, sharing articles, and viewing location data. The application also includes a WebView to display web content within the application. The project uses Gradle for build automation and dependency management.

## Features
- **Viewing News Articles**: Users can view a list of news articles fetched from the API. Each article includes a title, author, date, and content.
- **Detailed Article View**: Users can select an article to view its details. This opens a new activity where the full content of the article is displayed.
- **Sharing Articles**: Users can share articles via other apps. This is done using a share intent.
- **Accessibility and TalkBack Support**: The application supports TalkBack, a Google screen reader included on Android devices. This includes reading out the article's title, content, and other relevant information when the user navigates to them.
- **Location Data**: Users can view location data based on latitude and longitude coordinates. This data is fetched from the OpenWeatherMap Geo API.

## Activities
- **NewsHome**: This is the main activity of the application. It fetches news data based on the selected news type and displays it in a list. Each news type corresponds to a different category such as general, entertainment, health, science, sports, and technology. When a user selects a news article from the list, it launches the `NewsArticleContentActivity` to display the full content of the article.
- **NewsArticleContentActivity**: This activity is responsible for displaying the content of a news article. It fetches the content of the selected news article from a data source, displays it in the user interface, and handles user interactions such as clicks or swipes. It also supports TalkBack, a Google screen reader included on Android devices.
- **LocationHome**: This activity fetches and displays location data based on latitude and longitude coordinates. It uses the OpenWeatherMap Geo API to fetch the data and displays it in a user-friendly format.
- **MyAccessibilityService**: This is an accessibility service that uses TextToSpeech to read out the text from accessibility events. It is designed to help users with visual impairments interact with the application. The service speaks out the text from the event, providing spoken feedback to the user.

## Utility Classes
- **Constant**: This utility class contains constant values used throughout the application, such as API base URLs and API keys. It also contains mutable state values that are used to store and manage state across different parts of the application.

## Code Structure
The code is structured into several packages, each containing related classes. Here are the main packages and their contents:
- `com.mobilecomputing.newsapp.screens.news`: Contains the `NewsHome.kt` and `NewsArticleContentActivity.kt` files, which define the `NewsHome` and `NewsArticleContentActivity` activities respectively.
- `com.mobilecomputing.newsapp.screens.locations`: Contains the `LocationHome.kt` file, which defines the `LocationHome` activity.
- `com.mobilecomputing.newsapp`: Contains the `MyAccessibilityService.kt` file, which defines the `MyAccessibilityService` class.
- `com.mobilecomputing.newsapp.utils`: Contains the `Constant.kt` file, which defines the `Constant` utility class.

## Setup
To set up the project, clone the repository and open it in Android Studio. The project uses Gradle for build automation and dependency management, so all dependencies should be automatically downloaded and set up.

## Contributing
Contributions are welcome! Please read the contributing guidelines before making any changes.

## License
This project is licensed under the terms of the MIT license.
