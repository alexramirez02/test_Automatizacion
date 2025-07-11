Feature: Buscar vuelos

  Scenario: Buscar vuelos entre dos ciudades válidas
    Given el usuario accede a la pagina de vuelos
    When el usuario selecciona "Paris" como origen y "Buenos Aires" como destino
    Then debe ver notificacion "Thank you for your purchase today!"



  Scenario: No seleccionar ciudad de origen (caso no feliz)
    Given el usuario accede a la pagina de vuelos
    When el usuario selecciona "Caracas" como origen y "Buenos Aires" como destino
    Then debe ver notificacion "Datos faltantes"
