# UI Components

This directory contains reusable UI components organized by functionality.

## Structure

```
components/
├── alerts/          # Dialog and alert components
├── bottomBar/       # Bottom navigation components
├── buttons/         # Button components (Primary, Secondary, etc.)
├── input/           # Input field components
├── loading/         # Loading indicators
├── scaffold/        # Layout scaffold components
├── text/            # Text components with styling
└── toolbar/         # Top app bar components
```

## Usage Guidelines

- All components should be composable functions
- Use MaterialTheme for consistent styling
- Include proper content descriptions for accessibility
- Support both light and dark themes
- Use the custom color palette defined in theme/Color.kt

## Component Standards

1. **Naming**: Use descriptive names (e.g., `PrimaryButton`, `CustomInput`)
2. **Props**: Keep props minimal and focused
3. **Styling**: Use theme colors and typography
4. **Accessibility**: Include content descriptions
5. **Testing**: Each component should be testable 