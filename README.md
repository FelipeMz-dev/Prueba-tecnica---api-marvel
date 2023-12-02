# Prueba tecnica - api marvel
*Capas de la app: Clean Architecture MVI.*

Capa de Presentación: 
Esta app contiene todos los elementos de la UI (elementos composable: view, theme, component, etc) incluyendo el ViewModel

Capa de Dominio:
Esta capa es la que contiene los casos de uso

Capa de infraestructura:
Esta es la que contiene los datasources (model y persistencia de datos: local y remote) de la app ademas del resto de componentes para la logica de negocio como repository, mappers, dependency injection, etc.
