## ✨ Book Reader - Clean Architeture using MVVM
<p align="center">
  <img src="app/src/main/assets/banner.png">
</p>

## 📖 About
This project uses the [Gutenberg Project](https://www.gutenberg.org/) to fetch data from books.

## 🧑‍💻 Architeture
This project follows the **MVVM** pattern to separate concerns and promote modularity. Key practices include:
- State Management: ViewModels manage UI states efficiently, ensuring responsive and clean UI updates.
- Separation of Concerns: Clear distinction between UI logic, business logic, and data layers.
- Dependency Injection: Hilt handles dependencies across the app, making it easier to test and manage.

## 🔥 Features
- Dependency Injection: Utilizes **Hilt/Dagger** for efficient dependency management and to reduce boilerplate code.
- HTML Parsing: Leverages Jsoup to handle and extract meaningful content from HTML.
- Efficient Image Loading: Uses** Coil** for smooth and optimized image loading.
- API Communication: Using** Ktor** for network requests and responses.
- Navigation: Implements the Jetpack Navigation component for smooth transitions between screens.
