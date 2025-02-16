
# Implementación del Patrón [Nombre del Patrón]

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

## Dependencias Utilizadas
Este proyecto utiliza las siguientes dependencias definidas en el archivo **pom.xml**:
```xml
<dependencies>
    <!-- Dependencias estándar de Maven -->
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.13.2</version>
        <scope>test</scope>
    </dependency>
</dependencies>
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
```
[Ejemplo de resultado del programa]
```

---

## Diagrama UML
El siguiente diagrama muestra la estructura del patrón implementado:

![Diagrama UML](uml/diagrama.png)

---

## Explicación de la Implementación
El patrón FlyWeight ha sido implementado utilizando las siguientes clases principales:
- **[Clase 1]**: Explicación del propósito de la clase.
- **[Clase 2]**: Explicación del propósito de la clase.
- **[Clase 3]**: Explicación del propósito de la clase.

---

## Contribuciones
Este proyecto fue desarrollado por:
- Rafael Andrés Salcedo Valdivieso
- Fermin Alejandro Escalona Guillen
- Tomas Barrios Guevara

