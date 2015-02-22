package edu.uniandes.ecos.psp2.modelo;

/**
 * Clase que calcula una integral numérica, usando la regla de simpsom.
 *
 * @author JohnDany
 */
public class CalculadorIntegralNumerica {

    /**
     * Método que calcula el valor de una integral numérica usando la regla de
     * Simpson. No se valida la presición respecto al margen de error.
     *
     * @param numeroSegmentos Número de segmentos. El valor es par.
     * @param x es valor límite del rango a calcular.
     * @param dof (Degrees of freedom). Grados de libertad.
     * @return el valor de la integral numérica calculada.
     * @throws Exception lanzada en caso de error.
     */
    public Double calcularIntegralNumerica(int numeroSegmentos, Double x, Double dof) throws Exception {
        if (numeroSegmentos % 2 == 1) {
            throw new Exception("Se ingresó el número de segmentos impar. Se requiere un número par");
        }

        Double w = x / numeroSegmentos;
        Double parteFx1 = this.calculaParteDeFx(dof);

        Double resultado = 0.0;
        Double acumulado = 0.0;
        Double parcial = 0.0;
        Double fx = 0.0;
        int multiplicador = 0;
        for (int i = 0; i <= numeroSegmentos; i++) {
            if (i == 0 || i == numeroSegmentos) {
                multiplicador = 1;
            } else if (i % 2 == 0) {
                multiplicador = 2;
            } else {
                multiplicador = 4;
            }

            double parteFx2 = (1 + (Math.pow(acumulado, 2)) / dof);
            fx = parteFx1 * Math.pow(parteFx2, (-(dof + 1) / 2));

            parcial = (w / 3) * multiplicador * fx;
            resultado = resultado + parcial;
            acumulado = acumulado + w;
        }

        return resultado;
    }

    /**
     * Método que calcula el valor de una integral numérica usando la regla de
     * Simpson. No se valida la presición respecto al margen de error.
     *
     * @param numeroSegmentos Número de segmentos. El valor es par.
     * @param x es valor límite del rango a calcular.
     * @param e es el margen de error.
     * @param dof (Degrees of freedom). Grados de libertad.
     * @return el valor de la integral numérica calculada.
     * @throws Exception lanzada en caso de error.
     */
    public Double calcularIntegralNumericaEnMargenError(int numeroSegmentos, Double x, Double e, Double dof) throws Exception {
        int segmentos = numeroSegmentos;
        double ultimoResultado = this.calcularIntegralNumerica(segmentos, x, dof);
        double funcionRecalculada = ultimoResultado;

        do {
            segmentos = segmentos * 2;
            ultimoResultado = funcionRecalculada;
            funcionRecalculada = this.calcularIntegralNumerica(segmentos, x, dof);
        } while ((ultimoResultado - funcionRecalculada) > e);

        return funcionRecalculada;
    }

    /**
     * Método para calcula el factorial de un número.
     *
     * @param n indica el númerador del valor a calcularle la función gamma.
     * @param d indica el divisor del valor a calcularle la función gamma. Si el
     * número es un valor entero, colocar 1.
     * @return Gamma del número parametrizado.
     */
    public Double calcularGamma(Double n, Double d) {
        Double x = n / d;
        Double a = Math.floor(x);
        Double b = x - a;

        Double resultado = 1.0;
        if (b == 0.0) {
            for (int i = 1; i < x; i++) {
                resultado *= i;
            }
            return resultado;
        } else {
            double aa = 1.0;
            double bb = 1.0;
            for (int i = 1; i < n; i++) {
                if (i % 2 == 1) {
                    aa = aa * i;
                    bb = bb * d;
                }
            }
            resultado = (aa / bb) * Math.sqrt(Math.PI);

            return resultado;
        }
    }

    /**
     * Método que calcula una parte de la función Fx().
     *
     * @param dof (Degrees of freedom). Grados de libertad.
     * @return el valor calculado para el parámetro ingresado.
     */
    private Double calculaParteDeFx(Double dof) {
        Double a = this.calcularGamma((dof + 1), 2.0);
        Double b1 = (Math.pow((dof * Math.PI), 0.5));
        Double b2 = this.calcularGamma(dof, 2.0);
        Double b = b1 * b2;
        return a / b;
    }
}
