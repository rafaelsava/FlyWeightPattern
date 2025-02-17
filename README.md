
# Implementación del Patrón FlyWeight

## Descripción del Patrón
El patrón FlyWeight es utilizado para mantener más objetos dentro de la cantidad disponible de RAM compartiendo las partes comunes del estado entre varios objetos en lugar de mantener toda la información en cada objeto. Este proyecto demuestra su implementación mediante un ejemplo práctico en **Java** utilizando **Maven** como gestor de dependencias.

---

## Estructura del Proyecto
La estructura del proyecto sigue el estándar de **Maven**:

```
nombre-del-proyecto
│
├── pom.xml                # Archivo de configuración de Maven
├── README.md              # Documentación del proyecto
└── src
    ├── main
    │   ├── java
    │   │   └── [paquete base]    # Código fuente principal
    │   └── resources             # Recursos adicionales
    └── test
        └── java                  # Pruebas unitarias
```

---

## Plugins Utilizados
Este proyecto utiliza los siguientes plugins definidas en el archivo **pom.xml**:
```xml
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-resources-plugin</artifactId>
                <version>2.3</version>
            </plugin>
        </plugins>
```

---

## Instrucciones de Instalación

1. **Clonar el repositorio:**
```bash
git clone https://github.com/usuario/nombre_repositorio.git
cd nombre_repositorio
```

2. **Compilar el proyecto:**
```bash
mvn clean compile
```

3. **Ejecutar el proyecto:**
```bash
mvn exec:java -Dexec.mainClass="com.ejemplo.Main"
```

---

## Ejemplo de Ejecución
Al ejecutar el programa, deberías ver la siguiente salida:
---
<img width="322" alt="Captura de pantalla 2025-02-16 221634" src="https://github.com/user-attachments/assets/ce5d7fca-cbfb-4113-bd5e-76abbad23c40" />
<img width="322" alt="Captura de pantalla 2025-02-16 221645" src="https://github.com/user-attachments/assets/bf276129-112a-42c0-9d3b-6ae92089a83b" />


---

---

## Diagrama UML
El siguiente diagrama muestra la estructura del patrón implementado:

![GameFlyWeight](https://github.com/user-attachments/assets/ba8d7a02-fba7-4f9b-a2bb-fb3a7c47b257)


---

## Explicación de la Implementación
El patrón FlyWeight ha sido implementado utilizando las siguientes clases principales:
- **EnemyFlyweightFactory**: Administra la creación y reutilización de los objetos flyweight para los enemigos. Al solicitar un enemigo de un tipo específico (por ejemplo, "enemy1" o "enemy2"), verifica si ya existe un objeto compartido; si no, lo crea y lo almacena para futuras solicitudes.
- **GameEnemy**: Representa cada instancia de enemigo en el juego. Esta clase almacena el estado extrínseco (como posición, velocidad y ángulo) y mantiene una referencia al flyweight correspondiente que contiene el estado intrínseco (imagen, sprite). Su método de renderizado combina ambos estados para dibujar el enemigo correctamente.
- **BulletFlyweightFactory**: Se encarga de crear y gestionar la única instancia compartida (flyweight) para las balas.
- **GameBullet**: Encapsula el estado extrínseco de cada bala (posición y movimiento) y delega el renderizado en el flyweight compartido.

---

## Contribuciones
Este proyecto fue desarrollado por:
- Rafael Andrés Salcedo Valdivieso
- Fermin Alejandro Escalona Guillen
- Tomas Barrios Guevara

