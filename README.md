# ScrumExaminerDemo

A demo project to showcase how to structure android app to make it modular and scaleable.
The project is developed using Kotlin and Jetpack Compose.

## App Structure

Modules have been grouped in folders

### Common
This contains common composables used across features (i.e. the presentation layers)

### Core
This contains modules and logic that is core to the application or reused by different features

### ProfessionalScrumMaster folder
This is a feature folder containing the code related to PSM. It is divided into 3 layers which are modularised:
- Presentation: Contains UI related implementations
- Data: Contains the data layer implementaions
- Domain: Domain layer implementations (Core to the feature)

### ProfessionalScrumDeveloper folder
This is a feature folder containing the code related to PSD. It is divided into 3 layers which are modularised:
- Presentation: Contains UI related implementations
- Data: Contains the data layer implementaions
- Domain: Domain layer implementations (Core to the feature)

The app is still being developed and will be refactored accordingly
- No tests have been added
