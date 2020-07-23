import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class general {
    // public static final double

    public static void main(String[] args) {
        
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
        char parteUser;

        try {

            System.out.println("Que parte desea comenzar? (¿1 o 2?)");
            parteUser = entrada.readLine().charAt(0);

            if (parteUser == '1'){
                simulacionParteUno();
            }

            if (parteUser == '2'){
                simulacionParteDos();
            }

        } 
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void simulacionParteUno() throws NumberFormatException, IOException {

        double altura, salidaDeAgua, entradaDeAgua, pasoTiempo, area, alturaAgua;
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Ingrese la altura (en m) del tanque:");
        altura = Double.valueOf(entrada.readLine());
        //6
        System.out.println("Ingrese el area (en m²) del tanque:");
        area = Double.valueOf(entrada.readLine());
        //3
        System.out.println("Ingrese el paso del tiempo (en seg.):");
        pasoTiempo = Double.valueOf(entrada.readLine());
        //0.5
        System.out.println("Ingrese la entrada de agua (en m³/s):");
        entradaDeAgua = Double.valueOf(entrada.readLine());
        //5
        System.out.println("Ingrese la salida de agua (en m³/s):");
        salidaDeAgua = Double.valueOf(entrada.readLine());
        //2
        System.out.println("Ingrese la altura del agua del tanque (en m):");
        alturaAgua = Double.valueOf(entrada.readLine());
        //2.4

        
        simulacionUNO(altura, area, pasoTiempo, entradaDeAgua, salidaDeAgua, alturaAgua);
    }
    
    public static void simulacionParteDos() throws NumberFormatException, IOException {
        
        double altura, entradaDeAgua, pasoTiempo, area, alturaAgua, K, W, G, salidaUser;
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Si no tiene ningun valor asignar un '0' ");
        System.out.println("");

        System.out.println("Ingrese la altura (en m) del tanque:");
        altura = Double.valueOf(entrada.readLine());
        //6
        System.out.println("Ingrese el area (en m²) del tanque:");
        area = Double.valueOf(entrada.readLine());
        //3
        System.out.println("Ingrese el paso del tiempo (en seg.):");
        pasoTiempo = Double.valueOf(entrada.readLine());
        //0.5
        System.out.println("Ingrese la entrada de agua (en m³/s):");
        entradaDeAgua = Double.valueOf(entrada.readLine());
        //5
        System.out.println("Ingrese la altura del agua del tanque (en m):");
        alturaAgua = Double.valueOf(entrada.readLine());
        //
        //
        System.out.println("Ingrese que parametro uso su salida. ¿K, W o G?");
        salidaUser = entrada.readLine().charAt(0);
        //
        //
        if ((salidaUser == 'k') || (salidaUser == 'K')){
        System.out.println("Ingrese el valor de K (en m³/s²):");

        K = Double.valueOf(entrada.readLine());
        
        simulacionDOS_K(altura, area, pasoTiempo, entradaDeAgua, alturaAgua, K);

        }

        else if ((salidaUser == 'w') || (salidaUser == 'W')){
        //
        System.out.println("Ingrese el valor de W (en m³/s³):");
        W = Double.valueOf(entrada.readLine());
        
        simulacionDOS_W(altura, area, pasoTiempo, entradaDeAgua, alturaAgua, W);
        }
        
        else if((salidaUser == 'g') || (salidaUser == 'G')){
        System.out.println("Ingrese el valor de G (en m⁴/s):");
        G = Double.valueOf(entrada.readLine());

        simulacionDOS_G(altura, area, pasoTiempo, entradaDeAgua, alturaAgua, G);
        //
        }

        
    }

    private static void simulacionDOS_K(double altura, double area, double pasoTiempo, double entradaDeAgua, double alturaAgua, double k) {
        double salidaDeAgua = 0;
        double alturaActual = alturaAgua;
        double volumenActual = alturaAgua * area;
        double volumenTotal = altura * area;
        double volumenMitad = volumenTotal / 2;
        int iterador = 0;
        double tiempo = 0;
        int iTanqueMedio = 0;
                    
        while ((volumenActual < volumenTotal) && (tiempo <= 1000000)){

            salidaDeAgua = k * tiempo;
            //S(t) = K * t 

            alturaActual = alturaActual + (entradaDeAgua-salidaDeAgua)*pasoTiempo/area;

            volumenActual = alturaActual * area;

            tiempo += pasoTiempo;
            iterador += 1;
            
            while ((volumenActual >= volumenMitad)&&(iTanqueMedio == 0)) {
                System.out.println("Se lleno a la mitad en " + tiempo + " segundos y en " + iterador + " iteraciones.");
                iTanqueMedio++;
            }
            if(tiempo == 1000000){
                System.out.println("No se llena nunca, pasaron 1.000.000 de segundos");
                System.exit(0);
            }
        }

        System.out.println("Se llena luego de " + tiempo + " segundos y " + iterador + " iteraciones");
        
        while(volumenActual > 0){

            salidaDeAgua = k * tiempo;

            alturaActual = alturaActual  - salidaDeAgua * pasoTiempo / area;

            volumenActual = alturaActual * area; 

            tiempo += pasoTiempo;
            iterador += 1;
        }

        System.out.println("Se vacia luego de " + tiempo + " segundos y " + iterador + " iteraciones.");

    }

    private static void simulacionDOS_W(double altura, double area, double pasoTiempo, double entradaDeAgua, double alturaAgua, double w) {
        double salidaDeAgua = 0;
        double alturaActual = alturaAgua;
        double volumenActual = alturaAgua * area;
        double volumenTotal = altura * area;
        double volumenMitad = volumenTotal / 2;
        int iterador = 0;
        double tiempo = 0;
        int iTanqueMedio = 0;
                    
        while ((volumenActual < volumenTotal) && (tiempo <= 1000000)){

            salidaDeAgua = w * (tiempo * tiempo);
            //S(t) = W * t²

            alturaActual = alturaActual + (entradaDeAgua-salidaDeAgua)*pasoTiempo/area;

            volumenActual = alturaActual * area;
            
            tiempo += pasoTiempo;
            iterador += 1;

            while ((volumenActual >= volumenMitad) && (iTanqueMedio == 0)){
                System.out.println("Se lleno a la mitad (por primera vez) en " + tiempo + " segundos y en " + iterador + " iteraciones.");
                iTanqueMedio++;
            }  
            if(tiempo == 1000000){
                System.out.println("No se llena nunca, pasaron 1.000.000 de segundos");
                System.exit(0);
            }
        }

        System.out.println("Se llena luego de " + tiempo + " segundos y " + iterador + " iteraciones.");
        
        while(volumenActual > 0){

            salidaDeAgua = w * (tiempo * tiempo);

            alturaActual = alturaActual  - salidaDeAgua * pasoTiempo / area;

            volumenActual = alturaActual * area; 

            tiempo += pasoTiempo;
            iterador += 1;
        }

        System.out.println("Se vacia luego de " + tiempo + " segundos y " + iterador + " iteraciones.");

    }

    
    private static void simulacionDOS_G(double altura, double area, double pasoTiempo, double entradaDeAgua, double alturaAgua, double g) {
        double salidaDeAgua = 0;
        double alturaActual = alturaAgua;
        double volumenActual = alturaAgua * area;
        double volumenTotal = altura * area;
        double volumenMitad = volumenTotal / 2;
        int iterador = 0;
        double tiempo = 0;
        int iTanqueMedio = 0;

                    
        while ((volumenActual < volumenTotal) && (tiempo <= 1000000)){

            

            salidaDeAgua = g/area * alturaActual; 
            //S(t) = G/A * h(t);

            alturaActual = alturaActual + (entradaDeAgua-salidaDeAgua)*pasoTiempo/area;

            volumenActual = alturaActual * area;

            tiempo += pasoTiempo;
            iterador += 1;

            while ((volumenActual >= volumenMitad) && (iTanqueMedio == 0)){
                System.out.println("Se lleno a la mitad (por primera vez) en " + tiempo + " segundos y en " + iterador + " iteraciones.");
                iTanqueMedio++;
            }  
            if(tiempo == 1000000){
                System.out.println("No se llena nunca, pasaron 1.000.000 de segundos");
                System.exit(0);
            }
        }

        System.out.println("Se llena luego de " + tiempo + " segundos y " + iterador + " iteraciones.");
        
        while((volumenActual > 0)){

            salidaDeAgua = g/area * alturaActual;

            alturaActual = alturaActual  - salidaDeAgua * pasoTiempo / area;
            
            volumenActual = alturaActual * area; 


            tiempo += pasoTiempo;
            iterador += 1;



            if(alturaActual <= Math.pow(10,-9)){
                break;
            }

        }

        System.out.println("Se vacia luego de " + tiempo + " segundos y " + iterador + " iteraciones.");

    }


    private static void simulacionUNO(double altura, double area, double pasoTiempo, double entradaDeAgua, double salidaDeAgua, double alturaAgua) {
        
        double alturaActual = alturaAgua;
        double volumenActual = alturaAgua * area;
        double volumenTotal = altura * area;
        double volumenMitad = volumenTotal / 2;
        int iterador = 0;
        double tiempo = 0;
        int iTanqueMedio = 0;
                    
        while ((volumenActual < volumenTotal) && (tiempo <= 1000000)){

            
            alturaActual = alturaActual + (entradaDeAgua-salidaDeAgua)*pasoTiempo/area;

            volumenActual = alturaActual * area;

            tiempo += pasoTiempo;
            iterador += 1;
            
            while ((volumenActual >= volumenMitad)&&(iTanqueMedio == 0)) {
                System.out.println("Se lleno a la mitad en " + tiempo + " segundos y en " + iterador + " iteraciones.");
                iTanqueMedio++;
            }

            if(tiempo == 1000000){
                System.out.println("No se llena nunca (almenos que tome mas de 1.000.000 de segundos)");
                System.exit(0);
            }
        }

        System.out.println("Se llena luego de " + tiempo + " segundos y " + iterador + " iteraciones.");
        
        while(volumenActual > 0){

            alturaActual = alturaActual  - salidaDeAgua * pasoTiempo / area;

            volumenActual = alturaActual * area; 

            tiempo += pasoTiempo;
            iterador += 1;
        }

        System.out.println("Se vacia luego de " + tiempo + " segundos y " + iterador + " iteraciones.");
    }
}