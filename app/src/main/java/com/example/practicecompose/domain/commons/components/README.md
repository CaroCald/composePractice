# Components Documentation

This directory contains reusable UI components that follow Material Design 3 principles and maintain consistency across the application.

## 📁 Component Structure

```
components/
├── alerts/           # Alert dialogs and notifications
├── bottomBar/        # Bottom navigation components
├── buttons/          # Button components
├── input/           # Input field components
├── loading/         # Loading indicators
├── scaffold/        # Scaffold and layout components
├── text/            # Text components
└── toolbar/         # Toolbar components
```

## 🎨 Design Principles

- **Material Design 3**: All components follow Material Design 3 guidelines
- **Consistency**: Uniform styling and behavior across the app
- **Accessibility**: Proper content descriptions and semantic meaning
- **Customization**: Flexible parameters for different use cases
- **Performance**: Optimized for smooth rendering and interactions

## 📱 Component Usage

### Alerts

#### CustomAlertDialog
```kotlin
CustomAlertDialog(
    shouldShowDialog = remember { mutableStateOf(true) },
    onClick = { /* handle action */ },
    message = "Your message here"
)
```

### Buttons

#### PrimaryButton
```kotlin
PrimaryButton(
    text = "Click Me",
    onClick = { /* handle click */ },
    enabled = true,
    modifier = Modifier.fillMaxWidth()
)
```

### Input

#### PrimaryInput
```kotlin
PrimaryInput(
    value = text,
    onValueChange = { text = it },
    placeholder = "Enter text...",
    modifier = Modifier.fillMaxWidth()
)
```

#### CustomSearchBar
```kotlin
CustomSearchBar(
    value = searchQuery,
    onValueChange = { searchQuery = it },
    placeholder = "Search...",
    modifier = Modifier.fillMaxWidth()
)
```

**CustomSearchBar Features:**
- ✅ **Clear Button**: Automatically shows when text is entered
- ✅ **Customizable Icons**: Leading and trailing icons
- ✅ **Placeholder Text**: Configurable placeholder
- ✅ **Theme Integration**: Uses app color scheme
- ✅ **Accessibility**: Proper content descriptions

**CustomSearchBar Parameters:**
- `value`: Current search query
- `onValueChange`: Callback for text changes
- `placeholder`: Placeholder text (default: "Search...")
- `leadingIcon`: Leading icon (default: search icon)
- `trailingIcon`: Trailing icon (default: clear icon)
- `singleLine`: Single line input (default: true)
- `enabled`: Whether input is enabled (default: true)
- `onClear`: Optional callback when clear is pressed

### Loading

#### Loading
```kotlin
Loading(
    isLoading = true,
    message = "Loading..."
)
```

### Scaffold

#### ScaffoldCustom
```kotlin
ScaffoldCustom(
    customToolBar = { /* toolbar content */ },
    customBottomBar = { /* bottom bar content */ },
    customBody = { /* main content */ },
    isLoading = false,
    hasError = null,
    onClickError = { /* handle error */ }
)
```

### Text

#### TextCustom
```kotlin
TextCustom(
    text = "Your text here",
    style = MaterialTheme.typography.bodyLarge,
    color = MaterialTheme.colorScheme.onSurface,
    fontWeight = FontWeight.Normal,
    textAlign = TextAlign.Start,
    maxLines = 1,
    overflow = TextOverflow.Ellipsis
)
```

### Toolbar

#### CustomToolBar
```kotlin
CustomToolBar(
    title = "Screen Title",
    onBackPressed = { /* handle back */ },
    actions = { /* action buttons */ }
)
```

## 🎯 Best Practices

### Component Usage
1. **Import Components**: Always import from the correct package
2. **Use Modifiers**: Apply modifiers for layout and styling
3. **Handle Callbacks**: Implement proper callback functions
4. **Accessibility**: Provide content descriptions where needed

### Styling
1. **Theme Integration**: Use MaterialTheme colors and typography
2. **Consistent Spacing**: Use standard spacing values (8dp, 16dp, etc.)
3. **Rounded Corners**: Use consistent border radius (12dp for cards, 8dp for buttons)
4. **Elevation**: Use appropriate elevation for depth

### Performance
1. **Remember**: Use remember for expensive computations
2. **LaunchedEffect**: Use for side effects
3. **Recomposition**: Minimize unnecessary recompositions
4. **Lazy Loading**: Use LazyColumn/LazyGrid for large lists

## 🔧 Customization

### Theme Colors
All components automatically adapt to the app's theme colors:
- `MaterialTheme.colorScheme.primary`
- `MaterialTheme.colorScheme.surface`
- `MaterialTheme.colorScheme.onSurface`
- `MaterialTheme.colorScheme.outline`

### Typography
Use Material Design 3 typography:
- `MaterialTheme.typography.headlineLarge`
- `MaterialTheme.typography.bodyLarge`
- `MaterialTheme.typography.labelMedium`

### Spacing
Standard spacing values:
- `4.dp` - Small spacing
- `8.dp` - Medium spacing
- `16.dp` - Large spacing
- `24.dp` - Extra large spacing

## 📋 Component Checklist

When creating new components:

- [ ] Follow Material Design 3 guidelines
- [ ] Include comprehensive KDoc documentation
- [ ] Add proper accessibility support
- [ ] Use theme colors and typography
- [ ] Include customization parameters
- [ ] Test with different screen sizes
- [ ] Add to this documentation

## 🚀 Future Enhancements

- **Animation Support**: Add smooth animations and transitions
- **Dark Mode**: Enhanced dark mode support
- **Internationalization**: Multi-language support
- **Accessibility**: Enhanced accessibility features
- **Testing**: Unit tests for components 