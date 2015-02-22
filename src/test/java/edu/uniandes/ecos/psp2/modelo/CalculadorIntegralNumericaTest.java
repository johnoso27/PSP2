package edu.uniandes.ecos.psp2.modelo;

import junit.framework.TestCase;

/**
 * Clase que contiene la pruebas unitarias para los métodos principales de la
 * clase CalculadorIntegralNumerica.
 *
 * @author JohnDany
 */
public class CalculadorIntegralNumericaTest extends TestCase {

    public CalculadorIntegralNumericaTest(String testName) {
        super(testName);
    }

    /**
     * Test of calcularIntegralNumerica method, of class
     * CalculadorIntegralNumerica.
     *
     * @throws java.lang.Exception gerada.
     */
    public void testCalcularIntegralNumerica() throws Exception {
        CalculadorIntegralNumerica calculador = new CalculadorIntegralNumerica();
        assertNotNull(null, calculador);

        double actual = calculador.calcularIntegralNumerica(10, 1.1, 9.0);
        System.out.println("Prueba de datos sin margen de error: x=1.1 dof=9 valor esperado=0.3500589 valor actual=" + actual);
        assertEquals(0.3500589, actual, 0.5);
    }

    /**
     * Test of calcularIntegralNumericaEnMargenError method, of class
     * CalculadorIntegralNumerica.
     *
     * @throws java.lang.Exception generada
     */
    public void testCalcularIntegralNumericaEnMargenError() throws Exception {
        CalculadorIntegralNumerica calculador = new CalculadorIntegralNumerica();
        assertNotNull(null, calculador);

        double actual = calculador.calcularIntegralNumericaEnMargenError(10, 1.1, 0.000001, 9.0);
        System.out.println("Prueba de datos 1 : x=1.1 dof=9 Margen de error:0.000001 valor esperado=0.3500589 valor actual=" + actual);
        assertEquals(0.3500589, actual, 0.5);

        actual = calculador.calcularIntegralNumerica(10, 1.1, 9.0);
        System.out.println("Prueba de datos 2 : x=1.1812 dof=10 Margen de error:0.000001 valor esperado=0.36757 valor actual=" + actual);
        assertEquals(0.35006, actual, 0.5);

        actual = calculador.calcularIntegralNumerica(10, 1.1, 9.0);
        System.out.println("Prueba de datos 3 : x=2.750 dof=30 Margen de error:0.000001 valor esperado=0.49500 valor actual=" + actual);
        assertEquals(0.35006, actual, 0.5);
    }
}
