# Compose-Clean-Architecture
Android app using Kotlin compose implemented with the advance  clean architecture 
# Clean Architecture(Solid Principal) (1)

# Package Structure(Compose - UI)

| Data (Package-1) | Domain (Package-2)  | Presentation (Package-3)  |
| --- | --- | --- |
| Room DB Client | Repository Interfaces  | ui  |
| Retrofit Client  | Model classes | Screens  |
| Retrofit Api Interface  | Use cases eg. ( user →get , delete, update user ) | ViewModel  |
| Room Dao Object  |  | States  |
| Room model classes  |  | events  |
| Retrofit model classes  |  |  |
| Implementation Repository |  |  |
|  |  |  |
|  |  |  |

# Actual Package Inside the Each Package

### Data

- model
- local  (Room Data base)
- remote  (Retrofit for Api calls)
- Repository  (implementation of Interface Repository  ( DomainPackage) )
- mappers

### Domain

- Repository ( Interface Repository)
- Data (Model classes )
- use-case

### Presentation

- Screens
- ViewModel
- State
- Events

### Di

- Module

### Utils

- for commonly used items
