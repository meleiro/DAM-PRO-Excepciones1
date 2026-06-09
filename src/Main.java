// Importamos las excepciones y clases necesarias
import java.util.InputMismatchException;
import java.util.Scanner;

// Clase principal del programa
public class Main {

    // Método principal: punto de entrada del programa
    public static void main(String[] args) {

        // Creamos un Scanner para leer datos desde teclado
        Scanner sc = new Scanner(System.in);

        // Variable que almacenará la opción elegida en el menú
        int opcion;

        // Bucle principal del menú
        do {

            // Mostramos las opciones disponibles
            System.out.println("\n===== MENÚ EXCEPCIONES =====");
            System.out.println("1. División por cero");
            System.out.println("2. Letras en lugar de número");
            System.out.println("3. Array fuera de rango");
            System.out.println("4. NullPointerException");
            System.out.println("5. Múltiples catch");
            System.out.println("6. Finally");
            System.out.println("7. Throw");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");

            // Leemos la opción usando un método seguro
            opcion = leerEntero(sc);

            // Ejecutamos el caso correspondiente
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

                case 0:
                    System.out.println("Fin del programa.");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);

        // Cerramos el Scanner al finalizar
        sc.close();
    }

    // ======================================================
    // MÉTODO PARA LEER ENTEROS DE FORMA SEGURA
    // ======================================================
    public static int leerEntero(Scanner sc) {

        while (true) {

            try {
                // Intentamos leer un entero
                return sc.nextInt();

            } catch (InputMismatchException e) {

                // Si el usuario introduce letras u otro dato inválido
                System.out.print("Error. Introduce un número entero: ");

                // Limpiamos el buffer para evitar bucles infinitos
                sc.nextLine();
            }
        }
    }

    // ======================================================
    // MÉTODO PARA LEER NÚMEROS DECIMALES
    // ======================================================
    public static double leerDouble(Scanner sc) {

        while (true) {

            try {
                return sc.nextDouble();

            } catch (InputMismatchException e) {

                System.out.print("Error. Introduce un número válido: ");

                // Eliminamos la entrada incorrecta
                sc.nextLine();
            }
        }
    }

    // ======================================================
    // EJEMPLO 1: DIVISIÓN POR CERO
    // ======================================================
    public static void divisionPorCero(Scanner sc) {

        boolean correcto = false;

        while (!correcto) {

            try {

                System.out.print("Introduce el dividendo: ");
                int a = leerEntero(sc);

                System.out.print("Introduce el divisor: ");
                int b = leerEntero(sc);

                // Puede generar ArithmeticException
                int resultado = a / b;

                System.out.println("Resultado: " + resultado);

                correcto = true;

            } catch (ArithmeticException e) {

                // Se ejecuta si b vale 0
                System.out.println("No se puede dividir entre cero.");
            }
        }
    }

    // ======================================================
    // EJEMPLO 2: ENTRADA INCORRECTA
    // ======================================================
    public static void entradaIncorrecta(Scanner sc) {

        System.out.print("Introduce tu edad: ");

        // leerEntero ya controla InputMismatchException
        int edad = leerEntero(sc);

        System.out.println("Edad introducida correctamente: " + edad);
    }

    // ======================================================
    // EJEMPLO 3: ARRAY FUERA DE RANGO
    // ======================================================
    public static void errorArray(Scanner sc) {

        // Array de tres posiciones (0,1,2)
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

                // Puede producir ArrayIndexOutOfBoundsException
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

        // Variable inicializada a null
        String nombre = null;

        boolean correcto = false;

        while (!correcto) {

            try {

                System.out.print("Introduce tu nombre: ");

                // Limpiamos salto pendiente
                sc.nextLine();

                // Leemos el nombre
                nombre = sc.nextLine();

                // Si está vacío lo dejamos en null
                if (nombre.isBlank()) {
                    nombre = null;
                }

                // Si nombre es null provocará NullPointerException
                System.out.println(
                        "Longitud del nombre: " + nombre.length()
                );

                correcto = true;

            } catch (NullPointerException e) {

                System.out.println(
                        "El nombre no puede estar vacío."
                );
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

                // Puede producir ArithmeticException
                int resultado = a / b;

                System.out.println("Resultado: " + resultado);

                correcto = true;

            } catch (InputMismatchException e) {

                // Si se introducen letras
                System.out.println(
                        "Debes introducir números enteros."
                );

                // Limpiamos buffer
                sc.nextLine();

            } catch (ArithmeticException e) {

                // Si se divide entre cero
                System.out.println(
                        "No se puede dividir entre cero."
                );
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

                System.out.print(
                        "Introduce un número distinto de 0: "
                );

                int numero = leerEntero(sc);

                int resultado = 10 / numero;

                System.out.println(
                        "10 / " + numero + " = " + resultado
                );

                correcto = true;

            } catch (ArithmeticException e) {

                System.out.println(
                        "Error: el número no puede ser 0."
                );

            } finally {

                // Este bloque SIEMPRE se ejecuta
                // haya excepción o no
                System.out.println(
                        "Bloque finally ejecutado."
                );
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

                System.out.print(
                        "Introduce una edad positiva: "
                );

                int edad = leerEntero(sc);

                // Lanzamos manualmente una excepción
                if (edad < 0) {

                    throw new IllegalArgumentException(
                            "La edad no puede ser negativa."
                    );
                }

                System.out.println(
                        "Edad válida: " + edad
                );

                correcto = true;

            } catch (IllegalArgumentException e) {

                // Mostramos el mensaje de la excepción
                System.out.println(
                        e.getMessage()
                );
            }
        }
    }
}