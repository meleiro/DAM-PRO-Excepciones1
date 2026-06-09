import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        /*
         * Scanner permite leer datos introducidos por teclado.
         * Se crea una sola vez y se reutiliza en todos los métodos.
         */
        Scanner sc = new Scanner(System.in);

        int opcion;

        /*
         * El menú se repite hasta que el usuario elija la opción 0.
         */
        do {
            System.out.println("\n===== MENÚ EXCEPCIONES =====");
            System.out.println("1. División por cero");
            System.out.println("2. Letras en lugar de número");
            System.out.println("3. Array fuera de rango");
            System.out.println("4. NullPointerException");
            System.out.println("5. Múltiples catch");
            System.out.println("6. Finally");
            System.out.println("7. Throw");
            System.out.println("8. Excepciones personalizadas");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");

            /*
             * Usamos leerEntero() en vez de sc.nextInt()
             * para evitar que el programa falle si el usuario
             * escribe letras.
             */
            opcion = leerEntero(sc);

            switch (opcion) {
                case 1:
                    divisionPorCero(sc);
                    break;

                case 2:
                    entradaIncorrecta(sc);
                    break;

                case 3:
                    errorArray(sc);
                    break;

                case 4:
                    errorNull(sc);
                    break;

                case 5:
                    multiplesCatch(sc);
                    break;

                case 6:
                    ejemploFinally(sc);
                    break;

                case 7:
                    ejemploThrow(sc);
                    break;

                case 8:
                    excepcionesPersonalizadas(sc);
                    break;

                case 0:
                    System.out.println("Fin del programa.");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);

        /*
         * Cerramos el Scanner al final del programa.
         * No se debe cerrar antes porque lo usan varios métodos.
         */
        sc.close();
    }

    // ======================================================
    // MÉTODO PARA LEER ENTEROS DE FORMA SEGURA
    // ======================================================
    public static int leerEntero(Scanner sc) {

        /*
         * while(true) significa:
         * "Repite indefinidamente hasta que pueda devolver un entero".
         *
         * El bucle termina cuando se ejecuta return.
         */
        while (true) {

            try {
                /*
                 * Si el usuario escribe un entero correcto,
                 * se devuelve y el método termina.
                 */
                return sc.nextInt();

            } catch (InputMismatchException e) {

                /*
                 * Esta excepción ocurre cuando el usuario escribe
                 * algo que no se puede convertir a int.
                 *
                 * Ejemplo:
                 * - hola
                 * - 3.5
                 * - abc
                 */
                System.out.print("Error. Introduce un número entero: ");

                /*
                 * Limpiamos el buffer.
                 *
                 * Si no hacemos esto, la entrada incorrecta queda
                 * pendiente y el programa entraría en un bucle infinito.
                 */
                sc.nextLine();
            }
        }
    }

    // ======================================================
    // MÉTODO PARA LEER DECIMALES DE FORMA SEGURA
    // ======================================================
    public static double leerDouble(Scanner sc) {

        while (true) {

            try {
                /*
                 * Si el dato introducido es válido como decimal,
                 * se devuelve inmediatamente.
                 */
                return sc.nextDouble();

            } catch (InputMismatchException e) {

                /*
                 * Se captura cuando el usuario introduce un dato
                 * que no puede convertirse a double.
                 */
                System.out.print("Error. Introduce un número válido: ");

                /*
                 * Limpiamos la entrada incorrecta.
                 */
                sc.nextLine();
            }
        }
    }

    // ======================================================
    // EJEMPLO 1: ARITHMETICEXCEPTION
    // ======================================================
    public static void divisionPorCero(Scanner sc) {

        boolean correcto = false;

        /*
         * El bucle se repetirá hasta que la división sea válida.
         */
        while (!correcto) {

            try {
                System.out.print("Introduce el dividendo: ");
                int a = leerEntero(sc);

                System.out.print("Introduce el divisor: ");
                int b = leerEntero(sc);

                /*
                 * Esta línea puede provocar ArithmeticException
                 * si b vale 0.
                 */
                int resultado = a / b;

                System.out.println("Resultado: " + resultado);

                /*
                 * Si llegamos aquí, significa que no hubo excepción.
                 */
                correcto = true;

            } catch (ArithmeticException e) {

                /*
                 * Este catch se ejecuta únicamente si se intenta
                 * dividir entre cero.
                 */
                System.out.println("No se puede dividir entre cero.");
            }
        }
    }

    // ======================================================
    // EJEMPLO 2: INPUTMISMATCHEXCEPTION
    // ======================================================
    public static void entradaIncorrecta(Scanner sc) {

        System.out.print("Introduce tu edad: ");

        /*
         * No usamos try-catch aquí directamente porque leerEntero()
         * ya se encarga de capturar InputMismatchException.
         */
        int edad = leerEntero(sc);

        System.out.println("Edad introducida correctamente: " + edad);
    }

    // ======================================================
    // EJEMPLO 3: ARRAYINDEXOUTOFBOUNDSEXCEPTION
    // ======================================================
    public static void errorArray(Scanner sc) {

        /*
         * Este array tiene únicamente tres posiciones válidas:
         * 0, 1 y 2.
         */
        int[] numeros = {10, 20, 30};

        boolean correcto = false;

        while (!correcto) {

            try {
                System.out.println("Array disponible:");
                System.out.println("0 -> 10");
                System.out.println("1 -> 20");
                System.out.println("2 -> 30");

                System.out.print("Introduce una posición: ");
                int posicion = leerEntero(sc);

                /*
                 * Si posicion es menor que 0 o mayor que 2,
                 * Java lanzará ArrayIndexOutOfBoundsException.
                 */
                System.out.println("Valor: " + numeros[posicion]);

                correcto = true;

            } catch (ArrayIndexOutOfBoundsException e) {

                System.out.println(
                        "Posición fuera de rango. Prueba con 0, 1 o 2."
                );
            }
        }
    }

    // ======================================================
    // EJEMPLO 4: NULLPOINTEREXCEPTION
    // ======================================================
    public static void errorNull(Scanner sc) {

        /*
         * null significa que la variable no apunta a ningún objeto.
         */
        String nombre = null;

        boolean correcto = false;

        while (!correcto) {

            try {
                System.out.print("Introduce tu nombre: ");

                /*
                 * Limpiamos el salto de línea que puede quedar pendiente
                 * después de usar nextInt() o nextDouble().
                 */
                sc.nextLine();

                nombre = sc.nextLine();

                /*
                 * Si el usuario no escribe nada, forzamos que nombre
                 * vuelva a ser null para provocar la excepción.
                 */
                if (nombre.isBlank()) {
                    nombre = null;
                }

                /*
                 * Si nombre es null, no se puede llamar a length().
                 * Eso provoca NullPointerException.
                 */
                System.out.println(
                        "Longitud del nombre: " + nombre.length()
                );

                correcto = true;

            } catch (NullPointerException e) {

                System.out.println("El nombre no puede estar vacío.");
            }
        }
    }

    // ======================================================
    // EJEMPLO 5: MÚLTIPLES CATCH
    // ======================================================
    public static void multiplesCatch(Scanner sc) {

        boolean correcto = false;

        while (!correcto) {

            try {
                System.out.print("Número 1: ");
                int a = sc.nextInt();

                System.out.print("Número 2: ");
                int b = sc.nextInt();

                /*
                 * Aquí pueden pasar dos errores:
                 *
                 * 1. Que el usuario escriba letras.
                 *    En ese caso se lanza InputMismatchException.
                 *
                 * 2. Que b sea 0.
                 *    En ese caso se lanza ArithmeticException.
                 */
                int resultado = a / b;

                System.out.println("Resultado: " + resultado);

                correcto = true;

            } catch (InputMismatchException e) {

                System.out.println("Debes introducir números enteros.");

                /*
                 * Muy importante: limpiar la entrada incorrecta.
                 */
                sc.nextLine();

            } catch (ArithmeticException e) {

                System.out.println("No se puede dividir entre cero.");
            }
        }
    }

    // ======================================================
    // EJEMPLO 6: FINALLY
    // ======================================================
    public static void ejemploFinally(Scanner sc) {

        boolean correcto = false;

        while (!correcto) {

            try {
                System.out.print("Introduce un número distinto de 0: ");

                int numero = leerEntero(sc);

                /*
                 * Si numero vale 0, se lanza ArithmeticException.
                 */
                int resultado = 10 / numero;

                System.out.println("10 / " + numero + " = " + resultado);

                correcto = true;

            } catch (ArithmeticException e) {

                System.out.println("Error: el número no puede ser 0.");

            } finally {

                /*
                 * finally se ejecuta siempre:
                 *
                 * - Si hay excepción.
                 * - Si no hay excepción.
                 *
                 * Normalmente se usa para liberar recursos.
                 */
                System.out.println("Bloque finally ejecutado.");
            }
        }
    }

    // ======================================================
    // EJEMPLO 7: THROW
    // ======================================================
    public static void ejemploThrow(Scanner sc) {

        boolean correcto = false;

        while (!correcto) {

            try {
                System.out.print("Introduce una edad positiva: ");

                int edad = leerEntero(sc);

                /*
                 * throw permite lanzar una excepción manualmente.
                 *
                 * IllegalArgumentException significa:
                 * "el argumento recibido no es válido".
                 */
                if (edad < 0) {
                    throw new IllegalArgumentException(
                            "La edad no puede ser negativa."
                    );
                }

                System.out.println("Edad válida: " + edad);

                correcto = true;

            } catch (IllegalArgumentException e) {

                /*
                 * getMessage() devuelve el mensaje que pusimos
                 * al crear la excepción.
                 */
                System.out.println(e.getMessage());
            }
        }
    }

    // ======================================================
    // EJEMPLO 8: EXCEPCIONES PERSONALIZADAS
    // ======================================================
    public static void excepcionesPersonalizadas(Scanner sc) {

        boolean correcto = false;

        while (!correcto) {

            try {
                /*
                 * Limpiamos el buffer antes de leer una línea completa.
                 */
                sc.nextLine();

                System.out.print("Introduce tu nombre: ");
                String nombre = sc.nextLine();

                /*
                 * Este método puede lanzar NombreVacioException.
                 * Por eso debe estar dentro de un try-catch.
                 */
                validarNombre(nombre);

                System.out.print("Introduce tu edad: ");
                int edad = leerEntero(sc);

                /*
                 * Este método puede lanzar EdadInvalidaException.
                 */
                validarEdad(edad);

                System.out.print("Introduce tu saldo: ");
                double saldo = leerDouble(sc);

                System.out.print("Cantidad a retirar: ");
                double cantidad = leerDouble(sc);

                /*
                 * Este método puede lanzar SaldoInsuficienteException.
                 */
                retirarDinero(saldo, cantidad);

                /*
                 * Si se llega hasta aquí, todos los datos han sido válidos.
                 */
                System.out.println("Operación realizada correctamente.");

                correcto = true;

            } catch (NombreVacioException e) {

                System.out.println("Error de nombre: " + e.getMessage());

            } catch (EdadInvalidaException e) {

                System.out.println("Error de edad: " + e.getMessage());

            } catch (SaldoInsuficienteException e) {

                System.out.println("Error bancario: " + e.getMessage());
            }
        }
    }

    // ======================================================
    // VALIDACIÓN DEL NOMBRE
    // ======================================================
    public static void validarNombre(String nombre)
            throws NombreVacioException {

        /*
         * Este método no captura la excepción.
         * La lanza y obliga a quien lo llame a gestionarla.
         */
        if (nombre == null || nombre.isBlank()) {
            throw new NombreVacioException(
                    "El nombre no puede estar vacío."
            );
        }
    }

    // ======================================================
    // VALIDACIÓN DE LA EDAD
    // ======================================================
    public static void validarEdad(int edad)
            throws EdadInvalidaException {

        /*
         * Si la edad es negativa, lanzamos una excepción
         * personalizada porque incumple una regla del programa.
         */
        if (edad < 0) {
            throw new EdadInvalidaException(
                    "La edad no puede ser negativa."
            );
        }

        /*
         * También consideramos inválida una edad menor de 18.
         */
        if (edad < 18) {
            throw new EdadInvalidaException(
                    "Debes ser mayor de edad."
            );
        }
    }

    // ======================================================
    // VALIDACIÓN DE RETIRADA DE DINERO
    // ======================================================
    public static void retirarDinero(double saldo, double cantidad)
            throws SaldoInsuficienteException {

        /*
         * No tiene sentido retirar una cantidad negativa o cero.
         */
        if (cantidad <= 0) {
            throw new SaldoInsuficienteException(
                    "La cantidad debe ser mayor que cero."
            );
        }

        /*
         * Si se intenta retirar más dinero del disponible,
         * lanzamos una excepción personalizada.
         */
        if (cantidad > saldo) {
            throw new SaldoInsuficienteException(
                    "No hay saldo suficiente. Saldo actual: " + saldo
            );
        }
    }
}