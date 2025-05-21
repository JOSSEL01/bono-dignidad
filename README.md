# Bono Dignidad

Un sistema basado en Java para la gestión de bonos sociales, incluyendo administradores, beneficiarios y pagos de bonos. Soporta interfaces de consola y gráfica.

## Características
- Registrar y gestionar bonos, beneficiarios y administradores.
- Procesar solicitudes y pagos de bonos.
- Generar reportes (por ejemplo, beneficiarios por tipo de discapacidad, total pagado por período).
- Persistir datos en archivos CSV.
- Interfaz gráfica construida con Swing.

## Requisitos
- **Java Development Kit (JDK)**: Versión 8 o superior (recomendado JDK 11 o 17).
  - Verifica con: `java -version` y `javac -version`.
  - Descarga desde [Oracle](https://www.oracle.com/java/technologies/javase-downloads.html) o usa OpenJDK desde [Adoptium](https://adoptium.net/) (Nota: AdoptOpenJDK se trasladó a Adoptium).
- **Git**: Para clonar el repositorio.
  - Verifica con: `git --version`.
  - Descarga desde [git-scm.com](https://git-scm.com/downloads).
- Opcional: Un IDE como NetBeans, IntelliJ IDEA, VSCode o Eclipse para facilitar el desarrollo.

## Instalación y ejecución
Sigue estos pasos para clonar, compilar y ejecutar el proyecto:

1. **Clona el repositorio**:
   Abre una terminal (en Windows usa Git Bash, CMD o PowerShell; en macOS/Linux usa la terminal) y ejecuta:
   ```bash
   git clone https://github.com/JOSSEL01/bono-dignidad.git
   cd bono-dignidad
