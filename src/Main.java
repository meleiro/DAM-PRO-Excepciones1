// ======================================================
// EJEMPLO 8: EXCEPCIONES PERSONALIZADAS
// ======================================================
//
// Este método permite demostrar el funcionamiento de:
//
// - Excepciones creadas por el programador.
// - throw (lanzar una excepción).
// - throws (declarar una excepción).
// - Propagación de excepciones.
// - Captura mediante múltiples bloques catch.
//
// El usuario debe introducir:
//
// 1. Nombre
// 2. Edad
// 3. Saldo disponible
// 4. Cantidad a retirar
//
// Cada dato es validado por métodos independientes.
// Si alguna validación falla, se lanza una excepción
// personalizada que será capturada aquí.
//
public static void excepcionesPersonalizadas(Scanner sc) {

    // Variable de control para repetir el proceso
    // hasta que todos los datos sean correctos.
    boolean correcto = false;

    while (!correcto) {

        try {

            // --------------------------------------------------
            // LIMPIEZA DEL BUFFER
            // --------------------------------------------------
            //
            // Cuando previamente hemos utilizado nextInt()
            // o nextDouble(), queda pendiente un salto de línea.
            //
            // Si no lo eliminamos, el siguiente nextLine()
            // leerá una cadena vacía automáticamente.
            //
            sc.nextLine();

            // --------------------------------------------------
            // LECTURA Y VALIDACIÓN DEL NOMBRE
            // --------------------------------------------------
            //
            // Pedimos el nombre al usuario.
            //
            System.out.print("Introduce tu nombre: ");

            String nombre = sc.nextLine();

            // Este método puede lanzar:
            //
            // NombreVacioException
            //
            // Si el nombre es null o está vacío.
            //
            validarNombre(nombre);

            // --------------------------------------------------
            // LECTURA Y VALIDACIÓN DE LA EDAD
            // --------------------------------------------------
            //
            System.out.print("Introduce tu edad: ");

            int edad = leerEntero(sc);

            // Este método puede lanzar:
            //
            // EdadInvalidaException
            //
            // Si la edad es negativa o menor de 18.
            //
            validarEdad(edad);

            // --------------------------------------------------
            // LECTURA DEL SALDO
            // --------------------------------------------------
            //
            System.out.print("Introduce tu saldo: ");

            double saldo = leerDouble(sc);

            // --------------------------------------------------
            // LECTURA DE LA CANTIDAD A RETIRAR
            // --------------------------------------------------
            //
            System.out.print("Cantidad a retirar: ");

            double cantidad = leerDouble(sc);

            // --------------------------------------------------
            // VALIDACIÓN DE LA OPERACIÓN BANCARIA
            // --------------------------------------------------
            //
            // Este método puede lanzar:
            //
            // SaldoInsuficienteException
            //
            // Si el saldo no es suficiente o la cantidad
            // es inválida.
            //
            retirarDinero(saldo, cantidad);

            // --------------------------------------------------
            // SI HEMOS LLEGADO HASTA AQUÍ...
            // --------------------------------------------------
            //
            // Significa que:
            //
            // - El nombre es válido.
            // - La edad es válida.
            // - El saldo es suficiente.
            // - No se ha lanzado ninguna excepción.
            //
            System.out.println(
                    "Operación realizada correctamente."
            );

            // Finalizamos el bucle.
            correcto = true;

        }

        // ==================================================
        // CAPTURA DE EXCEPCIONES PERSONALIZADAS
        // ==================================================

        catch (NombreVacioException e) {

            // Se ejecuta cuando validarNombre()
            // detecta un nombre vacío.
            //
            // e contiene el objeto excepción lanzado.
            //
            System.out.println(
                    "Error de nombre: " +
                            e.getMessage()
            );

        }

        catch (EdadInvalidaException e) {

            // Se ejecuta cuando validarEdad()
            // detecta una edad incorrecta.
            //
            System.out.println(
                    "Error de edad: " +
                            e.getMessage()
            );

        }

        catch (SaldoInsuficienteException e) {

            // Se ejecuta cuando retirarDinero()
            // detecta una operación imposible.
            //
            System.out.println(
                    "Error bancario: " +
                            e.getMessage()
            );
        }
    }
}