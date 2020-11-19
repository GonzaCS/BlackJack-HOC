import com.sun.xml.internal.fastinfoset.util.StringArray
import java.util.Random

open class Carta (val numero: Int, val palo: String){


    fun imprimir(){
        println("Numero: $numero  Palo: $palo")
    }
}

open class Baraja(){
    public final val NUM_CARTAS = 40
    val PALOS = arrayOf( "ESPADAS" ,"OROS", "COPAS", "BASTOS")
    val LIMITE_CARTA_PALO = 12
    final var posSiguienteCarta: Int =0
    var cartas= arrayOfNulls<Carta>(NUM_CARTAS)

    init {
        crearBaraja();
        barajar();
    }

    fun crearBaraja(){
        for (i in 0 until PALOS.size){
            for(j in 0 until LIMITE_CARTA_PALO){
                if(!(j == 7 || j==8)){
                    if(j >=9){
                        // Solo para Sota, Caballo y Rey
                        cartas[((i * (LIMITE_CARTA_PALO-2)) + (j-2))] = Carta(j+1,PALOS[i])
                    }else {
                        //Para los casos de 1 a 7
                        cartas[((i * (LIMITE_CARTA_PALO - 2)) + j)] = Carta(j + 1, PALOS[i]);
                    }
                }
            }
        }
    }

    fun barajar(){
        var posAleatoria:Int =0
        var carta:Carta
        val random= Random()

        for(i in 0 until cartas.size){
            posAleatoria= random.nextInt(((NUM_CARTAS-1) -0) + 0)

            carta= cartas[i]!!
            cartas[i] = cartas[posAleatoria]
            cartas[posAleatoria]= carta
        }

        posSiguienteCarta = 0
    }

    fun siguienteCarta():Carta?{
        var carta: Carta? = null

        if (posSiguienteCarta == NUM_CARTAS) {
            println("Ya no hay mas cartas, barajea de nuevo")
        }else{
            carta = cartas[posSiguienteCarta++]!!;
        }

       return carta
    }

    fun darCartas(numCartas:Int):Array<Carta?>?{


        if(numCartas>NUM_CARTAS)
            println("No se puede dar mas cartas de las que hay")
        else if(cartasDisponible() < numCartas)
            println("No hay suficientes cartas que mostrar")
        else{
            var cartasDar = arrayOfNulls<Carta>(numCartas)

            for(i in 0 until cartasDar.size)
                cartasDar[i]= siguienteCarta()

            return cartasDar
        }

        return null
    }

    fun cartasDisponible():Int{
        return NUM_CARTAS-posSiguienteCarta
    }


    fun cartasMonton(){
        if(cartasDisponible() == NUM_CARTAS)
            println("No se ha sacado ninguna carta")
        else{
            for(i in 0 until posSiguienteCarta){
                println(cartas[i]?.imprimir())
            }
        }
    }

    fun mostrarBaraja(){
        if(cartasDisponible() == NUM_CARTAS)
            println("No se ha sacado ninguna carta")
        else{
            for(i in posSiguienteCarta until cartas.size){
                println(cartas[i]?.imprimir())
            }
        }
    }


}

fun main(){
    var baraja: Baraja = Baraja()
    println("Hay ${baraja.cartasDisponible()} cartas disponibles")

    baraja.siguienteCarta()
    baraja.darCartas(5)

    println("Hay ${baraja.cartasDisponible()} cartas disponibles")

    println("Cartas sacadas de momento")
    baraja.cartasMonton()

    baraja.barajar()
    var cartas:Array<Carta?>? = baraja.darCartas(5)
    println("Cartas sacadas despues de barajar")
    if (cartas != null) {
        for(i in 0 until cartas.size){
            println(cartas[i]?.imprimir())
        }
    }

}