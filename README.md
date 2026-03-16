# Sistema de Gestión de Pizzería - Mini Proyecto POO
> Mini gestor de pedidos de pizzería basado en Java, rediseñado bajo arquitectura limpia (adapters, entities, infrastructure y usecases) y principios POO.


```
         .,:::,.,
      .:::::::::::::.
    .::  o   :::  o  ::.
   :::   :::::::::::   ::
   ::  o  ::: ~ :::  o ::
   :::    ::: ~ :::    ::
    '::  o  :::::  o  ::'
      ':::::::::::::'
         ':::::::'

        P A P A ' S  
        pizzeria artesanal
```

---
## Estructura del proyecto
```
src/
├── adapters/
│   └── console/
│       ├── Main.java
│       └── PizzaAnimation.java
├── entities/
│   ├── Bebida.java
│   ├── Cliente.java
│   ├── DomainException.java
│   ├── Entregable.java
│   ├── EstadoPedido.java
│   ├── Pedido.java
│   ├── Pizza.java
│   ├── Producto.java
│   ├── Reglas.java
│   ├── Reserva.java
│   └── TipoEntrega.java
├── infrastructure/
│   ├── repositories/
│   │   ├── InMemoryClienteRepository.java
│   │   ├── InMemoryPedidoRepository.java
│   │   ├── InMemoryProductoRepository.java
│   │   └── InMemoryReservaRepository.java
│   └── services/
│       └── SimpleIdGenerator.java
└── usecases/
    ├── dto/
    │   ├── OperationResult.java
    │   └── ResumenPedido.java
    ├── ports/
    │   ├── ClienteRepository.java
    │   ├── IdGenerator.java
    │   ├── PedidoRepository.java
    │   ├── ProductoRepository.java
    │   └── ReservaRepository.java
    └── services/
        ├── CalculadoraTotal.java
        ├── CancelarPedido.java
        ├── CancelarReserva.java
        ├── ConfirmarReserva.java
        ├── EntregarPedido.java
        ├── PizzeriaApp.java
        ├── RealizarPedido.java
        ├── RegistrarBebida.java
        ├── RegistrarCliente.java
        ├── RegistrarPizza.java
        └── RegistrarReserva.java

```
---

## Compilación y ejecución
Se requiere tener instalada la versión 25 del Java Development Kit (JDK SE), la cual incluye el compilador (javac) y el ejecutable (java), necesarios para compilar y ejecutar el programa.

Una vez instalado, se debe descargar el proyecto (también es posible trabajar únicamente con el directorio src). Posteriormente, se abre una consola y se verifica que la ruta actual corresponda a la carpeta raíz del proyecto. Finalmente, se ejecutarán los siguientes comandos:
```bash
# Compilar
javac -d out -sourcepath src src/ui/Main.java
# Ejecutar
java -cp out -ui.Main
```
